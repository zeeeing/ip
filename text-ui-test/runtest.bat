@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM reset data file
if exist data\tasks.txt del data\tasks.txt

REM compile all sources into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ^
  ..\src\main\java\buddy\Buddy.java ^
  ..\src\main\java\buddy\commands\*.java ^
  ..\src\main\java\buddy\exceptions\*.java ^
  ..\src\main\java\buddy\parser\*.java ^
  ..\src\main\java\buddy\storage\*.java ^
  ..\src\main\java\buddy\tasks\*.java ^
  ..\src\main\java\buddy\ui\*.java ^
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Buddy < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
