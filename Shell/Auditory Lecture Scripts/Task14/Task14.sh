#!/bin/bash

for file in *;do
    # Skips itself so the shell file doesn't get displaced
    if [ "$file" = "Task14.sh" ];then
        continue;
    fi
    
    fileLength=${#file}
    
    # Check if the file's name is even or odd and moves them to correct folder
    if [ $((fileLength%2)) -eq 0 ];then
        if [ ! -d "Even" ];then
            mkdir "Even"
        fi
        
        mv "$file" "Even"
    else
        if [ ! -d "Odd" ];then
            mkdir "Odd"
        fi
        
        mv "$file" "Odd"
    fi
done