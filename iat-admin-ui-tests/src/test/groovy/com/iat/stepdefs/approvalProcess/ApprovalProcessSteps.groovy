package com.iat.stepdefs.approvalProcess

import com.iat.Config
import com.iat.domain.EcardsSettings
import com.iat.domain.ecardApprovalReject
import com.iat.domain.ecardAward
import com.iat.pages.DashboardPage
import com.iat.pages.allocationsHistory.UsersAllocationHistoryPage
import com.iat.pages.approvalProcess.ApprovalRejectionReasonPage
import com.iat.pages.approvalProcess.ForApprovalPage
import com.iat.pages.userProfile.LoginPage
import com.iat.repository.UserRepository
import com.iat.repository.impl.EcardsApprovalRepositoryImpl
import com.iat.repository.impl.EcardsAwardRepositoryImpl
import com.iat.repository.impl.EcardsSettingsRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JsonParserUtils
import geb.Browser

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

def approvalPage = new ForApprovalPage()
def dashboardPage = new DashboardPage()
def helpFunctions = new HelpFunctions()
def usersAllocationHistoryPage = new UsersAllocationHistoryPage()
def browser = new Browser()
def response
JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

def approvalsNumber
def pointsAwardId

Before('@setManagerThresholdToNoneBefore') {
    EcardsSettings settings = new EcardsSettings("SAME_COMPANY", "SAME_COMPANY", "true", "NONE", null, "false", null, "false")
    response = new EcardsSettingsRepositoryImpl().setEcardsPointsSharing(settings, Config.superAdminLogin + "," + Config.superAdminPassword)
}

After('@setManagerThresholdToNoneAfter') {
    EcardsSettings settings = new EcardsSettings("SAME_COMPANY", "SAME_COMPANY", "true", "NONE", null, "false", null, "false")
    response = new EcardsSettingsRepositoryImpl().setEcardsPointsSharing(settings, Config.superAdminLogin + "," + Config.superAdminPassword)
}

After('@setManagerThresholdToAllAfter') {
    EcardsSettings settings = new EcardsSettings("SAME_COMPANY", "SAME_COMPANY", "true", "ALL", null, "false", null, "false")
    response = new EcardsSettingsRepositoryImpl().setEcardsPointsSharing(settings, Config.superAdminLogin + "," + Config.superAdminPassword)
}

Before('@setManagerThresholdTo50Before') {
    EcardsSettings settings = new EcardsSettings("SAME_COMPANY", "SAME_COMPANY", "true", "THRESHOLD", "50", "false", null, "false")
    response = new EcardsSettingsRepositoryImpl().setEcardsPointsSharing(settings, Config.superAdminLogin + "," + Config.superAdminPassword)
}

After('@rejectCreatedAwardForApproval') {
    response = new EcardsApprovalRepositoryImpl().rejectEcard(new ecardApprovalReject('Automated test rejection after test'), pointsAwardId, Config.superAdminLogin, Config.superAdminPassword)
}

//Scenario Outline: Approval page - general view
Given(~/^Manager responsible for approving requests is signed to iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

Given(~/^Some managers already created epoints requests that require approval$/) { ->
    def body = new ecardAward(Config.reason1Id, Config.template1Id, "UI_automated_tests_message2", "55", [new UserRepositoryImpl().findByEmail(Config.associatedUser1).uuid], ["emailwybitnietestowy@gmail.com"])
    pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, Config.managerLogin, Config.managerPassword)
}

When(~/^Manger opens approval UI$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToApprovalPageUsingHomePageLinks()
    at ForApprovalPage
    approvalPage = page
}

Then(~/^He is presented with the pending requests list$/) { ->
    waitFor { approvalPage.approveAwardsHeader.isDisplayed() }
    assert approvalPage.approveAwardsHeader.text() == 'Reward: approve awards'
    assert approvalPage.transactionsToBeApprovedTable.isDisplayed()
}

Then(~/^They are listed in date order with oldest first$/) { ->
    for (int i = 0; i < approvalPage.transactionsToBeApprovedRowBasic.size() - 1; i++) {
        assert (helpFunctions.parseDateFromStringToMiliseconds(approvalPage.returnForApprovalTableLocator("Date", i + 1).text(), "dd MMM yyyy") >= helpFunctions.parseDateFromStringToMiliseconds(approvalPage.returnForApprovalTableLocator("Date", i).text(), "dd MMM yyyy"))
    }
}

Then(~/^They have all of the necessary data displayed '(.+?)'$/) { String columnNames ->
    def columns = columnNames.split(",")
    for (int i = 0; i < columns.length; i++) {
        assert approvalPage.transactionsToBeApprovedHeadersRowElement[i].text() == columns[i]
    }
}

Then(~/^Each row has an approval\/decline option available$/) { ->
    for (int i = 0; i < approvalPage.transactionsToBeApprovedRowBasic.size(); i++) {
        assert approvalPage.returnForApprovalTableLocator('Reject', i).isDisplayed()
        assert approvalPage.returnForApprovalTableLocator('Approve', i).isDisplayed()
    }
}

Then(~/^Live icon with number of awards for approve is displayed$/) { ->
    waitFor { approvalPage.transactionsNumberToBeApprovedInfoBasic[1].isDisplayed() }
    assert Integer.parseInt(approvalPage.transactionsNumberToBeApprovedInfoBasic[1].text()) == approvalPage.transactionsToBeApprovedRowBasic.size()
}

//Scenario Outline: Approval page - approve button clicked
When(~/^He clicks "approve" button next to one of the requests$/) { ->
    approvalPage.clickChosenTransactionApproveButton(approvalPage.approveConfirmationModalApproveButton.size() - 1)
}

Then(~/^Approve confirmation modal is displayed with "Approve" and "Cancel" options$/) { ->
    waitFor { approvalPage.approveConfirmationModal.isDisplayed() }
    assert approvalPage.approveConfirmationModalInfo.text() == 'Are you sure you want to approve this transaction?'
    assert approvalPage.approveConfirmationModalApproveButton.isDisplayed()
    assert approvalPage.approveConfirmationModalCancelButton.isDisplayed()
}

//Scenario Outline: Approval page - approve button clicked - confirmation modal cancel button
When(~/^He click "cancel" button on approval confirmation popup$/) { ->
    approvalsNumber = approvalPage.transactionsToBeApprovedRowBasic.size()
    approvalPage.clickApproveConfirmationModalNoButton()
}

Then(~/^Approval confirmation will be closed$/) { ->
    waitFor { !approvalPage.approveConfirmationModal.isDisplayed() }
}

Then(~/^Award for approve row is still visible in approval table$/) { ->
    assert approvalsNumber == approvalPage.transactionsToBeApprovedRowBasic.size()
}

//Approval page - approve button clicked - confirmation modal approve button
When(~/^He click "approve" button on approval confirmation popup$/) { ->
    approvalsNumber = approvalPage.transactionsToBeApprovedRowBasic.size()
    approvalPage.clickApproveConfirmationModalApproveButton()
}

When(~/^Approval confirmation information will be displayed$/) { ->
    waitFor { approvalPage.topNavigation.alertInfo.isDisplayed() }
    assert approvalPage.topNavigation.alertInfo.text() == "Transaction $pointsAwardId approved"
}

Then(~/^Award for approve row is not visible in approval table$/) { ->
    assert approvalsNumber - 1 == approvalPage.transactionsToBeApprovedRowBasic.size()
}

//Scenario Outline: Approval page - decline button clicked
When(~/^He clicks "decline" button next to one of the requests$/) { ->
    approvalPage.clickChosenTransactionRejectButton(0)
}

Then(~/^User is re-directed to reject transaction screen$/) { ->
    at ApprovalRejectionReasonPage
}

//Scenario Outline: Approval page - award more than one user, users modal, points per user displayed
Given(~/^Some managers already created multiple epoints requests that require approval$/) { ->
    UserRepository userRepository = new UserRepositoryImpl()
    def body = new ecardAward(Config.reason1Id, Config.template1Id, "UI_automated_tests_message2", "75", [userRepository.findByEmail(Config.associatedUser1).uuid, userRepository.findByEmail(Config.associatedUser2).uuid, userRepository.findByEmail(Config.associatedUser3).uuid, userRepository.findByEmail(Config.associatedUser4).uuid], ["emailwybitnietestowy@gmail.com"])
    pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, Config.managerLogin, Config.managerPassword)
}

When(~/^User click "X users" button in "To" column$/) { ->
    approvalPage.clickXUsersButtonInSelectedRow(approvalPage.approveConfirmationModalApproveButton.size() - 1)
}

Then(~/^Modal with number of users will be displayed$/) { ->
    waitFor { approvalPage.usersToBeAwardedModal.isDisplayed() }
    assert approvalPage.usersToBeAwardeModalSingleUser.size() == 4
    assert approvalPage.usersToBeAwardedModalHeader.text() == 'Users'
}

Then(~/^User can close modal contains users to be awarded$/) { ->
    approvalPage.closeUsersToBeAwardedModal()
    waitFor { !approvalPage.usersToBeAwardedModal.isDisplayed() }
}

Then(~/^Points and pounds value are correctly calculated per single user$/) { ->
    assert approvalPage.returnForApprovalTableLocator('Points', approvalPage.approveConfirmationModalApproveButton.size() - 1).text() == '300\n' + '4 x 75'
    assert approvalPage.returnForApprovalTableLocator('Amount', approvalPage.approveConfirmationModalApproveButton.size() - 1).text() == '£1.50\n' + '4 x £0.375'
}

//Scenario Outline: Approval page - approve and manager message check
Given(~/^Manager has no approval notifications - '(.+?)'$/) { String managerView ->
    def username, password
    if (managerView.contains('admin')) {
        username = Config.superAdminLogin
        password = Config.superAdminPassword
    } else {
        username = Config.managerLogin
        password = Config.managerPassword
    }

    response = new EcardsApprovalRepositoryImpl().getAllAdminNotifications(username, password)
    String[] notificationIdsJsons = response.replace('[', '').replace(']', '').replace('},{', '}&{').split('&')
    String notificationIdsList = ''
    if (response.contains('id')) {
        for (int i = 0; i < notificationIdsJsons.size(); i++) {
            if (i == 0)
                notificationIdsList = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(notificationIdsJsons[i]), 'id')
            else
                notificationIdsList = notificationIdsList + "," + jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(notificationIdsJsons[i]), 'id')
        }
        response = new EcardsApprovalRepositoryImpl().markNotificationsAsRead("[" + notificationIdsList + "]", username, password)
    }
}

Given(~/^One points award is already approved by admin responsible for approving$/) { ->
    response = new EcardsApprovalRepositoryImpl().approveEcard(pointsAwardId, Config.superAdminLogin, Config.superAdminPassword)
}

When(~/^Manager login into his account$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.managerLogin, Config.managerPassword)
}

Then(~/^He is notified that his ecard was approved$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.topNavigation.expandMessagesDDL()
    waitFor { dashboardPage.topNavigation.messagesDDLCounter.isDisplayed() }
    assert dashboardPage.topNavigation.messagesDDLCounter.text() == 'YOU HAVE 1 MESSAGE.'
    assert dashboardPage.topNavigation.messagesDDLApprovedIcon.isDisplayed()
    assert dashboardPage.topNavigation.messagesDDLApprovedLabel.text() == 'Your transaction has been approved by manager'
    assert dashboardPage.topNavigation.messagesDDLApprovedTime.text() == 'a few seconds ago'
}

Then(~/^Status of proper points award was changed to approved at user allocation history page$/) { ->
    dashboardPage.navigateToUsersHistoryAllocationPage()
    at UsersAllocationHistoryPage
    usersAllocationHistoryPage = page
    //TODO maybe more info needed here to validate if first row is exactly changed already by us
    waitFor { (usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', 0).text() == 'approved') }
}

//Scenario Outline: Approval page - messages widget - manager perspective - approve notification link
When(~/^He click approved elements notification$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.topNavigation.expandMessagesDDL()
    dashboardPage.topNavigation.clickApprovedNotification()
}

Then(~/^He is re-directed to user allocation history$/) { ->
    at UsersAllocationHistoryPage
    usersAllocationHistoryPage = page
}

Then(~/^It's filtered down by the "who sent" column$/) { ->
    waitFor { usersAllocationHistoryPage.searchInputField.text().contains(Config.managerName + Config.managerLastName) }
    assert usersAllocationHistoryPage.filtersInDDL.text().contains('Sender')
}

Then(~/^Only the ecards that this manager sent are available there$/) { ->
    for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size(); i++)
        assert usersAllocationHistoryPage.returnAllocationTableLocator('Send from', i).text().contains(Config.managerName) && usersAllocationHistoryPage.returnAllocationTableLocator('Send from', i).text().contains(Config.managerLastName)
}

//Scenario Outline: Approval page - reject and manager message check
Then(~/^He is notified that his ecard was rejected$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.topNavigation.expandMessagesDDL()
    assert dashboardPage.topNavigation.messagesDDLCounter.text() == 'YOU HAVE 1 MESSAGE.'
    assert dashboardPage.topNavigation.messagesDDLRejectedIcon.isDisplayed()
    assert dashboardPage.topNavigation.messagesDDLRejectedLabel.text() == 'Your transaction has been rejected by manager'
    assert dashboardPage.topNavigation.messagesDDLRejectedTime.text() == 'a few seconds ago'
}

Then(~/^Status of proper points award was changed to rejected at user allocation history page$/) { ->
    dashboardPage.navigateToUsersHistoryAllocationPage()
    at UsersAllocationHistoryPage
    usersAllocationHistoryPage = page
    //TODO maybe more info needed here to validate if first row is exactly changed already by us
    waitFor { (usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', 0).text() == 'rejected') }
}

//Scenario Outline: Approval page - messages widget - manager perspective - reject notification link
Given(~/^One points award is already rejected by admin responsible for approving$/) { ->
    message = "ui_automated_tests_reject_message_" + helpFunctions.returnCurrentEpochTime()
    response = new EcardsApprovalRepositoryImpl().rejectEcard(new ecardApprovalReject(message), pointsAwardId, Config.superAdminLogin, Config.superAdminPassword)
}

When(~/^He click rejected elements notification$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.topNavigation.expandMessagesDDL()
    dashboardPage.topNavigation.clickRejectedNotification()
}

//Scenario Outline: Approval page - messages widget - admin perspective - pending notification link
Given(~/^He is notified that some allocated points waiting for approval$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.topNavigation.expandMessagesDDL()
    assert dashboardPage.topNavigation.messagesDDLCounter.text() == 'YOU HAVE 1 MESSAGE.'
    assert dashboardPage.topNavigation.messagesDDLPendingIcon.isDisplayed()
    assert dashboardPage.topNavigation.messagesDDLPendingLabel.text() == 'You have 1 pending transaction to approve'
    assert dashboardPage.topNavigation.messagesDDLPendingTime.text() == 'a few seconds ago'
}

When(~/^He clicks on one of the pending requests$/) { ->
    dashboardPage.topNavigation.clickPendingNotification()
}

Then(~/^He is re-directed to approval UI$/) { ->
    at ForApprovalPage
    approvalPage = page
}

Then(~/^Same number of pending approval request is displayed there as on live icon on lefts side menu$/) { ->
    waitFor {
        approvalPage.transactionsToBeApprovedRowBasic.size() == Integer.parseInt(approvalPage.leftSidePanelNavigation.notificationsCounter.text())
    }
}

//Scenario Outline: Approval page - approval page availability for manager without approval permissions
Given(~/^Manager with approval process config set to "no" is signed to iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.managerLogin, Config.managerPassword)
}

When(~/^User expand "Points allocation" menu option$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
}

Then(~/^"For approval" option is not available$/) { ->
    assert !dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.forApproval).isDisplayed()
}

Then(~/^Status column is not displayed in users allocation history$/) { ->
    dashboardPage.navigateToUsersHistoryAllocationPage()
    at UsersAllocationHistoryPage
    usersAllocationHistoryPage = page
    waitFor { usersAllocationHistoryPage.allocationsTable.isDisplayed() }
    assert !usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', 0).isDisplayed()
}

Then(~/^For approval page is not available by direct link$/) { ->
    to ForApprovalPage
    approvalPage = page
    waitFor { approvalPage.approvalProcesDisabledInfo.isDisplayed() }
    assert approvalPage.approvalProcesDisabledInfo.text().equals('Approval process is disabled')
}