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
 * public class CreateAChallengeInviteRecentlySearchedFriends extends
 * CapabilitiesAndWebDriverUtils {
 * 
 * public CreateAChallengeInviteRecentlySearchedFriends() {
 * 
 * PageFactory.initElements(new AppiumFieldDecorator(driver), this); }
 * 
 * public static void inviteStudents() {
 * ClickOnMobileElement(CreateAChallengeBasicChallengeDetails.addFriendCTA);
 * SendKeysOnMobileElement(searchBox, "henry");
 * ClickOnMobileElement(searchOption); ClickOnMobileElement(inviteOption_User);
 * Assert.assertEquals(fluentWaitDisplayed(addedFriendNameCarosallist, 30, 2),
 * true); ClickOnMobileElement(addToChallenge);
 * Assert.assertEquals(fluentWaitDisplayed(CreateAChallengeBasicChallengeDetails
 * .iosAddedFriendCreateChallengepage, 30, 2),true);
 * 
 * 
 * }
 * 
 * 
 * @AndroidFindBy(xpath =
 * "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_invite_user\"])[1]")
 * 
 * @iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='INVITE']") public
 * static MobileElement inviteOption_User;
 * 
 * @AndroidFindBy(xpath =
 * "//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_invite_user\"]")
 * public static List<MobileElement> inviteOptionList;
 * 
 * @AndroidFindBy(xpath =
 * "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_userName\"])[1]")
 * 
 * @iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Henry\"]") public
 * static MobileElement recentfriendName;
 * 
 * @AndroidFindBy(xpath =
 * "//*[@resource-id=\"com.follett.fss.searchread.stage:id/txt_userName\"]")
 * public static List<MobileElement> recentSearchfriendList;
 * 
 * @AndroidFindBy(xpath =
 * "(//*[@resource-id=\"com.follett.fss.searchread.stage:id/img_user\"])[1]")
 * public static List<MobileElement> recentfriendAvatar;
 * 
 * // @AndroidFindBy(xpath =
 * "//*[@resource-id=\"com.follett.fss.searchread.stage:id/editText_description\"]")
 * 
 * @AndroidFindBy(id =
 * "com.follett.fss.searchread.stage:id/title_recently_searched")
 * 
 * @iOSXCUITFindBy(
 * xpath="//XCUIElementTypeStaticText[@name=\"RECENTLY SEARCHED\"]") public
 * static MobileElement recentsearchText;
 * 
 * @AndroidFindBy(xpath =
 * "//*[@resource-id=\"com.follett.fss.searchread.stage:id/search_close\"]")
 * public static MobileElement closeIcon;
 * 
 * @AndroidFindBy(xpath =
 * "//android.widget.TextView[@content-desc=\"Add to Challenge\"]")
 * 
 * @iOSXCUITFindBy(
 * xpath="//XCUIElementTypeButton[@name=\"AF_Add to Challenge\"]") public static
 * MobileElement addToChallenge;
 * 
 * @AndroidFindBy(xpath =
 * "//*[@resource-id=\"com.follett.fss.searchread.stage:id/etSearch\"]")
 * 
 * @iOSXCUITFindBy(xpath="//*[@label='Search student name']")
 * //@iOSXCUITFindBy(xpath=
 * "//XCUIElementTypeSearchField[@name='ACSR_SuggestedSearchTextField']") public
 * static MobileElement searchBox;
 * 
 * @AndroidFindBy(xpath =
 * "//*[@resource-id=\"com.follett.fss.searchread.stage:id/ivSearch\"]") public
 * static MobileElement searchIcon;
 * 
 * @iOSXCUITFindBy(id="iconsCoreRemove") public static MobileElement iosxicon;
 * 
 * @iOSXCUITFindBy(id="Search")
 * 
 * @AndroidFindBy(id=
 * "com.follett.fss.searchread.stage:id/nav_toolbar_center_title") public static
 * MobileElement searchHeader;
 * 
 * @iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name=\"Search\"]") public
 * static MobileElement searchOption;
 * 
 * @iOSXCUITFindBy(
 * xpath="//XCUIElementTypeApplication[@name=\"DD BookClub\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther"
 * ) public static MobileElement addedFriendNameCarosallist;
 * 
 * @AndroidFindBy(xpath=
 * "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button")
 * public static List<MobileElement> addedFriendInSearchPage;
 * 
 * @AndroidFindBy(xpath
 * ="//android.view.View[@content-desc=\"Remove Photon Student15\"]") public
 * static MobileElement removeFriendInSearchPage;
 * 
 * @AndroidFindBy(xpath=
 * "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button")
 * public static List<MobileElement> addedFriendInSearchFriend;
 * 
 * @AndroidFindBy(xpath
 * ="//android.view.View[@content-desc=\"Remove ph3 Photon\"]") public static
 * MobileElement removeFriendInSearchPage1;
 * 
 * public MobileElement getSearchOption() { return searchOption; }
 * 
 * public MobileElement getAddedFriendNameCarosallist() { return
 * addedFriendNameCarosallist; }
 * 
 * public MobileElement getIosxicon() { return iosxicon; }
 * 
 * public MobileElement getSearchHeader() { return searchHeader; }
 * 
 * public MobileElement getInviteOption() { return inviteOption_User; }
 * 
 * public MobileElement getRecentfriendName() { return recentfriendName; }
 * 
 * public List<MobileElement> getRecentfriendAvatar() { return
 * recentfriendAvatar; }
 * 
 * public MobileElement getRecentsearchText() { return recentsearchText; }
 * 
 * public MobileElement getCloseIcon() { return closeIcon; }
 * 
 * public MobileElement getAddToChallenge() { return addToChallenge; }
 * 
 * public MobileElement getSearchBox() { return searchBox; }
 * 
 * public MobileElement getSearchIcon() { return searchIcon; }
 * 
 * 
 * 
 * 
 * }
 */