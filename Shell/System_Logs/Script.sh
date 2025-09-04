#!/bin/bash

# Checks if right num of arguments is entered
if [ ! $# -eq 2 ];then
    echo "Wrong number of parameters"
    exit 1;
fi

# Checks if right status is entered
if [[ ! ("$1" = "DEBUG" || "$1" = "INFO" || "$1" = "WARN" || "$1" = "ERROR") ]];then
    echo "Wrong status format entered"
    exit 1;
fi

# Checks if the right date format was entered
if [[ ! "$2" =~  ^[0-9]{4}-[0-9]{2}$ ]];then
    echo "Wrong date format entered"
    exit 1;
fi

file="system_logs.txt"
status=$1
date=$2
month="${date:5:7}"

# Logs per month for the given status
logsInMonth=$(awk -v stat="$status" -v month="$month" ' NR > 1 && stat == $3 && month == substr($1, 6, 2) { count++ } END{ print count }' "$file")

# Most common IP address
mostCommonIP=$(awk -F"\t", -v stat="$status" 'NR > 1 { print As }' "$file")

# Counts appearance of each status in the given month
debug=$(awk -v stat="DEBUG" -v month="$month" 'BEGIN{ count=0 } NR > 1 && stat == $3 && month == substr($1, 6, 2) { count++ } END{ print count }' "$file")
info=$(awk -v stat="INFO" -v month="$month" 'BEGIN{ count=0 } NR > 1 && stat == $3 && month == substr($1, 6, 2) { count++ } END{ print count }' "$file")
warn=$(awk -v stat="WARN" -v month="$month" 'BEGIN{ count=0 } NR > 1 && stat == $3 && month == substr($1, 6, 2) { count++ } END{ print count }' "$file")
error=$(awk -v stat="ERROR" -v month="$month" 'BEGIN{ count=0 } NR > 1 && stat == $3 && month == substr($1, 6, 2) { count++ } END{ print count }' "$file")


echo "Number of logs with status '" $1 "' in month " $2 ": " $logsInMonth
echo "Most common IP address for logs with status '" $1 "': " $mostCommonIP

echo "Count per status for month " $date ":"
echo "DEBUG:" $debug
echo "INFO:" $info
echo "WARN:" $warn
echo "ERROR:" $error
