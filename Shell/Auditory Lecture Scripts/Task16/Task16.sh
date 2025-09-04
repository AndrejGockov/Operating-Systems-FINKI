#!/bin/bash

if [ $# -ne 1 ];then
    echo "No user was given"
    exit 1
fi

if [ -f "out.txt" ];then
    rm "out.txt"
fi

touch "out.txt"

for proc in $(ps | grep "$1" | awk '{ print $2; }');
do
    subProcesses=0
    
    for subProc in $(ps | grep "$1" | awk '{ print $3; }');
    do
        if [ "$proc" = "$subProc" ];then
            ((subProcesses++))
        fi
    done
    echo "$proc $subProcesses" >> "out.txt"
done

cat out.txt