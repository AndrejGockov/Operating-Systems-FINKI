Shell Script CSV Analysis Assignment
Objective

Create a shell script that can parse and analyze a CSV file containing student exam scores. You need to submit your final solution as .sh file.
Task Requirements
Input File Specification

The input CSV file will have the following format:

StudentID,Name,Math,Science,English,History
1001,Alice Johnson,85,92,78,88
1002,Bob Smith,90,85,92,79
...

In addition, find three .csv files (small.csv, complex.csv, edge_cases.csv), your script should handle the three different files as inputs.
Script Functionality

Your shell script must:

    Accept the CSV file path as the first command-line argument
    Validate the input file
    Perform the following analyses:
        Calculate and display the total number of students
        Calculate and display the average score for each subject
        Identify and display the highest and lowest scoring student in each subject
        Provide a clear, formatted output

Grading Criteria

The script will be evaluated based on the following aspects:
Validation (20 points)

    Correct shebang line (#!/bin/sh or #!/bin/bash)
    Proper file and argument validation
    Error handling for invalid inputs
    Script must be executable

Functionality (80 points)

    Correct calculation of total students
    Accurate average score calculations
    Proper identification of highest and lowest scoring students
    Handling of different input scenarios (small, large, edge cases)
    Clear and informative output formatting

Specific Technical Requirements

    Use shell scripting techniques (awk, sed, etc.)
    Handle potential errors gracefully
    Efficiently process the CSV file
    Remove the header before processing data

Example Expected Output

## Exam Scores Analysis

Total Number of Students: 3

Subject Averages:
Math: 83.33
Science: 85.67
English: 85.00
History: 87.33

Subject Extreme Performers:
Math - Highest: Bob Smith (Score: 90), Lowest: Charlie Brown (Score: 75)
Science - Highest: Alice Johnson (Score: 92), Lowest: Charlie Brown (Score: 80)
English - Highest: Bob Smith (Score: 92), Lowest: Alice Johnson (Score: 78)
History - Highest: Charlie Brown (Score: 95), Lowest: Bob Smith (Score: 79)

Submission Guidelines

    Save your script with a .sh extension
    Ensure the script has executable permissions (chmod +x your_script.sh)
    Test your script with various input files

Hints

    Use awk for advanced text processing
    Utilize shell script functions to modularize your code
    Use temporary files if needed
    Handle potential errors and edge cases

Recommended Tools

    awk, sed, cut, sort, uniq

Evaluation Notes

    The grading script will test your submission with multiple input files
    Points will be deducted for syntax errors, incorrect outputs, or poor error handling
    Partial credit will be given for partially correct implementations

If you have any questions, please visit some of the lab terms
