package com.pom;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SmokeRP  extends CapabilitiesAndWebDriverUtils{
	
	public SmokeRP() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}

	
	@iOSXCUITFindBy(accessibility = "My Programs")
	public static MobileElement myProgramstap;
	
	@iOSXCUITFindBy(accessibility = "Open Programs")
	public static MobileElement openProgramstap;
	
	@iOSXCUITFindBy(accessibility = "ACTIVE PROGRAMS")
	public static MobileElement activeprogramstxt;
	
	
	@iOSXCUITFindBy(accessibility = "MyProgram_2021-05-20-17:40:58")
	public static MobileElement programName;
	
	@iOSXCUITFindBy(accessibility = "MyProgram_2021-05-20-17:40:58")
	public static MobileElement programimage;
	
	@iOSXCUITFindBy(accessibility = "(//XCUIElementTypeStaticText[@name=\"READING CHALLENGE: READ BY 06/17/2021\"])[1]")
	public static MobileElement readyByDate;
	
	
	
}
