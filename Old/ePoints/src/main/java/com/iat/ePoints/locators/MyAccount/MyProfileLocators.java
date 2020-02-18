package com.iat.ePoints.Locators.MyAccount;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */


public class MyProfileLocators implements IPageLocators {

    public Locator myAccountSection = new Locator(LocatorType.XPATH, "//div[@id='myAccountSubMenu']");

    public Locator accountDetailsSection = new Locator(LocatorType.XPATH, "//div[@id='accountDetails']");
    public Locator personalDetailsSection = new Locator(LocatorType.XPATH, "//div[@id='personalDetails']");
    public Locator contactDetailsSection = new Locator(LocatorType.XPATH, "//div[@id='contactDetails']");

    //for tabs titles have to be listed
    public Locator basicTabsPageTitle = new Locator(LocatorType.XPATH, "//h1[@class='page-title']");
//My epoints
    public Locator myEpoints = new Locator(LocatorType.XPATH, "//a[text()='My epoints']");
    public Locator userinfoPanel = new Locator(LocatorType.XPATH, "//div[@id='userInfoPanel']");
    public Locator epointsTableName = new Locator(LocatorType.XPATH, "//div[@class='name']");
    public Locator totalEpointsToDateTable = new Locator(LocatorType.XPATH, "//td[text()='Total epoints to date:']");
    public Locator rewardsClaimedTable = new Locator(LocatorType.XPATH, "//td[text()='Rewards claimed:']");
    public Locator epointsTable = new Locator(LocatorType.XPATH, "//a[@class='link']");
    //0 - total, 1 - rewards, 2 - epoints alreadty
    public Locator basicMyEpointsTablePointsLocator = new Locator(LocatorType.XPATH, "//td[@class='points']");
    public Locator recentActivityTable = new Locator(LocatorType.XPATH, "//div[@id='recent-activity']");
    //table header
    public Locator recentActivityTableName = new Locator(LocatorType.XPATH, "//h3[text()='Recent activity']");
    public Locator allActivityLink = new Locator(LocatorType.XPATH, "//a[@class='inside-link link']");
    public Locator epEarned = new Locator(LocatorType.XPATH, "//th[text()='ep earned']");
    public Locator epSpent = new Locator(LocatorType.XPATH, "//th[text()='ep spent']");
    public Locator balance = new Locator(LocatorType.XPATH, "//th[text()='balance']");
    //table content
    public Locator basicActivityContent = new Locator(LocatorType.XPATH, "//tr//td[@class='date-cell']");
    public Locator basicpointsContentDiv2 = new Locator(LocatorType.XPATH, "//tr//td[@class='text-center points']");
    public Locator basicBalance = new Locator(LocatorType.XPATH, "//tr//td[@class='text-center points balance']");
    public Locator staticInfoTable = new Locator(LocatorType.XPATH, "//div[@id='staticInfo']");
    public Locator staticInfoTableName = new Locator(LocatorType.XPATH, "//h3[text()='Where to get epoints']");
    public Locator findYourFavouriteStoresButton = new Locator(LocatorType.XPATH, "//div[@id='staticInfo']//a[@class='btn btn-yellow']");
//My profile
    public Locator myProfile = new Locator(LocatorType.XPATH, "//li[@data-menutype='myDetails']//a");
    public Locator accountDetailsTableSectionTitle = new Locator(LocatorType.XPATH, "//div[@id='accountDetails']//h2"); //.getText() should be used to tak value
    public Locator userEmailAddress = new Locator(LocatorType.XPATH, "//div[@class='changeEmailBlock']//span[@id='userEmailVal']//strong"); //.getText() should be used to tak value
    public Locator userEmailAddressEditButton = new Locator(LocatorType.XPATH, "//a[@class='changeEmail pull-right btn btn-grey btn-small']");
    public Locator cancelNewEmailCreationButton = new Locator(LocatorType.XPATH, "//a[@class='pull-right btn btn-grey btn-small cancelChangeEmailForm']");
    public Locator newEmailAddressTextfield = new Locator(LocatorType.XPATH, "//input[@id='newEmailAddress']");
    public Locator repeatNewEmailAddressTextField = new Locator(LocatorType.XPATH, "//input[@id='reEnterNewEmailAddress']");
    //0 - Email address is invalid 1 - Emails do not match
    public Locator basicEmailSectionFieldsAlert = new Locator(LocatorType.XPATH, "//span[@class='error']");
    public Locator SaveNewEmailAddressButton = new Locator(LocatorType.XPATH, "//button[@class='btn btn-green saveEmail']");
    public Locator theSameNewEmailAlert = new Locator(LocatorType.XPATH, "//div[text()='New email has to differ from existing one, Email is already used by diffrent user']");
    public Locator changingEmailSuccessAlert = new Locator(LocatorType.XPATH, "//div[text()='Follow confirmation link in your email to verify it.']");

    public Locator userPassword = new Locator(LocatorType.XPATH, "//div[@class='changePassBlock']//div[@id='userPass']//strong");
    public Locator userPasswordEditButton = new Locator(LocatorType.XPATH, "//a[@class='btn btn-grey btn-small changePass pull-right']");
    public Locator userPasswordEditButtonGrey = new Locator(LocatorType.XPATH, "//a[@class='btn btn-grey btn-small changePass pull-right not-specified']");
    public Locator cancelPaswordChanging = new Locator(LocatorType.XPATH, "//a[@class='btn btn-grey btn-small pull-right cancelChangePasswordForm']");
    public Locator currentPasswordTwextField = new Locator(LocatorType.XPATH, "//input[@id='currentPassword']");
    public Locator newPasswordTwextField = new Locator(LocatorType.XPATH, "//input[@id='newPassword']");
    public Locator repeatNewPasswordTwextField = new Locator(LocatorType.XPATH, "//input[@id='confirmNewPassword']");
    //0 - Password is required 1 - Password is required 2 - Passwords do not match
    public Locator basicPasswordSectionFieldsAlert = new Locator(LocatorType.XPATH, "//span[@class='error']");
    public Locator saveNewPasswordButton = new Locator(LocatorType.XPATH, "//button[@data-form='changePasswordForm']");
    public Locator differentOldPasswordAlert = new Locator(LocatorType.XPATH, "//div[@class='alert alert-error']"); //.getText() should be used to tak value
    public Locator changingPasswordSuccessAlert = new Locator(LocatorType.XPATH, "//div[@class='alert alert-success']");

    public Locator personalDetailsTableSectionTitle = new Locator(LocatorType.XPATH, "//div[@id='personalDetails']//h2"); //.getText() should be used to tak value
    public Locator nameReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='personalDetails.firstName']");
    public Locator lastNameReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='personalDetails.lastName']");
    public Locator titleReadonlyAndDDL = new Locator(LocatorType.XPATH, "//select[@name='personalDetails.title']");
    public Locator genderReadonlyAndDDL = new Locator(LocatorType.XPATH, "//select[@name='personalDetails.gender']");
    public Locator dateOfBirthReadonlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='personalDetails.dob']");
    public Locator userPersonalDetailsEditbutton = new Locator(LocatorType.XPATH, "//a[@class='btn btn-grey btn-small editBtn pull-right']");
    // nameReadOnlyAndTextField
    // lastNameReadonlyAndTextField
    // titleReadonlyAndDDL
    // genderReadonlyAndDDL
    // dateOfBirthReadonlyAndTextField
    public Locator saveNewPersonalDataButton = new Locator(LocatorType.XPATH, "//button[@class='btn btn-green saveChanges']");
    public Locator cancelSavingNewPersonalDataButton = new Locator(LocatorType.XPATH, "//a[@class='cancel link']");
    public Locator changingPersonlaDataSuccessAlert = new Locator(LocatorType.XPATH, "//div[@class='alert alert-success']");

    public Locator contactDetailsTableSectionTitle = new Locator(LocatorType.XPATH, "//div[@id='contactDetails']//h2");
    public Locator contactNumberReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='contactDetails.phoneNo']");
    public Locator houseNumberReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='contactDetails.address.house']");
    public Locator streetNameReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='contactDetails.address.street']");
    public Locator cityNameReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='contactDetails.address.city']");
    public Locator countyNameReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='contactDetails.address.county']");
    public Locator countrynameReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='contactDetails.address.country']");
    public Locator postcodeReadOnlyAndTextField = new Locator(LocatorType.XPATH, "//input[@name='contactDetails.address.postCode']");
    public Locator userContactDetailsEditbutton = new Locator(LocatorType.XPATH, "//div[@id='contactDetails']//a[@class='btn btn-grey btn-small editBtn pull-right']");
    // contactNumberReadOnlyAndTextField
    // houseNumberReadOnlyAndTextField
    // streetNameReadOnlyAndTextField
    // cityNameReadOnlyAndTextField
    // countyNameReadOnlyAndTextField
    // countrynameReadOnlyAndTextField
    // postcodeReadOnlyAndTextField
    public Locator saveNewContactDataButton = new Locator(LocatorType.XPATH, "//div[@id='contactDetails']//button[@class='btn btn-green saveChanges']");
    public Locator cancelSavingNewContactButton = new Locator(LocatorType.XPATH, "//div[@id='contactDetails']//a[@class='cancel link']");
    public Locator changingContactDataSuccessAlert = new Locator(LocatorType.XPATH, "//div[@class='alert alert-success']");
//My activity
    public Locator currentBalanceTab = new Locator(LocatorType.XPATH, "//span[@data-type='CONFIRMED']");
    public Locator pendingTab = new Locator(LocatorType.XPATH, "//div[@class='results-info-wrapper results-types-bar']//span[@data-type='PENDING']");
    public Locator declinedTab = new Locator(LocatorType.XPATH, "//span[@data-type='DECLINED']");

    public Locator myActivity = new Locator(LocatorType.XPATH, "//a[text()='Activity']");
    public Locator activityTableHeadItemCurrentBalance = new Locator(LocatorType.XPATH, "//span[@class='active with-data']//span[2]");
    public Locator activityTableHeadItemPending = new Locator(LocatorType.XPATH, "//span[@class='with-data active']//span[2]");
    public Locator activityTableHeadItemDeclined = new Locator(LocatorType.XPATH, "//span[@class='active with-data']//span[2]");
    public Locator activityTableHeadItemsPerPage = new Locator(LocatorType.XPATH, "//div[@class='search-settings-wrapper']//div[@class='page-size-settings']");
    public Locator activityTableHeadItems20 = new Locator(LocatorType.XPATH, "//span[@data-pagesize='20']");
    public Locator activityTableHeadItems40 = new Locator(LocatorType.XPATH, "//span[@data-pagesize='40']");
    public Locator activityTableHeadItems100 = new Locator(LocatorType.XPATH, "//span[@data-pagesize='100']");
    public Locator activityTableHeadItemsSortBy = new Locator(LocatorType.XPATH, "//div[@class='sort-settings']");
    public Locator activityTableHeadItemsSortByDDL = new Locator(LocatorType.XPATH, "//select[@id='sort-transactions']");

    //0 - Date 1 - 2 -Activity 3 - Site 4 - In 5 - Out 6 - Balance
    //public Locator activityTableNames = new Locator(LocatorType.XPATH,"//th");
    public Locator return_activityTableNames_element(int number) {
        return new Locator(LocatorType.XPATH, "//th[" + number + "]");
    }

    //[row][column]
    //public Locator basicActivityTableContent = new Locator(LocatorType.XPATH,"/div[@id='transactions']//tr[x]//td[y]");
    public Locator basicRowLocator = new Locator(LocatorType.XPATH, "//tr");
    public Locator return_basicActivityTableContent_element(int x, int y) {
        return new Locator(LocatorType.XPATH, "//div[@id='transactions']//tr[" + x + "]//td[" + y + "]");
    }
//My rewards history
    //added products are first in table so i do not need return specific locator
    public Locator myRewardsHistory = new Locator(LocatorType.XPATH, "//a[text()='Rewards history']");
    public Locator basicDateTable = new Locator(LocatorType.XPATH, "//table[@class='table table-striped']//div[@class='date']");
    public Locator basicRewardPictureTable = new Locator(LocatorType.XPATH, "//table[@class='table table-striped']//div[@class='rewardThumb']");
    public Locator basicRewardDescriptionTable = new Locator(LocatorType.XPATH, "//table[@class='table table-striped']//td[@class='rewardInfoCell']//a");
    public Locator epointsUsed = new Locator(LocatorType.XPATH, "//table[@class='table table-striped']//td[@class='rewardInfoCell']//span[@class='sumPoints']");
    public Locator basicDeliveryDetailsTable = new Locator(LocatorType.XPATH, "//table[@class='table table-striped']//td[4]");
    public Locator basicContactUsTable = new Locator(LocatorType.XPATH, "//table[@class='table table-striped']//a[@class='link']");
    public Locator noRewardsInformation = new Locator(LocatorType.XPATH, "//p[@class='no-rewards']");
    public Locator basicTableRowLocator = new Locator(LocatorType.XPATH, "//table[@class='table table-striped']//tbody//tr");
//Invite friends
    public Locator inviteFriend = new Locator(LocatorType.XPATH, "//a[text()='Invite friends']");
    public Locator postToYourTimeline = new Locator(LocatorType.XPATH, "//span[contains(text(),'Post to your timeline')]");
    public Locator sendAndInviteTo = new Locator(LocatorType.XPATH, "//input[@id='searchFriends_name']");
    public Locator basicPictureLocator = new Locator(LocatorType.XPATH, "//div[@id='friends-list']");
    public Locator basicFriendAvatarLocatorNoMemeber = new Locator(LocatorType.XPATH, "//a[@class='facebook-feed facebook-friend']");
    public Locator basicFriendAvatarLocatorMemeber = new Locator(LocatorType.XPATH, "//a[@class='facebook-feed facebook-friend is-app-user']");
    public Locator get50PointsInformation = new Locator(LocatorType.XPATH, "//p[contains(text(),'and get 50 epoints for each of the first 10 of your friends who join this month')]");
    public Locator inviteFriendForceFbLoginButton = new Locator(LocatorType.XPATH, "//span[contains(text(),'Connect with facebook')]");
//Invite friend facebook application (important, used facebook account is PL)

    public Locator facebookIframe = new Locator(LocatorType.XPATH, "//div[@class='fb_dialog_content fb_dialog_iframe']//iframe");
    public Locator saySomethingTextArea = new Locator(LocatorType.ID, "feedform_user_message");
    public Locator linkWithRefererIdv1 = new Locator(LocatorType.XPATH, "//div[@class='uiAttachmentTitle']//span[3]"); //join?referrer=1b3e3d92-e3fb-414
    public Locator linkWithRefererIdv2 = new Locator(LocatorType.XPATH, "//div[@class='uiAttachmentTitle']//span[5]"); //d-a4db-ed7924330775&accessType
    public Locator shareButton = new Locator(LocatorType.XPATH, "//input[@name='publish']");
    public Locator cancelbutton = new Locator(LocatorType.XPATH, "//input[@name='cancel']");


}




















