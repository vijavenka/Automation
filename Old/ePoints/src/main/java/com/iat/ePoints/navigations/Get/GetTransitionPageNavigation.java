package com.iat.ePoints.Navigations.Get;

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.*;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 31.03.14
 * Time: 09:19
 * To change this template use File | Settings | File Templates.
 */
public class GetTransitionPageNavigation extends AbstractPage{

    private GetLandingPageLocators locator_landingPage = new GetLandingPageLocators();
    private GetStoresAZPageLocators locator_shopsAZ = new GetStoresAZPageLocators();
    private GetRetailerPageLocators locator_retailer = new GetRetailerPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();
    private GetTransitionPageLocators locator_transition = new GetTransitionPageLocators();
    private GetShopPageLocators locators_shop = new GetShopPageLocators();

    String retailer_name;

    public GetTransitionPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //clickout functions
    public void clickInFirstRetailerFromList() throws InterruptedException {
    int random = executor.return_random_value(20);
    List<WebElement> retailers = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
    List<WebElement> linksToRetailersPages = executor.getWebElementsList(locator_shopsAZ.retailerCardRetailerPageButtonAlphabeticOnly);
    List<WebElement> retailersNames = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerNameLocator);
    executor.moveMouseToWebElement(retailers.get(random));

    retailer_name = retailersNames.get(random).getText();
    executor.click(linksToRetailersPages.get(random));
    //change introduced because of developing individual retailer page;
    executor.click(locator_retailer.goToRetailerButton);
    }


    //Checking if Sign transition page have all required options ///////////////////////////////////////////////////////
    public void checkIfTransitionModalIsVisible() throws InterruptedException {
        // to avoid opening full screen transition page what may happen
        executor.is_element_present(locator_transition.transitionModalWindow);
    }

    public void checkVisibilityOfJoinHeareLink() {
        assertTrue("Join here link is invisible", executor.is_element_present(locator_transition.joinHereLink));
    }


    public void checkVisibilityOfSignInButton() {
        assertTrue("Sign in button is invisible", executor.is_element_present(locator_transition.SignInBtn));
    }

    public void checkVisibilityOfSignInUsingFacebookButton() {
        assertTrue("Sign in using facebook button is invisible", executor.is_element_present(locator_transition.SignInUsingFacebookBtn));
    }

    public void checkIfLearnMoreButtonIsVisible(){
        executor.is_element_present(locator_transition.learnMoreButton);
    }

    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - page content
    public void openFullWindowTransitionPage(){
        executor.return_driver().get(envVariables.baseUrl + "/transition?url=%2fclickout%2fmerchant%3fid%3d377b166c-1a17-445b-b59c-0b90c128397c&name=John+Lewis&multiplier=3&referrerUrl=http%3a%2f%2fdev.epoints.com%3a8911%2fretailer%2fjohn-lewis%2f377b166c-1a17-445b-b59c-0b90c128397c");
        retailer_name = "John Lewis";
    }

    public void checkIsAuthorizationPanelShown() throws InterruptedException {
        Thread.sleep(1000);
        executor.handleMultipleWindows("Sending to retailer | epoints");
        assertEquals("Page title is incorrect", executor.return_driver().getTitle(), "Sending to retailer | epoints");
    }

    // TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2731)
    public void clickSignInButton() throws InterruptedException {
        executor.click(locator_transition.SignInBtn);
    }

    public void enterEmailInTransitionPage(String Email) {
        executor.send_keys(locator_transition.EmailField, Email);
    }

    public void enterPasswordInTransiotionPage(String Password) throws InterruptedException {
        executor.send_keys(locator_transition.PasswordField, Password);
    }

    String CurrentDate;
    public void setCurrentDate() throws InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        CurrentDate = simpleDateFormat.format(new Date());
    }

    public void checkIfTransitionPageIsInvisible() throws InterruptedException {
        //wait some time for transition page
        Thread.sleep(10000);
        Set<String> windows = executor.return_driver().getWindowHandles();
        boolean isTransitionAvaliable = false;
        for (String window : windows) {
            if (executor.return_driver().getTitle().contains("Sending to retailer")) {
                isTransitionAvaliable = true;
            }
        }
        assertFalse("Retailer page was no opened", isTransitionAvaliable);
    }

    public void checkIfClickoutWasReported(String ifSigned, String Email) throws InterruptedException, SQLException {
        if(Email.equals("createdInNavClass")){
            Email = uniqueEmail;
        }
        JDBC jdbc = new JDBC("affiliate_manager");
        Thread.sleep(15000);
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), retailer_name);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailer_name + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "epoints");
        assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "ePoints.com");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailer_name + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailer_name + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }

    // TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2731) - wrong password
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - wrong password
    public void checkIfAuthorizationFailedAlertIsDisplayed() {
        assertTrue("Authorization alert should be visible", executor.is_element_present(locator_transition.authorizationFailedAlert));
    }

    // TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2731) - no login data
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - no login data
    public void checkIfEmailAndPasswordAlertsAreVisible() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue("No email alert is invisible", executor.is_element_present(locator_transition.noEmailRequired));
        assertTrue("No password alert is invisible", executor.is_element_present(locator_transition.noPasswordAlert));
    }

    // TRANSITION PAGE DESKTOP - new sign in option using FACEBOOK on module page (RD-2701).
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - facebook login
    public void clickSignInUsingFacebookButton(){
        executor.click(locator_transition.SignInUsingFacebookBtn);
    }

    // TRANSITION PAGE DESKTOP - new JOIN option on module page (RD-2711) - join correct
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - join correct
    public void clickJoinButton(){
        executor.click(locator_transition.joinHereLink);
    }

    String uniqueEmail;
    public void enterNewUniqueEmail() throws InterruptedException {
        uniqueEmail = Integer.toString(executor.return_random_value(1000000000))+"automatedtest@wp.pl";
        executor.send_keys(locator_transition.EmailFieldJoin, uniqueEmail);
    }

    public void checkIfSuccessAlertIsVisible(){
        executor.is_element_present(locator_transition.alertSuccess);
    }

    public void checkIfUserEmailWasStoredAsNotVerified() throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        assertTrue("Email is not stored as verified or not stored at all", jdbc.return_proper_db_value("SELECT verified FROM points_manager.User WHERE email = '"+uniqueEmail+"'",1).equals("0"));
        jdbc.close();
    }

    public void checkIfGoToRetailerButtonIsAvailable(){
        assertTrue("Continue to retailer button is no available", executor.is_element_present(locator_transition.continueToRetailerButton));
    }

    public void clickGoToRetailerButton(){
        executor.click(locator_transition.continueToRetailerButton);
    }

    // TRANSITION PAGE DESKTOP - new JOIN option on module page (RD-2711) - email taken
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - email taken
    public void enterTakenEmail() {
        executor.send_keys(locator_transition.EmailFieldJoin, "emailwybitnietestowy@gmail.com");
    }

    public void checkIfWarningAlertIsVisible(){
        assertTrue("Warning alert is invisible", executor.is_element_present(locator_transition.alertWarning));
    }

    // TRANSITION PAGE DESKTOP - new FORGOTTEN PASSWORD option on module page (RD-2741) - correct email
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - correct email
    public void clickForgotPasswordButton(){
        executor.click(locator_transition.ForgotPswdLink);
    }

    public void enterEmailForWhichPasswordHasToBeRetrieved(String validEmail){
        executor.send_keys(locator_transition.EmailFieldForgotPassweord, validEmail);
    }

    public void clickSendButton(){
        executor.click(locator_transition.SendButton);
    }

    // TRANSITION PAGE DESKTOP - new FORGOTTEN PASSWORD option on module page (RD-2741) - incorrect email, no email
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - incorrect email, no email
    public void checkIfEmailInvalidIsVisible(){
        assertTrue("No email alert is invisible", executor.is_element_present(locator_transition.noEmailInvalid));
    }

    public void clearEmailFieldForForgotPasswordModule(){
        executor.clear(locator_transition.EmailFieldForgotPassweord);
    }

    public void checkIfEmailRequiredIsVisible(){
        assertTrue("No email alert is invisible", executor.is_element_present(locator_transition.noEmailRequired));
    }

    // AFFILIATE MANAGER - cross channel sales tracking logic (RD-2837).
    String p1;
    String p2;
    String p3;
    String p4;
    public void openTransitionPageWithAdditionalPParameterts() throws InterruptedException {
        p1 = Integer.toString(executor.return_random_value(1000000));
        p2 = Integer.toString(executor.return_random_value(1000000));
        p3 = Integer.toString(executor.return_random_value(1000000));
        p4 = Integer.toString(executor.return_random_value(1000000));
        List<WebElement> productsHrefs = executor.getWebElementsList(locators_shop.basicProductBuyButtonHref);
        int random = executor.return_random_value(productsHrefs.size()-1);
        String basePageUrl = executor.getAttribute(productsHrefs.get(random),"href");
        executor.return_driver().get(basePageUrl+"&p1="+p1+"%21%23%24%26%27"+p1+"&p2="+p2+"%28%29%2A%2B%2C"+p2+"&p3="+p3+"%2F%3A%3B%3D"+p3+"&p4="+p4+"%3F%40%5B%5D"+p4);
        retailer_name = executor.getText(locator_transition.retailerNameOnTransitionPage);
    }

    public void checkIfPParametersWereCorrectlyStoredInDB() throws SQLException {
        JDBC jdbc = new JDBC("affiliate_manager");
        assertEquals("Parameter p1 is incorrect", p1+"!#$&'"+p1, jdbc.return_proper_db_value("SELECT p1Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        assertEquals("Parameter p2 is incorrect", p2+"()*+,"+p2, jdbc.return_proper_db_value("SELECT p2Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        assertEquals("Parameter p3 is incorrect", p3+"/:;="+p3, jdbc.return_proper_db_value("SELECT p3Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        assertEquals("Parameter p4 is incorrect", p4+"?@[]"+p4, jdbc.return_proper_db_value("SELECT p4Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        jdbc.close();
    }
}
