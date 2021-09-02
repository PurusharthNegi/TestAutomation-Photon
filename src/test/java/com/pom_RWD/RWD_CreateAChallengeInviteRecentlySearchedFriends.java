package com.pom_RWD;

import java.util.List;

import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RWD_CreateAChallengeInviteRecentlySearchedFriends extends CapabilitiesAndWebDriverUtils {
	
public RWD_CreateAChallengeInviteRecentlySearchedFriends() {
	 PageFactory.initElements(driver, this);
		
	}

@FindBy(id="chlg-user-add")
public WebElement RWD_AddFriendsCTA_Challenge;

@FindBy(xpath = "(.//*[contains(@id,\"mat-dialog-title\")])/..//*[contains(.,'INVITE FRIENDS')]")
public WebElement RWD_InviteFriendsLabel;

@FindBy(id="ivite-chlg-search")
public WebElement RWD_SearchStudentName;

@FindBy(xpath=".//*[@id=\"invite-chlg-stud\" or @id=\"invite-sug-studs\"]")
public  List<WebElement> RWD_InviteFriendLists;

@FindBy(xpath=".//*[@class=\"student-title\" and text()[normalize-space()]]")
public  List<WebElement> RWD_InviteFriendNameLists;

@FindBy(id="invite-chlg-add-chanlg")
public WebElement RWD_AddtoChallengeButton;

@FindBy(xpath=".//*[@class=\"dd-stud-info ng-star-inserted\"]//*[@class=\"ng-star-inserted\"][2]")
public  List<WebElement> RWD_AddedFrinedsName_RCpage;

@FindBy(xpath=".//*[@class=\"inv-user-name\"]")
public List<WebElement> RWD_AddedNameinSearchFrineds;

@FindBy(xpath="//*[@class=\"inv-user-name\"]/following-sibling::*[contains(@class,\"mat-icon\")]")
public  List<WebElement> RWD_AddedNameCloseiconSearchFrineds;

@FindBy(xpath =".//*[@id=\"invite-chlg-add-chanlg\"]//*[contains(@class,\"invite-count\")]")
public  WebElement RWD_InviteCountCTA_RC;

@FindBy(xpath =".//*[@class=\"dd-recnt-std ng-star-inserted\"]")
public  WebElement RWD_recently_searched_section_title;

@FindBy(xpath=".//*[@class=\"dd-sugg-std ng-star-inserted\"]//*[@class=\"ng-star-inserted\"]")
public  WebElement RWD_StudentFound_section_title;

@FindBy(xpath=".//*[@class=\"dd-sugg-std ng-star-inserted\"]//*[@class=\"ng-star-inserted\"]")
public  WebElement RWD_No_Search_foundtitle;

//  .//*[@class="inv-user-name"]/following-sibling::*[contains(.,"close")]


    public WebElement RWD_AddFriendsCTA_Challenge(){
        return RWD_AddFriendsCTA_Challenge;
    }

    public WebElement RWD_InviteFriendsLabel(){
        return RWD_InviteFriendsLabel;
    }

    public WebElement RWD_SearchStudentName(){
        return RWD_SearchStudentName;
    }

    public  List<WebElement> RWD_InviteFriendLists(){ return RWD_InviteFriendLists; }

    public  List<WebElement> RWD_InviteFriendNameLists(){ return RWD_InviteFriendNameLists; }

    public WebElement RWD_AddtoChallengeButton() {return  RWD_AddtoChallengeButton; }

    public  List<WebElement> RWD_AddedFrinedsName_RCpage() {return  RWD_AddedFrinedsName_RCpage;}

    public  List<WebElement> RWD_AddedNameinSearchFrineds() {return  RWD_AddedNameinSearchFrineds;}

    public  List<WebElement> RWD_AddedNameCloseiconSearchFrineds() {return  RWD_AddedNameCloseiconSearchFrineds;}

    public  WebElement RWD_InviteCountCTA_RC() { return RWD_InviteCountCTA_RC; }

    public  WebElement RWD_recently_searched_section_title(){ return  RWD_recently_searched_section_title;}

    public  WebElement RWD_StudentFound_section_title(){ return  RWD_StudentFound_section_title; }

    public  WebElement RWD_No_Search_foundtitle(){ return  RWD_No_Search_foundtitle; }



}
