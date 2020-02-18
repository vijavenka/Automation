package com.iat.pages.modules

import geb.Module

class ReasonWizardModule extends Module {

    static content = {
        reasonsListSection(wait: true) { $("div[ng-if*='HRConfigService.reasons']") }
        addReasonButton(wait: true) { reasonsListSection.find('.ReasonList-header').find('.btn-primary') }
        reasonsListLabelRowFirstBasic(wait: true) { reasonsListSection.find('thead').find('tr', 0).find('th') }
        reasonsListLabelRowSecondBasic(wait: true) { reasonsListSection.find('thead').find('tr', 1).find('th') }
        reasonsListDataRowBasic(wait: true, required: false) { reasonsListSection.find('tbody').find('tr') }

        deletePopup(wait: true, required: false) { $('md-dialog') }
        deletePopupQustion(wait: true) { deletePopup.find('h2') }
        deletePopupCancelButton(wait: true) { deletePopup.find('button', 0) }
        deletePopupDeleteButton(wait: true) { deletePopup.find('button', 1) }
    }

    def clickCancelButtonOnDeleteReasonConfirmationPopup() {
        waitFor { deletePopupCancelButton.isDisplayed() }; deletePopupCancelButton.click()
    }

    def clickDeleteButtonOnDeleteReasonConfirmationPopup() {
        waitFor { deletePopupDeleteButton.isDisplayed() }; deletePopupDeleteButton.click()
    }

    def clickDeleteReasonButtonOfChosenReason(int reasonNumber) {
        returnReasonTableLocator('action', reasonNumber).click()
    }

    def returnReasonTableLocator(String column, int position) {
        switch (column) {
            case "reasonName":
                waitFor { reasonsListDataRowBasic[position].find('td', 0).isDisplayed() }
                return reasonsListDataRowBasic[position].find('td', 0)
                break
            case "managerToUserMin":
                waitFor { reasonsListDataRowBasic[position].find('td', 1).isDisplayed() }
                return reasonsListDataRowBasic[position].find('td', 1)
                break
            case "managerToUserMax":
                waitFor { reasonsListDataRowBasic[position].find('td', 2).isDisplayed() }
                return reasonsListDataRowBasic[position].find('td', 2)
                break
            case "userToUserMin":
                waitFor { reasonsListDataRowBasic[position].find('td', 3).isDisplayed() }
                return reasonsListDataRowBasic[position].find('td', 3)
                break
            case "userToUserMax":
                waitFor { reasonsListDataRowBasic[position].find('td', 4).isDisplayed() }
                return reasonsListDataRowBasic[position].find('td', 4)
                break
            case "action":
                //waitFor { reasonsListDataRowBasic[position].find('td', 5).isDisplayed() }
                return reasonsListDataRowBasic[position].find('td', 5).find('.btn')
                break
        }
    }

    def findReasonPositionOnListByName(String name) {
        reasonsListSection.find('tbody > tr').findIndexOf { it.find("td", 0).text() == name }
    }

    def reasonListRowsAmount() {
        reasonsListSection.find("tbody").children().size()
    }

    def checkIfReasonOnList(def reasonSettings) {
        //reasonSettings:
        //[reasonName: String, globals: [manager2User: bool, user2User: bool], reasonManager2User: String[2], reasonUser2User: String[2]]
        waitFor { reasonsListDataRowBasic[0].isDisplayed() }
        def row_index = findReasonPositionOnListByName(reasonSettings.reasonName)
        def result = row_index >= 0
        if (!result) return false

        if (reasonSettings.globals.manager2User)
            result = result && returnReasonTableLocator('managerToUserMin', row_index).text().replaceAll(",", "") == reasonSettings.reasonManager2User[0] + '*' &&
                    returnReasonTableLocator('managerToUserMax', row_index).text().replaceAll(",", "") == reasonSettings.reasonManager2User[1] + '*'
        else
            result = result && returnReasonTableLocator('managerToUserMin', row_index).text().replaceAll(",", "") == reasonSettings.reasonManager2User[0] &&
                    returnReasonTableLocator('managerToUserMax', row_index).text().replaceAll(",", "") == reasonSettings.reasonManager2User[1]

        if (reasonSettings.globals.user2User)
            result = result && returnReasonTableLocator('userToUserMin', row_index).text().replaceAll(",", "") == reasonSettings.reasonUser2User[0] + '*' &&
                    returnReasonTableLocator('userToUserMax', row_index).text().replaceAll(",", "") == reasonSettings.reasonUser2User[1] + '*'
        else
            result = result && returnReasonTableLocator('userToUserMin', row_index).text().replaceAll(",", "") == reasonSettings.reasonUser2User[0] &&
                    returnReasonTableLocator('userToUserMax', row_index).text().replaceAll(",", "") == reasonSettings.reasonUser2User[1]

        result
    }

    def deleteReasonFromListByName(String name) {
        int row_index = findReasonPositionOnListByName(name)
        clickDeleteReasonButtonOfChosenReason(row_index)
        sleep(500) //popup needs to be steady
        clickDeleteButtonOnDeleteReasonConfirmationPopup()
    }

}