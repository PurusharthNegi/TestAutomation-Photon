package com.pom_RWD;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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


public class Rwd_RC extends CapabilitiesAndWebDriverUtils {
	static ExcelReader reader = new ExcelReader();
	private static final Logger logger = LogManager.getLogger();
	List<Map<String, String>> testData;
	public static String chlngName= challengeName();
			//challengeName(); 
	//public String participantName1 = addedParticipant();
	public Rwd_RC() {
		PageFactory.initElements(driver, this);
	}
	
	public void bookClub() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		WaitForWebElement(RWD_UIRefresh_PageHit.menu_profileIcon);
		//validatechallenges();
	//	searchValidation();
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/Home.png");
		waitFor(3000);
		WaitForWebElement(RWDbookClubOptionWeb);
		ClickOnWebElement(RWDbookClubOptionWeb);
		logger.info(" --Book club validation Starts-- ");
		WaitForWebElement(CCHeader);
		Assert.assertEquals(CCHeader.getText(), testData.get(0).get("lbl_challenges"));
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/BookClub.png");
		logger.info("Challenges tab is available #Pass");
		String crtChlg = RWDaddCTAWeb.getText().substring(0,16);
		Assert.assertEquals(crtChlg, testData.get(0).get("lbl_create"));
		logger.info("Create Challenge button is available #Pass");
		logger.info(" --Book club validation Completed-- ");
	}
	//discard();
	public void createChallenge() throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("-- Create challenge validation started--");
		ClickOnWebElement(RWDbookClubOptionWeb);
		WaitForWebElement(RWDaddCTAWeb);
		ClickOnWebElement(RWDaddCTAWeb);
		WaitForWebElement(lbl_createChlgTitle);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge.png");
		waitFor(2000);
		Assert.assertEquals(ChallengenamePlaceholder.getText(), testData.get(0).get("defval_ChlgNamTxtBox"));
		SendKeysOnWebElement(RWDChallengenametextbar, chlngName);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/ChallengeName.png");
		logger.info("Challenge name text box is available and Challenge name is Entered #Pass");
		Assert.assertEquals(ElementisPresent(chlngNameChar), true);
		logger.info("Challenge name character count is present #Pass");
		Assert.assertEquals(ChallengedescPlaceholder.getText(), testData.get(0).get("defval_ChlgDescTxtBox"));
		SendKeysOnWebElement(RWDChallengedescriptiontextbar, testData.get(0).get("Challenge Desc"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/ChallengeDescription.png");
		logger.info("Challenge Description text box is available and Challenge Description Entered #Pass");
		Assert.assertEquals(ElementisPresent(chlngDescChar), true);
		logger.info("Challenge description character count is present #Pass");
		
		challengeFriendValidation();
		setReminder(false);
		readBy();
		jsClick(RWDstartChalnge); //Validate function when any of the mandatory fields is left empty
		WaitForWebElement(RWD_AddTitleCTA);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/StartChallengeNotEnabled.png");
		logger.info("Start challenge button is not enabled when title is not added #Pass");
		addTitle();
		//save();
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/StartChallenge.png");
		ClickOnWebElement(RWDstartChalnge);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/StartChallenge1.png");
		logger.info("Start challenge button is clicked #Pass");
		logger.info("-- Create challenge validation Completed--");
	}
	public void startChallenge() throws IOException {
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/StartChallenge.png");
		ClickOnWebElement(RWDstartChalnge);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/StartChallenge1.png");
		logger.info("Start challenge button is clicked #Pass");
	}
	public void negativeStart() throws IOException {
		jsClick(RWDstartChalnge); //Validate function when any of the mandatory fields is left empty
		WaitForWebElement(RWD_AddTitleCTA);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/StartChallengeNotEnabled.png");
		logger.info("Start challenge button is not enabled when title is not added #Pass");
	}
	public void chlngName() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("-- Create challenge validation started--");
		ClickOnWebElement(RWDbookClubOptionWeb);
		WaitForWebElement(RWDaddCTAWeb);
		ClickOnWebElement(RWDaddCTAWeb);
		WaitForWebElement(lbl_createChlgTitle);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge.png");
		waitFor(2000);
		Assert.assertEquals(ChallengenamePlaceholder.getText(), testData.get(0).get("defval_ChlgNamTxtBox"));
		SendKeysOnWebElement(RWDChallengenametextbar, chlngName);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/ChallengeName.png");
		logger.info("Challenge name text box is available and Challenge name is Entered #Pass");
		Assert.assertEquals(ElementisPresent(chlngNameChar), true);
		logger.info("Challenge name character count is present #Pass");
		Assert.assertEquals(ChallengedescPlaceholder.getText(), testData.get(0).get("defval_ChlgDescTxtBox"));
		SendKeysOnWebElement(RWDChallengedescriptiontextbar, testData.get(0).get("Challenge Desc"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/ChallengeDescription.png");
		logger.info("Challenge Description text box is available and Challenge Description Entered #Pass");
		Assert.assertEquals(ElementisPresent(chlngDescChar), true);
		logger.info("Challenge description character count is present #Pass");
	}
	
	public void save() throws IOException {
		logger.info("--Save challenge validation Started--");
		Screenshots.takeScreenshot(driver, "Screenshots/CreateChallenge/SaveChallenge.png");
		ClickOnWebElement(save);
		logger.info("Save challenge button is clicked #Pass");
		WaitForWebElement(lbl_ActiveChallenge);
		Screenshots.takeScreenshot(driver, "Screenshots/CreateChallenge/SaveChallenge1.png");
		logger.info("Challenge is save and redirected to challenge listing screen #Pass");
		//challengeSearch();
		logger.info("--Save challenge validation Completed--");
	}
	
	public void validatechallenges() throws IOException, InvalidFormatException {
        List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "ChallengesValidation");
        
        logger.info("--Search title to add to new and existing challenge validation started--");
        waitFor(3000);
        ClickOnWebElement(ebooksSeeall);
        javaScriptScrollToEnd();
        waitFor(3000);
        javascriptScroll(RwdbookTitle);
        if(ElementisPresent(RwdbookTitle))
        {
            waitFor(2000);
            RWDcarList.findElement(By.id("more-option-horiz")).click();
            logger.info("Application should not display the ‘Include in a New Challenge’ CTAs for Non-Follett content. #Pass");
            Screenshots.takeScreenshot(driver,"Screenshots/ReadingChallenge/ListingSection/NofolletBook_include.png");
            logger.info("Application should not display the ‘Add to Existing Challenge’ CTA for Non-Follett content #Pass");
        }
        waitFor(3000);
        javascriptScroll(RWDcardList1);
        waitFor(3000);
        jsClick(moreMenu);
        logger.info("User should be able to view the more menu with Include in New Challenge options #Pass");
        Assert.assertTrue(RWDIncludechallenge.isDisplayed());
        logger.info("User should be able to view the more menu with Include in New Challenge options #Pass");
        Screenshots.takeScreenshot(driver,"Screenshots/ReadingChallenge/ListingSection/folletBook_include.png");
        Assert.assertTrue(RWDAddchallenge.isDisplayed());
        logger.info("User should be able to view the more menu with Add to Existing Challenge options #Pass");
        logger.info("User should be able to view list the challenges as a carousel #Pass");
        ClickOnWebElement(RWDIncludechallenge);
        waitFor(4000);
        Assert.assertEquals(RWDchlgcreatehead.getText(), testData.get(0).get("Expected").trim());
        Screenshots.takeScreenshot(driver,"Screenshots/ReadingChallenge/ListingSection/includeChallenge.png");
        driver.navigate().back();
        waitFor(5000);
        jsClick(moreMenu);
        waitFor(3000);
        ClickOnWebElement(RWDAddchallenge);
        waitFor(3000);
        waitFor(5000);
        Assert.assertEquals(rwdAddto.getText(), testData.get(1).get("Expected").trim());
        Screenshots.takeScreenshot(driver,"Screenshots/ReadingChallenge/ListingSection/AddtoChallenge.png");
        ClickOnWebElement(rwdAddtochallenges1.get(0));
        if(ElementisPresent(error)) {
        	for(int i=1; i<=rwdAddtochallenges1.size() ; i++) {
        		ClickOnWebElement(rwdAddtochallenges1.get(i));
        		waitFor(4000);
        		if(ElementisPresent(lbl_readingChallengeHeader)) {
        			break;
        		}
        	}	
        }
        waitFor(4000);
        Screenshots.takeScreenshot(driver,"Screenshots/ReadingChallenge/ListingSection/saveChallengeChallenge.png");
        logger.info("User should be able to add to list the challenges #Pass");
        ClickOnWebElement(RWDsaveChallenges); 
        logger.info("--Search title to add to new and existing challenge validation Completed--");
        driver.navigate().back();
    }

	public void searchValidation() throws IOException, InvalidFormatException {
		waitFor(4000);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "ChallengesValidation");
		logger.info("--Search validation assertion Started--");
		ClickOnWebElement(RWDsearch);
		waitFor(2000);
		SendKeysOnWebElement(rwdsearchInput, testData.get(0).get("Search"));
		SendKeysEnter(rwdsearchInput);
		waitFor(4000);
		Screenshots.takeScreenshot(driver, "Screenshots/Seacrhscreen/searchscreen.png");
		logger.info("User should be able to view the search results #Pass");
		jsClick(moreMenu);
		waitFor(3000);
		javaScriptScrollToEnd();
		ClickOnWebElement(RWDAddchallenge);
		waitFor(5000);
		Assert.assertEquals(rwdAddto.getText(), testData.get(1).get("Expected").trim());
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/ListingSection/AddtoChallenge.png");
		// ClickOnWebElement(rwdAddtochallenges);
		ClickOnWebElement(rwdAddtochallenges1.get(0));
		if (ElementisPresent(error)) {
			for (int i = 1; i <= rwdAddtochallenges1.size(); i++) {
				ClickOnWebElement(rwdAddtochallenges1.get(i));
				waitFor(4000);
				if (ElementisPresent(lbl_readingChallengeHeader)) {
					break;
				}
			}
		}
		waitFor(4000);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/ListingSection/saveChallengeChallenge.png");
		logger.info("User should be able to add to list the challenges #Pass");
		ClickOnWebElement(RWDsaveChallenges);
		waitFor(7000);
		ClickOnWebElement(RWDsearch);
		waitFor(3000);
		SendKeysOnWebElement(rwdsearchInput, testData.get(1).get("Search"));
		waitFor(3000);
		SendKeysEnter(rwdsearchInput);
		waitFor(9000);
		Assert.assertEquals(rwdNotitleFound.getText(), testData.get(2).get("Search"));
		logger.info("User should not able able to view the titles that are no more available to the user");
		Screenshots.takeScreenshot(driver, "Screenshots/Seacrhscreen/notitlesearchscreen.png");
		logger.info("Application should not display the titles with expired subscription #Pass");
		logger.info("--Search validation assertion Completed--");
		driver.navigate().back();
	}

	public void discard() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Discard create challenge validation started--");
		waitFor(8000);
	
		ClickOnWebElement(RWDaddCTAWeb);
		WaitForWebElement(lbl_createChlgTitle);
		SendKeysOnWebElement(RWDChallengenametextbar, chlngName);
		logger.info("Entered challenge name #Pass");
		waitFor(2000);
		ClickOnWebElement(closeChlng);
		// System.out.println(discardMsg1.getText()+"/n"+discardMsg2.getText());
		WaitForWebElement(discardMsg1);
		System.out.println(discardMsg1.getText());
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/Discard.png");
		// Assert.assertEquals(discardMsg.getText(),testData.get(0).get("discardMsg"));
		logger.info("Discard message/Warning is displayed #Pass");
		Assert.assertTrue(ok.isDisplayed(), "Ok button is not displayed");
		logger.info("Ok button is displayed for discard message #Pass");
		Assert.assertTrue(cancel.isDisplayed(), "Cancel button is not displayed");
		logger.info("Cancel button is displayed for discard message #Pass");
		ClickOnWebElement(ok);
		logger.info("--Discard create challenge validation Completed--");
		waitFor(1000);
	}

	public void challengeFriendValidation() throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("-- Challenge Friend Assertion Started --");
		// System.out.println(addFriendHeader.getText());
		Assert.assertEquals(addFriendHeader.getText(), testData.get(0).get("defval_addFriend"));
		logger.info("Add student to challenge header is present #Pass");
		ClickOnWebElement(RWDaddfriendCTA);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddFriend.png");
		Assert.assertEquals(lbl_searchStudent.getText(), testData.get(0).get("defval_searchStudentTitle"));
		logger.info("Search student is working #Pass");
		ClickOnWebElement(RWD_SearchStudentName);
		SendKeysOnWebElement(RWD_SearchStudentName, testData.get(1).get("studentName"));
		Assert.assertEquals(recentSearchheader.getText(), testData.get(0).get("defval_recentlySearched"));
		logger.info("Entered student name less than 2 string, no suggestions found #Pass");
		logger.info("Recently Searched Students Header is present #Pass");
		ClickOnWebElement(RWD_SearchStudentName);
		SendKeysOnWebElement(RWD_SearchStudentName, testData.get(2).get("studentName"));
		waitFor(3000);
		Assert.assertEquals(noResult.getText(), testData.get(0).get("lbl_noResult"));
		logger.info("No Search Result found for the search string #Pass");
		ClickOnWebElement(RWD_SearchStudentName);
		SendKeysOnWebElement(RWD_SearchStudentName, testData.get(0).get("studentName"));
		logger.info("Entered student name more then 3 string and suggestions found #Pass");
		Assert.assertTrue(recentSearch.size() <= 5, "Recently Searched Students is more than 5");
		logger.info("Recently Searched students is less than equal to 5 #Pass");
		waitFor(4000);
		addMultipleStudent(5); // Add multiple students to the challenge vaildation
		recentInviteValidation(); // Recently searched participant list validation
		removeMultipleStudent(5); // Remove multiple students to the challenge validation
		RWD_InviteFriendLists.get(0).click();
		waitFor(2000);
		//addedParticipant();
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddFriend1.png");
		ClickOnWebElement(RWDAddToChallenge);
		System.out.println("UserAddedTo Challenge");
		WaitForWebElement(RWDRemoveParticipants.get(0));
		removeParticipant();
		challengeFriend();
		logger.info("-- Challenge Friend Assertion Completed --");
	}
	public void recentInviteValidation() throws Exception {
		ClickOnWebElement(RWDAddToChallenge);
		WaitForWebElement(RWDaddfriendCTA);
		waitFor(2000);
		ClickOnWebElement(RWDaddfriendCTA);
		waitFor(7000);
		if(recentSearch.size()>0 & recentSearch.size()<=5) {
			logger.info("Recently Search participant list is not more than 5 #Pass");
		}
		else {
			throw new Exception("Recently search participant list is more than 5 #Fail");
		}
	}
	
	public void challengeFriend() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(6000);
		WaitForWebElement(RWDaddfriendCTA);
		ClickOnWebElement(RWDaddfriendCTA);
		WaitForWebElement(RWD_SearchStudentName);
		waitFor(5000);
		ClickOnWebElement(RWD_SearchStudentName);
		SendKeysOnWebElement(RWD_SearchStudentName,testData.get(0).get("studentName"));
		waitFor(6000);
		RWD_InviteFriendLists.get(0).click();
		ClickOnWebElement(RWDAddToChallenge);
		waitFor(3000);
		logger.info("Added Particitipant to the challenge #Pass");
	}
	//Need to implement
	public String addedParticipant() throws InvalidFormatException, IOException {
	//	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		String participantName = null;
		if(!adderUser.getText().isEmpty()) {
			switch(adderUser.getText().toLowerCase()) {
			case "photon1":
				participantName = "ph1";
				return participantName;
			case "photon11":
				participantName = "ph11";
				return participantName;
			}
            
		}
		return participantName;
	}
	public void addMultipleStudent(int noOfStudent) throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Add multiple students to the challenge vaildation started--");
		waitFor(10000);
		WaitForWebElement(recentSearch.get(0));
		if(recentSearch.size()>0) {
			logger.info("No of Search friends list: " + recentSearch.size());
			for(int i=0;i<=noOfStudent;i++) {
				Assert.assertEquals(btn_inviteList.get(i).getText(), testData.get(0).get("defval_invite"));
				//javascriptScroll(btn_inviteList.get(i));
				waitFor(4000);
				WaitForWebElement(btn_inviteList.get(i));
				ClickOnWebElement(btn_inviteList.get(i));
				logger.info(recentSearch.get(i).getText()+" Student is added #Pass");
			}
			logger.info("--Add multiple students to the challenge vaildation Completed--");
		}
		else {
			throw new Exception("No recent search results for friends");
		}
	}
	
	public void removeMultipleStudent(int noOfStudent) {
		logger.info("--Remove multiple students from the challenge vaildation started--");
		waitFor(2000);
		if(addedStudentList.size()>0) {
			logger.info("No of Added friends list: " + addedStudentList.size());
			for(int i=noOfStudent;i<addedStudentList.size();i--) {
				//System.out.println("inside remove");
				javascriptScroll(addedStudentList.get(i));
				waitFor(500);
				logger.info(addedStudentList.get(i).getText()+" Student is removed #Pass");
				ClickOnWebElement(addedListClose.get(i));
			   if(i==0) { break; } 
			}
		}
		logger.info("--Remove multiple students from the challenge vaildation Completed--");
	}
	
	public void setReminder(boolean edit) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
	        ClickOnWebElement(RWDsetReminder);
	        Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder.png");
	        List<WebElement> remindertype = RWDReminderTypes;
	        waitFor(2000);
	        if (edit == true) {
	        	 switch(testData.get(0).get("RemainderTypeEdit")) {
		            case "Daily":
		            	logger.info("Selected reminder type :"+remindertype.get(1).getText());
		            	waitFor(3000);
		                remindertype.get(1).click();
		                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(1).getText()+".png");
		                break;
		            case "Weekly":
		            	logger.info("Selected reminder type :"+remindertype.get(2).getText());
		            	waitFor(5000);
		                remindertype.get(2).click();
		                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(2).getText()+".png");
		                break;
		            case "Monthly":
		                logger.info("Selected reminder type :"+remindertype.get(3).getText());
		                waitFor(5000);
		                remindertype.get(3).click();
		                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(3).getText()+".png");
		                break;
	        	 }
	        }
	        else {
	        	switch(testData.get(0).get("RemainderType")) {
	            case "None":
	            	logger.info("Selected reminder type :"+remindertype.get(0).getText()+" #Pass");
	            	waitFor(3000);
	                remindertype.get(0).click();
	                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(0).getText()+".png");
	                break;
	            case "Daily":
	            	logger.info("Selected reminder type :"+remindertype.get(1).getText()+" #Pass");
	            	waitFor(3000);
	                remindertype.get(1).click();
	                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(1).getText()+".png");
	                break;
	            case "Weekly":
	            	logger.info("Selected reminder type :"+remindertype.get(2).getText()+" #Pass");
	            	waitFor(3000);
	                remindertype.get(2).click();
	                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(2).getText()+".png");
	                break;
	            case "Monthly":
	                logger.info("Selected reminder type :"+remindertype.get(3).getText()+" #Pass");
	                waitFor(3000);
	                remindertype.get(3).click();
	                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(3).getText()+".png");
	                break;
	            default:
	            	logger.info("Provided type is not matched");
	                Assert.assertFalse(true,"Provided type is not matched");
	        	}
	        }
	        
	    }
	public void readBy() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		 waitFor(3000);
	        javascriptScroll(RWDsetReminder1);
	        if (RWDcalenderImage.isDisplayed()) {
	        	logger.info("Set Ready by date calender icon is present #Pass");
	            ClickOnWebElement(RWDcalenderImage);
	            Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReadBy.png");
	            logger.info("Set Ready by date calender icon is Clicked #Pass");
	            IsDisplayedWebElement(RWDDatePickerSetReadyByDate);
	            if(RWDDatePickerSetReadyByDate.isDisplayed()) {
	            	String StartDate = testData.get(0).get("ReadBy");
	            	WebElement date = driver.findElement(By.xpath("//div[text()=' " + StartDate + " ']"));
	                ClickOnWebElement(date); //RWDSelectDate
	                waitFor(2000);
	                Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReadBy1.png");
	                Assert.assertTrue(true, "Date picker popup is shown");
	            }else{
	            	logger.info("Date picker popup is not shown ");
	                Assert.assertFalse(true,"Date picker popup is shown");
	            }
	        } else {
	        	logger.info("Set Ready by date calender icon is not present");
	            Assert.assertFalse(true, "Set Ready by date calender icon is not present");
	        }
	    }
	
	public void addTitle() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Add title assertion started--");
		Assert.assertEquals(addTitleHeader.getText(), testData.get(0).get("defval_addTitle"));
		logger.info("Add title to challenge header is present #Pass");
		javascriptScroll(RWD_AddTitleCTA);
		ClickOnWebElement(RWD_AddTitleCTA);
		WaitForWebElement(RWDSearchInput);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle1.png");
		Assert.assertTrue(closeIcon.isDisplayed());
		ClickOnWebElement(closeIcon);
		logger.info("Title search close icon is available and clicked #Pass");
		ClickOnWebElement(RWD_AddTitleCTA);
		WaitForWebElement(RWDSearchInput);
		waitFor(2000);
		Assert.assertEquals(searchTitleHeader.getText(), testData.get(0).get("defval_searchTitleHeader"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle.png");
		logger.info("Title search header is available #Pass");
		SendKeysOnWebElement(RWDSearchInput, testData.get(1).get("titleName"));
		waitFor(3000);
		Assert.assertEquals(ElementisPresent(lbl_noResult), true);
		logger.info("No Results found header is present for searched keyword #Pass");
		SendKeysOnWebElement(RWDSearchInput, testData.get(0).get("titleName"));
		waitFor(3000);
		SendKeysEnter(RWDSearchInput);
		logger.info("Title name is entered #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitlce_SearchResult.png");
		waitFor(2000);
		sortAndFilter(); // Sort and filter for add title screen
		waitFor(2000);
		addFavorites(); //Add to favorites validation
		titlePreview(); //Title preview validations
		//readOnline(); //Open title validation in reader
		jsClick(RWDMoreMenu);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle_MoreMenu.png");
		logger.info("More menu is clicked for the titile #Pass");
		Assert.assertTrue(Btn_checkoutorReturn.isDisplayed(),"Checkout button is not displayed");
		logger.info(Btn_checkoutorReturn.getText()+" button is available for title #Pass");
		ClickOnWebElement(AddToChallengeTitle);
		removeTitle();
		jsClick(RWDMoreMenu);
		ClickOnWebElement(AddToChallengeTitle);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle_ToChallenge.png");
		logger.info("Title is added to the challenge #Pass");
		waitFor(5000);
		jsClick(RWDMoreMenu);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/RemoveFromChallenge.png");
		waitFor(1000);
		Assert.assertTrue(RemoveFromChallengeTitle.isDisplayed(),"Remove from challenge button is not displayed");
		jsClick(RWDMoreMenu);
		logger.info("Remove from challenge button is displayed #Pass");
		logger.info(" --Add title assertion completed-- ");
	}
	public void addFavorites() throws IOException {
		jsClick(RWDMoreMenu);
		System.out.println(btn_favUnfav);
		if(btn_favUnfav.getText().equalsIgnoreCase("Add to Favorites")) {
			ClickOnWebElement(btn_favUnfav);
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddtoFavorites.png");
			logger.info("Add to Favorites button is present and clicked #Pass");
		}
		else if(btn_favUnfav.getText().equalsIgnoreCase("UnFavorite")) {
			ClickOnWebElement(btn_favUnfav);
			waitFor(1000);
			jsClick(RWDMoreMenu);
			Assert.assertTrue(btn_favUnfav.getText().equalsIgnoreCase("Add to Favorites"),"Add to favorites button is not present");
			ClickOnWebElement(btn_favUnfav);
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddtoFavorites.png");
			logger.info("Add to Favorites button is present and clicked #Pass");
		}
	}
	@FindBy(xpath="//button[text()='Add to Favorites ' or text()='UnFavorite ']")
	public WebElement btn_favUnfav;
	
	public void readOnline() {
		jsClick(RWDMoreMenu);
		ClickOnWebElement(btn_open);
		logger.info("Open online button is present and clicked #Pass");
		switchToIframe(frame);
		System.out.println("Switched to iframe header");
		waitFor(10000);
		ClickOnWebElement(btn_closeBook);
		logger.info("Close is present for book and clicked #Pass");
		WaitForWebElement(RWDMoreMenu);
		logger.info("Redirected back to Create challenge screen #Pass");
	}
	
	@FindBy(xpath="//*[text()=' Open ']")
	public WebElement btn_open;
	
	@FindBy(xpath="//span[@class='iframe-header']")
	public WebElement frame;  
	
	@FindBy(xpath="(//span[text()='Close Book'])[1]")
	public WebElement btn_closeBook;
			
	public void removeTitle() {
		logger.info("--Remove title validation Started--");
		WaitForWebElement(removeBook.get(1));
		ClickOnWebElement(removeBook.get(1));
		waitFor(2000);
		Assert.assertTrue(ElementisPresent(removeBookConfirmMsg),"Title remove confirmation message is not displayed");
		logger.info("Remove title confirmation message is displayed #Pass");
		
		Assert.assertTrue(ElementisPresent(btn_cancel),"Cancel button for remove confirmation is not displayed");
		logger.info("Cancel button for title remove confirmation message is displayed #Pass");
		
		ClickOnWebElement(btn_cancel);
		logger.info("Cancel button is clicked on title remove confirmation pop-up");
		
		WaitForWebElement(removeBook.get(1));
		logger.info("Title is not removed #Pass");
		ClickOnWebElement(removeBook.get(1));
		
		ClickOnWebElement(btn_remove);
		logger.info("Remove button for title remove confirmation message is displayed & clicked #Pass");
		logger.info("--Remove title validation Completed--");
		waitFor(2000);
	}
	
	public void titlePreview() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Title preview assertion started--");
	/*	ClickOnWebElement(RWDaddCTAWeb);
		WaitForWebElement(lbl_createChlgTitle);
		javascriptScroll(RWD_AddTitleCTA);
		ClickOnWebElement(RWD_AddTitleCTA);
		WaitForWebElement(RWDSearchInput);
		SendKeysOnWebElement(RWDSearchInput, testData.get(0).get("titleName"));
		waitFor(3000);
		Assert.assertEquals(lbl_suggestedResult.getText(), testData.get(0).get("defval_suggestedResult"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle_SuggestedResult.png");
		logger.info("Suggested result header is available #Pass");
		waitFor(3000);
		ClickOnWebElement(searchlist.get(0));*/
		
		WaitForWebElement(book);
		ClickOnWebElement(book);
		WaitForWebElement(bookName);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/TitleDetails.png");
		Assert.assertEquals(bookName.getText(), testData.get(0).get("titleName"));
		logger.info("Book name is present in title preview #Pass");
		
		Assert.assertEquals(ElementisPresent(authorName), true);
		logger.info("Author name is present in title preview #Pass");
		
		Assert.assertEquals(ElementisPresent(lbl_titleMaterial), true);
		logger.info(lbl_titleMaterial.getText()+" Title Material type is present in title preview #Pass");
		
		//Assert.assertEquals(ElementisPresent(RWDAddToChallenge1), true );
		logger.info("Add to challenge button is present in title preview #Pass");
		
		Assert.assertEquals(ElementisPresent(ratings), true );
		logger.info("Rating is available fot the title in preview screen #Pass");
		
		Assert.assertTrue(ElementisPresent(Btn_checkoutorReturn),"Checkout button is not displayed");
		logger.info(Btn_checkoutorReturn.getText()+" button is available for title preview screen #Pass");
		
		javascriptScroll(lbl_availableheader);
		Assert.assertEquals(ElementisPresent(lbl_availableheader), ElementisPresent(lbl_availableValue));
		logger.info("Available copy header and value is present #Pass");
		waitFor(2000);
		
		ClickOnWebElement(back);
		WaitForWebElement(RWD_AddTitleCTA);
		logger.info("Back icon is clicked and Navigated back to Create challenge screen #Pass");
		
		//titleDetail(); //not in scope
		logger.info("--Title preview assertion Completed--");
	}
	
	@FindBy(xpath="//button[@id='mystuff-tlt-prvw-back']/mat-icon")
	public WebElement back;
	
	@FindBy (xpath="//*[@class='cr-channel-sound ng-star-inserted']/span")
	public WebElement lbl_titleMaterial; 
	
	@FindBy (xpath="//*[@class='item-lft']/span")
	public WebElement lbl_availableheader;
	
	@FindBy (xpath="//*[@class='item-lft']/div")
	public WebElement lbl_availableValue;
	
	public void titleDetail() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Title details screen assertion started--");
		ClickOnWebElement(viewDetail);
		waitFor(3000);
		WaitForWebElement(bookName);
		waitFor(3000);
		Assert.assertEquals(bookName.getText(), testData.get(0).get("titleName"));
		logger.info("Book name is present in title details #Pass");
		Assert.assertEquals(ElementisPresent(authorName), true);
		logger.info("Author name is present in title details #Pass");
		Assert.assertEquals(ElementisPresent(RWDAddToChallenge1), true );
		logger.info("Add to challenge button is present in title details #Pass");
		Assert.assertEquals(ElementisPresent(ratings), true );
		logger.info("Rating is available fot the title in detail screen #Pass");
		Assert.assertTrue(ElementisPresent(Btn_checkoutorReturn),"Checkout button is not displayed");
		logger.info(Btn_checkoutorReturn.getText()+" button is available for title details screen #Pass");
		waitFor(2000);
		logger.info("--Title details screen assertion Completed--");
	}
	
	public void sortAndFilter() throws IOException, InvalidFormatException{
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		WaitForWebElement(btn_SortandFilter);
		Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterButton.png");
		ClickOnWebElement(btn_SortandFilter);
		logger.info("Sort and Filter button is displayed and clicked #Pass");
		Assert.assertTrue(title_SortandFilter.isDisplayed(), "Sort and Filter title is not displayed");
		logger.info("Sort and filter screen title is displayed #Pass");
		ClickOnWebElement(rb_Author);
		logger.info("Author radio button is clicked #Pass");
		Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterPage.png");
		for(int i=2; i<=9; i++) {
			if(i==2) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(0).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
			else if(i==3) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(1).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
			else if(i==4) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(2).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
			else if(i==5) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(3).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
			else if(i==6) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(4).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
			else if(i==7) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(5).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
			else if(i==8) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(6).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
			else if(i==9) {
				Assert.assertEquals(sortBy.get(i).getText(),testData.get(7).get("sortBy"));
				jsClick(sortBy.get(i));
				Screenshots.takeScreenshot(driver,"Screenshots/UIRefreshWeb/SortAndFilter/"+sortBy.get(i)+".png");
				logger.info("Sort By "+sortBy.get(i).getText()+" filter option is displayed and clicked #Pass");
			}
		}
		ClickOnWebElement(viewResult);
		logger.info("View results button for sort & filter is clicked #Pass");
	}
	
	
	public void readingChallengeValidation() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		WaitForWebElement(lbl_readingChallengeHeader);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/ReadingChallenge/ReadingChallenge.png");
		WaitForWebElement(lbl_readingChallengeHeader);
		Assert.assertEquals(lbl_readingChallengeName.getText(),chlngName);
		logger.info("Reading Challenge Name is available #Pass");
		Assert.assertEquals(lbl_readingChallengeDesc.getText(), testData.get(0).get("Challenge Desc"));
		logger.info("Reading Challenge Description is available #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/ReadingChallenge/ReadingChallenge2.png");
		logger.info("--Reading Challenge validation completed--");
	}
	
	public void editChallenge() throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(7000);
		waitForElementClick(btn_EditChlg);
		logger.info("-- Edited Challenge assertion started --");
		WaitForWebElement(lbl_editchlgHeader);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/EditReadingChallenge/BeforeEditChallenge.png");
		SendKeysOnWebElement(RWDChallengenametextbar, chlngName + testData.get(0).get("challengeNameEdit") );
		logger.info("Edited Challenge Name #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/EditReadingChallenge/EditChallengeName.png");
		SendKeysOnWebElement(RWDChallengedescriptiontextbar, testData.get(0).get("ChallengeDescEdit"));
		RWDChallengedescriptiontextbar.sendKeys(" ");
		logger.info("Edited Challenge Description #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/EditReadingChallenge/EditChallengeDesc.png");
		//ClickOnWebElement(RWDaddfriendCTA);
	//	waitFor(9000);
	//	addMultipleStudent(1); //Add more participant validation
		//ClickOnWebElement(RWDAddToChallenge);
		//setReminder(true); //Set Remainder validation
		logger.info("Edited Challenge Remainder #Pass");
		readBy(); //Read by date validation
		logger.info("Edited Challenge ReadBy date #Pass");
		//addTitleMore();
		ClickOnWebElement(save);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/EditReadingChallenge/SaveEdit.png");
		logger.info("Saved Edited Challenge #Pass");
		//Edit Validation in Reading challenge page
		waitFor(2000);
		WaitForWebElement(lbl_readingChallengeHeader);
		waitFor(4000);
		if(ElementisPresent(error1)) {
			logger.info("Challenge name is already exist");
			driver.quit();
		}
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/EditReadingChallenge/SaveEdit1.png");
		Assert.assertEquals(lbl_readingChallengeName.getText(), chlngName + testData.get(0).get("challengeNameEdit"));
		logger.info("Edited Reading Challenge Name is available #Pass");
		Assert.assertEquals(lbl_readingChallengeDesc.getText(),testData.get(0).get("ChallengeDescEdit"));
		logger.info("Edited Reading Challenge Description is available #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/EditReadingChallenge/AfterEditChallenge.png");
		logger.info("-- Edited Challenge assertion Completed --");
	}

	public void cancelEditValidation() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		WaitForWebElement(lbl_readingChallengeHeader);
		ClickOnWebElement(btn_EditChlg);
		WaitForWebElement(RWDChallengenametextbar);
		SendKeysOnWebElement(RWDChallengenametextbar, chlngName + testData.get(0).get("challengeNameEdit"));
		logger.info("Edited Challenge Name #Pass");
		SendKeysOnWebElement(RWDChallengedescriptiontextbar, testData.get(0).get("ChallengeDescEdit"));
		RWDChallengedescriptiontextbar.sendKeys(" ");
		logger.info("Edited Challenge Description #Pass");
		ClickOnWebElement(btn_cancel);
		logger.info("Cancel button is clicked and edit is cancelled #Pass");
	}
	
	public void validateLogout() throws InvalidFormatException, IOException {
		logger.info("-- Logout Page Validation Start --");
		waitFor(3000);
		ClickOnWebElement(menu_profileIcon);
		Screenshots.takeScreenshot(driver, "Screenshots/CreateChallenge/Logout/LogoutMenu.png");
		ClickOnWebElement(menu_Logout);
		logger.info("Logout button is displayed and clicked #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/CreateChallenge/Logout/LogoutPage.png");
		WaitForWebElement(lnk_Home);
		logger.info("-- Logout Page Validation End --");
	}
	public void challengeSearch() {
		for(int i=0; i<= 5; i++) {
			waitFor(2000);
			javaScriptScrollToEnd();
			waitFor(2000);
			javascriptScroll(lbl_ActiveChallenge);
			try {
				WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
				javascriptScroll(footer);
				waitFor(2000);
				javascriptScroll(ss);
				break;
			}
			catch (Exception e){
				System.out.println("Scrolling again for click");
				javascriptScroll(footer);
				waitFor(2000);
			}	
		}
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
		logger.info(ss.getText()+" is found in challenge listing screen and opened #Pass");
		jsClick(ss);
	}
	
	public void acceptRejectChallenge(boolean accept) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		WaitForWebElement(RWDbookClubOptionWeb);
		ClickOnWebElement(RWDbookClubOptionWeb);
		WaitForWebElement(lbl_ActiveChallenge);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/ActiveChallenge.png");
		logger.info("--Accept or Reject challenge validation started--");
		Assert.assertEquals(lbl_ActiveChallenge.getText(), testData.get(0).get("lbl_activeChlng"));
		logger.info("Active challenges header is available #Pass");
		javascriptScroll(footer);
		waitFor(8000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
			javascriptScroll(ss);
		}
		catch (Exception e){
			System.out.println("Scrolling again");
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		logger.info("Created challenge is available for Invited user #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreatedChallenge.png");
		challengeSearch(); // search and find the challenge in Challenge listing screen
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
		//jsClick(ss);
		logger.info("Created challenge is opened for the participant #Pass");
		WaitForWebElement(readingChallengeName);
		ClickOnWebElement(close);
		logger.info("Close button is available and clicked #Pass");
		int reportedChlng = reportedChlngs.size();
		for(int i=0; i<=activeChlngs.size()+reportedChlng; i++) {
			//System.out.println(challengeName.get(i).getText())
			if(challengeName.get(i).getText().equalsIgnoreCase(ss.getText())) {
				try {
					javascriptScroll(challengeStatus.get(i-reportedChlng-acceptedClng.size()-1));
					Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/ChallengeStatus.png");
				//	System.out.println(challengeStatus.get(i-reportedChlng).getText());
					Assert.assertEquals(challengeStatus.get(i-reportedChlng-acceptedClng.size()-1).getText(),"You've been Invited!");
					logger.info("Challenge status before accept is "+challengeStatus.get(i-reportedChlng-acceptedClng.size()-1).getText()+" #Pass");
					jsClick(ss);
					
				}
				catch (ArrayIndexOutOfBoundsException e) {
					logger.info("Challenge status before accept validation is skipped #Fail");
					jsClick(ss);
					break;
				}	
		}	
	}
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreatedChallenge1.png");
		WaitForWebElement(readingChallengeName);
		
		Assert.assertEquals(readingChallengeName.getText(), chlngName + testData.get(0).get("challengeNameEdit") ); //
		logger.info("Created challenge name is available for participant #Pass");
		Screenshots.takeScreenshot(driver,"Screenshots/BeforeAccept/ReadingChallenge.png");
		
		Assert.assertEquals(readingChallengeDesc.getText(),testData.get(0).get("ChallengeDescEdit")); //
		logger.info("Created challenge details are available for Invited user #Pass");
		
		Assert.assertTrue(createdDate.isDisplayed(),"Challenge created date is not available");
		logger.info("Challenge Created date is available for Invited user #Pass");
		
		Assert.assertTrue(participantHeader.isDisplayed(),"Participants header is not available");
		logger.info("Paticipants header is available for Invited user #Pass");
		participantsB4Accept();
		
		Assert.assertTrue(Readinglist.isDisplayed(),"Reading list header is not available");
		logger.info("Reading list header is available for Invited user #Pass");
		booksB4Accept();
		
		Assert.assertTrue(RWDchlgAccept.isDisplayed(),"Accept challenge button is not available");
		logger.info("Accept challenge button is available for participant #Pass ");
		
		Assert.assertTrue(RWDchlgNotAccept.isDisplayed(),"Accept challenge button is not available");
		logger.info("No thanks challenge button is available for participant #Pass ");
		
		if(accept==true) {
			ClickOnWebElement(RWDchlgAccept);
			waitFor(1000);
			logger.info("Accept challenge button is Clicked for participant #Pass ");
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/AcceptChallenge.png");
			waitFor(1000);
			Assert.assertEquals(acceptedMessage.getText(), testData.get(0).get("challengeAccept"));
			logger.info("Positive reinforcement message for accepting the challenge is available for participant #Pass ");
			ClickOnWebElement(GotoChlng);
			logger.info("Goto challenge button is available and Clicked for participant #Pass ");
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/AcceptedChallenge.png");
		}
		else {
			ClickOnWebElement(RWDchlgNotAccept);
			logger.info("Accept challenge button is clicked for participant #Pass ");
		}
		logger.info("--Accept or Reject challenge validation Completed--");
	}
	
	@FindBy(xpath="//label[@class='ng-star-inserted']")
	public List<WebElement> acceptedClng;
	
	public void participantsB4Accept() throws IOException {
		javascriptScroll(participantHeader);
		Assert.assertTrue(participantListB4.size()>0);
		Screenshots.takeScreenshot(driver,"Screenshots/BeforeAccept/participantList.png");
		logger.info("User able to see the participants before accepting the Reading challenge #Pass");
		for(int i=0; i<=participantListB4.size()-1; i++) {
			logger.info("Invited participant is "+ participantListB4.get(i).getText());
		}
	}

	public void booksB4Accept() throws IOException {
		javascriptScroll(Readinglist);
		Assert.assertTrue(bookList.size()>0);
		Screenshots.takeScreenshot(driver,"Screenshots/ParticipantScreen/ReadingList.png");
		logger.info("Reading list header is available for the Reading challenge #Pass");
		for(int i=0; i<=bookList.size()-1; i++) {
			int j = i+1;
			logger.info("Added title "+j+" is available for participants");
		}
	}
	
	public void chlgDashboard() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		ClickOnWebElement(RWDbookClubOptionWeb);
		WaitForWebElement(lbl_ActiveChallenge);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/ReadingChallenge/ActiveChallenge.png");
		logger.info("-- Challenge dashboard validation for creatot started--");
		Assert.assertEquals(lbl_ActiveChallenge.getText(), testData.get(0).get("lbl_activeChlng"));
		logger.info("Active challenges header is available #Pass");
		javascriptScroll(footer);
		waitFor(8000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
			javascriptScroll(ss);
		}
		catch (Exception e){
			System.out.println("Scrolling again");
			javascriptScroll(footer);
			waitFor(2000);
			javaScriptScrollToEnd();
		}
		waitFor(7000);
		javaScriptScrollToEnd();
		javascriptScroll(lbl_ActiveChallenge);
		javascriptScroll(footer);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
			javascriptScroll(ss);
		}
		catch (Exception e){
			javascriptScroll(lbl_ActiveChallenge);
			javascriptScroll(footer);
		}
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + chlngName + "')]"));
		jsClick(ss);
		logger.info("Created challenge is available in creator dashboard #Pass");
		logger.info("-- Challenge dashboard validation for creator Completed--");
	}
	
	public void Messagecenter() throws IOException {
		WaitForWebElement(msgCenter);
		logger.info("--Message centre assertion started--");
		ClickOnWebElement(msgCenter);
		if(ElementisPresent(RWDnoMessages)) {
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
		logger.info("Messages Center! You have No Messages! #Pass");
		ClickOnWebElement(msgclose);
		}else {
		logger.info("Messages are displayed in Message center #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
		waitFor(4000);
		validateMessage();
		logger.info("--Message centre assertion Completed--");
		}
	}
	
	public void FilterMsg() throws InvalidFormatException, IOException {	
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Message centre filter assertion started--");
		ClickOnWebElement(msgDropdown);
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/FilterOptions.png");
		waitFor(2000);
		switch(testData.get(0).get("FilterType").toUpperCase()) {
			case "ALL":
				ClickOnWebElement(RWDMessageAll);
				logger.info(RWDMessageAll.getText()+" is selected in message cenre filter #Pass");
				Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/filter"+RWDMessageAll.getText()+".png");
				break;
			case "NONE":
				ClickOnWebElement(RWDMessageNone);
				logger.info(RWDMessageNone.getText()+" is selected in message cenre filter #Pass");
				Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/filter"+RWDMessageNone.getText()+".png");
				break;
			case "READ":
				ClickOnWebElement(RWDMessageRead);
				logger.info(RWDMessageRead.getText()+" is selected in message cenre filter #Pass");
				Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/filter"+RWDMessageRead.getText()+".png");
				break;
			case "UNREAD":
				ClickOnWebElement(RWDMessageUnread);
				logger.info(RWDMessageUnread.getText()+" is selected in message cenre filter #Pass");
				Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/filter"+RWDMessageUnread.getText()+".png");
				break;
			default:
				logger.info("Provided type is not matched");
				//Assert.assertFalse("Provided type is not matched",true);
		}
		logger.info("Filter option is available #Pass");
		logger.info("--Message centre filter assertion Completed--");
	}
	
	public void validateMessage() {
	waitFor(3000);
	for (int i = 1; i <= RWDInviteText.size(); i++) {
	String firstMsgTime = RWDInviteText.get(0).findElement(By.tagName("span")).getText();
	String secondMsgTime = RWDInviteText.get(1).findElement(By.tagName("span")).getText();
	int compared = firstMsgTime.compareTo(secondMsgTime);
	if (compared >= 1 || compared==0) {
	logger.info("Verify latest message appears on top of the message list #Pass");
	break;
	} 
	else {
	 Assert.fail("Last message displayed Not Properly");
	}
	}
}
	
	public void CheckInviteMessage() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Message centre - Invite Message validation Started--");
		//ClickOnWebElement();
		for(int i=1; i<= msgHeader.size()-1; i++ ) {
			System.out.println(i +" : "+ msgHeader.get(i).getText());
			if(msgHeader.get(i).getText().equalsIgnoreCase("Challenge Renamed")) {
				//Assert.assertEquals(msgHeader.get(i).getText(), testData.get(0).get("InviteMessage"));
				logger.info("Invited challenge message is available for participant #Pass");
				javascriptScroll(msgHeader.get(i));
				ClickOnWebElement(msgHeader.get(i));
				Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/InviteMessage.png");
				waitFor(9000);
				//WaitForWebElement(viewChlng.get(i));
				//javascriptScroll(viewChlng.get(i));
				logger.info("Invited challenge details & view challenge details button is available for participant #Pass");
				System.out.println(i +" : "+ RwdmsgDetails.get(i).getText());
				if(RwdmsgDetails.get(i).getText().equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {  //+ testData1.get(0).get("challengeNameEdit")
					javascriptScroll(viewChlng.get(i));
					ClickOnWebElement(viewChlng.get(i));
					logger.info("Invited challenge details from message center #Pass");
					Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/ChallengeDetails.png");
					logger.info("--Message centre -Invite Message validation Completed--");
					break;
				}
			}
			else {
				int size = msgHeader.size();
				javascriptScroll(msgHeader.get(size-1));
				logger.info("Scrolling to the ");
			}
			
		}
	}
	
	public void CheckReportAbuse() throws IOException, InvalidFormatException {
    	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "ReportAbuse");
    	logger.info("--Report abuse assertion started--");
       
    	WaitForWebElement(lbl_readingChallengeHeader);
        Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/reportabusetext.png");
        Assert.assertTrue(RWDreportabusebtn.isDisplayed(), "Report Abuse button is not displayed");
        logger.info("Report abuse button is displayed #Pass");
        waitFor(2000);
        javascriptScroll(lbl_readingChallengeHeader);
        ClickOnWebElement(RWDreportabusebtn);
        waitFor(2000);
        Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/reportabusescreen.png");
        Assert.assertTrue(RWDreportheader.isDisplayed(), "Report Abuse");
        logger.info("Report Header Text displayed #Pass");
        Assert.assertTrue(RWDreportText.isDisplayed(), "Please provide details about your concern regarding the Reading Challenge.");
        SendKeysOnWebElement(RWDreport_abuseDesc, testData.get(0).get("Description").trim());
        Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/reportabuseErrorscreen.png");
        Assert.assertTrue(RWDErrormsg.isDisplayed(), "Minimum 15 characters");
        logger.info("User should get Error if typed minimum Character #Pass");
        RWDreport_abuseDesc.clear();
        waitFor(5000);
        //Cancel button validation for report abuse
        ClickOnWebElement(RWDreport_cancel);
        logger.info("Cancel button is clicked in report abuse #Pass");
        WaitForWebElement(lbl_readingChallengeHeader);
        logger.info("Navigated back to the Reading challenge details screen #Pass");
        Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/Cancel_Back.png");
        ClickOnWebElement(RWDreportabusebtn);
        waitFor(2000);
        
        SendKeysOnWebElement(RWDreport_abuseDesc, testData.get(1).get("Description").trim());
        logger.info("User should able to submit report abuse if Maximum character typed #Pass");
        Assert.assertTrue(RWDreport_submit.isDisplayed(), "Submit");
        //ClickOnWebElement(RWDreport_submit);
        ClickOnWebElement(RWDreport_cancel);
        logger.info("User should able to navigate back to Reading challenge listing screen #Pass");
        Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/Readingchallengescreen.png");
        logger.info("--Report abuse assertion Completed--");
    }
	
	public void participantScreen() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "Participant screen");
		logger.info("--Participant screen validation started--");
		WaitForWebElement(RWDbookClubOptionWeb);
		ClickOnWebElement(RWDbookClubOptionWeb);
		WaitForWebElement(CCHeader);
		challengeSearch();
		WaitForWebElement(lbl_readingChallengeHeader);
		Screenshots.takeScreenshot(driver,"Screenshots/ParticipantScreen/ParticipantScreen.png");
		
		Assert.assertTrue(ElementisPresent(breadcrumbList),"BreadCrumb is not available for participant");
		logger.info("Breadcrumb is available for participant #Pass");
		
		Assert.assertEquals(participantChallengeName.getText(), chlngName + testData.get(0).get("challengeNameEdit")); //
		logger.info("Created challenge name is available for participant #Pass");
		Screenshots.takeScreenshot(driver,"Screenshots/ParticipantScreen/ReadingChallenge.png");
		
		Assert.assertEquals(participantChallengeDesc.getText(),testData.get(0).get("ChallengeDescEdit")); //
		logger.info("Created challenge details are available for Invited user #Pass");
		
		//Issue raised 82255 
		//Assert.assertTrue(avatar.isDisplayed(),"Participant Avatar is not available");
		//logger.info("Participant Avatar is available for participant #Pass");

		//Assert.assertTrue(createdUser.isDisplayed(),"Created user profile is not available");
		//logger.info("Created user profile is available for participant #Pass");
		
		Assert.assertEquals(RWDreportabusebtn.getText().trim(), testData1.get(0).get("lbl_reportAbuse").trim());
		logger.info("Report abuse button is available for participant #Pass");
		String leaveChlg=leaveChlng.getText().substring(0, 15);
		Assert.assertEquals(leaveChlg, testData1.get(0).get("lbl_leaveChlg").trim());
		logger.info("Leave challenge button is available for participant #Pass");
		
		//Assert.assertEquals(Readinglist.getText().trim(),testData1.get(0).get("lbl_readingList").trim());
		logger.info("Reading list header is available for participant #Pass");
		titles();  //Reading list validation
		
		participants(); //Participant list validation
		
		Screenshots.takeScreenshot(driver,"Screenshots/ParticipantScreen/ReadingChallenge1.png");
		logger.info("--Participant screen validation Completed--");
	}
	
	public void participants() throws IOException {
		javascriptScroll(participantHeader1);
		Assert.assertTrue(participantList.size()>0);
		Screenshots.takeScreenshot(driver,"Screenshots/ParticipantScreen/participantList.png");
		logger.info("Participants are available for the Reading challenge #Pass");
		for(int i=0; i<=participantList.size()-1; i++) {
			logger.info("Invited participant is "+ participantList.get(i).getText()+" #Pass");
		}
	}
	
	public void titles() throws IOException {
		javascriptScroll(Readinglist1);
		Assert.assertTrue(bookList.size()>0);
		Screenshots.takeScreenshot(driver,"Screenshots/ParticipantScreen/ReadingList.png");
		logger.info("Reading list header is available for the Reading challenge #Pass");
		for(int i=0; i<=bookList.size()-1; i++) {
			int j = i+1;
			logger.info("Added title "+j+" is available for participants #Pass");
			Assert.assertTrue(titleProgress.get(i).isDisplayed(),"Title progress is not available");
			logger.info("Title progress is available for each "+j+" title #Pass");
		}
	}
	
	public void leaveChlng() throws IOException {
		logger.info("--Leave challenge validation started--");
		WaitForWebElement(lbl_readingChallengeHeader);
		javascriptScroll(lbl_readingChallengeHeader);
		Screenshots.takeScreenshot(driver,"Screenshots/ParticipantScreen/LeaveChallenge.png");
		ClickOnWebElement(leaveChlng);
		logger.info("Leave challenge button is clicked #Pass");
		WaitForWebElement(lbl_ActiveChallenge);
		logger.info("Participant left the challenge and redirected to challenge listing page #Pass");
		logger.info("--Leave challenge validation completed--");
	}

    public void removeParticipant() throws Exception{
        System.out.println("RWDRemoveParticipants"+RWDRemoveParticipants.size());
        List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
        if(RWDRemoveParticipants.size()>0){
        	closeX.get(0).click();
            waitFor(3000);
            logger.info("Remove Friend From Participants #Pass");
            Assert.assertTrue(ElementisPresent(RwdConfirm));
            logger.info("Remove confirmation messge is displayed #Pass");
            ClickOnWebElement(RWDRemoveFriend);
            logger.info("Remove button is clicked on confirmation messge #Pass");
            Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/Notification/RemoveFriend.png");
        }
        else{
        	throw new Exception("No recent participants");    
        }
    } 
    
    @FindBy(xpath="//*[@id='chlg-user-modal']/button/mat-icon")
    public List<WebElement> closeX;

    public void addTitleMore() throws InvalidFormatException, IOException {
        List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
    	logger.info("--Add title assertion started--");
        Assert.assertEquals(addTitleHeader.getText(), testData.get(0).get("defval_addTitle"));
        logger.info("Add title to challenge header is present #Pass");
        ClickOnWebElement(RWD_AddTitleCTA);
        WaitForWebElement(RWDSearchInput);
        Assert.assertEquals(searchTitleHeader.getText(), testData.get(0).get("defval_searchTitleHeader"));
        Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle.png");
        logger.info("Title search header is available #Pass");
        SendKeysOnWebElement(RWDSearchInput, testData.get(2).get("titleName"));
        //Assert.assertEquals(lbl_suggestedResult.getText(), testData.get(0).get("defval_suggestedResult"));
        Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_SuggestedResult.png");
        logger.info("Suggested result header is available #Pass");
        //Assert.assertTrue(ElementisPresent(closeIcon));
        logger.info("Title search close icon is available #Pass");
        //SendKeysEnter(RWDSearchInput);
        logger.info("Title name is entered #Pass");
        Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_SearchResult.png");
        waitFor(4000);
        ClickOnWebElement(RWDMoreMenu);
        Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_MoreMenu.png");
        logger.info("More menu is clicked for the titile #Pass");
        //Assert.assertTrue(Btn_checkout.isDisplayed(),"Checkout button is not displayed");
        logger.info("Checkout button is available for title #Pass");
        ClickOnWebElement(AddToChallengeTitle);
        Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_ToChallenge.png");
        logger.info("Title is added to the challenge #Pass");
        logger.info(" --Add title assertion completed-- ");
    }
    
	@FindBy(xpath="//*[@id=\"search-title-mat-drawer\"]/div/fss-ms-search-result-titles/div/div/div[2]/fss-ms-title-card-list/section/div/mat-card/fss-ms-action-buttons/button/mat-icon")
	public WebElement RWDButton;
	
	@FindBy(xpath="//*[@class='user-poster ng-star-inserted']")
	public List<WebElement> RWDListofTitle;
	
	@FindBy(id="chlg-remov-1")
	public WebElement RWDRemoveTitle;
	
	
    public void removeTitleEdit() throws Exception{
      //  List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");

    	if(RWDListofTitle.size()>0) {
            RWDListofTitle.get(1).click();
            waitFor(3000);
            logger.info("Remove Friend From Participants#Pass");
            ClickOnWebElement(RWDRemoveTitle);
            Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/Notification/RemoveTitle.png");

    	}else{
        	throw new Exception("No Title Available");    
    	}
    }

	
public void messageCenterValidation() throws IOException, InvalidFormatException {
		
		waitFor(3000);
		if (ElementisPresent(RWDnoMessages)) {
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
			logger.info("Messages Center! You have No Messages! #Pass");
			ClickOnWebElement(msgclose);
		} else {
			logger.info("Messages are displayed in Message center #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
			waitFor(4000);
			List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
			List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
			logger.info("--Message centre - Invite Message validation Started--");
			for (int i = 0; i <=msgHeader.size() ; i++) {
				waitFor(3000);
				try {
					if (msgHeader.get(i).getText().equalsIgnoreCase("You've been invited!")) {
						Assert.assertEquals(msgHeader.get(i).getText(), testData.get(0).get("InviteMessage"));
						ClickOnWebElement(msgHeader.get(i));
						waitFor(7000);
						if (RwdmsgDetails.get(i).getText()
								.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
							logger.info("Invited challenge message is available for participant #Pass");
							ClickOnWebElement(msgHeader.get(i));
							break;
						} else {
							ClickOnWebElement(msgHeader.get(i));

						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("The index you have entered is invalid skip ");
				}
				if (msgHeader.get(i).getText().equalsIgnoreCase("Challenge Renamed")) {

					Assert.assertEquals(msgHeader.get(i).getText(), testData.get(1).get("InviteMessage"));
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
							.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
						logger.info("user should be able to view message for Scenario- RC Update name #Pass");
						ClickOnWebElement(msgHeader.get(i));
						break;
					} else {
						ClickOnWebElement(msgHeader.get(i));
					}
				}
				if (msgHeader.get(i).getText().equalsIgnoreCase("Challenge Date Changed")) {

					Assert.assertEquals(msgHeader.get(i).getText().trim(), testData.get(2).get("InviteMessage").trim());
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
							.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
						logger.info("user should be able to  view message for Scenario- RC Update end date #Pass");
						ClickOnWebElement(msgHeader.get(i));
						break;
					} else {
						ClickOnWebElement(msgHeader.get(i));
					}
				}
				if (msgHeader.get(i).getText().equalsIgnoreCase("Title Added")) {

					Assert.assertEquals(msgHeader.get(i).getText().trim(), testData.get(3).get("InviteMessage").trim());
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
							.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
						logger.info("user should be able to view message  for Scenario- RC Add new title #Pass");
						ClickOnWebElement(msgHeader.get(i));
						break;
					} else {
						ClickOnWebElement(msgHeader.get(i));
					}
				}
				if (msgHeader.get(i).getText().equalsIgnoreCase("Title Removed")) {

					Assert.assertEquals(msgHeader.get(i).getText().trim(), testData.get(4).get("InviteMessage").trim());
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
							.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
						logger.info("user should be able to view message for Scenario- RC Remove title #Pass");
						ClickOnWebElement(msgHeader.get(i));
						break;
					} else {
						ClickOnWebElement(msgHeader.get(i));
					}
				}
				if (msgHeader.get(i).getText().equalsIgnoreCase("Invite Accepted")) {

					Assert.assertEquals(msgHeader.get(i).getText().trim(), testData.get(6).get("InviteMessage").trim());
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
							.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
						logger.info("To verify if user should be able to view message for Scenario- RC Invite accepted #Pass");
						ClickOnWebElement(msgHeader.get(i));
						break;
					} else {
						ClickOnWebElement(msgHeader.get(i));
					}
				}
				if (msgHeader.get(i).getText().equalsIgnoreCase("Invite Rejected")) {

					Assert.assertEquals(msgHeader.get(i).getText().trim(), testData.get(5).get("InviteMessage").trim());
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
							.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
						logger.info("user should be able to view message and for Scenario- RC Invite rejected #Pass");
						ClickOnWebElement(msgHeader.get(i));
						break;
					} else {
						ClickOnWebElement(msgHeader.get(i));
					}
				}
				if (msgHeader.get(i).getText().contains("leave challenges")) {

					logger.info("user should not able to view any message for Scenario- RC Leave RC #Pass");
					break;
				}

			}

		}
	}

	public void messageCenterValidationAdmin() throws InvalidFormatException, Exception{
		ClickOnWebElement(msgCenter);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
		for (int i = 0; i <=msgHeader.size() ; i++) {
			waitFor(5000);
			try {
				if (msgHeader.get(i).getText().equalsIgnoreCase("Abuse report submitted")) {
					Assert.assertEquals(msgHeader.get(i).getText(), testData.get(7).get("InviteMessage"));
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
						.equalsIgnoreCase(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("user should be able to view message for Scenario- RC Abuse reported #Pass");
					ClickOnWebElement(msgHeader.get(i));
					break;
				} else {
					ClickOnWebElement(msgHeader.get(i));

				}
			}
			javaScriptScrollToEnd();
			} 
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("The index you have entered is invalid skip ");
			}	
		}
	}
	
	public void loginMessagecenter() throws IOException {
		waitFor(7000);
		if (ElementisPresent(msgNotificationCount)) {
			Assert.assertTrue(msgNotificationCount.isEnabled());
			logger.info("Message Center Bell icon Displayed #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterBellIcon.png");
			logger.info("Unread Messages Displayed in top of hamburger Menu #Pass");
			
		} else {
			logger.info("Message Center Bell icon Not Displayed");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterNotBellIcon.png");
		}
		logger.info("User able to click on bell icon to navigate to messages listing page #Pass");
		ClickOnWebElement(RWDMessagecenter);
		if (ElementisPresent(RWDnoMessages)) {
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/noMessages.png");
			logger.info("Login into message Center! You have No Messages! #Pass");
			ClickOnWebElement(msgclose);
		} else {
			logger.info("Login into Message Center #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
		}
	}
	
public void dropdownMsg() throws IOException, InvalidFormatException {
		if(ElementisPresent(Rwddropdwon)) {
			ClickOnWebElement(Rwddropdwon);
			logger.info("User able to click on select dropdown to view the select menu options #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
			testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
			String all =testData.get(0).get("MessageView").trim();
			if(all.equalsIgnoreCase("all"))
			{
			waitFor(2000);
			ClickOnWebElement(RWDMessageAll);	
			logger.info("User able to click on the select all checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterAllselectCheckbox.png");
			}
			String none =testData.get(1).get("MessageView").trim();
			if(none.equalsIgnoreCase("none"))
			{
			waitFor(2000);
			ClickOnWebElement(Rwddropdwon);
			ClickOnWebElement(RWDMessageNone);	
			logger.info("User able to click on the un select all checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterUnselectCheckbox.png");
			}
			String read =testData.get(2).get("MessageView").trim();
			if(read.equalsIgnoreCase("read"))
			{
			waitFor(2000);
			ClickOnWebElement(Rwddropdwon);
			ClickOnWebElement(RWDMessageRead);	
			logger.info("User able to select Read message checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterselectReadCheckbox.png");
			}
			String unread =testData.get(3).get("MessageView").trim();
			if(unread.equalsIgnoreCase("unread"))
			{
			waitFor(2000);
			ClickOnWebElement(Rwddropdwon);
			ClickOnWebElement(RWDMessageUnread);	
			logger.info("User able to select on the Unread messages checkbox #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenterselectUnreadCheckbox.png");
			}
			waitFor(2000);
			ClickOnWebElement(Rwddropdwon);
			ClickOnWebElement(RWDMessageNone);
			waitFor(2000);
			/*
			 * ClickOnWebElement(RwdmsgCheckbox); waitFor(3000);
			 * logger.info("user able to view the delete confirmation pop-up​ #Pass");
			 * ClickOnWebElement(deletemsgall); waitFor(1000); logger.
			 * info("user able to view the pop-up text with the number of messages selected for deletion​ #Pass"
			 * ); Screenshots.takeScreenshot(driver,
			 * "./Screenshots/MessageCenter/messagecenterDelete.png");
			 * ClickOnWebElement(RWDdeletepopmsg);
			 */
			logger.info("Verify the user able to delete Messages from the list #Pass");
			waitFor(8000);
			ClickOnWebElement(RwdmsgCheckbox);
			waitFor(2000);
			ClickOnWebElement(deletemsgall);
			waitFor(2000);
			logger.info("User should be able to click Cancel CTA to cancel deletion and navigate back to the listing #Pass");
			Assert.assertEquals(RWDcancelpopup.getText(), "Cancel");
			ClickOnWebElement(RWDcancelpopup);
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/Backtocancelctamessagecenter.png");
			waitFor(3000);
			ClickOnWebElement(Rwddropdwon);
			waitFor(1000);
			ClickOnWebElement(RWDnone);
			message_details();
		}
		else{
			logger.info("No messages displayed #Fail");
		}
	}

public void message_details() throws IOException {
	for (WebElement Allele : RWDInviteText) {

		if (Allele.findElement(By.tagName("h4")).getText().equalsIgnoreCase("You've been invited!")) {
			Allele.click();
			waitFor(4000);
			logger.info("User able to view the message details in an expanded fashion​ #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/MessageDetails.png");
			waitFor(4000);
			//Assert.assertTrue(!RwdmsgDetails.findElement(By.tagName("h4")).getText().isEmpty());
			logger.info("User able to see the message title​ #Pass");
			Assert.assertTrue(!Allele.findElement(By.tagName("span")).getText().isEmpty());
			logger.info("User able to view the time at which message was received​ #Pass");
			waitFor(5000);
		//	javascriptScroll(viewChlng);
			logger.info("User should be able to see message heading for Reading List​ #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/ReadingList.png");
			waitFor(5000);
		//	ClickOnWebElement(viewChlng);
			waitFor(2000);
			logger.info("User able to view a See challenge details CTA if applicable to the message and tap on it to navigate View Details screen #Pass");
			Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/ViewdetailsScreen.png");
			break;
		}
	}
}

public void navigateToReportAbuse() throws IOException {
	waitFor(4000);
	WaitForWebElement(lbl_readingChallengeHeader);
    Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/reportabusetext.png");
    Assert.assertTrue(RWDreportabusebtn.isDisplayed(), "Report Abuse button is displayed");
    logger.info("Report abuse button is displayed #Pass");
    waitFor(2000);
    javascriptScroll(lbl_readingChallengeHeader);
    ClickOnWebElement(RWDreportabusebtn);
    waitFor(2000);
}

public void maxlengthchar() throws IOException, InvalidFormatException{
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "ReportAbuse");
    waitFor(2000);
    SendKeysOnWebElement(RWDreport_abuseDesc, testData.get(1).get("Description").trim());
    logger.info("User should able to submit report abuse if Maximum character typed #Pass");
    Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/reportsubmitscreen.png");
    Assert.assertTrue(RWDreport_submit.isDisplayed(), "Submit");
    ClickOnWebElement(RWDreport_submit);
	
}

public void minlengthchar() throws IOException, InvalidFormatException {
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "ReportAbuse");
	Assert.assertTrue(RWDreportheader.isDisplayed(), "Report Abuse");
    logger.info("Report Header Text displayed #Pass");
    Assert.assertTrue(RWDreportText.isDisplayed(), "Please provide details about your concern regarding the Reading Challenge.");
    SendKeysOnWebElement(RWDreport_abuseDesc, testData.get(0).get("Description").trim());
    Screenshots.takeScreenshot(driver,"Screenshots/ReportAbuse/reportabuseErrorscreen.png");
    Assert.assertTrue(RWDErrormsg.isDisplayed(), "Minimum 15 characters");
    logger.info("User should get Error if typed minimum Character #Pass");
    RWDreport_abuseDesc.clear();
    waitFor(3000);
	
}

public void checkInviteMessage() throws IOException, InvalidFormatException {
	if (ElementisPresent(RWDnoMessages)) {
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
		logger.info("Messages Center! You have No Messages! #Pass");
		 ClickOnWebElement(msgclose);
	} else {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("Messages are displayed in Message center #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/MessageCenter/messagecenter.png");
		waitFor(3000);
		logger.info("--Message centre - Invite Message validation Started--");
		for (int i = 0; i <= 10; i++) {
			waitFor(3000);
			try {
				if (msgHeader.get(i).getText().equalsIgnoreCase("You've been invited!")) {
					Assert.assertEquals(msgHeader.get(i).getText(), testData.get(0).get("InviteMessage"));
					ClickOnWebElement(msgHeader.get(i));
					waitFor(7000);
					if (RwdmsgDetails.get(i).getText()
							.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
						logger.info("Invited challenge message is available for participant #Pass");
						ClickOnWebElement(msgHeader.get(i));
						break;
					} else {
						ClickOnWebElement(msgHeader.get(i));
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("The index you have entered is invalid skip ");
			}
		}
	}
}
public void checkRCUpdateName() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
	List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
	waitFor(3000);
	for (int i = 0; i <= 10; i++) {
		waitFor(3000);
		try {
			if (msgHeader.get(i).getText().equalsIgnoreCase("Challenge Renamed")) {
				Assert.assertEquals(msgHeader.get(i).getText(), testData.get(1).get("InviteMessage"));
				ClickOnWebElement(msgHeader.get(i));
				waitFor(7000);
				if (RwdmsgDetails.get(i).getText()
						.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("User should be able to view message for Scenario- RC Update name #Pass");
					ClickOnWebElement(msgHeader.get(i));
					break;
				} else {
					ClickOnWebElement(msgHeader.get(i));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid skip ");
		}
	}
}
public void checkRCUpdateEndDate() throws InvalidFormatException, IOException {
	
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
	List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
	waitFor(3000);
	for (int i = 0; i <= 10; i++) {
		waitFor(3000);
		try {
			if (msgHeader.get(i).getText().equalsIgnoreCase("Challenge Date Changed")) {
				Assert.assertEquals(msgHeader.get(i).getText(), testData.get(2).get("InviteMessage"));
				ClickOnWebElement(msgHeader.get(i));
				waitFor(7000);
				if (RwdmsgDetails.get(i).getText()
						.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("User should be able to  view message for Scenario- RC Update end date #Pass");
					ClickOnWebElement(msgHeader.get(i));
					break;
				} else {
					ClickOnWebElement(msgHeader.get(i));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid skip ");
		}
	}
}
public void checkRCAddTitle() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
	List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
	waitFor(3000);
	for (int i = 0; i <= 10; i++) {
		waitFor(3000);
		try {
			if (msgHeader.get(i).getText().equalsIgnoreCase("Title Added")) {
				Assert.assertEquals(msgHeader.get(i).getText(), testData.get(3).get("InviteMessage"));
				ClickOnWebElement(msgHeader.get(i));
				waitFor(7000);
				if (RwdmsgDetails.get(i).getText()
						.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("User should be able to view message  for Scenario- RC Add new title #Pass");
					ClickOnWebElement(msgHeader.get(i));
					break;
				} else {
					ClickOnWebElement(msgHeader.get(i));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid skip ");
		}
	}
}
public void checkRCRemoveTitle() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
	List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
	waitFor(3000);
	for (int i = 0; i <= 10; i++) {
		waitFor(3000);
		try {
			if (msgHeader.get(i).getText().equalsIgnoreCase("Title Removed")) {
				Assert.assertEquals(msgHeader.get(i).getText(), testData.get(4).get("InviteMessage"));
				ClickOnWebElement(msgHeader.get(i));
				waitFor(7000);
				if (RwdmsgDetails.get(i).getText()
						.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("User should be able to view message for Scenario- RC Remove title #Pass");
					ClickOnWebElement(msgHeader.get(i));
					break;
				} else {
					ClickOnWebElement(msgHeader.get(i));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid skip ");
		}
	}
}

public void checkInviteAccept() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
	List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
	waitFor(3000);
	for (int i = 0; i <= 10; i++) {
		waitFor(3000);
		try {
			if (msgHeader.get(i).getText().equalsIgnoreCase("Invite Accepted")) {
				Assert.assertEquals(msgHeader.get(i).getText(), testData.get(6).get("InviteMessage"));
				ClickOnWebElement(msgHeader.get(i));
				waitFor(7000);
				if (RwdmsgDetails.get(i).getText()
						.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("To verify if user should be able to view message for Scenario- RC Invite accepted #Pass");
					ClickOnWebElement(msgHeader.get(i));
					break;
				} else {
					ClickOnWebElement(msgHeader.get(i));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid skip ");
		}
	}
}
public void checkInviteRejected() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
	List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
	waitFor(3000);
	for (int i = 0; i <= 10; i++) {
		waitFor(3000);
		try {
			if (msgHeader.get(i).getText().equalsIgnoreCase("Invite Rejected")) {
				Assert.assertEquals(msgHeader.get(i).getText(), testData.get(5).get("InviteMessage"));
				ClickOnWebElement(msgHeader.get(i));
				waitFor(7000);
				if (RwdmsgDetails.get(i).getText()
						.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("user should be able to view message and for Scenario- RC Invite rejected #Pass");
					ClickOnWebElement(msgHeader.get(i));
					break;
				} else {
					ClickOnWebElement(msgHeader.get(i));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid skip ");
		}
	}
}

public void checkRCLeave() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
	List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
	waitFor(3000);
	for (int i = 0; i <= 10; i++) {
		waitFor(3000);
		try {
			if (msgHeader.get(i).getText().equalsIgnoreCase("leave challenges")) {
//				Assert.assertEquals(msgHeader.get(i).getText(), testData.get(5).get("InviteMessage"));
//				ClickOnWebElement(msgHeader.get(i));
//				waitFor(7000);
//				if (RwdmsgDetails.get(i).getText()
//						.contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
					logger.info("User should not able to view any message for Scenario- RC Leave RC #Pass");
//					ClickOnWebElement(msgHeader.get(i));
					break;
//				} else {
//					ClickOnWebElement(msgHeader.get(i));
//				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The index you have entered is invalid skip ");
		}
	}
}
public void checkAbuseMessage() throws InvalidFormatException, IOException {
    List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "MessageCenter");
    List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
    waitFor(3000);
    for (int i = 0; i <= 10; i++) {
        waitFor(3000);
        try {
            if (msgHeader.get(i).getText().equalsIgnoreCase("Abuse report submitted")) {
                Assert.assertEquals(msgHeader.get(i).getText(), testData.get(7).get("InviteMessage"));
                ClickOnWebElement(msgHeader.get(i));
                waitFor(7000);
                if (RwdmsgDetails.get(i).getText()
                        .contains(chlngName + testData1.get(0).get("challengeNameEdit"))) {
                    logger.info("User should be able to view message for Scenario- RC Abuse reported #Pass");
                    ClickOnWebElement(msgHeader.get(i));
                    break;
                } else {
                    ClickOnWebElement(msgHeader.get(i));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("The index you have entered is invalid skip ");
        }
    }
}

@FindBy(id = "msg-delete-all")
public  WebElement deletemsgall;

@FindBy(xpath="//*[text()='Cancel']")
private WebElement RWDcancelpopup;
	
@FindBy(xpath = "//*[@aria-label='None']")
public  WebElement RWDnone;

@FindBy(xpath="//span[text()='Delete']")
private WebElement RWDdeletepopmsg;

@FindBy(xpath="//*[@class='mat-checkbox msg-read-all mat-accent ng-star-inserted']")
public WebElement RwdmsgCheckbox;

@FindBy(id="msg-checkbox")
public WebElement Rwddropdwon;

	@FindBy(xpath="//span[@class='msg-badge-count ng-star-inserted']")
	public WebElement msgNotificationCount;
	
	@FindBy(xpath = "//*[@class='msg-icon-button icon-bg mr-rgt ng-star-inserted']")
	public  WebElement RWDMessagecenter;

	@FindBy(id = "chlg-user-modal")
	private List<WebElement> RWDRemoveParticipants;

	@FindBy(xpath="//*[@class='mat-dialog-content mat-typography']")
	public WebElement RwdConfirm;

	@FindBy(xpath="//span[text()='Remove']")
	public WebElement RWDRemoveFriend;
	
	@FindBy(xpath="//*[@class='mat-card-title header-title txt-elipsishead']")
	public List<WebElement> challengeName;
	
	@FindBy(xpath="//*[@class='hrs ng-star-inserted']")
	public List<WebElement> challengeStatus;
	
	@FindBy(xpath="//*[@id='chlg-report-comp']/div")
	public List<WebElement> reportedChlngs; 
	
	@FindBy(xpath=" //*[@id='chlg-act-comp']/div")
	public List<WebElement> activeChlngs;
	
	@FindBy(xpath=" //*[@id='chlg-act-comp']/div")
	public WebElement activeChlngs1;
	
	@FindBy(xpath="//h2[text()=' NO RESULTS FOUND ']")
	public WebElement lbl_noResult;
	
	@FindBy(xpath="//*[text()='Cancel']")
	public WebElement btn_cancel;
	
	@FindBy(xpath="//*[text()='Remove']")
	public WebElement btn_remove; 
	
	@FindBy(xpath="//*[@class='mat-dialog-content mat-typography']")
	public WebElement removeBookConfirmMsg;  
	
	@FindBy(xpath="//button[@class='dd-btnx center-img ng-star-inserted']/mat-icon")       
	public List<WebElement> removeBook; 
	
	@FindBy(xpath="//*[@class='plp-dis-flx']")
	public WebElement book; 
	
	@FindBy(xpath="//*[@class='book-name']/h2")
	public WebElement bookName;  
	
	@FindBy(xpath="//*[@class='author-name-set']")
	public WebElement authorName;  
	
	@FindBy(xpath="//*[@class='star-rating']")
	public WebElement ratings;
	
	@FindBy(id="mystuff-tlt-prvw-viewdet")
	public WebElement viewDetail;
	
	@FindBy (xpath="//*[@class='message error-msg ng-star-inserted']")
	public WebElement error;

	@FindBy (xpath="//*[@class='message error-msg']")
	public WebElement error1;
	
	@FindBy(xpath="//small[text()=' 24 / 64 Characters ']")
	public WebElement chlngNameChar;
	
	@FindBy(xpath="//small[text()=' 28 / 1300 Characters ']")
	public WebElement chlngDescChar;
	
	@FindBy(id="sort-filter-view-reslts")
	public static WebElement viewResult;
	
	@FindBy(xpath = "//img[@alt='sort filter']")
	public static WebElement btn_SortandFilter;
	
	@FindBy(xpath = "//h2[contains(text(),'Sort & Filter')]")
	public static WebElement title_SortandFilter;
	
	@FindBy(xpath = "//div[text()='Author']")
	public static WebElement rb_Author;
	
	@FindBy(xpath = "//div[text()='Format']")
	public static WebElement rb_Format;
	
	@FindBy(xpath = "//div[text()='Relevance']")
	public static WebElement rb_Relevance;  
	
	@FindBy(xpath = "//*[@class='mat-radio-label-content']")
	public static List<WebElement> sortBy;
	
	@FindBy(xpath="//h4[text()='NO STUDENTS FOUND']")
	public WebElement noResult;
	
	@FindBy(xpath="//a[@class='searchlist']")
	public List<WebElement> searchlist;
	
	@FindBy (xpath="//span[text()='Cancel']")
	public WebElement cancel;
	
	@FindBy (xpath="//span[text()='OK']")
	public WebElement ok;
	
	@FindBy (id="cchlg-close-btn")
	public WebElement closeChlng;
	
	@FindBy(xpath="//div[@class='sure-you ng-tns-c139-102']")
	public WebElement discardMsg;
	
	//@FindBy(xpath="//mat-dialog-content[text()=' Are sure you want to discard the changes made? ']")
	@FindBy(xpath="//*[@id=\"mat-dialog-1\"]/fss-ms-alert-dialog/div/mat-dialog-content")
	public WebElement discardMsg1;
	
	@FindBy(xpath="//*[@aria-label='remove invite student']")
	public List<WebElement> addedListClose;
	
	@FindBy(xpath="//span[@class='inv-user-name']")
	public List<WebElement> addedStudentList;
	
	@FindBy(id="accpet-close-btn")
    public WebElement close; 
	
	@FindBy(xpath="//*[text()='Checkout ' or text()='Return ']") 
	public WebElement Btn_checkoutorReturn;  
	
	@FindBy(xpath="//li[@class='breadcrumb-item']")
    public WebElement breadcrumbList;
	
	@FindBy(xpath="//span[@id='chlg-det-progrperct']")
    public List<WebElement> titleProgress;
	
	@FindBy(xpath="//*[@class='bk-avail ng-star-inserted']")
    public List<WebElement> bookList;
	
	@FindBy(xpath="//*[text()='LEAVE CHALLENGE ']")
	public WebElement leaveChlng;
	
	@FindBy(id="cd-usr-pertext")
	public List<WebElement> participantList; 
	
	@FindBy(xpath="//div[@class='user-poster ng-star-inserted']")
	public List<WebElement> participantListB4; 
	
	@FindBy(id="chlge-det-date")
	public WebElement createdDate1; 
	
	@FindBy(id="chlge-det-userporf")
	public WebElement createdUser; 
	
	@FindBy(xpath="//*[@class='challenge-detail-avatar']")
    public WebElement avatar;
	
	@FindBy(xpath="//*[@class='accordion-msg ng-star-inserted']/h4")
    public List<WebElement> RwdmsgDetails;
	
	@FindBy(xpath="//div[@class='content-name-head']/h4")
    public List<WebElement> msgHeader;
	
	@FindBy(xpath="(//span[@class='inv-user-name'])[1]")
    public WebElement adderUser;

	@FindBy(id="report-abuse-btn")
	public WebElement RWDreportabusebtn;  

    @FindBy(id="goto-btn")
    public WebElement RWDgotoChallenge;

    @FindBy(id="reprt-abrs-desc")
    public WebElement RWDreport_abuseDesc;

    @FindBy(id="mat-hint-1")
    public WebElement RWDErrormsg;

    @FindBy(id="reprt-cancel")
    public WebElement RWDreport_cancel;
    
    @FindBy(id="reprt-submit")
    public WebElement RWDreport_submit;

    @FindBy(xpath="//*[@class='dd-text']")
    public WebElement RWDreportText;

    @FindBy(xpath="//*[@class='dd-rprt-hd']")
    public WebElement RWDreportheader;
	
	@FindBy(xpath="//*[@id=\"mat-expansion-panel-header-0\"]/span[1]/mat-panel-title/div[2]/div/span")
	public WebElement msgFirst;
	
	@FindBy(xpath="//*[@id=\"mat-expansion-panel-header-1\"]/span[1]/mat-panel-title/div[2]/div/span")
	public WebElement msgSecond;
	
	@FindBy(xpath="//*[@id=\"mat-expansion-panel-header-2\"]/span[1]/mat-panel-title/div[2]/div/span")
	public WebElement msgThird;
	
	@FindBy(xpath = "//label[text()='© 2021 Follett School Solutions. All Rights Reserved']")
	public  WebElement footer;
	
	@FindBy(xpath = "//h3[text()='CLOSED CHALLENGES']")
	public  WebElement closedChlg;  
	
	@FindBy(xpath = "//button[text()='Go to Challenge']")
	public  WebElement GotoChlng; 
	
	@FindBy(xpath = "//span[text()=' Challenge Accepted! ']")
	public  WebElement acceptedMessage; 
	
	@FindBy(xpath = "//span[text()='Reading List']")
	public  WebElement Readinglist;  
	
	@FindBy(xpath = "//h2[text()='Reading List ']")
	public  WebElement Readinglist1; 
	
	@FindBy(xpath = "//span[text()='Participating in Challenge']")
	public WebElement participantHeader;  
	
	@FindBy(xpath = "//h2[text()='Participants ']")
	public WebElement participantHeader1;  
	
	@FindBy(xpath = "//span[@class='challenge-created']")
	public WebElement createdDate;  
	
	@FindBy(id="no-thanks")
	public WebElement RWDchlgNotAccept;  
	
	@FindBy(id="accept-btn")
	public WebElement RWDchlgAccept;  
	
	@FindBy(xpath="//div[@class='content-name-head']")
	public List<WebElement> RWDInviteText;
	
	@FindBy(id="mat-option-4")
	public WebElement RWDMessageUnread;
	
	@FindBy(id="mat-option-3")
	public WebElement RWDMessageRead;
	
	@FindBy(id="mat-option-2")
	public WebElement RWDMessageNone;
	
	@FindBy(id="mat-option-1")
	public WebElement RWDMessageAll;
	
	@FindBy(id="msg-checkbox")
	public WebElement msgDropdown;
	
	@FindBy(xpath="//*[@class='msg-close']")
	public WebElement msgclose;
	
	@FindBy(xpath = "//*[contains(text(),'You have no messages')]")
	public WebElement RWDnoMessages;
	
	//@FindBy(xpath = "//button[@aria-label='Message Center']")
	@FindBy(xpath = "//button[@class='msg-icon-button icon-bg mr-rgt ng-star-inserted']")
	public WebElement msgCenter; 
	
	@FindBy(xpath = "//button[text()='View Challenge Details']")
	public List<WebElement> viewChlng; 
	
	@FindBy(xpath = "(//h4[text()=\"You've been invited!\"])[1]")
	public WebElement invitedChallenge; 
	
	@FindBy(xpath = "//a[text()=' Home ']")
	public WebElement lnk_Home;  
	
	@FindBy(xpath = "//button[@aria-label='logout']")
	public static WebElement menu_Logout;
	
	@FindBy(xpath = "//*[@aria-label='Open profile options window']")
	public static WebElement menu_profileIcon;
	
	@FindBy(xpath = "//h4[text()='RECENTLY SEARCHED STUDENTS']")
	public WebElement recentSearchheader;
	
	@FindBy(xpath = "//span[@class='student-title']")
	public List<WebElement> recentSearch;  
	
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement save;
	
	@FindBy(xpath = "//h2[text()='Edit Challenge']")
	public WebElement lbl_editchlgHeader; 
	
	@FindBy(xpath = "//a[text()='EDIT CHALLENGE ']")
	public WebElement btn_EditChlg;
	
	@FindBy(xpath = "//p[@class='challenge-desc']")
	public WebElement lbl_readingChallengeDesc;
	
	@FindBy(xpath = "//div[@class='challenge-description']")
	public WebElement readingChallengeDesc;  
	
	@FindBy(id="chlge-detail-ptext")
	public WebElement participantChallengeDesc; 
	
	@FindBy(xpath = "//h1[@class='challenge-heading']")
	public WebElement lbl_readingChallengeName;   
	
	@FindBy(xpath = "//*[@class='mat-card-subtitle challenge-name']")
    public WebElement readingChallengeName;
	
	@FindBy(id="chlge-detail-heading")
    public WebElement participantChallengeName;
	
	@FindBy(xpath = "//label[text()='Reading Challenge']")
	public WebElement lbl_readingChallengeHeader;
	
	@FindBy(xpath = "//h3[text()='ACTIVE CHALLENGES ']")
	public WebElement lbl_ActiveChallenge;
	
	@FindBy(xpath = "//*[@class='mat-card-title header-title txt-elipsishead' and text()='Automation_Smoke ']")
	public WebElement chlgName;
	
	@FindBy(xpath = "//img[@id='search-title-close']")
	public WebElement closeIcon; //h3[text()='ACTIVE CHALLENGES ']
	
	@FindBy(xpath = "//h2[text()=' SUGGESTED RESULT ']")
	public WebElement lbl_suggestedResult; 
	
	@FindBy(xpath = "//label[text()='Find titles and add them to your challenge']")
	public WebElement searchTitleHeader;
	
	@FindBy(xpath = "//h4[text()='Add Titles']")
	public WebElement addTitleHeader;
	
	@FindBy(xpath = "//*[text()='Challenge Friends']")
	public WebElement addFriendHeader;
	
	@FindBy(xpath = "//*[text()='more_horiz']")
	public WebElement RWDMoreMenu;
	
	@FindBy(id = "searchInput")
	public WebElement RWDSearchInput; 
	
	@FindBy(id = "start-chalge-btn")
	public WebElement RWDstartChalnge;
	
	@FindBy(id="chlg-add-title")
	public  WebElement RWD_AddTitleCTA;
	
	@FindBy(xpath = "//button[@aria-label='Open calendar'][1]")
	public WebElement RWDcalenderImage;
	
	@FindBy(xpath=".//*[contains(@id,\"mat-datepicker\")]")
	public  WebElement RWDDatePickerSetReadyByDate;
	
    @FindBy(xpath="//*[@class='mat-calendar-body-cell-content mat-calendar-body-today']")
	//@FindBy(xpath = "//*[@id="mat-datepicker-0"]/div/mat-month-view/table/tbody/tr[2]/td[6]/div")
    public WebElement RWDSelectDate;

	@FindBy(xpath = "//*[@aria-label='Open calendar']")
    public WebElement RWDOpenCalendar;
	
	@FindBy(xpath = "//*[@aria-label='Set Reminder']")
	public WebElement RWDsetReminder;
	
	@FindBy(xpath = "//*[@aria-controls='reminderOptions[i]']")
	public WebElement RWDsetReminder1;
	
//	@FindBy(id = "reminderOptions[i]")
	@FindBy(xpath="//span[@class='mat-option-text']")
	public List<WebElement> RWDReminderTypes;
	
	@FindBy(xpath="//span[@class='student-invite']")
	public List<WebElement> RWD_InviteFriendLists; 
	
	@FindBy(xpath="//*[text()='INVITE']")
	public List<WebElement> btn_inviteList; 
	
	@FindBy(xpath="//*[text()='Create Reading Challenge']")
	public WebElement lbl_createChlgTitle;
	
	@FindBy(xpath = "//textarea[@id='chalg-desc']")
	public WebElement RWDChallengedescriptiontextbar;
	
	@FindBy(xpath = "//*[@for='chalg-desc']")
	public WebElement ChallengedescPlaceholder;
	
	@FindBy(xpath="//*[text()='Search Students']")
	public WebElement lbl_searchStudent; 
	
	@FindBy(xpath="//div[@class='dd-stud-info ng-star-inserted'][2]")
	public WebElement RWDaddedFriendCCPage;
	
	@FindBy(xpath="//*[text()='Add to Challenge ']")
	public WebElement RWDAddToChallenge; 
	
	@FindBy(xpath="//*[text()=' Add to challenge ']")
	public WebElement RWDAddToChallenge1; 
	
	@FindBy(xpath="//*[@aria-label='Add to challenge']")
	public WebElement AddToChallengeTitle;   
	
	@FindBy(xpath="//button[text()=' Remove from challenge ']")
	public WebElement RemoveFromChallengeTitle;
	
	@FindBy(xpath="//*[@id=\"mat-dialog-1\"]/fss-ms-invite-friends/div[1]/div[2]/div/div/mat-list/mat-list-item/div/span[3]")
	public WebElement RWDInviteOption;
	
	@FindBy(xpath = "//*[id='mat-form-field-label-11']")
	public WebElement SearchStudentNamePlaceholder;
	
	@FindBy(xpath = "//input[@id='ivite-chlg-search']")
	public WebElement RWD_SearchStudentName;
	
	@FindBy(xpath = "//button[@id='chlg-user-add']")
	public WebElement RWDaddfriendCTA;
	
	@FindBy(xpath = "//input[@id='chlg-enter-name']")
	public WebElement RWDChallengenametextbar;
	
	@FindBy(xpath = "//*[@for='chlg-enter-name']")  //id='mat-form-field-label-1
	public WebElement ChallengenamePlaceholder;
	
	@FindBy(xpath = "//*[@aria-label='Challenges']")
	public static WebElement CCHeader;
	
	@FindBy(xpath = "//a[text()=' Book Club ']")
	public static WebElement RWDbookClubOptionWeb;

	@FindBy(xpath = "//*[text()='CREATE CHALLENGE ']")
	public static WebElement RWDaddCTAWeb;

	@FindBy(xpath = "//*[@id=\"outer1\"]/div[2]/div[2]/div[17]/fss-ms-challenge-card/mat-card/div[1]/div[2]/mat-card-content/img")
	public WebElement RWDyouHavebeenChallenged;

	@FindBy(xpath = "//*[text()='Automation-May4 ']")
	public WebElement RWDchallengeNamebookclub;

	@FindBy(xpath = "//*[@class=\"mat-card-title header-title txt-elipsishead\"]")
	public List<WebElement> RWDchallengeNameList;
	
	@FindBy(xpath = "//*[contains(@class,\"dd-chlg-youicon\")]")
	public List<WebElement> RWD_InvitedChallenge_icon_List;
	
	
	//____________________home page locators----------------------
	
	@FindBy(xpath = "//*[@aria-label='See All , eBooks']")
	private WebElement ebooksSeeall;

	@FindBy(xpath ="//*[@id=\"headerSideBar\"]/mat-sidenav-content/main/fss-ms-browse-core/fss-ms-browse-std/div/div/div[2]/fss-ms-library-card-std[33]/section/div/div")
	private WebElement RWDcarList;

	@FindBy(xpath="//*[@id=\"headerSideBar\"]/mat-sidenav-content/main/fss-ms-browse-core/fss-ms-browse-std/div/div/div[2]/fss-ms-library-card-std[1]/section/div/div/mat-card")
	private WebElement RWDcardList1;

	@FindBy(xpath = "//*[@aria-label='Include in a New Challenge']")
	private WebElement RWDIncludechallenge;

	@FindBy(xpath = "//*[@aria-label='Add to existing challenge']")
	private WebElement RWDAddchallenge;
	
	@FindBy(xpath = "//span[@title='Pride and prejudice']")
	private WebElement RwdbookTitle;

	@FindBy(id = "more-option-horiz")
	private WebElement moreMenu;

	@FindBy(id="chlg-create-head")
	private WebElement RWDchlgcreatehead;

	@FindBy(id="plp-book-addto-0")
	private WebElement rwdAddto;

	@FindBy(id="plp-book-addto2")
	private WebElement rwdAddtochallenges;
	
	@FindBy(id="plp-book-addto2")
	private List<WebElement> rwdAddtochallenges1;

	@FindBy(xpath = "//*[@aria-label='Update Save challenges']")
	private WebElement RWDsaveChallenges;

	@FindBy(xpath = "//*[@aria-label='Search']")
	private WebElement RWDsearch;

	@FindBy(id="searchInput")
	private WebElement rwdsearchInput;

	@FindBy(xpath="//div[@class='resultText ng-star-inserted']/span[1]")
	private WebElement rwdNotitleFound;

}
