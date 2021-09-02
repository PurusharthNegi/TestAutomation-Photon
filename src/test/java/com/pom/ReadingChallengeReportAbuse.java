package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReadingChallengeReportAbuse extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(ReadingChallengeReportAbuse.class);

	static BookClubLandingScreen bookClubLandingScreeen = new BookClubLandingScreen();
	static ReadingChallengeReportAbuse reportabuse = new ReadingChallengeReportAbuse();
	static CreateAChallengeCreatorRCDetailsScreen creatorRCDetailsScreeen = new CreateAChallengeCreatorRCDetailsScreen();
	static CreateAChallengeBasicChallengeDetails createBasicChallenge = new CreateAChallengeBasicChallengeDetails();

	public ReadingChallengeReportAbuse() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static void submitReportAbuse() throws Exception {

		// ClickOnMobileElement(createBasicChallenge.searchChallengeName);

//		ClickOnMobileElement(bookClubLandingScreeen.bookClubOption);

		logger.info(" --" + getData("platformName") + " - Report Abuse Validation Start -- ");
		waitFor(5000);
		ClickOnMobileElement(rcPagemoreIcon);
		Thread.sleep(2000);
		logger.info(getData("platformName") + " - user able to see report abuse button #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/ReportAbuse/MoreMenu Action btn.png");
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Report Abuse");
		Assert.assertEquals(reportAbuseButton.getText(), testData.get(0).get("lbl_ReportAbuse"));
		ClickOnMobileElement(reportAbuseButton);
		ClickOnMobileElement(backIcon);
		logger.info(getData("platformName") + " - user navigates to report abuse page #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/ReportAbuse/ReportAbuse page.png");
		logger.info(getData("platformName") + " - user taped on back button #Pass");
		waitFor(7000);
		Assert.assertEquals(creatorNameInRCpage.isDisplayed(), true);
		System.out.println("Creator Name : " + creatorNameInRCpage.getText());
		Screenshots.takeScreenshot(driver, "./screenshots/ReportAbuse/RC Details screen.png");
		logger.info(getData("platformName")
				+ " - Navigating back to RC detail page after tapping on back button in Report abuse page  #Pass");

		waitFor(5000);
		ClickOnMobileElement(rcPagemoreIcon);
		Assert.assertEquals(reportAbuseButton.getText(), testData.get(0).get("lbl_ReportAbuse"));
		ClickOnMobileElement(reportAbuseButton);

		Assert.assertEquals(reportAbuseHeader.isDisplayed(), true);
		Assert.assertEquals(reportAbuseHeader.getText(), testData.get(0).get("lbl_ReportAbuse"));
		logger.info(getData("platformName") + " - Report abuse header Validation #Pass");

		Assert.assertEquals(viewBriefDescription.isDisplayed(), true);
		Assert.assertEquals(viewBriefDescription.getText(), testData.get(0).get("defcont_ReportAbuse"));

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(beforeenter.getText(), testData.get(0).get("defVal_Textbox"));
		}

		else {

			Assert.assertEquals(beforeenter.getText(), testData.get(0).get("defVal_Textbox_ios"));

		}

		logger.info(getData("platformName") + " - Report abuse description Validation #Pass");

		ClickOnMobileElement(beforeenter);
		ClickOnMobileElement(viewBriefDescription);// for hiding keyboard
		hideMobileKeyboard();

		waitFor(2000);
		Assert.assertEquals(submitButton.getText(), testData.get(0).get("lbl_submit"));
		ClickOnMobileElement(submitButton);
		Assert.assertTrue(viewBriefDescription.isDisplayed());
		logger.info(getData("platformName") + " - Without entering data not able to submit report abuse  #pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/ReportAbuse/WithoutData Submit.png");

		ClickOnMobileElement(beforeenter);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/ReportAbuse/after click.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			SendKeysOnMobileElement(beforeenter, RandomStringGenerate(14));
			hideMobileKeyboard();
			Assert.assertEquals(charCountLessThanMinimum.getText(), testData.get(0).get("lbl_charCountMin"));
		} else {

			SendKeysOnMobileElement(enterconcernTxtBox, RandomStringGenerate(14));
			ClickOnMobileElement(viewBriefDescription);// for hiding keyboard

			Assert.assertEquals(charCountLessThanMinimum.getText(), testData.get(0).get("lbl_charCountMin_ios"));
		}

		waitFor(2000);
		Assert.assertTrue(minimumerror.isDisplayed());
		Assert.assertEquals(minimumerror.getText(), testData.get(0).get("lbl_minerrorMessage"));
		logger.info(getData("platformName") + " - Minimum text to be entered meassage displayed  #pass");
		ClickOnMobileElement(submitButton);
		Assert.assertTrue(viewBriefDescription.isDisplayed());
		logger.info(getData("platformName") + " - unbale to submit the report abuse with 14 character  #pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/ReportAbuse/Less_Min_Char.png");
		textboxclear.clear();

		ClickOnMobileElement(beforeenter);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			SendKeysOnMobileElement(beforeenter, RandomStringGenerate(1301));
			hideMobileKeyboard();
			Assert.assertEquals(maxcount1300.getText(), testData.get(0).get("lbl_charCountMax"));
		} else {
			SendKeysOnMobileElement(enterconcernTxtBox, RandomStringGenerate(1301));
			ClickOnMobileElement(viewBriefDescription);// for hiding keyboard
			Assert.assertEquals(maxcount1300.getText(), testData.get(0).get("lbl_charCountMax_ios"));

		}

		waitFor(2000);

		logger.info(getData("platformName") + " - User Not Able to enter 1301 character max limit is 1300");
		Assert.assertTrue(submitButton.isEnabled());
		logger.info(getData("platformName") + " - Validating with maximum +1 1301 character #pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/ReportAbuse/Maxplusone_char.png");
		textboxclear.clear();

		ClickOnMobileElement(beforeenter);

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			SendKeysOnMobileElement(beforeenter, RandomStringGenerate(1300));
			Assert.assertEquals(maxcount1300.getText(), testData.get(0).get("lbl_charCountMax"));
			hideMobileKeyboard();

		} else {
			SendKeysOnMobileElement(enterconcernTxtBox, RandomStringGenerate(1300));
			ClickOnMobileElement(viewBriefDescription);// for hiding keyboard
			Assert.assertEquals(maxcount1300.getText(), testData.get(0).get("lbl_charCountMax_ios"));

		}

		waitFor(2000);
		System.out.println("Entered char count : " + maxcount1300.getText());
		Assert.assertTrue(submitButton.isEnabled());
		logger.info(getData("platformName") + " - Validating with maximum 1300 character #pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/ReportAbuse/Max_char.png");
		waitFor(2000);
		textboxclear.clear();
		driver.resetInputState();


		String text = beforeenter.getText();
		System.out.println(text);
		
		//textboxclear.sendKeys(Keys.CONTROL+"a");
		//textboxclear.sendKeys(Keys.DELETE);
	
		waitFor(2000);
		ClickOnMobileElement(beforeenter);

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			SendKeysOnMobileElement(beforeenter, RandomStringGenerate(15));
			hideMobileKeyboard();
			Assert.assertEquals(exactCharCount.getText(), testData.get(0).get("lbl_charCountexact"));
		} else {
			SendKeysOnMobileElement(enterconcernTxtBox, RandomStringGenerate(15));
			ClickOnMobileElement(viewBriefDescription);// for hiding keyboard
			Assert.assertEquals(exactCharCount.getText(), testData.get(0).get("lbl_charCountexact_ios"));

		}

		System.out.println("Entered char count : " + exactCharCount.getText());

		Assert.assertTrue(submitButton.isEnabled());
		logger.info(getData("platformName") + " -submit button is enabled only after entering 15 char min  #pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/ReportAbuse/Exact_Char_Length.png");
		ClickOnMobileElement(submitButton);
		logger.info(getData("platformName") + " -Report Abuse Submitted Successfully-  #pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/ReportAbuse/toast.png");

//		toastMessage();

		Assert.assertEquals(fluentWaitDisplayed(bookClubLandingScreeen.addCTA, 30, 2), true);
		Assert.assertEquals(fluentWaitDisplayed(bookClubLandingScreeen.challenges, 30, 2), true);
		Assert.assertEquals(fluentWaitDisplayed(bookClubLandingScreeen.lbl_BooKClub_Header, 30, 2), true);
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/ReportAbuse/afterAbuseSubmit.png");
		logger.info("landed on book club landing page after submitting report abuse  #pass");
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
//				ClickOnMobileElement(createBasicChallenge.searchChallengeName);
				logger.info(getData("platformName")
						+ " - Challenge is not displayed in bookClub landing page after submitting Report Abuse #Fail");
			} else {
				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, ChallengeName);
				logger.info(getData("platformName")
						+ " - Challenge is not displayed in bookClub landing page after submitting Report Abuse #Fail");
			}
		} catch (Exception e) {
			waitFor(5000);
			logger.info(getData("platformName")
					+ " - Challenge is not displayed in bookClub landing page after submitting Report Abuse #Pass");
		}

		logger.info(getData("platformName") + " --- Report Abuse Validation End --- ");
	}

	public static void toastMessage() {
		WebDriverWait waitForToast = new WebDriverWait(driver, 25);
//		waitForToast.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
		waitForToast.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[1]")));
		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getText();
		System.out.println(toastMessage);

	}

	/************************************** updated ReportAbuse page Locators (updated on build 1.6.3)**********************************/

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/profile_name_text")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell/XCUIElementTypeStaticText)[5]")
	public static MobileElement creatorNameInRCpage;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_more")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\" More menu\"]")
	public static MobileElement rcPagemoreIcon;

	@iOSXCUITFindBy(xpath = "//*[@name='iconsCoreRemove']")
	public static MobileElement moreIcon;

	@iOSXCUITFindBy(accessibility = "Leave Challenge")
	public static MobileElement leaveChallenge;

	@iOSXCUITFindBy(accessibility = "Cancel")
	public static MobileElement cancel;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/report_abuse_text")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Report Abuse\"]")
	public static MobileElement reportAbuseButton;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_back")
	@iOSXCUITFindBy(accessibility = "NavigationBackButton")
	public static MobileElement backIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_title")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Report Abuse\"]")
	public static MobileElement reportAbuseHeader;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/sub_title_text")
	@iOSXCUITFindBy(accessibility = "Please provide details about your concern regarding the Reading Challenge.")
	public static MobileElement viewBriefDescription;

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Please tell us your concerns']")
	@iOSXCUITFindBy(xpath = "//*[@value='Enter your concern']")
	public static MobileElement beforeenter;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/report_text_input")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeOther[1]/XCUIElementTypeTextView")
	public static MobileElement enterconcernTxtBox;

	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/report_edit_text")
	@iOSXCUITFindBy(xpath = "//*[@height='251']")
	public static MobileElement reportEditText;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/characters_error_message")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Minimum 15 characters\"]")
	public static MobileElement minimumerror;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/count_text")
	@iOSXCUITFindBy(xpath = "//*[@name=\"14 / 1300 characters\"]")
	public static MobileElement charCountLessThanMinimum;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/count_text")
	@iOSXCUITFindBy(xpath = "//*[@name=\"1300 / 1300 characters\"]")
	public static MobileElement maxcount1300;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/count_text")
	@iOSXCUITFindBy(xpath = "//*[@name=\"15 / 1300 characters\"]")
	public static MobileElement exactCharCount;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/report_text_input")
	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypeTextView']")
	public static MobileElement textboxclear;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/submit_text")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Submit\"]")
	public static MobileElement submitButton;

	/***********************************************************************************************************************************/

	public MobileElement getViewBriefDescription() {
		return viewBriefDescription;
	}

	public MobileElement getReportEditText() {
		return reportEditText;
	}

	public MobileElement getSubmitButton() {
		return submitButton;
	}

	public MobileElement getBackIcon() {
		return backIcon;
	}

}
