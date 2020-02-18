package com.iat.ePoints.Navigations.Spend;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetStoresAZPageLocators;
import com.iat.ePoints.Locators.Spend.BrowseRewardsLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BrowseRewardsNavigation extends AbstractPage {

    protected BrowseRewardsLocators locators_rewards = new BrowseRewardsLocators();
    protected GetStoresAZPageLocators locators_az = new GetStoresAZPageLocators();

    public BrowseRewardsNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void checkIsBrowseRewardsPageOpened() {
        while (!executor.is_element_present(locators_rewards.itemsCardBoxes)) {
        }
        assertTrue("Browse Rewards page not opened properly", executor.is_element_present(locators_rewards.pageHeaderTitle));

    }

    public void checkIsPageSizeComponentAvailable() throws InterruptedException {
        Thread.sleep(2000);  // to remove if test will be still failed
        assertTrue("Browse Rewards - page size component not available", executor.is_element_present(locators_rewards.itemsPerPageComponent));
    }

    public void checkIsSortOrderComponentAvailable() {
        assertTrue("Browse Rewards - sort order component not available", executor.is_element_present(locators_rewards.sortByComponent));
    }

    public void checkIsPaginationComponentAvailable() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue("Browse Rewards - pagination top preview button not available", executor.is_element_present(locators_rewards.paginationTopPrev_btn));
        assertTrue("Browse Rewards - pagination top next button not available", executor.is_element_present(locators_rewards.paginationTopNext_btn));
        assertTrue("Browse Rewards - pagination bottom component not available", executor.is_element_present(locators_rewards.paginationBottomComponent));
    }

    public void checkIsRangesBtnsAvailable() {
        assertTrue("Browse Rewards - range 100+ bottom component not available", executor.is_element_present(locators_rewards.range100btn));
        assertTrue("Browse Rewards - range 1000+ bottom component not available", executor.is_element_present(locators_rewards.range1000btn));
        assertTrue("Browse Rewards - range 5000+ bottom component not available", executor.is_element_present(locators_rewards.range5000btn));
        assertTrue("Browse Rewards - range 20000+ bottom component not available", executor.is_element_present(locators_rewards.range20000btn));
        assertTrue("Browse Rewards - range 50000+ bottom component not available", executor.is_element_present(locators_rewards.range50000btn));
        assertTrue("Browse Rewards - range 100000+ bottom component not available", executor.is_element_present(locators_rewards.range100000btn));
    }

    public void checkIsCategoryComponentAvailable() {
        assertTrue("Category component is no visible", executor.is_element_present(locators_rewards.categoryFacet));
    }


    //Redemptions Landing page /////////////////////////////////////////////////////////////////////////////////////////
    public void goToRedemptionsLandingPage() {
        executor.click(locators_rewards.spendMenu_opt);
    }

    public void checkIsRedemptionsLandingPageOpened() {
        assertEquals("Redemptions Items landing page not opened", "active", executor.get_element(locators_rewards.spendMenuTab).getAttribute("class"));
    }

    public void checkIsItemsCounterAvailable() {
        assertTrue("Item counter is no available for not log in user", executor.is_element_present(locators_rewards.itemsToBuyCounter));
    }

    public void checkIsRangesBoxesAvailable() {
        assertTrue("Range 100-200 box is not available", executor.is_element_present(locators_rewards.range100box));
        assertTrue("Range 200-500 box is not available", executor.is_element_present(locators_rewards.range1000box));
        assertTrue("Range 500-1000 box is not available", executor.is_element_present(locators_rewards.range5000box));
        assertTrue("Range 1000-5000 box is not available", executor.is_element_present(locators_rewards.range20000box));
        assertTrue("Range 5000-500000 box is not available", executor.is_element_present(locators_rewards.range50000box));
        assertTrue("Range 5000-500000 box is not available", executor.is_element_present(locators_rewards.range100000box));
    }

    public void checkIfSearcherIsDisplayed() {
        assertTrue("Searcher text field is no visible", executor.is_element_present(locators_rewards.searcherTextfield));
        assertTrue("Search button is no visible", executor.is_element_present(locators_rewards.searcherButton));
    }

    public void checkIsRangesButtonsAvailable() {
        executor.click(locators_rewards.rangeShowAll_btn);
        assertTrue("Range 50-100 button is not available", executor.is_element_present(locators_rewards.range100box));
        assertTrue("Range 100-200 button is not available", executor.is_element_present(locators_rewards.range1000box));
        assertTrue("Range 200-500 button is not available", executor.is_element_present(locators_rewards.range5000box));
        assertTrue("Range 500-1000 button is not available", executor.is_element_present(locators_rewards.range20000box));
        assertTrue("Range 1000-5000 button is not available", executor.is_element_present(locators_rewards.range50000box));
        assertTrue("Range 5000-500000 button is not available", executor.is_element_present(locators_rewards.range100000box));
    }

    public void checkIfProperProductsAreasAreVisible() {
        List<WebElement> elements = executor.getWebElementsList(locators_rewards.basicBucketBoxLocator);
        assertTrue("There is no any offering box", elements.size() > 0);
        elements = executor.getWebElementsList(locators_rewards.basicRewardNumberPerRangeLocator);
        assertTrue("There is no any price range box", elements.size() > 0);
    }

    //Browse rewards items card ////////////////////////////////////////////////////////////////////////////////////////
    public void goToRedemptionsBrowserRewards() {
        executor.click(locators_rewards.spendMenu_opt);
        executor.click(locators_rewards.basicDepartmentName);
    }

    public void chooseProperNumberOfElementsPerPage(String ItemsPerPage) throws InterruptedException {
        Thread.sleep(2000);//has to stay
        executor.click(locators_rewards.returnItemsPerPageLocator(ItemsPerPage));
        Thread.sleep(2000);//has to stay
    }

    public void checkIsItamsCardsAvailable(String ItemsPerPage) {
        //check if there is a 20 cards displayed in default view
        assertEquals("There is incorrect number of available Items Cards", Integer.parseInt(ItemsPerPage), executor.getWebElementsList(locators_rewards.itemsCardBoxes).size());
    }

    public void checkIsItamsCardsImagesAvailable(String ItemsPerPage) {
        //check if there is a 40 cards displayed in default view
        assertEquals("There is incorrect number of available Items Cards Images", Integer.parseInt(ItemsPerPage), executor.getWebElementsList(locators_rewards.itemsCardImgs).size());
    }

    public void checkIsItamsCardsTitlesAvailable(String ItemsPerPage) {
        //check if there is a 20 cards displayed in default view
        assertEquals("There is incorrect number of available Items Cards Titles", Integer.parseInt(ItemsPerPage), executor.getWebElementsList(locators_rewards.itemsCardTitles).size());
    }

    public void checkIsItamsCardsPointsComponentAvailable(String ItemsPerPage) {
        //check if there is a 40 cards displayed in default view
        assertEquals("There is incorrect number of available Items Cards Points components", Integer.parseInt(ItemsPerPage), executor.getWebElementsList(locators_rewards.itemsCardPointComponents).size());
    }

    public void checkIsItamsCardsAddBasketButtonsAvailable(String ItemsPerPage) {
        //check if there is a 40 cards displayed in default view
        assertEquals("There is incorrect number of available Items Cards Add to basket buttons", Integer.parseInt(ItemsPerPage), executor.getWebElementsList(locators_rewards.itemsCardAddToBasketButton).size());
    }

    //Redemptions search ///////////////////////////////////////////////////////////////////////////////////////////////
    public void clickGetRewards() {
        executor.click(locators_rewards.getRewards);
    }

    public void searchReward(String RewardName) {
        executor.clear(locators_rewards.searcherTextfield);
        executor.send_keys(locators_rewards.searcherTextfield, RewardName);
        executor.click(locators_rewards.searcherButton);
    }

    public void sortBy(String opt) {
        if (opt.equals("low"))
            executor.selectOptionByText(locators_rewards.sortDrop, "Price low to high");
        else
            executor.selectOptionByText(locators_rewards.sortDrop, "Price high to low");
    }

    public boolean checkIfItemPage(String Item) {
        return executor.return_driver().getTitle().equals("Spend your epoints on " + Item + " | epoints");
    }

    public void click_item(String name) {
        executor.click(locators_rewards.item_page(name));
    }

    // Scenario: Checking if points range filter works properly ////////////////////////////////////////////////////////
    public void clickProperRangeButton(String rangeDown) {
        switch (Integer.parseInt(rangeDown)) {
            case 100:
                executor.click(locators_rewards.range100btn);
                break;
            case 1000:
                executor.click(locators_rewards.range1000btn);
                break;
            case 5000:
                executor.click(locators_rewards.range5000btn);
                break;
            case 20000:
                executor.click(locators_rewards.range20000btn);
                break;
            case 50000:
                executor.click(locators_rewards.range50000btn);
                break;
            case 100000:
                executor.click(locators_rewards.range100000btn);
                break;
            default:
                break;
        }
    }

    public void checkIfProductPointsValueAreInProperRange(String rangeDown, String rangeUp) throws InterruptedException {
        Thread.sleep(4000);//has to stay
        List<WebElement> products = executor.getWebElementsList(locators_rewards.itemsCardBoxes);
        for (WebElement pointsValue : products) {
            assertTrue("Point Value is out of range " + rangeDown + "-" + rangeUp + " - to small " + executor.getText(pointsValue), Integer.parseInt(executor.getText(locators_rewards.itemsCardPointComponents).replace(",", "")) >= Integer.parseInt(rangeDown));
            assertTrue("Point Value is out of range " + rangeDown + "-" + rangeUp + " - to high " + executor.getText(pointsValue), Integer.parseInt(executor.getText(locators_rewards.itemsCardPointComponents).replace(",", "")) <= Integer.parseInt(rangeUp));
        }
    }

    //Spend page refresh - restructure page (RD-3575)
    public void checkVisibilityOfThreeTabs() {
        assertTrue("Department tab is not visible", executor.is_element_present(locators_rewards.departmentTab));
        assertTrue("Bucket tab is not visible", executor.is_element_present(locators_rewards.bucketsTab));
        assertTrue("Reward by epoints tab is not visible", executor.is_element_present(locators_rewards.rewardsByEpointsTab));
    }

    public void chooseDepartmentTab() {
        executor.click(locators_rewards.departmentTab);
    }

    public void checkIfAllDepartmentAreVisible() {
        List<WebElement> departmentsNames = executor.getWebElementsList(locators_rewards.basicDepartmentName);
        for (int i = 0; i < departmentsNames.size(); i++) {
            assertEquals("Department names are not equals", executor.getText(departmentsNames.get(i)), locators_rewards.departments[i]);
        }
    }

    public void chooseRewardsByEpointsTotalTab() {
        executor.click(locators_rewards.rewardsByEpointsTab);
    }

    public void checkIfPointRangeCardsAreVisible() {
        List<WebElement> pointCards = executor.getWebElementsList(locators_rewards.basicSearchRangeBoxLocator);
        assertEquals("Incorrect number of Rewards by epoints cards", 6, pointCards.size());
    }

    public void chooseTopRewardsPickTab() {
        executor.click(locators_rewards.bucketsTab);
    }

    public void checkIfTopRewardPicksCardsAreVisible() {
        List<WebElement> bucketCards = executor.getWebElementsList(locators_rewards.basicBucketBoxLocator);
        assertTrue("Any bucket card is visible", bucketCards.size() > 0);
    }

    // Spend page refresh - add logic for department tab (RD-3595)
    String departmentName;

    public void selectSomeDepartment() {
        List<WebElement> departments = executor.getWebElementsList(locators_rewards.basicDepartmentName);
        int random = executor.return_random_value(departments.size());
        departmentName = executor.getText(departments.get(random));
        executor.click(departments.get(random));
    }

    public void checkIfRedemptionPageWasOpened() throws InterruptedException {
        Thread.sleep(2000);
        //assertTrue("Redemption page was not opened", executor.return_driver().getCurrentUrl().contains(("s_department:" + departmentName).replace(" ", "%20")));
        assertTrue("Redemption page was not opened", executor.return_driver().getCurrentUrl().contains(("/spend/list")));
    }

    public void checkVisibilityOfCategoryFilter() {
        assertTrue("Category filter is not available", executor.is_element_present(locators_rewards.categoryFacet));
    }

    // Spend page refresh - show initial department filter (RD-3605) - reach by product counter (without breadcrumb)
    public void openRedemptionPageUsingProductCounter() {
        executor.click(locators_rewards.itemsToBuyCounter);
    }

    public void checkIfDepartmentFilterIsAvailable() {
        assertTrue("Department filter is not available", executor.is_element_present(locators_rewards.departmentFacet));
    }

    String chosenDepartmentName;
    String chosenDepartmentProductNumber;

    public void chooseSomeDepartmentOption() {
        List<WebElement> departmentNames = executor.getWebElementsList(locators_rewards.departmentToSelectNameBasic);
        List<WebElement> departmentProductNumbers = executor.getWebElementsList(locators_rewards.departmentToSelectProductnumber);
        int random = executor.return_random_value(departmentNames.size());
        chosenDepartmentName = executor.getText(departmentNames.get(random));
        System.out.println("Dept: " + chosenDepartmentName);
        chosenDepartmentProductNumber = executor.getText(departmentProductNumbers.get(random));
        executor.click(departmentNames.get(random));
    }

    public void checkIfAllNeededFacetsAreDisplayed() {
        assertTrue("Category facet is not availabe", executor.is_element_present(locators_rewards.categoryFacet));
        assertTrue("Brand facet is not availabe", executor.is_element_present(locators_rewards.brandFacet));
        assertTrue("Epoints to purchase facet is not availabe", executor.is_element_present(locators_rewards.epointsFacet));
    }

    // Spend page refresh - show initial department filter (RD-3605) - reach by search (with breadcrumb)
    public void checkCorrectnessOfBreadcrumb(String whichLevel) throws InterruptedException {
        Thread.sleep(4000);
        assertTrue("Breadcrumb first element is not correct", executor.getText(locators_rewards.basicLevelBreadcrumbLocator).contains("All Departments"));
        if (whichLevel.equals("department_level")) {
            assertTrue("Breadcrumb is displayed incorrectly - last element", ((executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal) + ": " + executor.getText(locators_rewards.breadcrumbTotalProductsCounter).replace(",", "") + executor.getText(locators_rewards.breadcrumbTotalProductsCounter).replace(",", ""))).contains(chosenDepartmentName + ": " + chosenDepartmentProductNumber));
        } else if (whichLevel.equals("category_level") && !chosenCategoryName.equals("null")) {
            List<WebElement> breadCrumbLevels = executor.getWebElementsList(locators_rewards.basicLevelBreadcrumbLocator);
            assertTrue("Breadcrumb department part is displayed incorrectly", executor.getText(breadCrumbLevels.get(1)).contains(chosenDepartmentName));
            assertTrue("Breadcrumb category part is displayed incorrectly - last element", (executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal) + ": " + executor.getText(locators_rewards.breadcrumbTotalProductsCounter).replace(",", "")).contains(chosenCategoryName + ": " + chosenCategoryProductNumber));
        } else if (whichLevel.equals("brand_level") && !chosenBrandName.equals("null")) {
            List<WebElement> breadCrumbLevels = executor.getWebElementsList(locators_rewards.basicLevelBreadcrumbLocator);
            assertTrue("Breadcrumb department part is displayed incorrectly", executor.getText(breadCrumbLevels.get(1)).contains(chosenDepartmentName));
            assertTrue("Breadcrumb category part is displayed incorrectly", executor.getText(breadCrumbLevels.get(2)).contains(chosenCategoryName));
            assertTrue("Breadcrumb brand part is displayed incorrectly - last element", (executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal) + ": " + executor.getText(locators_rewards.breadcrumbTotalProductsCounter).replace(",", "")).contains(chosenBrandName + ": " + chosenBrandProductNumber));
        } else if (whichLevel.equals("type_level") && !chosenTypeName.equals("null")) {
            List<WebElement> breadCrumbLevels = executor.getWebElementsList(locators_rewards.basicLevelBreadcrumbLocator);
            assertTrue("Breadcrumb department part is displayed incorrectly", executor.getText(breadCrumbLevels.get(1)).contains(chosenDepartmentName));
            assertTrue("Breadcrumb category part is displayed incorrectly", executor.getText(breadCrumbLevels.get(2)).contains(chosenCategoryName));
            if (!chosenBrandName.equals("null")) {
                assertTrue("Breadcrumb brand part is displayed incorrectly", executor.getText(breadCrumbLevels.get(3)).contains(chosenBrandName));
            }
            assertTrue("Breadcrumb type part is displayed incorrectly - last element", (executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal) + ": " + executor.getText(locators_rewards.breadcrumbTotalProductsCounter).replace(",", "")).contains(chosenTypeName + ": " + chosenTypeProductNumber));
        } else if (whichLevel.equals("points_level")) {
            List<WebElement> breadCrumbLevels = executor.getWebElementsList(locators_rewards.basicLevelBreadcrumbLocator);
            System.out.println(breadCrumbLevels.size());
            assertTrue("Breadcrumb department part is displayed incorrectly", executor.getText(breadCrumbLevels.get(1)).contains(chosenDepartmentName));
            assertTrue("Breadcrumb category part is displayed incorrectly", executor.getText(breadCrumbLevels.get(2)).contains(chosenCategoryName));
            if (!chosenBrandName.equals("null")) {
                assertTrue("Breadcrumb brand part is displayed incorrectly", executor.getText(breadCrumbLevels.get(3)).contains(chosenBrandName));
            }
            if (chosenBrandName.equals("null")) {
                if (!chosenTypeName.equals("null")) {
                    assertTrue("Breadcrumb type part is displayed incorrectly", executor.getText(breadCrumbLevels.get(3)).contains(chosenTypeName));
                }
            } else {
                if (!chosenTypeName.equals("null")) {
                    assertTrue("Breadcrumb type part is displayed incorrectly", executor.getText(breadCrumbLevels.get(4)).contains(chosenTypeName));
                }
            }
            List<WebElement> products = executor.getWebElementsList(locators_rewards.itemsCardBoxes);
            if (products.size() > 0) {
                assertTrue("Breadcrumb points part is displayed incorrectly - last element", (executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal)).contains(Low + " - " + High));
            } else {
                assertTrue("Breadcrumb points part is displayed incorrectly - last element", (executor.getText(locators_rewards.lastLevelBradcrumb)).contains(Low + " - " + High));
            }
        }

    }

    public void clickAlldepartmentBreadcrumbOption() {
        executor.click(locators_rewards.basicLevelBreadcrumbLocator);
    }

    // Spend page refresh - points to purchase filter (RD-3645).
    String Low;
    String High;

    public void usePointRangeFilterWithRandomValues() throws InterruptedException {
        Low = Integer.toString(executor.return_random_value(500) + 200);
        High = Integer.toString(executor.return_random_value(2000) + 1000);
        executor.send_keys(locators_rewards.epointsFromField, Low);
        executor.send_keys(locators_rewards.epointsToField, High);
        executor.click(locators_rewards.epointsGoButton);
        Thread.sleep(4000);
    }

    public void checkIfProductPointsValueAreInProperRange() throws InterruptedException {
        Thread.sleep(4000);//has to stay
        checkIfProductPointsValueAreInProperRange(Low, High);
    }

    public void checkIfFoundBetweenInformationHasProperContent() {
        String temp = executor.getText(locators_rewards.foundBetweenInformation);
        if (!temp.equals("No results found")) {
            int highValueFromUI = Integer.parseInt(temp.substring(temp.indexOf("and") + 4).replace(",", "").replace(".", ""));
            int lowValueFromUI = Integer.parseInt(temp.substring(temp.indexOf("between") + 8).replace(",", "").substring(0, Low.length()));
            assertTrue("Found beteween information is incorrect", Integer.parseInt(High) >= highValueFromUI && Integer.parseInt(Low) <= lowValueFromUI);
        }
    }

    // Spend page refresh - breadcrumb logic (RD-3655).
    String chosenCategoryName = "null";
    String chosenCategoryProductNumber = "null";

    public void selectSomeCategory() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> categoryNames = executor.getWebElementsList(locators_rewards.categoryToSelectNameBasic);
        List<WebElement> categoryProductNumbers = executor.getWebElementsList(locators_rewards.categoryToSelectProductnumber);
        if (categoryNames.size() > 1) {
            int random = executor.return_random_value(categoryNames.size() - 1);
            chosenCategoryName = executor.getText(categoryNames.get(random));
            System.out.println("Cat: " + chosenCategoryName);
            chosenCategoryProductNumber = executor.getText(categoryProductNumbers.get(random));
            executor.click(categoryNames.get(random));
            Thread.sleep(2000);
        }
    }

    String chosenBrandName = "null";
    String chosenBrandProductNumber = "null";

    public void selectSomeBrand() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> brandNames = executor.getWebElementsList(locators_rewards.brandToSelectNameBasic);
        List<WebElement> brandProductNumbers = executor.getWebElementsList(locators_rewards.brandToSelectProductnumber);
        if (brandNames.size() > 1) {
            int random = executor.return_random_value(brandNames.size() - 1);
            chosenBrandName = executor.getText(brandNames.get(random));
            System.out.println("Brand: " + chosenBrandName);
            chosenBrandProductNumber = executor.getText(brandProductNumbers.get(random));
            executor.click(brandNames.get(random));
            Thread.sleep(2000);
        }
    }

    String chosenTypeName = "null";
    String chosenTypeProductNumber = "null";

    public void selectSomeType() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> typeNames = executor.getWebElementsList(locators_rewards.typeToSelectNameBasic);
        List<WebElement> typeProductNumbers = executor.getWebElementsList(locators_rewards.typeToSelectProductnumber);
        if (typeNames.size() > 1) {
            int random = executor.return_random_value(typeNames.size() - 1);
            chosenTypeName = executor.getText(typeNames.get(random));
            System.out.println("Type: " + chosenTypeName);
            chosenTypeProductNumber = executor.getText(typeProductNumbers.get(random));
            executor.click(typeNames.get(random));
            Thread.sleep(2000);
        }
    }

    // Spend page refresh - category filter (RD-3615).
    public void checkIfCategoryFilterIsVisible(boolean isVisible) {
        if (isVisible) {
            assertTrue("Category filter is not available", executor.is_element_present(locators_rewards.categoryFacet));
        } else {
            assertFalse("Category filter is not available", executor.is_element_present(locators_rewards.categoryFacet));
        }
    }

    public void clickDepartmentBreadcrumbOption() {
        List<WebElement> breadcrumbLevels = executor.getWebElementsList(locators_rewards.basicLevelBreadcrumbLocator);
        executor.click(breadcrumbLevels.get(1));
    }

    // Spend page refresh - brand filter (RD-3625) - functional + breadcrumb no search

    public void checkIfBrandFilterIsAvailable() {
        assertTrue("Brand filter is not available", executor.is_element_present(locators_rewards.brandFacet));
    }

    public void checkIfNewBrandWasAddedToBreadcrumb() {
        assertTrue("Second brand is not displayed in breadcrumb", executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal).contains(chosenBrandName));
    }

    public void checkBreadcrumbNumberInformation() {
        assertTrue("Second brand is not displayed in breadcrumb", executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal).contains("Brands: 3 selected"));
    }

    public void clickClearButtonInBranSection() throws InterruptedException {
        executor.click(locators_rewards.brandClearButton);
        Thread.sleep(2000);
    }

    public void checkIfOnlyBrandFacedWasCleared() {
        List<WebElement> breadcrumbLevels = executor.getWebElementsList(locators_rewards.basicLevelBreadcrumbLocator);
        assertEquals("Wrong number of breadcrumb numbers after clearing brands", breadcrumbLevels.size(), 1); // one because category was not selected
    }

    // Spend page refresh - brand filter (RD-3625) - functional + search
    public void useBrandSearch() throws InterruptedException {
        List<WebElement> brandList = executor.getWebElementsList(locators_rewards.brandToSelectNameBasic);
        int random = executor.return_random_value(brandList.size());
        chosenBrandName = executor.getText(brandList.get(random));
        executor.send_keys(locators_rewards.brandFacetSearchField, chosenBrandName);
        executor.pressEnterOnChosenLocator(locators_rewards.brandFacetSearchField);
        Thread.sleep(2000);
    }

    public void checkBrandResultsAfterSearching() {
        assertEquals("Searched brand was not found", chosenBrandName, executor.getText(locators_rewards.brandToSelectNameBasic));
    }

    // Spend page refresh - type filter (RD-3635) - functional + breadcrumb no search
    public void selectDepartmentAndCategoryHighlypopulated() throws InterruptedException {
        executor.click(locators_rewards.departmentToSelectProductnumber);
        Thread.sleep(2000);
        List<WebElement> categoryCounters = executor.getWebElementsList(locators_rewards.categoryToSelectProductnumber);
        List<WebElement> categoryNames = executor.getWebElementsList(locators_rewards.categoryToSelectNameBasic);
        int which = 3;
        int howMany=0;
        for(int i=0; i<categoryCounters.size(); i++){
            if((Integer.parseInt(executor.getText(categoryCounters.get(i))))>howMany){
                which = i;
                howMany = Integer.parseInt(executor.getText(categoryCounters.get(i)));
            }
        }
        executor.click(categoryNames.get(which));
        Thread.sleep(2000);
    }

    public void checkIfNewTypeWasAddedToBreadcrumb() {
        assertTrue("Second type is not displayed in breadcrumb", executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal).contains(chosenTypeName));
    }

    public void checkBreadcrumbTypeNumberInformation() {
        assertTrue("Second type is not displayed in breadcrumb", executor.getText(locators_rewards.lastLevelBradcrumbBeforeTotal).contains("Types: 3 selected"));
    }

    public void clickClearButtonInTypeSection() {
        executor.click(locators_rewards.typeClearButton);
    }

    public void checkIfOnlyTypeFacedWasCleared() {
        List<WebElement> breadcrumbLevels = executor.getWebElementsList(locators_rewards.basicLevelBreadcrumbLocator);
        assertEquals("Wrong number of breadcrumb numbers after clearing brands", breadcrumbLevels.size(), 2);
    }

    // Spend page refresh - type filter (RD-3635) - functional +  search
    public void checkIfTypeFilterIsAvailable() {
        assertTrue("Type filter is not available", executor.is_element_present(locators_rewards.typeFacet));
    }

    public void useTypeSearch() throws InterruptedException {
        List<WebElement> typeList = executor.getWebElementsList(locators_rewards.typeToSelectNameBasic);
        int random = executor.return_random_value(typeList.size());
        chosenTypeName = executor.getText(typeList.get(random));
        executor.send_keys(locators_rewards.typeFacetSearchField, chosenTypeName);
        executor.pressEnterOnChosenLocator(locators_rewards.typeFacetSearchField);
        Thread.sleep(2000);
    }

    public void checkTypeResultsAfterSearching() {
        assertEquals("Searched type was not found", chosenTypeName, executor.getText(locators_rewards.typeToSelectNameBasic));
    }

    // Spend page refresh - add counts to filter values (RD-3677).
    static String temp = "department";

    public void checkCorrectnessOfProductNumberOnNavigationBar() {
        List<WebElement> names;
        List<WebElement> counters;
        if (temp.equals("department") && !chosenDepartmentName.equals("null")) {
            assertTrue("Wrong product number after department chosen", executor.getText(locators_az.totalRetailersNumber).contains(chosenDepartmentProductNumber));
            names = executor.getWebElementsList(locators_rewards.departmentToSelectNameBasic);
            counters = executor.getWebElementsList(locators_rewards.departmentToSelectProductnumber);
            Assert.assertEquals("Not all department has product counter", names.size(), counters.size());
            temp = "category";
        } else if (temp.equals("category") && !chosenCategoryName.equals("null")) {
            assertTrue("Wrong product number after category chosen", executor.getText(locators_az.totalRetailersNumber).contains(chosenCategoryProductNumber));
            names = executor.getWebElementsList(locators_rewards.categoryToSelectNameBasic);
            counters = executor.getWebElementsList(locators_rewards.categoryToSelectProductnumber);
            Assert.assertEquals("Not all categories has product counter", names.size(), counters.size());
            temp = "brand";
        } else if (temp.equals("brand") && !chosenBrandName.equals("null")) {
            assertTrue("Wrong product number after brand chosen", executor.getText(locators_az.totalRetailersNumber).contains(chosenBrandProductNumber));
            names = executor.getWebElementsList(locators_rewards.brandToSelectNameBasic);
            counters = executor.getWebElementsList(locators_rewards.brandToSelectProductnumber);
            Assert.assertEquals("Not all brands has product counter", names.size(), counters.size());
            temp = "type";
        } else if (temp.equals("type") && !chosenTypeName.equals("null")) {
            assertTrue("Wrong product number after type chosen", executor.getText(locators_az.totalRetailersNumber).contains(chosenTypeProductNumber));
            names = executor.getWebElementsList(locators_rewards.typeToSelectNameBasic);
            counters = executor.getWebElementsList(locators_rewards.typeToSelectProductnumber);
            Assert.assertEquals("Not all types has product counter", names.size(), counters.size());
        }
    }
}