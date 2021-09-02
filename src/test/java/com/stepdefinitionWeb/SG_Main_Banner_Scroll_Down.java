package com.stepdefinitionWeb;

import com.pom.MainBannerScrollDown;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.base.CapabilitiesAndWebDriverUtils.driver;

public class SG_Main_Banner_Scroll_Down {

    MainBannerScrollDown scrollDown = new MainBannerScrollDown(driver);
    private static final Logger logger = LogManager.getLogger();

    @Given("that I am on senegence homepage")
    public void that_I_am_on_senegence_homepage() {
        logger.info("Browser Launched successfully #Pass");
    }

    @When("I select the scroll down Icon")
    public void i_select_the_scroll_down_Icon() {

        scrollDown.clickOnScrollDown();
    }

    @Then("I should get to the next section of homepage")
    public void i_should_get_to_the_next_section_of_homepage() {
    }
}
