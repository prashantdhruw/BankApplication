#!/bin/bash

# Set the output file name
output_file="file_contents.txt"

# Function to process each file
process_file() {
    local file="$1"
    echo "File: $file" >> "$output_file"
    echo "----------------------------------------" >> "$output_file"
    cat "$file" >> "$output_file"
    echo -e "\n\n" >> "$output_file"
}

# Create or clear the output file
> "$output_file"

# Find .java, .properties, and .xml files recursively in the current directory
# and process each file
while IFS= read -r -d '' file; do
    process_file "$file"
done < <(find . \( -name "*.java" -o -name "*.properties" -o -name "*.xml" \) -type f -print0 | sort -z)

# Print a message indicating where the file contents have been saved
echo "File contents have been saved to $output_file"

# Display the first few lines of the output file
echo "First few lines of $output_file:"
head -n 20 "$output_file"
