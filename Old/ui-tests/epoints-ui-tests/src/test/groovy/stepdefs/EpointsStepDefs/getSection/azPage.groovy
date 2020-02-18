package stepdefs.EpointsStepDefs.getSection

import cucumber.api.java.Before
import geb.Browser
import mysqlConnection.jdbc
import org.openqa.selenium.By
import org.openqa.selenium.Cookie
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 02.10.14
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def actions = new Actions(browser.driver)

def epointsLink = envVar.epointsLink
Given(~/^Epoints page is opened and user is logged in$/) { ->
    epointsPage.url = epointsLink
    to epointsPage
    epoints = page
    epoints.clickSignInButton()
    epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
    epoints.signModule.clickSignInButton()
    waitFor{ epoints.topnavSignOutButton.isDisplayed() }
}

Given(~/^Epoints page is opened and user is logged in clear$/) { ->
    epointsPage.url = epointsLink
    to epointsPage
    epoints = page
    func.clearCookiesAndStorage()
    epoints.clickSignInButton()
    epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
    epoints.signModule.clickSignInButton()
    waitFor{ epoints.topnavSignOutButton.isDisplayed() }
}

    // 1.1 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ---------------------------------------------------------------------------------------- check content of az page
    Given(~/^A-Z page is opened$/) { ->
        to epointsPage
        epoints = page
        Thread.sleep(1000)
        epoints.clickGetButton()
        Thread.sleep(1000)
        epoints.clickShopOnlineButtonAngular()
    }
    When(~/^User looks at az page$/) { ->
        assert browser.getTitle().contains(envVar.azPageTitle)
    }
    Then(~/^Search component is visible$/) { ->
        // TODO when search will be ready, add headers and all text to check
    }
    Then(~/^Voucher link is available$/) { ->
        waitFor{ epoints.azPage.voucherButton.isDisplayed() }
        assert epoints.azPage.voucherButton.isDisplayed()
    }
    Then(~/^Department facet is available$/) { ->
        ((JavascriptExecutor) browser.getDriver()).executeScript("scroll(0,650);"); Thread.sleep(1000)
        waitFor{ epoints.azPage.departmentFacet.isDisplayed() }
        assert epoints.azPage.departmentFacet.isDisplayed()
    }
    Then(~/^Letter facet is available$/) { ->
        waitFor{ epoints.azPage.azFacet.isDisplayed() }
        assert epoints.azPage.azFacet.isDisplayed()
    }
    Then(~/^Bottom pagination is available$/) { ->
        waitFor{ epoints.azPage.paginationModule.isDisplayed() }
        assert epoints.azPage.paginationModule.isDisplayed()
    }
    Then(~/^Retailer cards are available$/) { ->
        waitFor{ epoints.azPage.searchResults.isDisplayed() }
        assert epoints.azPage.searchResults.isDisplayed()
    }
    Then(~/^Search box is available$/) { ->
        waitFor{ epoints.azPage.topSearchBoxLeft.isDisplayed() }
        assert epoints.azPage.topSearchBoxLeft.isDisplayed()
        assert epoints.azPage.topSearchBoxRight.isDisplayed()
    }
    Then(~/^Favourite module is available$/) { ->
        waitFor{ epoints.azPage.favouriteButtonDisabled.isDisplayed() }
        assert epoints.azPage.favouriteButtonDisabled.isDisplayed()
    }

    // 1.2 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ----------------------------------------------------------------------------------------- working of letter facet
    Then(~/^User can use letter filter$/) { ->
        waitFor{ epoints.azPage.azFacet.isDisplayed() }
        waitFor{ epoints.azPage.azFacetHeader.text() == envVar.azFacetName }
        assert epoints.azPage.azFacet.isDisplayed()
        assert epoints.azPage.azFacetHeader.text() == envVar.azFacetName
    }
    String letter
    Then(~/^Results will match chosen letter$/) { ->
        for(int i=0; i<epoints.azPage.azFacetLetterButtonBasic.size()-1; i++){
            if(epoints.azPage.azFacetLetterButtonBasic[i].attr('class') == 'disabled'){
                continue
            }
            epoints.azPage.clickChosenLetter(i);
            Thread.sleep(5000)
            letter = epoints.azPage.azFacetLetterButtonBasic[i].text()
            for(int j=0; j<epoints.azPage.retailerCardBasicSearchResults.size(); j++){
                if(j>3){
                    break
                }else{
                    assert epoints.azPage.retailerCardBasicSearchResults[j].attr('data-retailername').toLowerCase().charAt(0) == letter.toLowerCase()
                }
            }
            if(i>5){
                break
            }
        }
    }

    // 1.3 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ---------------------------------------------------------------------------- working of clear button on az filter
    String firstRetailer
    String secondRetailer
    When(~/^User select some letter from az filter$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        firstRetailer = epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername')
        random = func.returnRandomValue(epoints.azPage.azFacetLetterButtonBasic.size())
        epoints.azPage.clickChosenLetter(random);
        letter = epoints.azPage.azFacetLetterButtonBasic[random].text()
        waitFor{ epoints.azPage.azFacetLetterButtonActiveBasic.isDisplayed() }
        waitFor{ !(epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername') == firstRetailer) }
        assert epoints.azPage.azFacetLetterButtonActiveBasic.isDisplayed()
        assert !(epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername') == firstRetailer)
    }
    When(~/^Click clear button on az filter$/) { ->
        epoints.azPage.clickClearButtonOnAzFacet()
    }
    Then(~/^Previous selection will be disabled and filter reset$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        waitFor{ (epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername') == firstRetailer) }
        assert !epoints.azPage.azFacetLetterButtonActiveBasic.isDisplayed()
        assert (epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername') == firstRetailer)
    }

    // 1.4 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ----------------------------------------------------------------------------- working of search allowed retailers
    When(~/^User enter '(.+)' into search$/) {String phrase ->
        epoints.azPage.enterPhraseIntoSearch(phrase)
    }
    When(~/^Click Search button$/) { ->
        epoints.azPage.clickSearchButton(); Thread.sleep(1000)
    }
    Then(~/^Proper results will be displayed according to typed '(.+)'$/) {String phrase ->
        for(int i =0; i<epoints.azPage.retailerCardBasicSearchResults.size(); i++){
            assert (epoints.azPage.retailerCardBasicSearchResults[i].attr('data-retailername').toLowerCase()).contains(phrase)
        }
    }

    // 1.5 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ------------------------------------------------------------------------- working of search not allowed retailers
    Then(~/^Proper message about not found retailer '(.+)' will be displayed$/) {String phrase ->
        waitFor{ epoints.azPage.noResultsFoundMessage.text() == envVar.returnInformationAboutNotFoundRetailer(phrase) }
        assert epoints.azPage.noResultsFoundMessage.text() == envVar.returnInformationAboutNotFoundRetailer(phrase)
    }

    // 1.6 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ------------------------------------------------------------- bottom pagination, working of next/previous buttons
    When(~/^User click next page button$/) { ->
        epoints = page //for switching context in spend page pagination tests
        waitFor{ epoints.azPage.pageNumberActiveBasic.isDisplayed() }
        waitFor{ epoints.azPage.pageNumberActiveBasic.text().contains('1') }
        assert epoints.azPage.pageNumberActiveBasic.text().contains('1')
        epoints.azPage.clickNextPageButton()
        Thread.sleep(1000)
    }
    Then(~/^Page will be changed to next$/) { ->
        waitFor{ epoints.azPage.pageNumberActiveBasic.text().contains('2') }
        assert epoints.azPage.pageNumberActiveBasic.text().contains('2')
    }
    Then(~/^Showing module will be increased$/) { ->
        waitFor{ epoints.azPage.showingElement.text().contains('Showing 21 - 40') }
        assert epoints.azPage.showingElement.text().contains('Showing 21 - 40')
    }
    When(~/^User click previous page button$/) { ->
        epoints.azPage.clickPreviousPageButton()
        Thread.sleep(1000)
    }
    When(~/^Showing module will be decreased$/) { ->
        waitFor{ epoints.azPage.showingElement .text().contains('Showing 1 - 20') }
        assert epoints.azPage.showingElement .text().contains('Showing 1 - 20')
    }
    Then(~/^Page will be changed to previous$/) { ->
        waitFor{ epoints.azPage.pageNumberActiveBasic.text().contains('1') }
        assert epoints.azPage.pageNumberActiveBasic.text().contains('1')
    }

    // 1.7 //  ------------------------------------------------------------------------------------------------ A-Z page
    // --------------------------------------------------------------- bottom pagination, working of page numbers button
    When(~/^User looks on bottom pagination component$/) { ->
        // leave empty
    }
    int numberOfProducts
    Then(~/^He can see tat proper number of page is displayed according to 'out of' information$/) { ->
       waitFor{ epoints.azPage.outOfElement.isDisplayed() }
       numberOfProducts = Integer.parseInt(epoints.azPage.outOfElement.text().replace("(out of ","").replace(")",""))
       if((numberOfProducts % 20) == 0){  // 20 because default number of products per page is 20
           waitFor{ Integer.parseInt(epoints.azPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20) }
           assert Integer.parseInt(epoints.azPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20)
       }else{
            waitFor{ Integer.parseInt(epoints.azPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20 +1) }
            assert Integer.parseInt(epoints.azPage.pageNumberBasic.last().text()) == (int)(numberOfProducts / 20 +1)
       }
    }
    int random
    When(~/^User use some bottom pagination page number$/) { ->
        random = func.returnRandomValue(8)
        epoints.azPage.clickChosenPageNumber(random)
        Thread.sleep(1000)
    }
    Then(~/^proper page will be displayed$/) { ->
        waitFor{ epoints.azPage.pageNumberActiveBasic.text().contains((random+1).toString()) }
        waitFor{ epoints.azPage.showingElement.text().contains('Showing '+(random*20+1).toString()+" - "+((random+1)*20).toString()) }
        assert epoints.azPage.pageNumberActiveBasic.text().contains((random+1).toString())
        assert epoints.azPage.showingElement.text().contains('Showing '+(random*20+1).toString()+" - "+((random+1)*20).toString())
    }

    // 1.8 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ---------------------------------------------------------------- bottom pagination, working of back to top button
    When(~/^User scroll page down$/) { ->
        //assert !epoints.azPage.backToTopButton.isDisplayed()
        Thread.sleep(4000)
        ((JavascriptExecutor) browser.getDriver()).executeScript("scroll(0,500);"); Thread.sleep(2000)
        waitFor{ epoints.azPage.backToTopButton.isDisplayed() }
        //assert epoints.azPage.backToTopButton.isDisplayed()
    }
    When(~/^User click 'back to top' button$/) { ->
        epoints.azPage.clickBackToTopButton()
        Thread.sleep(1000)
    }
    Then(~/^Page will be scrolled to top$/) { ->
        waitFor{ !epoints.azPage.backToTopButton.isDisplayed() }
        assert !epoints.azPage.backToTopButton.isDisplayed()
    }

    // 1.9 //  ------------------------------------------------------------------------------------------------ A-Z page
    // ------------------------------------------------------------------------------------------ top search box, arrows
    When(~/^User click right arrow on top search box$/) { ->
        epoints = page //for switching context in spend page pagination tests
        epoints.azPage.clickRightArrowOnTopSearchBox()
        Thread.sleep(1000)
    }
    When(~/^User click left arrow on top search box$/) { ->
        epoints.azPage.clickLeftArrowOnTopSearchBox()
        Thread.sleep(1000)
    }

    // 1.10 //  ----------------------------------------------------------------------------------------------- A-Z page
    // ---------------------------------------------------------------------------------- top search box, items per page
    When(~/^User change 'item per page' parameter on top search box$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        checkNumberOfElementsOnPage(20) // by default
        epoints.azPage.clickItemPerPage40(); Thread.sleep(2000)
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        checkNumberOfElementsOnPage(40)
        epoints.azPage.clickItemPerPage100(); Thread.sleep(2000)
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        checkNumberOfElementsOnPage(100)
        epoints.azPage.clickItemPerPage20(); Thread.sleep(2000)
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        checkNumberOfElementsOnPage(20)
    }
    Then(~/^Number of elements on page will be changed according to chosen value$/) { ->
        // proper assertions made in previous step to check all three cases in one test
    }

    def checkNumberOfElementsOnPage(int number){
        int outOfValue = Integer.parseInt( $('.results-info-wrapper').find('.items-counter').text().replace("(out of ","").replace(")","") )
        if(number <=  outOfValue ){
            waitFor{ ($('.reg-items').find('.retailerCard').size() == number) }
            assert  ($('.reg-items').find('.retailerCard').size() == number)
        }else{
            waitFor{ ($('.reg-items').find('.retailerCard').size() == outOfValue) }
            assert  ($('.reg-items').find('.retailerCard').size() == outOfValue)
        }
    }
    // 1.11 //  ----------------------------------------------------------------------------------------------- A-Z page
    // ----------------------------------------------------------------------------------------- top search box, filters
    When(~/^User select some filter type '(.+?)'$/) {String filter ->
        epoints.azPage.expandFilterDDL()
        if(filter == "relevance" ){
            waitFor{ epoints.azPage.orderSelectElementOption.isDisplayed() }
            assert epoints.azPage.orderSelectElementOption[0].text() == envVar.azPageSortOptions[0]
            epoints.azPage.selectChosenFilterOption(0)
        }else if(filter == "nameasc" ){
            waitFor{ epoints.azPage.orderSelectElementOption.isDisplayed() }
            assert epoints.azPage.orderSelectElementOption[1].text() == envVar.azPageSortOptions[1]
            epoints.azPage.selectChosenFilterOption(1)
        }else if(filter == "namedesc" ){
            waitFor{ epoints.azPage.orderSelectElementOption.isDisplayed() }
            assert epoints.azPage.orderSelectElementOption[2].text() == envVar.azPageSortOptions[2]
            epoints.azPage.selectChosenFilterOption(2)
        }else if(filter == "epointsMultiplierasc" ){
            waitFor{ epoints.azPage.orderSelectElementOption.isDisplayed() }
            assert epoints.azPage.orderSelectElementOption[3].text() == envVar.azPageSortOptions[3]
            epoints.azPage.selectChosenFilterOption(3)
        }else if(filter == "epointsMultiplierdesc" ){
            waitFor{ epoints.azPage.orderSelectElementOption.isDisplayed() }
            assert epoints.azPage.orderSelectElementOption[4].text() == envVar.azPageSortOptions[4]
            epoints.azPage.selectChosenFilterOption(4)
        }
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        epoints.azPage.clickItemPerPage100(); Thread.sleep(2000)
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
    }

    Then(~/^Results will be displayed according to chosen filter '(.+?)'$/) {String filter ->
        if(filter == "relevance" ){
            waitFor{ epoints.azPage.retailerCardBasicSearchResults.size()>0 }
            assert epoints.azPage.retailerCardBasicSearchResults.size()>0
            //logic connected with admin portal quality engine
        }else if(filter == "nameasc" ){
            waitFor{ epoints.azPage.retailerCardBasicSearchResults.size()>0 }
            assert epoints.azPage.retailerCardBasicSearchResults.size()>0
            for(int i=0;i<epoints.azPage.retailerCardBasicSearchResults.size()-1;i++)
            {
                assert (epoints.azPage.retailerCardBasicSearchResults[i].attr('data-retailername').charAt(0).toLowerCase().compareTo(epoints.azPage.retailerCardBasicSearchResults[i+1].attr('data-retailername').charAt(0).toLowerCase())<=0)
            }
        }else if(filter == "namedesc" ){
            waitFor{ epoints.azPage.retailerCardBasicSearchResults.size()>0 }
            assert epoints.azPage.retailerCardBasicSearchResults.size()>0
            for(int i=0;i<epoints.azPage.retailerCardBasicSearchResults.size()-1;i++)
            {
                assert (epoints.azPage.retailerCardBasicSearchResults[i].attr('data-retailername').charAt(0).toLowerCase().compareTo(epoints.azPage.retailerCardBasicSearchResults[i+1].attr('data-retailername').charAt(0).toLowerCase())>=0)
            }
        }else if(filter == "epointsMultiplierasc" ){
            waitFor{ epoints.azPage.retailerCardBasicSearchResults.size()>0 }
            assert epoints.azPage.retailerCardBasicSearchResults.size()>0
            for(int i=0;i<epoints.azPage.retailerCardBasicSearchResults.size()-1;i++)
            {
                 assert (epoints.azPage.epointsIconBasic[i].text().charAt(1).toLowerCase().compareTo(epoints.azPage.epointsIconBasic[i+1].text().charAt(1).toLowerCase())<=0)
            }
        }else if(filter == "epointsMultiplierdesc" ){
            waitFor{ epoints.azPage.retailerCardBasicSearchResults.size()>0 }
            assert epoints.azPage.retailerCardBasicSearchResults.size()>0
            for(int i=0;i<epoints.azPage.retailerCardBasicSearchResults.size()-1;i++)
            {
                assert (epoints.azPage.epointsIconBasic[i].text().charAt(1).toLowerCase().compareTo(epoints.azPage.epointsIconBasic[i+1].text().charAt(1).toLowerCase())>=0)
            }
        }
    }

    // 1.12 //  ----------------------------------------------------------------------------------------------- A-Z page
    // -------------------------------------------------------- retailer cards user not logged, going to transition page
    When(~/^User chose some retailer and click on its card$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        browser.withNewWindow({  epoints.azPage.clickOnChosenRetailerCard(func.returnRandomValue(epoints.azPage.retailerCardBasicSearchResults.size())) }, close:true){
            assert browser.getTitle().contains(envVar.transitionPageTag)
            waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
            waitFor{ epoints.transitionPage.informationModalNoButton.isDisplayed() }
            waitFor{ epoints.transitionPage.informationModalJoinButton.isDisplayed() }
            assert epoints.transitionPage.informationModal.isDisplayed()
            assert epoints.transitionPage.informationModalNoButton.isDisplayed()
            assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
        }
    }
    Then(~/^He will be redirected to transition page$/) { ->
        //done in User chose some retailer and click on its card step
    }
    Then(~/^Information modal will be displayed$/) { ->
        //done in User chose some retailer and click on its card step
    }

    // 1.13 //  ----------------------------------------------------------------------------------------------- A-Z page
    // ------------------------------------------------------------------------ retailer cards user not logged, tooltips
    When(~/^User mouseover epoints retailer card icon$/) { ->

    }
    Then(~/^Tooltip with proper epoints multiplier information will be displayed$/) { ->
        waitFor{ epoints.azPage.epointsIconBasic.isDisplayed() }
        //System.out.println(epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername'))
        //System.out.println(epoints.azPage.epointsIconBasic[0].attr('data-title'))
        waitFor{ epoints.azPage.epointsIconBasic[0].attr('data-original-title') == envVar.returnEpointsTooltipInformation(epoints.azPage.epointsIconBasic[0].text().replace('x','')) }
        assert epoints.azPage.epointsIconBasic[0].attr('data-original-title') == envVar.returnEpointsTooltipInformation(epoints.azPage.epointsIconBasic[0].text().replace('x',''))
    }
    When(~/^User mouseover favourites retailer card icon$/) { ->

    }
    Then(~/^Tooltip with proper information about login needed will be displayed$/) { ->
        waitFor{ epoints.azPage.favouriteIconDisbledBasicUserLogout[0].attr('data-original-title') == envVar.favouritesTooltipInfoNotLogged }
        assert epoints.azPage.favouriteIconDisbledBasicUserLogout[0].attr('data-original-title') == envVar.favouritesTooltipInfoNotLogged
    }
    When(~/^User mouseover voucher retailer card icon$/) { ->

    }
    Then(~/^Tooltip with proper information about available vouchers will be displayed$/) { ->
        if(epoints.azPage.voucherIconBasic.size()>0){
            waitFor{ epoints.azPage.voucherIconBasic[0].attr('data-original-title').contains(envVar.voucherTooltipInfo) }
            assert epoints.azPage.voucherIconBasic[0].attr('data-original-title').contains(envVar.voucherTooltipInfo)
        }
    }

    // 1.14 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // -------------------------------------------------------------------- retailer cards, user not logged, button name

    When(~/^User hover epoints retailer card with mouse pointer$/) { ->
        waitFor{ epoints.azPage.searchResults.isDisplayed() }
        actions.clickAndHold(browser.driver.findElement(By.xpath("//div[@class='retailerCard']"))).perform()
    }

    Then(~/^Card is greyed out$/) { ->
        Thread.sleep(5000)
    }

    And(~/^Button with name of retailer is shown$/) { ->
        waitFor{ epoints.azPage.retailerNameBasicSearchResults.isDisplayed() }
        assert epoints.azPage.retailerNameBasicSearchResults.isDisplayed()
        //TODO: Check the correctness of retialer name
    }

    // 1.15 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // --------------------------------------------------------------------------- retailer cards, user logged, tooltips
    Then(~/^Tooltip with proper epoints multiplier information will be displayed for logged user$/) { ->
        waitFor{ epoints.azPage.epointsIconBasic.isDisplayed() }
        assert epoints.azPage.epointsIconBasic[0].attr('data-original-title') == envVar.returnEpointsTooltipInformation(epoints.azPage.epointsIconBasic[0].text().replace('x',''))
    }
    Then(~/^Tooltip with information about adding to favourites is shown for logged user$/) { ->
        waitFor{ epoints.azPage.favouriteIconDisbledBasicUserLogout.isDisplayed() }
        assert epoints.azPage.favouriteIconDisbledBasicUserLogout[0].attr('data-original-title') == envVar.favouritesTooltipInfoLogged
    }
    Then(~/^Tooltip with proper information about available vouchers will be displayed for logged user$/) { ->
        if(epoints.azPage.voucherIconBasic.size()>0){
            waitFor{ epoints.azPage.voucherIconBasic[0].attr('data-original-title').contains(envVar.voucherTooltipInfo) }
            assert epoints.azPage.voucherIconBasic[0].attr('data-original-title').contains(envVar.voucherTooltipInfo)
        }
    }

    // 1.16 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // ------------------------------------------------------------------ user not logged and click on favourites button
    When(~/^User click add to favourites button on chosen retailer card$/) { ->
        epoints.azPage.clickAddToFavouriteChosenRetailerWhenUserLogout(0)
    }
    Then(~/^Login Modal window will be displayed$/) { ->
        waitFor{ epoints.signModule.loginModalHeader.text() == envVar.loginModalHeader }
        assert epoints.signModule.loginModalHeader.text() == envVar.loginModalHeader
    }

    // 1.17 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    //  ----------------------------------------------------------------- retailer cards, user logged, adding favourites
    @Before('@userInterestsTableRowsAreRemoved')
    def userIsLogoutFromFacebook(){
        def envVar = new envVariables()
        def mysql = new jdbc("points")
            mysql.update("DELETE FROM points_manager.UserInterests WHERE userId = '"+mysql.get("SELECT id FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)+"'")
        mysql.close()
    }
    def nameOfChosenRetailer
    When(~/^User clicks on favourites retailer card icon of chosen retailer$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        waitFor{ !epoints.azPage.favouriteIconEnabledBasic.isDisplayed() }
        assert  !epoints.azPage.favouriteIconEnabledBasic.isDisplayed()
        assert  epoints.azPage.favouriteButtonDisabled.isDisplayed()
        random = func.returnRandomValue(epoints.azPage.retailerCardBasicSearchResults.size())
        nameOfChosenRetailer = epoints.azPage.retailerCardBasicSearchResults[random].attr('data-retailername')
        epoints.azPage.addToFavouriteChosenRetailer(random)
    }
    Then(~/^Heart icon is marked as gold$/) { ->
        waitFor{ !epoints.azPage.favouriteButtonDisabled.isDisplayed() }
        waitFor{ epoints.azPage.favouriteIconEnabledBasic.isDisplayed() }
        assert  epoints.azPage.favouriteIconEnabledBasic.isDisplayed()
        assert  !epoints.azPage.favouriteButtonDisabled.isDisplayed()
    }
    Then(~/^Retailer is added to the favourite retailers$/) { ->
        boolean flag = false
        epoints.azPage.clickShowFavouritesButton()
        Thread.sleep(2000); waitFor{ epoints.azPage.retailerCardBasicSearchResults }
        for(int j=0; j<epoints.azPage.retailerCardBasicSearchResults.size(); j++){
            if( epoints.azPage.retailerCardBasicSearchResults[j].attr('data-retailername') == nameOfChosenRetailer ){
                flag = true
            }
        }
        assert flag
    }

    // 1.18 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // ----------------------------------------------------------------- retailer cards user logged, removing favourites
    When(~/^User go to favourite retailers section$/) { ->
        waitFor{ epoints.azPage.favouriteButtonEnabled.isDisplayed() }
        epoints.azPage.clickShowFavouritesButton()
    }
    When(~/^Remove from the favourite last retailer$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        epoints.azPage.removeFromFavouriteChosenRetailer(0)
    }
    Then(~/^Retailer will be removed and favourite section disabled$/) { ->
        waitFor{ epoints.azPage.favouriteButtonDisabled.isDisplayed() }
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        assert  !epoints.azPage.favouriteIconEnabledBasic.isDisplayed()
        assert  epoints.azPage.favouriteButtonDisabled.isDisplayed()
    }

    // 1.19 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // ----------------------------------------------------------- retailer cards, user logged, going to transition page
    When(~/^User chose retailer and click on its card$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        browser.withNewWindow({  epoints.azPage.clickOnChosenRetailerCard(func.returnRandomValue(epoints.azPage.retailerCardBasicSearchResults.size())) }, close:true){
            assert browser.getTitle().contains(envVar.transitionPageTag)
            waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
            waitFor{ !epoints.transitionPage.informationModalNoButton.isDisplayed() }
            waitFor{ epoints.transitionPage.informationModalGotItButton.isDisplayed() }
            assert epoints.transitionPage.informationModal.isDisplayed()
            assert !epoints.transitionPage.informationModalNoButton.isDisplayed()
            assert epoints.transitionPage.informationModalGotItButton.isDisplayed()
        }
    }

    // 1.20 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // ------------------------------------------------------------------------------------ working of department filter
    def chosenDepartmentName
    When(~/^User click chosen department on department filter$/) { ->
        waitFor{ epoints.azPage.departmentFacet.isDisplayed() }
        waitFor{ epoints.azPage.departmentFacetHeader.text() == envVar.departmentFacetName }
        assert epoints.azPage.departmentFacet.isDisplayed()
        assert epoints.azPage.departmentFacetHeader.text() == envVar.departmentFacetName
        waitFor{ epoints.azPage.departmentFacetDepartmentButtonBasic  }
        random = func.returnRandomValue(epoints.azPage.departmentFacetDepartmentButtonBasic.size())
        while(epoints.azPage.departmentFacetDepartmentButtonBasic[random].hasClass('disabled')){
            random = func.returnRandomValue(epoints.azPage.departmentFacetDepartmentButtonBasic.size())
        }
        firstRetailer = epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername')
        secondRetailer = epoints.azPage.retailerCardBasicSearchResults[1].attr('data-retailername')
        chosenDepartmentName = epoints.azPage.departmentFacetDepartmentButtonBasic[random].text()
        epoints.azPage.clickChosenDepartment(random); Thread.sleep(1000)
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
    }
    Then(~/^Retailers fill be properly filtered$/) { ->
        waitFor{ !(firstRetailer == epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername')) || !(secondRetailer == epoints.azPage.retailerCardBasicSearchResults[1].attr('data-retailername')) }
        assert !(firstRetailer == epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername')) || !(secondRetailer == epoints.azPage.retailerCardBasicSearchResults[1].attr('data-retailername'))
    }
    Then(~/^Chosen Department will be set as active on department facet$/) { ->
        waitFor{ epoints.azPage.departmentFacetDepartmentButtonBasic[random].hasClass('active') }
        assert epoints.azPage.departmentFacetDepartmentButtonBasic[random].hasClass('active')
    }

    // 1.1 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // -------------------------------------------------------------------- working of clear button on department filter
    When(~/^User click clear button on department filter$/) { ->
        epoints.azPage.clickClearButtonOnDepartmentFacet()
    }
    Then(~/^None of department option will be set as active$/) { ->
        for(int i=0; i<epoints.azPage.departmentFacetDepartmentButtonBasic.size(); i++ ){
            assert !(epoints.azPage.departmentFacetDepartmentButtonBasic[i].hasClass('active'))
        }
    }
    Then(~/^Retailers cards will be displayed in initial order$/) { ->
        waitFor{ firstRetailer == epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername') }
        assert firstRetailer == epoints.azPage.retailerCardBasicSearchResults[0].attr('data-retailername')
    }

    // 1.22 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
    // --------------------------------------------------------------------- recently visited retailer component working
    When(~/^User go to chosen retailer page using retailer card$/) { ->
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        random = func.returnRandomValue(epoints.azPage.retailerCardBasicSearchResults.size())
        firstRetailer = epoints.azPage.retailerCardBasicSearchResults[random].attr('data-retailername')
        browser.withNewWindow({  epoints.azPage.clickOnChosenRetailerCard(random) }, close:true){
            waitFor{ epoints.transitionPage.informationModal }
            epoints.transitionPage.clickGotItButtonOnInformationModal()
            Thread.sleep(4000)
        }
    }
    When(~/^Come back to az page$/) { ->
        func.refreshPage()
    }
    Then(~/^He will se that recently visited retailer component was updated about previous chosen retailer$/) { ->
        waitFor{ epoints.azPage.recentlyVisitedRetailersHeader.text().contains(envVar.recentlyVisitedRetailersHeader) }
        waitFor{ epoints.azPage.recentlyVisitedRetailersRetailerElement[0].text() == firstRetailer }
        assert epoints.azPage.recentlyVisitedRetailersHeader.text().contains(envVar.recentlyVisitedRetailersHeader)
        assert epoints.azPage.recentlyVisitedRetailersRetailerElement[0].text() == firstRetailer
    }
    Then(~/^He will be able to use links from recently visited retailers component$/) { ->
        browser.withNewWindow({  epoints.azPage.clickChosenRetailerFromRecentlyVisited(0) }, close:true){
            waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
            assert epoints.transitionPage.transitionPageRetailerInformation.text() == firstRetailer
        }
    }

    // 2.1 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build A-Z page (NBO-541)
    // --------------------------------------------------------------------------- recently visited and retailers / zone
    Given(~/^Proper zone is set '(.+?)'$/) { String zone->
        waitFor{ epoints.topNavZonePickerFlag.isDisplayed() }
        epoints.epandZonePickerPanel()
        Cookie cookie = new Cookie("key", "value");
        boolean cookieFound = false
        if(zone.equals('UK')){
            epoints.choseUKzone()
            Set<Cookie> allCookies = browser.getDriver().manage().getCookies();
            for (Cookie loadedCookie : allCookies) {
                if(loadedCookie.getName() == 'user_zone' && loadedCookie.getValue() == 'UK'){
                    cookieFound = true
                }
            }
            assert cookieFound
        }else if(zone.equals('IE')){
            epoints.choseIrelandZone()
            Set<Cookie> allCookies = browser.getDriver().manage().getCookies();
            for (Cookie loadedCookie : allCookies) {
                if(loadedCookie.getName() == 'user_zone' && loadedCookie.getValue() == 'IE'){
                    cookieFound = true
                }
            }
            assert cookieFound
        }
    }
    When(~/^User check recently visited retailers and retailer cards$/) { ->
        //all done in next step
    }
    Then(~/^He will see that all retailers are properly returned according to chosen zone '(.+?)'$/) { String zone ->
        waitFor{ epoints.azPage.recentlyVisitedRetailersRetailerElement.isDisplayed() }
        waitFor{ epoints.azPage.retailerCardBasicSearchResults.isDisplayed() }
        assert epoints.azPage.recentlyVisitedRetailersRetailerElement.isDisplayed()
        assert epoints.azPage.retailerCardBasicSearchResults.isDisplayed()
        Thread.sleep(2000)
        def mysql = new jdbc("adminNew")
        for(int i =0; i<epoints.azPage.retailerCardBasicSearchResults.size(); i++){
            if(zone.equals('UK')){
                assert mysql.get("SELECT COUNT(*) FROM admin_ui_qa.MerchantZone WHERE zone = 'UK' AND merchant_id = '"+mysql.get("SELECT id FROM admin_ui_qa.Merchant WHERE name = '"+epoints.azPage.retailerCardBasicSearchResults[i].attr('data-retailername')+"'",1)+"'",1) == '1'
            }else if(zone.equals('IE')){
                assert mysql.get("SELECT COUNT(*) FROM admin_ui_qa.MerchantZone WHERE zone = 'IE' AND merchant_id = '"+mysql.get("SELECT id FROM admin_ui_qa.Merchant WHERE name = '"+epoints.azPage.retailerCardBasicSearchResults[i].attr('data-retailername')+"'",1)+"'",1) == '1'
            }
        }
        for(int i =0; i<epoints.azPage.recentlyVisitedRetailersRetailerElement.size(); i++){
            if(zone.equals('UK')){
                assert mysql.get("SELECT COUNT(*) FROM admin_ui_qa.MerchantZone WHERE zone = 'UK' AND merchant_id = '"+mysql.get("SELECT id FROM admin_ui_qa.Merchant WHERE name = '"+epoints.azPage.recentlyVisitedRetailersRetailerElement[i].text()+"'",1)+"'",1) == '1'
            }else if(zone.equals('IE')){
                assert mysql.get("SELECT COUNT(*) FROM admin_ui_qa.MerchantZone WHERE zone = 'IE' AND merchant_id = '"+mysql.get("SELECT id FROM admin_ui_qa.Merchant WHERE name = '"+epoints.azPage.recentlyVisitedRetailersRetailerElement[i].text()+"'",1)+"'",1) == '1'
            }
        }
        mysql.close()
    }

    // 2.2 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build A-Z page (NBO-541)
    // -------------------------------------------------------------- recently visited and retailers after search / zone