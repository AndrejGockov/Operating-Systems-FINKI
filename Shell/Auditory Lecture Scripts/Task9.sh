#!/bin/bash

if [ ! $# -eq 1 ];then
    echo "Parameter wasn't passed in correctly"
    exit 1
fi

num=$1
ans=$(echo "scale=2; 3.5 * $num" | bc)

echo $ans