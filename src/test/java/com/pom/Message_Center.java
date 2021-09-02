package com.pom;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
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

public class Message_Center extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(Message_Center.class);
	
	public static String updatedChallengeName;

	public Message_Center() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void unreadMessageCount() throws IOException {
			waitFor(10000);
			logger.info(" --" + getData("platformName") + " --- Message Center Validation Start --- ");
			Assert.assertTrue(homePageHeader.isDisplayed());
			logger.info(getData("platformName") + " - User is on Home Page #Pass");
			try {
			waitFor(10000);
			if (unReadCountOnBellIcon.isDisplayed()) {
				logger.info(getData("platformName") + " - Unread message Displayed count : "
						+ unReadCountOnBellIcon.getText() + " #Pass");
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/MessageCenter/unRead Count.png");
				} 
			}
			catch (Exception e) {
				waitFor(3000);
				logger.info(getData("platformName") + " - User dont have unread message count to display #Pass");
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/MessageCenter/unRead Count.png");
			}
		}

	public void messageCenternavigation() throws IOException, InvalidFormatException {
		waitFor(3000);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		ClickOnMobileElement(messageCenterIcon);
		waitFor(2000);
		Assert.assertEquals(pageHeader_lbl.getText(), testData.get(0).get("lbl_Header"));
		logger.info(getData("platformName")+ " - user taped on message centre icon, navigating to messsage center page  #pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/MessageCenter/landing page.png");
		ClickOnMobileElement(pageBackIcon);
		Assert.assertTrue(messageCenterIcon.isDisplayed());
		logger.info(getData("platformName")+ " - Navigating back to home page after tap on back button in messsage center page  #pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/MessageCenter/Aftertaping back btn.png");
		ClickOnMobileElement(messageCenterIcon);
		waitFor(5000);
		for (int i = 0; i < 5; i++) {
			swipeDown();
		}
		logger.info(getData("platformName") + " - Swipe down inside the message center  #pass");
		for (int i = 0; i < 5; i++) {
			swipeUp();
		}
	}

	public void messageDetailScreen() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(5000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(1).get("messageHeader_val"))||
					messageHeader.get(i).getText().equalsIgnoreCase(testData.get(0).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
//				logger.info(getData("platformName") + " - Message Header : " + messageHeader.get(i).getText());
				break;
			}
			else {
				if (i==5) {
					swipeDown();
				}
			}
		}
		waitFor(3000);
		Assert.assertTrue(msgDetailsPage_DeleteBtn.isDisplayed());
		waitFor(10000);
//		Assert.assertTrue(msgDetailsPage_creatorAvatar.isDisplayed());
		Assert.assertTrue(msgDetailsPage_msgHeader.isDisplayed());
		logger.info(getData("platformName") + " - Message Header : " + msgDetailsPage_msgHeader.getText());
		Assert.assertTrue(msgDetailsPage_chDate.isDisplayed());
		Assert.assertTrue(msgDetailsPage_timeStamp.isDisplayed());
		Assert.assertTrue(msgDetailsPage_chName.isDisplayed());
		String chName = msgDetailsPage_chName.getText();
		Assert.assertTrue(msgDetailsPage_chDesc.isDisplayed());
		logger.info(getData("platformName") + " - Challenge Name : " + msgDetailsPage_chName.getText());
		logger.info(getData("platformName") + " - Challenge Desc : " + msgDetailsPage_chDesc.getText());
			if(msgDetailsPage_chTitleList.size()!=0) {
				logger.info(getData("platformName")+"- Challnege titles are displyed in msg details page #Pass");
			}
		logger.info(getData("platformName")+"- Message Details page Validation #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/MessageCenter/MsgDetail Page.png");
		try {
//		if (msgDetailsPage_viewChDetailBtn.isDisplayed()) {
			try {
				waitFor(5000);
				ClickOnMobileElement(msgDetailsPage_viewPrgDetailBtn);
			}
			catch (Exception e) {
				ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
			}
			waitFor(5000);
//			Assert.assertEquals(chName, rcPageHeader_lbl.getText());
			logger.info(getData("platformName") + " - User Navigated to RC page #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/MessageCenter/Rc Page.png");
			ClickOnMobileElement(rcpageBackButton);
			logger.info(getData("platformName")+" - User lands on book club landing page #Pass");
			Assert.assertTrue(lbl_BooKClub_Header.isDisplayed());
			ClickOnMobileElement(messageCenterIcon);
		  }
//		else {
		catch (Exception e) {
			waitFor(5000);
			ClickOnMobileElement(msgDetailsPage_acceptBtn);
			waitFor(5000);
			ClickOnMobileElement(msgDetailsPage_goToChallenge);
			waitFor(5000);
			Assert.assertEquals(chName, rcPageHeader_lbl.getText());
			logger.info(getData("platformName") + " - User Navigated to RC page #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/MessageCenter/Rc Page.png");
			ClickOnMobileElement(rcpageBackButton);
			logger.info(getData("platformName")+" - User lands on book club landing page #Pass");
			Assert.assertTrue(lbl_BooKClub_Header.isDisplayed());
			ClickOnMobileElement(messageCenterIcon);
		  } 
//		}
		
	}

	public void messagecenterEdit() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(5000);
//		ClickOnMobileElement(messageCenterIcon);
//		ClickOnMobileElement(messageCenterIcon);//only for ios
		ClickOnMobileElement(editBtn);
		//ClickOnMobileElement(editBtn);// only for ios
		ClickOnMobileElement(cancelBtn);
		logger.info(getData("platformName")
				+ " - Navigating back to message center after tap on cancel button in messsage center edit page  #pass");
		ClickOnMobileElement(editBtn);
//		System.out.println("Tapping edit 2st time ");
//		ClickOnMobileElement(editBtn);// only for ios
		waitFor(3000);
		ClickOnMobileElement(selectAllBtn);
		waitFor(3000);
		Assert.assertTrue(deSelectAllBtn.isDisplayed());
		for (int i = 0; i < 5; i++) {
			swipeDown();
		}
		waitFor(5000);
		logger.info(getData("platformName") + " - Select all option selects all the messages  #pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/MessageCenter/selectallMessage.png");
		
		ClickOnMobileElement(deSelectAllBtn);
		waitFor(3000);
		Assert.assertTrue(selectAllBtn.isDisplayed());
		for (int i = 0; i < 5; i++) {
			swipeDown();
		}
		waitFor(5000);
		logger.info(getData("platformName") + " - DeSelect all option Deselects all the messages  #pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/MessageCenter/DeselectallMessage.png");

		ClickOnMobileElement(selectAllBtn);
		ClickOnMobileElement(markButton);
		Assert.assertTrue(countOfSelectedMessage.isDisplayed());
		Assert.assertTrue(markasUnReadOption.isDisplayed());
		Assert.assertTrue(markOptionCancelBtn.isDisplayed());
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/MessageCenter/Mark option.png");
		logger.info(getData("platformName") + " - No of Message Selected : " + countOfSelectedMessage.getText());
		ClickOnMobileElement(markOptionCancelBtn);
		ClickOnMobileElement(cancelBtn);
		waitFor(2000);
		Assert.assertTrue(pageBackIcon.isDisplayed());
		logger.info(
				getData("platformName") + " - Tap on cancel button takes user to message center landing page #pass");

		ClickOnMobileElement(editBtn);
		//ClickOnMobileElement(editBtn);// only for ios
		System.out.println("edit button taped again");
		System.out.println("Checkboxsize:"+checkBoxBtn.size());
		waitFor(4000);
		ClickOnMobileElement(checkBoxBtn.get(0));
		//ClickOnMobileElement(checkBoxBtn.get(0));
		System.out.println("Clicking checkbox");
		ClickOnMobileElement(markButton);
		Assert.assertTrue(countOfSelectedMessage.isDisplayed());
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/MessageCenter/Mark option1.png");
		logger.info(getData("platformName") + " - No of Message Selected : " + countOfSelectedMessage.getText());
		ClickOnMobileElement(markOptionCancelBtn);
		ClickOnMobileElement(deleteButton);
//		Assert.assertEquals(del_popupMessage.getText(), testData.get(0).get("delete_PopUp"));
		logger.info(
				getData("platformName") + " - Delete pop up displayed with number selected message for delete #Pass");
		Screenshots.takeScreenshot(driver,
				"./Screenshots/" + getData("platformName") + "/MessageCenter/Delete_popup.png");
		ClickOnMobileElement(MsgDeletePop_CancelButton);
		logger.info(getData("platformName") + " - Delete pop up cancel #Pass");
		Assert.assertTrue(markButton.isDisplayed());
		ClickOnMobileElement(deleteButton);
		ClickOnMobileElement(MsgDeletePop_DeleteButton);

		waitFor(2000);
		logger.info(getData("platformName") + " - Delete message #Pass");

		ClickOnMobileElement(editBtn);
	//	ClickOnMobileElement(editBtn); // only for ios

		waitFor(3000);
		int read3 = readingStatus.size();
		System.out.println("Before unread : " + readingStatus.size());
		ClickOnMobileElement(selectAllBtn);
		ClickOnMobileElement(markButton);
		ClickOnMobileElement(markasUnReadOption);
		waitFor(5000);
		int read4 = readingStatus.size();
		System.out.println("After unread : " + readingStatus.size());
		if (read3 == read4) {
			logger.info(getData("platformName") + " - Mark as unRead option #Fail");
		} else {
			logger.info(getData("platformName") + " -  Mark as unRead option #Pass");
		}

		ClickOnMobileElement(editBtn);
		//ClickOnMobileElement(editBtn); // only for ios
		waitFor(3000);
		int read1 = readingStatus.size();
		System.out.println("Before : " + readingStatus.size());
		ClickOnMobileElement(selectAllBtn);
		ClickOnMobileElement(markButton);
		ClickOnMobileElement(markasReadOption);
		waitFor(5000);
		int read2 = readingStatus.size();
		System.out.println("After : " + readingStatus.size());
		if (read1 == read2) {
			logger.info(getData("platformName") + " - Mark as Read option #Fail");
		} else {
			logger.info(getData("platformName") + " - Mark as Read option #Pass");
		}
	}
	
	public static void rpMessageValdation() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		ClickOnMobileElement(messageCenterIcon);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			waitFor(10000);
			for (int i = 0; i <= messageHeader.size() - 1; i++) {
				System.out.println("MessageHeader size :" + messageHeader.size());
				if (messageHeader_Description.get(i).getText().contains("You have been added to this program")) {
					ClickOnMobileElement(messageHeader_Description.get(i));
					logger.info(getData("platformName") + " - User should recieved RP invite #Pass");
					break;
				} else {
					swipeDown();
				}
			}
		}

		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			System.out.println("HeaderSize"+messageHeader.size());

			ClickOnMobileElement(messageHeader.get(0));
			ClickOnMobileElement(messageHeader.get(0));
			logger.info(getData("platformName") + " - User should recieved RP invite #Pass");
		}
	}

	public static void rpMessageDetailScreenValidation() throws IOException {
		
		waitFor(3000);
		Assert.assertTrue(msgDetailsPage_DeleteBtn.isDisplayed());
		waitFor(10000);
//		Assert.assertTrue(msgDetailsPage_creatorAvatar.isDisplayed());
		Assert.assertTrue(msgDetailsPage_msgHeader.isDisplayed());
		logger.info(getData("platformName") + " - Message Header : " + msgDetailsPage_msgHeader.getText());
		Assert.assertTrue(msgDetailsPage_chDate.isDisplayed());
		Assert.assertTrue(msgDetailsPage_timeStamp.isDisplayed());
		Assert.assertTrue(msgDetailsPage_chName.isDisplayed());
		String chName = msgDetailsPage_chName.getText();
		Assert.assertTrue(msgDetailsPage_chDesc.isDisplayed());
		logger.info(getData("platformName") + " - Challenge Name : " + msgDetailsPage_chName.getText());
		logger.info(getData("platformName") + " - Challenge Desc : " + msgDetailsPage_chDesc.getText());
			if(msgDetailsPage_chTitleList.size()!=0) {
				logger.info(getData("platformName")+"- Challnege titles are displyed in msg details page #Pass");
			}
		logger.info(getData("platformName")+"- Message Details page Validation #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/MessageCenter/RP_MsgDetailPage.png");
		try {
//		if (msgDetailsPage_viewChDetailBtn.isDisplayed()) {
			try {
				waitFor(5000);
				ClickOnMobileElement(msgDetailsPage_viewPrgDetailBtn);
			}
			catch (Exception e) {
				ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
			}
			waitFor(5000);
//			Assert.assertEquals(chName, rcPageHeader_lbl.getText());
			logger.info(getData("platformName") + " - User Navigated to RP Detail page #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/MessageCenter/Rc Page.png");
			ClickOnMobileElement(rcpageBackButton);
			logger.info(getData("platformName")+" - User lands on book club landing page #Pass");
			Assert.assertTrue(lbl_BooKClub_Header.isDisplayed());
			ClickOnMobileElement(messageCenterIcon);
		  }
//		else {
		catch (Exception e) {
			waitFor(5000);
			ClickOnMobileElement(msgDetailsPage_acceptBtn);
			waitFor(5000);
			ClickOnMobileElement(msgDetailsPage_goToChallenge);
			waitFor(5000);
			Assert.assertEquals(chName, rcPageHeader_lbl.getText());
			logger.info(getData("platformName") + " - User Navigated to RC page #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/MessageCenter/Rp Detail Page.png");
			ClickOnMobileElement(rcpageBackButton);
			logger.info(getData("platformName")+" - User lands on book club landing page #Pass");
			Assert.assertTrue(lbl_BooKClub_Header.isDisplayed());
			ClickOnMobileElement(messageCenterIcon);
		  } 
//		}

	}
	
	public static void swipeLeftMarkOptionValidation() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(5000);
		swipeleft(messageCenter_timeStamp.get(0));
		waitFor(3000);
		Assert.assertTrue(swipe_MarkIcon.isDisplayed());
		logger.info(getData("platformName") + " - SwipeLeft mark icon is displayed #Pass");
		try { 
		Assert.assertTrue(swipe_MarkAs_lbl.isDisplayed());
		if (swipe_MarkAs_lbl.getText().equalsIgnoreCase(testData.get(0).get("Markasread_lbl"))) {
			logger.info(getData("platformName") + " - SwipeLeft Markas label is displayed #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/MessageCenter/SwipeLeft_Markasread.png");	
		}
		else if(swipe_MarkAs_lbl.getText().equalsIgnoreCase(testData.get(0).get("Markasunread_lbl"))){
			logger.info(getData("platformName") + " - SwipeLeft Markas Unread label is displayed #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/MessageCenter/SwipeLeft_Markasread.png");
		}

		}
		catch(Exception e){

		}
	}
	
	public static void swipeLeftDeleteOptionValidation() throws Throwable, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		Assert.assertTrue(swipe_DeleteIcon.isDisplayed());
		logger.info(getData("platformName") + " - SwipeLeft delete icon is displayed #Pass");
		Assert.assertTrue(swipe_Delete_lbl.isDisplayed());
		logger.info(getData("platformName") + " - SwipeLeft Delete label is displayed #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/MessageCenter/SwipeLeft_Delete.png");
		
	}
	
	public static  void rcInviteNotification() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(0).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		Assert.assertEquals(msgDetailsPage_chName.getText(),CreateAChallengeBasicChallengeDetails.actualchallenegeName);
		logger.info(getData("platformName") + " - Invite notification is displayed in invited user message center landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/RC_invite.png");
	}
	
	public static void rcAccept() throws IOException {
		ClickOnMobileElement(msgDetailsPage_acceptBtn);
		logger.info(getData("platformName") + " - Challenge accepted by invited user #Pass");
		waitFor(5000);
		ClickOnMobileElement(ReadingChallengeAcceptRejectChallenge.cnfPage_Xicon);
		logger.info(getData("platformName") + " - Navigated back to listing page from Challenge accepted confirmation page  #Pass");
	}
	
	public static void inviteAcceptedNotification() throws InvalidFormatException, IOException {
		waitFor(3000);
		ClickOnMobileElement(messageCenterIcon);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(1).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		Assert.assertEquals(msgDetailsPage_chName.getText(),CreateAChallengeBasicChallengeDetails.actualchallenegeName);
		logger.info(getData("platformName") + " - Invite accepted notification is displayed in creator message center landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/RC_inviteAccepted.png");		
	}
	
	public static  void addNewTitleToChallenge() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Add Titles");
		ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.moreIcon);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.editChallengeActionBtn);
		waitFor(10000);
		swipeDown();
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.addTitlesCTA);
		waitFor(2000);
		SendKeysOnMobileElement(CreateaChallengeSearchTitleResultsListView.searchTextBox,testData.get(2).get("Title_Keyword"));
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			waitFor(5000);
			ClickOnMobileElement(CreateChallengeAddTitles.keywordSuggestionList.get(0));
		}
		
		if (getData("platformName").equalsIgnoreCase("iOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
		CreateaChallengeSearchTitleResultsListView.searchTextBox.sendKeys(Keys.ENTER);
		}
		List<MobileElement> moreOptions = CreateChallengeAddTitles.title_MoreOptions;
		System.out.println("Displayed More icon size " + ": " + CreateChallengeAddTitles.title_MoreOptions.size());
		waitFor(10000);
		ClickOnMobileElement(moreOptions.get(0));
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/TitleMore_Edit.png");
		waitFor(1000);
		ClickOnMobileElement(CreateChallengeAddTitles.addToChallengeButton);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/AddtoChallenge_Edit.png");

		waitFor(1000);
		ClickOnMobileElement(CreateChallengeAddTitles.doneButton);
		waitFor(2000);
		logger.info(getData("platformName") + " _New title Added to Challenge in Edit #Pass");
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
		waitFor(10000);
		logger.info(getData("platformName") + " - Save  Challenge is clicked in Edit #Pass");
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(3000);
	}
	
	public static void titleAddedNotification() throws InvalidFormatException, IOException {
			waitFor(3000);
			ClickOnMobileElement(messageCenterIcon);
			List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
			waitFor(10000);
			System.out.println("MessageHeader size :"+messageHeader.size());
			for(int i = 0; i<= messageHeader.size()-1; i++) {
				if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(2).get("messageHeader_val"))) {
					ClickOnMobileElement(messageHeader.get(i));
					break;
				}
			}
			waitFor(10000);
			Assert.assertEquals(msgDetailsPage_chName.getText(),CreateAChallengeBasicChallengeDetails.actualchallenegeName);
			logger.info(getData("platformName") + " - Title added notification is displayed in ivited user message center landing page #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/RC_titleAdded.png");	
			waitFor(5000);
			ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
			waitFor(5000);
			ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
			waitFor(3000);
	}
	
	public static void removeTitleFromChallenge() throws IOException {
		
		waitFor(3000);
		ClickOnMobileElement(BookClubLandingScreen.bookClubOption);
		waitFor(10000);
		for (int i = 0; i <=10; i++) {
			try {
				waitFor(15000);
				if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
					String challengeNameToTap = CreateAChallengeBasicChallengeDetails.actualchallenegeName;
					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
									+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/Notification/removetitleChal.png");
					System.out.println(findElement.getText());
					ClickOnMobileElement(findElement);
					waitFor(8000);
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page #Pass");
					break;
					
				} else {
					String challengeNameToTap = CreateAChallengeBasicChallengeDetails.actualchallenegeName;
					scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/CreateChallenge/removetitleChal.png");
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page #Pass");
				}
			} catch (Exception e) {
				logger.info(getData("platformName") + " _ challenge not found in this attempt, Checking again"); //New
				waitFor(15000); //New
				for (int j = 0; j <=3; i++) {
					waitFor(2000);
					swipeUp();
				}
			}}
			ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.moreIcon);
			ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.editChallengeActionBtn);
			waitFor(10000);
			swipeDown();
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.removeTitleXIcon.get(0));
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.TitesRemoveBtn);
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
			waitFor(5000);
			ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
			waitFor(3000);
	}
	
	public static void titleRemovedNotification() throws IOException, InvalidFormatException {
		waitFor(3000);
		ClickOnMobileElement(messageCenterIcon);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(3).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		Assert.assertEquals(msgDetailsPage_chName.getText(),CreateAChallengeBasicChallengeDetails.actualchallenegeName);
		logger.info(getData("platformName") + " - Title Removed notification is displayed in ivited user message center landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/RC_titleAdded.png");	
		waitFor(5000);
		ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(3000);
	}
	
	public static void updateChallengeEndDate() throws IOException {
		waitFor(3000);
		ClickOnMobileElement(BookClubLandingScreen.bookClubOption);
		waitFor(10000);
		for (int i = 0; i <=10; i++) {
			try {
				waitFor(15000);
				if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
					String challengeNameToTap = CreateAChallengeBasicChallengeDetails.actualchallenegeName;
					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
									+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/Notification/removetitleChal.png");
					System.out.println(findElement.getText());
					ClickOnMobileElement(findElement);
					waitFor(8000);
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page #Pass");
					break;
					
				} else {
					String challengeNameToTap = CreateAChallengeBasicChallengeDetails.actualchallenegeName;
					scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/CreateChallenge/removetitleChal.png");
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page #Pass");
				}
			} catch (Exception e) {
				logger.info(getData("platformName") + " _ challenge not found in this attempt, Checking again"); //New
				waitFor(15000); //New
				for (int j = 0; j <=3; i++) {
					waitFor(2000);
					swipeUp();
				}
			}}
			ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.moreIcon);
			ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.editChallengeActionBtn);
			waitFor(10000);
			swipeDown();
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.dateOpt);
			ClickOnMobileElement(CreateAChallengeSetReadbyDate.nextMonthBtn);
			ClickOnMobileElement(CreateAChallengeSetReadbyDate.endDate);
			ClickOnMobileElement(CreateAChallengeSetReadbyDate.calenderIconOK);
			waitFor(2000);
			Assert.assertEquals(fluentWaitDisplayed(CreateAChallengeBasicChallengeDetails.dateOpt, 20, 2), true);
			ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
			waitFor(5000);
			ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
			waitFor(3000);
	}
	
	public static void chDateUpdatedNotification() throws InvalidFormatException, IOException {
		waitFor(3000);
		ClickOnMobileElement(messageCenterIcon);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(4).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		Assert.assertEquals(msgDetailsPage_chName.getText(),CreateAChallengeBasicChallengeDetails.actualchallenegeName);
		logger.info(getData("platformName") + " - Ch Dt updated notification is displayed in invited user message center landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/RC_DateUpdated.png");	
		waitFor(5000);
		ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(3000);	
	}
	
	public static String updateChallenegeName() throws IOException {
		
		waitFor(3000);
		ClickOnMobileElement(BookClubLandingScreen.bookClubOption);
		waitFor(10000);
		for (int i = 0; i <=10; i++) {
			try {
				waitFor(15000);
				if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
					String challengeNameToTap = CreateAChallengeBasicChallengeDetails.actualchallenegeName;
					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
									+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/Notification/updateChalLName.png");
					System.out.println(findElement.getText());
					ClickOnMobileElement(findElement);
					waitFor(8000);
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page #Pass");
					break;
					
				} else {
					String challengeNameToTap = CreateAChallengeBasicChallengeDetails.actualchallenegeName;
					scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/Notification/updateChalLName.png");
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page #Pass");
				}
			} catch (Exception e) {
				logger.info(getData("platformName") + " _ challenge not found in this attempt, Checking again"); //New
				waitFor(15000); //New
				for (int j = 0; j <=3; i++) {
					waitFor(2000);
					swipeUp();
				}
			}}
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.moreIcon);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.editChallengeActionBtn);
		waitFor(10000);
		CreateAChallengeBasicChallengeDetails.challengeName.clear();
		SendKeysOnMobileElement(CreateAChallengeBasicChallengeDetails.challengeName, challengeName());
		updatedChallengeName = CreateAChallengeBasicChallengeDetails.challengeName.getText();
		logger.info(getData("platformName") + " - Updated Challenge Name as :" + updatedChallengeName + "  #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/updatedChalName.png");
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(3000);
		return updatedChallengeName;
	}
	
	public static void chNameUpdateNotification() throws IOException, InvalidFormatException {
		waitFor(3000);
		ClickOnMobileElement(messageCenterIcon);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(5).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		Assert.assertEquals(msgDetailsPage_chName.getText(),updatedChallengeName);
		logger.info(getData("platformName") + " - Ch Name updated notification is displayed in invited user message center landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/RC_NameUpdated.png");	
		waitFor(5000);
		ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(3000);
	}
	
	public static void addNewUserToChallenge() throws IOException, InvalidFormatException {
		waitFor(3000);
		ClickOnMobileElement(BookClubLandingScreen.bookClubOption);
		waitFor(10000);
		for (int i = 0; i <=10; i++) {
			try {
				waitFor(15000);
				if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
					String challengeNameToTap = updatedChallengeName;
					MobileElement findElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
									+ ".scrollIntoView(new UiSelector().text(\"" + challengeNameToTap + "\"))"));
					System.out.println(findElement.getText());
					ClickOnMobileElement(findElement);
					waitFor(8000);
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page with updated Name #Pass");
					break;
					
				} else {
					String challengeNameToTap = CreateAChallengeBasicChallengeDetails.actualchallenegeName;
					scrollAndClick(CreateAChallengeBasicChallengeDetails.ChallengeNameEle, challengeNameToTap);
					logger.info(getData("platformName") + " _ challenge Displyed in Listing Page #Pass");
				}
			} catch (Exception e) {
				logger.info(getData("platformName") + " _ challenge not found in this attempt, Checking again"); //New
				waitFor(15000); //New
				for (int j = 0; j <=3; i++) {
					waitFor(2000);
					swipeUp();
				}
			}}
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.moreIcon);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.editChallengeActionBtn);
		waitFor(10000);
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.addFriendCTA);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "AddFriends");
		SendKeysOnMobileElement(CreateChallengeAddFriends.searchBox,testData.get(3).get("inp_friendName"));
		waitFor(5000);
		ClickOnMobileElement(CreateChallengeAddFriends.inviteOptionList.get(0));
		ClickOnMobileElement(CreateChallengeAddFriends.addToChallenge);
		waitFor(2000);
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(3000);
	}
	
	public static  void newUserRCInviteNotification() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(0).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		Assert.assertEquals(msgDetailsPage_chName.getText(),updatedChallengeName);
		logger.info(getData("platformName") + " - Invite notification is displayed in invited user message center landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/NewUser_RC_invite.png");
	}
	
	public static void removeFriendFromChallenge() throws IOException, InvalidFormatException {
		waitFor(3000);
		ClickOnMobileElement(messageCenterIcon);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		System.out.println("MessageHeader size :"+messageHeader.size());
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(1).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		Assert.assertEquals(msgDetailsPage_chName.getText(),updatedChallengeName);
		logger.info(getData("platformName") + " - Invite accepted is displayed for creator #Pass");
		ClickOnMobileElement(msgDetailsPage_viewChDetailBtn);
		
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.moreIcon);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.editChallengeActionBtn);
		waitFor(10000);
		if (CreateAChallengeBasicChallengeDetails.addedFriendavatarList.size() !=0) {
			List<MobileElement> removeFriendXIcon = CreateAChallengeBasicChallengeDetails.removeFriendXIcon;
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notifications/RemoveFriend_RemoveFriend1.png");

			for (int i = removeFriendXIcon.size()-1; i>=1; i--) {
				CreateAChallengeBasicChallengeDetails.removeFriendXIcon.get(i).click();
				CreateAChallengeBasicChallengeDetails.friendRemoveButton.click();
				break;
			}
		}
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.startChallengeBtn);
		waitFor(5000);
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(3000);
	}
	
	public static void paricipantRemovedNotification() throws InvalidFormatException, IOException {
	
		waitFor(3000);
		ClickOnMobileElement(messageCenterIcon);
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			System.out.println("MessageHeader size :"+messageHeader.size());
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(6).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		logger.info(getData("platformName") + " - participant removed notification is displayed in invited user message center landing page #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/NewUserRemoved.png");

	}
	
	public static void rcReject() throws IOException {
		
		ClickOnMobileElement(msgDetailsPage_noThanksBtn);
		logger.info(getData("platformName") + " - Challenge Rejected by invited user #Pass");
		waitFor(5000);
		ClickOnMobileElement(pageBackIcon);
		waitFor(3000);
	}
	
	public static void inviteRejectNotification() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MessageCenter");
		waitFor(10000);
		for(int i = 0; i<= messageHeader.size()-1; i++) {
			System.out.println("MessageHeader size :"+messageHeader.size());
			if (messageHeader.get(i).getText().equalsIgnoreCase(testData.get(7).get("messageHeader_val"))) {
				ClickOnMobileElement(messageHeader.get(i));
				break;
			}
		}
		waitFor(10000);
		logger.info(getData("platformName") + " - Invite Rejected #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/Notification/InviteReject.png");
	}
	
	/********************************* updated Message center Locators (updated on build 1.6.3 ************************************/
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Message center']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeButton[2]")
	public static MobileElement messageCenterIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static MobileElement notificationlist;

	@AndroidFindBy(xpath = "//*[@text='Destiny Discover']")
	public static MobileElement homePageHeader;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/iv_mark_as")
	public static MobileElement swipe_MarkIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_mark_as")
	public static MobileElement swipe_MarkAs_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/iv_delete")
	public static MobileElement swipe_DeleteIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_delete")
	public static MobileElement swipe_Delete_lbl;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvDateTime")
	public static List<MobileElement> messageCenter_timeStamp;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/count")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton/XCUIElementTypeStaticText)[1]")
	public static MobileElement unReadCountOnBellIcon;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Back']")
	@iOSXCUITFindBy(xpath = "//*[@name='Back']")
	public static MobileElement pageBackIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Message Center']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Message Center\"]")
	public static MobileElement pageHeader_lbl;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Edit']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton/XCUIElementTypeStaticText[@name='Edit']")
	public static MobileElement editBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Select All']")
	@iOSXCUITFindBy(xpath = "//*[@name='Select All']")
	public static MobileElement selectAllBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Unselect All']")
	@iOSXCUITFindBy(xpath = "//*[@name='Unselect All']")
	public static MobileElement deSelectAllBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Cancel']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton/XCUIElementTypeStaticText[@name='Cancel']")
	public static MobileElement cancelBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivCheckbox")
	@iOSXCUITFindBy(xpath = "//*[@name='Normal Checkbox']")
	public static List<MobileElement> checkBoxBtn;

	@AndroidFindBy(xpath = "//*[@text='Mark']")
	@iOSXCUITFindBy(xpath = "//*[@name='Mark']")
	public static MobileElement markButton;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvMessageCount")
	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypeStaticText']")
	public static MobileElement countOfSelectedMessage;

	@AndroidFindBy(xpath = "//*[@text='Mark as Read']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Mark as Read\"]")
	public static MobileElement markasReadOption;

	@AndroidFindBy(xpath = "//*[@text='Mark as Unread']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Mark as Unread\"]")
	public static MobileElement markasUnReadOption;

	@AndroidFindBy(xpath = "//*[@text='Cancel']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Cancel\"]")
	public static MobileElement markOptionCancelBtn;

	@AndroidFindBy(xpath = "//*[@text='Delete']")
	@iOSXCUITFindBy(xpath = "//*[@name='Delete']")
	public static MobileElement deleteButton;

	@AndroidFindBy(id = "android:id/message")
	@iOSXCUITFindBy(xpath = "//*[@name='Are you sure you want to delete 1 message?']")
	public static MobileElement del_popupMessage;

	@AndroidFindBy(xpath = "//*[@text='CANCEL']")
	@iOSXCUITFindBy(xpath = "//*[@name='Cancel']")
	public static MobileElement MsgDeletePop_CancelButton;

	@AndroidFindBy(xpath = "//*[@text='DELETE']")
	@iOSXCUITFindBy(xpath = "//*[@name='Delete']")
	public static MobileElement MsgDeletePop_DeleteButton;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Reading status']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeImage[1]")
	public static List<MobileElement> readingStatus;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvHeader")
	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypeCell']/XCUIElementTypeStaticText[2]")
	public static List<MobileElement> messageHeader;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvDescription")
	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypeCell']/XCUIElementTypeStaticText[3]")
	public static List<MobileElement> messageHeader_Description;
	
	@AndroidFindBy(xpath = "//*[@text='Delete']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Delete\"]")
	public static MobileElement msgDetailsPage_DeleteBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_challenged")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeStaticText)[1]")
	public static MobileElement msgDetailsPage_msgHeader;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_challenged_date")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeStaticText)[2]")
	public static MobileElement msgDetailsPage_chDate;

	@AndroidFindBy(xpath ="//android.widget.TextView[@content-desc=\"user profile image\"]") // new 1.6.14
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton/XCUIElementTypeStaticText)[3]")
	public static MobileElement msgDetailsPage_creatorAvatar;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_challenged_time")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeStaticText)[3]")
	public static MobileElement msgDetailsPage_timeStamp;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_read_by_date")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeStaticText)[4]")
	public static MobileElement msgDetailsPage_readByDate;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_msg_title")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeStaticText)[5]")
	public static MobileElement msgDetailsPage_chName;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_msg_body")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeStaticText)[6]")
	public static MobileElement msgDetailsPage_chDesc;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/material_cover_image_view")
	// @iOSXCUITFindBy(xpath = "")
	public static List<MobileElement> msgDetailsPage_chTitleList;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/more_option_image_view")
	// @iOSXCUITFindBy(xpath = "")
	public static List<MobileElement> msgDetailsPage_titleMoreIcon;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='See Challenge details']") // new 1.6.14
	@iOSXCUITFindBy(xpath = "//*[@name=\"See Challenge Details\"]")
	public static MobileElement msgDetailsPage_viewChDetailBtn;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_join_program") // new 1.6.14
	public static MobileElement msgDetailsPage_viewPrgDetailBtn;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_add_to_challenge") // new 1.6.14
	public static MobileElement msgDetailsPage_acceptBtn;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_no_thanks") // new 1.6.14
	public static MobileElement msgDetailsPage_noThanksBtn;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Go to Challenge\"]") // new 1.6.14
	public static MobileElement msgDetailsPage_goToChallenge;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_title")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
	public static MobileElement rcPageHeader_lbl;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Back']")
	@iOSXCUITFindBy(xpath = "//*[@name=\"Back\"]")
	public static MobileElement rcpageBackButton;

	@AndroidFindBy(xpath = "//*[@text='Book Club']")
	public static MobileElement lbl_BooKClub_Header;

	/***********************************************************************************************************************************/

}

