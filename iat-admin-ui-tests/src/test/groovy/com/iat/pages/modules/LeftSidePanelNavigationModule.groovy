package com.iat.pages.modules

import com.iat.pages.DashboardPage
import com.iat.pages.allocationsHistory.DepartmentsAllocationHistoryPage
import com.iat.pages.allocationsHistory.PartnersAllocationHistoryPage
import com.iat.pages.allocationsHistory.UsersAllocationHistoryPage
import com.iat.pages.approvalProcess.ForApprovalPage
import com.iat.pages.config.EcardTemplatesPage
import com.iat.pages.config.PointsSharingPage
import com.iat.pages.config.ReasonsPage
import com.iat.pages.config.WizardPage
import com.iat.pages.pointsAllocationManagement.GrantDepartmentsPage
import com.iat.pages.pointsAllocationManagement.GrantPartnersPage
import com.iat.pages.pointsAllocationManagement.GrantUsersPage
import com.iat.pages.reporting.EcardUsageBreakdownPage
import com.iat.pages.reporting.ReportingPage
import com.iat.pages.usersManagement.BrowseUsersPage
import com.iat.pages.usersManagement.CreateUserPage
import com.iat.stepdefs.utils.HelpFunctions
import geb.Module

class LeftSidePanelNavigationModule extends Module {

    def helpFunctions = new HelpFunctions()

    String statistics = "Reporting"
    String ecardUsageBreakdown = "Ecard usage breakdown"

    String config = "Config"
    String configReasons = "Reasons"
    String configPointsSharing = "Points sharing"
    String configWizard = "Wizard"
    String configEcardTemplates = "Ecard templates"
    String pointsAllocation = "Points Allocation"
    String pointsAllocationGrantDepartments = "Grant departments"
    String pointsAllocationGrantPartners = "[IAT] Grant partners"
    String pointsAllocationGrantUsers = "Grant users"
    String allocationHistory = "Allocation History"
    String allocationHistoryPartnersAllocationHistory = "[IAT] Partners"
    String allocationHistoryDepartmentsAllocationHistory = "Departments"
    String allocationHistoryUsersAllocationHistory = "Users"
    String forApproval = "For approval"
    String usersManagement = "Users Management"
    String browseUsers = "Browse users"
    String newUser = "New user"

    String pathHome = DashboardPage.url
    String pathStatistics = ReportingPage.url
    String pathEcardUsageBreakdown = EcardUsageBreakdownPage.url

    String pathWizard = WizardPage.url
    String pathConfigPointsSharing = PointsSharingPage.url
    String pathConfigReasons = ReasonsPage.url
    String pathConfigEcardTemplates = EcardTemplatesPage.url
    String pathGrantPartners = GrantPartnersPage.url
    String pathGrantDepartments = GrantDepartmentsPage.url
    String pathGrantUsers = GrantUsersPage.url
    String pathForApproval = ForApprovalPage.url
    String pathPartnersAllocationHistory = PartnersAllocationHistoryPage.url
    String pathDepartmentsAllocationHistory = DepartmentsAllocationHistoryPage.url
    String pathUsersAllocationHistory = UsersAllocationHistoryPage.url
    String pathBrowseUsers = BrowseUsersPage.url
    String pathNewUser = CreateUserPage.url

    static content = {
        navigationPanel(wait: true) { $('.nav') }
        basicNavigationElement(required: false) { navigationPanel.find('.md-button') }
        notificationsCounter(required: false) { $('.badge-info') }
    }

    def createNavgationElement(String label) {
//        helpFunctions.waitSomeTime(Config.waitMedium)
        return basicNavigationElement.find('span', text: contains(label), 0)
    }

    def clickNavigationOption(String label) {
        waitFor { createNavgationElement(label).isDisplayed(); createNavgationElement(label).click() }
    }

    int getNotificationCounterValue() {
        if (notificationsCounter[0].isDisplayed())
            return notificationsCounter[0].text().toInteger()
        else
            return notificationsCounter[1].text().toInteger()
    }
}