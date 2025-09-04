#!/bin/bash

if [ ! $# -eq 1 ];then
    echo "Input arguments"
    exit 1
fi

if [ ! -d $1 ];then
    echo "Копирајте ја вашата скрипта овде."
    exit 1
fi


files=$( ls -la $1 | awk '$9 ~ '/\.rez$/' { print $9 }' )

touch resultsTmpFile

for file in $files;do
    results=$(awk -F"," '
    { 
        if($1 == "w" || $1 == "d" ){ 
            print $2 >> "resultsTmpFile"
        }
    }' "$file")
done

cat resultsTmpFile | sort | uniq

rm resultsTmpFile