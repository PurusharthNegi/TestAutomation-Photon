package com.pom;

import com.base.CapabilitiesAndWebDriverUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookClubProgramsLandingScreenPage extends CapabilitiesAndWebDriverUtils {
	public BookClubProgramsLandingScreenPage() { PageFactory.initElements(new AppiumFieldDecorator(driver), this); }
    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Library\"]")
    private MobileElement libraryTab;

	@AndroidFindBy(id="LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"My Stuff\"]")
    private MobileElement myStuffTab;

	@AndroidFindBy(id = "LocatarNeede")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    private MobileElement DoneKeyboadButton;

    @AndroidFindBy(id="LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Book Club\"]")
    private MobileElement bookClubTab;

    @AndroidFindBy(id="LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Search\"]")
    private MobileElement searchTab;

    @AndroidFindBy(id="LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Downloads\"]")
    private MobileElement downloadsTab;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Challenges\"]")
    private MobileElement ChallengesTab;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Programs\"]")
    private MobileElement ProgramsTab;

    @AndroidFindBy(xpath = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Public Programs\"]")
    private MobileElement publicProgramsTab;

    @AndroidFindBy(id = "LocatorNeed")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"No Reading Programs Yet!\"]")
    private MobileElement NoReadingProgramsYetText;

    @AndroidFindBy(id = "LocatorNeed")
    @iOSXCUITFindBy(id = "ACTIVE PROGRAMS")
    private MobileElement ongoingProgramsSection;

    @AndroidFindBy(id = "LocatorNeed")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"READING CHALLENGE: READ BY\")]")
    private MobileElement readingChallangeText;

    @AndroidFindBy(id="LocatorNeede")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"CREATED\")]")
    private MobileElement createdDateDetailsScreen;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"READ BY\"]")
    private MobileElement readByHeaderText;

    @AndroidFindBy(id = "LocatarNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"START DATE\"]")
    private MobileElement startDateHeadedText;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"END DATE\"]")
    private MobileElement endDateHeaderText;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"STATUS\"]")
    private MobileElement statusHeaderText;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"VISIBILITY\"]")
    private MobileElement visibilityHeaderText;

    @AndroidFindBy(id = "Needed")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"TYPE\"]")
    private MobileElement typeHeaderText;

    @AndroidFindBy(id = "Needed")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"REMINDERS\"]")
    private MobileElement remaindersHeaderText;

    @AndroidFindBy(id = "Locator")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Invited to Challenge\"]")
    private MobileElement invitedToChallengeSection;

    @AndroidFindBy(id = "Needed")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Reading List\"]")
    private MobileElement readingListSection;

    @AndroidFindBy(id = "Needed")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Join Program\"]")
    private MobileElement joinProgramButton;

    @AndroidFindBy(id = "Needed")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"No, Thanks\"]")
    private MobileElement noThanksButton;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"DRAFT PROGRAMS\"]")
    private MobileElement draftProgramasSection;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"CLOSED PROGRAMS\"]")
    private MobileElement closedProgramsSection;

    @AndroidFindBy(id = "LocatorNeeded")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"CLOSED PROGRAMS\"]")
    public List<MobileElement> closedProgramsListSection;

    @AndroidFindBy(id = "LocatorNeed")
    @iOSXCUITFindBy(id = "ACTIVE PROGRAMS")
    private MobileElement activeProgramsSection;

    @AndroidFindBy(id = "LocatorNeede")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]['index=1'])[2]")
    private List<MobileElement> activeProgramList;

    @AndroidFindBy(id = "LocatorNeede")
    @iOSXCUITFindBy(xpath = "///XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell")
    private List<MobileElement> closedProgramList;

    public MobileElement getLibraryTab() {
        return libraryTab;
    }
    public MobileElement getMyStuffTab() {
        return myStuffTab;
    }
    public MobileElement getBookClubTab() {
        return bookClubTab;
    }
    public MobileElement getSearchTab() {
        return searchTab;
    }
    public MobileElement getDownloadsTab() {
        return downloadsTab;
    }
    public MobileElement getChallengesTab() {
        return ChallengesTab;
    }
    public MobileElement getProgramsTab() {
        return ProgramsTab;
    }
    public MobileElement getpublicProgramsTab() {
        return publicProgramsTab;
    }
    public MobileElement getNoReadingProgramsYetText(){return NoReadingProgramsYetText;}
    public MobileElement getDoneKeyboadButton(){return DoneKeyboadButton;}
    public MobileElement getdraftProgramasSection() {
        return draftProgramasSection;
    }
    public MobileElement getclosedProgramsSection() {
        return closedProgramsSection;
    }
    public MobileElement getactiveProgramsSection() {
        return activeProgramsSection;
    }
    public List<MobileElement> getactiveProgramList(){return activeProgramList;}
    public List<MobileElement> getclosedProgramList(){return closedProgramList;}
}
