package com.iat.stepdefs.approvalProcess

import com.iat.Config
import com.iat.domain.EcardsSettings
import com.iat.domain.ecardApprovalReject
import com.iat.domain.ecardAward
import com.iat.pages.DashboardPage
import com.iat.pages.approvalProcess.ApprovalRejectionReasonPage
import com.iat.pages.approvalProcess.ForApprovalPage
import com.iat.pages.userProfile.LoginPage
import com.iat.repository.EcardsSettingsRepository
import com.iat.repository.impl.EcardsApprovalRepositoryImpl
import com.iat.repository.impl.EcardsAwardRepositoryImpl
import com.iat.repository.impl.EcardsSettingsRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.HelpFunctions

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.After

def helpFunctions = new HelpFunctions()

ForApprovalPage forApprovalPage = new ForApprovalPage()
ApprovalRejectionReasonPage approvalRejectionReasonPage = new ApprovalRejectionReasonPage()

String toUsers
String points
String pointsSum
int transactionListSize
int notificationCounterValue
String approvalManager
def awardManager = [:]

def pointsAwardId

After('@rejectAwardForApproval') {
    String response = new EcardsApprovalRepositoryImpl().rejectEcard(new ecardApprovalReject('Automated test rejection after test'), pointsAwardId, approvalManager, "password")
}

Given(~/^User who has award to approve '(.*)' is logged into iat admin '(.*)', '(.*)'$/) { String _awardData, String _username, String _password ->
}

Given(~/^Manager '(.+?)' responsible for approving requests is signed to iat admin$/) { String manager ->
    def username, password
    if (manager.contains('admin')) {
        username = Config.superAdminPassword
        password = Config.superAdminPassword
    } else {
        username = Config.managerLogin
        password = Config.managerPassword
    }

    to LoginPage
    loginPage = page
    helpFunctions.clearCookiesAndLocalStorage()
    loginPage.signInUser(username, password)

    at DashboardPage
    dashboardPage = page
    waitFor { dashboardPage.topNavigation.topNavigationLogo.isDisplayed() }
    waitFor { dashboardPage.leftSidePanelNavigation.navigationPanel.isDisplayed() }

    //Set approval on "for all users"
    EcardsSettingsRepository ecardsSettingsRep = new EcardsSettingsRepositoryImpl()
    EcardsSettings settings = new EcardsSettings("ALL_USERS", "SAME_COMPANY", "true", "ALL", null, "false", null, "false")
    String credentials = approvalManager + ",password"
    ecardsSettingsRep.setEcardsPointsSharing(settings, credentials)
}

And(~/^Some manager '(.*)' already created epoints requests '(.*)' that require approval$/) { String _user, String _awardData ->
    awardManager = _user

    String reasonId = _awardData.split(";")[0]
    String templateId = _awardData.split(";")[1]
    points = _awardData.split(";")[2]
    toUsers = _awardData.split(";")[3]
    int usersAmount = toUsers.split(",").size()
    String usersKey = ''; //= _awardData.split(";")[4]

    for (int i = 0; i < usersAmount; i++)
        usersKey += new UserRepositoryImpl().findByEmail(toUsers.split(',')[i]).uuid + ","
    usersKey = usersKey.replaceFirst(~',$', '')

    int pointsSumInt = points.toInteger() * usersAmount
    pointsSum = pointsSumInt.toString()

    //Manager create epoints request
    def body = new ecardAward(reasonId, templateId, "test", points, [usersKey], [])
    pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, awardManager, "password")

    helpFunctions.refreshPage()
    waitFor { dashboardPage.leftSidePanelNavigation.navigationPanel.isDisplayed() }
    notificationCounterValue = dashboardPage.leftSidePanelNavigation.getNotificationCounterValue()
    assert notificationCounterValue > 0
}

When(~/^He goes to \'Reject transaction\' page for given award$/) { ->
    dashboardPage.navigateToApprovalPageUsingHomePageLinks()
    at ForApprovalPage
    forApprovalPage = page

    transactionListSize = forApprovalPage.getSizeOfTransactionList()

    def rowNumber = forApprovalPage.findFirstIndexOfTransactionWithData(awardManager, toUsers, pointsSum)
    assert rowNumber != null
    forApprovalPage.clickChosenTransactionRejectButton(rowNumber)

    at ApprovalRejectionReasonPage
    approvalRejectionReasonPage = page
}

And(~/^He inputs reason message with length '(.*)', clicks \'Reject\' and confirms$/) { int _messageLength ->
    approvalRejectionReasonPage.rejectTransactionAndConfirm(helpFunctions.returnRandomString(_messageLength))
}

Then(~/^User goes back to approval list$/) { ->
    at ForApprovalPage
    forApprovalPage = page
}

And(~/^Rejected award transaction is no longer on approaval list$/) { ->
    assert forApprovalPage.getSizeOfTransactionList() == transactionListSize - 1
}

And(~/^He inputs reason message with length '(.*)', clicks \'Reject\' and \'No\' afterwards$/) { int _messageLength ->
    approvalRejectionReasonPage.rejectTransactionAndNo(helpFunctions.returnRandomString(_messageLength))
}

Then(~/^User is still on \'Reject transaction\' page$/) { ->
    at ApprovalRejectionReasonPage
    approvalRejectionReasonPage = page
}

And(~/^He clicks \'Cancel\' button$/) { ->
    approvalRejectionReasonPage.cancelRejectTransaction()
}

And(~/^Award transaction is still on approaval list$/) { ->
    assert forApprovalPage.getSizeOfTransactionList() == transactionListSize
}

Then(~/^\'Reject transaction\' page has correct information$/) { ->
    assert approvalRejectionReasonPage.checkIfProperTitle("Reject transaction")
    assert approvalRejectionReasonPage.checkIfProperColumnsTitle("Date", "From", "To", "Reason", "Points", "Amount")
    assert approvalRejectionReasonPage.checkIfRecipientsListProper(toUsers)
    assert approvalRejectionReasonPage.checkIfIdenticalTransactionData(awardManager, toUsers, pointsSum)
}

And(~/^He provides message with length '(.*)' and clicks \'Reject\'$/) { int _messageLength ->
    approvalRejectionReasonPage.addRejectionReasonText(helpFunctions.returnRandomString(_messageLength))
    approvalRejectionReasonPage.clickRejectTransaction()
}

And(~/^Number in \'Approval live icon\' decreased$/) { ->
    dashboardPage = page
    assert dashboardPage.leftSidePanelNavigation.getNotificationCounterValue() == notificationCounterValue - 1
}