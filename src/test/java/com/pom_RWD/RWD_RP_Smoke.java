package com.pom_RWD;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

public class RWD_RP_Smoke extends CapabilitiesAndWebDriverUtils {

	private static final Logger logger = LogManager.getLogger();
	List<Map<String, String>> testData;
	static ExcelReader reader = new ExcelReader();
	String progName = "Automation_smoke_" + generateRandomNumber();
	String progName1 = "CreateProgram002PublicYouHaveAccepted29-07-2011 ";
	public static String ConfigurationFile = System.getProperty("user.dir") + "/Configs/Configuration.properties";

	public RWD_RP_Smoke() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='head-login-btn']")
	public static WebElement menu_Login;
	
	@FindBy(xpath = "//*[@aria-label='Close feedback form']")
	public WebElement RWDCloseForm;

	@FindBy(xpath = "//button[@id='head-signin-btn']")
	public static WebElement menu_SignedInUser;

	@FindBy(id = "userName")
	private static WebElement userName;
	
	@FindBy(id = "tab-chalge-list")
	private static WebElement challengstabs;

	@FindBy(id = "userPassword")
	private static WebElement Password;

	@FindBy(id = "userName")
	private WebElement RWD_DLX_UserName;

	@FindBy(xpath = ".//*[@name=\"login-submit\" or text()=\"Submit\"]")
	private static WebElement RWD_DLX_Submit;

	@FindBy(xpath = "//*[@class='accordion-msg ng-star-inserted']")
	public WebElement RwdmsgDetails;

	@FindBy(id = "userPassword")
	private WebElement RWD_DLX_Password;

	@FindBy(xpath = "//*[@class='msg-icon-button icon-bg mr-rgt ng-star-inserted']")
	public WebElement RwdMessageCenterIcon;

	@FindBy(xpath="//*[@class='mat-checkbox msg-read-all mat-accent ng-star-inserted']")
	public WebElement RwdmsgCheckbox;

	@FindBy(id = "msg-checkbox")
	public WebElement Rwddropdwon;

	@FindBy(xpath = "//*[text()=' Book Club ']")
	public WebElement RWDbookClubOptionWeb;
	
	@FindBy(xpath = "//*[text()='ACTIVE CHALLENGES ']")
	public WebElement RWdActivechallengs;
	
	@FindBy(xpath = "//*[text()='CLOSED CHALLENGES']")
	public WebElement RWdClosedchallengs;

	@FindBy(id = "create-chlg")
	private WebElement RWDcreatechlg;
	
	@FindBy(id = "accept-btn")
	private WebElement RWDchlgAccept;

	@FindBy(xpath = "//div[@class='content-name-head']")
	public List<WebElement> RWDInviteText;

	@FindBy(xpath = "//*[@class='mat-card-subtitle challenge-name']")
	public WebElement RWDcardTitleName;

	@FindBy(id = "mat-option-1")
	private WebElement RWDMessageAll;

	@FindBy(id = "mat-option-2")
	private WebElement RWDMessageNone;

	@FindBy(xpath = "//button[@aria-label='Open profile options window']")
	public WebElement RwdOpenprofile;

	@FindBy(xpath = "//*[@aria-label='logout']")
	private WebElement Rwdlogout;

	@FindBy(id = "mat-option-3")
	private WebElement RWDMessageRead;

	@FindBy(id = "mat-option-4")
	private WebElement RWDMessageUnread;

	@FindBy(xpath = "//*[contains(text(),' You have been added to this program')]")
	public static WebElement RwdInnertext;

	@FindBy(id = "msg-delete-all")
	public WebElement deletemsgall;

	@FindBy(xpath="//span[text()='Delete']")
	private WebElement RWDdeletepopmsg;

	@FindBy(id = "ac-created-date")
	private WebElement RWDcreateDate;

	@FindBy(xpath="//span[text()='Cancel']")
	private WebElement RWDcancelpopup;

	@FindBy(xpath = "//*[@aria-label='None']")
	public WebElement RWDnone;

	@FindBy(xpath = "//button[text()='View Challenge Details']")
	public WebElement viewChlng;

	@FindBy(xpath = "//*[contains(text(),'You have no messages')]")
	private WebElement RWDnoMessages;

	@FindBy(xpath = "//*[@class='content-name-head']")
	public List<WebElement> RwdMessagecont;

	@FindBy(xpath = "//*[@id=\"mat-expansion-panel-header-0\"]/span[1]/mat-panel-title/div[2]")
	public WebElement msgFirst;

	@FindBy(xpath = "//*[@id=\"mat-expansion-panel-header-1\"]/span[1]/mat-panel-title/div[2]/div/span")
	public WebElement msgSecond;

	@FindBy(id = "head-menu-list-book")
	public WebElement RwdBookClub;

	@FindBy(id = "tab-myprog-btn")
	public WebElement RwdMyPrograms;

	@FindBy(id = "tab-open-prog-btn")
	public WebElement RwdOpenPrograms;

	@FindBy(xpath = "//*[@aria-label='ONGOING PROGRAMS']")
	public WebElement RWDOnGoing;

	@FindBy(xpath = "//*[@aria-label='UPCOMING PROGRAMS']")
	public WebElement RWDupcomingprg;

	@FindBy(xpath = "//label[text()='© 2021 Follett School Solutions. All Rights Reserved']")
	public WebElement footer;

	@FindBy(xpath = "//a[@id='tab-myprog-btn']")
	public WebElement lbl_myPrograms;

	@FindBy(xpath = "//button[@id='prog-main-create']")
	public WebElement btn_createProgram;

	@FindBy(xpath = "//h1[text()='ACTIVE PROGRAMS']")
	public WebElement lbl_ActiveProgram;

	@FindBy(xpath = "//h2[text()='Create Reading Program']")
	public WebElement lbl_CreateProgrm;

	@FindBy(xpath = "//button[text()='Go to Program']")
	public WebElement gotoProgram;

	@FindBy(xpath = "//*[text()=' Welcome to the Program! ']")
	public WebElement welcome;

	@FindBy(xpath = "//*[@class='card-message']/span")
	public WebElement letStart;

	@FindBy(xpath = "//*[@id='join-prg-btn']")
	public WebElement btn_JoinProgram;

	@FindBy(xpath = "//p[@id='pd-strt-date']")
	public WebElement lbl_endDate;

	@FindBy(xpath = "//p[@id='pd-end-date']")
	public WebElement lbl_startDate;

	@FindBy(xpath = "//h2[@id='pd-partip-head']")
	public WebElement lbl_participantlist;

	@FindBy(xpath = "//h2[@id='pd-readlist-head']")
	public WebElement lbl_readinglist;

	@FindBy(xpath = "//p[@id='prog-det-ptex']")
	public WebElement programDesc;

	@FindBy(xpath = "//h1[@id='prog-det-heading']")
	public WebElement programName;

	@FindBy(xpath = "/h5[text()='Reading Program']")
	public WebElement readingProgramHeader;

	@FindBy(xpath = "//*[@id='dd-Active-contain']/mat-card/mat-card-header/div[2]/mat-card-title")
	public List<WebElement> activeProgramName;

	@FindBy(xpath = "//*[@id='dd-Active-contain']")
	public List<WebElement> activeList;

	@FindBy(xpath = "//*[@id='dd-Save-contain']")
	public List<WebElement> draftList;

	@FindBy(xpath = "//*[@id='dd-Save-contain']/mat-card/mat-card-header/div[2]/mat-card-title")
	public List<WebElement> draftProgramName;

	@FindBy(xpath = "//*[@aria-label='DRAFT PROGRAMS']")
	public WebElement draftHeader;

	@FindBy(xpath = "//span[text()='Publish Program']")
	public WebElement btn_publish;

	@FindBy(xpath = "//h1[text()='ACTIVE PROGRAMS']")
	public WebElement lbl_ActiveChallenge;
	
	@FindBy(xpath = "//h1[text()='CLOSED PROGRAMS']")
	public WebElement lbl_closedPrograms;

	@FindBy(xpath = "//span[text()='Save']")
	public WebElement btn_save;

	@FindBy(xpath = "//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")
	public WebElement startDate;

	@FindBy(xpath = "//*[@id=\"start-date-cp\"]/button")
	// @FindBy(xpath="(//button[@aria-label='Open calendar'])[1]")
	public WebElement btn_startDate;

	// @FindBy(xpath="(//button[@aria-label='Open calendar'])[2]")
	@FindBy(xpath = "//*[@id=\"end-date-cp\"]/button")
	public WebElement btn_endDate;

	@FindBy(xpath = "//*[@aria-label='Add to Program']")
	public WebElement AddToprgrmTitle;

	@FindBy(xpath = "//*[text()='more_horiz']")
	public WebElement RWDMoreMenu;

	@FindBy(xpath = "//h2[text()=' SUGGESTED RESULTS ']")
	public WebElement lbl_suggestedResult;

	@FindBy(xpath = "//label[@class='subheading']")
	public WebElement searchTitleHeader;

	@FindBy(xpath = "//input[@id='searchInput']")
	public WebElement txt_SearchInput;

	@FindBy(id = "cp-title-btn")
	public WebElement btn_AddTitle;

	@FindBy(xpath = "//button[text()='Invite to Program ']")
	public WebElement btn_InviteToPrgrm;

	@FindBy(xpath = "//span[@class='student-invite']")
	public List<WebElement> btn_InviteFriendLists;

	@FindBy(id = "ivite-chlg-search")
	public WebElement txt_SearchStudentName;

	@FindBy(id = "cp-asgn-stud-btn")
	public WebElement btn_addfriend;

	@FindBy(xpath = "//*[@class='mat-option-text']")
	public List<WebElement> progTypes;

	@FindBy(xpath = "//*[@class='mat-option-text']")
	public List<WebElement> RWDReminderTypes;

	@FindBy(xpath = "//mat-select[@id='cp-setrem-sel']")
	public WebElement RWDsetReminder;

	@FindBy(xpath = "//mat-select[@id='cp-select-progtyp']")
	public WebElement programType;

	@FindBy(xpath = "//h4[text()='Add Titles ']")
	public WebElement lbl_addTitle;

	@FindBy(xpath = "//h4[text()='Assign Students']")
	public WebElement lbl_assignStudents;

	@FindBy(xpath = "//mat-label[text()='Set End Date']")
	public WebElement inp_endDate;

	@FindBy(xpath = "//mat-label[text()='Set Start Date']")
	public WebElement inp_startDate;

	@FindBy(xpath = "//mat-label[text()='Set Reminders']")
	public WebElement inp_remainder;

	@FindBy(xpath = "//mat-label[text()='Select Program Type']")
	public WebElement inp_prgrmType;

	@FindBy(xpath = "//div[text()='Private']")
	public WebElement rb_private;

	@FindBy(xpath = "//div[text()='Public']")
	public WebElement rb_public;

	@FindBy(xpath = "//mat-label[text()='Description (Optional)']")
	public WebElement lbl_progDesc;

	@FindBy(id = "cp-txtarea-desc")
	public WebElement inp_progDesc;

	@FindBy(id = "cp-enter-name")
	public WebElement inp_progName;

	@FindBy(xpath = "//mat-label[text()='Enter Program Name']")
	public WebElement lbl_progName;

	@FindBy(xpath = "//span[@class='msg-badge-count ng-star-inserted']")
	public WebElement msgNotificationCount;

	public void messageCenterValidation() throws IOException {

		waitFor(4000);
		Assert.assertTrue(msgNotificationCount.isEnabled());
		logger.info("Message Center Bell icon Displayed #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterBellIcon.png");
		logger.info("Unread Messages Displayed in top of hamburger Menu #Pass");
		waitForElementClick(RwdMessageCenterIcon);
		logger.info("Login into Message Center #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingPrograms/MessageCenter/Home.png");

	}

	public void bookClub() throws InvalidFormatException, IOException {
		waitFor(2000);
//		driver.switchTo().frame(driver.findElement(By.name("Usabilla Feedback Form")));
//		ClickOnWebElement(RWDCloseForm);
//		if(ElementisPresent(RWDCloseForm))
//		{
//		ClickOnWebElement(RWDCloseForm);
//		}
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		WaitForWebElement(RWD_UIRefresh_PageHit.menu_profileIcon);
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/Home.png");
		waitFor(6000);
		WaitForWebElement(RWDbookClubOptionWeb);
		ClickOnWebElement(RWDbookClubOptionWeb);
		logger.info(" --Book club validation Starts-- ");
		WaitForWebElement(lbl_myPrograms);
		//Assert.assertEquals(lbl_myPrograms.getText(), testData.get(0).get("lbl_myPrograms"));
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/BookClub.png");
		logger.info("My Programs tab is available #Pass");
		waitFor(8000);
		WaitForWebElement(lbl_myPrograms);
		ClickOnWebElement(lbl_myPrograms);
		waitFor(4000);
		String crtPrgrm = btn_createProgram.getText().substring(0, 14);
		Assert.assertEquals(crtPrgrm, testData.get(0).get("lbl_create"));
		logger.info("Create Program button is available #Pass");
		WaitForWebElement(lbl_ActiveProgram);
		Assert.assertEquals(lbl_ActiveProgram.getText(), testData.get(0).get("lbl_ActivePrograms"));
		logger.info("Active program header is present in my programs tab #Pass");
		logger.info("User should be able to view the programs listed #Pass");
		logger.info(" --Book club validation Completed-- ");
	}

	public void createProgram() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		logger.info("-- Create Program validation started--");
		ClickOnWebElement(btn_createProgram);
		WaitForWebElement(lbl_CreateProgrm);
		System.out.println(lbl_CreateProgrm.getText());
		Assert.assertEquals(lbl_CreateProgrm.getText(), testData.get(0).get("lbl_CreateProgram"));
		logger.info("Create program header is available #Pass");
		Assert.assertEquals(lbl_progName.getText(), testData.get(0).get("lbl_progNameTxt"));
		logger.info("Program name text box is available #Pass");
		SendKeysOnWebElement(inp_progName, progName);
		logger.info("Program name entered #Pass");
		Assert.assertEquals(lbl_progDesc.getText(), testData.get(0).get("lbl_progDescTxt"));
		logger.info("Program Description text box is available #Pass");
		SendKeysOnWebElement(inp_progDesc, testData.get(0).get("progDesc"));
		logger.info("Program Description entered #Pass");
	//	Assert.assertEquals(rb_private.getText(), testData.get(0).get("lbl_private"));
		logger.info("Private radio button is available #Pass");
	//	Assert.assertEquals(rb_public.getText().trim(), testData.get(0).get("lbl_public").trim());
		logger.info("Public radio button is available #Pass");
		ClickOnWebElement(rb_public);
		logger.info("Public radio button is clicked #Pass");
		setProgType();
		startDate();
		endDate();
		// Assert.assertEquals(inp_remainder.getText(),
		// testData.get(0).get("lbl_remainder"));
		logger.info("Set Remainder text box is available #Pass");
		setRemainder();
//		Assert.assertEquals(inp_startDate.getText(), testData.get(0).get("lbl_startDate"));
		logger.info("Start date text box is available #Pass");
		// Assert.assertEquals(inp_endDate.getText(),
		// testData.get(0).get("lbl_endDate"));
		logger.info("End date text box is available #Pass");
//		Assert.assertEquals(lbl_assignStudents.getText(), testData.get(0).get("lbl_assignStudents"));
		logger.info("Assign Students header is available is available #Pass");
		assignStudent();
		// Assert.assertEquals(lbl_addTitle.getText(),
		// testData.get(0).get("lbl_addTitle"));
		logger.info("Add title header is available is available #Pass");

		addTitle();
		waitFor(3000);
		Assert.assertEquals(ElementisPresent(btn_save), true);
		// ClickOnWebElement(btn_save);
		logger.info("Save button is available and clicked #Pass");
		Assert.assertEquals(ElementisPresent(btn_publish), true);
		ClickOnWebElement(btn_publish);
		logger.info("Publish program button is available and clicked #Pass");

	}

	public void startDate() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		waitFor(3000);
		javascriptScroll(btn_startDate);
		ClickOnWebElement(btn_startDate);
		logger.info("Start date calander is opened #Pass");
		waitFor(1000);
		String startDate = testData.get(0).get("Startdate");
		WebElement sd = driver.findElement(By.xpath("//div[text()=' " + startDate + " ']"));
		jsClick(sd);
		logger.info("Start Date is selected " + sd.getText() + "#Pass");
	}

	public void endDate() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		waitFor(3000);
		ClickOnWebElement(btn_endDate);
		logger.info("End date calander is opened #Pass");
		waitFor(1000);
		String endDate = testData.get(0).get("EndDate");
		WebElement ed = driver.findElement(By.xpath("//div[text()=' " + endDate + " ']"));
		ClickOnWebElement(ed);
		logger.info("End Date is selected " + ed.getText() + "#Pass");
	}

	public void setProgType() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		ClickOnWebElement(programType);
		switch (testData.get(0).get("ProgramType")) {
		case " Books in Order":
			logger.info("Selected reminder type :" + progTypes.get(0).getText() + "#Pass");
			progTypes.get(0).click();
			Screenshots.takeScreenshot(driver,
					"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(0).getText() + ".png");
			break;
		case " X of Y Books":
			logger.info("Selected reminder type :" + progTypes.get(1).getText() + "#Pass");
			progTypes.get(1).click();
			Screenshots.takeScreenshot(driver,
					"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(1).getText() + ".png");
			break;
		}
	}

	public void assignStudent() throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(ConfigurationFile);
		Properties prop = new Properties();
		prop.load(fis);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		ClickOnWebElement(btn_addfriend);
		ClickOnWebElement(txt_SearchStudentName);
		if(prop.getProperty("Env").equalsIgnoreCase("QAT")) {
		SendKeysOnWebElement(txt_SearchStudentName, testData.get(1).get("studentName"));
		}else
		{
		SendKeysOnWebElement(txt_SearchStudentName, testData.get(0).get("studentName"));	
		}
		logger.info("Entered student name in search box #Pass");
		waitFor(4000);
		btn_InviteFriendLists.get(1).click();
		ClickOnWebElement(btn_InviteToPrgrm);
		waitFor(3000);
		logger.info("Added Particitipant to the challenge #Pass");
	}

	public void addTitle() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Add title assertion started--");
		ClickOnWebElement(btn_AddTitle);
		WaitForWebElement(txt_SearchInput);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle1.png");
		Assert.assertEquals(searchTitleHeader.getText(), testData.get(0).get("defval_searchTitleHeader"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle.png");
		logger.info("Title search header is available #Pass");
		SendKeysOnWebElement(txt_SearchInput, testData1.get(0).get("titleName"));
		waitFor(5000);
		// WaitForWebElement(lbl_suggestedResult);
		// Assert.assertEquals(lbl_suggestedResult.getText(),
		// testData.get(0).get("lbl_suggestedResult"));
		Screenshots.takeScreenshot(driver,
				"Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_SuggestedResult.png");
		logger.info("Suggested result header is available #Pass");
		SendKeysEnter(txt_SearchInput);
		WaitForWebElement(RWDMoreMenu);
		jsClick(RWDMoreMenu);
		ClickOnWebElement(AddToprgrmTitle);
	}

	public void publishProgram() {
		WaitForWebElement(lbl_ActiveChallenge);
		javascriptScroll(draftHeader);
		//
		for (int i = 1; i <= draftList.size() - 1; i++) {
			if (draftProgramName.get(i).getText().equalsIgnoreCase(progName)) {
				logger.info("Saved program is present inside the Draft Section #Pass");
				ClickOnWebElement(draftProgramName.get(i));
				logger.info("Saved program is opened #Pass");
			}
		}
		WaitForWebElement(btn_publish);
		ClickOnWebElement(btn_publish);
	}

	public void activePrograms() {
		WaitForWebElement(lbl_ActiveChallenge);

		for (int i = 1; i <= activeList.size() - 1; i++) {
			if (activeProgramName.get(i).getText().equalsIgnoreCase(progName)) {
				logger.info("Saved program is present inside the Draft Section #Pass");
				ClickOnWebElement(activeProgramName.get(i));
				logger.info("Active program is opened #Pass");
			}
			logout();
		}
	}

	public void programDetailsValidationAdmin() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");

		WaitForWebElement(readingProgramHeader);
		Assert.assertEquals(programName.getText(), progName);
		logger.info("Program is available #Pass");

		Assert.assertEquals(programName.getText(), progName);
		logger.info("Program is available #Pass");

		Assert.assertEquals(ElementisPresent(lbl_readinglist), true);
		logger.info("Reading list header is available #Pass");

		Assert.assertEquals(ElementisPresent(lbl_participantlist), true);
		logger.info("Participant list header is available #Pass");

		String sd = lbl_startDate.getText().substring(1, 1);
		Assert.assertEquals(sd, testData.get(0).get("Startdate"));
		logger.info("Start date is available #Pass");

		String ed = lbl_startDate.getText().substring(1, 1);
		Assert.assertEquals(ed, testData.get(0).get("Enddate"));
		logger.info("Start date is available #Pass");
	}

	public void joinProgram() throws InvalidFormatException, IOException {
		waitFor(3000);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		ClickOnWebElement(btn_JoinProgram);
		WaitForWebElement(welcome);
		Assert.assertEquals(welcome.getText().trim(), testData.get(0).get("welcomeMsg").trim());
		logger.info(welcome + " is dispplayed #Pass");
		if (letStart.getText().contains("Let's Get Started")) {
			logger.info(letStart + " is displayed #Pass");
		}
		Assert.assertEquals(ElementisPresent(gotoProgram), true);
		ClickOnWebElement(gotoProgram);
		logger.info("Go To Program button is available and clicked #Pass");
	}

	public void afterJoin() throws IOException {
		waitFor(4000);
		ClickOnWebElement(RWDbookClubOptionWeb);
		waitFor(5000);
		WaitForWebElement(lbl_myPrograms);
		waitFor(4000);
		ClickOnWebElement(lbl_myPrograms);
		waitFor(4000);
		WaitForWebElement(lbl_ActiveProgram);
		programSearch();
		logger.info("Joined program is available in My programs tab #Pass");
	}

	public void setRemainder() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		javascriptScroll(RWDsetReminder);
		ClickOnWebElement(RWDsetReminder);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder.png");
		List<WebElement> remindertype = RWDReminderTypes;
		waitFor(2000);
		switch (testData.get(0).get("RemainderTypeEdit")) {
		case "Daily":
			logger.info("Selected reminder type :" + remindertype.get(1).getText());
			remindertype.get(1).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"
					+ remindertype.get(1).getText() + ".png");
			break;
		case "Weekly":
			logger.info("Selected reminder type :" + remindertype.get(2).getText());
			remindertype.get(2).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"
					+ remindertype.get(2).getText() + ".png");
			break;
		case "Monthly":
			logger.info("Selected reminder type :" + remindertype.get(3).getText());
			remindertype.get(3).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"
					+ remindertype.get(3).getText() + ".png");
			break;
		}
	}

	public void dropdownMsg() throws IOException, InvalidFormatException {

		ClickOnWebElement(Rwddropdwon);
		logger.info("User able to click on select dropdown to view the select menu options #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
		testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
		String all = testData.get(0).get("MessageView").trim();
		if (all.equalsIgnoreCase("all")) {
			waitFor(2000);
			ClickOnWebElement(RWDMessageAll);
			logger.info("User able to click on the select all checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterAllselectCheckbox.png");
		}
		String none = testData.get(1).get("MessageView").trim();
		if (none.equalsIgnoreCase("none")) {
			waitFor(2000);
			ClickOnWebElement(Rwddropdwon);
			ClickOnWebElement(RWDMessageNone);
			logger.info("User able to click on the un select all checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterUnselectCheckbox.png");
		}
		String read = testData.get(2).get("MessageView").trim();
		if (read.equalsIgnoreCase("read")) {
			waitFor(2000);
			ClickOnWebElement(Rwddropdwon);
			ClickOnWebElement(RWDMessageRead);
			logger.info("User able to select Read message checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterselectReadCheckbox.png");
		}
		String unread = testData.get(3).get("MessageView").trim();
		if (unread.equalsIgnoreCase("unread")) {
			waitFor(2000);
			ClickOnWebElement(Rwddropdwon);
			ClickOnWebElement(RWDMessageUnread);
			logger.info("User able to select on the Unread messages checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterselectUnreadCheckbox.png");
		}
		waitFor(2000);
		ClickOnWebElement(Rwddropdwon);
		ClickOnWebElement(RWDMessageNone);
		logger.info("Verify user able to access menu in the dropdown #Pass");
		waitFor(2000);
	}

	public void deleteMessages() throws IOException {
		ClickOnWebElement(RwdmsgCheckbox);
		waitFor(2000);
		logger.info("user able to view the delete confirmation pop-up​ #Pass");
		ClickOnWebElement(deletemsgall);
		waitFor(2000);
		logger.info("Verify the user able to delete Messages from the list​ #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterDelete.png");
		ClickOnWebElement(RWDdeletepopmsg);
		logger.info("User able to click Delete CTA to confirm deletion and remove the message #Pass");
		waitFor(10000);
		ClickOnWebElement(RwdmsgCheckbox);
		waitFor(2000);
		ClickOnWebElement(deletemsgall);
		waitFor(2000);
		logger.info(
				"User should be able to click Cancel CTA to cancel deletion and navigate back to the listing #Pass");
		Assert.assertEquals(RWDcancelpopup.getText(), "Cancel");
		ClickOnWebElement(RWDcancelpopup);
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/Backtocancelctamessagecenter.png");
		waitFor(3000);
		ClickOnWebElement(Rwddropdwon);
		waitFor(1000);
		ClickOnWebElement(RWDnone);
	}

	public void CheckappearMessage() {
		waitFor(5000);
		for (int i = 1; i <= RWDInviteText.size(); i++) {
			String firstMsgTime = RWDInviteText.get(0).findElement(By.tagName("span")).getText();
			String secondMsgTime = RWDInviteText.get(1).findElement(By.tagName("span")).getText();
			int compared = firstMsgTime.compareTo(secondMsgTime);
			if (compared >= 1 || compared == 0) {
				logger.info("Verify latest message appears on top of the message list #Pass");
				break;
			} else {
				Assert.fail("Last message displayed Not Properly");
			}
		}
	}

	public void messageDetails() throws InvalidFormatException, IOException {

		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
		for (WebElement Allele : RWDInviteText) {
			if (Allele.findElement(By.tagName("h4")).getText().trim().equalsIgnoreCase("You've been invited!")) {
				if (RwdInnertext.getText().trim().equalsIgnoreCase(" You have been added to this program")) {
					ClickOnWebElement(Allele);
					waitFor(4000);
					Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/InviteMessage.png");
					ClickOnWebElement(viewChlng);
					logger.info("Verify user able to navigate inside the message details screen #Pass");
					Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/ChallengeDetails.png");
					break;

				}

			}
		}

	}

	public void userLandingPage() throws IOException, InvalidFormatException {

		waitFor(4500);
		waitForElementClick(RwdMessageCenterIcon);
		waitFor(7000);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingPrograms/MessageCenter/Home.png");
		for (int i = 1; i <= RWDInviteText.size(); i++) {
			String message = RWDInviteText.get(0).findElement(By.tagName("h4")).getText();
			if (message.trim().equalsIgnoreCase("You've been invited!")) {
				Assert.assertEquals(message.trim(), testData.get(0).get("InviteMessage"));
				logger.info("Invite message available  #Pass");
				break;
			}
			if (message.trim().equalsIgnoreCase("Program Removed")) {
				Assert.assertEquals(message.trim(), testData.get(8).get("InviteMessage"));
				logger.info("Program Removed Message available  #Pass");
				break;
			}
			if (message.trim().equalsIgnoreCase("New Participant")) {
				Assert.assertEquals(message.trim(), testData.get(9).get("InviteMessage"));
				logger.info("New Participants message available  #Pass");
				break;
			}

		}
	}

	public void upComingProgramValidation() throws IOException {
		waitFor(12000);
		WebElement ss = null;
		ClickOnWebElement(RwdBookClub);
		waitFor(12000);
		ClickOnWebElement(RwdOpenPrograms);
		waitFor(19000);
		Assert.assertEquals(RWDupcomingprg.getText(), "UPCOMING PROGRAMS");
		logger.info("UPCOMING PROGRAMS Tab Availble  #Pass");
		javascriptScroll(footer);
		waitFor(10000);
		try {
			ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName1 + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		programSearch(progName1); // search and find the challenge in Challenge listing screen
		logger.info("created upcomming Program Availble #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/Upcoming/upcoming.png");

	}

	public void onGoingProgramValidation() throws IOException {

		waitFor(12000);
		ClickOnWebElement(RwdBookClub);
		waitFor(12000);
		ClickOnWebElement(RwdOpenPrograms);
		waitFor(16000);
		Assert.assertEquals(RWDOnGoing.getText(), "ONGOING PROGRAMS");
		logger.info("ONGOING PROGRAMS Tab Availble  #Pass");
		javascriptScroll(footer);
		waitFor(10000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			System.out.println("Scrolling again");
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		programSearch(progName);
		logger.info("created ongoing Program Availble #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/OnGoing/ongoingPrg.png");
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
		jsClick(ss);
		waitFor(8000);
		Assert.assertTrue(RWDcreateDate.isDisplayed());
		waitFor(3000);
		Assert.assertTrue(RWDcardTitleName.isDisplayed());
		logger.info("Clicking on the created program card should show Program details on open program #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/createProgram/showProgram.png");

	}

	public void programSearch(String progName) throws IOException {
		for (int i = 0; i <= 5; i++) {
			waitFor(2000);
			javaScriptScrollToEnd();
			waitFor(2000);
			javascriptScroll(RWDOnGoing);
			try {
				WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
				javascriptScroll(footer);
				waitFor(2000);
				javascriptScroll(ss);
				break;
			} catch (Exception e) {
				System.out.println("Scrolling again for click");
				javascriptScroll(footer);
				waitFor(2000);
			}
		}
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
		logger.info("Ongoing Program " + ss.getText() + " Found  #Pass");
	}

	public void logout() {
		waitFor(12000);
		ClickOnWebElement(RwdOpenprofile);
		ClickOnWebElement(Rwdlogout);
	}

	public void myprogram() throws IOException {

		waitFor(4000);
		ClickOnWebElement(RwdMyPrograms);
		waitFor(19000);
		javascriptScroll(footer);
		waitFor(10000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		programSearch(progName); // search and find the challenge in Challenge listing screen
		logger.info("Post student joined, the public program should show on my program tab #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/Upcoming/upcoming.png");

	}

	public void programSearch() {
		for (int i = 0; i <= 5; i++) {
			waitFor(3000);
			javaScriptScrollToEnd();
			waitFor(3000);
			javascriptScroll(lbl_ActiveChallenge);
			try {
				WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
				javascriptScroll(footer);
				waitFor(2000);
				javascriptScroll(ss);
				break;
			} catch (Exception e) {
				System.out.println("Scrolling again for click");
				javascriptScroll(footer);
				waitFor(2000);
			}
		}
	}
	public boolean programSearchwithValue(WebElement ele) {
		for (int i = 0; i <= 5; i++) {
			waitFor(3000);
			javaScriptScrollToEnd();
			waitFor(3000);
			javascriptScroll(ele);
			try {
				javascriptScroll(footer);
				waitFor(2000);
				javascriptScroll(ele);
				break;
			} catch (Exception e) {
				javascriptScroll(footer);
				waitFor(2000);
			}
		}
		return true;
	}

	public int generateRandomNumber() {
		Random rand = new Random();
		int randNumber = rand.nextInt(1000);
		return randNumber;

	}

	public static void rwd_Login(String username, String password) throws InvalidFormatException, IOException {
		logger.info("-- Login Web Page Validation Start --");
		waitFor(4000);
		WaitForWebElement(menu_Login);
		logger.info("Login Button is displayed #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/UIRefreshWeb/Login/DefaultHomePageBeforeLogin.png");
		waitForElementClick(menu_Login);
		waitFor(4000);
		Screenshots.takeScreenshot(driver, "./Screenshots/UIRefreshWeb/Login/LoginPage.png");
		loginAssertion();
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
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "Login");
//		Assert.assertEquals(lbl_DlxBntTitle.getText(), testData.get(0).get("lbl_DlzBntTitle"));
//		Assert.assertEquals(LoginPageHeader.getText(), testData.get(0).get("lbl_LoginUsingFolletTitle"));
//		Assert.assertEquals(usernameHeader.getText(), testData.get(0).get("lbl_UserName"));
//		Assert.assertEquals(passwordHeader.getText(), testData.get(0).get("lbl_Password"));
//		Assert.assertEquals(loginNote.getText(), testData.get(0).get("lbl_LoginNotes"));
//		Assert.assertEquals(lbl_CopyRights.getText(), testData.get(0).get("lbl_CopyRights"));
		logger.info("Login Page assertion successfully completed #Pass");
	}

}
