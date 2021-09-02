package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateAChallengeSetReadbyDate extends CapabilitiesAndWebDriverUtils {
	
	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(CreateAChallengeSetReadbyDate.class);

	
public CreateAChallengeSetReadbyDate()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

public static void iOSSetReadByDate() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
	
	try {

		waitFor(2000);
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.dateOpt);
		waitFor(1000);
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelBefore.png");
		SendKeysOnMobileElementList(iOSDate.get(0), testData.get(0).get("Month"));	
		SendKeysOnMobileElementList(iOSDate.get(1), testData.get(0).get("Date"));
		SendKeysOnMobileElementList(iOSDate.get(2), testData.get(0).get("Year"));
		ClickUsingXandYCords(CreateAChallengeBasicChallengeDetails.iosDontBtn);
		logger.info(getData("platformName")+" - Selected date is : " + testData.get(0).get("Month") + "-" + testData.get(0).get("Date") + "-" + testData.get(0).get("Year") +" #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelAfter.png");

	}
	catch (Exception e) {
		waitFor(2000);
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.dateOpt);
		waitFor(1000);
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelBefore.png");
		SendKeysOnMobileElementList(iOSDate.get(0), testData.get(0).get("Date"));
		SendKeysOnMobileElementList(iOSDate.get(1), testData.get(0).get("Month"));	
		SendKeysOnMobileElementList(iOSDate.get(2), testData.get(0).get("Year"));
		ClickUsingXandYCords(CreateAChallengeBasicChallengeDetails.iosDontBtn);
		logger.info(getData("platformName")+" - Selected date is : " + testData.get(0).get("Date") + "-" + testData.get(0).get("Month") + "-" + testData.get(0).get("Year") +" #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelAfter.png");

	}

	
	
}
		


public static void editedDate() throws InvalidFormatException, IOException {
	
List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
	
	try {

		waitFor(2000);
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.dateOpt);
		waitFor(1000);
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelBefore.png");
		SendKeysOnMobileElementList(iOSDate.get(0), testData.get(1).get("Month"));	
		SendKeysOnMobileElementList(iOSDate.get(1), testData.get(1).get("Date"));
		SendKeysOnMobileElementList(iOSDate.get(2), testData.get(0).get("Year"));
		ClickUsingXandYCords(CreateAChallengeBasicChallengeDetails.iosDontBtn);
		logger.info(getData("platformName")+" - Selected date is : " + testData.get(0).get("Month") + "-" + testData.get(0).get("Date") + "-" + testData.get(0).get("Year") +" #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelAfter.png");

	}
	catch (Exception e) {
		waitFor(2000);
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.dateOpt);
		waitFor(1000);
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelBefore.png");
		SendKeysOnMobileElementList(iOSDate.get(0), testData.get(1).get("Date"));
		SendKeysOnMobileElementList(iOSDate.get(1), testData.get(1).get("Month"));	
		SendKeysOnMobileElementList(iOSDate.get(2), testData.get(0).get("Year"));
		ClickUsingXandYCords(CreateAChallengeBasicChallengeDetails.iosDontBtn);
		logger.info(getData("platformName")+" - Selected date is : " + testData.get(1).get("Date") + "-" + testData.get(1).get("Month") + "-" + testData.get(0).get("Year") +" #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateSelAfter.png");

	}

	
	
	
}
	
	
public static void androidSetReadByDate() throws InvalidFormatException, IOException {
	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
	Assert.assertEquals(setReadByDate_lbl.getText(), testData.get(0).get("lbl_Set Read By Date"));
	ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.dateOpt);
	ClickOnMobileElement(CreateAChallengeSetReadbyDate.nextMonthBtn);
	ClickOnMobileElement(CreateAChallengeSetReadbyDate.endDate);
	ClickOnMobileElement(CreateAChallengeSetReadbyDate.calenderIconOK);
	Assert.assertEquals(fluentWaitDisplayed(CreateAChallengeBasicChallengeDetails.dateOpt, 20, 2), true);
}

public static void androidDateValidation() throws InvalidFormatException, IOException {

	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
	Assert.assertEquals(setReadByDate_lbl.getText(), testData.get(0).get("lbl_Set Read By Date"));
	ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.dateOpt);
	ClickOnMobileElement(yearSelcetionBtn);
	ClickOnMobileElement(NextyearBtn);
	ClickOnMobileElement(updatedEndDate);
	if (updatedEndDate.isEnabled()) {
		ClickOnMobileElement(nextMonthBtn);
		ClickOnMobileElement(updatedEndDate);
		if (updatedEndDate.isEnabled()) {
			logger.info(getData("platformName")+" - Unable to select the date beyond 365Days #Fail");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateAfter_365Days.png");
		}
		else {
			logger.info(getData("platformName")+" - Unable to select the date beyond 365Days #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateAfter_365Days.png");
		}
	}
	else {
		logger.info(getData("platformName")+" - Unable to select the date beyond 365Days #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/DateAfter_365Days.png");
	}
	ClickOnMobileElement(yearSelcetionBtn);
	ClickOnMobileElement(currentyearBtn);
	ClickOnMobileElement(calenderIconOK);
}

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/title_set_read_by_date")
public static MobileElement setReadByDate_lbl;

@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.follett.fss.searchread.stage:id/value_date_picker\"]")
public static MobileElement calenderIcon;

@AndroidFindBy(xpath = "//*[@resource-id=\"android:id/button1\"]")
public static MobileElement calenderIconOK;

@AndroidFindBy(xpath = "//*[@resource-id=\"android:id/button2\"]")
public static MobileElement calenderIconCancel;

@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id=\"android:id/next\"]")
public static MobileElement nextMonthBtn;

@AndroidFindBy(id="android:id/date_picker_header_year")
public static MobileElement yearSelcetionBtn;

@AndroidFindBy(xpath="(//*[@resource-id='android:id/text1'])[1]")
public static MobileElement currentyearBtn;

@AndroidFindBy(xpath="(//*[@resource-id='android:id/text1'])[2]")
public static MobileElement NextyearBtn;

@AndroidFindBy(xpath = "//android.view.View[@text=\"31\"]")
public static MobileElement updatedEndDate;

@AndroidFindBy(xpath = "//android.view.View[@text=\"15\"]")
public static MobileElement endDate;

@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Set read by date\"]")
public static MobileElement iosSetReadByDateText;

@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"iconsInsightsDays\"]")
public static MobileElement iosCalender;

@iOSXCUITFindBy(xpath="//*[@value='May 25, 2021']")
public static MobileElement iosEndDate;

@iOSXCUITFindBy(xpath="//*[@type='XCUIElementTypePickerWheel']")
public static List<MobileElement> iOSDate;

@iOSXCUITFindBy(xpath="//*[@type='XCUIElementTypePickerWheel'][1]")
public static MobileElement ioDate;

@iOSXCUITFindBy(xpath="xpath=//*[@type='XCUIElementTypePickerWheel'][3]")
public static MobileElement iosYear;



public MobileElement getCalenderIcon() {
	return calenderIcon;
}

public MobileElement getCalenderIconOK() {
	return calenderIconOK;
}

public MobileElement getCalenderIconCancel() {
	return calenderIconCancel;
}

public MobileElement getNextMonthBtn() {
	return nextMonthBtn;
}

public MobileElement getEndDate() {
	return endDate;
}
//
//public List<MobileElement> getIosMonth() {
//	return iosDate;
//}

public MobileElement getIoDate() {
	return ioDate;
}

public MobileElement getIosYear() {
	return iosYear;
}

public MobileElement getIosSetReadByDateText() {
	return iosSetReadByDateText;
}

public MobileElement getIosCalender() {
	return iosCalender;
}

public MobileElement getIosEndDate() {
	return iosEndDate;
}



}
