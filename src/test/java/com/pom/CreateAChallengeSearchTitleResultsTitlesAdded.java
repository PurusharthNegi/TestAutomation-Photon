package com.pom;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateAChallengeSearchTitleResultsTitlesAdded extends CapabilitiesAndWebDriverUtils{
	public CreateAChallengeSearchTitleResultsTitlesAdded()  {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivSearch\"]")
	public static  MobileElement searchIcon;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/etSearch\"]")
	public static  MobileElement searchBar;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvRecommendationsHead\"]")
	public static  MobileElement recommendationText;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivBook\"])[1]")
	public static  MobileElement recommendationBook;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvFavouritesHead\"]")
	public static  MobileElement favouritesText;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivBook\"])[4]")
	public static  MobileElement favouritesBook;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvResultsHead\"]")
	public static  MobileElement recentlySearchedText;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvTitle\"])[1]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Harry Potter\"]")
	public static  MobileElement recentlySearchedTitles;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Done\"]")
	public static  MobileElement doneBtn;
	
	@iOSXCUITFindBy(xpath ="Search title, author or subject")
	public static  MobileElement iosSearchTitle;
	
	@iOSXCUITFindBy(id = "(//XCUIElementTypeButton[@name=\"iconsCoreRemove\"])[1]")
	public static  MobileElement iosXIcon;

	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"Harry porter\"]")
	public static  MobileElement iosheader;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreSortFilter\"]")
	public static  MobileElement sortandFilter;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivMore\"])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"controlsButtonsCardsMore\"])[1]")
	public static  MobileElement moreOptions;
	
	@iOSXCUITFindBy(id = "(//XCUIElementTypeImage[@name=\"iconsContentBook\"])[2]")
	public static  MobileElement iosMaterialType;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeStaticText[@name=\"J.K. Rowling Mary GrandPre\"])[1]")
	public static  MobileElement iosauthorName;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Harry Porter and the Chamber of Secrets\"])[1]")
	public static  MobileElement iosBookName;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"iconsCoreRatingsSolid\"])[1]")
	public static  MobileElement iosFavIcon;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/clRemove")
	public static List<MobileElement> bookDisplayXIcon;

	public MobileElement getDoneBtn() {
		return doneBtn;
	}

	public MobileElement getIosXIcon() {
		return iosXIcon;
	}

	public MobileElement getIosheader() {
		return iosheader;
	}

	public MobileElement getSortandFilter() {
		return sortandFilter;
	}

	public MobileElement getMoreOptions() {
		return moreOptions;
	}

	public MobileElement getIosMaterialType() {
		return iosMaterialType;
	}

	public MobileElement getSearchIcon() {
		return searchIcon;
	}

	public MobileElement getSearchBar() {
		return searchBar;
	}

	public MobileElement getRecommendationText() {
		return recommendationText;
	}

	public MobileElement getRecommendationBook() {
		return recommendationBook;
	}

	public MobileElement getFavouritesText() {
		return favouritesText;
	}

	public MobileElement getFavouritesBook() {
		return favouritesBook;
	}

	public MobileElement getRecentlySearchedText() {
		return recentlySearchedText;
	}

	public MobileElement getRecentlySearchedTitles() {
		return recentlySearchedTitles;
	}

}
