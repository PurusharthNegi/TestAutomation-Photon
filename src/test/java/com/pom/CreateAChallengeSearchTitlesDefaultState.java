package com.pom;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CreateAChallengeSearchTitlesDefaultState extends CapabilitiesAndWebDriverUtils{
	
public CreateAChallengeSearchTitlesDefaultState()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

}
