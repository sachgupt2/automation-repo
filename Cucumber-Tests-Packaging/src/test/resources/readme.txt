Steps to trigger automation suite:

* Prerequisites
    
    + Java1.8 or later version should be installed.
    + Your machine should be Windows.

1. Edit file api.porporties in directory <zip-extract-directory>\properties and set values for base_url, user_name, password, terminal_id

2. Open command prompt, navigate to directory where zip file is extracted:

	E:\Execution>cd <zip-extract-directory>\bin

3. To trigger automation run command :

	E:\Execution\TestAutomation\bin>Trigger_Automation.bat

4. To generate report, run command:

	E:\Execution\TestAutomation\bin>Generate_Report.bat

5. Report will be generated in following directory:

	<zip-extract-directory>\Execution-Report\cucumber-html-reports\cucumber-html-reports

6. Open file 'overview-features.html' in browser to view the report.




