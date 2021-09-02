package com.pom;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateChallengeSearchTitleResultsMoreCTA extends CapabilitiesAndWebDriverUtils {

	public CreateChallengeSearchTitleResultsMoreCTA() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivMore\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='ItemMoreOptionsIcon']") 
	public static List<MobileElement> moreOptions;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.follett.fss.searchread.stage:id/tvAddToChallenge']")
	@iOSXCUITFindBy(accessibility = "Add to Challenge")
	public static MobileElement AddToChallenge;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.follett.fss.searchread.stage:id/tvCheckout']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Checkout\"]")
	public static MobileElement CheckoutBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.follett.fss.searchread.stage:id/tvReadOnline']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Read Online\"]")
	public static MobileElement ReadOnlineBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.follett.fss.searchread.stage:id/tvCancel']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeSheet/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
	public static MobileElement CancelBtn;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"cover image\"]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name='DD BookClub']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")
	public static List<MobileElement> selectedBooks;
	
	
	public List<MobileElement> getSelectedBooks() {
		return selectedBooks;
	}

	public List<MobileElement> getMoreOptions() {
		return moreOptions;
	}

	public MobileElement getAddToChallenge() {
		return AddToChallenge;
	}

	public MobileElement getCheckoutBtn() {
		return CheckoutBtn;
	}

	public MobileElement getReadOnlineBtn() {
		return ReadOnlineBtn;
	}

	public MobileElement getCancelBtn() {
		return CancelBtn;
	}

}
