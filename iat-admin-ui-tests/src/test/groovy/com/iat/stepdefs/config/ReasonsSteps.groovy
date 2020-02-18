package com.iat.stepdefs.config

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.config.AddReasonPage
import com.iat.pages.config.ReasonsGlobalSettingsPage
import com.iat.pages.config.ReasonsPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions

import static cucumber.api.groovy.EN.*

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def reasonsPage = new ReasonsPage()
def addReasonPage = new AddReasonPage()
def helpFunctions = new HelpFunctions()
def reasonGlobalSettingsPage = new ReasonsGlobalSettingsPage()

def uniqueReasonName
String existingReasonName
def reasonName
String[] globalSettings = new String[4] //m->u max,/m->u min,/u->u max,/u->u min,

// Scenario Outline: Reasons - check content of create reason form
Given(~/^User with reasons management permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

Given(~/^Reasons page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToReasonsPageUsingHomePageLinks()
    at ReasonsPage
    reasonsPage = page
}

When(~/^Add reason page will be clicked$/) { ->
    reasonsPage.clickAddReasonButton()
}

Then(~/^Add reason form will be displayed$/) { ->
    at AddReasonPage
    addReasonPage = page
    assert addReasonPage.addReasonForm.isDisplayed()
}

Then(~/^Reason input is displayed$/) { ->
    waitFor { addReasonPage.reasonLabel.text() == 'Reason: *' }
    assert addReasonPage.reasonInputField.isDisplayed()
}

Then(~/^Manager to user limit setting is displayed$/) { ->
    assert addReasonPage.managerToUserLimitLabel.text() == 'Manager to user limit'
    assert addReasonPage.managerToUserGlobalCheckbox.isDisplayed()
    assert addReasonPage.managerToUserGlobalCheckbox.attr('aria-checked') == 'true'
    assert addReasonPage.managerToUserDefineCheckbox.isDisplayed()
}

Then(~/^User to user limit setting is displayed$/) { ->
    assert addReasonPage.userToUserLimitLabel.text() == 'User to user limit'
    assert addReasonPage.userToUserGlobalCheckbox.isDisplayed()
    assert addReasonPage.userToUserGlobalCheckbox.attr('aria-checked') == 'true'
    assert addReasonPage.userToUserDefineCheckbox.isDisplayed()
}

Then(~/^Cancel button is displayed$/) { ->
    assert addReasonPage.cancelButton.isDisplayed()
}

Then(~/^Disabled save button is displayed$/) { ->
    assert addReasonPage.saveButton.isDisplayed()
    assert addReasonPage.saveButton.attr('disabled') == 'true'
}

//Scenario Outline: Reasons - cancel button click on add reason page
Given(~/^Add reasons page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToReasonsPageUsingHomePageLinks()
    at ReasonsPage
    reasonsPage = page
    rememberGlobalValues(globalSettings)
    existingReasonName = reasonsPage.returnReasonTableLocator('reasonName', 0).text()
    reasonsPage.clickAddReasonButton()
    at AddReasonPage
    addReasonPage = page
}

def rememberGlobalValues(String[] globalSettings) {
    def reasonsPage = new ReasonsPage()
    reasonsPage = page
    globalSettings[0] = reasonsPage.globalSettingsManagerToUserMinValue.text().replace(",", "")
    globalSettings[1] = reasonsPage.globalSettingsManagerToUserMaxValue.text().replace(",", "")
    globalSettings[2] = reasonsPage.globalSettingsUserToUserMinValue.text().replace(",", "")
    globalSettings[3] = reasonsPage.globalSettingsUserToUserMaxValue.text().replace(",", "")
}

When(~/^User click cancel reason creation button$/) { ->
    addReasonPage.clickCancelButton()
    waitFor { (addReasonPage.topNavigation.alertInfo.text() == 'Cancelled') }
}

Then(~/^Reason page will be opened$/) { ->
    at ReasonsPage
}

// Scenario Outline: Reasons - create new reason
When(~/^User provide unique reason name$/) { ->
    uniqueReasonName = 'uniqueReasonName' + helpFunctions.returnCurrentEpochTime();
    addReasonPage.enterReasonName(uniqueReasonName)
}

When(~/^Click save new reason button$/) { ->
    addReasonPage.clickSaveButton()
}

Then(~/^New reason will be available on reason list$/) { ->
    //defaults sort order should be created date desc so new reason should be available on top of list
    waitFor { (reasonsPage.topNavigation.alertInfo.text() == 'Saved') }
    waitFor { reasonsPage.returnReasonTableLocator('reasonName', 0).text() == uniqueReasonName }
    assert reasonsPage.returnReasonTableLocator('reasonName', 0).text() == uniqueReasonName
}

Then(~/^New reason limits are same as global values$/) { ->
    assert reasonsPage.returnReasonTableLocator('managerToUserMin', 0).text().contains(reasonsPage.globalSettingsManagerToUserMinValue.text())
    assert reasonsPage.returnReasonTableLocator('managerToUserMax', 0).text().contains(reasonsPage.globalSettingsManagerToUserMaxValue.text())
    assert reasonsPage.returnReasonTableLocator('userToUserMin', 0).text().contains(reasonsPage.globalSettingsUserToUserMinValue.text())
    assert reasonsPage.returnReasonTableLocator('userToUserMax', 0).text().contains(reasonsPage.globalSettingsUserToUserMaxValue.text())
}

//Scenario Outline: Reasons - create new reason with not unique reason name
When(~/^User provide not unique reason name$/) { ->
    //assumption is that WIZARD name is used by backend automated tests and always reason wit that name will be available
    addReasonPage.enterReasonName(existingReasonName)
}

Then(~/^Alert about reason uniqueness requirement will be displayed$/) { ->
    waitFor { addReasonPage.alert.isDisplayed() }
    assert addReasonPage.alertSingleMessage.text() == "name: Reason name should be unique."
}

Then(~/^New reason will not be created$/) { ->
    //leave empty
}

//Scenario Outline: Reasons - check content of reasons list
Then(~/^Reasons table will be displayed$/) { ->
    waitFor { reasonsPage.reasonsListSection.isDisplayed() }
    assert reasonsPage.reasonsListSection.isDisplayed()
}

Then(~/^Reason table has proper columns '(.+?)'$/) { String columnsLabels ->
    def labels = columnsLabels.split(",")
    waitFor { reasonsPage.reasonsListLabelRowFirstBasic[0].text() == labels[0] }
    assert reasonsPage.reasonsListLabelRowFirstBasic[0].text() == labels[0]
    assert reasonsPage.reasonsListLabelRowFirstBasic[1].text() == labels[1]
    assert reasonsPage.reasonsListLabelRowFirstBasic[2].text() == labels[4]
    assert reasonsPage.reasonsListLabelRowFirstBasic[3].text() == labels[7]

    assert reasonsPage.reasonsListLabelRowSecondBasic[0].text() == labels[2]
    assert reasonsPage.reasonsListLabelRowSecondBasic[1].text() == labels[3]
    assert reasonsPage.reasonsListLabelRowSecondBasic[2].text() == labels[5]
    assert reasonsPage.reasonsListLabelRowSecondBasic[3].text() == labels[6]
}

//Scenario Outline: Reasons - delete reason, cancel button on confirmation popup
Given(~/^User click delete reason button$/) { ->
    //assumption order creatte date desc
    reasonName = reasonsPage.returnReasonTableLocator('reasonName', 0).text()
    reasonsPage.clickDeleteReasonButtonOfChosenReason(0)
}
When(~/^Cancel button will be clicked on confirmation popup$/) { ->
    waitFor { reasonsPage.deletePopup.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium)
    reasonsPage.clickCancelButtonOnDeleteReasonConfirmationPopup()
}

Then(~/^Popup will be closed$/) { ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    waitFor { !reasonsPage.deletePopup.isDisplayed() }
}

Then(~/^Reason will not be deleted$/) { ->
    assert reasonName == reasonsPage.returnReasonTableLocator('reasonName', 0).text()
}

//Scenario Outline: Reasons - delete created reason
When(~/^Delete button will be clicked on confirmation popup$/) { ->
    waitFor { reasonsPage.deletePopup.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium) //to ensure that popup appear animation is finished
    reasonsPage.clickDeleteButtonOnDeleteReasonConfirmationPopup()
    waitFor { (reasonsPage.topNavigation.alertInfo.text() == 'Reason has been removed') }
}

Then(~/^Reason will be deleted from reasons list$/) { ->
    boolean ifAvailableOnList = false
    for (int i = 0; i < reasonsPage.reasonsListDataRowBasic.size() && !ifAvailableOnList; i++)
        ifAvailableOnList = reasonsPage.returnReasonTableLocator('reasonName', i).text() == uniqueReasonName
    assert !ifAvailableOnList
}

//Scenario Outline: Reasons - global reason settings page content
When(~/^User click edit reason config button$/) { ->
    reasonsPage.clickEditConfigButton()
}

Then(~/^Reasons global settings page will be opened$/) { ->
    at ReasonsGlobalSettingsPage
    reasonGlobalSettingsPage = page
}

Then(~/^Global settings page has needed input fields '(.+?)'$/) { String inputLabels ->
    String[] labels = inputLabels.split(",")
    waitFor { reasonGlobalSettingsPage.mainModule.managerToUserMinInputLabel.text() == labels[0] }
    assert reasonGlobalSettingsPage.mainModule.managerToUserMinInputLabel.text() == labels[0]
    assert reasonGlobalSettingsPage.mainModule.managerToUserMinInput.isDisplayed()
    assert reasonGlobalSettingsPage.mainModule.managerToUserMaxInputLabel.text() == labels[1]
    assert reasonGlobalSettingsPage.mainModule.managerToUserMaxInput.isDisplayed()
    assert reasonGlobalSettingsPage.mainModule.userToUserMinInputLabel.text() == labels[2]
    assert reasonGlobalSettingsPage.mainModule.userToUserMinInput.isDisplayed()
    assert reasonGlobalSettingsPage.mainModule.userToUserMaxInputLabel.text() == labels[3]
    assert reasonGlobalSettingsPage.mainModule.userToUserMaxInput.isDisplayed()
}

Then(~/^Cancel button is displayed on reasons settings page$/) { ->
    assert reasonGlobalSettingsPage.mainModule.cancelButton.isDisplayed()
}

Then(~/^Save button is displayed on reasons settings page$/) { ->
    assert reasonGlobalSettingsPage.mainModule.saveButton.isDisplayed()
}

//Scenario Outline: Reasons - global reason settings page, cancel button click
When(~/^Cancel button will be clicked on reasons global config page$/) { ->
    at ReasonsGlobalSettingsPage
    reasonGlobalSettingsPage = page
    reasonGlobalSettingsPage.mainModule.clickCancelButton()
    waitFor { (reasonGlobalSettingsPage.topNavigation.alertInfo.text() == 'Global config changes cancelled') }
}

Then(~/^User will return to reasons page$/) { ->
    at ReasonsPage
    reasonsPage = page
}

//Scenario Outline: Reasons - global reason settings page, correct global setting save
When(~/^User provide new global settings values$/) { ->
    //random global settings creation
    globalSettings[0] = helpFunctions.returnRandomValue(100) + 50
    globalSettings[1] = helpFunctions.returnRandomValue(1000) + 250
    globalSettings[2] = helpFunctions.returnRandomValue(100) + 50
    globalSettings[3] = helpFunctions.returnRandomValue(1000) + 250

    at ReasonsGlobalSettingsPage
    reasonGlobalSettingsPage = page
    reasonGlobalSettingsPage.mainModule.fillReasonsGlobalSettingsForm(globalSettings[0], globalSettings[1], globalSettings[2], globalSettings[3])
}

When(~/^User click save button on reasons global config page$/) { ->
    reasonGlobalSettingsPage.mainModule.clickSaveButton()
}

And(~/^User click yes on global settings save confirmation popup$/) { ->
    waitFor { reasonGlobalSettingsPage.mainModule.saveConfirmationPopup.isDisplayed() }
    reasonGlobalSettingsPage.mainModule.clickYesButtonOnSaveConfirmationPopup()
}

Then(~/^New reasons global settings values will be visible in reasons config area$/) { ->
    waitFor { (reasonGlobalSettingsPage.topNavigation.alertInfo.text() == 'Saved') }
    waitFor { reasonsPage.globalSettingsManagerToUserMinValue.text().replace(",", "") == globalSettings[0] }
    assert reasonsPage.globalSettingsManagerToUserMinValue.text().replace(",", "") == globalSettings[0]
    assert reasonsPage.globalSettingsManagerToUserMaxValue.text().replace(",", "") == globalSettings[1]
    assert reasonsPage.globalSettingsUserToUserMinValue.text().replace(",", "") == globalSettings[2]
    assert reasonsPage.globalSettingsUserToUserMaxValue.text().replace(",", "") == globalSettings[3]
}

//Scenario Outline: Reasons - global reason settings page min values grater than max
When(~/^User provide new global settings values minimums greater than maximums$/) { ->
    at ReasonsGlobalSettingsPage
    reasonGlobalSettingsPage = page
    reasonGlobalSettingsPage.mainModule.fillReasonsGlobalSettingsForm("100", "99", "100", "99")
}

Then(~/^Alert that maximum cannot be lower than minimum will be displayed$/) { ->
    waitFor { reasonGlobalSettingsPage.mainModule.alert.isDisplayed() }
    assert reasonGlobalSettingsPage.mainModule.alertSingleMessage[0].text() == "Manager to user max: Maximum cannot be lower than minimum"
    assert reasonGlobalSettingsPage.mainModule.alertSingleMessage[1].text() == "User to user max: Maximum cannot be lower than minimum"
}

//Scenario Outline: Reasons - create new reason, define values input check
When(~/^User click define option for both manager to user and user to manager fields$/) { ->
    addReasonPage.clickManagerToUserDefineCheckbox()
    addReasonPage.clickUserToUserDefineCheckbox()

}
Then(~/^Inputs to provide minimum and maximum values will be displayed$/) { ->
    waitFor { addReasonPage.managerToUserMinInput.isDisplayed() }
    assert addReasonPage.managerToUserMinInput.isDisplayed()
    assert addReasonPage.managerToUserMaxInput.isDisplayed()
    assert addReasonPage.userToUserMinInput.isDisplayed()
    assert addReasonPage.userToUserMaxInput.isDisplayed()
}

//Scenario Outline: Reasons - create new reason with defined limits values
When(~/^And provide values which not exceeds global settings values$/) { ->
    addReasonPage.fillManagerToUserValues((Integer.parseInt(globalSettings[0].replace(",", "")) + 1), (Integer.parseInt(globalSettings[1].replace(",", "")) - 1))
    addReasonPage.fillUserToUserValues((Integer.parseInt(globalSettings[2].replace(",", "")) + 1), (Integer.parseInt(globalSettings[3].replace(",", "")) - 1))
}
Then(~/^New reason will be available on list with proper limits values$/) { ->
    waitFor { reasonsPage.returnReasonTableLocator('reasonName', 0).text() == uniqueReasonName }
    assert reasonsPage.returnReasonTableLocator('reasonName', 0).text() == uniqueReasonName
    assert reasonsPage.returnReasonTableLocator('managerToUserMin', 0).text().replace(",", "") == (Integer.parseInt(globalSettings[0]) + 1).toString()
    assert reasonsPage.returnReasonTableLocator('managerToUserMax', 0).text().replace(",", "") == (Integer.parseInt(globalSettings[1]) - 1).toString()
    assert reasonsPage.returnReasonTableLocator('userToUserMin', 0).text().replace(",", "") == (Integer.parseInt(globalSettings[2]) + 1).toString()
    assert reasonsPage.returnReasonTableLocator('userToUserMax', 0).text().replace(",", "") == (Integer.parseInt(globalSettings[3]) - 1).toString()
}

//Scenario Outline: Reasons - create new reason with defined limits values which exceeds global values
When(~/^And provide values which exceeds global settings values$/) { ->
    addReasonPage.fillManagerToUserValues((Integer.parseInt(globalSettings[0].replace(",", "")) - 1), (Integer.parseInt(globalSettings[1].replace(",", "")) + 1))
    addReasonPage.fillUserToUserValues((Integer.parseInt(globalSettings[2].replace(",", "")) - 1), (Integer.parseInt(globalSettings[3].replace(",", "")) + 1))
}
Then(~/^Alert that global reason values were exceeded$/) { ->
    waitFor { addReasonPage.alert.isDisplayed() }
    assert addReasonPage.alertSingleMessage[2].text() == "managerToUserMin: Min value must be greater or equal than global (" + globalSettings[0] + ")."
    assert addReasonPage.alertSingleMessage[3].text() == "managerToUserMax: Max value must be lower or equal than global (" + globalSettings[1] + ")."
    assert addReasonPage.alertSingleMessage[0].text() == "userToUserMin: Min value must be greater or equal than global (" + globalSettings[2] + ")."
    assert addReasonPage.alertSingleMessage[1].text() == "userToUserMax: Max value must be lower or equal than global (" + globalSettings[3] + ")."
}

//Scenario Outline: Reasons - create new reason with min values grater than max
When(~/^And provide new reason values minimums greater than maximums$/) { ->
    addReasonPage.fillManagerToUserValues((Integer.parseInt(globalSettings[0].replace(",", "")) + 2), (Integer.parseInt(globalSettings[0].replace(",", "")) + 1))
    addReasonPage.fillUserToUserValues((Integer.parseInt(globalSettings[2].replace(",", "")) + 2), (Integer.parseInt(globalSettings[2].replace(",", "")) + 1))
}

Then(~/^Alert that maximum cannot be lower than minimum will be displayed on add reason page$/) { ->
    waitFor { addReasonPage.alert.isDisplayed() }
    assert addReasonPage.alertSingleMessage[0].text() == "Manager to user limit: Maximum cannot be lower than minimum"
    assert addReasonPage.alertSingleMessage[1].text() == "User to user limit: Maximum cannot be lower than minimum"
}

//Scenario Outline: Reasons - possibility of deleting last reason
When(~/^User delete all reasons apart of last one$/) { ->
    waitFor { reasonsPage.reasonsListDataRowBasic[0].isDisplayed() }
    for (int i = 0; i < reasonsPage.reasonsListDataRowBasic.size() - 1; i++) {
        reasonsPage.clickDeleteReasonButtonOfChosenReason(i)
        waitFor { reasonsPage.deletePopup.isDisplayed() }
        helpFunctions.waitSomeTime(Config.waitShort)
        reasonsPage.clickDeleteButtonOnDeleteReasonConfirmationPopup()
        waitFor { !reasonsPage.deletePopup.isDisplayed() }
    }
    waitFor(10) { reasonsPage.reasonsListDataRowBasic.size() == 1 }
}

Then(~/^Delete reason button wil be hidden$/) { ->
    waitFor { !reasonsPage.returnReasonTableLocator('action', 0).isDisplayed() }
}

Then(~/^User will not be able to delete last reason$/) { ->

}