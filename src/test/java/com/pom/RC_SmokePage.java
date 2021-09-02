package com.pom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RC_SmokePage extends CapabilitiesAndWebDriverUtils {
	public static final Logger logger = LogManager.getLogger(RC_SmokePage.class);
	static ExcelReader reader = new ExcelReader();
	
	static CreateAChallengeCreatorRCDetailsScreen rcScreen = new CreateAChallengeCreatorRCDetailsScreen();
	static ReadingChallengeAcceptRejectChallenge acceptChallenge = new ReadingChallengeAcceptRejectChallenge();
	static Message_Center mc = new Message_Center();
	
	public static String actualchallenegeName;
	public static String actualchallenegeDesc;

	public RC_SmokePage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static void addCreateChallengeButton() throws InvalidFormatException, IOException {

		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Book Club");
		waitFor(10000);

		logger.info(getData("platformName") + " - User is in Home Page #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallange/HomePage.png");
		logger.info(" --" + getData("platformName") + " - Book Club Assertion Start -- ");
		ClickOnMobileElement(BookClubLandingScreen.bookClubOption);
		logger.info(getData("platformName") + " - Book Club menu is clicked #Pass");
		waitFor(5000);
		ClickOnMobileElement(BookClubLandingScreen.addCTA);
		waitFor(3000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallange/AddCTA.png");
		logger.info(getData("platformName") + " - Create New Challenge Button is Clicked #Pass");

	}

	public static String enterChallengeName() throws IOException {

		SendKeysOnMobileElement(CreateAChallengeBasicChallengeDetails.challengeName, challengeName());
		actualchallenegeName = CreateAChallengeBasicChallengeDetails.challengeName.getText();
		logger.info(getData("platformName") + " - Entered Challenge Name as :" + actualchallenegeName + "  #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallange/ChallengeNameEntered.png");
		return actualchallenegeName;
	}

	public static void enterChallengeDescName() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Create challenge");
		SendKeysOnMobileElement(CreateAChallengeBasicChallengeDetails.description,
				testData.get(0).get("challenge_Desc"));
		actualchallenegeDesc = CreateAChallengeBasicChallengeDetails.description.getText();
		logger.info(
				getData("platformName") + " - Entered Challenge Description  as :" + actualchallenegeDesc + "  #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallange/ChallengeDescEntered.png");
	}

	public static void searchAndAddFriend() throws InvalidFormatException, IOException {

		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "AddFriends");

		ClickOnMobileElement(CreateChallengeAddFriends.basicChallenge.addFriendCTA);
		logger.info(getData("platformName") + " - Add friend button clicked #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallange/AddFriendIcon.png");
		
		if (getData("platformName").equalsIgnoreCase("android") // newly Added
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			SendKeysOnMobileElement(CreateChallengeAddFriends.searchBox, testData.get(1).get("inp_friendName"));
			Screenshots.takeScreenshot(driver,
					"./Screenshots/" + getData("platformName") + "/CreateChallange/AddFriendInput.png");
			// CreateChallengeAddFriends.searchBox.sendKeys(Keys.ENTER);
		} else {
			SendKeysOnMobileElement(CreateChallengeAddFriends.searchBox, testData.get(0).get("inp_friendName"));
			Screenshots.takeScreenshot(driver,
					"./Screenshots/" + getData("platformName") + "/CreateChallange/AddFriendInput.png");
			CreateChallengeAddFriends.searchBox.sendKeys(Keys.ENTER);
		}
		
		waitFor(5000);
		List<MobileElement> userNamelist = CreateChallengeAddFriends.recentSearchfriendList;

		logger.info(getData("platformName") + " - Suggested Friend List Size : " + userNamelist.size() + " #Pass");
		waitFor(2000);
		int size = CreateChallengeAddFriends.inviteOptionList.size();
		System.out.println("Size :"+ size);
		//List<MobileElement> inviteOption = CreateChallengeAddFriends.inviteOptionList;
		ClickOnMobileElement(CreateChallengeAddFriends.inviteOptionList.get(0));
		waitFor(2000);
		ClickOnMobileElement(CreateChallengeAddFriends.addToChallenge);
		logger.info(getData("platformName") + " - Add to Challenge button is clicked in add friends  #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallange/AddFriendPage_FriendAdded.png");
		waitFor(2000);

	}

	public static void setReminder() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			CreateChallengeSetReminders.androidSetReminder(testData1.get(0).get("inp_reminder"));
			logger.info(getData("platformName") + " - Reminder is added successfully  #Pass");

		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {

			CreateChallengeSetReminders.iOSSetReminder();
			logger.info(getData("platformName") + " - Reminder is added successfully  #Pass");

		}

	}

	public static void setDate() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			CreateAChallengeSetReadbyDate.androidSetReadByDate();
			logger.info(getData("platformName") + " - Set Read by date is added successfully #Pass");

		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {

			CreateAChallengeSetReadbyDate.iOSSetReadByDate();
			logger.info(getData("platformName") + " - Set Read by date is added successfully #Pass");

		}
	}
	public static void searchAndAddTitle() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "FilterPage");
		waitFor(2000);
		swipeDown();
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.addTitlesCTA);
		waitFor(3000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CC_AddTitle.png");
		logger.info(getData("platformName") + " - Clicked on Add Title button  #Pass");
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,
				testData.get(2).get("Title_Keyword"));
		
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			waitFor(15000); // New
			ClickOnMobileElement(CreateChallengeAddTitles.keywordSuggestionList.get(0));
		} else {
			CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
		}
		
		waitFor(10000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_GridView.png");
		logger.info(getData("platformName") + " _ List view and Grid view is Working fine #Pass");
		ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(CreateChallengeAddTitles.fil_SearchType.getText(), testData1.get(0).get("lbl_Option1"));
			Assert.assertEquals(CreateChallengeAddTitles.filterPagedefVal_Option3.getText(), testData1.get(0).get("defVal_Option3"));
			ClickOnMobileElement(CreateChallengeAddTitles.filterPagePlusIcon.get(2));
			waitFor(1000);
			logger.info(getData("platformName") + " _ Format filter is clicked #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter.png");
			ClickOnMobileElement(CreateChallengeAddTitles.subfilterSelcetion.get(1));
			waitFor(1000);
			logger.info(getData("platformName") + " _ eBook is Selected #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_eBookSelected.png");
		}
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
			Assert.assertEquals(CreateChallengeAddTitles.fil_SearchType.getText(), testData1.get(0).get("lbl_Option1_iOS"));
			Assert.assertEquals(CreateChallengeAddTitles.filterPagedefVal_Option3.getText(), testData1.get(0).get("defVal_Option3_iOS"));
			ClickOnMobileElement(CreateChallengeAddTitles.filterPagePlusIcon.get(5));
			waitFor(2000);
			waitFor(1000);
			logger.info(getData("platformName") + " _ Format filter is clicked #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FormatFilter.png");
			ClickOnMobileElement(CreateChallengeAddTitles.subfilterSelcetion.get(1));
			waitFor(1000);
			logger.info(getData("platformName") + " _ eBook is Selected #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_eBookSelected.png");
		}
		waitFor(2000);
		ClickOnMobileElement(CreateChallengeAddTitles.filterApplyButton);
		waitFor(10000);
		List<MobileElement> moreOptions = CreateChallengeAddTitles.title_MoreOptions;
		System.out.println("Displayed More icon size " + ": " + CreateChallengeAddTitles.title_MoreOptions.size());

		ClickOnMobileElement(moreOptions.get(1));
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/TitleMore.png");
		waitFor(1000);
		ClickOnMobileElement(CreateChallengeAddTitles.addToChallengeButton);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddtoChallenge.png");

		waitFor(1000);
		
		ClickOnMobileElement(CreateChallengeAddTitles.filterIcon);
		ClickOnMobileElement(CreateChallengeAddTitles.filterResetAllButton);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddTitlePage_FilterReset.png");
		ClickOnMobileElement(CreateChallengeAddTitles.filterApplyButton);
		logger.info(getData("platformName") + " _ Filter is reset successfully #Pass");
		waitFor(1000);
		ClickOnMobileElement(CreateChallengeAddTitles.doneButton);
		waitFor(2000);
		swipeDown();
		logger.info(getData("platformName") + " _ Added to Challenge #Pass");
		
	}

	
	public static void startChallengeButton() throws IOException {
		
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
		waitFor(5000);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/CCDone.png");
		logger.info(getData("platformName") + " - Start Challenge is clicked  #Pass");
		waitFor(3000);
//		WaitForMobileElement(CreateAChallengeBasicChallengeDetails.title_CCDetails);
		logger.info(getData("platformName") + " - Create Challenge Deatils screen displayed #Pass");
		logger.info(" --" + getData("platformName") + " - Create Challenge Validation End -- ");
		waitFor(3000);
	}
	
	public static void saveChallenge() throws IOException, InterruptedException {

		Assert.assertTrue(CreateAChallengeBasicChallengeDetails.saveChallengeBtn.isEnabled());
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.saveChallengeBtn);
		logger.info(getData("platformName") + " _ Tap on Save Challenge #Pass");
		waitFor(15000);
		Assert.assertEquals(rcScreen.rcPageHeader_lbl.getText(), actualchallenegeName);
		logger.info(getData("platformName") + " _ User Navigates to Rc Page #Pass");
		ClickOnMobileElement(rcScreen.pageBackButton);
		logger.info(getData("platformName") + " _ Navigate Back to Listing page and Searching for saved challenge");
		waitFor(15000);

		for (int i = 0; i <=10; i++) {
		try {
			waitFor(15000);
			if (getData("platformName").equalsIgnoreCase("android")
					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				String challengeNameToTap = actualchallenegeName;
				MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/CreateChallenge/SavedChallenge.png");
				System.out.println(findElement.getText());
				ClickOnMobileElement(findElement);
				waitFor(5000);
				logger.info(getData("platformName") + " _ Saved challenge Displyed in Listing Page #Pass");
				break;
				
			} else {
				String challengeNameToTap = actualchallenegeName;
				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/CreateChallenge/SavedChallenge.png");
				logger.info(getData("platformName") + " _ Saved challenge Displyed in Listing Page #Pass");
			}
		} catch (Exception e) {
			logger.info(getData("platformName") + " _ Saved challenge not found in this attempt, Checking again"); //New
			waitFor(15000); //New
			for (int j = 0; j < 5; i++) {
				waitFor(2000);
				swipeUp();
			}
//			if (getData("platformName").equalsIgnoreCase("android")
//					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
//				
//				String challengeNameToTap = actualchallenegeName;
//				MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
//								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
//				Screenshots.takeScreenshot(driver,
//						"./Screenshots/" + getData("platformName") + "/CreateChallenge/SavedChallenge.png");
//				System.out.println(findElement.getText());
//				ClickOnMobileElement(findElement);
//				waitFor(5000);
//				logger.info(
//						getData("platformName") + " - Saved Challenge found on Listing Page in second attempt #Pass ");
//			} else {
//				String challengeNameToTap = actualchallenegeName;
//				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
//				Screenshots.takeScreenshot(driver,
//						"./Screenshots/" + getData("platformName") + "/CreateChallenge/SavedChallenge.png");
//				logger.info(
//						getData("platformName") + " - Saved Challenge found on Listing Page in second attempt #Pass ");
//			}

			}
		}
		logger.info(getData("platformName") + " - Entering into Create Challenge Page to Start Challenge");
		waitFor(5000); //new
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallenge/After_Save.png");
	}

	public static void editChallenge() throws InvalidFormatException, IOException {
		
		EditChallengeUpDateRcDetailsScreen.editReadingChalleng();
	}
	
	public static void challengeDisplayCheck() throws InvalidFormatException, IOException, InterruptedException {
		
		for (int i = 0; i <10; i++) {
		try {
			waitFor(15000);
			if (getData("platformName").equalsIgnoreCase("android")
					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				waitFor(5000); // new
				String challengeNameToTap = actualchallenegeName;
				MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/CreateChallenge/edited_Challenge.png");
				System.out.println(findElement.getText());
				waitFor(5000);
				logger.info(getData("platformName") + " _ Edited challenge Displyed in Listing Page #Pass");
				break;
			} else {
				String challengeNameToTap = actualchallenegeName;
				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/CreateChallenge/edited_Challenge.png");
				logger.info(getData("platformName") + " _ Edited challenge Displyed in Listing Page #Pass");
			}
		} catch (Exception e) {
			logger.info(getData("platformName") + " _ Edited challenge Not found in this attempt, Checking again"); // new
			waitFor(5000); //new
			for (int j = 0; j < 5; i++) {
				waitFor(2000);
				swipeUp();
			}
//			if (getData("platformName").equalsIgnoreCase("android")
//					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
//
//				String challengeNameToTap = actualchallenegeName;
//				MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
//								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
//				Screenshots.takeScreenshot(driver,
//						"./Screenshots/" + getData("platformName") + "/CreateChallenge/Edited_Challenge.png");
//				System.out.println(findElement.getText());
//				logger.info(
//						getData("platformName") + " - Edited Challenge found on Listing Page in second attempt #Pass ");
//			} else {
//				String challengeNameToTap = actualchallenegeName;
//				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
//				Screenshots.takeScreenshot(driver,
//						"./Screenshots/" + getData("platformName") + "/CreateChallenge/Edited_Challenge.png");
//				logger.info(
//						getData("platformName") + " - Edited Challenge found on Listing Page in second attempt #Pass ");
			}

		}
		logger.info(getData("platformName") + " - Challenge displayed in dashboard after edit #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallenge/After_edit.png");
		
	}
	
	public static void acceptChallenge() throws IOException, InterruptedException {
		logger.info(getData("platformName")+" - User is Navigating to Home page #Pass");
		waitFor(2000);
		ClickOnMobileElement(BookClubLandingScreen.bookClubOption);
		waitFor(8000);
		logger.info(getData("platformName")+" -- Searching For New Challenge to accept --");
		for (int i = 0; i <10; i++) {
		try {
			waitFor(15000);
			if (getData("platformName").equalsIgnoreCase("android")
					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				System.out.println(actualchallenegeName);
				String challengeNameToTap = actualchallenegeName;
				MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
								+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
				System.out.println(findElement.getText());
				ClickOnMobileElement(findElement);
				logger.info(getData("platformName")+" -New Challenge found #Pass ");
				Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/Acceptpage.png");
				break;
			}
			else {
				String challengeNameToTap = actualchallenegeName;
				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
				logger.info(getData("platformName")+" -New Challenge found #Pass ");
				Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/Acceptpage.png");
				
			}
		}
		
		catch(Exception e){
			
			waitFor(15000);
			logger.info(getData("platformName")+" -New Challenge Not found checking again ");
			for (int j = 0; j < 5; i++) {
				swipeUp();
			}
//			if (getData("platformName").equalsIgnoreCase("android")
//					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
//				waitFor(10000);
//			String challengeNameToTap = actualchallenegeName;
//			MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
//							+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
//			System.out.println(findElement.getText());
//			ClickOnMobileElement(findElement);
//			logger.info(getData("platformName")+" -New Challenge found on second attempt #Pass ");	
//			}
//			else {
//				String challengeNameToTap = actualchallenegeName;
//				scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
//				logger.info(getData("platformName")+" -New Challenge found #Pass ");
//				Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/Acceptpage.png");
//				
//			}

			}
		}
		waitFor(10000);
		Assert.assertTrue(acceptChallenge.challengeicon.isDisplayed());
		Assert.assertTrue(acceptChallenge.chName.isDisplayed());
		Assert.assertTrue(acceptChallenge.chDesc.isDisplayed());
		//Assert.assertTrue(acceptChallenge.creatorName.isDisplayed());
		Assert.assertTrue(acceptChallenge.readByDateText.isDisplayed());
		for (int i = 0; i<acceptChallenge.participantsList.size(); i++) {
			Assert.assertTrue(acceptChallenge.participantsList.get(i).isDisplayed());
		}
		for (int i = 0; i<acceptChallenge.titleReadingChallenge.size(); i++) {
			Assert.assertTrue(acceptChallenge.titleReadingChallenge.get(i).isDisplayed());
		}
		Assert.assertTrue(acceptChallenge.noThanksButton.isDisplayed());
		ClickOnMobileElement(acceptChallenge.accpetChallegeButton);
		waitFor(5000);
		logger.info(getData("platformName")+" - Chalenge Accepted sucessfully #Pass ");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/AfterAccepting.png");
		waitFor(10000);
		Assert.assertTrue(acceptChallenge.cnfPage_AcceptText.isDisplayed());
		logger.info(getData("platformName")+" - user naviagtes to Challenge confirmation page #Pass ");
		logger.info(getData("platformName")+" - Challenge Accepted text is displayed #Pass ");
		Assert.assertTrue(acceptChallenge.cnfPage_UserInfo.isDisplayed());
		logger.info(getData("platformName")+" - User info text is displayed in confirmation page #Pass ");
		Assert.assertTrue(acceptChallenge.cnfPage_GoToChallenge.isDisplayed());
		logger.info(getData("platformName")+" - Go to Challenge button is displayed in confirmation page #Pass ");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/AcceptChallenge/CnfPage.png");
		ClickOnMobileElement(acceptChallenge.cnfPage_GoToChallenge);
		waitFor(5000);
	}
	
	public static void rcPageValiation() throws IOException, InvalidFormatException {

		logger.info(" --"+ getData("platformName")+" -- Participant RC details Page Validation Start -- ");
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "RC Details Screen");
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
		List<Map<String, String>> testData2 = reader.getData("./Data/MobileData.xlsx", "Create challenge");
		logger.info(getData("platformName")+" - created Challenge Name : " + actualchallenegeName);
		waitFor(3000);
		Assert.assertEquals(rcScreen.rcPageHeader_lbl.getText(),actualchallenegeName);
		logger.info(getData("platformName")+" - Challenge Name is displayed as RC details page Header  #Pass");
		waitFor(3000);
		Assert.assertTrue(rcScreen.moreIcon.isDisplayed());
	//	Assert.assertEquals(rcScreen.creatorAvatar.isDisplayed(),true);
		logger.info( getData("platformName")+ " - Challenge Created By : "+ rcScreen.creatorName.getText());
	//	Assert.assertEquals(rcScreen.creatorName.getText(), testData.get(0).get("defVal_Creatorname"));
		Assert.assertEquals(rcScreen.createDate.isDisplayed(),true);
		logger.info( getData("platformName")+ " - Challenge Created Date : "+ rcScreen.createDate.getText());
//		logger.info(getData("platformName")+ " - User Selected Reminder : " + rcScreen.reminderValue.getText());
		Assert.assertTrue(rcScreen.readingChallengeIcon.isDisplayed());
		Assert.assertTrue(rcScreen.readingChallenge_lbl.isDisplayed());
		Assert.assertEquals(rcScreen.challengeDesc.getText(), testData2.get(0).get("Edit_Desc"));
		logger.info( getData("platformName")+ " - CreatorName,CreatorAvatar,CreatedDate,Ch_Name,Ch_Description,ReadyBydate,Reminder Assertion #Pass");	
        Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/RCDetailPage/RC Page.png");
//        Assert.assertEquals(rcScreen.readingList_lbl.getText(), testData.get(0).get("lbl_ReadingList"));
        if(rcScreen.challengeTitleList.size()!=0) {     	        	
    		logger.info(" --"+ getData("platformName")+ " - Challenge Titles are displayed #Pass");	        	
        }
        else {
        	Assert.assertTrue(false);
        	logger.info(" --"+ getData("platformName")+ " - Challenge Titles are displayed #Fail");
        }       
        swipeDown();
//       Assert.assertEquals(rcScreen.participants_lbl.getText(), testData.get(0).get("lbl_Participants"));
        List<String> participantsData = new ArrayList<String>();
        if(rcScreen.partipantsAvatar.size()!=0) {
        	for (int i = 0; i<= rcScreen.partipantsAvatar.size()-1; i++) {
    			Assert.assertTrue(rcScreen.partipantsAvatar.get(i).isDisplayed());
    			Assert.assertTrue(rcScreen.participantsList.get(i).isDisplayed());
    			Assert.assertTrue(rcScreen.participantProgresspercent.get(i).isDisplayed());
//    			horizontalSwipe(rcScreen.participantsList);
    			participantsData.add(rcScreen.participantsList.get(i).getText());
        		}
    		logger.info(" --"+ getData("platformName")+ "Challenge participants are displayed #Pass");	        	
        }
        else {
        	logger.info(" --"+ getData("platformName")+ "Challenge participants are displayed #Fail");
        	Assert.assertTrue(false);
        }
        Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/RCDetailPage/RC Participants.png");

        ClickOnMobileElement(rcScreen.moreIcon);
        waitFor(2000);
        Assert.assertTrue(rcScreen.leaveChallengeActionBtn.isDisplayed());
//        Assert.assertEquals(rcScreen.leaveChallengeActionBtn.getText(),testData.get(0).get("leave ChallengeBtn_lbl"));
        Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/RCDetailPage/RC_page_More_actionbtns .png");
    	logger.info(" --"+ getData("platformName")+ "RC page More action buttons Validation #Pass");
        ClickOnMobileElement(rcScreen.cancelChallengeActionBtn);
    	Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/RCDetailPage/NavBack_RCpage .png");
    	logger.info(" --"+ getData("platformName")+ "Navgiating Back to RC page after taping cancel button #Pass");
    	ClickOnMobileElement(rcScreen.pageBackButton);
    	waitFor(5000);
    	logger.info(getData("platformName")+" --- RC details Screen Validation End --- ");
	}
	
	public static void rcInviteValidation() throws InvalidFormatException, IOException {

		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		ClickOnMobileElement(mc.messageCenterIcon);
		logger.info(getData("platformName") + " - User is on Message center Landing Page #pass");
		waitFor(10000);
		try {
			for (int i = 0; i<=mc.messageHeader_Description.size()-1; i++) {
				if (mc.messageHeader_Description.get(i).getText().equals(testData.get(1).get("messageHeaderDesc_val"))) {
					logger.info(getData("platformName") + " - User should get rc invite #pass");	
					break;
				}	
			}
			//logger.info(getData("platformName") + " - Last invite came on "+ Message_Center.msgRecievedTime.get(0).getText() + "#pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/CreateChallenge/Message_Center.png");
		} catch (Exception e) {
			logger.info(getData("platformName") + " - User should get rc invite #Fail");
			Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/CreateChallenge/Message_Center.png");
		}
	}

	public static void msgDetailsScreen() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");

		for (int i = 0; i <=mc.messageHeader_Description.size()-1; i++) {
			if (mc.messageHeader_Description.get(i).getText().equals(testData.get(1).get("messageHeaderDesc_val"))) {
				ClickOnMobileElement(mc.messageHeader_Description.get(i));
			}	
		}
		waitFor(5000);
		Assert.assertTrue(mc.msgDetailsPage_chName.isDisplayed());
		logger.info(getData("platformName") + " - User Navigates to Message details Screen #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/CreateChallenge/Message_Details_Page.png");
	}

}

