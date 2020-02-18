package com.iat.stepdefs.pointsSection

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.VouchersPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def voucherPage = new VouchersPage()

def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()

def random

Given(~/^Voucher page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.headerModule.clickOnPointsButton()
    epointsHomePage.headerModule.clickPointsSubNavigationOption("Vouchers")
    at VouchersPage
    voucherPage = page

}

// 1.1 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
// --------------------------------------------------------------------------------------- not logged, expiry filter
// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
When(~/^User select chosen expiry filter '(.+?)'$/) { String filterName ->
    waitFor { voucherPage.voucherCards.size() > 0 }
    voucherPage.selectSortFilterByName(filterName)
    sleep(1000)
    waitFor { voucherPage.voucherCards.get(0).voucherExpiryDate.isDisplayed() }
}
Then(~/^Voucher results will be displayed in chosen filter order '(.+?)'$/) { String filter ->
    long currentDate, nextDate;
    for (int i = 0; i < voucherPage.voucherCards.size() - 1; i++) {
        currentDate = func.convertDateToEpochFormat(voucherPage.voucherCards.get(i).voucherExpiryDate.text().replace('expires on ', ''), "dd/MM/yyy")
        nextDate = func.convertDateToEpochFormat(voucherPage.voucherCards.get(i + 1).voucherExpiryDate.text().replace('expires on ', ''), "dd/MM/yyy")

        if (filter == 'expirysoonest') {
            assert currentDate <= nextDate
        } else if (filter == 'expirylatest') {
            assert currentDate >= nextDate
        }
    }
}

// 1.2 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
// ------------------------------------------------------------------ not logged, department filter and clear button
// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
String facetName
String initialFacetVouchersNumber
//String initialFacetVouchersNumberSecondLevel
String allVouchersNumber
String firstVoucherDescription
String firstVoucherName
When(~/^User select some department on department filter$/) { ->
    waitFor { voucherPage.departmentFacet.isDisplayed() }
    waitFor { voucherPage.breadCrumbNumberOfElementsFound.isDisplayed() }
    waitFor { voucherPage.departmentFacetHeader.text() == envVar.departmentFacetNameOnVouchers }
    assert voucherPage.departmentFacetHeader.text() == envVar.departmentFacetNameOnVouchers
    allVouchersNumber = voucherPage.breadCrumbNumberOfElementsFound.text().replace(' items found', '').replace(' item found', '').replace(': ', '')
    random = func.returnRandomValue(voucherPage.departmentFacetDepartmentButtonBasic.size())
    facetName = voucherPage.departmentFacetDepartmentButtonBasic[random].text()
    initialFacetVouchersNumber = voucherPage.departmentFacetDepartmentButtonBasicCount[random].text().replace('(', '').replace(')', '')
    firstVoucherName = voucherPage.voucherCards.get(0).voucherName.text()
    voucherPage.selectChosenDepartment(random)
}
Then(~/^Category facet will appear$/) { ->
    waitFor { voucherPage.categoryFacet.isDisplayed() }
}
Then(~/^Vouchers will be properly filtered according to chosen department$/) { ->
//    assert !(voucherPage.voucherNameBasic[0].text() == firstVoucherName)
    waitFor { voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
    assert voucherPage.outOfElement.text().contains(initialFacetVouchersNumber)
    if (Integer.parseInt(initialFacetVouchersNumber) <= 20) {
        waitFor { voucherPage.voucherCards.size() == Integer.parseInt(initialFacetVouchersNumber) }
        assert voucherPage.voucherCards.size() == Integer.parseInt(initialFacetVouchersNumber)
    }
}
Then(~/^Bradcrumb will show proper department name$/) { ->
    waitFor { voucherPage.breadCrumbValues[1].text() == facetName }
    assert voucherPage.breadCrumbValues[1].text() == facetName
}
When(~/^User click 'all departments' button on breadcrumb to show initial results$/) { ->
    waitFor { voucherPage.breadCrumbValues[0].isDisplayed() }
    waitFor { voucherPage.breadCrumbValues[0].text() == 'All departments' }
    assert voucherPage.breadCrumbValues[0].isDisplayed()
    assert voucherPage.breadCrumbValues[0].text() == 'All departments'
    voucherPage.clickChosenBreadcrumbElement(0)
}
Then(~/^Category facet will disappear$/) { ->
    waitFor { !voucherPage.categoryFacet.isDisplayed() }
}
Then(~/^Results will be reset$/) { ->
    waitFor { voucherPage.outOfElement.text().contains(allVouchersNumber) }
    waitFor { voucherPage.voucherCards.get(0).voucherName.text() == firstVoucherName }
    assert voucherPage.outOfElement.text().contains(allVouchersNumber)
    if (Integer.parseInt(allVouchersNumber) <= 20) {
        waitFor { voucherPage.voucherCards.size() == Integer.parseInt(allVouchersNumber) }
        assert voucherPage.voucherCards.size() == Integer.parseInt(allVouchersNumber)
    }
}

// 1.3 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
// -------------------------------------------------------------------- not logged, merchant filter and clear button
// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
When(~/^User select some merchant on merchant filter$/) { ->
    waitFor { voucherPage.merchantFacet.isDisplayed() }
    waitFor { voucherPage.breadCrumbNumberOfElementsFound.isDisplayed() }
    waitFor { voucherPage.merchantFacetHeader.text().contains(envVar.merchantFacetNameOnVouchers) }
    assert voucherPage.merchantFacetHeader.text().contains(envVar.merchantFacetNameOnVouchers)
    allVouchersNumber = voucherPage.breadCrumbNumberOfElementsFound.text().replace(' items found', '').replace(' item found', '').replace(': ', '')

    random = voucherPage.merchantFacetMerchantButtonBasic.size() - 2
    //below code is 100% good if merchants on qa will return some results(if enabled above line should be removed)
//    for (int i = 0; i < voucherPage.merchantFacetMerchantButtonBasic.size(); i++) {
//        if (voucherPage.merchantFacetMerchantButtonBasic[i].text() != voucherPage.voucherCards.get(0).voucherMerchantName.text()) {
//            random = i
//            break
//        }
//    }
    facetName = voucherPage.merchantFacetMerchantButtonBasic[random].text()
    firstVoucherName = voucherPage.voucherCards.get(0).voucherName.text()
    voucherPage.selectChosenMerchant(random)
}
Then(~/^Vouchers will be properly filtered according to chosen merchant$/) { ->
    if (firstVoucherName != "") {
        assert !(voucherPage.voucherCards.get(0).voucherName.text() == firstVoucherName)
    }
}
When(~/^Bradcrumb will show proper merchant name$/) { ->
    waitFor { voucherPage.breadCrumbValues[1].text() == facetName }
    assert voucherPage.breadCrumbValues[1].text() == facetName
}
When(~/^User click 'clear' button on merchant facet$/) { ->
    voucherPage.clikClearButtonOnMerchantFacet()
}

// 1.4 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
// -------------------------------------------------------------------- not logged, category filter and clear button
// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
String chosenDepartmentName
String chosenCategoryName
String chosenMerchantName
Given(~/^Some department is chosen$/) { ->
    waitFor { voucherPage.departmentFacet.isDisplayed() }
    waitFor { voucherPage.voucherCards.get(0).isDisplayed() }
    waitFor { !voucherPage.categoryFacet.isDisplayed() }
    assert !voucherPage.categoryFacet.isDisplayed()
    random = func.returnRandomValue(voucherPage.departmentFacetDepartmentButtonBasic.size())
    allVouchersNumber = voucherPage.departmentFacetDepartmentButtonBasicCount[random].text()
    chosenDepartmentName = voucherPage.departmentFacetDepartmentButtonBasic[random].text()
    voucherPage.selectChosenDepartment(random)
    sleep(500)
    waitFor { voucherPage.voucherCards.get(0).isDisplayed() }
}
When(~/^User select some category on category filter$/) { ->
    waitFor { voucherPage.categoryFacet.isDisplayed() }
    waitFor { voucherPage.breadCrumbNumberOfElementsFound.isDisplayed() }
    waitFor { voucherPage.categoryFacetHeader.text().contains(envVar.categoryFacetNameOnVouchers) }
    assert voucherPage.categoryFacetHeader.text().contains(envVar.categoryFacetNameOnVouchers)
    allVouchersNumber = voucherPage.breadCrumbNumberOfElementsFound.text().replace(' items found', '').replace(' item found', '').replace(': ', '')
    firstVoucherName = voucherPage.voucherCards.get(0).voucherName.text()

    random = func.returnRandomValue(voucherPage.categoryFacetCategoryButtonBasic.size())
    facetName = voucherPage.categoryFacetCategoryButtonBasic[random].text()
    initialFacetVouchersNumber = voucherPage.categoryFacetCategoryButtonBasicCount[random].text().replace('(', '').replace(')', '')
    voucherPage.selectChosenCategory(random)


}
Then(~/^Vouchers will be properly filtered according to chosen category$/) { ->
    if (firstVoucherName != "") {
        assert !(voucherPage.voucherCards.get(0).voucherName.text() == firstVoucherName)
    }

    if (voucherPage.categoryFacetCategoryButtonBasic.size() > 0) {
        waitFor { voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
        assert voucherPage.outOfElement.text().contains(initialFacetVouchersNumber)
        if (Integer.parseInt(initialFacetVouchersNumber) <= 40) {
            waitFor {
                voucherPage.voucherCards.size() == Integer.parseInt(initialFacetVouchersNumber)
            }
            assert voucherPage.voucherCards.size() == Integer.parseInt(initialFacetVouchersNumber)
        }
    }
}
When(~/^Bradcrumb will show proper category name$/) { ->
    if (voucherPage.categoryFacetCategoryButtonBasic.size() > 0) {
        waitFor { voucherPage.breadCrumbValues[2].text() == facetName }
        assert voucherPage.breadCrumbValues[2].text() == facetName
    }
}
When(~/^User click 'clear' button on category facet$/) { ->
    voucherPage.clikClearButtonOnCategoryFacet()
}

// 1.7 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
// ------------------------------------------------------------------------------------ not logged, clear all button
// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
Given(~/^Some merchant is chosen$/) { ->
    if (voucherPage.merchantFacetMerchantButtonBasic.size() > 0) {
        chosenMerchantName = voucherPage.merchantFacetMerchantButtonBasic[0].text()
        voucherPage.selectChosenMerchant(0)
    }
}
Given(~/^Some category is chosen$/) { ->
    if (voucherPage.categoryFacetCategoryButtonBasic.size() > 0) {
        chosenCategoryName = voucherPage.categoryFacetCategoryButtonBasic[0].text()
        voucherPage.selectChosenCategory(0)
    }
}
Then(~/^Vouchers results are filtered and breadcrumb is in use$/) { ->
    firstVoucherName = voucherPage.voucherCards.get(0).voucherName.text()
    waitFor { voucherPage.breadCrumbValues.size() >= 3 }
}
When(~/^User click clear all button$/) { ->
    voucherPage.clickClarAllButton()
}
Then(~/^Results will be set to initial position and no filter will be selected$/) { ->
    waitFor { !voucherPage.categoryFacet.isDisplayed() }
    assert !voucherPage.categoryFacet.isDisplayed()
    assert voucherPage.departmentFacet.isDisplayed()
    if (firstVoucherName != "") {
        assert !(firstVoucherName == voucherPage.voucherCards.get(0).voucherName.text())
    }
    assert voucherPage.breadCrumbValues.size() == 1
}

// 1.9 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
// ------------------------------------------------------------------ not logged, breadcrumb independent more than 3
// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
Then(~/^Breadcrumb shows selected filters$/) { ->
    waitFor { voucherPage.breadCrumbValues[3].isDisplayed() }
    assert voucherPage.breadCrumbValues[1].text() == chosenDepartmentName
    assert voucherPage.breadCrumbValues[2].text() == chosenCategoryName
    assert voucherPage.breadCrumbValues[3].text() == chosenMerchantName
}

// 1.11 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
// ----------------------------------------------------------------------------- not logged, get voucher code button
// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
String voucherRetailerName
When(~/^User click 'get voucher code' button of chosen voucher$/) { ->
    waitFor { voucherPage.voucherCards.get(0).isDisplayed() }
    random = func.returnRandomValue(voucherPage.voucherCards.size())
    waitFor { !voucherPage.voucherCards.get(random).voucherCode.isDisplayed() }
    assert !voucherPage.voucherCards.get(random).voucherCode.isDisplayed()
    assert !voucherPage.voucherCards.get(random).openSiteButton.isDisplayed()
    voucherRetailerName = voucherPage.voucherCards.get(random).voucherMerchantName.text()
    browser.withNewWindow({
        voucherPage.voucherCards.get(random).clickGetVoucherCodeButton()
    }, close: true, wait: true) {
        waitFor { browser.getTitle().contains("Sending to retailer | epoints") }

    }
}
Then(~/^New page with transition page will be opened$/) { ->
    //done in step 'User click 'get voucher code' button of chosen voucher'
}
Then(~/^Voucher code will be displayed on chosen voucher card$/) { ->
    waitFor { voucherPage.voucherCards.get(random).voucherCode.isDisplayed() }
    assert voucherPage.voucherCards.get(random).voucherCode.isDisplayed()
    assert voucherPage.voucherCards.get(random).openSiteButton.isDisplayed()
    assert voucherPage.voucherCards.get(random).voucherCodeHelpText.text() == envVar.voucherCodeHelpText
}

//// 1.12 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
//// --------------------------------------------------------------------- not logged, individual voucher page content
//// Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
//String voucherName
//String voucherExpiryDate
//String individualVoucherPageLink
//Given(~/^Chosen voucher elements are saved and individual voucher page is opened$/) { ->
//    waitFor { voucherPage.voucherCard.isDisplayed() }
//    random = func.returnRandomValue(voucherPage.voucherCard.size())
//    firstVoucherDescription = voucherPage.voucherDescriptionBasic[random].text()
//    voucherRetailerName = voucherPage.voucherMerchantNameBasic[random].text()
//    voucherName = voucherPage.voucherNameBasic[random].text()
//    voucherExpiryDate = voucherPage.voucherExpiryDateBasic[random].text()
//    //getting individual voucher page
//    waitFor { voucherPage.voucherShareGoogleBasic[random].isDisplayed() }
//    browser.withNewWindow({
//        voucherPage.clickShareGoogleOptionOfChosenVoucher(random)
//    }, close: true, wait: true) {
//        sleep(1000)
//        if ($('#Email').isDisplayed()) {
//            if ($('#signIn').isDisplayed()) {
//                $('#Email').value(envVar.gmailTestEmail)
//                $('#Passwd').value(envVar.gmailTestEmailPassword)
//                $('#signIn').click()
//            } else if ($('#next').isDisplayed()) {
//                $('#Email').value(envVar.gmailTestEmail)
//                $('#next').click()
//                waitFor { $('#signIn').isDisplayed() }
//                $('#Passwd').value(envVar.gmailTestEmailPassword)
//                $('#signIn').click()
//            }
//        }
//        waitFor(20) { $('.d-s.ot-anchor').isDisplayed() }
//        individualVoucherPageLink = $('.d-s.ot-anchor').text()
//    }
//    browser.go(individualVoucherPageLink)
//}
//Then(~/^All voucher card elements on individual page are the same as on voucher page$/) { ->
//    waitFor { voucherPage.indVoucherCard.isDisplayed() }
//    waitFor { voucherPage.indVoucherMerchantNameBasic }
//    //waitFor{ voucherPage.indVoucherNameBasic.isDisplayed() }
//    waitFor { voucherPage.indVoucherDescriptionBasic.isDisplayed() }
//    waitFor { voucherPage.indVoucherExpiryDateBasic.isDisplayed() }
//    waitFor { voucherPage.indGetVoucherCodeButtonBasic.isDisplayed() }
//    waitFor { voucherPage.indVoucherShareFacebookBasic.isDisplayed() }
//    waitFor { voucherPage.indVoucherShareTwitterBasic.isDisplayed() }
//    waitFor { voucherPage.indVoucherShareGoogleBasic.isDisplayed() }
//    assert voucherPage.indVoucherMerchantNameBasic.text().contains(voucherRetailerName)
//    assert voucherPage.indVoucherNameBasic.text() == voucherName
//    assert voucherPage.indVoucherDescriptionBasic.text() == firstVoucherDescription
//    assert voucherPage.indVoucherExpiryDateBasic.text() == voucherExpiryDate
//    assert voucherPage.indGetVoucherCodeButtonBasic.isDisplayed()
//    assert voucherPage.indVoucherShareFacebookBasic.isDisplayed()
//    assert voucherPage.indVoucherShareTwitterBasic.isDisplayed()
//    assert voucherPage.indVoucherShareGoogleBasic.isDisplayed()
//}
//
//// 1.13 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
//// ---------------------------------------------------------------- ind voucher page, main - get voucher code button
//Given(~/^Individual voucher page is opened$/) { ->
//    epointsPage.url = epointsLink
//    to epointsPage
//    epoints = page
//    func.clearCookiesAndStorage()
//    epoints.clickPointsButton()
//    epoints.azPage.clickVoucherButton()
//    waitFor { voucherPage.voucherShareGoogleBasic.isDisplayed() }
//    browser.withNewWindow({ voucherPage.clickShareGoogleOptionOfChosenVoucher(0) }, close: true, wait: true) {
//        sleep(1000)
//        if ($('#Email').isDisplayed()) {
//            if ($('#signIn').isDisplayed()) {
//                $('#Email').value(Config.epointsUser)
//                $('#Passwd').value(Config.epointsUserPassword)
//                $('#signIn').click()
//            } else if ($('#next').isDisplayed()) {
//                $('#Email').value(Config.epointsUser)
//                $('#next').click()
//                waitFor { $('#signIn').isDisplayed() }
//                $('#Passwd').value(Config.epointsUserPassword)
//                $('#signIn').click()
//            }
//        }
//        waitFor(20) { $('.d-s.ot-anchor').isDisplayed() }
//        individualVoucherPageLink = $('.d-s.ot-anchor').text()
//    }
//    browser.go(individualVoucherPageLink)
//}
//When(~/^User click 'get voucher code' button of main voucher on individual voucher page$/) { ->
//    waitFor { voucherPage.indVoucherMerchantNameBasic.isDisplayed() }
//    voucherRetailerName = voucherPage.indVoucherMerchantNameBasic.text()
//    browser.withNewWindow({
//        voucherPage.clickGetVoucherCodeButtonOfMainVoucherOnIndividualVoucherPage()
//    }, close: true, wait: true) {
//        waitFor { browser.getTitle().contains(envVar.transitionPageTag) }
//        assert browser.getTitle().contains(envVar.transitionPageTag)
//        waitFor { epoints.transitionPage.informationModal.isDisplayed() }
//        assert epoints.transitionPage.transitionPageRetailerInformation.text() == voucherRetailerName.replace(':', '')
//        assert epoints.transitionPage.informationModal.isDisplayed()
//        assert epoints.transitionPage.informationModalNoButton.isDisplayed()
//        assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
//    }
//}
//Then(~/^Voucher code will be displayed on main voucher card on individual voucher page$/) { ->
//    waitFor { voucherPage.indVoucherCode.isDisplayed() }
//    assert voucherPage.indVoucherCode.isDisplayed()
//}
//Then(~/^Go to retailer button will be displayed on main voucher card on individual voucher page$/) { ->
//    waitFor { voucherPage.indGetVoucherCodeButtonBasic.isDisplayed() }
//    assert voucherPage.indGetVoucherCodeButtonBasic.text() == envVar.goToretailerButtonText
//}
//
//// 1.15 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
//// ------------------------------------------------------------------------ ind voucher page, more - proper retailer
//def retailerNameOfChosenVoucher
//boolean moreVoucherAvailable = false
//Given(~/^Individual voucher page is opened with more vouchers section$/) { ->
//    epointsPage.url = epointsLink
//    to epointsPage
//    epoints = page
//    func.clearCookiesAndStorage()
//    epoints.clickPointsButton()
//    epoints.azPage.clickVoucherButton()
//    waitFor { voucherPage.merchantFacetMerchantButtonBasicCount.isDisplayed() }
//    for (int i = 0; i < voucherPage.merchantFacetMerchantButtonBasicCount.size(); i++) {
//        if (voucherPage.merchantFacetMerchantButtonBasicCount[i].text() != '1') {
//            random = i
//            moreVoucherAvailable = true
//        }
//    }
//    if (moreVoucherAvailable) {
//        retailerNameOfChosenVoucher = voucherPage.merchantFacetMerchantButtonBasic[random].text()
//        voucherPage.selectChosenMerchant(random)
//        sleep(500)
//        waitFor { voucherPage.voucherShareGoogleBasic }
//        browser.withNewWindow({ voucherPage.clickShareGoogleOptionOfChosenVoucher(0) }, close: true) {
//            sleep(1000)
//            if ($('#Email').isDisplayed()) {
//                if ($('#signIn').isDisplayed()) {
//                    $('#Email').value(Config.epointsUser)
//                    $('#Passwd').value(Config.epointsUserPassword)
//                    $('#signIn').click()
//                } else if ($('#next').isDisplayed()) {
//                    $('#Email').value(Config.epointsUser)
//                    $('#next').click()
//                    waitFor { $('#signIn').isDisplayed() }
//                    $('#Passwd').value(Config.epointsUserPassword)
//                    $('#signIn').click()
//                }
//            }
//            waitFor(20) { $('.d-s.ot-anchor').isDisplayed() }
//            individualVoucherPageLink = $('.d-s.ot-anchor').text()
//        }
//        browser.go(individualVoucherPageLink)
//    }
//}
//When(~/^User look on individual voucher page$/) { ->
//
//}
//Then(~/^He can see that additional vouchers belongs to proper retailer$/) { ->
//    if (moreVoucherAvailable) {
//        waitFor { voucherPage.moreVouchersVoucherCard.isDisplayed() }
//        for (int i = 0; i < voucherPage.moreVouchersVoucherCard.size(); i++) {
//            assert voucherPage.moreVouchersVoucherMerchantNameBasic.text() == retailerNameOfChosenVoucher
//        }
//    }
//}
//Then(~/^More voucher section header has proper name$/) { ->
//    if (moreVoucherAvailable) {
//        waitFor { voucherPage.indVoucherMoreVouchersSectionHeader }
//        assert voucherPage.indVoucherMoreVouchersSectionHeader.text().contains(retailerNameOfChosenVoucher)
//    }
//}
//
//// 1.16 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
//// ------------------------------------------------------------------------- ind voucher page, more - see all button
//When(~/^User click se all button in more vouchers section$/) { ->
//    if (moreVoucherAvailable) {
//        voucherPage.clickSeeAllButtonOnIndividualVoucherPage()
//    }
//}
//Then(~/^He will be redirected to voucher page$/) { ->
//    if (moreVoucherAvailable) {
//        waitFor { browser.currentUrl.contains(envVar.epointsLink + envVar.voucherPageURL) }
//        assert browser.currentUrl.contains(envVar.epointsLink + envVar.voucherPageURL)
//    }
//}
//Then(~/^All displayed vouchers are belongs to same selected merchant$/) { ->
//    if (moreVoucherAvailable) {
//        sleep(1000)
//        waitFor { voucherPage.voucherCard.isDisplayed() }
//        for (int i = 0; i < voucherPage.voucherCard.size(); i++)
//            assert voucherPage.voucherMerchantNameBasic[i].text() == retailerNameOfChosenVoucher
//    }
//}
//
//// 1.17 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
//// ---------------------------------------------------------------- ind voucher page, more - get voucher code button
//When(~/^User click 'get voucher code' button of other voucher on individual voucher page$/) { ->
//    waitFor { voucherPage.moreVouchersVoucherMerchantNameBasic.isDisplayed() }
//    voucherRetailerName = voucherPage.moreVouchersVoucherMerchantNameBasic[0].text()
//    browser.withNewWindow({
//        voucherPage.clickGetVoucherCodeButtonOfOtherVoucherOnIndividualVoucherPage(0)
//    }, close: true, wait: true) {
//        waitFor { browser.getTitle().contains(envVar.transitionPageTag) }
//        assert browser.getTitle().contains(envVar.transitionPageTag)
//        waitFor { epoints.transitionPage.informationModal.isDisplayed() }
//        assert epoints.transitionPage.transitionPageRetailerInformation.text() == voucherRetailerName.replace(':', '')
//        assert epoints.transitionPage.informationModal.isDisplayed()
//        assert epoints.transitionPage.informationModalNoButton.isDisplayed()
//        assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
//    }
//}
//Then(~/^Voucher code will be displayed on other voucher card on individual voucher page$/) { ->
//    waitFor { voucherPage.moreVouchersVoucherCode.isDisplayed() }
//    assert voucherPage.moreVouchersVoucherCode.isDisplayed()
//}
//Then(~/^Go to retailer button will be displayed on other voucher card on individual voucher page$/) { ->
//    waitFor { voucherPage.moreVouchersGoToRetailerButtonBasic.isDisplayed() }
//    assert voucherPage.moreVouchersGoToRetailerButtonBasic.text() == envVar.openSiteButtonText
//}
//
//// 1.18 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
//// ----------------------------------------------------------------------------- ind voucher page, more - share options
//When(~/^User click chosen share option '(.+?)' of other voucher on individual voucher page$/) { String shareOption ->
//    waitFor(20) { voucherPage.moreVouchersVoucherCard.isDisplayed() }
//    if (shareOption == 'facebook') {
//        waitFor { voucherPage.moreVouchersVoucherShareFacebookBasic.isDisplayed() };
//        browser.withNewWindow({
//            voucherPage.clickShareFacebookOptionOfOtherVoucherOnIndividualVoucherPage(0)
//        }, close: true, wait: true) {
//            waitFor { $('#email').isDisplayed() }
//            $('#email').value(Config.epointsUser)
//            $('#pass').value(Config.epointsUserPassword)
//            $('#loginbutton').click()
//            waitFor { browser.getCurrentUrl().contains(envVar.facebookURL) }
//            waitFor() { $('.shareMediaLink').attr('onmouseover').replace('\\', '').contains(envVar.epointsLink) }
//            assert browser.getCurrentUrl().contains(envVar.facebookURL)
//            assert $('.shareMediaLink').attr('onmouseover').replace('\\', '').contains(envVar.epointsLink)
//            // $('.attachmentText') it changes sometimes on facebook site
//        }
//    } else if (shareOption == 'twitter') {
//        waitFor { voucherPage.moreVouchersVoucherShareTwitterBasic.isDisplayed() }
//        browser.withNewWindow({
//            voucherPage.clickShareTwitterOptionOfOtherVoucherOnIndividualVoucherPage(0)
//        }, close: true, wait: true) {
//            waitFor { browser.getCurrentUrl().contains(envVar.twitterURL) }
//            waitFor { $('#status').text().contains(envVar.epointsLink) }
//            assert browser.getCurrentUrl().contains(envVar.twitterURL)
//            assert $('#status').text().contains(envVar.epointsLink)
//        }
//    } else if (shareOption == 'google') {
//        waitFor { voucherPage.moreVouchersVoucherShareGoogleBasic.isDisplayed() }
//        browser.withNewWindow({
//            voucherPage.clickShareGoogleOptionOfOtherVoucherOnIndividualVoucherPage(0)
//        }, close: true, wait: true) {
//            if ($('#Email').isDisplayed()) {
//                if ($('#signIn').isDisplayed()) {
//                    $('#Email').value(envVar.gmailTestEmail)
//                    $('#Passwd').value(envVar.gmailTestEmailPassword)
//                    $('#signIn').click()
//                } else if ($('#next').isDisplayed()) {
//                    $('#Email').value(envVar.gmailTestEmail)
//                    $('#next').click()
//                    waitFor { $('#signIn').isDisplayed() }
//                    $('#Passwd').value(envVar.gmailTestEmailPassword)
//                    $('#signIn').click()
//                }
//            }
//            waitFor { browser.getCurrentUrl().contains(envVar.googleURL) }
//            waitFor(20) { $('.d-s.ot-anchor').text().contains(envVar.epointsLink) }
//            assert browser.getCurrentUrl().contains(envVar.googleURL)
//            assert $('.d-s.ot-anchor').text().contains(envVar.epointsLink)
//        }
//    }
//}