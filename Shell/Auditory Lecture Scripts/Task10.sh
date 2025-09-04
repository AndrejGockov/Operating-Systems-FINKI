#!/bin/bash

if [ $# -lt 1 ];then
    echo "No parameters were given"
    exit 1
fi

echo $@ | tr '[A-Z]' '[a-z]'

# for par in $@;do
#     echo "$par" | tr '[:upper:]' '[:lower:]'
# done