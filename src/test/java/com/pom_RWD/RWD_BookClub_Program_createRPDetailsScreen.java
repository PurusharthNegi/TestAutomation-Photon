package com.pom_RWD;


import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import java.security.cert.X509Certificate;
import java.util.List;

public class RWD_BookClub_Program_createRPDetailsScreen extends CapabilitiesAndWebDriverUtils {

    public RWD_BookClub_Program_createRPDetailsScreen() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()=\"HOME\"]")
    public WebElement RWD_homePage;

    @FindBy(xpath="//*[contains(text(),'My Programs')]")
    private WebElement RWD_myProgramTab;

    @FindBy(xpath = "//*[contains(text(),\"Open Programs\")]")
    private WebElement RWD_openPrograms;

    @FindBy(id = "tab-chalge-list")
    public WebElement RWD_challengeTab;

    @FindBy(xpath = ".//*[@id=\"prog-main-create\" or contains(text(),'CREATE PROGRAM')]")
    private WebElement RWD_CreateProgramLink;

    @FindBy(xpath = "//*[contains(text(),'Create Reading Program')]")
    private WebElement RWD_createReadingProgramHeader;

    @FindBy(xpath = "//*[@formcontrolname=\"programName\"]")
    private WebElement RWD_enterProgramName;

    @FindBy(xpath = "//*[@formcontrolname=\"description\"]")
    private WebElement RWD_Description;

    @FindBy(xpath = "//div[contains(text(),\"Private\")]")
    private WebElement RWD_privateRadiobutton;

    @FindBy(xpath = "//div[contains(text(),\"Public\")]")
    private WebElement RWD_publicRadiobutton;

    @FindBy(xpath = "//*[@formcontrolname=\"programType\"]")
    private WebElement RWD_selectProgramType;

    @FindBy(xpath = "//*[contains(text(),\"Books in Order\")]")
    public WebElement RWD_booksInOrder;

    @FindBy(xpath = "(//*[contains(text(),\"X of Y Books\")])")
    public WebElement RWD_xAndYBooks;

    @FindBy(xpath = "//*[@placeholder=\"Number of Books\"]")
    public WebElement RWD_numberOfBooksDropDown;

    @FindBy(xpath = "(//*[@role=\"option\"])[1]")
    public WebElement RWD_oneBookSelection;

    @FindBy(xpath = "(//*[@role=\"option\"])[2]")
    public WebElement RWD_oneBookSelection2;

    @FindBy(xpath = "//*[@formcontrolname=\"remainderDuration\"]")
    private WebElement RWD_setReminder;

    @FindBy(xpath = "//*[contains(text(),\"Daily\")]")
    private WebElement RWD_setDailyreminder;

    @FindBy(xpath = "//*[contains(text(),\"Weekly\")]")
    public WebElement RWD_setWeeklyReminder;

    @FindBy(xpath = "//*[contains(text(),\"Monthly\")]")
    public WebElement RWD_setMonthlyReminder;

    @FindBy(xpath = "(//*[@aria-label=\"Example icon button with a delete icon\"])[1]")
    private WebElement RWD_addStudentsButton;

    @FindBy(xpath = "//*[contains(text(),'Search Students')]")
    private WebElement RWD_searchFriendsHeaderText;

    @FindBy(xpath = "//*[@placeholder=\"Search student's name\"]")
    private WebElement RWD_searchStudentName;

    @FindBy(xpath = "(//*[@class=\"student-invite\"])[1]")
    private WebElement RWD_inviteButton;

    @FindBy(xpath = "//*[contains(text(),\"Invite to Program\")]")
    private WebElement RWD_inviteToProgramButton;

    @FindBy(xpath = "(//*[@aria-label=\"Example icon button with a delete icon\"])[2]")
    private WebElement RWD_addTitleButton;

    @FindBy(xpath = "//*[@placeholder=\"Search title, author or topic\"]")
    private WebElement RWD_searchTitleAuthorBox;

    @FindBy(xpath = "(//*[@aria-label=\"Open calendar\"])[1]")
    private WebElement RWD_startDateCalendarIcon;

    @FindBy(xpath = "//*[@class=\"mat-calendar-body-cell-content mat-calendar-body-today\"]")
    private WebElement RWD_startDateCalender;

    @FindBy(xpath = "(//*[@aria-label=\"Open calendar\"])[2]")
    private WebElement RWD_endDateCalendarIcon;

    @FindBy(xpath = "//*[@aria-label=\"Next month\"]")
    private WebElement RWD_CalendarNextbutton;

    @FindBy(xpath = "(//*[@class=\"mat-calendar-body-cell-content\"])[2]")
    private WebElement RWD_endDateCalender;

    // @FindBy(xpath = "//input[@id=\"search-title-name\"]")
    @FindBy(xpath = "//*[@class=\"title-subtext\"]")
    private WebElement RWD_AddTitleHeaderText;

    @FindBy(xpath = "(//*[@class=\"searchlist\"])[1]")
    private WebElement RWD_SearchResultsList;

    @FindBy(xpath = "(//*[contains(text(),\"more_vert\")])[1]")
    private WebElement RWD_moreBookButton;

    @FindBy(xpath = ".//*[text()[normalize-space() = \"Include in a New Program\"]]")
    private WebElement RWD_addToProgramButton;

    @FindBy(xpath = "//*[contains(text(),\"Save\")]")
    private WebElement RWD_saveProgramButton;

    @FindBy(xpath = "(//*[contains(text(),\"Publish Program\")])")
    private WebElement RWD_publishProgramButton;

    @FindBy(xpath = "//*[@formcontrolname=\"startDate\"]")
    private WebElement RWD_startDateSelectCalender;

    @FindBy(xpath = "//*[@formcontrolname=\"endDate\"]")
    private WebElement RWD_endDateSelectCalender;

    @FindBy (xpath = "//*[contains(text(),\"Reading Program\")]")
    private WebElement RWD_readingProgramHeader;

    @FindBy(xpath = "//h5[contains(text(),'Reading Program')]")
    private WebElement RWD_readingProgramType;

    @FindBy(xpath = "//div/h1")
    private WebElement RWD_readingProgramName;

    @FindBy(xpath = "(//div/p)[4]")
    private WebElement RWD_programDescription;

    @FindBy(xpath = "//div/a[contains(text(),'EDIT PROGRAM ')]")
    private WebElement RWD_editProgramOption;

    @FindBy(xpath = "//div/a[contains(text(),'DUPLICATE PROGRAM ')]")
    private WebElement RWD_duplicateProgramOption;

    @FindBy(xpath = "//div/a[contains(text(),'UNPUBLISH ')]")
    private WebElement RWD_unPublishOption;

    @FindBy(xpath = "//span[@class='createon']")
    private WebElement RWD_programCreatedDate;

    @FindBy(xpath = "//span[@class='createinfo']")
    private WebElement RWD_programInfo;

    @FindBy(xpath = "//div/h4[contains(text(),'START DATE')]")
    private WebElement RWD_startDateText;

    @FindBy(xpath = "//div/h4[contains(text(),'END DATE')]")
    private WebElement RWD_endDateText;

    @FindBy(xpath = "//div/h4[contains(text(),'STATUS')]")
    private WebElement RWD_statusText;

    @FindBy(xpath = "//div/h4[contains(text(),'PROGRAM TYPE')]")
    private WebElement RWD_programTypeText;

    @FindBy(xpath = "//div/h4[contains(text(),'VISIBILITY')]")
    private WebElement RWD_visibilityText;

    @FindBy(xpath="//div/h4[contains(text(),'REMINDERS')]")
    private WebElement RWD_remindersText;

    @FindBy(xpath="(//div/p)[5]")
    private WebElement RWD_startDate;

    @FindBy(xpath="(//div/p)[6]")
    private WebElement RWD_endDate;

    @FindBy(xpath="(//div/p)[7]")
    private WebElement RWD_status;

    @FindBy(xpath="(//div/p)[8]")
    private WebElement RWD_visibility;

    @FindBy(xpath="(//div/p)[9]")
    private WebElement RWD_programType;

    @FindBy(xpath="(//div/p)[10]")
    private WebElement RWD_reminder;

    @FindBy(xpath="(//div/h2)[3]")
    private WebElement RWD_readingListText;

    @FindBy(xpath="//mat-icon[contains(text(),'more_vert')]")
    private List<WebElement> RWD_titleMoreIcon;

    @FindBy(xpath="//button/span[@class='ui-carousel-next-icon pi pi-chevron-right']")
    private WebElement RWD_carouselRight;

    @FindBy(xpath="//button/span[@class='ui-carousel-prev-icon pi pi-chevron-left']")
    private WebElement RWD_carouselLeft;

    @FindBy(xpath = "//*[contains(text(),\"Book Club\")]")
    private WebElement RWD_bookClubTab;

    @FindBy(xpath = "//*[@class=\"mat-card-title header-title txt-elipsishead\"]")
    private List<WebElement> RWD_programHeaderValidation;

    @FindBy(xpath = "//*[@class=\"dd-desc-txt txt-elipsis\"]")
    private List<WebElement> RWD_ProgramDescriptionValidation;

    @FindBy(xpath = "//*[contains(text(),\"DELETE PROGRAM\")]")
    private WebElement RWD_deleteProgramButton;

//    @FindBy(xpath = "//*[@aria-label=\"close\"]")
//    private WebElement RWD_deleteProgramButton;

    @FindBy(xpath = "(//*[text()='close'])[1]")
    private WebElement RWD_closeButton;

    @FindBy(xpath = "//div[contains(text(),\"sure you want\")]")
    public WebElement RWD_DiscardConfirmationText;

    @FindBy(xpath = "//*[text()='Ok']")
    public WebElement RWD_okButton;

    @FindBy(xpath = "//*[text()='Cancel']")
    public WebElement RWD_CancelButton;

    @FindBy(xpath = "//*[@class=\"readchallenge\"]/h1")
    private WebElement RWD_programNameDetailsPage;

    @FindBy(xpath = "//*[contains(text(),\" info_outline \")]")
    private WebElement RWD_publicTooltipButton;

    @FindBy(xpath = "//*[contains(text(),\"Select Private to only\")]")
    private WebElement RWD_publicTooltipText;

    @FindBy(xpath = "//*[@id=\"cp-nofbook-optn\"]")
    public List<WebElement> RWD_numberOfBookDropDown;

    @FindBy(xpath = "//*[text()=\"DRAFT PROGRAMS \"]")
    public WebElement RWD_draftProgram;

    @FindBy(xpath = "//*[text()=\"ACTIVE PROGRAMS \"]")
    public WebElement RWD_activeProgram;

    @FindBy(xpath = "//*[@class=\"mat-card-header-text\"]/mat-card-title")
    public List<WebElement> RWD_ListofProgramsDisplayed;

    @FindBy(xpath = "//*[@aria-label=\"Ongoing programs\"]")
    private WebElement RWD_OngoingPrograms;

    @FindBy(id = "join-prg-btn")
    private WebElement RWD_join_Prg;

    @FindBy(id = "join-nothks-btn")
    private WebElement RWD_join_Nothanks;

    @FindBy(xpath = "//*[@aria-label=\"upcoming programs\"]")
    private WebElement RWD_UpcomingPrograms;

    @FindBy(className = "challenge-name")
    public WebElement RWD_challenge_name;

    @FindBy(id = "op-progname")
    public WebElement RWD_programName;

    @FindBy(id = "dd-card-ongoing")
    private WebElement RWD_card_ongoing;

    @FindBy(xpath = "//*[@class=\"mat-card-title header-title txt-elipsishead\"]")
    private List<WebElement> RWDActiveProgramname_list;

    @FindBy(xpath = "(//*[@id=\"invite-prog-close\"]/span/mat-icon")
    private WebElement RWD_AddStudent_back;

    @FindBy(xpath = "(//*[@aria-label=\"close\"])[6]")
    private WebElement RWD_removeStudent;

    @FindBy(xpath = "(//*[@class=\"inv-user-name\"])")
    private List<WebElement> RWD_invitedStudent;

    @FindBy(xpath = "(//*[@class=\'student-title\'])")
    private List<WebElement>  RWD_inviteStudentNameList;

    @FindBy(xpath = "(//*[@class=\'student-invite\'])")
    private List<WebElement> RWD_inviteButtonList;

    @FindBy(xpath = "(//*[@class=\'mat-card mat-focus-indicator poster ng-star-inserted\'])[1]")
    private WebElement RWD_titleSearchResults;

    @FindBy(xpath = "(//*[@class=\'success\']")
    private WebElement RWD_titileSuccess;

    @FindBy(xpath = "(//*[@class=\'error\'])")
    private WebElement RWD_inviteError;

    @FindBy(xpath = "//*[text()=\"Find titles and add them to your Program \"]")
    private WebElement RWD_addTitlePageHeaderText;

    @FindBy(xpath = "//*[text()=\"Search Students\"]")
    private WebElement RWD_searchStudentPageHeader;

    @FindBy(xpath = "//*[text()=\"Invite Students\"]")
    private WebElement RWD_inviteStudentSubHeader;

    @FindBy(id = "srslt-sortby-filtr-btn")
    private WebElement RWD_filterButton;

    @FindBy(xpath = "//*[@class=\"dialog-title\"]/h2")
    private WebElement RWD_filterPageHeader;

    @FindBy(xpath = "//*[@class=\"mat-radio-label-content\"]")
    private List<WebElement> RWD_radioButtonFilter;

    @FindBy(xpath = "//*[text()=\" CLEAR ALL \"]")
    private WebElement RWD_clearFilterButton;

    @FindBy(id = "sort-filter-view-reslts")
    private WebElement RWD_viewResultsButton;

    @FindBy(xpath = "//*[text()=\"Set Start Date\"]")
    private WebElement RWD_startDateFieldText;

    @FindBy(xpath = "//*[text()=\"Set End Date\"]")
    private WebElement RWD_endDateFieldText;

    @FindBy(xpath = "//*[@role=\"gridcell\"]")
    public List<WebElement> RWD_selectStartDate;

    @FindBy(id = "cp-addbk-close")
    public List<WebElement> RWD_removeTitle;

    @FindBy(xpath = "//*[ text()=\" Are you sure you want to\"]")
    private WebElement RWD_removeConfirmationMessage;

    @FindBy(id = "cp-addtilt-remv1")
    private WebElement RWD_confirmationRemoveButton;

    @FindBy(id = "CP Cancel books")
    private WebElement RWD_confirmationCancelButton;

    @FindBy(xpath = "//div[contains(@class,\"dd-book-card\")][2]/parent::div/div[3]//*[contains(@class,\"mat-card-title\")]")
    public List<WebElement> RWD_draftProgramList;

    @FindBy(xpath = "//div[contains(@class,\"dd-book-card\")][1]/parent::div/div[2]//*[contains(@class,\"mat-card-title\")]")
    public List<WebElement> RWD_activeProgramList;

    @FindBy(xpath = "//*[text()=\"CLOSED CHALLENGES \"]")
    public WebElement RWD_closedProgram;

    @FindBy(xpath = "//div[contains(@class,\"dd-book-card\")][1]/parent::div/div[4]//*[contains(@class,\"mat-card-title\")]")
    public List<WebElement> RWD_closedProgramList;

    @FindBy(xpath = "//div[contains(@class,\"dd-book-card\")][2]/parent::div/div[2]//*[contains(@class,\"mat-card-title\")]")
    public List<WebElement> RWD_ActiveChallengeList;

    @FindBy(id = "edit-challenge")
    public WebElement RWD_editChallengeButton;

    @FindBy(id = "leave-chalge")
    public WebElement RWD_leaveChallengeButton;

    @FindBy(id = "UpdateSave")
    public WebElement RWD_SaveChallange;

    @FindBy(id = "join-prg-btn")
    public WebElement RWD_joinProgramButton;

    @FindBy(id = "join-nothks-btn")
    public WebElement RWD_noThanksButton;

    @FindBy(xpath = "//*[@class='card-title']")
    public WebElement RWD_welComeMessageProgram;

    @FindBy(id = "join-goto-btn")
    public WebElement RWD_goToProgramButton;

    @FindBy(id = "join-prg-share")
    public  WebElement RWD_shareProgramButton;

    @FindBy(xpath = "(//*[contains(@id,\"mat-expansion-panel-header\")])[1]")
    public WebElement RWD_expandFilter;

    @FindBy(id = "chlg-enter-name")
    public WebElement RWD_EnterChallengeName;

    @FindBy(id = "chalg-desc")
    public WebElement RWD_ChallengeDescription;

    public WebElement getRWD_confirmationCancelButton(){return RWD_confirmationCancelButton;}

    public WebElement getRWD_confirmationRemoveButton(){return RWD_confirmationRemoveButton;}

    public WebElement getRWD_removeConfirmationMessage(){return RWD_removeConfirmationMessage;}

    public WebElement getRWD_startDateFieldText(){return RWD_startDateFieldText;}

    public WebElement getRWD_endDateFieldText(){return RWD_endDateFieldText;}

    public WebElement getRWD_viewResultsButton(){return RWD_viewResultsButton;}

    public WebElement getRWD_clearFilterButton(){return RWD_clearFilterButton;}

    public List<WebElement> getRWD_radioButtonFilter(){return  RWD_radioButtonFilter;}

    public WebElement getRWD_filterPageHeader(){return RWD_filterPageHeader;}

    public WebElement getRWD_filterButton(){return RWD_filterButton;}

    public WebElement getRWD_inviteStudentSubHeader(){return RWD_inviteStudentSubHeader;}

    public WebElement getRWD_searchStudentPageHeader(){return RWD_searchStudentPageHeader;}

    private WebElement getRWD_addTitlePageHeaderText(){return RWD_addTitlePageHeaderText;}

    public WebElement getRWD_publicTooltipButton(){ return RWD_publicTooltipButton;}

    public WebElement getRWD_RWD_publicTooltipText(){return RWD_publicTooltipText;}

    public WebElement getRWD_programNameDetailsPage(){return RWD_programNameDetailsPage;}

    public WebElement getRWD_closeButton(){
        return RWD_closeButton;
    }

    public WebElement getRWD_deleteProgramButton(){
        return RWD_deleteProgramButton;
    }

    public WebElement getRWD_openPrograms(){return RWD_openPrograms;}

    public WebElement getRWD_myProgramTab() {
        return RWD_myProgramTab;
    }

    public WebElement getRWD_CreateProgramLink() {
        return RWD_CreateProgramLink;
    }

    public WebElement getRWD_createReadingProgramHeader() {
        return RWD_createReadingProgramHeader;
    }

    public WebElement getRWD_enterProgramName() {
        return RWD_enterProgramName;
    }

    public WebElement getRWD_Description() {
        return RWD_Description;
    }

    public WebElement getRWD_privateRadiobutton() {
        return RWD_privateRadiobutton;
    }

    public WebElement getRWD_publicRadiobutton() {
        return RWD_publicRadiobutton;
    }

    public WebElement getRWD_selectProgramType(){return RWD_selectProgramType;}

    public WebElement getRWD_setReminder() {
        return RWD_setReminder;
    }
    public WebElement getRWD_setDailyreminder(){return RWD_setDailyreminder;}

    public WebElement getRWD_addStudentsButton() { return RWD_addStudentsButton; }

    public WebElement getRWD_searchFriendsHeaderText() {
        return RWD_searchFriendsHeaderText;
    }

    public WebElement getRWD_searchStudentName() { return RWD_searchStudentName; }

    public WebElement getRWD_inviteButton() {
        return RWD_inviteButton;
    }

    public WebElement getRWD_inviteToProgramButton() {
        return RWD_inviteToProgramButton;
    }


    public WebElement getRWD_addTitleButton() {
        return RWD_addTitleButton;
    }

    public WebElement getRWD_searchTitleAuthorBox() {
        return RWD_searchTitleAuthorBox;
    }

    public WebElement getRWD_startDateCalenderIcon(){return RWD_startDateCalendarIcon;}

    public WebElement getRWD_startSateCalender() { return RWD_startDateCalender;}

    public WebElement getRWD_endDateCalendarIcon(){return RWD_endDateCalendarIcon;}

    public WebElement getRWD_Nextbutton(){ return RWD_CalendarNextbutton;}

    public WebElement RWD_endDateCalender() { return RWD_endDateCalender;
    }

    public WebElement getRWD_AddTitleHeaderText() { return RWD_AddTitleHeaderText; }

    public WebElement getRWD_SearchResultsList() {
        return RWD_SearchResultsList;
    }

    public WebElement getRWD_moreBookButton() {
        return RWD_moreBookButton;
    }

    public WebElement getRWD_addToProgramButton() { return RWD_addToProgramButton; }

    public WebElement getRWD_saveProgramButton() {
        return RWD_saveProgramButton;
    }

    public WebElement getRWD_publishProgramButton() {
        return RWD_publishProgramButton;
    }

    public WebElement getRWD_startDateSelectCalender() {
        return RWD_startDateSelectCalender;
    }

    public WebElement getRWD_endDateSelectCalender() {
        return RWD_endDateSelectCalender;
    }

    public WebElement getRWD_readingProgramHeader() {
        return RWD_readingProgramHeader;
    }

    public WebElement getRWD_readingProgramType() {
        return RWD_readingProgramType;
    }

    public WebElement getRWD_readingProgramName() {
        return RWD_readingProgramName;
    }

    public WebElement getRWD_programDescription() {
        return RWD_programDescription;
    }

    public WebElement getRWD_editProgramOption() {
        return RWD_editProgramOption;
    }

    public WebElement getRWD_duplicateProgramOption() {
        return RWD_duplicateProgramOption;
    }

    public WebElement getRWD_unPublishOption() { return RWD_unPublishOption; }

    public WebElement getRWD_programCreatedDate() {
        return RWD_programCreatedDate;
    }

    public WebElement getRWD_programInfo() {
        return RWD_programInfo;
    }

    public WebElement getRWD_startDateText() {
        return RWD_startDateText;
    }

    public WebElement getRWD_endDateText() {
        return RWD_endDateText;
    }

    public WebElement getRWD_statusText() {
        return RWD_statusText;
    }

    public WebElement getRWD_programTypeText() {
        return RWD_programTypeText;
    }

    public WebElement getRWD_visibilityText() {
        return RWD_visibilityText;
    }

    public WebElement getRWD_remindersText() {
        return RWD_remindersText;
    }

    public WebElement getRWD_startDate() {
        return RWD_startDate;
    }

    public WebElement getRWD_endDate() {
        return RWD_endDate;
    }

    public WebElement getRWD_status() {
        return RWD_status;
    }

    public WebElement getRWD_visibility() {
        return RWD_visibility;
    }

    public WebElement getRWD_programType() {
        return RWD_programType;
    }

    public WebElement getRWD_reminder() { return RWD_reminder; }

    public WebElement getRWD_readingListText() {
        return RWD_readingListText;
    }

    public List<WebElement> getRWD_titleMoreIcon() {
        return RWD_titleMoreIcon;
    }

    public WebElement getRWD_carouselRight() {
        return RWD_carouselRight;
    }

    public WebElement getRWD_carouselLeft() {
        return RWD_carouselLeft;
    }

    public WebElement getRWD_bookClubTab() {
        return RWD_bookClubTab;
    }

    public List<WebElement> getRWD_programHeaderValidation() {
        return RWD_programHeaderValidation;
    }

    public List<WebElement> getRWD_ProgramDescriptionValidation() {
        return RWD_ProgramDescriptionValidation;
    }

    public void landingScreen(){
        Assert.assertTrue(RWD_homePage.isEnabled());
    }

    public WebElement getRWD_OngoingPrograms(){ return RWD_OngoingPrograms; }

    public WebElement getRWD_programName(){ return RWD_programName; }

    public WebElement getRWD_UpcomingPrograms(){ return RWD_UpcomingPrograms; }

    public WebElement getRWD_card_ongoing(){ return RWD_card_ongoing; }

    public WebElement getRWD_challenge_name(){ return RWD_challenge_name; }

    public WebElement getRWD_join_Prg(){ return RWD_join_Prg; }

    public WebElement getRWD_join_Nothanks(){ return RWD_join_Nothanks; }

    public List<WebElement> getRWDActiveProgramname_list() {
        return RWDActiveProgramname_list;
    }

    public List<WebElement> getRWD_inviteButtonList() {
        return RWD_inviteButtonList;
    }

    //new
    public List<WebElement> getRWD_invitedStudent() {
        return RWD_invitedStudent;
    }

    //new
    public List<WebElement>  getRWD_inviteStudentNameList() {
        return RWD_inviteStudentNameList;
    }

//    public WebElement getRWD_inviteToProgramButton() {
//        return RWD_inviteToProgramButton;
//    }

    //new
    public WebElement getRWD_removeStudent() {
        return RWD_removeStudent;
    }

    //new
    public WebElement getRWD_AddStudent_back() {
        return RWD_AddStudent_back;
    }

    public WebElement getRWD_titileSuccess() {
        return RWD_titileSuccess;
    }

    public WebElement getRWD_inviteError() {
        return RWD_inviteError;
    }



    public void program_AddTitle(String AddTitle){
        waitForElementClick(getRWD_addTitleButton());
        logger.info("User clicked on the Add title button");
        waitForPageLoaded(getRWD_addTitlePageHeaderText());
        Assert.assertTrue(getRWD_addTitlePageHeaderText().isDisplayed());
        SendKeysOnWebElement(getRWD_searchTitleAuthorBox(),AddTitle);
        logger.info("user entered the ProgramName");
        waitForElementClick(getRWD_SearchResultsList());
        waitForPageLoaded(getRWD_moreBookButton());
        waitForElementClick(getRWD_moreBookButton());
        waitForElementClick(getRWD_addToProgramButton());
        logger.info("titles are added to the program");
    }

    public void addStudents() {
        javascriptScroll(getRWD_addTitleButton());
        waitForElementClick(getRWD_addStudentsButton());
        logger.info("user clicked on the add title in the create form");
        List<WebElement> inviteStudent = getRWD_inviteButtonList();
        if(inviteStudent.size()>=1) {
            inviteStudent.get(0).click();
        }
        Assert.assertTrue(getRWD_inviteStudentSubHeader().isDisplayed());
        Assert.assertTrue(getRWD_searchStudentPageHeader().isDisplayed());
        waitForElementClick(getRWD_inviteToProgramButton());
        logger.info("user clicked on the invite button");
    }

    public String searchAndAddStudents(String StudentName) {
        waitForElementClick(getRWD_addStudentsButton());
        logger.info("user clicked on the add student in the create form");
        SendKeysOnWebElement(getRWD_searchStudentName(),StudentName);
        logger.info("entered the search results in search box");
        ClickOnWebElement(getRWD_inviteButtonList().get(0));
        logger.info("selected the first student");
        waitForElementClick(getRWD_inviteToProgramButton());
        logger.info("user clicked on the invite button");
        return StudentName;
    }

    public void AddMultipleStudents(String StudentName, int noOfStudents) throws Exception {
        List<WebElement> inviteStudent = getRWD_inviteButtonList();
        List<WebElement> StudentsName = getRWD_inviteStudentNameList();
        List<WebElement> addedStudents =  getRWD_invitedStudent();
        waitForPageLoaded(getRWD_addTitleButton());
        logger.info("User clicked on the add title button in the create form");
        javascriptScroll(getRWD_addTitleButton());
        logger.info("user is scrolled to add student button");
        waitForElementClick(getRWD_addStudentsButton());
        logger.info("user is selected students");
        //SendKeysOnWebElement(getRWD_searchStudentName(),StudentName);
        int studentsListCount = StudentsName.size();
        System.out.println(studentsListCount);
        if (studentsListCount> 0) {
            for(int i=0;i<studentsListCount;i++) {
                javascriptScroll(inviteStudent.get(i));
                logger.info("selecting multiple students");
                WebElement inviteNext = inviteStudent.get(i);
                waitForElementClick(inviteNext);
                String S = StudentsName.get(i).getText();
                logger.info("Added Student: " + S +" "+i);
            }
        } else {
            System.out.println("No recent search results for Students");
        }
        waitForPageLoaded(inviteStudent.get(0));
        javascriptScroll(inviteStudent.get(0));
        if(inviteStudent.size()==addedStudents.size()) {
            Assert.assertTrue("All Students from Search list are added  ",true);
        }
        else {
            Assert.assertFalse("all invitees from Search list are not added ",true);
        }
        waitFor(4000);
        waitForElementClick(getRWD_inviteToProgramButton());

    }


    public boolean removeStudents() {
        waitForElementClick(getRWD_removeStudent());
        logger.info("user is on students page");
        Boolean IsElementPresent = getRWD_invitedStudent().get(0).isEnabled();
        if (IsElementPresent) {
            waitForElementClick(getRWD_removeStudent());
            logger.info("user removed the student which is already added");
        }
        else {
            waitForElementClick(getRWD_AddStudent_back());
            logger.info("if students are not added and system should allow to add student to program");
        }
        return IsElementPresent;
    }

    public String add_titile(String TitleName) {
        waitForElementClick(getRWD_addTitleButton());
        logger.info("user clicked on the add title");
        waitForElementClick(getRWD_AddTitleHeaderText());
        logger.info("verified the title of page");
        SendKeysOnWebElement(getRWD_AddTitleHeaderText(), TitleName);
        waitForElementClick(getRWD_SearchResultsList());
        waitForElementClick(getRWD_moreBookButton());
        waitForElementClick(getRWD_addToProgramButton());
        String Title = getRWD_SearchResultsList().getText();
        if(getRWD_titileSuccess().isDisplayed()) {
            logger.info("Title added Successfully" + Title);
        }
        else {
            logger.info("Title is not added to the new program. Please check! ");
        }
        return TitleName;
    }

    public void addMultipleTitile(String TitleName, int noOfTitle ) {
        waitForElementClick(getRWD_addTitleButton());
        logger.info("user is on all title page");
        waitForElementClick(getRWD_AddTitleHeaderText());
        SendKeysOnWebElement(getRWD_AddTitleHeaderText(), TitleName);
        logger.info("user enters the title name");
        waitForElementClick(getRWD_SearchResultsList());
        waitForElementClick(getRWD_moreBookButton());
        logger.info("user click on more icon to select the books");
        waitForElementClick(getRWD_addToProgramButton());
        logger.info("user clicked on the include in a new program");
        if(getRWD_inviteError().isDisplayed()) {
            for(int i =1;i<noOfTitle;i++) {
                System.out.println("Added Student: " + TitleName + i);
                WebElement RWD_addNext = driver.findElementByXPath("(//*[@class=\'mat-card mat-focus-indicator poster ng-star-inserted\'])["+i+"]");
                waitForElementClick(RWD_addNext);
            }
            ClickOnWebElement(getRWD_inviteButtonList().get(0));
        }
        else {
            waitForElementClick(getRWD_inviteToProgramButton());
        }
        waitForElementClick(getRWD_inviteToProgramButton());
        return;
    }

    public void addAndRemoveFilterOption(String filterOption) {
        logger.info("---User Click on Filter Option-------");
        waitForElementClick(getRWD_filterButton());
        Assert.assertTrue(getRWD_filterPageHeader().isDisplayed());
        WebElement filterOptions=driver.findElementByXPath("//*[@class=\"mat-radio-label-content\" and contains(text(),'"+filterOption+"')]");
        logger.info("User selected the filter option");
    }

    public void setReminderType(String reminder){
        javascriptScroll(getRWD_setReminder());
        waitForElementClick(getRWD_setReminder());
        logger.info("User is set reminder page");
        WebElement setReminder= driver.findElementByXPath("//div[@id='cp-setrem-sel-panel']/mat-option/span[contains(text(),'"+reminder+"')]");
        logger.info("User has selected reminder type");
        waitForElementClick(setReminder);
        logger.info("Reminder is selected");
    }

    public void SetStartAndEndDate(String startDate, String EndDate) throws Exception {
        javascriptScroll(getRWD_startDateCalenderIcon());
        Assert.assertTrue(getRWD_startDateFieldText().isDisplayed());
        Assert.assertTrue(getRWD_endDateFieldText().isDisplayed());
        logger.info("Start and end date fields are assertion is done");
        waitForElementClick(getRWD_startDateCalenderIcon());
        logger.info("Use is clicked on the Start date clicked");
        if(RWD_selectStartDate.size()>0)
            for (int i=0; i<RWD_selectStartDate.size();i++){
                if (startDate.equalsIgnoreCase(RWD_selectStartDate.get(i).getText())){
                    waitForElementClick(RWD_selectStartDate.get(i));
                    logger.info("Use is selected Start Date");
                }
                else {
                    throw new Exception("No program results for user is not displayed");
                }
            }
        waitForElementClick(getRWD_endDateCalendarIcon());
        logger.info("Use is clicked on the End date clicked");
        waitForElementClick(getRWD_Nextbutton());
        logger.info("user is on to date selection calender page");
        if(RWD_selectStartDate.size()>0)
            for (int i=0; i<RWD_selectStartDate.size();i++){
                if (EndDate.equalsIgnoreCase(RWD_selectStartDate.get(i).getText())){
                    waitForElementClick(RWD_selectStartDate.get(i));
                    logger.info("Use is selected end Date");
                }
                else {
                    throw new Exception("No program results for user is not displayed");
                }
            }
    }

    public void removeTitle() throws Exception {
        logger.info("user is on Create or edit program page");
        int titleSize=RWD_removeTitle.size();
        if(titleSize>0){
            for (int i=0; i<titleSize;i++){
                waitForElementClick(RWD_removeTitle.get(i));
                logger.info("program title are remover based on the index");
                Assert.assertTrue(getRWD_removeConfirmationMessage().isDisplayed());
                Assert.assertTrue(getRWD_confirmationCancelButton().isDisplayed());
                logger.info("system displayed the confirmation message");
                waitForElementClick(getRWD_confirmationRemoveButton());
            }
        }
        else {
            throw new Exception("No program results for user is not displayed");
        }
    }

    public void setStartDate(String startDate) throws Exception {
        javascriptScroll(getRWD_startDateCalenderIcon());
        Assert.assertTrue(getRWD_startDateFieldText().isDisplayed());
        logger.info("user is on set date page");
        waitForElementClick(getRWD_startDateCalenderIcon());
        if(RWD_selectStartDate.size()>0)
            for (int i=0; i<RWD_selectStartDate.size();i++){
                if (startDate.equalsIgnoreCase(RWD_selectStartDate.get(i).getText())){
                    logger.info("User searching for Date");
                    waitForElementClick(RWD_selectStartDate.get(i));
                    logger.info("user has selected start date and returns to create challenge");
                }
                else {
                    throw new Exception("No program results for user is not displayed");
                }
            }
    }
    String ChallengeName = "Automation_Challenge_" + RandomStringUtils.randomAlphanumeric(5);
    String ChallengeDescription = "Automation_Challenge_Description" + RandomStringUtils.randomAlphanumeric(25);
    public void editChallenge(String ChallengeName){
        int challengeSize=RWD_ActiveChallengeList.size();
        if(challengeSize>0){
            for (int i=0;i<challengeSize;i++){
                if(ChallengeName.equalsIgnoreCase(RWD_ActiveChallengeList.get(i).getText())){
                    logger.info("program is selected navigated to details screen");
                    waitForElementClick(RWD_ActiveChallengeList.get(i));
                    waitForElementClick(RWD_editChallengeButton);
                    logger.info("User navigated to details screen");
                    RWD_EnterChallengeName.sendKeys(Keys.ENTER,Keys.CLEAR);
                    logger.info("clear the challenge name");
                    SendKeysOnWebElement(RWD_EnterChallengeName, ChallengeName);
                    RWD_ChallengeDescription.clear();
                    SendKeysOnWebElement(RWD_ChallengeDescription, ChallengeDescription);
                    logger.info("enter the challenge name");
                    waitForElementClick(RWD_SaveChallange);
                }
            }
        }

    }

    public void leaveChallengeMethod(String ChanllengeName){
        int challengeSize=RWD_ActiveChallengeList.size();
        if(challengeSize>0){
            for (int i=0;i<challengeSize;i++){
                if(ChallengeName.equalsIgnoreCase(RWD_ActiveChallengeList.get(i).getText())){
                    logger.info("program is selected navigated to details screen");
                    waitForElementClick(RWD_ActiveChallengeList.get(i));
                    logger.info("User navigated to details screen");
                    waitForElementClick(RWD_leaveChallengeButton);
                    logger.info("user clicked on leave challenge button in details screen");
                }
            }
        }
    }
}
