#!/bin/bash

files='ls'

select file in $files "Exit Program"
do
    if [ -z "$file" ];then
        continue
    fi
    
    if [ "$file" = "Exit Progam" ];then
        break
    fi
    
    if [ -f "$file" ]; then
        echo "File doesn't exist"
        continue
    fi
    
    cat $file
done