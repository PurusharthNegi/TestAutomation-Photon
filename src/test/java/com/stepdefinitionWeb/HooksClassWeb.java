package com.stepdefinitionWeb;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.base.CapabilitiesAndWebDriverUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;

public class HooksClassWeb extends CapabilitiesAndWebDriverUtils {
	//WebDriver driver;

	Scenario scenario;
	String screenshotdir = System.getProperty("user.dir") + "/target/test-output/Screenshots/";

	@Before
	public void beforMethodSetUp(Scenario scenario) throws Throwable {
		this.scenario = scenario;
		if ((new File(screenshotdir)).exists())
			FileUtils.cleanDirectory(new File(screenshotdir));
		//appiumStart();
		System.out.println("Get In To Invoke app");
		initiateNativeAPP();
	}

	@AfterStep
	public void attach_screenshot(Scenario scenario) throws Throwable {
		 if(scenario.isFailed()){
//		// ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getBase64Screenshot(driver,screenshotdir));
//		// //for html
//		scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png"); // for spark
	
//			 if (getData("platformName").equalsIgnoreCase("android") || (getData("platformName").equalsIgnoreCase("IOS"))){
////					ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getBase64Screenshot(driver,screenshotdir)); //for html
//					scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png"); //for spark
//					
//					} else {
//						
//						ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getBase64Screenshot(driver1,screenshotdir)); //for html
						
						scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png"); //for spark
						
					}
		
	}
//	@Before
//	public void before(Scenario scenario) throws Exception {
//		appiumStart();
//		System.out.println("Get In To Invoke app");
//		new ReusableMethods().initiateNativeAPP();
//	}


//	@After
//	public void afterClass(Scenario scenario) throws Exception {
//		if (scenario.isFailed()) {
//			System.out.println("Scenario Failed...Taking screenshot....");
//			takeScreenShotonFailure(scenario);
//		}
//		appiumStop();
//	}

//	@After
//	public void tearDown() throws Exception {
////		try {
////		if(s.isFailed()) {
////				TakesScreenshot tk= (TakesScreenshot)driver;
////				byte[] screenShot = tk.getScreenshotAs(OutputType.BYTES);
////		s.embed(screenShot, "images/png");
////
////			}
////		}catch (Exception e){
////
////		}finally {
////
//			driver.quit();

	//}
			
			}

