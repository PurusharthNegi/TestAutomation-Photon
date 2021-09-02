package com.pom;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateAChallengeCreatorRCDetailsScreenMoreIcon extends CapabilitiesAndWebDriverUtils{
public CreateAChallengeCreatorRCDetailsScreenMoreIcon() {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/nav_more']") // Changed on 1.6.9
	@iOSXCUITFindBy(xpath="//*[contains(@name,'More menu')]")
	public static  MobileElement moreIcon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Leave Challenge\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Leave Challenge\"]")
	public static  MobileElement leaveChallenge;
	
	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/report_abuse_button\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Report Abuse\"]")
	public static  MobileElement reportAbuse;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Cancel\"]")
	@iOSXCUITFindBy(xpath="//*[@name=\"Cancel\"]")
	public static MobileElement cancel;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[1]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='Edit Challenge']")
	public static  MobileElement editChallenge;
		
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Edit Challenge\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Library\"]")
	public static  MobileElement editChallengeRCHeader;
	

	
	public MobileElement getEditChallengeRCHeader() {
		return editChallengeRCHeader;
	}

	

	public MobileElement getEditChallenge() {
		return editChallenge;
	}



	public MobileElement getMoreIcon() {
		return moreIcon;
	}

	public MobileElement getLeaveChallenge() {
		return leaveChallenge;
	}

	public MobileElement getReportAbuse() {
		return reportAbuse;
	}

	public MobileElement getCancel() {
		return cancel;
	}
}
