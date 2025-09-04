#!/bin/bash

count=0

while [ $count -le 20 ]
do
    echo $count
    ((count++))
done

# for i in $(seq 0 20)
# do
#     echo $i
# done