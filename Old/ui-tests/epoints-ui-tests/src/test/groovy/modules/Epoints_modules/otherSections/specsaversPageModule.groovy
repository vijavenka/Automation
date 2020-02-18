package modules.Epoints_modules.otherSections

import geb.Module

/**
 * Created by kbaranowski on 2015-02-05.
 */
class specsaversPageModule extends Module{
    static content = {
        //login panel
        mainHeader{ $('.u-pageTitle') }
        loginForm{ $('.sign-in-form') }
        emailLabel{ loginForm.find('label', for: 'email') }
        emailInputField{ loginForm.find('#email') }
        emailError{ loginForm.find('.valid-error',0) }
        passwordLabel{ loginForm.find('label', for: 'password') }
        passwordInputField{ loginForm.find('#password') }
        passwordError{ loginForm.find('.valid-error',1) }
        authorizationError{ loginForm.find('.alert-danger') }
        signInButton{ loginForm.find('.btn-green') }
        //control panel
        awardPointsTab{ $('.nav-tabs').find('li',0).find('a') }
            selectBranchesLabel{ $('label',0) }
            selectAllCheckbox{ $('span', text: 'Select all') }
            selectAllCheckboxLabel{ $('label',1) }
            pickBranchInput{ $('.selectize-control',0).find('input') }
            pickBranchOption(required: false){ $('.selectize-dropdown-content').find('.option') }
            pickBranchChosenOptionBasic(required: false){ $('.item') }
            pickBranchChosenOptionXButtonBasic(required: false){ $('.remove') }
            reasonForAwardingLabel{ $('label',2) }
            reasonForAwardingDDL{ $('.selectize-control',1) }

        reasonForAwardingInput{ reasonForAwardingDDL.find('input') }
            reasonForAwardingOption(required: false){ $('.selectize-dropdown-content').find('.option') }
            pickBranchChosenSelectedOption{ $('.item').last() }
            pointsToAwardLabel{ $('label',3) }
            pointsToAwardInput{ $('input', name: 'amount') }
            pointsCounter{ $('.spec-points').find('strong') }
            clearButton{ $('.clear') }
            awardPointsButton{ $('.btn-green',0) }
            basicAlert{ $('.valid-error') }
        //import tab
        importTab{ $('.nav-tabs').find('li',1).find('a') }
            bulkUploadInput{ $('.tab-pane').find('input').last() }
            bulkUploadInputVisual{ $('.drop-box') }
            importButton{ $('.btn-green.spinner-button') }
            uploadAlertSuccess{ $('.alert-success') }
            uploadAlerDanger{ $('.alert-danger') }
        signOutButton{ $('.Specsavers-logout') }
        successMessage(required: false){ $('span', text: 'Points awarded successfully.') }
        //confirmation modal
        confirmationModal(required: false){ $('.modal-content') }
        confirmModalHeader(required: false){ confirmationModal.find('.modal-title') }
        confirmModalInfo(required: false){ confirmationModal.find('.modal-body') }
        confirmModalYesButton(required: false){ confirmationModal.find('.btn-primary') }
        confirmModalNoButton(required: false){ confirmationModal.find('.btn-warning') }
        //reporting tab
        reportingTab{  $('.nav-tabs').find('li',2).find('a') }
            startDateLabel{ $('.row').find('label',0) }
            startDateInputField{ $('.DatePicker',0) }
            startDateDatePicker{ $('.btn-noShadow',0) }
            endDateLabel{ $('.row').find('label',1) }
            endDateInputField{ $('.DatePicker',1) }
            endDateDatePicker{ $('.btn-noShadow',1) }
            generateReportButton{ $('.GenerateReport') }
            overviewSectionTab{ $('.btn-group-justified').find('a',0) }
            basicSectionHeader{ $('h3') }
            epointsAwardedSectionTab{ $('.btn-group-justified').find('a',1) }
            epointsRedeemedSectionTab{ $('.btn-group-justified').find('a',2) }
            downloadReportTab{ $('.u-link') }

            calendar{ $('.dropdown-menu.ng-valid-date-disabled') }
            calendarMonthYear{ calendar.find('strong') }
            calendarMonthNext{ calendar.find('.glyphicon-chevron-right') }
            calendarMonthPrevious{ calendar.find('.glyphicon-chevron-left') }
            calendarDayBasic{ calendar.find('tbody').find('td').find('button') }
            calendarTodayButton{ calendar.find('.btn-group.pull-left').find('button',0) }
            calendarClearButton{ calendar.find('.btn-group.pull-left').find('button',1) }
            calendarCloseButton{ calendar.find('.btn-success.pull-right') }
            wrongDateAlert{ $('.text-danger') }

            contentLoader(required: false){ $('.u-loader') }

        //overview section

        //epoints awarded section
            overwiewTable{ $('.ReportOverview-table') }
            overwiewTableRowBasic{ whichRow -> overwiewTable.find('tr', whichRow) }
            overwiewTableRowLabelBasic{ whichRow -> overwiewTableRowBasic(whichRow).find('th') }
            overwiewTableRowDataBasic{ whichRow -> overwiewTableRowBasic(whichRow).find('td') }
        //epoints redeemed section
            tableHeaderRow{ $('.SpecsaversReports-reportsView').find('thead') }
            tableHeaderRowElementBasic{ tableHeaderRow.find('th') }
            tableRowStatic{ $('.SpecsaversReports-reportsView').find('tbody').find('tr') }
            tableRowBasic{ whichRow -> $('.SpecsaversReports-reportsView').find('tbody').find('tr', whichRow) }
            tableRowElementBasic{ whichRow, whichElement -> tableRowBasic(whichRow).find('td',whichElement) }
            epointsSummary{  $('.ReportTotals').find('div',0) }
            valueSummary{  $('.ReportTotals').find('div',1) }
            //pagination
            showingElement{ $('.pagination-summary') }
            outOfElement{ showingElement.find('.pagination-totalItems') }
            rowNumberSelector{ $('.paginationPerPage') }
            rowNumberSelectorOption{ rowNumberSelector.find('button') }
            topPaginationArrowLeft(required: false){ $('.pagination-buttons',0).find('.pagination--prevButton').find('i') }
            topPaginationArrowRight(required: false){ $('.pagination-buttons',0).find('.pagination--nextButton').find('i') }
            bottomPaginationArrowLeft{ $('.pagination-buttons',1).find('.pagination--prevButton').find('i') }
            bottomPaginationArrowRight{ $('.pagination-buttons',1).find('.pagination--nextButton').find('i') }
            bottomPaginationPageNumberBasic{ $('.pagination-numericButton') }
            bottomPaginationPageNumberActiveBasic{ $('.pagination-numericButton.is-active') }



    }

    def enterEmail(email){ waitFor{ emailInputField.isDisplayed() }; emailInputField.value(email) }
    def enterPassword(password){ waitFor{ passwordInputField.isDisplayed() }; passwordInputField.value(password) }
    def enterLoginData(email, password){ enterEmail(email); enterPassword(password)}
    def clickSignInButton(){ waitFor{ signInButton.isDisplayed() }; signInButton.click() }

    def clickSelectAllCheckbox(){ waitFor{ selectAllCheckbox.isDisplayed() }; selectAllCheckbox.click() }
    def clickSelectBranchInputField(){ waitFor{ pickBranchInput.isDisplayed() }; pickBranchInput.click() }
    def clickChosenBranchOption(number){ waitFor{ pickBranchOption[number].isDisplayed() }; pickBranchOption[number].click() }
    def removeChosenBranchOption(number){ waitFor{ pickBranchChosenOptionXButtonBasic[number].isDisplayed() }; pickBranchChosenOptionXButtonBasic[number].click() }
    def clickReasonForAwardingDDL(){ waitFor{ reasonForAwardingDDL.isDisplayed() }; reasonForAwardingDDL.click() }
    def clickReasonForAwardingInput(){ waitFor{ reasonForAwardingInput.isDisplayed() }; reasonForAwardingInput.click() }
    def clickChosenReasonForAwardingOption(number){ waitFor{ reasonForAwardingOption[number].isDisplayed() }; reasonForAwardingOption[number].click() }
    def enterPhraseIntoReasonForAwardinInput(phrase){ waitFor{ reasonForAwardingInput.isDisplayed() }; reasonForAwardingInput.value(phrase) }
    def enterValueIntoPointsToAwardInput(value){ waitFor{ pointsToAwardInput.isDisplayed() }; pointsToAwardInput.value(value) }
    def clickAwardPointsTab(){ waitFor{ awardPointsTab.isDisplayed() }; awardPointsTab.click() }
    def clickAwardPointsButton(){ waitFor{ awardPointsButton.isDisplayed() }; awardPointsButton.click() }

    def clickSignOutButton(){ waitFor{ signOutButton.isDisplayed() }; signOutButton.click() }

    def clickYesButtonInConfirmPointsModalWindow(){ waitFor{ confirmModalYesButton.isDisplayed() }; confirmModalYesButton.click() }
    def clickNoButtonInConfirmPointsModalWindow(){ waitFor{ confirmModalNoButton.isDisplayed() }; confirmModalNoButton.click() }

    //import tab
    def clickImportTab(){ waitFor{ importTab.isDisplayed() }; importTab.click() }
    def clickImportButton(){ waitFor{ importButton.isDisplayed() }; importButton.click() }
    def clickOnImportFileButton(){ waitFor{ bulkUploadInputVisual.isDisplayed() }; bulkUploadInputVisual.click() }

    //reporting tab
    def clickReportingTab(){ waitFor{ reportingTab.isDisplayed() }; reportingTab.click() }
    def openOverviewSection(){ waitFor{ overviewSectionTab.isDisplayed() }; overviewSectionTab.click() }
    def openAwardedSection(){ waitFor{ epointsAwardedSectionTab.isDisplayed() }; epointsAwardedSectionTab.click() }
    def openRedeemedSection(){ waitFor{ epointsRedeemedSectionTab.isDisplayed() }; epointsRedeemedSectionTab.click() }
    def openStartDateCalendar(){ waitFor{ startDateDatePicker.isDisplayed() }; startDateDatePicker.click() }
    def clickCalendarTodayButton(){ waitFor{ calendarTodayButton.isDisplayed() }; calendarTodayButton.click() }
    def clickCalendarClearButton(){ waitFor{ calendarClearButton.isDisplayed() }; calendarClearButton.click() }
    def clickCalendarCloseButton(){ waitFor{ calendarCloseButton.isDisplayed() }; calendarCloseButton.click() }
    def clickCalendarChosenDayButton(number){ waitFor{ calendarDayBasic[number].isDisplayed() }; calendarDayBasic[number].click() }
    def enterStartDate(startDate){ waitFor{ startDateInputField.isDisplayed() }; startDateInputField.value(startDate) }
    def enterEndDate(endDate){ waitFor{ endDateInputField.isDisplayed() }; endDateInputField.value(endDate) }
    def clickGenerateReportButton(){ waitFor{ generateReportButton.isDisplayed() }; generateReportButton.click() }
    def clickOpenLinkOfChosenRow(whichRow){ waitFor{ tableRowElementBasic(whichRow, 6).isDisplayed() }; tableRowElementBasic(whichRow, 6).click()  }
    //pagination
    def clickItemPerPage20(){ waitFor{ rowNumberSelectorOption[0].isDisplayed() }; rowNumberSelectorOption[0].click() }
    def clickItemPerPage50(){ waitFor{ rowNumberSelectorOption[1].isDisplayed() }; rowNumberSelectorOption[1].click() }
    def clickItemPerPage100(){ waitFor{ rowNumberSelectorOption[2].isDisplayed() }; rowNumberSelectorOption[2].click() }
    def clickItemPerPageAll(){ waitFor{ rowNumberSelectorOption[3].isDisplayed() }; rowNumberSelectorOption[3].click() }
    def clickNextPageButton(){ waitFor{ topPaginationArrowRight.isDisplayed() }; topPaginationArrowRight.click() }
    def clickPreviousPageButton(){ waitFor{ topPaginationArrowLeft.isDisplayed() }; topPaginationArrowLeft.click() }
    def clickChosenPageNumber(number){ waitFor{ bottomPaginationPageNumberBasic[number].isDisplayed() }; bottomPaginationPageNumberBasic[number].click() }
    def clickNextBottomPageButton(){ waitFor{ bottomPaginationArrowRight.isDisplayed() }; bottomPaginationArrowRight.click() }
    def clickPreviousBottomPageButton(){ waitFor{ bottomPaginationArrowLeft.isDisplayed() }; bottomPaginationArrowLeft.click() }
}