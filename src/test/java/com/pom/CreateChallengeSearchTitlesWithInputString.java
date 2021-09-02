package com.pom;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateChallengeSearchTitlesWithInputString extends CapabilitiesAndWebDriverUtils {
public CreateChallengeSearchTitlesWithInputString()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.develop:id/tvTitle\"])[1]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeSearchField[@name=\"Search title, author or subject\"]")
	public static MobileElement searchInputResults;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/ivSearch")
	public static MobileElement searchIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/tvTitle")
	public static MobileElement searchTitleResult;

	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/ivBook")
	public static MobileElement searchBook;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/tvRecommendationsHead")
	public static MobileElement RecommendationsHeadText;

	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/tvFavouritesHead")
	public static MobileElement FavouritesHeadText;

	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/tvResultsHead")
	public static MobileElement ResultsHeadText;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"iconsCoreRemove\"]")
	public static MobileElement iosRemoveBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Harry Potter\"]")
	public static MobileElement iosSuggestedResultsHeader;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"SUGGESTED RESULTS\"]")
	public static MobileElement iosSuggestedResultTxt;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/tvTitle")
	public static List<MobileElement> searchTitleResultList;
	
	@AndroidFindBy(id = "com.follett.fss.searchread.develop:id/tvTitle")
	public static List<MobileElement> recentSearchList;

	public MobileElement getIosRemoveBtn() {
		return iosRemoveBtn;
	}

	public MobileElement getIosSuggestedResultsHeader() {
		return iosSuggestedResultsHeader;
	}

	public MobileElement getIosSuggestedResultTxt() {
		return iosSuggestedResultTxt;
	}

	public MobileElement getSearchInputResults() {
		return searchInputResults;
	}

	public MobileElement getSearchIcon() {
		return searchIcon;
	}

	public MobileElement getSearchTitleResult() {
		return searchTitleResult;
	}

	public MobileElement getSearchBook() {
		return searchBook;
	}

	public MobileElement getRecommendationsHeadText() {
		return RecommendationsHeadText;
	}

	public MobileElement getFavouritesHeadText() {
		return FavouritesHeadText;
	}

	public MobileElement getResultsHeadText() {
		return ResultsHeadText;
	}

	

}
