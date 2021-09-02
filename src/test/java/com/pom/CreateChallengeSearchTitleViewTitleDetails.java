package com.pom;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateChallengeSearchTitleViewTitleDetails extends CapabilitiesAndWebDriverUtils {
	
public CreateChallengeSearchTitleViewTitleDetails()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"TEXT TO SPEECH ENABLED\"]")
private MobileElement iosTextToSpeechEnabledTxt;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Add to Challenge\"]")
private MobileElement iosTextToSpeechEnabled;

@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"CheckOut\"])[1]")
private MobileElement iosCheckoutBtn;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Reserve\"]")
private MobileElement iosReserveBtn;

@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"CheckOut\"])[2]")
private MobileElement iosCheckoutBtn2;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"More\"]")
private MobileElement iosMoreBtn;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Anthony Horowitz\"]")
private MobileElement iosAuthorName;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Moonfower Murders\"]")
private MobileElement iosBookName;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"IN\"]")
private MobileElement iosMaterialType;

@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeImage")
private MobileElement iosDigitalIcon;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Digital\"]")
private MobileElement iosDigitalTxt;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"3 of 3 available\"]")
private MobileElement iosDigitalCopiesAvailable;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"56.3 MB\"]")
private MobileElement iosDigitalBookSize;

@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeImage")
private MobileElement iosPrintIcon;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Print\"]")
private MobileElement iosPrintTxt;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"2 of 1 available\"]")
private MobileElement iosPrintCopiesAvailable;

@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[4]/XCUIElementTypeImage")
private MobileElement iosAudioBookIcon;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Audio Book\"]")
private MobileElement iosAudioBookTxt;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"5 of 3 available\"]")
private MobileElement iosAudioBookCopiesAvailable;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"310 MB\"]")
private MobileElement iosAudioBookSize;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/ivClose")
private MobileElement closeIcon;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/ivMore")
private MobileElement moreIcon;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvBookName")
private MobileElement titleName;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/ivBook")
private MobileElement titleimage;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvCallNumber")
private MobileElement callNumber;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/ivIn")
private MobileElement materialTypeIcon;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvTextToSpeechEnable")
private MobileElement textToSpeech;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvAddToChallenge")
private MobileElement addToChallenge;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvCheckout")
private MobileElement checkOut;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvReadMore")
private MobileElement readMore;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/ivStar1")
private MobileElement ratingStar;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/tvAuthorName")
private MobileElement authorName;

public MobileElement getAuthorName() {
	return authorName;
}

public MobileElement getRatingStar() {
	return ratingStar;
}

public MobileElement getCloseIcon() {
	return closeIcon;
}

public MobileElement getMoreIcon() {
	return moreIcon;
}

public MobileElement getTitleName() {
	return titleName;
}

public MobileElement getTitleimage() {
	return titleimage;
}

public MobileElement getCallNumber() {
	return callNumber;
}

public MobileElement getMaterialTypeIcon() {
	return materialTypeIcon;
}

public MobileElement getTextToSpeech() {
	return textToSpeech;
}

public MobileElement getAddToChallenge() {
	return addToChallenge;
}

public MobileElement getCheckOut() {
	return checkOut;
}

public MobileElement getReadMore() {
	return readMore;
}

public MobileElement getIosTextToSpeechEnabledTxt() {
	return iosTextToSpeechEnabledTxt;
}

public MobileElement getIosTextToSpeechEnabled() {
	return iosTextToSpeechEnabled;
}

public MobileElement getIosCheckoutBtn() {
	return iosCheckoutBtn;
}

public MobileElement getIosReserveBtn() {
	return iosReserveBtn;
}

public MobileElement getIosCheckoutBtn2() {
	return iosCheckoutBtn2;
}

public MobileElement getIosMoreBtn() {
	return iosMoreBtn;
}

public MobileElement getIosAuthorName() {
	return iosAuthorName;
}

public MobileElement getIosBookName() {
	return iosBookName;
}

public MobileElement getIosMaterialType() {
	return iosMaterialType;
}

public MobileElement getIosDigitalIcon() {
	return iosDigitalIcon;
}

public MobileElement getIosDigitalTxt() {
	return iosDigitalTxt;
}

public MobileElement getIosDigitalCopiesAvailable() {
	return iosDigitalCopiesAvailable;
}

public MobileElement getIosDigitalBookSize() {
	return iosDigitalBookSize;
}

public MobileElement getIosPrintIcon() {
	return iosPrintIcon;
}

public MobileElement getIosPrintTxt() {
	return iosPrintTxt;
}

public MobileElement getIosPrintCopiesAvailable() {
	return iosPrintCopiesAvailable;
}

public MobileElement getIosAudioBookIcon() {
	return iosAudioBookIcon;
}

public MobileElement getIosAudioBookTxt() {
	return iosAudioBookTxt;
}

public MobileElement getIosAudioBookCopiesAvailable() {
	return iosAudioBookCopiesAvailable;
}

public MobileElement getIosAudioBookSize() {
	return iosAudioBookSize;
}


}
