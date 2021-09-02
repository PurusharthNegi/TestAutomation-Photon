package com.pom_RWD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.CapabilitiesAndWebDriverUtils;

import java.util.List;

public class RWD_CreateaChallengeSearchTitleResultsListView extends CapabilitiesAndWebDriverUtils {
	
	public RWD_CreateaChallengeSearchTitleResultsListView() {

		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(id = "search-title-name")
	private WebElement RWD_SearchTitleBox;
	
	
	@FindBy(xpath = " //*[@class='searchlist'][1]")
	private WebElement RWD_HarryPotter;
	
	@FindBy(xpath = "//mat-icon[@class='mat-icon notranslate mat-menu-trigger more-vert bluroption material-icons mat-icon-no-color'][1] ")
	private WebElement RWDMoreIcon;
	
	@FindBy(xpath = "//button[@role='menuitem'][1]")
	private WebElement RWDAddToChallenge;
	
	@FindBy(xpath = "//*[@class='addedBooks ng-star-inserted'][1]")
	private WebElement RWDAddedBookinCCPage;

	@FindBy(id="chlg-add-title")
	private  WebElement RWD_AddTitleCTA;

	@FindBy(xpath=".//*[@class=\"recentsearch\" and contains(.,\"RECENT SEARCHES\")]")
	private  WebElement RWD_recentSearchSuggestiontitle;

	@FindBy(xpath=".//*[@class=\"recentsearch\" and contains(.,\"SUGGESTED RESULT\")]")
	private WebElement RWD_SearchSuggestion;

	@FindBy(id ="search-recent-items")
	private  List<WebElement> RWD_recentSuggestionlists;

	@FindBy(id="search-sug-list")
	private List<WebElement> RWD_searchSuggestionList;

	@FindBy(xpath = ".//*[@class=\"no-titles ng-star-inserted\"]")
	private  WebElement noSearchSuggestionMessage;

	@FindBy(id="sch-rslt-title")
	private  WebElement RWDsearchSuggestionlistingscreenTitle;

	@FindBy(id="srslt-showbook-click")
	private  List<WebElement> RWDsearchSuggestionlistingBooks;

	@FindBy(xpath = ".//*[@class=\"mat-card mat-focus-indicator poster ng-star-inserted\"]")
	private  List<WebElement> RWD_SearchSuggBooksListing;

	@FindBy(id="search-title-close")
	private  WebElement RWD_searchsuggestionscreen_closeicon;

	@FindBy(id="srslt-sortby-filtr-btn")
	private  WebElement RWD_searchSuggestionoverlay_filtericon;

	@FindBy(xpath = ".//*[@class=\"dialog-title\"]//*[contains(.,'Sort & Filter')]")
	private  WebElement RWD_sortFiltersection;

	@FindBy(xpath = ".//*[@class=\"mat-radio-label-content\"]")
	private  List<WebElement> RWD_sortingRadioOptions;

	@FindBy(xpath = ".//*[contains(@id,\"mat-expansion-panel-header\")]")
	private  List<WebElement> RWD_FilterAccordion;

	@FindBy(xpath = ".//*[@class=\"mat-card mat-focus-indicator poster ng-star-inserted\"]/img")
	private  List<WebElement> RWDserachSuggestionListingTitleImage;

	@FindBy(xpath = ".//*[@class=\"mat-card mat-focus-indicator poster ng-star-inserted\"]//*[@class=\"bookimg\"]")
	private  List<WebElement> RWDserachSuggestionListingBooksMaterials;

	@FindBy(xpath = ".//*[@class=\"mat-card mat-focus-indicator poster ng-star-inserted\"]//*[contains(@class,\"mat-icon\")]")
	private  List<WebElement> RWDserachSuggestionListingBooksMoreicon;


	public WebElement getRWD_SearchTitleBox() {
		return RWD_SearchTitleBox;
	}

	public WebElement getRWD_HarryPotter() {
		return RWD_HarryPotter;
	}

	public WebElement getRWDMoreIcon() {
		return RWDMoreIcon;
	}

	public WebElement getRWDAddToChallenge() {
		return RWDAddToChallenge;
	}

	public WebElement getRWDAddedBookinCCPage() {
		return RWDAddedBookinCCPage;
	}

	public  WebElement getRWD_AddTitleCTA(){ return  RWD_AddTitleCTA;}

	public  WebElement getRWD_SearchSuggestion() { return  RWD_SearchSuggestion; }

	public List<WebElement> getRWD_searchSuggestionList() { return  RWD_searchSuggestionList; }

	public  List<WebElement> getRWD_recentSuggestionlists() { return RWD_recentSuggestionlists; }

	public WebElement getnoSearchSuggestionMessage() { return  noSearchSuggestionMessage; }

	public  WebElement getRWD_recentSearchSuggestiontitle() { return  RWD_recentSearchSuggestiontitle;}

	public  WebElement getRWDsearchSuggestionlistingscreenTitle() { return  RWDsearchSuggestionlistingscreenTitle; }

	public  List<WebElement> getRWDsearchSuggestionlistingBooks() { return RWDsearchSuggestionlistingBooks; }

	public  WebElement getRWD_searchsuggestionscreen_closeicon() { return  RWD_searchsuggestionscreen_closeicon; }

	public  WebElement getRWD_searchSuggestionoverlay_filtericon() { return  RWD_searchSuggestionoverlay_filtericon; }

	public  WebElement getRWD_sortFiltersection() { return  RWD_sortFiltersection; }

	public  List<WebElement> getRWD_sortingRadioOptions() { return RWD_sortingRadioOptions; }

	public  List<WebElement> getRWD_FilterAccordion() { return  RWD_FilterAccordion; }

	public  List<WebElement> getRWDserachSuggestionListingTitleImage() { return  RWDserachSuggestionListingTitleImage; }

	public  List<WebElement> getRWDserachSuggestionListingBooksMaterials() { return  RWDserachSuggestionListingBooksMaterials; }

	public  List<WebElement> getRWDserachSuggestionListingBooksMoreicon() { return  RWDserachSuggestionListingBooksMoreicon; }

	public  List<WebElement> getRWD_SearchSuggBooksListing() {return RWD_SearchSuggBooksListing; }



	
}
