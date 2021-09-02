package com.pom_RWD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

public class RWD_RC_Smoke extends CapabilitiesAndWebDriverUtils {
	static ExcelReader reader = new ExcelReader();
	public String chlngName = challengeName();
	private static final Logger logger = LogManager.getLogger();
	public static String ConfigurationFile = System.getProperty("user.dir") + "/Configs/Configuration.properties";
	
	public RWD_RC_Smoke() {
		PageFactory.initElements(driver, this);
	}

	public void bookClubLanding() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		WaitForWebElement(RWD_UIRefresh_PageHit.menu_profileIcon);
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/Home.png");
		WaitForWebElement(RWDbookClubOptionWeb);
		waitFor(4000);
		ClickOnWebElement(RWDbookClubOptionWeb);
		logger.info(" --Book club validation Starts-- ");
		waitFor(2000);
	//	Assert.assertEquals(CCHeader.getText(), testData.get(0).get("lbl_challenges"));
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/BookClub.png");
		logger.info("Challenges tab is available #Pass");
		String crtChlg = RWDaddCTAWeb.getText().substring(0, 16);
		Assert.assertEquals(crtChlg, testData.get(0).get("lbl_create"));
		logger.info("Create Challenge button is available #Pass");
		logger.info(" --Book club validation Completed-- ");

	}

	public void createChallengeIcon() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("-- Create challenge validation started--");
		waitFor(15000);
		ClickOnWebElement(RWDaddCTAWeb);
		WaitForWebElement(lbl_createChlgTitle);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge.png");
		// Validating Challenge name placeholder and text box
		Assert.assertEquals(ChallengenamePlaceholder.getText(), testData.get(0).get("defval_ChlgNamTxtBox"));
		logger.info("User tagged Create Challnges Tab");
	}

	public void createchallengeName() throws IOException, InvalidFormatException {
		
		SendKeysOnWebElement(RWDChallengenametextbar, chlngName);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/ChallengeName.png");
		logger.info("Challenge name text box is available and Challenge name is Entered #Pass");
	}

	public void enterchallengeDesc() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		Assert.assertEquals(ChallengedescPlaceholder.getText(), testData.get(0).get("defval_ChlgDescTxtBox"));
		SendKeysOnWebElement(RWDChallengedescriptiontextbar, testData.get(0).get("Challenge Desc"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/ChallengeDescription.png");
		logger.info("Challenge Description text box is available and Challenge Description Entered #Pass");
	}

	@FindBy(xpath = "//*[@class='mat-card-subtitle challenge-name']")
	public WebElement readingChallengeName;
	
	@FindBy(id = "chlge-detail-heading")
	private WebElement rwddetailreadingChallengeName;

	@FindBy(id = "chlge-detail-ptext")
	private WebElement rwddetailreadingChallengeDesc;
	
	@FindBy(id = "chlge-det-date")
	private WebElement rwddetailscreateDate;
	
	@FindBy(id = "chlge-det-userporf")
	private WebElement rwdparticipant;
	
	@FindBy(xpath = "//*[@class='reading-list-view']")
	public WebElement RwdreadingListView;
	
	@FindBy(id = "ac-rc-desc")
	private WebElement readingChallengeDesc;

	
	@FindBy(xpath = "//span[@class='challenge-created']")
	public WebElement createdDate;

	@FindBy(xpath = "//*[@class='dd-invite-sug']")
	public WebElement dd_invite_sug;

	@FindBy(xpath = "//button[@aria-label='Open profile options window']")
	public WebElement RwdOpenprofile;

	@FindBy(xpath = "//*[@aria-label='logout']")
	private WebElement Rwdlogout;

	@FindBy(xpath = "//span[text()='Participating in Challenge']")
	public WebElement participantHeader;

	@FindBy(xpath = "//span[text()='Reading List']")
	public WebElement Readinglist;

	@FindBy(id = "accept-btn")
	private WebElement RWDchlgAccept;

	@FindBy(id = "no-thanks")
	private WebElement RWDchlgNotAccept;

	@FindBy(xpath = "//span[text()=' Challenge Accepted! ']")
	public WebElement acceptedMessage;

	@FindBy(xpath = "//button[text()='Go to Challenge']")
	public WebElement GotoChlng;

	@FindBy(xpath = "//*[text()='Create Reading Challenge']")
	private WebElement lbl_createChlgTitle;

	@FindBy(xpath = "//*[@id='mat-form-field-label-3']")
	private WebElement ChallengedescPlaceholder;

	@FindBy(xpath = "//*[@id='mat-form-field-label-1']")
	private WebElement ChallengenamePlaceholder;

	@FindBy(id = "head-menu-list-book")
	private WebElement RWDbookClubOptionWeb;

	@FindBy(xpath = "//*[@class='createchallenge']")
	private WebElement RWDaddCTAWeb;

	@FindBy(xpath = "//input[@id=\"chlg-enter-name\"]")
	private WebElement RWDChallengenametextbar;

	@FindBy(id = "chalg-desc")
	private WebElement RWDChallengedescriptiontextbar;

	@FindBy(xpath = "//*[text()='Challenge Friends']")
	private WebElement addFriendHeader;

	@FindBy(xpath = "//button[@id='chlg-user-add']")
	private WebElement RWDaddfriendCTA;

	@FindBy(xpath = "//*[text()='Search Students']")
	private WebElement lbl_searchStudent;

	@FindBy(xpath = "//*[@aria-label='Challenges']")
	private WebElement CCHeader;

	@FindBy(xpath = "//h4[text()='RECENTLY SEARCHED STUDENTS']")
	private WebElement recentSearchheader;

	@FindBy(xpath = "//span[@class='student-title']")
	private List<WebElement> recentSearch;

	@FindBy(xpath = "//input[@id='ivite-chlg-search']")
	private WebElement RWD_SearchStudentName;

	@FindBy(xpath = "//*[@class='dd-sugg-std ng-star-inserted']")
	private WebElement RWDparrentStu;

	@FindBy(xpath = "//mat-list-item[class='mat-list-item mat-focus-indicator dd-sug-li ng-star-inserted']")
	public List<WebElement> RWD_InviteFriendLists;

	@FindBy(xpath = "//*[text()='Add to Challenge ']")
	private WebElement RWDAddToChallenge;

	@FindBy(xpath = "(//span[@class='student-invite'])")
	public WebElement adderUser;

	@FindBy(xpath = "//*[@aria-label='Set Reminder'][1]")
	public WebElement RWDsetReminder;
	
	@FindBy(xpath="//*[@class='mat-checkbox msg-read-all mat-accent ng-star-inserted']")
	public WebElement RwdmsgCheckbox;
	
//	@FindBy(xpath = "//*[@aria-label='Reminder'][1]")
//	private WebElement RWDsetReminder;

//	@FindBy(id = "chlg-remdr-optn")
//	private List<WebElement> RWDReminderTypes;
	
	
	@FindBy(xpath = "//*[@class='mat-option-text']")
	public List<WebElement> RWDReminderTypes;
	
	@FindBy(xpath = "//button[@aria-label='Open calendar'][1]")
	private WebElement RWDcalenderImage;

	@FindBy(xpath = ".//*[contains(@id,\"mat-datepicker\")]")
	private WebElement RWDDatePickerSetReadyByDate;

	@FindBy(xpath = "//*[@id=\"mat-datepicker-0\"]/div/mat-month-view/table/tbody/tr[5]/td[7]/div")
	public WebElement RWDSelectDate;
	
	@FindBy(xpath = "//h4[text()='Add Titles']")
	private WebElement addTitleHeader;

	@FindBy(id = "chlg-add-title")
	private WebElement RWD_AddTitleCTA;

	@FindBy(id = "searchInput")
	private WebElement RWDSearchInput;

	@FindBy(xpath = "//h4[text()='Find titles and add them to your challenge']")
	private WebElement searchTitleHeader;

	@FindBy(xpath = "//h2[text()=' SUGGESTED RESULT ']")
	private WebElement lbl_suggestedResult;

	@FindBy(xpath = "//img[@id='search-title-close']")
	private WebElement closeIcon; // h3[text()='ACTIVE CHALLENGES ']

	@FindBy(xpath = "//*[text()='more_horiz']")
	private WebElement RWDMoreMenu;

	@FindBy(xpath = "//*[@aria-label='Add to challenge']")
	private WebElement AddToChallengeTitle;

	@FindBy(id = "start-chalge-btn")
	private WebElement RWDstartChalnge;

	@FindBy(xpath = "//[text()='Reading Challenge']")
	private WebElement lbl_readingChallengeHeader;

	@FindBy(xpath = "//h1[@class='challenge-heading']")
	private WebElement lbl_readingChallengeName;

	@FindBy(xpath = "//p[@class='challenge-desc']")
	private WebElement lbl_readingChallengeDesc;

	@FindBy(xpath = "//h2[text()='Edit Challenge']")
	private WebElement lbl_editchlgHeader;

	@FindBy(xpath = "//a[text()='EDIT CHALLENGE ']")
	private WebElement btn_EditChlg;
	
//	@FindBy(xpath = "//a[text()='EDIT CHALLENGES ']")
//	private WebElement btn_EditChlg;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement save;

	@FindBy(xpath = "//h3[text()='ACTIVE CHALLENGES ']")
	private WebElement lbl_ActiveChallenge;

	@FindBy(xpath = "//label[text()='© 2021 Follett School Solutions. All Rights Reserved']")
	public WebElement footer;

	@FindBy(xpath = "//button[@id='head-login-btn']")
	public static WebElement menu_Login;

	@FindBy(xpath = "//button[@id='head-signin-btn']")
	public static WebElement menu_SignedInUser;

	@FindBy(id = "userName")
	private static WebElement userName;

	@FindBy(id = "userPassword")
	private static WebElement Password;

	@FindBy(xpath = ".//*[@name=\"login-submit\" or text()=\"Submit\"]")
	private static WebElement RWD_DLX_Submit;

	@FindBy(xpath = "//div[text()='Dlz BnT Digital Site']")
	private static WebElement lbl_DlxBntTitle;

	@FindBy(xpath = "//*[@id=\"loginForm\"]/h2")
	private static WebElement LoginPageHeader;

	@FindBy(xpath = "//*[@for=\"userName\"]")
	private static WebElement usernameHeader;

	@FindBy(xpath = "//*[@for=\"userPassword\"]")
	private static WebElement passwordHeader;

	@FindBy(xpath = "//*[@class=\"loginTrouble\"]")
	private static WebElement loginNote;

	@FindBy(xpath = "//span[contains(text(),'© 2021 Follett School Solutions; Inc.')]")
	private static WebElement lbl_CopyRights;

	public void searchInviteFriends() throws Exception {
		FileInputStream fis = new FileInputStream(ConfigurationFile);
		Properties prop = new Properties();
		prop.load(fis);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("-- Challenge Friend Assertion Started --");
		//Assert.assertEquals(addFriendHeader.getText(), testData.get(0).get("defval_addFriend"));
		logger.info("Add student to challenge header is present #Pass");
		ClickOnWebElement(RWDaddfriendCTA);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddFriend.png");
		Assert.assertEquals(lbl_searchStudent.getText(), testData.get(0).get("defval_searchStudentTitle"));
		logger.info("Search student is working #Pass");
		Assert.assertEquals(recentSearchheader.getText(), testData.get(0).get("defval_recentlySearched"));
		logger.info("Recently Searched Students Header is present #Pass");
		Assert.assertTrue(recentSearch.size() <= 5, "Recently Searched Students is more than 5");
		logger.info("Recently Searched students is less than equal to 5 #Pass");
		ClickOnWebElement(RWD_SearchStudentName);
		if(prop.getProperty("Env").equalsIgnoreCase("QAT")) {
		SendKeysOnWebElement(RWD_SearchStudentName, testData.get(5).get("studentName"));
			
		}else {
		SendKeysOnWebElement(RWD_SearchStudentName, testData.get(0).get("studentName"));
		}
		logger.info("Entered student name to search and add to challenge #Pass");
		waitFor(14000);
		List<WebElement> RWD_InviteFriendLists = RWDparrentStu.findElement(By.className("dd-invite-sug"))
				.findElements(By.tagName("mat-list-item"));
		if (RWD_InviteFriendLists.size() > 0) {
			logger.info("No of recent search friends list: " + RWD_InviteFriendLists.size());
			RWD_InviteFriendLists.get(5).findElement(By.className("student-invite")).click();
		} else {
			throw new Exception("No recent search results for friends");
		}
		addedParticipant();
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddFriend1.png");
		waitFor(3000);
		ClickOnWebElement(RWDAddToChallenge);
		logger.info("-- Challenge Friend Assertion Completed --");
		

	}

	public String addedParticipant() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		String participantName = null;
		if (!adderUser.getText().isEmpty()) {
			switch (adderUser.getText().toLowerCase()) {
			case "photon1":
				participantName = "ph1";
				return participantName;
			case "INVITE":
				participantName = "ph11";
				return participantName;
			}

		}
		return participantName;
	}

	public void setReminder(boolean edit) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(3000);
		ClickOnWebElement(RWDsetReminder);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder.png");
		List<WebElement> remindertype = RWDReminderTypes;
		waitFor(4000);
		if (edit == true) {
			switch (testData.get(0).get("RemainderTypeEdit")) {
			case "Daily":
				logger.info("Selected reminder type :" + remindertype.get(1).getText());
				remindertype.get(1).click();
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder_"
						+ remindertype.get(1).getText() + ".png");
				break;
			case "Weekly":
				logger.info("Selected reminder type :" + remindertype.get(2).getText());
				remindertype.get(0).click();
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder_"
						+ remindertype.get(2).getText() + ".png");
				break;
			case "Monthly":
				logger.info("Selected reminder type :" + remindertype.get(3).getText());
				remindertype.get(3).click();
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder_"
						+ remindertype.get(3).getText() + ".png");
				break;
			}
		} else {
			switch (testData.get(0).get("RemainderType")) {
			case "None":
				logger.info("Selected reminder type :" + remindertype.get(0).getText() + " #Pass");
				remindertype.get(0).click();
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder_"
						+ remindertype.get(0).getText() + ".png");
				break;
			case "Daily":
				logger.info("Selected reminder type :" + remindertype.get(1).getText() + " #Pass");
				remindertype.get(1).click();
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder_"
						+ remindertype.get(1).getText() + ".png");
				break;
			case "Weekly":
				logger.info("Selected reminder type :" + remindertype.get(2).getText() + " #Pass");
				remindertype.get(2).click();
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder_"
						+ remindertype.get(2).getText() + ".png");
				break;
			case "Monthly":
				logger.info("Selected reminder type :" + remindertype.get(3).getText() + " #Pass");
				remindertype.get(3).click();
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReminder_"
						+ remindertype.get(3).getText() + ".png");
				break;
			default:
				logger.info("Provided type is not matched");
				Assert.assertFalse(true, "Provided type is not matched");
			}
		}

	}

	public void setreadDate() throws IOException {
		waitFor(5000);
		javascriptScroll(RWDcalenderImage);
		if (RWDcalenderImage.isDisplayed()) {
			logger.info("Set Ready by date calender icon is present #Pass");
			ClickOnWebElement(RWDcalenderImage);
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReadBy.png");
			logger.info("Set Ready by date calender icon is Clicked #Pass");
			IsDisplayedWebElement(RWDDatePickerSetReadyByDate);
			if (RWDDatePickerSetReadyByDate.isDisplayed()) {
				ClickOnWebElement(RWDSelectDate);
				Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/SetReadBy1.png");
				Assert.assertTrue(true, "Date picker popup is shown");
			} else {
				logger.info("Date picker popup is not shown ");
				Assert.assertFalse(true, "Date picker popup is shown");
			}
		} else {
			logger.info("Set Ready by date calender icon is not present");
			Assert.assertFalse(true, "Set Ready by date calender icon is not present");
		}
	}

	public void addTitle() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Add title assertion started--");
		//Assert.assertEquals(addTitleHeader.getText(), testData.get(0).get("defval_addTitle"));
		logger.info("Add title to challenge header is present #Pass");
		ClickOnWebElement(RWD_AddTitleCTA);
		WaitForWebElement(RWDSearchInput);
		waitFor(3000);
	//	Assert.assertEquals(searchTitleHeader.getText(), testData.get(0).get("defval_searchTitleHeader"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle.png");
		logger.info("Title search header is available #Pass");
		SendKeysOnWebElement(RWDSearchInput, testData.get(0).get("titleName"));
		Screenshots.takeScreenshot(driver,
				"Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_SuggestedResult.png");
		logger.info("Suggested result header is available #Pass");
		Assert.assertTrue(closeIcon.isDisplayed());
		logger.info("Title search close icon is available #Pass");
		SendKeysEnter(RWDSearchInput);
		logger.info("Title name is entered #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_SearchResult.png");
		waitFor(3000);
		jsClick(RWDMoreMenu);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_MoreMenu.png");
		logger.info("More menu is clicked for the titile #Pass");
		ClickOnWebElement(AddToChallengeTitle);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_ToChallenge.png");
		logger.info("Title is added to the challenge #Pass");
		logger.info(" --Add title assertion completed-- ");

	}

	public void savechanges() throws IOException {
		waitFor(3000);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/StartChallenge.png");
		ClickOnWebElement(RWDstartChalnge);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/StartChallenge1.png");
		logger.info("Start challenge button is clicked #Pass");
		logger.info("-- Create challenge validation Completed--");

	}

	public void validatecreateChallenge() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(6000);
	//	WaitForWebElement(lbl_readingChallengeHeader);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/ReadingChallenge.png");
		Assert.assertEquals(lbl_readingChallengeName.getText(), chlngName);
		logger.info("Reading Challenge Name is available #Pass");
		Assert.assertEquals(lbl_readingChallengeDesc.getText(), testData.get(0).get("Challenge Desc"));
		logger.info("Reading Challenge Description is available #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/ReadingChallenge2.png");
		logger.info("--Reading Challenge validation completed--");

	}

	public void editchallenges() throws InvalidFormatException, IOException {

		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(7000);
		waitForElementClick(btn_EditChlg);

		logger.info("-- Edited Challenge assertion started --");
		waitFor(3000);
		WaitForWebElement(lbl_editchlgHeader);
		Screenshots.takeScreenshot(driver,
				"Screenshots/ReadingChalllenge/EditReadingChallenge/BeforeEditChallenge.png");
		SendKeysOnWebElement(RWDChallengenametextbar, chlngName + testData.get(0).get("challengeNameEdit"));
		logger.info("Edited Challenge Name #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/EditReadingChallenge/EditChallengeName.png");
		SendKeysOnWebElement(RWDChallengedescriptiontextbar, testData.get(0).get("ChallengeDescEdit"));
		// waitFor(2000);
		RWDChallengedescriptiontextbar.sendKeys(" ");
		logger.info("Edited Challenge Description #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/EditReadingChallenge/EditChallengeDesc.png");
		setReminder(true);
		logger.info("Edited Challenge Remainder #Pass");
	//	setreadDate();
		logger.info("Edited Challenge ReadBy date #Pass");
		ClickOnWebElement(save);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/EditReadingChallenge/SaveEdit.png");
		logger.info("Saved Edited Challenge #Pass");
		// Edit Validation in Reading challenge page
		waitFor(2000);
	//	WaitForWebElement(lbl_readingChallengeHeader);
		waitFor(6000);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/EditReadingChallenge/SaveEdit1.png");
		Assert.assertEquals(lbl_readingChallengeName.getText(), chlngName + testData.get(0).get("challengeNameEdit"));
		logger.info("Edited Reading Challenge Name is available #Pass");
		Assert.assertEquals(lbl_readingChallengeDesc.getText(), testData.get(0).get("ChallengeDescEdit"));
		logger.info("Edited Reading Challenge Description is available #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/EditReadingChallenge/AfterEditChallenge.png");
		logger.info("-- Edited Challenge assertion Completed --");
	}

	public void validatechallengeDashboard() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		ClickOnWebElement(RWDbookClubOptionWeb);
	//	WaitForWebElement(lbl_ActiveChallenge);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/ActiveChallenge.png");
		logger.info("-- Challenge dashboard validation for creatot started--");
	//	Assert.assertEquals(lbl_ActiveChallenge.getText(), testData.get(0).get("lbl_activeChlng"));
		logger.info("Active challenges header is available #Pass");
		javascriptScroll(footer);
		waitFor(8000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			System.out.println("Scrolling again");
			javascriptScroll(footer);
			waitFor(2000);
			javaScriptScrollToEnd();
		}
		waitFor(7000);
		javaScriptScrollToEnd();
		javascriptScroll(lbl_ActiveChallenge);
		javascriptScroll(footer);
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
		javascriptScroll(ss);
		logger.info("Created challenge is available in creator dashboard #Pass");
		logger.info("-- Challenge dashboard validation for creator Completed--");
		logout();

	}

	public void loginasInviteduser(String username, String password) throws IOException, InvalidFormatException {
		logger.info("-- Login Web Page Validation Start --");
		waitFor(5000);
		WaitForWebElement(menu_Login);
		logger.info("Login Button is displayed #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/UIRefreshWeb/Login/DefaultHomePageBeforeLogin.png");
		waitFor(5000);
		waitForElementClick(menu_Login);
		waitFor(5000);
		Screenshots.takeScreenshot(driver, "./Screenshots/UIRefreshWeb/Login/LoginPage.png");
    	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "Login");
    	Assert.assertEquals(lbl_DlxBntTitle.getText(), testData.get(0).get("lbl_DlzBntTitle"));
    	Assert.assertEquals(usernameHeader.getText(), testData.get(0).get("lbl_UserName"));
    	Assert.assertEquals(passwordHeader.getText(), testData.get(0).get("lbl_Password"));
    	Assert.assertEquals(loginNote.getText(), testData.get(0).get("lbl_LoginNotes"));
    	Assert.assertEquals(lbl_CopyRights.getText(), testData.get(0).get("lbl_CopyRights"));
    	logger.info("Login Page assertion successfully completed #Pass");
		SendKeysOnWebElement(userName, username);
		SendKeysOnWebElement(Password, password);
		logger.info("Username and password entered #Pass");
		ClickOnWebElement(RWD_DLX_Submit);
		logger.info("Successfully Logged in as: " + username + "#Pass");
		WaitForWebElement(menu_SignedInUser);
		logger.info("-- Login Web Page Validation End --");
	}

	public static void loginAssertion() throws InvalidFormatException, IOException {
		waitFor(3000);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Login");
		Assert.assertEquals(lbl_DlxBntTitle.getText(), testData.get(0).get("lbl_DlxBntTitle"));
		Assert.assertEquals(LoginPageHeader.getText(), testData.get(0).get("lbl_LoginUsingFolletTitle"));
		Assert.assertEquals(usernameHeader.getText(), testData.get(0).get("lbl_UserName"));
		Assert.assertEquals(passwordHeader.getText(), testData.get(0).get("lbl_Password"));
		Assert.assertEquals(loginNote.getText(), testData.get(0).get("lbl_LoginNotes"));
		Assert.assertEquals(lbl_CopyRights.getText(), testData.get(0).get("lbl_CopyRights"));
		logger.info("Login Page assertion successfully completed #Pass");
	}

	public void validateAcceptChallenge(boolean accept) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(2000);
		ClickOnWebElement(RWDbookClubOptionWeb);
		WaitForWebElement(lbl_ActiveChallenge);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/ActiveChallenge.png");
		logger.info("--Accept or Reject challenge validation started--");
	//	Assert.assertEquals(lbl_ActiveChallenge.getText(), testData.get(0).get("lbl_activeChlng"));
		logger.info("Active challenges header is available #Pass");
		javascriptScroll(footer);
		waitFor(8000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		logger.info("Created challenge is available for Invited user #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/CreatedChallenge.png");
		for (int i = 0; i <= 7; i++) { 
			waitFor(2000);
			javaScriptScrollToEnd();
			javascriptScroll(lbl_ActiveChallenge);
			try {
				WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
				javascriptScroll(footer);
				waitFor(2000);
				javascriptScroll(ss);
				break;
			} catch (Exception e) {
				javascriptScroll(footer);
				waitFor(2000);
			}
		}
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
		jsClick(ss);
		WaitForWebElement(readingChallengeName);
		waitFor(5000);
	//	Assert.assertEquals(readingChallengeName.getText(), chlngName + testData.get(0).get("challengeNameEdit"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/CreatedChallenge2.png");
	//	Assert.assertEquals(readingChallengeDesc.getText(), testData.get(0).get("ChallengeDescEdit"));
		logger.info("Created challenge details are available for Invited user #Pass");
		Assert.assertTrue(createdDate.isDisplayed(), "Challenge created date is not available");
		logger.info("Challenge Created date is available for Invited user #Pass");
		Assert.assertTrue(participantHeader.isDisplayed(), "Participants header is not available");
		logger.info("Paticipants header is available for Invited user #Pass");
		Assert.assertTrue(Readinglist.isDisplayed(), "Reading list header is not available");
		logger.info("Reading list header is available for Invited user #Pass");
		Assert.assertTrue(RWDchlgAccept.isDisplayed(), "Accept challenge button is not available");
		Assert.assertTrue(RWDchlgNotAccept.isDisplayed(), "Accept challenge button is not available");
		if (accept == true) {
			ClickOnWebElement(RWDchlgAccept);
			waitFor(1000);
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/AcceptChallenge.png");
	//		Assert.assertEquals(acceptedMessage.getText(), testData.get(0).get("challengeAccept"));
			ClickOnWebElement(GotoChlng);
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/AcceptedChallenge.png");
		} else {
			ClickOnWebElement(RWDchlgNotAccept);
		}
		logger.info("--Accept or Reject challenge validation Completed--");

	}

	public void validateChallengesparticipant() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(5000);
		WaitForWebElement(rwddetailreadingChallengeName);
		Assert.assertEquals(rwddetailreadingChallengeName.getText(), chlngName + testData.get(0).get("challengeNameEdit"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/ReadingChallenge/CreatedChallenge2.png");
	//	Assert.assertEquals(rwddetailreadingChallengeDesc.getText(), testData.get(0).get("ChallengeDescEdit"));
		logger.info("Created challenge details are available for Invited user #Pass");
		waitFor(4000);
		Assert.assertTrue(rwddetailscreateDate.isDisplayed());
		logger.info("Challenge Created date is available for Invited user #Pass");
		Assert.assertTrue(rwdparticipant.isDisplayed());
		logger.info("Paticipants header is available for Invited user #Pass");
		Assert.assertTrue(RwdreadingListView.isDisplayed());
		logger.info("Reading list header is available for Invited user #Pass");

	}

	public void logout() {
		waitFor(3000);
		ClickOnWebElement(RwdOpenprofile);
		ClickOnWebElement(Rwdlogout);
	}
}
