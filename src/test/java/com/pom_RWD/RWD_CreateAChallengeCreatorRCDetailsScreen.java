package com.pom_RWD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;


public class RWD_CreateAChallengeCreatorRCDetailsScreen extends CapabilitiesAndWebDriverUtils {
	public RWD_CreateAChallengeCreatorRCDetailsScreen() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div/h1")
	private WebElement RWDheader;
	
	@FindBy(xpath="//*[@class='ui-carousel-item ui-carousel-item-active ui-carousel-item-start ng-star-inserted'][1]")
	private WebElement RWDreadingBook;

	

	public WebElement getRWDheader() {
		return RWDheader;
	}



	public WebElement getRWDreadingBook() {
		return RWDreadingBook;
	}

}