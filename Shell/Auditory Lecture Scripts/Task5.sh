#!/bin/bash

if [ $# -eq 0 ];then
    echo "Number not entered"
    exit 1
fi

count=$1

while [ $count -gt 0 ]
do
    echo $count
    ((count--))
    sleep 1
done

echo "Competition's started"