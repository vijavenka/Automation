package com.iat.stepdefs.userProfile

import com.iat.Config
import com.iat.domain.EcardsSettings
import com.iat.domain.ecardAward
import com.iat.pages.DashboardPage
import com.iat.pages.config.WizardPage
import com.iat.pages.userProfile.LoginPage
import com.iat.repository.EcardsSettingsRepository
import com.iat.repository.impl.EcardsAwardRepositoryImpl
import com.iat.repository.impl.EcardsSettingsRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*

def helpFunctions = new HelpFunctions()

String roleType
boolean approvalOptionEnabled = false
def browser = new Browser()
def dashboardPage = new DashboardPage()
def loginPage = new LoginPage()

Given(~/^(.+?) is logged in to IAT Admin platform$/) { String admin ->
    def username, password
    if (admin.equalsIgnoreCase('superadmin')) {
        username = Config.superAdminLogin
        password = Config.superAdminPassword
    } else if (admin.equalsIgnoreCase('admin')) {
        username = Config.adminLogin
        password = Config.adminPassword
    } else if (admin.equalsIgnoreCase('wizard admin')) {
        username = Config.wizardSuperAdminLogin
        password = Config.wizardSuperAdminPassword
    } else if (admin.equalsIgnoreCase('manager')) {
        username = Config.managerLogin
        password = Config.managerPassword
    } else if (admin.equalsIgnoreCase('uberadmin')) {
        username = Config.uberAdminLogin
        password = Config.uberAdminPassword
    }

    to LoginPage
    loginPage = page

    waitFor { !loginPage.loader.isDisplayed() }

    loginPage.enterUsernameOnLoginForm(username)
    loginPage.enterPasswordOnLoginForm(password)
    loginPage.clickOnSignInButton()

    if (username.contains('wizard')) {
        at WizardPage
        wizardPage = page
    } else {
        at DashboardPage
        dashboardPage = page
    }
}

When(~/^He has role '(.*)' in the system$/) { String _roleType ->
    roleType = _roleType
}

boolean checkIfImpossibleToReachSiteByUrl(String urlPath, String wizardFinished, DashboardPage dashboardPage) {
    new HelpFunctions().waitSomeTime(Config.waitMedium)
    def browser = new Browser()

    browser.go(Config.getIatAdminUrl() + '/' + urlPath)
    waitFor { dashboardPage.topNavigation.warningInfo.isDisplayed() }
    if (wizardFinished == 'true') {
        waitFor {
            (dashboardPage.topNavigation.warningInfo.text() == 'You don\'t have permission to see requested page.')
        }
        waitFor { browser.currentUrl.contains(DashboardPage.url) }
    } else {
        waitFor {
            (dashboardPage.topNavigation.warningInfo.text() == 'Wizard is not finished. Redirecting to wizard page.')
        }
        waitFor { browser.currentUrl.contains(WizardPage.url) }
    }
    waitFor(10) { !dashboardPage.topNavigation.warningInfo.isDisplayed() }
    return true
}

def sendEcardToApproval() {
    //TODO: fill in proper data
    String userEmail = ""
    String usersKey = new UserRepositoryImpl().findByEmail(userEmail).getUuid()
    String awardManager = Config.adminLogin
    String awardManagerPassword = Config.adminPassword

    //Set approval on "for all users"
    EcardsSettingsRepository ecardsSettingsRep = new EcardsSettingsRepositoryImpl()
    EcardsSettings settings = new EcardsSettings("SAME_COMPANY", "SAME_COMPANY", "true", "ALL", null, "false", null, "false")
    String credentials = awardManager + "," + awardManagerPassword
    ecardsSettingsRep.setEcardsPointsSharing(settings, credentials)

    //Manager create epoints request
    def body = new ecardAward("reasonId", "templateId", "test", "1", [usersKey], [])
    pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, awardManager, awardManagerPassword)

    helpFunctions.refreshPage()
    waitFor { dashboardPage.leftSidePanelNavigation.navigationPanel.isDisplayed() }
}

Then(~/^User can see only appropriate menu elements according to his role accesses '(.*)', '(.*)'$/) { String accesses, String wizardFinished ->
    def accessesList = accesses.split(";")

    if (accessesList.any { it == "Reporting" })
        dashboardPage.navigateToReportingPage()
    else {
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.statistics).isDisplayed() == false
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathStatistics, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "EcardUsageBreakdown" })
        dashboardPage.navigateToEcardUsageBreakdownPage()
    else {
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pathEcardUsageBreakdown).isDisplayed() == false
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathEcardUsageBreakdown, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "Config" }) {
        dashboardPage.navigateToReasonsConfig()
        dashboardPage.navigateToEPointsSharingConfing()
        dashboardPage.navigateToECardTemplateConfig()
    } else {
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.config).isDisplayed() == false
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.configPointsSharing).isDisplayed() == false
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.configReasons).isDisplayed() == false
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.configEcardTemplates).isDisplayed() == false
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathConfigPointsSharing, wizardFinished, dashboardPage)
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathConfigReasons, wizardFinished, dashboardPage)
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathConfigEcardTemplates, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "Wizard" })
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.configWizard).isDisplayed()
    else {
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.configWizard).isDisplayed() == false
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathWizard, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "PointsAllocation" })
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocation).isDisplayed()
    else
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocation).isDisplayed() == false

    if (accessesList.any { it == "GrantPartners" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantPartners).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantPartners).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "PointsAllocation" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantPartners).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantPartners).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathGrantPartners, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "GrantDepartments" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantDepartments).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantDepartments).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "PointsAllocation" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantDepartments).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantDepartments).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathGrantDepartments, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "GrantUsers" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantUsers).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantUsers).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "PointsAllocation" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantUsers).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.pointsAllocationGrantUsers).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathGrantUsers, wizardFinished, dashboardPage)
    }

    if (approvalOptionEnabled) {
        if (accessesList.any { it == "ForApproval" }) {
            sendEcardToApproval()

            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.forApproval).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
            waitFor {
                dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.forApproval).isDisplayed()
            }
        } else {
            if (accessesList.any { it == "PointsAllocation" }) {
                if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.forApproval).isDisplayed() == false)
                    dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.pointsAllocation)
                assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.forApproval).isDisplayed() == false
            }
            assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathForApproval, wizardFinished, dashboardPage)
        }
    }

    if (accessesList.any { it == "AllocationHistory" })
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistory).isDisplayed()
    else
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistory).isDisplayed() == false

    if (accessesList.any { it == "GrantPartnersHistory" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.allocationHistory)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "AllocationHistory" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.allocationHistory)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathPartnersAllocationHistory, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "GrantDepartmentsHistory" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.allocationHistory)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "AllocationHistory" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.allocationHistory)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathDepartmentsAllocationHistory, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "GrantUsersHistory" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryUsersAllocationHistory).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.allocationHistory)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryUsersAllocationHistory).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "AllocationHistory" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryUsersAllocationHistory).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.allocationHistory)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.allocationHistoryUsersAllocationHistory).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathUsersAllocationHistory, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "UsersManagement" })
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.usersManagement).isDisplayed()
    else
        assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.usersManagement).isDisplayed() == false

    if (accessesList.any { it == "BrowseUsers" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.browseUsers).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.usersManagement)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.browseUsers).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "UsersManagement" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.browseUsers).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.usersManagement)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.browseUsers).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathBrowseUsers, wizardFinished, dashboardPage)
    }

    if (accessesList.any { it == "NewUser" }) {
        if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.newUser).isDisplayed() == false)
            dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.usersManagement)
        waitFor {
            dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.newUser).isDisplayed()
        }
    } else {
        if (accessesList.any { it == "UsersManagement" }) {
            if (dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.newUser).isDisplayed() == false)
                dashboardPage.leftSidePanelNavigation.clickNavigationOption(dashboardPage.leftSidePanelNavigation.usersManagement)
            assert dashboardPage.leftSidePanelNavigation.createNavgationElement(dashboardPage.leftSidePanelNavigation.newUser).isDisplayed() == false
        }
        assert checkIfImpossibleToReachSiteByUrl(dashboardPage.leftSidePanelNavigation.pathNewUser, wizardFinished, dashboardPage)
    }
}

Then(~/^User '(.*)' is on landing page and can see links '(.*)'$/) { String username, String links ->
    def linksList = links.split(";")

    if (username.contains('wizard'))
        waitFor { browser.currentUrl.contains("/hr/wizard") }
    else {
        waitFor { browser.currentUrl.contains("/home") }
        //TODO jenkins fix, waiting for all cards are loaded, to be changed to dynamic
        helpFunctions.waitSomeTime(Config.waitLong)
        linksList.each { String link ->
            assert dashboardPage.landingPageLinks.any { it.text() == link }
        }
    }
}
Then(~/^'(.*)' cannot see links for which he does not have permissions '(.*)'$/) { String username, String notAvailableLinks ->
    def linksList = notAvailableLinks.split(";")

    if (username.contains('wizard')) {

    } else {
        linksList.each { String link ->
            assert !dashboardPage.landingPageLinks.any { it.text() == link }
        }
    }
}

// Scenario Outline: Help pdf
When(~/^User click help option available in menu$/) { ->
    browser.withNewWindow({ dashboardPage.topNavigation.clickHelpButton() }, close: true, wait: true) {
        assert browser.currentUrl == 'https://s3-eu-west-1.amazonaws.com/user-guides-production/Employee+reward+platform+user+guide+-+final+.pdf'
    }
}

Then(~/^New tab will be opened with IAT admin pdf guide$/) { ->
    //assertion done in previous step
}