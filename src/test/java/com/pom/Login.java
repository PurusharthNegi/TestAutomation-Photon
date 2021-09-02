package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Login extends CapabilitiesAndWebDriverUtils {
	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(Login.class);
	public Login() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); 
	}

	public void login(String UserName, String Password)
			throws IOException, InterruptedException, InvalidFormatException {
		//List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Login");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			logger.info(" --"+ getData("platformName")+" - Login Page Validation Start -- ");
			loginAssertion();
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/Login/Login1.png");
			SendKeysOnMobileElement(getUserName(), UserName); 
			logger.info(getData("platformName")+" - Entered Username #Pass");
			SendKeysOnMobileElement(getPassword(), Password);
			logger.info(getData("platformName")+" - Entered Password #Pass");
			ClickOnMobileElement(getSubmit());
			Screenshots.takeScreenshot(driver,"./screenshots/Login/LoginPage2.png");
			logger.info(" -- Successfully Logged in as: " + UserName+" -- ");
			//setPreference();
			logger.info(" --"+ getData("platformName")+" - Login Page Validation End -- ");


		} else if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			logger.info(" --"+ getData("platformName")+" - Login Page Validation Start -- ");
			loginAssertion();
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/Login/Login1.png");
			SendKeysOnMobileElement(userName, UserName);
			logger.info(getData("platformName")+" - Entered Username #Pass");
			SendKeysOnMobileElement(password, Password);
			logger.info(getData("platformName")+" - Entered Password #Pass");
			hideMobileKeyboard();
			ClickOnMobileElement(submit);
			try {
				ClickOnMobileElement(btn_Allow);

			}
			catch(Exception e) {
				System.out.println("Notification Not displayed");
				
			}
			logger.info(getData("platformName")+" - Successfully Logged in as: " + UserName+" #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/Login/Login2.png");
			logger.info(" --"+ getData("platformName")+" - Login Page Validation End -- ");

		}
	}

	public void selectSchool(String Location, String SchoolName) throws IOException, InvalidFormatException {

		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "SelectSchool");

		if (getData("platformName").equalsIgnoreCase("android")|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			try {
				waitFor(3000);
				Assert.assertTrue(footerMoreIcon.isDisplayed()); 
				logOut();
				ClickOnMobileElement(selectSchool_Xicon);
				
			} catch (Exception e) {
				logger.info(" --"+ getData("platformName")+" - User Not in Home Page -- ");
			}
			
			logger.info(" --"+ getData("platformName")+" - Environment and School Selection Start -- ");
			selectSchoolAssertion();
			SendKeysOnMobileElement(getKeyWordEnter(), testData.get(0).get("environnment_Selection"));
			logger.info(getData("platformName")+"- DLX Environment is selected #Pass");	
			SendKeysOnMobileElement(getKeyWordEnter(),testData.get(1).get("inp_SchoolName_Android"));
			hideMobileKeyboard();
			waitFor(10000);
			MobileElement ss = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc='"+testData.get(1).get("inp_SchoolName_Android")+"']"));
			logger.info(getData("platformName")+"- Selected school name is : "+ ss.getText() +" #Pass");
			ClickOnMobileElement(ss);
			waitFor(3000);
			ClickOnMobileElement(btn_SearchSchoolCont);
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/EnvSelection/SchoolSelection2.png");
			ClickOnMobileElement(Continue);
			logger.info(" --"+ getData("platformName")+" - Environment and School Selection End -- ");
		}

		else if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			logger.info(" --"+ getData("platformName")+" - Environment and School Selection Start -- ");
			
			//ClickOnMobileElement(menu_StartNormally);
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/EnvSelection/SchoolSelection.png");
			
			/*
			 * if (IsDisplayedMobileElement(btn_Allow)) {
			 * logger.info("Allow button is displayed ");
			 * Assert.assertEquals(lbl_PopUpNitification.getText(),
			 * testData.get(0).get("lbl_PopUpNitifications"));
			 * ClickOnMobileElement(btn_Allow); }
			 */
		
			ClickOnMobileElement(menu);
			ClickOnMobileElement(testingTools);
			ClickOnMobileElement(env_DLX);
			logger.info(getData("platformName")+"- DLZ Environment is selected #Pass");	
			ClickOnMobileElement(iosDone);
			selectSchoolAssertion();
			ClickOnMobileElement(defVal_SearchSchool_txt);
			SendKeysOnMobileElement(defVal_SearchSchool, SchoolName);
			 hideMobileKeyboard();
			waitFor(5000);
			MobileElement ss = (MobileElement) driver
					.findElement(By.xpath("//*[@label='" + testData.get(1).get("inp_SchoolName") + "'][1]"));
			logger.info(getData("platformName")+"- Selected school name is : "+ ss.getText() +" #Pass");
			ClickOnMobileElement(ss);
			waitFor(3000);
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/EnvSelection/SchoolSelection2.png");
			//if (IsDisplayedMobileElement(ss)) {
			//	ClickOnMobileElement(ss);
			//}
			ClickOnMobileElement(IOSContinue);
			ClickOnMobileElement(btn_SearchSchoolCont);
			logger.info(" --"+ getData("platformName")+" - Environment and School Selection End -- ");
		}
	}


	public void setPreference() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Set Preference");
		waitFor(5000);;
		logger.info("Set Preference Window");
		if (IsDisplayedMobileElement(setPref_Header_lbl)) {
			Assert.assertTrue(setPref_Header_Description.isDisplayed());
			System.out.println(setPref_NoThanksBtn.getText());
			Assert.assertEquals(setPref_NoThanksBtn.getText(), testData.get(0).get("btn_DontSavePref"));
			logger.info(" -- Set Preference Window is present -- ");
			logger.info("Set Preference - No, thanks button is present and enabled #Pass");
			Assert.assertEquals(setPref_SaveBtn.getText(), testData.get(0).get("btn_SavePref"));
			logger.info("Set Preference - Save button is present and enabled #Pass");
			Assert.assertTrue(setPref_CloseIcon.isEnabled());
			logger.info("Set Preference - Close icon  is present and enabled #Pass");
			ClickOnMobileElement(setPref_CloseIcon);
			logger.info(" -- Set preference Window is Closed -- ");
		}
	}

	public void selectSchoolAssertion() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "SelectSchool");
		logger.info(" --"+ getData("platformName")+" - Select School Assertion Start -- ");
		WaitForMobileElement(lbl_DDTitle);
		Assert.assertEquals(lbl_DDTitle.getText(), testData.get(0).get("lbl_DDTitle"));
		
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
//			Assert.assertEquals(title_SchoolName.getText(), testData.get(0).get("lbl_FindSchoolTitle_android"));
		}
		else if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
//			Assert.assertEquals(title_SchoolName.getText(), testData.get(0).get("lbl_FindSchoolTitle"));
		}
		Assert.assertEquals(lbl_SearchSchool.getText(), testData.get(0).get("defVal_SearchSchool"));
		logger.info(getData("platformName")+" - Search school text box header is displayed #Pass");
		Assert.assertEquals(lbl_Location.getText(), testData.get(0).get("lbl_Location"));
		logger.info(getData("platformName")+" - Location text box header is displayed #Pass");
		Assert.assertEquals(defVal_Location.getText(), testData.get(0).get("defVal_Location"));
		logger.info(getData("platformName")+" - Environment Selection Assertion Successfully completed #Pass");
		logger.info(" --"+ getData("platformName")+" - Select School Assertion End -- ");
	}

	public void loginAssertion() throws InvalidFormatException, IOException {
		waitFor(3000);
		logger.info(" --"+ getData("platformName")+" - Login Assertion Start -- ");
		waitFor(20000);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Login");
		Assert.assertEquals(lbl_LoginUsingFolletTitle.getText(), testData.get(0).get("lbl_LoginUsingFolletTitle"));
		logger.info(getData("platformName")+" - Follett title is displayed #Pass ");
		Assert.assertEquals(lbl_UserName.getText(), testData.get(0).get("lbl_UserName"));
		logger.info(getData("platformName")+" - Username label is displayed #Pass");
		Assert.assertEquals(lbl_Password.getText(), testData.get(0).get("lbl_Password"));
		logger.info(getData("platformName")+" - Password label is displayed #Pass");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(lbl_CopyRights.getText(), testData.get(0).get("lbl_CopyRights_android"));
			logger.info(getData("platformName")+" - Copy rights is displayed #Pass");
		}
		else if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			Assert.assertEquals(lbl_CopyRights.getText(), testData.get(0).get("lbl_CopyRights"));
			logger.info(getData("platformName")+" - Copy rights is displayed #Pass");
		}
		Assert.assertEquals(lbl_LoginNotes.getText(), testData.get(0).get("lbl_LoginNotes"));
		logger.info(getData("platformName")+" - Note is displayed #Pass");
		logger.info(" --"+ getData("platformName")+" - Login Assertion End -- ");
	}
	
	public void logOut() throws InvalidFormatException, IOException {
	
		ClickOnMobileElement(footerMoreIcon);
		waitFor(2000);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Login");
		Assert.assertEquals(logOutButton.getText(), testData.get(0).get("lbl_Logout"));
		Assert.assertEquals(downloadsButton.getText(), testData.get(0).get("lbl_Downloads"));
		Assert.assertEquals(aboutButton.getText(), testData.get(0).get("lbl_About"));
		ClickOnMobileElement(logOutButton);
		waitFor(5000);
	}

	public static void schoolSelectionInvited() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "SelectSchool");
		ClickOnMobileElement(defVal_SearchSchool_txt);
		System.out.println("School:"+testData.get(0).get("inp_SchoolName"));
		SendKeysOnMobileElement(defVal_SearchSchool, testData.get(0).get("inp_SchoolName"));
		ClickOnMobileElement(selectSchoolName);
		ClickOnMobileElement(IOSContinue);
		ClickOnMobileElement(btn_SearchSchoolCont);
	}

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeButton)[2]")
	public static MobileElement notificationicon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static MobileElement notificationlist;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Allow']")
	public MobileElement btn_Allow;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='banner']")
	public static MobileElement lbl_LoginTitle;
	// new
	@iOSXCUITFindBy(accessibility = "Continue")
	public static MobileElement continue2;

	@iOSXCUITFindBy(accessibility = "Start Normally")
	public MobileElement menu_StartNormally;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"location list selection\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Any Location\"]")
	public static MobileElement locationList;

	@AndroidFindBy(id = "android:id/search_src_text")
	@iOSXCUITFindBy(xpath = "//*[@value='School name']")
	public static MobileElement typeYourSchoolName;

	//@AndroidFindBy(id = "android:id/search_src_text")
	@AndroidFindBy(xpath = "//*[@text=\"Search School Name\"]")
	public static MobileElement keyWordEnter;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Clear\"]")
	public static MobileElement selectSchool_Xicon;
	
	@AndroidFindBy(xpath = "//*[@text='New York (NY)']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"New York (NY)\"]")
	public static MobileElement chooseState;

	@AndroidFindBy(xpath = "//*[@text='Photon_01_Site_A, McHenry, IL']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dlx BnT Digital Site, McHenry, IL']")
	public static MobileElement selectSchoolName;

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"That's my school\"]")
	public static MobileElement thatsMySchool;

	@AndroidFindBy(id = "com.android.chrome:id/terms_accept")
	public static MobileElement acceptContinue;

	@AndroidFindBy(id = "com.android.chrome:id/next_button")
	public static MobileElement nextButton;

	@AndroidFindBy(id = "com.android.chrome:id/negative_button")
	public static MobileElement negativeButton;

	@AndroidFindBy(xpath = "//*[@resource-id = 'userName']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"main\"]/XCUIElementTypeOther[2]/XCUIElementTypeTextField")
	public static MobileElement userName;

	@AndroidFindBy(xpath = "//*[@resource-id='userPassword']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"main\"]/XCUIElementTypeOther[2]/XCUIElementTypeTextField/XCUIElementTypeSecureTextField")
	public static MobileElement password;

	@AndroidFindBy(xpath = "//*[@class='android.widget.Button' and @text='Submit']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"Submit\"])[2]")
	public static MobileElement submit;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Done\"]")
	public static MobileElement iosDone;

	@iOSXCUITFindBy(id = "Continue")
	public static MobileElement IOSContinue;

	@iOSXCUITFindBy(xpath = "//*[@label='iconsCoreInfo']")
	public static MobileElement menu;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypePopover/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther")
	public static MobileElement testingTools;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"DLY\"]")
	public static MobileElement DLY;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"UAT\"]")
	public static MobileElement UAT;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='DLX']")
	public static MobileElement env_DLX;

	@iOSXCUITFindBy(xpath = "//*[@name='DLZ']")
	public static MobileElement env_DLZ;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='")
	public static MobileElement iosButtonPre;

	@iOSXCUITFindBy(xpath = "\"']")
	public static MobileElement iosButtonPost;

	@iOSXCUITFindBy(xpath = "(//*[@name='No, Thanks'])[2]")
	public static MobileElement btn_NoThanks;

	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Let's Find Your School\"]")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"Let's Find Your School\"]")
	public static MobileElement title_SchoolName;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"“DD BookClub” Would Like to Send You Notifications\"]")
	public static MobileElement lbl_PopUpNitification;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/school_picker_location_textView")
	@iOSXCUITFindBy(xpath = "//*[@label='LOCATION']")
	public static MobileElement lbl_Location;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/school_picker_search_view")
	@iOSXCUITFindBy(xpath = "//*[@label='Search School Name']")
	public static MobileElement lbl_SearchSchool;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Destiny Discover\"]")
	@iOSXCUITFindBy(xpath = "//*[@label='Destiny Discover']")
	public static MobileElement lbl_DDTitle;

	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Any Location\"]")
	@iOSXCUITFindBy(xpath = "//*[@value='Any Location']")
	public static MobileElement defVal_Location;

	@AndroidFindBy(xpath = "//*[@value='Search School Name']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField)[2]")
	public static MobileElement defVal_SearchSchool;

	@AndroidFindBy(xpath = "//*[@value='Search School Name']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Search School Name\"]")
	public static MobileElement defVal_SearchSchool_txt;

	@AndroidFindBy(xpath = "//*[@text='CONTINUE']")
	public static MobileElement Continue;
	
	@AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/school_picker_content_continue_button']")
	public static MobileElement searchSchoolBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/school_picker_content_continue_button")
	@iOSXCUITFindBy(xpath = "//*[@label='Continue']")
	public static MobileElement btn_SearchSchoolCont;
	
	/************************** Login assertion Mobile Elements ****************************************/

//	@AndroidFindBy(xpath = "//*[@text='Photon_01_Site_A']")
//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='banner']")
//	public MobileElement lbl_LoginTitle;
//	
	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @text='Log in using your Follett account']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Log in using your Follett account']")
	public MobileElement lbl_LoginUsingFolletTitle;

	@AndroidFindBy(xpath = "//*[@class ='android.view.View' and @text='Username']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Username']")
	public MobileElement lbl_UserName;

	@AndroidFindBy(xpath = "//*[@class ='android.view.View' and @text='Password']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Password']")
	public MobileElement lbl_Password;

	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @text='Trouble logging in? Ask your media specialist or librarian.']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Trouble logging in? Ask your media specialist or librarian.']")
	public MobileElement lbl_LoginNotes;

	@AndroidFindBy(xpath = "//*[@class='android.view.View' and @text='© 2021 Follett School Solutions; Inc.All Rights Reserved.']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='© 2021 Follett School Solutions; Inc.']")
	public MobileElement lbl_CopyRights;
	
	/************************** Logout Mobile Elements ****************************************/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"MORE\"]/android.view.ViewGroup/android.widget.TextView")
	public static MobileElement footerMoreIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/logout_text")
	public static MobileElement logOutButton;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/download_text")
	public static MobileElement downloadsButton;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/about_text")
	public static MobileElement aboutButton;
	
	/********************************** Set Preference Mobile Elements ****************************************/

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvTitle")
	@iOSXCUITFindBy(xpath = "//*[@label='Set Your Reading Preferences']")
	public static MobileElement setPref_Header_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvDescription")
	public static MobileElement setPref_Header_Description;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/image")
	public static List<MobileElement> setPref_Options_Image;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/info_text")
	public static List<MobileElement> setPref_Options_Info;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/close_image")
	@iOSXCUITFindBy(xpath = "//*[@label='iconsCoreRemove']")
	public static MobileElement setPref_CloseIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/saveInterestBt")
	@iOSXCUITFindBy(xpath = "//*[@label='Save Interests']")
	public static MobileElement setPref_SaveBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/noThanksBt")
	@iOSXCUITFindBy(xpath = "(//*[@name='No, Thanks'])[2]")
	public static MobileElement setPref_NoThanksBtn;

	

	
	
	public MobileElement getIosDone() {
		return iosDone;
	}

	public MobileElement getUAT() {
		return UAT;
	}

	public MobileElement getLocationList() {
		return locationList;
	}

	public MobileElement getTypeYourSchoolName() {
		return typeYourSchoolName;
	}

	public MobileElement getKeyWordEnter() {
		return keyWordEnter;
	}

	public MobileElement getChooseState() {
		return chooseState;
	}

	public MobileElement getSelectSchoolName() {
		return selectSchoolName;
	}

	public MobileElement getThatsMySchool() {
		return thatsMySchool;
	}

	public MobileElement getAcceptContinue() {
		return acceptContinue;
	}

	public MobileElement getNextButton() {
		return nextButton;
	}

	public MobileElement getNegativeButton() {
		return negativeButton;
	}

	public MobileElement getUserName() {
		return userName;
	}

	public MobileElement getPassword() {
		return password;
	}

	public MobileElement getSubmit() {
		return submit;
	}

	public MobileElement getIOSContinue() {
		return IOSContinue;
	}

	public MobileElement getMenu() {
		return menu;
	}

	public MobileElement getTestingTools() {
		return testingTools;
	}

	public MobileElement getDLY() {
		return DLY;
	}

}
