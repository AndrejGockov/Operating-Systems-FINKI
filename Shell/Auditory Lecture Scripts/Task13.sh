#!/bin/bash

if [ ! -f "fruits.txt" ];then
    echo "Fruits.txt doesnt exist"
    exit 1
fi

file="fruits.txt"

awk '{
    for(i = NF; i >= 1; i--){
        printf("%s | ", $i);
    }
    printf("\n");
}
' $file