package com.iat.pages

import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class DashboardPage extends Page {

    static url = '/home'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains('home') } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        wizardCompleted(wait: true) { $("li[ng-if='vm.wizardCompleted']") }
        landingPageLinks(required: false) {
            $(".HomePage-card").find(".card-action > a").findAll { it.isDisplayed() }
        }
        homePageLinksCard(required: false) { $('.HomePage-cardAction') }

        reportingHRHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.reporting.hrPeerToPeer']")
        }
        ecardUsageBreeakdownHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.reporting.hrPeerToPeerBreakdown']")
        }
        configReasonHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.config.reasons']")
        }
        configPointsSharingHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.config.pointsSharing']")
        }
        configEcardsTemplatesHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.config.ecardTemplates']")
        }
        pointsAllocationPartnersHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.pointsAllocation.grantPartners']")
        }
        pointsAllocationDepartmentsHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.pointsAllocation.grantDepartments']")
        }
        pointsAllocationUsersHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.pointsAllocation.grantUsers']")
        }
        pointsAllocationForApprovalHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.pointsAllocation.forApproval']")
        }
        allocationsHistoryPartnersHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.history.partnersAllocation']")
        }
        allocationHistoryDepartmentsHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.history.departmentsAllocation']")
        }
        allocationHistoryUsersHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.history.usersAllocation']")
        }
        userManagementBrowseUsersHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.usersManagement.usersList']")
        }
        userManagementCreateUserHomePageLink(required: false) {
            homePageLinksCard.find("a[ui-sref='app.hr.usersManagement.createUser']")
        }
    }

    def clickHomePageNavigationOption(String label) {
        switch (label) {
            case leftSidePanelNavigation.statistics:
                waitFor { reportingHRHomePageLink.isDisplayed() }
                reportingHRHomePageLink.click()
                break
            case leftSidePanelNavigation.ecardUsageBreakdown:
                waitFor { ecardUsageBreeakdownHomePageLink.isDisplayed() }
                ecardUsageBreeakdownHomePageLink.click()
                break
            case leftSidePanelNavigation.configReasons:
                waitFor { configReasonHomePageLink.isDisplayed() }
                configReasonHomePageLink.click()
                break
            case leftSidePanelNavigation.configPointsSharing:
                waitFor { configPointsSharingHomePageLink.isDisplayed() }
                configPointsSharingHomePageLink.click()
                break
            case leftSidePanelNavigation.configEcardTemplates:
                waitFor { configEcardsTemplatesHomePageLink.isDisplayed() }
                configEcardsTemplatesHomePageLink.click()
                break
            case leftSidePanelNavigation.pointsAllocationGrantPartners:
                waitFor { pointsAllocationPartnersHomePageLink.isDisplayed() }
                pointsAllocationPartnersHomePageLink.click()
                break
            case leftSidePanelNavigation.pointsAllocationGrantDepartments:
                waitFor { pointsAllocationDepartmentsHomePageLink.isDisplayed() }
                pointsAllocationDepartmentsHomePageLink.click()
                break
            case leftSidePanelNavigation.pointsAllocationGrantUsers:
                waitFor { pointsAllocationUsersHomePageLink.isDisplayed() }
                pointsAllocationUsersHomePageLink.click()
                break
            case leftSidePanelNavigation.forApproval:
                waitFor { pointsAllocationForApprovalHomePageLink.isDisplayed() }
                pointsAllocationForApprovalHomePageLink.click()
                break
            case leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory:
                waitFor { allocationsHistoryPartnersHomePageLink.isDisplayed() }
                allocationsHistoryPartnersHomePageLink.click()
                break
            case leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory:
                waitFor { allocationHistoryDepartmentsHomePageLink.isDisplayed() }
                allocationHistoryDepartmentsHomePageLink.click()
                break
            case leftSidePanelNavigation.allocationHistoryUsersAllocationHistory:
                waitFor { allocationHistoryUsersHomePageLink.isDisplayed() }
                allocationHistoryUsersHomePageLink.click()
                break
            case leftSidePanelNavigation.browseUsers:
                waitFor { userManagementBrowseUsersHomePageLink.isDisplayed() }
                userManagementBrowseUsersHomePageLink.click()
                break
            case leftSidePanelNavigation.newUser:
                waitFor { userManagementCreateUserHomePageLink.isDisplayed() }
                userManagementCreateUserHomePageLink.click()
                break
        }
    }

    def navigateToReportingPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.statistics)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.statistics)
    }

    def navigateToReportingPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.statistics)
    }

    def navigateToEcardUsageBreakdownPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.ecardUsageBreakdown)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.ecardUsageBreakdown)
    }

    def navigateToEcardUsageBreakdownPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.ecardUsageBreakdown)
    }

    def navigateToEPointsSharingConfing() {
        waitWhenElementDisplayed(leftSidePanelNavigation.config)
        if (!leftSidePanelNavigation.createNavgationElement(leftSidePanelNavigation.configPointsSharing).isDisplayed())
            leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.config)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.configPointsSharing)
    }

    def navigateToEPointsSharingConfingUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.configPointsSharing)
    }

    def navigateToReasonsConfig() {
        waitWhenElementDisplayed(leftSidePanelNavigation.config)
        if (!leftSidePanelNavigation.createNavgationElement(leftSidePanelNavigation.configReasons).isDisplayed())
            leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.config)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.configReasons)
    }

    def navigateToECardTemplateConfig() {
        waitWhenElementDisplayed(leftSidePanelNavigation.config)
        if (!leftSidePanelNavigation.createNavgationElement(leftSidePanelNavigation.configEcardTemplates).isDisplayed())
            leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.config)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.configEcardTemplates)
    }

    def navigateToConfigWizard() {
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.configWizard)
    }

    def waitWhenElementDisplayed(String label) {
        waitFor { leftSidePanelNavigation.createNavgationElement(label).isDisplayed() }
    }

    def navigateToApprovalPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.pointsAllocation)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.pointsAllocation)
        waitWhenElementDisplayed(leftSidePanelNavigation.forApproval)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.forApproval)
    }

    def navigateToApprovalPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.forApproval)
    }

    def navigateToUsersHistoryAllocationPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.allocationHistory)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.allocationHistory)
        waitWhenElementDisplayed(leftSidePanelNavigation.allocationHistoryUsersAllocationHistory)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.allocationHistoryUsersAllocationHistory)
    }

    def navigateToUsersHistoryAllocationPageUsingHomePage() {
        clickHomePageNavigationOption(leftSidePanelNavigation.allocationHistoryUsersAllocationHistory)
    }

    def navigateToPartnersHistoryAllocationPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.allocationHistory)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.allocationHistory)
        waitWhenElementDisplayed(leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory)
    }

    def navigateToPartnersHistoryAllocationPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.allocationHistoryPartnersAllocationHistory)
    }

    def navigateToDepartmentsHistoryAllocationPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.allocationHistory)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.allocationHistory)
        waitWhenElementDisplayed(leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory)
    }

    def navigateToDepartmentsHistoryAllocationPageUsinhHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.allocationHistoryDepartmentsAllocationHistory)
    }

    def navigateToGrantDepartmentsPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.pointsAllocation)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.pointsAllocation)
        waitWhenElementDisplayed(leftSidePanelNavigation.pointsAllocationGrantDepartments)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.pointsAllocationGrantDepartments)
    }

    def navigateToGrantDepartmentsPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.pointsAllocationGrantDepartments)
    }

    def navigateToGrantPartnersPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.pointsAllocation)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.pointsAllocation)
        waitWhenElementDisplayed(leftSidePanelNavigation.pointsAllocationGrantPartners)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.pointsAllocationGrantPartners)
    }

    def navigateToGrantPartnersPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.pointsAllocationGrantPartners)
    }

    def navigateToGrantUsersPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.pointsAllocation)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.pointsAllocation)
        waitWhenElementDisplayed(leftSidePanelNavigation.pointsAllocationGrantUsers)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.pointsAllocationGrantUsers)
    }

    def navigateToGrantUsersPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.pointsAllocationGrantUsers)
    }

    def navigateToReasonsPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.config)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.config)
        waitWhenElementDisplayed(leftSidePanelNavigation.configReasons)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.configReasons)
    }

    def navigateToReasonsPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.configReasons)
    }

    def navigateToTemplatesPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.config)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.config)
        waitWhenElementDisplayed(leftSidePanelNavigation.configEcardTemplates)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.configEcardTemplates)
    }

    def navigateToTemplatesPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.configEcardTemplates)
    }

    def navigateToBrowseUsersPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.usersManagement)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.usersManagement)
        waitWhenElementDisplayed(leftSidePanelNavigation.browseUsers)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.browseUsers)
    }

    def navigateToBrowseUsersPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.browseUsers)
    }

    def navigateToAddUserPage() {
        waitWhenElementDisplayed(leftSidePanelNavigation.usersManagement)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.usersManagement)
        waitWhenElementDisplayed(leftSidePanelNavigation.newUser)
        leftSidePanelNavigation.clickNavigationOption(leftSidePanelNavigation.newUser)
    }

    def navigateToAddUserPageUsingHomePageLinks() {
        clickHomePageNavigationOption(leftSidePanelNavigation.newUser)
    }

    def navigateToUserProfilePage() {
        topNavigation.expandUserImageDDL()
        topNavigation.clickMyProfileOption()
    }

    def navigateToChangePasswordPage() {
        topNavigation.expandUserImageDDL()
        topNavigation.clickChangePasswordOption()
    }
}