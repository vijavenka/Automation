package com.iat.ePoints.Navigations.Get;

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetDailyDealsPageLocators;
import com.iat.ePoints.Locators.Get.GetLandingPageLocators;
import com.iat.ePoints.Locators.Get.GetTransitionPageLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 12.02.14
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class GetDailyDealsPageNavigation extends AbstractPage{

    private GetDailyDealsPageLocators locators_dealyDeal = new GetDailyDealsPageLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private GetLandingPageLocators locators_landingPage = new GetLandingPageLocators();
    private GetTransitionPageLocators locators_transition = new GetTransitionPageLocators();

    private EnvironmentVariables envVariables = new EnvironmentVariables();

    public GetDailyDealsPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // EPOINTS - add DAILY DEALS list interfaces to epoints.com (RD-1141) - check content of all daily deals
    public void openDailyDealsPage(boolean locationModalClosed) throws InterruptedException {
        executor.click(locators_header.getPoints);
        executor.click(locators_landingPage.dailyDealsSubmenu);
        Thread.sleep(4000);
        if(locationModalClosed && executor.is_element_present(locators_dealyDeal.locationModal)){
            executor.click(locators_dealyDeal.locationCloseModal);
        }
        Thread.sleep(1000);
    }

    public void forAllDailyDealsCheckElementsLikeExpiryDatePriceETC(){
        List<WebElement> dealContainer = executor.getWebElementsList(locators_dealyDeal.dealContainerDailyDealPage);
        List<WebElement> dealPicture = executor.getWebElementsList(locators_dealyDeal.dealImageDailyDealPage);
        List<WebElement> dealEpoints = executor.getWebElementsList(locators_dealyDeal.dealEpRewarnDailyDealPage);
        List<WebElement> dealDescription = executor.getWebElementsList(locators_dealyDeal.dealDescriptionDailyDealPage);
        List<WebElement> dealGetButton = executor.getWebElementsList(locators_dealyDeal.getDealButtonDailyDealPage);
        List<WebElement> dealExpiryDate = executor.getWebElementsList(locators_dealyDeal.dealExpiryDateDailyDealPage);
        List<WebElement> dealPrice = executor.getWebElementsList(locators_dealyDeal.dealPriceDailyDealPage);

        assertTrue("Daily deal elements number is improper" , dealContainer.size() == dealDescription.size() &&
                dealContainer.size() == dealEpoints.size() &&
                dealContainer.size() == dealExpiryDate.size() &&
                dealContainer.size() == dealGetButton.size() &&
                //dealContainer.size() == dealPicture.size() &&
                dealContainer.size() == dealPrice.size());
    }

    // EPOINTS - add DAILY DEALS list interfaces to epoints.com (RD-1141) - check possibility of buying daily deals and check reported clickout
    public void choseSomeDailyDealsAndClickGetThisDealButton() throws InterruptedException {
        List<WebElement> getThisDealButtons = executor.getWebElementsList(locators_dealyDeal.getDealButtonDailyDealPage);
        executor.click(getThisDealButtons.get(executor.return_random_value(getThisDealButtons.size())));
        Thread.sleep(1000);

        executor.handleMultipleWindows("Sending to retailer");
        rememberMerchantName();
    }

    String dailyDealsRetailerName;
    public void rememberMerchantName(){
        dailyDealsRetailerName = executor.getText(locators_transition.retailerNameOnTransitionPage);
    }

    /*public void clickContinueAnywayInTransitionPage(){
        executor.click(locators_transition.ContinueAnywayLink);
    } */

    public void checkIfClickoutWasReported(String ifSigned, String Email) throws InterruptedException, SQLException {
        // this function body is a copy of function from transition page navigation class, it is only a little bit changed
        Thread.sleep(10000);
        String CurrentDate = executor.returnCurrentDate();
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), dailyDealsRetailerName);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + dailyDealsRetailerName + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "epoints");
        assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "ePoints.com");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + dailyDealsRetailerName + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + dailyDealsRetailerName + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }

    // EPOINTS - add DAILY DEALS list interfaces to epoints.com (RD-1141) - epoints range, price, saving slider
    public void openPopupFacetsWindowUsingSeeAllButton() throws InterruptedException {
        executor.click(locators_dealyDeal.facetsSeeAllButton);
        Thread.sleep(2000);
    }

    int epointsEarnedLow;
    int epointsEarnedUp;
    int priceLow;
    int priceUp;
    int savingLow;
    int savingUp;
    public void setEpointsEarnedSliderRange() throws InterruptedException {
        executor.click(locators_dealyDeal.epointsEarnedSliderFacet);
        int low = (int) (Integer.parseInt(executor.getText(locators_dealyDeal.leftValueEpointsEarned))+0.2*Integer.parseInt(executor.getText(locators_dealyDeal.leftValueEpointsEarned)));
        int up = (int) (Integer.parseInt(executor.getText(locators_dealyDeal.rightValueEpointsEarned))-0.2*Integer.parseInt(executor.getText(locators_dealyDeal.rightValueEpointsEarned)));

        Actions action = new Actions(executor.return_driver());

        Thread.sleep(3000);
        while (Integer.parseInt(executor.getText(locators_dealyDeal.leftValueEpointsEarned)) < low) {
            action.dragAndDropBy(executor.get_element(locators_dealyDeal.leftSliderEpointsEarned), 20, 0).perform();
            Thread.sleep(3000);
        }
        Thread.sleep(3000);
        while (Integer.parseInt(executor.getText(locators_dealyDeal.rightValueEpointsEarned)) > up && Integer.parseInt(executor.getText(locators_dealyDeal.rightValueEpointsEarned)) - 0.1 * up > low) {
            action.dragAndDropBy(executor.get_element(locators_dealyDeal.rightSliderEpointsEarned), -20, 0).perform();
            Thread.sleep(3000);
        }

        epointsEarnedLow = Integer.parseInt(executor.getText(locators_dealyDeal.leftValueEpointsEarned));
        epointsEarnedUp = Integer.parseInt(executor.getText(locators_dealyDeal.rightValueEpointsEarned));
    }

    public void setPriceSliderRange() throws InterruptedException {
        executor.click(locators_dealyDeal.priceSliderFacet);
        int low = (int) (Integer.parseInt(executor.getText(locators_dealyDeal.leftValuePrice))+0.2*Integer.parseInt(executor.getText(locators_dealyDeal.leftValuePrice)));
        int up = (int) (Integer.parseInt(executor.getText(locators_dealyDeal.rightValuePrice))-0.2*Integer.parseInt(executor.getText(locators_dealyDeal.rightValuePrice)));

        Actions action = new Actions(executor.return_driver());

        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_dealyDeal.leftValuePrice)) < low) {
            action.dragAndDropBy(executor.get_element(locators_dealyDeal.leftSliderPrice), 20, 0).perform();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_dealyDeal.rightValuePrice)) > up && Integer.parseInt(executor.getText(locators_dealyDeal.rightValuePrice)) - 0.1 * up > low) {
            action.dragAndDropBy(executor.get_element(locators_dealyDeal.rightSliderPrice), -20, 0).perform();
            Thread.sleep(2000);
        }

        priceLow = Integer.parseInt(executor.getText(locators_dealyDeal.leftValuePrice));
        priceUp = Integer.parseInt(executor.getText(locators_dealyDeal.rightValuePrice));
    }

    public void setSavingSliderRange() throws InterruptedException {
        executor.click(locators_dealyDeal.savingSliderFacet);
        int low = 10;
        int up = (int) (Integer.parseInt(executor.getText(locators_dealyDeal.rightValueSaving))-0.05*Integer.parseInt(executor.getText(locators_dealyDeal.rightValueSaving)));

        Actions action = new Actions(executor.return_driver());

        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_dealyDeal.leftValueSaving)) < low) {
            action.dragAndDropBy(executor.get_element(locators_dealyDeal.leftSliderSaving), 20, 0).perform();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_dealyDeal.rightValueSaving)) > up && Integer.parseInt(executor.getText(locators_dealyDeal.rightValueSaving)) - 0.1 * up > low) {
            action.dragAndDropBy(executor.get_element(locators_dealyDeal.rightSliderSaving), -20, 0).perform();
            Thread.sleep(2000);
        }

        savingLow = Integer.parseInt(executor.getText(locators_dealyDeal.leftValueSaving));
        savingUp = Integer.parseInt(executor.getText(locators_dealyDeal.rightValueSaving));
    }

    public void clickDoneButtonOnFAcetsPopupWindow() throws InterruptedException {
        executor.click(locators_dealyDeal.facetsDoneButton);
        Thread.sleep(2000);
    }

    public void checkCorrectnesOfallFeaturesValue(){
        List<WebElement> epointsList = executor.getWebElementsList(locators_dealyDeal.dealEpRewarnDailyDealPage);
        List<WebElement> pricesList = executor.getWebElementsList(locators_dealyDeal.dealPriceDailyDealPage);
        List<WebElement> savingsList = executor.getWebElementsList(locators_dealyDeal.dealPercentageSavingDailyDealPage);

        for(int i = 0; i<epointsList.size(); i++){
            assertTrue("Epoints value is not in the range for range "+ epointsEarnedLow + " " + epointsEarnedUp + " value " + executor.getText(epointsList.get(i)), Integer.parseInt(executor.getText(epointsList.get(i)).replace("+","")) >= epointsEarnedLow && Integer.parseInt(executor.getText(epointsList.get(i)).replace("+","")) <= epointsEarnedUp);
            assertTrue("Price value is not in the range for range "+ priceLow + " " + priceUp + " value " + executor.getText(pricesList.get(i)), (int)Double.parseDouble(executor.getText(pricesList.get(i)).replace("£ ","")) >= priceLow && (int)Double.parseDouble(executor.getText(pricesList.get(i)).replace("£ ","")) <= priceUp);
            assertTrue("Saving value is not in the range for range "+ savingLow + " " + savingUp + " value " + executor.getText(savingsList.get(i)), Integer.parseInt(executor.getText(savingsList.get(i)).replace("% saving","")) >= savingLow && Integer.parseInt(executor.getText(savingsList.get(i)).replace("% saving","")) <= savingUp);
        }
    }

    // DAILY DEALS - ensure proper use of location filter on epoints (RD-1151) - check showing of modal windows with locations
    public void checkVisibilityOfLocationModalWindow(){
        assertTrue("Location window is no visible", executor.is_element_present(locators_dealyDeal.locationModal));
    }

    public void checkContentOfLocationModalWindow(){
        assertTrue("Location header is no visible", executor.is_element_present(locators_dealyDeal.locationHeaderModal));
        assertTrue("Location any option is no visible", executor.is_element_present(locators_dealyDeal.locationOptionModal));
        assertTrue("Location close button is no visible", executor.is_element_present(locators_dealyDeal.locationCloseModal));
    }

    // DAILY DEALS - ensure proper use of location filter on epoints (RD-1151) - check working of change location button
    public void closeLocationModalWindow(){
        if(executor.is_element_present(locators_dealyDeal.locationModal)){
            executor.click(locators_dealyDeal.locationCloseModal);
        }
    }

    public void changeLocationAndCheckCorrectnessOfSendCookies(){
        executor.click(locators_dealyDeal.changeLocationButton);
        List<WebElement> locationOptions = executor.getWebElementsList(locators_dealyDeal.locationOptionModal);
        int random = executor.return_random_value(locationOptions.size());
        while(executor.getText(locationOptions.get(random)).contains("/")){
            random = executor.return_random_value(locationOptions.size());
        }
        String chosenLocation = executor.getText(locationOptions.get(random));
        executor.click(locationOptions.get(random));
        // check cookie correctness and header correctness
        if(executor.return_driver().manage().getCookieNamed("dealLocation").getValue().equals("All deals")){
            // cookie parameter in such case has false value
        }else{
            assertEquals("Send cookie contains location name different than chosen by user", chosenLocation, executor.return_driver().manage().getCookieNamed("dealLocation").getValue());
        }
        assertEquals("Daily deals header has improper location name", chosenLocation, executor.getText(locators_dealyDeal.dealHeaderLocationDailyDealPage));

    }

    // EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check page content
    String dealName;
    String dealPointsValue;
    String dealPrice;
    public void chooseOneOfDailyDeals() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> dealsNames = executor.getWebElementsList(locators_dealyDeal.dealDescriptionDailyDealPage);
        List<WebElement> dealsePoints = executor.getWebElementsList(locators_dealyDeal.dealEpRewarnDailyDealPage);
        List<WebElement> dealsPrice = executor.getWebElementsList(locators_dealyDeal.dealPriceDailyDealPage);
        List<WebElement> dealsSaving = executor.getWebElementsList(locators_dealyDeal.dealPercentageSavingDailyDealPage);
        int random = executor.return_random_value(dealsNames.size()-1);
        dealName = executor.getText(dealsNames.get(random));
        dealPointsValue = executor.getText(dealsePoints.get(random));
        dealPrice = executor.getText(dealsPrice.get(random));

        executor.click(dealsNames.get(random));
        // waiting for trust pilot icon
        Thread.sleep(4000);
    }

    public void checkIfIndividualDealPageWasOpened(){
        //assertEquals("Page tab name is incorrect", (dealName + " | epoints").replace(" ",""), executor.return_driver().getTitle().replace(" ",""));
        //assertTrue("Page tab name is incorrect", executor.return_driver().getTitle().contains(dealName));
    }

    public void checkVisibilityOfDealsIndividualPageAndItsValues(){
        // static elements
        assertTrue("Back to previous page button is not visible", executor.is_element_present(locators_dealyDeal.backToPreviousPageDDIndivididualPage));
        assertTrue("Deal picture is not visible", executor.is_element_present(locators_dealyDeal.dealPictureDDIndivididualPage));
        assertTrue("Link to retailer is not visible", executor.is_element_present(locators_dealyDeal.dealRetailerDDIndivididualPage));
        assertTrue("Trust pilot icon is not visible", executor.is_element_present(locators_dealyDeal.dealTrustPilotDDIndivididualPage));
        assertTrue("Get deal button is not visible", executor.is_element_present(locators_dealyDeal.getDealButtonDDIndivididualPage));
        assertTrue("Expiry counter is not visible", executor.is_element_present(locators_dealyDeal.expireCounterDDIndivididualPage));
        assertTrue("Deal description is not visible", executor.is_element_present(locators_dealyDeal.dealDescriptionDDIndivididualPage));
        assertTrue("Share component is not visible", executor.is_element_present(locators_dealyDeal.dealShareComponentDDIndivididualPage));
        //deal saving is not mandatory
        //assertTrue("Deal saving is not visible", executor.is_element_present(locators_dealyDeal.dealSavingDDIndivididualPage));
        // dynamic elements
        assertEquals("Deal name is different than on card", dealName ,executor.getText(locators_dealyDeal.dealNameDDIndivididualPage));
        assertEquals("Deal points reward is different than on card", dealPointsValue,executor.getText(locators_dealyDeal.dealEpointsDDIndivididualPage).replace(",",""));
        assertEquals("Deal price is different than on card", dealPrice,executor.getText(locators_dealyDeal.dealPriceDDIndivididualPage));
    }

    public void clickBackToPreviousPageButton(){
        executor.click(locators_dealyDeal.backToPreviousPageDDIndivididualPage);
    }

    public void checkIfUserWasCorrectlyRedirectedToPreviousPage(){
        assertTrue("User was not correctly redirected to previous page", executor.return_driver().getCurrentUrl().contains("/deals"));
    }

    // EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check working of clickout
    public void clickGetDealButton(){
        dailyDealsRetailerName = executor.getText(locators_dealyDeal.dealRetailerDDIndivididualPage).replace("from ","");
        executor.click(locators_dealyDeal.getDealButtonDDIndivididualPage);
    }

    // EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check link to retailer
    String retailerName;
    public void clickRetailerLink(){
        retailerName = executor.getText(locators_dealyDeal.dealRetailerDDIndivididualPage);
        executor.click(locators_dealyDeal.dealRetailerDDIndivididualPage);
    }

    public void checkIfProperRetailerPageWasOpened(){
        assertEquals("User was not correctly redirected to retailer page", retailerName.replace("from ","")+ ": Offers, Vouchers and Reviews | epoints", executor.return_driver().getTitle());
    }
}