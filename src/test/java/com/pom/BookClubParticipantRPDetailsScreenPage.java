package com.pom;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookClubParticipantRPDetailsScreenPage extends CapabilitiesAndWebDriverUtils {
	//MobileDriverUtils mobileutils = new MobileDriverUtils();
	ExcelReader reader = new ExcelReader();
    public BookClubParticipantRPDetailsScreenPage() { 
    	PageFactory.initElements(new AppiumFieldDecorator(driver), this); }
    
    public void programDetailAssertion(boolean joinProgram) throws InvalidFormatException, IOException {
    	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Programs");
    	logger.info("Program Details assertion Started");
    	swipeUp();
    	if(joinProgram==true) {
    		logger.info("On Going Program Details assertion Started");
    	    WaitForMobileElement(readingListHeaderOpenProg);
    	    swipeUp();
    		String readingListCount = readingListHeaderOpenProg.getText();
        	String countText = readingListCount.substring(13,14); 
        	int count = Integer.parseInt(countText);
        	System.out.println("Text count & Actual count:"+count+":"+ReadingBookList.size());
        	WaitForMobileElement(ReadingBookList.get(0));
        	if(count!=ReadingBookList.size() && count>=3) {
        		waitFor(500);
        		horizontalSwipe(ReadingBookList);
        		WaitForMobileElement(ReadingBookList.get(count));
        		horizontalSwipe(ReadingBookList);
        		System.out.println("Text count & Actual count after swipe:"+count+":"+ReadingBookList.size());
        	}
        	Assert.assertEquals(ReadingBookList.size(), count);
        	logger.info("Reading List Assertion completed");
			/*
			 * String participantsListCount = ParticipantsHeaderDetailsScreen.getText();
			 * String pariticipantCountText = participantsListCount.substring(13,14); int
			 * participantCount = Integer.parseInt(pariticipantCountText);
			 * Assert.assertEquals(participantCount,ParticipantsAvatarDetailsScree.size());
			 * logger.info("Participants Assertion completed");
			 */
        	Assert.assertEquals(joinProgramButton.getText(), testData.get(0).get("defval_joinProgram"));
        	Assert.assertEquals(noThanksButton.getText(), testData.get(0).get("defval_noThanksProgram"));
        	logger.info("On Going Program Details assertion successfully Completed");
    	}
    	else if(joinProgram ==false) {
    		logger.info("Active Program Details assertion Started");
    		swipeUp();
    		WaitForMobileElement(readingListHeaderDetailsScreen);
    		String readingListCount = readingListHeaderDetailsScreen.getText();
        	String countText = readingListCount.substring(13,14); 
        	int count = Integer.parseInt(countText);
        	System.out.println("Reading book count"+":"+count+":"+ReadingBookList.size());
        	if(count!=ReadingBookList.size() && count>=3) {
        		waitFor(500);
        		horizontalSwipe(ReadingBookList);
        		WaitForMobileElement(ReadingBookList.get(count));
        		horizontalSwipe(ReadingBookList);
        		System.out.println("Text count & Actual count after swipe:"+count+":"+ReadingBookList.size());
        	}
        	Assert.assertEquals(ReadingBookList.size(), count);
        	swipeUp();
        	String participantsListCount = ParticipantsHeaderDetailsScreen.getText();
        	String pariticipantCountText = participantsListCount.substring(13,14); 
        	int participantCount = Integer.parseInt(pariticipantCountText);
        	System.out.println("Text count & Actual count: "+participantCount+":"+ParticipantsListMyProgDetailsScree.size());
        	Assert.assertEquals(participantCount,ParticipantsListMyProgDetailsScree.size());
        	logger.info("Active Program Details assertion successfully Completed");
    	}
    	
    }
    public void bookclubAssertion() throws InvalidFormatException, IOException {
    	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Book Club");
    	logger.info("Book CLub Screen assertion Started");
    	Assert.assertEquals(myPrograms.getText(),testData.get(0).get("defval_myProgram"));
    	System.out.println("Default BookClub landing screen is: "+myPrograms.getText());
    	WaitForMobileElement(openPrograms);
    	Assert.assertEquals(openPrograms.getText(),testData.get(0).get("defval_openProgram"));
    	Assert.assertTrue(notificationIcon.isEnabled());
    	Assert.assertTrue(searchIcon.isDisplayed());
    	Assert.assertTrue(more.isDisplayed());
    	logger.info("Book CLub Screen assertion successfully Completed");
    	
    }
    public void homeAssertion() throws InvalidFormatException, IOException {
    	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Home");
    	logger.info("Home Screen assertion started");
    	Assert.assertEquals(homeTitle.getText(),testData.get(0).get("defVal_homeTitle"));
    	Assert.assertTrue(bookclubMenu.isDisplayed());
    	Assert.assertTrue(searchIcon.isDisplayed());
    	Assert.assertTrue(more.isDisplayed());
    	Assert.assertTrue(homeMenu_myStuff.isDisplayed());
    	Assert.assertTrue(homeMenu_discover.isDisplayed());
    	Assert.assertTrue(homeMenu_more.isDisplayed());
    	logger.info("Home Screen assertion successfully Completed");
    }
    
    public void openProgramAssertion() throws InvalidFormatException, IOException {
    	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Programs"); 
    	logger.info("Open Program screen assertion started");
    	//System.out.println("Open Program :"+openPrograms.getText());
    	Assert.assertEquals(openPrograms.getText(),testData.get(0).get("defval_openProgram"));
    	WaitForMobileElement(onGoingPrograms);
    	Assert.assertEquals(onGoingPrograms.getText(),testData.get(0).get("defval_onGoingProgram"));
    	Assert.assertTrue(OpenprogramBook.isDisplayed());
    	Assert.assertTrue(programTitle.isDisplayed());
    	Assert.assertTrue(programDescription.isDisplayed());
    	Assert.assertTrue(programReadBy.isDisplayed());
    	//Assert.assertTrue(programReadBy.isDisplayed());
    	logger.info("Open Program Screen assertion successfully Completed");
    }
    
    public void myProgramAssertion() throws InvalidFormatException, IOException {
    	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Programs");
    	logger.info("My Program Screen assertion Started");
    	WaitForMobileElement(myProgramsActive);
    	Assert.assertEquals(myProgramsActive.getText(),testData.get(0).get("defval_ActivePrograms")); 
    	Assert.assertTrue(myprogramBook.isDisplayed());
    	Assert.assertTrue(programTitle.isDisplayed());
    	Assert.assertTrue(programDescription.isDisplayed());
    	Assert.assertTrue(programReadBy.isDisplayed()); 
    	Assert.assertTrue(myprogramPercentage.isDisplayed()); 
    	Assert.assertTrue(myprogramPercentageSymbol.isDisplayed());
    	Assert.assertTrue(myprogramHoursSpent.isDisplayed());
    	logger.info("My Program Screen assertion successfully Completed");
    }
    
	//String ProgramName,
    //Join using given given program name
	  public void join(int maxswipe) throws InvalidFormatException, IOException {
		  List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Programs");
	  //MobileElement progName = (MobileElement) driver.findElement(By.xpath("//*[@text='" + ProgramName + "']")); 
	  MobileElement progName = (MobileElement)driver.findElement(By.xpath("//*[@text='"+testData.get(0).get("Program_Name")+"']")); 
	  for(int i=1;i<maxswipe;i++) {
		  if(progName.isDisplayed()) {
			  ClickOnMobileElement(progName);
			  WaitForMobileElement(joinProgramButton);
			  ClickOnMobileElement(joinProgramButton);
		  }
		  else {
			  swipeUp();
		  }
	  }
	  
	  //driver.findElement(MobileBy.AndroidUIAutomator("Your UiScrollable here"));
	  //UiScrollable ui = new UiScrollable(); ui.scrollToEnd();
	  ClickOnMobileElement(progName); }
	 
	/*
	 * public void testVertical() throws UiobjectNotFoundException{ Uiscrollable
	 * scroll=new Uiscrollabie(new UiSelector().className("");
	 * scroll.setAsHorizontalList(); scroll.setAsVerticalList(); }
	 */
    
    public void joinProgram(int noOfProgram) throws InvalidFormatException, IOException {
    	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Programs");
    	logger.info("Opening the on-going program to Join the program.");
    	swipeDown();
    	WaitForMobileElement(ProgramNameDetailsScreen);
    	String progName = ProgramNameDetailsScreen.getText();
    	System.out.println("1st Program to be joined:"+progName);
    	if (noOfProgram >1) {
    		for (int i=1;i<=noOfProgram;i++) {
    			//programDetailAssertion(true);
    			//ClickOnMobileElement(noThanksButton);
    			ClickOnMobileElement(joinProgramButton);
    			Assert.assertEquals(joinProgSuccessTxt.getText(), testData.get(0).get("defVal_JoinProgramSuccessText"));
    	    	ClickOnMobileElement(closeIcon);
    	    	WaitForMobileElement(openPrograms);
    			ClickOnMobileElement(openPrograms);
    			WaitForMobileElement(onGoingPrograms);
    			WaitForMobileElement(programTitle);
    			//validate previously joined prog is not present in on going prog | progname != prognamenext
    			String progNameNext = programTitle.getText();
    			System.out.println("Next "+i+":"+progNameNext);
    			Assert.assertNotEquals(progName, progNameNext);
    			ClickOnMobileElement(OpenprogramBook);
    			logger.info("Previously joined program is not present in onGoing program");
    	    	swipeUp();
    		}
    	}
    	else {
    		//ClickOnMobileElement(OpenprogramBook);
        	//logger.info("Opening the first on-going program to Join the program.");
        	programDetailAssertion(true);
        	//logger.info("OpenProgram > On-going Program Assertion successfully completed.");
        	ClickOnMobileElement(joinProgramButton);
    	}
    	logger.info("Joined in "+noOfProgram+"number of program's' successfully.");
    }
    
    public void leaveProgram(int noOfProgram) throws InvalidFormatException, IOException {
    	//List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Programs");
    	logger.info("Leave the program method started.");
    	WaitForMobileElement(ProgramNameDetailsScreen);
    	String progName = ProgramNameDetailsScreen.getText();
    	if (noOfProgram >1) {
    		for (int i=0;i<=noOfProgram;i++) {
    			programDetailAssertion(false);
    	    	logger.info("My Program > Active Program Assertion successfully completed.");
    	    	ClickOnMobileElement(moreProgDetails);
    	    	ClickOnMobileElement(leaveProgram);
    			//Assert.assertEquals(joinProgSuccessTxt.getText(), testData.get(0).get("defVal_JoinProgramSuccessText"));
    	    	ClickOnMobileElement(backProgDetails);
    	    	WaitForMobileElement(myPrograms);
    			ClickOnMobileElement(myPrograms);
    			WaitForMobileElement(programTitle);
    			//validate left prog should not present in Active prog | progname != prognamenext
    			String progNameNext = programTitle.getText();
    			Assert.assertNotEquals(progName, progNameNext);
    			logger.info("Left program is not present in Active programs");
    			ClickOnMobileElement(myprogramBook);
    		}
    	}
    	else {
    		//programDetailAssertion(false);
    		ClickOnMobileElement(moreProgDetails);
	    	ClickOnMobileElement(leaveProgram);
    	}
    	logger.info("Left "+noOfProgram+"number of program's' successfully.");
    }
    /***************************************************************************************/
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"More\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement moreProgDetails; 
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Leave Program\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement leaveProgram;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Cancel\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement cancelMyProgram;
    
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"< Back\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement backProgDetails; 
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\" Discovery\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement homeTitle;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\" Discovery\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement bookclubTitle;
    
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"BOOK CLUB\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement bookclubMenu;
    
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Search Icon\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement searchIcon;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"More\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement more;
    
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"My Programs\"]/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement myPrograms;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Active Programs\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement myProgramsActive;
    
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Open Programs\"]/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement openPrograms;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Ongoing Programs\"]")
    //@AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/txt_active_programs']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement onGoingPrograms;
 
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/imageView2']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement notificationIcon;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/first_book']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement OpenprogramBook;
    
    //need to add assertion from this
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/program_title_text']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement programTitle;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/description_books']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement programDescription;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/program_readby_text']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement programReadBy;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/second_book']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement myprogramBook;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/tv_percentagetext']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement myprogramPercentage;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/tv_percent']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement myprogramPercentageSymbol;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/tv_hoursspent']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement myprogramHoursSpent;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/participant_image']")
    @iOSXCUITFindBy(xpath = "Locator needed")
    public List<MobileElement> ParticipantsAvatarDetailsScree;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Photon\"]")
    @iOSXCUITFindBy(xpath = "Locator needed")
    public List<MobileElement> ParticipantsListMyProgDetailsScree;
    
    @AndroidFindBy(xpath = "//*[@resource-id= 'com.follett.fss.searchread.stage:id/close_image_view']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement closeIcon;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/accepted_text']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement joinProgSuccessTxt;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/share_text']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement joinProgSuccessShare;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/user_info_text']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement joinProgSuccessUserInfo;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/action_myLibrary']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement homeMenu_myStuff;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/action_searchResults']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement homeMenu_discover;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/action_more']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement homeMenu_more;
    
  /*************************************************************************************************************/
    
    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[contains(@name,\"READING\")])[1]")
    public MobileElement readByHeaderText;

    @AndroidFindBy(id = "LocatorNeeded")    
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Join Program\"]")
    public List<MobileElement> ListjoinProgramButton;

    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/joinProgram_text']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Join Program\"]")
    public MobileElement joinProgramButton;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.follett.fss.searchread.stage:id/no_thanks_text']")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public MobileElement  noThanksButton;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeNavigationBar['index=0'])[4]")
    public MobileElement programDetailsHeaderText;

    @AndroidFindBy(id = "LocatoreNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"CREATED\")]")
    public MobileElement createdByRDDetailsText;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell/XCUIElementTypeStaticText['@index=1'])[5]")
    public MobileElement creatorNameDetailsScreen;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[1]/XCUIElementTypeStaticText['@index=1'])[1]")
    public MobileElement descriptionDetailsScreen;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"READ BY\")]")
    public MobileElement readingProgramReadByDetailsScreen;

    //@AndroidFindBy(id="com.follett.fss.searchread.stage:id/participants_text")
    @AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/rp_readinglist_text']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Reading List\"]")
    public MobileElement readingListHeaderDetailsScreen;
    
    @AndroidFindBy(id="com.follett.fss.searchread.stage:id/reading_list_text")
    public MobileElement readingListHeaderOpenProg;

    @AndroidFindBy(xpath = "//*[@resource-id = 'com.follett.fss.searchread.stage:id/rp_participants_text']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Participants \")]")
    public MobileElement ParticipantsHeaderDetailsScreen;

    @AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/go_to_challenge_text']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Go to Program\"]")
    public MobileElement goToProgramButton;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"CREATED\")]")
    public MobileElement createdDetailsScreen;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeNavigationBar/XCUIElementTypeStaticText['index=1'])[2]")
    public MobileElement programDetailsHeader;

    @AndroidFindBy(id = "LocatarNeeded")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[4]['index=4'])[1]")
    public MobileElement AvatarImageDetailsScreen;

    @AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/program_name_text']")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText['index=4'])[5]")
    public MobileElement ProgramNameDetailsScreen;

    @AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/program_desc_text']")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText['index=2'])[3]")
    public MobileElement descriptionProgramDetailsScreen;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText['index=0'])[1]")
    public MobileElement EndDateProgramDetailsScreen;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "(///XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText['index=5'])[7]")
    public MobileElement startDateProgramDetails;

    @AndroidFindBy(id = "LocatorNeede")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]['index=3']")
    public List<MobileElement> BookList;
    
    //new
    @AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"cover image\"]")
    @iOSXCUITFindBy(xpath = "LocatorNeeded")
    public List<MobileElement> ReadingBookList;

    public MobileElement getprogramDetailsHeaderText() {
        return programDetailsHeaderText;
    }
    public MobileElement getcreatedByRDDetailsText() {
        return createdByRDDetailsText;
    }
    public MobileElement getcreatorNameDetails() {
        return creatorNameDetailsScreen;
    }
    public MobileElement getdescriptionDetails() {
        return descriptionDetailsScreen;
    }
    public MobileElement getreadingProgramReadByDetails() {
        return readingProgramReadByDetailsScreen;
    }
    public MobileElement getreadingListHeaderDetails() {
        return readingListHeaderDetailsScreen;
    }
    public MobileElement getParticipantsHeaderDetails() {
        return ParticipantsHeaderDetailsScreen;
    }
    public MobileElement getreadByHeaderText(){return readByHeaderText;}
    public MobileElement getJoinProgramDatailsButt0n(){return joinProgramButton;}
    public List<MobileElement> getJoinPrgmLists(){return ListjoinProgramButton;}
    public MobileElement getgoToProgramButton(){ return goToProgramButton;}
    public MobileElement getcreatedDetailsScreen(){ return createdDetailsScreen;}
    public MobileElement getprogramDetailsHeader(){return programDetailsHeader;}
    public MobileElement getAvatarImageDetailsScreen(){return AvatarImageDetailsScreen;}
    public MobileElement getProgramNameDetailsScreen(){return ProgramNameDetailsScreen;}
    public MobileElement getdescriptionProgramDetailsScreen(){return descriptionProgramDetailsScreen;}
    public MobileElement getstartDateProgramDetails(){return startDateProgramDetails;}
    public MobileElement getEndDateProgramDetailsScreen(){return EndDateProgramDetailsScreen;}
    public List<MobileElement> GetBookList(){return BookList;}

}
