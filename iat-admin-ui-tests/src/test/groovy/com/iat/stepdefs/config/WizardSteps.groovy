package com.iat.stepdefs.config

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.config.EcardTemplatesPage
import com.iat.pages.config.PointsSharingPage
import com.iat.pages.config.ReasonsPage
import com.iat.pages.config.WizardPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.Before

def helpFunctions = new HelpFunctions()
Browser browser = new Browser()

def pointsSharingConfigPage = new PointsSharingPage()
def reasonPage = new ReasonsPage()
def templatePage = new EcardTemplatesPage()
def wizardPage = new WizardPage()
def dashboardPage = new DashboardPage()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)

int step, numberOfReasons

//all main settings across wizard
allSettings = [:]

def prepareAllSettings(int type) {
    def helpFunctions = new HelpFunctions()

    pointsSharingSettings1 = [userWithUsers: 'from same department', managerWithUsers: 'from same department', userWithManager: 'Yes', approvalState: [approvalOption: 'approvalForAll']]
    pointsSharingSettings2 = [userWithUsers: 'from same department', managerWithUsers: 'from same company', userWithManager: 'No', approvalState: [approvalOption: 'noApproval']]
    pointsSharingSettings3 = [userWithUsers: 'from same company', managerWithUsers: 'from same department', userWithManager: 'Yes',
                              approvalState: [approvalOption: 'approvalForThreshold', approvalProcessThreshold: 5]]

    globalConfigSettingsInt = new Integer[4] //m->u min,/m->u max,/u->u min,/u->u max
    globalConfigSettingsInt[0] = helpFunctions.returnRandomValue(100) + 50
    globalConfigSettingsInt[1] = helpFunctions.returnRandomValue(1000) + 250
    globalConfigSettingsInt[2] = helpFunctions.returnRandomValue(100) + 50
    globalConfigSettingsInt[3] = helpFunctions.returnRandomValue(1000) + 250

    globalConfigSettings1 = new String[4]
    globalConfigSettings1[0] = globalConfigSettingsInt[0]
    globalConfigSettings1[1] = globalConfigSettingsInt[1]
    globalConfigSettings1[2] = globalConfigSettingsInt[2]
    globalConfigSettings1[3] = globalConfigSettingsInt[3]

    def uniqueTemplateName = 'uniqueTemplate' + helpFunctions.returnCurrentEpochTime()
    templateSettings1 = [useDefaultTemplates: true]
    templateSettings2 = [useDefaultTemplates: false]
    templateSettings3 = [useDefaultTemplates: false, customTemplate: [customTemplateName: uniqueTemplateName, imageName: 'image.jpg']]

    def uniqueReasonName = 'uniqueReason' + helpFunctions.returnCurrentEpochTime()
    reasonManager2User1 = new String[2]
    reasonManager2User1[0] = globalConfigSettingsInt[0] + helpFunctions.returnRandomValue(40)
    reasonManager2User1[1] = globalConfigSettingsInt[1] - helpFunctions.returnRandomValue(40)
    reasonUser2User1 = new String[2]
    reasonUser2User1[0] = globalConfigSettingsInt[2] + helpFunctions.returnRandomValue(40)
    reasonUser2User1[1] = globalConfigSettingsInt[3] - helpFunctions.returnRandomValue(40)
    reasonGlobalManager2User = new String[2]
    reasonGlobalManager2User[0] = globalConfigSettings1[0]
    reasonGlobalManager2User[1] = globalConfigSettings1[1]
    reasonGlobalUser2User = new String[2]
    reasonGlobalUser2User[0] = globalConfigSettings1[2]
    reasonGlobalUser2User[1] = globalConfigSettings1[3]
    reasonSettings1 = [reasonName: uniqueReasonName, globals: [manager2User: true, user2User: true], reasonManager2User: reasonGlobalManager2User, reasonUser2User: reasonGlobalUser2User]
    reasonSettings2 = [reasonName: uniqueReasonName, globals: [manager2User: false, user2User: false], reasonManager2User: reasonManager2User1, reasonUser2User: reasonUser2User1]
    reasonSettings3 = [reasonName: uniqueReasonName, globals: [manager2User: false, user2User: true], reasonManager2User: reasonManager2User1, reasonUser2User: reasonGlobalUser2User]
    reasonSettings4 = [reasonName: uniqueReasonName, globals: [manager2User: true, user2User: false], reasonManager2User: reasonGlobalManager2User, reasonUser2User: reasonUser2User1]

    def pointsSharingSettings = [:]
    def globalConfigSettings = [:]
    def templateSettings = [:]
    def reasonSettings = [:]

    switch (type) {
        case 1: pointsSharingSettings = pointsSharingSettings1; globalConfigSettings = globalConfigSettings1; templateSettings = templateSettings1; reasonSettings = reasonSettings1; break;
        case 2: pointsSharingSettings = pointsSharingSettings3; globalConfigSettings = globalConfigSettings1; templateSettings = templateSettings3; reasonSettings = reasonSettings3; break;
        case 3: pointsSharingSettings = pointsSharingSettings1; globalConfigSettings = globalConfigSettings1; templateSettings = templateSettings1; reasonSettings = reasonSettings2; break;
        case 4: pointsSharingSettings = pointsSharingSettings2; globalConfigSettings = globalConfigSettings1; templateSettings = templateSettings1; reasonSettings = reasonSettings3; break;
        case 5: pointsSharingSettings = pointsSharingSettings3; globalConfigSettings = globalConfigSettings1; templateSettings = templateSettings1; reasonSettings = reasonSettings4; break;
        case 6: pointsSharingSettings = pointsSharingSettings1; globalConfigSettings = globalConfigSettings1; templateSettings = templateSettings2; reasonSettings = reasonSettings1; break;
    }

    allSettings = [pointsSharingSet: pointsSharingSettings, globalConfigSet: globalConfigSettings, templateSet: templateSettings, reasonSet: reasonSettings]
}
//

Before('@beforeWizard') {
    //take a partner ID for special admin user - wizard only:
    def admin = Config.wizardSuperAdminLogin
    String partnerID = mySQLConnector.getSingleResult("SELECT partnerId FROM User WHERE username='" + admin + "'")
    mySQLConnector.execute("DELETE FROM ECardsSettings WHERE partnerId='" + partnerID + "';")
    mySQLConnector.execute("DELETE FROM ECardsReason WHERE partnerId='" + partnerID + "';")
    mySQLConnector.execute("DELETE FROM ECardsTemplate WHERE partnerId='" + partnerID + "';")

    //default settings
    prepareAllSettings(1)
}

Given(~/^New admin user is logged$/) { ->
    to LoginPage
    loginPage = page
    helpFunctions.clearCookiesAndLocalStorage()
    loginPage.signInUser(Config.wizardSuperAdminLogin, Config.wizardSuperAdminPassword)

    at WizardPage
    wizardPage = page
}

Then(~/^He is presented with wizard view first page$/) { ->
    waitFor { wizardPage.getActualStep() == 1 }
    waitFor { wizardPage.getActualStep() == 1 }
}

And(~/^Other steps in wizard are disabled$/) { ->
    assert wizardPage.checkIfStepsDisabledFromNr(wizardPage.getActualStep() + 1)
}

Then(~/^He cannot access the next config step in wizard$/) { ->
    int lastStep = wizardPage.getActualStep()
    wizardPage.clickNext()
    helpFunctions.refreshPage()
    waitFor { wizardPage.getActualStep() == lastStep }
}

And(~/^Default values for settings in wizard first page are following: '(.*)', '(.*)', '(.*)', '(.*)' and '(.*)'$/) {
    String _userWithUsers,
    String _managerWithUsers,
    String _userWithManager,
    String _approvalOption,
    String _approvalThreshold ->
        def config = [:]
        config['userWithUsers'] = _userWithUsers
        config['managerWithUsers'] = _managerWithUsers
        config['userWithManager'] = _userWithManager
        assert wizardPage.modulePointsSharing.isConfigurationIdentical(config)
}

And(~/^Admin stopped on last step in wizard$/) { ->
    wizardPage.fillInSettingsUpToStepEx(allSettings, 4, false)
}

Then(~/^He cannot save wizard configuration$/) { ->
    int lastStep = wizardPage.getActualStep()
    wizardPage.clickSaveAllConfig()
    helpFunctions.refreshPage()
    waitFor { wizardPage.getActualStep() == lastStep }
}

And(~/^Admin stopped on wizard's step '(.*)'$/) { int _step ->
    step = _step
    wizardPage.fillInSettingsUpToStepEx(allSettings, _step, false)
}

And(~/^He fills in data in current wizard step$/) { ->
    wizardPage.fillInCurrentStep(allSettings)
}

When(~/^He clicks the '(.*)' button in wizard$/) { String buttonName ->
    switch (buttonName) {
        case 'Next': wizardPage.clickNext(); break;
        case 'Back': wizardPage.clickBack(); break;
        case 'Save': wizardPage.clickSaveAllConfig(); break;
    }
}

And(~/^His work on last step '(.*)' in wizard isn't cancelled$/) { int _step ->
    wizardPage.goToAvailableStepByTopNavigation(_step)
    assert wizardPage.checkIfProperSettingsOnCurrentStep(allSettings)
}

And(~/^His work on last step '(.*)' in wizard is cancelled$/) { int _step ->
    wizardPage.goToAvailableStepByTopNavigation(_step)
    wizardPage.checkIfNoSettingsOnCurrentStep()
}

And(~/^Wizard still doesn't have the "Complete" state$/) { ->
    waitFor { wizardPage.actualStepBase.isDisplayed() }
}

Then(~/^He is re-directed to the step '(.*)' in wizard and can edit it$/) { int _step ->
    step = _step
    waitFor { wizardPage.getActualStep() == step }
}

And(~/^Current step is saved in the system$/) { ->
    helpFunctions.refreshPage()
    waitFor { wizardPage.getActualStep() == step }
}

And(~/^He finished filling in the last step in wizard, he uses config type '(.*)'$/) { int configType ->
    prepareAllSettings(configType)
    wizardPage.fillInSettingsUpToStep(allSettings, 4)
}

When(~/^Admin stopped on wizard step '(.*)', he uses config type '(.*)'$/) { int _step, int _configType ->
    step = _step
    prepareAllSettings(_configType)
    wizardPage.fillInSettingsUpToStep(allSettings, step)
}

When(~/^He goes to step '(.*)' using navigation bar$/) { int _step ->
    wizardPage.goToAvailableStepByTopNavigation(_step)
}

And(~/^Admin stopped on "Ecard templates" step in wizard$/) { ->
    step = 3
    wizardPage.fillInSettingsUpToStepEx(allSettings, 3, false)
}

When(~/^He unchecks "Use default templates" in wizard$/) { ->
    wizardPage.uncheckUseDefaultTemplates()
}

Then(~/^He is still on current step in wizard$/) { ->
    //helpFunctions.refreshPage()
    waitFor { wizardPage.getActualStep() == step }
}

When(~/^Admin wants to delete last available reason$/) { ->
    //leave empty
}

Then(~/^He is not able to do that because delete button is not displayed$/) { ->
    waitFor { !wizardPage.moduleReasons.returnReasonTableLocator('action', 0).isDisplayed() }
}

Given(~/^At least (\d+) reasons are already added$/) { int reasonsNumber ->
    numberOfReasons = reasonsNumber
    for (int i = 0; i < numberOfReasons - wizardPage.moduleReasons.reasonsListDataRowBasic.size(); i++) {
        newReason = [reasonName: "automatedTest" + helpFunctions.returnCurrentEpochTime(), globals: [manager2User: true, user2User: true], reasonManager2User: reasonGlobalManager2User, reasonUser2User: reasonGlobalUser2User]
        wizardPage.moduleAddReason.addReasonWithSettings(newReason)
    }
    waitFor { wizardPage.moduleReasons.reasonsListDataRowBasic.size() == numberOfReasons }
}

When(~/^Admin deletes last added reason from table in wizard$/) { ->
    wizardPage.deleteReasonFromListByName(allSettings.reasonSet.reasonName)
}

And(~/^He adds custom template in wizard$/) { ->
    allSettings.templateSet = templateSettings3
    wizardPage.addCustomTemplate(allSettings.templateSet)
}

When(~/^Admin clicks on remove custom template button in wizard with following confirmation$/) { ->
    wizardPage.removeCustomTemplateByName(allSettings.templateSet.customTemplate.customTemplateName)
}

Then(~/^Custom templates list in wizard doesn't contain deleted template$/) { ->
    //helpFunctions.refreshPage()
    waitFor { wizardPage.actualStepBase.isDisplayed() }
    assert wizardPage.checkIfTemplateOnListByName(allSettings.templateSet.customTemplate.customTemplateName) == false
}

Then(~/^Table doesn't have deleted reason in wizard$/) { ->
    //helpFunctions.refreshPage()
    waitFor { wizardPage.actualStepBase.isDisplayed() }
    waitFor { !wizardPage.moduleReasons.reasonsListDataRowBasic[numberOfReasons - 1].isDisplayed() }
    assert !wizardPage.moduleReasons.reasonsListDataRowBasic[numberOfReasons - 1].isDisplayed()
}

Then(~/^All settings from wizard are on config pages/) { ->
    at DashboardPage
    dashboardPage = page
    waitFor { dashboardPage.topNavigation.topNavigationLogo.isDisplayed() }
    waitFor { dashboardPage.leftSidePanelNavigation.navigationPanel.isDisplayed() }
    dashboardPage.navigateToEPointsSharingConfingUsingHomePageLinks()
    at PointsSharingPage
    pointsSharingConfigPage = page
    assert pointsSharingConfigPage.mainModule.isConfigurationIdentical(allSettings.pointsSharingSet)
    to DashboardPage
    dashboardPage = page
    dashboardPage.navigateToTemplatesPageUsingHomePageLinks()
    at EcardTemplatesPage
    templatePage = page
    assert templatePage.getStateUseDefaultAndCustomRB() == allSettings.templateSet.useDefaultTemplates
    if (!(allSettings.templateSet.customTemplate == null))
        assert templatePage.checkIfCustomTemplateOnListByName(allSettings.templateSet.customTemplate.customTemplateName)
    to DashboardPage
    dashboardPage = page
    dashboardPage.navigateToReasonsPageUsingHomePageLinks()
    at ReasonsPage
    reasonPage = page
    assert reasonPage.checkIfReasonOnList(allSettings.reasonSet)
    assert reasonPage.checkIfProperGlobalReasons(allSettings.globalConfigSet)
}

And(~/^Approval settings for wizard are given '(.*)', '(.*)'$/) { String _approvalOption, String _approvalThreshold ->
    def approvalState = [:]
    def config = [:]
    approvalState['approvalOption'] = _approvalOption
    if (_approvalThreshold != null)
        approvalState['approvalProcessThreshold'] = _approvalThreshold

    config['approvalState'] = approvalState
    wizardPage.modulePointsSharing.setConfiguration(config)
}