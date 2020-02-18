package com.iat.adminportal.locators.email_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 14.03.14
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class EmailManagerLocators {

    // main page view
    public Locator emailManagerPageTitle = new Locator(LocatorType.XPATH, "//h1");
    public Locator numberOfRows = new Locator(LocatorType.XPATH, " //select[@name='emailsTable_length']//option[@selected='selected']");
    public Locator numberOfElements = new Locator(LocatorType.ID, "emailsTable_info");

    // columns
    public Locator nameColumn = new Locator(LocatorType.XPATH, "//th[@class='name sorting_asc']");
    public Locator scheduleColumn = new Locator(LocatorType.XPATH, "//th[@class='sorting_disabled schedule']");
    public Locator createdDateColumn = new Locator(LocatorType.XPATH, "//th[@class='createdDate sorting']");
    public Locator modifiedDateColumn = new Locator(LocatorType.XPATH, "//th[@class='modifiedDate sorting']");
    public Locator viewColumn = new Locator(LocatorType.XPATH, "//th[@class='sorting_disabled view']");
    public Locator activeColumn = new Locator(LocatorType.XPATH, "//th[@class='sorting_disabled active']");
    public Locator copyColumn = new Locator(LocatorType.XPATH, "//th[@class='sorting_disabled copy']");
    public Locator editColumn = new Locator(LocatorType.XPATH, "//th[@class='sorting_disabled edit']");

    public Locator showAllDDLMainPage = new Locator(LocatorType.ID, "searchcolumn");    // Options values: '', 'name;
    public Locator searchFieldMainPage = new Locator(LocatorType.ID, "searchkeyword");
    public Locator searchButtonMainPage = new Locator(LocatorType.ID, "searchbtn");
    public Locator createNewButtonMainPage = new Locator(LocatorType.ID, "emailAdd");
    public Locator showXEntriesDDLMainPage = new Locator(LocatorType.XPATH, "//select[@name='emailsTable_length']");    // Options: '25','50','75','100'
    public Locator showingTextInformationMainPage = new Locator(LocatorType.XPATH, "emailsTable_info");
    public Locator firstMainPage = new Locator(LocatorType.ID, "emailsTable_first");
    public Locator previousMainPage = new Locator(LocatorType.ID, "emailsTable_previous");
    public Locator paginationActiveMainPage = new Locator(LocatorType.XPATH, "//a[@class='paginate_active']");
    public Locator paginationNotActiveBasicMainPage = new Locator(LocatorType.XPATH, "//a[@class='paginate_button']");
    public Locator nextMainPage = new Locator(LocatorType.ID, "emailsTable_next");
    public Locator lastMainPage = new Locator(LocatorType.ID, "emailsTable_last");
    public Locator nameBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='name sorting_1']//a[@class='emailView']");  // Also clickable edit
    public Locator nameRequired = new Locator(LocatorType.XPATH, "//label[@for='name']//span[@class='required']");
    public Locator scheduleBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='schedule']//a[@class='emailView']");  // Also clickable edit
    public Locator createdDateBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='createdDate']");
    public Locator modifiedDateBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='modifiedDate']");
    public Locator viewButtonBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='view']//a[@class='btn small emailViewList']");
    public Locator activePausedInformationMainPage = new Locator(LocatorType.XPATH, "//td[@class='active']//span");// sort to find and check
    public Locator pauseActivateButtonBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='active']//a");
    public Locator pauseActivateConfirmWindowMainPage = new Locator(LocatorType.ID, "wl_dialog");
    public Locator pauseActivateConfirmYesButtonMainPage = new Locator(LocatorType.XPATH, "//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']");
    public Locator copyButtonBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='copy']//a[@class='btn small emailCopy']");
    public Locator editButtonBasicMainPage = new Locator(LocatorType.XPATH, "//td[@class='edit']//a[@class='btn small emailEdit']");

    // create new view
    public Locator closedWindowCreateNew = new Locator(LocatorType.ID, "fancybox-close");
    public Locator nameCreateNew = new Locator(LocatorType.ID, "name");
    public Locator scheduleCreateNew = new Locator(LocatorType.ID, "schedule");
    public Locator addRuleButtonBasicCreateNew = new Locator(LocatorType.XPATH, "//button[@class='addrule']");
    public Locator saveButtonCreateNew = new Locator(LocatorType.XPATH, "//button[@class='submit']");
    public Locator cancelButtonCreateNew = new Locator(LocatorType.XPATH, "//button[contains(text(),'Cancel')]");
    // adding rules
    public Locator ruleTypeCreateNew = new Locator(LocatorType.XPATH, "//select[@class='ruleType']");  // Option values: 'CREATED_AT','CREATED_AT_DATE','LAST_LOGGED_IN','LAST_LOGGED_IN_DATE','GENDER','PROFILE_COMPLETED','ACCOUNT_STATUS','REGISTRATION_SITE'
    public Locator accountCreatedAtCreateNew = new Locator(LocatorType.XPATH, "//div[@class='ruleValueRegion']//select");
    public Locator creationTimeIntervalFromCreateNew = new Locator(LocatorType.XPATH, "//div[@class='ruleTimeInterval']//label//input");  //Options values: 'WEEK','MONTH','QUARTER'
    public Locator creationTimeIntervalToCreateNew = new Locator(LocatorType.XPATH, "//div[@class='ruleTimeInterval']//label[2]//input");
    public Locator lastLoggedInCreateNew = new Locator(LocatorType.XPATH, "//div[@class='ruleTimePeriod']//select");   //Options values: 'WEEK','MONTH','QUARTER'
    //public Locator lastLoginIntervalFromCreateNew = new Locator(LocatorType.XPATH, "");
    //public Locator lastLoginIntervalToCreateNew = new Locator(LocatorType.XPATH, "");
    public Locator genderCreateNew = new Locator(LocatorType.XPATH, "//div[@class='ruleGender']//select");  // Option values: 'MALE','FEMALE','EMPTY'
    public Locator profileCompletedCreateNew = new Locator(LocatorType.XPATH, "//div[@class='ruleProfileStatus']//select"); // Option values: 'CONTACT','PERSONAL','BOTH','NONE'
    public Locator accountStstusCreateNew = new Locator(LocatorType.XPATH, "//div[@class='ruleAccountStatus']//select");     // Option values: 'VERIFIED','UNVERIFIED'
    public Locator registrationSite = new Locator(LocatorType.XPATH, "//a[@class='select2-choice select2-default']//span");
    public Locator registrationSiteAfterChoce = new Locator(LocatorType.XPATH, "//a[@class='select2-choice']");
    public Locator registrationSiteChoice = new Locator(LocatorType.XPATH, "//li[@class='select2-results-dept-0 select2-result select2-result-selectable']//div[@class='select2-result-label']");
    public Locator deleteButtonCreateNew = new Locator(LocatorType.XPATH, "//button[@class='removerule']");
// copy view

    // view view ;)
    public Locator emailsPreviewProcessing = new Locator(LocatorType.XPATH, "//div[@class='dataTables_processing'");
    public Locator emailBasicEmaiView = new Locator(LocatorType.XPATH, "//table[@class='dataTableRegion dataTable']//tr//td[3]");
    public Locator emailsNumberPerPage = new Locator(LocatorType.XPATH, "//select[@name='DataTables_Table_0_length']");
    public Locator closedViewListWindow = new Locator(LocatorType.XPATH, "//div[@id='fancybox']//button[@class='cancel']");

// edit view
}
