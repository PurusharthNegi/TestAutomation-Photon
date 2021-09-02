package com.pom;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReadingChallengeChallengeAcceptedConfirmation extends CapabilitiesAndWebDriverUtils{
public ReadingChallengeChallengeAcceptedConfirmation()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}


@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/accepted_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Challenge Accepted!\"]")
private MobileElement AccpetedText;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/user_info_text")
@iOSXCUITFindBy(xpath="//*[@x='635']")
private MobileElement userInfoText;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/go_to_challenge_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Go to Challenge\"]")
private MobileElement go_to_challeng;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/close_image_view")
@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"CloseLightBlue\"]")
private MobileElement closeicon;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/share_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Share\"]")
private MobileElement shareButton;

public MobileElement getAccpetedText() {
	return AccpetedText;
}

public MobileElement getGo_to_challeng() {
	return go_to_challeng;
}

public MobileElement getCloseicon() {
	return closeicon;
}

public MobileElement getShareButton() {
	return shareButton;
}

}
