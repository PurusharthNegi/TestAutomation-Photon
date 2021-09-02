package com.pom_RWD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RWD_ReadingChallengeAcceptRejectChallenge extends CapabilitiesAndWebDriverUtils {

	public RWD_ReadingChallengeAcceptRejectChallenge() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[text()=' Accept Challenge']")
	private WebElement RWDacceptChallengeBtn;

	@FindBy(xpath = "//*[@class=\"challenge-board\"]")
	private WebElement RWDchallengeBoard;

	public WebElement getRWDacceptChallengeBtn() {
		
		return RWDacceptChallengeBtn;
	}

	public WebElement getRWDchallengeBoard() {
		return RWDchallengeBoard;
	}

}
