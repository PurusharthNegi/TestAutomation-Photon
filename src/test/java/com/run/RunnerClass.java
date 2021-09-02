package com.run;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.JvmReportMobile;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features= {"Mobile Features"},
		glue= {"com.stepdefinition"},
		monochrome=false,
		dryRun=false,
		tags= {"@Reading_Challenge_others"},

		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/ResultsMobile/cucumber.json",
	"rerun:FailedTestCases\\failed.txt","pretty"})

//@RC_Smoke,@Reading_Challenge_Rejection,@Reading_Challenge_others,@Reading_Challenge_LeaveChallenge,@Message_Center
//@Reading_Challenge,@Reading_Challenge_Rejection,@Reading_Challenge_LeaveChallenge,@Reading_Challenge_others
public class RunnerClass extends CapabilitiesAndWebDriverUtils{
	 @AfterClass         
	   public static void afterClass() throws IOException {                 
		   JvmReportMobile.generateReport(System.getProperty("user.dir")+"/target/ResultsMobile/cucumber.json");  
		   
		   }
}  