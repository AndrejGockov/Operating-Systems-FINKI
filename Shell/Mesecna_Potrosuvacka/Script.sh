#!/bin/bash

if [ ! $# -eq 2 ];then
    echo "Wrong number of input parameters"
    exit 1
fi

file="mesecna_potrosuvacka.csv"

if [ ! -r $file ];then
    echo "File isn't readable"
    exit
fi

startMonth=$1
endMonth=$2

awk -F, -v firstMonth="$startMonth" -v secondMonth="$endMonth" '
    BEGIN{
        countOne=0;
        countTwo=0;
    }
    NR > 1 {
        if (firstMonth == $3) {
            firstMonthNames[countOne]=$1;
            firstMonthSpending[countOne]=$5;
            countOne++;
        }
    
        if(secondMonth == $3){
            secondMonthNames[countTwo]=$1;
            secondMonthSpending[countTwo]=$5;
            countTwo++;
        }
    }
    
    END{
        for(monthOneName in firstMonthNames){
            for(monthTwoName in secondMonthNames){
                if(firstMonthNames[monthOneName] == secondMonthNames[monthTwoName] && firstMonthSpending[monthOneName] > secondMonthSpending[monthTwoName]){
                    print firstMonthNames[monthOneName];
                }
            }
        }
    }' "$file"