package com.pom;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

public class OpenProgram extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(Login.class);
	static MyPrograms myprogram = new MyPrograms();

	public OpenProgram() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static void onGoingProgramDetailPageValidation() throws IOException {
		ClickOnMobileElement(myprogram.openProgram_tab);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/OpenProgram/Landing_page.png");
		waitFor(10000);
		Assert.assertEquals(onGoingProgram_lbl.isDisplayed(), true);
		
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		ClickOnMobileElement(openprogramList.get(0));
		}
		if (getData("platformName").equalsIgnoreCase("ios")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
		ClickOnMobileElement(openprogramList.get(5));
		System.out.println("OpenProgram Clicked");
		}
		waitFor(9000);
		logger.info(" --" + getData("platformName") + " - User Navigated to the ongoing detail page #pass");

		Assert.assertEquals(readingProgram_lbl.isDisplayed(), true);
		logger.info(getData("platformName") + " - RP label displayed #pass");
		Assert.assertEquals(readingProgram_Icon.isDisplayed(), true);
		logger.info(getData("platformName") + " - RP Icon displayed #pass");
		Assert.assertEquals(programName.isDisplayed(), true);
		logger.info(getData("platformName") + "-Program Name: " + programName.getText() + "- RP name displayed #pass");
		
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(desc.isDisplayed(), true);
			System.out.println("Created Date Displayed");
			logger.info(getData("platformName") + " - RP description displayed #pass");
			Assert.assertEquals(createdDate.isDisplayed(), true);
			waitFor(3000);
			System.out.println("StartDate: " + startDate.getText());
			Assert.assertEquals(startDate.isDisplayed(), true);
			System.out.println("endDate: " + endDate.getText());
			Assert.assertEquals(endDate.isDisplayed(), true);
			logger.info(getData("platformName") + " - CreatedDate,CreatedUser,start,end date displayed #pass");
			Assert.assertEquals(type_text.isDisplayed(), true);
			logger.info(getData("platformName") + " - type displayed #pass");
		}
		if (getData("platformName").equalsIgnoreCase("ios")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			waitFor(4000);
			waitForElementDisplayed(desc);
			logger.info(getData("platformName") + "-Description: " + desc.getText() + "- RP description displayed #pass");
			logger.info(getData("platformName") + "-CreatedDate: " + createdDate.getText()+ "- RP created date displayed #pass");
			logger.info(getData("platformName") + "-Start Date: " + startDate.getText() + "- RP start date displayed #pass");
			logger.info(getData("platformName") + "-End Date: " + endDate.getText() + "- RP end date displayed #pass");
			logger.info(getData("platformName") + "-Status: " + status.getText() + "- RP status displayed #pass");
			logger.info(getData("platformName") + "-Visibility: " + visibility_text.getText()+ "- RP visibility displayed #pass");
			logger.info(getData("platformName") + "-Reminder: " + reminder_text.getText()+ "- RP reminder displayed #pass");
		}
		try {
		Assert.assertEquals(participant_lbl.isDisplayed(), true);
		}catch(Exception e) {
		}
		System.out.println("Participant Size:" + participantList.size());
		logger.info(getData("platformName") + participantList.size() + " - Participant list validation #pass");
		if (participantList.size()>4) {
			horizontalSwipe(participantList);
			logger.info(getData("platformName") + " - participantList carosel swipe #Pass");
		}
		else {
			for (int i = 0; i <=participantList.size()-1; i++) {
				Assert.assertTrue(participantList.get(i).isDisplayed());		
			}
		}
		try {
			Assert.assertEquals(readingList_lbl.isDisplayed(), true);
		} catch (Exception e1) {
		}
		swipeDown();
		logger.info(getData("platformName") + readingList.size() + " - Reading list validation #pass ");
		System.out.println("Reading list Size:" + readingList.size());
		if (readingList.size()>2) {
			horizontalSwipe(readingList);
			logger.info(getData("platformName") + " - Titles carosel swipe #Pass");

		}
		
		Assert.assertEquals(noThanks_bnt.isDisplayed(), true);
		logger.info(getData("platformName") + " - No thanks button displayed #pass");
		logger.info(getData("platformName") + " - Ongoing Program details page validation End #pass");
		Screenshots.takeScreenshot(driver, "./screenshots/"+ getData("platformName") +"/OpenProgram/Detailpage1.png");
	}

	public static void joinProgramDetailPageValidation() throws IOException {
		ClickOnMobileElement(joinProgram_btn);
		waitFor(3000);
		Assert.assertEquals(gotoProgram_btn.isDisplayed(), true);
		Assert.assertEquals(close_btn.isDisplayed(), true);
		//Assert.assertEquals(readingProgram_image.isDisplayed(), true);
		Assert.assertEquals(joinedUser.isDisplayed(), true);
		logger.info(getData("platformName")+ " - RP Confirmation page rp image,close,gotochallenge,user displayed --- #pass");
		Screenshots.takeScreenshot(driver, "./screenshots/"+ getData("platformName") +"/OpenProgram/confirmationpage1.png");
		logger.info(getData("platformName") + " - RP Joined program confirmation page validation End--- #pass");

		ClickOnMobileElement(gotoProgram_btn);
		waitFor(10000);
		Screenshots.takeScreenshot(driver, "./screenshots/"+ getData("platformName") +"/OpenProgram/rcdetailspage.png");
		Assert.assertTrue(rpDetails_ReadingProgram_lbl.isDisplayed());
		Assert.assertTrue(rpDetails_ProgramName_lbl.isDisplayed());
		Assert.assertTrue(rpDetails_desc.isDisplayed());
		logger.info(getData("platformName") + " - RP details page label,name,description displayed--- ##pass");
		Assert.assertTrue(rpDetails_CreatedDate_lbl.isDisplayed());
		Assert.assertTrue(rpDetails_startDate.isDisplayed());
		Assert.assertTrue(rpDetails_endDate.isDisplayed());
		Assert.assertTrue(rpDetails_Statustxt.isDisplayed());
		Assert.assertTrue(rpDetails_visibilitytxt.isDisplayed());
		logger.info(getData("platformName")+ " - RP details page creted date,start and end date,status,visibility displayed #pass");
		Assert.assertTrue(rpDetails_type_txt.isDisplayed());
		Assert.assertTrue(rpDetails_reminder_txt.isDisplayed());
		logger.info(getData("platformName") + " - RP details page type,reminder displayed #pass");
		// Assert.assertTrue(rpDetails_readingList_lbl.isDisplayed());
		waitFor(3000);
		if (readingList.size() > 2) {
			horizontalSwipe(rpDetails_readingList);
			logger.info(" --" + getData("platformName")+ " RP  participants view titles carosel swipe validation --- #pass");

		}
		else {
			Assert.assertTrue(readingList.get(0).isDisplayed());
			logger.info(" --" + getData("platformName")+ " RP  participants view titles carosel swipe validation --- #pass");
		}
		//Assert.assertTrue(rpDetails_participants_lbl.isDisplayed());
		swipeDown();
		swipeDown();
		swipeDown();
		for (int i = 0; i <=participantList.size()-1; i++) {
			Assert.assertTrue(rpDetails_participantList.get(i).isDisplayed());	
		}
		
		Screenshots.takeScreenshot(driver, "./screenshots/"+ getData("platformName") +"/OpenProgram/rcdetailspage1.png");
		logger.info(getData("platformName")+ " - RP details page participants list,reading list displayed #pass");
		logger.info(getData("platformName") + " -- Program details page validation End--");

		ClickOnMobileElement(rpDetails_Back_btn);
		waitFor(5000);

	}
	
	public static void onGoingupComingPrg() throws IOException {
		waitFor(3000);
		ClickOnMobileElement(myprogram.openProgram_tab);
		waitFor(6000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			if (openprogramList.size()!=0) {
				Assert.assertTrue(onGoingProgram_lbl.isDisplayed());
				logger.info(getData("platformName") + " - onGoing Programs are displayed #Pass");
			}

			for (int i = 0; i<=10; i++) {
				try {
					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(new UiSelector().text(\"UPCOMING PROGRAMS\"))"));
					Assert.assertTrue(findElement.isDisplayed());
					swipeDown();
					logger.info(getData("platformName") + " - Upcoming Program is displayed #Pass");	
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/Closed Program.png");
					break;
					}
				catch (Exception e) {
					if (i==10) {
						logger.info(getData("platformName") + " - No upcoming Program available to display");
					}		
				}
			}
		}
						
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			
			if (openprogramList.size() != 0) {
				Assert.assertTrue(onGoingProgram_lbl.isDisplayed());
				logger.info(getData("platformName") + " - onGoing Programs are displayed #Pass");
			}
		
		   while(upComingProgram_lbl.size()==0) {
			  
					swipeDown();	
				
          if(upComingProgram_lbl.size()!=0) {
        	  Assert.assertTrue(upComingProgram_lbl.get(0).isDisplayed());
					
        	  swipeDown();
				logger.info(getData("platformName") + " - Upcoming Program is displayed #Pass");
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/Reading_Program/upcoming program.png");
					waitFor(3000);
					break;
				}

			}

			}
	}

	public static void openProgramlistingPage() throws IOException {
		
		waitFor(10000);
		logger.info("-- " + getData("platformName") + " --- Open Program listing page validation start ---");
		Assert.assertTrue(listPage_DefaultIcon.get(0).isDisplayed());
		logger.info(getData("platformName") + " - Reading program Default icon is displayed #Pass");
		Assert.assertTrue(listPage_Readby_lbl.get(0).isDisplayed());
		logger.info(getData("platformName") + " - Reading program readby lable is displayed #Pass");
		Assert.assertTrue(listPage_title_Name.get(0).isDisplayed());
		logger.info(getData("platformName") + " - Reading program Name is displayed #Pass");
		Assert.assertTrue(listPage_title_CoverImage.get(0).isDisplayed());
		logger.info(getData("platformName") + " - Reading program title cover image displayed #Pass");
		try {
			Assert.assertTrue(listPage_title_Description.get(0).isDisplayed());
			logger.info(getData("platformName") + " - Reading program has description and it is displayed #Pass");	
		} catch (Exception e) {
			logger.info(getData("platformName") + " - Reading program has no description to displayed #Pass");
		}
		logger.info("-- " + getData("platformName") + " --- Open Program listing page validation End ---");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/Open_prg_Listing_page.png");
	}

	public static void onGoingProgramValidation() throws IOException {
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		for (int i = 0; i < 2; i++) {
			try {
				waitFor(5000);
				MobileElement findElement = (MobileElement) driver
						.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(\"ONGOING PROGRAMS\"))"));
				//ClickOnMobileElement(onGoingProgram_lbl);
				ClickOnMobileElement(openprogramList.get(0));
				waitFor(10000);
				Assert.assertTrue(startDate.isDisplayed());
				logger.info(getData("platformName") + " - User can able to navigate join program details page  #Pass");
				String displayDate = startDate.getText();
				System.out.println(displayDate);
				SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
				Date date1 = s.parse(displayDate);
				LocalDate dateObj = LocalDate.now();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String currentDate = dateObj.format(formatter);
				System.out.println(currentDate);
				Date date2 = s.parse(currentDate);
				System.out.println("currentDate:"+date2);
				if (date1.compareTo(date2) < 0 || date1.compareTo(date2) == 0) {
					logger.info(
							getData("platformName") + " - Challenge start date should be past or present date  #Pass");
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/Reading_Program/onGoingDateValidation.png");
				} else {
					Assert.assertTrue(false);
				}
				swipeUp();
				break;
			} catch (Exception e) {

			}
		}
		
		}
		
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackiOS")) {
			
			for (int i = 0; i < 2; i++) {
				try {
					waitFor(5000);	
					ClickOnMobileElement(openprogramList.get(0));
					waitFor(10000);
					Assert.assertTrue(startDate.isDisplayed());
					logger.info(getData("platformName") + " - User can able to navigate join program details page  #Pass");
					String displayDate = startDate.getText();
					System.out.println(displayDate);
					SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
					Date date1 = s.parse(displayDate);
					LocalDate dateObj = LocalDate.now();
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					String currentDate = dateObj.format(formatter);
					System.out.println(currentDate);
					Date date2 = s.parse(currentDate);
					System.out.println("currentDate:"+date2);
					if (date1.compareTo(date2) < 0 || date1.compareTo(date2) == 0) {
						logger.info(
								getData("platformName") + " - Challenge start date should be past or present date  #Pass");
						Screenshots.takeScreenshot(driver,
								"./Screenshots/" + getData("platformName") + "/Reading_Program/onGoingDateValidation.png");
					} else {
						Assert.assertTrue(false);
					}
					swipeUp();
					break;
				} catch (Exception e) {

				}
			}
			
			}			
	}

	public static void upComingProgramValidation() throws ParseException, IOException {
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			ClickOnMobileElement(openprogramList.get(1));
		}
		if (getData("platformName").equalsIgnoreCase("ios")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			ClickOnMobileElement(upComingProgramList.get(6));
		}
		try {
		waitFor(10000);
		Assert.assertTrue(startDate.isDisplayed());
		logger.info(getData("platformName") + " - User can able to navigate join program details page  #Pass");
		String displayDate = startDate.getText();
		System.out.println("RPDate:" + displayDate);
		SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = s.parse(displayDate);
		LocalDate dateObj = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	    String currentDate = dateObj.format(formatter);
	    System.out.println(currentDate);
	    Date date2 = s.parse(currentDate);
	    if (date1.compareTo(date2)>0) {
	    	logger.info(getData("platformName") + " - Challenge start date is a future date  #Pass");
	    	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/upcomingDateValidation.png");
		}
	    else {
	    	Assert.assertTrue(false);
	    }
		}catch (Exception e) {
			logger.info(getData("platformName") + " - This program is not upcoming program ");
		}
	    swipeUp();
	    waitFor(5000);
	}
	
	public static void publicProgramValidation() throws IOException {
		
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		ClickOnMobileElement(openprogramList.get(0));
		}
		if (getData("platformName").equalsIgnoreCase("ios")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			ClickOnMobileElement(openprogramList.get(5));
		}
		waitFor(5000);
		String text = visibility_text.getText();
		if (text.equalsIgnoreCase("Private")) {
			logger.info(getData("platformName") + " - User Should not see private programs in ongoing programs  #Fail");
	    	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/privateProgramValidation.png");
		}
		else {
			logger.info(getData("platformName") + " - User Should not see private programs in ongoing programs  #Pass");
	    	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/privateProgramValidation.png");
		}
		swipeDown();
	}
	
	public static void joingProgramToListingPageNavigation() throws IOException {

		ClickOnMobileElement(myprogram.openProgram_tab);
		waitFor(10000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/OpenProgram/ListingPage.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			ClickOnMobileElement(openprogramList.get(0));
		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			ClickOnMobileElement(openprogramList.get(5));
		}
		waitFor(5000);
		Assert.assertEquals(readingProgram_lbl.isDisplayed(), true);
		Assert.assertEquals(readingProgram_Icon.isDisplayed(), true);
		logger.info(getData("platformName") + " - User Navigated to the ongoing detail page #pass -- ");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		swipeUp();
		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			swipeDown1();
		}
		waitFor(5000);
		Assert.assertTrue(onGoingProgram_lbl.isDisplayed());
		logger.info(getData("platformName") + " -  when swipedown details page to listing page navigated #pass");

	}

	public static void rpConfirmationPageToListingPgaeNavigation() throws IOException {

		waitFor(5000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			ClickOnMobileElement(openprogramList.get(0));
		}
		if (getData("platformName").equalsIgnoreCase("ios")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			ClickOnMobileElement(openprogramList.get(5));
		}
		waitFor(5000);
		ClickOnMobileElement(joinProgram_btn);
		waitFor(3000);
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/Reading_Program/Programconfirmation_page.png");
		logger.info(getData("platformName") + " - RP Confirmation page Displayed #Pass");
		ClickOnMobileElement(close_btn);
		waitFor(10000);
		Assert.assertTrue(onGoingProgram_lbl.isDisplayed());
		logger.info(getData("platformName") + " - RP Confirmation page to listing page navigated #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/Reading_Program/Programlistingpage.png");
	}
	
	public static void leaveProgram() throws IOException {
		ClickOnMobileElement(joinProgram_btn);
		waitFor(5000);
		Assert.assertEquals(gotoProgram_btn.isDisplayed(), true);
		Assert.assertEquals(close_btn.isDisplayed(), true);
		//Assert.assertEquals(readingProgram_image.isDisplayed(), true);
		Assert.assertEquals(joinedUser.isDisplayed(), true);
		logger.info(getData("platformName")+ " RP Confirmation page rp image,close,gotochallenge,user displayed --- #pass");
		Screenshots.takeScreenshot(driver, "./screenshots/"+ getData("platformName") +"/OpenProgram/confirmationpage1.png");
		logger.info(getData("platformName") + " - RP Joined program confirmation page validation End--- #pass");

		ClickOnMobileElement(gotoProgram_btn);
		waitFor(10000);
		Screenshots.takeScreenshot(driver, "./screenshots/"+ getData("platformName") +"/OpenProgram/rcdetailspage.png");
		Assert.assertTrue(rpDetails_ReadingProgram_lbl.isDisplayed());
		Assert.assertTrue(rpDetails_ProgramName_lbl.isDisplayed());
		Assert.assertTrue(rpDetails_desc.isDisplayed());
		logger.info(getData("platformName") + " - RP details page label,name,description displayed--- #pass");
		Assert.assertTrue(rpDetails_CreatedDate_lbl.isDisplayed());
		Assert.assertTrue(rpDetails_startDate.isDisplayed());
		Assert.assertTrue(rpDetails_endDate.isDisplayed());
		Assert.assertTrue(rpDetails_Statustxt.isDisplayed());
		Assert.assertTrue(rpDetails_visibilitytxt.isDisplayed());
		logger.info(getData("platformName")+ " - RP details page creted date,start and end date,status,visibility displayed #pass");
		Assert.assertTrue(rpDetails_type_txt.isDisplayed());
		Assert.assertTrue(rpDetails_reminder_txt.isDisplayed());
		logger.info(getData("platformName") + " - RP details page type,reminder displayed #pass");
	//	Assert.assertTrue(rpDetails_readingList_lbl.isDisplayed());
		if (readingList.size()>2) {
			horizontalSwipe(rpDetails_readingList);
			logger.info(getData("platformName")+ " RP  participants view titles carosel swipe validation --- #pass");
		}
		else {
			Assert.assertTrue(readingList.get(0).isDisplayed());
			logger.info(getData("platformName")+ " RP  participants view titles carosel swipe validation --- #pass");
		}
		//Assert.assertTrue(rpDetails_participants_lbl.isDisplayed());
		swipeDown();
		swipeDown();
		swipeDown();
		for (int i = 0; i <=participantList.size()-1; i++) {
			Assert.assertTrue(rpDetails_participantList.get(i).isDisplayed());	
		}
		ClickOnMobileElement(rpDetails_More_icon);
		ClickOnMobileElement(rpDetails_LeavePrgButton);
		waitFor(10000);
		Assert.assertTrue(BookClubLandingScreen.lbl_BooKClub_Header.isDisplayed());
	}
	
	
	/***********************************************Open program Listing page Locators********************************************/	
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_image")
	public static List<MobileElement> listPage_DefaultIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_readby_text")
	public static List<MobileElement> listPage_Readby_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_title_text")
	public static List<MobileElement> listPage_title_Name;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/first_book")
	public static List<MobileElement> listPage_title_CoverImage;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/description_books")
	public static List<MobileElement> listPage_title_Description;
	
	/****************************************************Join program page Locators**********************************************/	

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_active_programs")
	@iOSXCUITFindBy(xpath = "//*[@name='dd_statustag']")
	public static MobileElement onGoingProgram_lbl;
	
	
	@iOSXCUITFindBy(xpath = "//*[@label='UPCOMING PROGRAMS']")
	public static List<MobileElement> upComingProgram_lbl;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> upComingProgramList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/parentLayout")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> openprogramList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_preview_image")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Reading Program\"]")
	public static MobileElement readingProgram_Icon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='READING PROGRAM']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"READING PROGRAM\"]")
	public static MobileElement readingProgram_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]")
	public static MobileElement programName;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_desc_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[3]")
	public static MobileElement desc;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/created_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]")
	public static MobileElement createdDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/profile_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]")
	public static MobileElement createdUser;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_start_date")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]")
	public static MobileElement startDate_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_end_date")		
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]")
	public static MobileElement endDate_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_status")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[3]")
	public static MobileElement status_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_start_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[4]")
	public static MobileElement startDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_end_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[5]")
	public static MobileElement endDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_status_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[6]")
	public static MobileElement status;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_visibility")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[7]")
	public static MobileElement visibility_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_type")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[8]")
	public static MobileElement type_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_reminders")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[9]")
	public static MobileElement reminder_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_visibility_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[10]")
	public static MobileElement visibility_text;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_type_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[11]")
	public static MobileElement type_text;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_reminders_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[12]")
	public static MobileElement reminder_text;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/participants_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeOther[1]")
	public static MobileElement participant_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/participant_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeCell")
	public static List<MobileElement> participantList;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/reading_list_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Reading List\"]")
	public static MobileElement readingList_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/material_cover_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> readingList;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/joinProgram_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Join Program\"]")
	public static MobileElement joinProgram_btn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/no_thanks_text")
	@iOSXCUITFindBy(xpath = "//*[@name='No, thanks']")
	public static MobileElement noThanks_bnt;

	/*****************************************************Confirmation page Locators*********************************************/	

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/accepted_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Challenge Accepted\"]")
	public static MobileElement programJoined_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/go_to_challenge_text")
	@iOSXCUITFindBy(xpath = "//*[@name='Go to Program']")
	public static MobileElement gotoProgram_btn;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/close_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Close\"]")
	public static MobileElement close_btn;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/challenge_accepted_image")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"illustrationsReadingProgram\"]")
	public static MobileElement readingProgram_image;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/user_info_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[2]")
	public static MobileElement joinedUser;
	
	/*****************************************************Details page Locators*************************************************/	

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_back")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]")
	public static MobileElement rpDetails_Back_btn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_more")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[2]")
	public static MobileElement rpDetails_More_icon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/leave_challenge_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Leave Program\"]")
	
	public static MobileElement rpDetails_LeavePrgButton;
	
	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/cancel_text")	
	public static MobileElement rpDetails_CancelButton;
	
	@AndroidFindBy(xpath ="//*[@text='READING PROGRAM']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")
	public static MobileElement rpDetails_ReadingProgram_lbl;

	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/rp_title_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]")
	public static MobileElement rpDetails_ProgramName_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_created_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]")
	public static MobileElement rpDetails_CreatedDate_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_profile_name_text")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[4]")
	public static MobileElement rpDetails_joinedUser_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_start_date")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[5]")
	public static MobileElement rpDetails_startDate_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_start_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[6]")
	public static MobileElement rpDetails_startDate;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_end_date")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[7]")
	public static MobileElement rpDetails_enddate_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_end_date_text")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[8]")
	public static MobileElement rpDetails_endDate;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_status")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[9]")
	public static MobileElement rpDetails_status_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_status_text")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[10]")
	public static MobileElement rpDetails_Statustxt;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_visibility")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[11]")
	public static MobileElement rpDetails_visibility_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_visibility_text")	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[12]")
	public static MobileElement rpDetails_visibilitytxt;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_type")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[13]")
	public static MobileElement rpDetails_type_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_type_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[14]")
	public static MobileElement rpDetails_type_txt;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_reminders")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[15]")
	public static MobileElement rpDetails_reminder_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_reminders_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[16]")
	public static MobileElement rpDetails_reminder_txt;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_description_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[17]")
	public static MobileElement rpDetails_desc;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_readinglist_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther")
	public static MobileElement rpDetails_readingList_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/material_cover_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeCell")
	public static List<MobileElement> rpDetails_readingList;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/rp_participants_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeOther[2]")
	public static MobileElement rpDetails_participants_lbl;

	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/participant_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[3]")
	public static List<MobileElement> rpDetails_participantList;

}
