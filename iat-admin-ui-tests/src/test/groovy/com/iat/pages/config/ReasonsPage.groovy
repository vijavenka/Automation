package com.iat.pages.config

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class ReasonsPage extends Page {

    def helpFunction = new HelpFunctions()

    static url = '/hr/config/reasons'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("reasons") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        reasonsConfigSection(wait: true) { $('.row.ui-section', 0) }
        editReasonConfigButton(wait: true) { reasonsConfigSection.find('.ReasonList-header').find('.btn-primary') }
        globalSettingsMinColumnLabel(wait: true) { reasonsConfigSection.find('thead').find('th', 1) }
        globalSettingsMaxColumnLabel(wait: true) { reasonsConfigSection.find('thead').find('th', 2) }
        globalSettingsManagerToUserMinValue(wait: true) {
            reasonsConfigSection.find('tbody').find('tr', 0).find('td', 1)
        }
        globalSettingsManagerToUserMaxValue(wait: true) {
            reasonsConfigSection.find('tbody').find('tr', 0).find('td', 2)
        }
        globalSettingsUserToUserMinValue(wait: true) { reasonsConfigSection.find('tbody').find('tr', 1).find('td', 1) }
        globalSettingsUserToUserMaxValue(wait: true) { reasonsConfigSection.find('tbody').find('tr', 1).find('td', 2) }

        reasonsListSection(wait: true) { $('.row.ui-section', 1) }
        addReasonButton(wait: true) { reasonsListSection.find('.ReasonList-header').find('.btn-primary') }
        reasonsListLabelRowFirstBasic(wait: true) { reasonsListSection.find('thead').find('tr', 0).find('th') }
        reasonsListLabelRowSecondBasic(wait: true) { reasonsListSection.find('thead').find('tr', 1).find('th') }
        reasonsListDataRowBasic(wait: true, required: false) { reasonsListSection.find('tbody').find('tr') }

        deletePopup(wait: true, required: false) { $('md-dialog') }
        deletePopupQustion(wait: true) { deletePopup.find('h2') }
        deletePopupCancelButton(wait: true) { deletePopup.find('button', 0) }
        deletePopupDeleteButton(wait: true) { deletePopup.find('button', 1) }
    }

    def clickEditConfigButton() {
        waitFor { editReasonConfigButton.isDisplayed() }; editReasonConfigButton.click()
    }

    def clickAddReasonButton() {
        waitFor { addReasonButton.isDisplayed() }; addReasonButton.click()
    }

    def clickCancelButtonOnDeleteReasonConfirmationPopup() {
        waitFor { deletePopupCancelButton.isDisplayed() }; deletePopupCancelButton.click()
    }

    def clickDeleteButtonOnDeleteReasonConfirmationPopup() {
        waitFor { deletePopupDeleteButton.isDisplayed() }; deletePopupDeleteButton.click()
    }

    def clickDeleteReasonButtonOfChosenReason(int reasonNumber) {
        helpFunction.waitSomeTime(Config.waitMedium)
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

    def checkIfProperGlobalReasons(def globalConfigSet) {
        //globalConfigSet: String[4], m->u min,/m->u max,/u->u min,/u->u max
        globalSettingsManagerToUserMinValue.text().replace(',', '') == globalConfigSet[0].toString()
        globalSettingsManagerToUserMaxValue.text().replace(',', '') == globalConfigSet[1].toString()
        globalSettingsUserToUserMinValue.text().replace(',', '') == globalConfigSet[2].toString()
        globalSettingsUserToUserMaxValue.text().replace(',', '') == globalConfigSet[3].toString()
    }
}