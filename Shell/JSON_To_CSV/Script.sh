#!/bin/bash

if [ ! $# -eq 2 ];then
    echo "Input arguments"
    exit 1
fi

if [ ! -f $1 ] || [ ! $1 = *json ];then
    echo "File doesn't exist or it isn't a json file"
    exit 1
fi

if [ ! -d $2 ];then
    echo "Directory doesn't exist"
    exit 1
fi

touch $2/output.csv
echo "id, filepath, filesize, is_longer" >> $2/output.csv

# Filters out the {}[] lines from the json file
filterParenthesis=$(grep -v -E '{|}|\[|\]' $1)

awk -F' ' -v outdir="$2" '
BEGIN{
    id=1;
    pathCnt=0;
    durationCnt=0;
    sizeCnt=0;
}

# Filters based on what the line starts with
# Removes the "" from the strings
{
    if($1 == "\"filepath\":"){
        filePath[pathCnt]=substr($2, 2, length($2) - 3);
        pathCnt++;
    }

    if($1 == "\"duration\":"){
        duration[durationCnt]=substr($2, 1, length($2) - 1);
        durationCnt++;
    }

    if($1 == "\"filesize\":"){
        fileSize[sizeCnt]=substr($2, 2, length($2) - 2);
        sizeCnt++;
    }
}

END{
    # Gets the average duration length
    average=0;
    for(i=0; i < durationCnt; i++){
        average+=duration[i];
    }
    average/=durationCnt
    
    for(i=0; i < pathCnt; i++){
        isLonger=0
        if(strtonum(duration[i]) > average){
            isLonger=1
        }
        
        # Adds new line to the output.csv
        newLine=id", "filePath[i]", "fileSize[i]", "isLonger
        print newLine >> (outdir"/output.csv")
        
        id++;
    }
}' <<< "$filterParenthesis"