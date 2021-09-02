package com.run;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="@DestinyDiscover\\FailedTestCases\\failed.txt",monochrome=true,glue="com.step")
public class FailedCaseRunner {
	

}
