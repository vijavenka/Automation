package com.iat.stepdefs.pointsSection

import com.iat.pages.JoinPage
import com.iat.pages.points.LeadRetailerPage
import com.iat.pages.points.TransitionPage
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.Then
import static cucumber.api.groovy.EN.When

def retailerLeadPage = new LeadRetailerPage()
def browser = new Browser()
def func = new Functions()
def transitionPage = new TransitionPage()
def offersNumber = 7
def offersWithMaxCommissionNumber = 4
def offersForNewUsersNumber = 5


Then(~/^Merchant (.+?) Lead's elements are properly displayed to (.+?) user$/) { String merchantName, String loginState ->
    at LeadRetailerPage
    retailerLeadPage = page
    waitFor { retailerLeadPage.termsAndConditionsContent.isDisplayed() }
    assert retailerLeadPage.name == merchantName
    assert retailerLeadPage.description.isDisplayed()
    assert retailerLeadPage.maxCommission.isDisplayed()
    assert retailerLeadPage.daysToConfirmPoints.isDisplayed()
    assert retailerLeadPage.earnPointsButton.text().contains(merchantName)
    assert retailerLeadPage.termsAndConditionsTitle.isDisplayed()
    assert retailerLeadPage.termsAndConditionsContent.isDisplayed()

    if (merchantName == "UI AUTOMATION LEAD MERCHANT I") {
        assert retailerLeadPage.readMoreHideDescriptionButton.isDisplayed()
        assert retailerLeadPage.readMoreHideTermsButton.isDisplayed()
        assert retailerLeadPage.checkOutOffersButton.isDisplayed()
        assert retailerLeadPage.offers.size() == offersNumber
    } else if (merchantName == "UI AUTOMATION LEAD MERCHANT II") {
        assert !retailerLeadPage.readMoreHideDescriptionButton.isDisplayed()
        assert !retailerLeadPage.readMoreHideTermsButton.isDisplayed()
        assert !retailerLeadPage.checkOutOffersButton.isDisplayed()
        assert retailerLeadPage.offers.size() == 0
    }

    if (loginState.equals("logged")) {
        assert !retailerLeadPage.epointsRecomendationBlock.isDisplayed()
        assert !retailerLeadPage.epointsRecomendationSignUpButton.isDisplayed()
        assert !retailerLeadPage.epointsDescriptionBlock.isDisplayed()
        assert !retailerLeadPage.epointsDescriptionSignUpButton.isDisplayed()
    } else if (loginState.equals("not logged")) {
        assert retailerLeadPage.epointsRecomendationBlock.isDisplayed()
        assert retailerLeadPage.epointsRecomendationSignUpButton.isDisplayed()
        assert retailerLeadPage.epointsDescriptionBlock.isDisplayed()
        assert retailerLeadPage.epointsDescriptionSignUpButton.isDisplayed()
    }
}

Then(~/^User can be moved to join page by clicking join button in (.+?) section$/) { String block ->
    at LeadRetailerPage
    retailerLeadPage = page
    if (block.equals("description")) {
        sleep(500)
        retailerLeadPage.epointsDescriptionSignUpButton.click()
    } else if (block == "recommendation") {
        func.scrolPageUpDown('down')
        sleep(500)
        retailerLeadPage.epointsRecomendationSignUpButton.click()
    }
    at JoinPage
}


Then(~/^On lead's merchant page user can see list of offers with all required elements\(commission, title, description, earn button\)$/) {
    ->
    at LeadRetailerPage
    retailerLeadPage = page
    waitFor { retailerLeadPage.offers.size() == offersNumber }
    sleep(2000)
    assert retailerLeadPage.offers.each { it.commissionValue.isDisplayed() }
    assert retailerLeadPage.offers.each { it.title.isDisplayed() }
    assert retailerLeadPage.offers.each { it.description.isDisplayed() }

    def counter = 0
    for (int i = 0; i < retailerLeadPage.offers.size(); i++) {
        if (retailerLeadPage.offers[i].maxCommissionBadge.isDisplayed())
            counter++
    }
    assert counter == offersWithMaxCommissionNumber

    counter = 0
    for (int i = 0; i < retailerLeadPage.offers.size(); i++) {
        if (retailerLeadPage.offers[i].forNewUsersOnly.isDisplayed())
            counter++
    }
    assert counter == offersForNewUsersNumber
}

def merchantMaxCommission
def offerMaxCommission
When(~/^Earn (.+?) button will be clicked by (.+?) user$/) { String earnButton, String loginState ->
    at LeadRetailerPage
    retailerLeadPage = page

    waitFor { retailerLeadPage.offers[0].commissionValue.isDisplayed() }

    merchantMaxCommission = retailerLeadPage.maxCommission.text().replace("+", "")
    offerMaxCommission = retailerLeadPage.offers[0].commissionValue.text().replace("up to", "").replace("+", "").trim()

    if (earnButton == "merchant")
        retailerLeadPage.earnPointsButton.click()
    else
        retailerLeadPage.offers[0].earnPointsButton.click()

    at TransitionPage
    transitionPage = page
}

Then(~/^He will be redirected to transition page or retailer page according to login state (.+?)$/) { String loginState ->
    at TransitionPage
    transitionPage = page

    assert (transitionPage.transitionPagePointsInfo.text().contains("Earn epoints up to " + merchantMaxCommission) || transitionPage.transitionPagePointsInfo.text().contains("Earn epoints up to " + offerMaxCommission))

    if (!loginState.equals("logged"))
        assert !transitionPage.transitionAnimation.isDisplayed()
    else {
        waitFor { !transitionPage.transitionAnimation.isDisplayed() }
        assert !browser.currentUrl.contains("/transition")
    }
}
