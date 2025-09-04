#!/bin/bash

if [ $# -ne 2 ];then
    echo "No parameteres given"
    exit 1
fi

if [[ ! "$1" =~ ^[0-9]+$ ]];then
    echo "Parameter must be a number"
    exit 1
fi

if [ ! -d "$2" ];then
    mkdir "$2"
fi

for file in *;do
    if [ $file = "Task17.sh" ];then
        continue
    fi
    
    if [ -d $file ];then
        continue
    fi

    echo "$file"
done