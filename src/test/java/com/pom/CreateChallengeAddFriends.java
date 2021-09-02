package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateChallengeAddFriends extends CapabilitiesAndWebDriverUtils {
	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(CreateChallengeAddFriends.class);
	static CreateAChallengeBasicChallengeDetails basicChallenge = new CreateAChallengeBasicChallengeDetails();

	public static String frinedToRemove;

	public CreateChallengeAddFriends() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

		public void addFriend() throws InvalidFormatException, IOException {
			List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "AddFriends");
			logger.info(" --"+ getData("platformName")+" - Add Friend Validation Start -- ");

			Boolean txtValidation = false;
			ClickOnMobileElement(basicChallenge.addFriendCTA);
			logger.info(getData("platformName")+" - Add friend button clicked #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddFriendPage.png");

			Assert.assertEquals(searchHeader.getText(),testData.get(0).get("lbl_Header"));
			//Assert.assertEquals(recentsearchText.getText(),testData.get(0).get("lbl_recentlySearchedResult"));
			waitForElementDisplayed(addToChallenge);
			SendKeysOnMobileElement(searchBox, testData.get(0).get("inp_sizeValidation"));
			if(recentSearchfriendList.size()!=0) {
				logger.info(getData("platformName")+" - Search box giving suggestion when user enter onlt two character #Fail");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddFriend_SizeIssue.png");
				Assert.assertTrue(txtValidation);
			}
			else {
				txtValidation = true;
				logger.info(getData("platformName")+" - Search box is not giving suggestion when user enter only two character #Pass");
				Assert.assertTrue(txtValidation);
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddFriend_SizeValPass.png");

			}
			if (getData("platformName").equalsIgnoreCase("android")
					|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			SendKeysOnMobileElement(searchBox, testData.get(1).get("inp_friendName"));
			}
			if (getData("platformName").equalsIgnoreCase("iOS")
					|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
				SendKeysOnMobileElement(searchBox, testData.get(0).get("inp_friendName"));
				searchBox.sendKeys(Keys.ENTER);
			}
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddFriendInput.png");
			waitFor(5000);
			List<MobileElement> userNamelist = recentSearchfriendList;
			
			logger.info(getData("platformName")+" - Suggested Friend List Size : " + userNamelist.size() +" #Pass");
			List<MobileElement> inviteOption =  inviteOptionList;
			ClickOnMobileElement(inviteOptionList.get(0));
			waitFor(2000);
			ClickOnMobileElement(removeFriendfromSearchPage);
			logger.info(getData("platformName")+" - Friend removed after added from search page itself #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddFriendPage_RemoveFriend.png");

			for (int i = 0; i<=userNamelist.size()-1; i++)
			{
			ClickOnMobileElement(inviteOptionList.get(i));
			waitFor(2000);
			String addChallengeTxt = addToChallenge.getText(); 
			int addedFriendcount = i + 1;
			boolean lableTxt = addChallengeTxt.equalsIgnoreCase("Add to Challenge("+addedFriendcount+")");
			System.out.println(addChallengeTxt);
			Assert.assertEquals(addChallengeTxt, testData.get(0).get("lbl_addToChallenge"));

			ClickOnMobileElement(addToChallenge);
			logger.info(getData("platformName")+" - Add to Challenge button is clicked in add friends  #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddFriendPage_FriendAdded.png");


			}
			logger.info(" --"+ getData("platformName")+" - Add Friend Validation End -- ");
		}
	
	public void removeFriendFromChallenge() throws IOException {
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
		
		if (basicChallenge.addedFriendavatarList.size() > 1) {
			List<MobileElement> removeFriendXIcon = basicChallenge.removeFriendXIcon;
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_RemoveFriend1.png");

			for (int i = removeFriendXIcon.size()-1; i>=1; i--) {
				basicChallenge.removeFriendXIcon.get(i).click();
				basicChallenge.friendRemoveButton.click();	
				
			}}
			
			if (getData("platformName").equalsIgnoreCase("iOS")
					|| getData("platformName").equalsIgnoreCase("BrowserStackios")) {
				if (basicChallenge.addedFriendavatarList.size() > 1) {
					List<MobileElement> removeFriendXIcon1 = basicChallenge.removeFriendXIcon;
					Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_RemoveFriend1.png");

				
						for(int i=0;i<=removeFriendXIcon1.size();i++)
						{
							
						basicChallenge.removeFriendXIcon.get(i).click();
						basicChallenge.friendRemoveButton.click();	
						
					}
				
				
			}}
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_RemoveFriend2.png");
			logger.info(getData("platformName")+" - User removed from create Challenge page #Pass");
		}
	}

	public void AddRecentFriends() throws Exception {

		ClickOnMobileElement(basicChallenge.addFriendCTA);
		int friendListCount = inviteOptionList.size();
		System.out.println(friendListCount);
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddRecentFriend1.png");

		
		if (recentSearchfriendList.size() > 0 && recentSearchfriendList.size()<=5) {
			for (int i = 0; i < friendListCount; i++) {
				Assert.assertTrue(inviteOptionList.get(i).isDisplayed());
				MobileElement inviteNext = inviteOptionList.get(i);
				String S = recentSearchfriendList.get(i).getText();
				waitFor(4000);
				logger.info(getData("platformName")+" - Added friend is : " + S + " #Pass");
				ClickOnMobileElement(inviteNext);
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddRecentFriend_FriendAdded.png");
				logger.info(getData("platformName")+" - Recently searched list size is less than 5 #Pass");
				logger.info(getData("platformName")+" - Friend Added from the recently searched list  #Pass");
			}

		} else {
			logger.info(getData("platformName")+" - There is no friend list available to select  #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddRecentFriend_NoFriends.png");

		}
		
		Assert.assertTrue(addToChallenge.isEnabled());
		ClickOnMobileElement(addToChallenge);
		waitFor(2000);
		logger.info(getData("platformName")+" - Friend is added from recently searched friend  #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/AddRecentFriend_Done.png");
	
		}
	
	public void addFriendPageForNewUser() throws IOException {
		Assert.assertTrue(addToChallenge.isDisplayed());
		try {
			if (userNameText.get(0).isDisplayed()) {
				logger.info(getData("platformName")+" - Friend list is not displayed for new user  #Fail");
				Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/addFriend_newUser.png");
			}
		} catch (Exception e) {
			
			logger.info(getData("platformName")+" - Friend list is not displayed for new user  #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/" + getData("platformName") + "/CreateChallenge/addFriend_newUser.png");
		}
	}
	
	public void alreadyAddedFriend() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "AddFriends");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			SendKeysOnMobileElement(searchBox, testData.get(1).get("inp_friendName"));
			hideMobileKeyboard();
			waitFor(15000);
			ClickOnMobileElement(inviteOptionList.get(0));
			waitFor(2000);
			String addChallengeTxt = addToChallenge.getText();
			ClickOnMobileElement(inviteOptionList.get(0));
			waitFor(5000);
			String addChallengeTxtAfter = addToChallenge.getText();

			if (addChallengeTxt.equals(addChallengeTxtAfter)) {
				logger.info(getData("platformName") + " - User tries to add already added frined #Pass");
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/CreateChallenge/Duplicate_user.png");
			}
		}
		else {
			SendKeysOnMobileElement(searchBox, testData.get(0).get("inp_friendName"));
			hideMobileKeyboard();
			waitFor(3000);
			String user = userNameText.get(0).getText();

			ClickOnMobileElement(inviteOptionList.get(0));
			waitFor(2000);
			String addChallengeTxt = addToChallenge.getText();
			searchBox.clear();
			SendKeysOnMobileElement(searchBox, user);
			hideMobileKeyboard();
			ClickOnMobileElement(inviteOptionList.get(0));
			Assert.assertEquals(useralreadyaddedpopuptxt.isDisplayed(), true);
			ClickOnMobileElement(popUpOkBtn);
			String addChallengeTxtAfter = addToChallenge.getText();

			if (addChallengeTxt.equals(addChallengeTxtAfter)) {
				logger.info(getData("platformName") + " - User tries to add already added frined #Pass");
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/CreateChallenge/Duplicate_user.png");
			}

		}
		ClickOnMobileElement(addToChallenge);

		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {

			if (basicChallenge.addedFriendavatarList.size() > 1) {
				List<MobileElement> removeFriendXIcon = basicChallenge.removeFriendXIcon;
				int before = removeFriendXIcon.size();
				for (int i = removeFriendXIcon.size() - 1; i >= 1; i--) {
					basicChallenge.removeFriendXIcon.get(i).click();
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_Cancel.png");
					basicChallenge.friendCancelButton.click();
				}
				int after = removeFriendXIcon.size();
				if (before == after) {
					logger.info(
							getData("platformName") + " - Friend is not removed after tapping cancel in popup #Pass");
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_Cancel.png");
				} else {
					logger.info(
							getData("platformName") + " - Friend is not removed after tapping cancel in popup  #Fail");
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_Cancel.png");
				}

			}

		}

		else {

			if (basicChallenge.addedFriendavatarList.size() > 1) {
				List<MobileElement> removeFriendXIcon = basicChallenge.removeFriendXIcon;
				int before = removeFriendXIcon.size();

				basicChallenge.removeFriendXIcon.get(2).click();
				Screenshots.takeScreenshot(driver,
						"./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_Cancel.png");
				basicChallenge.friendCancelButton.click();

				int after = removeFriendXIcon.size();
				if (before == after) {
					logger.info(
							getData("platformName") + " - Friend is not removed after tapping cancel in popup #Pass");
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_Cancel.png");
				} else {
					logger.info(
							getData("platformName") + " - Friend is not removed after tapping cancel in popup  #Fail");
					Screenshots.takeScreenshot(driver,
							"./Screenshots/" + getData("platformName") + "/CreateChallenge/RemoveFriend_Cancel.png");
				}

			}
		}

	}

	public static String removeFriendXpath() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "AddFriends");
		frinedToRemove = testData.get(1).get("inp_friendName");
		return frinedToRemove;
	}
	
	
	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_invite_user\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='INVITE']")
	public static MobileElement inviteOption_User;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_userName\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Henry\"]")
	public static MobileElement recentfriendName;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_userName\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText")
	public static List<MobileElement> recentSearchfriendList;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/img_user\"])[1]")
	public static List<MobileElement> recentfriendAvatar;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/title_recently_searched")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='RECENTLY SEARCHED STUDENTS']")
	public static MobileElement recentsearchText;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/search_close\"]")
	public static MobileElement closeIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/accept_challenge")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Add to Challenge']/XCUIElementTypeStaticText")
	public static MobileElement addToChallenge;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/etSearch\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='ACSR_SuggestedSearchTextField']")
	public static MobileElement searchBox;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivSearch\"]")
	public static MobileElement searchIcon;

	@iOSXCUITFindBy(id = "iconsCoreRemove")
	public static MobileElement iosxicon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"User had already been added to the participants list\"]")
	public static MobileElement useralreadyaddedpopuptxt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"OK\"]")
	public static MobileElement popUpOkBtn;

	@iOSXCUITFindBy(id = "Search")
	@AndroidFindBy(xpath = "//*[@text='Search']")
	public static MobileElement searchHeader;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Search\"]")
	public static MobileElement searchOption;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther")
	public static MobileElement addedFriendNameCarosallist;

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button")
	public static List<MobileElement> addedFriendInSearchPage;

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button")
	public static List<MobileElement> addedFriendInSearchFriend;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/etSearch")
	@iOSXCUITFindBy(id = "Search student name")
	public MobileElement searchInputTextbox;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/img_user")
	public MobileElement ImageUser;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Remove ph3 Photon']")
//	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Remove "+frinedToRemove+"']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='Remove friend']")
	public MobileElement removeFriendfromSearchPage;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_userName")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText")
	public List<MobileElement> userNameText;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_invite_user")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"INVITE\"])[1]")
	public MobileElement inviteUserLink;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/button_save_challenge")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Add to Challenge (2)\"]")
	public MobileElement addToChallengeButton;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/search_close")
	@iOSXCUITFindBy(id = "iconsCoreRemove")
	public MobileElement searchCloseIcon;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_invite_user\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='INVITE']")
	public static List<MobileElement> inviteOptionList;

	public MobileElement getSearchOption() {
		return searchOption;
	}

	public MobileElement getAddedFriendNameCarosallist() {
		return addedFriendNameCarosallist;
	}

	public MobileElement getIosxicon() {
		return iosxicon;
	}

	public MobileElement getSearchHeader() {
		return searchHeader;
	}

	public MobileElement getInviteOption() {
		return inviteOption_User;
	}

	public MobileElement getRecentfriendName() {
		return recentfriendName;
	}

	public List<MobileElement> getRecentfriendAvatar() {
		return recentfriendAvatar;
	}

	public MobileElement getRecentsearchText() {
		return recentsearchText;
	}

	public MobileElement getCloseIcon() {
		return closeIcon;
	}

	public MobileElement getAddToChallenge() {
		return addToChallenge;
	}

	public MobileElement getSearchBox() {
		return searchBox;
	}

	public MobileElement getSearchIcon() {
		return searchIcon;
	}

	
	

}
