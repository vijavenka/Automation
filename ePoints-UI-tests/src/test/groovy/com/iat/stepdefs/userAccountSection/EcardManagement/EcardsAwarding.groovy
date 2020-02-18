package com.iat.stepdefs.userAccountSection.EcardManagement

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.userAccount.ecardsManagement.EcardsCreationPage
import com.iat.pages.userAccount.ecardsManagement.EcardsHistoryPage
import com.iat.stepdefs.utils.Functions

import static cucumber.api.groovy.EN.*

def homePage = new EpointsHomePage()
def functions = new Functions()
def ecardsCreationPage = new EcardsCreationPage()
def ecardsHistoryPage = new EcardsHistoryPage()
def selectedUserFirstname
def selectedUserLastName
def pointsToAward
def reasonName
def reasonGlobalMinValue = 10
def reasonGlobalMaxValue = 100
def message
def ccEmail = 'emailWhichDoNotHaveExistsInOurSystem@gmail.com'
int imageNr
int currentBalanceState
String imageSource

Given(~/^User with a certain ecard access is signed in to epoints$/) { ->
    to EpointsHomePage
    homePage = page
    functions.clearCookiesAndStorage()
    homePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    homePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
}

When(~/^Interface to add ecard recipients is opened$/) { ->
    homePage.userDropDownMenuModule.clickOnEcardManagementOption()
    at EcardsCreationPage
    ecardsCreationPage = page
}

When(~/^User clicks on an image$/) { ->
    imageNr = 1
    ecardsCreationPage.clickOnThumbnailImageFirstStep(imageNr)
    imageSource = ecardsCreationPage.ecardCreationOneThumbnailImage[imageNr].find('img').attr('ng-src')
}

Then(~/^Its large version is displayed$/) { ->
    //waitFor{ ecardsCreationPage.ecardCreationOneLargeImageArea.find('img').attr('ng-src') == imageSource }
}

And(~/^The thumbnail changes to selected state$/) { ->
    assert ecardsCreationPage.checkIfThumbnailIsMarkedAsSelected(imageNr)
}

When(~/^User picks a reason, message, image and clicks on next$/) { ->
    ecardsCreationPage.completeEcardCreationFirstStep(1, 1, "I am some short message")
}

Then(~/^Ecard should be created and user should be moved to the second step$/) { ->
    assert ecardsCreationPage.getActualStep() == 2
}

When(~/^User do not enter data into '(.+?)' field$/) { String emptyField ->
    if (emptyField.equals('reason')) {
        ecardsCreationPage.completeEcardCreationFirstStep(1, 99, "message")
    } else if (emptyField.equals('message')) {
        ecardsCreationPage.completeEcardCreationFirstStep(0, 0, "")
    }
}

When(~/^User click next button on ecard creation step one$/) { ->
    ecardsCreationPage.clickOnNextButtonFirstStep()
}

Then(~/^Alert '(.+?)' should be diplayed$/) { String alert ->
    if (alert.contains('Reason')) {
        waitFor { ecardsCreationPage.ecardCreationValidationMessageBasic[0].isDisplayed() }
        assert ecardsCreationPage.ecardCreationValidationMessageBasic[0].text() == alert
    } else {
        waitFor { ecardsCreationPage.ecardCreationValidationMessageBasic[1].isDisplayed() }
        assert ecardsCreationPage.ecardCreationValidationMessageBasic[1].text() == alert
    }
}

Then(~/^User should remain on page '(.*)'$/) { int _actualPage ->
    assert ecardsCreationPage.getActualStep() == _actualPage
}

When(~/^User picks a reason, message with '(.*)' chars, image and clicks on next$/) { int _msgSize ->
    ecardsCreationPage.clickOnThumbnailImageFirstStep(1)
    ecardsCreationPage.enterReasonTextFirstStep(functions.returnRandomString(_msgSize))
    ecardsCreationPage.expandReasonDDLList()
    ecardsCreationPage.selectReasonFromDropDownFirstStep(1)
    ecardsCreationPage.clickOnNextButtonFirstStep()
}

Then(~/^Validation '(.+?)' should be displayed$/) { String lengthAlert ->
    waitFor { ecardsCreationPage.ecardCreationValidationMessageBasic[1].isDisplayed() }
    assert ecardsCreationPage.ecardCreationValidationMessageBasic[1].text() == lengthAlert
}

//HS-101
//Scenario Create ecard page - find users - initial view
When(~/^First ecard creation step is properly finished$/) { ->
    message = 'random message ' + functions.returnRandomValue(1000000)
    reasonName = ecardsCreationPage.completeEcardCreationFirstStep(1, 0, message)

    reasonGlobalMinValue = 10
    reasonGlobalMaxValue = 100
}

Then(~/^User can see empty search box$/) { ->
    waitFor { ecardsCreationPage.ecardCreationTwoSearchInputField.isDisplayed() }
    assert !ecardsCreationPage.ecardCreationTwoSelectedUserBasic.isDisplayed()
    assert ecardsCreationPage.ecardCreationTwoSearchUsersLabel.text() == 'Send ecard to:'
}

Then(~/^Clear all users option is available$/) { ->
    assert ecardsCreationPage.ecardCreationTwoClearAllButton.isDisplayed()
}

Then(~/^Initially there are no users displayed on search list$/) { ->
    assert !ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic.isDisplayed()
}

//Scenario Outline: Create ecard page - find users - email not existing in epoints
When(~/^He wants to add an email address that is not in epoints '(.+?)'$/) { String searchPhrase ->
    ecardsCreationPage.enterSearchPhraseToUsersSearchInputField(searchPhrase)
}

Then(~/^The '(.+?)' address is not found in the predictive search$/) { String searchPhrase ->
    for (int i = 0; i < ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic.size(); i++) {
        assert !ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic[i].text().contains(searchPhrase)
    }
}

Then(~/^Its impossible to add that address to recipients list$/) { ->
    //leave empty
}

//Scenario Outline: Create ecard page - find users - inactive mail
When(~/^He wants to add an email address that is in epoints but is not active '(.+?)'$/) { String searchPhrase ->
    ecardsCreationPage.enterSearchPhraseToUsersSearchInputField(searchPhrase)
}

//Scenario Outline: Create ecard page - find users - Search
When(~/^He types in the first\/last name or email in the search box '(.+?)'$/) { String searchPhrase ->
    ecardsCreationPage.enterSearchPhraseToUsersSearchInputField(searchPhrase)
}

Then(~/^Predictive users list is shown with matching results '(.+?)'$/) { String searchPhrase ->
    waitFor { ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic.size() > 0 }
    for (int i = 0; i < ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic.size(); i++) {
        assert ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic[i].text().toLowerCase().contains(searchPhrase.toLowerCase())
    }
}

//Scenario Outline: Create ecard page - find users - unverified mail
When(~/^He wants to add an email address that is in epoints but is not verified '(.+?)'$/) { searchPhrase ->
    ecardsCreationPage.enterSearchPhraseToUsersSearchInputField(searchPhrase)
}

//Scenario Outline: Create ecard page - find users - select a user
When(~/^Select a user from predictive search list$/) { ->
    waitFor { ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic[0].isDisplayed() }
    def basicUserInfo = ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasicEmail[0].text()
    selectedUserFirstname = basicUserInfo.split(' ')[0]
    selectedUserLastName = basicUserInfo.split(' ')[1]
    ecardsCreationPage.selectChosenUserFromPredictiveUsersList(0)
}

Then(~/^User's first and last name is added to the search box as a tag view$/) { ->
    waitFor { ecardsCreationPage.ecardCreationTwoSelectedUserBasic[0].text().contains(selectedUserFirstname) }
    waitFor { ecardsCreationPage.ecardCreationTwoSelectedUserBasic[0].text().contains(selectedUserLastName) }
}

//Scenario Outline: Create ecard page - find users - deselect a user
When(~/^One user is already selected '(.+?)'$/) { String searchPhrase ->
    ecardsCreationPage.enterSearchPhraseToUsersSearchInputField(searchPhrase)
    waitFor { ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic[0].isDisplayed() }
    def basicUserInfo = ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasicEmail[0].text()
    selectedUserFirstname = basicUserInfo.split(' ')[0]
    selectedUserLastName = basicUserInfo.split(' ')[1]
    ecardsCreationPage.selectChosenUserFromPredictiveUsersList(0)
}

When(~/^He clicks on the "X" on selected user's tag$/) { ->
    ecardsCreationPage.removeChosenUserFromSelectedUsersInputField(0)
}

Then(~/^This user is removed from selected users panel$/) { ->
    waitFor { !ecardsCreationPage.ecardCreationTwoSelectedUserBasic.isDisplayed() }
    assert !ecardsCreationPage.ecardCreationTwoSelectedUserBasic.isDisplayed()
}

//Scenario Outline: Create ecard page - find users - clear button
When(~/^He clicks on the "clear button" next to users search$/) { ->
    ecardsCreationPage.clickClearAllButton()
}

Then(~/^Predictive search list contains no elements$/) { ->
    waitFor { !ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic[0].isDisplayed() }
    assert !ecardsCreationPage.ecardCreationTwoUserOnTheUsersListBasic[0].isDisplayed()
}

//HS-98
//Scenario Outline: Create ecard page - points award - general view
When(~/^We choose the number of epoints to award$/) { ->
    pointsToAward = reasonGlobalMinValue + 1
    ecardsCreationPage.enterPointsValueForUserAward(pointsToAward)
}

Then(~/^The total number of epoints is counted$/) { ->
    ecardsCreationPage.cardCreationTwoPointsToAwardValue.text().equals(pointsToAward.toString())
}

Then(~/^User is informed how many users will receive these points$/) { ->
    assert ecardsCreationPage.cardCreationTwoUserAwardedNumberInfo.text().replace('\n', ' ').equals("You are sending this to a total of 1 user, at a total cost of")
}

Then(~/^The total number of epoints is displayed$/) { ->
    assert ecardsCreationPage.cardCreationTwoPointsToAwardValue.text().equals(pointsToAward.toString())
}

Then(~/^The number of epoints left after transaction is shown as well$/) { ->
    assert ecardsCreationPage.cardCreationTwoLeftPointsValue.text().replace(',', '').contains((Integer.parseInt(ecardsCreationPage.headerModule.headerPointsBalanceConfirmed.text()) - pointsToAward).toString())
}

//Scenario: Create ecard page - points award - points exceeded reason limit
When(~/^He provides number of points bigger than available in the reason max limit$/) { ->
    ecardsCreationPage.enterPointsValueForUserAward(reasonGlobalMaxValue + 1)
}

Then(~/^He is informed that he number of points exceeded the max reason limit$/) { ->
    waitFor { ecardsCreationPage.cardCreationTwoAlertReasonMaxExceeded.isDisplayed() }
    assert ecardsCreationPage.cardCreationTwoAlertReasonMaxExceeded.text() == 'You exceeded max reason value (' + reasonGlobalMaxValue + ')'
}

Then(~/^He cannot move on and send an ecard$/) { ->
    //waitFor{ ecardsCreationPage.ecardCreationTwoNextButton.attr('disabled') == 'disabled' }
    assert ecardsCreationPage.getActualStep() == 2
}

//Scenario: Create ecard page - points award - points exceeded max reason limit
When(~/^He provides number of points lower than available in the reason min limit$/) { ->
    ecardsCreationPage.enterPointsValueForUserAward(reasonGlobalMinValue - 1)
}

Then(~/^He is informed that he number of points exceeded the min reason limit$/) { ->
    waitFor { ecardsCreationPage.cardCreationTwoAlertReasonMinExceeded.isDisplayed() }
    assert ecardsCreationPage.cardCreationTwoAlertReasonMinExceeded.text() == 'You didn\'t reach min reason value (' + reasonGlobalMinValue + ')'
}

//Scenario Outline: Create ecard page - points award - points exceeded min reason limit
When(~/^Two users are already selected '(.+?)'$/) { String searchPhrase ->
    ecardsCreationPage.enterSearchPhraseToUsersSearchInputField(searchPhrase)
    ecardsCreationPage.selectChosenUserFromPredictiveUsersList(0)
    ecardsCreationPage.selectChosenUserFromPredictiveUsersList(1)
}

When(~/^He chooses such number of epoints that \(no\. of users x ep\) exceeds available points$/) { ->
    pointsToAward = (Integer.parseInt(ecardsCreationPage.headerModule.headerPointsBalanceConfirmed.text()) / 2 + 1).toInteger() * 2
    ecardsCreationPage.enterPointsValueForUserAward(pointsToAward)
}

Then(~/^He is informed that he cannot allocate these points$/) { ->
    waitFor { ecardsCreationPage.cardCreationTwoAlertPointsExceeded.isDisplayed() }
    assert ecardsCreationPage.cardCreationTwoAlertPointsExceeded.text() == 'You exceeded your account balance'
}

//Scenario Outline: Create ecard page - points award - enough points
When(~/^He chooses such number of epoints that \(no\. of users x ep\) is lower or the same as available points$/) { ->
    //pointsToAward = Integer.parseInt(ecardsCreationPage.headerModule.headerPointsBalanceConfirmed.text()) - 1
    pointsToAward = reasonGlobalMinValue + 1
    ecardsCreationPage.enterPointsValueForUserAward(pointsToAward)
}

Then(~/^Information about the cost of transaction is displayed$/) { ->
    ecardsCreationPage.cardCreationTwoPointsToAwardValue.text().equals(pointsToAward.toString())
}

Then(~/^User can move on to the next step by clicking next button on ecard create second step$/) { ->
    ecardsCreationPage.clickEcardCreationTwoNextButton()
    waitFor { ecardsCreationPage.getActualStep() == 3 }
    assert ecardsCreationPage.ecardCreationThreePointsSummarySection.isDisplayed()
}

//Scenario: Create ecard page - points award - back button
When(~/^User clicks back button on ecard creation second step$/) { ->
    ecardsCreationPage.clickEcardCreationTwoBackButton()
}

Then(~/^He will be moved to ecard creation step one$/) { ->
    waitFor { ecardsCreationPage.getActualStep() == 1 }
}

Then(~/^End previous data filled on ecard creation step one will be displayed$/) { ->
    assert ecardsCreationPage.ecardCreationOneReasonDropDown.text().equals(reasonName)
    assert ecardsCreationPage.ecardCreationOneMessageTextArea.value().equals(message)
    assert ecardsCreationPage.ecardCreationOneThumbnailImage[1].hasClass('selected')
}

//HS-102
//Scenario: Create ecard page - summary - preview
When(~/^Second ecard creation step is properly finished with (.*) selected user$/) { int numberOfUserToBeAwarded ->
    pointsToAward = 11
    currentBalanceState = Integer.parseInt(ecardsCreationPage.headerModule.headerPointsBalanceConfirmed.text())
    def firstUserData = ecardsCreationPage.completeEcardCreationSecondStep('emailfortest', pointsToAward, numberOfUserToBeAwarded)
    sleep(5000)
    selectedUserFirstname = firstUserData.split(' ')[0]
    selectedUserLastName = firstUserData.split(' ')[1]
}

Then(~/^He is presented with the preview of an ecard$/) { ->
    waitFor { ecardsCreationPage.getActualStep() == 3 }
    assert ecardsCreationPage.ecardCreationThreePointsSummarySection.isDisplayed()
}

Then(~/^Preview Contains image, message, reason and epoints$/) { ->
    assert ecardsCreationPage.cardCreationThreeReason.text().equals(reasonName)
    assert ecardsCreationPage.cardCreationThreeReasonLabel.text().equals('Reason:')
    assert ecardsCreationPage.cardCreationThreeTotalEpointsValue.text().equals(pointsToAward.toString())
    assert ecardsCreationPage.cardCreationThreeTotalEpointsLabel.text().equals('Total epoints:')
    assert ecardsCreationPage.cardCreationThreePicture.isDisplayed()
    assert ecardsCreationPage.cardCreationThreePictureLabel.text().equals('Picture:')
    assert ecardsCreationPage.cardCreationThreePersonalMessage.text().equals(message)
    assert ecardsCreationPage.cardCreationThreePersonalMessageLabel.text().equals('Personal message:')
}

//Scenario: Create ecard page - summary - not enough points
When(~/^User doesn't have enough points on his account$/) { ->
    //TODO user ballance has to be changed and set to 0
}

When(~/^He clicks "Send ecard" button$/) { ->
    ecardsCreationPage.clickEcardCreationThreeSendEcardButton()
}

Then(~/^He is informed that he doesn't have enough points$/) { ->
    waitFor { ecardsCreationPage.cardCreationThreeAlert.isDisplayed() }
    assert ecardsCreationPage.cardCreationThreeAlert.text().equals('')
}

Then(~/^Card is not sent$/) { ->
    //leave empty
}

Then(~/^He stays on the third step of ecard sending wizard page$/) { ->
    waitFor { ecardsCreationPage.getActualStep() == 3 }
    assert ecardsCreationPage.ecardCreationThreePointsSummarySection.isDisplayed()
}

//Scenario: Create ecard page - summary - enough points
Then(~/^He is re-directed to the ecard send success screen$/) { ->
    waitFor { ecardsCreationPage.ecardCreationSuccessGreenAlert.isDisplayed() }
    assert ecardsCreationPage.ecardCreationSuccessHeader.text().equals('Thank you for recognising a colleague')
}

Then(~/^There's a link to proceed to "History"$/) { ->
    assert ecardsCreationPage.ecardCreationSuccessPersonalEcardsHistoryLink[0].isDisplayed()
}

Then(~/^User can see his sent email in activity stream$/) { ->
    ecardsCreationPage.clickEcardCreationSuccessPersonalEcardsHistoryLink()
    waitFor { at EcardsHistoryPage }
    ecardsHistoryPage = page
    assert ecardsHistoryPage.ecardReasonBasic[0].text() == reasonName
    assert ecardsHistoryPage.ecardFromToWhoBasic[0].text().contains(selectedUserFirstname)
    assert ecardsHistoryPage.ecardFromToWhoBasic[0].text().contains(selectedUserLastName)
    assert ecardsHistoryPage.ecardPointsValueBasic[0].text().contains(pointsToAward.toString())
    assert ecardsHistoryPage.ecardAddedDate[0].text() == 'a few seconds ago'
}
Then(~/^Balance value was reduced of sent epoints$/) { ->
    waitFor {
        Integer.parseInt(ecardsHistoryPage.headerModule.headerPointsBalanceConfirmed.text()) == currentBalanceState - pointsToAward
    }
}

//Scenario: Create ecard page - summary - back button
When(~/^He clicks "Back" button on ecard summary screen$/) { ->
    ecardsCreationPage.clickEcardCreationThreeBackButton()
}

Then(~/^He is re-directed to the second wizard page$/) { ->
    waitFor { ecardsCreationPage.getActualStep() == 2 }
    assert ecardsCreationPage.ecardCreationTwoSearchUsersSection.isDisplayed()
}

Then(~/^He can see his selected users$/) { ->
    assert ecardsCreationPage.ecardCreationTwoSelectedUserBasic[0].text().contains(selectedUserFirstname)
    assert ecardsCreationPage.ecardCreationTwoSelectedUserBasic[0].text().contains(selectedUserLastName)
}

Then(~/^He can see all of the data he provided on users selection screen$/) { ->
    assert ecardsCreationPage.cardCreationTwoPointsToAwardValue.text().equals(pointsToAward.toString())
}

//Scenario: Create ecard page - summary - recipients list
When(~/^"X others" link is displayed under the email addresses$/) { ->
    waitFor { ecardsCreationPage.cardCreationThreeExpandListButton.isDisplayed() }
}

When(~/^He clicks on "X others" button$/) { ->
    ecardsCreationPage.clickEcardCreationThreeExpandListButton()
}

Then(~/^The scrollable pop-up with recipients list is displayed here$/) { ->
    waitFor { ecardsCreationPage.cardCreationThreeMoreUsersModal.isDisplayed() }
    assert ecardsCreationPage.cardCreationThreeMoreUsersModalContent.isDisplayed()
    assert ecardsCreationPage.cardCreationThreeMoreUsersCloseButton.isDisplayed()
    assert ecardsCreationPage.cardCreationThreeMoreUsersXButton.isDisplayed()
}

Then(~/^User can close it after viewing it$/) { ->
    ecardsCreationPage.clickEcardCreationThreeMoreUsersCloseButton()
    waitFor { !ecardsCreationPage.cardCreationThreeMoreUsersModal.isDisplayed() }
}

//Scenario: Create ecard page - summary - recipients list adding
When(~/^User provide an email to cc input field$/) { ->
    ecardsCreationPage.enterCCValueIntoCCInputField(ccEmail)
}

Then(~/^New email entry will be added to cc list$/) { ->
    waitFor { ecardsCreationPage.cardCreationThreeCCInputFieldSelectedElementBasic[0].text().contains(ccEmail) }
    assert ecardsCreationPage.cardCreationThreeCCInputFieldSelectedElementBasic[0].text().contains(ccEmail)
}

//Scenario: Create ecard page - summary - recipients list removing
When(~/^User removed provide email from cc list$/) { ->
    ecardsCreationPage.removeChosenCCFromSelectedCCInputField(0)
}

Then(~/^removed email will be romoved from cc input field$/) { ->
    waitFor { !ecardsCreationPage.cardCreationThreeCCInputFieldSelectedElementBasic[0].isDisplayed() }
    assert !ecardsCreationPage.cardCreationThreeCCInputFieldSelectedElementBasic[0].isDisplayed()
}

//Scenario: Create ecard page - summary - enough points cc provided
//Scenario: Create ecard page - summary - enough points cc not provided covered in some of previous scenarious