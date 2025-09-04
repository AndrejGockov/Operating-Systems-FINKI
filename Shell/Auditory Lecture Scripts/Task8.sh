#!/bin/bash

if [ ! $# -eq 2 ];then
    echo "Must give 2 parameters only"
    exit 1
fi

oldName=$1
newName=$2

for file in *;
do
    if [ ! -f $file ];then
        continue
    fi
    
    if [ "$file" == "$oldName" ];then
        mv "$file" "$newName"
        echo "File renamed"
    fi

    echo $file
done