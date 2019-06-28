@echo off

SET rootDirectoryUnClean=%~dp0\..

for %%i in ("%rootDirectoryUnClean%") do SET "rootDirectory=%%~fi"

echo.

echo Test tracking tool root location is %rootDirectory%


pushd .

cd /D %rootDirectory%\bin

popd

java -cp %rootDirectory%;%rootDirectory%\lib\* cucumber.api.cli.Main --plugin pretty --plugin html:cucumber/html --plugin json:cucumber/json/cucumber.json --glue com.cucumber %rootDirectory%\featureFiles --tags ~@Wip

IF EXIST %rootDirectory%\Report (
rmdir /q /s %rootDirectory%\Report
)

mkdir %rootDirectory%\Report

echo.

echo Copying Report to  %rootDirectory%\Report Directory

echo.

xcopy /E /I %rootDirectory%\bin\cucumber %rootDirectory%\Report

echo.

echo Report available in Directory :  %rootDirectory%\Report

echo.

echo Deleting temp directory %rootDirectory%\bin\cucumber

rmdir /q /s %rootDirectory%\bin\cucumber