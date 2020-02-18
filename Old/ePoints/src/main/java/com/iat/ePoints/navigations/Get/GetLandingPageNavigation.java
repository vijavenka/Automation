package com.iat.ePoints.Navigations.Get;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 18.06.13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetLandingPageLocators;
import com.iat.ePoints.Locators.Get.GetStoresAZPageLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.HomePageLocators;
import com.iat.ePoints.Locators.StaticPages.AboutUsLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetLandingPageNavigation extends AbstractPage {

    private GetLandingPageLocators locator_landingPage = new GetLandingPageLocators();
    private GetStoresAZPageLocators locator_shopsAZ = new GetStoresAZPageLocators();
    private GetStoresAZPageLocators locator_AZshops = new GetStoresAZPageLocators();
    private HomePageLocators locator_home = new HomePageLocators();
    private HeaderLocators locator_header = new HeaderLocators();
    private AboutUsLocators locators_about = new AboutUsLocators();

    public GetLandingPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //Landing page

    //Checking page structure //////////////////////////////////////////////////////////////////////////////////////////
    public void goToEarnEPointsPage() throws InterruptedException {
        executor.click(locator_landingPage.earnEpointsMenu);
        Thread.sleep(2000);
    }

    public void checkIsEarnLandingPageOpend() {
        assertTrue("Get ePoints page not opened properly", executor.is_element_present(locator_landingPage.sectionZeroTitle));
    }

    public void checkIsEarnLandingPageHaveProperSections() throws InterruptedException {
        assertTrue("How to gets epoints section is no available", executor.is_element_present(locator_landingPage.sectionOne));
        executor.click(locator_landingPage.firstSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("From more than 2000 online stores section is no available", executor.is_element_present(locator_landingPage.sectionTwo));
        executor.click(locator_landingPage.secondSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("Free epoints section is no avaliable", executor.is_element_present(locator_landingPage.sectionThree));
        executor.click(locator_landingPage.thirdSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("For doing what you do already section is no available", executor.is_element_present(locator_landingPage.sectionFour));
        executor.click(locator_landingPage.fourthSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("Top tips for you section is no avaliable", executor.is_element_present(locator_landingPage.sectionFive));
        //executor.click(locator_landingPage.getEpointsMenu);
    }

    public void checkIfEarnLandingPageSectionsHaveProperContent() throws InterruptedException {

        //section 1
        Thread.sleep(1000);
        assertTrue("Section one title is no visible", executor.is_element_present(locator_landingPage.sectionZeroTitle));
        //executor.return_driver().switchTo().frame(executor.get_element(locator_landingPage.firstSectionIframe));
        assertTrue("Shopping circle is no visible", executor.is_element_present(locator_landingPage.shopingInfo));
        assertTrue("Doing circle is no visible", executor.is_element_present(locator_landingPage.doingInfo));
        assertTrue("Loving circle is no visible", executor.is_element_present(locator_landingPage.lovingInfo));
        assertTrue("Arrow down is no visible", executor.is_element_present(locator_landingPage.firstSectionArrowDown));

        //section 2
        executor.click(locator_landingPage.firstSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("Section Two title is no visible", executor.is_element_present(locator_landingPage.sectionOneTitle));
        assertTrue("Search text field is no visible", executor.is_element_present(locator_landingPage.sectionOneSearcherTextfield));
        assertTrue("Search Button is no Visible", executor.is_element_present(locator_landingPage.sectionOneSearchButton));
        List<WebElement> retailers = executor.getWebElementsList(locator_landingPage.retailerGetPageBasic);
        //assertTrue("There is no eight retailers in second section", retailers.size() == 8);        //can changing
        assertTrue("A_Z of all stores button is no visible", executor.is_element_present(locator_landingPage.azOfaLLStoresButton));
        assertTrue("Arrow down is no visible", executor.is_element_present(locator_landingPage.secondSectionArrowDown));

        //section 3
        executor.click(locator_landingPage.secondSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("Free epoints circle is no visible", executor.is_element_present(locator_landingPage.freeEpointsLogo));
        assertTrue("Registering via epoints is no visible", executor.is_element_present(locator_landingPage.registeringViaEpoints));
        assertTrue("Completing your profile is no visible", executor.is_element_present(locator_landingPage.completingYourProfile));
        assertTrue("Liking on facebook is no visible", executor.is_element_present(locator_landingPage.likingOnFacebook));
        assertTrue("Following us on Twitter is no visible", executor.is_element_present(locator_landingPage.followingUsOnTweeter));
        //TODO - this changing so many time that I decided to comment those two assertions
        //assertTrue("Signing in to epoints every day is no visible", executor.is_element_present(locator_landingPage.visitingEpointsEveryDay));
        //assertTrue("Recommending epoints is no visible", executor.is_element_present(locator_landingPage.recommendingEpoints));
        assertTrue("Arrow down is no visible", executor.is_element_present(locator_landingPage.thirdSectionArrowDown));

        //section 4
        executor.click(locator_landingPage.thirdSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("Section four title is no visible", executor.is_element_present(locator_landingPage.sectionThreeTitle));
        assertTrue("Watch video activity is no visible", executor.is_element_present(locator_landingPage.activityOneVideos));
        assertTrue("Commenting activity is no visible", executor.is_element_present(locator_landingPage.activityTwoCommenting));
        assertTrue("Like and follow activity is no visible", executor.is_element_present(locator_landingPage.activityThreeFollowing));
        assertTrue("Registering activity is no visible", executor.is_element_present(locator_landingPage.activityFourRegistering));
        assertTrue("Daily visits activity is no visible", executor.is_element_present(locator_landingPage.activityFiveDailyVisits));
        assertTrue("Arrow down is no visible", executor.is_element_present(locator_landingPage.fourthSectionArrowDown));

        //section 5
        executor.click(locator_landingPage.fourthSectionArrowDown);
        Thread.sleep(2000);
        assertTrue("Section five title is no visible", executor.is_element_present(locator_landingPage.sectionFourTitle));
        assertTrue("First box is no visible", executor.is_element_present(locator_landingPage.boxOneStrongText));
        assertTrue("Second box is no visible", executor.is_element_present(locator_landingPage.boxTwoStrongText));
        assertTrue("Third box is no visible", executor.is_element_present(locator_landingPage.boxThreeStrongText));
        assertTrue("Fourth box is no visible", executor.is_element_present(locator_landingPage.boxFourStrongText));
        assertTrue("Fifth box is no visible", executor.is_element_present(locator_landingPage.boxFiveStrongText));
        assertTrue("Sixth box is no visible", executor.is_element_present(locator_landingPage.boxSixStrongText));
        assertTrue("Contact us button is no visible", executor.is_element_present(locator_landingPage.contactUsButton));
    }

    // Checking if Links and buttons works properly ////////////////////////////////////////////////////////////////////
    public void checkSearcherLink() throws InterruptedException {
        executor.click(locator_landingPage.earnEpointsMenu);
        //executor.click(locator_landingPage.firstSectionArrowDown);
        executor.send_keys(locator_landingPage.sectionOneSearcherTextfield, "John Lewis");
        executor.click(locator_landingPage.sectionOneSearchButton);
        assertTrue("Get page url is incorrect", executor.return_driver().getCurrentUrl().contains("/retailers"));
        Thread.sleep(4000);
        executor.moveMouseToWebElement(locator_AZshops.basicAlphabetRetailerLocator);
        assertEquals("Chosen retailer was not found", "John Lewis", executor.get_element(locator_AZshops.basicAlphabetRetailerNameLocator).getText());
    }

    public void checkAZOfAllStoresButtonLink() throws InterruptedException {
        executor.click(locator_landingPage.earnEpointsMenu);
        executor.click(locator_landingPage.firstSectionArrowDown);
        Thread.sleep(1000);
        executor.click(locator_landingPage.azOfaLLStoresButton);
        assertEquals("Page title is incorrect", executor.return_driver().getTitle(), "Get epoints from thousands of retailers | epoints");
    }

    public void checkContactUSButtonLink() throws InterruptedException {
        executor.click(locator_landingPage.getEpointsMenu);
        executor.click(locator_landingPage.firstSectionArrowDown);
        Thread.sleep(1000);
        executor.click(locator_landingPage.secondSectionArrowDown);
        Thread.sleep(1000);
        executor.click(locator_landingPage.thirdSectionArrowDown);
        Thread.sleep(1000);
        executor.click(locator_landingPage.fourthSectionArrowDown);
        Thread.sleep(2000);
        executor.click(locator_landingPage.contactUsButton);
        assertEquals("Page title is incorrect", executor.return_driver().getTitle(), "epoints support : Submit a request for assistance");
    }

    // RETAILER PAGE ON EPOINTS - change retailer links on home and get pages (RD-1156 release 10) /////////////////////
    public void checkCorrectnessOfRetailersLinksOnMainPage() throws SQLException {
        JDBC jdbc = new JDBC("affiliate_manager");
        List<WebElement> retailers = executor.getWebElementsList(locator_home.retailerMainPageBasic);
        int iterator = 0;
        for (WebElement retailer : retailers) {
            //Check if retailer link has proper content
            assertTrue("Link does not contains /retailer/", retailer.getAttribute("href").contains("/retailer/"));
            iterator++;
            System.out.println(retailer.getAttribute("data-retailername") + " " + jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailer.getAttribute("data-retailername") + "'", 1));
            //If name hidden in html is the same as in data base check also retailer id, this part can throws exceptions
            if (jdbc.return_proper_db_value("SELECT name FROM admin_ui.Merchant WHERE name='" + retailer.getAttribute("data-retailername") + "'", 1).equals(retailer.getAttribute("data-retailername"))) {
                assertTrue("Link " + iterator + " is not correct for " + retailer.getAttribute("data-retailername"), retailer.getAttribute("href").contains("/retailer/") &&  retailer.getAttribute("href").contains(jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailer.getAttribute("data-retailername") + "' AND active = '1'", 1)));
            }
        }
        jdbc.close();
    }

    public void checkCorrectnessOfRetailersLinksOnGetPage() throws SQLException {
        executor.click(locator_header.getPoints);
        JDBC jdbc = new JDBC("affiliate_manager");
        List<WebElement> retailers = executor.getWebElementsList(locator_landingPage.retailerGetPageBasic);
        int iterator = 0;
        for (WebElement retailer : retailers) {
            //Check if retailer link has proper content
            assertTrue("Link does not contains /retailer/", retailer.getAttribute("href").contains("/retailer/"));
            iterator++;
            //If name hidden in html is the same as in data base check also retailer id, this part can throws exceptions
            if (jdbc.return_proper_db_value("SELECT name FROM admin_ui.Merchant WHERE name='" + retailer.getAttribute("data-retailername") + "'", 1).equals(retailer.getAttribute("data-retailername"))) {
                assertTrue("Link " + iterator + " is not correct for " + retailer.getAttribute("data-retailername"), retailer.getAttribute("href").contains("/retailer/") && retailer.getAttribute("href").contains(jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailer.getAttribute("data-retailername") + "'", 1)));
            }
        }
        jdbc.close();
    }

    public void checkCorrectnessOfRetailersLinksOnStoresAZPage() throws SQLException, InterruptedException {
            executor.click(locator_landingPage.shopsAZsubmenu);
            JDBC jdbc = new JDBC("affiliate_manager");
            List<WebElement> retailers;
            List<WebElement> retailersNames;
            int iterator = 0;

            //Popular stores part Locators used from favourites, please do not be confused :)
            //Because of moving carousel only one retailer will be checked
                //Check if retailer link has proper content
                executor.moveMouseToWebElement(locator_shopsAZ.favouriteStoresBasicRetailerlocator);
                assertTrue("Link does not contains /retailer/", executor.get_element(locator_AZshops.retailerCardRetailerPageButton).getAttribute("href").contains("/retailer/"));
                String retailerName = executor.getText(locator_AZshops.favouriteStoresBasicRetailerlocator);
                iterator++;
                //iterator-1 to start from List index zero
                //If name hidden in html is the same as in data base check also retailer id, this part can throws exceptions
                if (jdbc.return_proper_db_value("SELECT name FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1).equals(retailerName)) {
                    assertTrue("Link is not correct for " + retailerName, executor.get_element(locator_shopsAZ.retailerCardRetailerPageButton).getAttribute("href").contains("/retailer/" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1)));
                }

            //Alphabetic part
            retailers = executor.getWebElementsList(locator_AZshops.basicAlphabetRetailerHrefLocator); //for their href
            retailersNames = executor.getWebElementsList(locator_AZshops.basicAlphabetRetailerNameLocator); //for their names
            iterator = 0;
            for (WebElement retailer : retailers) {
                //Check if retailer link has proper content
                assertTrue("Link does not contains /retailer/", retailer.getAttribute("href").contains("/retailer/"));
                iterator++;
                //iterator-1 to start from List index zero
                //If name hidden in html is the same as in data base check also retailer id, this part can throws exceptions
                if (jdbc.return_proper_db_value("SELECT name FROM admin_ui.Merchant WHERE name='" + retailersNames.get(iterator - 1).getText() + "'", 1).equals(retailer.getAttribute("data-retailername"))) {
                    assertTrue("Link " + iterator + " is not correct for " + retailersNames.get(iterator - 1).getText(), retailer.getAttribute("href").contains("/retailer/") & retailer.getAttribute("href").contains(jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailersNames.get(iterator - 1).getText() + "'", 1)));
                }
            }
        jdbc.close();
    }

    public void checkCorrectnessOfRetailersLinksOnAboutUsPage() throws SQLException {
        //Popular stores part Locators used from favourites, please do not be confused :)
        //Because of moving carousel only one retailer will be checked
        //Check if retailer link has proper content
        JDBC jdbc = new JDBC("affiliate_manager");
        executor.click(locator_header.aboutEpoints);
        executor.moveMouseToWebElement(locator_shopsAZ.retailerCardRetailerCardContainer);
        assertTrue("Link does not contains /retailer/", executor.get_element(locator_AZshops.retailerCardRetailerPageButton).getAttribute("href").contains("/retailer/"));
        String retailerName = executor.getText(locators_about.retailerNameBasic);
        //iterator-1 to start from List index zero
        //If name hidden in html is the same as in data base check also retailer id, this part can throws exceptions
        if (jdbc.return_proper_db_value("SELECT name FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1).equals(retailerName)) {
            assertTrue("Link is not correct for " + retailerName, executor.get_element(locator_shopsAZ.retailerCardRetailerPageButton).getAttribute("href").contains("/retailer/" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1)));
        }
        jdbc.close();
    }
}