@echo off

SET rootDirectoryUnClean=%~dp0\..

for %%i in ("%rootDirectoryUnClean%") do SET "rootDirectory=%%~fi"

echo Test tracking tool root location is %rootDirectory%

IF EXIST %rootDirectory%\Execution-Report (
rmdir /q /s %rootDirectory%\Execution-Report
)
mkdir %rootDirectory%\Execution-Report

pushd .

cd /D %rootDirectory%\bin

popd

java -cp %rootDirectory%\lib\* net.masterthought.cucumber.sandwich.CucumberReportMonitor -n -f %rootDirectory%\Report\json -o %rootDirectory%\Execution-Report

echo.

echo ################################## Execution Report ##########################################################

echo.

echo Report generated in directory %rootDirectory%\Execution-Report\cucumber-html-reports\cucumber-html-reports

echo.

echo Open file 'overview-features.html' in browser to view the report.

echo.
echo ##############################################################################################################