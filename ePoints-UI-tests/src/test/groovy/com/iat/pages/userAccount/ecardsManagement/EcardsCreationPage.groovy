package com.iat.pages.userAccount.ecardsManagement

import com.iat.pages.AbstractPage
import com.iat.pages.userAccount.ecardsManagement.modules.EcardsNavigationModule
import org.openqa.selenium.Keys

class EcardsCreationPage extends AbstractPage {

    static url = '/my-account/ecards/create'
    static atCheckWaiting = true
    static at = { waitFor { title.equals('Create ecard | epoints') } }

    static content = {
        //General
        ecardsNavigationModule { module EcardsNavigationModule }
        ecardCreationCurrentStepNr(wait: true) { $('.CreateEcards-progress').find('p', 1) }

        //First step on path to create Ecard
        ecardCreationOneBase(wait: true) { $(".EcardMgmt-content") }
        ecardCreationOneLargeImageArea(wait: true) { ecardCreationOneBase.find(".EcardSlider-activeImage") }
        ecardCreationOneThumbnailImage(wait: true) { ecardCreationOneBase.find(".EcardSlider-ecardsList").find('li') }
        ecardCreationOneReasonLabel(wait: true) { ecardCreationOneBase.find(".selectize-input").find('label', 0) }
        ecardCreationOneReasonDropDown(wait: true) { ecardCreationOneBase.find(".selectize-input") }
        ecardCreationOneReasonDropDownOption(wait: true) {
            ecardCreationOneBase.find(".selectize-dropdown").find('.option')
        }
        ecardCreationOneMessageLabel(wait: true) { ecardCreationOneBase.find("textarea").find('label', 1) }
        ecardCreationOneMessageTextArea(wait: true) { ecardCreationOneBase.find("textarea") }
        ecardCreationOneTitle(wait: true) { ecardCreationOneBase.find("h2") }
        ecardCreationOneNextButton(wait: true) { ecardCreationOneBase.find(".btn") }
        ecardCreationValidationMessageBasic(wait: true) { ecardCreationOneBase.find(".errors") }

        //Second step on path to create Ecard
        ecardCreationTwoSearchUsersSection(wait: true) { $('.CreateEcards-search') }
        ecardCreationTwoSearchUsersLabel(wait: true) { $('.CreateEcards-content').find('h4') }
        ecardCreationTwoSearchInputField(wait: true) { ecardCreationTwoSearchUsersSection.find('input') }
        ecardCreationTwoSelectedUserBasic(wait: true, required: false) {
            ecardCreationTwoSearchUsersSection.find('.selectize-input').find('.item')
        }
        ecardCreationTwoSelectedUserXButtonBasic(wait: true, required: false) {
            ecardCreationTwoSearchUsersSection.find('.selectize-input').find('.remove')
        }
        ecardCreationTwoClearAllButton(wait: true) { ecardCreationTwoSearchUsersSection.find('.CreateEcards-clearAll') }
        ecardCreationTwoUsersListSection(wait: true, required: false) {
            ecardCreationTwoSearchUsersSection.find('.selectize-dropdown.multi')
        }
        ecardCreationTwoUserOnTheUsersListBasic(wait: true, required: false) {
            ecardCreationTwoUsersListSection.find('.selectize-dropdown-content').find('.option')
        }
        ecardCreationTwoUserOnTheUsersListBasicEmail(wait: true, required: false) {
            ecardCreationTwoUsersListSection.find('.selectize-name')
        }
        ecardCreationTwoUserOnTheUsersListBasicFirstName(wait: true, required: false) {
            ecardCreationTwoUsersListSection.find('.selectize-name')
        } //same as previous will be split in tests
        ecardCreationTwoUserOnTheUsersListBasicLastName(wait: true, required: false) {
            ecardCreationTwoUsersListSection.find('.selectize-name')
        }//same as previous will be split in tests

        ecardCreationTwoPointsValueSection(wait: true) { $('.CreateEcards-sidebar') }
        cardCreationTwoEpointsInputFieldLabel(wait: true) { ecardCreationTwoPointsValueSection.find('label') }
        cardCreationTwoEpointsInputField(wait: true) { ecardCreationTwoPointsValueSection.find('input') }
        cardCreationTwoUserAwardedNumberInfo(wait: true) { ecardCreationTwoPointsValueSection.find('.text-muted') }
        cardCreationTwoPointsToAwardValue(wait: true) { ecardCreationTwoPointsValueSection.find('.CreateEcards-total') }
        cardCreationTwoLeftPointsValue(wait: true) { ecardCreationTwoPointsValueSection.find('.CreateEcards-balance') }
        cardCreationTwoAlertReasonMaxExceeded(wait: true, required: false) {
            $('span[ng-show="vm.selectUsersStep.errors.reasonMaxExceeded"]')
        }
        cardCreationTwoAlertReasonMinExceeded(wait: true, required: false) {
            $('span[ng-show="vm.selectUsersStep.errors.reasonMinExceeded"]')
        }
        cardCreationTwoAlertPointsExceeded(wait: true, required: false) {
            $('span[ng-show="vm.selectUsersStep.errors.pointsExceeded"]')
        }
        cardCreationTwoReasonRange(wait: true) { $("") }

        ecardCreationTwoBackButton(wait: true) { ecardCreationTwoPointsValueSection.find('.CreateEcards-button-back') }
        ecardCreationTwoNextButton(wait: true) { ecardCreationTwoPointsValueSection.find('.CreateEcards-button-next') }

        //Third step on path to create Ecard
        ecardCreationThreePointsSummarySection(wait: true) { $('.CreateEcards-content') }
        cardCreationThreeSendEcardToLabel(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation').find('dt', 0)
        }
        cardCreationThreeSendEcardToNameBasic(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation-user')
        }
        cardCreationThreeExpandListButton(wait: true, required: false) {
            ecardCreationThreePointsSummarySection.find('.EcardsActivity-others')
        }
        cardCreationThreeMoreUsersModal(wait: true, required: false) { $('.modal-body') }
        cardCreationThreeMoreUsersModalContent(wait: true, required: false) {
            cardCreationThreeMoreUsersModal.find('.EcardOtherUsers')
        }
        cardCreationThreeMoreUsersCloseButton(wait: true, required: false) {
            cardCreationThreeMoreUsersModal.find('.btn-yellow')
        }
        cardCreationThreeMoreUsersXButton(wait: true, required: false) { $('.modal-close-btn') }
        cardCreationThreeTotalEpointsLabel(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation').find('dt', 1)
        }
        cardCreationThreeTotalEpointsValue(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation-points')
        }
        cardCreationThreeReasonLabel(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation').find('dt', 2)
        }
        cardCreationThreeReason(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation-reason')
        }
        cardCreationThreePictureLabel(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation').find('dt', 3)
        }
        cardCreationThreePicture(wait: true) { ecardCreationThreePointsSummarySection.find('img') }
        cardCreationThreePersonalMessageLabel(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation').find('dt', 4)
        }
        cardCreationThreePersonalMessage(wait: true) {
            ecardCreationThreePointsSummarySection.find('.CreateEcardsConfirmation-message')
        }

        ecardCreationThreePoiCCSection(wait: true) { $('.CreateEcards-grant') }
        cardCreationThreeCCInputFildLabel(wait: true) { ecardCreationThreePoiCCSection.find('label') }
        cardCreationThreeCCInputField(wait: true) {
            ecardCreationThreePoiCCSection.find('.selectize-input').find('input')
        }
        cardCreationThreeCCInputFieldSelectedElementBasic(wait: true, required: false) {
            ecardCreationThreePoiCCSection.find('.item')
        }
        cardCreationThreeCCInputFieldSelectedElementXbuttonBasic(wait: true, required: false) {
            ecardCreationThreePoiCCSection.find('.remove')
        }
        cardCreationThreeAlert(wait: true, required: false) { $('') }

        ecardCreationThreeSendEcardButton(wait: true) { $('.CreateEcards-button-next') }
        ecardCreationThreeBackButton(wait: true) { $('.CreateEcards-button-back') }

        ecardCreationSuccessHeader(wait: true) { $('.CreateEcards-completed-title') }
        ecardCreationSuccessGreenAlert(wait: true) { $('.CreateEcards-completed--success') }
        ecardCreationSuccessPersonalEcardsHistoryLink(wait: true) { $('.CreateEcards-completed-historyLink', 0) }
        ecardCreationSuccessCreatrNewEcardLink(wait: true) { $('.CreateEcards-completed-historyLink', 1) }
    }

    int getActualStep() {
        waitFor { ecardCreationCurrentStepNr.isDisplayed() };
        Integer.parseInt(ecardCreationCurrentStepNr.text().substring(0, ecardCreationCurrentStepNr.text().lastIndexOf(' /')))
    }

    //Atomic methods for Ecards Creation Flow first step
    def clickOnThumbnailImageFirstStep(int image) {
        waitFor { ecardCreationOneThumbnailImage[image].isDisplayed() }
        sleep(1000)
        ecardCreationOneThumbnailImage[image].click()
    }

    def expandReasonDDLList() {
        waitFor { ecardCreationOneReasonDropDown.isDisplayed() }; ecardCreationOneReasonDropDown.click()
    }

    def selectReasonFromDropDownFirstStep(int reason) {
        waitFor { ecardCreationOneReasonDropDownOption[reason].isDisplayed() };
        ecardCreationOneReasonDropDownOption[reason].click()
    }

    def enterReasonTextFirstStep(String reasonText) {
        waitFor { ecardCreationOneMessageTextArea.isDisplayed() }; ecardCreationOneMessageTextArea.value(reasonText)
    }

    def clickOnNextButtonFirstStep() {
        waitFor { ecardCreationOneNextButton.isDisplayed() }; ecardCreationOneNextButton.click()
        sleep(500)
    }

    def getValidationMessageFirstStep() {
        waitFor { ecardCreationValidationMessageBasic }; ecardCreationValidationMessageBasic.text()
    }

    def checkIfImageSameAsSelectedThumbnail(imageNumber) {
        return ecardCreationOneThumbnailImage[imageNumber].hasClass('selected')
    }

    def checkIfThumbnailIsMarkedAsSelected(imageNumber) {
        return ecardCreationOneThumbnailImage[imageNumber].hasClass('selected')
    }

    //Atomic methods for Ecards Creation Flow second step
    def enterSearchPhraseToUsersSearchInputField(searchPhrase) {
        waitFor { ecardCreationTwoSearchInputField.isDisplayed() }; ecardCreationTwoSearchInputField.value(searchPhrase)
    }

    def selectChosenUserFromPredictiveUsersList(number) {
        /*waitFor{ ecardCreationTwoUserOnTheUsersListBasic[number].isDisplayed() };*/ sleep(1000);
        ecardCreationTwoUserOnTheUsersListBasic[number].click()
    }

    def removeChosenUserFromSelectedUsersInputField(number) {
        waitFor { ecardCreationTwoSelectedUserXButtonBasic[number].isDisplayed() };
        ecardCreationTwoSelectedUserXButtonBasic[number].click()
    }

    def clickClearAllButton() {
        waitFor { ecardCreationTwoClearAllButton.isDisplayed() }; ecardCreationTwoClearAllButton.click()
    }

    def clickEcardCreationTwoNextButton() {
        waitFor { ecardCreationTwoNextButton.isDisplayed() }; ecardCreationTwoNextButton.click()
        sleep(500)
    }

    def enterPointsValueForUserAward(pointsValue) {
        waitFor { cardCreationTwoEpointsInputField.isDisplayed() }; cardCreationTwoEpointsInputField.value(pointsValue)
    }

    def clickEcardCreationTwoBackButton() {
        waitFor { ecardCreationTwoBackButton.isDisplayed() }; ecardCreationTwoBackButton.click()
    }

    int getReasonIndexInDropDownByName(String reasonName) {
        sleep(1000)
        expandReasonDDLList()
        ecardCreationOneReasonDropDownOption.findIndexOf { it.text() == reasonName }
    }

    String completeEcardCreationFirstStep(int image, int reason, String reasonText) {
        def reasonName
        clickOnThumbnailImageFirstStep(image)
        enterReasonTextFirstStep(reasonText)
        expandReasonDDLList()
        if (!(reason == 99)) {
            reasonName = ecardCreationOneReasonDropDownOption[reason].text()
            selectReasonFromDropDownFirstStep(reason)
        }
        clickOnNextButtonFirstStep()
        return reasonName
    }

    def completeEcardCreationSecondStep(String searchPhrase, pointsValue, usersToAwardNumber) {
        enterSearchPhraseToUsersSearchInputField(searchPhrase)
        waitFor { ecardCreationTwoUserOnTheUsersListBasic[0].isDisplayed() }
        String firstUserData = ecardCreationTwoUserOnTheUsersListBasicEmail[0].text()
        //we need 4 users to check see more list on third step, wee need to have four associated with search phrase tests accounts
        for (int i = 0; i < usersToAwardNumber; i++) {
            selectChosenUserFromPredictiveUsersList(0)
        }
        enterPointsValueForUserAward(pointsValue)
        clickEcardCreationTwoNextButton()
        return firstUserData
    }

    def completeEcardCreationSecondStepEx(searchPhrases, pointsValue) {
        searchPhrases.each { String phrase ->
            enterSearchPhraseToUsersSearchInputField(phrase)
            selectChosenUserFromPredictiveUsersList(0)
        }
        enterPointsValueForUserAward(pointsValue)
        clickEcardCreationTwoNextButton()
    }

    def proceedToCreateEcardThirdStep() {
        ecardsNavigationModule.clickCreateEcardNavigationOption()
        completeEcardCreationFirstStep(1, 1, "Test")
        completeEcardCreationSecondStep("userX", 1)
    }

    //Atomic methods for Ecards Creation Flow third step
    def enterCCValueIntoCCInputField(cc) {
        waitFor { cardCreationThreeCCInputField.isDisplayed() }; cardCreationThreeCCInputField.value(cc)
        cardCreationThreeCCInputField << Keys.chord(Keys.ENTER)
    }

    def removeChosenCCFromSelectedCCInputField(number) {
        waitFor { cardCreationThreeCCInputFieldSelectedElementXbuttonBasic[number].isDisplayed() };
        cardCreationThreeCCInputFieldSelectedElementXbuttonBasic[number].click()
    }

    def clickEcardCreationThreeBackButton() {
        waitFor { ecardCreationThreeBackButton.isDisplayed() }; ecardCreationThreeBackButton.click()
    }

    def clickEcardCreationThreeSendEcardButton() {
        waitFor { ecardCreationThreeSendEcardButton.isDisplayed() }; ecardCreationThreeSendEcardButton.click()
    }

    def clickEcardCreationSuccessPersonalEcardsHistoryLink() {
        waitFor { ecardCreationSuccessPersonalEcardsHistoryLink.isDisplayed() };
        ecardCreationSuccessPersonalEcardsHistoryLink.click()
    }

    def clickEcardCreationThreeExpandListButton() {
        waitFor { cardCreationThreeExpandListButton.isDisplayed() }; cardCreationThreeExpandListButton.click()
    }

    def clickEcardCreationThreeMoreUsersCloseButton() {
        sleep(1000)
        waitFor { cardCreationThreeMoreUsersCloseButton.isDisplayed() }; cardCreationThreeMoreUsersCloseButton.click()
    }
}
