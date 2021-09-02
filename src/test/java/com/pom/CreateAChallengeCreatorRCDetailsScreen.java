package com.pom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class CreateAChallengeCreatorRCDetailsScreen extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(CreateAChallengeCreatorRCDetailsScreen.class);

	static CreateAChallengeBasicChallengeDetails createBasicChallenge = new CreateAChallengeBasicChallengeDetails();

	public CreateAChallengeCreatorRCDetailsScreen() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static void creatorRCDetailScreenValidation() throws Exception {

		logger.info(" --" + getData("platformName") + " -- Creator RC details Page Validation Start -- ");
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "RC Details Screen");
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
		List<Map<String, String>> testData2 = reader.getData("./Data/MobileData.xlsx", "Create challenge");
		logger.info(" --" + getData("platformName") + "created Challenge Name : "
				+ createBasicChallenge.actualchallenegeName);
		waitFor(5000);
		Assert.assertEquals(rcPageHeader_lbl.getText(), createBasicChallenge.actualchallenegeName);
		logger.info(" --" + getData("platformName") + " - Challenge Name is displayed as RC details page Header  #Pass");
		waitFor(7000);
		Assert.assertTrue(moreIcon.isDisplayed());

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(creatorAvatar.isDisplayed(), true);
		} else {
			System.out.println("Locator not found for avathar image");
		}

		logger.info(" --" + getData("platformName") + " - Challenge Created By : " + creatorName.getText());
		Assert.assertEquals(creatorName.getText(), testData.get(2).get("defVal_Creatorname"));
		Assert.assertEquals(createDate.isDisplayed(), true);
		logger.info(" --" + getData("platformName") + " - Challenge Created Date : " + createDate.getText());
//		Assert.assertEquals(readByDate_lbl.getText(), testData.get(0).get("lbl_ReadBy"));
//		Assert.assertTrue(readByDateValue.isDisplayed());
//		logger.info(" --" + getData("platformName") + " - Challenge end Date : " + readByDateValue.getText());
//		Assert.assertEquals(reminder_lbl.getText(), testData.get(0).get("lbl_Reminders"));
//		Assert.assertTrue(reminderValue.isDisplayed());
//		Assert.assertEquals(reminderValue.getText(), testData1.get(0).get("inp_reminder"));
//		logger.info(" --" + getData("platformName") + "User Selected Reminder : " + reminderValue.getText());
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertTrue(readingChallengeIcon.isDisplayed());
			logger.info(" --" + getData("platformName") + "- Challenge Icon is displayed ");
		}

		else {

			System.out.println("Locator not found for readingchallenge icon");
		}

		Assert.assertTrue(readingChallenge_lbl.isDisplayed());
		Assert.assertEquals(challengeName.getText(), rcPageHeader_lbl.getText());
		logger.info(" --" + getData("platformName") + "- Challenge Name is displayed ");
		Assert.assertEquals(challengeDesc.getText(), testData2.get(0).get("challenge_Desc"));
		logger.info(" --" + getData("platformName") + "- Challenge Desc is displayed ");
		logger.info(" --" + getData("platformName")
				+ " - CreatorName,CreatorAvatar,CreatedDate,Ch_Name,Ch_Description,ReadyBydate,Reminder Assertion #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/RCDetailPage/RC Page.png");

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertTrue(readingList_lbl.isDisplayed());
		} else {
			Assert.assertEquals(readingList_lbl.isDisplayed(), true);
		}
		if (challengeTitleList.size() != 0) {
			for (int i = 0; i <= challengeTitleList.size() - 1; i++) {
				Assert.assertTrue(challengeTitleList.get(i).isDisplayed());
//				Assert.assertTrue(titleReadProgressBar.get(i).isDisplayed());
//				Assert.assertTrue(titlereadProgressPercent.get(i).isDisplayed());		
			}
			if (challengeTitleList.size()>2) {
				horizontalSwipe(challengeTitleList);
			}	
			logger.info(" --" + getData("platformName") + " - Challenge Titles are displayed #Pass");
		} else {
			Assert.assertTrue(false);
			logger.info(" --" + getData("platformName") + " - Challenge Titles are displayed #Fail");
		}
		swipeDown();
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertTrue(participants_lbl.isDisplayed());
		} else {
			Assert.assertEquals(participants_lbl.isDisplayed(), true);
		}
		if (partipantsAvatar.size() != 0) {
			for (int i = 0; i <= partipantsAvatar.size() - 1; i++) {
				Assert.assertTrue(partipantsAvatar.get(i).isDisplayed());
				Assert.assertTrue(participantsList.get(i).isDisplayed());
				Assert.assertTrue(participantProgresspercent.get(i).isDisplayed());
			}
			if (participantsList.size()>5) {
				horizontalSwipe(participantsList);
			}
			logger.info(" --" + getData("platformName") + " - Challenge participants are displayed #Pass");
		} else {
			Assert.assertTrue(false);
			logger.info(" --" + getData("platformName") + " - Challenge participants are displayed #Fail");
		}
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/RCDetailPage/RC Participants.png");

		ClickOnMobileElement(moreIcon);
		waitFor(2000);
		Assert.assertTrue(editChallengeActionBtn.isDisplayed());
		Assert.assertEquals(editChallengeActionBtn.getText(), testData.get(0).get("edit ChallengeBtn_lbl"));
		Assert.assertTrue(leaveChallengeActionBtn.isDisplayed());
		Assert.assertEquals(leaveChallengeActionBtn.getText(), testData.get(0).get("leave ChallengeBtn_lbl"));
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/RCDetailPage/RC_page_More_actionbtns .png");
		logger.info(" --" + getData("platformName") + "RC page More action buttons Validation #Pass");
		ClickOnMobileElement(cancelChallengeActionBtn);
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/RCDetailPage/NavBack_RCpage .png");
		logger.info(" --" + getData("platformName") + "Navgiating Back to RC page after taping cancel button #Pass");
		ClickOnMobileElement(pageBackButton);
		waitFor(5000);
		try {
			waitFor(10000);

			if (getData("platformName").equalsIgnoreCase("android")
					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				logger.info(getData("platformName") + " - Created Challenge Name : "
						+ createBasicChallenge.actualchallenegeName);
				String challengeNameToTap = createBasicChallenge.actualchallenegeName;
				MobileElement findElement = (MobileElement) driver
						.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
				System.out.println(findElement.getText());
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
				logger.info(getData("platformName")
						+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
				ClickOnMobileElement(findElement);
			} else {
				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, ChallengeName);
				logger.info(getData("platformName")
						+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
			}
		} catch (Exception e) {
			waitFor(10000);
			for (int i = 0; i <= 5; i++) {
				swipeUp();
			}
			if (getData("platformName").equalsIgnoreCase("android")
					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				logger.info(getData("platformName") + " - Created Challenge Name : "
						+ createBasicChallenge.actualchallenegeName);
				String challengeNameToTap = createBasicChallenge.actualchallenegeName;
				MobileElement findElement = (MobileElement) driver
						.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
				System.out.println(findElement.getText());
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
				logger.info(getData("platformName")
						+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
				ClickOnMobileElement(findElement);
			} else {
				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, ChallengeName);
				logger.info(getData("platformName")
						+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
			}
			logger.info(getData("platformName")
					+ " - Second attempt - Challenge is displayed in bookClub Challenge Listing page after creating");
		}
		logger.info(getData("platformName") + " --- Preview Screen Validation End --- ");
	}
	
	
	public static void leaveChallenge() throws IOException, InterruptedException {
		
		waitFor(5000);
		logger.info(getData("platformName")+ " - User enters challenge detail page");
		ClickOnMobileElement(moreIcon);
		waitFor(2000);
		ClickOnMobileElement(leaveChallengeActionBtn);
		waitFor(15000);
		Assert.assertTrue(BookClubLandingScreen.lbl_BooKClub_Header.isDisplayed());
		try {
			waitFor(5000);
			if (getData("platformName").equalsIgnoreCase("android")|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				logger.info(getData("platformName") + " - Created Challenge Name : "+ createBasicChallenge.actualchallenegeName);
				String challengeNameToTap = createBasicChallenge.actualchallenegeName;
				MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
				System.out.println(findElement.getText());
				Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
				logger.info(getData("platformName")+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
				
			} else {
				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, ChallengeName);
				logger.info(getData("platformName")+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
				Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
			}
		} catch (Exception e) {
			waitFor(10000);
			for (int i = 0; i <= 5; i++) {
				swipeUp();
			}
			try {
				if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
					String challengeNameToTap = createBasicChallenge.actualchallenegeName;
					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
									+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
					Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
					logger.info(getData("platformName")+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
					} else {
					scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, ChallengeName);
					logger.info(getData("platformName")
							+ " - Challenge is displayed in bookClub Challenge Listing page after creating #Pass");
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/RCDetailPage/Listing_Page .png");
				}
			} catch (Exception e2) {
				logger.info(getData("platformName")+ " - Challenge is not displayed in bookClub Challenge Listing page after user left fromm challange #Pass");
			}
		}
		logger.info(getData("platformName")+ " - Leave challenge Validation #pass");
	}
	
	/************************************** updated RC detail page Locators (updated on build 1.6.3) **********************************/

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_title")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
	public static MobileElement rcPageHeader_lbl;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More Icon']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=' More menu']")
	public static MobileElement moreIcon;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Back']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Back']")
	public static MobileElement pageBackButton;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/profile_image")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]")
	public static MobileElement creatorAvatar;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/created_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[4]")
	public static MobileElement createDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/profile_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[3]")
	public static MobileElement creatorName;

//	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/challenge_readby_text\"]")
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_readby_label")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='READ BY']")
	public static MobileElement readByDate_lbl;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Read by']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[7]")
	public static MobileElement readByDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_readby_date")
	public static MobileElement readByDateValue;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_reminders_label")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='REMINDERS']")
	public static MobileElement reminder_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_reminders")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[9]")
	public static MobileElement reminderValue;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/illustration_image\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\" Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton")
	public static MobileElement readingChallengeIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/challenge_readby_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
	public static MobileElement readingChallenge_lbl;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/challenge_name_text\"]")
	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypeStaticText'][2]")
	public static MobileElement challengeName;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/description_text\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
	public static MobileElement challengeDesc;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/readinglist_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeStaticText[1]")
	public static MobileElement readingList_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/material_cover_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\" Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static List<MobileElement> challengeTitleList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/more_option_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeStaticText[1]")
	public static List<MobileElement> titleMoreIconList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/readingtitle_progressbar")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeProgressIndicator[@name=\"Progress\"]")
	public static List<MobileElement> titleReadProgressBar;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/readingList_progress_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeProgressIndicator[@name=\"Progress\"]")
	public static List<MobileElement> titlereadProgressPercent;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/participants_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
	public static MobileElement participants_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/leaderboard_profile_image")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\" Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]")
	public static List<MobileElement> partipantsAvatar;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/leaderboard_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\" Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]")
	public static List<MobileElement> participantsList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/leaderboard_perc_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\" Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]")
	public static List<MobileElement> participantProgresspercent;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Edit Challenge']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Edit Challenge\"]")
	public static MobileElement editChallengeActionBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Leave Challenge']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Leave Challenge\"]")
	public static MobileElement leaveChallengeActionBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Cancel']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Cancel\"]")
	public static MobileElement cancelChallengeActionBtn;

//	/************************************************** old Locators ***********************************************************/

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/leaderboard_name_text\"])[1]")
	@iOSXCUITFindBy(id = "Henry")
	public static MobileElement leaderName;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/rc_book_image\"])[1]")
	public static MobileElement readListBook;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='IN'])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"IN\"])[3]")
	public static MobileElement readIn;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='IN'])[1]")
	public static MobileElement readInImage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Reading List\"]")
	public static MobileElement iosreadingListText;

	@iOSXCUITFindBy(xpath = "noReadingChallenge")
	public static MobileElement iosreadingChallengeImage;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"controlsButtonsCardsMore\"])[1]")
	public static MobileElement IOScontrolButtton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static List<MobileElement> readingList;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static List<MobileElement> readingListafteraccept;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]")
	public static List<MobileElement> participantListafteraccept;

	@iOSXCUITFindBy(xpath = "//*[@name='ChallengeProfileIconMedium']")
	public static MobileElement readImageafteraccept;

//	/***********************************************************************************************************************************/

	public MobileElement getIosreadingListText() {
		return iosreadingListText;
	}

	public MobileElement getIosreadingChallengeImage() {
		return iosreadingChallengeImage;
	}

	public MobileElement getIOScontrolButtton() {
		return IOScontrolButtton;
	}

	public MobileElement getCreatorAvatar() {
		return creatorAvatar;
	}

	public MobileElement getCreateDate() {
		return createDate;
	}

	public MobileElement getCreatorName() {
		return creatorName;
	}

	public MobileElement getReadByDate() {
		return readByDate_lbl;
	}

	public MobileElement getChallengeName() {
		return challengeName;
	}

	public MobileElement getChallengeDesc() {
		return challengeDesc;
	}

	public MobileElement getReadImage() {
		return readingChallengeIcon;
	}

	public MobileElement getLeaderName() {
		return leaderName;
	}

	public MobileElement getReadListBook() {
		return readListBook;
	}

	public MobileElement getReadIn() {
		return readIn;
	}

	public MobileElement getReadInImage() {
		return readInImage;
	}

	public MobileElement getMoreIcon() {
		return moreIcon;
	}

}
