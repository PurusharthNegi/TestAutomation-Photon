package com.pom_RWD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

public class RWD_UIRefresh_PageHit extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	private static final Logger logger = LogManager.getLogger();
	// static String result = "";

	public RWD_UIRefresh_PageHit() {
		PageFactory.initElements(driver, this);
	}

	public void validateProfile() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");

		WaitForWebElement(menu_profileIcon);
		logger.info("-- Profile Page Validation Start --");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_SingedInUser.png");
		ClickOnWebElement(menu_profileIcon);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_Menu.png");
		ClickOnWebElement(menu_Profile);
		WaitForWebElement(profile_UserName);
		Assert.assertEquals(profile_UserName.getText(), testData.get(0).get("ProfileUser"));
		logger.info("User logged in as : " + profile_UserName.getText() + " #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_PersonalInfo.png");
		if (std_10th.isSelected()) {
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_StdBeforeEdit.png");
			ClickOnWebElement(std_10th);
			logger.info("10th Std is Unselected and saved successfully #Pass");
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_StdAfterEdit.png");
		} else {

			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_StdBeforeEdit.png");
			ClickOnWebElement(std_10th);
			logger.info("10th Std is selected and saved successfully #Pass");
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_StdAfterEdit.png");
		}

		javascriptScroll(std_10th);

		if (sub_FineArts.isSelected()) {
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_SubjectBeforeEdit.png");
			ClickOnWebElement(sub_FineArts);
			logger.info("Fine Arts subject is Unselected and saved successfully #Pass");
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_SubjectAfterEdit.png");
		} else {

			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_SubjectBeforeEdit.png");
			ClickOnWebElement(sub_FineArts);
			logger.info("Fine Arts subject is selected and saved successfully #Pass");
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/Profile_SubjectAfterEdit.png");
		}
		jsClick(btn_Edit_Save);
		waitFor(1000);

		validateProfilePic();
		//ClickOnWebElement(lnk_Home);
		logger.info("-- Profile Page Validation End --");

	}

	public static void validateProfilePic() throws IOException {

		System.out.println("inside profile pic");

		ClickOnWebElement(menu_profileIcon);
		WaitForWebElement(menu_Profile);
		ClickOnWebElement(menu_Profile);
		WaitForWebElement(profile_UserName);
		List<WebElement> ele = driver
				.findElements(By.xpath("//*[@class='profile-mat-list']/mat-list-item[@role='listitem']"));
		int picTabsize = ele.size();
		System.out.println(picTabsize);

		for (int i = 1; i < picTabsize; i++) {
			System.out.println("inside for loop");
			waitFor(2000);
			WaitForWebElement(profile_UserName);
			ClickOnWebElement(profile_Pic.get(i));
			waitFor(1000);
			String str = profile_Pic.get(i).getText();
			String tabName = str.replaceAll("\\s.*", "");
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Profile/ProfilePic_" + tabName + ".png");
			ClickOnWebElement(profile_2ndPic);
			jsClick(btn_Edit_Save);
			waitFor(2000);
			WaitForWebElement(newsAndAnnouncement);
			logger.info(tabName + " Profile is updated successfully and its redirected to home page #Pass");
			ClickOnWebElement(menu_profileIcon);
			ClickOnWebElement(menu_Profile);
			if (i == 4) {
				break;
			}
		}

	}

	public void validateHomePage() throws IOException {

		logger.info("-- Home Page Validation Start --");
		waitFor(3000);
		ClickOnWebElement(lnk_Home);
		WaitForWebElement(newsAndAnnouncement);
		nonFollettValidation();
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/HomePage/HomePage_AfterLoggedIn.png");
		waitFor(5000);
		int RibbonCount = ribbon.size();
		logger.info("Total number of ribbon displayed in Home Page is :" + RibbonCount + " #Pass");
		ArrayList<String> ribbonNames = new ArrayList<String>();
		for (int i = 0; i < RibbonCount; i++) {
			javascriptScroll(ribbon.get(i));
			waitFor(2000);
			String list = ribbon.get(i).getText();
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/HomePage/HomePage_" + list + ".png");
			logger.info(list + " Ribbon is displayed #Pass");
			if (ribbon_SeeAll.get(i).isDisplayed()) {
				if (list.matches("Audiobooks")) {
					javascriptScroll(newsAndAnnouncement);
				}
				if (list.matches("(?i)eBooks")) {
					waitFor(5000);
					if (eBook_FirstIn.isDisplayed()) {
						waitFor(1000);
//						if (eBook_FirstOut.isDisplayed()) {
//							ClickOnWebElement(eBook_FirstOut);
//							Screenshots.takeScreenshot(driver,
//									"Screenshots/UIRefreshWeb/HomePage/HomePage_" + list + "OutBook.png");
//							Assert.assertTrue(btn_HoldOrUnHold.isDisplayed(),
//									"Hold/UnHold Button is not displayed #Pass");
//							logger.info("Hold is displayed in Out status eBook #Pass");
//						}
//						else {
//							jsClick(ribbon_SeeAll.get(i));
//							ClickOnWebElement(eBook_FirstOut);
//							Screenshots.takeScreenshot(driver,
//									"Screenshots/UIRefreshWeb/HomePage/HomePage_" + list + "OutBook.png");
//							Assert.assertTrue(btn_HoldOrUnHold.isDisplayed(),
//									"Hold/UnHold Button is not displayed #Pass");
//							logger.info("Hold is displayed in Out status eBook #Pass");
//							ClickOnWebElement(lnk_Home);
//							javascriptScroll(newsAndAnnouncement);
//							
//						}

						WebElement rightArrow = driver.findElement(By.xpath(
								"(//button[@class='ui-carousel-next ui-button ui-widget ui-state-default ui-corner-all'])[2]"));
						if (rightArrow.isDisplayed()) {
							jsClick(rightArrow);
							waitFor(2000);
							logger.info("Right Arrow Click is working in eBooks #Pass");
							WebElement leftArrow = driver.findElement(By.xpath(
									"(//button[@class='ui-carousel-prev ui-button ui-widget ui-state-default ui-corner-all'])"));
							jsClick(leftArrow);
							logger.info("Left Arrow Click is working in eBooks #Pass");
							waitFor(2000);
						}
						jsClick(eBook_FirstIn);
						waitFor(2000);
						Screenshots.takeScreenshot(driver,
								"Screenshots/UIRefreshWeb/HomePage/eBookMore_" + list + ".png");
						Assert.assertTrue(btn_CheckoutOrReturn.isDisplayed(),
								"Checkout/Uncheckout Button is not displayed");
						Assert.assertTrue(btn_OpenMore.isDisplayed(), "Open Button is not displayed");
						Assert.assertTrue(btn_FavOrUnFav.isDisplayed(), "Favorite/UnFavorite Button is not displayed");
						Screenshots.takeScreenshot(driver,
								"Screenshots/UIRefreshWeb/HomePage/eBookMore_IN_" + list + ".png");
						logger.info(
								"More button clicked and values displayed are Checkout/Open/Favorite in eBooks #Pass");
						waitFor(2000);
						// jsClick(eBook_FirstIn);
					}
				}
//					else {
//						logger.info("Validating the more button in first (OUT) ebooks");
//						Assert.assertTrue(btn_HoldOrUnHold.isDisplayed(), "Checkout/Uncheckout Button is not displayed");
//						Assert.assertTrue(btn_FavOrUnFav.isDisplayed(), "Favorite/UnFavorite Button is not displayed");
//						Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/eBookMore_OUT_" + list + ".png");
//						waitFor(2000);
//						logger.info("Validation successfull for the more button in first (OUT) ebooks");
//
//					}

				waitFor(2000);
				jsClick(ribbon_SeeAll.get(i));

				if (list.matches("(?i)Learning Resources|Collections|Topics")) {
					waitFor(5000);
					Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/HomePage/SeeAllPage_" + list + ".png");
					logger.info(list + " Ribbon See All Clicked and PLP is Displayed #Pass");
				} else {
					waitFor(3000);
					WaitForWebElement(seeAll_FirstBook);
					Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/HomePage/SeeAllPage_" + list + ".png");
					logger.info(list + " See All Clicked and PLP is Displayed #Pass");
					ClickOnWebElement(gridView);
					Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/HomePage/" + list + "_GridView.png");
					logger.info(list + " Grid view clicked #Pass");
					ClickOnWebElement(listView);
					Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/HomePage/" + list + "_ListView.png");
					logger.info(list + " List view clicked #Pass");

				}
				ClickOnWebElement(lnk_Home);
				waitFor(3000);
			}

			ribbonNames.add(list);
			if (i == (RibbonCount - 1)) {
				break;
			}
		}
		logger.info("-- Home Page Validation End --");
	}

	public void nonFollettValidation() throws IOException {
		waitFor(3000);
		System.out.println(list_more.size());
		ClickOnWebElement(list_more.get(1));
		logger.info("More button is clicked for Non-Follett Book Validation #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/NonFollet/NonFollett_more.png");
		Assert.assertTrue(btn_OpenMore.isDisplayed(), "Open Button is not displayed");
		logger.info("Open button is Available for Non-Follett Book #Pass");
		Assert.assertTrue(btn_FavOrUnFav.isDisplayed(), "Favorite/UnFavorite Button is not displayed");
		try {
			Assert.assertFalse(btn_CheckoutOrReturn.isDisplayed(),
					"Checkout/Uncheckout Button is Available for Non-Follett book");
		} catch (Exception e) {
			logger.info("Checkout button is Not available for Non-Follett Book #Pass");
		}

		ClickOnWebElement(btn_Add2Fav);
		logger.info("Favorite button is Available and clicked for Non-Follett Book #Pass");
		waitFor(1000);
		ClickOnWebElement(list_more.get(1));
		ClickOnWebElement(btn_UnFav);
		logger.info("Un-Favorite button is Available and clicked for Non-Follett Book #Pass");
		jsClick(list_more.get(1));

	}

	public void validateSearchScreen() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Search Screen Page Validation Start --");
		WaitForWebElement(lnk_Home);
		ClickOnWebElement(lnk_Home);
		ClickOnWebElement(icon_Search);
		logger.info("Search Icon is displayed and clicked #Pass");
		waitFor(2000);
		// WaitForWebElement(title_Search);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/SearchScreen/SearchPage1.png");
		String str = testData.get(0).get("def_Search");
		str.toUpperCase();
		Assert.assertEquals(def_Search.getText(), str);
		if (IsDisplayedWebElement(lbl_Favourites)) {
			javascriptScroll(lbl_Favourites);
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/SearchScreen/SearchPage2.png");
			logger.info("Favorites ribbon is displayed in Search Page #Pass");

		}
		logger.info("-- Search sScreen Page Validation End --");
	}

	public void validateSearchResultScreen() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("--Search Result Page Validation Start --");
		SendKeysOnWebElement(inp_Search, testData.get(0).get("eBook"));
		logger.info("Search text box is displayed and :" + testData.get(0).get("eBook") + " value is entered #Pass");
		inp_Search.sendKeys(Keys.ENTER);
		waitFor(3000);
		// WaitForWebElement(lbl_SearchResults);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/SearchResult/SearchResultPage.png");
		Assert.assertEquals(lbl_SearchResults.getText(), testData.get(0).get("eBook"));
		logger.info("Search result is displayed with :" + testData.get(0).get("eBook") + " Book #Pass");
		logger.info("-- Search Result Page Validation End --");
	}

	public void validateTitlePreview() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Title Preview Page Validation Start --");
		ClickOnWebElement(item_FirstImgFrmSearchResult);
		logger.info("Clicked on : " + testData.get(0).get("eBook") + " Book #Pass");
		WaitForWebElement(title_TitlePreview);
		logger.info("Title Preview screen is displayed #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/TitlePreview/TitlePreviewPage1.png");
		String SearchedBook = val_SearchedBook.getText();
		if (SearchedBook.contains(SearchedBook)) {
			System.out.println("Selected book title is having the search input value");
		}
		waitFor(2000);
		ClickOnWebElement(dot_more);
		logger.info("Clicked on More button in Title Preview Page #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/TitlePreview/ShareTitle1.png");
		ClickOnWebElement(submenu_Share);
		logger.info("Share button is displayed and Clicked #Pass");
		Assert.assertTrue(btn_QR.isEnabled(), "QR Code button is missing");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/TitlePreview/ShareTitle2.png");
		logger.info("QR Code button is displayed #Pass");
		ClickOnWebElement(btn_Close1);
		logger.info("Close button is displayed and clicked on title preview screen #Pass");
		javascriptScroll(val_SearchedBook);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/TitlePreview/TitlePreviewPage2.png");
		logger.info("-- Title Preview Page Validation End --");
	}

	public void validateViewMoreDetails()
			throws IOException, InvalidFormatException, InterruptedException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- View More Details Page Validation Start --");
		ClickOnWebElement(btn_ViewMoreDetails);
		logger.info("View more details button is displayed and clicked #Pass");
		waitFor(3000);
		WaitForWebElement(lbl_OverView);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/ViewMoreDetails/ViewMoreDetailsPage1.png");

		try {
			ClickOnWebElement(lnk_addReview);
			logger.info("Add review button is displayed and clicked #Pass");
			WaitForWebElement(title_addReview);
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/ViewMoreDetails/AddReviewPage.png");
			Assert.assertTrue(star_Review.isDisplayed(), "Star icon's are missing");
			logger.info("Add review title and rating stars are displayed #Pass");
			ClickOnWebElement(btn_RevClose);
			logger.info("Close button is displayed and closed the Add review screen #Pass");
		} catch (Exception e) {
			WaitForWebElement(reviewed);
			logger.info("This book has been already reviewed #Pass");

		}
		javaScriptScrollToEnd();
		Assert.assertTrue(title_Similar.isDisplayed(), "Similar title is not displayed ");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/ViewMoreDetails/ViewMoreDetailsSimilarTitle.png");
		logger.info("Similar title is displayed #Pass");
		javascriptScroll(lbl_OverView);
		int tabSize = tabList_ViewMore.size();
		logger.info("Total tab size in View more details screen is " + tabSize + " #Pass");
		for (int i = 0; i < tabSize; i++) {
			String tabName = tabList_ViewMore.get(i).getText();
			logger.info(tabName + " is displayed in View More details screen #Pass");
			WebElement element = driver
					.findElement(By.xpath("(//div[@class='mat-tab-label-content'])[" + (i + 1) + "]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			ClickOnWebElement(element);
			Screenshots.takeScreenshot(driver,
					"Screenshots/UIRefreshWeb/ViewMoreDetails/ViewMoreTab_" + tabName + ".png");
			if (i == (tabSize - 1)) {
				break;
			}
		}
		logger.info("All the tabs are clicked and verified in view more details screen #Pass");
		javascriptScroll(val_SearchedBook);
		waitFor(1000);
		if (btn_Open.isDisplayed()) {
			ClickOnWebElement(btn_Open);
			logger.info("Open button is displayed and Clicked to verify the Book reading page #Pass");
			waitFor(10000);
			exwait = new WebDriverWait(driver, 60);
			exwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_Body));
			// logger.info("Switched to Ebook reading iframe-2");
			waitFor(15000);

			Actions act = new Actions(driver);
			act.doubleClick(logo).build().perform();
			act.moveToElement(logo);
			// ClickOnWebElement(nextPage);
			System.out.println("Clicked");
			logger.info("Ebook reading page Navigation-Next button is present. #Pass");
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/EBookReading/EBookReadingNavigationNext.png");
			driver.switchTo().defaultContent();
			ClickOnWebElement(btn_CloseBook);
			logger.info("Closed button is displayed and closed from eBook reading screen #Pass");
			Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/EBookReading/CloseBook.png");
		} else {
			ClickOnWebElement(lnk_Home);
		}

		logger.info("-- View More Details Page Validation End --");

	}

	public void validateMyStuff() throws IOException, InvalidFormatException {

		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Mystuff Page Validation Start --");
		ClickOnWebElement(menu_MyStuff);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/MyStuff/MyStuffMenu.png");
		ClickOnWebElement(subMenu_Checkouts);
		WaitForWebElement(title_MyStuff);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/MyStuff/MyStuffPage1.png");
		Assert.assertEquals(title_MyStuff.getText(), testData.get(0).get("title_MyStuff"));
		logger.info("Mystuff title is displayed #Pass");
		int subListMystuff = myStuff_SubMenuList.size();
		// System.out.println("List size :"+subListMystuff);
		for (int i = 4; i <= subListMystuff; i++) {
			waitFor(2000);
			String myStuffTabs = myStuff_SubMenuList.get(i).getText();
			ClickOnWebElement(myStuff_SubMenuList.get(i));
			logger.info(myStuffTabs + " tab is clicked on MyStuff #Pass");
			waitFor(2000);

			int mystuff_book = mystuff_bookName.size();
			if (mystuff_book > 0) {
				ClickOnWebElement(list_more.get(0));
				logger.info("More is clicked for the book in " + myStuffTabs + " tab #Pass");
				waitFor(2000);
				jsClick(list_more.get(0));
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/MyStuff/" + myStuffTabs + "_More.png");
				if (myStuffTabs.equalsIgnoreCase("History") || myStuffTabs.contains("HOLDS")
						|| myStuffTabs.equalsIgnoreCase("FAVORITES") || myStuffTabs.equalsIgnoreCase("Fines")) {
					for (int j = 0; j <= 2; j++) {
						waitFor(2000);
						ClickOnWebElement(mystuff_bookName.get(j));
						WaitForWebElement(title_TitlePreview);
						logger.info("Title preview is opened for " + mystuff_bookName.get(j).getText() + " in "
								+ myStuffTabs + " tab #Pass");
						Screenshots.takeScreenshot(driver,
								"Screenshots/UIRefreshWeb/MyStuff/" + myStuffTabs + "_TitlePreview.png");
						ClickOnWebElement(titlePrev_Close);
						if (j == (mystuff_book - 1)) {
							break;

						}
					}

					if (myStuffTabs.contains("HOLDS")) {
						int holdStatus = holdPendingorReady.size();
						String holdStatusText = holdPendingorReady.get(0).getText();
						Assert.assertEquals(holdStatus, mystuff_book);
						logger.info("Hold status is available for all the book in Holds tab #Pass");
						waitFor(1000);
						Assert.assertTrue(holdPendingorReady.get(0).isDisplayed(), "Hold Status is not avaiilable");
						ClickOnWebElement(holdPendingorReady.get(0));
						Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/MyStuff/HoldStatus.png");
						if (holdStatusText.equalsIgnoreCase("Hold Ready")) {
							Assert.assertTrue(mystuff_expires.isDisplayed(),
									"Expiry Date is not available for Hold Ready book");
							logger.info("Expiry date is available for the Hold Ready book #Pass");

						}
					}
				}

			} else {

				Assert.assertTrue(mystuff_noBook.isDisplayed(), "No books Message is not displayed");
				logger.info("No books are available in " + myStuffTabs +" #Pass");

			}
			waitFor(2000);

			Screenshots.takeScreenshot(driver,
					"Screenshots/UIRefreshWeb/MyStuff/MyStuffPage_" + myStuffTabs + "Page.png");

			if (i == subListMystuff - 1) {

				break;

			}

		}

		logger.info("-- Mystuff Page Validation End --");
	}

	public void validateAudioBook() throws Exception {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- AudioBook Listening Page Validation Start --");
		ClickOnWebElement(lnk_Home);
		ClickOnWebElement(icon_Search);
		SendKeysOnWebElement(inp_Search, testData.get(0).get("audioBook"));
		inp_Search.sendKeys(Keys.ENTER);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBook1.png");
		ClickOnWebElement(item_FirstImgFrmSearchResult);
		WaitForWebElement(title_TitlePreview);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBook2.png");
		javascriptScroll(val_ABook);
		ClickOnWebElement(btn_ViewMoreDetails);
		waitFor(3000);
		WaitForWebElement(lbl_OverView);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBook3.png");
		javaScriptScrollToEnd();
		javascriptScroll(lbl_OverView);
		javascriptScroll(val_ABook);
		waitFor(1000);
		if (btn_Play.isDisplayed()) {
			ClickOnWebElement(btn_Play);
			logger.info("Audio book play button is displayed and clicked #Pass");
			waitFor(20000);
			try {
				WaitForWebElement(img_Error);
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBookPlay_Error.png");
				logger.info("After clicked on Play button its showing error image #Fail");
				throw new Exception("Play book preview is not loaded its showing error image");
			} catch (Exception e) {
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBook4.png");

				logger.info("Play button is displayed and Clicked to verify the Audio listening page #Pass");
				waitFor(10000);
				exwait = new WebDriverWait(driver, 60);
				exwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_Body));
				waitFor(5000);
				System.out.println("Switched to frame");
				ClickOnWebElement(btn_Yes);
				Actions act = new Actions(driver);
				act.doubleClick(logo).build().perform();
				act.moveToElement(logo);
				waitFor(1000);
				// driver.switchTo().defaultContent();
				// exwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_Body));
				waitFor(2000);
				// if(btn_Yes.isDisplayed()) {
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBookLocationConf.png");
				// ClickOnWebElement(btn_Play);
				// }
				waitFor(2000);
				logger.info("Audio book page is Played  #Pass");
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBookListening.png");
				ClickOnWebElement(btn_Pause);
				driver.switchTo().defaultContent();
				ClickOnWebElement(btn_CloseBook);
				logger.info("Close button is displayed and clicked on in audio book listening page #Pass");
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/AudioBookPlay/AudioBook5.png");
				ClickOnWebElement(lnk_Home);
			}
			logger.info("-- AudioBook Listening Page Validation End --");
		} else {
			ClickOnWebElement(lnk_Home);
		}

	}

	public void validateCollection() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Collection Page Validation Start --");
		waitFor(3000);
		ClickOnWebElement(menu_Collection);
		waitFor(5000);
		WaitForWebElement(title_Collection);
		javascriptScroll(title_Collection);
		Assert.assertEquals(title_Collection.getText(), testData.get(0).get("title_Collections"));
		logger.info("Collection Menu is displayed and after selected Collection title also displayed #Pass");
		logger.info("-- Collection Page Validation End --");

	}

	public void validateCollectionListPage() throws Throwable {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Collection List Page Validation Start --");
		// Assert.assertTrue(tab_Public.isDisplayed(), "Public tab is missing");
		int tabSize = tab_List.size();
		logger.info("Total number of tab in collection page is " + tabSize + " #Pass");

		for (int i = 0; i < tabSize; i++) {
			String tabName = tab_List.get(i).getText();
			logger.info(tabName + " is displayed collection page #Pass");
			if (tabName.contains("(")) {
				String result = tabName.substring(0, tabName.indexOf("("));
				// logger.info(result +" tab is displayed in Collection Page #Pass");
				tab_List.get(i).click();
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Collections/CollectionListPage_" + result
						+ "_BeforeClickingList.png");
				if (result.contains("PUBLIC")) {
					ClickOnWebElement(img_FirstColImg);
					logger.info("Clicked on Public tab and validated the first colletion block #Pass");
					ClickOnWebElement(btn_sort);
					ClickOnWebElement(btn_gridView);
					WaitForWebElement(firstColImg);
					Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Collections/CollectionListPage_"
							+ result + "_AfterClickedList.png");
					// getMainWindow();
					// String mainWindow = driver.getWindowHandle();
					// ClickOnWebElement(firstColImg);
					waitFor(3000);
					driver.navigate().back();
					waitFor(3000);
					javascriptScroll(tab_List.get(i));
					javascriptScroll(title_Collection);
					waitFor(2000);
				}
				/*
				 * Set<String> childWindow = driver.getWindowHandles(); Iterator<String> itr =
				 * childWindow.iterator(); System.out.println(childWindow); while(itr.hasNext())
				 * { String ChildWindow=itr.next();
				 * 
				 * if(!mainWindow.equalsIgnoreCase(ChildWindow)) {
				 * driver.switchTo().window(ChildWindow); Screenshots.takeScreenshot(driver,
				 * "Screenshots/UIRefreshWeb/CollectionListPage_"+tabName+"_NewWindow.png");
				 * waitFor(4000); Assert.assertTrue(btn_Open.isDisplayed(),
				 * "Page not lodaed with Open button"); driver.close();
				 * System.out.println("driver closed"); }
				 * 
				 * } waitFor(2000); driver.switchTo().window(mainWindow);
				 * System.out.println("Switched to main window"); //}
				 * ClickOnWebElement(menu_Collection); waitFor(2000);
				 * javascriptScroll(img_FirstColImg);
				 */
				if (result.contains("PRIVATE")) {
					waitFor(2000);
					ClickOnWebElement(create_Collection);
					Assert.assertEquals(title_CreateNewCol.getText(), testData.get(0).get("title_CreateCol"));
					logger.info("Clicked on Private tab and validated the first colletion block #Pass");
					Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Collections/CollectionListPage_"
							+ result + "_InsideCreateCollection.png");
					ClickOnWebElement(btn_Close);
				}
			}
			if (i == (tabSize - 1)) {
				break;
			}

		}

		ClickOnWebElement(lnk_Home);
		logger.info("-- Collection List Page Validation End --");

	}

	public void ValidateCheckoutAndFavoriteFunctionality() throws InvalidFormatException, IOException {
		logger.info("-- Validate Checkout and Favorite Functionality Start --");
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		ClickOnWebElement(lnk_Home);
		ClickOnWebElement(icon_Search);
		SendKeysOnWebElement(inp_Search, testData.get(0).get("eBook"));
		inp_Search.sendKeys(Keys.ENTER);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav1.png");
		Assert.assertEquals(lbl_SearchResults.getText(), testData.get(0).get("eBook"));
		WebElement element = driver
				.findElement(By.xpath("//*[text()='" + testData.get(0).get("eBook") + "']//following::mat-icon[2]"));
		ClickOnWebElement(element);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav2.png");
		if (lnk_CheckReturn.isDisplayed()) {
			if (lnk_CheckReturn.getText().matches("Checkout")) {
				ClickOnWebElement(btn_Checkout);
				logger.info("Checkout is clicked in more option #Pass");
				waitFor(2000);
			} else {
				waitFor(2000);
				// jsClick(element);
				Screenshots.takeScreenshot(driver,
						"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_Return.png");
				jsClick(btn_Return);
				waitFor(2000);
				jsClick(element);
				waitFor(2000);
				jsClick(btn_Checkout);
				logger.info("Return is displayed, Clicked on return then Checkout is clicked in more option #Pass");
				waitFor(2000);
			}
		}
		jsClick(element);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_Checkout.png");
		if (lnk_FavUnFav.isDisplayed()) {
			if (lnk_FavUnFav.getText().matches("Add to Favorites")) {
				Screenshots.takeScreenshot(driver,
						"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_Add2Fav.png");
				ClickOnWebElement(btn_Add2Fav);
				logger.info("Add to Favorite is clicked in more option #Pass");
				waitFor(2000);
			}

			else {
				waitFor(2000);
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckouAndFav_UnFav.png");
				ClickOnWebElement(btn_UnFav);
				waitFor(2000);
				jsClick(element);
				waitFor(2000);
				jsClick(btn_Add2Fav);
				logger.info(
						"UnFavorite is displayed, Clicked on unFavorite then Add to Favorite is clicked in more option #Pass");
			}

		}

		waitFor(3000);
		ClickOnWebElement(menu_MyStuff);
		ClickOnWebElement(subMenu_Checkouts);
		logger.info("Inside Checkout tab in MyStuff to validate added books in Checkout/Favorite #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_MyStuff_Checkout.png");
		waitFor(1000);
		WebElement inCheckout = driver
				.findElement(By.xpath("//*[contains(text(),'" + testData.get(0).get("eBook") + "')]"));
		if (inCheckout.isDisplayed()) {
			// jsClick(inCheckout);
			jsClick(more);
			Screenshots.takeScreenshot(driver,
					"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_MyStuff_Checkout_Return.png");
			jsClick(btn_Return);
			logger.info("Added book is displayed in the Checkout tab and clicked on return button #Pass");

		} else {
			javascriptScroll(inCheckout);
			// jsClick(inCheckout);
			jsClick(more);
			Screenshots.takeScreenshot(driver,
					"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_MyStuff_Checkout_Return.png");
			logger.info("Added book is displayed in the Checkout tab and clicked on return button #Pass");
			jsClick(btn_Return);
		}
		jsClick(tab_Favorite);
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_MyStuff_Favorite.png");
		WebElement inFav = driver.findElement(By.xpath("//*[contains(text(),'" + testData.get(0).get("eBook") + "')]"));
		// h2[contains(text(),'" + testData.get(0).get("eBook") +
		// "')]//following::mat-icon
		if (inFav.isDisplayed()) {
			// ClickOnWebElement(inFav);
			jsClick(more);
			Screenshots.takeScreenshot(driver,
					"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_MyStuff_Favorite_Unfav.png");
			waitFor(1000);
			ClickOnWebElement(btn_UnFav);
			logger.info("Added book is displayed in the Favorite tab and clicked on UnFavorite button #Pass");

		} else {
			javascriptScroll(inFav);
			// jsClick(inFav);
			jsClick(more);
			Screenshots.takeScreenshot(driver,
					"Screenshots/UIRefreshWeb/CheckoutFavFunc/CheckoutAndFav_MyStuff_Favorite_Unfav.png");
			jsClick(btn_UnFav);
			logger.info("Added book is displayed in the Favorite tab and clicked on UnFavorite button #Pass");

		}
		logger.info("-- Validate Checkout and Favorite Functionality - End --");

	}

	public void validateHelp() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Help Page Validation Start --");
		String parentWindow = driver.getWindowHandle();
		waitFor(3000);
		ClickOnWebElement(menu_More);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Help/HelpPageMenu.png");
		ClickOnWebElement(menu_Help);
		logger.info("Clicked on Help Menu link #Pass");
		waitFor(3000);
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			if (!parentWindow.equalsIgnoreCase(parentWindow)) {
				logger.info("Switched to new window to validate the help screen #Pass");
				driver.switchTo().window(childWindow);
				waitFor(3000);
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Help/HelpPage.png");
				Assert.assertEquals(title_Help.getText(), testData.get(0).get("title_Help"));
				driver.close();
				logger.info(
						"Help Page is loaded without any error and closed the window and return back to Home Page #Pass");
			}
		}
		WaitForWebElement(lnk_Home);
		ClickOnWebElement(lnk_Home);
		logger.info("-- Help Page Validation End --");

	}

	public void validateBackOffice() throws InvalidFormatException, IOException {
		logger.info("-- Back Office Page Validation Start --");
		waitFor(3000);
		ClickOnWebElement(menu_More);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/BackOffice/BackOfficeMenu.png");
		ClickOnWebElement(menu_BackOfz);
		WaitForWebElement(lbl_NarrowSearch);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/BackOffice/BackOfficePage.png");
		ClickOnWebElement(bo_DDMenu);
		logger.info("Back office link is displayed and Clicked , Back office page is loaded without any error #Pass");
		WaitForWebElement(lnk_Home);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/BackOffice/BackOfficePagetoHome.png");
		logger.info("-- Back Office Page Validation End --");

	}

	public void validateLogout() throws InvalidFormatException, IOException {
		logger.info("-- Logout Page Validation Start --");
		waitFor(3000);
		ClickOnWebElement(menu_profileIcon);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Logout/LogoutMenu.png");
		ClickOnWebElement(menu_Logout);
		logger.info("Logout button is displayed and clicked #Pass");
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Logout/LogoutPage.png");
		WaitForWebElement(lnk_Home);
		logger.info("-- Logout Page Validation End --");
	}

	public void validateAdminFSS() throws InvalidFormatException, IOException {
		// WaitForWebElement(lnk_Home);
		waitFor(3000);
		logger.info("-- Admin FSS Page Validation Start --");
		ClickOnWebElement(menu_More);
		String mainWindow = driver.getWindowHandle();
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Admin/AdminMenu.png");
		ClickOnWebElement(menu_Admin);
		logger.info("Admin menu is displayed and clicked #Pass");
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			if (!mainWindow.equalsIgnoreCase(mainWindow)) {
				driver.switchTo().window(childWindow);
				waitFor(3000);
				WaitForWebElement(title_Reports);
				Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Admin/AdminPage.png");
				driver.close();
				logger.info(
						"Switched to new Window to validate the Admin FSS page and closed the window and return back to Home Page #Pass");
			}
		}
		waitFor(3000);
		logger.info("-- Admin FSS Page Validation Start --");

	}

	public void validateAdminDD() throws InvalidFormatException, IOException {
		logger.info("-- Admin DD Page Validation Start --");
		waitFor(3000);
		ClickOnWebElement(menu_Profile1);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Admin/AdminMenu.png");
		ClickOnWebElement(menu_Logout);
		Screenshots.takeScreenshot(driver, "Screenshots/UIRefreshWeb/Admin/LogoutPage.png");
		logger.info("Clicked on the Logout from Admin user #Pass");
		WaitForWebElement(lnk_Home);
		logger.info("-- Admin DD Page Validation End --");
	}

	public void validateSortAndFilterValidation() throws IOException, InvalidFormatException {
		logger.info("-- Sort and Filter Validation Start --");
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		ClickOnWebElement(lnk_Home);
		int RibbonCount = ribbon.size();
		for (int i = 0; i < RibbonCount; i++) {
			javascriptScroll(ribbon.get(i));
			waitFor(2000);
			String list = ribbon.get(i).getText();
			if (ribbon_SeeAll.get(i).isDisplayed()) {
				if (list.matches("Audiobooks")) {
					javascriptScroll(newsAndAnnouncement);
					jsClick(ribbon_SeeAll.get(i));
					WaitForWebElement(btn_SortandFilter);
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterButton_" + list + ".png");
					ClickOnWebElement(btn_SortandFilter);
					logger.info("Sort and Filter button is displayed and clicked in " + list + " #Pass");
					Assert.assertTrue(title_SortandFilter.isDisplayed(), "Sort and Filter title is not displayed");
					ClickOnWebElement(rb_Author);
					logger.info("Author radio button is clicked in " + list + " #Pass");
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterPage_ " + list + ".png");
					javascriptScroll(fr_Format);
					waitFor(1000);
					ClickOnWebElement(fr_Format);
					waitFor(1000);
					jsClick(fr_FAudio);
					logger.info("Format filter is clicked and Follet Audio is selected in " + list + " #Pass");
					ClickOnWebElement(fr_Author);
					waitFor(1000);
					ClickOnWebElement(fr_Auth_TUV);
					waitFor(1000);
					String Author = fr_Auth_TUV_DD.getText();
					jsClick(fr_Auth_TUV_DD);
					waitFor(1000);
					logger.info(
							"Author filter is clicked and TUV is selected and Twin sister production is selected in "
									+ list + " #Pass");
					javascriptScroll(btn_ViewResult);
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterViewResultButton_ " + list + ".png");
					ClickOnWebElement(btn_ViewResult);
					waitFor(2000);
					logger.info("View search button is clicked and result is displayed in " + list + " #Pass");
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterAfterSort_ " + list + ".png");
					if (item_FirstImgFrmSearchResult.isDisplayed()) {
						WaitForWebElement(item_FirstImgFrmSearchResult);
						logger.info("Audio book is displayed after sort and filter #Pass");
						Assert.assertEquals(author_Validation.getText(), testData.get(0).get("a_Author"));
						logger.info("Audio book is displayed with applied Author name #Pass");
					} else {
						logger.info("Audio book is not displayed after sort and filter #Pass");
					}
					ClickOnWebElement(lnk_Home);
				}
				if (list.matches("(?i)eBooks")) {
					waitFor(5000);
					jsClick(ribbon_SeeAll.get(i));
					WaitForWebElement(btn_SortandFilter);
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterButton_" + list + ".png");
					ClickOnWebElement(btn_SortandFilter);
					logger.info("Sort and Filter button is displayed and clicked in " + list + " #Pass");
					Assert.assertTrue(title_SortandFilter.isDisplayed(), "Sort and Filter title is not displayed");
					ClickOnWebElement(rb_Author);
					logger.info("Author radio button is clicked in " + list + " #Pass");
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterPage_ " + list + ".png");
					javascriptScroll(fr_Format);
					ClickOnWebElement(fr_Format);
					waitFor(1000);
					jsClick(fr_FEbook);
					logger.info("Format filter is clicked and Follet eBook is selected in " + list + " #Pass");
					ClickOnWebElement(fr_Author);
					waitFor(1000);
					ClickOnWebElement(fr_Auth_ABC);
					waitFor(1000);
					String Author = fr_Auth_ABC_DD.getText();
					jsClick(fr_Auth_ABC_DD);
					waitFor(1000);
					logger.info("Author filter is clicked and ABC is selected and ADA, Alma Flor is selected in " + list
							+ " #Pass");
					javascriptScroll(btn_ViewResult);
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterViewResultButtont_ " + list + ".png");
					ClickOnWebElement(btn_ViewResult);
					WaitForWebElement(item_FirstImgFrmSearchResult);
					logger.info("View search button is clicked and result is displayed in " + list + " #Pass");
					Screenshots.takeScreenshot(driver,
							"Screenshots/UIRefreshWeb/SortAndFilter/SortAndFilterAfterSort_ " + list + ".png");
					if (item_FirstImgFrmSearchResult.isDisplayed()) {
						WaitForWebElement(item_FirstImgFrmSearchResult);
						logger.info("eBook book is displayed after sort and filter #Pass");
						Assert.assertEquals(author_Validation.getText(), testData.get(0).get("e_Author"));
						logger.info("eBook book is displayed with applied Author name #Pass");
					} else {
						logger.info("eBook book is not displayed after sort and filter #Pass");
					}
					ClickOnWebElement(lnk_Home);

				}
				if (i == 1) {
					break;
				}

			}

		}
		logger.info("-- Sort and Filter Validation End --");

	}

	public void createCollection(String CollectionType) throws InvalidFormatException, IOException {

		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Create " + CollectionType + " Collection Validation Start --");
		// if(CollectionType.equalsIgnoreCase("Private Collection")) {
		waitFor(2000);
		ClickOnWebElement(menu_Collection);
		waitFor(2000);
		ClickOnWebElement(tab_Private);
		// }
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_ " + CollectionType + "Collection.png");
		logger.info("Private tab is Clicked in Collection #Pass");
		WaitForWebElement(create_Collection);
		ClickOnWebElement(create_Collection);
		WaitForWebElement(tab_Title);
		logger.info("Create collection is Clicked and its redirected to create page #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " page.png");
		SendKeysOnWebElement(inp_Title, testData.get(0).get("Col_Name"));
		SendKeysOnWebElement(inp_TitleDesc, testData.get(0).get("Col_Des"));
		WebElement Ctype = driver
				.findElement(By.xpath("//div[@class='bold-title' and contains(text(),'" + CollectionType + "')]"));
		ClickOnWebElement(Ctype);
		logger.info("Collection title name, description and type has entered in title tab #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " TitleDataEntry page.png");
		ClickOnWebElement(tab_Grade);
		WebElement grade = driver
				.findElement(By.xpath("//button[@aria-label='" + testData.get(0).get("grade") + " grade']"));
		WebElement subject = driver
				.findElement(By.xpath("//button[@aria-label='" + testData.get(0).get("subject") + " subject']"));
		ClickOnWebElement(grade);
		ClickOnWebElement(subject);
		logger.info("Grade and subject has entered in Grade/Subject tab #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " GradeDataEntry page.png");
		ClickOnWebElement(tab_Image);
		// waitFor(2000);
		// jsClick(btn_rightArrow);
		javaScriptScrollToEnd();
		logger.info("Image and Color has selected in Image/Color tab #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " ImgColorDataEntry page.png");
		// ClickOnWebElement(btn_color_orange);
		waitFor(2000);
		jsClick(btn_createCollection);
		logger.info("Collection created successfully #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " Success page.png");

		waitFor(3000);
		ClickOnWebElement(btn_AddItem);
		waitFor(1000);
		logger.info("Add item buttton clicked #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " AddItem_URLPage.png");
		SendKeysOnWebElement(inp_enterURL, testData.get(0).get("item_Url"));
		ClickOnWebElement(btn_save);
		logger.info("URL Item is added successfully #Pass");

		waitFor(2000);
		ClickOnWebElement(additem);
		waitFor(1000);
		ClickOnWebElement(tab_Note);
		SendKeysOnWebElement(inp_enterTitle, testData.get(0).get("inp_Note"));
		SendKeysOnWebElement(inp_noteDesc, testData.get(0).get("inp_NoteDesc"));
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " AddItem_NotePage.png");
		ClickOnWebElement(btn_save);
		logger.info("Note Item is added successfully #Pass");

		waitFor(2000);
		ClickOnWebElement(additem);
		waitFor(1000);
		ClickOnWebElement(tab_Collection);
		waitFor(1000);
		System.out.println("Number of collection: " + list_Collection.size());
		if (list_Collection.size() > 0) {
			jsClick(list_Collection.get(0));
			if (list_Collection.size() > 1) {
				jsClick(list_Collection.get(1));
			}
			Screenshots.takeScreenshot(driver,
					"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " AddItem_CollectionPage.png");
			jsClick(btn_save);
		} else {
			jsClick(cancel);
		}
		logger.info("Collection Item is added successfully #Pass");

		waitFor(2000);
		ClickOnWebElement(additem);
		waitFor(1000);
		ClickOnWebElement(tab_PlayList);
		SendKeysOnWebElement(inp_enterTitle, testData.get(0).get("inp_Playlist"));
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " AddItem_PlayListPage.png");
		ClickOnWebElement(btn_save);
		logger.info("Clicked on save button in add playlist #Pass");
		waitFor(2000);

		int slideSize = playlist_AddSlide.size();
		logger.info("Total no of item's are available to add playlist is " + slideSize +" #Pass");

		try {
			for (int i = 0; i < slideSize; i++) {
				waitFor(1000);
				playlist_AddSlide.get(i).click();
				waitFor(1000);
				if (i == slideSize) {
					break;
				}
				Screenshots.takeScreenshot(driver,
						"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " AddItem_PlayListPage2.png");

			}

		} catch (Exception e) {
			logger.info("There is no items to add in playlist #Pass");

		}

		logger.info("Playlist Item is added successfully #Pass");
		ClickOnWebElement(sm_AutoCol);
		WaitForWebElement(btn_sort);
		ClickOnWebElement(btn_sort);
		logger.info("Sort Button is Clicked #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " SortIcon.png");
		ClickOnWebElement(btn_scatteredView);
		logger.info("Scattered View is Clicked #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " ScatteredView.png");
		ClickOnWebElement(btn_sort);
		ClickOnWebElement(btn_gridView);

		int itemCounts = col_totalMore.size();
		logger.info("Total number of items in collections are :" + itemCounts + "#Pass");
		if (itemCounts > 1) {

			for (int i = 0; i < itemCounts; i++) {
				waitFor(1000);
				ClickOnWebElement(col_totalMore.get(i));
				logger.info("Collection more icon is clicked successfully #Pass");
				ClickOnWebElement(btn_ChangePos);
				ClickOnWebElement(btn_AddPos);
				Screenshots.takeScreenshot(driver,
						"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " ChangePosition.png");
				ClickOnWebElement(btn_MoveItem);
				waitFor(1000);
				if (i == 0) {
					break;
				}

			}
			logger.info("Collection item position changed successfully #Pass");
		}

		jsClick(col_totalMore.get(itemCounts - 1));
		ClickOnWebElement(btn_Delete);
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " RemoveItem.png");
		ClickOnWebElement(btn_Delete);
		logger.info("Colletion item is removed from the list successfully #Pass");

		logger.info("Grid View is Clicked #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " GridView.png");
		ClickOnWebElement(btn_sort);
		ClickOnWebElement(btn_listView);
		logger.info("List View is Clicked #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " ListView.png");

		ClickOnWebElement(share);
		logger.info("Collection share icon is clicked #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " Share.png");
		ClickOnWebElement(btn_Done);

		ClickOnWebElement(btn_ColMore);
		logger.info("Clicked on Collection more button #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " CollectionMore.png");
		ClickOnWebElement(btn_Edit);
		waitFor(2000);
		inp_EditColTitle.click();
		SendKeysOnWebElement(inp_EditColTitle, testData.get(0).get("inp_EditColTitle"));
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " CollectionEdit.png");
		javascriptScroll(btn_Edit_Save);
		jsClick(btn_Edit_Save);
		logger.info("Collection edited successfully #Pass");
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " CollectionAfterEdit.png");

		ClickOnWebElement(btn_ColMore);
		ClickOnWebElement(btn_Delete);
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/Collections/Create_" + CollectionType + " CollectionDelete.png");
		ClickOnWebElement(btn_Delete);
		logger.info("Collection deleted successfully #Pass");

		ClickOnWebElement(lnk_Home);
		WaitForWebElement(newsAndAnnouncement);

		logger.info("-- Create " + CollectionType + " Collection Validation End --");

	}

	public void validateCollectionFilter(String CollectionType) throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/WebData.xlsx", "UIRefresh");
		logger.info("-- Collection filter for " + CollectionType + " Validation Start --");
		waitFor(2000);
		ClickOnWebElement(menu_Collection);
		WaitForWebElement(title_Collection);
		if (CollectionType.equalsIgnoreCase("Public Collection")) {
			ClickOnWebElement(tab_Public);
			ClickOnWebElement(col_SeeAll);
		}
		if (CollectionType.equalsIgnoreCase("Private Collection")) {
			ClickOnWebElement(tab_Private);
		}
		ClickOnWebElement(col_Filter);
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/CollectionsFilter/" + CollectionType + " CollectionFilter.png");
		logger.info(CollectionType + " Filter is clicked #Pass");
		ClickOnWebElement(colF_Grade);
		javascriptScroll(colF_10th);
		ClickOnWebElement(colF_10th);
		waitFor(2000);
		String str = btn_ColViewResult.getText();
		String number = str.replaceAll("[^0-9]", "");
		int result = Integer.parseInt(number);
		ClickOnWebElement(btn_ColViewResult);
		Screenshots.takeScreenshot(driver,
				"Screenshots/UIRefreshWeb/CollectionsFilter/" + CollectionType + " CollectionAfterFilter.png");
		logger.info(CollectionType + " Filter is Applied and count of the Filter result is : " + result + "  #Pass");
		Assert.assertEquals(col_ResultComp.size(), result, " Filter and output counts are mismatched");
		logger.info(CollectionType + " Filter count and output is same as expected  #Pass ");
		ClickOnWebElement(lnk_Home);
		WaitForWebElement(newsAndAnnouncement);
		logger.info("-- Collection filter for " + CollectionType + " Validation End --");

	}

	/*************** Profile validation locators ***************************/

	@FindBy(xpath = "//*[@aria-label='Open profile options window']")
	public static WebElement menu_profileIcon;

	@FindBy(xpath = "//button[@aria-label='profile']")
	public static WebElement menu_Profile;

	@FindBy(xpath = "(//h1[@class='profileName'])[1]")
	public static WebElement profile_UserName;

	@FindBy(xpath = "//*[@class='profile-mat-list']/mat-list-item[@role='listitem']")
	public static List<WebElement> profile_Pic;

	@FindBy(xpath = "(//img[@alt='profile image'])[2]")
	public static WebElement profile_2ndPic;

	@FindBy(xpath = "//a[text()=' Home ']")
	public WebElement lnk_Home;

	@FindBy(xpath = "//button[@name='Head-UserName']")
	public WebElement menu_Profile1;

	@FindBy(xpath = "//li[@class='ng-star-inserted']/button[contains(text(),'10th')]")
	public WebElement std_10th;

	@FindBy(xpath = "//li[@class='ng-star-inserted']/button[contains(text(),'Fine Arts')]")
	public WebElement sub_FineArts;

	/*************** Home Page validation locators ***************************/
	@FindBy(xpath = "//h3[text()='Audiobooks']")
	public static WebElement ribbon_AudioBooks;

	@FindBy(xpath = "//*[@aria-label='more']")
	public static List<WebElement> list_more;

	@FindBy(xpath = "//h2[@class='customHeader news-title']")
	public static WebElement newsAndAnnouncement;

	@FindBy(xpath = "//div[@class='carousel-header ng-star-inserted']/h3")
	public static List<WebElement> ribbon;

	@FindBy(xpath = "//div[@class='carousel-header ng-star-inserted']/a")
	public static List<WebElement> ribbon_SeeAll;

	@FindBy(xpath = "(//*[@class='plp-card-book ng-star-inserted'])[1]")
	public static WebElement seeAll_FirstBook;

	@FindBy(xpath = "//img[@alt='grid view icon']")
	public static WebElement gridView;

	@FindBy(xpath = "//img[@alt='list view icon']")
	public static WebElement listView;

	@FindBy(xpath = "//*[contains(text(),'Checkout') or contains(text(),'Return')]")
	public static WebElement btn_CheckoutOrReturn;

	@FindBy(xpath = "//*[contains(text(),'Add to Favorites') or contains(text(),'UnFavorite')]")
	public static WebElement btn_FavOrUnFav;

	@FindBy(xpath = "//*[contains(text(),'Hold') or contains(text(),'UnHold')]")
	public static WebElement btn_HoldOrUnHold;

	@FindBy(xpath = "//button[contains(text(),'Open')]")
	public static WebElement btn_OpenMore;

	@FindBy(xpath = "((//div[@class='ribbonWrapper ng-star-inserted'][2]//p-carousel//div[@class='book-poster in'])[1]//parent::mat-card//mat-icon)[2]")
	public static WebElement eBook_FirstIn;

	@FindBy(xpath = "//iframe[@id='follett-content-reader']")
	public static WebElement ebook_frame;

	@FindBy(xpath = "((//div[@class='ribbonWrapper ng-star-inserted'][2]//p-carousel//div[@class='book-poster out'])[1]//parent::mat-card//mat-icon)[2]")
	public static WebElement eBook_FirstOut;

	@FindBy(xpath = "(//span[@class='ui-carousel-next-icon pi pi-chevron-right'])")
	public static List<WebElement> btn_RightArrow;

	@FindBy(xpath = "(//span[@class='ui-carousel-prev-icon pi pi-chevron-left'])")
	public static List<WebElement> btn_LeftArrow;

	/*************** Search Page validation locators ***************************/
	@FindBy(xpath = "//button[@aria-label='Search']")
	public static WebElement icon_Search;

	// @FindBy(xpath = "//label[@aria-label='Search']")
	@FindBy(xpath = "//*[contains(text(),'Search')]")
	public static WebElement title_Search;

	@FindBy(xpath = "//input[@name='searchInput']")
	public static WebElement inp_Search;

	@FindBy(xpath = "//*[@aria-label='Search title, author or topic']")
	public static WebElement def_Search;

	@FindBy(xpath = "//*[@class='favourities']")
	public static WebElement lbl_Favourites;

	@FindBy(xpath = "//div[@class='resultText ng-star-inserted']/span[2]")
	public static WebElement lbl_SearchResults;

	/*************** Title preview validation locators ***************************/
	@FindBy(xpath = "(//img[@id='plist-opentilt-img'])[1]")
	public static WebElement item_FirstImgFrmSearchResult;

	@FindBy(xpath = "//*[contains(text(),'Title Preview')]")
	public static WebElement title_TitlePreview;

	@FindBy(xpath = "//h2[contains(text(),'The Three Golden Oranges')]")
	public static WebElement val_SearchedBook;

	@FindBy(xpath = "//h2[contains(text(),'Dory story')]")
	public static WebElement val_SearchedBook1;

	@FindBy(xpath = "//button[contains(@class,'more preview-more ng-star-inserted')]")
	public static WebElement dot_more;

	@FindBy(xpath = "//button[@id='share-det-dwnd-qrcode']")
	public static WebElement btn_QR;

	// @FindBy(xpath = "//button[@id='plptitle-det-sharbtn']")
	@FindBy(xpath = "//button[@aria-label='share']")
	public static WebElement submenu_Share;

	@FindBy(xpath = "//h2[contains(text(),'Colors And Shapes')]")
	public static WebElement val_ABook;

	/***************
	 * * Title preview view more details validation locators
	 ************/
	@FindBy(xpath = "//button[@name='mystuff-Title-Prevw-ViewDetl']")
	public static WebElement btn_ViewMoreDetails;

	@FindBy(xpath = "//span[text()='OVERVIEW']")
	public static WebElement lbl_OverView;

	@FindBy(xpath = "//*[contains(text(),'Similar Titles')]")
	public static WebElement title_Similar;

	@FindBy(xpath = "//span[@id='mystuff-tlt-detail-add-review']")
	public static WebElement lnk_addReview;

	@FindBy(xpath = "//h2[text()='Add Review']")
	public static WebElement title_addReview;

	@FindBy(xpath = "//div[@class='ratings ng-star-inserted']")
	public static WebElement reviewed;

	@FindBy(xpath = "(//div[@class='reviewDisplay'])[2]")
	public static WebElement star_Review;

	@FindBy(xpath = "//iframe[@src='/metasearch/rest/v2/library/launchTitleDetails/LIBRARY:638a4c1308d75a1000ae8ad858e774697d08d65e/openBookRedirect']")
	public static WebElement ifreame_BookReadingPage;

	/*************** * View more details validation locators ************/
	@FindBy(xpath = "(//a[@id='head-menu-list-mystuff'])[1]")
	public static WebElement menu_MyStuff;

	@FindBy(xpath = "//a[@id='mystuff-checkout']")
	public static WebElement subMenu_Checkouts;
	
	@FindBy(xpath = "//*[@class='btn-no-bg color-link']") 
	 public static List<WebElement> mystuff_bookName;
	
	@FindBy(xpath = "//div[@class='errorMessage ng-star-inserted']") 
    public static WebElement mystuff_noBook;

    @FindBy(xpath = "//*[contains(text(),'Expires')]") 
    public static WebElement mystuff_expires;

	@FindBy(xpath = "//*[@aria-label='close dialog']") 
    public static WebElement titlePrev_Close;
	
	@FindBy(xpath = "//div[text()= 'Hold Pending' or text()='Hold Ready'] ") 
    public static List<WebElement> holdPendingorReady;

	@FindBy(xpath = "//label[text()='My Stuff']")
	public static WebElement title_MyStuff;

	@FindBy(xpath = "(//div[@class='mat-list-item-content']/a)")
	public static List<WebElement> myStuff_SubMenuList;

	@FindBy(xpath = "//span[text()='Open']")
	public static WebElement btn_Open;

	@FindBy(xpath = "(//iframe[@class='iframe-fixed'])[1]")
	public static WebElement ebook_1stPage;

	@FindBy(xpath = "(//span[@class='icon  icon-arrow-left'])[2]")
	public static WebElement ebook_1stPageRight;

	@FindBy(xpath = "(//span[@class='icon  icon-arrow-left'])[1]")
	public static WebElement ebook_1stPageLeft;

	@FindBy(xpath = "//span[text()='Play']")
	public static WebElement btn_Play;

	@FindBy(xpath = "//img[@alt='Error']")
	public static WebElement img_Error;

	@FindBy(xpath = "//div[text()='ONLINE MODE']")
	public static WebElement btn_OnlineMode;

	@FindBy(xpath = "//*[@class='mat-button-wrapper' and text()='Close Book']")
	public static WebElement btn_CloseBook;

	@FindBy(xpath = "//button[@aria-label='Next Page']/span")
	public static WebElement nextPage;

	@FindBy(xpath = "//button[@aria-label='Previous Page']")
	public static WebElement previousPage;

	@FindBy(xpath = "//button[@id='right-page-btn']/span[@class='icon  icon-arrow-left']")
	public static WebElement right_Arrow;

	@FindBy(xpath = "//*[@id='x9781481443074_ebk']")
	public static WebElement book;
	// div[@class='portal-logo']

	@FindBy(xpath = "//div[@class='portal-logo']")
	public static WebElement logo;

	@FindBy(xpath = "//button[contains(text(),'Yes') and @class='btn btn-primary btn-bg-none']")
	public static WebElement btn_Yes;

	@FindBy(xpath = "//button[@id='btnPause']")
	public static WebElement btn_Pause;

	@FindBy(xpath = "//iframe[@id='follett-content-reader']")
	public static WebElement ebook_frame3;

	@FindBy(xpath = "//iframe[@aria-label='Book Content']")
	public static WebElement ebook_frame2;

	@FindBy(xpath = "//iframe[@id='follett-content-reader']")
	public static WebElement frame_Body;

	/*************** Collection Page validation locators ************/
	@FindBy(xpath = "//a[@id='head-menu-list-collct']")
	public static WebElement menu_Collection;

	@FindBy(xpath = "//div[text()='Collections']")
	public static WebElement title_Collection;

	@FindBy(xpath = "(//span[text()='Public'])[1]")
	public static WebElement tab_Public;

	@FindBy(xpath = "(//span[text()='Private'])[1]")
	public static WebElement tab_Private;

	@FindBy(xpath = "//img[@class='mat-card-image']")
	public static WebElement img_FirstColImg;

	@FindBy(xpath = "//span[text()='Create collection']")
	public static WebElement create_Collection;

	@FindBy(xpath = "//span[text()='You are creating a new Collection']")
	public static WebElement title_CreateNewCol;

	@FindBy(xpath = "//img[@class='controls-icon']")
	public static WebElement btn_Close;

	@FindBy(xpath = "//mat-icon[text()='close']")
	public static WebElement btn_Close1;

	@FindBy(xpath = "//span[contains(text(),'close')]")
	public static WebElement btn_RevClose;

	@FindBy(xpath = "//div[contains(@id,'mat-tab-label')]")
	public static List<WebElement> tab_List;

	@FindBy(xpath = "//img[@class='actionElement mat-card-lg-image']")
	public static WebElement firstColImg;

	@FindBy(xpath = "//div[@class='mat-tab-label-content']")
	public static List<WebElement> tabList_ViewMore;

	/***************
	 * Validate Checkout and Fav Page validation locators
	 ************/
	@FindBy(xpath = "//*[contains(text(),'Checkout')]")
	public static WebElement btn_Checkout;

	@FindBy(xpath = "//*[contains(text(),'Add to Favorites')]")
	public static WebElement btn_Add2Fav;

	@FindBy(xpath = "//*[contains(text(),'Return')]")
	public static WebElement btn_Return;

	@FindBy(xpath = "//*[contains(text(),'UnFavorite')]")
	public static WebElement btn_UnFav;

	@FindBy(xpath = "//*[@aria-label='more']")
	public static WebElement more;

	@FindBy(xpath = "//*[@id='tab-favourites-btn']")
	public static WebElement tab_Favorite;

	@FindBy(xpath = "//*[contains(text(),'Add to Favorite') or contains(text(),'UnFavorite') ]")
	public static WebElement lnk_FavUnFav;

	@FindBy(xpath = "//*[contains(text(),'Checkout') or contains(text(),'Return')]")
	public static WebElement lnk_CheckReturn;

	/*************** Help Page validation locators ************/
	@FindBy(xpath = "//a[@id='head-menu-list-more']")
	public static WebElement menu_More;

	@FindBy(xpath = "//a[contains(text(),'Help')]")
	public static WebElement menu_Help;

	@FindBy(xpath = "//h1[text()='Destiny Discover Help Center']")
	public static WebElement title_Help;

	/*************** Back Office validation locators ************/
	@FindBy(xpath = "//a[@id='hdmen-more-back']")
	public static WebElement menu_BackOfz;

	@FindBy(xpath = "//h3[@class='ColRowBold']")
	public static WebElement lbl_NarrowSearch;

	@FindBy(xpath = "//span[text()='Destiny Discover']")
	public static WebElement bo_DDMenu;

	/*************** Logout Page validation locators ************/
	@FindBy(xpath = "//button[@aria-label='logout']")
	public static WebElement menu_Logout;

	@FindBy(xpath = "//button[contains(text(),'Logout')]")
	public static WebElement menu_Logout2;

	/*************** Admin FSS Page validation locators ************/
	@FindBy(xpath = "//a[@id='desk-more-admin']")
	public static WebElement menu_Admin;

	@FindBy(xpath = "//h2[text()='Reports']")
	public static WebElement title_Reports;

	/*************** Sort and Filter Page validation locators ************/
	@FindBy(xpath = "//img[@alt='sort and filter icon']")
	public static WebElement btn_SortandFilter;

	@FindBy(xpath = "//h2[contains(text(),'Sort & Filter')]")
	public static WebElement title_SortandFilter;

	@FindBy(xpath = "//div[text()='Author']")
	public static WebElement rb_Author;

	@FindBy(xpath = "//h4[text()='FILTERS']")
	public static WebElement lbl_Filter;

	@FindBy(xpath = "//span[text()='Author']")
	public static WebElement fr_Author;

	@FindBy(xpath = "//span[text()='Format']")
	public static WebElement fr_Format;

	@FindBy(xpath = "//span[contains(text(),'Follett Audiobook ')]")
	public static WebElement fr_FAudio;

	@FindBy(xpath = "//span[contains(text(),'Follett eBook')]")
	public static WebElement fr_FEbook;

	@FindBy(xpath = "//button[text()=' TUV ']")
	public static WebElement fr_Auth_TUV;

	@FindBy(xpath = "//button[text()=' ABC ']")
	public static WebElement fr_Auth_ABC;

	@FindBy(xpath = "//span[contains(text(),'Twin Sisters Productions ')]")
	public static WebElement fr_Auth_TUV_DD;

	@FindBy(xpath = "//span[contains(text(),'ADA, Alma Flor')]")
	public static WebElement fr_Auth_ABC_DD;

	@FindBy(xpath = "//button[@name='Sort_Filter_View_Btn']")
	public static WebElement btn_ViewResult;

	@FindBy(xpath = "(//p[@class='plp-para-sz ng-star-inserted'])[1]")
	public static WebElement author_Validation;

	/*************** Collection validation locators ************/

	@FindBy(xpath = "//div[text()=' Title/Description ']")
	public WebElement tab_Title;

	@FindBy(xpath = "//div[text()=' Grade/Subject ']")
	public WebElement tab_Grade;

	@FindBy(xpath = "//div[text()=' Image/Color ']")
	public WebElement tab_Image;

	@FindBy(xpath = "//*[@id='collectionTitle']")
	public WebElement inp_Title;

	@FindBy(xpath = "//*[@id='collectionDescription']")
	public WebElement inp_TitleDesc;

	@FindBy(xpath = "//*[text()='Private']")
	public WebElement rb_public;

	@FindBy(xpath = "//*[text()='Public']")
	public WebElement rb_Public;

	@FindBy(xpath = "//button[contains(text(),'Create Collection')]")
	public WebElement btn_createCollection;

	@FindBy(xpath = "//*[@id='pr_id_7']/div/div/button[2]")
	public WebElement btn_rightArrow;

	@FindBy(xpath = "//button[@aria-label='choose orange for the collection background color']")
	public WebElement btn_color_orange;

	@FindBy(xpath = "//a[@class='add-item-card mat-fab mat-button-base mat-accent']")
	public WebElement btn_AddItem;

	@FindBy(xpath = "//*[@id='sortViewButton']")
	public WebElement btn_sort;

	@FindBy(xpath = "//*[@aria-label='Scattered View']")
	public WebElement btn_scatteredView;

	@FindBy(xpath = "//*[@aria-label='Grid View']")
	public WebElement btn_gridView;

	@FindBy(xpath = "//*[@aria-label='List View']")
	public WebElement btn_listView;

	@FindBy(xpath = "//*[@id='mat-tab-label-4-0']/div")
	public WebElement tab_Url;

	@FindBy(xpath = "//div[contains(text(),'Playlist')]")
	public WebElement tab_PlayList;

	@FindBy(xpath = "//*[@id='mat-tab-label-4-2']/div")
	public WebElement tab_Upload;

	@FindBy(xpath = "//div[contains(text(),'Note')]")
	public WebElement tab_Note;

	@FindBy(xpath = "//div[contains(text(),'Collections')]")
	public WebElement tab_Collection;

	@FindBy(xpath = "(//*[@class='content'])")
	public List<WebElement> list_Collection;

	@FindBy(xpath = "//*[@id='resourceUri']")
	public WebElement inp_enterURL;

	@FindBy(xpath = "//*[@aria-label='Save']")
	public WebElement btn_save; // Common for url, playlist, upload, note & collection

	@FindBy(xpath = "//*[@aria-label='Cancel']")
	public WebElement btn_cancel; // Common for url, playlist, upload, note & collection

	@FindBy(xpath = "//*[@id='resourceTitle']")
	public WebElement inp_enterTitle; // Common for playlist title and note title

	@FindBy(xpath = "//*[contains(text(),'Save')]")
	public static WebElement btn_Edit_Save;

	@FindBy(xpath = "//*[@aria-label='Cancel']")
	public WebElement cancel;

	@FindBy(xpath = "//*[@id='resourceDescription']")
	public WebElement inp_noteDesc;

	@FindBy(xpath = "//*[@id='collectionTitle']")
	public WebElement inp_EditColTitle;

	@FindBy(xpath = "//*[@id='collectionDescription']")
	public WebElement inp_EditColDesc;

	@FindBy(xpath = "//*[@id='collTitle']")
	public WebElement cb_collection;

	@FindBy(xpath = "//*[@id='searchSlidesInPoolInput']")
	public WebElement inp_searchTitle;

	@FindBy(xpath = "(//*[text()='Automation Collection'])[1]")
	public WebElement list_AutoCollection;

	@FindBy(xpath = "(//*[text()='QA Collection'])[1]")
	public WebElement list_QACollection;

	@FindBy(xpath = "//span[text()='Add Slide']")
	public List<WebElement> playlist_AddSlide;

	@FindBy(xpath = "//*[@class='noSlidesText mat-card ng-star-inserted']")
	public WebElement playlist_dropSlide;

	@FindBy(xpath = "//li[text()=' Automation Collection']")
	public WebElement sm_AutoCol;

	@FindBy(xpath = "//img[@src='assets/icons-share.png']")
	public WebElement share;

	@FindBy(xpath = "//img[@src='assets/add-item-icon.png']")
	public WebElement additem;

	@FindBy(xpath = "//*[contains(text(),'Done')]")
	public WebElement btn_Done;

	@FindBy(xpath = "//button[contains(@aria-label,'action menu for collection')]")
	public WebElement btn_ColMore;

	@FindBy(xpath = "//span[contains(text(),'Edit')]")
	public WebElement btn_Edit;

	@FindBy(xpath = "//*[contains(text(),'Delete')]")
	public WebElement btn_Delete;

	@FindBy(xpath = "//button[@id='filterButton']")
	public WebElement icon_filter; // Collection filter icon

	@FindBy(xpath = "//*[@class='controls-icons ng-star-inserted']")
	public List<WebElement> col_totalMore;

	@FindBy(xpath = "//span[contains(text(),'Change Position')]")
	public WebElement btn_ChangePos;

	@FindBy(xpath = "//mat-icon[contains(text(),'add')]")
	public WebElement btn_AddPos;

	@FindBy(xpath = "//button[contains(text(),'Move Item')]")
	public WebElement btn_MoveItem;

	/*************** Collection Filter validation locators ************/

	@FindBy(xpath = "//a[@class='showAll mat-button mat-button-base ng-star-inserted']/span")
	public WebElement col_SeeAll;

	@FindBy(xpath = "//img[@src='assets/fss-filter.svg']")
	public WebElement col_Filter;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Grade')]")
	public WebElement colF_Grade;

	@FindBy(xpath = "(//span[contains(text(),'10th')]/following::mat-checkbox)[1]")
	public WebElement colF_10th;

	@FindBy(xpath = "//button[@class='view-btn']")
	public WebElement btn_ColViewResult;

	@FindBy(xpath = "//img[@class='mat-card-image']")
	public List<WebElement> col_ResultComp;

}
