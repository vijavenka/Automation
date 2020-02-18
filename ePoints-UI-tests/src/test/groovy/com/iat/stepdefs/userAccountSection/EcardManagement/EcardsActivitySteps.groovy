package com.iat.stepdefs.userAccountSection.EcardManagement

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.userAccount.ecardsManagement.EcardsActivityPage
import com.iat.pages.userAccount.ecardsManagement.EcardsCreationPage

import static cucumber.api.groovy.EN.*

def homePage = new EpointsHomePage()
def ecardsCreationPage = new EcardsCreationPage()
def ecardsActivityPage = new EcardsActivityPage()

Given(~/^Company activity page is opened$/) { ->
    at EpointsHomePage
    homePage = page
    homePage.userDropDownMenuModule.clickOnEcardManagementOption()
    waitFor { at EcardsCreationPage }
    ecardsCreationPage = page
    ecardsCreationPage.ecardsNavigationModule.clickCompanyActivityNavigationOption()
    waitFor { at EcardsActivityPage }
    ecardsActivityPage = page
}

When(~/^He tries to access ecard activity area$/) { ->
    browser.go('/my-account/ecards/activity')
}

Then(~/^He cannot open ecards activity page$/) { ->
    assert !ecardsActivityPage.pageBaseLocator.isDisplayed()
}

Then(~/^All of the necessary ecard data is shown, including date,from,to,reason,ep$/) { ->
    for (int i = 0; i < ecardsActivityPage.singleEcardContainer.size(); i++) {
        assert ecardsActivityPage.ecardImageBasic[i].isDisplayed()
        assert ecardsActivityPage.ecardAddedDate[i].isDisplayed()
        assert ecardsActivityPage.ecardReasonBasic[i].isDisplayed()
        assert ecardsActivityPage.ecardFromToWhoBasic[i].isDisplayed()
    }
}

Then(~/^Ecard display limit is met - up to '(.*)' ecards$/) { int _listSize ->
    assert ecardsActivityPage.singleEcardContainer.size() <= _listSize
}

And(~/^The newest ecards are displayed on the top$/) { ->
    for (int i = 1; i < ecardsActivityPage.singleEcardContainer.size(); i++)
        ecardsActivityPage.checkIfTime1NotGreaterThanTime2(i - 1, i)
}

When(~/^He clicks on "X others" button on chosen ecard on company activity page$/) { ->
    ecardsActivityPage.openMoreRecipientsModalOfChosenEcard(0)
}

Then(~/^The scrollable pop-up with recipients list is displayed over company activity page$/) { ->
    waitFor { ecardsActivityPage.moreUsersModal.isDisplayed() }
    assert ecardsActivityPage.moreUsersModalContentSingleUser.size() == 4
    assert ecardsActivityPage.moreUsersModalCloseButton.isDisplayed()
    assert ecardsActivityPage.moreUsersModalXButton.isDisplayed()
}

Then(~/^User can close recipients list popup of chosen company activity ecard$/) { ->
    ecardsActivityPage.clickMoreRecipientsModalCloseButton()
    waitFor { !ecardsActivityPage.moreUsersModal.isDisplayed() }
}
