package com.pom;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.ClickAndHoldAction;
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

public class MyPrograms extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(Login.class);

	static BookClubLandingScreen bookClubLandingScreeen = new BookClubLandingScreen();
	
	public MyPrograms() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

	public static void headerValidation() throws IOException {
     waitFor(4000);
		ClickOnMobileElement(bookClubLandingScreeen.bookClubOption);
		logger.info(getData("platformName") + " - Entering book club landing page #Pass");
		waitFor(2000);
		Assert.assertTrue(bookClubLandingScreeen.challenges.isDisplayed());
		Assert.assertTrue(myProgram_tab.isDisplayed());
		logger.info(getData("platformName") + " - My program tab is displayed #Pass");
		Assert.assertTrue(openProgram_tab.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Open program tab is displayed #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/BookClub_Landing_page.png");
	}

	public static void validatingActiveDraftClosedPrograms() throws IOException {
		ClickOnMobileElement(myProgram_tab);
		logger.info(getData("platformName") + " - Switching to my Program landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/MyPrg_Landing_page.png");
		waitFor(3000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			if (myprogramslist.size()!=0) {
				Assert.assertTrue(activeprogram_lbl.isDisplayed());
				logger.info(getData("platformName") + " - Active Programs are displayed #Pass");
			}
			for (int i = 0; i<=5; i++) {
				try {
					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
							+ ".scrollIntoView(new UiSelector().text(\"CLOSED PROGRAMS\"))"));
					ClickOnMobileElement(findElement);
					Assert.assertTrue(findElement.isDisplayed());
					logger.info(getData("platformName") + " - Closed Program is displayed #Pass");	
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/Closed Program.png");
					break;
					}
				catch (Exception e) {
					if (i==5) {
						logger.info(getData("platformName") + " - No Closed Program available to display");		
					}	
			     }
			}
	   	}
			if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			
			if (myprogramslist.size()!=0) {
				Assert.assertTrue(activeprogram_lbl.isDisplayed());
				logger.info(getData("platformName") + " - Active Programs are displayed #Pass");
			}
			while(closedProgram_lbl.size() == 0) {
				swipeDown();
			}  
			if (closedProgram_lbl.size()> 0) {
				Assert.assertTrue(closedProgram_lbl.get(0).isDisplayed());
				logger.info(" --" + getData("platformName") + " - Closed Program validation pass -- ");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgramPage.png");
			}
		  }
	}

	public static void myProgramsDetailsScreenValidation() throws IOException {
		ClickOnMobileElement(myProgram_tab);
		waitFor(7000);
		ClickOnMobileElement(myprogramslist.get(0));
		waitFor(15000);
		Assert.assertTrue(createdDate.isDisplayed());
		logger.info(getData("platformName") + " - Program created Date is Displayed #Pass");
		Assert.assertTrue(createdUser.isDisplayed());
		logger.info(getData("platformName") + " - Program creator name is Displayed #Pass");
		
        waitFor(4000);
		Assert.assertTrue(readingProgram_lbl.isDisplayed());
		Assert.assertTrue(readingProgramName.isDisplayed());
		logger.info(getData("platformName") + " - Program Name is Displayed #Pass");
		Assert.assertTrue(desc.isDisplayed());
		logger.info(getData("platformName") + " - Program description is Displayed #Pass");
		Assert.assertTrue(startDate.isDisplayed());
		logger.info(getData("platformName") + " - Program Start Date is Displayed #Pass");
		Assert.assertTrue(endDate.isDisplayed());
		logger.info(getData("platformName") + " - Program End Date is Displayed #Pass");
		Assert.assertTrue(status_lbl.isDisplayed());
		Assert.assertTrue(status_text.isDisplayed());
		logger.info(getData("platformName") + " - Program Status is Displayed #Pass");
		Assert.assertTrue(visibility_text.isDisplayed());
		logger.info(getData("platformName") + " - Program Visibility is Displayed #Pass");
		waitFor(3000);
		Assert.assertTrue(type_text.isDisplayed());
		logger.info(getData("platformName") + " - Program Type is Displayed #Pass");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		Assert.assertTrue(readingList_Lbl.isDisplayed());
		}
		
		Assert.assertTrue(titleList.get(0).isDisplayed());
		logger.info(getData("platformName") + " - Program titles are Displayed #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ProgramDetail_page.png");
		if (titleList.size()>2) {
			horizontalSwipe(titleList);
		}
		else {
			for (int i = 0; i<=titleList.size()-1; i++) {
				Assert.assertTrue(titleList.get(i).isDisplayed());
			}
		}
		swipeDown();
		swipeDown();
		swipeDown();
		waitFor(5000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		Assert.assertTrue(participants_lbl.isDisplayed());
		}
		if(participantsList.size()!=0) {
			logger.info("Participants list displayed #Pass");
			for (int i = 0; i <=participantsList.size()-1; i++) {
				if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				
				
				Assert.assertTrue(participantsAvatarList.get(i).isDisplayed());
				logger.info(getData("platformName") + " - Program Participant :"+participantsList.get(i).getText());
			}}
			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/Participant_List.png");
			try {
				swipeDown();
				if (participantsList.size()>=10) {
//					Assert.assertTrue(loadMoreCTA.isDisplayed());
//					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/loadCTA.png");
//					ClickOnMobileElement(loadMoreCTA);
					swipeDown();
					swipeDown();
					waitFor(5000);
					for (int i = 0; i <=participantsList.size()-1; i++) {
						logger.info(getData("platformName") + " - Program Participant :"+participantsList.get(i).getText());
					}
				}
				else {
					logger.info(getData("platformName") + " - Participant size is less than 10 #Pass");			
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/Participant_List_LoadMore.png");
				}
			} 
			catch (Exception e) {
			}
		}
		else {
			logger.info(getData("platformName") + " - No Participants available to display");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/Participant_List.png");
			Assert.assertTrue(false);
		}
		logger.info(getData("platformName") + " - MyProgram Details Screen Validation #pass");
		ClickOnMobileElement(backBtn);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/Landing_page.png");
		waitFor(5000);
	}
	
	public static void myProgramListingPageValidation() throws IOException {
		
			waitFor(5000);
			ClickOnMobileElement(myProgram_tab);
			waitFor(10000);
			Assert.assertTrue(ListingPage_readingProgramIcon.get(0).isDisplayed());
			logger.info(getData("platformName") + " - Reading program icon is displyed #Pass");
			Assert.assertTrue(ListingPage_readingProgramName.get(0).isDisplayed());
			logger.info(getData("platformName") + " - Reading program name is displyed #Pass");
			Assert.assertTrue(ListingPage_readingProgramDescription.get(0).isDisplayed());
			logger.info(getData("platformName") + " - Reading Description is displyed #Pass");
			Assert.assertTrue(ListingPage_titleCoverImage.get(0).isDisplayed());
			logger.info(getData("platformName") + " - Title cover image is displyed #Pass");
			Assert.assertTrue(ListingPage_readBy.get(0).isDisplayed());
			logger.info(getData("platformName") + " - ReadBY lable is displyed #Pass");
			Assert.assertTrue(ListingPage_hoursSpent.get(0).isDisplayed());
			logger.info(getData("platformName") + " - Hours Spent lable is displyed #Pass");
			String spentHours = ListingPage_hoursSpent.get(0).getText();
			String[] split = spentHours.split(" ");
			boolean valid = false;
			 SimpleDateFormat s = new SimpleDateFormat("HH:MM");
			 try {
			 Date hours = s.parse(split[0]);
			 String formatValid = s.format(hours);
			 System.out.println("valid format:" + formatValid);
			 valid = true;
			 Assert.assertTrue(valid);
			 logger.info(getData("platformName") + " - Hours Spent time format is HH:MM #Pass");
			 } catch (ParseException e) {
			 logger.info(getData("platformName") + " - Hours Spent time format is HH:MM #Fail");
			 }			
	}

public static void ClosedProgramDetailsPageValidation() throws IOException{
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			ClickOnMobileElement(myProgram_tab);
			
			while(closedProgram_lbl.size() == 0) {
				swipeDown();
			}  
			
			
			
			if (closedProgram_lbl.size()!= 0) {
				Assert.assertTrue(closedProgram_lbl.get(0).isDisplayed());
				logger.info(" --" + getData("platformName") + " - Closed Program list validation pass## -- ");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgramPage.png");
				ClickOnMobileElement(myprogramslist.get(1));
				waitFor(9000);
				logger.info(" --" + getData("platformName") + " - User navigate to the closed program details page -- ");
				Assert.assertTrue(createdDate.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program created Date is Displayed #Pass");
				Assert.assertTrue(createdUser.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program creator name is Displayed #Pass");
				Assert.assertTrue(readingProgram_lbl.isDisplayed());
				Assert.assertTrue(readingProgramName.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program Name is Displayed #Pass");
				Assert.assertTrue(desc.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program description is Displayed #Pass");
				Assert.assertTrue(startDate.isDisplayed());
				logger.info(getData("platformName") + " - CLosed Program Start Date is Displayed #Pass");
				Assert.assertTrue(endDate.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program End Date is Displayed #Pass");
				Assert.assertTrue(status_lbl.isDisplayed());
				Assert.assertTrue(status_text.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program Status is Displayed #Pass");
				Assert.assertTrue(visibility_text.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program Visibility is Displayed #Pass");
				waitFor(3000);
				Assert.assertTrue(type_text.isDisplayed());
				logger.info(getData("platformName") + " - Closed Program Type is Displayed #Pass");
				waitFor(4000);
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgramDetail_page.png");
				if (titleList.size()>2) {
					horizontalSwipe(titleList);
					logger.info(getData("platformName") + " - Closed  Program carosel swipe #Pass");

				}
				else {
					Assert.assertTrue(titleList.get(0).isDisplayed());
					logger.info(getData("platformName") + " - Closed  Program titles are Displayed #Pass");

				}
				waitFor(5000);

				swipeDown();
				swipeDown();
				swipeDown();
				waitFor(5000);
				if(participantsList.size()!=0) {
					logger.info("Closed Program Participants list displayed #Pass");
					for (int i = 0; i <=participantsList.size()-1; i++) {
						
						logger.info(getData("platformName") + " -Closed Program Program Participant :"+participantsList.get(i).getText());
					}
					
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgramParticipant_List.png");
					try {
						swipeDown();
						if (participantsList.size()==10) {
							//Assert.assertTrue(loadMoreCTA.isDisplayed());
							//Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgramloadCTA.png");
							//ClickOnMobileElement(loadMoreCTA);
							swipeDown();
							swipeDown();

							waitFor(5000);
							for (int i = 0; i <=participantsList.size()-1; i++) {
								logger.info(getData("platformName") + " - Closed Program Participant :"+participantsList.get(i).getText());
							}
						}
						else {
							logger.info(getData("platformName") + " -Closed Program Participant size is less than 10  #Pass");			
							Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgramParticipant_List_LoadMore.png");
						}
					} 
					catch (Exception e) {
					}
				}
				else {
					logger.info(getData("platformName") + " - Closed Program No Participants available to display");
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgramParticipant_List.png");
					//Assert.assertTrue(false);
				}
				logger.info(getData("platformName") + " -Closed Program Details Screen Validation #pass");
				ClickOnMobileElement(backBtn);
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Reading_Program/ClosedProgram.png");
				waitFor(5000);
				
			}
		  }

		
}
	
 /******************************************************My program Locators*******************************************************/	
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_back")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]")
	public static MobileElement backBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_active_programs")
	@iOSXCUITFindBy(xpath = "//*[@label='ACTIVE PROGRAMS']")
	public static MobileElement activeprogram_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_closed_programs")
	@iOSXCUITFindBy(xpath = "//*[@label='CLOSED PROGRAMS']")
	public static List<MobileElement> closedProgram_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/parentLayout")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> myprogramslist;

	@AndroidFindBy(xpath = "//*[@text='My Programs']")
	@iOSXCUITFindBy(xpath = "//*[@name='My Programs']")
	public static MobileElement myProgram_tab;

	@AndroidFindBy(xpath = "//*[@text='Open Programs']")
	@iOSXCUITFindBy(xpath = "//*[@name='Open Programs']")
	public static MobileElement openProgram_tab;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_more")
	@iOSXCUITFindBy(xpath = "//*[@name='Test Program Preview __3 More menu']")
	public static MobileElement moreBtn_Myprogram;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_created_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[3]")
	public static MobileElement createdDate;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_profile_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[4]")
	public static MobileElement createdUser;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_challenge_readby_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[1]")	
	public static MobileElement readingProgram_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_title_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[2]")
	public static MobileElement readingProgramName;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_description_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[4]")
	public static MobileElement desc;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_start_date")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[5]")
	public static MobileElement sartDate_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_start_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[6]")
	public static MobileElement startDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_end_date")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[7]")
	public static MobileElement endDate_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_end_date_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[8]")
	public static MobileElement endDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_status")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[9]")
	public static MobileElement status_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_status_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[10]")
	public static MobileElement status_text;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_visibility")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[11]")
	public static MobileElement visibility_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_visibility_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[12]")
	public static MobileElement visibility_text;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_type")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[13]")
	public static MobileElement type_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_type_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[14]")
	public static MobileElement type_text;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_reminders")
	public static MobileElement reminder_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_reminders_text")
	public static MobileElement reminder_text;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_readinglist_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[2]/XCUIElementTypeStaticText")
	public static MobileElement readingList_Lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/material_cover_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static List<MobileElement> titleList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_participants_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
	public static MobileElement participants_lbl;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/participant_name_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[3]/XCUIElementTypeOther")
	public static List<MobileElement> participantsList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/participant_image")
	public static List<MobileElement> participantsAvatarList;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/rp_load_more_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[3]/XCUIElementTypeOther")
	public static MobileElement loadMoreCTA;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_image")
	public static List<MobileElement> ListingPage_readingProgramIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_title_text")
	public static List<MobileElement> ListingPage_readingProgramName;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/first_book")
	public static List<MobileElement> ListingPage_titleCoverImage;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/program_readby_text")
	public static List<MobileElement> ListingPage_readBy;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/description_books")
	public static List<MobileElement> ListingPage_readingProgramDescription;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_hoursspent")
	public static List<MobileElement> ListingPage_hoursSpent;
	
}
