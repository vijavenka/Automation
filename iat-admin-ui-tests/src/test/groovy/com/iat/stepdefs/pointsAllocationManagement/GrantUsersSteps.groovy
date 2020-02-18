package com.iat.stepdefs.pointsAllocationManagement

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.pointsAllocationManagement.GrantUsersPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions
import org.openqa.selenium.Keys

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.is

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def grantUsersPage = new GrantUsersPage()
def helpFunctions = new HelpFunctions()

def ccEmail
def message
def reason
def awardedUsersNumber
def reasonManagerToUserMin = Config.reasonManagerToUserMin
def reasonManagerToUserMax = Config.reasonManagerToUserMax
def userToBeAwardedEmail
def userToBeAwardedName
def userToBeAwardedDepartment
int availablePointsForAllocation = Config.availablePointsNumber
String existingAssociatedUser = 'Kris Baranowski'

def imageToBeSelected = 0
def reasonToBeSelected = 1
def userToBeSelectedDepartmentNode = 0

//Grant users - grant users step 1 page content
Given(~/^User with points users award permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    helpFunctions.clearCookiesAndLocalStorage()
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

When(~/^Recognise users page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToGrantUsersPageUsingHomePageLinks()
    at GrantUsersPage
    grantUsersPage = page
    waitFor { grantUsersPage.stepNavigatorSelectCardDetails.hasClass('current') }
}

Then(~/^Select reason ddl is available$/) { ->
    waitFor { grantUsersPage.selectReasonDDL.isDisplayed() }
    assert grantUsersPage.selectReasonLabel.text().contains('Select reason')
    assert grantUsersPage.selectReasonLabel.text().contains('*')
}

Then(~/^Message input field is available$/) { ->
    waitFor { grantUsersPage.messageInputField.isDisplayed() }
    assert grantUsersPage.messageLabel.text().contains('Message')
    assert grantUsersPage.messageLabel.text().contains('*')
}

Then(~/^Cc input field is available$/) { ->
    waitFor { grantUsersPage.ccInputField.isDisplayed() }
    assert grantUsersPage.ccLabel.text() == 'CC'
}

Then(~/^Image preview is available$/) { ->
    waitFor { grantUsersPage.imagePreviewBigImage.isDisplayed() }
    assert grantUsersPage.imagePreviewLabel.text() == 'Image preview'
    assert grantUsersPage.imagePreviewImageName.isDisplayed()
}

Then(~/^Images to select are available$/) { ->
    waitFor { grantUsersPage.selectImageTemplateBasic[imageToBeSelected].isDisplayed() }
    assert grantUsersPage.selectImageLabel.text() == 'Select image'
}

Then(~/^Next button is displayed$/) { ->
    waitFor { grantUsersPage.nextButtonStep1.isDisplayed() }
    assert grantUsersPage.nextButtonStep1.attr('disabled') == 'true'
}

//Scenario Outline: Grant users - cc list adding new email
When(~/^User types email address in cc field$/) { ->
    ccEmail = 'ccEmailRandom_' + helpFunctions.returnCurrentEpochTime() + "@gmail.com"
    grantUsersPage.enterCC(ccEmail)
}

Then(~/^It's added to the CC list$/) { ->
    assert grantUsersPage.ccElementBasic[0].text().contains(ccEmail)
}

//Scenario Outline: Grant users - cc email removing
When(~/^Manager decides to remove an email$/) { ->
    grantUsersPage.clickCcElementBasicCloseCross(0)
}


Then(~/^It disappears from cc field$/) { ->
    assert !grantUsersPage.ccElementBasic[0].isDisplayed()
}

//Scenario Outline: Grant users - new image selection
Given(~/^One of the images is selected$/) { ->
    grantUsersPage.selectImage(imageToBeSelected)
}

When(~/^Manager decides to select another image$/) { ->
    grantUsersPage.selectImage(1)
}

Then(~/^The new image is highlighted$/) { ->
    waitFor { grantUsersPage.selectImageTemplateBasic[1].hasClass('selected') }
    assert grantUsersPage.selectImageTemplateBasic[1].hasClass('selected')
}

Then(~/^The previous image is unselected$/) { ->
    assert !grantUsersPage.selectImageTemplateBasic[imageToBeSelected].hasClass('selected')
}

//Scenario Outline: Grant users - first step finished next button
When(~/^User chooses the reason from the drop-down$/) { ->
    grantUsersPage.expandReasonDDL()
    reason = grantUsersPage.selectReasonDDLOption[reasonToBeSelected].text()
    grantUsersPage.selectAwardReasonDDLOption(reasonToBeSelected)
}

When(~/^User adds message$/) { ->
    message = 'randomMessage_' + helpFunctions.returnCurrentEpochTime()
    grantUsersPage.enterMessage(message)
}

When(~/^User clicks next button on first step$/) { ->
    grantUsersPage.clickNextButtonOnStepOne()
}

Then(~/^He is re-directed to the select users screen$/) { ->
    waitFor { grantUsersPage.stepNavigatorSelectUsers.hasClass('current') }
    assert grantUsersPage.stepNavigatorSelectUsers.hasClass('current')
}

//Scenario Outline: Grant users - grant users step 2 page content
When(~/^Second users award step is opened$/) { ->
    grantUsersPage.expandReasonDDL()
    waitFor { grantUsersPage.selectReasonDDLOption[reasonToBeSelected].isDisplayed() }
    reason = grantUsersPage.selectReasonDDLOption[reasonToBeSelected].text()
    grantUsersPage.selectAwardReasonDDLOption(reasonToBeSelected)
    message = 'randomMessage_' + helpFunctions.returnCurrentEpochTime()
    grantUsersPage.enterMessage(message)
    ccEmail = 'ccEmailRandom_' + helpFunctions.returnCurrentEpochTime() + "@pl.com"
    grantUsersPage.enterCC(ccEmail)
    grantUsersPage.selectImage(imageToBeSelected)
    grantUsersPage.clickNextButtonOnStepOne()
    waitFor { grantUsersPage.stepNavigatorSelectUsers.hasClass('current') }
}

Then(~/^Available points for award info is displayed$/) { ->
    assert grantUsersPage.availablePointsToAlocate.isDisplayed()
    assert grantUsersPage.availablePointsToAlocate.text().replace(',', '').contains(availablePointsForAllocation.toString())
}

Then(~/^Points per user input field is available$/) { ->
    assert grantUsersPage.pointsPerUserInputField.isDisplayed()
    assert grantUsersPage.pointsPerUserLabel.text() == 'Points per user'
}

Then(~/^Global search for user is available$/) { ->
    assertThat("Global users search is not displayed", grantUsersPage.search.isDisplayed())
    assertThat("Global users search label is not correct", grantUsersPage.search.isDisplayed())
}

Then(~/^Select users tree is available$/) { ->
    assert grantUsersPage.selectUsersNode[userToBeSelectedDepartmentNode].isDisplayed()
    assert grantUsersPage.selectUsersLabel.text() == 'Browse for user'
}

Then(~/^User award summary is displayed$/) { ->
    assert grantUsersPage.summaryInformation.isDisplayed()
}

Then(~/^Next button is displayed on second step$/) { ->
    assert grantUsersPage.nextButtonStep2.isDisplayed()
    assert grantUsersPage.nextButtonStep2.attr('disabled') == 'true'
}

Then(~/^Back button is displayed on second step$/) { ->
    assert grantUsersPage.backButtonStep2.isDisplayed()
}

//Scenario Outline: Grant users - grant users step 2 users tree all users from chosen node selected
Given(~/^Users tree is opened$/) { ->
    //TODO it is opened from begin
}

When(~/^Users clicks All option next to chosen group$/) { ->
    //second department selected as in first are some broken data
    awardedUsersNumber = grantUsersPage.selectUsersNodeUsersNumber[1].text().substring(0, grantUsersPage.selectUsersNodeUsersNumber[0].text().lastIndexOf(' users'))
    grantUsersPage.clickAllUsersInChosenNode(1)
}

Then(~/^All of the users from the group appear selected$/) { ->
    //second department selected as in first are some broken data
    waitFor { grantUsersPage.selectUsersNodeUsersNumberSelected[1].isDisplayed() }
    assert grantUsersPage.selectUsersNodeUsersNumberSelected[1].text() == awardedUsersNumber + "selected"
}

//Scenario Outline: Grant users - grant users step 2 points value < than reason global minimum
When(~/^Manager types points value smaller than reason's minimum to points per user field$/) { ->
    grantUsersPage.enterPointsValue(reasonManagerToUserMin - 1)
}

Then(~/^He is informed that he exceed minimum reason limit$/) { ->
    waitFor { grantUsersPage.alert.isDisplayed() }
    assert grantUsersPage.alert.text() == "You didn't reach min reason value (" + reasonManagerToUserMin.toString() + ")"
}

//Scenario Outline: Grant users - grant users step 2 points value > than reason global maximum
When(~/^Manager types points value bigger than reason's maximum to points per user field$/) { ->
    grantUsersPage.enterPointsValue(reasonManagerToUserMax + 1)
}

Then(~/^He is informed that he exceed maximum reason limit$/) { ->
    waitFor { grantUsersPage.alert.isDisplayed() }
    assert grantUsersPage.alert.text() == "You exceeded maximum reason limit (" + reasonManagerToUserMax.toString() + ")"
}

//Scenario Outline: Grant users - grant users step 2 predictive input search
When(~/^User decides to choose users from one group$/) { ->
    grantUsersPage.clickChooseInChosenNode(userToBeSelectedDepartmentNode)
}

When(~/^Types part of a first last name or email$/) { ->
    grantUsersPage.enterEmailIntoChoosenNodeSearch(existingAssociatedUser)
    userToBeAwardedEmail = grantUsersPage.selectUserChooseDDLOptionEmail[userToBeSelectedDepartmentNode].text()
}

When(~/^He confirms$/) { ->
    grantUsersPage.selectUsersChooseDDLInput[userToBeSelectedDepartmentNode] << Keys.chord(Keys.ENTER)
}

Then(~/^Chosen user appears selected$/) { ->
    waitFor { grantUsersPage.selectUserChooseDDLOptionSelected[userToBeSelectedDepartmentNode].isDisplayed() }
    assert grantUsersPage.selectUserChooseDDLOptionSelected[userToBeSelectedDepartmentNode].text().replace(' ', '').replace('\n', '').replace('(', '').replace(')', '').contains(userToBeAwardedEmail.replace(' ', '').replace('\n', '').replace('(', '').replace(')', ''))
}

//Scenario Outline: Grant users - grant users step 2 predictive input search removing user
Given(~/^Some users are already chosen$/) { ->
    grantUsersPage.clickChooseInChosenNode(userToBeSelectedDepartmentNode)
    grantUsersPage.enterEmailIntoChoosenNodeSearch(existingAssociatedUser)
    waitFor { grantUsersPage.selectUserChooseDDLOptionEmail[userToBeSelectedDepartmentNode].isDisplayed() }
    userToBeAwardedEmail = grantUsersPage.selectUserChooseDDLOptionEmail[userToBeSelectedDepartmentNode].text()
    grantUsersPage.selectUserFromChosenNodeDDL(userToBeSelectedDepartmentNode)
    waitFor { grantUsersPage.selectUserChooseDDLOptionSelected[userToBeSelectedDepartmentNode].isDisplayed() }
}

When(~/^He decides to remove selected user$/) { ->
    grantUsersPage.clickSelectUserChooseDDLOptionSelectedCrossButton(userToBeSelectedDepartmentNode)
}

Then(~/^Chosen users disappear from the selection$/) { ->
    waitFor { !grantUsersPage.selectUserChooseDDLOptionSelected[userToBeSelectedDepartmentNode].isDisplayed() }
    assert !grantUsersPage.selectUserChooseDDLOptionSelected[userToBeSelectedDepartmentNode].isDisplayed()
}

//Scenario Outline: Grant users - grant users step 2 not enough points for allocation
When(~/^He decides to allocate greater number of points that is available$/) { ->
    grantUsersPage.enterPointsValue(availablePointsForAllocation * 2)
}

Then(~/^He is not able to continue$/) { ->
    waitFor { grantUsersPage.nextButtonStep2.attr('disabled') == 'true' }
    assert grantUsersPage.nextButtonStep2.attr('disabled') == 'true'
}

Then(~/^He is informed that there is not enough points to allocate$/) { ->
    assert grantUsersPage.summaryInformation.text().replace(',', '').replace(' ', '').replace('\n', '').equals(("You are going to share " + (availablePointsForAllocation * 2).toString() + " points with 1 user \n" +
            "which is " + (availablePointsForAllocation * 2).toString() + " points in total.").replace(',', '').replace(' ', '').replace('\n', ''))
    assert grantUsersPage.alert.text() == 'You don\'t have enough points'
}

//Scenario Outline: Grant users - grant users step 2 enough points for allocation
When(~/^He decides to allocate points from his account to the users$/) { ->
    grantUsersPage.enterPointsValue(reasonManagerToUserMax)
}

When(~/^User clicks next button on second step$/) { ->
    grantUsersPage.clickNextButtonOnStepTwo()
}

Then(~/^He is re-directed to the summary screen$/) { ->
    waitFor { grantUsersPage.stepNavigatorConfirmation.hasClass('current') }
    assert grantUsersPage.stepNavigatorConfirmation.hasClass('current')
}

Then(~/^He is able to continue the process$/) { ->
    grantUsersPage.saveButton.attr('disabled') == 'false'
}

//Scenario Outline: Grant users - grant users step 2 points field empty
When(~/^When he decides to leave the points field empty$/) { ->
    //leave empty
}

//Scenario Outline: Grant users - grant users step 2 back button
When(~/^User click back button on second step$/) { ->
    grantUsersPage.clickBackButtonOnStepTwo()
}

Then(~/^He is re-directed to the select card details screen$/) { ->
    waitFor { grantUsersPage.stepNavigatorSelectCardDetails.hasClass('current') }
    assert grantUsersPage.stepNavigatorSelectCardDetails.hasClass('current')
}

Then(~/^All previous set data on select card detail screen are persisted$/) { ->
    waitFor { grantUsersPage.stepNavigatorSelectCardDetails.hasClass('current') }
    assert grantUsersPage.selectReasonDDL.text().startsWith(reason)
    assert grantUsersPage.messageInputField.value() == message
    assert grantUsersPage.ccElementBasic[0].text().contains(ccEmail)
    assert grantUsersPage.selectImageTemplateBasic[imageToBeSelected].hasClass('selected')
}

When(~/^User use global search search with phrase '(.+?)'$/) { String searchPhrase ->
    grantUsersPage.enterPhraseToGlobalSearch(searchPhrase)
}

Then(~/^Results will contain '(.+?)' in field '(.+?)'$/) { String searchPhrase, String field ->
    waitFor { grantUsersPage.searchDdlElement.size() > 0 }
    for (int i = 1; i < grantUsersPage.searchDdlElement.size(); i++) {
        assertThat("Search result: " + grantUsersPage.getSearchResultData(field, i) + " does not contain searched phrase: " + searchPhrase, grantUsersPage.getSearchResultData(field, i).contains(searchPhrase))
        assertThat("Search result: " + grantUsersPage.getSearchResultData(field, i) + " does not contain department name", grantUsersPage.getSearchResultData("department", i).contains("Department"))
        assertThat("Search result: " + grantUsersPage.getSearchResultData(field, i) + " does not contain manager name", grantUsersPage.getSearchResultData("managerName", i).contains("Manager"))
    }
}

Given(~/^Same users are added by global search$/) { ->
    grantUsersPage.enterPhraseToGlobalSearch(existingAssociatedUser)
    grantUsersPage.selectUserFromGlobalSearchResultsList("userName", existingAssociatedUser);
    sleep(3000)
}

Then(~/^Points summary take into consideration twice added users$/) { ->
    assertThat("Wrong number of users mentioned by summary info", grantUsersPage.summaryInformation.text(), is(containsString("with 1 user")))
    assertThat("Wrong number of total points displayed in summary", Integer.parseInt(grantUsersPage.summaryInformationPointsInTotal.text()), is(reasonManagerToUserMax))
}

Then(~/^Twice added user are merged on third confirmation step$/) { ->
    grantUsersPage.clickNextButtonOnStepTwo()
    waitFor { grantUsersPage.stepNavigatorConfirmation.hasClass('current') }
    assertThat("Wrong number of users on list", grantUsersPage.usersTableDataRowBasic.size(), is(1))
    assertThat("Users list on confirmtion page does not contain selected user: " + existingAssociatedUser, grantUsersPage.usersTableDataRowBasic.text(), is(containsString(existingAssociatedUser)))
}

//Scenario Outline: Grant users - grant users step 3 back button, table data correctness
Given(~/^Manager is on the third wizard screen enabling him to confirm the ecard$/) { ->
    grantUsersPage.expandReasonDDL()
    reason = grantUsersPage.selectReasonDDLOption[reasonToBeSelected].text()
    grantUsersPage.selectAwardReasonDDLOption(reasonToBeSelected)
    message = 'randomMessage_' + helpFunctions.returnCurrentEpochTime()
    grantUsersPage.enterMessage(message)
    ccEmail = 'ccEmailRandom_' + helpFunctions.returnCurrentEpochTime() + "@pl.com"
    grantUsersPage.enterCC(ccEmail)
    grantUsersPage.selectImage(imageToBeSelected)
    grantUsersPage.clickNextButtonOnStepOne()
    waitFor { grantUsersPage.stepNavigatorSelectUsers.hasClass('current') }
    waitFor { grantUsersPage.selectUsersNode[userToBeSelectedDepartmentNode].isDisplayed() }

    if (grantUsersPage.selectUsersNode[userToBeSelectedDepartmentNode].text().contains("("))
        userToBeAwardedDepartment = grantUsersPage.selectUsersNode[userToBeSelectedDepartmentNode].text().substring(0, grantUsersPage.selectUsersNode[userToBeSelectedDepartmentNode].text().indexOf("(") - 1)
    else
        userToBeAwardedDepartment = grantUsersPage.selectUsersNode[userToBeSelectedDepartmentNode].text()

    grantUsersPage.clickChooseInChosenNode(userToBeSelectedDepartmentNode)
    grantUsersPage.enterEmailIntoChoosenNodeSearch(existingAssociatedUser)
    grantUsersPage.selectUserFromChosenNodeDDL(userToBeSelectedDepartmentNode)
    waitFor { grantUsersPage.selectUserChooseDDLOptionSelected[userToBeSelectedDepartmentNode].isDisplayed() }
    userToBeAwardedEmail = grantUsersPage.selectUserChooseDDLOptionSelectedEmail[userToBeSelectedDepartmentNode].text()
    userToBeAwardedName = grantUsersPage.selectUserChooseDDLOptionSelectedName[userToBeSelectedDepartmentNode].text()
    grantUsersPage.enterPointsValue(reasonManagerToUserMax)
    grantUsersPage.clickNextButtonOnStepTwo()
    waitFor { grantUsersPage.stepNavigatorConfirmation.hasClass('current') }
}

Given(~/^The tabular view of company members along with allocated points is displayed at the top '(.+?)'$/) { String tableColumns ->
    columns = tableColumns.split(',')
    waitFor { grantUsersPage.usersTable.isDisplayed() }
    for (int i = 0; i < columns.length; i++) {
        assert grantUsersPage.usersTableHeaderRowElementBasic[i].text() == columns[i]
    }
    waitFor {
        grantUsersPage.returnAllocationTableLocator('Points', 0).text().replace(',', '') == reasonManagerToUserMax.toString()
    }
    assert grantUsersPage.usersTableDataRowBasic.size() == 1
    assert grantUsersPage.returnAllocationTableLocator('Name', 0).text() == userToBeAwardedName
    assert grantUsersPage.returnAllocationTableLocator('Email', 0).text() == userToBeAwardedEmail
    assert grantUsersPage.returnAllocationTableLocator('Department', 0).text() == userToBeAwardedDepartment
    assert grantUsersPage.returnAllocationTableLocator('Points', 0).text().replace(',', '') == reasonManagerToUserMax.toString()
    assert grantUsersPage.usersTableDataTotalPointsValue.text().replace(',', '') == reasonManagerToUserMax.toString()
}

Given(~/^Ecard preview is available, including reason, \(ep amount\), message & image$/) { ->
    assert grantUsersPage.previewHeader.text() == 'Preview'
    assert grantUsersPage.previewImg.isDisplayed()
    assert grantUsersPage.previewMessage.text() == message
}

When(~/^User click back button on third step$/) { ->
    grantUsersPage.clickBackButtonOnStepThird()
}

//Scenario Outline: Grant users - grant users step 3 save button
When(~/^Manager clicks on the save button on third step$/) { ->
    helpFunctions.waitSomeTime(Config.waitShort)
    grantUsersPage.clickSaveButtonOnStepThree()
}

Then(~/^Confirmation is shown about card being sent successfully$/) { ->
    waitFor { grantUsersPage.grantUsersLoader.isDisplayed() }
    assert { grantUsersPage.grantUsersLoader.text().contains('Sending... Please wait') }
    waitFor(15) { grantUsersPage.topNavigation.alertInfo.text() == 'Points have been sent' }
}

Then(~/^Epoints are added to chosen accounts$/) { ->
    //Leave empty
}

Then(~/^Virtual pot is deducted$/) { ->
    waitFor { grantUsersPage.stepNavigatorSelectCardDetails.hasClass('current') }
    assert (grantUsersPage.availablePointsToAlocate.text() == availablePointsForAllocation - reasonManagerToUserMax).toString()
}

//Scenario Outline: Grant users - grant users step 3 remove
When(~/^Manager decides to remove users from award table$/) { ->
    grantUsersPage.clickRemoveButtonOfChosenUser(userToBeSelectedDepartmentNode)
}

When(~/^Confirm user removing in confirmation popup$/) { ->
    waitFor { grantUsersPage.deleteUserConfirmationPopup.isDisplayed() }
    assert grantUsersPage.deleteUserConfirmationPopupInfo.text().contains(userToBeAwardedName)
    grantUsersPage.clickDeleteUserConfirmationPopupRemoveButton()
}

Then(~/^User will be removed from users award table$/) { ->
    waitFor { !grantUsersPage.usersTable.isDisplayed() }
}