#!/bin/bash

file="CocaCola.txt"

if [ ! -f $file ];then
    echo "File doesn't exist"
    exit
fi

currDate=$(date)
day=$( awk -F' ' '{print $3}' <<< "$currDate")
month=$( awk -F' ' '{print $2}' <<< "$currDate")

awk -F' ' -v month="$month" -v day="$day" ' $3 == month && $4 == day { print $1" "$2" | " $6 " " $7; }' "$file"