package com.iat.pages

import static org.junit.Assert.assertTrue

class CouponUsagePage extends AbstractPage {

    static url = '/coupon-usage'
    static at = {
        waitFor() {
            getTitle().contains('Coupon usage | epoints')
        }
    }
    String HEADER_TEXT = 'Coupon usage'
    String PAGE_INFO_TEXT = 'Earn epoints for every coupon redeemed in your store.\n' +
            'epoints over the value of 2,000 (£10) can be exchanged for cash.'
    String EPOINTS_REWARDED_INFO_TEXT = 'Total epoints rewarded via coupon incentive'
    String EPOINTS_AVAILABLE_INFO_TEXT = 'Total epoints available to spend or convert to cash'
    String EPOINTS_CONVERTED_INFO_TEXT = 'Total epoints converted'
    String SORT_CODE_LABEL = 'Sort code*'
    String ACCOUNT_NUMBER_LABEL = 'Account number*'
    String ACCOUNT_HOLDERS_NAME_LABEL = 'Account holder\' s name*'
    String USERS_REFERENCE_LABEL = 'User\'s reference*'
    String CASH_OUT_VALUE_LABEL = 'Cash ou value*'

    static content = {

        //top section
        pageHeader(wait: true) { $('h1') }
        pageInfoText(wait: true) { $('p', 1) }

        couponsUsageSummary(wait: true) { $('.CouponUsage-summary') }
        totalEpointRewardedLabel(wait: true) { couponsUsageSummary.find('small', 0) }
        totalEpointsRewardedValue(wait: true) { couponsUsageSummary.find('strong', 0) }
        totalEpointAvailableLabel(wait: true) { couponsUsageSummary.find('small', 1) }
        totalEpointsAvailableValue(wait: true) { couponsUsageSummary.find('strong', 1) }
        totalEpointConvertedLabel(wait: true) { couponsUsageSummary.find('small', 2) }
        totalEpointsConvertedValue(wait: true) { couponsUsageSummary.find('strong', 2) }

        //cash out form
        cashOutButton(wait: true) {}
        cashOutButtonCashValue(wait: true) {}

        sortCodeLabel(wait: true) {}
        sortCodeInputField(wait: true, required: false) {}
        accountNumberLabel(wait: true, required: false) {}
        accountNumberInputField(wait: true, required: false) {}
        accountHoldersNameLabel(wait: true, required: false) {}
        accountsHoldersNameInputField(wait: true, required: false) {}
        userReferenceLabel(wait: true, required: false) {}
        userReferenceInputField(wait: true, required: false) {}
        cashOutValueLabel(wait: true, required: false) {}
        maximumAvailableCheckbox(wait: true, required: false) {}
        cashOutValueInputField(wait: true, required: false) {}
        cashOutPoundsValueCalculated(wait: true, required: false) {}
        cancelButton(wait: true, required: false) {}
        submitButton(wait: true, required: false) {}

        //table breakdown
        tableDataRow(wait: true) {}
        tableColumnsLabelsRow(wait: true) {}
        tableColumnsDataRow(wait: true, required: false) {}

        paginatinNextButton(wait: true, required: false) {}
        paginatinPreviousButton(wait: true, required: false) {}
        paginatinPageNumberButtonBasic(wait: true, required: false) {}
    }

    def checkIfPageHeaderIsDisplayed() {
        assertTrue("Page header is not displayed or has incorrect text", waitFor {
            pageHeader.isDisplayed() && pageHeader.text().equals(HEADER_TEXT)
        })
    }

    def checkPageInfoTextIsDisplayed() {
        assertTrue("Page info text is not displayed or has incorrect text", waitFor {
            pageInfoText.isDisplayed() && pageInfoText.text().equals(PAGE_INFO_TEXT)
        })
    }

    def checkEpointsRewardedValueIsAsExpected(pointsValue) {
        assertTrue("Epoints rewarded value is not displayed or not as expected", waitFor {
            totalEpointsRewardedValue.isDisplayed() && totalEpointsRewardedValue.text().equals(pointsValue)
        })
    }

    def checkEpointsRewardedLabelIsDisplayed() {
        assertTrue("Epoints rewarded label is not displayed or has incorrect text", waitFor {
            totalEpointRewardedLabel.isDisplayed() && totalEpointRewardedLabel.text().equals(EPOINTS_REWARDED_INFO_TEXT)
        })
    }

    def checkEpointsAvailableValueIsAsExpected(pointsValue) {
        assertTrue("Epoints available value is not displayed or not as expected", waitFor {
            totalEpointsAvailableValue.isDisplayed() && totalEpointsAvailableValue.text().equals(pointsValue)
        })
    }

    def checkEpointsAvailableLabelIsDisplayed() {
        assertTrue("Epoints available label is not displayed or has incorrect text", waitFor {
            totalEpointAvailableLabel.isDisplayed() && totalEpointAvailableLabel.text().equals(EPOINTS_AVAILABLE_INFO_TEXT)
        })
    }

    def checkEpointsConvertedValueIsAsExpected(pointsValue) {
        assertTrue("Epoints converted value is not displayed or not as expected", waitFor {
            totalEpointsConvertedValue.isDisplayed() && totalEpointsConvertedValue.text().equals(pointsValue)
        })
    }

    def checkEpointsConvertedLabelIsDisplayed() {
        assertTrue("Epoints converted label is not displayed or has incorrect text", waitFor {
            totalEpointConvertedLabel.isDisplayed() && totalEpointConvertedLabel.text().equals(EPOINTS_CONVERTED_INFO_TEXT)
        })
    }

    def checkIfCashOutButtonIsDisplayed() {
        waitFor { cashOutButton.isDisplayed() }
    }

    def clickCashOutButton() {
        waitFor { cashOutButton.isDisplayed() }; cashOutButton.click()
    }

    def checkCashButtonPoundsValueIsAsExpected(cashValue) {
        assertTrue("Cash button value is not displayed or not as expected", waitFor {
            cashOutButtonCashValue.isDisplayed() && cashOutButtonCashValue.text().equals(cashValue)
        })
    }

    def checkIfAllCashOutFormElementsAreAvailable() {
        assertTrue("Sort code field is not available", waitFor {
            sortCodeInputField.isDisplayed() && sortCodeLabel.text().equals(SORT_CODE_LABEL)
        })
        assertTrue("Account number field is not available", waitFor {
            accountNumberInputField.isDisplayed() && accountNumberLabel.text().equals(ACCOUNT_NUMBER_LABEL)
        })
        assertTrue("Account holder's name field is not available", waitFor {
            accountsHoldersNameInputField.isDisplayed() && accountHoldersNameLabel.text().equals(ACCOUNT_HOLDERS_NAME_LABEL)
        })
        assertTrue("User's reference name field is not available", waitFor {
            userReferenceInputField.isDisplayed() && userReferenceLabel.text().equals(USERS_REFERENCE_LABEL)
        })
        assertTrue("Cash out value label is not availabl,", waitFor {
            cashOutValueLabel.text().equals(CASH_OUT_VALUE_LABEL)
        })
        assertTrue("Maximum available checkbox is not available", waitFor {
            maximumAvailableCheckbox.isDisplayed()
        })
        assertTrue("Cash out input field is not available", waitFor {
            cashOutValueInputField.isDisplayed()
        })
        assertTrue("Pounds calculated field is not available", waitFor {
            cashOutPoundsValueCalculated.isDisplayed()
        })
        assertTrue("Cancel button is not available", waitFor {
            cancelButton.isDisplayed()
        })
        assertTrue("Submit button is not available", waitFor {
            submitButton.isDisplayed()
        })
    }

    def enterSortCode(sortCode) {
        waitFor { sortCodeInputField.isDisplayed() }; sortCodeInputField.value(sortCode)
    }

    def enterAccountNumber(accountNumber) {
        waitFor { accountNumberInputField.isDisplayed() }; accountNumberInputField.value(accountNumberInputField)
    }

    def enterAccountHoldersName(accountsHolderName) {
        waitFor { accountsHoldersNameInputField.isDisplayed() }; accountsHoldersNameInputField.value(accountsHolderName)
    }

    def enterUsersReference(usersReference) {
        waitFor { userReferenceInputField.isDisplayed() }; userReferenceInputField.value(usersReference)
    }

    def enterCashValue(cashValue) {
        waitFor { cashOutValueInputField.isDisplayed() }; cashOutValueInputField.value(cashValue)
    }

    def checkPoundsValuIsProperlyCalculated(pointsValue) {
        assertTrue("Pounds value is incorrectly calculated",
                Float.parseFloat(cashOutPoundsValueCalculated.text()) == pointsValue / 200
        )
    }

    def clickCancelButton() {
        waitFor { cancelButton.isDisplayed() }; cancelButton.click()
    }

    def clickSubmitButton() {
        waitFor { submitButton.isDisplayed() }; submitButton.click()
    }

    def checkCorrectnessOfColumNames(columnNames) {
        names = columnNames.split(";")
        for (int i = 0; i < names.size(); i++) {
            assertTrue("Column name is incorrect", tableColumnsLabelsRow[i].text().equals(names[i]))
        }
    }

    def returnNeededTableDataLocator(String type, int position) {
        switch (type) {
            case "Date":
                waitFor { tableColumnsDataRow[position].find('.info-value', 0).isDisplayed() }
                return tableColumnsDataRow[position].find('.info-value', 0)
                break
            case "Store Name":
                waitFor { tableColumnsDataRow[position].find('.info-value', 1).isDisplayed() }
                return tableColumnsDataRow[position].find('.info-value', 1)
                break
            case "Coupon Name":
                waitFor { tableColumnsDataRow[position].find('.info-value', 2).isDisplayed() }
                return tableColumnsDataRow[position].find('.info-value', 2)
                break
            case "Coupon Saving":
                waitFor { tableColumnsDataRow[position].find('.info-value', 3).isDisplayed() }
                return tableColumnsDataRow[position].find('.info-value', 3)
                break
            case "epoints Earned":
                waitFor { tableColumnsDataRow[position].find('.info-value', 4).isDisplayed() }
                return tableColumnsDataRow[position].find('.info-value', 4)
                break
        }
    }

    def clickPaginationNextButton() {
        waitFor { paginatinNextButton.isDisplayed() }; paginatinNextButton.click()
    }

    def clickPaginationPreviousButton() {
        waitFor { paginatinPreviousButton.isDisplayed() }; paginatinPreviousButton.click()
    }

    def clickPaginationChosenPageButton(pageNumber) {
        waitFor { paginatinPageNumberButtonBasic[pageNumber].isDisplayed() }
        paginatinPageNumberButtonBasic[pageNumber].click()
    }
}