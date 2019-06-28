package com.cucumber.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith( Cucumber.class )
@CucumberOptions( plugin = {"pretty", "json:target/JsonReport/cucumber.json",
                            "html:target/HtmlReport", "junit:target/JUnitReport/cucumber.xml",
                            "rerun:target/rerun.txt"},
                  features = "src/test/resources/featureFiles/api",
                  glue = {"com.cucumber.api.stepdefs"},
                  tags = {"@BVT"}, dryRun = false )

public class CucumberRunner
{

}