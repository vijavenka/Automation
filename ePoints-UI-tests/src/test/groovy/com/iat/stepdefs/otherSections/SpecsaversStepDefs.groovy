package com.iat.stepdefs.otherSections

import com.iat.Config
import com.iat.dataProvider.dataProvider
import com.iat.pages.specsaversAdmin.SpecsaversAdminImportPage
import com.iat.pages.specsaversAdmin.SpecsaversAdminPage
import com.iat.pages.specsaversAdmin.SpecsaversAdminReportingPage
import com.iat.pages.specsaversAdmin.SpecsaversAdminStorePage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.DateTimeUtil
import com.iat.stepdefs.utils.Functions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import cucumber.api.java.After
import geb.Browser
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat

def specsaversAdminPage = new SpecsaversAdminPage()
def specsaversAdminImportPage = new SpecsaversAdminImportPage()
def specsaversAdminReportingPage = new SpecsaversAdminReportingPage()
def specsaversAdminStorePage = new SpecsaversAdminStorePage()


def dateTimeUtil = new DateTimeUtil()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)
def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()

// 1.1 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
// ---------------------------------------------------------------------------------------------- login page content
Given(~/^Specsavers login page is opened$/) { ->
    to SpecsaversAdminPage
    specsaversAdminPage = page
    ((JavascriptExecutor) browser.getDriver()).executeScript("window.focus();")
}
Then(~/^He can see that specsavers login page contains all needed elements$/) { ->
    waitFor { specsaversAdminPage.mainHeader.text() == envVar.specsaversLoginHeader }
    assert specsaversAdminPage.mainHeader.text() == envVar.specsaversLoginHeader
    assert specsaversAdminPage.emailLabel.text() == envVar.specsaversEmailLabel
    assert specsaversAdminPage.passwordLabel.text() == envVar.specsaversPasswordLabel
    assert specsaversAdminPage.emailInputField.isDisplayed()
    assert specsaversAdminPage.passwordInputField.isDisplayed()
    assert specsaversAdminPage.signInButton.isDisplayed()
    assert specsaversAdminPage.emailInputField.attr('placeholder') == envVar.specsaversEmailPlaceholder
    assert specsaversAdminPage.passwordInputField.attr('placeholder') == envVar.specsaversPasswordPlaceholder
}

// 1.2 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
// ------------------------------------------------------------------------------------------- required input alerts
When(~/^User type some login data and remove them$/) { ->
    specsaversAdminPage.enterLoginData('something', 'something')
    specsaversAdminPage.enterLoginData('', '')
}
Then(~/^Required alert will be displayed for both inputs$/) { ->
    waitFor { specsaversAdminPage.emailError.isDisplayed() }
    waitFor { specsaversAdminPage.passwordError.isDisplayed() }
    sleep(500) //test fix proposal
    assert specsaversAdminPage.emailError.text() == envVar.specsaversEmailIsRequiredAlert
    assert specsaversAdminPage.passwordError.text() == envVar.specsaversPasswordIsRequireAlert
}

// 1.3 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
// --------------------------------------------------------------------------------------------------- invalid email
When(~/^User type invalid email into email field$/) { ->
    specsaversAdminPage.enterEmail(envVar.testUserEmailInvalid)
}
Then(~/^Email is invalid alert will be displayed under email input$/) { ->
    waitFor { specsaversAdminPage.emailError.isDisplayed() }
    sleep(1000) //test fix proposal
    assert specsaversAdminPage.emailError.text() == envVar.specsaversEmailIsInvalidAlert
}

// 1.4 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
// -------------------------------------------------------------------------------------------- not registered email
When(~/^User type not registered email into email field$/) { ->
    specsaversAdminPage.enterLoginData(envVar.testUserEmailNotActivated, Config.epointsUserPassword)
}
Then(~/^Authorization alert will be displayed under password input$/) { ->
    waitFor { specsaversAdminPage.authorizationError.isDisplayed() }
    assert specsaversAdminPage.authorizationError.text() == envVar.specsaversAuthorizationAlert
}

// 1.5 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
// ---------------------------------------------------------------------------------------------------- proper login
When(~/^User type correct specsaver user login data$/) { ->
    specsaversAdminPage.enterLoginData(envVar.specsaverUser, envVar.specsaversPassword)
}
When(~/^Click sign in button on specsaver login page$/) { ->
    specsaversAdminPage.clickSignInButton()
}
Then(~/^He will be properly logged into admin panel$/) { ->
    waitFor { specsaversAdminPage.mainHeader.text() == envVar.specsaversControlPanelHeader }
    waitFor { specsaversAdminPage.tabNaviagationModule.awardPointsTab.isDisplayed() }
    assert specsaversAdminPage.mainHeader.text() == envVar.specsaversControlPanelHeader
    assert specsaversAdminPage.tabNaviagationModule.awardPointsTab.isDisplayed()
}
When(~/^User click sign out button in specsavers control panel$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickSignOutButton()
}
Then(~/^He will be properly logged out form specsavers control panel$/) { ->
    waitFor { specsaversAdminPage.mainHeader.text() == envVar.specsaversLoginHeader }
    waitFor { specsaversAdminPage.emailInputField.isDisplayed() }
    assert specsaversAdminPage.mainHeader.text() == envVar.specsaversLoginHeader
    assert specsaversAdminPage.emailInputField.isDisplayed()
}

// 2.1 //  ------------------------------------------------------------------------------ SPECSAVERS - control panel
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Specsavers control panel is opened$/) { ->
    to SpecsaversAdminPage
    specsaversAdminPage = page
    ((JavascriptExecutor) browser.getDriver()).executeScript("window.focus();")
    specsaversAdminPage.enterLoginData(envVar.specsaverUser, envVar.specsaversPassword)
    specsaversAdminPage.clickSignInButton()
    waitFor { specsaversAdminPage.tabNaviagationModule.awardPointsTab.isDisplayed() }
}
Then(~/^He can see that specsavers control panel contains all needed elements$/) { ->
    waitFor { specsaversAdminPage.awardPointsButton.isDisplayed() }
    assert specsaversAdminPage.tabNaviagationModule.awardPointsTab.text() == envVar.specsaversAwardPointsTab
    assert specsaversAdminPage.selectBranchesLabel.text() == envVar.specsaversSelectBranchesLabel
    assert specsaversAdminPage.selectAllCheckbox.isDisplayed()
    assert specsaversAdminPage.pickBranchInput.isDisplayed()
    assert specsaversAdminPage.pickBranchInput.attr('placeholder') == envVar.specsaversPickBranchesPlaceholder
    assert specsaversAdminPage.reasonForAwardingLabel.text() == envVar.specsaversReasonForAwardingLabel
    assert specsaversAdminPage.reasonForAwardingInput.isDisplayed()
    assert specsaversAdminPage.reasonForAwardingInput.attr('placeholder') == envVar.specsaversReasonForAwardingPlaceholder
    assert specsaversAdminPage.pointsToAwardLabel.text() == envVar.specsaversPointsToAwardLabel
    assert specsaversAdminPage.pointsToAwardInput.isDisplayed()
    assert specsaversAdminPage.pointsCounter.isDisplayed()
    assert specsaversAdminPage.clearButton.isDisplayed()
    assert specsaversAdminPage.awardPointsButton.isDisplayed()
}

// 2.2 //  ------------------------------------------------------------------------------ SPECSAVERS - control panel
// ------------------------------------------------------------------------------------------------- required alerts
When(~/^User select branch$/) { ->
    sleep(2000)
    specsaversAdminPage.clickSelectBranchInputField()
    specsaversAdminPage.clickChosenBranchOption(0)
}
When(~/^Remove selected branch$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
    specsaversAdminPage.removeChosenBranchOption(0)
}
When(~/^User select tag$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
    sleep(2000)
    specsaversAdminPage.clickUseSpecificTagDDL()
    specsaversAdminPage.clickChosenUseSpecificTagOption(0)
}
When(~/^Remove selected tag$/) { ->
    specsaversAdminPage.clickUseSpecificTagDDL()
    specsaversAdminPage.useSpecificTagInput << Keys.chord(Keys.BACK_SPACE)
    specsaversAdminPage.clickUseSpecificTagDDL()
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
}
When(~/^Enter reason for awarding$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
    sleep(2000)
    specsaversAdminPage.clickReasonForAwardingInput()
    specsaversAdminPage.enterPhraseIntoReasonForAwardinInput('reason')
    specsaversAdminPage.reasonForAwardingInput << Keys.chord(Keys.ENTER)
}
When(~/^Remove reason for awarding$/) { ->
    specsaversAdminPage.clickReasonForAwardingDDL()
    sleep(1000)
    specsaversAdminPage.reasonForAwardingInput << Keys.chord(Keys.BACK_SPACE)
    specsaversAdminPage.clickReasonForAwardingDDL()
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
}
When(~/^Enter some points to award value$/) { ->
    specsaversAdminPage.enterValueIntoPointsToAwardInput('1')
}
When(~/^Remove points to award value$/) { ->
    specsaversAdminPage.enterValueIntoPointsToAwardInput('')
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
}
Then(~/^Under each section should appear field is required alert$/) { ->
    waitFor { specsaversAdminPage.basicAlert[0].isDisplayed() }
    waitFor { specsaversAdminPage.basicAlert[1].isDisplayed() }
    waitFor { specsaversAdminPage.basicAlert[2].isDisplayed() }
    waitFor { specsaversAdminPage.basicAlert[3].isDisplayed() }
    assert specsaversAdminPage.basicAlert[0].text() == envVar.specSaversPickBranchesIsRequiredAlert
    assert specsaversAdminPage.basicAlert[1].text() == envVar.specSaversUseSpecificTagIsRequiredAlert
    assert specsaversAdminPage.basicAlert[2].text() == envVar.specsaversReasonForAwardingPointsIsRequiredAlert
    assert specsaversAdminPage.basicAlert[3].text() == envVar.specsaversPointsToAwardIsRequiredAlert
}

// 3.1 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
// ----------------------------------------------------------------------------------------------- select all button
When(~/^User use select all checkbox$/) { ->
    specsaversAdminPage.clickSelectAllCheckbox()
}
Then(~/^pick Branches Input Field will be disabled$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
    assert !specsaversAdminPage.pickBranchOption.isDisplayed()
}

// 3.2 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
// ------------------------------------------------------------------------------------- selecting/removing branches
String selectedbranch1, selectedbranch2
When(~/^User want to select a branch$/) { ->
    //leave empty
}
Then(~/^He can select few branches manually$/) { ->
    sleep(2000)
    specsaversAdminPage.clickSelectBranchInputField()
    selectedbranch1 = specsaversAdminPage.pickBranchOption[1].text()
    selectedbranch2 = specsaversAdminPage.pickBranchOption[0].text()
    specsaversAdminPage.clickChosenBranchOption(1)
    specsaversAdminPage.clickChosenBranchOption(0)
}
Then(~/^See that branches were properly added to selected list$/) { ->
    assert specsaversAdminPage.pickBranchChosenOptionBasic[0].text().replace("\n", "").replace("×", "") == selectedbranch1
    assert specsaversAdminPage.pickBranchChosenOptionBasic[1].text().replace("\n", "").replace("×", "") == selectedbranch2
}
When(~/^User want to remove branches$/) { ->
    //leave empty
}
Then(~/^He can remove few selected branches manually$/) { ->
    int selectedBranchesSize = specsaversAdminPage.pickBranchChosenOptionXButtonBasic.size()
    for (int i = 0; i < selectedBranchesSize; i++) {
        specsaversAdminPage.removeChosenBranchOption(selectedBranchesSize - 1 - i)
    }
}
Then(~/^See that branches were properly removed from selected list$/) { ->
    waitFor { !specsaversAdminPage.pickBranchChosenOptionBasic.isDisplayed() }
    assert !specsaversAdminPage.pickBranchChosenOptionBasic.isDisplayed()
}

// 4.1 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
//-------------------------------------------------------------------------------------- selecting/removing branches
When(~/^User type not number into points to award input field$/) { ->
    specsaversAdminPage.enterValueIntoPointsToAwardInput('not number value')
}
Then(~/^Points to award is invalid alert will be displayed$/) { ->
    waitFor { specsaversAdminPage.basicAlert[2].isDisplayed() }
    assert specsaversAdminPage.basicAlert[2].text() == envVar.specsaversPointsToAwardIsInvalidAlert
}

// 3.3 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
// ----------------------------------------------------------------------------------------- correctness of branches
When(~/^Expand branches DDL$/) { ->
    sleep(2000)
    specsaversAdminPage.clickSelectBranchInputField()
    sleep(1000)
}
Then(~/^User can see that set of branches is properly returned$/) { ->
    assertThat("Branches are not returned", specsaversAdminPage.pickBranchOption.size() > 0)
}

// 4.2 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
// ------------------------------------------------------------------------------------------------- points counting
int random
When(~/^User enter proper £ value into points to award input field$/) { ->
    random = func.returnRandomValue(10000)
    specsaversAdminPage.enterValueIntoPointsToAwardInput(random)
}
Then(~/^This value will be properly changed int epoints$/) { ->
    waitFor { (random * 200 == Integer.parseInt(specsaversAdminPage.pointsCounter.text())) }
    assert random * 200 == Integer.parseInt(specsaversAdminPage.pointsCounter.text())
}

// 4.3 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
// ----------------------------------------------------------------------------------------- modal content/no button
String chosenBranch
String chosenReason
String chosenTag
String pointsValue
Given(~/^Branches are chosen$/) { ->
    sleep(2000)
    specsaversAdminPage.clickSelectBranchInputField()
    waitFor { specsaversAdminPage.pickBranchOption[0].isDisplayed() }
    chosenBranch = specsaversAdminPage.pickBranchOption[0].text()
    specsaversAdminPage.clickChosenBranchOption(0)
}
Given(~/^Tag is chosen$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
    sleep(2000)
    specsaversAdminPage.clickUseSpecificTagDDL()
    specsaversAdminPage.clickChosenUseSpecificTagOption(0)
    chosenTag = specsaversAdminPage.useSpecificTagChosenOptionBasic[0].text()
}
Given(~/^Reason for awarding points is chosen$/) { ->
    chosenReason = 'automated test reason reason' + func.returnRandomValue(1000000)
    specsaversAdminPage.tabNaviagationModule.clickAwardPointsTab()
    specsaversAdminPage.clickReasonForAwardingInput()
    specsaversAdminPage.enterPhraseIntoReasonForAwardinInput(chosenReason)
    specsaversAdminPage.reasonForAwardingInput << Keys.chord(Keys.ENTER)
}
Given(~/^Points to award value is entered$/) { ->
    random = func.returnRandomValue(3) + 1
    pointsValue = Integer.toString(random)
    specsaversAdminPage.enterValueIntoPointsToAwardInput(pointsValue)
}
When(~/^User click award points button$/) { ->
    waitFor { !(specsaversAdminPage.awardPointsButton.attr('disabled') == 'disabled') }
    specsaversAdminPage.clickAwardPointsButton()
}
Then(~/^Confirm points modal window with proper content will be displayed$/) { ->
    waitFor { specsaversAdminPage.confirmationModal.isDisplayed() }
    assert specsaversAdminPage.confirmationModal.isDisplayed()
    assert specsaversAdminPage.confirmModalHeader.text() == envVar.specsaversConfirmModalHeader
    assert specsaversAdminPage.confirmModalInfo.text().replace("\n", "") == envVar.returnSpecsaversConfirmModalMainText('£' + random + '.00 - ' + random * 200 + ' points', '1', Integer.toString(random * 200))
    assert specsaversAdminPage.confirmModalYesButton.isDisplayed()
    assert specsaversAdminPage.confirmModalNoButton.isDisplayed()
}
When(~/^User click no button in confirm modal window$/) { ->
    specsaversAdminPage.clickNoButtonInConfirmPointsModalWindow()
}
Then(~/^Confirm points modal window will be closed$/) { ->
    waitFor { !specsaversAdminPage.confirmationModal.isDisplayed() }
}

// 5.1 //  -------------------------------------------------- SPECSAVERS - admin page - reason for awarding(NBO-742)
// ------------------------------------------------------------------------------------------ correctness of reasons
When(~/^Expand reason for awarding DDL$/) { ->
    sleep(2000)
    specsaversAdminPage.clickReasonForAwardingDDL()
    waitFor { specsaversAdminPage.reasonForAwardingOption[0].isDisplayed() }
}
Then(~/^User can see that set of reasons is properly returned$/) { ->
    assert Integer.toString(specsaversAdminPage.reasonForAwardingOption.size()) == mySQLConnector.getSingleResult("SELECT COUNT(DISTINCT(activityInfo)) FROM points_manager.Points WHERE partnerId = '100000'")
    for (int i = 0; i < specsaversAdminPage.reasonForAwardingOption.size(); i++) {
        if (specsaversAdminPage.reasonForAwardingOption[i].text().contains('\"')) {
            continue
        }
        assert specsaversAdminPage.reasonForAwardingOption[i].text() == mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points WHERE partnerId = '100000' AND activityInfo = \"" + specsaversAdminPage.reasonForAwardingOption[i].text() + "\"")
    }
}

@After('@automatedTestsReasonsAreDeletedAfter')
void setHighEpointsValue() {
    def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)
    mySQLConnector.execute("DELETE FROM points_manager.Points WHERE activityInfo LIKE '%automated%'")
}

// 6.1 //  ------------------------------------------------- SPECSAVERS - award points to selected branches(NBO-744)
// ------------------------------------------------------------------ correct points award, checking database update
When(~/^User confirm points award by clicking yes button in confirmation modal$/) { ->
    specsaversAdminPage.clickYesButtonInConfirmPointsModalWindow()
}
Then(~/^Award success message will be displayed$/) { ->
    waitFor { specsaversAdminPage.successMessage.isDisplayed() }
    assert specsaversAdminPage.successMessage.isDisplayed()
    waitFor { !specsaversAdminPage.successMessage.isDisplayed() }
    assert !specsaversAdminPage.successMessage.isDisplayed()
}
And(~/^Points will be properly awarded to chosen branches$/) { ->
    waitFor {
        mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC") == chosenReason
    }
    //assert mySQLConnector.getSingleResult("SELECT lastName FROM points_manager.User WHERE id = '" + mySQLConnector.getSingleResult("SELECT userId FROM points_manager.Points ORDER BY id DESC") + "'") == chosenBranch
    assert mySQLConnector.getSingleResult("SELECT tagId FROM points_manager.Points ORDER BY id DESC") == mySQLConnector.getSingleResult("SELECT id FROM points_manager.Tag WHERE description = '" + chosenTag + "'")
    assert mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC") == chosenReason
    assert mySQLConnector.getSingleResult("SELECT delta FROM points_manager.Points ORDER BY id DESC") == Integer.toString(Integer.parseInt(pointsValue) * 200)
    assert mySQLConnector.getSingleResult("SELECT onBehalfOfId FROM points_manager.Points ORDER BY id DESC") == envVar.specsaversPartnerId
    assert mySQLConnector.getSingleResult("SELECT externalTransactionId FROM points_manager.Points ORDER BY id DESC") == envVar.specsaverUser
}

// 7.1 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// ----------------------------------------------------------------------------------------- bulk upload tab content
Given(~/^Import tab is selected$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickImportTab()
    at SpecsaversAdminImportPage
    specsaversAdminImportPage = page
}
When(~/^User look on import tab$/) { ->
    //leave empty
}
Then(~/^He will see field for file selection$/) { ->
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    assert specsaversAdminImportPage.bulkUploadInputVisual.text() == envVar.bulkUploadDropBoxText
}
Then(~/^He will see import button$/) { ->
    waitFor { specsaversAdminImportPage.importButton.isDisplayed() }
    assert specsaversAdminImportPage.importButton.isDisplayed()
}

// 7.2 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
//--------------------------------------------------------------------------------------------------- correct upload
Object[][] dataxls
When(~/^User provide file with correct data$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//correct.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
When(~/^Click import button$/) { ->
    waitFor { !specsaversAdminImportPage.importButton.attr('disabled') }
    specsaversAdminImportPage.clickImportButton()
}
Then(~/^All file rows will be properly uploaded$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlertSuccess.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlertSuccess.text() == envVar.bulkUploadAlertSuccess
    waitFor {
        mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC") == dataxls[1][2]
    }
    assert mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC") == dataxls[1][2]
    assert mySQLConnector.getSingleResult("SELECT delta FROM points_manager.Points ORDER BY id DESC") == ((int) ((dataxls[1][3]) * 200)).toString()
    assert mySQLConnector.getSingleResult("SELECT onBehalfOfId FROM points_manager.Points ORDER BY id DESC") == '100000'
    assert mySQLConnector.getSingleResult("SELECT externalTransactionId FROM points_manager.Points ORDER BY id DESC") == dataxls[1][4]
    //assert mySQLConnector.getSingleResult("SELECT userID FROM points_manager.Points ORDER BY id DESC") == mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE email = '" + dataxls[1][1] + "'")
    //assert mySQLConnector.getSingleResult("SELECT lastName FROM points_manager.User WHERE email = '" + dataxls[1][1] + "'") == dataxls[1][0]
    assert mySQLConnector.getSingleResult("SELECT tagId FROM points_manager.Points ORDER BY id DESC") == mySQLConnector.getSingleResult("SELECT id FROM points_manager.Tag WHERE tagKey = '" + dataxls[1][5] + "'")
}

// 7.3 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// --------------------------------------------------------------------------------------------- incorrect file type
When(~/^User provide file with incorrect data$/) { ->
    String fileAbsolutePath = new File('src//data files//wrongFormat.docx').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Incorrect file format alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text() == envVar.bulkUploadWrongFormatAlert
}

// 7.4 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// ---------------------------------------------------------------------------------------------------- missing name
When(~/^User provide file with missing name$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//missingName.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Missing name alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadMissingNameAlert.replace("\n", "").replace(" ", "")
}

// 7.5 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// --------------------------------------------------------------------------------------------------- missing email
When(~/^User provide file with missing email$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//missingEmail.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Missing email alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadMissingEmailAlert.replace("\n", "").replace(" ", "")
}

// 7.6 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// -------------------------------------------------------------------------------------------------- missing reason
When(~/^User provide file with missing reason$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//missingReason.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Missing reason alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadMissingReasonAlert.replace("\n", "").replace(" ", "")
}

// 7.7 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// -------------------------------------------------------------------------------------------------- missing amount
When(~/^User provide file with missing amount$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//missingAmount.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Missing amount alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadMissingAmountAlert.replace("\n", "").replace(" ", "")
}

// 7.8 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// --------------------------------------------------------------------------------------------------- missing admin
When(~/^User provide file with missing admin$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//missingAdmin.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Missing admin alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadMissingAdminAlert.replace("\n", "").replace(" ", "")
}

// 7.9 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// ----------------------------------------------------------------------------------------------------- wrong email
When(~/^User provide file with wrong email$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//wrongEmail.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Wrong email alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadWrongEmailAlert.replace("\n", "").replace(" ", "")
}

// 7.10 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// ------------------------------------------------------------------------------------------------------ low amount
When(~/^User provide file with too low amount$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//tooLowAmount.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Wrong too low amount alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadTooLowAmountAlert.replace("\n", "").replace(" ", "")
}

// 7.11 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// ------------------------------------------------------------------------------------------------ very long reason
When(~/^User provide file with very long reason$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//tooLongReason.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Points will be properly awarded and reason truncated to 255 signs$/) { ->
    waitFor {
        mySQLConnector.getSingleResult("SELECT externalTransactionId FROM points_manager.Points ORDER BY id DESC") == dataxls[1][4]
    }
    assert mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC").size() == 255
}

// 7.12 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
// ------------------------------------------------------------------------------------------------ incorrect header
When(~/^User provide file with incorrect header$/) { ->
    def dataProv = new dataProvider()
    dataxls = dataProv.returndata('src//data files//correct.xls')
    String fileAbsolutePath = new File('src//data files//wrongHeader.xls').getAbsolutePath().replace('\\', '\\\\')
    waitFor { specsaversAdminImportPage.bulkUploadInputVisual.isDisplayed() }
    specsaversAdminImportPage.clickOnImportFileButton()
    sleep(500)
    specsaversAdminImportPage.bulkUploadInput = fileAbsolutePath
}
Then(~/^Wrong header alert will be displayed$/) { ->
    waitFor { specsaversAdminImportPage.uploadAlerDanger.isDisplayed() }
    assert specsaversAdminImportPage.uploadAlerDanger.text().replace("\n", "").replace(" ", "") == envVar.bulkUploadWrongHeaderAlert.replace("\n", "").replace(" ", "")
}

// 8.1 //  ------ SPECSAVERS - create the reporting tab within Specsavers admin UI adding "view" structure(NBO-1612)
// ------------------------------------------------------------------------------------------- reporting tab content
When(~/^User click on reporting tab$/) { ->
    specsaversAdminPage.tabNaviagationModule.clickReportingTab()
    at SpecsaversAdminReportingPage
    specsaversAdminReportingPage = page
}
Then(~/^Reporting tab will be opened$/) { ->

}
Then(~/^Reporting tab contains proper elements$/) { ->
    assert specsaversAdminReportingPage.startDateLabel.text() == envVar.specsaversReportsTabStartDateLabel
    assert specsaversAdminReportingPage.endDateLabel.text() == envVar.specsaversReportsTabEndDateLabel
    assert specsaversAdminReportingPage.startDateInputField.isDisplayed()
    assert specsaversAdminReportingPage.endDateInputField.isDisplayed()
    assert specsaversAdminReportingPage.startDateInputField.isDisplayed()
    assert specsaversAdminReportingPage.endDateInputField.isDisplayed()
    assert specsaversAdminReportingPage.generateReportButton.isDisplayed()
    assert specsaversAdminReportingPage.overviewSectionTab.text() == envVar.specsaversReportsTabOverviewSection
    assert specsaversAdminReportingPage.epointsAwardedSectionTab.text() == envVar.specsaversReportsTabAwardedSection
    assert specsaversAdminReportingPage.epointsRedeemedSectionTab.text() == envVar.specsaversReportsTabRedeemedSection
    assert specsaversAdminReportingPage.downloadReportTab[0].isDisplayed()
}

// 9.1 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
// -------------------------------------------------------------------------------------------- default dates values
Then(~/^Dates fields will be automatically filled with default values$/) { ->
    waitFor { specsaversAdminReportingPage.startDateInputField.isDisplayed() }
    waitFor { specsaversAdminReportingPage.endDateInputField.isDisplayed() }
    Calendar calFirstDay = Calendar.getInstance()
    Calendar calCurrentDay = Calendar.getInstance()
    calFirstDay.set(Calendar.DAY_OF_MONTH, 1)

    def epochOfFirstMonthDayDiff = (calFirstDay.getTimeInMillis() - func.convertDateToEpochFormat(specsaversAdminReportingPage.startDateInputField.value(), 'dd-MM-yyyy'))
    def epochOfCurrentDayDiff = (calCurrentDay.getTimeInMillis() - func.convertDateToEpochFormat(specsaversAdminReportingPage.endDateInputField.value(), 'dd-MM-yyyy'))

    assert (epochOfFirstMonthDayDiff < 86400000 && epochOfFirstMonthDayDiff > 0)//day  in epoch time
    assert (epochOfCurrentDayDiff < 86400000 && epochOfCurrentDayDiff > 0)
}

// 9.2 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
// ---------------------------------------------------------------------------------------------- dates between tabs
Given(~/^Specsavers control panel reports tab is opened$/) { ->
    to SpecsaversAdminPage
    specsaversAdminPage = page
    ((JavascriptExecutor) browser.getDriver()).executeScript("window.focus();")
    specsaversAdminPage.enterLoginData(envVar.specsaverUser, envVar.specsaversPassword)
    specsaversAdminPage.clickSignInButton()
    waitFor { specsaversAdminPage.tabNaviagationModule.awardPointsTab.isDisplayed() }
    specsaversAdminPage.tabNaviagationModule.clickReportingTab()
    at SpecsaversAdminReportingPage
    specsaversAdminReportingPage = page
}
When(~/^User will navigate between three available report tabs$/) { ->
    waitFor { specsaversAdminReportingPage.startDateInputField.isDisplayed() }
    waitFor { specsaversAdminReportingPage.endDateInputField.isDisplayed() }
    String startDate = specsaversAdminReportingPage.startDateInputField.value()
    String endDate = specsaversAdminReportingPage.endDateInputField.value()

    specsaversAdminReportingPage.openOverviewSection()
    assert startDate == specsaversAdminReportingPage.startDateInputField.value()
    assert endDate == specsaversAdminReportingPage.endDateInputField.value()
    specsaversAdminReportingPage.openAwardedSection()
    assert startDate == specsaversAdminReportingPage.startDateInputField.value()
    assert endDate == specsaversAdminReportingPage.endDateInputField.value()
    specsaversAdminReportingPage.openRedeemedSection()
    assert startDate == specsaversAdminReportingPage.startDateInputField.value()
    assert endDate == specsaversAdminReportingPage.endDateInputField.value()
}
Then(~/^Dates will stay the same$/) { ->
    //done in previous step
}

// 9.3 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
// ------------------------------------------------------------------------------------------------- dates between tabs
When(~/^User open calendar picker$/) { ->
    specsaversAdminReportingPage.openStartDateCalendar()
    waitFor { specsaversAdminReportingPage.calendar[0].isDisplayed() }
}
When(~/^Click '(.+?)' on calendar view$/) { String buttonType ->
    if (buttonType == 'today') {
        specsaversAdminReportingPage.clickCalendarTodayButton()
    } else if (buttonType == 'clear') {
        specsaversAdminReportingPage.clickCalendarClearButton()
    } else if (buttonType == 'close') {
        specsaversAdminReportingPage.clickCalendarCloseButton()
    }
}

Then(~/^Proper action will be executed after clicking '(.+?)'$/) { String buttonType ->
    if (buttonType == 'today') {
        assert (func.returnEpochOfCurrentDay() - func.convertDateToEpochFormat(specsaversAdminReportingPage.startDateInputField.value(), 'dd-MM-yyyy')) < 86400000
    } else if (buttonType == 'clear') {
        assert specsaversAdminReportingPage.startDateInputField.value() == ''
    } else if (buttonType == 'close') {
        waitFor { !specsaversAdminReportingPage.calendar[0].isDisplayed() }
        assert !specsaversAdminReportingPage.calendar[0].isDisplayed()
    }
}

// 9.4 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
// ------------------------------------------------------------------------------------------ choosing calendar date
String chosenDay
When(~/^User chose some date from calendar$/) { ->
    waitFor { specsaversAdminReportingPage.calendarDayBasic[0].isDisplayed() }
    random = 0//func.returnRandomValue(42)
//    while (specsaversAdminReportingPage.calendarDayBasic[random].attr('disabled') == 'disabled') {
//        random = func.returnRandomValue(42)
//    }
    chosenDay = specsaversAdminReportingPage.calendarDayBasic[random].text()
    specsaversAdminReportingPage.clickCalendarChosenDayButton(random)
    sleep(5000)
}
Then(~/^Field will be filled with proper date$/) { ->
    waitFor { specsaversAdminReportingPage.startDateInputField.value().contains(chosenDay) }
    assert specsaversAdminReportingPage.startDateInputField.value().contains(chosenDay)
}

// 9.5 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
// ------------------------------------------------------------------------------------- filling wrong dates by hand
When(~/^User fill start and end dates with not allowed values '(.+?)' '(.+?)'$/) { String startDate, String endDate ->
    specsaversAdminReportingPage.enterStartDate(startDate)
    specsaversAdminReportingPage.enterEndDate(endDate)
}
Then(~/^Please specify correct date range alert will be displayed$/) { ->
    waitFor { specsaversAdminReportingPage.wrongDateAlert.isDisplayed() }
    assert specsaversAdminReportingPage.wrongDateAlert.text() == envVar.specsaversReportsCalendarALert
}
Then(~/^Generate report button will be disabled$/) { ->
    waitFor { specsaversAdminReportingPage.generateReportButton.attr('disabled') }
    assert specsaversAdminReportingPage.generateReportButton.attr('disabled')
}

// 10.1 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Epoints awarded section is opened$/) { ->
    specsaversAdminReportingPage.openAwardedSection()
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
    waitFor { browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingAwardedURL }
    assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingAwardedURL
}
When(~/^User look on epoints awarded section$/) { ->
    //leave empty
}
Then(~/^He can see that it contains table with proper columns$/) { ->
    waitFor { specsaversAdminReportingPage.tableHeaderRow.isDisplayed() }
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[0].text() == envVar.specsaversReportsAwardedDateLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[1].text() == envVar.specsaversReportsAwardedEpointsLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[2].text() == envVar.specsaversReportsAwardedValueLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[3].text() == envVar.specsaversReportsAwardedIssuedByLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[4].text() == envVar.specsaversReportsAwardedAwardedToLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[5].text() == envVar.specsaversReportsAwardedIncentiveLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[6].text() == envVar.specsaversReportsAwardedReasonLabel
}
Then(~/^Summary values are available$/) { ->
    waitFor { specsaversAdminReportingPage.epointsSummary.isDisplayed() }
    waitFor { specsaversAdminReportingPage.valueSummary.isDisplayed() }
    assert specsaversAdminReportingPage.epointsSummary.isDisplayed()
    assert specsaversAdminReportingPage.valueSummary.isDisplayed()
}

// 10.2 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
// ------------------------------------------------------------------------------------------ table data correctness
def startDay, endDay
def startMonth, endMonth
def year
Given(~/^Report is generated with random start and end dates$/) { ->
//    startDay = (func.returnRandomValue(10) + 10).toString()
//    endDay = (func.returnRandomValue(10) + 20).toString()
//    startMonth = '01'
//    endMonth = '04'//(func.returnRandomValue(10)+2).toString()
//    if (endMonth.size() == 1) {
//        endMonth = '0' + endMonth
//    }
//    year = '2015'

    startDay = '01'; endDay = '04'; startMonth = '10'; endMonth = '10'; year = '2017'

    specsaversAdminReportingPage.enterStartDate(startDay + "-" + startMonth + "-" + year)
    specsaversAdminReportingPage.enterEndDate(endDay + "-" + endMonth + "-" + year)
    waitFor { !specsaversAdminReportingPage.generateReportButton.attr('disabled') }
    specsaversAdminReportingPage.clickGenerateReportButton()
}
Then(~/^He can see that all awarded section data is correct according to database$/) { ->
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
    waitFor { specsaversAdminReportingPage.tableRowBasic(0) }
    def stopCondition
    if (specsaversAdminReportingPage.tableRowStatic.size() < 50) {
        stopCondition = specsaversAdminReportingPage.tableRowStatic.size()
    } else {
        stopCondition = 50
    }
    HashMap<Integer, List> pointsElements = mySQLConnector.getResult("SELECT createdAt, activityInfo, delta, externalTransactionId, tagId, userId  FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY createdAt DESC;", Arrays.asList("createdAt", "activityInfo", "delta", "externalTransactionId", "tagId", "userId"))

    for (int i = 0; i < stopCondition; i++) {
        assert dateTimeUtil.addUtcOffset(dateTimeUtil.convertToDate(pointsElements.get(i).get(0).toString(), DateTimeUtil.Format.yyyyMMdd_HHmmss)).toTimestamp() == dateTimeUtil.convertToDate(specsaversAdminReportingPage.tableRowElementBasic(i, 0).text(), DateTimeUtil.Format.ddMMyyyy_HHmmss_2).toTimestamp()
        assert pointsElements.get(i).get(2).toString() == specsaversAdminReportingPage.tableRowElementBasic(i, 1).text().replace(',', '')
        assert specsaversAdminReportingPage.tableRowElementBasic(i, 2).text().replace(',', '').contains('£' + Float.parseFloat(pointsElements.get(i).get(2).toString()) / 200)
        assert pointsElements.get(i).get(3).toString() == specsaversAdminReportingPage.tableRowElementBasic(i, 3).text()
        assert mySQLConnector.getSingleResult("SELECT tagKey from points_manager.Tag where id = '" + pointsElements.get(i).get(4).toString() + "'") == specsaversAdminReportingPage.tableRowElementBasic(i, 5).text()
        assert pointsElements.get(i).get(1).toString() == specsaversAdminReportingPage.tableRowElementBasic(i, 6).text().replace(',', '')
        if (i == 5) {
            break
        }
    }
}

// 10.3 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
// --------------------------------------------------------------------------------------------------------- summary
Then(~/^He can see that epoints and value summary are correct in awarded section$/) { ->
    waitFor { specsaversAdminReportingPage.epointsSummary.isDisplayed() }
    waitFor { specsaversAdminReportingPage.valueSummary.isDisplayed() }
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
    assert mySQLConnector.getSingleResult("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY createdAt DESC") == specsaversAdminReportingPage.epointsSummary.text().replace(',', '').replace('Total epoints: ', '')
    assert (Float.parseFloat(specsaversAdminReportingPage.valueSummary.text().replace('Total value: £', '').replace(',', ''))) - Float.parseFloat(mySQLConnector.getSingleResult("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY createdAt DESC")) / 200 < 0.006
}

// 11.1 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Epoints redeemed section is opened$/) { ->
    specsaversAdminReportingPage.openRedeemedSection()
    waitFor { browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingRedeemedURL }
    assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingRedeemedURL
}
When(~/^User look on epoints redeemed section$/) { ->
    //leave empty
}
Then(~/^He can see that redeemed section contains table with proper columns$/) { ->
    waitFor { specsaversAdminReportingPage.tableHeaderRow.isDisplayed() }
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[0].text() == envVar.specsaversReportsRedeemedDateLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[1].text() == envVar.specsaversReportsRedeemedEpointsLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[2].text() == envVar.specsaversReportsRedeemedValueLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[3].text() == envVar.specsaversReportsRedeemedSpentByLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[4].text() == envVar.specsaversReportsRedeemedRedemptionItemLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[5].text() == envVar.specsaversReportsRedeemedQuantityLabel
    assert specsaversAdminReportingPage.tableHeaderRowElementBasic[6].text() == envVar.specsaversReportsRedeemedUrlLabel
}

// 11.2 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
// ------------------------------------------------------------------------------------------ table data correctness
Then(~/^He can see that all redemeed section data is correct according to database$/) { ->
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
    waitFor { specsaversAdminReportingPage.tableRowBasic(0) }
    def stopCondition
    if (specsaversAdminReportingPage.tableRowStatic.size() < 50) {
        stopCondition = specsaversAdminReportingPage.tableRowStatic.size() - 1
    } else {
        stopCondition = 50
    }
    HashMap<Integer, List> pointsElements = mySQLConnector.getResult("SELECT points_manager.Points.createdAt, delta, userId, productId FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY points_manager.Points.createdAt DESC;", Arrays.asList("createdAt", "delta", "userId", "productId"))
    for (int i = 0; i < stopCondition; i++) {
        assert dateTimeUtil.addUtcOffset(dateTimeUtil.convertToDate(pointsElements.get(i).get(0).toString(), DateTimeUtil.Format.yyyyMMdd_HHmmss)).toTimestamp() == dateTimeUtil.convertToDate(specsaversAdminReportingPage.tableRowElementBasic(i, 0).text(), DateTimeUtil.Format.ddMMyyyy_HHmmss_2).toTimestamp()
        assert pointsElements.get(i).get(1).toString() == specsaversAdminReportingPage.tableRowElementBasic(i, 1).text().replace(',', '')
        assert (Float.parseFloat(specsaversAdminReportingPage.tableRowElementBasic(i, 2).text().replace(',', '').replace('£', '')) * Integer.parseInt(specsaversAdminReportingPage.tableRowElementBasic(i, 5).text()) - Float.parseFloat(pointsElements.get(i).get(1).toString()) / 200).abs() < 0.006
        assert mySQLConnector.getSingleResult("SELECT title FROM points_manager.Product WHERE id = '" + pointsElements.get(i).get(3).toString() + "'") == specsaversAdminReportingPage.tableRowElementBasic(i, 4).text()
        assert mySQLConnector.getSingleResult("SELECT quantity FROM points_manager.Product WHERE id = '" + pointsElements.get(i).get(3).toString() + "'") == specsaversAdminReportingPage.tableRowElementBasic(i, 5).text()
        assert (specsaversAdminReportingPage.tableRowElementBasic(i, 6).find('a').attr('href').contains(mySQLConnector.getSingleResult("SELECT productId FROM points_manager.Product WHERE id = '" + pointsElements.get(i).get(3).toString() + "'").replace('!', '/')) || specsaversAdminReportingPage.tableRowElementBasic(i, 6).find('a').attr('href').contains(envVar.spendURL))
        if (i == 5) {
            break
        }
    }
}

// 11.3 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
// ------------------------------------------------------------------------------------------------------- open link
When(~/^User click open link of chosen row$/) { ->
    HashMap<Integer, List> pointsElements = mySQLConnector.getResult("SELECT points_manager.Product.productId FROM points_manager.Points JOIN points_manager.Product ON points_manager.Points.productId = points_manager.Product.id JOIN points_manager.User ON points_manager.User.id = points_manager.Points.userId WHERE points_manager.Points.status = 'REDEEMED' AND points_manager.User.registrationSiteId = '100000' AND points_manager.Points.createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY points_manager.Points.createdAt DESC", Arrays.asList("productId"))
    browser.withNewWindow({ specsaversAdminReportingPage.clickOpenLinkOfChosenRow(0) }, close: true, wait: true) {
        waitFor {
            (browser.currentUrl.contains((pointsElements.get(i).get(0).toString()).replace('!', '/')) || browser.currentUrl.contains(envVar.spendURL))
        }
        assert (browser.currentUrl.contains((pointsElements.get(i).get(0).toString()).replace('!', '/')) || browser.currentUrl.contains(envVar.spendURL))
    }
}
Then(~/^Product page of redeemed product will be opened in new window$/) { ->
    //done in previous step
}

// 11.4 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
// --------------------------------------------------------------------------------------------------------- summary
Then(~/^He can see that epoints and value summary are correct$/) { ->
    waitFor { specsaversAdminReportingPage.epointsSummary.isDisplayed() }
    waitFor { specsaversAdminReportingPage.valueSummary.isDisplayed() }
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
    assert mySQLConnector.getSingleResult("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY points_manager.Points.createdAt DESC") == specsaversAdminReportingPage.epointsSummary.text().replace(',', '').replace('Total epoints: ', '')
    assert (Float.parseFloat(specsaversAdminReportingPage.valueSummary.text().replace('Total value: £', '').replace(',', ''))) - Float.parseFloat(mySQLConnector.getSingleResult("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY points_manager.Points.createdAt DESC")) / 200 < 0.006
}

// 12.1 //  ------------------------------ SPECSAVERS - populate required data and UI for "overview view".(NBO-1614)
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Epoints overview section is opened$/) { ->
    specsaversAdminReportingPage.openOverviewSection()
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
    waitFor { browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingURL }
    assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingURL
}
When(~/^User look on epoints overview section$/) { ->
    //leave empty
}
Then(~/^He can see that overview section contains table with proper columns$/) { ->
    waitFor { specsaversAdminReportingPage.overwiewTable.isDisplayed() }
    assert specsaversAdminReportingPage.overwiewTableRowLabelBasic(0).text() == envVar.specsaversReportsOverviewEPAwardedLabel
    assert specsaversAdminReportingPage.overwiewTableRowLabelBasic(1).text() == envVar.specsaversReportsOverviewAcAwardedLabel
    assert specsaversAdminReportingPage.overwiewTableRowLabelBasic(2).text() == envVar.specsaversReportsOverviewValueOfEPLabel
    assert specsaversAdminReportingPage.overwiewTableRowLabelBasic(3).text() == envVar.specsaversReportsOverviewEPRedeemedLabel
    assert specsaversAdminReportingPage.overwiewTableRowLabelBasic(4).text() == envVar.specsaversReportsOverviewNumberOfRedLabel
    assert specsaversAdminReportingPage.overwiewTableRowLabelBasic(5).text() == envVar.specsaversReportsOverviewValueOFRedLabel
}

// 12.2 //  ------------------------------ SPECSAVERS - populate required data and UI for "overview view".(NBO-1614)
// ------------------------------------------------------------------------------------------ table data correctness
Then(~/^He can see that all overview section data is correct according to database$/) { ->
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
    assert specsaversAdminReportingPage.overwiewTableRowDataBasic(0).text().replace(',', '') == mySQLConnector.getSingleResult("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY createdAt DESC")
    assert specsaversAdminReportingPage.overwiewTableRowDataBasic(1).text() == mySQLConnector.getSingleResult("SELECT COUNT(DISTINCT(userId)) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY createdAt DESC")
    assert Float.parseFloat(specsaversAdminReportingPage.overwiewTableRowDataBasic(2).text().replace(',', '').replace('£', '')) - Float.parseFloat(mySQLConnector.getSingleResult("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY createdAt DESC")) / 200 < 0.006
    assert specsaversAdminReportingPage.overwiewTableRowDataBasic(3).text().replace(',', '') == mySQLConnector.getSingleResult("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY points_manager.Points.createdAt DESC")
    assert specsaversAdminReportingPage.overwiewTableRowDataBasic(4).text() == mySQLConnector.getSingleResult("SELECT SUM(quantity) FROM points_manager.Points JOIN points_manager.Product ON points_manager.Points.productId = points_manager.Product.id JOIN points_manager.User ON points_manager.User.id = points_manager.Points.userId WHERE points_manager.Points.status = 'REDEEMED' AND points_manager.User.registrationSiteId = '100000' AND points_manager.Points.createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY points_manager.Points.createdAt DESC").replace(".0", "")
    assert Float.parseFloat(specsaversAdminReportingPage.overwiewTableRowDataBasic(5).text().replace(',', '').replace('£', '')) - Float.parseFloat(mySQLConnector.getSingleResult("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "' ORDER BY points_manager.Points.createdAt DESC")) / 200 < 0.006
}

// 13.1 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
// ------------------------------------------------------------------------------ availability of pagination options
Then(~/^He can see pagination component in reporting tab$/) { ->
    waitFor { specsaversAdminReportingPage.showingElement.isDisplayed() }
    specsaversAdminReportingPage.showingElement.isDisplayed()
    specsaversAdminReportingPage.outOfElement.isDisplayed()
    specsaversAdminReportingPage.rowNumberSelector.isDisplayed()
    if (Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', '')) > Integer.parseInt(specsaversAdminReportingPage.rowNumberSelector.find('.is-active').text())) {
        waitFor { specsaversAdminReportingPage.topPaginationArrowLeft.isDisplayed() }
        assert specsaversAdminReportingPage.topPaginationArrowLeft.isDisplayed()
        assert specsaversAdminReportingPage.topPaginationArrowRight.isDisplayed()
        assert specsaversAdminReportingPage.bottomPaginationArrowLeft.isDisplayed()
        assert specsaversAdminReportingPage.bottomPaginationArrowRight.isDisplayed()
        assert specsaversAdminReportingPage.bottomPaginationPageNumberBasic[0].isDisplayed()
    } else {
        waitFor { !specsaversAdminReportingPage.topPaginationArrowLeft.isDisplayed() }
        assert !specsaversAdminReportingPage.topPaginationArrowLeft.isDisplayed()
        assert !specsaversAdminReportingPage.topPaginationArrowRight.isDisplayed()
        assert !specsaversAdminReportingPage.bottomPaginationArrowLeft.isDisplayed()
        assert !specsaversAdminReportingPage.bottomPaginationArrowRight.isDisplayed()
        assert !specsaversAdminReportingPage.bottomPaginationPageNumberBasic[0].isDisplayed()
    }
}
Then(~/^Epoints redeemed section will be opened$/) { ->
    specsaversAdminReportingPage.openRedeemedSection()
    waitFor(20) { !specsaversAdminReportingPage.contentLoader.isDisplayed() }
}
// 13.2 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
// ---------------------------------------------------------------- top pagination, working of next/previous buttons
Given(~/^Visible rows number is set to 20$/) { ->
    waitFor { specsaversAdminReportingPage.tableRowBasic(0).isDisplayed() }
    specsaversAdminReportingPage.clickItemPerPage20(); sleep(1000)
}
When(~/^User click next page button on specsavers awarded section$/) { ->
    waitFor { specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.isDisplayed() }
    waitFor { specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains('1') }
    if (Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', '')) > 20) {
        assert specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains('1')
        specsaversAdminReportingPage.clickNextPageButton()
        sleep(1000)
    }
}
Then(~/^Page will be changed to next on specsavers awarded section$/) { ->
    if (Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', '')) > 20) {
        waitFor { specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains('2') }
        assert specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains('2')
    }
}
Then(~/^Showing module will be increased on specsavers awarded section$/) { ->
    def outOfNumber = Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', ''))
    if (outOfNumber > 20) {
        if (outOfNumber > 40) {
            waitFor { specsaversAdminReportingPage.showingElement.text().contains('Showing 21 - 40') }
            assert specsaversAdminReportingPage.showingElement.text().contains('Showing 21 - 40')
        } else {
            waitFor { specsaversAdminReportingPage.showingElement.text().contains('Showing 21 - ' + outOfNumber) }
            assert specsaversAdminReportingPage.showingElement.text().contains('Showing 21 - ' + outOfNumber)
        }
    }
}
When(~/^User click previous page button on specsavers awarded section$/) { ->
    if (Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', '')) > 20) {
        specsaversAdminReportingPage.clickPreviousPageButton()
        sleep(1000)
    }
}
Then(~/^Showing module will be decreased on specsavers awarded section$/) { ->
    if (Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', '')) > 20) {
        waitFor { specsaversAdminReportingPage.showingElement.text().contains('Showing 1 - 20') }
        assert specsaversAdminReportingPage.showingElement.text().contains('Showing 1 - 20')
    }
}
Then(~/^Page will be changed to previous on specsavers awarded section$/) { ->
    if (Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', '')) > 20) {
        waitFor { specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains('1') }
        assert specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains('1')
    }
}

// 13.3 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
// --------------------------------------------------------------- bottom pagination, working of page numbers button
int numberOfProducts
Then(~/^He can see tat proper number of page is displayed according to 'out of' information on specsavers awarded section$/) {
    ->
    waitFor { specsaversAdminReportingPage.outOfElement.isDisplayed() }
    numberOfProducts = Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace("(out of ", "").replace(")", ""))
    if ((numberOfProducts % 20) == 0) {  // 20 because default number of products per page is 20
        waitFor {
            Integer.parseInt(specsaversAdminReportingPage.bottomPaginationPageNumberBasic.last().text()) == (int) (numberOfProducts / 20)
        }
        assert Integer.parseInt(specsaversAdminReportingPage.bottomPaginationPageNumberBasic.last().text()) == (int) (numberOfProducts / 20)
    } else {
        waitFor {
            Integer.parseInt(specsaversAdminReportingPage.bottomPaginationPageNumberBasic.last().text()) == (int) (numberOfProducts / 20 + 1)
        }
        assert Integer.parseInt(specsaversAdminReportingPage.bottomPaginationPageNumberBasic.last().text()) == (int) (numberOfProducts / 20 + 1)
    }
}
When(~/^User use some bottom pagination page number on specsavers awarded section$/) { ->
    if (Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', '')) > 20) {
        random = 1
        specsaversAdminReportingPage.clickChosenPageNumber(random)
        sleep(1000)
    }
}
Then(~/^proper page will be displayed on specsavers awarded section$/) { ->
    def outOfNumber = Integer.parseInt(specsaversAdminReportingPage.outOfElement.text().replace('(out of ', '').replace(')', ''))
    if (outOfNumber > 20) {
        if (outOfNumber > 40) {
            waitFor {
                specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains((random + 1).toString())
            }
            waitFor {
                specsaversAdminReportingPage.showingElement.text().contains('Showing ' + (random * 20 + 1).toString() + " - " + ((random + 1) * 20).toString())
            }
            assert specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains((random + 1).toString())
            assert specsaversAdminReportingPage.showingElement.text().contains('Showing ' + (random * 20 + 1).toString() + " - " + ((random + 1) * 20).toString())
        } else {
            waitFor {
                specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains((random + 1).toString())
            }
            waitFor {
                specsaversAdminReportingPage.showingElement.text().contains('Showing ' + (random * 20 + 1).toString() + " - " + outOfNumber)
            }
            assert specsaversAdminReportingPage.bottomPaginationPageNumberActiveBasic.text().contains((random + 1).toString())
            assert specsaversAdminReportingPage.showingElement.text().contains('Showing ' + (random * 20 + 1).toString() + " - " + outOfNumber)
        }
    }
}

// 13.4 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
// ------------------------------------------------------------- bottom pagination, working of next/previous buttons
When(~/^User click next page bottom button on specsavers awarded section$/) { ->
    specsaversAdminReportingPage.clickNextBottomPageButton()
}
When(~/^User click previous page bottom button on specsavers awarded section$/) { ->
    sleep(1000)
    specsaversAdminReportingPage.clickPreviousBottomPageButton()
}

// 13.5 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
// ---------------------------------------------------------------------------------- top pagination, items per page
When(~/^User change 'item per page' parameter on specsavers awarded section$/) { ->
    waitFor { specsaversAdminReportingPage.tableRowStatic[0].isDisplayed() }
    checkNumberOfElementsOnPage(50) // by default
    specsaversAdminReportingPage.clickItemPerPage20(); sleep(2000)
    waitFor { specsaversAdminReportingPage.tableRowStatic[0].isDisplayed() }
    checkNumberOfElementsOnPage(20)
    specsaversAdminReportingPage.clickItemPerPage100(); sleep(2000)
    waitFor { specsaversAdminReportingPage.tableRowStatic[0].isDisplayed() }
    checkNumberOfElementsOnPage(100)
    specsaversAdminReportingPage.clickItemPerPageAll(); sleep(2000)
    waitFor { specsaversAdminReportingPage.tableRowStatic[0].isDisplayed() }
    int rowNumber = Integer.parseInt(mySQLConnector.getSingleResult("SELECT COUNT(*) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '" + year + "-" + startMonth + "-" + startDay + "' AND '" + year + "-" + endMonth + "-" + endDay + " 23:59:59" + "'"))
    checkNumberOfElementsOnPage(rowNumber)
}
Then(~/^Number of displayed elements on specsavers awarded section will be changed$/) { ->
    // proper assertions made in previous step to check all three cases in one test
}

def checkNumberOfElementsOnPage(int number) {
    waitFor { $('.pagination-summary').find('.pagination-totalItems').isDisplayed() }
    int outOfValue = Integer.parseInt($('.pagination-summary').find('.pagination-totalItems').text().replace("(out of ", "").replace(")", ""))
    if (number <= outOfValue) {
        waitFor { ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == number) }
        assert ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == number)
    } else {
        waitFor { ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == outOfValue) }
        assert ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == outOfValue)
    }
}

// 14.1 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
// ------------------------------------------------------------------------------------------- new store tab content
Given(~/^Specsavers store tab is opened$/) { ->
    to SpecsaversAdminPage
    specsaversAdminPage = page
    ((JavascriptExecutor) browser.getDriver()).executeScript("window.focus();")
    specsaversAdminPage.enterLoginData(envVar.specsaverUser, envVar.specsaversPassword)
    specsaversAdminPage.clickSignInButton()
    waitFor { specsaversAdminPage.tabNaviagationModule.storeTab.isDisplayed() }
    specsaversAdminPage.tabNaviagationModule.clickStoreTab()
    at SpecsaversAdminStorePage
    specsaversAdminStorePage = page
}
When(~/^User looks on specsavers store tab$/) { ->
    waitFor { specsaversAdminStorePage.storeHeader.text() == envVar.specsaversStoreHeader }
    assert specsaversAdminStorePage.storeHeader.text() == envVar.specsaversStoreHeader
}
Then(~/^He can see store name input field with proper label$/) { ->
    waitFor { specsaversAdminStorePage.storeNameLabel.text() == envVar.specsaversStoreNameLabel }
    assert specsaversAdminStorePage.storeNameLabel.text() == envVar.specsaversStoreNameLabel
    assert specsaversAdminStorePage.storeNameInputField.isDisplayed()
}
Then(~/^He can see email input field with proper label$/) { ->
    waitFor { specsaversAdminStorePage.storeEmailLabel.text() == envVar.specsaversStoreEmailLabel }
    assert specsaversAdminStorePage.storeEmailLabel.text() == envVar.specsaversStoreEmailLabel
    assert specsaversAdminStorePage.storeEmailInputField.isDisplayed()
}
Then(~/^Cancel and send button is properly displayed$/) { ->
    assert specsaversAdminStorePage.clearNewStoreDataButton.isDisplayed()
    assert specsaversAdminStorePage.sendNewStoreButton.isDisplayed()
}

// 14.2 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
// ------------------------------------------------------------------------------------- wrong email structure alert
When(~/^User provide not correct email$/) { ->
    specsaversAdminStorePage.enterNewStoreEmail(envVar.testUserEmailInvalid)
}
Then(~/^Email is incorrect alert will be displayed under new store email field$/) { ->
    waitFor { specsaversAdminStorePage.nameEmailValidErrorBasic[2].isDisplayed() }
    assert specsaversAdminStorePage.nameEmailValidErrorBasic[2].text() == envVar.specsaversStoreInvalidEmailAlert
}

// 14.3 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
// ----------------------------------------------------------------------------------------------------- taken email
def newStoreName
When(~/^User provide new store email which is already used$/) { ->
    specsaversAdminStorePage.enterNewStoreEmail(Config.epointsUser)
}
When(~/^User provide new store name$/) { ->
    specsaversAdminStorePage.enterNewStoreName(newStoreName = (envVar.newStoreName + func.returnRandomValue(1000000)))
}
When(~/^User clicks send button on new store form$/) { ->
    specsaversAdminStorePage.clickSendButton()
}
Then(~/^Email is already in use alert will be displayed under new store email field$/) { ->
    waitFor { specsaversAdminStorePage.sendAlertDanger.isDisplayed() }
    assert specsaversAdminStorePage.sendAlertDanger.text() == envVar.specsaversStoreEmailAlreadyInUseAlert
}

// 14.4 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
// ------------------------------------------------------------------------------------------------ taken store name
def newStoreEmail
When(~/^User provide new store email$/) { ->
    specsaversAdminStorePage.enterNewStoreEmail(newStoreEmail = (envVar.newStoreEmail + func.returnRandomValue(1000000)) + '@wp.pl')
}
When(~/^User provide new store name which is already used$/) { ->
    specsaversAdminStorePage.enterNewStoreName(envVar.testUserSurname)
}
Then(~/^Store name is already in use alert will be displayed under new store email field$/) { ->
    waitFor { specsaversAdminStorePage.sendAlertDanger.isDisplayed() }
    assert specsaversAdminStorePage.sendAlertDanger.text() == envVar.specsaversStoreNameAlreadyInUseAlert
}

// 14.5 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
// ------------------------------------------------------------------------------------------------ clear all button
//TODO finish when bug will be fixed
When(~/^User click clear new store data button$/) { ->
    specsaversAdminStorePage.clickClearButton()
}
Then(~/^New store input fields will be cleared$/) { ->
    waitFor { specsaversAdminStorePage.storeNameInputField.value() == '' }
    assert specsaversAdminStorePage.storeNameInputField.value() == ''
    assert specsaversAdminStorePage.storeEmailInputField.value() == ''
}

// 14.6 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
// ------------------------------------------------------------------------------------------ correct store creation
Then(~/^New store \(points manager user\) will be properly created$/) { ->
    //TODO need to add validation of all .User elements
    waitFor {
        new UserRepositoryImpl().findByEmail(newStoreEmail.toLowerCase()).getEmail() == newStoreEmail.toLowerCase()
    }
}
Then(~/^For new user will get '(.+?)' points for registration$/) { String registrationPointsValue ->
    assert mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(newStoreEmail.toLowerCase()).getUuid() + "'") == registrationPointsValue
    //func.deleteUserFromPointsManager(newStoreEmail)
}