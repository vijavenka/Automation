package com.iat.stepdefs.pointsAllocationManagement

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.pointsAllocationManagement.GrantPartnersPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions

import static cucumber.api.groovy.EN.*

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def grantPartnersPage = new GrantPartnersPage()
def helpFunctions = new HelpFunctions()

int allocatedPointsNumber = 1000
String searchedPhrase
def partnerNodesNumber
int partnerToBeAwarded = 0

//cenario Outline: Grant partners - grant partner page content
Given(~/^User with points partner allocation permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.uberAdminLogin, Config.uberAdminPassword)
}

When(~/^Recognise partner page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToGrantPartnersPageUsingHomePageLinks()
    at GrantPartnersPage
    grantPartnersPage = page
    waitFor { !grantPartnersPage.loader.isDisplayed() }
    waitFor { grantPartnersPage.entitisTree.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitShort)
}

Then(~/^The whole partners tree is displayed$/) { ->
    waitFor { grantPartnersPage.entitisTree.isDisplayed() }
    assert grantPartnersPage.entitisTree.isDisplayed()
}

Then(~/^Partners search input field is available$/) { ->
    assert grantPartnersPage.partnetrSearchInputField.isDisplayed()
}

Then(~/^Partner allocation history button is available$/) { ->
    assert grantPartnersPage.historyButton.isDisplayed()
}

Then(~/^Partner allocation reason input is available$/) { ->
    assert grantPartnersPage.reasonMessageInputField.isDisplayed()
    assert grantPartnersPage.reasonMessageInputLabel.text() == 'Here you can add a reason for this allocation'
}

Then(~/^Partner points Allocation summary is available$/) { ->
    waitFor { grantPartnersPage.totalPointsToAlocate.isDisplayed() }
    assert grantPartnersPage.totalPointsToAlocate.isDisplayed()
}

Then(~/^Partner pounds allocation summary is available$/) { ->
    grantPartnersPage.totalPoundsToAlocate.isDisplayed()
}

Then(~/^He is able to browse through all partners in the tree$/) { ->
    //TODO
}

//Scenario Outline: Grant partners - partners points allocation, total summary
When(~/^User clicks allocate next to a chosen partner$/) { ->
    grantPartnersPage.clickChosenNodeAllocateButton(partnerToBeAwarded)
}

When(~/^Chooses 1 or more points for partner allocation$/) { ->
    allocatedPointsNumber = helpFunctions.returnRandomValue(10) * 200
    grantPartnersPage.enterPointsValueInChosenNode(partnerToBeAwarded, allocatedPointsNumber)
}

Then(~/^The number total points to allocate partner is increased$/) { ->
    waitFor {
        grantPartnersPage.totalPointsToAlocate.text().replace(',', '').contains(allocatedPointsNumber.toString())
    }
    assert grantPartnersPage.totalPointsToAlocate.text().replace(',', '').contains(allocatedPointsNumber.toString())
}

Then(~/^The total value is also displayed as a pound value$/) { ->
    assert grantPartnersPage.totalPoundsToAlocate.text().contains((allocatedPointsNumber / 200).toString())
}

Then(~/^Proper points value appears next to a partner$/) { ->
    assert grantPartnersPage.entityNodePointsValueInputBasic[partnerToBeAwarded].value().contains(allocatedPointsNumber.toString())
}

//Scenario Outline: Grant partners - partners points allocation, total summary when cancel button clicked
Given(~/^There are some points added to chosen partner$/) { ->
    grantPartnersPage.clickChosenNodeAllocateButton(partnerToBeAwarded)
    allocatedPointsNumber = helpFunctions.returnRandomValue(10) * 200
    grantPartnersPage.enterPointsValueInChosenNode(partnerToBeAwarded, allocatedPointsNumber)
    waitFor {
        grantPartnersPage.totalPointsToAlocate.text().replace(',', '').contains(allocatedPointsNumber.toString())
    }
}

When(~/^User clicks cancel next to partner points$/) { ->
    grantPartnersPage.clickChosenNodeCancelButton(partnerToBeAwarded)
}

Then(~/^The number total points to partner allocate is decreased$/) { ->
    waitFor { grantPartnersPage.totalPointsToAlocate.text().contains("0") }
}

Then(~/^Allocate button appears next to chosen partner$/) { ->
    assert grantPartnersPage.entityNodeAllocateButtonBasic[partnerToBeAwarded].isDisplayed()
}

//Scenario Outline: Grant partners - partners points allocation, allocated points not exceeds available
Given(~/^Message field is filled properly for partner allocation$/) { ->
    grantPartnersPage.enterPointsAllocationReason("Allocation reason automated test partner")
    helpFunctions.scrolPageUpDown("up") //fix for lower resolutions
}

When(~/^User chose to allocate proper number of points to partner$/) { ->
    grantPartnersPage.clickChosenNodeAllocateButton(partnerToBeAwarded)
    grantPartnersPage.enterPointsValueInChosenNode(partnerToBeAwarded, allocatedPointsNumber)
    waitFor {
        grantPartnersPage.totalPointsToAlocate.text().replace(',', '').contains(allocatedPointsNumber.toString())
    }
}

When(~/^User will click save button on partner allocation page$/) { ->
    grantPartnersPage.clickSaveButton()
}

Then(~/^Partner allocation confirm dialog box is displayed$/) { ->
    waitFor { grantPartnersPage.confirmationPopup.isDisplayed() }
}

Then(~/^He can either confirm or cancel the partner allocation$/) { ->
    assert grantPartnersPage.confirmationPopupInfo.text() == 'Are you sure you want to award ' + allocatedPointsNumber.toString() + ' points to this partner?'
    assert grantPartnersPage.confirmationPopupConfirmButton.isDisplayed()
    assert grantPartnersPage.confirmationPopupCancelButton.isDisplayed()
    grantPartnersPage.clickCancelButtonOnAllocationConfirmationPopup()
}

//Scenario Outline: Grant partners - partners points allocation, confirmation popup cancel button
When(~/^He clicks cancel button on partner points allocation confirmation popup$/) { ->
    grantPartnersPage.clickCancelButtonOnAllocationConfirmationPopup()
}

Then(~/^No points are allocated to partner$/) { ->
    assert !grantPartnersPage.topNavigation.alertInfo.isDisplayed()
}

//Scenario Outline: Grant partners - partners points allocation, confirmation popup save button
When(~/^He clicks confirm button on partner points allocation confirmation popup$/) { ->
    grantPartnersPage.clickConfirmButtonOnAllocationConfirmationPopup()
}

Then(~/^Virtual points are granted to proper partners$/) { ->

}

Then(~/^Partner allocation confirmation info is displayed$/) { ->
    waitFor { grantPartnersPage.topNavigation.alertInfo.isDisplayed() }
    assert grantPartnersPage.topNavigation.alertInfo.text() == 'Points have been awarded'
    assert !grantPartnersPage.entityNodeCancelButtonBasic[partnerToBeAwarded].isDisplayed()
    assert grantPartnersPage.totalPointsToAlocate.text().contains("0")
}

//Scenario Outline: Grant partners - partner search
When(~/^He types phrase into partner search input field$/) { ->
    searchedPhrase = grantPartnersPage.entityNodeNameBasic[partnerToBeAwarded].text().substring(0, 3)
    partnerNodesNumber = grantPartnersPage.entityNodeBasic.size()
    grantPartnersPage.enterSearchPhraseIntoSearchInputField(searchedPhrase)
    helpFunctions.waitSomeTime(Config.waitMedium)
}

Then(~/^Only connected partners are displayed$/) { ->
    waitFor { grantPartnersPage.entityNodeBasic.isDisplayed() }
    for (int i = 0; i < grantPartnersPage.entityNodeBasic.size(); i++) {
        if (grantPartnersPage.entityNodeNameBasic[i].isDisplayed())
            assert grantPartnersPage.entityNodeNameBasic[i].text().toLowerCase().contains(searchedPhrase.toLowerCase())
    }
}