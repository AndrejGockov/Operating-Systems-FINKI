#!/bin/bash

command=$(netstat -r | grep ^[0-9])
commandOutput=$( echo "$command" | awk '{print $NF}' | sort | uniq)

for interface in $commandOutput; do
    count=$( echo "$commandOutput" | grep -c "$interface")
    echo $count
done