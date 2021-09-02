package com.pom_RWD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RWD_ReadingChallengeChallengeAcceptedConfirmation extends CapabilitiesAndWebDriverUtils{
public RWD_ReadingChallengeChallengeAcceptedConfirmation()  {
	PageFactory.initElements(driver, this);

		
	}

@FindBy(xpath="//*[text()=' Challenge Accepted! ']")
private WebElement RWDChallengeAcceptedText;

public WebElement getRWDChallengeAcceptedText() {
	return RWDChallengeAcceptedText;
}


}
