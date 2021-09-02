package com.pom;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReadingChallengeAcceptRejectChallenge extends CapabilitiesAndWebDriverUtils {
	
	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(BookClubLandingScreen.class);
	
	static BookClubLandingScreen bookClubScreen = new BookClubLandingScreen();
    static CreateAChallengeBasicChallengeDetails createBasicChallenge = new CreateAChallengeBasicChallengeDetails();	
    
public ReadingChallengeAcceptRejectChallenge()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

public static void acceptChallenge() throws IOException, Exception {
	
	logger.info("User is Navigating to Home page");
	waitFor(2000);
	ClickOnMobileElement(bookClubScreen.bookClubOption);
	waitFor(8000);
	logger.info(getData("platformName")+" -- Searching For New Challenge to accept --");
	try {
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			System.out.println(createBasicChallenge.actualchallenegeName);
			String challengeNameToTap = createBasicChallenge.actualchallenegeName;
			MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
			System.out.println(findElement.getText());
			ClickOnMobileElement(findElement);
			logger.info(getData("platformName")+" -New Challenge found #Pass ");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/Acceptpage.png");
		}
		else {
			scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, CreateAChallengeBasicChallengeDetails.actualchallenegeDesc);	
		}
	}
	
	catch(Exception e){
		for (int i = 0; i < 5; i++) {
			waitFor(5000);;
			swipeUp();
		}
//		String Pagetop = bookClubScreen.lbl_ActiveChallenges.getText();
		String challengeNameToTap = createBasicChallenge.actualchallenegeName;
		MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
		System.out.println(findElement.getText());
		ClickOnMobileElement(findElement);
		logger.info(getData("platformName")+" -New Challenge found on second attempt #Pass ");	
	}
	waitFor(10000);
	Assert.assertTrue(challengeicon.isDisplayed());
	Assert.assertTrue(chName.isDisplayed());
	Assert.assertTrue(chDesc.isDisplayed());
	Assert.assertTrue(chDesc.isDisplayed());
	Assert.assertTrue(creatorName.isDisplayed());
	Assert.assertTrue(readByDateText.isDisplayed());
	for (int i = 0; i<participantsList.size(); i++) {
		Assert.assertTrue(participantsList.get(i).isDisplayed());
	}
	for (int i = 0; i<titleReadingChallenge.size(); i++) {
		Assert.assertTrue(titleReadingChallenge.get(i).isDisplayed());
	}
	Assert.assertTrue(noThanksButton.isDisplayed());
	ClickOnMobileElement(accpetChallegeButton);
	logger.info(getData("platformName")+" - Chalenge Accepted sucessfully #Pass ");
	waitFor(5000);
	Assert.assertTrue(cnfPage_AcceptText.isDisplayed());
	logger.info(getData("platformName")+" - user naviagtes to Challenge confirmation page #Pass ");
	logger.info(getData("platformName")+" - Challenge Accepted text is displayed #Pass ");
	Assert.assertTrue(cnfPage_UserInfo.isDisplayed());
	logger.info(getData("platformName")+" - User info text is displayed in confirmation page #Pass ");
	Assert.assertTrue(cnfPage_GoToChallenge.isDisplayed());
	logger.info(getData("platformName")+" - Go to Challenge button is displayed in confirmation page #Pass ");
	Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/CnfPage.png");
	ClickOnMobileElement(cnfPage_GoToChallenge);
}

public void rejectChallenge() throws IOException {
	logger.info("User is Navigating to Home page");
	waitFor(2000);
	ClickOnMobileElement(bookClubScreen.bookClubOption);
	waitFor(8000);
	logger.info(getData("platformName")+" -- Searching For New Challenge to Reject --");
	try {
		waitFor(15000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			System.out.println(createBasicChallenge.actualchallenegeName);
			String challengeNameToTap = createBasicChallenge.actualchallenegeName;
			MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
			System.out.println(findElement.getText());
			ClickOnMobileElement(findElement);
			logger.info(getData("platformName")+" -New Challenge found #Pass ");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/Acceptpage.png");
		}
		else {
			scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, CreateAChallengeBasicChallengeDetails.actualchallenegeDesc);
			
		}
	}
	
	catch(Exception e){
		waitFor(15000);
		for (int i = 0; i < 5; i++) {
			waitFor(2000);
			swipeUp();
		}
//		String Pagetop = bookClubScreen.lbl_ActiveChallenges.getText();
		String challengeNameToTap = createBasicChallenge.actualchallenegeName;
		MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
		System.out.println(findElement.getText());
		ClickOnMobileElement(findElement);
		logger.info(getData("platformName")+" -New Challenge found on second attempt #Pass ");	
	}
	waitFor(10000);
	Assert.assertTrue(challengeicon.isDisplayed());
	Assert.assertTrue(chName.isDisplayed());
	Assert.assertTrue(chDesc.isDisplayed());
	Assert.assertTrue(chDesc.isDisplayed());
	Assert.assertTrue(creatorName.isDisplayed());
	Assert.assertTrue(readByDateText.isDisplayed());
	for (int i = 0; i<participantsList.size(); i++) {
		Assert.assertTrue(participantsList.get(i).isDisplayed());
	}
	for (int i = 0; i<titleReadingChallenge.size(); i++) {
		Assert.assertTrue(titleReadingChallenge.get(i).isDisplayed());
	}
	Assert.assertTrue(noThanksButton.isDisplayed());
	ClickOnMobileElement(noThanksButton);
	waitFor(15000);
	try {
		waitFor(15000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			System.out.println(createBasicChallenge.actualchallenegeName);
			String challengeNameToTap = createBasicChallenge.actualchallenegeName;
			MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
			if (findElement.isDisplayed()) {
				logger.info(getData("platformName")+" - Rejected challenge displayed in listing page #fail ");
				Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/rejectChallenge.png");
			}
		}
		else {
			
		}
		
	} catch (Exception e) {
		waitFor(15000);
		for (int i = 0; i < 5; i++) {
			waitFor(2000);
			swipeUp();
		}
		try {
		String challengeNameToTap = createBasicChallenge.actualchallenegeName;
		MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
		ClickOnMobileElement(findElement);
		}
		catch(Exception e2){
			logger.info(getData("platformName")+" - Rejected challenge not displayed in listing page #Pass ");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/rejectChallenge.png");
		}
	} 
}

/***************************************New Locators********************************************************/

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/challenge_name_text")
@iOSXCUITFindBy(xpath="XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
public static MobileElement chName;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/challenge_desc_text")
@iOSXCUITFindBy(xpath="XCUIElementTypeOther/XCUIElementTypeStaticText[3]")
public static MobileElement chDesc;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/illustration_image")
@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Reading challenge\"]")
public static MobileElement challengeicon;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/profile_name_text")
@iOSXCUITFindBy(xpath="XCUIElementTypeOther/XCUIElementTypeStaticText[6]")
public static MobileElement creatorName;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/challenge_read_by_text")
@iOSXCUITFindBy(xpath="XCUIElementTypeOther/XCUIElementTypeStaticText[5]")
public static MobileElement readByDateText;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/user_name_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeCell[1]/XCUIElementTypeCell")
public static List<MobileElement> participantsList;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/material_cover_image_view")
@iOSXCUITFindBy(xpath="//XCUIElementTypeCell[2]/XCUIElementTypeCell")
public static List<MobileElement> titleReadingChallenge;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/accept_challenge_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Accept Challenge\"]")
public static MobileElement accpetChallegeButton;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/no_thanks_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"No, Thanks\"]")
public static MobileElement noThanksButton;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/close_image_view")
@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Close\"]")
public static MobileElement cnfPage_Xicon;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/accepted_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Challenge Accepted!\"]")
public static MobileElement cnfPage_AcceptText;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/user_info_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
public static MobileElement cnfPage_UserInfo;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/share_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Share\"]")
public static MobileElement cnfPage_ShareBtn;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/go_to_challenge_text")
@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Go to Challenge\"]")
public static MobileElement cnfPage_GoToChallenge;

@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Back\"]")
public static MobileElement back;

/***************************************old Locators********************************************************/
@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"You've been Challenged!\"])[1]")
private MobileElement youHaveBeenChallenged;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/challenge_name_text")
@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther/XCUIElementTypeStaticText)[2]")
private MobileElement readingChallengeName;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/challenge_desc_text")
@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther/XCUIElementTypeStaticText)[3]")
private MobileElement description;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/more_option")
private MobileElement homePageMoreIcon;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/title")
private List<MobileElement> logout;

@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"Log In\"]")
private MobileElement login;


}
