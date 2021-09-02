package com.pom;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateAChallengeSearchTitleResultsTileView extends CapabilitiesAndWebDriverUtils {

	public CreateAChallengeSearchTitleResultsTileView() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivBook")
	public MobileElement titleImage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreGridView\"]")
	@AndroidFindBy(xpath= "//android.widget.ImageButton[@content-desc=\"Books with Grid Format\"]")
	public MobileElement iosgridView;

//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreListView\"]")
	@AndroidFindBy(xpath= "//android.widget.ImageButton[@content-desc=\"Books with Grid Format\"]")
	@iOSXCUITFindBy(accessibility = "iconsCoreGridView")
	public static MobileElement listView;
	
	@iOSXCUITFindBy(accessibility = "SortByIcon")
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Books with List Format\"]")
	public MobileElement sortandFilter;

	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"30 of 1084 Results: mystery\"]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvPageCount")
	public MobileElement searchResults;

	@iOSXCUITFindBy(id = "//XCUIElementTypeButton[@name=\"Done\"]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/book_club_toolbar_left_action_button")
	public MobileElement iosdoneBtn;

	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"Harry porter\"]")
	public MobileElement iosheader;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeButton[@name=\"iconsCoreRemove\"])[1]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"Close Icon\"]")
	public MobileElement iosXIcon;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeImage[@name=\"elementsImagesBookTitlesMurderBook3\"])[1]")
	public MobileElement iosBooksImage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreRemove\"]")
	public MobileElement iOSXButton;

	@iOSXCUITFindBy(id = "Search title, author or subject")
	public MobileElement iosSearchTextBox;

	@iOSXCUITFindBy(id = "//XCUIElementTypeStaticText[@name=\"RECENTLY SEARCHED\"]")
	public MobileElement recentlySearchedText;

	@iOSXCUITFindBy(id = "(//XCUIElementTypeStaticText[@name=\"J.K. Rowling Mary GrandPre\"])[1]")
	public MobileElement iosauthorName;
	
	
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvBookName")
	public List<MobileElement> bookName;
	
	public List<MobileElement> getBookName() {
		return bookName;
	}

	public List<MobileElement> getAuthorName() {
		return authorName;
	}

	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvAuthorName")
	public List<MobileElement> authorName;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Harry Porter and the Chamber of Secrets\"])[1]")
	public MobileElement iosBookName;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"iconsCoreRatingsSolid\"])[1]")
	public MobileElement iosFavIcon;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivMore\"])[1]")
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"controlsButtonsCardsMore\"])[1]")
	public MobileElement moreOptions;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivIn")
	@iOSXCUITFindBy(id = "(//XCUIElementTypeStaticText[@name=\"IN\"])[1]")
	public MobileElement materialTypeIcon;

	public MobileElement getIosgridView() {
		return iosgridView;
	}

	public MobileElement getListView() {
		return listView;
	}

	public MobileElement getSortandFilter() {
		return sortandFilter;
	}

	public MobileElement getSearchResults() {
		return searchResults;
	}

	public MobileElement getIosdoneBtn() {
		return iosdoneBtn;
	}

	public MobileElement getIosheader() {
		return iosheader;
	}

	public MobileElement getIosXIcon() {
		return iosXIcon;
	}

	public MobileElement getIosBooksImage() {
		return iosBooksImage;
	}

	public MobileElement getiOSXButton() {
		return iOSXButton;
	}

	public MobileElement getIosSearchTextBox() {
		return iosSearchTextBox;
	}

	public MobileElement getRecentlySearchedText() {
		return recentlySearchedText;
	}

	public MobileElement getIosauthorName() {
		return iosauthorName;
	}

	public MobileElement getIosBookName() {
		return iosBookName;
	}

	public MobileElement getIosFavIcon() {
		return iosFavIcon;
	}

	public MobileElement getMoreOptions() {
		return moreOptions;
	}

	public MobileElement getMaterialTypeIcon() {
		return materialTypeIcon;
	}

	public MobileElement getTitleImage() {
		return titleImage;
	}

}
