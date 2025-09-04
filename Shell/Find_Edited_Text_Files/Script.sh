#!/bin/nash

getFiles(){
    # Directory
    directory=$3
    
    for file in *;do
        # Skips if its a directory
        if [ -d $file ];then
            continue
        fi
        
        # Gets file's stats, then filters out only the dates for this month
        fileDetails=$(stat -c %y "$file")
        filterDay="${fileDetails:8:2}"
        
        # Gets the file's size
        fileSize=$( wc -c < "$file" )
        
        if [ $filterDay -ge $1 ] && [ $filterDay -le $2 ] && [[ $file == *txt ]] && [ $fileSize -gt 150 ];then
            # Copies file to new location
            cp $file $directory
            newFile=$directory"/"$file

            # Gets the number of times "echo" appeared in the files
            echoCount=$( grep -o 'echo' "$newFile" | wc -l )
            
            # Checks if the file already existed before renaming it
            newName=$directory"/232120_"$echoCount".txt"
            if [ ! -f $newName ];then
                mv $newFile $newName
                continue
            fi

            findAllTxtFiles $directory $newName $echoCount $newFile
        fi
    done
}

findAllTxtFiles(){
    sameFileCount=0
    
    for file in $1/*;do
            if [ $2 = $file ];then
            sameFileCount=$((sameFileCount+1))
        fi
    done

    finalName=$1"/232120_$3$sameFileCount.txt"
    # echo $1
    mv $4 $finalName
}

if [ ! $# -eq 3 ];then
    echo "Wrong number of inputs"
    exit 1
fi

if [[ ! "$1" =~ ^[0-9]{2}$ ]] || [[ ! "$2" =~ ^[0-9]{2}$ ]];then
    echo "Date numbers entered improperly"
    exit 1
fi

if [ -d $3 ];then
    rm -r $3
fi

mkdir $3
getFiles $1 $2 $3