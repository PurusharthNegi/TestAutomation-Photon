package com.pom_RWD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;


public class RWD_CreateAChallengeSearchAndInviteFriends extends CapabilitiesAndWebDriverUtils{
	//ReusableMethods reUse= new ReusableMethods();
	public RWD_CreateAChallengeSearchAndInviteFriends() {

		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(xpath = "(//div[@class=\"dd-rprt-txtara\"]/mat-form-field/div/div)[7]")
	private WebElement RWD_Search;
	
	@FindBy(xpath = "//*[@placeholder=\"Search student's name\"]")
	private WebElement RWD_SearchStudentName;
	
   // @FindBy(xpath="//span[@class='student-invite']")
    @FindBy(xpath="//*[@id=\"mat-dialog-1\"]/fss-ms-invite-friends/div[1]/div[2]/div/div/mat-list/mat-list-item/div/span[3]")

	private WebElement RWDInviteOption;
	
	@FindBy(xpath="//*[text()='Add to Challenge']")
	private WebElement RWDAddToChallenge;
	
	@FindBy(xpath="//div[@class='dd-stud-info ng-star-inserted'][2]")
	private WebElement RWDaddedFriendCCPage;

	
	
	public WebElement getRWD_Search() {
		return RWD_Search;
	}
	public WebElement getRWD_SearchStudentName() {
		return RWD_SearchStudentName;
	}
//	public ReusableMethods getReUse() {
//		return reUse;
//	}
	public WebElement getRWDInviteOption() {
		return RWDInviteOption;
	}

	public WebElement getRWDAddToChallenge() {
		return RWDAddToChallenge;
	}

	public WebElement getRWDaddedFriendCCPage() {
		return RWDaddedFriendCCPage;
	}
	
}
