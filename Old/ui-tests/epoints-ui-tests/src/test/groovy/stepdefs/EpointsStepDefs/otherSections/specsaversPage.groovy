package stepdefs.EpointsStepDefs.otherSections

import cucumber.api.java.After
import geb.Browser
import mysqlConnection.jdbc
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables
import dataProvider.dataProvider

import java.awt.*
import java.awt.event.KeyEvent
import java.security.Key

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-02-05.
 */

def epoints = new epointsPage()

def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()

    // 1.1 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
    // ---------------------------------------------------------------------------------------------- login page content
    Given(~/^Specsavers login page is opened$/) { ->
        epointsPage.url= envVar.specsaversURL
        to epointsPage
        epoints = page
        ((JavascriptExecutor) browser.getDriver()).executeScript("window.focus();")
    }
    When(~/^User looks on login page$/) { ->
        //leave empty
    }
    Then(~/^He can see that specsavers login page contains all needed elements$/) { ->
        waitFor{ epoints.specsaversPage.mainHeader.text() == envVar.specsaversLoginHeader }
        assert epoints.specsaversPage.mainHeader.text() == envVar.specsaversLoginHeader
        assert epoints.specsaversPage.emailLabel.text() == envVar.specsaversEmailLabel
        assert epoints.specsaversPage.passwordLabel.text() == envVar.specsaversPasswordLabel
        assert epoints.specsaversPage.emailInputField.isDisplayed()
        assert epoints.specsaversPage.passwordInputField.isDisplayed()
        assert epoints.specsaversPage.signInButton.isDisplayed()
        assert epoints.specsaversPage.emailInputField.attr('placeholder') == envVar.specsaversEmailPlaceholder
        assert epoints.specsaversPage.passwordInputField.attr('placeholder') == envVar.specsaversPasswordPlaceholder
    }

    // 1.2 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
    // ------------------------------------------------------------------------------------------- required input alerts
    When(~/^User type some login data and remove them$/) { ->
        epoints.specsaversPage.enterLoginData('something','something')
        epoints.specsaversPage.enterLoginData('','')
    }
    Then(~/^Required alert will be displayed for both inputs$/) { ->
        waitFor{ epoints.specsaversPage.emailError.isDisplayed() }
        waitFor{ epoints.specsaversPage.passwordError.isDisplayed() }
        assert epoints.specsaversPage.emailError.text() == envVar.specsaversEmailIsRequiredAlert
        assert epoints.specsaversPage.passwordError.text() == envVar.specsaversPasswordIsRequireAlert
    }

    // 1.3 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
    // --------------------------------------------------------------------------------------------------- invalid email
    When(~/^User type invalid email into email field$/) { ->
        epoints.specsaversPage.enterEmail(envVar.testUserEmailInvalid)
    }
    Then(~/^Email is invalid alert will be displayed under email input$/) { ->
        waitFor{ epoints.specsaversPage.emailError.isDisplayed() }
        assert epoints.specsaversPage.emailError.text() == envVar.specsaversEmailIsInvalidAlert
    }

    // 1.4 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
    // -------------------------------------------------------------------------------------------- not registered email
    When(~/^User type not registered email into email field$/) { ->
        epoints.specsaversPage.enterLoginData(envVar.testUserEmailNotActivated,envVar.testUserPassword)
    }
    Then(~/^Authorization alert will be displayed under password input$/) { ->
        waitFor{ epoints.specsaversPage.authorizationError.isDisplayed() }
        assert epoints.specsaversPage.authorizationError.text() == envVar.specsaversAuthorizationAlert
    }

    // 1.5 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
    // ---------------------------------------------------------------------------------------------------- proper login
    When(~/^User type correct specsaver user login data$/) { ->
        epoints.specsaversPage.enterLoginData(envVar.specsaverUser, envVar.specsaversPassword)
    }
    When(~/^Click sign in button on specsaver login page$/) { ->
        epoints.specsaversPage.clickSignInButton()
    }
    Then(~/^He will be properly logged into admin panel$/) { ->
        waitFor{ epoints.specsaversPage.mainHeader.text() == envVar.specsaversControlPanelHeader }
        waitFor{ epoints.specsaversPage.awardPointsTab.isDisplayed() }
        assert epoints.specsaversPage.mainHeader.text() == envVar.specsaversControlPanelHeader
        assert epoints.specsaversPage.awardPointsTab.isDisplayed()
    }
    When(~/^User click sign out button in specsavers control panel$/) { ->
        epoints.specsaversPage.clickSignOutButton()
    }
    Then(~/^He will be properly logged out form specsavers control panel$/) { ->
        waitFor{ epoints.specsaversPage.mainHeader.text() == envVar.specsaversLoginHeader }
        waitFor{ epoints.specsaversPage.emailInputField.isDisplayed() }
        assert epoints.specsaversPage.mainHeader.text() == envVar.specsaversLoginHeader
        assert epoints.specsaversPage.emailInputField.isDisplayed()
    }

    // 2.1 //  ------------------------------------------------------------------------------ SPECSAVERS - control panel
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Specsavers control panel is opened$/) { ->
        epointsPage.url= envVar.specsaversURL
        to epointsPage
        epoints = page
        ((JavascriptExecutor) browser.getDriver()).executeScript("window.focus();")
        epoints.specsaversPage.enterLoginData(envVar.specsaverUser, envVar.specsaversPassword)
        epoints.specsaversPage.clickSignInButton()
        waitFor{ epoints.specsaversPage.awardPointsTab.isDisplayed() }
    }
    When(~/^User look on specsavers control panel$/) { ->
        //leave empty
    }
    Then(~/^He can see that specsavers control panel contains all needed elements$/) { ->
        waitFor{ epoints.specsaversPage.awardPointsButton.isDisplayed() }
        assert epoints.specsaversPage.awardPointsTab.text() == envVar.specsaversAwardPointsTab
        assert epoints.specsaversPage.importTab.text() == envVar.specsaversImportTab
        assert epoints.specsaversPage.selectBranchesLabel.text() == envVar.specsaversSelectBranchesLabel
        assert epoints.specsaversPage.selectAllCheckbox.isDisplayed()
        assert epoints.specsaversPage.pickBranchInput.isDisplayed()
        assert epoints.specsaversPage.pickBranchInput.attr('placeholder') == envVar.specsaversPickBranchesPlaceholder
        assert epoints.specsaversPage.reasonForAwardingLabel.text() == envVar.specsaversReasonForAwardingLabel
        assert epoints.specsaversPage.reasonForAwardingInput.isDisplayed()
        assert epoints.specsaversPage.reasonForAwardingInput.attr('placeholder') == envVar.specsaversReasonForAwardingPlaceholder
        assert epoints.specsaversPage.pointsToAwardLabel.text() == envVar.specsaversPointsToAwardLabel
        assert epoints.specsaversPage.pointsToAwardInput.isDisplayed()
        assert epoints.specsaversPage.pointsCounter.isDisplayed()
        assert epoints.specsaversPage.clearButton.isDisplayed()
        assert epoints.specsaversPage.awardPointsButton.isDisplayed()
    }

    // 2.2 //  ------------------------------------------------------------------------------ SPECSAVERS - control panel
    // ------------------------------------------------------------------------------------------------- required alerts
    When(~/^User select branch$/) { ->
        Thread.sleep(2000)
        epoints.specsaversPage.clickSelectBranchInputField()
        epoints.specsaversPage.clickChosenBranchOption(0)
    }
    When(~/^Remove selected branch$/) { ->
        epoints.specsaversPage.clickAwardPointsTab()
        epoints.specsaversPage.removeChosenBranchOption(0)
    }
    When(~/^Enter reason for awarding$/) { ->
        epoints.specsaversPage.clickAwardPointsTab()
        Thread.sleep(2000)
        epoints.specsaversPage.clickReasonForAwardingInput()
        epoints.specsaversPage.enterPhraseIntoReasonForAwardinInput('reason')
        epoints.specsaversPage.reasonForAwardingInput << Keys.chord(Keys.ENTER)
    }
    When(~/^Remove reason for awarding$/) { ->
        epoints.specsaversPage.clickReasonForAwardingDDL()
        Thread.sleep(1000)
        epoints.specsaversPage.reasonForAwardingInput << Keys.chord(Keys.BACK_SPACE)
        epoints.specsaversPage.clickReasonForAwardingDDL()
        epoints.specsaversPage.clickAwardPointsTab()
    }
    When(~/^Enter some points to award value$/) { ->
        epoints.specsaversPage.enterValueIntoPointsToAwardInput('1')
    }
    When(~/^Remove points to award value$/) { ->
        epoints.specsaversPage.enterValueIntoPointsToAwardInput('')
    }
    Then(~/^Under each section should appear field is required alert$/) { ->
        waitFor{ epoints.specsaversPage.basicAlert[0].isDisplayed() }
        waitFor{ epoints.specsaversPage.basicAlert[1].isDisplayed() }
        waitFor{ epoints.specsaversPage.basicAlert[2].isDisplayed() }
        assert epoints.specsaversPage.basicAlert[0].text() == envVar.specSaversPickBranchesIsRequiredAlert
        assert epoints.specsaversPage.basicAlert[1].text() == envVar.specsaversReasonForAwardingPointsIsRequiredAlert
        assert epoints.specsaversPage.basicAlert[2].text() == envVar.specsaversPointsToAwardIsRequiredAlert
    }

    // 3.1 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
    // ----------------------------------------------------------------------------------------------- select all button
    When(~/^User use select all checkbox$/) { ->
        epoints.specsaversPage.clickSelectAllCheckbox()
    }
    Then(~/^pick Branches Input Field will be disabled$/) { ->
        epoints.specsaversPage.clickAwardPointsTab()
        assert !epoints.specsaversPage.pickBranchOption.isDisplayed()
    }

    // 3.2 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
    // ------------------------------------------------------------------------------------- selecting/removing branches
    String selectedbranch1, selectedbranch2
    When(~/^User want to select a branch$/) { ->
        //leave empty
    }
    Then(~/^He can select few branches manually$/) { ->
        Thread.sleep(2000)
        epoints.specsaversPage.clickSelectBranchInputField()
        selectedbranch1 = epoints.specsaversPage.pickBranchOption[1].text()
        selectedbranch2 = epoints.specsaversPage.pickBranchOption[0].text()
        epoints.specsaversPage.clickChosenBranchOption(1)
        epoints.specsaversPage.clickChosenBranchOption(0)
    }
    Then(~/^See that branches were properly added to selected list$/) { ->
        assert epoints.specsaversPage.pickBranchChosenOptionBasic[0].text().replace("\n","").replace("×","") == selectedbranch1
        assert epoints.specsaversPage.pickBranchChosenOptionBasic[1].text().replace("\n","").replace("×","") == selectedbranch2
    }
    When(~/^User want to remove branches$/) { ->
        //leave empty
    }
    Then(~/^He can remove few selected branches manually$/) { ->
        int selectedBranchesSize = epoints.specsaversPage.pickBranchChosenOptionXButtonBasic.size()
        for(int i=0; i<selectedBranchesSize; i++){
            epoints.specsaversPage.removeChosenBranchOption(selectedBranchesSize-1-i)
        }
    }
    Then(~/^See that branches were properly removed from selected list$/) { ->
        waitFor{ !epoints.specsaversPage.pickBranchChosenOptionBasic.isDisplayed() }
        assert !epoints.specsaversPage.pickBranchChosenOptionBasic.isDisplayed()
    }

    // 4.1 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
    //-------------------------------------------------------------------------------------- selecting/removing branches
    When(~/^User type not number into points to award input field$/) { ->
        epoints.specsaversPage.enterValueIntoPointsToAwardInput('not number value')
    }
    Then(~/^Points to award is invalid alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage.basicAlert[2].isDisplayed() }
        assert epoints.specsaversPage.basicAlert[2].text() == envVar.specsaversPointsToAwardIsInvalidAlert
    }

    // 3.3 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
    // ----------------------------------------------------------------------------------------- correctness of branches
    When(~/^Expand branches DDL$/) { ->
        Thread.sleep(2000)
        epoints.specsaversPage.clickSelectBranchInputField()
    }
    Then(~/^User can see that set of branches is properly returned$/) { ->
        def mysql = new jdbc("points")
        assert Integer.toString(epoints.specsaversPage.pickBranchOption.size()) == mysql.get("SELECT COUNT(*) FROM points_manager.User WHERE registrationSiteId = '100000' AND active = '1'",1)
        waitFor{ epoints.specsaversPage.pickBranchOption.isDisplayed() }
        for(int i=0; i <epoints.specsaversPage.pickBranchOption.size(); i++){
            assert epoints.specsaversPage.pickBranchOption[i].text() == mysql.get("SELECT lastName FROM points_manager.User WHERE registrationSiteId = '100000' AND lastName = '"+epoints.specsaversPage.pickBranchOption[i].text()+"'",1)
        }
        mysql.close()
    }

    // 4.2 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
    // ------------------------------------------------------------------------------------------------- points counting
    int random
    When(~/^User enter proper £ value into points to award input field$/) { ->
        random = func.returnRandomValue(10000)
        epoints.specsaversPage.enterValueIntoPointsToAwardInput(random)
    }
    Then(~/^This value will be properly changed int epoints$/) { ->
        waitFor{ (random*200 == Integer.parseInt(epoints.specsaversPage.pointsCounter.text()))  }
        assert random*200 == Integer.parseInt(epoints.specsaversPage.pointsCounter.text())
    }

    // 4.3 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
    // ----------------------------------------------------------------------------------------- modal content/no button
    String chosenBranch
    String chosenReason
    String pointsValue
    Given(~/^Branches are chosen$/) { ->
        Thread.sleep(2000)
        epoints.specsaversPage.clickSelectBranchInputField()
        waitFor{ epoints.specsaversPage.pickBranchOption[0].isDisplayed() }
        chosenBranch = epoints.specsaversPage.pickBranchOption[0].text()
        epoints.specsaversPage.clickChosenBranchOption(0)
    }
    Given(~/^Reason for awarding points is chosen$/) { ->
        chosenReason = 'automated test reason reason'+func.returnRandomValue(1000000)
        epoints.specsaversPage.clickAwardPointsTab()
        epoints.specsaversPage.clickReasonForAwardingInput()
        epoints.specsaversPage.enterPhraseIntoReasonForAwardinInput(chosenReason)
        epoints.specsaversPage.reasonForAwardingInput << Keys.chord(Keys.ENTER)
    }
    Given(~/^Points to award value is entered$/) { ->
        random = func.returnRandomValue(3)+1
        pointsValue = Integer.toString(random)
        epoints.specsaversPage.enterValueIntoPointsToAwardInput(pointsValue)
    }
    When(~/^User click award points button$/) { ->
        waitFor{ !(epoints.specsaversPage.awardPointsButton.attr('disabled') == 'disabled') }
        epoints.specsaversPage.clickAwardPointsButton()
    }
    Then(~/^Confirm points modal window with proper content will be displayed$/) { ->
        waitFor{ epoints.specsaversPage.confirmationModal.isDisplayed() }
        assert epoints.specsaversPage.confirmationModal.isDisplayed()
        assert epoints.specsaversPage.confirmModalHeader.text() == envVar.specsaversConfirmModalHeader
        assert epoints.specsaversPage.confirmModalInfo.text().replace("\n","") == envVar.returnSpecsaversConfirmModalMainText('£'+random+' - '+random*200+' points','1', Integer.toString(random*200))
        assert epoints.specsaversPage.confirmModalYesButton.isDisplayed()
        assert epoints.specsaversPage.confirmModalNoButton.isDisplayed()
    }
    When(~/^User click no button in confirm modal window$/) { ->
        epoints.specsaversPage.clickNoButtonInConfirmPointsModalWindow()
    }
    Then(~/^Confirm points modal window will be closed$/) { ->
        waitFor{ !epoints.specsaversPage.confirmationModal.isDisplayed() }
        //assert !epoints.specsaversPage.confirmationModal.isDisplayed()
    }

    // 5.1 //  -------------------------------------------------- SPECSAVERS - admin page - reason for awarding(NBO-742)
    // ------------------------------------------------------------------------------------------ correctness of reasons
    When(~/^Expand reason for awarding DDL$/) { ->
        Thread.sleep(2000)
        epoints.specsaversPage.clickReasonForAwardingDDL()
        waitFor{ epoints.specsaversPage.reasonForAwardingOption.isDisplayed() }
    }
    Then(~/^User can see that set of reasons is properly returned$/) { ->
        def mysql = new jdbc("points")
        assert Integer.toString(epoints.specsaversPage.reasonForAwardingOption.size()) == mysql.get("SELECT COUNT(DISTINCT(activityInfo)) FROM points_manager.Points WHERE partnerId = '100000'",1)
            waitFor{ epoints.specsaversPage.reasonForAwardingOption.isDisplayed() }
            for(int i=0; i <epoints.specsaversPage.reasonForAwardingOption.size(); i++){
                if(epoints.specsaversPage.reasonForAwardingOption[i].text().contains('\"')){
                    continue
                }
                assert epoints.specsaversPage.reasonForAwardingOption[i].text() == mysql.get("SELECT activityInfo FROM points_manager.Points WHERE partnerId = '100000' AND activityInfo = \""+epoints.specsaversPage.reasonForAwardingOption[i].text()+"\"",1)
            }
        mysql.close()
    }
    @After('@automatedTestsReasonsAreDeletedAfter')
    public void setHighEpointsValue(){
        def mysql = new jdbc('points')
        mysql.update("DELETE FROM points_manager.Points WHERE activityInfo LIKE '%automated%'")
        mysql.close()
    }

    // 6.1 //  ------------------------------------------------- SPECSAVERS - award points to selected branches(NBO-744)
    // ------------------------------------------------------------------ correct points award, checking database update
    When(~/^User confirm points award by clicking yes button in confirmation modal$/) { ->
        epoints.specsaversPage.clickYesButtonInConfirmPointsModalWindow()
    }
    Then(~/^Award success message will be displayed$/) { ->
       waitFor{ epoints.specsaversPage.successMessage.isDisplayed() }
       assert epoints.specsaversPage.successMessage.isDisplayed()
       waitFor{ !epoints.specsaversPage.successMessage.isDisplayed() }
       assert !epoints.specsaversPage.successMessage.isDisplayed()
    }
    And(~/^Points will be properly awarded to chosen branches$/) { ->
        def mysql = new jdbc('points')
            waitFor{ mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC",1) == chosenReason }
            assert mysql.get("SELECT lastName FROM points_manager.User WHERE id = '"+mysql.get("SELECT userId FROM points_manager.Points ORDER BY id DESC",1)+"'",1) == chosenBranch
            assert mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC",1) == chosenReason
            assert mysql.get("SELECT delta FROM points_manager.Points ORDER BY id DESC",1) == Integer.toString(Integer.parseInt(pointsValue)*200)
            assert mysql.get("SELECT onBehalfOfId FROM points_manager.Points ORDER BY id DESC",1) == envVar.specsaversPartnerId
            assert mysql.get("SELECT externalTransactionId FROM points_manager.Points ORDER BY id DESC",1) == envVar.specsaverUser
        mysql.close()
    }

    // 7.1 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // ----------------------------------------------------------------------------------------- bulk upload tab content
    Given(~/^Import tab is selected$/) { ->
        epoints.specsaversPage.clickImportTab()
    }
    When(~/^User look on import tab$/) { ->
        //leave empty
    }
    Then(~/^He will see field for file selection$/) { ->
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        assert epoints.specsaversPage.bulkUploadInputVisual.text() == envVar.bulkUploadDropBoxText
    }
    Then(~/^He will see import button$/) { ->
        waitFor{ epoints.specsaversPage.importButton.isDisplayed() }
        assert epoints.specsaversPage.importButton.isDisplayed()
    }

    // 7.2 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    //--------------------------------------------------------------------------------------------------- correct upload
    Object[][] dataxls
    When(~/^User provide file with correct data$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//correct.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    When(~/^Click import button$/) { ->
        waitFor{ !epoints.specsaversPage.importButton.attr('disabled')}
        epoints.specsaversPage.clickImportButton()
    }
    Then(~/^All file rows will be properly uploaded$/) { ->
        waitFor{ epoints.specsaversPage.uploadAlertSuccess.isDisplayed() }
        assert epoints.specsaversPage.uploadAlertSuccess.text() == envVar.bulkUploadAlertSuccess
        def mysql = new jdbc('points')
        waitFor(10){ mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC",1) == dataxls[1][2] }
            assert mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC",1) == dataxls[1][2]
            assert mysql.get("SELECT delta FROM points_manager.Points ORDER BY id DESC",1) == ((int)((dataxls[1][3])*200)).toString()
            assert mysql.get("SELECT onBehalfOfId FROM points_manager.Points ORDER BY id DESC",1) == '100000'
            assert mysql.get("SELECT externalTransactionId FROM points_manager.Points ORDER BY id DESC",1) == dataxls[1][4]
            assert mysql.get("SELECT userID FROM points_manager.Points ORDER BY id DESC",1) == mysql.get("SELECT id FROM points_manager.User WHERE email = '"+dataxls[1][1]+"'",1)
            assert mysql.get("SELECT lastName FROM points_manager.User WHERE email = '"+dataxls[1][1]+"'",1) == dataxls[1][0]
        mysql.close()
    }

    // 7.3 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // --------------------------------------------------------------------------------------------- incorrect file type
    When(~/^User provide file with incorrect data$/) { ->
        String fileAbsolutePath = new File('src//data files//wrongFormat.docx').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Incorrect file format alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage. uploadAlerDanger.text() == envVar.bulkUploadWrongFormatAlert
    }

    // 7.4 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // ---------------------------------------------------------------------------------------------------- missing name
    When(~/^User provide file with missing name$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//missingName.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Missing name alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadMissingNameAlert.replace("\n","").replace(" ","")
    }

    // 7.5 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // --------------------------------------------------------------------------------------------------- missing email
    When(~/^User provide file with missing email$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//missingEmail.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Missing email alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadMissingEmailAlert.replace("\n","").replace(" ","")
    }

    // 7.6 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // -------------------------------------------------------------------------------------------------- missing reason
    When(~/^User provide file with missing reason$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//missingReason.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Missing reason alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadMissingReasonAlert.replace("\n","").replace(" ","")
    }

    // 7.7 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // -------------------------------------------------------------------------------------------------- missing amount
    When(~/^User provide file with missing amount$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//missingAmount.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Missing amount alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadMissingAmountAlert.replace("\n","").replace(" ","")
    }

    // 7.8 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // --------------------------------------------------------------------------------------------------- missing admin
    When(~/^User provide file with missing admin$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//missingAdmin.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Missing admin alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadMissingAdminAlert.replace("\n","").replace(" ","")
    }

    // 7.9 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // ----------------------------------------------------------------------------------------------------- wrong email
    When(~/^User provide file with wrong email$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//wrongEmail.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Wrong email alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadWrongEmailAlert.replace("\n","").replace(" ","")
    }

    // 7.10 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // ------------------------------------------------------------------------------------------------------ low amount
    When(~/^User provide file with too low amount$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//tooLowAmount.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Wrong too low amount alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadTooLowAmountAlert.replace("\n","").replace(" ","")
    }

    // 7.11 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // ------------------------------------------------------------------------------------------------ very long reason
    When(~/^User provide file with very long reason$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//tooLongReason.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Points will be properly awarded and reason truncated to 255 signs$/) { ->
        def mysql = new jdbc('points')
            waitFor(10){ mysql.get("SELECT externalTransactionId FROM points_manager.Points ORDER BY id DESC",1) == dataxls[1][4] }
            assert mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY id DESC",1).size() == 255
        mysql.close()
    }

    // 7.12 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
    // ------------------------------------------------------------------------------------------------ incorrect header
    When(~/^User provide file with incorrect header$/) { ->
        def dataProv = new dataProvider()
        dataxls = dataProv.returndata('src//data files//correct.xls')
        String fileAbsolutePath = new File('src//data files//wrongHeader.xls').getAbsolutePath().replace('\\','\\\\');
        waitFor{ epoints.specsaversPage.bulkUploadInputVisual.isDisplayed() }
        epoints.specsaversPage.clickOnImportFileButton()
        Thread.sleep(500)
        epoints.specsaversPage.bulkUploadInput = fileAbsolutePath
    }
    Then(~/^Wrong header alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage. uploadAlerDanger.isDisplayed() }
        assert epoints.specsaversPage.uploadAlerDanger.text().replace("\n","").replace(" ","") == envVar.bulkUploadWrongHeaderAlert.replace("\n","").replace(" ","")
    }

    // 8.1 //  ------ SPECSAVERS - create the reporting tab within Specsavers admin UI adding "view" structure(NBO-1612)
    // ------------------------------------------------------------------------------------------- reporting tab content
    When(~/^User click on reporting tab$/) { ->
        epoints.specsaversPage.clickReportingTab()
    }
    Then(~/^Reporting tab will be opened$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingURL
    }
    Then(~/^Reporting tab contains proper elements$/) { ->
        assert epoints.specsaversPage.startDateLabel.text() == envVar.specsaversReportsTabStartDateLabel
        assert epoints.specsaversPage.endDateLabel.text() == envVar.specsaversReportsTabEndDateLabel
        assert epoints.specsaversPage.startDateInputField.isDisplayed()
        assert epoints.specsaversPage.endDateInputField.isDisplayed()
        assert epoints.specsaversPage.startDateInputField.isDisplayed()
        assert epoints.specsaversPage.endDateInputField.isDisplayed()
        assert epoints.specsaversPage.generateReportButton.isDisplayed()
        assert epoints.specsaversPage.overviewSectionTab.text() == envVar.specsaversReportsTabOverviewSection
        assert epoints.specsaversPage.epointsAwardedSectionTab.text() == envVar.specsaversReportsTabAwardedSection
        assert epoints.specsaversPage.epointsRedeemedSectionTab.text() == envVar.specsaversReportsTabRedeemedSection
        assert epoints.specsaversPage.downloadReportTab.isDisplayed()
    }

    // 9.1 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
    // -------------------------------------------------------------------------------------------- default dates values
    Then(~/^Dates fields will be automatically filled with default values$/) { ->
        waitFor{ epoints.specsaversPage.startDateInputField.isDisplayed() }
        waitFor{ epoints.specsaversPage.endDateInputField.isDisplayed() }
        Calendar calFirstDay = Calendar.getInstance();
        Calendar calCurrentDay = Calendar.getInstance();
        calFirstDay.set(Calendar.DAY_OF_MONTH, 1);

        def epochOfFirstMonthDayDiff = (calFirstDay.getTimeInMillis() - func.convertDateToEpochFormat(epoints.specsaversPage.startDateInputField.value() ,'dd-MM-yyyy'))
        def epochOfCurrentDayDiff = (calCurrentDay.getTimeInMillis() - func.convertDateToEpochFormat(epoints.specsaversPage.endDateInputField.value() ,'dd-MM-yyyy'))

        assert (epochOfFirstMonthDayDiff < 86400000 && epochOfFirstMonthDayDiff > 0)//day  in epoch time
        assert (epochOfCurrentDayDiff < 86400000 && epochOfCurrentDayDiff > 0)
    }

    // 9.2 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
    // ---------------------------------------------------------------------------------------------- dates between tabs
    Given(~/^Specsavers control panel reports tab is opened$/) { ->
        epointsPage.url= envVar.specsaversURL
        to epointsPage
        epoints = page
        ((JavascriptExecutor) browser.getDriver()).executeScript("window.focus();")
        epoints.specsaversPage.enterLoginData(envVar.specsaverUser, envVar.specsaversPassword)
        epoints.specsaversPage.clickSignInButton()
        waitFor{ epoints.specsaversPage.awardPointsTab.isDisplayed() }
        epoints.specsaversPage.clickReportingTab()
    }
    When(~/^User will navigate between three available report tabs$/) { ->
        waitFor{ epoints.specsaversPage.startDateInputField.isDisplayed() }
        waitFor{ epoints.specsaversPage.endDateInputField.isDisplayed() }
        String startDate = epoints.specsaversPage.startDateInputField.value()
        String endDate = epoints.specsaversPage.endDateInputField.value()

        epoints.specsaversPage.openOverviewSection()
        assert startDate == epoints.specsaversPage.startDateInputField.value()
        assert endDate == epoints.specsaversPage.endDateInputField.value()
        epoints.specsaversPage.openAwardedSection()
        assert startDate == epoints.specsaversPage.startDateInputField.value()
        assert endDate == epoints.specsaversPage.endDateInputField.value()
        epoints.specsaversPage.openRedeemedSection()
        assert startDate == epoints.specsaversPage.startDateInputField.value()
        assert endDate == epoints.specsaversPage.endDateInputField.value()
    }
    Then(~/^Dates will stay the same$/) { ->
        //done in previous step
    }


    // 9.3 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
    // ------------------------------------------------------------------------------------------------- dates between tabs
    When(~/^User open calendar picker$/) { ->
        epoints.specsaversPage.openStartDateCalendar()
        waitFor{ epoints.specsaversPage.calendar(0).isDisplayed() }
    }
    When(~/^Click '(.+?)' on calendar view$/) { String buttonType ->
        if(buttonType == 'today'){
            epoints.specsaversPage.clickCalendarTodayButton()
        }else if(buttonType == 'clear'){
            epoints.specsaversPage.clickCalendarClearButton()
        }else if(buttonType == 'close'){
            epoints.specsaversPage.clickCalendarCloseButton()
        }
    }

    Then(~/^Proper action will be executed after clicking '(.+?)'$/) { String buttonType ->
        if(buttonType == 'today'){
            assert (func.returnEpochOfCurrentDay() - func.convertDateToEpochFormat(epoints.specsaversPage.startDateInputField.value() ,'dd-MM-yyyy')) < 86400000
        }else if(buttonType == 'clear'){
            assert epoints.specsaversPage.startDateInputField.value() == ''
        }else if(buttonType == 'close'){
            waitFor{ !epoints.specsaversPage.calendar(0).isDisplayed() }
            assert !epoints.specsaversPage.calendar(0).isDisplayed()
        }
    }

    // 9.4 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
    // ------------------------------------------------------------------------------------------ choosing calendar date
    String chosenDay
    When(~/^User chose some date from calendar$/) { ->
        waitFor{ epoints.specsaversPage.calendarDayBasic.isDisplayed() }
        random = func.returnRandomValue(42)
        while( epoints.specsaversPage.calendarDayBasic[random].attr('disabled') == 'disabled'){
            random = func.returnRandomValue(42)
        }
        chosenDay = epoints.specsaversPage.calendarDayBasic[random].text()
        epoints.specsaversPage.clickCalendarChosenDayButton(random)
        Thread.sleep(5000)
    }
    Then(~/^Field will be filled with proper date$/) { ->
        waitFor{ epoints.specsaversPage.startDateInputField.value().contains(chosenDay) }
        assert epoints.specsaversPage.startDateInputField.value().contains(chosenDay)
    }

    // 9.5 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
    // ------------------------------------------------------------------------------------- filling wrong dates by hand
    When(~/^User fill start and end dates with not allowed values '(.+?)' '(.+?)'$/) { String startDate, String endDate ->
        epoints.specsaversPage.enterStartDate(startDate)
        epoints.specsaversPage.enterEndDate(endDate)
    }
    Then(~/^Please specify correct date range alert will be displayed$/) { ->
        waitFor{ epoints.specsaversPage.wrongDateAlert.isDisplayed() }
        assert epoints.specsaversPage.wrongDateAlert.text() == envVar.specsaversReportsCalendarALert
    }
    Then(~/^Generate report button will be disabled$/) { ->
        waitFor{ epoints.specsaversPage.generateReportButton.attr('disabled') }
        assert epoints.specsaversPage.generateReportButton.attr('disabled')
    }

    // 10.1 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Epoints awarded section is opened$/) { ->
        epoints.specsaversPage.openAwardedSection()
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingAwardedURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingAwardedURL
    }
    When(~/^User look on epoints awarded section$/) { ->
        //leave empty
    }
    Then(~/^He can see that it contains table with proper columns$/) { ->
        waitFor{ epoints.specsaversPage.tableHeaderRow.isDisplayed() }
        assert epoints.specsaversPage.tableHeaderRowElementBasic[0].text() == envVar.specsaversReportsAwardedDateLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[1].text() == envVar.specsaversReportsAwardedEpointsLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[2].text() == envVar.specsaversReportsAwardedValueLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[3].text() == envVar.specsaversReportsAwardedIssuedByLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[4].text() == envVar.specsaversReportsAwardedAwardedToLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[5].text() == envVar.specsaversReportsAwardedReasonLabel
    }
    Then(~/^Summary values are available$/) { ->
        waitFor{ epoints.specsaversPage.epointsSummary.isDisplayed() }
        waitFor{ epoints.specsaversPage.valueSummary.isDisplayed() }
        assert epoints.specsaversPage.epointsSummary.isDisplayed()
        assert epoints.specsaversPage.valueSummary.isDisplayed()
    }

    // 10.2 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
    // ------------------------------------------------------------------------------------------ table data correctness
    def startDay, endDay
    def startMonth, endMonth
    def year
    Given(~/^Report is generated with random start and end dates$/) { ->
        startDay = (func.returnRandomValue(10)+10).toString()
        endDay = (func.returnRandomValue(10)+20).toString()
        startMonth = '01'
        endMonth = '04'//(func.returnRandomValue(10)+2).toString()
        if(endMonth.size()==1){
            endMonth = '0' + endMonth
        }
        year = '2015'

        startDay = '01'; endDay = '25'; startMonth = '05'; endMonth = '05'; year = '2015'

        epoints.specsaversPage.enterStartDate(startDay+"-"+startMonth+"-"+year)
        epoints.specsaversPage.enterEndDate(endDay+"-"+endMonth+"-"+year)
        waitFor{ !epoints.specsaversPage.generateReportButton.attr('disabled') }
        epoints.specsaversPage.clickGenerateReportButton()
    }
    Then(~/^He can see that all awarded section data is correct according to database$/) { ->
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
        waitFor{ epoints.specsaversPage.tableRowBasic(0) }
        def mysql = new jdbc("points")
        def stopCondition
        if(epoints.specsaversPage.tableRowStatic.size() < 50){
            stopCondition = epoints.specsaversPage.tableRowStatic.size()
        }else{
            stopCondition = 50
        }

        mysql.get_all_query_content("SELECT * FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY createdAt DESC;")
        for(int i=0; i< stopCondition; i++){
            System.out.println(i)
            assert func.convertDateToEpochFormat(mysql.get_value_of_proper_row(true,2),'yyyy-MM-dd mm:HH:ss') == func.convertDateToEpochFormat(epoints.specsaversPage.tableRowElementBasic(i,0).text(),'dd.MM.yyyy mm:HH:ss')
            assert mysql.get_value_of_proper_row(false,5) == epoints.specsaversPage.tableRowElementBasic(i,1).text().replace(',','')
            assert epoints.specsaversPage.tableRowElementBasic(i,2).text().replace(',','').contains('£'+Float.parseFloat(mysql.get_value_of_proper_row(false,5))/200)
            assert mysql.get_value_of_proper_row(false,6) == epoints.specsaversPage.tableRowElementBasic(i,3).text()
            assert epoints.specsaversPage.tableRowElementBasic(i,4).text() == mysql.get("SELECT lastName FROM points_manager.User WHERE registrationSiteId = '100000' AND id = '"+mysql.get_value_of_proper_row(false,11)+"'",1)
            assert mysql.get_value_of_proper_row(false,4) == epoints.specsaversPage.tableRowElementBasic(i,5).text().replace(',','')
        }
        mysql.close()
    }

    // 10.3 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
    // --------------------------------------------------------------------------------------------------------- summary
    Then(~/^He can see that epoints and value summary are correct in awarded section$/) { ->
        waitFor{ epoints.specsaversPage.epointsSummary.isDisplayed() }
        waitFor{ epoints.specsaversPage.valueSummary.isDisplayed() }
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
        def mysql = new jdbc("points")
            assert  mysql.get("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY createdAt DESC",1) == epoints.specsaversPage.epointsSummary.text().replace(',','').replace('Total epoints: ','')
            assert  (Float.parseFloat(epoints.specsaversPage.valueSummary.text().replace('Total value: £','').replace(',',''))) - Float.parseFloat(mysql.get("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY createdAt DESC",1))/200 <0.006
        mysql.close()
    }

    // 11.1 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Epoints redeemed section is opened$/) { ->
        epoints.specsaversPage.openRedeemedSection()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingRedeemedURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingRedeemedURL
    }
    When(~/^User look on epoints redeemed section$/) { ->
        //leave empty
    }
    Then(~/^He can see that redeemed section contains table with proper columns$/) { ->
        waitFor{ epoints.specsaversPage.tableHeaderRow.isDisplayed() }
        assert epoints.specsaversPage.tableHeaderRowElementBasic[0].text() == envVar.specsaversReportsRedeemedDateLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[1].text() == envVar.specsaversReportsRedeemedEpointsLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[2].text() == envVar.specsaversReportsRedeemedValueLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[3].text() == envVar.specsaversReportsRedeemedSpentByLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[4].text() == envVar.specsaversReportsRedeemedRedemptionItemLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[5].text() == envVar.specsaversReportsRedeemedQuantityLabel
        assert epoints.specsaversPage.tableHeaderRowElementBasic[6].text() == envVar.specsaversReportsRedeemedUrlLabel
    }

    // 11.2 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
    // ------------------------------------------------------------------------------------------ table data correctness
    Then(~/^He can see that all redemeed section data is correct according to database$/) { ->
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
        waitFor{ epoints.specsaversPage.tableRowBasic(0) }
        def mysql = new jdbc("points")
        def stopCondition
        if(epoints.specsaversPage.tableRowStatic.size() < 50){
            stopCondition = epoints.specsaversPage.tableRowStatic.size()
        }else{
            stopCondition = 50
        }
        mysql.get_all_query_content("SELECT * FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY points_manager.Points.createdAt DESC;")
        for(int i=0; i<stopCondition; i++){
            assert func.convertDateToEpochFormat(mysql.get_value_of_proper_row(true,2),'yyyy-MM-dd mm:HH:ss') == func.convertDateToEpochFormat(epoints.specsaversPage.tableRowElementBasic(i,0).text(),'dd.MM.yyyy mm:HH:ss')
            assert mysql.get_value_of_proper_row(false,5) == epoints.specsaversPage.tableRowElementBasic(i,1).text().replace(',','')
            assert (Float.parseFloat(epoints.specsaversPage.tableRowElementBasic(i,2).text().replace(',','').replace('£','')) - Float.parseFloat(mysql.get_value_of_proper_row(false,5))/200).abs() < 0.006
            assert epoints.specsaversPage.tableRowElementBasic(i,3).text().replace(',','') == mysql.get("SELECT lastName FROM points_manager.User WHERE registrationSiteId = '100000' AND id = '"+mysql.get_value_of_proper_row(false,11)+"'",1)
            assert mysql.get("SELECT title FROM points_manager.Product WHERE id = '"+mysql.get_value_of_proper_row(false,13)+"'",1) == epoints.specsaversPage.tableRowElementBasic(i,4).text()
            assert mysql.get("SELECT quantity FROM points_manager.Product WHERE id = '"+mysql.get_value_of_proper_row(false,13)+"'",1) == epoints.specsaversPage.tableRowElementBasic(i,5).text()
            assert epoints.specsaversPage.tableRowElementBasic(i,6).find('a').attr('href').contains(mysql.get("SELECT productId FROM points_manager.Product WHERE id = '"+mysql.get_value_of_proper_row(false,13)+"'",1).replace('!','/'))
        }
        mysql.close()
    }

    // 11.3 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
    // ------------------------------------------------------------------------------------------------------- open link
    When(~/^User click open link of chosen row$/) { ->
        def mysql = new jdbc("points")
            mysql.get_all_query_content("SELECT * FROM points_manager.Points JOIN points_manager.Product ON points_manager.Points.productId = points_manager.Product.id JOIN points_manager.User ON points_manager.User.id = points_manager.Points.userId WHERE points_manager.Points.status = 'REDEEMED' AND points_manager.User.registrationSiteId = '100000' AND points_manager.Points.createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY points_manager.Points.createdAt DESC")
            browser.withNewWindow({ epoints.specsaversPage.clickOpenLinkOfChosenRow(0) }, close:true){
                waitFor{ browser.currentUrl.contains((mysql.get_value_of_proper_row(true,23)).replace('!','/')) }
                assert browser.currentUrl.contains((mysql.get_value_of_proper_row(false,23)).replace('!','/'))
            }
        mysql.close()
    }
    Then(~/^Product page of redeemed product will be opened in new window$/) { ->
        //done in previous step
    }

    // 11.4 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
    // --------------------------------------------------------------------------------------------------------- summary
    Then(~/^He can see that epoints and value summary are correct$/) { ->
        waitFor{ epoints.specsaversPage.epointsSummary.isDisplayed() }
        waitFor{ epoints.specsaversPage.valueSummary.isDisplayed() }
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
        def mysql = new jdbc("points")
            assert  mysql.get("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY points_manager.Points.createdAt DESC",1) == epoints.specsaversPage.epointsSummary.text().replace(',','').replace('Total epoints: ','')
            assert  (Float.parseFloat(epoints.specsaversPage.valueSummary.text().replace('Total value: £','').replace(',',''))) - Float.parseFloat(mysql.get("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY points_manager.Points.createdAt DESC",1))/200 <0.006
        mysql.close()
    }

    // 12.1 //  ------------------------------ SPECSAVERS - populate required data and UI for "overview view".(NBO-1614)
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Epoints overview section is opened$/) { ->
        epoints.specsaversPage.openOverviewSection()
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversReportingURL
    }
    When(~/^User look on epoints overview section$/) { ->
        //leave empty
    }
    Then(~/^He can see that overview section contains table with proper columns$/) { ->
        waitFor{ epoints.specsaversPage.overwiewTable.isDisplayed() }
        assert epoints.specsaversPage.overwiewTableRowLabelBasic(0).text() == envVar.specsaversReportsOverviewEPAwardedLabel
        assert epoints.specsaversPage.overwiewTableRowLabelBasic(1).text() == envVar.specsaversReportsOverviewAcAwardedLabel
        assert epoints.specsaversPage.overwiewTableRowLabelBasic(2).text() == envVar.specsaversReportsOverviewValueOfEPLabel
        assert epoints.specsaversPage.overwiewTableRowLabelBasic(3).text() == envVar.specsaversReportsOverviewEPRedeemedLabel
        assert epoints.specsaversPage.overwiewTableRowLabelBasic(4).text() == envVar.specsaversReportsOverviewNumberOfRedLabel
        assert epoints.specsaversPage.overwiewTableRowLabelBasic(5).text() == envVar.specsaversReportsOverviewValueOFRedLabel
    }

    // 12.2 //  ------------------------------ SPECSAVERS - populate required data and UI for "overview view".(NBO-1614)
    // ------------------------------------------------------------------------------------------ table data correctness
    Then(~/^He can see that all overview section data is correct according to database$/) { ->
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
        def mysql = new jdbc("points")
            assert epoints.specsaversPage.overwiewTableRowDataBasic(0).text().replace(',','') == mysql.get("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY createdAt DESC",1)
            assert epoints.specsaversPage.overwiewTableRowDataBasic(1).text() == mysql.get("SELECT COUNT(DISTINCT(userId)) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY createdAt DESC",1)
            assert Float.parseFloat(epoints.specsaversPage.overwiewTableRowDataBasic(2).text().replace(',','').replace('£','')) - Float.parseFloat(mysql.get("SELECT SUM(delta) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY createdAt DESC",1))/200 < 0.006
            assert epoints.specsaversPage.overwiewTableRowDataBasic(3).text().replace(',','') == mysql.get("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY points_manager.Points.createdAt DESC",1)
            assert epoints.specsaversPage.overwiewTableRowDataBasic(4).text() == mysql.get("SELECT SUM(quantity) FROM points_manager.Points JOIN points_manager.Product ON points_manager.Points.productId = points_manager.Product.id JOIN points_manager.User ON points_manager.User.id = points_manager.Points.userId WHERE points_manager.Points.status = 'REDEEMED' AND points_manager.User.registrationSiteId = '100000' AND points_manager.Points.createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY points_manager.Points.createdAt DESC",1)
            assert Float.parseFloat(epoints.specsaversPage.overwiewTableRowDataBasic(5).text().replace(',','').replace('£','')) - Float.parseFloat(mysql.get("SELECT SUM(points_manager.Points.delta) FROM points_manager.Points JOIN points_manager.User ON  points_manager.Points.userId = points_manager.User.id WHERE points_manager.User.registrationSiteId = '100000' AND points_manager.Points.status = 'REDEEMED' AND points_manager.Points.createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"' ORDER BY points_manager.Points.createdAt DESC",1))/200 < 0.006
        mysql.close()
    }

    // 13.1 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
    // ------------------------------------------------------------------------------ availability of pagination options
    Then(~/^He can see pagination component in reporting tab$/) { ->
        waitFor{ epoints.specsaversPage.showingElement.isDisplayed() }
        epoints.specsaversPage.showingElement.isDisplayed()
        epoints.specsaversPage.outOfElement.isDisplayed()
        epoints.specsaversPage.rowNumberSelector.isDisplayed()
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) >  Integer.parseInt(epoints.specsaversPage.rowNumberSelector.find('.is-active').text()) ){
            waitFor{ epoints.specsaversPage.topPaginationArrowLeft.isDisplayed() }
            assert epoints.specsaversPage.topPaginationArrowLeft.isDisplayed()
            assert epoints.specsaversPage.topPaginationArrowRight.isDisplayed()
            assert epoints.specsaversPage.bottomPaginationArrowLeft.isDisplayed()
            assert epoints.specsaversPage.bottomPaginationArrowRight.isDisplayed()
            assert epoints.specsaversPage.bottomPaginationPageNumberBasic.isDisplayed()
        }else{
            waitFor{ !epoints.specsaversPage.topPaginationArrowLeft.isDisplayed() }
            assert !epoints.specsaversPage.topPaginationArrowLeft.isDisplayed()
            assert !epoints.specsaversPage.topPaginationArrowRight.isDisplayed()
            assert !epoints.specsaversPage.bottomPaginationArrowLeft.isDisplayed()
            assert !epoints.specsaversPage.bottomPaginationArrowRight.isDisplayed()
            assert !epoints.specsaversPage.bottomPaginationPageNumberBasic.isDisplayed()
        }
    }
    Then(~/^Epoints redeemed section will be opened$/) { ->
        epoints.specsaversPage.openRedeemedSection()
        waitFor(20){ !epoints.specsaversPage.contentLoader.isDisplayed() }
    }
    // 13.2 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
    // ---------------------------------------------------------------- top pagination, working of next/previous buttons
    Given(~/^Visible rows number is set to 20$/) { ->
        epoints.specsaversPage.clickItemPerPage20(); Thread.sleep(1000)
    }
    When(~/^User click next page button on specsavers awarded section$/) { ->
        waitFor { epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.isDisplayed() }
        waitFor { epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains('1') }
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            assert epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains('1')
            epoints.specsaversPage.clickNextPageButton()
            Thread.sleep(1000)
        }
    }
    Then(~/^Page will be changed to next on specsavers awarded section$/) { ->
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            waitFor { epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains('2') }
            assert epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains('2')
        }
    }
    Then(~/^Showing module will be increased on specsavers awarded section$/) { ->
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            waitFor { epoints.specsaversPage.showingElement.text().contains('Showing 21 - 40') }
            assert epoints.specsaversPage.showingElement.text().contains('Showing 21 - 40')
        }
    }
    When(~/^User click previous page button on specsavers awarded section$/) { ->
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            epoints.specsaversPage.clickPreviousPageButton()
            Thread.sleep(1000)
        }
    }
    Then(~/^Showing module will be decreased on specsavers awarded section$/) { ->
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            waitFor { epoints.specsaversPage.showingElement.text().contains('Showing 1 - 20') }
            assert epoints.specsaversPage.showingElement.text().contains('Showing 1 - 20')
        }
    }
    Then(~/^Page will be changed to previous on specsavers awarded section$/) { ->
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            waitFor { epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains('1') }
            assert epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains('1')
        }
    }

    // 13.3 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
    // --------------------------------------------------------------- bottom pagination, working of page numbers button
    int numberOfProducts
    Then(~/^He can see tat proper number of page is displayed according to 'out of' information on specsavers awarded section$/) { ->
        waitFor{ epoints.specsaversPage.outOfElement.isDisplayed() }
        numberOfProducts = Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace("(out of ","").replace(")",""))
        if((numberOfProducts % 20) == 0){  // 20 because default number of products per page is 20
            waitFor{ Integer.parseInt(epoints.specsaversPage.bottomPaginationPageNumberBasic.last().text()) == (int)(numberOfProducts / 20) }
            assert Integer.parseInt(epoints.specsaversPage.bottomPaginationPageNumberBasic.last().text()) == (int)(numberOfProducts / 20)
        }else{
            waitFor{ Integer.parseInt(epoints.specsaversPage.bottomPaginationPageNumberBasic.last().text()) == (int)(numberOfProducts / 20 +1) }
            assert Integer.parseInt(epoints.specsaversPage.bottomPaginationPageNumberBasic.last().text()) == (int)(numberOfProducts / 20 +1)
        }
    }
    When(~/^User use some bottom pagination page number on specsavers awarded section$/) { ->
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            random = 1
            epoints.specsaversPage.clickChosenPageNumber(random)
            Thread.sleep(1000)
        }
    }
    Then(~/^proper page will be displayed on specsavers awarded section$/) { ->
        if(Integer.parseInt(epoints.specsaversPage.outOfElement.text().replace('(out of ','').replace(')','')) > 20) {
            waitFor{ epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains((random+1).toString()) }
            waitFor{ epoints.specsaversPage.showingElement.text().contains('Showing '+(random*20+1).toString()+" - "+((random+1)*20).toString()) }
            assert epoints.specsaversPage.bottomPaginationPageNumberActiveBasic.text().contains((random+1).toString())
            assert epoints.specsaversPage.showingElement.text().contains('Showing '+(random*20+1).toString()+" - "+((random+1)*20).toString())
        }
    }

    // 13.4 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
    // ------------------------------------------------------------- bottom pagination, working of next/previous buttons
    When(~/^User click next page bottom button on specsavers awarded section$/) { ->
        epoints.specsaversPage.clickNextBottomPageButton()
    }
    When(~/^User click previous page bottom button on specsavers awarded section$/) { ->
        Thread.sleep(1000)
        epoints.specsaversPage.clickPreviousBottomPageButton()
    }

    // 13.5 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
    // ---------------------------------------------------------------------------------- top pagination, items per page
    When(~/^User change 'item per page' parameter on specsavers awarded section$/) { ->
        waitFor{ epoints.specsaversPage.tableRowStatic.isDisplayed() }
        checkNumberOfElementsOnPage(50) // by default
        epoints.specsaversPage.clickItemPerPage20(); Thread.sleep(2000)
        waitFor{ epoints.specsaversPage.tableRowStatic.isDisplayed() }
        checkNumberOfElementsOnPage(20)
        epoints.specsaversPage.clickItemPerPage100(); Thread.sleep(2000)
        waitFor{ epoints.specsaversPage.tableRowStatic.isDisplayed() }
        checkNumberOfElementsOnPage(100)
        epoints.specsaversPage.clickItemPerPageAll(); Thread.sleep(2000)
        waitFor{ epoints.specsaversPage.tableRowStatic.isDisplayed() }
        def mysql = new jdbc("points")
            int rowNumber = Integer.parseInt(mysql.get("SELECT COUNT(*) FROM points_manager.Points WHERE partnerId = '100000' AND status = 'CONFIRMED' AND createdAt BETWEEN '"+year+"-"+startMonth+"-"+startDay+"' AND '"+year+"-"+endMonth+"-"+endDay+" 23:59:59"+"'",1))
        mysql.close()
        checkNumberOfElementsOnPage(rowNumber)
    }
    Then(~/^Number of displayed elements on specsavers awarded section will be changed$/) { ->
        // proper assertions made in previous step to check all three cases in one test
    }

    def checkNumberOfElementsOnPage(int number){
        waitFor{ $('.pagination-summary').find('.pagination-totalItems').isDisplayed() }
        int outOfValue = Integer.parseInt( $('.pagination-summary').find('.pagination-totalItems').text().replace("(out of ","").replace(")","") )
        if(number <=  outOfValue ){
            waitFor{ ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == number) }
            assert  ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == number)
        }else{
            waitFor{ ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == outOfValue) }
            assert  ($('.SpecsaversReports-reportsView').find('tbody').find('tr').size() == outOfValue)
        }
    }