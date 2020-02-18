package com.iat.ePoints.Navigations.MyAccount;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetStoresAZPageLocators;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyEpointsStepsNavigation extends AbstractPage {
    private MyProfileLocators locators_myprofile = new MyProfileLocators();
    private GetStoresAZPageLocators locators_earnshopsaz = new GetStoresAZPageLocators();

    public MyEpointsStepsNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void openMyEpointsTab() {
        executor.click(locators_myprofile.myEpoints);
    }

    public void checkMyEpointsPageContent() {
        assertTrue("User info panel is no visible", executor.is_element_present(locators_myprofile.userinfoPanel));
        assertTrue("---User name is no visible", executor.is_element_present(locators_myprofile.epointsTableName));
        assertEquals("---User name is no correct", "Krzysztof Baranowski", executor.getText(locators_myprofile.epointsTableName));
        assertTrue("---Total epoints to date is no visible", executor.is_element_present(locators_myprofile.totalEpointsToDateTable));
        assertTrue("---Rewards claimed is no visoble", executor.is_element_present(locators_myprofile.rewardsClaimedTable));
        assertTrue("---Epoints link is no visible", executor.is_element_present(locators_myprofile.epointsTable));
        assertTrue("---Total epoints to date is no visible", executor.is_element_present(locators_myprofile.totalEpointsToDateTable));
        assertTrue("---Total epoints to date is no visible", executor.is_element_present(locators_myprofile.totalEpointsToDateTable));

        List<WebElement> Values = executor.getWebElementsList(locators_myprofile.basicMyEpointsTablePointsLocator);
        for (int i = 0; i < 3; i++) {
            assertTrue("Value number" + i + " is not visible", executor.is_element_present(Values.get(i)));
        }

        assertTrue("User activity table is no visible", executor.is_element_present(locators_myprofile.recentActivityTable));
        assertTrue("Recent activity name is no visible", executor.is_element_present(locators_myprofile.recentActivityTableName));
        assertTrue("All activity link is no visible", executor.is_element_present(locators_myprofile.allActivityLink));
        assertTrue("Ep earned is no visible", executor.is_element_present(locators_myprofile.epEarned));
        assertTrue("Ep spent is no visible", executor.is_element_present(locators_myprofile.epSpent));
        assertTrue("Balance is no visible", executor.is_element_present(locators_myprofile.balance));

        assertTrue("Static info table is no visible", executor.is_element_present(locators_myprofile.staticInfoTable));
        assertTrue("Static info table name is no visible", executor.is_element_present(locators_myprofile.staticInfoTableName));
        assertTrue("Find your favourite stores is no visible", executor.is_element_present(locators_myprofile.findYourFavouriteStoresButton));
    }

    public void checkIfaAllActivityLinkWorksProperly() {
        executor.click(locators_myprofile.allActivityLink);
        assertEquals("Page title is no correct", "Activity", executor.getText(locators_myprofile.basicTabsPageTitle));
    }

    public void returnToMyepointsTab() {
        executor.click(locators_myprofile.myEpoints);
    }

    public void checkIfButtonWorksCorrectly() {
        executor.click(locators_myprofile.findYourFavouriteStoresButton);
        assertTrue("Page url is no correct", executor.return_driver().getCurrentUrl().contains("/retailers"));
    }

}


