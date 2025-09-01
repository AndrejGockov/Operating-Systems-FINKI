#!/bin/bash

if [ ! $# -eq 1 ];then
    echo "Input wasn't properly entered"
    exit 1
fi

if [ ! -f "$1" ] || [ ! -r "$1" ];then
    echo "Wrong path to file or file is unreadable"
    exit 1
fi

csvFile="$1"
studentCount=$(awk -F "," 'NR > 1 {} END{print NR - 1}' "$csvFile")

if [ $studentCount -lt 1 ];then
    echo "No students in the table"
    exit 1
fi

IFS=',' read -r -a header < "$csvFile"

echo "Exam Scores Analysis"
echo "-------------------"
echo "Total Number of Students:" $studentCount
echo ""
echo "Subject Averages:"


for ((i = 2; i < ${#header[@]}; i++));do
    average=$(awk -F, -v col="$((i+1))" 'NR > 1 && $col ~ /^[0-9]+$/ { sum+=$col; count++; } END{ printf "%.2f", sum/count; }' "$csvFile")
    
    echo ${header[i]} " - " $average
done

echo ""
echo "Subject Highest and Lowest Scores:"

for ((i = 2; i < ${#header[@]}; i++)); do
    echo -n ${header[i]}
    
    awk -F, -v col="$((i + 1))" '
    NR > 1 && $col ~ /^[0-9]+$/ {
        if(NR == 2 || $col > max){
            max = $col;
            maxName = $2;
        }
        if(NR == 2 || $col < min){
            min = $col;
            lowName = $2;
        }
    }
    END{
        printf " - Highest: %s (Score: %d), Lowest: %s (Score: %d)", maxName, max, lowName, min;
        printf "\n"
    }' "$csvFile"
done