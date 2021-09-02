package com.pom_RWD;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;

import javax.xml.bind.annotation.W3CDomHandler;
import java.util.List;

public class RWD_CreateChallengeSetReminders extends CapabilitiesAndWebDriverUtils {
	
public RWD_CreateChallengeSetReminders()  {
	PageFactory.initElements(driver, this);
		
	}
	String AbuseDescFieldText="Please provide details about your concern regarding the Reading Challenge.";

	@FindBy(id = "tab-chalge-list")
	public WebElement RWD_ChallengeTab;

	@FindBy(xpath = "//div[contains(@class,\"dd-book-card\")][1]/parent::div/div[2]//*[contains(@class,\"mat-card-title\")]")
	public List<WebElement> RWD_activeChallengeList;

	@FindBy(xpath = "//*[@class=\"readchallenge\"]/h1")
	public WebElement RWD_challengeNameDetailsScreen;

	@FindBy(id = "accept-btn")
	public WebElement RWD_acceptChallengeButton;

	@FindBy(id = "no-thanks")
	public WebElement RWD_noThanksButton;

	@FindBy(id = "report-abuse-btn")
	public WebElement RWD_abuseButton;

	@FindBy(xpath = "//*[text()=\"Report Abuse\"]")
	public WebElement RWD_reportAbuseHeaderText;

	@FindBy(id = "reprt-abrs-desc")
	public WebElement RWD_descriptionAbuse;

	@FindBy(id = "reprt-cancel")
	public WebElement RWD_cancelAbuseButton;

	@FindBy(id = "reprt-submit")
	public WebElement RWD_submitAbuseButton;

	@FindBy(xpath = "//*[@class=\"card-title\"]")
	public WebElement RWD_challengeAcceptText;

	@FindBy(id = "goto-btn")
	public WebElement RWD_goToChallengeButton;

	@FindBy(id = "share-btn")
	public  WebElement RWD_shareButton;

	public void abuseChallenge(String challengeName) throws Exception {
		int noOfChallenges=RWD_activeChallengeList.size();
		for(int i=0; i<=noOfChallenges; i++){
			logger.info("user is searching for Challenge Name");
			String challengName=RWD_activeChallengeList.get(i).getText();
			if (challengeName.equalsIgnoreCase(challengName)){
				waitForElementClick(RWD_activeChallengeList.get(i));
				if(RWD_goToChallengeButton.isDisplayed()){
				waitForElementClick(RWD_acceptChallengeButton);
				Assert.assertTrue(RWD_shareButton.isDisplayed());
				logger.info("User has successfully accepted the Challenge");
				Assert.assertTrue(RWD_challengeAcceptText.isDisplayed());
				waitForElementClick(RWD_goToChallengeButton);
				logger.info("user clicked on the go challenge name");
				waitForElementClick(RWD_abuseButton);
				logger.info("user clicked on the abuse button");
				Assert.assertTrue(RWD_reportAbuseHeaderText.isDisplayed());
				logger.info("Abuse Page header is verified");
				Assert.assertTrue(RWD_reportAbuseHeaderText.isDisplayed());
				logger.info("Abuse description field text");
				waitForElementClick(RWD_descriptionAbuse);
				RWD_reportAbuseHeaderText.sendKeys("Automation_Challenge Description_" + RandomStringUtils.randomAlphanumeric(15));
				Assert.assertTrue(RWD_submitAbuseButton.isDisplayed());
				Assert.assertTrue(RWD_cancelAbuseButton.isDisplayed());
				waitForElementClick(RWD_submitAbuseButton);
				logger.info("Used clicked on the Submit button");
				}
				else{
					logger.info("user clicked on the challenge name");
					waitForElementClick(RWD_abuseButton);
					logger.info("user clicked on the abuse button");
					Assert.assertTrue(RWD_reportAbuseHeaderText.isDisplayed());
					logger.info("Abuse Page header is verified");
					Assert.assertTrue(RWD_reportAbuseHeaderText.isDisplayed());
					logger.info("Abuse description field text");
					waitForElementClick(RWD_descriptionAbuse);
					RWD_reportAbuseHeaderText.sendKeys("Automation_Challenge Description_" + RandomStringUtils.randomAlphanumeric(15));
					Assert.assertTrue(RWD_submitAbuseButton.isDisplayed());
					Assert.assertTrue(RWD_cancelAbuseButton.isDisplayed());
					waitForElementClick(RWD_submitAbuseButton);
					logger.info("Used clicked on the Submit button");
				}
			}
			else {
				throw new Exception("Challenge is not displayed the abuse button");
			}
		}
	}
	public void AcceptChallenge(String challengeName) throws Exception {
		int noOfChallenge = RWD_activeChallengeList.size();
		for (int i = 0; i <= noOfChallenge; i++) {
			String challengName = RWD_activeChallengeList.get(i).getText();
			if (challengeName.equalsIgnoreCase(challengName)) {
				waitForElementClick(RWD_activeChallengeList.get(i));
				if (RWD_challengeAcceptText.isDisplayed()) {
					waitForElementClick(RWD_acceptChallengeButton);
					Assert.assertTrue(RWD_shareButton.isDisplayed());
					logger.info("User has successfully accepted the Challenge");
					Assert.assertEquals(RWD_challengeAcceptText.getText()," Challenge Accepted! ");
					logger.info("challenge accepted success message should be displayed");
					waitForElementClick(RWD_goToChallengeButton);
					Assert.assertTrue(RWD_abuseButton.isDisplayed());
					logger.info("user should be able to see the Abuse button in details screen");
				} else {
					logger.info("Challenge is already accepted");
					throw new Exception("Challenge is already accepted");
				}
			}
		}
	}
}
