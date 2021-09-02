package com.pom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.internal.TestMethodWithDataProviderMethodWorker;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateChallengeSetReminders extends CapabilitiesAndWebDriverUtils {

	static ExcelReader reader = new ExcelReader();
	public static final Logger logger = LogManager.getLogger(CreateChallengeSetReminders.class);

	public CreateChallengeSetReminders() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static void iOSSetReminder() throws IOException, InvalidFormatException {
		List<Map<String, String>> testData1 = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.setReminderOpt);
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/ReminderBefore.png");
		SendKeysWithoutClear(iosRemainderOptions, testData1.get(0).get("inp_reminder"));
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.iosDoneBtn);
		logger.info(getData("platformName")+" - Selected iOS Set reminder is :" + testData1.get(0).get("inp_reminder") +" #Pass");
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/ReminderAfter.png");

	}

	/*
	 * public static void iOSDateSelection(String Month, String Day, String Year)
	 * throws IOException { try {
	 * 
	 * waitFor(2000); ClickOnMobileElement(calendarClick); waitFor(1000);
	 * Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+
	 * "/CreateChallange/DateSelBefore.png");
	 * SendKeysOnMobileElementList(iOSDate.get(0), Month);
	 * SendKeysOnMobileElementList(iOSDate.get(1), Day);
	 * ClickUsingXandYCords(btn_iOS_Date_Done);
	 * logger.info(getData("platformName")+" - Selected date is : " + Month + "-" +
	 * Day + "-" + 2021 +" #Pass");
	 * Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+
	 * "/CreateChallange/DateSelAfter.png");
	 * 
	 * } catch (Exception e) { waitFor(2000); ClickOnMobileElement(calendarClick);
	 * waitFor(1000);
	 * Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+
	 * "/CreateChallange/DateSelBefore.png");
	 * SendKeysOnMobileElementList(iOSDate.get(0), Day);
	 * SendKeysOnMobileElementList(iOSDate.get(1), Month);
	 * ClickUsingXandYCords(btn_iOS_Date_Done);
	 * logger.info(getData("platformName")+" - Selected date is : " + Day + "-" +
	 * Month + "-" + 2021 +" #Pass");
	 * Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+
	 * "/CreateChallange/DateSelAfter.png");
	 * 
	 * } }
	 */

	public static void androidSetReminder(String reminder) throws InvalidFormatException, IOException {
		swipeDown();
		List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Reminder and End date");
		Assert.assertEquals(setReminder_lbl.getText(),testData.get(0).get("lbl_setReminders_android"));
		ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.setReminderOpt);
		Assert.assertTrue(dailyOption.isDisplayed());
		Assert.assertTrue(weeklyOption.isDisplayed());
		Assert.assertTrue(monthlyOption.isDisplayed());
		Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/ReminderBefore.png");

		if(reminder.equalsIgnoreCase("Daily")) {
			String actualremainder = dailyOption.getText();
			ClickOnMobileElement(dailyOption);
			String expectedremainder = addedremainderinccpage.getText();
			Assert.assertEquals(actualremainder, expectedremainder);
			logger.info(getData("platformName")+" - Selected Set reminder is :" + reminder +" #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/ReminderAfter.png");

		}
		else if(reminder.equalsIgnoreCase("Weekly")){
			String actualremainder = weeklyOption.getText();
			ClickOnMobileElement(weeklyOption);
			String expectedremainder = addedremainderinccpage.getText();
			Assert.assertEquals(actualremainder, expectedremainder);
			logger.info(getData("platformName")+" - Selected  Set reminder is :" + reminder +" #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/ReminderAfter.png");

		}
		else if (reminder.equalsIgnoreCase("Monthly")) {
			String actualremainder = monthlyOption.getText();
			ClickOnMobileElement(monthlyOption);
			String expectedremainder = addedremainderinccpage.getText();
			Assert.assertEquals(actualremainder, expectedremainder);
			logger.info(getData("platformName")+" - Selected Set reminder is :" + reminder +" #Pass");
			Screenshots.takeScreenshot(driver,"./Screenshots/"+getData("platformName")+"/CreateChallange/ReminderAfter.png");

		}
		
	}
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Weekly']")
	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/spinner_item")
	public static MobileElement addedremainderinccpage;

	@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/title_set_remainder")
	public static MobileElement setReminder_lbl;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text=\"None\"]")
	public static MobileElement noneOption;
	
	@AndroidFindBy(xpath = "//*[@text='Daily']")
	public static MobileElement dailyOption;

	@AndroidFindBy(xpath = "//*[@text='Weekly']")
	public static MobileElement weeklyOption;

	@AndroidFindBy(xpath = "//*[@text='Monthly']")
	public static MobileElement monthlyOption;

	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypePickerWheel']")
	public static MobileElement iosRemainderOptions;

	@AndroidFindBy(xpath = "//*[@resource-id=\"com.follett.fss.searchread.stage:id/alertTitle\"]")
	public static MobileElement chooseOneText;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/value_date_picker\"])[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CC_SetDate']")
	public static MobileElement calendarClick;

	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypePickerWheel']")
	public static List<MobileElement> iOSDate;

	@iOSXCUITFindBy(xpath = "//*[@label='Done']")
	public static MobileElement btn_iOS_Date_Done;

	public MobileElement getNoneOption() {
		return noneOption;
	}

	public MobileElement getDailyOption() {
		return dailyOption;
	}

	public MobileElement getWeeklyOption() {
		return weeklyOption;
	}

	public MobileElement getMonthlyOption() {
		return monthlyOption;
	}

	public MobileElement getChooseOneText() {
		return chooseOneText;
	}

}
