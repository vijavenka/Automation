package stepdefs.EpointsStepDefs.spendSection
import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2014-11-06.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def epointsLink = envVar.epointsLink

    // 1.1 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
    // ---------------------------------------------------------------------------------------------- spend page content
    Given(~/^Spend page is opened$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        Thread.sleep(1000)
        epoints.clickSpendButton()
        waitFor{ browser.title == envVar.spendPageTitle }
    }
    When(~/^User look at spend landing page$/) { ->
        waitFor{ browser.title == envVar.spendPageTitle }
        //TODO what happens with item counter link
        //waitFor{ epoints.spendPage.spendPageHeader.text() == envVar.returnSpendPageHeaderText(epoints.spendPage.itemCounterLink.text()) }
        assert browser.title == envVar.spendPageTitle
        //assert epoints.spendPage.spendPageHeader.text() == envVar.returnSpendPageHeaderText(epoints.spendPage.itemCounterLink.text())
    }
    Then(~/^He can see main items counter$/) { ->
        //waitFor{ epoints.spendPage.itemCounterLink.isDisplayed() }
        //epoints.spendPage.itemCounterLink.isDisplayed()
    }
    Then(~/^He can see search component$/) { ->
        waitFor{ epoints.spendPage.searchInputField.isDisplayed() }
        waitFor{ epoints.spendPage.rewardByEpointsDDL.isDisplayed() }
        waitFor{ epoints.spendPage.searchButton.isDisplayed() }
        assert epoints.spendPage.searchInputField.isDisplayed()
        assert epoints.spendPage.rewardByEpointsDDL.isDisplayed()
        assert epoints.spendPage.searchButton.isDisplayed()
    }
    Then(~/^He can see two tabs with basic filter selection$/) { ->
        waitFor{ epoints.spendPage.browseByDepartmensTab.text() == envVar.departmentsTabName }
        waitFor{ epoints.spendPage.rewardsByEpointsTotalTab.text() == envVar.rewardsByEpointsTabName }
        assert epoints.spendPage.browseByDepartmensTab.text() == envVar.departmentsTabName
        assert epoints.spendPage.rewardsByEpointsTotalTab.text() == envVar.rewardsByEpointsTabName
    }
    Then(~/^He can see top generic banner$/) { ->
        epoints = page
        waitFor{ epoints.spendPage.topBannerRow.isDisplayed() }
        assert epoints.spendPage.topBannerColumnImageBasic(0).isDisplayed()
        assert epoints.spendPage.topBannerColumnImageBasic(1).isDisplayed()
        assert epoints.spendPage.topBannerColumnImageBasic(2).isDisplayed()
        assert epoints.spendPage.topBannerColumnDescriptionBasic(0).text().replace('\n','').replace(' ','') == envVar.spendPageBanner1ColumnText.replace('\n','').replace(' ','')
        assert epoints.spendPage.topBannerColumnDescriptionBasic(1).text().replace('\n','').replace(' ','') == envVar.spendPageBanner2ColumnText.replace('\n','').replace(' ','')
        assert epoints.spendPage.topBannerColumnDescriptionBasic(2).text().replace('\n','').replace(' ','') == envVar.spendPageBanner3ColumnText.replace('\n','').replace(' ','')
    }

    // 1.2 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
    // ----------------------------------------------------------------------------------------- redemption counter link
    String redemptionItemsNumber
    When(~/^User click redemption counter link$/) { ->
        waitFor{ epoints.spendPage.itemCounterLink }
        redemptionItemsNumber = epoints.spendPage.itemCounterLink.text()
        epoints.spendPage.clickItemCounterLink()
    }
    Then(~/^He will be redirected to browse rewards page$/) { ->
        waitFor{ browser.title == envVar.browseRewardsPageTitle }
        assert browser.title == envVar.browseRewardsPageTitle
    }
    Then(~/^Item number on search settings panel will be the same as on redemption counter$/) { ->
        waitFor{ epoints.browseRewardsPage.outOfElement.text().contains(redemptionItemsNumber.replace(',','')) }
        assert epoints.browseRewardsPage.outOfElement.text().contains(redemptionItemsNumber.replace(',',''))
    }

    // 1.3 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
    // ----------------------------------------------------------------------------- search with points range DDL filter
    When(~/^User enter some phrase '(.+?)' into search input field$/) { String phrase->
        epoints.spendPage.enterTextIntoSearch(phrase)
    }
    When(~/^User select some redemption range '(.+?)' from ddl$/) { String pointsRange ->
        epoints.spendPage.expandEpointsRangeDDL()
        if(pointsRange.equals('All rewards')){
            epoints.spendPage.choseRangeOption(0)
        }else if(pointsRange.equals('100+')){
            epoints.spendPage.choseRangeOption(1)
        }else if(pointsRange.equals('1,000+')){
            epoints.spendPage.choseRangeOption(2)
        }else if(pointsRange.equals('5,000+')){
            epoints.spendPage.choseRangeOption(3)
        }else if(pointsRange.equals('20,000+')){
            epoints.spendPage.choseRangeOption(4)
        }else if(pointsRange.equals('50,000+')){
            epoints.spendPage.choseRangeOption(5)
        }else if(pointsRange.equals('100,000+')){
            epoints.spendPage.choseRangeOption(6)
        }
    }
    When(~/^User click search button on spend page$/) { ->
        Thread.sleep(3000)
        epoints.spendPage.clickSearchButton()
    }
    Then(~/^Filters will be properly set on browse rewards page according to typed phrase and chosen points range '(.+?)' '(.+?)'$/) { String phrase, String pointsRange ->
        waitFor{ epoints.browseRewardsPage.epointsFilterModuleOptionActive.text() == pointsRange }
        waitFor{ epoints.browseRewardsPage.rewardByEpointsDDL.text() == pointsRange }
        waitFor{ epoints.browseRewardsPage.searchInputField.value() == phrase }
        assert epoints.browseRewardsPage.epointsFilterModuleOptionActive.text() == pointsRange
        assert epoints.browseRewardsPage.rewardByEpointsDDL.text() == pointsRange
        assert epoints.browseRewardsPage.searchInputField.value() == phrase
    }
    Then(~/^Results will be appropriate to typed phrase and chosen points range '(.+?)' '(.+?)'$/) {  String phrase, String pointsRange->
        int currentpointsValue
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic }
        for(int i=0; i<epoints.browseRewardsPage.redemptionCardBasic.size(); i++) {
            assert epoints.browseRewardsPage.redemptionCardNameBasic[i].text().toLowerCase().replace('-', '').contains(phrase.toLowerCase())
            currentpointsValue = Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',', ''))
            checkIfRedemptionPointValuesAreInProperRange(pointsRange, currentpointsValue)
            if(i>10){
                break
            }
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

    // 1.4 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
    // ------------------------------------------------------------------------------------------- browse by departments
    When(~/^User go to 'browse by departments' tab$/) { ->
        epoints.spendPage.goToBrowseByDepartmentTab()
    }
    String chosenDepartmentName
    String itemsNumberInChosenDepartment
    When(~/^User select some department card$/) { ->
        waitFor{ epoints.spendPage.departmentCardBasic.size() == 12 }
        random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        while(epoints.spendPage.departmentCardBasic[random].hasClass('is-disabled')){
            random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        }
        chosenDepartmentName = epoints.spendPage.departmentCardDepartmentNameBasic[random].text()
        itemsNumberInChosenDepartment = epoints.spendPage.departmentCardItemsNumberBasic[random].text().replace(',','').replace(' items','')
        epoints.spendPage.clickChosenDepartmentCard(random)
    }
    Then(~/^Department facet should be already chosen$/) { ->
        waitFor{ epoints.browseRewardsPage.breadcrumbElementBasic[1].text().contains(chosenDepartmentName) }
        assert epoints.browseRewardsPage.breadcrumbElementBasic[1].text().contains(chosenDepartmentName)
    }
    Then(~/^Products number should be correct according to department card counter$/) { ->
        System.out.println(itemsNumberInChosenDepartment)
        System.out.println(epoints.browseRewardsPage.outOfElement.text())
        waitFor{ epoints.browseRewardsPage.outOfElement.text().contains(itemsNumberInChosenDepartment) }
        assert epoints.browseRewardsPage.outOfElement.text().contains(itemsNumberInChosenDepartment)
    }

    // 1.5 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
    // ---------------------------------------------------------------------------------------- rewards by epoints total
    When(~/^User go to 'rewards by epoints total' tab$/) { ->
        epoints.spendPage.goToRewardsByEpointsTotalTab()
    }
    String pointsRangeOfChosenCard
    String itemsNumberInChosenCard
    When(~/^User select some epoints value card$/) { ->
        waitFor{ epoints.spendPage.rewardsCardBasic.size() == 6 }
        waitFor{ epoints.spendPage.rewardsCardPointsRangeBasic.isDisplayed() }
        waitFor{ epoints.spendPage.rewardsCardItemsNumberBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.spendPage.rewardsCardBasic.size())
        pointsRangeOfChosenCard = epoints.spendPage.rewardsCardPointsRangeBasic[random].text()
        itemsNumberInChosenCard = epoints.spendPage.rewardsCardItemsNumberBasic[random].text().replace(',','').replace(' items','')
        epoints.spendPage.clickChosenRewardCard(random)
    }
    Then(~/^Presented redemptions will be in proper ranges according to chosen epoints value card$/) { ->
        int currentpointsValue
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic }
        for(int i=0; i<epoints.browseRewardsPage.redemptionCardBasic.size(); i++) {
            currentpointsValue = Integer.parseInt(epoints.browseRewardsPage.redemptionCardPointsValueBasic[i].text().replace(',', ''))
            checkIfRedemptionPointValuesAreInProperRange(pointsRangeOfChosenCard, currentpointsValue)
        }
        waitFor{ epoints.browseRewardsPage.outOfElement.text().contains(itemsNumberInChosenCard) }
        assert epoints.browseRewardsPage.outOfElement.text().contains(itemsNumberInChosenCard)
    }

    // 1.6 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
    // ----------------------------------------------------- redemption counter link for logged user / high points value
    Given(~/^Spend page is opened by logged user$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickSignInButton()
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
        epoints.clickSpendButton()
        waitFor{ browser.title == envVar.spendPageTitle }
    }
    String availableItemsInfo
    Then(~/^He can see main available items counter$/) { ->
        waitFor{ epoints.spendPage.itemAvailableInfoAfterLogin.isDisplayed() }
        assert epoints.spendPage.itemAvailableInfoAfterLogin.text().contains(envVar.itemsCounterTextForLoggedUser)
        availableItemsInfoOnSpend = epoints.spendPage.itemAvailableInfoAfterLogin.text()
        Thread.sleep(500)
        while(epoints.spendPage.itemAvailableInfoAfterLogin.text() != availableItemsInfoOnSpend){
            Thread.sleep(500)
            availableItemsInfoOnSpend = epoints.spendPage.itemAvailableInfoAfterLogin.text()
        }
    }
    When(~/^User click items counter$/) { ->
        epoints.spendPage.clickItemCounterAfterLogin()
    }
    Then(~/^He will be redirected to spend list page$/) { ->
        waitFor{ browser.currentUrl.contains(envVar.epointsLink + envVar.browseRewardURL) }
        waitFor{ epoints.browseRewardsPage.itemAvailableInfoAfterLogin.isDisplayed() }
        availableItemsInfoOnSpendList = epoints.browseRewardsPage.itemAvailableInfoAfterLogin.text()
        Thread.sleep(500)
        while(epoints.browseRewardsPage.itemAvailableInfoAfterLogin.text() != availableItemsInfoOnSpendList){
            Thread.sleep(500)
            availableItemsInfoOnSpendList = epoints.browseRewardsPage.itemAvailableInfoAfterLogin.text()
        }
        assert availableItemsInfoOnSpendList == availableItemsInfoOnSpend
    }

    // 1.7 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
    // ------------------------------------------------------ redemption counter link for logged user / low points value
    Then(~/^He will not see items counter because any of them is available for him$/) { ->
        waitFor{ !epoints.spendPage.itemAvailableInfoAfterLogin.isDisplayed() }
        assert !epoints.spendPage.itemAvailableInfoAfterLogin.isDisplayed()
    }