package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateAChallengeBasicChallengeDetails extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(CreateAChallengeBasicChallengeDetails.class);

	BookClubLandingScreen bookClubLandingScreeen = new BookClubLandingScreen();
	CreateChallengeAddFriends CRaddFriend = new CreateChallengeAddFriends();
	CreateaChallengeSearchTitleResultsListView searchTitles = new CreateaChallengeSearchTitleResultsListView();
	CreateChallengeSetReminders setReminders = new CreateChallengeSetReminders();
	CreateAChallengeSetReadbyDate setReadbyDate = new CreateAChallengeSetReadbyDate();
	CreateAChallengeCreatorRCDetailsScreen creatorRCDetailsScreeen = new CreateAChallengeCreatorRCDetailsScreen();
	CreateChallengeAddTitles addTitles = new CreateChallengeAddTitles();
	CreateAChallengeCreatorRCDetailsScreenMoreIcon creatorRCDetailsScreenMoreIcon = new CreateAChallengeCreatorRCDetailsScreenMoreIcon();
	EditChallengeUpDateRcDetailsScreen editchallenge = new EditChallengeUpDateRcDetailsScreen();

	public static String actualchallenegeName;
	public static String actualchallenegeDesc;
	public static String updatedchallenegeName;

	public CreateAChallengeBasicChallengeDetails() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public String createChallenge() throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Create challenge");
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");

		logger.info(" --" + getData("platformName") + " - Create Challenge Validation Start -- ");

		ClickOnMobileElement(bookClubLandingScreeen.addCTA);
		waitFor(3000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_HomePage.png");

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(challengeNameCharcount_lbl.getText(), testData.get(0).get("defVal_ChalCount"));
			Assert.assertEquals(descNameCharcount_lbl.getText(), testData.get(0).get("defVal_ChalDesCount"));
		}

		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			System.out.println(challengeNameCharcount_lbl.getText());
			System.out.println(descNameCharcount_lbl.getText());

			Assert.assertEquals(challengeNameCharcount_lbl.getText(), testData.get(0).get("defVal_ChalCountIOS"));
			Assert.assertEquals(descNameCharcount_lbl.getText(), testData.get(0).get("defVal_ChalDesCountIOS"));
		}
		logger.info(getData("platformName") + " - Challenge title :" + challcreationpageHeader_lbl.getText() + "#Pass");
		Assert.assertEquals(challcreationpageHeader_lbl.getText(), testData.get(0).get("lbl_CreateChallenge"));
		SendKeysOnMobileElement(challengeName, challengeName());
		actualchallenegeName = challengeName.getText();
		logger.info(getData("platformName") + " - Entered Challenge Name as :" + actualchallenegeName + "  #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_ChallengeNameEntered.png");

		SendKeysOnMobileElement(description, testData.get(0).get("challenge_Desc"));
		hideMobileKeyboard();
		actualchallenegeDesc = description.getText();
		logger.info(getData("platformName") + " - Entered Challenge Description  as :" + actualchallenegeDesc + "  #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_ChallengeDescEntered.png");

		CRaddFriend.addFriend();
		logger.info(getData("platformName") + " - Search and add friend is working fine #Pass");

		waitFor(2000);
		CRaddFriend.removeFriendFromChallenge();
		logger.info(getData("platformName") + " - Remove friend is working fine  #Pass");

		waitFor(2000);
		CRaddFriend.AddRecentFriends();
		logger.info(getData("platformName") + " - Add Recent friend  is working fine  #Pass");

		if (getData("platformName").equalsIgnoreCase("android")|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			setReminders.androidSetReminder(testData1.get(0).get("inp_reminder"));
			logger.info(getData("platformName") + " - Reminder is added successfully  #Pass");

			setReadbyDate.androidSetReadByDate();
			logger.info(getData("platformName") + " - Set Read by date is added successfully #Pass");

		}
		if (getData("platformName").equalsIgnoreCase("iOS")|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {

			setReminders.iOSSetReminder();
			logger.info(getData("platformName") + " - Reminder is added successfully  #Pass");

			setReadbyDate.iOSSetReadByDate();
			logger.info(getData("platformName") + " - Set Read by date is added successfully #Pass");

		}
		swipeDown();
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_BeforeAddTitle.png");

		ClickOnMobileElement(addTitlesCTA);

		addTitles.addTitleToChallenge();
		logger.info(getData("platformName") + " - Title added to the challenge  #Pass");

		addTitles.removeTitleFromChallenge();

		ClickOnMobileElement(startChallengeBtn);
		waitFor(5000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CCDone.png");
		logger.info(getData("platformName") + " - Create Challenge is clicked  #Pass");

		logger.info(" --" + getData("platformName") + " - Create Challenge Validation End -- ");
		return actualchallenegeName;
	}
	
	public void discardChallenge() throws IOException {
		
		ClickOnMobileElement(bookClubLandingScreeen.addCTA);
		waitFor(3000);
		SendKeysOnMobileElement(challengeName, challengeName());
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_HomePage.png");
		ClickOnMobileElement(discardButton);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/disCardChalleneg_pop.png");
		ClickOnMobileElement(discardCancelButton);
		Assert.assertTrue(challcreationpageHeader_lbl.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Discard Cancel tapped user Stays on create challenge page #pass");
		ClickOnMobileElement(discardButton);
		ClickOnMobileElement(discardOkButton);
		waitFor(2000);
		Assert.assertTrue(bookClubLandingScreeen.lbl_BooKClub_Header.isDisplayed());
		logger.info(" --" + getData("platformName") + " - After Discard user lands on Book club landing page #pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/afterDiscard.png");
	}
	
	public void startButtonValidation() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Create challenge");
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		waitFor(3000);
		SendKeysOnMobileElement(challengeName, challengeName());
		SendKeysOnMobileElement(description, testData.get(0).get("challenge_Desc"));
		ClickOnMobileElement(startChallengeBtn);
		waitFor(5000);
		Assert.assertTrue(challcreationpageHeader_lbl.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Not able to Start challenge without mandatory fields #pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/startButtonEnable.png");
		ClickOnMobileElement(saveChallengeBtn);
		waitFor(5000);
		ClickOnMobileElement(creatorRCDetailsScreeen.moreIcon);
		ClickOnMobileElement(creatorRCDetailsScreeen.editChallengeActionBtn);	
	}
	
	public void updateChallangeDetails() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "AddFriends");
		waitFor(5000);
		swipeUp();
		challengeName.clear();
		SendKeysOnMobileElement(challengeName, challengeName());
		updatedchallenegeName= challengeName.getText();
		logger.info(getData("platformName") + " - Challenge Name Updated as :" + updatedchallenegeName + "  #Pass");
		
		ClickOnMobileElement(addFriendCTA);
		SendKeysOnMobileElement(CRaddFriend.searchBox, testData1.get(2).get("inp_friendName"));
		if (getData("platformName").equalsIgnoreCase("android")|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			waitFor(5000);
			ClickOnMobileElement(CRaddFriend.inviteOptionList.get(0));
			waitFor(3000);
			ClickOnMobileElement(CRaddFriend.addToChallenge);
			logger.info(getData("platformName") + " - New friend added to the challenge successfully  #Pass");
	
			setReminders.androidSetReminder(testData.get(0).get("upDated_reminder"));
			logger.info(getData("platformName") + " - Reminder is Updated successfully  #Pass");
			
			setReadbyDate.androidDateValidation();
		}
		if (getData("platformName").equalsIgnoreCase("iOS")|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			CRaddFriend.searchBox.sendKeys(Keys.ENTER);
			ClickOnMobileElement(CRaddFriend.inviteOptionList.get(0));
			ClickOnMobileElement(CRaddFriend.addToChallenge);
			logger.info(getData("platformName") + " - New friend added to the challenge successfully  #Pass");
//			CreateChallengeSetReminders.editSetReminder();
			setReadbyDate.editedDate();
		}
		ClickOnMobileElement(startChallengeBtn);
		logger.info(getData("platformName") + " - Challenge started successfully without description #Pass");
		logger.info(getData("platformName") + " - Updated challenge started successfully  #Pass");
		waitFor(5000);
		ClickOnMobileElement(creatorRCDetailsScreeen.moreIcon);
		ClickOnMobileElement(creatorRCDetailsScreeen.editChallengeActionBtn);
		waitFor(5000);
		Assert.assertEquals(addTitles.editChallenge_Header.isDisplayed(), true);
		ClickOnMobileElement(addTitles.editChallenge_Delete);
		ClickOnMobileElement(addTitles.editChallenge_ConfirmDelete);
		waitFor(8000);
	}
	
	
	public static By ChallengeNameEle = By.xpath("//XCUIElementTypeCollectionView[1]/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText");

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]")
	public static MobileElement previewbackbtn;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/challenge_title_edit_text\"]")
//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextView['index=1'][1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextView[@name='CC_EnterChallengeName']")
	public static MobileElement challengeName;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/challenge_details_edit_text")
//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextView['index=1'][2]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextView[@name='CC_EnterChallengeDescription']")
	public static MobileElement description;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/title_invite_friends\"])[8]")
	@iOSXCUITFindBy(id = "INVITE FRIENDS")
	public static MobileElement inviteText;

//	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/add_invitees\"])[1]")
	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/ic_remove_users\"]")
	// @iOSXCUITFindBy(xpath = "//*[@name='CC_AddOrRemoveFriend']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"dd_addImageFriend\"]")
	public static MobileElement addFriendCTA;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/title_set_remainder\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Set Reminder\"]")
	public static MobileElement setReminderText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Horizontal scroll bar, 2 pages\"]")
	public static MobileElement setReminderOptions;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"PV_Done\"]")
	public static MobileElement iosDoneBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Photon\"]")
	public static MobileElement iosAddedFriendCreateChallengepage;

	@AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/spinner_item']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='CC_SetReminderText']")
	public static MobileElement setReminderOpt;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/title_set_read_by_date\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Set Read By Date\"]")
	public static MobileElement setReadByText;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/value_date_picker\"])[1]")
//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"CalendarIcon\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CC_SetDate']")
	public static MobileElement dateOpt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"CC_DatePickerDone\"]")
	public static MobileElement iosDontBtn;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/title_add_titles\"])[1]")
	@iOSXCUITFindBy(id = "ADD TITLES")
	public static MobileElement addTitlesText;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Add Search and add titles']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"dd_addBtnTitle\"]")
	public static MobileElement addTitlesCTA;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/button_start_challenge\"])[1]")
	@iOSXCUITFindBy(xpath = "//*[@value='Start Challenge']")
	public static MobileElement startChallengeBtn;
	
//	@AndroidFindBy(xpath = "")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText)[1]")
	public static MobileElement title_CCDetails;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/button_save_challenge\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Save\"]")
	public static MobileElement saveChallengeBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/book_club_toolbar_right_action_button")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Menu\"]")
	public static MobileElement deleteBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/book_club_navigation_icon")
	@iOSXCUITFindBy(id = "Book Club")
	public static MobileElement backBtn;

	@AndroidFindBy(xpath = "//*[@text='REMOVE']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Remove']")
	public static MobileElement TitesRemoveBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivBook")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> addedBooks;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_header_desc")
	public static List<MobileElement> challengeNameList;

	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))"
			+ ".scrollIntoView(new UiSelector().text(\"Smoke Dlx\"))")
	public static MobileElement searchChallengeName;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ic_remove_users")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeButton")
	public static List<MobileElement> removeFriendXIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivUsers")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView[1]/XCUIElementTypeCell")
	public static List<MobileElement> addedFriendavatarList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_user_name")
	public static List<MobileElement> addedFriendNameList;

	@AndroidFindBy(xpath = "//*[@text='REMOVE']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Remove']")
	public static MobileElement friendRemoveButton;

	@AndroidFindBy(xpath = "//*[@text='CANCEL']")
	@iOSXCUITFindBy(xpath = "//*[@name='Cancel']")
	public static MobileElement friendCancelButton;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ic_remove_titles")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[7]/XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> removeTitleXIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/remove_book_remove_button")
	@iOSXCUITFindBy(xpath = "//*[@name='Remove']")
	public static MobileElement titleRemoveButton;

	@AndroidFindBy(xpath = "//*[@text='CANCEL']")
	@iOSXCUITFindBy(xpath = "//*[@name='Cancel']")
	public static MobileElement titleCancelButton;
	
	@AndroidFindBy(xpath = "//*[@text='Create Challenge']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Create Challenge']")
	public static MobileElement challcreationpageHeader_lbl;

	@AndroidFindBy(xpath = "//*[@text='0/64 Characters']")
	@iOSXCUITFindBy(xpath = "//*[@name='0 of 64 characters entered']")
	public static MobileElement challengeNameCharcount_lbl;

	@AndroidFindBy(xpath = "//*[@text='0/1300 Characters']")
	@iOSXCUITFindBy(xpath = "//*[@name='0 of 1300 characters entered']")
	public static MobileElement descNameCharcount_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_back")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"NavigationBackButton\"]")
	public static MobileElement discardButton;

	@AndroidFindBy(xpath = "//*[@text='CANCEL']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Cancel\"]")
	public static MobileElement discardCancelButton;

	@AndroidFindBy(xpath = "//*[@text='OK']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"OK\"]")
	public static MobileElement discardOkButton;
	
	public MobileElement getTitesRemoveBtn() {
		return TitesRemoveBtn;
	}

	public MobileElement getSearchChallengeName(String ChallengeName) {
		return searchChallengeName;
	}

	public List<MobileElement> getChallengeNameList() {
		return challengeNameList;
	}

	public MobileElement getChallengeName() {
		return challengeName;
	}

	public List<MobileElement> getAddedBooks() {
		return addedBooks;
	}

//	public MobileElement getAddedBook() {
//		return addedBook;
//	}

	public MobileElement getDescription() {
		return description;
	}

	public MobileElement getInviteText() {
		return inviteText;
	}

	public MobileElement getAddFriendCTA() {
		return addFriendCTA;
	}

	public MobileElement getSetReminderText() {
		return setReminderText;
	}

	public MobileElement getSetReminderOptions() {
		return setReminderOptions;
	}

	public MobileElement getIosDoneBtn() {
		return iosDoneBtn;
	}

	public MobileElement getSetReminderOpt() {
		return setReminderOpt;
	}

	public MobileElement getSetReadByText() {
		return setReadByText;
	}

	public MobileElement getDateOpt() {
		return dateOpt;
	}

	public MobileElement getIosDontBtn() {
		return iosDontBtn;
	}

	public MobileElement getAddTitlesText() {
		return addTitlesText;
	}

	public MobileElement getDeleteBtn() {
		return deleteBtn;
	}

	public MobileElement getBackBtn() {
		return backBtn;
	}

	public MobileElement getIosAddedFriendCreateChallengepage() {
		return iosAddedFriendCreateChallengepage;
	}

	public MobileElement getAddTitlesCTA() {
		return addTitlesCTA;
	}

	public MobileElement getStartChallengeBtn() {
		return startChallengeBtn;
	}

	public MobileElement getSaveChallengeBtn() {
		return saveChallengeBtn;
	}

}
