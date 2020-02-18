package com.iat.stepdefs.config

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.config.PointsSharingPage
import com.iat.pages.reporting.EcardUsageBreakdownPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JdbcDatabaseConnector

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

def pointsSharingConfigPage = new PointsSharingPage()
def ecardUsageBreakdownPage = new EcardUsageBreakdownPage()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)

def config = [:]
def oldConfig = [:]
def helpFunctions = new HelpFunctions()
String newPassword

Before('@setGlobalPasswordFromCompanyConfigurationBeforeTest') {
    mySQLConnector.execute("UPDATE ECardsSettings SET globalPassword= " + null + ", allowGlobalPassword = 1 WHERE partnerId = '" + mySQLConnector.getSingleResult("SELECT partnerId FROM User WHERE username = '" + Config.bupaSuperAdminLogin + "'") + "'")
}

After('@deleteGlobalPasswordFromCompanyConfigurationAfterTest') {
    mySQLConnector.execute("UPDATE ECardsSettings SET globalPassword= " + null + " allowGlobalPassword = 0 WHERE partnerId = '" + mySQLConnector.getSingleResult("SELECT partnerId FROM User WHERE username = '" + Config.bupaSuperAdminLogin + "'") + "'")
}

//General view, options availability
Given(~/^(.+?) bupa user '(true|false)' is logged to the system$/) { String admin, boolean bupaUser ->
    def username, password
    if (admin.equalsIgnoreCase('superadmin')) {
        if (bupaUser) {
            username = Config.bupaSuperAdminLogin
            password = Config.bupaSuperAdminPassword
        } else {
            username = Config.superAdminLogin
            password = Config.superAdminPassword
        }
    }

    config.clear()
    oldConfig.clear()

    to LoginPage
    loginPage = page
    loginPage.signInUser(username, password)

    at DashboardPage
    dashboardPage = page
    waitFor { dashboardPage.topNavigation.topNavigationLogo.isDisplayed() }
    waitFor { dashboardPage.leftSidePanelNavigation.navigationPanel.isDisplayed() }
}

And(~/^He goes to 'Points sharing' config page$/) { ->
    dashboardPage.navigateToEPointsSharingConfingUsingHomePageLinks()

    at PointsSharingPage
    pointsSharingConfigPage = page
}

Then(~/^He will see all configuration options according to company '(.+?)'$/) { String bupaUser ->
    waitFor { pointsSharingConfigPage.optionRoot.isDisplayed() }
    assert pointsSharingConfigPage.mainModule.userWithUsersOptionLabel.text() == 'User can share epoints with users:'
    assert pointsSharingConfigPage.mainModule.userWithUsersOptionBasic[0].text() == 'from same department'
    assert pointsSharingConfigPage.mainModule.userWithUsersOptionBasic[1].text() == 'from same company'
    assert pointsSharingConfigPage.mainModule.managerWithUsersOptionLabel.text() == 'Manager can share epoints with users:'
    assert pointsSharingConfigPage.mainModule.managerWithUsersOptionBasic[0].text() == 'from same department'
    assert pointsSharingConfigPage.mainModule.managerWithUsersOptionBasic[1].text() == 'from same company'
    assert pointsSharingConfigPage.mainModule.userWithManagerOptionLabel.text() == 'Manager can share points with their manager:'
    assert pointsSharingConfigPage.mainModule.userWithManagerBasic[0].text() == 'Yes'
    assert pointsSharingConfigPage.mainModule.userWithManagerBasic[1].text() == 'No'
    if (bupaUser == 'true') {
        assert pointsSharingConfigPage.globalPasswordOptionLabel.text() == 'Set password for all new epoints users'
        assert pointsSharingConfigPage.globalPasswordOptionBasic[0].text() == 'No'
        assert pointsSharingConfigPage.globalPasswordOptionBasic[1].text() == 'Yes, use global password'
    } else if (bupaUser == 'false')
        assert !pointsSharingConfigPage.globalPasswordOption.isDisplayed()
}

//Manager to user points awarding
When(~/^He saves the '(.*)' for manager to user points sharing$/) { String managerWithUsers ->
    pointsSharingConfigPage.mainModule.setManagerWithUsersOption(managerWithUsers)
}

Then(~/^'(.*)' are saved in the system$/) { String managerWithUsers ->
    helpFunctions.refreshPage()
    assert pointsSharingConfigPage.mainModule.checkIfManagerWithUsersOption(managerWithUsers)
}

//Points Sharing configuration
When(~/^He saves the settings for '(.*)', '(.*)' and '(.*)'$/) {
    String userWithUsers,
    String managerWithUsers,
    String userWithManager ->
        config['userWithUsers'] = userWithUsers
        config['managerWithUsers'] = managerWithUsers
        config['userWithManager'] = userWithManager
        pointsSharingConfigPage.mainModule.setConfigurationWithSave(config)
}

Then(~/^Values are saved in the system$/) { ->
    helpFunctions.refreshPage()
    assert pointsSharingConfigPage.mainModule.isConfigurationIdentical(config)
}

//Points Sharing configuration - persistance state of data
When(~/^He changes radio-buttons state for '(.*)', '(.*)' and '(.*)'$/) {
    String userWithUsers,
    String managerWithUsers,
    String userWithManager ->
        oldConfig = pointsSharingConfigPage.mainModule.getConfiguration()
        config['userWithUsers'] = userWithUsers
        config['managerWithUsers'] = managerWithUsers
        config['userWithManager'] = userWithManager
        pointsSharingConfigPage.mainModule.setConfiguration(config)
}

Then(~/^Values are not saved in the system$/) { ->
    helpFunctions.refreshPage()
    assert pointsSharingConfigPage.mainModule.isConfigurationIdentical(oldConfig)
}

Then(~/^Save button is inactive$/) { ->
    waitFor { pointsSharingConfigPage.mainModule.saveButton.getAttribute("disabled") == 'true' }
}

//General password, default state
Then(~/^Global password setting will be set to 'No' by default$/) { ->
    waitFor { pointsSharingConfigPage.globalPasswordOption.isDisplayed() }
    assert pointsSharingConfigPage.globalPasswordOptionBasic[0].attr('aria-checked') == 'true'
}

Then(~/^Global password input field will be displayed when user selects option '(.+?)'$/) { String option ->
    pointsSharingConfigPage.clickGlobalPasswordOption(option)
    waitFor { pointsSharingConfigPage.globalPasswordInputField.isDisplayed() }
    assert pointsSharingConfigPage.globalPasswordInputFieldLabel.text() == "Global password *"
}

//General password, setting global password
When(~/^User provide global password$/) { ->
    pointsSharingConfigPage.clickGlobalPasswordOption('Yes, use global password')
    newPassword = helpFunctions.returnCurrentEpochTime()
    pointsSharingConfigPage.enterGlobalPassword(newPassword)
    waitFor {
        (pointsSharingConfigPage.globalPasswordInputFieldLabel.text() == "Global password - WARNING! Once set, password cannot be changed later! *")
    }
}

When(~/^User save sharing rules$/) { ->
    pointsSharingConfigPage.mainModule.clickSaveButton()
}

Then(~/^Password will be set$/) { ->
    waitFor { pointsSharingConfigPage.topNavigation.alertInfo.isDisplayed() }
    assert pointsSharingConfigPage.topNavigation.alertInfo.text() == "Configuration saved"
}

Then(~/^global password cannot be changed$/) { ->
    waitFor { pointsSharingConfigPage.globalPasswordInputField.attr('disabled') == 'true' }
}

Then(~/^Global password saved option will be persisted$/) { ->
    pointsSharingConfigPage.leftSidePanelNavigation.clickNavigationOption('Everyday Heroes')
    at EcardUsageBreakdownPage
    ecardUsageBreakdownPage = page
    ecardUsageBreakdownPage.leftSidePanelNavigation.clickNavigationOption('Config')
    ecardUsageBreakdownPage.leftSidePanelNavigation.clickNavigationOption('Points sharing')
    at PointsSharingPage
    pointsSharingConfigPage = page

    waitFor { (pointsSharingConfigPage.globalPasswordInputField.value() == newPassword) }
    assert pointsSharingConfigPage.globalPasswordInputField.attr('disabled') == 'true'
}