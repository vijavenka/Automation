package com.iat.ePoints.Steps.MyAccount;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Registration.RegistrationNavigation;
import com.iat.ePoints.Navigations.SignIn.LogInNavigation;
import com.iat.ePoints.Navigations.MyAccount.InviteFriendsNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 09.12.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class InviteFriendSteps {

    private IExecutor executor;
    private InviteFriendsNavigation inviteFriendNavi;
    private LogInNavigation loginNavi;
    private RegistrationNavigation regNavi;

    public InviteFriendSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        inviteFriendNavi = new InviteFriendsNavigation(executor);
        loginNavi = new LogInNavigation(executor);
        regNavi = new RegistrationNavigation(executor);
    }

    @After
    public void tear_down() throws SQLException {
        inviteFriendNavi.close();
        executor.stop();
    }

    // 1 REFER A FRIEND - update epoints,com for new interface (RD-666) ////////////////////////////////////////////////
    @Then("^Go to invite friends page$")
    public void Go_to_invite_friends_page() throws Throwable {
        inviteFriendNavi.goToInviteFriendsPage();
    }

    @Then("^Check content of invite friends page$")
    public void Check_content_of_invite_friends_page() throws Throwable {
        inviteFriendNavi.checkContentOfInviteFriendsPage();
    }

    // 2 REFER A FRIEND - access control, epoints and Facebook v1 (RD-668) /////////////////////////////////////////////
    @Then("^All invite friend options should be available for user logged via facebook$")
    public void All_invite_friend_options_should_be_available_for_user_logged_via_facebook() throws Throwable {
        inviteFriendNavi.checkIfLoginButtonIsInvisible();
    }

    @Then("^User should login via facebook to use invite friend options$")
    public void User_should_login_via_facebook_to_use_invite_friend_options() throws Throwable {
        go_sleep(3000);
        inviteFriendNavi.checkIfLoginButtonIsVisible();
    }

    // 3 REFER A FRIEND - option to add link on user wall (RD-669) /////////////////////////////////////////////////////
    @Then("^Post Link on own wall$")
    public void Post_Link_on_own_wall() throws Throwable {
        inviteFriendNavi.clickPostToYourTimelineButton();
        inviteFriendNavi.checkRefererIdAndShareLink();
    }

    @Then("^Check if link was posted and it works '(.+)' '(.+)'$")
    public void Check_if_link_was_posted_and_it_works(String email, String password) throws Throwable {
        inviteFriendNavi.openFacebbokPage();
        //inviteFriendNavi.loginIntoFacebbok(email, password);
        inviteFriendNavi.goToUserWallAndValidateLinkContentAndIfItWorksCorrectly(email);
    }

    // 5 REFER A FRIEND - record friend access against referer record (RD-672) /////////////////////////////////////////
    @Given("^User followed fb referer '(.+)' link$")
    public void User_followed_fb_referer_iat_epoints_test_gmail_com_link(String refererEmail) throws Throwable {
        inviteFriendNavi.followRefererLink(refererEmail);
    }

    @Then("^He '(.+)' joined epoints$")
    public void He_joined_epoints(String freindEmail) throws Throwable {
        regNavi.enterStringToEmailField(freindEmail);
        regNavi.clickSignUp();
    }

    @Then("^RefererID, how accessed '(.+)' are correctly stored in friend '(.+)' data in db$")
    public void RefererID_how_accessed_are_correctly_stored_in_friends_data_in_db(String refererEmail, String friendEmail) throws Throwable {
        inviteFriendNavi.checkIfRefererIdAndHowAccessedAreCorrectlyStored(refererEmail, friendEmail);
    }

    // REFER A FRIEND - Award points on friend completing registration (RD-673) & REFER A FRIEND - add friend identifier to http session (RD-712)
    @Given("^He navigated through different pages to get information about ePoints$")
    public void He_navigated_through_different_pages_to_get_information_about_ePoints() throws Throwable {
        inviteFriendNavi.navigateToDifferentPages();
    }

    @Given("^Referer '(.+)' knows how many points he has$")
    public void Referer_knows_how_many_points_he_has(String refererEmail) throws Throwable {
        inviteFriendNavi.storeRefererPointsNumber(refererEmail);
    }

    @Then("^Referer '(.+)' should received another fifty points$")
    public void Referer_should_received_another_fifteen_points(String refererEmail) throws Throwable {
        // Express the Regexp above with the code you wish you had
        inviteFriendNavi.checkIfRefererRecieved50points(refererEmail);
    }

    // REFER A FRIEND - option to add link on friends wall (RD-670) ////////////////////////////////////////////////////
    @Then("^Use search to find proper friend '(.+)'$")
    public void Use_search_to_find_proper_friend(String friendName) throws Throwable {
        inviteFriendNavi.searchProperFriend(friendName);
    }

    @Then("^Post Link on friend wall$")
    public void Post_Link_on_friend_wall() throws Throwable {
        inviteFriendNavi.postLinkOnfriendWall();
    }

    @Then("^Check if link was posted on friend wall and it works '(.+)' '(.+)' '(.+)'$")
    public void Check_if_link_was_posted_on_friend_wall_and_it_works_iat_epoints_test_gmail_com_everest_(String friendFbEmail, String friendFbPassword, String refererEmail) throws Throwable {
        inviteFriendNavi.openFacebbokPage();
        inviteFriendNavi.loginIntoFacebook(friendFbEmail, friendFbPassword);
        inviteFriendNavi.checkIfLinkIsvisibleOnFriendWallAndItWorks(refererEmail);
    }

    // REFER A FRIEND - disable friends who are members when adding to friends wall (RD-671) ///////////////////////////
    @Then("^Check If user can not add link on friend wall who i already ePoints member$")
    public void Check_If_user_can_not_add_link_on_friend_wall_who_i_already_ePoints_member() throws Throwable {
        inviteFriendNavi.tryToPostLinkOnEPMemberWall();
    }
}
