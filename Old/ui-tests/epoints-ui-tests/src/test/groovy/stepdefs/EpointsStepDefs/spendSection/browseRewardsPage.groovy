package stepdefs.EpointsStepDefs.spendSection
import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
/**
 * Created by kbaranowski on 2014-11-10.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def epointsLink = envVar.epointsLink
int random

    // 1.1 //  ------------------------------------------------------------------------------------- Browse rewards page
    // ------------------------------------------------------------------------------------- browse rewards page content
    Given(~/^Browse rewards page is  opened$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page;  Thread.sleep(1000)
        epoints.clickSpendButton(); Thread.sleep(1000)
        //TODO when counter will be available it should returns
        //epoints.spendPage.clickItemCounterLink()
        waitFor{ epoints.spendPage.departmentCardBasic.size() == 12 }
            random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
            while(epoints.spendPage.departmentCardBasic[random].hasClass('is-disabled')){
                random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
            }
            epoints.spendPage.clickChosenDepartmentCard(random)
        waitFor{ browser.title == envVar.browseRewardsPageTitle }
        Thread.sleep(1000)
            epoints.browseRewardsPage.clickClearAllButton()
    }
    When(~/^User look at browse redemption page$/) { ->
        //leave empty
    }
    Then(~/^He can see that search tool is available$/) { ->
        waitFor{ epoints.browseRewardsPage.searchComponent.isDisplayed() }
        assert epoints.browseRewardsPage.searchComponent.isDisplayed()
    }
    Then(~/^Top points range filter is available$/) { ->
        waitFor{ epoints.browseRewardsPage.epointsFilterModule.isDisplayed() }
        assert epoints.browseRewardsPage.epointsFilterModule.isDisplayed()
    }
    Then(~/^Only department facet is available$/) { ->
        waitFor{ epoints.browseRewardsPage.departmentFacet.isDisplayed() }
        assert epoints.browseRewardsPage.departmentFacet.isDisplayed()
        assert !epoints.browseRewardsPage.categoryFacet.isDisplayed()
        assert !epoints.browseRewardsPage.brandFacet.isDisplayed()
        assert !epoints.browseRewardsPage.typeFacet.isDisplayed()
        assert !epoints.browseRewardsPage.epointsFacet.isDisplayed()
    }
    Then(~/^Search setting wrapper is available$/) { ->
        waitFor{ epoints.browseRewardsPage.topSearchBoxLeft.isDisplayed() }
        waitFor{ epoints.browseRewardsPage.topSearchBoxRight.isDisplayed() }
        assert epoints.browseRewardsPage.topSearchBoxLeft.isDisplayed()
        assert epoints.browseRewardsPage.topSearchBoxRight.isDisplayed()
    }
    Then(~/^Breadcrumb is available$/) { ->
        waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic.isDisplayed() }
        assert epoints.browseRewardsPage.breadcrumbElementBasic.isDisplayed()
    }
    Then(~/^Pagination module is available$/) { ->
        waitFor{ epoints.browseRewardsPage.paginationModule.isDisplayed() }
        assert epoints.browseRewardsPage.paginationModule.isDisplayed()
    }
    Then(~/^Redemption cards are available$/) { ->
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        assert epoints.browseRewardsPage.redemptionCardBasic.isDisplayed()
    }
    Then(~/^Other needed buttons are available$/) { ->
        // TODO top button cannot be found by geb
        waitFor{ epoints.browseRewardsPage.clearAllfiltersButton.isDisplayed() }
        //waitFor{ epoints.browseRewardsPage.backToTopButton }
        assert epoints.browseRewardsPage.clearAllfiltersButton.isDisplayed()
        //assert epoints.browseRewardsPage.backToTopButton.text() == 'Back to top'
    }

    // 1.2 //  ------------------------------------------- Spend page refresh - show initial department filter (RD-3605)
    // ------------------------------------------------------------------------------- availability of department filter
    String chosenDepartmentName
    String chosenDepartmentProductsNumber
    String firstRedemptionBeforeFiltering
    String secondRedemptionBeforeFiltering
    When(~/^User choose some department option$/) { ->
        Thread.sleep(1000)
        waitFor{ epoints.browseRewardsPage.departmentFacetDepartmentButtonBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.browseRewardsPage.departmentFacetDepartmentButtonBasic.size())
        chosenDepartmentName = epoints.browseRewardsPage.departmentFacetDepartmentButtonBasic[random].text()
        waitFor{ epoints.browseRewardsPage.redemptionCardNameBasic[0].isDisplayed() }
        firstRedemptionBeforeFiltering = epoints.browseRewardsPage.redemptionCardNameBasic[0].text()
        epoints.browseRewardsPage.selectChosenDepartment(random)
    }
    Then(~/^Category, brand and epoints range facets will appear$/) { ->
        waitFor{ epoints.browseRewardsPage.categoryFacet.isDisplayed() }
        waitFor{ epoints.browseRewardsPage.brandFacet.isDisplayed() }
        waitFor{ epoints.browseRewardsPage.epointsFacet.isDisplayed() }
        assert epoints.browseRewardsPage.categoryFacet.isDisplayed()
        assert epoints.browseRewardsPage.brandFacet.isDisplayed()
        assert epoints.browseRewardsPage.epointsFacet.isDisplayed()
    }

    // 1.3 //  ------------------------------------------- Spend page refresh - show initial department filter (RD-3605)
    // ------------------------------------------------------------------------------------ working of department filter
    Then(~/^Department filter will disappears$/) { ->
        waitFor{ !epoints.browseRewardsPage.departmentFacet.isDisplayed() }
        assert !epoints.browseRewardsPage.departmentFacet.isDisplayed()
    }
    Then(~/^Redemptions will be filtered according to chosen filter$/) { ->
        waitFor{ firstRedemptionBeforeFiltering != epoints.browseRewardsPage.redemptionCardNameBasic[0].text() }
        assert (firstRedemptionBeforeFiltering != epoints.browseRewardsPage.redemptionCardNameBasic[0].text())
    }
    When(~/^User use 'All department' option from breadcrumb$/) { ->
        epoints.browseRewardsPage.clickBreadcrumbChosenElement(0)
    }
    Then(~/^Department filter will appear$/) { ->
        waitFor{ epoints.browseRewardsPage.departmentFacet.isDisplayed() }
        assert epoints.browseRewardsPage.departmentFacet.isDisplayed()
    }
    Then(~/^Redemption results will be reset$/) { ->
        waitFor{ (firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text()) }
        assert (firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text())
    }

    // 1.4 //  ------------------------------------------------------------------------------------- Browse rewards page
    // -------------------------------------------------------------------------------- Top epoints range filter working
    String rangeFilterOptionName
    When(~/^User chose some top epoints range option from top range filter$/) { ->
        waitFor{ epoints.browseRewardsPage.epointsFilterModuleOptionBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.browseRewardsPage.epointsFilterModuleOptionBasic.size())
        rangeFilterOptionName = epoints.browseRewardsPage.epointsFilterModuleOptionBasic[random].text()
        epoints.browseRewardsPage.clickChosenTopEpointsRangeFilterOption(random)
        Thread.sleep(1000)
    }
    Then(~/^Redemptions will be properly filtered according to chosen range option from top range filter$/) { ->
        int currentpointsValue
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        for(int i=0; i<epoints.browseRewardsPage.redemptionCardBasic.size(); i++) {
            currentpointsValue = Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',', ''))
            checkIfRedemptionPointValuesAreInProperRange(rangeFilterOptionName, currentpointsValue)
        }
    }

    def checkIfRedemptionPointValuesAreInProperRange(String pointsRange, int currentpointsValue){
        if(pointsRange.equals('All rewards')){
            //do nothing
        }else if(pointsRange.equals('100+')){
            assert (100<=currentpointsValue && 1000>currentpointsValue)
        }else if(pointsRange.equals('1,000+')){
            assert (1000<=currentpointsValue && 5000>currentpointsValue)
        }else if(pointsRange.equals('5,000+')){
            assert (5000<=currentpointsValue && 20000>currentpointsValue)
        }else if(pointsRange.equals('20,000+')){
            assert (20000<=currentpointsValue && 50000>currentpointsValue)
        }else if(pointsRange.equals('50,000+')){
            assert (50000<=currentpointsValue && 100000>currentpointsValue)
        }else if(pointsRange.equals('100,000+')){
            assert (100000<=currentpointsValue)
        }
    }

    // 1.5 //  ------------------------------------------------------------------------------------- Browse rewards page
    // -------------------------------------------------------------------- Search, and epoints range filter working DDL
    When(~/^User enter some phrase '(.+?)' into search input field on redemption page$/) {String phrase ->
        epoints.browseRewardsPage.enterPhraseIntoSearchInputField(phrase)
    }
    When(~/^User select some redemption range from ddl on redemption page$/) { ->
        epoints.browseRewardsPage.expandEpointsRangeDDL()
        random = 2//func.returnRandomValue(epoints.browseRewardsPage.rewardByEpointsDDLOption.size())
        rangeFilterOptionName = epoints.browseRewardsPage.rewardByEpointsDDLOption[random].text()
        epoints.browseRewardsPage.clickChosenDDLEpointsRangeFilterOption(random)
    }
    When(~/^User click search button on redemption page$/) { ->
        epoints.browseRewardsPage.clickSearchButton()
        Thread.sleep(2000)
    }
    Then(~/^Results will be appropriate to typed phrase '(.+?)' and chosen points range$/) {String phrase ->
        int currentpointsValue
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        for(int i=0; i< epoints.browseRewardsPage.redemptionCardBasic.size(); i++) {
            assert epoints.browseRewardsPage.redemptionCardNameBasic[i].text().toLowerCase().replace('-', '').contains(phrase.toLowerCase())
            currentpointsValue = Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',', ''))
            checkIfRedemptionPointValuesAreInProperRange(rangeFilterOptionName, currentpointsValue)
            if(i>10){
                break
            }
        }
    }

    // 1.6 //  ------------------------------------------------------------------------------------- Browse rewards page
    // --------------------------------------------------------------------------------- add redemption to basket option
    String chosenRedemptionName
    When(~/^User add to basket chosen redemption$/) { ->
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.browseRewardsPage.redemptionCardBasic.size())
        waitFor{ !epoints.browseRewardsPage.redemptionCardAddedToBasketRibbon.isDisplayed() }
        assert !epoints.browseRewardsPage.redemptionCardAddedToBasketRibbon.isDisplayed()
        chosenRedemptionName = epoints.browseRewardsPage.redemptionCardNameBasic[random].text()
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
    }
    Then(~/^Added redemption can be found in basket preview area$/) { ->
        waitFor{ epoints.basket.basketSmallPreview.text().contains(envVar.returnBasketPreviewQuantityInformation('1')) }
        assert epoints.basket.basketSmallPreview.text().contains(envVar.returnBasketPreviewQuantityInformation('1'))
        epoints.basket.openBasketPreview()
        waitFor{ epoints.basket.basketSmallPreviewOption.text().contains(chosenRedemptionName) }
        waitFor{ epoints.basket.basketSmallPreviewOptionQuantity.text().contains(envVar.returnBasketPreviewContentQuantityInformation('1')) }
        assert epoints.basket.basketSmallPreviewOption.text().contains(chosenRedemptionName)
        assert epoints.basket.basketSmallPreviewOptionQuantity.text().contains(envVar.returnBasketPreviewContentQuantityInformation('1'))
    }
    Then(~/^Information that product was added to basket will appear on product card$/) { ->
        waitFor{ epoints.browseRewardsPage.redemptionCardAddedToBasketRibbon.isDisplayed() }
        assert  epoints.browseRewardsPage.redemptionCardAddedToBasketRibbon.isDisplayed()
    }

    // 1.7 //  ---------------------------------------------------------- Spend page refresh - category filter (RD-3615)
    // ------------------------------------------------------------------------------------------ category facet working

    Given(~/^Some department is chosen on redemption page$/) { ->
        waitFor{ epoints.browseRewardsPage.departmentFacetDepartmentButtonBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.browseRewardsPage.departmentFacetDepartmentButtonBasic.size())
        chosenDepartmentName = epoints.browseRewardsPage.departmentFacetDepartmentButtonBasic[random].text()
        chosenDepartmentProductsNumber = epoints.browseRewardsPage.departmentFacetDepartmentButtonBasicCount[random].text()
        firstRedemptionBeforeFiltering = epoints.browseRewardsPage.redemptionCardNameBasic[0].text()
        epoints.browseRewardsPage.selectChosenDepartment(random)
    }
    String chosenCategoryName
    String chosenCategoryProductsNumber
    When(~/^Some category is chosen on redemption page$/) { ->
        Thread.sleep(1000)
        waitFor { epoints.browseRewardsPage.categoryFacet.isDisplayed() }
        if (epoints.browseRewardsPage.categoryFacetCategoryButtonBasic.size() > 0) {
            waitFor { epoints.browseRewardsPage.categoryFacetCategoryButtonBasic.isDisplayed() }
            random = func.returnRandomValue(epoints.browseRewardsPage.categoryFacetCategoryButtonBasic.size())
            chosenCategoryName = epoints.browseRewardsPage.categoryFacetCategoryButtonBasic[random].text()
            chosenCategoryProductsNumber = epoints.browseRewardsPage.categoryFacetCategoryButtonCounterBasic[random].text()
            waitFor{ epoints.browseRewardsPage.redemptionCardNameBasic[0].isDisplayed() }
            firstRedemptionBeforeFiltering = epoints.browseRewardsPage.redemptionCardNameBasic[0].text()
            epoints.browseRewardsPage.selectChosenCategory(random)
        }
    }
    Then(~/^Category facet will disappear$/) { ->
        if (epoints.browseRewardsPage.categoryFacetCategoryButtonBasic.size() > 0) {
            waitFor { !epoints.browseRewardsPage.categoryFacet.isDisplayed() }
            assert !epoints.browseRewardsPage.categoryFacet.isDisplayed()
        }
    }
    Then(~/^Type filter will appear$/) { ->
        if (epoints.browseRewardsPage.categoryFacetCategoryButtonBasic.size() > 0) {
            waitFor { epoints.browseRewardsPage.typeFacet.isDisplayed() }
            assert epoints.browseRewardsPage.typeFacet.isDisplayed()
        }
    }
    Then(~/^Redemptions will be filtered according to chosen category$/) { ->
        if (epoints.browseRewardsPage.categoryFacetCategoryButtonBasic.size() > 0) {
            waitFor { !(firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text()) }
            assert !(firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text())
        }
    }
    When(~/^User click selected department name option from breadcrumb$/) { ->
        epoints.browseRewardsPage.clickBreadcrumbChosenElement(1)
    }
    Then(~/^Type filter will disappears$/) { ->
        waitFor{ !epoints.browseRewardsPage.typeFacet.isDisplayed() }
        assert !epoints.browseRewardsPage.typeFacet.isDisplayed()
    }
    Then(~/^Category filter will appear$/) { ->
        waitFor{ epoints.browseRewardsPage.categoryFacet.isDisplayed() }
        assert epoints.browseRewardsPage.categoryFacet.isDisplayed()
    }
    Then(~/^Redemption results will be reset to this from only department selected$/) { ->
        waitFor{ (firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text()) }
        assert (firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text())
    }

    // 1.8 //  ------------------------------------------------------------- Spend page refresh - brand filter (RD-3625)
    // --------------------------------------------------------------------------- brand facet working, and clear button
    String chosenBrandName
    String chosenBrandProductsNumber
    When(~/^Some brand is chosen on redemption page$/) { ->
        Thread.sleep(1000)
        waitFor { epoints.browseRewardsPage.brandFacet.isDisplayed() }
        if (epoints.browseRewardsPage.brandFacetBrandButtonBasic.size() > 1) {
            waitFor { epoints.browseRewardsPage.brandFacetBrandButtonBasic.isDisplayed() }
            random = func.returnRandomValue(epoints.browseRewardsPage.brandFacetBrandButtonBasic.size())
            chosenBrandName = epoints.browseRewardsPage.brandFacetBrandButtonBasic[random].text()
            chosenBrandProductsNumber = epoints.browseRewardsPage.brandFacetBrandButtonCounterBasic[random].text()
            waitFor{ epoints.browseRewardsPage.redemptionCardNameBasic[0].isDisplayed() }
            firstRedemptionBeforeFiltering = epoints.browseRewardsPage.redemptionCardNameBasic[0].text()
            epoints.browseRewardsPage.selectChosenBrand(random)
        }
    }
    When(~/^Redemptions will be filtered according to chosen brand$/) { ->
        if (epoints.browseRewardsPage.brandFacetBrandButtonBasic.size() > 1) {
            waitFor { !(firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text()) }
            assert !(firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text())
        }
    }
    When(~/^User click clear button on brand facet$/) { ->
        epoints.browseRewardsPage.clickClearButtonOnBrandFacet()
    }

    // 1.9 //  ------------------------------------------------------------- Spend page refresh - brand filter (RD-3625)
    // -------------------------------------------------------------------------------------- brand facet search working
    When(~/^User type some brand name into brand facet search$/) { ->
        waitFor { epoints.browseRewardsPage.brandFacet.isDisplayed() }
        if (epoints.browseRewardsPage.brandFacetSearchInputField.isDisplayed()) {
            random = func.returnRandomValue(epoints.browseRewardsPage.brandFacetBrandButtonBasic.size())
            chosenBrandName = epoints.browseRewardsPage.brandFacetBrandButtonBasic[random].text()
            epoints.browseRewardsPage.enterPhraseIntoBrandSearch(chosenBrandName)
            Thread.sleep(1000)
        }
    }
    Then(~/^Brands on brand facet list will match typed name$/) { ->
        if (epoints.browseRewardsPage.brandFacetSearchInputField.isDisplayed()) {
            for (int i = 0; i < epoints.browseRewardsPage.brandFacetBrandButtonBasic.size(); i++) {
                assert epoints.browseRewardsPage.brandFacetBrandButtonBasic[i].text().toLowerCase().contains(chosenBrandName.toLowerCase())
            }
        }
    }

    // 1.10 //  ------------------------------------------------------------- Spend page refresh - type filter (RD-3635)
    // ---------------------------------------------------------------------------- type facet working, and clear button
    String chosenTypeName
    String chosenTypeProductsNumber
    When(~/^Some type is chosen on redemption page$/) { ->
        Thread.sleep(1000)
        waitFor { epoints.browseRewardsPage.typeFacet.isDisplayed() }
        if (epoints.browseRewardsPage.typeFacetTypeButtonBasic.size() > 1) {
            waitFor { epoints.browseRewardsPage.typeFacetTypeButtonBasic.isDisplayed() }
            random = func.returnRandomValue(epoints.browseRewardsPage.typeFacetTypeButtonBasic.size())
            chosenTypeName = epoints.browseRewardsPage.typeFacetTypeButtonBasic[random].text()
            chosenTypeProductsNumber = epoints.browseRewardsPage.typeFacetTypeButtonCounterBasic[random].text()
            waitFor{ epoints.browseRewardsPage.redemptionCardNameBasic[0].isDisplayed() }
            firstRedemptionBeforeFiltering = epoints.browseRewardsPage.redemptionCardNameBasic[0].text()
            epoints.browseRewardsPage.selectChosenType(random)
        }
    }
    When(~/^Redemptions will be filtered according to chosen type$/) { ->
        if (epoints.browseRewardsPage.typeFacetTypeButtonBasic.size() > 1) {
            waitFor { !(firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text()) }
            assert !(firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text())
        }
    }
    When(~/^User click clear button on type facet$/) { ->
        epoints.browseRewardsPage.clickClearButtonOnTypeFacet()
    }
    Then(~/^Redemption results will be reset to this from department and category selected$/) { ->
        if (epoints.browseRewardsPage.typeFacetTypeButtonBasic.size() > 1) {
            waitFor { (firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text()) }
            assert (firstRedemptionBeforeFiltering == epoints.browseRewardsPage.redemptionCardNameBasic[0].text())
        }
    }

    // 1.11 //  ------------------------------------------------------------- Spend page refresh - type filter (RD-3635)
    // --------------------------------------------------------------------------------------- type facet search working
    When(~/^User type some type name into type facet search$/) { ->
        waitFor { epoints.browseRewardsPage.typeFacet.isDisplayed() }
        if (epoints.browseRewardsPage.typeFacetSearchInputField.isDisplayed()) {
            random = func.returnRandomValue(epoints.browseRewardsPage.typeFacetTypeButtonBasic.size())
            chosenTypeName = epoints.browseRewardsPage.typeFacetTypeButtonBasic[random].text()
            epoints.browseRewardsPage.enterPhraseIntoTypeSearch(chosenTypeName)
            Thread.sleep(1000)
        }
    }
    Then(~/^Types on type facet list will match typed name$/) { ->
        if (epoints.browseRewardsPage.typeFacetSearchInputField.isDisplayed()) {
            for (int i = 0; i < epoints.browseRewardsPage.typeFacetTypeButtonBasic.size(); i++) {
                assert epoints.browseRewardsPage.typeFacetTypeButtonBasic[i].text().toLowerCase().contains(chosenTypeName.toLowerCase())
            }
        }
    }

    // 1.12 //  --------------------------------------------- Spend page refresh - add counts to filter values (RD-3677)
    // -------------------------------------------------------------------------------------------- counters correctness
    Then(~/^Proper number of product should be displayed on navigation bar according to chosen department$/) { ->
        waitFor{ epoints.browseRewardsPage.outOfElement.text().contains(chosenDepartmentProductsNumber) }
        assert epoints.browseRewardsPage.outOfElement.text().contains(chosenDepartmentProductsNumber)
    }
    Then(~/^Proper number of product should be displayed on navigation bar according to chosen category$/) { ->
        waitFor{ epoints.browseRewardsPage.outOfElement.text().contains(chosenCategoryProductsNumber) }
        assert epoints.browseRewardsPage.outOfElement.text().contains(chosenCategoryProductsNumber)
    }
    Then(~/^Proper number of product should be displayed on navigation bar according to chosen brand$/) { ->
        if (epoints.browseRewardsPage.brandFacetBrandButtonBasic.size() > 1) {
            waitFor { epoints.browseRewardsPage.outOfElement.text().contains(chosenBrandProductsNumber) }
            assert epoints.browseRewardsPage.outOfElement.text().contains(chosenBrandProductsNumber)
        }
    }
    Then(~/^Proper number of product should be displayed on navigation bar according to chosen type$/) { ->
        if (epoints.browseRewardsPage.typeFacetTypeButtonBasic.size() > 1) {
            waitFor { epoints.browseRewardsPage.outOfElement.text().contains(chosenTypeProductsNumber) }
            assert epoints.browseRewardsPage.outOfElement.text().contains(chosenTypeProductsNumber)
        }
    }

    // 1.13 //  ----------------------------------------------- Spend page refresh - points to purchase filter (RD-3645)
    // ---------------------------------------------------------------------------------------- number of epoints filter
    Given(~/^Number of epoints filter is visible$/) { ->
        waitFor{ epoints.browseRewardsPage.epointsFacet.isDisplayed() }
        assert epoints.browseRewardsPage.epointsFacet.isDisplayed()
    }
    int fromEpointsValue
    int toEpointsValue
    When(~/^User enter epoints 'from' and 'to' values$/) { ->
        waitFor{ epoints.browseRewardsPage.redemptionCardNameBasic[0].isDisplayed() }
        firstRedemptionBeforeFiltering = epoints.browseRewardsPage.redemptionCardNameBasic[0].text()
        fromEpointsValue = func.returnRandomValue(500)+3000
        toEpointsValue = func.returnRandomValue(500)+5000
        epoints.browseRewardsPage.enterFromValue(fromEpointsValue); Thread.sleep(500)
        epoints.browseRewardsPage.enterToValue(toEpointsValue); Thread.sleep(500)
    }
    When(~/^Click Go button on number of epoints filter$/) { ->
        epoints.browseRewardsPage.clickGoButton(); Thread.sleep(2000)
    }
    int fromEpointsValueReal
    int toEpointsValueReal
    Then(~/^Redemptions will be filtered according to chosen 'from' and 'to' range$/) { ->
        String temp = epoints.browseRewardsPage.epointsFacetRangeInfo.text()
        if(!(temp == 'No results found.')) {
            toEpointsValueReal = Integer.parseInt(temp.substring(temp.indexOf("and") + 4).replace(",", "").replace(".", ""))
            fromEpointsValueReal = Integer.parseInt(temp.substring(temp.indexOf("between") + 8).replace(",", "").substring(0, Integer.toString(fromEpointsValue).length()));
            assert fromEpointsValue<=fromEpointsValueReal
            assert toEpointsValue>=toEpointsValueReal
        }
        def currentRedemptionPouintsValue
        for(int i=0; i<epoints.browseRewardsPage.redemptionCardPointsValueBasic.size(); i++){
            currentRedemptionPouintsValue = Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',',''))
            assert (currentRedemptionPouintsValue>=fromEpointsValueReal)
            assert (currentRedemptionPouintsValue<=toEpointsValueReal)
        }
    }
    When(~/^User click clear button on number of epoints facet$/) { ->
        epoints.browseRewardsPage.clickClearButtonOnEpointsFacet()
    }

    // 1.14 //  ------------------------------------------------------------------------------------ Browse rewards page
    // ------------------------------------------------------------------------------------------------ clear all button
    When(~/^User click 'clear all' button$/) { ->
        epoints.browseRewardsPage.clickClearAllButton()
    }
    Then(~/^Brand filter will disappears$/) { ->
        waitFor{ !epoints.browseRewardsPage.brandFacet.isDisplayed() }
        assert !epoints.browseRewardsPage.brandFacet.isDisplayed()
    }

    // 1.15 //  -------------------------------------------------------- Spend page refresh - breadcrumb logic (RD-3655)
    // ------------------------------------------------------------------------------ breadcrumb independent more than 3
    When(~/^User select three brands$/) { ->
        Thread.sleep(1000)
        waitFor{ epoints.browseRewardsPage.brandFacet.isDisplayed() }
        if(epoints.browseRewardsPage.brandFacetBrandButtonBasic.size()>=3){
            epoints.browseRewardsPage.selectChosenBrand(0); Thread.sleep(500)
            epoints.browseRewardsPage.selectChosenBrand(1); Thread.sleep(500)
            epoints.browseRewardsPage.selectChosenBrand(2); Thread.sleep(500)
        }
    }
    Then(~/^Breadcrumb shows Three brands sentence$/) { ->
        if(epoints.browseRewardsPage.brandFacetBrandButtonBasic.size()>=3){
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains('Brands: 3 selected') }
            assert epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains('Brands: 3 selected')
        }
    }
    When(~/^User select three types$/) { ->
        Thread.sleep(1000)
        waitFor{ epoints.browseRewardsPage.typeFacet.isDisplayed() }
        if(epoints.browseRewardsPage.typeFacetTypeButtonBasic.size()>=3){
            epoints.browseRewardsPage.selectChosenType(0); Thread.sleep(500)
            epoints.browseRewardsPage.selectChosenType(1); Thread.sleep(500)
            epoints.browseRewardsPage.selectChosenType(2); Thread.sleep(500)
        }
    }
    Then(~/^Breadcrumb shows Three types sentence$/) { ->
        if(epoints.browseRewardsPage.typeFacetTypeButtonBasic.size()>=3 && epoints.browseRewardsPage.brandFacetBrandButtonBasic.size()>=3){
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[4].text().contains('Types: 3 selected') }
            assert epoints.browseRewardsPage.breadcrumbElementBasic[4].text().contains('Types: 3 selected')
        }else if(epoints.browseRewardsPage.typeFacetTypeButtonBasic.size()>=3 && epoints.browseRewardsPage.brandFacetBrandButtonBasic.size()< 3){
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains('Types: 3 selected') }
            assert epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains('Types: 3 selected')
        }
    }
    // 1.16 //  -------------------------------------------------------- Spend page refresh - breadcrumb logic (RD-3655)
    // ------------------------------------------------------------------------------------------- all filters used once
    Then(~/^Breadcrumb shows all facet values in proper order$/) { ->
        waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[1].text().contains(chosenDepartmentName) }
        waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[2].text().contains(chosenCategoryName) }
        assert epoints.browseRewardsPage.breadcrumbElementBasic[1].text().contains(chosenDepartmentName)
        assert epoints.browseRewardsPage.breadcrumbElementBasic[2].text().contains(chosenCategoryName)
        if(epoints.browseRewardsPage.brandFacetBrandButtonBasic.size()>=1){
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains(chosenBrandName) }
            assert epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains(chosenBrandName)
        }
        if(epoints.browseRewardsPage.typeFacetTypeButtonBasic.size()>=1 && epoints.browseRewardsPage.brandFacetBrandButtonBasic.size()>=1){
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[4].text().contains((chosenTypeName)) }
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[4].text().contains((chosenTypeName)) }
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[5].text().contains(Integer.toString(fromEpointsValue)) }
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[5].text().contains(Integer.toString(toEpointsValue)) }
            assert epoints.browseRewardsPage.breadcrumbElementBasic[4].text().contains((chosenTypeName))
            assert epoints.browseRewardsPage.breadcrumbElementBasic[5].text().contains(Integer.toString(fromEpointsValue))
            assert epoints.browseRewardsPage.breadcrumbElementBasic[5].text().contains(Integer.toString(toEpointsValue))
        }else if(epoints.browseRewardsPage.typeFacetTypeButtonBasic.size()>=1 && epoints.browseRewardsPage.brandFacetBrandButtonBasic.size()< 1){
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains(chosenTypeName) }
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains(chosenTypeName) }
            waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[4].text().contains(fromEpointsValue) }
            waitFor{ epoints.browseRewardsPage.outOfElement.text().contains(epoints.browseRewardsPage.breadcrumbTotalElement.replace(',','').replace(' item found','').replace(' items found','')) }
            assert epoints.browseRewardsPage.breadcrumbElementBasic[3].text().contains(chosenTypeName)
            assert epoints.browseRewardsPage.breadcrumbElementBasic[4].text().contains(fromEpointsValue)
            assert epoints.browseRewardsPage.outOfElement.text().contains(epoints.browseRewardsPage.breadcrumbTotalElement.replace(',','').replace(' item found','').replace(' items found',''))
        }
    }

    // 1.17 //  ------------------------------------------------------------------------------------ Browse rewards page
    // --------------------------------------------------------------------------------------------- points order filter
    When(~/^User chose points order option '(.+?)'$/) { String orderOption ->
        epoints.browseRewardsPage.expandExpiryFilter()
        if(orderOption == "Relevance"){
            epoints.browseRewardsPage.clickChosenExpiryfilterOption(0)
        }else if(orderOption == "Points low to high"){
            epoints.browseRewardsPage.clickChosenExpiryfilterOption(1)
        }else if(orderOption == "Points high to low"){
            epoints.browseRewardsPage.clickChosenExpiryfilterOption(2)
        }
        Thread.sleep(2000)
    }
    Then(~/^Redemption results will be displayed in proper order '(.+?)'$/) { String orderOption ->
        waitFor{ epoints.browseRewardsPage.redemptionCardPointsValueBasic.isDisplayed() }
        int previousValue = Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[0].text().replace(',',''))
        for(int i=1; i<epoints.browseRewardsPage.redemptionCardPointsValueBasic.size(); i++) {
            if (orderOption == "Relevance") {
                waitFor{ epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].isDisplayed() }
                assert epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].isDisplayed()
            } else if (orderOption == "Points low to high") {
                waitFor{ Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',','')) >= Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i-1].text().replace(',','')) }
                assert Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',','')) >= Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i-1].text().replace(',',''))
            } else if (orderOption == "Points high to low") {
                waitFor{ Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',','')) <= Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i-1].text().replace(',','')) }
                assert Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',','')) <= Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i-1].text().replace(',',''))
            }
            if(i>10){
                break
            }
        }
    }
    // 1.18 //  ------------------------------------------------------------------------------------ Browse rewards page
    // ------------------------------------------------------------- bottom pagination, working of next/previous buttons
    Given(~/^Visible redemption number is set to 20$/) {  ->
        epoints.browseRewardsPage.clickItemPerPage20(); Thread.sleep(1000)
    }
    When(~/^User click next page button on spend$/) { ->
        epoints = page //for switching context in spend page pagination tests
        waitFor{ epoints.browseRewardsPage.pageNumberActiveBasic.isDisplayed() }
        waitFor{ epoints.browseRewardsPage.pageNumberActiveBasic.text().contains('1') }
        assert epoints.browseRewardsPage.pageNumberActiveBasic.text().contains('1')
        epoints.browseRewardsPage.clickNextPageButton()
        Thread.sleep(1000)
    }
    Then(~/^Page will be changed to next on spend$/) { ->
        waitFor{ epoints.browseRewardsPage.pageNumberActiveBasic.text().contains('2') }
        assert epoints.browseRewardsPage.pageNumberActiveBasic.text().contains('2')
    }
    Then(~/^Showing module will be increased on spend$/) { ->
        System.out.println(epoints.browseRewardsPage.showingElement.text())
        waitFor{ epoints.browseRewardsPage.showingElement.text().contains('Showing 21 - 40') }
        assert epoints.browseRewardsPage.showingElement.text().contains('Showing 21 - 40')
    }
    When(~/^User click previous page button on spend$/) { ->
        epoints.browseRewardsPage.clickPreviousPageButton()
        Thread.sleep(1000)
    }
    When(~/^Showing module will be decreased on spend$/) { ->
        waitFor{ epoints.browseRewardsPage.showingElement.text().contains('Showing 1 - 20') }
        assert epoints.browseRewardsPage.showingElement.text().contains('Showing 1 - 20')
    }
    Then(~/^Page will be changed to previous on spend$/) { ->
        waitFor{ epoints.browseRewardsPage.pageNumberActiveBasic.text().contains('1') }
        assert epoints.browseRewardsPage.pageNumberActiveBasic.text().contains('1')
    }

    // 1.19 //  ------------------------------------------------------------------------------------ Browse rewards page
    // ------------------------------------------------------------------ bottom pagination, working of page numbers button
    int numberOfProducts
    Then(~/^He can see tat proper number of page is displayed according to 'out of' information on spend$/) { ->
        waitFor{ epoints.browseRewardsPage.outOfElement.isDisplayed() }
        numberOfProducts = Integer.parseInt(epoints.browseRewardsPage.outOfElement.text().replace("(out of ","").replace(")",""))
        if((numberOfProducts % 20) == 0){  // 20 because default number of products per page is 20
            waitFor{ Integer.parseInt(epoints.browseRewardsPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20) }
            assert Integer.parseInt(epoints.browseRewardsPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20)
        }else{
            waitFor{ Integer.parseInt(epoints.browseRewardsPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20 +1) }
            assert Integer.parseInt(epoints.browseRewardsPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20 +1)
        }
    }
    When(~/^User use some bottom pagination page number on spend$/) { ->
        random = func.returnRandomValue(3)+1
        epoints.browseRewardsPage.clickChosenPageNumber(random)
        Thread.sleep(1000)
    }
    Then(~/^proper page will be displayed on spend$/) { ->
        System.out.println(random)
        System.out.println(epoints.browseRewardsPage.pageNumberActiveBasic.text())
        waitFor{ epoints.browseRewardsPage.pageNumberActiveBasic.text().contains((random+1).toString()) }
        waitFor{ epoints.browseRewardsPage.showingElement.text().contains('Showing '+(random*20+1).toString()+" - "+((random+1)*20).toString()) }
        assert epoints.browseRewardsPage.pageNumberActiveBasic.text().contains((random+1).toString())
        assert epoints.browseRewardsPage.showingElement.text().contains('Showing '+(random*20+1).toString()+" - "+((random+1)*20).toString())
    }

    // 1.20 //  ------------------------------------------------------------------------------------ Browse rewards page
    // --------------------------------------------------------------------------------------------- top search box, arrows
    When(~/^User click right arrow on top search box on spend$/) { ->
        epoints = page //for switching context in spend page pagination tests
        epoints.browseRewardsPage.clickRightArrowOnTopSearchBox()
        Thread.sleep(1000)
    }
    When(~/^User click left arrow on top search box on spend$/) { ->
        epoints.browseRewardsPage.clickLeftArrowOnTopSearchBox()
        Thread.sleep(1000)
    }

    // 1.21 //  ------------------------------------------------------------------------------------ Browse rewards page
    // ---------------------------------------------------------------------------------- top search box, items per page
    When(~/^User change 'item per page' parameter on  top search box$/) { ->
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        checkNumberOfElementsOnPage(40) // by default
        epoints.browseRewardsPage.clickItemPerPage20(); Thread.sleep(2000)
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        checkNumberOfElementsOnPage(20)
        epoints.browseRewardsPage.clickItemPerPage100(); Thread.sleep(2000)
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        checkNumberOfElementsOnPage(100)
        epoints.browseRewardsPage.clickItemPerPage40(); Thread.sleep(2000)
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        checkNumberOfElementsOnPage(40)
    }
    Then(~/^Number of elements on page will be changed$/) { ->
        // proper assertions made in previous step to check all three cases in one test
    }

    def checkNumberOfElementsOnPage(int number){
        int outOfValue = Integer.parseInt( $('.pagination-totalItems').text().replace("(out of ","").replace(")","") )
        if(number <=  outOfValue ){
            waitFor{ ($('.ProductCard').size() == number) }
            assert  ($('.ProductCard').size() == number)
        }else{
            waitFor{ ($('.ProductCard').size() == outOfValue) }
            assert  ($('.ProductCard').size() == outOfValue)
        }
    }