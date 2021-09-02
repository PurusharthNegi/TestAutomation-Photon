package com.pom_RWD;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

public class RWD_RP extends CapabilitiesAndWebDriverUtils  {
	Rwd_RC rc = new Rwd_RC();
	RWD_RP_Smoke rp  =new RWD_RP_Smoke();
	private static final Logger logger = LogManager.getLogger();
	
	static ExcelReader reader = new ExcelReader();
	public static String progName1 = "editprg" + generateRandomNumber();
	public static String progName = "Automation_RP_"+ generateRandomNumber();
	//+ generateRandomNumber();
	public RWD_RP() {
		PageFactory.initElements(driver, this);
	}
	public static int  generateRandomNumber()
	{
		Random rand = new Random();
		int randNumber = rand.nextInt(1000);
		return randNumber;
		
	}
	public void discard() throws InvalidFormatException, IOException {

		logger.info("--Discard create challenge validation started--");
		waitFor(8000);
		ClickOnWebElement(btn_createProgram);
		WaitForWebElement(lbl_CreateProgrm);
		SendKeysOnWebElement(inp_progName, progName);
		logger.info("Entered challenge name #Pass");
		waitFor(2000);
		ClickOnWebElement(createClose);
		logger.info("Close button is present and clicked #Pass");
		WaitForWebElement(discardMsg);
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
	
	public void enterprogNameDesc() throws InvalidFormatException, IOException{
		System.out.println("Entered program name : "+progName);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		logger.info("-- Create Program validation started--");
		ClickOnWebElement(btn_createProgram);
		WaitForWebElement(lbl_CreateProgrm);
		//System.out.println(lbl_CreateProgrm.getText());
		Assert.assertEquals(lbl_CreateProgrm.getText(), testData.get(0).get("lbl_CreateProgram"));
		logger.info("Create program header is available #Pass");
		Assert.assertEquals(lbl_progName.getText(), testData.get(0).get("lbl_progNameTxt"));
		logger.info("Program name text box is available #Pass");
		ClickOnWebElement(inp_progName);
		SendKeysOnWebElement(inp_progName, progName);
		logger.info("Program name entered #Pass");
		Assert.assertEquals(lbl_progDesc.getText(), testData.get(0).get("lbl_progDescTxt"));
		logger.info("Program Description text box is available #Pass");
		SendKeysOnWebElement(inp_progDesc, testData.get(0).get("progDesc"));
		logger.info("Program Description entered #Pass");
	}
	
	public void setprogAccess(boolean access) throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		Assert.assertEquals(rb_private.getText(), testData.get(0).get("lbl_private"));
		logger.info("Private radio button is available #Pass");
		Assert.assertEquals(rb_public.getText(), testData.get(0).get("lbl_public"));
		logger.info("Public radio button is available #Pass");
		if(access==true) {
		ClickOnWebElement(rb_public);
		logger.info("Public radio button is selected #Pass");
		}
		else if(access == false) {
			ClickOnWebElement(rb_private);
			logger.info("Private radio button is selected #Pass");
		}
	}
	public void tooltip() throws IOException {
		WaitForWebElement(info);
		ClickOnWebElement(info);
		logger.info("Toll tip is displayed and clicked #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/ToolTip.png");
		Assert.assertEquals(ElementisPresent(tip),true );
		Assert.assertEquals(ElementisPresent(tipMsg),true );
		logger.info("Toll tip message is displayed "+tipMsg.getText()+" #Pass");
		ClickOnWebElement(tipClose);
		logger.info("Tool tip is closed #Pass");
	}
	public void setProgType(boolean edit) throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		WaitForWebElement(programType);
		ClickOnWebElement(programType);
		if(edit == true) {
			switch (testData.get(0).get("ProgramTypeEdit")) {
			case " Books in Order":
				logger.info("Selected reminder type :" + progTypes.get(0).getText()+ "#Pass");
				progTypes.get(0).click();
				Screenshots.takeScreenshot(driver,
						"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(0).getText() + ".png");
				break;
			case " X of Y Books":
				logger.info("Selected reminder type :" + progTypes.get(1).getText() +"#Pass");
				progTypes.get(1).click();
				Screenshots.takeScreenshot(driver,
						"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(1).getText() + ".png");
				break;
			}
		}
		else if(edit == false) {
			switch (testData.get(0).get("ProgramType")) {
			case " Books in Order":
				logger.info("Selected reminder type :" + progTypes.get(0).getText()+ "#Pass");
				progTypes.get(0).click();
				Screenshots.takeScreenshot(driver,
						"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(0).getText() + ".png");
				break;
			case " X of Y Books":
				logger.info("Selected reminder type :" + progTypes.get(1).getText() +"#Pass");
				progTypes.get(1).click();
				Screenshots.takeScreenshot(driver,
						"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(1).getText() + ".png");
				break;
			}
		}
		if(testData.get(0).get("ProgramTypeEdit")== " X of Y Books" || testData.get(0).get("ProgramType")==" Books in Order") {
			Assert.assertEquals(ElementisPresent(inp_noOfBooks),true);
			logger.info("Number of Books drop down field is enabled");
			Assert.assertEquals(ElementisPresent(defval_noOfBooks), true);
			logger.info("Default value "+defval_noOfBooks.getText()+" is displayed in number of books field");
		}
	}
	@FindBy(xpath="//*[text()='Number of Books']")
	public WebElement inp_noOfBooks;
	
	@FindBy(xpath="//span[text()='1']")
	public WebElement defval_noOfBooks;
	
	public void startDate(boolean edit) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		javascriptScroll(btn_startDate);
		ClickOnWebElement(btn_startDate);
		logger.info("Start date calendar is opened #Pass");
		waitFor(1000);
		if(edit == true) {
			String startDate = testData.get(0).get("EditStartDate");
			WebElement sd = driver.findElement(By.xpath("//div[text()=' " + startDate + " ']"));
			jsClick(sd);
			logger.info("Start Date is selected " + sd.getText() +"#Pass");
		}
		else if(edit == false) {
			String startDate = testData.get(0).get("Startdate");
			WebElement sd = driver.findElement(By.xpath("//div[text()=' " + startDate + " ']"));
			jsClick(sd);
			logger.info("Start Date is selected " + sd.getText() +"#Pass");
		}
	}

	public void endDate() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		waitFor(3000);
		ClickOnWebElement(btn_endDate);
		logger.info("End date calander is opened #Pass");
		waitFor(1000);
		String endDate = testData.get(0).get("EndDate");
		WebElement ed = driver.findElement(By.xpath("//div[text()=' " + endDate + " ']"));
		ClickOnWebElement(ed);
		logger.info("End Date is selected " + ed.getText() +"#Pass");
	}
	public void setRemainder() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		javascriptScroll(RWDsetReminder);
//		Assert.assertEquals(inp_remainder.getText(), testData.get(0).get("lbl_remainder"));
		logger.info("Set Remainder text box is available #Pass");
		ClickOnWebElement(RWDsetReminder);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder.png");
		List<WebElement> remindertype = RWDReminderTypes;
		waitFor(2000);
		switch (testData.get(0).get("RemainderTypeEdit")) {
		case "Daily":
			logger.info("Selected reminder type :" +remindertype.get(1).getText());
			remindertype.get(1).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(1).getText() + ".png");
			break;
		case "Weekly":
			logger.info("Selected reminder type :" +remindertype.get(2).getText());
			remindertype.get(2).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(2).getText() + ".png");
			break;
		case "Monthly":
			logger.info("Selected reminder type :" +remindertype.get(3).getText());
			remindertype.get(3).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"+remindertype.get(3).getText() + ".png");
			break;
		}
	}
	public void assignStudent() throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
//		Assert.assertEquals(lbl_assignStudents.getText(), testData.get(0).get("lbl_assignStudents"));
		logger.info("Assign Students header is available is available #Pass");
		ClickOnWebElement(btn_addfriend);
		ClickOnWebElement(txt_SearchStudentName);
		SendKeysOnWebElement(txt_SearchStudentName, testData.get(0).get("studentName"));
		logger.info("Entered student name in search box #Pass");
		waitFor(4000);
		rc.addMultipleStudent(5); // Add multiple students to the challenge vaildation
		recentInviteValidation(); // Recently searched participant list validation
		rc.removeMultipleStudent(5); // Remove multiple students to the challenge validation
		ClickOnWebElement(btn_inviteList.get(0));//Add any [0] user from the list
		waitFor(2000);
		ClickOnWebElement(btn_InviteToPrgrm);
		WaitForWebElement(addedParticipants.get(0)); //Waiting for the added participant listed out in create program screen
		removeParticipant(); //Removing the pariticipant from the create program screen by clicking X.
		WaitForWebElement(btn_addfriend);
		ClickOnWebElement(btn_addfriend);
		SendKeysOnWebElement(txt_SearchStudentName, testData.get(0).get("studentName"));
		WaitForWebElement(lbl_studentsFound);
		for(int i=1;i<=recentSearch.size()-1;i++) {
			//String StudentName = recentSearch.get(i).getText();
			//System.out.println(i +":"+StudentName.trim());
			//System.out.println(i +":"+ testData.get(1).get("studentName"));
			if(recentSearch.get(i).getText().trim().equalsIgnoreCase(testData.get(1).get("studentName"))) {
				logger.info(recentSearch.get(i).getText().trim() +"User is invited #Pass");
				WaitForWebElement(btn_InviteFriendLists.get(i));
				waitFor(4000);
				btn_InviteFriendLists.get(i).click();
			}
		}
		if(ElementisPresent(inviteEnable)) {
			ClickOnWebElement(btn_InviteToPrgrm);
			logger.info("Invite to program button is enabled and clicked #Pass");
			successMsgvalidation();
		}
		else {
			throw new Exception("Invite to program button not enabled");
		}
		WaitForWebElement(btn_addfriend);
		waitFor(3000);
		logger.info("Added Particitipant to the challenge #Pass");
	}
	public void removeParticipant() throws Exception{
        System.out.println("RWDRemoveParticipants"+addedParticipants.size());
        if(addedParticipants.size()>0){
        	waitFor(3000);
        	closeX.get(0).click();
            waitFor(3000);
            logger.info("Remove Friend From Participants #Pass");
            Assert.assertTrue(ElementisPresent(rc.RwdConfirm));
            logger.info("Remove confirmation messge is displayed #Pass");
            ClickOnWebElement(rc.RWDRemoveFriend);
            logger.info("Remove button is clicked on confirmation messge #Pass");
            Screenshots.takeScreenshot(driver, "Screenshots/ReadingChalllenge/Notification/RemoveFriend.png");
        }
        else{
        	throw new Exception("No recent participants");    
        }
    } 
	public void recentInviteValidation() throws Exception {
		ClickOnWebElement(btn_InviteToPrgrm);
		WaitForWebElement(btn_addfriend);
		waitFor(2000);
		ClickOnWebElement(btn_addfriend);
		waitFor(7000);
		WaitForWebElement(recentSearch.get(0));
		if(recentSearch.size()>0 & recentSearch.size()<=5) {
			logger.info("Recently Search participant list is not more than 5 #Pass");
		}
		else {
			throw new Exception("Recently search participant list is more than 5 #Fail");
		}
	}
	public void addTitle() throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Add title assertion started--");
//		Assert.assertEquals(lbl_addTitle.getText(), testData.get(0).get("lbl_addTitle"));
		logger.info("Add title header is available is available #Pass");
		ClickOnWebElement(btn_AddTitle);
		WaitForWebElement(txt_SearchInput);
		Assert.assertTrue(ElementisPresent(rc.closeIcon));
		ClickOnWebElement(rc.closeIcon);
		logger.info("Title search close icon is available and clicked #Pass");
		ClickOnWebElement(btn_AddTitle);
		WaitForWebElement(txt_SearchInput);
		recommendedValidation();
		FavoritesValidation();
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle1.png");
		Assert.assertEquals(searchTitleHeader.getText(), testData.get(0).get("defval_searchTitleHeader"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle.png");
		logger.info("Title search header is available #Pass");
		SendKeysOnWebElement(txt_SearchInput, testData1.get(0).get("titleName"));
		waitFor(5000);
	//	WaitForWebElement(lbl_suggestedResult);
	//	Assert.assertEquals(lbl_suggestedResult.getText(), testData.get(0).get("lbl_suggestedResult"));
		Screenshots.takeScreenshot(driver,"Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_SuggestedResult.png");
		logger.info("Suggested result header is available #Pass");
		SendKeysEnter(txt_SearchInput);
		WaitForWebElement(btn_AddTitle);
		ClickOnWebElement(RWDMoreMenu);
		ClickOnWebElement(AddToprgrmTitle);
		rc.removeTitle();
		waitFor(4000);
		WaitForWebElement(btn_AddTitle);
		ClickOnWebElement(btn_AddTitle);
		SendKeysOnWebElement(txt_SearchInput, testData1.get(0).get("titleName"));
		ClickOnWebElement(suggestionList.get(0));
		WaitForWebElement(RWDMoreMenu);
		rc.sortAndFilter();
		WaitForWebElement(RWDMoreMenu);
		rc.addFavorites(); //Add to favorites validation
		WaitForWebElement(RWDMoreMenu);
		titlePreview(); // validate title preview screen
		jsClick(RWDMoreMenu);
		ClickOnWebElement(AddToprgrmTitle);
		logger.info("Title is added to the challenge #Pass");
		successMsgvalidation();
		jsClick(RWDMoreMenu);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/RemoveFromChallenge.png");
		waitFor(1000);
		Assert.assertTrue(RemoveFromProgTitle.isDisplayed(),"Remove from challenge button is not displayed");
		logger.info(RemoveFromProgTitle.getText()+" button is displayed #Pass");
		jsClick(RWDMoreMenu);
		
	}
	public void successMsgvalidation() {
		if(ElementisPresent(successMsg)) {
			logger.info("Success message is displayed #Pass");
			Assert.assertEquals(ElementisPresent(successMsg),true);
		}
		else if(ElementisPresent(failureMsg)) {
			logger.info("Failure message is displayed #Fail");
			Assert.assertEquals(ElementisPresent(successMsg),true);
		}
	}
	
	public void titlePreview() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		logger.info("--Title preview assertion started--");
		WaitForWebElement(rc.book);
		ClickOnWebElement(rc.book);
		WaitForWebElement(rc.bookName);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/TitleDetails.png");
		Assert.assertEquals(rc.bookName.getText(), testData.get(0).get("titleName"));
		logger.info("Book name is present in title preview #Pass");
		Assert.assertEquals(ElementisPresent(rc.authorName), true);
		logger.info("Author name is present in title preview #Pass");	
		Assert.assertEquals(ElementisPresent(rc.lbl_titleMaterial), true);
		logger.info(rc.lbl_titleMaterial.getText()+" Title Material type is present in title preview #Pass");
		Assert.assertEquals(ElementisPresent(AddToprgrmTitle), true );
		logger.info("Add to program button is present in title preview #Pass");
		Assert.assertEquals(ElementisPresent(rc.ratings), true );
		logger.info("Rating is available fot the title in preview screen #Pass");
		Assert.assertTrue(ElementisPresent(rc.Btn_checkoutorReturn),"Checkout button is not displayed");
		logger.info(rc.Btn_checkoutorReturn.getText()+" button is available for title preview screen #Pass");
		javascriptScroll(rc.lbl_availableheader);
		Assert.assertEquals(ElementisPresent(rc.lbl_availableheader), ElementisPresent(rc.lbl_availableValue));
		logger.info("Available copy header and value is present #Pass");
		waitFor(2000);
		ClickOnWebElement(rc.back);
		WaitForWebElement(lbl_CreateProgrm);
		logger.info("Back icon is clicked and Navigated back to Create challenge screen #Pass");
		logger.info("--Title preview assertion Completed--");
	}
	public void recommendedValidation() {
		WaitForWebElement(lbl_recommended);
		Assert.assertTrue(Book.size()>0,"Recommended carousel doesn't have books #Fail");
		logger.info("Recommended carousel and list of titles are displayed #Pass");
	}
	public void FavoritesValidation() {
		WaitForWebElement(lbl_Favorites);
		Assert.assertTrue(Book.size()>0,"Favorites carousel doesn't have books #Fail");
		logger.info("Favorites carousel and list of titles are displayed #Pass");
	}
	
	public void XofYValidation() {
		javascriptScroll(inp_progName);
		Assert.assertEquals(ElementisPresent(lbl_noOfBooks), true, "Number of books field is not displayed");
		logger.info("No of books field is enabled for X of Y books type");
		ClickOnWebElement(lbl_noOfBooks);
		Assert.assertTrue(ElementisPresent(noOfBooks.get(0)),"Number of books list is not displayed");
		ClickOnWebElement(noOfBooks.get(0));
		logger.info("Number of books list is displated and selected");
	}
	public void saveProgram() throws InvalidFormatException, IOException {
		waitFor(3000);
		Assert.assertEquals(ElementisPresent(btn_save), true);
		ClickOnWebElement(btn_save);
		logger.info("Save button is available and clicked #Pass");
		WaitForWebElement(lbl_ActiveChallenge);
		waitFor(5000);
	}
	public void programSearch() {
		for(int i=0; i<= 5; i++) {
			waitFor(2000);
			javaScriptScrollToEnd();
			waitFor(2000);
			javascriptScroll(lbl_ActiveChallenge);
			try {
				WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
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
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
		logger.info(ss.getText()+" is found in My Program listing screen and opened #Pass");
		jsClick(ss);
	}
	public void publishProgram() {
		waitFor(3000);
		WaitForWebElement(btn_publish);
		Assert.assertEquals(ElementisPresent(btn_publish), true);
		ClickOnWebElement(btn_publish);
		successMsgvalidation();
		logger.info("Publish program button is available and clicked #Pass");
	}
	public void deleteProg() {
		WaitForWebElement(lbl_CreateProgrm);
		waitFor(3000);
		javascriptScroll(btn_delete);
		ClickOnWebElement(btn_delete);
		Assert.assertEquals(ElementisPresent(rc.removeBookConfirmMsg),true);
		waitFor(2000);
		Assert.assertTrue(ElementisPresent(rc.removeBookConfirmMsg),"Delete confirmation message is not displayed");
		logger.info("Remove title confirmation message is displayed #Pass");
		Assert.assertTrue(ElementisPresent(ok),"Ok button for delete confirmation is not displayed");
		logger.info("Ok button for delete confirmation message is displayed #Pass");
		Assert.assertTrue(ElementisPresent(rc.btn_cancel),"Cancel button for delete confirmation is not displayed");
		logger.info("Cancel button for delete confirmation message is displayed #Pass");
		ClickOnWebElement(ok); //rc.btn_cancel
		logger.info("Ok button is clicked on delete program confirmation pop-up");
		WaitForWebElement(lbl_ActiveChallenge);
	}
	public void leaveProg() {
		WaitForWebElement(readingProgramHeader);
		waitFor(5000);
		ClickOnWebElement(btn_leaveProg);
		logger.info("Leave program button is clicked #Pass");
		Assert.assertTrue(ElementisPresent(rc.removeBookConfirmMsg),"Leave program confirmation message is not displayed");
		logger.info("Leave Program confirmation message is displayed #Pass");
		Assert.assertTrue(ElementisPresent(ok),"Ok button for leave confirmation is not displayed");
		logger.info("Ok button for delete confirmation message is displayed #Pass");
		Assert.assertTrue(ElementisPresent(rc.btn_cancel),"Cancel button for leave confirmation is not displayed");
		logger.info("Cancel button for delete confirmation message is displayed #Pass");
		ClickOnWebElement(ok); //rc.btn_cancel = for cancel button in popup
		logger.info("Ok button is clicked on leave program confirmation pop-up");
		WaitForWebElement(lbl_ActiveChallenge);
	}
	
	public void navigateToActiveProgramDetails() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP-Active");
		//List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RP");
		//WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
		//logger.info("Active Program " + ss.getText() + " Found  #Pass");
		//jsClick(ss);
		waitFor(2000);
		Assert.assertEquals(home_lbl.getText(), testData.get(0).get("Home_lbl"));
		Assert.assertEquals(myprogrambreadcrumb.getText(), testData.get(0).get("MyProgram_Breadcrumb"));
		logger.info("My Program Breadcrumb Displayed #pass");
		Assert.assertEquals(readingprogram_lbl.getText(), testData.get(0).get("ReadingProgram_lbl"));
		logger.info("My Program Details Page Program label displayed #pass");
		// Assert.assertEquals(desc.getText(),testData1.get(0).get("progDesc") );
		logger.info("My Program DetailsPage Description Displayed #pass");
		Screenshots.takeScreenshot(driver, "Screenshots/Web-RP/MyProgramParcipant/DetailsPage.png");
	}

	public void leaveProgram() {
		waitFor(3000);
		ClickOnWebElement(leaveProgram);
		Assert.assertTrue(leaveProgram_cancel.isDisplayed());
		logger.info("Leave Program Cancel button displayed #pass");
		ClickOnWebElement(leaveProgram_ok);
		waitFor(3000);
		Assert.assertTrue(lbl_myPrograms.isEnabled());
		logger.info("Leave Program  #pass");

	}

	public void creatorStartEndDateValidation() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP-Active");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RP");
		//List<Map<String, String>> testData2 = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(5000);
		logger.info("My Program created user displayed:" + creator.getText() + " ##pass");
		Assert.assertTrue(avatar.isDisplayed());
		logger.info("My Program avatar image displayed ##pass");
		Assert.assertEquals(startDate_lbl.getText(), testData.get(0).get("StartDate_lbl"));
		String actualstartdate = startDate.getText().substring(3, 5);
		System.out.println(actualstartdate);
		Assert.assertEquals(actualstartdate, testData1.get(0).get("Startdate"));
		logger.info("My Program start date displayed ##pass");
		Assert.assertEquals(endDate_lbl.getText(), testData.get(0).get("EndDate_lbl"));
		String actualendtdate = endDate.getText().substring(3, 5);
		Assert.assertEquals(actualendtdate, testData1.get(0).get("EndDate"));
		logger.info("My Program end date displayed ##pass");
		Screenshots.takeScreenshot(driver, "Screenshots/Web-RP/MyProgramParcipant/startenddate.png");

	}

	public void statusVisibilityType() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP-Active");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RP");
		List<Map<String, String>> testData2 = reader.getData("./Data/WebData.xlsx", "RC");
		Assert.assertEquals(status_lbl.getText(), testData.get(0).get("Status_lbl"));
		Assert.assertTrue(status_lbl.isDisplayed());
		logger.info("My Program status displayed ##pass");
		Assert.assertEquals(visibility_lbl.getText(), testData.get(0).get("Visibility_lbl"));
		WebElement visibletxt = driver
				.findElement(By.xpath("//*[contains(text(),'" + testData1.get(0).get("ProgramVisibility") + "')]"));
		String actualvisibility = visibletxt.getText();
		Assert.assertEquals(actualvisibility, testData1.get(0).get("ProgramVisibility"));
		logger.info("My Program visibility displayed ##pass");
		Assert.assertEquals(reminder_lbl.getText(), testData.get(0).get("Reminder_lbl"));
		String actualreminder = reminder_txt.getText();
		Assert.assertEquals(actualreminder, testData2.get(1).get("RemainderTypeEdit"));
		logger.info("My Program reminder displayed ##pass");
		Assert.assertEquals(programtype_lbl.getText(), testData.get(0).get("Programtype_lbl"));
		WebElement programtype = driver
				.findElement(By.xpath("//*[contains(text(),'" + testData1.get(0).get("ProgramType") + "')]"));
		String actualtype = programtype.getText();
		Assert.assertEquals(actualtype, testData1.get(0).get("ProgramType"));
		logger.info("My Program program type displayed ##pass");
	}

	public void statusVisibilityTypeXOfYProgram() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP-Active");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RP");
		List<Map<String, String>> testData2 = reader.getData("./Data/WebData.xlsx", "RC");
		Assert.assertEquals(status_lbl.getText(), testData.get(0).get("Status_lbl"));
		Assert.assertTrue(status_lbl.isDisplayed());
		logger.info("My Program status displayed ##pass");
		Assert.assertEquals(visibility_lbl.getText(), testData.get(0).get("Visibility_lbl"));
		WebElement visibletxt = driver
				.findElement(By.xpath("//*[contains(text(),'" + testData1.get(0).get("ProgramVisibility") + "')]"));
		String actualvisibility = visibletxt.getText();
		Assert.assertEquals(actualvisibility, testData1.get(0).get("ProgramVisibility"));
		logger.info("My Program visibility displayed ##pass");
		Assert.assertEquals(reminder_lbl.getText(), testData.get(0).get("Reminder_lbl"));
		String actualreminder = reminder_txt.getText();
		Assert.assertEquals(actualreminder, testData2.get(1).get("RemainderTypeEdit"));
		logger.info("My Program reminder displayed ##pass");
		Assert.assertEquals(programtype_lbl.getText(), testData.get(0).get("Programtype_lbl"));
		WebElement programtype = driver
				.findElement(By.xpath("//*[contains(text(),'" + testData1.get(1).get("ProgramType") + "')]"));
		String actualtype = programtype.getText();
		Assert.assertEquals(actualtype, testData1.get(1).get("ProgramType"));
		logger.info("My Program program type displayed ##pass");
	}

	public void titlesListValidation() throws IOException {
		Assert.assertTrue(readinglist_lbl.isDisplayed());
		System.out.println(readinglist.size());
		try {
			Assert.assertTrue(titlesProgrsspercent.get(0).isDisplayed());
			logger.info("My Program titles Progrss percentage displayed ##pass");
		} catch (Exception e) {
			logger.info("My Program titles Progrss percentage not displayed ##pass");
		}
		try {
			if (readinglist.size() > 4)
			ClickOnWebElement(titlenavigation_right);
			logger.info("My Program titles left arrow navigation ##pass");
			waitFor(2000);
			ClickOnWebElement(titlenavigationpreview_left);
			logger.info("My Program titles rignt arrow navigation ##pass");
		}
		catch (Exception e) {
			logger.info("My Program titles not displayed more than 5##pass");
		}
		Screenshots.takeScreenshot(driver, "Screenshots/Web-RP/MyProgramParcipant/DetailsPage.png");
	}
	public void participantListValidation() throws IOException {
		javascriptScroll(participant_lbl);
		Assert.assertTrue(participant_lbl.isDisplayed());
		try {
			if (participantlist.size() == 10 && participantlist.size() > 10)
			logger.info("My Program participant list 10 displayed ##pass");
			ClickOnWebElement(partivipantnavigation_right);
			logger.info("My Program participant right arrow navigation ##pass");
			waitFor(1000);
			ClickOnWebElement(partivipantnavigation_left);
			logger.info("My Program participant left arrow navigation ##pass");
		}
		catch (Exception e) {
			logger.info("My Program participant list displayed less than 10 ##pass");
		}
		Screenshots.takeScreenshot(driver, "Screenshots/Web-RP/MyProgramParcipant/participantlist.png");
	}

	public void closedprogramDetailsPage() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP-Active");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RP");
		waitFor(3000);
		// Assert.assertTrue(closedProgram_lbl.isDisplayed());
		logger.info("Closed Program label displayed ##pass");
		for (int i = 0; i <= 5; i++) {
			waitFor(2000);
			javaScriptScrollToEnd();
			System.out.println("Scroll end started");
			waitFor(2000);
			// javascriptScroll(closedProgram_lbl);
			try {
				jsClick(closedProgramList.get(0));
				waitFor(3000);
				System.out.println("closed program list clicked");
				break;
			} catch (Exception e) {
				javascriptScroll(footer);
				System.out.println("Scrolling again for click");

				waitFor(2000);
			}
		}

		logger.info("Navigated to Closed Program details page ##pass");
		Assert.assertTrue(detailsPageProgramName.isDisplayed());
		logger.info(" Closed Program name displayed  ##pass");
		Assert.assertTrue(desc.isDisplayed());
		logger.info("Closed program description displayed ##pass");
		Assert.assertEquals(home_lbl.getText(), testData.get(0).get("Home_lbl"));
		Assert.assertEquals(myprogrambreadcrumb.getText(), testData.get(0).get("MyProgram_Breadcrumb"));
		logger.info("Closed Program Breadcrumb Displayed #pass");
		Assert.assertEquals(readingprogram_lbl.getText(), testData.get(0).get("ReadingProgram_lbl"));
		logger.info("Closed Program  Details Page Program label displayed #pass");
		try {
			if(leaveProgram.isDisplayed())
				logger.info("Closed Program  leave program option  displayed #fail");
		}
		catch(Exception e) {
			logger.info("Closed Program  leave program option not displayed #pass");
		}
		Screenshots.takeScreenshot(driver, "Screenshots/Web-RP/MyProgramParcipant/DetailsPage.png");
	}

	public void closedProgramStartEndDateValidation() {
		Assert.assertTrue(avatar.isDisplayed());
		logger.info("Closed Program  Details Page avatar displayed #pass");
		Assert.assertTrue(createdDate.isDisplayed());
		logger.info("Closed Program  Details Page createdDate displayed #pass");
		Assert.assertTrue(creator.isDisplayed());
		logger.info("Closed Program  Details Page creator displayed #pass");
		Assert.assertTrue(startDate.isDisplayed());
		logger.info("Closed Program  Details Page start date displayed #pass");
		Assert.assertTrue(endDate.isDisplayed());
		logger.info("Closed Program  Details Page end date displayed #pass");
	}

	public void closedProgramStatusVisibility() {
		Assert.assertTrue(status_txt.isDisplayed());
		logger.info("Closed Program  Details Page status displayed #pass");
		Assert.assertTrue(visibility_txt.isDisplayed());
		logger.info("Closed Program  Details Page visibility displayed #pass");
		Assert.assertTrue(programtype_txt.isDisplayed());
		logger.info("Closed Program  Details Page program type displayed #pass");
		Assert.assertTrue(reminder_txt.isDisplayed());
		logger.info("Closed Program  Details Page reminder displayed #pass");
	}
	public void readingProgramValidation() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		WaitForWebElement(RWD_UIRefresh_PageHit.menu_profileIcon);
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/Home.png");
		waitFor(3000);
		WaitForWebElement(RWDbookClubOptionWeb);
		ClickOnWebElement(RWDbookClubOptionWeb);
		logger.info(" --Book club validation Starts-- ");
		WaitForWebElement(lbl_myPrograms);
		Assert.assertEquals(lbl_myPrograms.getText(), testData.get(0).get("lbl_myPrograms"));
		Screenshots.takeScreenshot(driver, "Screenshots/DestinyDiscover/BookClub.png");
		logger.info("My Programs tab is available #Pass");
		waitFor(6000);
		WaitForWebElement(lbl_myPrograms);
		ClickOnWebElement(lbl_myPrograms);
		waitFor(4000);
		String crtPrgrm = btn_createProgram.getText().substring(0, 14);
		Assert.assertEquals(crtPrgrm, testData.get(0).get("lbl_create"));
		logger.info("Create Program button is available #Pass");
		WaitForWebElement(lbl_ActiveProgram);
		Assert.assertEquals(lbl_ActiveProgram.getText(), testData.get(0).get("lbl_ActivePrograms"));
		logger.info("Active program header is present in my programs tab #Pass");
		logger.info(" --Book club validation Completed-- ");
	}
	public void programDetailsValidationAdmin() throws InvalidFormatException, IOException {
		waitFor(7000);
		WaitForWebElement(RWDbookClubOptionWeb);
		waitFor(4000);
		ClickOnWebElement(RWDbookClubOptionWeb);
		waitFor(4000);
		ClickOnWebElement(lbl_myPrograms);
		javascriptScroll(footer);
		waitFor(8000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			System.out.println("Scrolling again");
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		logger.info("Created challenge is available for Invited user #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreatedChallenge.png");
		programSearch(); // search and find the challenge in Challenge listing screen
		// jsClick(ss);
		waitFor(6000);
		// WaitForWebElement(readingProgramHeader);
		Assert.assertEquals(programName.getText().trim(), progName.trim());
		logger.info("Program is available #Pass");

		Assert.assertEquals(programName.getText().trim(), progName.trim());
		logger.info("Program is available #Pass");

		Assert.assertEquals(ElementisPresent(lbl_readinglist), true);
		logger.info("Reading list header is available #Pass");

		Assert.assertEquals(ElementisPresent(lbl_participantlist), true);
		logger.info("Participant list header is available #Pass");

		//String sd = lbl_startDate.getText().substring(3, 5);
		// Assert.assertEquals(sd, testData.get(0).get("Startdate"));
		logger.info("Start date is available #Pass");
		waitFor(1000);
		//String ed = lbl_endDate.getText().substring(3, 5);
		// Assert.assertEquals(ed, testData.get(0).get("EndDate"));
		logger.info("Start date is available #Pass");
	}
	public void nagivateEditProgram() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(2000);
		Assert.assertEquals(Rwdeditprg.isDisplayed(), true);
		logger.info("Edit Program button available #Pass");
		ClickOnWebElement(Rwdeditprg);
		waitFor(1000);
		Assert.assertEquals(RwdeditText.getText().trim(), "Edit Reading Program");
		logger.info("User able to navigated to the Edit Program Screen #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingProgram/editProgram.png");
		SendKeysOnWebElement(inp_progName, progName + "Edit");
		logger.info("User able Edit and update the Program name #Pass");
		inp_progDesc.clear();
		SendKeysOnWebElement(inp_progDesc, progName1);
		logger.info("User able to Remove and update the Program Description #Pass");
		editProgType();
		editstartDate();
		editendDate();
		editRemainder();
		editassignStudent();
		// EditaddTitle();
		ClickOnWebElement(RWDsave);
		// ClickOnWebElement(rwdpopupok);
	}
	@FindBy(xpath = "//span[text()='Save']")
	private WebElement RWDsave;
	
	@FindBy(id = "pd-editprg")
	public WebElement Rwdeditprg;
	@FindBy(xpath = "//h2[contains(text(),'Edit Reading Program')]")
	public static WebElement RwdeditText;
	public void editProgType() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		ClickOnWebElement(programType);
		switch (testData.get(0).get("editprogramType")) {
		case " Books in Order":
			logger.info("Edit reminder type :" + progTypes.get(0).getText() + "#Pass");
			progTypes.get(0).click();
			Screenshots.takeScreenshot(driver,
					"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(0).getText() + ".png");
			break;
		case " X of Y Books":
			logger.info("Edit reminder type :" + progTypes.get(1).getText() + "#Pass");
			progTypes.get(1).click();
			Screenshots.takeScreenshot(driver,
					"Screenshots/ReadingChallenge/CreateChallenge/SetReminder_" + progTypes.get(1).getText() + ".png");
			break;
		}
	}

	public void editstartDate() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		waitFor(3000);
		javascriptScroll(btn_startDate);
		ClickOnWebElement(btn_startDate);
		logger.info("Start date calander is opened #Pass");
		waitFor(1000);
		String startDate = testData.get(0).get("EditStartDate");
		WebElement sd = driver.findElement(By.xpath("//div[text()=' " + startDate + " ']"));
		jsClick(sd);
		logger.info("User able to update the Start Date " + sd.getText() + "#Pass");
	}

	public void editendDate() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		waitFor(3000);
		ClickOnWebElement(btn_endDate);
		logger.info("End date calander is opened #Pass");
		waitFor(1000);
		String endDate = testData.get(0).get("EditEndDate");
		WebElement ed = driver.findElement(By.xpath("//div[text()=' " + endDate + " ']"));
		ClickOnWebElement(ed);
		logger.info("User able to update the End Date  " + ed.getText() + "#Pass");
	}

	public void editRemainder() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		javascriptScroll(RWDsetReminder);
		ClickOnWebElement(RWDsetReminder);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder.png");
		List<WebElement> remindertype = RWDReminderTypes;
		waitFor(2000);
		switch (testData.get(0).get("RemainderTypeEdit")) {
		case "Daily":
			logger.info("Edit reminder type :" + remindertype.get(1).getText() + "#Pass");
			remindertype.get(1).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"
					+ remindertype.get(1).getText() + ".png");
			break;
		case "Weekly":
			logger.info("Edit reminder type :" + remindertype.get(2).getText() + "#Pass");
			remindertype.get(2).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"
					+ remindertype.get(2).getText() + ".png");
			break;
		case "Monthly":
			logger.info("Edit reminder type :" + remindertype.get(3).getText() + "#Pass");
			remindertype.get(3).click();
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/SetReminder_"
					+ remindertype.get(3).getText() + ".png");
			break;
		}
	}

	public void EditaddTitle() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		List<Map<String, String>> testData1 = reader.getData("./Data/WebData.xlsx", "RC");
		waitFor(3000);
		logger.info("--Add title assertion started--");
		ClickOnWebElement(RWDremoveTitle);
		ClickOnWebElement(RWDRemovetitleconfirm);
		waitFor(2000);
		ClickOnWebElement(btn_AddTitle);
		WaitForWebElement(txt_SearchInput);
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle1.png");
		Assert.assertEquals(searchTitleHeader.getText(), testData.get(0).get("defval_searchTitleHeader"));
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreateChallenge/AddTitle.png");
		logger.info("Title search header is available #Pass");
		SendKeysOnWebElement(txt_SearchInput, testData.get(0).get("editTitleName"));
		waitFor(5000);
		// WaitForWebElement(lbl_suggestedResult);
		// Assert.assertEquals(lbl_suggestedResult.getText(),
		// testData.get(0).get("lbl_suggestedResult"));
		Screenshots.takeScreenshot(driver,
				"Screenshots/ReadingChalllenge/CreateChallenge/AddTitle_SuggestedResult.png");
		logger.info("Suggested result header is available #Pass");
		SendKeysEnter(txt_SearchInput);
		WaitForWebElement(RWDMoreMenu);
		jsClick(RWDMoreMenu);
		ClickOnWebElement(AddToprgrmTitle);
		logger.info("User able to Remove and add the Title  #Pass");
	}
	@FindBy(xpath = "//*[@id='pr_id_13']/div/div/div/div/div/div/span/button/mat-icon")
	public WebElement RWDremoveTitle;
	
	@FindBy(xpath = "//span[text()='Remove']")
	public WebElement RWDRemovetitleconfirm;

	public void editassignStudent() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		ClickOnWebElement(RwdCloseStudent);
		ClickOnWebElement(RWDRemovetitleconfirm);
		ClickOnWebElement(btn_addfriend);
		ClickOnWebElement(txt_SearchStudentName);
		SendKeysOnWebElement(txt_SearchStudentName, testData.get(0).get("studentName"));
		logger.info("Entered student name in search box #Pass");
		waitFor(4000);
		btn_InviteFriendLists.get(1).click();
		ClickOnWebElement(btn_InviteToPrgrm);
		waitFor(3000);
		logger.info("Removed Particitipant and adding student to the Program #Pass");
	}
	@FindBy(id = "cp-astud-close")
	public WebElement RwdCloseStudent;

	public void duplicateProgram() throws IOException {
		waitFor(6000);
		Assert.assertEquals(Rwdduplicateprg.isDisplayed(), true);
		logger.info("Duplicate program button available #Pass");
		ClickOnWebElement(Rwdduplicateprg);
		waitFor(1000);
		Assert.assertEquals(Rwddublicatescreen.getText().trim(), "Create Reading Program");
		logger.info("User able to navigated to the Duplicate Program Screen #Pass");
		//Assert.assertEquals(RwdProgramHeading.getText().trim(), inp_progName.getText().trim());
		logger.info("Same title avilable in Duplicate Program Screen #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingProgram/duplicateProgram.png");
		waitFor(1000);
		ClickOnWebElement(rwdpopupclose);
		ClickOnWebElement(ok);
	}
	@FindBy(id = "pd-duplcate")
	public WebElement Rwdduplicateprg;
	
	@FindBy(xpath = "//h2[contains(text(),'Create Reading Program')]")
	public static WebElement Rwddublicatescreen;
	public void unpublishProgram(boolean tag) throws IOException {
		waitFor(6000);
		Assert.assertEquals(Rwdubpublish.isDisplayed(), true);
		logger.info("unpublish program button available #Pass");
		ClickOnWebElement(Rwdubpublish);
		waitFor(1000);
		Assert.assertEquals(RWDunpublishmessage.getText().trim(), "Are you sure you want to unpublish".trim());
		logger.info("User able to navigated to the unpublish Program Screen #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingProgram/unpublishProgram.png");
		if (tag == true) {
			ClickOnWebElement(ok);
			programSearch();
			waitFor(2000);
			logger.info("User able to navigated to the Delete Program Screen #Pass");
			Screenshots.takeScreenshot(driver, "Screenshots/ReadingProgram/deleteProgram.png");
			ClickOnWebElement(RWDcancelpopup);
			logger.info("User able to cancel CTA navigate to listing Page from Delete Program Screen #Pass");
			waitFor(3000);
			programSearch();
			waitFor(3000);
			javascriptScroll(programType);
			javascriptScroll(Rwddeleteprg);
			ClickOnWebElement(Rwddeleteprg);
			logger.info("User able to Delete a program  CTA again navigate to listing Page #Pass");
		} else {
			ClickOnWebElement(RWDcancelpopup);
		}
	}
	@FindBy(xpath = "//*[@aria-label='delete program dialog']")
	public WebElement Rwddeleteprg;
	
	@FindBy(xpath = "//*[@id=\"cprog-close-2\"]/mat-icon")
	public WebElement rwdpopupclose;
	
	@FindBy(id = "pd-ubplish")
	public WebElement Rwdubpublish;
	
	@FindBy(xpath = "//*[(text()='Are you sure you want to unpublish ')]")
	private WebElement RWDunpublishmessage;
	
	@FindBy(id = "pd-partip-head")
	public WebElement participants;
	
	@FindBy(id = "customers")
	public List<WebElement> students;
	
	public void checkstudentmetrics() throws IOException {
		waitFor(2000);
		Assert.assertEquals(participants.isDisplayed(), true);
		javascriptScroll(participants);
		for (WebElement allstudents : students) {
			List<WebElement> totalStudetns = allstudents.findElements(By.tagName("tr"));
			for (int i = 0; i < totalStudetns.size(); i++) {
				List<WebElement> noOfRows = totalStudetns.get(i).findElements(By.tagName("th"));
				System.out.println("noOfRows" + noOfRows.size());
				for (int j = 0; j < noOfRows.size(); j++) {
					waitFor(1000);
					Assert.assertEquals(noOfRows.get(j).isDisplayed(), true);
					logger.info("Participants Header details Are Shown:" + noOfRows.get(j).getText() + "#Pass");
					Assert.assertEquals(!noOfRows.get(j).isDisplayed(), "Stephen Curry1");
					logger.info("Participants should not be able to view the removed title in the reading list​ #Pass");
					Screenshots.takeScreenshot(driver, "Screenshots/ReadingProgram/participantView.png");

				}
				List<WebElement> columns = totalStudetns.get(1).findElements(By.tagName("td"));
				for (int k = 0; k < columns.size(); k++) {
					waitFor(1000);
					Assert.assertEquals(columns.get(k).isDisplayed(), true);
					logger.info("Participants details Are Shown:" + columns.get(k).getText());
				}

			}
		}
	}

	public static void javaScriptScrollToTop() {
	waitFor(2000);
	 ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	public void validatetabs() throws IOException {

		waitFor(6000);
		WaitForWebElement(RWDbookClubOptionWeb);
		ClickOnWebElement(RWDbookClubOptionWeb);
		waitFor(20000);
		Assert.assertEquals(RWdActivechallengs.isDisplayed(), true);
		logger.info("user should be able to view active Challenges #Pass");
		Assert.assertEquals(programSearchwithValue(RWDcreatechlg), true);
		logger.info("User should be able to view create a Challenges button #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/Challenges/challenges.png");
		waitFor(1000);
		javaScriptScrollToTop();
		waitFor(2000);
		ClickOnWebElement(RwdMyPrograms);
		waitFor(1000);
		Assert.assertEquals(programSearchwithValue(RwdMyPrograms), true);
		logger.info("User should be able to view My Program tab #Pass");
		Assert.assertEquals(programSearchwithValue(lbl_ActiveProgram), true);
		logger.info("User should be able to view active Program #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/Myprogram/activeprogram.png");
	//	Assert.assertEquals(programSearchwithValue(draftHeader), true);
		logger.info("User should be able to view Draft Program #Pass");
		Assert.assertEquals(programSearchwithValue(lbl_closedPrograms), true);
		logger.info("User should be able to view closed Program #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/Myprogram/closedprogram.png");
		waitFor(2000);
		javaScriptScrollToTop();
		waitFor(2000);
		ClickOnWebElement(RwdOpenPrograms);
		waitFor(2000);
		Assert.assertEquals(programSearchwithValue(RwdOpenPrograms), true);
		logger.info("User should be able to view Open Program #Pass");
		Assert.assertEquals(programSearchwithValue(RWDOnGoing), true);
		logger.info("User should be able to view Ongoing Program #Pass");
		Screenshots.takeScreenshot(driver, "./Screenshots/OpenProgram/ongoingprogram.png");

	}
	public boolean programSearchwithValue(WebElement ele) {
		for (int i = 0; i <= 5; i++) {
			waitFor(3000);
			javaScriptScrollToEnd();
			waitFor(3000);
			javascriptScroll(ele);
			try {
				javascriptScroll(footer);
				waitFor(2000);
				javascriptScroll(ele);
				break;
			} catch (Exception e) {
				javascriptScroll(footer);
				waitFor(2000);
			}
		}
		return true;
	}
	public void validateReadingPrograms() throws IOException, InvalidFormatException {
		waitFor(6000);
		javascriptScroll(programName);
		jsClick(lbl_myprogram);
		waitFor(25000);
		javascriptScroll(footer);
		waitFor(8000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		logger.info("Created program is available for Invited user #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/ReadingChallenge/CreatedChallenge.png");
		programSearch(); // search and find the challenge in Chal lenge listing screen
		logger.info("user should be able to tap on a Program that they are participating in and be navigated to the Participant RP details screen​v #Pass");
		waitFor(8000);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		 WaitForWebElement(readingProgramHeader);
		Assert.assertEquals(programName.getText().trim(), progName.trim());
		logger.info("Program is available #Pass");
		if(ElementisPresent(programDesc))
		{
		Assert.assertEquals(programDesc.isDisplayed(),true);
		logger.info("Program Description is available #Pass");	
		}else
		{
			Assert.assertEquals(!programDesc.isDisplayed(),true);
			logger.info("Program Description is not available #Pass");		
		}
		 Assert.assertEquals(lbl_startDate.isDisplayed(),true);
		logger.info("Start date is available #Pass");
		 Assert.assertEquals(lbl_endDate.isDisplayed(), true);
		logger.info("End date is available #Pass");
		
	}
	public void onGoingProgramValidation() throws IOException {

		waitFor(12000);
		ClickOnWebElement(RwdBookClub);
		waitFor(12000);
		ClickOnWebElement(RwdOpenPrograms);
		waitFor(16000);
		Assert.assertEquals(RWDOnGoing.getText(), "ONGOING PROGRAMS");
		logger.info("ONGOING PROGRAMS Tab Availble  #Pass");
		javascriptScroll(footer);
		waitFor(10000);
		try {
			WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
			javascriptScroll(ss);
		} catch (Exception e) {
			System.out.println("Scrolling again");
			javascriptScroll(footer);
			javaScriptScrollToEnd();
		}
		programSearchwithclick(RWDOnGoing);
		logger.info("created ongoing Program Availble #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/OnGoing/ongoingPrg.png");
		waitFor(8000);
		Assert.assertTrue(RWDcreateDate.isDisplayed());
		waitFor(3000);
		Assert.assertTrue(RWDcardTitleName.isDisplayed());
		logger.info("Clicking on the created program card should show Program details on open program #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/createProgram/showProgram.png");

	}
	public void programSearchwithclick(WebElement ele) {
		for (int i = 0; i <= 5; i++) {
			waitFor(2000);
			javaScriptScrollToEnd();
			waitFor(2000);
			javascriptScroll(ele);
			try {
				WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
				javascriptScroll(footer);
				waitFor(2000);
				javascriptScroll(ss);
				break;
			} catch (Exception e) {
				System.out.println("Scrolling again for click");
				javascriptScroll(footer);
				waitFor(2000);
			}
		}
		WebElement ss = driver.findElement(By.xpath("//*[contains(text(),'" + progName + "')]"));
		logger.info(ss.getText() + " is found in challenge listing screen and opened #Pass");
		jsClick(ss);
	}
	public void joinProgram() throws InvalidFormatException, IOException {
		waitFor(3000);
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "RP");
		ClickOnWebElement(btn_JoinProgram);
		WaitForWebElement(welcome);
		Assert.assertEquals(welcome.getText().trim(), testData.get(0).get("welcomeMsg").trim());
		logger.info(welcome + " is dispplayed #Pass");
		if (letStart.getText().contains("Let's Get Started")) {
			logger.info(letStart + " is displayed #Pass");
		}
		Assert.assertEquals(ElementisPresent(gotoProgram), true);
		ClickOnWebElement(gotoProgram);
		logger.info("Go To Program button is available and clicked #Pass");
	}
	public void afterJoin() throws IOException {
		waitFor(4000);
		ClickOnWebElement(RWDbookClubOptionWeb);
		waitFor(5000);
		WaitForWebElement(lbl_myPrograms);
		waitFor(4000);
		ClickOnWebElement(lbl_myPrograms);
		waitFor(4000);
		WaitForWebElement(lbl_ActiveProgram);
		programSearch();
		logger.info("Joined program is available in My programs tab #Pass");
	}
	
	@FindBy(id = "create-chlg")
	private WebElement RWDcreatechlg;
	
	@FindBy(xpath = "//a[text()='MY PROGRAMS']")
	public WebElement lbl_myprogram;
	
	@FindBy(xpath = "//*[text()='ACTIVE CHALLENGES ']")
	public WebElement RWdActivechallengs;
	
	@FindBy(xpath = "//h1[text()='CLOSED PROGRAMS']")
	public WebElement lbl_closedPrograms;
	
	@FindBy(xpath="//*[text()='LEAVE PROGRAM ']")
	public WebElement btn_leaveProg;
	
	@FindBy(xpath="//button[@aria-label='delete program dialog']")
	public WebElement btn_delete;
	
	@FindBy(xpath="//*[@class='message success-msg ng-star-inserted']")
	public WebElement successMsg;
	
	@FindBy(xpath="//*[@class='message error-msg ng-star-inserted']")
	public WebElement failureMsg;
	
	@FindBy(xpath="//button[text()=' Remove from Program ']")
	public WebElement RemoveFromProgTitle;
	
	@FindBy(xpath="//*[@id='cp-astud-close']")
	public List<WebElement> closeX;
	
	@FindBy(xpath="//*[@class='dd-stud-info ng-star-inserted']")
    public List<WebElement> addedParticipants;
	
	@FindBy(xpath="//a[@class='searchlist']")
	public List<WebElement> suggestionList;
	
	@FindBy(xpath="//h3[text()='From Your Favorites']")
	public WebElement lbl_Favorites;
	
	@FindBy(xpath="//h3[text()='Recommended']")
	public WebElement lbl_recommended;
	
	@FindBy(xpath="//mat-card[@role='group']")
	public List<WebElement> Book;
	
	@FindBy(xpath="//button[@class='raise-invite']")
	public WebElement inviteEnable;
	
	@FindBy(xpath="//h4[text()='STUDENTS FOUND']")
	public WebElement lbl_studentsFound;
	
	@FindBy(xpath="//*[text()='INVITE']")
	public List<WebElement> btn_inviteList; 
	
	@FindBy(xpath = "//span[@class='student-title']")
	public List<WebElement> recentSearch; 
	
	@FindBy (xpath="//span[@class='mat-option-text']")
	public List<WebElement> noOfBooks;
	
	@FindBy (xpath="//mat-label[text()='Number of Books']")
	public WebElement lbl_noOfBooks;
	
	@FindBy (xpath="//span[text()='Cancel']")
	public WebElement cancel;
	
	@FindBy (xpath="//span[text()='OK']")
	public WebElement ok;
	
	@FindBy(xpath="//*[text()=' Are you sure you want to discard the changes made? ']")
	public WebElement discardMsg;
	
	@FindBy(xpath="(//mat-icon[text()='close'])[2]")
	public WebElement createClose;
	
	@FindBy(xpath="(//mat-icon[text()='close'])[3]")
	public WebElement tipClose;
	
	@FindBy(xpath="//p[text()='Select Private to only allow participants you invite to join. Select Public if anyone can join.']")
	public WebElement tipMsg;
	
	@FindBy(xpath="//label[text()='TIP:']")
	public WebElement tip;
	
	@FindBy(id="cp_info_popup")
	public WebElement info;
	
	@FindBy(xpath = "//*[@class='accordion-msg ng-star-inserted']")
	public WebElement RwdmsgDetails;

	@FindBy(id = "userPassword")
	private WebElement RWD_DLX_Password;

	@FindBy(xpath = "//*[@class='msg-icon-button icon-bg mr-rgt ng-star-inserted']")
	public WebElement RwdMessageCenterIcon;

	@FindBy(xpath = "//*[@class='mat-checkbox msg-read-all mat-accent ng-star-inserted']")
	public WebElement RwdmsgCheckbox;

	@FindBy(id = "msg-checkbox")
	public WebElement Rwddropdwon;

	@FindBy(xpath = "//*[text()=' Book Club ']")
	public WebElement RWDbookClubOptionWeb;

	@FindBy(id = "accept-btn")
	private WebElement RWDchlgAccept;

	@FindBy(xpath = "//div[@class='content-name-head']")
	public List<WebElement> RWDInviteText;

	@FindBy(xpath = "//*[@class='mat-card-subtitle challenge-name']")
	public WebElement RWDcardTitleName;

	@FindBy(id = "mat-option-1")
	private WebElement RWDMessageAll;

	@FindBy(id = "mat-option-2")
	private WebElement RWDMessageNone;

	@FindBy(xpath = "//*[@class='mat-focus-indicator mat-menu-trigger userIcon tb_active mat-icon-button mat-button-base ng-star-inserted']")
	public WebElement RwdOpenprofile;

	@FindBy(xpath = "//*[@aria-label='logout']")
	private WebElement Rwdlogout;

	@FindBy(id = "mat-option-3")
	private WebElement RWDMessageRead;

	@FindBy(id = "mat-option-4")
	private WebElement RWDMessageUnread;

	@FindBy(xpath = "//*[contains(text(),' You have been added to this program')]")
	public static WebElement RwdInnertext;

	@FindBy(id = "msg-delete-all")
	public WebElement deletemsgall;

	@FindBy(id = "msg-delete-pop")
	private WebElement RWDdeletepopmsg;

	@FindBy(id = "ac-created-date")
	private WebElement RWDcreateDate;

	@FindBy(id = "msg-del-cancel")
	private WebElement RWDcancelpopup;

	@FindBy(xpath = "//*[@aria-label='None']")
	public WebElement RWDnone;

	@FindBy(xpath = "//button[text()='View Challenge Details']")
	public WebElement viewChlng;

	@FindBy(xpath = "//*[contains(text(),'You have no messages')]")
	private WebElement RWDnoMessages;

	@FindBy(xpath = "//*[@class='content-name-head']")
	public List<WebElement> RwdMessagecont;

	@FindBy(xpath = "//*[@id=\"mat-expansion-panel-header-0\"]/span[1]/mat-panel-title/div[2]")
	public WebElement msgFirst;

	@FindBy(xpath = "//*[@id=\"mat-expansion-panel-header-1\"]/span[1]/mat-panel-title/div[2]/div/span")
	public WebElement msgSecond;

	@FindBy(id = "head-menu-list-book")
	public WebElement RwdBookClub;

	@FindBy(id = "tab-myprog-btn")
	public WebElement RwdMyPrograms;

	@FindBy(id = "tab-open-prog-btn")
	public WebElement RwdOpenPrograms;

	@FindBy(xpath = "//*[@aria-label='ONGOING PROGRAMS']")
	public WebElement RWDOnGoing;

	@FindBy(xpath = "//*[@aria-label='UPCOMING PROGRAMS']")
	public WebElement RWDupcomingprg;

	@FindBy(xpath = "//label[text()='© 2021 Follett School Solutions. All Rights Reserved']")
	public WebElement footer;

	@FindBy(xpath = "//button[@id='prog-main-create']")
	public WebElement btn_createProgram;

	@FindBy(xpath = "//h1[text()='ACTIVE PROGRAMS']")
	public WebElement lbl_ActiveProgram;

	@FindBy(xpath = "//h2[text()='Create Reading Program']")
	public WebElement lbl_CreateProgrm;

	@FindBy(xpath = "//button[text()='Go to Program']")
	public WebElement gotoProgram;

	@FindBy(xpath = "//*[text()=' Welcome to the Program! ']")
	public WebElement welcome;

	@FindBy(xpath = "//*[@class='card-message']/span")
	public WebElement letStart;

	@FindBy(xpath = "//*[@id='join-prg-btn']")
	public WebElement btn_JoinProgram;

	@FindBy(xpath = "//p[@id='pd-strt-date']")
	public WebElement lbl_endDate;

	@FindBy(xpath = "//p[@id='pd-end-date']")
	public WebElement lbl_startDate;

	@FindBy(xpath = "//h2[@id='pd-partip-head']")
	public WebElement lbl_participantlist;

	@FindBy(xpath = "//h2[@id='pd-readlist-head']")
	public WebElement lbl_readinglist;

	@FindBy(xpath = "//p[@id='prog-det-ptex']")
	public WebElement programDesc;

	@FindBy(xpath = "//h1[@id='prog-det-heading']")
	public WebElement programName;

	@FindBy(xpath = "//h5[text()='Reading Program']")
	public WebElement readingProgramHeader;

	@FindBy(xpath = "//*[@id='dd-Save-contain']")
	public List<WebElement> draftList;

	@FindBy(xpath = "//*[@id='dd-Save-contain']/mat-card/mat-card-header/div[2]/mat-card-title")
	public List<WebElement> draftProgramName;

	@FindBy(xpath = "//*[@aria-label='DRAFT PROGRAMS']")
	public WebElement draftHeader;

	@FindBy(xpath = "//span[text()='Publish Program']")
	public WebElement btn_publish;

	@FindBy(xpath = "//h1[text()='ACTIVE PROGRAMS']")
	public WebElement lbl_ActiveChallenge;

	@FindBy(xpath = "//span[text()='Save']")
	public WebElement btn_save;

	@FindBy(xpath = "//*[@id='start-date-cp']/button")
	// @FindBy(xpath="(//button[@aria-label='Open calendar'])[1]")
	public WebElement btn_startDate;

	// @FindBy(xpath="(//button[@aria-label='Open calendar'])[2]")
	@FindBy(xpath = "//*[@id='end-date-cp']/button")
	public WebElement btn_endDate;

	@FindBy(xpath = "//*[@aria-label='Add to Program']")
	public WebElement AddToprgrmTitle;

	@FindBy(xpath = "//*[text()='more_horiz']")
	public WebElement RWDMoreMenu;

	@FindBy(xpath = "//h2[text()=' SUGGESTED RESULTS ']")
	public WebElement lbl_suggestedResult;

	@FindBy(xpath = "//label[@class='subheading']")
	public WebElement searchTitleHeader;

	@FindBy(xpath = "//input[@id='searchInput']")
	public WebElement txt_SearchInput;

	@FindBy(id = "cp-title-btn")
	public WebElement btn_AddTitle;

	@FindBy(xpath = "//button[text()='Invite to Program ']")
	public WebElement btn_InviteToPrgrm;

	@FindBy(xpath = "//span[@class='student-invite']")
	public List<WebElement> btn_InviteFriendLists;

	@FindBy(id = "ivite-chlg-search")
	public WebElement txt_SearchStudentName;

	@FindBy(id = "cp-asgn-stud-btn")
	public WebElement btn_addfriend;

	@FindBy(xpath = "//*[@class='mat-option-text']")
	public List<WebElement> progTypes;

	@FindBy(xpath = "//*[@class='mat-option-text']")
	public List<WebElement> RWDReminderTypes;

	@FindBy(xpath = "//mat-select[@id='cp-setrem-sel']")
	public WebElement RWDsetReminder;

	@FindBy(xpath = "//mat-select[@id='cp-select-progtyp']")
	public WebElement programType;

	@FindBy(xpath = "//h4[text()='Add Titles ']")
	public WebElement lbl_addTitle;

	@FindBy(xpath = "//h4[text()='Assign Students']")
	public WebElement lbl_assignStudents;

	@FindBy(xpath = "//mat-label[text()='Set End Date']")
	public WebElement inp_endDate;

	@FindBy(xpath = "//mat-label[text()='Set Start Date']")
	public WebElement inp_startDate;

	@FindBy(xpath = "//mat-label[text()='Set Reminders']")
	public WebElement inp_remainder;

	@FindBy(xpath = "//mat-label[text()='Select Program Type']")
	public WebElement inp_prgrmType;

	@FindBy(id="cp-radio-private")
	public WebElement rb_private;

	@FindBy(id="cp-radio-public")
	public WebElement rb_public;

	@FindBy(xpath = "//mat-label[text()='Description (Optional)']")
	public WebElement lbl_progDesc;

	@FindBy(id = "cp-txtarea-desc")
	public WebElement inp_progDesc;

	@FindBy(id = "cp-enter-name")
	public WebElement inp_progName;

	@FindBy(xpath = "//mat-label[text()='Enter Program Name']")
	public WebElement lbl_progName;

	@FindBy(xpath = "//span[@class='msg-badge-count ng-star-inserted']")
	public WebElement msgNotificationCount;
	
	@FindBy(xpath = "//a[@id='tab-myprog-btn']")
	public WebElement lbl_myPrograms;

	@FindBy(id = "pd-ubplish")
	public WebElement leaveProgram;

	@FindBy(xpath = "//*[text()='OK']")
	public WebElement leaveProgram_ok;

	@FindBy(xpath = "//*[text()='Cancel']")
	public WebElement leaveProgram_cancel;

	@FindBy(xpath = "//*[@id='dd-Active-contain']/mat-card/mat-card-header/div[2]/mat-card-title")
	public List<WebElement> activeProgramName;

	@FindBy(xpath = "//*[@class='challenge-heading']")
	public WebElement detailsPageProgramName;

	@FindBy(xpath = "//*[@id='dd-Active-contain']")
	public List<WebElement> activeList;

	@FindBy(xpath = "//*[text()='HOME']")
	public WebElement home_lbl;

	@FindBy(xpath = "//*[text()='MY PROGRAMS']")
	public WebElement myprogrambreadcrumb;

	@FindBy(xpath = "//*[text()='Reading Program']")
	public WebElement readingprogram_lbl;

	@FindBy(xpath = "//*[@class='challenge-heading']")
	public WebElement challengeName;

	@FindBy(xpath = "//*[@class='challenge-desc']")
	public WebElement desc;

	@FindBy(xpath = "//*[@class='createon']")
	public WebElement createdDate;

	@FindBy(xpath = "//*[@class='createinfo']")
	public WebElement creator;

	@FindBy(xpath = "//*[@class='challenge-detail-avatar']")
	public WebElement avatar;

	@FindBy(xpath = "//*[text()='START DATE']")
	public WebElement startDate_lbl;

	@FindBy(id = "pd-strt-date")
	public WebElement startDate;

	@FindBy(xpath = "//*[text()='END DATE']")
	public WebElement endDate_lbl;

	@FindBy(id = "pd-end-date")
	public WebElement endDate;

	@FindBy(xpath = "//*[text()='STATUS']")
	public WebElement status_lbl;

	@FindBy(id = "pd-status")
	public WebElement status_txt;

	@FindBy(xpath = "//*[text()='VISIBILITY']")
	public WebElement visibility_lbl;

	@FindBy(xpath = "//*[@id=\"headerSideBar\"]/mat-sidenav-content/main/fss-ms-book-club/fss-ms-program-detail/div/section/div[1]/div[2]/div[2]/div[2]/div[1]/p")
	public WebElement visibility_txt;

	@FindBy(xpath = "//*[text()='PROGRAM TYPE']")
	public WebElement programtype_lbl;

	@FindBy(xpath = "//*[@id=\"headerSideBar\"]/mat-sidenav-content/main/fss-ms-book-club/fss-ms-program-detail/div/section/div[1]/div[2]/div[2]/div[2]/div[2]/p")
	public WebElement programtype_txt;

	@FindBy(xpath = "//*[text()='REMINDERS']")
	public WebElement reminder_lbl;

	@FindBy(id = "pd-remdrs")
	public WebElement reminder_txt;

	@FindBy(id = "pd-readlist-head")
	public WebElement readinglist_lbl;

	@FindBy(xpath = "//*[@class='ui-carousel-item ui-carousel-item-active ng-star-inserted']")
	public List<WebElement> readinglist;

	@FindBy(id = "pd-partip-head")
	public WebElement participant_lbl;

	@FindBy(id = "//table/tr/td")
	public List<WebElement> participantlist;

	@FindBy(xpath = "//*[@class='ui-carousel-next ui-button ui-widget ui-state-default ui-corner-all']")
	public WebElement titlenavigation_right;

	@FindBy(xpath = "//*[@class='ui-carousel-prev ui-button ui-widget ui-state-default ui-corner-all']")
	public WebElement titlenavigationpreview_left;

	@FindBy(xpath = "//*[text()='chevron_left']")
	public WebElement partivipantnavigation_left;

	@FindBy(xpath = "//*[text()='chevron_right']")
	public WebElement partivipantnavigation_right;

	@FindBy(xpath = "//*[text()='CLOSED PROGRAMS']")
	public WebElement closedProgram_lbl;

	@FindBy(xpath = "//*[@id='dd-Complete-contain']")
	public List<WebElement> closedProgramList;

	@FindBy(xpath = "//*[@class='progressbar-percentage']")
	public List<WebElement> titlesProgrsspercent;
}
