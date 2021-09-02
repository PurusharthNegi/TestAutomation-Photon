package com.pom_RWD;

import com.base.CapabilitiesAndWebDriverUtils;
import com.base.ExcelReader;
import com.base.Screenshots;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login_RWD extends CapabilitiesAndWebDriverUtils {
    static ExcelReader reader = new ExcelReader();
    private static final Logger logger = LogManager.getLogger();

    public Login_RWD() {
	PageFactory.initElements(driver, this);
    }

    public static void rwd_Login(String username, String password) throws InvalidFormatException, IOException {
	logger.info("-- Login Web Page Validation Start --");
	waitFor(5000);
	WaitForWebElement(menu_Login);
	logger.info("Login Button is displayed #Pass");
	Screenshots.takeScreenshot(driver, "./Screenshots/UIRefreshWeb/Login/DefaultHomePageBeforeLogin.png");
	waitForElementClick(menu_Login);
	waitFor(5000);
	Screenshots.takeScreenshot(driver, "./Screenshots/UIRefreshWeb/Login/LoginPage.png");
	loginAssertion();
	SendKeysOnWebElement(userName, username);
	SendKeysOnWebElement(Password, password);
	logger.info("Username and password entered #Pass");
	ClickOnWebElement(RWD_DLX_Submit);
	logger.info("Successfully Logged in as: " + username +"#Pass");
	WaitForWebElement(menu_SignedInUser);
	logger.info("-- Login Web Page Validation End --");

    }

    public static void loginAssertion() throws InvalidFormatException, IOException {
	waitFor(3000);
	List<Map<String, String>> testData = reader.getData("./Data/MobileData.xlsx", "Login");
	Assert.assertEquals(lbl_DlxBntTitle.getText(), testData.get(0).get("lbl_DlxBntTitle"));
	Assert.assertEquals(LoginPageHeader.getText(), testData.get(0).get("lbl_LoginUsingFolletTitle"));
	Assert.assertEquals(usernameHeader.getText(), testData.get(0).get("lbl_UserName"));
	Assert.assertEquals(passwordHeader.getText(), testData.get(0).get("lbl_Password"));
	Assert.assertEquals(loginNote.getText(), testData.get(0).get("lbl_LoginNotes"));
	Assert.assertEquals(lbl_CopyRights.getText(), testData.get(0).get("lbl_CopyRights"));
	logger.info("Login Page assertion successfully completed #Pass");
    }

    // span[text()=' Login ']
    @FindBy(xpath = "//button[@id='head-login-btn']")
    public static WebElement menu_Login;

    @FindBy(xpath = "//button[@id='head-signin-btn']")
    public static WebElement menu_SignedInUser;

    @FindBy(xpath = "//div[text()='Dlx BnT Digital Site']")
    private static WebElement lbl_DlxBntTitle;

    @FindBy(xpath = "//span[contains(text(),'© 2021 Follett School Solutions; Inc.')]")
    private static WebElement lbl_CopyRights;

    @FindBy(xpath = "//*[@id=\"loginForm\"]/h2")
    private static WebElement LoginPageHeader;

    @FindBy(xpath = "//*[@for=\"userName\"]")
    private static WebElement usernameHeader;

    @FindBy(xpath = "//*[@for=\"userPassword\"]")
    private static WebElement passwordHeader;

    @FindBy(xpath = "//*[@class=\"loginTrouble\"]")
    private static WebElement loginNote;

    @FindBy(xpath = ".//*[@id=\"head-login-btn\" or text()[normalize-space()=\"Login\"]]")
    private static WebElement RWD_DLX_Login_button;

    @FindBy(id = "userName")
    private static WebElement userName;

    @FindBy(id = "userPassword")
    private static WebElement Password;

    @FindBy(id = "userName")
    private WebElement RWD_DLX_UserName;

    @FindBy(id = "userPassword")
    private WebElement RWD_DLX_Password;

    @FindBy(xpath = ".//*[@name=\"login-submit\" or text()=\"Submit\"]")
    private static WebElement RWD_DLX_Submit;

    @FindBy(xpath = ".//*[contains(@class,\"mat-focus-indicator mat-badge menuIcon\")]")
    private WebElement RWD_DLX_Hambugermenu;

    @FindBy(xpath = ".//*[@id=\"head-menu-list-book\" or text()=\"Book Club\"]")
    private WebElement RWD_DD_BookClub;

    @FindBy(xpath = ".//*[@id=\"head-signin-btn\" or contains(@name,\"UserName\")]")
    private WebElement RWD_DLX_Welcomeback_link;

    @FindBy(xpath = ".//*[text()[normalize-space() = \"Logout\"] and @aria-disabled=\"false\"]")
    private WebElement RWD_DLX_Logout_button;

    public WebElement getRWD_DLX_LoginPageHeader() {
	return LoginPageHeader;
    }

    public WebElement getRWD_DLX_usernameHeader() {
	return usernameHeader;
    }

    public WebElement getRWD_DLX_passwordHeader() {
	return passwordHeader;
    }

    public WebElement getRWD_DLX_LoginNote() {
	return loginNote;
    }

    public static WebElement getRWD_DLX_Login_button() {
	return RWD_DLX_Login_button;
    }

    public WebElement getRWD_DLX_UserName() {
	return RWD_DLX_UserName;
    }

    public WebElement getRWD_DLX_Password() {
	return RWD_DLX_Password;
    }

    public WebElement getRWD_DLX_Submit() {
	return RWD_DLX_Submit;
    }

    public WebElement getRWD_DLX_Hambugermenu() {
	return RWD_DLX_Hambugermenu;
    }

    public WebElement getRWD_DD_BookClub() {
	return RWD_DD_BookClub;
    }

    public WebElement getRWD_DLX_Welcomeback_link() {
	return RWD_DLX_Welcomeback_link;
    }

    public WebElement getRWD_DLX_Logout_button() {
	return RWD_DLX_Logout_button;
    }

}
