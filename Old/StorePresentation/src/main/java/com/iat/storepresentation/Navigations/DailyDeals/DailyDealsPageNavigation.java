package com.iat.storepresentation.Navigations.DailyDeals;

import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.DailyDeals.DailyDealsPageLocators;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Locators.TransitionPage.TransitionPageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public class DailyDealsPageNavigation extends AbstractPage {

    private HomePageLocators locators_home = new HomePageLocators();
    private DailyDealsPageLocators locators_deal = new DailyDealsPageLocators();
    private TransitionPageLocators locator_transition = new TransitionPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();


    public DailyDealsPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }
    
    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check content of all daily deals
    public void openDailyDealsPage(boolean locationModalClosed) throws InterruptedException {
        executor.click(locators_home.dailyDelas);
        Thread.sleep(4000);
    }

    public void forAllDailyDealsCheckElementsLikeExpiryDatePriceETC(){
        List<WebElement> dealContainer = executor.getWebElementsList(locators_deal.dealContainerDailyDealPage);
        List<WebElement> dealPicture = executor.getWebElementsList(locators_deal.dealImageDailyDealPage);
        List<WebElement> dealEpoints = executor.getWebElementsList(locators_deal.dealEpRewarnDailyDealPage);
        List<WebElement> dealDescription = executor.getWebElementsList(locators_deal.dealDescriptionDailyDealPage);
        List<WebElement> dealGetButton = executor.getWebElementsList(locators_deal.getDealButtonDailyDealPage);
        List<WebElement> dealExpiryDate = executor.getWebElementsList(locators_deal.dealExpiryDateDailyDealPage);
        List<WebElement> dealPrice = executor.getWebElementsList(locators_deal.dealPriceDailyDealPage);

        assertTrue("Daily deal elements number is improper for deal description", dealContainer.size() == dealDescription.size() &&
                dealContainer.size() == dealEpoints.size() &&
                dealContainer.size() == dealExpiryDate.size() &&
                dealContainer.size() == dealGetButton.size() &&
                //dealContainer.size() == dealPicture.size() &&
                dealContainer.size() == dealPrice.size());
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check possibility of buying daily deals and check reported clickout
    public void choseSomeDailyDealsAndClickGetThisDealButton(){
        List<WebElement> getThisDealButtons = executor.getWebElementsList(locators_deal.getDealButtonDailyDealPage);
        executor.click(getThisDealButtons.get(executor.return_random_value(getThisDealButtons.size())));
    }

    String dailyDealsRetailerName;
    public void rememberMerchantName(){
        dailyDealsRetailerName = executor.return_driver().getTitle().replace("Purchase from ","").replace(" | "+envVariables.StoreName+"'s shop","");
    }

    public void clickContinueAnywayInTransitionPage(){
        executor.click(locator_transition.continueAnywayButton);
    }

    public void checkIfClickoutWasReported(String ifSigned, String Email) throws InterruptedException, SQLException {
        // this function body is a copy of function from transition page navigation class, it is only a little bit changed
        Thread.sleep(2000);
        String CurrentDate = executor.returnCurrentDate();
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), dailyDealsRetailerName);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + dailyDealsRetailerName + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), envVariables.StoreName);
        assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "525e54ede4b0dbd2decff9a7");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + dailyDealsRetailerName + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + dailyDealsRetailerName + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - location from home page
    String locationName;
    public void selectSpecifiedLocation(){
        executor.click(locators_home.dealyDealsSelectlocation);
        List<WebElement> locations = executor.getWebElementsList(locators_home.dealyDealsSpecifiedLocationlocation);
        int random = executor.return_random_value(locations.size());
        locationName = executor.getText(locations.get(random));
        executor.click(locations.get(random));
    }

    public void checkIfDailyDealsPageWasOpened(){
        assertTrue("Daily deals page was not opened", executor.return_driver().getCurrentUrl().contains("/daily-deals"));
    }

    public void checkIfLocationWasAppliedToResults() throws InterruptedException {
        executor.waitUntilElementAppears(locators_deal.dealDescriptionDailyDealPage);
        List<WebElement> dealsNames = executor.getWebElementsList(locators_deal.dealDescriptionDailyDealPage);
        boolean ifLocationfound = false;
        for(WebElement deal : dealsNames ){
            // this solution is temporary
            if(executor.getText(deal).contains(locationName)){
                    ifLocationfound = true;
            }
        }
        //TODO should be finished when location will be introduced to cookie like on epoints
        //assertTrue("Location was not aplied for any Deal for " + locationName, ifLocationfound);
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - location from popup window
    public void openFacetsPopupWindow() throws InterruptedException {
        executor.click(locators_deal.facetsSeeAllButton);
        Thread.sleep(1000);
    }

    public void selectSpecifiedLocationFromPopupWindow() throws InterruptedException {
        executor.click(locators_deal.locationFacetPopupWindow);
        executor.waitUntilElementAppears(locators_deal.locationFacetLocationPopupWindow);
        List<WebElement> locations = executor.getWebElementsList(locators_deal.locationFacetLocationPopupWindow);
        int random = executor.return_random_value(locations.size());
        locationName = executor.getText(locations.get(random));
        executor.click(locations.get(random));
    }

    public void clickDoneButton() throws InterruptedException {
        executor.click(locators_deal.facetsDoneButton);
        Thread.sleep(4000);
    }
    
    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check individual daily deals page content
    String dealName;
    String dealPointsValue;
    String dealPrice;
    public void chooseOneOfDailyDeals() throws InterruptedException {
        List<WebElement> dealsNames = executor.getWebElementsList(locators_deal.dealDescriptionDailyDealPage);
        List<WebElement> dealsePoints = executor.getWebElementsList(locators_deal.dealEpRewarnDailyDealPage);
        List<WebElement> dealsPrice = executor.getWebElementsList(locators_deal.dealPriceDailyDealPage);
        List<WebElement> dealsSaving = executor.getWebElementsList(locators_deal.dealPercentageSavingDailyDealPage);
        int random = executor.return_random_value(dealsNames.size());
        dealName = executor.getText(dealsNames.get(random));
        dealPointsValue = executor.getText(dealsePoints.get(random));
        dealPrice = executor.getText(dealsPrice.get(random));

        executor.click(dealsNames.get(random));
        // waiting for trust pilot icon
        Thread.sleep(4000);
    }

    public void checkIfIndividualDealPageWasOpened(){
        //assertEquals("Page tab name is incorrect", (dealName + " | shopshop's shop"), executor.return_driver().getTitle());
        //assertTrue("Page tab name is incorrect", executor.return_driver().getTitle().contains(dealName));
    }

    public void checkVisibilityOfDealsIndividualPageAndItsValues(){
        // static elements
        assertTrue("Back to previous page button is not visible", executor.is_element_present(locators_deal.backToPreviousPageDDIndivididualPage));
        assertTrue("Deal picture is not visible", executor.is_element_present(locators_deal.dealPictureDDIndivididualPage));
        assertTrue("Link to retailer is not visible", executor.is_element_present(locators_deal.dealRetailerDDIndivididualPage));
        //assertTrue("Trust pilot icon is not visible", executor.is_element_present(locators_deal.dealTrustPilotDDIndivididualPage));
        assertTrue("Get deal button is not visible", executor.is_element_present(locators_deal.getDealButtonDDIndivididualPage));
        assertTrue("Expiry counter is not visible", executor.is_element_present(locators_deal.expireCounterDDIndivididualPage));
        assertTrue("Deal description is not visible", executor.is_element_present(locators_deal.dealDescriptionDDIndivididualPage));
        assertTrue("Share component is not visible", executor.is_element_present(locators_deal.dealShareComponentDDIndivididualPage));
        //deal saving is not mandatory
        //assertTrue("Deal saving is not visible", executor.is_element_present(locators_deal.dealSavingDDIndivididualPage));
        // dynamic elements
        assertEquals("Deal name is different than on card", dealName ,executor.getText(locators_deal.dealNameDDIndivididualPage));
        assertEquals("Deal points reward is different than on card", dealPointsValue,executor.getText(locators_deal.dealEpointsDDIndivididualPage).replace(",",""));
        assertTrue("Deal price is different than on card", executor.getText(locators_deal.dealPriceDDIndivididualPage).contains(dealPrice));
    }

    public void clickBackToPreviousPageButton(){
        executor.click(locators_deal.backToPreviousPageDDIndivididualPage);
    }

    public void checkIfUserWasCorrectlyRedirectedToPreviousPage() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue("User was not correctly redirected to previous page", executor.return_driver().getCurrentUrl().contains("/daily-deals"));
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check working of clickout from individual daily deal page
    public void clickGetDealButton(){
        dailyDealsRetailerName = executor.getText(locators_deal.dealRetailerDDIndivididualPage).replace("from ", "");
        executor.click(locators_deal.getDealButtonDDIndivididualPage);
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check link to retailer from individual daily deal page
    String retailerName;
    public void clickRetailerLink(){
        retailerName = executor.getText(locators_deal.dealRetailerDDIndivididualPage).replace("from ","");
        executor.click(locators_deal.dealRetailerDDIndivididualPage);
    }

    public void checkIfProperRetailerPageWasOpened(){
        assertTrue("User was not correctly redirected to retailer page", executor.return_driver().getTitle().contains(retailerName.replace("from ","")+ ": Offers, Vouchers and Reviews"));
    }
    
    // FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - - epoints range, price, saving slider
    int epointsEarnedLow;
    int epointsEarnedUp;
    int priceLow;
    int priceUp;
    int savingLow;
    int savingUp;
    public void setEpointsEarnedSliderRange() throws InterruptedException {
        executor.click(locators_deal.epointsEarnedSliderFacet);
        int low = (int) (Integer.parseInt(executor.getText(locators_deal.leftValueEpointsEarned))+0.2*Integer.parseInt(executor.getText(locators_deal.leftValueEpointsEarned)));
        int up = (int) (Integer.parseInt(executor.getText(locators_deal.rightValueEpointsEarned))-0.2*Integer.parseInt(executor.getText(locators_deal.rightValueEpointsEarned)));

        Actions action = new Actions(executor.return_driver());

        Thread.sleep(3000);
        while (Integer.parseInt(executor.getText(locators_deal.leftValueEpointsEarned)) < low) {
            action.dragAndDropBy(executor.get_element(locators_deal.leftSliderEpointsEarned), 20, 0).perform();
            Thread.sleep(3000);
        }
        Thread.sleep(3000);
        while (Integer.parseInt(executor.getText(locators_deal.rightValueEpointsEarned)) > up && Integer.parseInt(executor.getText(locators_deal.rightValueEpointsEarned)) - 0.1 * up > low) {
            action.dragAndDropBy(executor.get_element(locators_deal.rightSliderEpointsEarned), -20, 0).perform();
            Thread.sleep(3000);
        }

        epointsEarnedLow = Integer.parseInt(executor.getText(locators_deal.leftValueEpointsEarned));
        epointsEarnedUp = Integer.parseInt(executor.getText(locators_deal.rightValueEpointsEarned));
    }

    public void setPriceSliderRange() throws InterruptedException {
        executor.click(locators_deal.priceSliderFacet);
        int low = (int) (Integer.parseInt(executor.getText(locators_deal.leftValuePrice))+0.2*Integer.parseInt(executor.getText(locators_deal.leftValuePrice)));
        int up = (int) (Integer.parseInt(executor.getText(locators_deal.rightValuePrice))-0.2*Integer.parseInt(executor.getText(locators_deal.rightValuePrice)));

        Actions action = new Actions(executor.return_driver());

        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_deal.leftValuePrice)) < low) {
            action.dragAndDropBy(executor.get_element(locators_deal.leftSliderPrice), 20, 0).perform();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_deal.rightValuePrice)) > up && Integer.parseInt(executor.getText(locators_deal.rightValuePrice)) - 0.1 * up > low) {
            action.dragAndDropBy(executor.get_element(locators_deal.rightSliderPrice), -20, 0).perform();
            Thread.sleep(2000);
        }

        priceLow = Integer.parseInt(executor.getText(locators_deal.leftValuePrice));
        priceUp = Integer.parseInt(executor.getText(locators_deal.rightValuePrice));
    }

    public void setSavingSliderRange() throws InterruptedException {
        executor.click(locators_deal.savingSliderFacet);
        int low = 55;
        int up = (int) (Integer.parseInt(executor.getText(locators_deal.rightValueSaving))-0.05*Integer.parseInt(executor.getText(locators_deal.rightValueSaving)));

        Actions action = new Actions(executor.return_driver());

        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_deal.leftValueSaving)) < low) {
            action.dragAndDropBy(executor.get_element(locators_deal.leftSliderSaving), 20, 0).perform();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        while (Integer.parseInt(executor.getText(locators_deal.rightValueSaving)) > up && Integer.parseInt(executor.getText(locators_deal.rightValueSaving)) - 0.1 * up > low) {
            action.dragAndDropBy(executor.get_element(locators_deal.rightSliderSaving), -20, 0).perform();
            Thread.sleep(2000);
        }

        savingLow = Integer.parseInt(executor.getText(locators_deal.leftValueSaving));
        savingUp = Integer.parseInt(executor.getText(locators_deal.rightValueSaving));
    }

    public void clickDoneButtonOnFAcetsPopupWindow() throws InterruptedException {
        executor.click(locators_deal.facetsDoneButton);
        Thread.sleep(2000);
    }

    public void checkCorrectnesOfallFeaturesValue(){
        List<WebElement> epointsList = executor.getWebElementsList(locators_deal.dealEpRewarnDailyDealPage);
        List<WebElement> pricesList = executor.getWebElementsList(locators_deal.dealPriceDailyDealPage);
        List<WebElement> savingsList = executor.getWebElementsList(locators_deal.dealPercentageSavingDailyDealPage);

        for(int i = 0; i<epointsList.size(); i++){
            assertTrue("Epoints value is not in the range for range "+ epointsEarnedLow + " " + epointsEarnedUp + " value " + executor.getText(epointsList.get(i)), Integer.parseInt(executor.getText(epointsList.get(i)).replace("+","")) >= epointsEarnedLow && Integer.parseInt(executor.getText(epointsList.get(i)).replace("+","")) <= epointsEarnedUp);
            assertTrue("Price value is not in the range for range "+ priceLow + " " + priceUp + " value " + executor.getText(pricesList.get(i)), (int)Double.parseDouble(executor.getText(pricesList.get(i)).replace("£ ","")) >= priceLow && (int)Double.parseDouble(executor.getText(pricesList.get(i)).replace("£ ","")) <= priceUp);
            assertTrue("Saving value is not in the range for range "+ savingLow + " " + savingUp + " value " + executor.getText(savingsList.get(i)), Integer.parseInt(executor.getText(savingsList.get(i)).replace("% saving","")) >= savingLow && Integer.parseInt(executor.getText(savingsList.get(i)).replace("% saving","")) <= savingUp);
        }
    }

}
