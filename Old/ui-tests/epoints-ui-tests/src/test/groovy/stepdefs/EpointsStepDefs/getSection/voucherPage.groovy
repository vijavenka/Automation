package stepdefs.EpointsStepDefs.getSection
import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 23.10.14
 * Time: 08:30
 * To change this template use File | Settings | File Templates.
 */

def epoints = new epointsPage()
def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()

def epointsLink = envVar.epointsLink
def random

Given(~/^Voucher page is opened$/) { ->
    epointsPage.url = epointsLink
    to epointsPage
    epoints = page
    epoints.clickGetButton()
    epoints.azPage.clickVoucherButton()

}

Given(~/^Voucher page is opened clear$/) { ->
    epointsPage.url = epointsLink
    to epointsPage
    epoints = page
    func.clearCookiesAndStorage()
    epoints.clickGetButton()
    epoints.azPage.clickVoucherButton()
}

    // 1.1 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // --------------------------------------------------------------------------------------- not logged, expiry filter
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    When(~/^User select chosen expiry filter '(.+?)'$/) {String filter ->
        waitFor(20){ epoints.voucherPage.voucherCard }
        epoints.voucherPage.expandExpiryFilter()
        if(filter == 'relevance'){
            waitFor{ epoints.voucherPage.clickChosenExpiryfilterOption(0).text() == envVar.voucherPageSortOptions[0] }
            assert epoints.voucherPage.clickChosenExpiryfilterOption(0).text() == envVar.voucherPageSortOptions[0]
            epoints.voucherPage.clickChosenExpiryfilterOption(0)
        }else if(filter == 'expirysoonest'){
            waitFor{ epoints.voucherPage.clickChosenExpiryfilterOption(1).text() == envVar.voucherPageSortOptions[1] }
            assert epoints.voucherPage.clickChosenExpiryfilterOption(1).text() == envVar.voucherPageSortOptions[1]
            epoints.voucherPage.clickChosenExpiryfilterOption(1)
        }else if(filter == 'expirylatest'){
            waitFor{ epoints.voucherPage.clickChosenExpiryfilterOption(2).text() == envVar.voucherPageSortOptions[2] }
            assert epoints.voucherPage.clickChosenExpiryfilterOption(2).text() == envVar.voucherPageSortOptions[2]
            epoints.voucherPage.clickChosenExpiryfilterOption(2)
        }
        Thread.sleep(2000)
        waitFor{ epoints.voucherPage.voucherExpiryDateBasic.isDisplayed() }
    }
    Then(~/^Voucher results will be displayed in chosen filter order '(.+?)'$/) {String filter ->
        long currentDate = func.convertDateToEpochFormat(epoints.voucherPage.voucherExpiryDateBasic.text().replace('expires on ',''), "dd/MM/yyy")
        long nextDate
        if(filter == 'expirysoonest'){
            for(int i=1; i<epoints.voucherPage.voucherExpiryDateBasic.size()-1; i++){
                assert currentDate <= (nextDate = func.convertDateToEpochFormat(epoints.voucherPage.voucherExpiryDateBasic[i].text().replace('expires on ',''), "dd/MM/yyy"))
                currentDate = nextDate
            }
        }else if(filter == 'expirylatest'){
            for(int i=1; i<epoints.voucherPage.voucherExpiryDateBasic.size()-1; i++){
                Thread.sleep(1000)
                assert currentDate >= (nextDate = func.convertDateToEpochFormat(epoints.voucherPage.voucherExpiryDateBasic[i].text().replace('expires on ',''), "dd/MM/yyy"))
                currentDate = nextDate
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
        waitFor{ epoints.voucherPage.departmentFacet.isDisplayed() }
        waitFor(10){ epoints.voucherPage.breadCrumbNumberOfElementsFound.isDisplayed() }
        waitFor{ epoints.voucherPage.departmentFacetHeader.text() == envVar.departmentFacetNameOnVouchers }
        assert epoints.voucherPage.departmentFacetHeader.text() == envVar.departmentFacetNameOnVouchers
        allVouchersNumber = epoints.voucherPage.breadCrumbNumberOfElementsFound.text().replace(' items found','').replace(': ','')
        random = func.returnRandomValue(epoints.voucherPage.departmentFacetDepartmentButtonBasic.size())
        facetName = epoints.voucherPage.departmentFacetDepartmentButtonBasic[random].text()
        initialFacetVouchersNumber = epoints.voucherPage.departmentFacetDepartmentButtonBasicCount[random].text()
        firstVoucherName = epoints.voucherPage.voucherNameBasic[0].text()
        epoints.voucherPage.selectChosenDepartment(random)
        waitFor{ epoints.voucherPage.categoryFacet.isDisplayed()  }
        waitFor{ epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
    }
    Then(~/^Department facet will disappear$/) { ->
        waitFor{ !epoints.voucherPage.departmentFacet.isDisplayed() }
        assert !epoints.voucherPage.departmentFacet.isDisplayed()
    }
    Then(~/^Vouchers will be properly filtered according to chosen department$/) { ->
        assert !(epoints.voucherPage.voucherNameBasic[0].text() == firstVoucherName)
        waitFor{ epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
        assert epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber)
        if(Integer.parseInt(initialFacetVouchersNumber)<=40){
            waitFor{ epoints.voucherPage.voucherCard.size() == Integer.parseInt(initialFacetVouchersNumber) }
            assert epoints.voucherPage.voucherCard.size() == Integer.parseInt(initialFacetVouchersNumber)
        }
    }
    Then(~/^Bradcrumb will show proper department name$/) { ->
        waitFor{ epoints.voucherPage.breadCrumbValues[1].text() == facetName }
        assert epoints.voucherPage.breadCrumbValues[1].text() == facetName
    }
    When(~/^User click 'all departments' button on breadcrumb to show initial results$/) { ->
        waitFor{ epoints.voucherPage.breadCrumbValues[0].isDisplayed() }
        waitFor{ epoints.voucherPage.breadCrumbValues[0].text() == 'All departments' }
        assert epoints.voucherPage.breadCrumbValues[0].isDisplayed()
        assert epoints.voucherPage.breadCrumbValues[0].text() == 'All departments'
        epoints.voucherPage.clickChosenBreadcrumbElement(0)
    }
    Then(~/^Department facet will appear$/) { ->
        waitFor{ epoints.voucherPage.departmentFacet.isDisplayed()  }
        assert epoints.voucherPage.departmentFacet.isDisplayed()
    }
    Then(~/^Results will be reset$/) { ->
        waitFor{ epoints.voucherPage.outOfElement.text().contains(allVouchersNumber) }
        waitFor{ epoints.voucherPage.voucherNameBasic[0].text() == firstVoucherName }
        assert epoints.voucherPage.voucherNameBasic[0].text() == firstVoucherName
        assert epoints.voucherPage.outOfElement.text().contains(allVouchersNumber)
        if(Integer.parseInt(allVouchersNumber)<=40){
            waitFor{ epoints.voucherPage.voucherCard.size() == Integer.parseInt(allVouchersNumber) }
            assert epoints.voucherPage.voucherCard.size() == Integer.parseInt(allVouchersNumber)
        }
    }

    // 1.3 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // -------------------------------------------------------------------- not logged, merchant filter and clear button
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    When(~/^User select some merchant on merchant filter$/) { ->
        waitFor{ epoints.voucherPage.merchantFacet.isDisplayed() }
        waitFor(10){ epoints.voucherPage.breadCrumbNumberOfElementsFound.isDisplayed() }
        waitFor{ epoints.voucherPage.merchantFacetHeader.text().contains(envVar.merchantFacetNameOnVouchers) }
        assert epoints.voucherPage.merchantFacetHeader.text().contains(envVar.merchantFacetNameOnVouchers)
        allVouchersNumber = epoints.voucherPage.breadCrumbNumberOfElementsFound.text().replace(' items found','').replace(': ','')
        random = func.returnRandomValue(epoints.voucherPage.merchantFacetMerchantButtonBasic.size())
        facetName = epoints.voucherPage.merchantFacetMerchantButtonBasic[random].text()
        initialFacetVouchersNumber = epoints.voucherPage.merchantFacetMerchantButtonBasicCount[random].text()
        firstVoucherName = epoints.voucherPage.voucherNameBasic[0].text()
        epoints.voucherPage.selectChosenMerchant(random)
        waitFor{ epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
    }
    Then(~/^Vouchers will be properly filtered according to chosen merchant$/) { ->
        assert !(epoints.voucherPage.voucherNameBasic[0].text() == firstVoucherName)
        waitFor{ epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
        assert epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber)
        if(Integer.parseInt(initialFacetVouchersNumber)<=40){
            waitFor{ epoints.voucherPage.voucherCard.size() == Integer.parseInt(initialFacetVouchersNumber) }
            assert epoints.voucherPage.voucherCard.size() == Integer.parseInt(initialFacetVouchersNumber)
        }
    }
    When(~/^Bradcrumb will show proper merchant name$/) { ->
        waitFor{ epoints.voucherPage.breadCrumbValues[1].text() == facetName }
        assert epoints.voucherPage.breadCrumbValues[1].text() == facetName
    }
    When(~/^User click 'clear' button on merchant facet$/) { ->
        epoints.voucherPage.clikClearButtonOnMerchantFacet()
    }

    // 1.4 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // -------------------------------------------------------------------- not logged, category filter and clear button
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    String chosenDepartmentName
    String chosenCategoryName
    String chosenMerchantName
    Given(~/^Some department is chosen$/) { ->
        waitFor(10){ epoints.voucherPage.departmentFacet.isDisplayed() }
        waitFor(10){ epoints.voucherPage.voucherCard.isDisplayed() }
        waitFor{ !epoints.voucherPage.categoryFacet.isDisplayed() }
        assert !epoints.voucherPage.categoryFacet.isDisplayed()
        allVouchersNumber = epoints.voucherPage.departmentFacetDepartmentButtonBasicCount[2].text()
        chosenDepartmentName = epoints.voucherPage.departmentFacetDepartmentButtonBasic[2].text()
        epoints.voucherPage.selectChosenDepartment(2);Thread.sleep(500)
        waitFor{ epoints.voucherPage.voucherCard.isDisplayed() }
    }
    When(~/^User select some category on category filter$/) { ->
        waitFor{ epoints.voucherPage.categoryFacet.isDisplayed()  }
        waitFor(10){ epoints.voucherPage.breadCrumbNumberOfElementsFound.isDisplayed() }
        waitFor{ epoints.voucherPage.categoryFacetHeader.text().contains(envVar.categoryFacetNameOnVouchers) }
        assert epoints.voucherPage.categoryFacetHeader.text().contains(envVar.categoryFacetNameOnVouchers)
        firstVoucherName = epoints.voucherPage.voucherNameBasic[0].text()
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>0) {
            //TODO has to be change when more voucher will be available on voucher page
            random = 2 //func.returnRandomValue(epoints.voucherPage.categoryFacetCategoryButtonBasic.size())
            facetName = epoints.voucherPage.categoryFacetCategoryButtonBasic[random].text()
            initialFacetVouchersNumber = epoints.voucherPage.categoryFacetCategoryButtonBasicCount[random].text()
            epoints.voucherPage.selectChosenCategory(random)
            waitFor { epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
            Thread.sleep(2000)
        }
    }
    Then(~/^Vouchers will be properly filtered according to chosen category$/) { ->
        //if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>0) {
            assert !(epoints.voucherPage.voucherNameBasic[0].text() == firstVoucherName)
            if (epoints.voucherPage.categoryFacetCategoryButtonBasic.size() > 0) {
                waitFor { epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber) }
                assert epoints.voucherPage.outOfElement.text().contains(initialFacetVouchersNumber)
                if (Integer.parseInt(initialFacetVouchersNumber) <= 40) {
                    waitFor(10) {
                        epoints.voucherPage.voucherCard.size() == Integer.parseInt(initialFacetVouchersNumber)
                    }
                    assert epoints.voucherPage.voucherCard.size() == Integer.parseInt(initialFacetVouchersNumber)
                }
            }
        //}
    }
    When(~/^Bradcrumb will show proper category name$/) { ->
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>0) {
            waitFor { epoints.voucherPage.breadCrumbValues[2].text() == facetName }
            assert epoints.voucherPage.breadCrumbValues[2].text() == facetName
        }
    }
    When(~/^User click 'clear' button on category facet$/) { ->
        epoints.voucherPage.clikClearButtonOnCategoryFacet()
    }

    // 1.5 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // ------------------------------------------------------------------------------ not logged, merchant filter search
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    String phrase
    When(~/^User use merchant search with some phrase$/) { ->
            waitFor { epoints.voucherPage.merchantFacetMerchantButtonBasic.isDisplayed() }
            phrase = epoints.voucherPage.merchantFacetMerchantButtonBasic[func.returnRandomValue(epoints.voucherPage.merchantFacetMerchantButtonBasic.size())].text()
            if (epoints.voucherPage.merchantFacetSearch.isDisplayed()) {
                epoints.voucherPage.useMerchantSearchWithPhrase(phrase)
                Thread.sleep(1000)
            }
    }
    Then(~/^Merchants on merchant facet should match search criteria$/) { ->
        if(epoints.voucherPage.merchantFacetSearch.isDisplayed()){
            for(int i=0; i<epoints.voucherPage.merchantFacetMerchantButtonBasic.size(); i++){
                assert epoints.voucherPage.merchantFacetMerchantButtonBasic.text().contains(phrase)
            }
        }
    }

    // 1.6 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // ------------------------------------------------------------------------------ not logged, category filter search
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    When(~/^User use category search with some phrase$/) { ->
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>0) {
            waitFor { epoints.voucherPage.categoryFacetCategoryButtonBasic.isDisplayed() }
            phrase = epoints.voucherPage.categoryFacetCategoryButtonBasic[func.returnRandomValue(epoints.voucherPage.categoryFacetCategoryButtonBasic.size())].text()
            if (epoints.voucherPage.categoryFacetSearch.isDisplayed()) {
                epoints.voucherPage.useCategorySearchWithPhrase(phrase)
                Thread.sleep(1000)
            }
        }
    }
    Then(~/^Categories on category facet should match search criteria$/) { ->
        if(epoints.voucherPage.categoryFacetSearch.isDisplayed()){
            for(int i=0; i<epoints.voucherPage.categoryFacetCategoryButtonBasic.size(); i++){
                assert epoints.voucherPage.categoryFacetCategoryButtonBasic.text().contains(phrase)
            }
        }
    }

    // 1.7 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // ------------------------------------------------------------------------------------ not logged, clear all button
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    Given(~/^Some merchant is chosen$/) { ->
        if(epoints.voucherPage.merchantFacetMerchantButtonBasic.size()>0){
            chosenMerchantName = epoints.voucherPage.merchantFacetMerchantButtonBasic[0].text()
            epoints.voucherPage.selectChosenMerchant(0);Thread.sleep(500)
        }
    }
    Given(~/^Some category is chosen$/) { ->
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>0){
            chosenCategoryName = epoints.voucherPage.categoryFacetCategoryButtonBasic[0].text()
            epoints.voucherPage.selectChosenCategory(0);Thread.sleep(500)
        }
    }
    Then(~/^Vouchers results are filtered, department filter is invisible and breadcrumb is in use$/) { ->
        firstVoucherName = epoints.voucherPage.voucherNameBasic[0].text()
        waitFor{ !epoints.voucherPage.departmentFacet.isDisplayed() }
        waitFor{ epoints.voucherPage.breadCrumbValues.size() >= 3 }
        assert !epoints.voucherPage.departmentFacet.isDisplayed()
        assert epoints.voucherPage.breadCrumbValues.size() >= 3
    }
    When(~/^User click clear all button$/) { ->
        epoints.voucherPage.clickClarAllButton()
    }
    Then(~/^Results will be set to initial position and no filter will be selected$/) { ->
        waitFor{ epoints.voucherPage.departmentFacet.isDisplayed() }
        waitFor{ !(firstVoucherName == epoints.voucherPage.voucherNameBasic[0].text()) }
        waitFor{ epoints.voucherPage.breadCrumbValues.size() == 1 }
        assert epoints.voucherPage.departmentFacet.isDisplayed()
        assert !(firstVoucherName == epoints.voucherPage.voucherNameBasic[0].text())
        assert epoints.voucherPage.breadCrumbValues.size() == 1
    }

    // 1.8 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // --------------------------------------------------------------------------------------------------- share options
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    When(~/^User click chosen share option '(.+?)' of chosen deal$/) {String shareOption ->
        waitFor(20){ epoints.voucherPage.voucherCard.isDisplayed() }
        if(shareOption == 'facebook'){
            waitFor{ epoints.voucherPage.voucherShareFacebookBasic[0].isDisplayed() };
            browser.withNewWindow({ epoints.voucherPage.clickShareFacebookOptionOfChosenVoucher(0) }, close:true){
                waitFor { $('#email').isDisplayed() }
                $('#email').value(envVar.testUserEmail)
                $('#pass').value(envVar.testUserPassword)
                $('#loginbutton').click()
                waitFor{ browser.getCurrentUrl().contains(envVar.facebookURL) }
                waitFor(){ $('.shareMediaLink').attr('onmouseover').replace('\\','').contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.facebookURL)
                assert $('.shareMediaLink').attr('onmouseover').replace('\\','').contains(envVar.epointsLink)
                // $('.attachmentText') it changes sometimes on facebook site
            }
        }else if(shareOption == 'twitter'){
            waitFor{ epoints.voucherPage.voucherShareTwitterBasic[0].isDisplayed() }
            browser.withNewWindow({  epoints.voucherPage.clickShareTwitterOptionOfChosenVoucher(0) }, close:true){
                waitFor{ browser.getCurrentUrl().contains(envVar.twitterURL) }
                waitFor{ $('#status').text().contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.twitterURL)
                assert $('#status').text().contains(envVar.epointsLink)
            }
        }else if(shareOption == 'google'){
            //TODO all sleeps have to be replaced
            waitFor{ epoints.voucherPage.voucherShareGoogleBasic[0].isDisplayed() }
            browser.withNewWindow({  epoints.voucherPage.clickShareGoogleOptionOfChosenVoucher(0) }, close:true){
                Thread.sleep(1000)
                if($('#Email').isDisplayed()) {
                    if ($('#signIn').isDisplayed()) {
                        $('#Email').value(envVar.testUserEmail)
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    } else if ($('#next').isDisplayed()) {
                        $('#Email').value(envVar.testUserEmail)
                        $('#next').click()
                        waitFor{ $('#signIn').isDisplayed() }
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    }
                }
                waitFor{ browser.getCurrentUrl().contains(envVar.googleURL) }
                waitFor(20){ $('.d-s.ot-anchor').text().contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.googleURL)
                assert $('.d-s.ot-anchor').text().contains(envVar.epointsLink)
            }
        }
    }
    Then(~/^Proper share modal window will be opened with filled link$/) { ->
        // done in step 'User click chosen share option 'facebook' of chosen deal'
    }

    // 1.9 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // ------------------------------------------------------------------ not logged, breadcrumb independent more than 3
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    When(~/^User select three categories$/) { ->
        waitFor{ epoints.voucherPage.categoryFacet.isDisplayed()  }
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>=3){
            epoints.voucherPage.selectChosenCategory(0); Thread.sleep(500)
            epoints.voucherPage.selectChosenCategory(1); Thread.sleep(500)
            epoints.voucherPage.selectChosenCategory(2); Thread.sleep(500)
        }
    }
    Then(~/^Breadcrumb shows three categories sentence$/) { ->
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>=3){
            waitFor{ epoints.voucherPage.breadCrumbValues[2].isDisplayed() }
            waitFor{ epoints.voucherPage.breadCrumbValues[2].text() == envVar.threeCategoriesSelectedBreadcrumbText }
            assert epoints.voucherPage.breadCrumbValues[2].text() == envVar.threeCategoriesSelectedBreadcrumbText
        }
    }
    When(~/^User select three merchants$/) { ->
        if(epoints.voucherPage.merchantFacetMerchantButtonBasic.size()>=3){
            epoints.voucherPage.selectChosenMerchant(0); Thread.sleep(500)
            epoints.voucherPage.selectChosenMerchant(1); Thread.sleep(500)
            epoints.voucherPage.selectChosenMerchant(2); Thread.sleep(500)
        }
    }
    Then(~/^Breadcrumb shows three merchants sentence$/) { ->
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>=3 && epoints.voucherPage.merchantFacetMerchantButtonBasic.size()>=3){
            waitFor{ epoints.voucherPage.breadCrumbValues[3].text() == envVar.threeMerchantsSelectedBreadcrumbText }
            assert epoints.voucherPage.breadCrumbValues[3].text() == envVar.threeMerchantsSelectedBreadcrumbText
        }else if(epoints.voucherPage.merchantFacetMerchantButtonBasic.size()>=3){
            waitFor{ epoints.voucherPage.breadCrumbValues[2].text() == envVar.threeMerchantsSelectedBreadcrumbText }
            assert epoints.voucherPage.breadCrumbValues[2].text() == envVar.threeMerchantsSelectedBreadcrumbText
        }
    }

    // 1.10 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // -------------------------------------------------------------------- not logged, breadcrumb all filters used once
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    When(~/^User look on breadcrumb$/) { ->
        //leave empty
    }
    Then(~/^He can see that all chosen filters are included in breadcrumb$/) { ->
        int waterfallVariable=2
        waitFor{ epoints.voucherPage.breadCrumbValues.isDisplayed() }
        assert epoints.voucherPage.breadCrumbValues[1].text() == chosenDepartmentName
            if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>=1){
                waitFor{ epoints.voucherPage.breadCrumbValues[waterfallVariable].text() == chosenCategoryName }
                assert epoints.voucherPage.breadCrumbValues[waterfallVariable].text() == chosenCategoryName
                waterfallVariable++
            }
                if(epoints.voucherPage.merchantFacetMerchantButtonBasic.size()>=1){
                    waitFor{ epoints.voucherPage.breadCrumbValues[waterfallVariable].text() == chosenMerchantName }
                    assert epoints.voucherPage.breadCrumbValues[waterfallVariable].text() == chosenMerchantName
                }
    }
    Then(~/^User can remove selected options using breadcrumb links$/) { ->
        if(epoints.voucherPage.categoryFacetCategoryButtonBasic.size()>=1 && epoints.voucherPage.merchantFacetMerchantButtonBasic.size()>=1){
            epoints.voucherPage.breadCrumbValues[2].click(); Thread.sleep(500)
            waitFor{ epoints.voucherPage.breadCrumbValues.last().text() == chosenCategoryName }
            assert epoints.voucherPage.breadCrumbValues.last().text() == chosenCategoryName
        }
        epoints.voucherPage.breadCrumbValues[1].click(); Thread.sleep(500)
        waitFor{ epoints.voucherPage.breadCrumbValues.last().text() == chosenDepartmentName }
        assert epoints.voucherPage.breadCrumbValues.last().text() == chosenDepartmentName
    }

    // 1.11 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // ----------------------------------------------------------------------------- not logged, get voucher code button
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    String voucherRetailerName
    When(~/^User click 'get voucher code' button of chosen voucher$/) { ->
        waitFor(10){ epoints.voucherPage.voucherCard.isDisplayed() }
        random = func.returnRandomValue(epoints.voucherPage.getVoucherCodeButtonBasic.size())
        waitFor{ !epoints.voucherPage.voucherCode[random].isDisplayed() }
        waitFor{ !epoints.voucherPage.openSiteButton[random].isDisplayed() }
        assert !epoints.voucherPage.voucherCode[random].isDisplayed()
        assert !epoints.voucherPage.openSiteButton[random].isDisplayed()
        voucherRetailerName = epoints.voucherPage.voucherMerchantNameBasic[random].text()
        browser.withNewWindow({  epoints.voucherPage.clickGetVoucherCodeButtonOfChosenVoucher(random) }, close:true){
            waitFor{ browser.getTitle().contains(envVar.transitionPageTag) }
            assert browser.getTitle().contains(envVar.transitionPageTag)
            waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
            assert epoints.transitionPage.transitionPageRetailerInformation.text() == voucherRetailerName
            assert epoints.transitionPage.informationModal.isDisplayed()
            assert epoints.transitionPage.informationModalNoButton.isDisplayed()
            assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
        }
    }
    Then(~/^New page with transition page will be opened$/) { ->
        //done in step 'User click 'get voucher code' button of chosen voucher'
    }
    Then(~/^Voucher code will be displayed on chosen voucher card$/) { ->
        waitFor{ epoints.voucherPage.voucherCode.isDisplayed() }
        assert epoints.voucherPage.voucherCode.isDisplayed()
        assert epoints.voucherPage.openSiteButton.isDisplayed()
        assert epoints.voucherPage.voucherCodeHelpText.text() == envVar.voucherCodeHelpText
    }

    // 1.12 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
    // --------------------------------------------------------------------- not logged, individual voucher page content
    // Update --------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    String voucherName
    String voucherExpiryDate
    String individualVoucherPageLink
    Given(~/^Chosen voucher elements are saved and individual voucher page is opened$/) { ->
        waitFor(10){ epoints.voucherPage.voucherCard.isDisplayed() }
        random = func.returnRandomValue(epoints.voucherPage.voucherCard.size())
        firstVoucherDescription = epoints.voucherPage.voucherDescriptionBasic[random].text()
        voucherRetailerName = epoints.voucherPage.voucherMerchantNameBasic[random].text()
        voucherName = epoints.voucherPage.voucherNameBasic[random].text()
        voucherExpiryDate = epoints.voucherPage.voucherExpiryDateBasic[random].text()
        //getting individual voucher page
        waitFor(10){ epoints.voucherPage.voucherShareGoogleBasic[random].isDisplayed() }
        browser.withNewWindow({  epoints.voucherPage.clickShareGoogleOptionOfChosenVoucher(random) }, close:true){
            Thread.sleep(1000)
            if($('#Email').isDisplayed()){
                if($('#signIn').isDisplayed()){
                    $('#Email').value(envVar.testUserEmail)
                    $('#Passwd').value(envVar.testUserPassword)
                    $('#signIn').click()
                }else if($('#next').isDisplayed()) {
                    $('#Email').value(envVar.testUserEmail)
                    $('#next').click()
                    waitFor{ $('#signIn').isDisplayed() }
                    $('#Passwd').value(envVar.testUserPassword)
                    $('#signIn').click()
                }
            }
            waitFor(20){ $('.d-s.ot-anchor').isDisplayed() }
            individualVoucherPageLink =  $('.d-s.ot-anchor').text()
        }
        browser.go(individualVoucherPageLink)
    }
    Then(~/^All voucher card elements on individual page are the same as on voucher page$/) { ->
        waitFor{ epoints.voucherPage.indVoucherCard.isDisplayed() }
        waitFor{ epoints.voucherPage.indVoucherMerchantNameBasic }
        //waitFor{ epoints.voucherPage.indVoucherNameBasic.isDisplayed() }
        waitFor{ epoints.voucherPage.indVoucherDescriptionBasic.isDisplayed() }
        waitFor{ epoints.voucherPage.indVoucherExpiryDateBasic.isDisplayed() }
        waitFor{ epoints.voucherPage.indGetVoucherCodeButtonBasic.isDisplayed() }
        waitFor{ epoints.voucherPage.indVoucherShareFacebookBasic.isDisplayed() }
        waitFor{ epoints.voucherPage.indVoucherShareTwitterBasic.isDisplayed() }
        waitFor{ epoints.voucherPage.indVoucherShareGoogleBasic.isDisplayed() }
        assert epoints.voucherPage.indVoucherMerchantNameBasic.text().contains(voucherRetailerName)
        assert epoints.voucherPage.indVoucherNameBasic.text() == voucherName
        assert epoints.voucherPage.indVoucherDescriptionBasic.text() == firstVoucherDescription
        assert epoints.voucherPage.indVoucherExpiryDateBasic.text() == voucherExpiryDate
        assert epoints.voucherPage.indGetVoucherCodeButtonBasic.isDisplayed()
        assert epoints.voucherPage.indVoucherShareFacebookBasic.isDisplayed()
        assert epoints.voucherPage.indVoucherShareTwitterBasic.isDisplayed()
        assert epoints.voucherPage.indVoucherShareGoogleBasic.isDisplayed()
    }

    // 1.13 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    // ---------------------------------------------------------------- ind voucher page, main - get voucher code button
    Given(~/^Individual voucher page is opened$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickGetButton()
        epoints.azPage.clickVoucherButton()
        waitFor{ epoints.voucherPage.voucherShareGoogleBasic.isDisplayed() }
        browser.withNewWindow({ epoints.voucherPage.clickShareGoogleOptionOfChosenVoucher(0) }, close:true){
            Thread.sleep(1000)
            if($('#Email').isDisplayed()){
                if($('#signIn').isDisplayed()){
                    $('#Email').value(envVar.testUserEmail)
                    $('#Passwd').value(envVar.testUserPassword)
                    $('#signIn').click()
                }else if($('#next').isDisplayed()) {
                    $('#Email').value(envVar.testUserEmail)
                    $('#next').click()
                    waitFor{ $('#signIn').isDisplayed() }
                    $('#Passwd').value(envVar.testUserPassword)
                    $('#signIn').click()
                }
            }
            waitFor(20){ $('.d-s.ot-anchor').isDisplayed() }
            individualVoucherPageLink =  $('.d-s.ot-anchor').text()
        }
        browser.go(individualVoucherPageLink)
    }
    When(~/^User click 'get voucher code' button of main voucher on individual voucher page$/) { ->
        waitFor{ epoints.voucherPage.indVoucherMerchantNameBasic.isDisplayed() }
        voucherRetailerName = epoints.voucherPage.indVoucherMerchantNameBasic.text()
        browser.withNewWindow({  epoints.voucherPage.clickGetVoucherCodeButtonOfMainVoucherOnIndividualVoucherPage() }, close:true){
            waitFor{ browser.getTitle().contains(envVar.transitionPageTag) }
            assert browser.getTitle().contains(envVar.transitionPageTag)
            waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
            assert epoints.transitionPage.transitionPageRetailerInformation.text() == voucherRetailerName.replace(':','')
            assert epoints.transitionPage.informationModal.isDisplayed()
            assert epoints.transitionPage.informationModalNoButton.isDisplayed()
            assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
        }
    }
    Then(~/^Voucher code will be displayed on main voucher card on individual voucher page$/) { ->
        waitFor{ epoints.voucherPage.indVoucherCode.isDisplayed() }
        assert epoints.voucherPage.indVoucherCode.isDisplayed()
    }
    Then(~/^Go to retailer button will be displayed on main voucher card on individual voucher page$/) { ->
        waitFor{ epoints.voucherPage.indGetVoucherCodeButtonBasic.isDisplayed() }
        assert epoints.voucherPage.indGetVoucherCodeButtonBasic.text() == envVar.goToretailerButtonText
    }

    // 1.14 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    // -------------------------------------------------------------------------- ind voucher page, main - share options
    When(~/^User click chosen share option '(.+?)' of main voucher on individual voucher page$/) { String shareOption->
        waitFor(20){ epoints.voucherPage.indVoucherCard.isDisplayed() }
        if(shareOption == 'facebook'){
            waitFor{ epoints.voucherPage.indVoucherShareFacebookBasic.isDisplayed() };
            browser.withNewWindow({ epoints.voucherPage.clickShareFacebookOptionOfMainVoucherOnIndividualVoucherPage() }, close:true){
                waitFor { $('#email').isDisplayed() }
                $('#email').value(envVar.testUserEmail)
                $('#pass').value(envVar.testUserPassword)
                $('#loginbutton').click()
                waitFor{ browser.getCurrentUrl().contains(envVar.facebookURL) }
                waitFor(){ $('.shareMediaLink').attr('onmouseover').replace('\\','').contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.facebookURL)
                assert $('.shareMediaLink').attr('onmouseover').replace('\\','').contains(envVar.epointsLink)
                // $('.attachmentText') it changes sometimes on facebook site
            }
        }else if(shareOption == 'twitter'){
            waitFor{ epoints.voucherPage.indVoucherShareTwitterBasic.isDisplayed() }
            browser.withNewWindow({  epoints.voucherPage.clickShareTwitterOptionOfMainVoucherOnIndividualVoucherPage() }, close:true){
                waitFor{ browser.getCurrentUrl().contains(envVar.twitterURL) }
                waitFor{ $('#status').text().contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.twitterURL)
                assert $('#status').text().contains(envVar.epointsLink)
            }
        }else if(shareOption == 'google'){
            waitFor{ epoints.voucherPage.indVoucherShareGoogleBasic.isDisplayed() }
            browser.withNewWindow({  epoints.voucherPage.clickShareGoogleOptionOfMainVoucherOnIndividualVoucherPage() }, close:true){
                Thread.sleep(1000)
                if($('#Email').isDisplayed()) {
                    if($('#signIn').isDisplayed()){
                        $('#Email').value(envVar.testUserEmail)
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    }else if($('#next').isDisplayed()) {
                        $('#Email').value(envVar.testUserEmail)
                        $('#next').click()
                        waitFor{ $('#signIn').isDisplayed() }
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    }
                }
                waitFor{ browser.getCurrentUrl().contains(envVar.googleURL) }
                waitFor(20){ $('.d-s.ot-anchor').text().contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.googleURL)
                assert $('.d-s.ot-anchor').text().contains(envVar.epointsLink)
            }
        }
    }

    // 1.15 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    // ------------------------------------------------------------------------ ind voucher page, more - proper retailer
    def retailerNameOfChosenVoucher
    boolean moreVoucherAvailable = false
    Given(~/^Individual voucher page is opened with more vouchers section$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickGetButton()
        epoints.azPage.clickVoucherButton()
        waitFor{ epoints.voucherPage.merchantFacetMerchantButtonBasicCount.isDisplayed() }
        for(int i=0;i<epoints.voucherPage.merchantFacetMerchantButtonBasicCount.size();i++){
            if(epoints.voucherPage.merchantFacetMerchantButtonBasicCount[i].text() != '1'){
                random = i
                moreVoucherAvailable = true
            }
        }
        if(moreVoucherAvailable) {
            retailerNameOfChosenVoucher = epoints.voucherPage.merchantFacetMerchantButtonBasic[random].text()
            epoints.voucherPage.selectChosenMerchant(random)
            Thread.sleep(500)
            waitFor{ epoints.voucherPage.voucherShareGoogleBasic }
            browser.withNewWindow({ epoints.voucherPage.clickShareGoogleOptionOfChosenVoucher(0) }, close: true) {
                Thread.sleep(1000)
                if($('#Email').isDisplayed()) {
                    if($('#signIn').isDisplayed()){
                        $('#Email').value(envVar.testUserEmail)
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    }else if($('#next').isDisplayed()) {
                        $('#Email').value(envVar.testUserEmail)
                        $('#next').click()
                        waitFor{ $('#signIn').isDisplayed() }
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    }
                }
                waitFor(20) { $('.d-s.ot-anchor').isDisplayed() }
                individualVoucherPageLink = $('.d-s.ot-anchor').text()
            }
            browser.go(individualVoucherPageLink)
        }
    }
    When(~/^User look on individual voucher page$/) { ->

    }
    Then(~/^He can see that additional vouchers belongs to proper retailer$/) { ->
        if(moreVoucherAvailable) {
            waitFor{ epoints.voucherPage.moreVouchersVoucherCard.isDisplayed() }
            for(int i=0;i<epoints.voucherPage.moreVouchersVoucherCard.size();i++){
                assert epoints.voucherPage.moreVouchersVoucherMerchantNameBasic.text() == retailerNameOfChosenVoucher
            }
        }
    }
    Then(~/^More voucher section header has proper name$/) { ->
        if(moreVoucherAvailable) {
            waitFor{ epoints.voucherPage.indVoucherMoreVouchersSectionHeader }
            assert epoints.voucherPage.indVoucherMoreVouchersSectionHeader.text().contains(retailerNameOfChosenVoucher)
        }
    }

    // 1.16 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    // ------------------------------------------------------------------------- ind voucher page, more - see all button
    When(~/^User click se all button in more vouchers section$/) { ->
        if(moreVoucherAvailable) {
            epoints.voucherPage.clickSeeAllButtonOnIndividualVoucherPage()
        }
    }
    Then(~/^He will be redirected to voucher page$/) { ->
        if(moreVoucherAvailable) {
            waitFor { browser.currentUrl.contains(envVar.epointsLink + envVar.voucherPageURL) }
            assert browser.currentUrl.contains(envVar.epointsLink + envVar.voucherPageURL)
        }
    }
    Then(~/^All displayed vouchers are belongs to same selected merchant$/) { ->
        if(moreVoucherAvailable) {
            Thread.sleep(1000)
            waitFor { epoints.voucherPage.voucherCard.isDisplayed() }
            for(int i=0; i < epoints.voucherPage.voucherCard.size(); i++) {
                assert epoints.voucherPage.voucherMerchantNameBasic[i].text() == retailerNameOfChosenVoucher
            }
        }
    }

    // 1.17 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    // ---------------------------------------------------------------- ind voucher page, more - get voucher code button
    When(~/^User click 'get voucher code' button of other voucher on individual voucher page$/) { ->
        waitFor{ epoints.voucherPage.moreVouchersVoucherMerchantNameBasic.isDisplayed() }
        voucherRetailerName = epoints.voucherPage.moreVouchersVoucherMerchantNameBasic[0].text()
        browser.withNewWindow({  epoints.voucherPage.clickGetVoucherCodeButtonOfOtherVoucherOnIndividualVoucherPage(0) }, close:true){
            waitFor{ browser.getTitle().contains(envVar.transitionPageTag) }
            assert browser.getTitle().contains(envVar.transitionPageTag)
            waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
            assert epoints.transitionPage.transitionPageRetailerInformation.text() == voucherRetailerName.replace(':','')
            assert epoints.transitionPage.informationModal.isDisplayed()
            assert epoints.transitionPage.informationModalNoButton.isDisplayed()
            assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
        }
    }
    Then(~/^Voucher code will be displayed on other voucher card on individual voucher page$/) { ->
        waitFor{ epoints.voucherPage.moreVouchersVoucherCode.isDisplayed() }
        assert epoints.voucherPage.moreVouchersVoucherCode.isDisplayed()
    }
    Then(~/^Go to retailer button will be displayed on other voucher card on individual voucher page$/) { ->
        waitFor{ epoints.voucherPage.moreVouchersGoToRetailerButtonBasic.isDisplayed() }
        assert epoints.voucherPage.moreVouchersGoToRetailerButtonBasic.text() == envVar.openSiteButtonText
    }

    // 1.18 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
    // ----------------------------------------------------------------------------- ind voucher page, more - share options
    When(~/^User click chosen share option '(.+?)' of other voucher on individual voucher page$/) { String shareOption->
        waitFor(20){ epoints.voucherPage.moreVouchersVoucherCard.isDisplayed() }
        if(shareOption == 'facebook'){
            waitFor{ epoints.voucherPage.moreVouchersVoucherShareFacebookBasic.isDisplayed() };
            browser.withNewWindow({ epoints.voucherPage.clickShareFacebookOptionOfOtherVoucherOnIndividualVoucherPage(0) }, close:true){
                waitFor { $('#email').isDisplayed() }
                $('#email').value(envVar.testUserEmail)
                $('#pass').value(envVar.testUserPassword)
                $('#loginbutton').click()
                waitFor{ browser.getCurrentUrl().contains(envVar.facebookURL) }
                waitFor(){ $('.shareMediaLink').attr('onmouseover').replace('\\','').contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.facebookURL)
                assert $('.shareMediaLink').attr('onmouseover').replace('\\','').contains(envVar.epointsLink)
                // $('.attachmentText') it changes sometimes on facebook site
            }
        }else if(shareOption == 'twitter'){
            waitFor{ epoints.voucherPage.moreVouchersVoucherShareTwitterBasic.isDisplayed() }
            browser.withNewWindow({  epoints.voucherPage.clickShareTwitterOptionOfOtherVoucherOnIndividualVoucherPage(0) }, close:true){
                waitFor{ browser.getCurrentUrl().contains(envVar.twitterURL) }
                waitFor{ $('#status').text().contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.twitterURL)
                assert $('#status').text().contains(envVar.epointsLink)
            }
        }else if(shareOption == 'google'){
            waitFor{ epoints.voucherPage.moreVouchersVoucherShareGoogleBasic.isDisplayed() }
            browser.withNewWindow({  epoints.voucherPage.clickShareGoogleOptionOfOtherVoucherOnIndividualVoucherPage(0) }, close:true){
                if($('#Email').isDisplayed()) {
                    if ($('#signIn').isDisplayed()) {
                        $('#Email').value(envVar.testUserEmail)
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    } else if ($('#next').isDisplayed()) {
                        $('#Email').value(envVar.testUserEmail)
                        $('#next').click()
                        waitFor{ $('#signIn').isDisplayed() }
                        $('#Passwd').value(envVar.testUserPassword)
                        $('#signIn').click()
                    }
                }
                waitFor{ browser.getCurrentUrl().contains(envVar.googleURL) }
                waitFor(20){ $('.d-s.ot-anchor').text().contains(envVar.epointsLink) }
                assert browser.getCurrentUrl().contains(envVar.googleURL)
                assert $('.d-s.ot-anchor').text().contains(envVar.epointsLink)
            }
        }
    }