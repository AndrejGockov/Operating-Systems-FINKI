#!/bin/bash

if [ ! $# -eq 1 ];then
    echo "Wrong number of parameters inputted"
    exit 1
fi

if [ ! $1 == *csv ];then
    echo "Must be a csv file"
    exit 1
fi

if [ ! -r $1 ];then
    echo "File must be readable"
    exit 1
fi

file=$1

touch "passed_${file}"
passed="passed_${file}"

# Overwrites file if it existed
> $passed

passedStudents=$( awk -F, 'NR > 1 { print $4 }' "$file")
averageGrade=$( awk -F, 'NR > 1 && $4 >= 5 { sum+=$4; count++; } END{ if(count > 0) print sum/count; else print 0; }' "$file")

awk -F, 'NR > 1 && $4 >= 5' "$file" >> $passed
echo "Passed students average points: "$averageGrade >> $passed