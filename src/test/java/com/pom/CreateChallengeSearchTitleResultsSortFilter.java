package com.pom;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateChallengeSearchTitleResultsSortFilter extends CapabilitiesAndWebDriverUtils {

	public CreateChallengeSearchTitleResultsSortFilter() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"iconsCoreSortFilter\"]")
	private MobileElement iosYourFavorite;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Cancel\"]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/book_club_toolbar_left_action_button")
	private MobileElement iosCancel;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Sort & Filter\"]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/book_club_toolbar_title")
	private MobileElement iosSortFilterHeader;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Reset All\"]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/book_club_toolbar_right_action_button")
	private MobileElement iosResetAll;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sort by']")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/sort_by_text")
	private MobileElement iosSortByTxt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther[2]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/relevancy_radio_btn")
	private MobileElement iosRelevancyRadioBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeOther[2]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/subject_radio_btn")
	private MobileElement iosSubjectRadioBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeOther[2]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/title_radio_btn")
	private MobileElement iosTitleRadioBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[4]/XCUIElementTypeButton")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/author_radio_btn")
	private MobileElement iosAuthorRadioBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[5]/XCUIElementTypeOther[2]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/available_now_radio_btn")
	private MobileElement iosAvailableRadioBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Relevancy\"]")
	private MobileElement iosRelevancyTxt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Subject\"]")
	private MobileElement iosSubjectTxt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Title\"]")
	private MobileElement iosTitleTxt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Author\"]")
	private MobileElement iosAuthorTxt;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Available Now\"]")
	private MobileElement iosAvailableTxt;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Format\"])[1]")
	private MobileElement iosFormatTxt;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"iconsCorePlusCopy\"])[1]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/plus_icon")
	private MobileElement iosplusIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"View 1084 Results\"]")
	@AndroidFindBy(id="com.follett.fss.searchread.stage:id/view_results_button")
	private MobileElement iosviewResults;

	public MobileElement getIosYourFavorite() {
		return iosYourFavorite;
	}

	public MobileElement getIosCancel() {
		return iosCancel;
	}

	public MobileElement getIosSortFilterHeader() {
		return iosSortFilterHeader;
	}

	public MobileElement getIosResetAll() {
		return iosResetAll;
	}

	public MobileElement getIosSortByTxt() {
		return iosSortByTxt;
	}

	public MobileElement getIosRelevancyRadioBtn() {
		return iosRelevancyRadioBtn;
	}

	public MobileElement getIosSubjectRadioBtn() {
		return iosSubjectRadioBtn;
	}

	public MobileElement getIosTitleRadioBtn() {
		return iosTitleRadioBtn;
	}

	public MobileElement getIosAuthorRadioBtn() {
		return iosAuthorRadioBtn;
	}

	public MobileElement getIosAvailableRadioBtn() {
		return iosAvailableRadioBtn;
	}

	public MobileElement getIosRelevancyTxt() {
		return iosRelevancyTxt;
	}

	public MobileElement getIosSubjectTxt() {
		return iosSubjectTxt;
	}

	public MobileElement getIosTitleTxt() {
		return iosTitleTxt;
	}

	public MobileElement getIosAuthorTxt() {
		return iosAuthorTxt;
	}

	public MobileElement getIosAvailableTxt() {
		return iosAvailableTxt;
	}

	public MobileElement getIosFormatTxt() {
		return iosFormatTxt;
	}

	public MobileElement getIosplusIcon() {
		return iosplusIcon;
	}

	public MobileElement getIosviewResults() {
		return iosviewResults;
	}

}
