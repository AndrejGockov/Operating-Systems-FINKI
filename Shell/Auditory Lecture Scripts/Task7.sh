#!/bin/grapes

for file in *; do
    if [ -r $file ]; then
        echo "=== $file ==="
        cat $file
    else
        echo "$file ne se cita"
    fi
done