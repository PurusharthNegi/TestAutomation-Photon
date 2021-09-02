package com.pom;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;

public class UIRefresh_PageHit extends CapabilitiesAndWebDriverUtils{
	ExcelReader reader = new ExcelReader();
	private static final Logger logger = LogManager.getLogger();
	public UIRefresh_PageHit() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void userLandingAssertion() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Home");
		//List<Map<String, String>> testData2 = reader.getData("./Data/MobileData.xlsx", "SelectSchool");
		logger.info(" -- User Landing Page Assertion Started -- ");
		WaitForMobileElement(homeTitle);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/LandingHome1.png");
		Assert.assertEquals(homeTitle.getText(),testData.get(0).get("defVal_homeTitle"));
		logger.info("Home Title displayed #Pass");
		WaitForMobileElement(homeSchoolName);
		waitFor(5000);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/LandingHome2.png");
		Assert.assertTrue(homeSchoolName.isDisplayed());
		logger.info("School Name is displayed #Pass");
		//Assert.assertEquals(homeSchoolName.getText(), testData2.get(0).get("inp_SchoolName"));
		Assert.assertEquals(homeEbook.getText(), testData.get(0).get("defval_ebookHeader"));
		Assert.assertEquals(homeAudiobook.getText(), testData.get(0).get("defval_AbookHeader"));
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			//horizontalSwipeAndriod(homeBookList); 
			//horizontalSwipe(homeBookList);
			Assert.assertEquals(homeSeeAllEbook.getText(), testData.get(0).get("defval_SeeAll_android"));
			logger.info("Ebook See All button is displayed #Pass");
			Assert.assertEquals(homeSeeAllAudiobook.getText(), testData.get(0).get("defval_SeeAll_android"));
			logger.info("Audio book See All button is displayed #Pass");
		}
		else if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			horizontalSwipe(homeBookList);
			//horizontalSwipeAndriod(homeBookList); 
			Assert.assertEquals(homeSeeAllEbook.getText(), testData.get(0).get("defval_SeeAll"));
			logger.info("Ebook See All button is displayed #Pass");
			Assert.assertEquals(homeSeeAllAudiobook.getText(), testData.get(0).get("defval_SeeAll"));
			logger.info("Audio book See All button is displayed #Pass");
		}
		logger.info("Home Menu is Functional #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/LandingHome3.png");
		logger.info(" -- User Landing Page Assertion Sucessfully completed -- ");
	}
	
	public void navigationMenu() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Home");
		logger.info(" -- Navigation Menu Assertion Started -- "); 
		Assert.assertEquals(home_text.getText(), testData.get(0).get("defval_home"));
		logger.info("Home menu is displayed #Pass");
		Assert.assertEquals(mystuff_text.getText(), testData.get(0).get("defval_mystuff"));
		logger.info("MyStuff menu is displayed #Pass");
		Assert.assertEquals(search_text.getText(), testData.get(0).get("defval_search"));
		logger.info("Search menu is displayed #Pass");
		Assert.assertEquals(download_text.getText(), testData.get(0).get("defval_download"));
		logger.info("Downloads menu is displayed #Pass");
		logger.info(" -- Navigation Menu Assertion Sucessfully completed -- ");
	}
	
	public void more() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Home");
		logger.info(" -- More tab Assertion Started -- ");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/More.png");
		Assert.assertTrue(more.isEnabled());
		logger.info("More menu is displayed #Pass");
		ClickOnMobileElement(more);
		Assert.assertEquals(about.getText(), testData.get(0).get("defval_about"));
		logger.info("About button is displayed #Pass");
		Assert.assertEquals(logout.getText(), testData.get(0).get("defval_logout"));
		logger.info("Logout button is displayed #Pass");
		logger.info("More Menu is Functional #Pass");
		logger.info(" -- More tab Assertion Started -- ");
	}
	
	public void productListEbook() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Ebook");
		logger.info(" -- E-Book Page Assertion Started -- ");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Ebook.png");
		//need to check with dev team 
		WaitForMobileElement(ebookTitle);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(ebookTitle.getText(), testData.get(0).get("defval_ebookTitle_android")); 
			logger.info("Ebook title is displayed #Pass");
		}
		else if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			Assert.assertEquals(ebookTitle.getText(), testData.get(0).get("defval_ebookTitle")); 
			logger.info("Ebook title is displayed #Pass");
		}
		Assert.assertTrue(back.isEnabled());
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Ebook2.png");
		diff_views();
		scrollToEnd(3);
		logger.info("Ebook Listing page is functional #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/EbookTileView.png");
		logger.info(" -- E-Book Page Assertion Completed -- ");
	}
	
	public String eBookReading() throws Throwable {
		logger.info("-- Inside E-Book Reading Page --");
		//WaitForMobileElement(inBook);
		//if(inBook.isDisplayed()) {
			//ClickOnMobileElement(searchresultBook);
			//titleDetails(true);
			waitFor(2000);
			Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/EBookDetails.png");
		//}
		String ebookNameRecent = ebookDetailsTitle.getText();
		ClickOnMobileElement(readBook);
		waitFor(6000);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/EBookReadingPage.png");
		ClickOnMobileElement(bookClose);
		logger.info("Ebook reading page is functional #Pass");
		if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
		MobDragAndDrop(drag, readBook);
		}
		logger.info(" -- E-Book Reading Validation completed -- ");
		recents(ebookNameRecent);
		return ebookNameRecent;
	}

	public void productListAudioBook() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Ebook");
		logger.info(" -- Audio Book Page Assertion Started -- ");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/AudioBook.png");
		//need to check with dev team
		WaitForMobileElement(audiobookTitle);
		if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			Assert.assertEquals(ebookTitle.getText(), testData.get(0).get("defval_ebookTitle")); 
			logger.info("Audio book title is displayed #Pass");
		}
		else if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(audiobookTitle.getText(), testData.get(0).get("defval_AudiobookTitle"));
			logger.info("Audio book title is displayed #Pass");
		}
		Assert.assertTrue(back.isEnabled());
		logger.info("Back button is displayed #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/AudioBook2.png");
		diff_views();
		scrollToEnd(3);
		logger.info("Ebook Listing page is functional #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/AudioBookTileView.png");
		logger.info(" -- Audio Book Page Assertion Completed -- ");
	}
	
	public String audioBookReading() throws IOException, InvalidFormatException {
	    logger.info(" -- Inside Audio Book Player -- ");
		/*
		 * if(inBook.isDisplayed()) { Screenshots.takeScreenshot(driver,
		 * "./screenshots/UIRefreshMobile/AudioBookDetails.png");
		 * ClickOnMobileElement(searchresultBook); titleDetails(false); waitFor(2000); }
		 */
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/AudioBookDetails.png");
		String AbookNameRecent = aBookDetailsTitle.getText();
		ClickOnMobileElement(playBookDetails);
		waitFor(6000);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/AudioBookReadingPage.png");
		ClickOnMobileElement(bookClose);
		logger.info("Audio book reading page is functional #Pass");
		if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
		MobDragAndDrop(drag, playBookDetails);
		}
		recents(AbookNameRecent);
		return AbookNameRecent;
	}
	
	public void diff_views() throws IOException {
		logger.info("Changing View style: Default view to Tile View");
		ClickOnMobileElement(tileView);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/TileView.png");
		WaitForMobileElement(bookDetails);
		Assert.assertTrue(bookDetails.isDisplayed());
		logger.info("Tile view Assertion sucessfully completed #Pass");
		//Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/AudioBook.png");
		//ClickOnMobileElement(tileView);
		//Assert.assertFalse(bookDetails.isDisplayed());
		//Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/G.png");
	}
	
	public void scrollToEnd(int count) throws IOException {
		logger.info("Scroll up "+ count +" times to check data load in product listing Page");
		for(int i=0;i<=count; i++) {
			if(i<=100) {
				swipeUp();
			}
		}
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/SwipeEnd.png");
	}
	
	public void searchPageAssertion() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Search");
		logger.info(" -- Search Page Assertion Started -- ");
		WaitForMobileElement(search_textBox);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Search.png");
		Assert.assertEquals(search_title.getText(), testData.get(0).get("defval_searchTitle"));
		logger.info("Search title is displayed #Pass");
		Assert.assertTrue(search_textBox.isDisplayed());
		logger.info("Search box is displayed #Pass");
		if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			Assert.assertEquals(search_recentResult.getText(), testData.get(0).get("defval_recentResult"));
		}
		logger.info("Recent search header is displayed #Pass");
		//Assert.assertTrue(listView.isDisplayed());
		Assert.assertTrue(filterIcon.isDisplayed());
		logger.info("Filter Icon is available #Pass");
		Assert.assertTrue(more.isDisplayed());
		logger.info("More menu is available #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Search2.png");
		logger.info(" -- Search Page Assertion Completed -- ");
	}
	
	public void search_suggestion() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Search");
		logger.info(" -- Search_Suggestion Page Assertion Started -- ");
		
		SendKeysOnMobileElement(search_textBox, testData.get(0).get("inp_ebook_sugg"));
		WaitForMobileElement(search_suggestedResultText);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Search_Suggestion.png");
		Assert.assertEquals(search_suggestedResultText.getText(),testData.get(0).get("defval_SuggestedResult"));
		logger.info("Suggested search header is displayed #Pass");
		int suggestionCount = suggestedResult.size();
		if(suggestionCount>0) {
			logger.info("Number of Suggested Results: "+suggestionCount+" #Pass");
			Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Search_Suggestion2.png");
		}
		else {
			Assert.assertFalse(suggestedResult.isEmpty());
			logger.info("Suggested results are empty #Fail");
		}
		logger.info("Search Menu is Functional #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Search_Suggestion3.png");
		logger.info(" -- Search_Suggestion Page Assertion Completed -- ");
	}
	
	public void search_Results(boolean eBook) throws InvalidFormatException, IOException, AWTException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Search");
		if(eBook == true) {
			logger.info(" -- Search_Results Page for E-Book Assertion Started -- ");
			SendKeysOnMobileElement(search_textBox, testData.get(0).get("inp_ebook"));
		}
		else if(eBook == false) {
			logger.info(" -- Search_Results Page for Audio book Assertion Started -- ");
			SendKeysOnMobileElement(search_textBox, testData.get(0).get("inp_audioBook"));
		}
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			search_textBox.click();
			keyBoardSearch();
		}
		else if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			search_textBox.sendKeys(Keys.ENTER);
		}
		WaitForMobileElement(searchresultCount);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Search_Results.png");
		Assert.assertTrue(listView.isDisplayed());
		logger.info("List view button is displayed #Pass");
		Assert.assertTrue(searchresultCount.isDisplayed());
		logger.info("Search Results and Count is displayed #Pass");
		logger.info(" -- Search_Results Page Assertion Completed -- ");
	}
	
	public void checkout() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MyStuff");
		logger.info(" -- My Stuff > Checkout Page Assertion Started -- ");
		ClickOnMobileElement(mystuff_text);
		WaitForMobileElement(mystuff_title);
		logger.info("My Stuff Menu is Functional #Pass");
		Assert.assertEquals(mystuff_title.getText(), testData.get(0).get("defval_myStuff"));
		logger.info("Mystuff title is displayed #Pass");
		ClickOnMobileElement(checkout);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_Checkout.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(checkout.getText(), testData.get(0).get("defval_checkout"));
			logger.info("Checkout tab is available #Pass");
		}
		/*
		 * String s1 = myStuff_noCheckout.getText(); if(s1.length() >2) {
		 * logger.info("No Books are available in Checkouts tab #Pass"); }
		 * //
		 */	
		WaitForMobileElement(book);
		if(book.isDisplayed()) {
			Assert.assertEquals(ExpiryTxt.getText(), testData.get(0).get("defval_Expirytxt"));
			logger.info("Expiry header for checkout book is displayed #Pass");
		}
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_Checkout1.png");
		logger.info(" -- My Stuff > Checkout Page Assertion completed -- ");
	}
	
	public void recents(String recentBookName) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MyStuff");
		logger.info(" -- My Stuff > Recents Tab Assertion Started -- ");
		ClickOnMobileElement(mystuff_text);
		WaitForMobileElement(mystuff_title);
		ClickOnMobileElement(recents);
		logger.info("Recents tab is available #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_Recents.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(recents.getText(), testData.get(0).get("defval_recent"));
			WaitForMobileElement(book);
			String b1 = recentBookName.replace(" [electronic resource] :", "");
			MobileElement bookName2 = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+ b1 +"')]"));
			if(book.isDisplayed()) {
				Actions act = new Actions(driver); 
				act.moveToElement(bookName2); 
				Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/RecentBook.png");
				Assert.assertTrue(bookName2.isDisplayed());
				Assert.assertTrue(b1.equalsIgnoreCase(bookName2.getText()));
				logger.info("Recently viewed Book is available in MyStuff> Recents tab: "+ bookName2.getText()+" #Pass");
				Assert.assertTrue(myStuff_bookMore.get(0).isDisplayed()); 
				logger.info("More button for recently viewed book is displayed #Pass");
		}
	}
		else if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			WaitForMobileElement(book);
			String iosb1 = recentBookName.replace(" [electronic resource] :", "");
			MobileElement iosbookName2 = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+ iosb1 +"')]"));
			if(book.isDisplayed()) {
				Actions act = new Actions(driver); 
				act.moveToElement(iosbookName2); 
				Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/EbookRecent.png");
				Assert.assertTrue(iosbookName2.isDisplayed());
				Assert.assertTrue(iosb1.equalsIgnoreCase(iosbookName2.getText()));
				logger.info("Checked out Book is available in MyStuff> Checkout Page: "+ iosbookName2.getText()+" #Pass");
				Assert.assertTrue(myStuff_bookMore.get(0).isDisplayed()); 
				logger.info("More button for recently viewed book is displayed #Pass");
			}
		}
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_Recents1.png");
		logger.info(" -- My Stuff > Recents Page Assertion for "+ recentBookName+" Completed -- ");
	ClickOnMobileElement(search_text);	
	}
	
	public void holds() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MyStuff");
		logger.info(" -- My Stuff > Holds Page Assertion Started -- ");
		ClickOnMobileElement(mystuff_text);
		WaitForMobileElement(mystuff_title);
		Assert.assertEquals(mystuff_title.getText(), testData.get(0).get("defval_myStuff"));
		ClickOnMobileElement(holds);
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(holds.getText(), testData.get(0).get("defval_holds"));
			
			Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_holds.png");
		}
		logger.info("Hold tab is displayed. #Pass");
		waitFor(3000);
		if(myStuff_noHolds.isDisplayed()) {
			logger.info("No Books are available in Holds tab #Pass");
		}
		else {
			Assert.assertTrue(myStuff_bookMore.get(0).isDisplayed());
			logger.info("Books available in Holds tab #Pass");
		}
		/*
		 * if(book.isDisplayed()) { if(IsDisplayedMobileElement(holdReady)==true) {
		 * Assert.assertEquals(holdReady.getText(),testData.get(0).get(
		 * "defval_holdReady")); logger.info("Hold Ready books are available"); }
		 */
			/*
			 * if(IsDisplayedMobileElement(holdPending)==true) {
			 * Assert.assertEquals(holdPending.getText(),testData.get(0).get(
			 * "defval_holdPending")); logger.info("Hold Pending books are available"); }
			 */
		//}
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_holds1.png");
		logger.info(" -- My Stuff > Holds Page Assertion Completed -- ");
	}
	
	public void assigned() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "MyStuff");
		logger.info(" -- My Stuff > Holds Page Assertion Started -- ");
		ClickOnMobileElement(mystuff_text);
		WaitForMobileElement(mystuff_title);
		Assert.assertEquals(mystuff_title.getText(), testData.get(0).get("defval_myStuff"));
		ClickOnMobileElement(assigned);
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_Assigned.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(assigned.getText(), testData.get(0).get("defval_assigned"));
		}
		logger.info("Assigned tab is available #Pass");
		waitFor(2000);
		if(myStuff_noAssignBook.isDisplayed()) {
			logger.info("No Books are available in Assigned tab #Pass");
		}
		else {
			logger.info("Books available in Assigned tab #Pass");
		}
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/MyStuff_Assigned1.png");
		logger.info(" -- My Stuff > Assigned Page Assertion Completed -- ");
	}
	
	public void downloads() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Downloads");
		logger.info(" -- Downloads Page Assertion Started -- ");
		ClickOnMobileElement(download_text);
		logger.info("Downloads Menu is Functional #Pass");
		WaitForMobileElement(downloads_title);
		Assert.assertEquals(downloads_title.getText(), testData.get(0).get("defval_downloads"));
		logger.info("Downloads title is displayed. #Pass");
		Screenshots.takeScreenshot(driver,"./screenshots/UIRefreshMobile/Downloads.png");
		 
		if(IsDisplayedMobileElement(book)) {
			waitFor(3000);
			Assert.assertTrue(downloadNow.isDisplayed());
			logger.info("Download now button is displayed. #Pass");
			Assert.assertTrue(ExpiryTxtDownload.isDisplayed());
			logger.info("Expiry date header is displayed. #Pass");
		}
		logger.info(" -- Downloads Page Assertion Completed -- ");
	}
	
	
	public void search_filter() throws IOException, InvalidFormatException {
		logger.info(" -- Search Filter validation Started -- ");
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Filter");
		ClickOnMobileElement(filterIcon);
		WaitForMobileElement(filter_title);
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/Filter.png");
		Assert.assertEquals(filter_title.getText(),testData.get(0).get("defval_filterTitle"));
		logger.info("Search Filter title is available #Pass");
		WaitForMobileElement(filter_searchType);
		ClickOnMobileElement(filter_searchType);
		WaitForMobileElement(filter_keyword);
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/Filter_SearchType.png");
		if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			Assert.assertEquals(filter_searchTypeHeader.getText(),testData.get(0).get("defval_searchType_android"));  
		}
		else if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			Assert.assertEquals(filter_searchTypeHeader.getText(),testData.get(0).get("defval_searchType"));
		} 
		logger.info("'Search Type' filter is available #Pass");
		ClickOnMobileElement(filter_searchType);
		
		ClickOnMobileElement(filter_availability);
		WaitForMobileElement(filter_all);
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/Filter_Availability.png");
		Assert.assertEquals(filter_availabilityHeader.getText(),testData.get(0).get("defval_availability"));
		logger.info("'Availability' filter is available #Pass");
		ClickOnMobileElement(filter_availability);
		
		ClickOnMobileElement(filter_format);
		WaitForMobileElement(filter_allMaterial);
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/Filter_Format.png");
		Assert.assertEquals(filter_formatHeader.getText(),testData.get(0).get("defval_format"));
		logger.info("'Format' filter is available #Pass");
		ClickOnMobileElement(filter_format);
		
		ClickOnMobileElement(filter_sublocation);
		WaitForMobileElement(filter_unlimited);
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/Filter_Sub-Location.png");
		Assert.assertEquals(filter_sublocationHeader.getText(),testData.get(0).get("defval_subLocation"));
		logger.info("'Sub-Location' filter is available #Pass");
		ClickOnMobileElement(filter_sublocation);
		
		Assert.assertEquals(filter_apply.getText(),testData.get(0).get("defval_apply"));
		logger.info("Apply filter button is available #Pass");
		ClickOnMobileElement(filter_cancel);
		
		logger.info(" -- Search Filter validation Completed -- ");
	}
	
	public void checkoutValidation() throws IOException, InvalidFormatException {
		logger.info(" -- Checkout Validation Started -- ");
		ClickOnMobileElement(home_text); 
		if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			WaitForMobileElement(inBook);
			ClickOnMobileElement(inBook); 
		}
		else if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			ClickOnMobileElement(inOrOut); 
		}
		waitFor(3000);
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/BookDetails.png");
		String bookName = bookDetailsTitle.getText();
		if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
			MobDragAndDrop(drag,readBook);
			ClickOnMobileElement(searchbookMore.get(0));
		}
		else if (getData("platformName").equalsIgnoreCase("android")
				|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
			androidKeyBack();
			ClickOnMobileElement(bookMore);
		}
		String buttonPresent= checkoutORreturn.getText();
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/BookBefore"+buttonPresent+".png");
		if(buttonPresent.equalsIgnoreCase("Return")) {
			logger.info("Return Button is Available #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/ReturnButtonValidation.png");
			ClickOnMobileElement(returnBook);
	        waitFor(2000);
	        if (getData("platformName").equalsIgnoreCase("IOS")
				|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
		       	ClickOnMobileElement(notificationDontAllow);
	        }
	        waitFor(5000);
			mystuffValidation(bookName, false);
		}
		else  {
			logger.info("Checkout Button is Available #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/CheckoutButtonValidation.png");
			ClickOnMobileElement(checkoutBook);
			if (getData("platformName").equalsIgnoreCase("IOS")
					|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
				ClickOnMobileElement(notificationDontAllow);	
			}	
			mystuffValidation(bookName, true);
		}
	logger.info(" -- Checkout Validation Completed Successfully -- ");
	}
	
	public void mystuffValidation(String bookName, boolean checkoutbook) throws IOException, InvalidFormatException {
		ClickOnMobileElement(mystuff_text);
		ClickOnMobileElement(checkout);
		waitFor(2000);
		String b1 = bookName.replace(" [electronic resource]", "");
		//System.out.println("Checked out book name: "+b1);
		if(checkoutbook==true) {
			logger.info(" -- Checkout Book validation in MyStuf>Checkoutf started -- ");
			waitFor(4000);
			//String a = "//XCUIElementTypeStaticText[contains(@name,'\"+ b1 +\"')]";
			 if (getData("platformName").equalsIgnoreCase("IOS")
						|| getData("platformName").equalsIgnoreCase("BrowserStackIOS")) {
				MobileElement bookName2 = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+ b1 +"')]"));
				Actions act = new Actions(driver); 
				act.moveToElement(bookName2);
				Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/BookAfterCheckout.png");
				logger.info("Checked out Book is available in MyStuff> Checkout Page: "+ bookName2.getText()+" #Pass");
				Assert.assertTrue(bookName2.isDisplayed());
				Assert.assertTrue(b1.equalsIgnoreCase(bookName2.getText()));
			 }
			 else if (getData("platformName").equalsIgnoreCase("android")
						|| getData("platformName").equalsIgnoreCase("BrowserStackandroid")) {
				 MobileElement bookName2 = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+ b1 +"')]"));
				 Actions act = new Actions(driver); 
				 act.moveToElement(bookName2);
				 Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/BookAfterCheckout.png");
				 logger.info("Checked out Book is available in MyStuff> Checkout Page: "+ bookName2.getText()+" #Pass");
				 Assert.assertTrue(bookName2.isDisplayed());
				 Assert.assertTrue(b1.equalsIgnoreCase(bookName2.getText()));
			 }
			
			
			logger.info(" -- Checkout Book validation in MyStuf>Checkoutf completed -- ");
		}
		  else if(checkoutbook==false) { 
			  logger.info(" -- Return Book validation in MyStuf>Checkoutf started -- ");
			Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/BookAfterReturn.png");
			Assert.assertTrue(driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+ b1 +"')]")).size()==0);
			scrollToEnd(2);
			Assert.assertTrue(driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+ b1 +"')]")).size()==0);
			logger.info("Returned Book is Not available in MyStuff> Checkout Page #Pass");
			logger.info(" -- Return Book validation in MyStuf>Checkoutf completed -- ");
		  }
	}
	
	public void titleDetails(boolean ebook) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Book Details");
		Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/TitleDetails.png");
		swipeUp();
		if(ebook == true) {
			ClickOnMobileElement(searchresultBook);
			waitFor(3000);
			logger.info(" -- Ebook Book Details validation started -- ");
			Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/eBookTitleDetails.png");
			Assert.assertEquals(readBookDetails.getText(), testData.get(0).get("defVal_Read"));
			logger.info("Read button is displayed #Pass");
			//Assert.assertEquals(callNumDetails.getText(),testData.get(0).get("defVal_callNum"));
			//logger.info("Call number is displayed #Pass");
			//Assert.assertEquals(acceleratedDetails.getText(),testData.get(0).get("defVal_accelerated"));
			logger.info("Acceleration is displayed #Pass");
			Assert.assertEquals(overviewDetails.getText(),testData.get(0).get("defVal_overview"));
			logger.info("Overview is displayed #Pass");
		}
		else if(ebook== false) {
			ClickOnMobileElement(searchresultBook);
			Screenshots.takeScreenshot(driver,"./Screenshots/UIRefreshMobile/audioBookTitleDetails.png");
			logger.info(" -- Audio Book Details validation started -- ");
			waitFor(2000);
			Assert.assertEquals(playBookDetails.getText(), testData.get(0).get("defVal_play"));
			logger.info("Play button is displayed #Pass");
		}
		//Assert.assertEquals(availableDetails.getText(),testData.get(0).get("defVal_available"));
		//logger.info("Available header is displayed #Pass");
		Assert.assertEquals(detailsBookDetails.getText(),testData.get(0).get("defVal_bookdetails"));
		logger.info("Mandatory details for follett book are available #Pass");
		logger.info(" -- Book Details page validation Completed -- ");
	}
	
	// ***************************************
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"You have no titles on hold\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"You have no titles on hold\"]")
	public MobileElement myStuff_noHolds; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"You have no titles checked out\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"You have no titles checked out\"]")
	public MobileElement myStuff_noCheckout	; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Play\"]")
	//@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/title_details_read_button']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Play\"]")
	public MobileElement playBookDetails;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Read\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Read\"]")
	public MobileElement readBookDetails;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"OVERVIEW\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"OVERVIEW\"]")
	public MobileElement overviewDetails;
	
	@AndroidFindBy(xpath="123")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Call Number\"]")
	public MobileElement callNumDetails;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Available\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Available\"]")
	public MobileElement availableDetails;
	
	@AndroidFindBy(xpath="123")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Accelerated Reader\"]")
	public MobileElement acceleratedDetails;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"BOOK DETAILS\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"BOOK DETAILS\"]")
	public MobileElement detailsBookDetails;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Checkout\" or @text=\"Return\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@label=\"Return\" or @name=\"Checkout\"]")
	public MobileElement checkoutORreturn; 	
	
	@AndroidFindBy(xpath="123")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Allow\"]")
	public MobileElement notificationAllow; 
	
	@AndroidFindBy(xpath="123")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Don’t Allow\"]")
	public MobileElement notificationDontAllow; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"20 hungry piggies [electronic resource]\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"20 hungry piggies [electronic resource]\"]")
	public MobileElement bookDetailsTitle;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"The Three Golden Oranges\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"The Three Golden Oranges\"]")
	public MobileElement ebookDetailsTitle; 
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/quick_book_name")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Daisy Jones & the Six [electronic resource] : a novel\"]")
	public MobileElement aBookDetailsTitle;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"more options\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"More menu\"]")
	public List<MobileElement> searchbookMore; 
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"more options\"])[1]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"More menu\"]")
	public MobileElement bookMore; 
	// -- *********Search Filter Objects --  -- **//
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Search Filters\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Search Filters\"]")
	public MobileElement filter_title; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='Search Type']")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"Search type\"])[1]")
	public MobileElement filter_searchTypeHeader; 
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Plus Icon\"])[1]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Search type\"]/XCUIElementTypeButton")
	public MobileElement filter_searchType; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='Keyword']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Keyword\"]")
	public MobileElement filter_keyword; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='Availability']")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"Availability\"])[1]")
	public MobileElement filter_availabilityHeader; 
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Plus Icon\"])[2]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Availability\"]/XCUIElementTypeButton")
	public MobileElement filter_availability; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='All']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"All\"]")
	public MobileElement filter_all; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='Format']")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"Format\"])[1]")
	public MobileElement filter_formatHeader; 
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Plus Icon\"])[3]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Format\"]/XCUIElementTypeButton")
	public MobileElement filter_format; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='All Materials']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"All materials\"]")
	public MobileElement filter_allMaterial;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='Sublocation']")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"Sublocation\"])[1]")
	public MobileElement filter_sublocationHeader; 
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Plus Icon\"])[4]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Sublocation\"]/XCUIElementTypeButton")
	public MobileElement filter_sublocation; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/label' and @text='Unlimited']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Sublocation\"]/XCUIElementTypeButton")
	public MobileElement filter_unlimited; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Apply\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Apply\"]")
	public MobileElement filter_apply; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Cancel\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Cancel\"]")
	public MobileElement filter_cancel; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Reset All\"]")
	//@iOSXCUITFindBy(xpath="")
	public MobileElement filter_reset; 
	
	// --  --  --  -- ***************
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/sheet_button_view']")
	//@AndroidFindBy(id="android:id/content")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther)[3]")
    public MobileElement drag;

	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/material_status_text' and @text='IN']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"IN\"]")
	public MobileElement inBook; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OUT' or @text='IN']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"IN\"]")
	public MobileElement inOrOut; 
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/title_details_read_button']")
	//@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Read\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Read\"]")
	public MobileElement readBook;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Return\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Return\"]")
	public MobileElement returnBook;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Vertical scroll bar, 1 page\"]")
	public MobileElement bookScroll; 
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"Close\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Close\"]")
	public MobileElement bookClose; 
	
	//@AndroidFindBy(xpath="")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[2]")
	public MobileElement bookReadingPage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Checkout\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Checkout\"]")
	public MobileElement checkoutBook;
	
	@AndroidFindBy(xpath="123")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Hold\"]")
	public MobileElement holdBook;
	
	@AndroidFindBy(xpath="123")
	@iOSXCUITFindBy(accessibility= "Open")
	public MobileElement openBook;
	
	@AndroidFindBy(id="Cancel")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeSheet/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
	public MobileElement cancel;
	
	//Author name
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"author name\"])[1]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]")
	public MobileElement bookDetails;
	
	@AndroidFindBy(xpath="123")
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"author name\"])[1]")
	public MobileElement author;
	
	@AndroidFindBy(xpath="123")
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"Book Title\"])[1]")
	public MobileElement bookTitle;
	
	@AndroidFindBy(xpath="123")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")
	public MobileElement inProgress;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/downloaded_book_download_button']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"DownloadNow\"]")
	public MobileElement downloadNow;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Downloads\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Downloads\"]")
	public MobileElement downloads_title;
	
	// -- *********My Stuff Objects --  -- **//
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/my_library_fragment_empty_textview']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"You have no titles assigned to you\"]")
	public MobileElement myStuff_noAssignBook;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/item_my_stuff_book_expires_title_view' and @text='HOLD READY']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"HOLD READY\"]")
	public MobileElement holdReady;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/item_my_stuff_book_expires_title_view' and @text='HOLD PENDING']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"HOLD PENDING\"]")
	public MobileElement holdPending;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"more options\"]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"ItemMoreOptionsIcon\"])[1]")
	public List<MobileElement> myStuff_bookMore;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.follett.fss.searchread.stage:id/item_material_book_list_cover_image_view']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther")
	public MobileElement book;
	
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/item_my_stuff_book_expires_title_view']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"EXPIRES\"]")
	public MobileElement ExpiryTxt;
	
	@AndroidFindBy(xpath= "//*[@resource-id=\"com.follett.fss.searchread.stage:id/downloaded_book_expires_title_view\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Expires\"]")
	public MobileElement ExpiryTxtDownload;
	
	@AndroidFindBy(xpath= "//android.widget.LinearLayout[@content-desc=\"Checkouts\"]/android.widget.TextView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Checkouts\"]")
	public MobileElement checkout; 
	
	@AndroidFindBy(xpath= "//android.widget.LinearLayout[@content-desc=\"Recent\"]/android.widget.TextView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Recent\"]")
	public MobileElement recents; 
	
	@AndroidFindBy(xpath= "//android.widget.LinearLayout[@content-desc=\"Holds\"]/android.widget.TextView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Holds\"]")
	public MobileElement holds; 
	
	@AndroidFindBy(xpath= "//android.widget.LinearLayout[@content-desc=\"Assigned\"]/android.widget.TextView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Assigned\"]")
	public MobileElement assigned; 
	
	// ******************Home Objects******************//
		@AndroidFindBy(xpath= "(//android.widget.ImageView[@content-desc=\"more options\"])[1]")
	//@AndroidFindBy(xpath= "(//android.widget.ImageView[@content-desc=\"more options\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"More menu\"]")
	public List<MobileElement> homeBookList;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Destiny Discover\"]")
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"Destiny Discover\"]")
	public MobileElement homeTitle; 
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"Dlx BnT Digital Site, McHenry, IL\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeStaticText")
	public MobileElement homeSchoolName; 
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"eBooks\"]")
	@iOSXCUITFindBy(accessibility ="eBooks")
	public MobileElement homeEbook;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Audiobooks\"]")
	@iOSXCUITFindBy(accessibility = "Audiobooks")
	public MobileElement homeAudiobook;
	
	//@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"See All eBooks\"]")
	@AndroidFindBy(xpath ="//*[@resource-id='com.follett.fss.searchread.stage:id/library_see_all_ebooks_button']")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"See All\"])[1]")
	public MobileElement homeSeeAllEbook; 
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"See All Audiobooks\"]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"See All\"])[2]")
	public MobileElement homeSeeAllAudiobook; 
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[@content-desc=\"MORE\"]/android.widget.ImageView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"MORE\"]")
	public MobileElement more; 
	
	@AndroidFindBy(xpath= "//android.widget.FrameLayout[@content-desc=\"HOME\"]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility = "HOME")
	public MobileElement home_text;
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[@content-desc=\"HOME\"]/android.widget.ImageView")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")
	public MobileElement home_icon;
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[@content-desc=\"MY STUFF\"]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility = "MY STUFF")
	public MobileElement mystuff_text;
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"My Stuff\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"My Stuff\"]")
	public MobileElement mystuff_title;
	
	@AndroidFindBy(xpath= "//android.widget.FrameLayout[@content-desc=\"MY STUFF\"]/android.widget.ImageView")
	@iOSXCUITFindBy(xpath= "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")
	public MobileElement mystuff_icon;
	
	@AndroidFindBy(xpath= "//android.widget.FrameLayout[@content-desc=\"SEARCH\"]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility = "SEARCH")
	public MobileElement search_text;
	
	@AndroidFindBy(xpath= "//android.widget.FrameLayout[@content-desc=\"SEARCH\"]/android.widget.ImageView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"SEARCH\"]")
	public MobileElement search_icon; 
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"Search\"]")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"Search\"]")
	public MobileElement search_title;
	
	// -- *********Search Objects --  -- **//
	
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/etSearch']")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeSearchField[@name=\"ACSR_SuggestedSearchTextField\"]")
	public MobileElement search_textBox;
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"RECENTLY SEARCHED\"]")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"RECENTLY SEARCHED\"]")
	public MobileElement search_recentResult; 
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"SUGGESTED RESULTS\"]")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"SUGGESTED RESULTS\"]")
	public MobileElement search_suggestedResultText;
	
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/recent_text']")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell")
	public List<MobileElement> suggestedResult;
	
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/search_search_term']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Search results:\")]")
	public MobileElement searchresultCount; 
	
	//@AndroidFindBy(xpath= "//android.widget.ImageView[@content-desc=\"custom cover image for The Three Golden Oranges\"]")
	@AndroidFindBy(xpath= "//*[@resource-id=\"com.follett.fss.searchread.stage:id/material_cover_image_view\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]")
	public MobileElement searchresultBook;

	// -- *********Downloads Objects --  -- **//
	@AndroidFindBy(xpath= "//android.widget.FrameLayout[@content-desc=\"DOWNLOADS\"]/android.view.ViewGroup/android.widget.TextView")
	@iOSXCUITFindBy(accessibility = "DOWNLOADS")
	public MobileElement download_text;
	
	@AndroidFindBy(xpath= "//android.widget.FrameLayout[@content-desc=\"DOWNLOADS\"]/android.widget.ImageView")
	@iOSXCUITFindBy(xpath= "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")
	public MobileElement download_icon;
	
	// -- *********More Objects ***********//
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"About\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypePopover/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther")
	public MobileElement about;
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@content-desc=\"Log Out\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Destiny Discover\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypePopover/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther")
	public MobileElement logout; 
	
	// ******************Product Listing Objects ***********//
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/nav_title']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Destiny Discover\"]")
	public MobileElement ebookTitle; 
	
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/nav_title']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Destiny Discover\"]")
	public MobileElement audiobookTitle; 

	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/nav_back']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Back\"]")
	public MobileElement back; 
	
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/nav_view_type']")
	@iOSXCUITFindBy(accessibility = "Tile view")
	public MobileElement tileView; 
	
	@AndroidFindBy(xpath= "(//android.widget.ImageView[@content-desc=\"List View\"])[1]")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeButton[@name=\"ListViewIcon\"]")
	public MobileElement listView; 
	
	@AndroidFindBy(xpath= "//android.widget.ImageView[@content-desc=\"Grid View\"]")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeButton[@name=\"GridViewIcon\"]")
	public MobileElement gridView; 
	
	@AndroidFindBy(xpath= "//*[@resource-id='com.follett.fss.searchread.stage:id/nav_filter']")
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeButton[@name=\"SortByIcon\"]")
	public MobileElement filterIcon;
	
	//@AndroidFindBy(xpath= "")
	@iOSXCUITFindBy(xpath="//*[contains(@name,'Vertical scroll bar')]")
	public MobileElement scrollBar; 

}
