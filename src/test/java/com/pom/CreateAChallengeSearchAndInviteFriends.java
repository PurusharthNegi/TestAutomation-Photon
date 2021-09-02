/*
 * package com.pom;
 * 
 * import java.util.List;
 * 
 * import org.junit.Assert; import org.openqa.selenium.support.PageFactory;
 * 
 * import com.base.CapabilitiesAndWebDriverUtils;
 * 
 * import io.appium.java_client.MobileElement; import
 * io.appium.java_client.pagefactory.AndroidFindBy; import
 * io.appium.java_client.pagefactory.AppiumFieldDecorator; import
 * io.appium.java_client.pagefactory.iOSXCUITFindBy;
 * 
 * public class CreateAChallengeSearchAndInviteFriends extends
 * CapabilitiesAndWebDriverUtils {
 * 
 * public CreateAChallengeSearchAndInviteFriends() {
 * 
 * PageFactory.initElements(new AppiumFieldDecorator(driver), this); }
 * 
 * public static void addFriend(String friendName) {
 * 
 * ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.addFriendCTA);
 * SendKeysOnMobileElement(CreateAChallengeInviteRecentlySearchedFriends.
 * searchBox, friendName); List<MobileElement> inviteopt =
 * CreateAChallengeInviteRecentlySearchedFriends.inviteOptionList; for (int i =
 * 0; i < inviteopt.size(); i++) { inviteopt.get(i).click(); }
 * ClickOnMobileElement(CreateAChallengeInviteRecentlySearchedFriends.
 * addToChallenge); for (int i = 0; i <
 * CreateAChallengeBasicChallengeDetails.addedFriendNameList.size(); i++) {
 * Assert.assertTrue(CreateAChallengeBasicChallengeDetails.addedFriendNameList.
 * get(i).isDisplayed()); } }
 * 
 * 
 * public static void removeFriend(String studentNameToRemove) { for (int i =
 * CreateAChallengeBasicChallengeDetails.addedFriendNameList.size()-1; i>=1 ;
 * i--) {
 * Assert.assertTrue(CreateAChallengeBasicChallengeDetails.addedFriendNameList.
 * get(i).isDisplayed()); String studentName =
 * CreateAChallengeBasicChallengeDetails.addedFriendNameList.get(i).getText();
 * if(studentName.equalsIgnoreCase(studentNameToRemove)) { int j =i-1;
 * CreateAChallengeBasicChallengeDetails.removeFriendXIcon.get(j).click();
 * CreateAChallengeBasicChallengeDetails.friendRemoveButton.click();
 * System.out.println("user "+studentName+" is removed from the list"); break; }
 * } }
 * 
 * 
 * @AndroidFindBy(id = "com.follett.fss.searchread.stage:id/etSearch")
 * 
 * @iOSXCUITFindBy(id="Search student name") private MobileElement
 * searchInputTextbox;
 * 
 * @AndroidFindBy(id = "com.follett.fss.searchread.stage:id/ivSearch") private
 * MobileElement searchIcon;
 * 
 * @AndroidFindBy(id = "com.follett.fss.searchread.stage:id/img_user") private
 * MobileElement ImageUser;
 * 
 * @AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_userName")
 * 
 * @iOSXCUITFindBy(xpath="//*[@type='XCUIElementTypeStaticText']") private
 * List<MobileElement> userNameText;
 * 
 * @AndroidFindBy(id = "com.follett.fss.searchread.stage:id/txt_invite_user")
 * 
 * @iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"INVITE\"])[1]")
 * private MobileElement inviteUserLink;
 * 
 * @AndroidFindBy(id =
 * "com.follett.fss.searchread.stage:id/button_save_challenge")
 * 
 * @iOSXCUITFindBy(
 * xpath="//XCUIElementTypeStaticText[@name=\"Add to Challenge (2)\"]") private
 * MobileElement addToChallengeButton;
 * 
 * @AndroidFindBy(id = "com.follett.fss.searchread.stage:id/search_close")
 * 
 * @iOSXCUITFindBy(id="iconsCoreRemove") private MobileElement searchCloseIcon;
 * 
 * @AndroidFindBy(xpath =
 * "//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_invite_user\"]")
 * 
 * @iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText[@name=\"INVITE\"])")
 * private List<MobileElement> inviteOptionList;
 * 
 * 
 * 
 * public List<MobileElement> getInviteOptionList() { return inviteOptionList; }
 * 
 * public MobileElement getSearchCloseIcon() { return searchCloseIcon; }
 * 
 * public MobileElement getSearchInputTextbox() { return searchInputTextbox; }
 * 
 * public MobileElement getSearchIcon() { return searchIcon; }
 * 
 * public MobileElement getImageUser() { return ImageUser; }
 * 
 * public List<MobileElement> getUserNameText() { return userNameText; }
 * 
 * public MobileElement getInviteUserLink() { return inviteUserLink; }
 * 
 * public MobileElement getAddToChallengeButton() { return addToChallengeButton;
 * }
 * 
 * }
 */