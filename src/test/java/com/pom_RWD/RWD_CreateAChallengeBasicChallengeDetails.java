package com.pom_RWD;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RWD_CreateAChallengeBasicChallengeDetails extends CapabilitiesAndWebDriverUtils {



//	 WebDriver driver=reUse.getDriver();
	public RWD_CreateAChallengeBasicChallengeDetails() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//mat-icon[@class='mat-icon notranslate material-icons mat-icon-no-color'])[2]")
	private WebElement RWDaddfriendCTA;

	@FindBy(xpath = "(//mat-icon[@class='mat-icon notranslate material-icons mat-icon-no-color'])[3]")
	private WebElement RWDaddTitlesCTA;

	@FindBy(xpath = "//input[@id=\"chlg-enter-name\"]")
	private WebElement RWDChallengenametextbar;

	@FindBy(xpath = "//textarea[@id='mat-input-1']")
	private WebElement RWDChallengedescriptiontextbar;

	@FindBy(xpath = "//*[@aria-label='Next month']")
	private WebElement RWDNextMonthArrow;

	// *[@aria-label='Next month']
	// @FindBy(xpath="//*[@class='mat-form-field-flex ng-tns-c136-9'][1]")
	@FindBy(xpath = "//*[@aria-label='Mode'][1]")
	private WebElement RWDsetReminder;

	@FindBy(xpath = "(//span[@class='mat-option-text'])[3]")
	private WebElement RWDweeklyOption;

	@FindBy(xpath = "//div[@class='dd-create-name']/h2")
	private WebElement CCHeader;

	@FindBy(xpath = "//button[@aria-label='Open calendar'][1]")
	private WebElement RWDcalenderImage;

	@FindBy(xpath = "//*[text()=' 30 ']")
	// @FindBy(xpath="//div[@class='mat-calendar-body-cell-content
	// mat-calendar-body-selected mat-calendar-body-today'][1]")
	private WebElement RWDDate;

	@FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/fss-ms-createchallengemodal/div[3]/form/div[1]/small")
	private WebElement threeOfSixtyfourTextValidation;

	@FindBy(xpath = "//input[@placeholder='Choose a date']")
	private WebElement RWDSelectedDateINCCPage;

	@FindBy(xpath = "//*[@class='mat-focus-indicator dd-strt-btn mat-raised-button mat-button-base ng-star-inserted']")
	private WebElement RWDStartChallengeBtn;

	@FindBy(xpath="(((//*[@class=\"mat-calendar-body\"])//*[@class=\"ng-star-inserted\"][4]//*[@class=\"mat-calendar-body-cell-content\"])[4]/..)")
	private WebElement RWDNextMonthMidDate;

	@FindBy(xpath=".//*[contains(@id,\"mat-datepicker\")]")
	private  WebElement RWDDatePickerSetReadyByDate;


	@FindBy(xpath=".//*[@class=\"mat-calendar-body-cell-content mat-calendar-body-today\"]")
	private  WebElement RWDTodayDateonDatePicker;

	//(.//*[contains(@class,"mat-calendar-body-today")]/..)/preceding-sibling::*
	@FindBy(xpath="(.//*[contains(@class,\"mat-calendar-body-today\")]/..)/preceding-sibling::*")
	private  List<WebElement> getRWDYesterdaysDateonDatePicker;

	@FindBy(xpath="")
	private  WebElement getRWDSelectedDateonDatePicker;

	@FindBy(id="chlg-remdr-optn")
	private List<WebElement> RWDReminderTypes;



	public WebElement getRWDNextMonthArrow() {
		return RWDNextMonthArrow;
	}

	public WebElement getThreeOfSixtyfourTextValidation() {
		return threeOfSixtyfourTextValidation;
	}

	public WebElement getRWDaddTitlesCTA() {
		return RWDaddTitlesCTA;
	}

	public WebElement getRWDcalenderImage() {
		return RWDcalenderImage;
	}

	public WebElement getRWDDate() {
		return RWDDate;
	}

	public WebElement getRWDSelectedDateINCCPage() {
		return RWDSelectedDateINCCPage;
	}

	public WebElement getRWDStartChallengeBtn() {
		return RWDStartChallengeBtn;
	}

//	public ReusableMethods getReUse() {
//		return reUse;
//	}

	public WebElement getCCHeader() {
		return CCHeader;
	}

	public WebElement getRWDsetReminder() {
		return RWDsetReminder;
	}

	public WebElement getRWDweeklyOption() {
		return RWDweeklyOption;
	}

	public WebElement getRWDChallengenametextbar() {
		return RWDChallengenametextbar;
	}

	public WebElement getRWDChallengedescriptiontextbar() {
		return RWDChallengedescriptiontextbar;
	}

	public WebElement getRWDaddfriendCTA() {
		return RWDaddfriendCTA;
	}

	public WebElement getRWDNextMonthMidDate() { return  RWDNextMonthMidDate;}

	public WebElement getRWDDatePickerSetReadyByDate() { return  RWDDatePickerSetReadyByDate;}

	public  WebElement getRWDTodayDateonDatePicker() { return  RWDTodayDateonDatePicker; }

	public  List<WebElement> getRWDYesterdaysDateonDatePicker() { return  getRWDYesterdaysDateonDatePicker; }

	public  WebElement getRWDSelectedDateonDatePicker() { return  getRWDSelectedDateonDatePicker; }

	public List<WebElement> getRWDReminderTypes() { return RWDReminderTypes; }
	
	
	
	
	//Select reminder type
	
	public void SelectReminder(String reminderType)
	{
		ClickOnWebElement(getRWDsetReminder());
		List<WebElement> remindertype = getRWDReminderTypes();
		waitFor(2000);
		switch(reminderType) {
			case "None":
				System.out.println("Clicking reminder type :"+remindertype.get(0).getText());
				remindertype.get(0).click();
				break;
			case "Daily":
				System.out.println("Clicking reminder type :"+remindertype.get(1).getText());
				remindertype.get(1).click();
				break;
			case "Weekly":
				System.out.println("Clicking reminder type :"+remindertype.get(2).getText());
				remindertype.get(2).click();
				break;
			case "Monthly":
				System.out.println("Clicking reminder type :"+remindertype.get(3).getText());
				remindertype.get(3).click();
				break;
			default:
				System.out.println("Provided type is not matched");
				Assert.assertFalse("Provided type is not matched",true);
		}
		
		
	}
	
	
	
	
}
