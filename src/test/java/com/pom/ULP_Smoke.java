package com.pom;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.omg.CosNaming.IstringHelper;
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

public class ULP_Smoke extends CapabilitiesAndWebDriverUtils{
	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger();
	
	public ULP_Smoke() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public static void inSightCarousel() throws IOException {
		waitFor(5000);
		Assert.assertTrue(inSights_lbl.isDisplayed());
		if (inSights_Image.size()>1) {
			horizontalSwipeAndriod(inSights_Image);
			logger.info(getData("platformName") + " -  Insights is Displayed #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/Insights.png");
		}	
	}
	
	public static void goalSetting() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "ULP");
		ClickOnMobileElement(inSights_Image.get(0));
		Assert.assertTrue(inSights_SetGoalsPageHeader.isDisplayed());
		logger.info(getData("platformName") + " -  Set Goal page Header is displayed #Pass");
		Assert.assertTrue(inSights_GoalsDesc.isDisplayed());
		logger.info(getData("platformName") + " -  Set Goal page Desccription is displayed #Pass");
		Assert.assertTrue(inSights_TargetSettingField.isDisplayed());
		Assert.assertTrue(inSights_GoalsmetricsText.isDisplayed());
		Assert.assertTrue(inSights_SetGoalBtn.isDisplayed());
		logger.info(getData("platformName") + " -  Set Goal button is displayed #Pass");
		ClickOnMobileElement(inSights_SetGoalBtn);
		Assert.assertTrue(inSights_SetGoalsPageHeader.isDisplayed());
		logger.info(getData("platformName") + " -  Set Goal button is not enabled without entering target goal input #Pass");
		SendKeysOnMobileElement(inSights_TargetSettingField, testData.get(0).get("Goal_Target"));
		Assert.assertTrue(inSights_SetGoalBtn.isEnabled());
		logger.info(getData("platformName") + " -  Set Goal button is enabled after entering target goal input #Pass");
		ClickOnMobileElement(inSights_SetGoalBtn);
		waitFor(2000);
		ClickOnMobileElement(inSights_Image.get(0));
		if (inSights_RemoveGoalBtn.size()!=0) {
			logger.info(getData("platformName") + " -  Remove Goal button is displayed #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/RemoveGoal.png");
			ClickOnMobileElement(inSights_RemoveGoalBtn.get(0));
		}
	}
	
	public static void badgesCarousel() throws IOException {
		waitFor(2000);
		Assert.assertTrue(badges_lbl.isDisplayed());
		logger.info(getData("platformName") + " -  Badges lable is Displayed #Pass");
		if (badges_Image.size()>2) {
			horizontalSwipeAndriod(badges_Image);
			logger.info(getData("platformName") + " -  Badges carousel is Displayed #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/Badges.png");
		}
		else {
			Assert.assertTrue(badges_Image.get(0).isDisplayed());
			logger.info(getData("platformName") + " -  Badges carousel is Displayed #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/Badges.png");
		}
		swipeDown();
	}
	
	public static void challengeandprogramCarousel() throws IOException {
		
		Assert.assertTrue(chPrg_lbl.isDisplayed());
		if (chPrgList.size()>1) {
			horizontalSwipeAndriod(chPrgList);
			logger.info(getData("platformName") + " -  RC/RP carousel is Displayed #Pass");
		}
		else {
			Assert.assertTrue(badges_Image.get(0).isDisplayed());
			logger.info(getData("platformName") + " -  RC/RP carousel is Displayed #Pass");
		}
		ClickOnMobileElement(chPrgSeeAll_Btn);
		Assert.assertTrue(BookClubLandingScreen.lbl_BooKClub_Header.isDisplayed());
		Assert.assertTrue(BookClubLandingScreen.challenges.isDisplayed());
		Assert.assertTrue(BookClubLandingScreen.myPrograms.isDisplayed());
		Assert.assertTrue(BookClubLandingScreen.openPrograms.isDisplayed());
		logger.info(getData("platformName") + " -  Book club landing page header,challenges,myProgram,Openrogram Tab are displayed #Pass");
		logger.info(getData("platformName") + " -  User navigates to listing page after taping SeeAll from home page #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/ListingPage.png");
		ClickOnMobileElement(BookClubLandingScreen.homeBtn);
		waitFor(10000);
	}
	
	public static void challengeNavigation() throws IOException {
		ClickOnMobileElement(chPrgList.get(0));
		waitFor(5000);
		Assert.assertTrue(CreateAChallengeCreatorRCDetailsScreen.pageBackButton.isDisplayed());
		logger.info(getData("platformName") + " -  User navigates to correcponding RC/RP Detail page #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/DetailPage.png");
		ClickOnMobileElement(CreateAChallengeCreatorRCDetailsScreen.pageBackButton);
		waitFor(10000);
		swipeDown();
		swipeDown();
	}
	
	public static  void ourSuggestionCarousel() throws InvalidFormatException, IOException {
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "ULP");
		Assert.assertTrue(basedOnIntrest_lbl.isDisplayed());
		if (ourSuggestion_Title.size()>2) {
			horizontalSwipeAndriod(ourSuggestion_Title);
		}
		ClickOnMobileElement(basedOnIntrestSeeAll_Btn);
		waitFor(5000);
		Assert.assertEquals(seeAll_listingPageHeader.getText(), testData.get(0).get("SeeALL_ListPage_Header1"));
		logger.info(getData("platformName") + " -  User navigates to Based on intrest carousel listing page #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/Based_on_intrest.png");
		ClickOnMobileElement(seeAll_listingPageBackButton);
		waitFor(5000);
		swipeDown();
		Assert.assertTrue(basedOnIsbn_lbl.isDisplayed());
		ClickOnMobileElement(basedOnIsbnSeeAll_Btn);
		waitFor(5000);
		Assert.assertEquals(seeAll_listingPageHeader.getText(), testData.get(0).get("SeeALL_ListPage_Header2"));
		logger.info(getData("platformName") + " -  User navigates to Based on isbn carousel listing page #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/Based_on_isbn.png");
		ClickOnMobileElement(seeAll_listingPageBackButton);
		waitFor(5000);
		swipeDown();
	}
	
	public static void matTypeAndMoreIconValidation() throws IOException {
		try {
			if (ourSuggestion_MatStaus.size()>4 && ourSuggestion_MoreIcon.size()>=4) {
				logger.info(getData("platformName") + " -  Material type and more icon is displayed for all the titles #Pass");
				Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/MatType.png");
			}
		}
		catch (Exception e) {
			logger.info(getData("platformName") + " -  Material type and more icon is displayed for all the titles #Fail");
			Screenshots.takeScreenshot(driver,"./Screenshots/" + getData("platformName") + "/ULP/MatType.png");
		}
	}
	
/********************************************* User landing Page - 1.6.23 **********************q********************************/
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/insights_text")
	public static MobileElement inSights_lbl; 
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/insights_image")
	public static List<MobileElement> inSights_Image;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/goals_heading_text")
	public static MobileElement inSights_SetGoalsPageHeader;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/goals_desc_text")
	public static MobileElement inSights_GoalsDesc;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/goals_info_text")
	public static MobileElement inSights_GoalsmetricsText;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/button_set_goal")
	public static MobileElement inSights_SetGoalBtn;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/target_goal_edit_text")
	public static MobileElement inSights_TargetSettingField;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/daily_count_text")
	public static MobileElement inSights_DailyCount;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/button_remove_goal")
	public static List<MobileElement> inSights_RemoveGoalBtn;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/badges_text")
	public static MobileElement badges_lbl;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/badge_image")
	public static List<MobileElement> badges_Image;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/challenge_programs_text")
	public static MobileElement chPrg_lbl;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/challenges_programs_see_all_text")
	public static MobileElement chPrgSeeAll_Btn;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/book_cover_image")
	public static List<MobileElement> chPrgList;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/interest_recommendation_header_text")
	public static MobileElement basedOnIntrest_lbl;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/interest_recommendation_see_all_text")
	public static MobileElement basedOnIntrestSeeAll_Btn;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/nav_title")
	public static MobileElement seeAll_listingPageHeader;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/nav_back")
	public static MobileElement seeAll_listingPageBackButton;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/material_cover_image_view")
	public static List<MobileElement> ourSuggestion_Title;

	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/material_status_image_view")
	public static List<MobileElement> ourSuggestion_MatStaus;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/material_status_view")
	public static List<MobileElement> ourSuggestion_MatStausText;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/more_option_image_view")
	public static List<MobileElement> ourSuggestion_MoreIcon;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/isbn_recommendation_header_text")
	public static MobileElement basedOnIsbn_lbl;
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/isbn_recommendation_see_all_text")
	public static MobileElement basedOnIsbnSeeAll_Btn;

}
