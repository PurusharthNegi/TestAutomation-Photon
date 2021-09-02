package com.pom;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateaChallengeSearchTitleResultsListView extends CapabilitiesAndWebDriverUtils {

	public CreateaChallengeSearchTitleResultsListView() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivMore\"])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"ItemMoreOptionsIcon\"])[1]")
	public static MobileElement moreOptions;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivIn")
	@iOSXCUITFindBy(id = "(//XCUIElementTypeStaticText[@name=\"IN\"])[1]")
	public static MobileElement materialTypeIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivBook")
	public static MobileElement titleImage;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/etSearch")
	@iOSXCUITFindBy(accessibility = "ACSR_SuggestedSearchTextField")
	public static MobileElement searchTextBox;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvResultsHead")
	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"RECENTLY SEARCHED\"]")
	public static MobileElement recentlySearchedText;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeStaticText[@name=\"J.K. Rowling Mary GrandPre\"])[1]")
	public static MobileElement iosauthorName;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvTitle\"])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Harry Porter and the Chamber of Secrets\"])[1]")
	public static MobileElement iosBookName;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"iconsCoreRatingsSolid\"])[1]")
	public static MobileElement iosFavIcon;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvRecommendationsHead")
	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"Recommendations\"]")
	public static MobileElement recommendationText;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvFavouritesHead")
	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"From Your Favorites\"]")
	public static MobileElement yourFavoriteText;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/book_club_cross_image\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreRemove\"]")
	public static MobileElement iOSXButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreGridView\"]")
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_view_type")
	public static MobileElement iosgridView;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreSortFilter\"]")
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/nav_filter")
	public static MobileElement sortandFilter;

	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"30 of 1084 Results: mystery\"]")
	public static MobileElement searchResults;


	@AndroidFindBy(id ="com.follett.fss.searchread.stage:id/nav_toolbar_left_action_button")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Done\"]")
	public static MobileElement iosdoneBtn;

	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"Harry porter\"]")
	public static MobileElement iosheader;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeButton[@name=\"iconsCoreRemove\"])[1]")
	public static MobileElement iosXIcon;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeImage[@name=\"elementsImagesBookTitlesMurderBook3\"])[1]")
	public static MobileElement iosBooksImage;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvTitle\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Harry Potter\"]")
	public static MobileElement iosHorryPotter;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.follett.fss.searchread.stage:id/tvAddToChallenge']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Add to Challenge\"]")
	public static MobileElement iosAddToChallenge;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Checkout\"]")
	public static MobileElement iosCheckoutBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Read Online\"]")
	public static MobileElement iosReadOnlineBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeSheet/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
	public static MobileElement iosCancelBtn;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/tvTitle\"]")
	public static List<MobileElement> TitleSearchResult;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tvPageCount")
	public static MobileElement pageCountText;

	@iOSXCUITFindBy(accessibility = "NavigationBackButton")
	public static MobileElement btn_Back;

	@iOSXCUITFindBy(accessibility = "cc_navigationTitle")
	public static MobileElement title_NavigationTitle;

	@iOSXCUITFindBy(xpath = "//*[@name='IN'][1]")
	public static MobileElement inImage;

	public static MobileElement getIosAddToChallenge() {
		return iosAddToChallenge;
	}

	public static List<MobileElement> getTitleSearchResult() {
		return TitleSearchResult;
	}

	public static MobileElement getIosCheckoutBtn() {
		return iosCheckoutBtn;
	}

	public static MobileElement getIosReadOnlineBtn() {
		return iosReadOnlineBtn;
	}

	public static MobileElement getIosCancelBtn() {
		return iosCancelBtn;
	}

	public static MobileElement getIosHorryPotter() {
		return iosHorryPotter;
	}

	public static MobileElement getIosauthorName() {
		return iosauthorName;
	}

	public static MobileElement getIosBookName() {
		return iosBookName;
	}

	public static MobileElement getIosFavIcon() {
		return iosFavIcon;
	}

	public static MobileElement getIosBooksImage() {
		return iosBooksImage;
	}

	public static MobileElement getIosSearchTextBox() {
		return searchTextBox;
	}

	public static MobileElement getRecentlySearchedText() {
		return recentlySearchedText;
	}

	public static MobileElement getRecommendationText() {
		return recommendationText;
	}

	public static MobileElement getYourFavoriteText() {
		return yourFavoriteText;
	}

	public static MobileElement getiOSXButton() {
		return iOSXButton;
	}

	public static MobileElement getIosgridView() {
		return iosgridView;
	}

	public static MobileElement getSortandFilter() {
		return sortandFilter;
	}

	public static MobileElement getSearchResults() {
		return searchResults;
	}

	public static MobileElement getIosdoneBtn() {
		return iosdoneBtn;
	}

	public static MobileElement getIosheader() {
		return iosheader;
	}

	public static MobileElement getIosXIcon() {
		return iosXIcon;
	}

	public static MobileElement getMoreOptions() {
		return moreOptions;
	}

	public static MobileElement getMaterialTypeIcon() {
		return materialTypeIcon;
	}

	public static MobileElement getTitleImage() {
		return titleImage;
	}

}
