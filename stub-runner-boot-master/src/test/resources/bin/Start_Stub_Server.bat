@echo off

SET rootDirectoryUnClean=%~dp0\..

for %%i in ("%rootDirectoryUnClean%") do SET "rootDirectory=%%~fi"

echo Stub Runner root location is %rootDirectory%

pushd .

cd /D %rootDirectory%\bin

popd

java -Dstubrunner.stubs-mode=local -Dstubrunner.ids=com.baeldung.spring.cloud:spring-cloud-contract-producer:+:stubs:8091 -jar %rootDirectory%\lib\stub-runner-boot-stub-server.jar