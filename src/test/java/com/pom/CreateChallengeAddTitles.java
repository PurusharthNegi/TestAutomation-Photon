package com.pom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

public class CreateChallengeAddTitles extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(CreateChallengeAddTitles.class);

	CreateChallengeSetReminders setReminders = new CreateChallengeSetReminders();
	CreateAChallengeSetReadbyDate setReadbyDate = new CreateAChallengeSetReadbyDate();
	CreateAChallengeCreatorRCDetailsScreen creatorRCDetailsScreeen = new CreateAChallengeCreatorRCDetailsScreen();
	
	public static String challengeNameToAddTitle;
	
	public CreateChallengeAddTitles() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static void addTitleToChallenge() throws IOException, InvalidFormatException {

		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "FilterPage");
		logger.info("--"+ getData("platformName") + " -  Add to Challenge Validation Start --");
		waitFor(3000);
		Assert.assertEquals(CreateAChallengeBasicChallengeDetails.ChallengeName, searchTitle_Header.getText());
		logger.info(getData("platformName") + " -  Challenge Name :"+CreateAChallengeBasicChallengeDetails.ChallengeName+" is Displayed #Pass");
		WaitForMobileElement(filterIcon);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage.png");

		for (int i = 0; i <= 2; i++) {
			Boolean listcheck = true;
			SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,
					testData.get(i).get("Title_Keyword"));
			logger.info(getData("platformName") + " _ "+testData.get(i).get("Title_Keyword") + " : Search keyword is enterd #Pass");
			waitFor(2000);
			int searchCount = keywordSuggestionList.size();
			logger.info(getData("platformName") + " _ "+testData.get(i).get("Title_Keyword") + " : Search keyword is displayed :" + searchCount
					+ " Times #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_"+(i+1)+".png");


			waitFor(1000);
			if (keywordSuggestionList.size() != 0) {
				for (int j = 0; j < keywordSuggestionList.size(); j++) {
					keywordSuggestionList.get(j).getText().contains(testData.get(i).get("Title_Keyword"));

				}
				Assert.assertTrue(listcheck);
			}
			if (i == 2) {
				if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
					ClickOnMobileElement(keywordSuggestionList.get(0));
				}
				
				if (getData("platformName").equalsIgnoreCase("iOS")
						|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
				CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
				}
			} else {
				CreateaChallengeSearchTitleResultsListView.searchTextBox.clear();
			}
		}
		waitFor(10000);
		WaitForMobileElement(listViewButton);
		// keyBoardSearch();
		String beforeCount = searchResultCount.getText();
		logger.info(getData("platformName") + " _ Search Result count before Filter apply : " + beforeCount +" #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_SearchResult.png");
		ClickOnMobileElement(filterIcon);
		waitFor(2000);
		Assert.assertEquals(filterCancelButton.getText(), testData1.get(0).get("lbl_Cancel"));
		Assert.assertEquals(filterResetAllButton.getText(), testData1.get(0).get("lbl_ResetAll"));
		Assert.assertEquals(fil_Availablility.getText(), testData1.get(0).get("lbl_Option2"));
		Assert.assertEquals(fil_Format.getText(), testData1.get(0).get("lbl_Option3"));
		Assert.assertEquals(fil_SubLocation.getText(), testData1.get(0).get("lbl_Option4"));
		Assert.assertEquals(filterPagedefVal_Option1.getText(), testData1.get(0).get("defVal_Option1"));
		Assert.assertEquals(filterPagedefVal_Option2.getText(), testData1.get(0).get("defVal_Option2"));
		Assert.assertEquals(filterPagedefVal_Option4.getText(), testData1.get(0).get("defVal_Option4"));

		logger.info(getData("platformName") + " _ Filter Page Lable Validation Completed #Pass");

		waitFor(5000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(fil_SearchType.getText(), testData1.get(0).get("lbl_Option1"));
			Assert.assertEquals(filterPagedefVal_Option3.getText(), testData1.get(0).get("defVal_Option3"));
			ClickOnMobileElement(filterPagePlusIcon.get(2));
			waitFor(1000);
			logger.info(getData("platformName") + " _ Format filter is clicked #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter.png");
			ClickOnMobileElement(subfilterSelcetion.get(1));
			waitFor(1000);
			logger.info(getData("platformName") + " _ eBook is Selected #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_eBookSelected.png");
		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			Assert.assertEquals(fil_SearchType.getText(), testData1.get(0).get("lbl_Option1_iOS"));
			Assert.assertEquals(filterPagedefVal_Option3.getText(), testData1.get(0).get("defVal_Option3_iOS"));
			ClickOnMobileElement(formatPlusIcon);
			waitFor(2000);
			waitFor(1000);
			logger.info(getData("platformName") + " _ Format filter is clicked #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter.png");
			ClickOnMobileElement(subfilterSelcetion.get(1));
			waitFor(1000);
			logger.info(getData("platformName") + " _ eBook is Selected #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_eBookSelected.png");
		}
		waitFor(2000);
		ClickOnMobileElement(filterApplyButton);
		logger.info(getData("platformName") + " _ Filter Applied Successfully #Pass");
		waitFor(4000);
		ClickOnMobileElement(listViewButton);
		waitFor(5000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_ListView.png");

		ClickOnMobileElement(listViewButton);
		waitFor(5000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_GridView.png");
		logger.info(getData("platformName") + " _ List view and Grid view is Working fine #Pass");

		ClickOnMobileElement(titleImage);
		waitFor(5000);
		Assert.assertTrue(quickViewTiltleName.isDisplayed());
		logger.info(getData("platformName") + " _ Quick view page is displayed #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_QuickView.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		swipeUp();
		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
		MobDragAndDrop(quickViewClose, quickViewTiltleName);
		}
		logger.info(getData("platformName") + " _ Returned from Quick view page #Pass");
		waitFor(15000);
		
		String afterCount = searchResultCount.getText();
		logger.info(getData("platformName") + " _ Search Result count after Filter apply : " + afterCount +" #Pass");
		if (beforeCount.equals(afterCount)) {
			Assert.assertTrue(false);
			logger.info(getData("platformName") + " _ Filter option is not working #Fail");
		} else {
			logger.info(getData("platformName") + " _ Filter is working fine #Pass");
		}
		logger.info(getData("platformName") + " _ Started adding titles to the Challenge #Pass");
		System.out.println("Displayed More icon size " + ": " + title_MoreOptions.size());
		for (int j = 0; j < 1; j++) {
			waitFor(2000);
			List<MobileElement> moreOptions = title_MoreOptions;
			for (int i = 0; i < moreOptions.size(); i++) {
				moreOptions.get(i).click();
				waitFor(2000);
				int moreMenu = acList.size();
				if (moreMenu > 2) {
					waitFor(1000);
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_AddedtoChallenge_"+(i+1)+".png");
					ClickOnMobileElement(addToChallengeButton);
					logger.info(getData("platformName") + " _ Added to Challenge #Pass");
					waitFor(5000);
				} else {
					waitFor(1000);
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_Open_"+(i+1)+".png");
					ClickOnMobileElement(cancelButton);
					logger.info(getData("platformName") + " _ Add to Challenge button is not displayed to moving to next book #Pass");

				}
			}
		}
		CreateaChallengeSearchTitleResultsListView.searchTextBox.clear();
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(4).get("Title_Keyword"));
		waitFor(5000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			ClickOnMobileElement(keywordSuggestionList.get(0));
			waitFor(8000);
		}
		
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
		CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
		}
		List<MobileElement> moreOptions = title_MoreOptions;
		for (int i = 0; i < moreOptions.size(); i++) {
			moreOptions.get(i).click();
			waitFor(2000);
			int moreMenu = acList.size();
			if (moreMenu > 2) {
				waitFor(1000);
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_AddedtoChallenge_"+(i+1)+".png");
				ClickOnMobileElement(addToChallengeButton);
				logger.info(getData("platformName") + " _ Added to Challenge #Pass");
				waitFor(5000);
			} else {
				waitFor(1000);
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_Open_"+(i+1)+".png");
				ClickOnMobileElement(cancelButton);
				logger.info(getData("platformName") + " _ Add to Challenge button is not displayed to moving to next book #Pass");

			}
		}
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_AddedBooks.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			if (favTitleBooklist.size()>4) {
				horizontalSwipeAndriod(favTitleBooklist);
				logger.info(getData("platformName") + " _ Books are added to Challenge and displayed in the favourite and horizontal scroll is working fine #Pass");
			}
		logger.info(getData("platformName") + " _ Added books are displayed and size is less the 5 #Pass");

		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			
		horizontalSwipe(favTitleBooklist);
		logger.info(getData("platformName") + " _ Books are added to Challenge and displayed in the favourite and horizontal scroll is working fine #Pass");

		}
		
		int addedBookSize = favTitleBooklist.size();
		removeTitlefromSearchPage.get(0).click();
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_RemoveBookinFav.png");

		int afterDeleteBookSize = favTitleBooklist.size();
		if (addedBookSize > afterDeleteBookSize) {
			logger.info(getData("platformName") + " _ Book is removed from favourite  #Pass");
		}
		ClickOnMobileElement(filterIcon);
		ClickOnMobileElement(filterResetAllButton);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FilterReset.png");
		ClickOnMobileElement(filterApplyButton);
		logger.info(getData("platformName") + " _ Filter is reset successfully #Pass");

		ClickOnMobileElement(doneButton);
		swipeDown();
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		if (CreateAChallengeBasicChallengeDetails.addedBooks.size()>3) {
			horizontalSwipe(CreateAChallengeBasicChallengeDetails.addedBooks);
		}
		for (int i = 0; i < CreateAChallengeBasicChallengeDetails.addedBooks.size(); i++) {
			Assert.assertEquals(CreateAChallengeBasicChallengeDetails.addedBooks.get(i).isDisplayed(), true);

		}
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_TitlesAdded.png");
		logger.info(getData("platformName") + " _ Validating the title display in create challenge page #Pass");
		
		}
		
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			horizontalSwipe(CreateAChallengeBasicChallengeDetails.addedBooks);
			for (int i = 3; i < CreateAChallengeBasicChallengeDetails.addedBooks.size(); i++) {
				Assert.assertEquals(CreateAChallengeBasicChallengeDetails.addedBooks.get(i).isDisplayed(), true);
		
	}
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_TitlesAdded.png");
			logger.info(getData("platformName") + " _ Validating the title display in create challenge page #Pass");
			
	}
		addTitleAssertion();
		logger.info("--"+ getData("platformName") + " -  Add to Challenge Validation End --");

	}

	public static void addTitleAssertion() throws IOException {

		logger.info(getData("platformName") + " - Header Validation #Pass");
		logger.info(getData("platformName") + " - Search result Validation #Pass");
		logger.info(getData("platformName") + " - Add title to challenge #Pass");
		logger.info(getData("platformName") + " - Add title carousel Validation #Pass");
		logger.info(getData("platformName") + " - Remove title from carousel Validation #Pass");
		logger.info(getData("platformName") + " - Apply Filter #Pass");
		logger.info(getData("platformName") + " - Reset Filter #Pass");
	}

	public static void removeTitleFromChallenge() throws IOException {
		swipeDown();
		for (int i = 1; i <= 1; i++) {
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.removeTitleXIcon.get(i));
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.TitesRemoveBtn);
		}

		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_TitlesRemoved.png");
		logger.info(getData("platformName") + " _ Title removed from CC Home page #Pass");	
		
	}
	
	public void addTitleNewUser() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		waitFor(5000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		
		try {
			if (keywordSuggestionList.get(0).isDisplayed()) {
				logger.info(" --" + getData("platformName") + " - No suggestion list is displayed for new user #Fail");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/addTitle_NewUser.png");
			}
		} catch (Exception e) {
				logger.info(" --" + getData("platformName") + " - No suggestion list is displayed for new user #pass");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/addTitle_NewUser.png");
		}
		
		}
		
		else {
			
			Assert.assertEquals(emptyList.isDisplayed(), true);
			logger.info(" --" + getData("platformName") + " - No suggestion list is displayed for new user #pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/addTitle_NewUser.png");
		}
		
		
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(0).get("invalidInput"));
		hideMobileKeyboard();
		
		
		try {
			waitFor(20000);
			System.out.println("Keyword suggestion list Size : "+ keywordSuggestionList.size());
			if (keywordSuggestionList.size()==0) {
				logger.info(" --" + getData("platformName") + " - No suggestion displayed for invalid input #pass");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/addTitle_invalidInput.png");
			}
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - No suggestion List displayed for invalid input #Fail");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/addTitle_invalidInput.png");
		}
		CreateaChallengeSearchTitleResultsListView.searchTextBox.clear();
		waitFor(5000);
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(2).get("Title_Keyword"));
		hideMobileKeyboard();
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			waitFor(15000); // New
			ClickOnMobileElement(CreateChallengeAddTitles.keywordSuggestionList.get(0));
		} else {
			CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
		}
		waitFor(10000);
		ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			ClickOnMobileElement(CreateChallengeAddTitles.filterPagePlusIcon.get(2));
			waitFor(1000);
			logger.info(getData("platformName") + " _ Format filter is clicked #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter.png");
			ClickOnMobileElement(CreateChallengeAddTitles.subfilterSelcetion.get(1));
			waitFor(1000);
			logger.info(getData("platformName") + " _ eBook is Selected #Pass");
		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			ClickOnMobileElement(CreateChallengeAddTitles.formatPlusIcon);
			waitFor(3000);
			logger.info(getData("platformName") + " _ Format filter is clicked #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter.png");
			ClickOnMobileElement(CreateChallengeAddTitles.subfilterSelcetion.get(1));
			waitFor(1000);
			logger.info(getData("platformName") + " _ eBook is Selected #Pass");
			}
		waitFor(2000);
		ClickOnMobileElement(CreateChallengeAddTitles.filterApplyButton);
		waitFor(20000);
		List<MobileElement> moreOptions = CreateChallengeAddTitles.title_MoreOptions;
		System.out.println("Displayed More icon size " + ": " + CreateChallengeAddTitles.title_MoreOptions.size());

		ClickOnMobileElement(moreOptions.get(1));
		waitFor(1000);
		ClickOnMobileElement(CreateChallengeAddTitles.addToChallengeButton);
		ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
		ClickOnMobileElement(CreateChallengeAddTitles.filterResetAllButton);
		ClickOnMobileElement(CreateChallengeAddTitles.filterApplyButton);
		logger.info(getData("platformName") + " _ Filter is reset successfully #Pass");
		logger.info(getData("platformName") + " _ Title Added to Challenge #Pass");
		CreateaChallengeSearchTitleResultsListView.searchTextBox.clear();
	}

	public void moreOptionValidation() throws InvalidFormatException, IOException {
		
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		waitFor(3000);
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(1).get("Title_Keyword"));
		waitFor(15000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			ClickOnMobileElement(keywordSuggestionList.get(0));
			ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
			ClickOnMobileElement(filterPagePlusIcon.get(1));
			waitFor(1000);
			ClickOnMobileElement(subfilterSelcetion.get(2));
			waitFor(1000);
			}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
			ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
			ClickOnMobileElement(availablePlusIcon);// need to update for iOS
			waitFor(3000);
			ClickOnMobileElement(subfilterSelcetion.get(1));// need to update for iOS
			}
		waitFor(3000);
		ClickOnMobileElement(filterApplyButton);
		waitFor(15000);
		ClickOnMobileElement(title_MoreOptions.get(0));
		logger.info(" --" + getData("platformName") + " - More action button options #pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/moreaction button.png");
		ClickOnMobileElement(cancelBtnforFolletBooks);
		logger.info(" --" + getData("platformName") + " - User lands on search result page after taping cancel #pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/afterCancel tap.png");
		ClickOnMobileElement(title_MoreOptions.get(0));
		ClickOnMobileElement(readButton);
		waitFor(5000);
		Assert.assertTrue(readerCloseButton.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Taping on read option launches reader #pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/reader.png");
		ClickOnMobileElement(readerCloseButton);
		waitFor(10000);
		ClickOnMobileElement(title_MoreOptions.get(0));
		String checkout = checkoutButton.getText();
		System.out.println("Before Tap : "+checkout);
		ClickOnMobileElement(checkoutButton);
		waitFor(10000);
		ClickOnMobileElement(title_MoreOptions.get(0));
		Assert.assertTrue(returnButton.isDisplayed());
		String returnTitle = returnButton.getText();
		System.out.println("After Tap : "+returnTitle);
		try {
			if (checkout.equals(returnTitle)) {
				logger.info(" --" + getData("platformName") + " - Checkout button should turn into return after taping #Fail");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/Checkout_return.png");
			}
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Checkout button should turn into return after taping #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/Checkout_return.png");
		}
		ClickOnMobileElement(returnButton);
		waitFor(5000);
		ClickOnMobileElement(title_MoreOptions.get(0));
		String addChal = addToChallengeButton.getText();
		System.out.println("Before Tap : "+addChal);
		ClickOnMobileElement(addToChallengeButton);
		waitFor(10000);
		ClickOnMobileElement(title_MoreOptions.get(0));
		String removeChal = removeFromChallengeButton.getText();
		System.out.println("After Tap : "+removeChal);
		try {
			if (addChal.equals(removeChal)) {
				logger.info(" --" + getData("platformName") + " - Add to challenge button should turn into Remove from challenge after taping #Fail");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/Add_remove.png");
			}
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Add to challenge button should turn into Remove from challenge after taping #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/Add_remove.png");
		}
		ClickOnMobileElement(removeFromChallengeButton);
		ClickOnMobileElement(listViewButton);
	}
	
	public void listViewDataValidation() throws IOException {
		waitFor(5000);
		Assert.assertTrue(listView_materialTypeIcon.get(0).isDisplayed());
		Assert.assertTrue(listView_titlecoverImage.get(0).isDisplayed());
		try {
			Assert.assertTrue(listView_authorName.get(0).isDisplayed());
			logger.info(" --" + getData("platformName") + " - Author name is displayed for first titles #Pass");
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Author name is not displayed for first titles #Pass");			
		}
		Assert.assertTrue(listView_titleName.get(0).isDisplayed());
		logger.info(" --" + getData("platformName") + " - title name is displayed for first titles #Pass");
		try {
			Assert.assertTrue(listView_callNumber.get(0).isDisplayed());
			logger.info(" --" + getData("platformName") + " - Call Number is displayed for first titles #Pass");
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Call Number is not displayed for first titles #Pass");			
		}
		try {
			Assert.assertTrue(listView_starRating.isDisplayed());
			logger.info(" --" + getData("platformName") + " - Star Rating is displayed for first titles #Pass");
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Star Rating is not displayed for the first title #Pass");			
		}
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/listView.png");
		ClickOnMobileElement(listViewButton);
	}
	
	public void moreOptionNonFollet() throws InvalidFormatException, IOException {
		
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		CreateaChallengeSearchTitleResultsListView.searchTextBox.clear();
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(0).get("Non follet Title"));
		hideMobileKeyboard();
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			waitFor(15000);
			ClickOnMobileElement(keywordSuggestionList.get(0));
		}
		
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
		CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
		}
		waitFor(15000);
		ClickOnMobileElement(title_MoreOptions.get(0));
		try {
			if(acList.size()>2) {
				logger.info(" --" + getData("platformName") + " - Add to Challange is not displayed for non Follet books #Fail");			
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/nonFollet_ MoreOption.png");
			}
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Add to Challange is not displayed for non Follet books #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/nonFollet_ MoreOption.png");
		}
		ClickOnMobileElement(cancelButton);
		
	}

	public void tdpValidation() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		CreateaChallengeSearchTitleResultsListView.searchTextBox.clear();
		waitFor(3000);
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(1).get("Title_Keyword"));
		hideMobileKeyboard();
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			waitFor(15000);
			ClickOnMobileElement(keywordSuggestionList.get(0));
		}
		
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
		CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
		//ClickOnMobileElement(book);
		}
		waitFor(10000);
		logger.info(" --" + getData("platformName") + " -- Title detail page validation start --");
		ClickOnMobileElement(titleImage);
		logger.info(" --" + getData("platformName") + " - User Naviagates to title detail page #Pass");
		waitFor(10000);
		Assert.assertTrue(tdp_MaterialTypeIcon.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Material type icon is displayed for title in TDP #Pass");
		Assert.assertTrue(tdp_TitleCoverImage.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Title cover image is displayed for title in TDP #Pass");
		Assert.assertTrue(tdp_TitleTypeText.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Title Type is displayed for title in TDP #Pass");
		Assert.assertTrue(quickViewTiltleName.isDisplayed());
		logger.info(" --" + getData("platformName") + " - Title Name is displayed for title in TDP #Pass");
		try {
			Assert.assertTrue(tdp_AuthorName.isDisplayed());
			logger.info(" --" + getData("platformName") + " - Author name is displayed for title #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Author.png");
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Author name is not avaialable for title #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Author.png");
		}
		try {
			Assert.assertTrue(tdp_readMoreButton.isDisplayed());
			ClickOnMobileElement(tdp_readMoreButton);
			waitFor(5000);
			swipeDown();
			Assert.assertTrue(tdp_readLessButton.isDisplayed());
			logger.info(" --" + getData("platformName") + " - Read more opt available for the title in TDP #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_ReadMore.png");
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Read more opt is not available for the title in TDP #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_ReadMore.png");
		}
		try {
			Assert.assertTrue(tdp_CallNumber.isDisplayed());
			logger.info(" --" + getData("platformName") + " - Call Number available for the title in TDP #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Call number.png");
		} catch (Exception e) {
			logger.info(" --" + getData("platformName") + " - Call Number is not available for the title in TDP #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Call number.png");
		}
		try {
			swipeDown();
			Assert.assertTrue(tdp_titleAvailabilityText.isDisplayed());
			logger.info(" --" + getData("platformName") + " -Title availability is displayed for the title in TDP #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Availabilty.png");

		} catch (Exception e) {
			swipeDown();
			logger.info(" --" + getData("platformName") + " -Title availability is displyed for the title in TDP #Fail");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Availabilty.png");
		}
		WaitForMobileElement(tdp_addToChallengeButton);
		String lable1 = tdp_addToChallengeButton.getText();
		ClickOnMobileElement(tdp_addToChallengeButton);
		waitFor(5000);
		String lable2 = tdp_addToChallengeButton.getText();
		try {
			System.out.println("Entering try block");
			if (lable1.equals(lable2)) {
				ClickOnMobileElement(tdp_addToChallengeButton);
				logger.info(" --" + getData("platformName") + " - Add to challenge button is change to Remove from challenge in TDP #Fail");			
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Button.png");
			}
		} catch (Exception e) {
			ClickOnMobileElement(tdp_addToChallengeButton);
			logger.info(" --" + getData("platformName") + " - Add to challenge button is change to Remove from challenge in TDP #Pass");			
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TDP_Button.png");
		}
		swipeUp();
		swipeUp();
		try {
			ClickOnMobileElement(doneButton);
		} catch (Exception e) {
			ClickOnMobileElement(searchTitle_pageBack);
		}
		waitFor(5000);
	}
	
	public void removeTitleCancelValidation() throws IOException {
		swipeDown();
		int before = CreateAChallengeBasicChallengeDetails.removeTitleXIcon.size();
		for (int i = 1; i <= 1; i++) {
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.removeTitleXIcon.get(i));
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.titleCancelButton);
		}
		int after = CreateAChallengeBasicChallengeDetails.removeTitleXIcon.size();
		if (before==after) {
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_Titleremove cancel.png");
			logger.info(getData("platformName") + " _ Title is not removed from CC Home page #Pass");	
		}		
	}
	
	public void globalTitleSearch() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		ClickOnMobileElement(globalSearchIcon);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/globalSearchPage.png");
		waitFor(3000);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(2).get("Title_Keyword"));
			waitFor(15000);
			ClickOnMobileElement(keywordSuggestionList.get(0));
			ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
			ClickOnMobileElement(CreateChallengeAddTitles.filterResetAllButton);
			ClickOnMobileElement(CreateChallengeAddTitles.filterApplyButton);
			ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
			ClickOnMobileElement(filterPagePlusIcon.get(2));
			waitFor(1000);
			ClickOnMobileElement(subfilterSelcetion.get(1));
			waitFor(1000);
			}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(2).get("Title_Keyword"));
			waitFor(3000);
			CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
			ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
			ClickOnMobileElement(CreateChallengeAddTitles.filterResetAllButton);
			ClickOnMobileElement(CreateChallengeAddTitles.filterApplyButton);
			ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
			ClickOnMobileElement(filterPagePlusIcon.get(5));
			waitFor(2000);
			ClickOnMobileElement(subfilterSelcetion.get(1));
			waitFor(1000);
		}
		ClickOnMobileElement(filterApplyButton);
		waitFor(15000);
		
		ClickOnMobileElement(title_MoreOptions.get(0));
		ClickOnMobileElement(globalSearch_Moreoptions.get(4));
		logger.info(getData("platformName") + " _ clicking on cancel takes user to title Listing page #Pass");
		
		ClickOnMobileElement(title_MoreOptions.get(0));
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		
		ClickOnMobileElement(globalSearch_Moreoptions.get(0));
		}
		else {
			
			ClickOnMobileElement(globalSearch_Moreoptions.get(2));
			System.out.println("Tapping Add to existing challenge button");
		}
		
		
		waitFor(10000);
		Assert.assertTrue(globalSearch_existingChallengeNameList.get(0).isDisplayed());
		horizontalSwipe(globalSearch_existingChallengeTiltecoverimage);
		logger.info(getData("platformName") + " _ User can able to tap on Add to existing challenge option #Pass");
		logger.info(getData("platformName") + " _ User can able to see current active challenge in the form of carousel #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/globalSearch_ExistChallengeList.png");
		challengeNameToAddTitle = globalSearch_existingChallengeNameList.get(1).getText();
		ClickOnMobileElement(globalSearch_existingChallengeNameList.get(1));
		waitFor(5000);
		Assert.assertEquals(editChallenge_Header.isDisplayed(), true);
		logger.info(getData("platformName") + " _ User naviagates to edit challenge page to save the challenge #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/globalSearch_addtitleExistChallenge.png");
		ClickOnMobileElement(editChallenge_Delete);
		ClickOnMobileElement(editChallenge_ConfirmDelete);
		waitFor(15000);
		Assert.assertTrue(title_MoreOptions.get(0).isDisplayed());
		logger.info(getData("platformName") + " _ User can discard the changes and navigated back to PLP #Pass");
		
		ClickOnMobileElement(title_MoreOptions.get(0));
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		
		ClickOnMobileElement(globalSearch_Moreoptions.get(1));
		
		}
		else {
			ClickOnMobileElement(globalSearch_Moreoptions.get(3));
			
			
		}
		waitFor(5000);
		Assert.assertEquals(editChallenge_Header.isDisplayed(), true);
		swipeDown();
		if (CreateAChallengeBasicChallengeDetails.addedBooks.size()!=0) {
			logger.info(getData("platformName") + " _ User naviagates to Create challenge page to start new challenge with added title  #Pass");	
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/globalSearch_addtitle.png");
		}
		else {
			logger.info(getData("platformName") + " _ User naviagates to Create challenge page to start new challenge with added title  #Fail");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/globalSearch_addtitle.png");
		}
	}
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView[2]/XCUIElementTypeCell[1]")
	public static MobileElement bookname;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"No results found\"]")
	public static MobileElement noResultsFound;
	
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_title")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeStaticText)[1]")
	public static MobileElement searchTitle_Header;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable[@name=\"Empty list\"]")
	public static MobileElement emptyList;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[1]")
	public static MobileElement book;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_back")
	public static MobileElement searchTitle_pageBack;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_global_search_icon")
	@iOSXCUITFindBy(xpath = "//*[@name='Search']")
	public static MobileElement globalSearchIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_filter")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Filter icon']")
	//@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SortByIcon']")
	public static MobileElement filterIcon;

	@AndroidFindBy(xpath = "//*[@text='Search Filters']")
	 @iOSXCUITFindBy(xpath="//*[@name=\"Search Filters\"]")
	public static MobileElement filterPageHeader;

	@AndroidFindBy(xpath = "//*[@text='Search Type']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Search Type\"][1]")
	public static MobileElement fil_SearchType;

	@AndroidFindBy(xpath = "//*[@text='Availability']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Availability'])[1]")
	public static MobileElement fil_Availablility;
	
	@AndroidFindBy(xpath = "//*[@text='Format']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Format'])[1]")
	public static MobileElement fil_Format;

	@AndroidFindBy(xpath = "//*[@text='Sublocation']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Sublocation'])[1]")
	public static MobileElement fil_SubLocation;

	@AndroidFindBy(xpath = "//*[@text='Keyword']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Keyword'])[1]")
	public static MobileElement filterPagedefVal_Option1;

	@AndroidFindBy(xpath = "//*[@text='All']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='All'])[1]")
	public static MobileElement filterPagedefVal_Option2;

	@AndroidFindBy(xpath = "//*[@text='All Materials']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"All Materials\"])[1]")
	public static MobileElement filterPagedefVal_Option3;

	@AndroidFindBy(xpath = "//*[@text='Unlimited']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Unlimited'])[1]")
	public static MobileElement filterPagedefVal_Option4;

	@AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/group_icon']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther /XCUIElementTypeButton")
	public static List<MobileElement> filterPagePlusIcon;
	
	@iOSXCUITFindBy(xpath = "//*[@name='Expand Format']")
	public static MobileElement formatPlusIcon;
	
	@iOSXCUITFindBy(xpath = "//*[@name=\"Expand Availability\"][1]")
	public static MobileElement availablePlusIcon;
	
	@iOSXCUITFindBy(xpath = "//*[@name='Expand Sublocation']")
	public static MobileElement sublocationPlusIcon;
	
	@AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/selected_image']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell/XCUIElementTypeButton)")
	public static List<MobileElement> subfilterSelcetion;

	@AndroidFindBy(xpath = "//*[@text='Apply']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply']")
	public static MobileElement filterApplyButton;

	@AndroidFindBy(xpath = "//*[@text='Cancel']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Cancel']")
	public static MobileElement filterCancelButton;

	@AndroidFindBy(xpath = "//*[@text='Reset All']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Reset All']")
	public static MobileElement filterResetAllButton;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/clear_icon")
	// @iOSXCUITFindBy(xpath="")
	public static MobileElement searchBarClearIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_left_action_button")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Done\"]")
	public static MobileElement doneButton;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_view_type")
	@iOSXCUITFindBy(xpath = "//*[@name=\"List view\"]")
	public static MobileElement listViewButton;

	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/material_cover_image_view'])[1]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]")
	public static MobileElement titleImage;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/sheet_button_view")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeApplication[@name=' Discover']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther)[3]")
	public static MobileElement quickViewClose;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/search_search_term")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText)[2]")
	public static MobileElement searchResultCount;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/quick_book_name")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeCell/XCUIElementTypeStaticText)[4]")
	public static MobileElement quickViewTiltleName;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ic_remove_titles")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeButton[@name='RemoveItemMedium'])[1]")
	public static List<MobileElement> removeTitlefromSearchPage;

	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/option_view'])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Add to Challenge']")
	public static MobileElement addToChallengeButton;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Remove from Challenge\"]")
	public static MobileElement removefromchallengebtn;
	
	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/option_view'])[2]")
	public static MobileElement checkoutButton;
	
	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/option_view'])[3]")
	public static MobileElement readButton;
	
	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/option_view'])[4]")
	public static MobileElement cancelBtnforFolletBooks;
	
	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/option_view'])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Add to Challenge']")
	public static MobileElement removeFromChallengeButton;
	
	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/option_view'])[3]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Add to Challenge']")
	public static MobileElement returnButton;
		
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/option_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton")
	public static List<MobileElement> acList;
	
	@AndroidFindBy(xpath = "(//*[@resource-id='com.follett.fss.searchread.stage:id/option_view'])[2]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
	public static MobileElement cancelButton;

	@AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/recent_text']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell")
	public static List<MobileElement> keywordSuggestionList;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeButton[@name=\"iconsCoreRemove\"])[1]")
	public static MobileElement iosXIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/more_option_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'More')]")
	//@iOSXCUITFindBy(xpath = "//*[@label='iconsCoreInfo']")
	public static List<MobileElement> title_MoreOptions;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/reader_close_button")
	public static MobileElement readerCloseButton;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/quick_status_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[4]")
	public static MobileElement tdp_MaterialTypeIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/quick_cover_image_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static MobileElement tdp_TitleCoverImage;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/quick_book_author_name")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[5]")
	public static MobileElement tdp_AuthorName;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/title_details_material_type_text_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]")
	public static MobileElement tdp_TitleTypeText;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/title_details_material_type_text_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]")
	public static MobileElement tdp_CallNumber;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/title_details_add_to_challenge_button")
	public static MobileElement tdp_addToChallengeButton;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvReadMore")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Read More\"]")
	public static MobileElement tdp_readMoreButton;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvReadLess")
	public static MobileElement tdp_readLessButton;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvAvailableInfo")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[2]/XCUIElementTypeStaticText[4]")
	public static MobileElement tdp_titleAvailabilityText;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/item_material_book_list_material_status_image_view")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell)")
	public static List<MobileElement> listView_materialTypeIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/item_material_book_list_cover_image_view")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell)")
	public static List<MobileElement> listView_titlecoverImage;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/item_material_book_list_author_name")
	public static List<MobileElement> listView_authorName;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/item_material_book_list_book_title")
	public static List<MobileElement> listView_titleName;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/item_material_book_list_call_number")
	public static List<MobileElement> listView_callNumber;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/item_material_book_list_rating_bar")
	public static MobileElement listView_starRating;
		
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/item_material_book_list_more_image_view")
	public static MobileElement listView_moreIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/option_text")
	public static List<MobileElement> globalSearch_Moreoptions;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/first_book")
	@iOSXCUITFindBy(id = "(//XCUIElementTypeCell)")
	public static List<MobileElement> globalSearch_existingChallengeTiltecoverimage;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(id = "(//XCUIElementTypeCell)")
	public static List<MobileElement> globalSearch_existingChallengeNameList;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_title")
	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"Edit Challenge\"]")
	public static MobileElement editChallenge_Header;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_right_action_button")
	public static MobileElement editChallenge_Delete;
	
	@AndroidFindBy(xpath = "//*[@text='DELETE']")
	public static MobileElement editChallenge_ConfirmDelete;
	
	@iOSXCUITFindBy(id = "(//XCUIElementTypeImage[@name=\"iconsContentBook\"])[2]")
	// @iOSXCUITFindBy(xpath="")
	public static MobileElement iosMaterialType;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeStaticText[@name=\"J.K. Rowling Mary GrandPre\"])[1]")
	public static MobileElement iosauthorName;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Harry Porter and the Chamber of Secrets\"])[1]")
	public static MobileElement iosBookName;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"iconsCoreRatingsSolid\"])[1]")
	public static MobileElement iosFavIcon;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvResultsHead\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"RECENTLY SEARCHED\"]")
	public static MobileElement iosRecentlySearchedText;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvTitle\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Harry Potter\"]")
	public static MobileElement iosRecentlySearchedTitles;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvRecommendationsHead\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Recommendations\"]]")
	public static MobileElement iosRecommendationsText;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvFavouritesHead\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"From Your Favorites\"]")
	public static MobileElement iosYourFavorite;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_navigation_icon")
	// @iOSXCUITFindBy(xpath="")
	public static MobileElement titleSearchPageBackbtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Search\"]")
	// @iOSXCUITFindBy(xpath="")
	public static MobileElement addTitleSearchHeader;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivBook")
	 @iOSXCUITFindBy(xpath="//XCUIElementTypeCollectionView/XCUIElementTypeCell")
	public static List<MobileElement> favTitleBooklist;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeCollectionView)[1]")
	public static MobileElement addedTitles;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='Add to Challenge']")
	public static MobileElement addToChallengeBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/book_image")
	// @iOSXCUITFindBy(xpath="")
	public static List<MobileElement> recommendTitleBooklist;

	public MobileElement getIosSearchTitle() {
		return searchTitle_Header;
	}

	public MobileElement getTitleSearchPageBackbtn() {
		return titleSearchPageBackbtn;
	}

	public MobileElement getAddTitleSearchHeader() {
		return addTitleSearchHeader;
	}

	public MobileElement getIosXIcon() {
		return iosXIcon;
	}

	public List<MobileElement> getMoreOptions() {
		return title_MoreOptions;
	}

	public MobileElement getIosMaterialType() {
		return iosMaterialType;
	}

	public MobileElement getIosauthorName() {
		return iosauthorName;
	}

	public MobileElement getIosBookName() {
		return iosBookName;
	}

	public MobileElement getIosFavIcon() {
		return iosFavIcon;
	}

	public MobileElement getIosRecentlySearchedText() {
		return iosRecentlySearchedText;
	}

	public MobileElement getIosRecentlySearchedTitles() {
		return iosRecentlySearchedTitles;
	}

	public MobileElement getIosRecommendationsText() {
		return iosRecommendationsText;
	}

	public MobileElement getIosYourFavorite() {
		return iosYourFavorite;
	}

}
