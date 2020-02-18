package com.iat.ePoints.Navigations.MyAccount;

import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.MyAccount.FacebookPageLocators;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 09.12.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class InviteFriendsNavigation extends AbstractPage {

    private MyProfileLocators locators_myprofile = new MyProfileLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private FacebookPageLocators locators_facebook = new FacebookPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();
    private JDBC jdbc = new JDBC("points_manager");
    String env = envVariables.baseUrl;

    public InviteFriendsNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void goToInviteFriendsPage() {
        executor.click(locators_myprofile.inviteFriend);
    }

    public void checkContentOfInviteFriendsPage() {
        assertTrue("Post to own timeline button is no visible", executor.is_element_present(locators_myprofile.postToYourTimeline));
        assertTrue("Send an invite to (search) is no visible", executor.is_element_present(locators_myprofile.sendAndInviteTo));
        assertTrue("Any friend avatar is no visible", executor.is_element_present(locators_myprofile.basicPictureLocator));
        assertTrue("Get 50 points... is no visible", executor.is_element_present(locators_myprofile.get50PointsInformation));
    }

    // REFER A FRIEND - access control, epoints and Facebook v1 (RD-668) ///////////////////////////////////////////////
    public void checkIfLoginButtonIsInvisible() {
        assertFalse("login popup is visible but should not", executor.is_element_present(locators_myprofile.inviteFriendForceFbLoginButton));
    }

    public void checkIfLoginButtonIsVisible() {
        assertTrue("Login popup is invisible", executor.is_element_present(locators_myprofile.inviteFriendForceFbLoginButton));
    }

    // 3 REFER A FRIEND - option to add link on user wall (Rd-669) /////////////////////////////////////////////////////
    String postedMessageContent = Integer.toString(executor.return_random_value(1000000000));

    public void clickPostToYourTimelineButton() throws InterruptedException {
        executor.click(locators_myprofile.postToYourTimeline);
        Thread.sleep(2000);// has to stay
    }

    public void checkRefererIdAndShareLink() throws InterruptedException {
        executor.return_driver().switchTo().frame(executor.get_element(locators_myprofile.facebookIframe));
        executor.send_keys(locators_myprofile.saySomethingTextArea, postedMessageContent);
        Thread.sleep(2000);//has to stay
        //assertEquals("Referer Id is incorrect in the link", executor.getText(locators_myprofile.linkWithRefererIdv1) + executor.getText(locators_myprofile.linkWithRefererIdv2), "join?referrer=" + jdbc.return_proper_db_value("SELECT userId FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", "iat.epoints.test@gmail.com"), 1) + "' AND userIdType='UUID'", 1) + "&accessType");
        executor.click(locators_myprofile.shareButton);
    }

    public void openFacebbokPage() {
        executor.return_driver().get("https://www.facebook.com/");
    }

    public void loginIntoFacebook(String email, String password) throws InterruptedException {
        if (executor.is_element_present(locators_facebook.SettingsButton)) {
            executor.click(locators_facebook.SettingsButton);
            executor.click(locators_facebook.SignOutButton);
        }
        Thread.sleep(2000);
        executor.send_keys(locators_facebook.EmailTextField, email);
        executor.send_keys(locators_facebook.PasswordTextField, password);
        executor.click(locators_facebook.LogInButton);
    }

    public void goToUserWallAndValidateLinkContentAndIfItWorksCorrectly(String email) {
        String fbLinkForQA = env + "/join?referrer=" + jdbc.return_proper_db_value("SELECT userId FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", email), 1) + "' AND userIdType='UUID'", 1) + "&accessType=facebook";
        executor.click(locators_facebook.HomeButton);
        assertEquals("Message contents are different", executor.getText(locators_facebook.MessageContent), postedMessageContent);
        //assertEquals("Links are different", executor.getText(locators_facebook.MessageLink), fbLinkForQA);
        executor.return_driver().get(fbLinkForQA);
        assertTrue("Link does not work", executor.return_driver().getTitle().equals("Join epoints | epoints"));
    }

    // 5 REFER A FRIEND - record friend access against referer record (RD-672) /////////////////////////////////////////
    public void followRefererLink(String refererEmail) {
        String fbLinkForQA = env + "/join?referrer=" + jdbc.return_proper_db_value("SELECT userId FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", refererEmail), 1) + "' AND userIdType='UUID'", 1) + "&accessType=facebook";
        System.out.println(jdbc.return_proper_db_value("SELECT userId FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", refererEmail), 1) + "' AND userIdType='UUID'", 1));
        executor.return_driver().get(fbLinkForQA);
    }

    public void checkIfRefererIdAndHowAccessedAreCorrectlyStored(String refererEmail, String friendEmail) throws InterruptedException {
        Thread.sleep(2000);//has to stay
        assertEquals("refererId is incorrect", jdbc.return_proper_db_value("Select userKey FROM points_manager.User WHERE email='" + refererEmail + "'", 1), jdbc.return_proper_db_value("Select refererId FROM points_manager.User WHERE email='" + friendEmail + "'", 1));
        assertEquals("accessType is incorrect", "facebook", jdbc.return_proper_db_value("Select accessType FROM points_manager.User WHERE email='" + friendEmail + "'", 1));
    }

    // REFER A FRIEND - Award points on friend completing registration (RD-673) & REFER A FRIEND - add friend identifier to http session (RD-712)
    String refererPointsNumber;

    public void navigateToDifferentPages() {
        executor.click(locators_header.spendPoints);
        executor.click(locators_header.aboutEpoints);
        executor.click(locators_header.JoinNow);
    }

    public void storeRefererPointsNumber(String refererEmail) {
        refererPointsNumber = jdbc.return_proper_db_value("Select confirmed FROM points_manager.User WHERE email='iat.epoints.test@gmail.com'", 1);
    }

    public void checkIfRefererRecieved50points(String refererEmail) {
        assertEquals("Referer get different number of points than 50", Integer.parseInt(refererPointsNumber) + 50, Integer.parseInt(jdbc.return_proper_db_value("Select confirmed FROM points_manager.User WHERE email='iat.epoints.test@gmail.com'", 1)));
    }

    // REFER A FRIEND - option to add link on friends wall (RD-670) ////////////////////////////////////////////////////\
    public void searchProperFriend(String friendName) {
        executor.send_keys(locators_myprofile.sendAndInviteTo, friendName);
    }

    public void postLinkOnfriendWall() throws InterruptedException {
        executor.click(locators_myprofile.basicFriendAvatarLocatorNoMemeber);
        executor.return_driver().switchTo().frame(executor.get_element(locators_myprofile.facebookIframe));
        executor.send_keys(locators_myprofile.saySomethingTextArea, postedMessageContent);
        Thread.sleep(2000);//has to stay
        //assertEquals("Referer Id is incorrect in the link", executor.getText(locators_myprofile.linkWithRefererIdv1) + executor.getText(locators_myprofile.linkWithRefererIdv2), "join?referrer=" + jdbc.return_proper_db_value("SELECT userId FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", "iat.epoints.test@gmail.com"), 1) + "' AND userIdType='UUID'", 1) + "&accessType");
        executor.click(locators_myprofile.shareButton);
        executor.handleMultipleWindows("My epoints");
    }

    public void checkIfLinkIsvisibleOnFriendWallAndItWorks(String refererEmail) {
        String fbLink = env + "/join?referrer=" + jdbc.return_proper_db_value("SELECT userId FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", refererEmail), 1) + "' AND userIdType='UUID'", 1) + "&accessType=facebook";
        executor.click(locators_facebook.HomeButtonForPLFB);
        assertEquals("Message contents are different", executor.getText(locators_facebook.MessageContentForPLFB), postedMessageContent);
        //assertEquals("Links are different", executor.getText(locators_facebook.MessageLink).replace("https","http"), fbLink.replace("https","http"));
        executor.return_driver().get(fbLink);
        assertTrue("Link does not work", executor.return_driver().getTitle().equals("Join epoints | epoints"));
    }

    // REFER A FRIEND - disable friends who are members when adding to friends wall (RD-671) ///////////////////////////
    public void tryToPostLinkOnEPMemberWall() {
        executor.click(locators_myprofile.basicFriendAvatarLocatorMemeber);
        assertFalse("Fb post window is visible but should not", executor.handleMultipleWindows("Post Story to"));
    }

    public void close() throws SQLException {
        jdbc.close();
    }

}
