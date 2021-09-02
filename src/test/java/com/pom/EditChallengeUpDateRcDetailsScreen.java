package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EditChallengeUpDateRcDetailsScreen extends CapabilitiesAndWebDriverUtils{
	static CreateChallengeAddFriends addFriends = new CreateChallengeAddFriends();
	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(EditChallengeUpDateRcDetailsScreen.class);
	
public EditChallengeUpDateRcDetailsScreen()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
public static void editReadingChalleng() throws IOException, InvalidFormatException {
	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Create challenge");
	waitFor(10000);
	ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreenMoreIcon.moreIcon);
	logger.info(getData("platformName") + " - CC details page is displayed with more icon #Pass");

	Assert.assertTrue(CreateAChallengeCreatorRCDetailsScreenMoreIcon.editChallenge.isDisplayed());
	ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreenMoreIcon.editChallenge);
	logger.info(getData("platformName") + " - Edit Challenge button is clicked #Pass");
	waitFor(5000);
	
	CreateAChallengeBasicChallengeDetails.description.clear();
	SendKeysOnMobileElement(CreateAChallengeBasicChallengeDetails.description, testData.get(0).get("Edit_Desc"));
	hideMobileKeyboard();
	logger.info(getData("platformName") + " - Description is edited successfully #Pass");
	
	
	List<Map<String, String>> testData2 = reader.getData("./Data/MobileData.xlsx", "Add Titles");
	List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "FilterPage");
	waitFor(5000);
	swipeDown();
	ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.addTitlesCTA);
	waitFor(1000);
	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_AddTitle_Edit.png");
	logger.info(getData("platformName") + " - Clicked on Add Title button in Edit #Pass");
	SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,
			testData2.get(0).get("Title_Keyword"));
	if (getData("platformName").equalsIgnoreCase("android")
			|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		waitFor(15000);
		ClickOnMobileElement(CreateChallengeAddTitles.keywordSuggestionList.get(0));
	}
	else {
		CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);	
	}
	waitFor(10000);
//	WaitForMobileElement(CreateChallengeAddTitles.listViewButton);
//	ClickOnMobileElement(CreateChallengeAddTitles.listViewButton);
//	waitFor(5000);
//	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_ListView_Edit.png");
//	ClickOnMobileElement(CreateChallengeAddTitles.listViewButton);
//	waitFor(15000);
//	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_GridView_Edit.png");
//	logger.info(getData("platformName") + " _ List view and Grid view is Working fine in Edit #Pass");
	
	ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
	if (getData("platformName").equalsIgnoreCase("android")
			|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		Assert.assertEquals(CreateChallengeAddTitles.fil_SearchType.getText(), testData1.get(0).get("lbl_Option1"));
		Assert.assertEquals(CreateChallengeAddTitles.filterPagedefVal_Option3.getText(), testData1.get(0).get("defVal_Option3"));
		ClickOnMobileElement(CreateChallengeAddTitles.filterPagePlusIcon.get(1));
		waitFor(5000);
		logger.info(getData("platformName") + " _ Material filter is clicked in Edit #Pass");
		ClickOnMobileElement(CreateChallengeAddTitles.subfilterSelcetion.get(1));
		logger.info(getData("platformName") + " _ All available filter is clicked in Edit #Pass");
		waitFor(3000);
		ClickOnMobileElement(CreateChallengeAddTitles.filterPagePlusIcon.get(2));
		waitFor(5000);
		logger.info(getData("platformName") + " _ Format filter is clicked in Edit #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter_Edit.png");
		ClickOnMobileElement(CreateChallengeAddTitles.subfilterSelcetion.get(1));
		waitFor(1000);
		logger.info(getData("platformName") + " _ eBook is Selected in Edit #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_eBookSelected_Edit.png");
	}
	if (getData("platformName").equalsIgnoreCase("iOS")
			|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
		Assert.assertEquals(CreateChallengeAddTitles.fil_SearchType.getText(), testData1.get(0).get("lbl_Option1_iOS"));
		Assert.assertEquals(CreateChallengeAddTitles.filterPagedefVal_Option3.getText(), testData1.get(0).get("defVal_Option3_iOS"));
		ClickOnMobileElement(CreateChallengeAddTitles.filterPagePlusIcon.get(5));
		waitFor(2000);
		waitFor(1000);
		logger.info(getData("platformName") + " _ Format filter is clicked in Edit #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter_Edit.png");
		ClickOnMobileElement(CreateChallengeAddTitles.subfilterSelcetion.get(1));
		waitFor(1000);
		logger.info(getData("platformName") + " _ eBook is Selected in Edit #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_eBookSelected_Edit.png");
	}
	waitFor(2000);
	ClickOnMobileElement(CreateChallengeAddTitles.filterApplyButton);
	waitFor(20000);
	List<MobileElement> moreOptions = CreateChallengeAddTitles.title_MoreOptions;
	System.out.println("Displayed More icon size " + ": " + CreateChallengeAddTitles.title_MoreOptions.size());

	ClickOnMobileElement(moreOptions.get(0));
	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TitleMore_Edit.png");
	waitFor(1000);
	ClickOnMobileElement(CreateChallengeAddTitles.addToChallengeButton);
	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddtoChallenge_Edit.png");

	waitFor(1000);
	ClickOnMobileElement(CreateChallengeAddTitles.doneButton);
	waitFor(2000);
	logger.info(getData("platformName") + " _ Added to Challenge in Edit #Pass");
	ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
	waitFor(10000);
	logger.info(getData("platformName") + " - Save  Challenge is clicked in Edit #Pass");
	waitFor(5000);
	Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CCDone_Edit.png");
	logger.info(getData("platformName") + " - Challenge is Edited Successfully #Pass");

	waitFor(5000); //new
	Assert.assertEquals(CreateAChallengeCreatorRCDetailsScreen.challengeDesc.getText(),testData.get(0).get("Edit_Desc")); //new
	logger.info(getData("platformName") + " - Edited content reflected in RC Page #Pass"); //new

	}

	public static void editrc() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Create challenge");
		List<Map<String, String>> testData2 = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		
		waitFor(4000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreenMoreIcon.moreIcon);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreenMoreIcon.editChallenge);
		waitFor(3000);
		CreateAChallengeBasicChallengeDetails.challengeName.clear();
		SendKeysOnMobileElement(CreateAChallengeBasicChallengeDetails.challengeName,
				RandomStringGenerate(5));
		hideMobileKeyboard();
		logger.info(getData("platformName") + " - Challenge Name is Edited Successfully #Pass");
		CreateAChallengeSetReadbyDate.editedDate();
		logger.info(getData("platformName") + " - End date is Edited Successfully #Pass");
		swipeDown();
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.addTitlesCTA);
		waitFor(1000);
		
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,
				testData2.get(3).get("Title_Keyword"));
		CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
		
		ClickOnMobileElement(CreateChallengeAddTitles.bookname);
		ClickOnMobileElement(CreateChallengeAddTitles.addToChallengeBtn);
		ClickOnMobileElement(CreateChallengeAddTitles.doneButton);
		
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallenge/CCDone_Edit.png");
		logger.info(getData("platformName") + " - Added new Titles Successfully #Pass");
	
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.saveChallengeBtn);
		waitFor(4000);
		
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallenge/CCDone_Edit.png");
		logger.info(getData("platformName") + " - Edited  Successfully #Pass");
		
		ClickOnMobileElement(editedrcpage_backbtn);
		waitFor(3000);
			
	}
	
@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]")
public static MobileElement editedrcpage_backbtn;
		
@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/editText_challenge_name\"]")
@iOSXCUITFindBy(xpath="//*[@value='Enter Challenge Name']")
public static MobileElement ChNameTextBox;

@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/text_input_description\"]")
@iOSXCUITFindBy(xpath="//*[@value='Description (Optional)']")
public static MobileElement ChNameDescrip;

@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/add_invitees\"]")
@iOSXCUITFindBy(xpath="//*[@x='20']")
public static MobileElement ChAddInvFrds;

@AndroidFindBy(xpath =  "//*[@resource-id=\"com.follett.fss.searchread.stage:id/add_titles\"]")
@iOSXCUITFindBy(xpath="//*[@x='36']")
public static MobileElement ChAddTitles;

@AndroidFindBy(xpath =  "//*[@resource-id=\"com.follett.fss.searchread.stage:id/button_start_challenge\"]")
@iOSXCUITFindBy(xpath="//*[@x='16'']")
public static MobileElement btn_Save;

@AndroidFindBy(xpath =  "//*[@resource-id=\"com.follett.fss.searchread.stage:id/button_save_challenge\"]")
@iOSXCUITFindBy(xpath="//*[@x='186']")
public static MobileElement CHSave;

@AndroidFindBy(xpath =  "//*[@resource-id=\"android:id/text1\"]")
@iOSXCUITFindBy(xpath="//*[@value='Enter Challenge Name']")
public static MobileElement SetRmdr;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvAddToExistingChallenge")
public static MobileElement addTOExistingChallengeBtn;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvIncludeInNewChallenge")
public static MobileElement includeinNewChallengeBtn;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvCheckout")
public static MobileElement checkoutBtn;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvReadOnline")
public static MobileElement readonlineBtn;



public MobileElement getChNameTextBox() {
	return ChNameTextBox;
}

public MobileElement getChNameDescrip() {
	return ChNameDescrip;
}

}

