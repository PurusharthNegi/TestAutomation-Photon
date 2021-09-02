package com.pom_RWD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RWD_EditChallengeUpDateRcDetailsDcreen extends CapabilitiesAndWebDriverUtils {
	public RWD_EditChallengeUpDateRcDetailsDcreen() {
		PageFactory.initElements(driver, this);

	}
	
	
	@FindBy(xpath = "//*[text()=\"EDIT CHALLENGES \"] ")
	private WebElement RWDEditChallenge;


	public WebElement getRWDEditChallenge() {
		return RWDEditChallenge;
	}

}
