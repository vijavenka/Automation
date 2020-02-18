package com.iat.stepdefs.pointsSection

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.LeadRetailerPage
import com.iat.pages.points.PointsPage
import com.iat.pages.points.TransitionPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser
import org.openqa.selenium.Cookie
import org.openqa.selenium.JavascriptExecutor

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def pointsPage = new PointsPage()
def transitionPage = new TransitionPage()
def leadRetailerPage = new LeadRetailerPage()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
int random
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAdminPortal)

// 1.1 //  --------------------------------------------------------------------------------------------- Points page
// ------------------------------------------------------------------------------------ check content of points page
Given(~/^Points page is opened by (.+?) user$/) { String loginState ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    if (loginState == "logged")
        epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.headerModule.clickOnPointsButton()
    at PointsPage
    pointsPage = page
    sleep(1000)
    waitFor(10) { pointsPage.loader.hasClass('ng-hide') }
}

Given(~/^Points page is opened$/) { ->
    at EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.headerModule.clickOnPointsButton()
    at PointsPage
    pointsPage = page
    waitFor(10) { pointsPage.loader.hasClass('ng-hide') }
}
When(~/^User looks at az page$/) { ->
    //leave empty
}
Then(~/^Search component is visible$/) { ->
    waitFor { pointsPage.searchComponent.isDisplayed() }
    assert pointsPage.searchComponent.isDisplayed()
    assert pointsPage.searchField.isDisplayed()
    assert pointsPage.searchButton.isDisplayed()
}
//Then(~/^Roulette banner is visible$/) { ->
//    waitFor { pointsPage.roulettBanner.isDisplayed() }
//    assertThat("Roulette banner is not available", pointsPage.roulettBanner.isDisplayed())
//}
Then(~/^Department facet is available$/) { ->
    ((JavascriptExecutor) browser.getDriver()).executeScript("scroll(0,650);"); sleep(1000)
    waitFor { pointsPage.departmentsFacetModule.departmentFacet.isDisplayed() }
    assert pointsPage.departmentsFacetModule.departmentFacet.isDisplayed()
}
Then(~/^Letter facet is available$/) { ->
    waitFor { pointsPage.lettersFacetModule.azFacet.isDisplayed() }
    assert pointsPage.lettersFacetModule.azFacet.isDisplayed()
}
Then(~/^Bottom pagination is available$/) { ->
    waitFor { pointsPage.paginationModule.paginationModule.isDisplayed() }
    assert pointsPage.paginationModule.paginationModule.isDisplayed()
}
Then(~/^Retailer cards are available$/) { ->
    waitFor { pointsPage.retailersList.size() > 0 }
    assert pointsPage.retailersList.size() > 0
}
Then(~/^Top pagination component is available$/) { ->
    waitFor { pointsPage.paginationModule.topPaginationResultInfo.isDisplayed() }
    assert pointsPage.paginationModule.topPaginationResultInfo.isDisplayed()
    assert pointsPage.paginationModule.topPaginationOrder.isDisplayed()
    assert pointsPage.paginationModule.topPaginationPerPage.isDisplayed()
}
Then(~/^Favourite module is available$/) { ->
    waitFor { pointsPage.favouriteButtonDisabled.isDisplayed() }
    assert pointsPage.favouriteButtonDisabled.isDisplayed()
}
Then(~/^Points page breadcrumb is available$/) { ->
    waitFor { pointsPage.breadcrumbModule.breadcrumb.isDisplayed() }
    pointsPage.breadcrumbModule.breadcrumbSingleElementBasic[0].text().equals('Home')
    pointsPage.breadcrumbModule.breadcrumbSingleElementBasic[0].text().equals('Points')
    assert pointsPage.breadcrumbModule.breadcrumbSingleElementBasic.iterator().any { it.isDisplayed() }
}
Then(~/^Specials promotion block is available with all required elements\(title; merchant cards; check more leads link\)$/) {
    ->
    waitFor { pointsPage.promotedLeadsRetailesModule.isDisplayed() }
    assert pointsPage.promotedLeadsRetailesModuleTitle.text() == "Special Offers"
    assert pointsPage.promotedLeadsRetailesModuleDescription.text() == "Explore the latest ways to earn lots of epoints"
    assert pointsPage.promotedLeadsRetailer.size() == 2
    assert pointsPage.promotedLeadsRetailerImage.iterator().any { it.isDisplayed() }
    assert pointsPage.promotedLeadsRetailerMaxCommision.iterator().any { it.isDisplayed() }
    assert pointsPage.promotedLeadsRetailerCheckButton.iterator().any { it.isDisplayed() }
}

// 1.2 //  --------------------------------------------------------------------------------------------- Points page
// ----------------------------------------------------------------------------------------- working of letter facet
Then(~/^User can use letter filter$/) { ->
    waitFor { pointsPage.lettersFacetModule.azFacet.isDisplayed() }
    waitFor { pointsPage.lettersFacetModule.azFacetHeader.text() == envVar.azFacetName }
    assert pointsPage.lettersFacetModule.azFacet.isDisplayed()
    assert pointsPage.lettersFacetModule.azFacetHeader.text() == envVar.azFacetName
}
String letter
Then(~/^Results will match chosen letter$/) { ->
    for (int i = 0; i < 5; i++) {
        if (pointsPage.lettersFacetModule.azFacetLetterButtonBasic[i].attr('class') == 'disabled') {
            continue
        }
        pointsPage.lettersFacetModule.clickChosenLetter(i)
        waitFor { pointsPage.loader.hasClass('ng-hide') }
        letter = pointsPage.lettersFacetModule.azFacetLetterButtonBasic[i].text()
        assert pointsPage.breadcrumbModule.breadcrumbSingleElementBasic.last().text().equals(letter)
        for (int j = 0; j < 1; j++) {
            waitFor { pointsPage.retailersList[j].isDisplayed() }
            sleep(500)
            browser.interact { moveToElement(pointsPage.retailersList[j]) }
            waitFor { pointsPage.retailersList[j].retailerCardOverlayButton.isDisplayed() }
            assert pointsPage.retailersList[j].retailerCardOverlayButton.text().toLowerCase().charAt(0) == letter.toLowerCase()
        }
    }
}
Then(~/^Breadcrumb last level show chosen letter$/) { ->
    //checked in previous step
}

// 1.3 //  ------------------------------------------------------------------------------------------------ Points page
// ---------------------------------------------------------------------------- working of clear button on az filter
String firstRetailer
String secondRetailer
When(~/^User select some letter from az filter$/) { ->
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0]) }//this is a hack
    waitFor { pointsPage.retailersList[0].retailerCardOverlayButton.isDisplayed() }
    firstRetailer = pointsPage.retailersList[0].retailerCardOverlayButton.text()
    random = func.returnRandomValue(pointsPage.lettersFacetModule.azFacetLetterButtonBasic.size())
    pointsPage.lettersFacetModule.clickChosenLetter(random)
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    letter = pointsPage.lettersFacetModule.azFacetLetterButtonBasic[random].text()
    waitFor { pointsPage.lettersFacetModule.azFacetLetterButtonActiveBasic.isDisplayed() }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0]) }//this is a hack
    waitFor { pointsPage.retailersList[0].retailerCardOverlayButton.isDisplayed() }
    waitFor { !(pointsPage.retailersList[0].retailerCardOverlayButton.text() == firstRetailer) }
}
When(~/^Click clear button on az filter$/) { ->
    pointsPage.lettersFacetModule.clickClearButtonOnAzFacet()
}
Then(~/^Previous selection will be disabled and filter reset$/) { ->
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    waitFor { !pointsPage.lettersFacetModule.azFacetLetterButtonActiveBasic.isDisplayed() }
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0]) }//this is a hack
    waitFor { pointsPage.retailersList[0].retailerCardOverlayButton.isDisplayed() }
    assert (pointsPage.retailersList[0].retailerCardOverlayButton.text() == firstRetailer)
}
Then(~/^Breadcrumb last level is set to points$/) { ->
    assert pointsPage.breadcrumbModule.breadcrumbSingleElementBasic.last().text().equals('Points')
}

// 1.4 //  ------------------------------------------------------------------------------------------------ Points page
// ----------------------------------------------------------------------------- working of search allowed retailers
When(~/^User enter '(.+)' into search$/) { String phrase ->
    pointsPage.enterPhraseIntoSearch(phrase)
    if (!phrase.equals('notexistingretailer')) {
        waitFor { pointsPage.searchResultsDDLOption[0].isDisplayed() }
        for (int i = 0; i < pointsPage.searchResultsDDLOption.size(); i++)
            assert pointsPage.searchResultsDDLOption[i].text().toLowerCase().contains(phrase)
    } else
        assert !pointsPage.searchResultsDDLOption[0].isDisplayed()
    sleep(1000)
}
When(~/^Click Search button$/) { ->
    pointsPage.clickSearchButton()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}
Then(~/^Proper results will be displayed according to typed '(.+)'$/) { String phrase ->
    for (int i = 0; i < pointsPage.retailersList.size(); i++) {
        waitFor { pointsPage.retailersList[i].isDisplayed() }
        sleep(500)
        browser.interact { moveToElement(pointsPage.retailersList[i]) }
        waitFor { pointsPage.retailersList[i].retailerCardOverlayButton.isDisplayed() }
        assert (pointsPage.retailersList[i].retailerCardOverlayButton.text().toLowerCase()).contains(phrase)
    }
}

// 1.5 //  ------------------------------------------------------------------------------------------------ Points page
// ------------------------------------------------------------------------- working of search not allowed retailers
Then(~/^Proper message about not found retailer '(.+)' will be displayed$/) { String phrase ->
    waitFor { pointsPage.noResultsFoundMessage.text() == envVar.returnInformationAboutNotFoundRetailer(phrase) }
    assert pointsPage.noResultsFoundMessage.text() == envVar.returnInformationAboutNotFoundRetailer(phrase)
}

// 1.6 //  ------------------------------------------------------------------------------------------------ Points page
// ------------------------------------------------------------- bottom pagination, working of next/previous buttons
When(~/^User click next page button$/) { ->
    waitFor { pointsPage.paginationModule.pageNumberActiveBasic.isDisplayed() }
    waitFor { pointsPage.paginationModule.pageNumberActiveBasic.text().contains('1') }
    assert pointsPage.paginationModule.pageNumberActiveBasic.text().contains('1')
    pointsPage.paginationModule.clickNextPageButton()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}
Then(~/^Page will be changed to next$/) { ->
    waitFor { pointsPage.paginationModule.pageNumberActiveBasic.text().contains('2') }
    assert pointsPage.paginationModule.pageNumberActiveBasic.text().contains('2')
}
Then(~/^Showing module will be increased$/) { ->
    waitFor { pointsPage.paginationModule.showingElement.text().contains('Showing 21 - 40') }
    assert pointsPage.paginationModule.showingElement.text().contains('Showing 21 - 40')
}
When(~/^User click previous page button$/) { ->
    pointsPage.paginationModule.clickPreviousPageButton()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}
When(~/^Showing module will be decreased$/) { ->
    waitFor { pointsPage.paginationModule.showingElement.text().contains('Showing 1 - 20') }
    assert pointsPage.paginationModule.text().contains('Showing 1 - 20')
}
Then(~/^Page will be changed to previous$/) { ->
    waitFor { pointsPage.paginationModule.pageNumberActiveBasic.text().contains('1') }
    assert pointsPage.paginationModule.pageNumberActiveBasic.text().contains('1')
}

// 1.7 //  ------------------------------------------------------------------------------------------------ Points page
// --------------------------------------------------------------- bottom pagination, working of page numbers button
When(~/^User looks on bottom pagination component$/) { ->
    // leave empty
}
int numberOfProducts
Then(~/^He can see tat proper number of page is displayed according to 'out of' information$/) { ->
    waitFor { pointsPage.paginationModule.outOfElement.isDisplayed() }
    numberOfProducts = Integer.parseInt(pointsPage.paginationModule.outOfElement.text().replace("(out of ", "").replace(")", "").replace(',', ''))
    if ((numberOfProducts % 20) == 0) {  // 20 because default number of products per page is 20
        waitFor {
            Integer.parseInt(pointsPage.paginationModule.pageNumberBasic.last().text()) == (int) (numberOfProducts / 20)
        }
        assert Integer.parseInt(pointsPage.paginationModule.pageNumberBasic.last().text()) == (int) (numberOfProducts / 20)
    } else {
        waitFor {
            Integer.parseInt(pointsPage.paginationModule.pageNumberBasic.last().text()) == (int) (numberOfProducts / 20 + 1)
        }
        assert Integer.parseInt(pointsPage.paginationModule.pageNumberBasic.last().text()) == (int) (numberOfProducts / 20 + 1)
    }
}
When(~/^User use some bottom pagination page number$/) { ->
    random = func.returnRandomValue(4) + 1
    pointsPage.paginationModule.clickChosenPageNumber(random)
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}
Then(~/^proper page will be displayed$/) { ->
    waitFor { pointsPage.paginationModule.pageNumberActiveBasic.text().contains((random + 1).toString()) }
    waitFor {
        pointsPage.paginationModule.showingElement.text().contains('Showing ' + (random * 20 + 1).toString() + " - " + ((random + 1) * 20).toString())
    }
    assert pointsPage.paginationModule.pageNumberActiveBasic.text().contains((random + 1).toString())
    assert pointsPage.paginationModule.showingElement.text().contains('Showing ' + (random * 20 + 1).toString() + " - " + ((random + 1) * 20).toString())
}

// 1.8 //  ------------------------------------------------------------------------------------------------ Points page
// ---------------------------------------------------------------- bottom pagination, working of back to top button
When(~/^User scroll page down$/) { ->
    //assert !pointsPage.paginationModule.backToTopButton.isDisplayed()
    sleep(4000)
    ((JavascriptExecutor) browser.getDriver()).executeScript("scroll(0,600);"); sleep(2000)
    waitFor { pointsPage.paginationModule.backToTopButton.isDisplayed() }
    //assert pointsPage.paginationModule.backToTopButton.isDisplayed()
}
When(~/^User click 'back to top' button$/) { ->
    pointsPage.paginationModule.clickBackToTopButton()
    sleep(1000)
}
Then(~/^Page will be scrolled to top$/) { ->
    //waitFor{ !pointsPage.paginationModule.backToTopButton.isDisplayed() }
    //assert !pointsPage.paginationModule.backToTopButton.isDisplayed()
}

// 1.9 //  ------------------------------------------------------------------------------------------------ Points page
// ------------------------------------------------------------------------------------------ top search box, arrows
When(~/^User click right arrow on top search box$/) { ->
    epoints = page //for switching context in spend page pagination tests
    pointsPage.paginationModule.clickRightArrowOnTopSearchBox()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}
When(~/^User click left arrow on top search box$/) { ->
    pointsPage.paginationModule.clickLeftArrowOnTopSearchBox()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}

// 1.10 //  ----------------------------------------------------------------------------------------------- Points page
// ---------------------------------------------------------------------------------- top search box, items per page
When(~/^User change 'item per page' parameter on top search box$/) { ->
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    sleep(1000)
    waitFor { !pointsPage.loader.isDisplayed() }
    waitFor { pointsPage.retailersList[19].isDisplayed() }
    checkNumberOfElementsOnPage(20) // by default
    pointsPage.paginationModule.clickItemPerPage40()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    sleep(1000)
    waitFor { !pointsPage.loader.isDisplayed() }
    waitFor { pointsPage.retailersList[39].isDisplayed() }
    checkNumberOfElementsOnPage(40)
    pointsPage.paginationModule.clickItemPerPage100()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    sleep(1000)
    waitFor { !pointsPage.loader.isDisplayed() }
    waitFor { pointsPage.retailersList[99].isDisplayed() }
    checkNumberOfElementsOnPage(100)
    pointsPage.paginationModule.clickItemPerPage20()
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    sleep(1000)
    waitFor { !pointsPage.loader.isDisplayed() }
    waitFor { pointsPage.retailersList[19].isDisplayed() }
    checkNumberOfElementsOnPage(20)
}
Then(~/^Number of elements on page will be changed according to chosen value$/) { ->
    // proper assertions made in previous step to check all three cases in one test
}

def checkNumberOfElementsOnPage(int number) {
    int outOfValue = Integer.parseInt($('.topPagination--resultsInfo').find('.pagination-totalItems').text().replace("(out of ", "").replace(")", "").replace(',', ''))
    if (number <= outOfValue) {
        waitFor { ($('.custom-collection-view').find('.RetailerCard').size() == number) }
        assert ($('.custom-collection-view').find('.RetailerCard').size() == number)
    } else {
        waitFor { ($('.custom-collection-view').find('.RetailerCard').size() == outOfValue) }
        assert ($('.custom-collection-view').find('.RetailerCard').size() == outOfValue)
    }
}
// 1.11 //  ----------------------------------------------------------------------------------------------- Points page
// ----------------------------------------------------------------------------------------- top search box, filters
When(~/^User select some filter type '(.+?)'$/) { String filter ->
    //TODO move it to page and make one pretty function
    pointsPage.paginationModule.expandFilterDDL()
    if (filter == "relevance") {
        waitFor { pointsPage.paginationModule.orderSelectElementOption[0].isDisplayed() }
        assert pointsPage.paginationModule.orderSelectElementOption[0].text() == envVar.azPageSortOptions[0]
        pointsPage.paginationModule.selectChosenFilterOption(0)
    } else if (filter == "nameasc") {
        waitFor { pointsPage.paginationModule.orderSelectElementOption[0].isDisplayed() }
        assert pointsPage.paginationModule.orderSelectElementOption[1].text() == envVar.azPageSortOptions[1]
        pointsPage.paginationModule.selectChosenFilterOption(1)
        waitFor { pointsPage.loader.isDisplayed() }
    } else if (filter == "namedesc") {
        waitFor { pointsPage.paginationModule.orderSelectElementOption[0].isDisplayed() }
        assert pointsPage.paginationModule.orderSelectElementOption[2].text() == envVar.azPageSortOptions[2]
        pointsPage.paginationModule.selectChosenFilterOption(2)
        waitFor { pointsPage.loader.isDisplayed() }
    } else if (filter == "epointsMultiplierasc") {
        waitFor { pointsPage.paginationModule.orderSelectElementOption[0].isDisplayed() }
        assert pointsPage.paginationModule.orderSelectElementOption[3].text() == envVar.azPageSortOptions[3]
        pointsPage.paginationModule.selectChosenFilterOption(3)
        waitFor { pointsPage.loader.isDisplayed() }
    } else if (filter == "epointsMultiplierdesc") {
        waitFor { pointsPage.paginationModule.orderSelectElementOption[0].isDisplayed() }
        assert pointsPage.paginationModule.orderSelectElementOption[4].text() == envVar.azPageSortOptions[4]
        pointsPage.paginationModule.selectChosenFilterOption(4)
        waitFor { pointsPage.loader.isDisplayed() }
    }
    waitFor { !pointsPage.loader.isDisplayed() }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    waitFor { pointsPage.retailersList[1].isDisplayed() }
}

Then(~/^Results will be displayed according to chosen filter '(.+?)'$/) { String filter ->
    if (filter == "relevance") {
        waitFor { pointsPage.retailersList.size() > 0 }
        //logic connected with admin portal quality engine
    } else if (filter == "nameasc") {
        waitFor { pointsPage.retailersList.size() > 0 }
        for (int i = 0; i < pointsPage.retailersList.size() - 1; i++) {
            waitFor { pointsPage.retailersList[i].isDisplayed() }
            sleep(500)
            browser.interact { moveToElement(pointsPage.retailersList[i]) }
            waitFor { pointsPage.retailersList[i].retailerCardOverlayButton.isDisplayed() }
            def earlierRetailerName = pointsPage.retailersList[i].retailerCardOverlayButton.text()

            waitFor { pointsPage.retailersList[i + 1].isDisplayed() }
            sleep(500)
            browser.interact { moveToElement(pointsPage.retailersList[i + 1]) }
            waitFor { pointsPage.retailersList[i + 1].retailerCardOverlayButton.isDisplayed() }
            def nextRetailerName = pointsPage.retailersList[i + 1].retailerCardOverlayButton.text()
            assert (earlierRetailerName.charAt(0).toLowerCase().compareTo(nextRetailerName.charAt(0).toLowerCase()) <= 0)
        }
    } else if (filter == "namedesc") {
        waitFor { pointsPage.retailersList.size() > 0 }
        for (int i = 0; i < pointsPage.retailersList.size() - 1; i++) {
            waitFor { pointsPage.retailersList[i].isDisplayed() }
            sleep(500)
            browser.interact { moveToElement(pointsPage.retailersList[i]) }
            waitFor { pointsPage.retailersList[i].retailerCardOverlayButton.isDisplayed() }
            def earlierRetailerName = pointsPage.retailersList.retailerCardOverlayButton[i].text()

            waitFor { pointsPage.retailersList[i + 1].isDisplayed() }
            sleep(500)
            browser.interact { moveToElement(pointsPage.retailersList[i + 1]) }
            waitFor { pointsPage.retailersList[i + 1].retailerCardOverlayButton.isDisplayed() }
            def nextRetailerName = pointsPage.retailersList[i + 1].retailerCardOverlayButton.text()
            assert (earlierRetailerName.charAt(0).toLowerCase().compareTo(nextRetailerName.charAt(0).toLowerCase()) >= 0)
        }
    } else if (filter == "epointsMultiplierasc") {
        waitFor { pointsPage.retailersList.size() > 0 }
        for (int i = 0; i < pointsPage.retailersList.size() - 1; i++) {
            if (pointsPage.retailersList[i].epointsInfo.text().contains("up to") || pointsPage.retailersList[i + 1].epointsInfo.text().contains("up to"))
                continue //skip leads merchants which multiplier value is not visible on ui
            assert (pointsPage.retailersList[i].epointsInfo.text().charAt(1).toLowerCase().compareTo(pointsPage.retailersList[i + 1].epointsInfo.text().charAt(1).toLowerCase()) <= 0)
        }
    } else if (filter == "epointsMultiplierdesc") {
        waitFor { pointsPage.retailersList.size() > 0 }
        for (int i = 0; i < pointsPage.retailersList.size() - 1; i++) {
            if (pointsPage.retailersList[i].epointsInfo.text().contains("up to") || pointsPage.retailersList[i + 1].epointsInfo.text().contains("up to"))
                continue //skip leads merchants which multiplier value is not visible on ui
            assert (pointsPage.retailersList[i].epointsInfo.text().charAt(1).toLowerCase().compareTo(pointsPage.retailersList[i + 1].epointsInfo.text().charAt(1).toLowerCase()) >= 0)
        }
    }
}

// 1.12 //  ----------------------------------------------------------------------------------------------- Points page
// -------------------------------------------------------- retailer cards user not logged, going to transition page
When(~/^User chose some retailer and click on its card$/) { ->
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    random = func.returnRandomValue(pointsPage.retailersList.size())
    while (pointsPage.retailersList[random].leadsBadge.isDisplayed()) {
        random = func.returnRandomValue(pointsPage.retailersList.size())
    }
    browser.withNewWindow({
        pointsPage.retailersList[random].click()
    }, close: true, wait: true) {
        at TransitionPage
        transitionPage = page

        waitFor { transitionPage.transitionPagePointsInfo.isDisplayed() }
        waitFor { transitionPage.transitionPageRetailerInformation.isDisplayed() }
        waitFor { transitionPage.transitionPageEpointsAnimation.isDisplayed() }
    }
}
Then(~/^He will be redirected to transition page$/) { ->
    //done in User chose some retailer and click on its card step
}

// 1.13 //  ----------------------------------------------------------------------------------------------- Points page
// ------------------------------------------------------------------------ retailer cards user not logged, tooltips
When(~/^User mouseover epoints retailer card icon$/) { ->
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0].epointsIcon) }
}
Then(~/^Tooltip with proper epoints multiplier information will be displayed$/) { ->
//    waitFor {
//        pointsPage.retailersList[0].epointsIcon.attr('tooltip').contains(envVar.returnEpointsTooltipInformation(pointsPage.retailersList[0].epointsInfo.text().replace('x', '').substring(0,1)))
//    }
//    assert pointsPage.retailersList[0].epointsIcon.attr('tooltip').contains(envVar.returnEpointsTooltipInformation(pointsPage.retailersList[0].epointsInfo.text().replace('x', '').substring(0,1)))
}
When(~/^User mouseover favourites retailer card icon$/) { ->
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0].favouriteIconDisbleUserLogout) }
}
Then(~/^Tooltip with proper information about login needed will be displayed$/) { ->
    waitFor {
        pointsPage.retailersList[0].favouriteIconDisbleUserLogout.attr('tooltip') == envVar.favouritesTooltipInfoNotLogged
    }
    assert pointsPage.retailersList[0].favouriteIconDisbleUserLogout.attr('tooltip') == envVar.favouritesTooltipInfoNotLogged
}
When(~/^User mouseover voucher retailer card icon$/) { ->
    //TODO find out how to handel not always available object
//    if (pointsPage.retailersList.voucherIcon.size() > 0) {
//        sleep(500)
//        browser.interact { moveToElement(pointsPage.retailersList.voucherIcon) }
//    }
}
Then(~/^Tooltip with proper information about available vouchers will be displayed$/) { ->
//    if (pointsPage.retailersList.voucherIcon.size() > 0) {
//        waitFor {
//            pointsPage.retailersList.voucherIcon.attr('tooltip').contains(envVar.voucherTooltipInfo)
//        }
//        assert pointsPage.retailersList.voucherIcon.attr('tooltip').contains(envVar.voucherTooltipInfo)
//    }
}

// 1.14 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
// -------------------------------------------------------------------- retailer cards, user not logged, button name

When(~/^User hover epoints retailer card with mouse pointer$/) { ->
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0]) }
}

Then(~/^Card is greyed out$/) { ->
    //leave empty
}

And(~/^Button with name of retailer is shown$/) { ->
    waitFor { pointsPage.retailersList[0].retailerCardOverlayButton.isDisplayed() }
    assert pointsPage.retailersList[0].retailerCardOverlayButton.isDisplayed()
}

// 1.15 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
// --------------------------------------------------------------------------- retailer cards, user logged, tooltips
Then(~/^Tooltip with proper epoints multiplier information will be displayed for logged user$/) { ->
    //TODO
//    waitFor {
//        pointsPage.retailersList[0].epointsIcon.attr('tooltip').contains(envVar.returnEpointsTooltipInformation(pointsPage.retailersList[0].epointsInfo.text().replace('x', '').substring(0, 1)))
//    }
//    assert pointsPage.retailersList[0].epointsIcon.attr('tooltip').contains(envVar.returnEpointsTooltipInformation(pointsPage.retailersList[0].epointsInfo.text().replace('x', '').substring(0, 1)))
}
Then(~/^Tooltip with information about adding to favourites is shown for logged user$/) { ->
    //TODO
//    waitFor {
//        pointsPage.retailersList[0].favouriteIconDisbleUserLogout.attr('tooltip') == envVar.favouritesTooltipInfoLogged || pointsPage.retailersList[0].favouriteIconDisbleUserLogout.attr('tooltip') == envVar.favouritesTooltipInfoLogged2
//    }
//    assert pointsPage.retailersList[0].favouriteIconDisbleUserLogout.attr('tooltip') == envVar.favouritesTooltipInfoLogged || pointsPage.retailersList[0].favouriteIconDisbleUserLogout.attr('tooltip') == envVar.favouritesTooltipInfoLogged2
}
Then(~/^Tooltip with proper information about available vouchers will be displayed for logged user$/) { ->
    //TODO find out how to handel not always available object
//    if (pointsPage.retailersList.voucherIconBasic.size() > 0) {
//        waitFor {
//            pointsPage.retailersList[0].voucherIcon.attr('tooltip').contains(envVar.voucherTooltipInfo)
//        }
//        assert pointsPage.retailersList[0].voucherIcon.attr('tooltip').contains(envVar.voucherTooltipInfo)
//    }
}

// 1.16 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
// ------------------------------------------------------------------ user not logged and click on favourites button
When(~/^User click add to favourites button on chosen retailer card$/) { ->
    pointsPage.retailersList[0].addRetailerToFavouriteWhenLogout()
}
Then(~/^Login Modal window will be displayed$/) { ->
    waitFor { pointsPage.loginModalModule.loginModalHeader.text() == envVar.loginModalHeader }
    assert pointsPage.loginModalModule.loginModalHeader.text() == envVar.loginModalHeader
}

// 1.17 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
//  ----------------------------------------------------------------- retailer cards, user logged, adding favourites
def nameOfChosenRetailer
When(~/^User clicks on favourites retailer card icon of chosen retailer$/) { ->
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    if (pointsPage.retailersList[0].favouriteIconEnabled.isDisplayed()) {
        pointsPage.retailersList[0].removeRetailerFromFavourite()
    }
    waitFor { !pointsPage.retailersList[0].favouriteIconEnabled[0].isDisplayed() }
    waitFor { pointsPage.favouriteButtonDisabled.isDisplayed() }
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0]) }
    nameOfChosenRetailer = pointsPage.retailersList[0].retailerCardOverlayButton.text()
    pointsPage.retailersList[0].addRetailerToFavourite()
}
Then(~/^Heart icon is marked as gold$/) { ->
    waitFor { !pointsPage.favouriteButtonDisabled.isDisplayed() }
    waitFor { pointsPage.retailersList[0].favouriteIconEnabled.isDisplayed() }
    assert pointsPage.retailersList[0].favouriteIconEnabled.isDisplayed()
    assert !pointsPage.favouriteButtonDisabled.isDisplayed()
}
Then(~/^Retailer is added to the favourite retailers$/) { ->
    boolean flag = false
    pointsPage.clickShowFavouritesButton()
    waitFor { browser.currentUrl.contains('?favourite=true') }
    sleep(500)
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    for (int j = 0; j < pointsPage.retailersList.size(); j++) {
        waitFor { pointsPage.retailersList[j].isDisplayed() }
        sleep(500)
        browser.interact { moveToElement(pointsPage.retailersList[j]) }
        if (pointsPage.retailersList[j].retailerCardOverlayButton.text() == nameOfChosenRetailer) {
            flag = true
        }
    }
    assert flag
}

// 1.18 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
// ----------------------------------------------------------------- retailer cards user logged, removing favourites
When(~/^User go to favourite retailers section$/) { ->
    if (!pointsPage.retailersList[0].favouriteIconEnabled.isDisplayed()) {
        pointsPage.retailersList[0].addRetailerToFavourite()
        waitFor { pointsPage.retailersList[0].favouriteIconEnabled.isDisplayed() }
        waitFor { pointsPage.favouriteButtonEnabled.isDisplayed() }
    }
    pointsPage.clickShowFavouritesButton()
    waitFor { browser.currentUrl.contains('?favourite=true') }
    sleep(500)
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}
When(~/^Remove from the favourite last retailer$/) { ->
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    pointsPage.retailersList[0].removeRetailerFromFavourite()
    waitFor { browser.currentUrl.contains('?favourite=false') }
    sleep(500)
    waitFor { pointsPage.loader.hasClass('ng-hide') }
}
Then(~/^Retailer will be removed and favourite section disabled$/) { ->
    waitFor { pointsPage.favouriteButtonDisabled.isDisplayed() }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    assert !pointsPage.retailersList[0].favouriteIconEnabled.isDisplayed()
    assert pointsPage.favouriteButtonDisabled.isDisplayed()
}

// 1.19 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
// ------------------------------------------------------------------------------------ working of department filter
def chosenDepartmentName
When(~/^User click chosen department on department filter$/) { ->
    waitFor { pointsPage.departmentsFacetModule.departmentFacet.isDisplayed() }
    waitFor { pointsPage.departmentsFacetModule.departmentFacetHeader.text() == envVar.departmentFacetName }
    waitFor { pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic[0].isDisplayed() }
    random = func.returnRandomValue(pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic.size())
    while (pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic[random].hasClass('disabled')) {
        random = func.returnRandomValue(pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic.size())
    }
    waitFor { pointsPage.retailersList[1].isDisplayed() }
    sleep(500)

    browser.interact { moveToElement(pointsPage.retailersList[1]) }
    secondRetailer = pointsPage.retailersList[1].retailerCardOverlayButton.text()
    chosenDepartmentName = pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic[random].text()
    pointsPage.departmentsFacetModule.selectDepartment(random)
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
}
Then(~/^Retailers fill be properly filtered$/) { ->
    waitFor { pointsPage.retailersList[1].isDisplayed() }
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[1]) }
    waitFor {
        !(secondRetailer == pointsPage.retailersList[1].retailerCardOverlayButton.text())
    }
    assert !(secondRetailer == pointsPage.retailersList[1].retailerCardOverlayButton.text())
}
Then(~/^Chosen Department will be set as active on department facet$/) { ->
    waitFor { pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic[random].hasClass('active') }
    assert pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic[random].hasClass('active')
}
Then(~/^Breadcrumb last level show chosen department$/) { ->
    assert pointsPage.breadcrumbModule.breadcrumbSingleElementBasic.last().text().equals(chosenDepartmentName)
}
Then(~/^Seo department heading is displayed$/) { ->
    assert pointsPage.departmentSeoHeading.isDisplayed()
}
Then(~/^Seo department copy is displayed$/) { ->
    assert pointsPage.departmentSeoDescription.isDisplayed()
}

// 1.20 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
// -------------------------------------------------------------------- working of clear button on department filter
When(~/^User click clear button on department filter$/) { ->
    pointsPage.departmentsFacetModule.clickClearButtonOnDepartmentFacet()
}
Then(~/^None of department option will be set as active$/) { ->
    for (int i = 0; i < pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic.size(); i++) {
        assert !(pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonBasic[i].hasClass('active'))
    }
}
Then(~/^Retailers cards will be displayed in initial order$/) { ->
    waitFor { pointsPage.retailersList[1].isDisplayed() }
    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[1]) }
    waitFor { secondRetailer == pointsPage.retailersList[1].retailerCardOverlayButton.text() }
    assert secondRetailer == pointsPage.retailersList[1].retailerCardOverlayButton.text()
}

// 1.22 //  ------------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
// --------------------------------------------------------------------- recently visited retailer component working
When(~/^User go to chosen retailer page using retailer card$/) { ->
    waitFor { pointsPage.retailersList[0].isDisplayed() }
    random = func.returnRandomValue(pointsPage.retailersList.size())
    while (pointsPage.retailersList[random].leadsBadge.isDisplayed()) {
        random = func.returnRandomValue(pointsPage.retailersList.size())
    }
    sleep(500)

    browser.interact { moveToElement(pointsPage.retailersList[random]) }
    firstRetailer = pointsPage.retailersList[random].retailerCardOverlayButton.text()
    browser.withNewWindow({
        pointsPage.retailersList[random].click()
    }, close: true, wait: true) {
        at TransitionPage
        transitionPage = page
        sleep(4000)
    }
}
When(~/^Come back to points page$/) { ->
    func.refreshPage()
    at PointsPage
    pointsPage = page
}
Then(~/^He will se that recently visited retailer component was updated about previous chosen retailer$/) { ->
    waitFor { pointsPage.recentlyVisitedRetailersHeader.text().contains(envVar.recentlyVisitedRetailersHeader) }
    waitFor { pointsPage.recentlyVisitedRetailersRetailerElement[0].text() == firstRetailer }
    assert pointsPage.recentlyVisitedRetailersHeader.text().contains(envVar.recentlyVisitedRetailersHeader)
    assert pointsPage.recentlyVisitedRetailersRetailerElement[0].text() == firstRetailer
}
Then(~/^He will be able to use links from recently visited retailers component$/) { ->
    browser.withNewWindow({ pointsPage.clickChosenRetailerFromRecentlyVisited(0) }, close: true, wait: true) {
        assert transitionPage.transitionPageRetailerInformation.text() == firstRetailer
    }
}

// 2.1 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build Points page (NBO-541)
// --------------------------------------------------------------------------- recently visited and retailers / zone
Given(~/^Proper zone is set '(.+?)'$/) { String zone ->
    waitFor { pointsPage.headerModule.zonePickerFlag[0].isDisplayed() }
    pointsPage.headerModule.epandZonePickerPanel()
    Cookie cookie = new Cookie("key", "value")
    boolean cookieFound = false
    if (zone.equals('UK')) {
        pointsPage.headerModule.choseUKzone()
        Set<Cookie> allCookies = browser.getDriver().manage().getCookies()
        for (Cookie loadedCookie : allCookies) {
            if (loadedCookie.getName() == 'user_zone' && loadedCookie.getValue() == 'UK') {
                cookieFound = true
            }
        }
        assert cookieFound
    } else if (zone.equals('IE')) {
        pointsPage.headerModule.choseIrelandZone()
        Set<Cookie> allCookies = browser.getDriver().manage().getCookies()
        for (Cookie loadedCookie : allCookies) {
            if (loadedCookie.getName() == 'user_zone' && loadedCookie.getValue() == 'IE') {
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
    waitFor { pointsPage.recentlyVisitedRetailersRetailerElement[0].isDisplayed() }
    waitFor { pointsPage.retailersList[0].isDisplayed() }
//    for (int i = 0; i < pointsPage.retailersList.size(); i++) {
//        if (zone.equals('UK')) {
//            assert mySQLConnector.getSingleResult("SELECT COUNT(*) FROM admin_ui_qa.MerchantZone WHERE zone = 'UK' AND merchant_id = '" + mySQLConnector.getSingleResult("SELECT id FROM admin_ui_qa.Merchant WHERE name = '" + pointsPage.cardretailerCardsSearchResults[i].attr('data-retailername') + "'") + "'") == '1'
//        } else if (zone.equals('IE')) {
//            assert mySQLConnector.getSingleResult("SELECT COUNT(*) FROM admin_ui_qa.MerchantZone WHERE zone = 'IE' AND merchant_id = '" + mySQLConnector.getSingleResult("SELECT id FROM admin_ui_qa.Merchant WHERE name = '" + pointsPage.retailerCardsSearchResults[i].attr('data-retailername') + "'") + "'") == '1'
//        }
//    }
    for (int i = 0; i < pointsPage.recentlyVisitedRetailersRetailerElement.size(); i++) {
        if (zone.equals('UK')) {
            assert mySQLConnector.getSingleResult("SELECT COUNT(*) FROM admin_ui.MerchantZone WHERE zone = 'UK' AND merchant_id = '" + mySQLConnector.getSingleResult("SELECT id FROM admin_ui.Merchant WHERE name = '" + pointsPage.recentlyVisitedRetailersRetailerElement[i].text() + "'") + "'") == '1'
        } else if (zone.equals('IE')) {
            assert mySQLConnector.getSingleResult("SELECT COUNT(*) FROM admin_ui.MerchantZone WHERE zone = 'IE' AND merchant_id = '" + mySQLConnector.getSingleResult("SELECT id FROM admin_ui.Merchant WHERE name = '" + pointsPage.recentlyVisitedRetailersRetailerElement[i].text() + "'") + "'") == '1'
        }
    }
}

// 2.2 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build Points page (NBO-541)
// -------------------------------------------------------------- recently visited and retailers after search / zone


When(~/^"(.+?)" department is selected from departments list$/) { String departmentName ->
    pointsPage.departmentsFacetModule.selectDepartmentByName(departmentName)
}

Then(~/^Only "Lead generator" merchants are displayed$/) { ->
    waitFor { pointsPage.retailersList.size() > 0 }
    assert pointsPage.retailersList.leadsBadge.iterator().any { it.isDisplayed() }
    assert pointsPage.retailersList.maxCommissionName.iterator().any { it.isDisplayed() }
}

Then(~/^Stores A-Z filter is narrowed to show only "Lead generator" merchants when used$/) { ->
    random = func.returnRandomValue(pointsPage.lettersFacetModule.azFacetLetterButtonBasic.size())
    while (pointsPage.lettersFacetModule.azFacetLetterButtonBasic[random].attr('class') == 'disabled') {
        random = func.returnRandomValue(pointsPage.lettersFacetModule.azFacetLetterButtonBasic.size())
    }
    pointsPage.lettersFacetModule.clickChosenLetter(random)
    waitFor { pointsPage.loader.hasClass('ng-hide') }
    waitFor { pointsPage.retailersList.size() > 0 }
    assert pointsPage.retailersList.leadsBadge.iterator().any { it.isDisplayed() }
    assert pointsPage.retailersList.maxCommissionName.iterator().any { it.isDisplayed() }
}


Then(~/^"Lead generator" merchants are displayed with different styling than original merchant card$/) { ->
    //TODO all checked in next steps but if something additional appear can be added here
}

Then(~/^"Lead generator" merchants cards contains additional "(.+?)" badge at top of card$/) { badge ->
    waitFor { pointsPage.retailersList.size() > 0 }

    assert pointsPage.retailersList.leadsBadge.iterator().any { it.isDisplayed() }
    assert pointsPage.retailersList.leadsBadge.iterator().any { it.text().contains(badge) }
}

Then(~/^"Lead generator" merchants cards contains information about maximum commission$/) { ->
    assert pointsPage.retailersList.maxCommissionName.iterator().any { it.isDisplayed() }
    assert pointsPage.retailersList.maxCommissionName.iterator().any { it.text().contains("up to") }
    assert pointsPage.retailersList.maxCommissionValue.iterator().any { it.isDisplayed() }
    assert pointsPage.retailersList.maxCommissionValue.iterator().any { it.text().length() != 0 }
}

When(~/^User click on chosen "Lead generator" merchant card$/) { ->
    waitFor { pointsPage.retailersList.size() > 0 }
    random = func.returnRandomValue(pointsPage.retailersList.size())
    browser.interact { moveToElement(pointsPage.retailersList[random]) }
    waitFor { pointsPage.retailersList[random].retailerCardOverlayButton.isDisplayed() }
    nameOfChosenRetailer = pointsPage.retailersList[random].retailerCardOverlayButton.text()
    pointsPage.retailersList[random].click()
}

Then(~/^User is redirected to "Lead generator" merchant page$/) { ->
    at LeadRetailerPage
    leadRetailerPage = page
    assert leadRetailerPage.name == nameOfChosenRetailer
}

def maxCommision
When(~/^"Check" button of chosen merchant from specials promoted block will be clicked$/) { ->
    maxCommision = pointsPage.promotedLeadsRetailerMaxCommision[0].text()
    pointsPage.promotedLeadsRetailerCheckButton[0].click()
}

Then(~/^User is redirected to selected "Lead generator" merchant page$/) { ->
    at LeadRetailerPage
    leadRetailerPage = page
    waitFor { leadRetailerPage.maxCommission.text().contains(maxCommision) }
}

When(~/^"Check more leads" button from specials promoted block will be clicked$/) { ->
    pointsPage.promotedLeadsRetailesModuleCheckMoreLeadsButton.click()
}

Then(~/^Special department will be selected$/) { ->
    waitFor { pointsPage.departmentsFacetModule.departmentFacetDepartmentButtonActiveBasic.text() == "Specials" }
}

When(~/^Merchant Lead ([^"]*) is opened$/) { String merchant ->
    pointsPage.enterPhraseIntoSearch(merchant)
    waitFor { pointsPage.searchResultsDDLOption.size() > 0 }
    pointsPage.searchResultsDDLOption[0].$("a").click()
    at LeadRetailerPage
    leadRetailerPage = page
}

When(~/^User click prizes banner on points page$/) { ->
    waitFor { pointsPage.roulettBanner.isDisplayed() }
    pointsPage.roulettBanner.click()
}