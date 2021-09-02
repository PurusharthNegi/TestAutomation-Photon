package com.pom;

import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReadingChallengeParticipantRCDetailsScreen extends CapabilitiesAndWebDriverUtils {
public ReadingChallengeParticipantRCDetailsScreen()  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/profile_image")
private MobileElement AvatarProfileImage;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/profile_name_text")
@iOSXCUITFindBy(xpath="//*[@value='Jennifer Piazza']")
private MobileElement profileName;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/created_date_text")
@iOSXCUITFindBy(xpath="//*[@value='CREATED 31/03/2021']")
private MobileElement createdDate;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/illustration_image")
private MobileElement challenegIcon;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/tv_readby_date")
@iOSXCUITFindBy(xpath="//*[@value='Reading challenge: Read by 31/03/2021']")
private MobileElement challengeReadbyTextEndDate;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/challenge_name_text")
@iOSXCUITFindBy(xpath="//*[@value='The Emergents Saga']")
private MobileElement challengeNameText;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/description_text")
@iOSXCUITFindBy(xpath="//*[@value='In the year 2043, with the nation destroyed by war, seventeen-year-old Kress and her Conspiracy of friends embark on a dangerous cross-country mission to locate and recruit other Emergents, the name given to teenagers who have begun to exhibit strange evolutionary abilities.']")
private MobileElement descriptionText;


@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/read_progressBar")
private MobileElement progressBar;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/progress_Percentage_text")
private MobileElement progresPercentageText;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/more_option")
private MobileElement moreIcon;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/bottom_sheet")
private MobileElement moreMenuItems;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/leave_challenge_button")
@iOSXCUITFindBy(xpath="//*[@name='Leave Challenge']")
private MobileElement leaveChallengeButton;

@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/report_abuse_button")
@iOSXCUITFindBy(xpath="//*[@name='Report Abuse']")
private MobileElement reportAbuseButton;


@AndroidFindBy(id = "com.follett.fss.searchread.stage:id/cancel_button")
@iOSXCUITFindBy(xpath="//*[@height='57']")
private MobileElement cancelButton;

@AndroidFindBy(id="com.follett.fss.searchread.stage:id/nav_navigation_icon")
private MobileElement navBackBtn;

public MobileElement getNavBackBtn() {
	return navBackBtn;
}


public MobileElement getAvatarProfileImage() {
	return AvatarProfileImage;
}


public MobileElement getProfileName() {
	return profileName;
}


public MobileElement getCreatedDate() {
	return createdDate;
}


public MobileElement getChallenegIcon() {
	return challenegIcon;
}


public MobileElement getChallengeReadbyTextEndDate() {
	return challengeReadbyTextEndDate;
}


public MobileElement getChallengeNameText() {
	return challengeNameText;
}


public MobileElement getDescriptionText() {
	return descriptionText;
}


public MobileElement getProgressBar() {
	return progressBar;
}


public MobileElement getProgresPercentageText() {
	return progresPercentageText;
}


public MobileElement getMoreIcon() {
	return moreIcon;
}


public MobileElement getMoreMenuItems() {
	return moreMenuItems;
}


public MobileElement getLeaveChallengeButton() {
	return leaveChallengeButton;
}


public MobileElement getReportAbuseButton() {
	return reportAbuseButton;
}


public MobileElement getCancelButton() {
	return cancelButton;
}



}
