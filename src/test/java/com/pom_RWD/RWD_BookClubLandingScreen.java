package com.pom_RWD;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

public class RWD_BookClubLandingScreen extends CapabilitiesAndWebDriverUtils {
	public RWD_BookClubLandingScreen() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()=\"Book Club\"]")
	private WebElement RWDbookClubOptionWeb;

	@FindBy(xpath = "//*[@class='createchallenge']")
	private WebElement RWDaddCTAWeb;

	@FindBy(xpath = "//*[@id=\"outer1\"]/div[2]/div[2]/div[17]/fss-ms-challenge-card/mat-card/div[1]/div[2]/mat-card-content/img")
	private WebElement RWDyouHavebeenChallenged;

	@FindBy(xpath = "//*[text()='Automation-May4 ']")
	private WebElement RWDchallengeNamebookclub;

	@FindBy(xpath = "//*[@class=\"mat-card-title header-title txt-elipsishead\"]")
	private List<WebElement> RWDchallengeNameList;
	
	@FindBy(xpath = "//*[contains(@class,\"dd-chlg-youicon\")]")
	private List<WebElement> RWD_InvitedChallenge_icon_List;

	public void testMessage() {
		System.out.println("title is diaplyed as: " + driver.getTitle());
//		System.out.println("Application is opening but now interacting with elements:"+RWDaddCTAWeb.isDisplayed());
	}

	public List<WebElement> getRWDchallengeNameList() {
		return RWDchallengeNameList;
	}

	public WebElement getRWDyouHavebeenChallenged() {
		return RWDyouHavebeenChallenged;
	}

	public WebElement getRWDchallengeNamebookclub() {
		return RWDchallengeNamebookclub;
	}

	public WebElement getRWDbookClubOptionWeb() {
		return RWDbookClubOptionWeb;
	}

	public WebElement getRWDaddCTAWeb() {
		return RWDaddCTAWeb;
	}

	public WebElement getAddElement() {
		return driver.findElement(By.xpath("//mat-icon[text()=\"add \"]"));
	}

	public List<WebElement> getRWD_InvitedChallenge_icon_List() { return RWD_InvitedChallenge_icon_List;}
	
}
