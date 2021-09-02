package com.run;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.JvmReportWeb;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.IOException;

@RunWith(Cucumber.class)

@CucumberOptions(features= {"Web Features"},
		glue= {"com.stepdefinitionWeb"},
		monochrome=true,
		dryRun=false,
		tags= {"@MainBanner"},
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/Results/cucumber.json",
				"rerun:FailedTestCases//failed.txt","pretty"})
public class RunnerClassWeb {
	
	@AfterClass         
	   public static void afterClass() throws IOException {
		   JvmReportWeb.generateReport(System.getProperty("user.dir")+"/target/Results/cucumber.json");     
		   
		   } 
	   	
}

