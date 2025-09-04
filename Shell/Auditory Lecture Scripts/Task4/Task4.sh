#!/bin/bash

unzip '*.zip'
gunzip  '*.zip'
bunzip  '*.zip'

# for file in *; do
#     echo "$file";

#     if [ "$file" == *.zip ] || [ "$file" == *.gzip ] || [ "$file" == *.bzip2 ]; then
#         echo "ZIP FOUND"
#         unzip '$file'
#     fi
# done