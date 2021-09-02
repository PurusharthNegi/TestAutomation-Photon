package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class BookClubLandingScreen extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(Login.class);
	
	public BookClubLandingScreen() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void bookClubAssertion() throws InvalidFormatException, IOException {
		
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Book Club");
		waitFor(15000);
		
		logger.info(getData("platformName")+" - Waiting to enter Book club ");
		logger.info(" --"+ getData("platformName")+" - Book Club Assertion Start -- ");
		WaitForMobileElement(bookClubOption);
		waitFor(15000);
		logger.info(getData("platformName")+" - User is in Home Page #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/HomePage/HomePage.png");
		ClickOnMobileElement(bookClubOption);
		waitFor(5000);
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/BookClub/BookClubHomePage.png");
		logger.info(getData("platformName")+" - Navigation to Book club Landing Page #Pass");
		WaitForMobileElement(challenges);
		logger.info(getData("platformName")+" - Challenges Tab displayed #Pass");
		WaitForMobileElement(myPrograms);
		logger.info(getData("platformName")+" - MyPrograms Tab displayed #Pass");
		WaitForMobileElement(openPrograms);
		logger.info(getData("platformName")+" - Open programs Tab displayed #Pass");
		isSelected(challenges);
		logger.info(getData("platformName")+" - Challenges Tab is selected by default #Pass");
		WaitForMobileElement(searchIcon);
		logger.info(getData("platformName")+" - GLobal search icon is displayed #Pass");
		WaitForMobileElement(messageCenterIcon);
		logger.info(getData("platformName")+" - Message Center icon is displayed #Pass");
		WaitForMobileElement(addCTA);
		logger.info(getData("platformName")+" - Create challenge CTA is displayed #Pass");
    	logger.info(" --"+ getData("platformName")+" - Book Club Assertion End -- ");
	}
	
	public static void challenges() {
		ClickOnMobileElement(bookClubOption);
		waitFor(4000);
		Assert.assertEquals(challengesList.get(1).isDisplayed(), true);
		String spentHours = hoursSpenttext.get(0).getText();
		boolean valid = false;
		SimpleDateFormat s = new SimpleDateFormat("hh:mm a");
		try {
			Date hours = s.parse(spentHours);
			String formatValid = s.format(hours);
			System.out.println("valid format:" + formatValid);
			valid = true;

		} catch (ParseException e) {
			valid = false;
		}
		Assert.assertEquals(valid, true);
	}
	
	public static void hrsSpentValidation() throws IOException {
		
		try {
			for (int i = 0; i<=10; i++) {
				if (hoursSpenttext.size()!=0) {
					String spentHours = hoursSpenttext.get(0).getText();
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
						Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/Hrs_Spent.png");
					} catch (ParseException e) {
						logger.info(getData("platformName") + " - Hours Spent time format is HH:MM #Fail");	
						Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/Hrs_Spent.png");
					}
					break;
				}	
				else {
					logger.info(getData("platformName") + " - Hours Spent time content not found swiping down to check");	
					swipeDown();
				}
			}	
			
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public static void challengeDisplay() {
		waitFor(10000); 
		try {
			 if (challengeList.size()!=0) {
				 logger.info(getData("platformName") + " - User can able to view the challenge in listing page #Pass");
				 Assert.assertTrue(readChallengeDefaultIcon.isDisplayed());
				 logger.info(getData("platformName") + " - User can able to view the RC default icon #Pass");
				 Assert.assertTrue(readByText.isDisplayed());
				 logger.info(getData("platformName") + " - User can able to view the RC readby date #Pass");
				 Assert.assertTrue(challengeName.isDisplayed());	 
				 logger.info(getData("platformName") + " - User can able to view the challenge Name #Pass");
				 Assert.assertTrue(bookImage.isDisplayed());
				 logger.info(getData("platformName") + " - User can able to view the title cover image #Pass");
				 for (int i = 0; i<=5; i++) {
						if (challengeDescription.size()!=0) {
						 logger.info(getData("platformName") + " - User can able to view the challenge description #Pass");
						 break;
					}
					else {
						swipeDown();
						logger.info(getData("platformName") + " - User cant able to view the challenge description for first title, Swiping down");
				    }
				 }
				 Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/challengeDisplay.png");
			}
			 else {
				 logger.info(getData("platformName") + " - No challenges available to display");	
				 Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/challengeDisplay.png");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void progressPercentageValidation() throws IOException {
		
		for (int i = 0; i<=10; i++) {
			if (progressPercentage.size()!=0) {
				logger.info(getData("platformName") + " - Challenge Progress pecentage is displayed #Pass");	
				 Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/progressPercent.png");
				 break;
			}
			else {
				swipeDown();
			}
		}
	}
	
	public static void progressPercentBarValidation() throws IOException {
		
		for (int i = 0; i<=10; i++) {
			try {
			if (progressPercentage.size()!=0) {
				String percent = progressPercentage.get(0).getText();
				int a = Integer.parseInt(percent);
				if (a>0) {
					Assert.assertTrue(progressbar.get(0).isDisplayed());
					logger.info(getData("platformName") + " - Challenge Progress bar is displayed #Pass");	
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/progressBar.png");
					break;
				}
			}
				else {
				swipeDown();
				}
			}catch (Exception e) {
			e.printStackTrace();
			}
		}	
	}
	
	public static void challengeInviteValidation() throws IOException {
//		try {
//			for (int i = 0; i <=5; i++) {
//				waitFor(10000);
//				try {
//					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
//							+ ".scrollIntoView(new UiSelector().text(\"You have been invited!\"))"));
//					Assert.assertTrue(invitedImage.isDisplayed());
//					logger.info(getData("platformName") + " - user can able to see invite icon and text for challnges which are not accepted #Pass");	
//					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/RC-invite.png");
//					break;
//				} catch (Exception e) {
//					logger.info(getData("platformName") + " - New invite not found , Page refresh done");					
//				}
//			}	
//		} catch (Exception e) {
//			logger.info(getData("platformName") + " - User dont have new invite");
//		}
		
		while (invitedImage.size()==0) {
			swipeDown();
			waitFor(3000);
			if (invitedImage.size()!=0 && lbl_DraftChallenges.size()==0) {
				Assert.assertTrue(hoursSpenttext.get(0).isDisplayed());
				logger.info(getData("platformName") + " - user can able to see invite icon and text for challnges which are not accepted #Pass");	
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/RC-invite.png");
				break;
			}
			else if (invitedImage.size()!=0 && lbl_ClosedChallenges.size()==0) {
				Assert.assertTrue(hoursSpenttext.get(0).isDisplayed());
				logger.info(getData("platformName") + " - user can able to see invite icon and text for challnges which are not accepted #Pass");	
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/RC-invite.png");
				break;
			}
			else if (lbl_DraftChallenges.size()!=0 || lbl_ClosedChallenges.size()!=0) {
				logger.info(getData("platformName") + " - User dont have new invite");
				break;
			}
		}
	}
	
	public static void DraftClosedChallengeValidation() throws IOException {
		
		while (lbl_DraftChallenges.size()==0 && lbl_ClosedChallenges.size()==0) {
			swipeDown();
			waitFor(2000);
		}
		if (lbl_ClosedChallenges.size()!=0 || lbl_DraftChallenges.size()!=0) {
			swipeDown();
			swipeDown();
			try {
				Assert.assertTrue(progressPercentage.get(0).isDisplayed());
				Assert.assertTrue(hoursSpenttext.get(0).isDisplayed());
				
			} catch (Exception e) {
				logger.info(getData("platformName") + " - User not able to see progress percentage in draft challenges #Pass");	
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/View challenge/DraftClosedChallenge.png");
			}
		}
	}
	
/***********************************************************Mobile Locator**********************************************************/
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> challengesList;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='BOOK CLUB']")
	@iOSXCUITFindBy(xpath = "//*[@name='Book Club tab']")
	public static MobileElement bookClubOption;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Search']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Search']")
	public static MobileElement searchIcon;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Message center']")
	@iOSXCUITFindBy(xpath="//*[@name='Message center icon']")
	public static MobileElement messageCenterIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_title")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Book Club\"]")
	public static MobileElement breadCrumBookClub;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Challenges\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Challenges\"]")
	public static MobileElement challenges;

	@AndroidFindBy(xpath = "//*[@text='My Programs']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='My Programs']")
	public static MobileElement myPrograms;

	@AndroidFindBy(xpath = "//*[@text='Open Programs']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Open Programs']")
	public static MobileElement openPrograms;
	
	@AndroidFindBy(xpath = "//*[@text='Book Club']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Book Club\"]")
	public static MobileElement lbl_BooKClub_Header;

	@AndroidFindBy(xpath = "(//*[@resource-id ='com.follett.fss.searchread.stage:id/iv_challenge_icon'])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name=\"illustrationsReadingChallenge\"])[1]")
	public static MobileElement readChallengeDefaultIcon;

	@AndroidFindBy(xpath = "(//*[@resource-id ='com.follett.fss.searchread.stage:id/tv_header_title'])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Reading challenge: Read by 01/16/2021\"]")
	public static MobileElement readByText;

	@AndroidFindBy(xpath = "(//*[@resource-id ='com.follett.fss.searchread.stage:id/tv_header_desc'])[1]")
	public static MobileElement challengeName;

	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/first_book'])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeImage[2]")
	public static MobileElement bookImage;

	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/iv_challege'])[1]")
	public static MobileElement challengeImageone;

	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/description_books'])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"The September 11 attacks, often referred to as 9/11, were a series of four coordinated terrorist attacks by the Wahhabi terrorist group Al-Qaeda against the United States on the morning of Tuesday, September\"]")
	public static List<MobileElement> challengeDescription;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/progress_circular")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther")
	public static List<MobileElement> progressbar;

	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tv_percentagetext")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"50%\"]")
	public static List<MobileElement> progressPercentage;

	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/tv_hoursspent'])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"07:00 hrs spent\"]")
	public static List<MobileElement> hoursSpenttext;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_header_desc")
	public static  List<MobileElement> challengeNameList;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/parentLayout")
	public static  List<MobileElement> challengeList;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/iv_challenge")
	public static List<MobileElement> invitedImage;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_logo_image")
	public static MobileElement logo;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_filter_image")
	public static MobileElement filter;
	
	@AndroidFindBy(xpath = "//*[@text='ACTIVE CHALLENGES']")
	public static MobileElement lbl_ActiveChallenges;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_staus_draft")
	public static List<MobileElement> lbl_DraftChallenges;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_staus_closed")
	public static List<MobileElement> lbl_ClosedChallenges;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_main_menu")
	public static MobileElement navButton;
	
	@iOSXCUITFindBy(xpath = "//*[@name=\"iconsCoreMore\"]")
	public static MobileElement moreBtn;

	@iOSXCUITFindBy(id = "HOME")
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/menu_engage_home")
	public static MobileElement homeBtn;
	
	@iOSXCUITFindBy(xpath = "//*[@name=\"Log out\"]")
	public static MobileElement logout;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Add Create new challenge']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"addChallengeCTA\"]")
	public static MobileElement addCTA;

	@iOSXCUITFindBy(id = "//XCUIElementTypeNavigationBar[@name=\"Book Club\"]/XCUIElementTypeButton[2]")
	public static MobileElement iosnotification;
	
}
