package com.pom;

import com.base.CapabilitiesAndWebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 */
public class MainBannerScrollDown extends CapabilitiesAndWebDriverUtils {

    @FindBy(xpath="//div[@class='down-arrow-image']")
    private WebElement scrolldown;


    public MainBannerScrollDown(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOnScrollDown()
    {
scrolldown.click();
    }

    public WebElement getScrolldown() {
        return scrolldown;
    }
}

