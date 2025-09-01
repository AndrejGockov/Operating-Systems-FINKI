# Write a command procedure that will receive an arbitrary number of input arguments representing execution times of a program expressed in minutes.

# Example - bash script.sh 5 7 15 8 22 6 

# The procedure should calculate and print the average duration of the program according to the first three executions (5, 7, 15 in the example) represented in seconds, as well as the number of program executions (the number of measurements made/entered).

# If the number of input arguments is grater than or equal to 5, it should print 'The testing is done', while if it is less than 5, it should print 'More testing is needed'.

# The output of the example should be as follows:
# Average execution time: 540
# Count of executions: 6
# The testing is done

#!/usr/bin/bash

if [ $# -eq 0 ];then
    echo "No inputs given. Cannot do tests"
    exit 1
fi

elemCount=0
average=0

for i in {1..3};do
    # Breaks loop if there are less than 3 elements given
    if [ $i -gt $# ];then
        break
    fi
    
    average=$(( average + "${!i}"))
    elemCount=$((elemCount + 1))
done

average=$(( (average * 60) / elemCount ))


# Print results
echo "Average execution time: $average"
echo "Count of executions: $#"

if [ $# -gt 4 ];then
    echo "The testing is done"
else
    echo "More testing is needed"
fi