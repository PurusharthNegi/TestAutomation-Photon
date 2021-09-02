package com.pom_RWD;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

public class RWD_CreateaChallenge_AddTitles extends CapabilitiesAndWebDriverUtils {

    public RWD_CreateaChallenge_AddTitles() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//label[@class='dd-logo-txt']")
    public WebElement RWD_Logo;

    @FindBy(xpath = "//label[@class='bb-text text-white']")
    public WebElement RWD_BookClubText;

    @FindBy(xpath = "//*[contains(text(),'No Reading Challenges Yet!')]")
    public WebElement RWD_NochallahngeCreated;

    @FindBy(xpath = "//a[contains(text(),'Challenge')]")
    public WebElement RWD_ChallengeTab;

    @FindBy(xpath = "//a[contains(text(),'My Programs')]")
    public WebElement RWD_MyProgramsTab;

    @FindBy(xpath = "//a[contains(text(),'Open Programs')]")
    public WebElement RWD_OpenPrograms;

    @FindBy(xpath = "//*[@class='createchallenge']")
    public WebElement RWD_CreateChallengeLink;

    @FindBy(xpath = "//div[@class='dd-create-name']/h2")
    public WebElement RWD_CreateChallengeHeader;

    @FindBy(xpath = "//*[@formcontrolname='challengeName']")
    public WebElement RWD_EnterChallengeName;

    @FindBy(xpath = "//*[@formcontrolname='description']")
    public WebElement RWD_EnterDescription;

    @FindBy(xpath = "//*[@class='mat-focus-indicator dd-usr-btn mat-fab mat-button-base mat-accent']")
    public WebElement RWD_AddFriends;

    @FindBy(xpath = "//*[contains(text(),'Search Students')]")
    public WebElement RWD_SearchFriendsHeader;

    @FindBy(xpath = "//*[@placeholder=\"Search student's name\"]")
    public WebElement RWD_SearchStudentName;

    @FindBy(id = "chlg-enter-name")
    private WebElement RWDchlgentername;



    @FindBy(id = "chalg-desc")
    private WebElement RWDchalgdesc;



    public WebElement getRWD_Logo(){
        return RWD_Logo;
    }

    public WebElement getRWD_BookClubText(){
        return RWD_BookClubText;
    }



    public String enterChallengeName(String name) {

        if (!name.isEmpty() && name.length() <=64) {
            SendKeysOnWebElement(RWDchlgentername, name);
        } else {
            System.out.println("It's Not Valid String Format");

        }
        return name;
    }




    public String enterChallengeDescription(String name) {

        if (!name.isEmpty() && name.length() <= 1300) {
            SendKeysOnWebElement(RWDchalgdesc, name);
        } else {

            System.out.println("It's Not Valid String Format");
        }

        return name;

    }


}
