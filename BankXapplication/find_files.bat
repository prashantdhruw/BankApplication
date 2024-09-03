@echo off
setlocal enabledelayedexpansion

:: Set the output file name
set "output_file=file_contents.txt"

:: Create or clear the output file
type nul > "%output_file%"

:: Find .java, .properties, and .xml files recursively in the current directory
:: and process each file
for /r %%F in (*.java *.properties *.xml *.html *.css) do (
    echo File: %%F >> "%output_file%"
    echo ---------------------------------------- >> "%output_file%"
    type "%%F" >> "%output_file%"
    echo. >> "%output_file%"
    echo. >> "%output_file%"
)

:: Print a message indicating where the file contents have been saved
echo File contents have been saved to %output_file%

:: Display the first few lines of the output file
echo First few lines of %output_file%:
powershell -command "if (Test-Path '%output_file%') { Get-Content '%output_file%' -TotalCount 20 } else { Write-Host 'Output file not found.' }"

endlocal