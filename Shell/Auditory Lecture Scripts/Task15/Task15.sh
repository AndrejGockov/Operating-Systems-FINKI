#!/bin/bash

if [ $# -ne 1 ];then
    echo "No file given"
    exit 1
fi

if [ ! -f $1 ];then
    echo "out.txt doesn't exist in current directory"
    exit 1
fi

if [ ! -r $1 ];then
    echo "out.txt can't be read"
    exit 1
fi


file="$1"
timeLoggedIn=$(awk '{print $NF}' "$file")

totalMinutes=0

for time in $timeLoggedIn;do
    time=${time//[()]/}
    IFS=: read hours minutes <<< "$time"
    # echo $hours $minutes
    
    totalMinutes=$((totalMinutes + (hours * 60)))
    totalMinutes=$(( totalMinutes + minutes ))
done

# Appending to the end of the file
echo $totalMinutes >> out.txt

# Overwriting
# echo $totalMinutes >> out.txt

# Shows result
echo $totalMinutes