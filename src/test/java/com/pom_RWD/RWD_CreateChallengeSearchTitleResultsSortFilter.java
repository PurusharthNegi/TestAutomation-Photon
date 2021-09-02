package com.pom_RWD;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class RWD_CreateChallengeSearchTitleResultsSortFilter extends CapabilitiesAndWebDriverUtils {

	public RWD_CreateChallengeSearchTitleResultsSortFilter() {

	}


	@FindBy(className ="mat-radio-button")
	public List<WebElement> RWD_RadioAllElement;



	@FindBy(className ="mat-expansion-panel-header-title")
	public List<WebElement> RWD_FilterAllElement;







	public WebElement applySortby(String value) throws InterruptedException {

		for (WebElement allElemenet : RWD_RadioAllElement) {
			String radioText = allElemenet.getText();
			if (radioText.equalsIgnoreCase(value)) {
				WebActionClick(allElemenet);
				return allElemenet;
			}
		}
		return null;

	}



	public void applyFilter(String value) throws InterruptedException {

		for (WebElement allElemenet : RWD_FilterAllElement) {
			String radioText = allElemenet.getText();
			if (radioText.equalsIgnoreCase(value)) {
				WebActionClick(allElemenet);
			}
		}

	}


}
