package stepdefs.EpointsStepDefs.joinLoginSection
import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
/**
 * Created by kbaranowski on 2014-11-05.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def epointsLink = envVar.epointsLink

    // 1.1 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
    // ----------------------------------------------------------------------------------- complete account page content
    String automatedTestEmail
    Given(~/^Complete account page is opened$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        automatedTestEmail = 'automatedtestemail'+func.returnRandomValue(1000000)+'@gamil.com'
        epoints.clickJoinNowButton()
        epoints.joinModule.enterEmailAddressOnJoin(automatedTestEmail)
        epoints.joinModule.clickJoinNowButton()
        Thread.sleep(2000)
        def db = new jdbc("points");
        browser.go(envVar.epointsLink+"join/confirm-email/"+db.get("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType = 'REGISTER' AND user_id='"+db.get("SELECT id FROM points_manager.User WHERE email = '"+automatedTestEmail+"'",1)+"'",1));
        db.close();

    }
    Given(~/^Complete account page is opened clear$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        automatedTestEmail = 'automatedtestemail'+func.returnRandomValue(1000000)+'@gamil.com'
        epoints.clickJoinNowButton()
        epoints.joinModule.enterEmailAddressOnJoin(automatedTestEmail)
        epoints.joinModule.clickJoinNowButton()
        Thread.sleep(2000)
        def db = new jdbc("points");
        browser.go(envVar.epointsLink+"join/confirm-email/"+db.get("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType = 'REGISTER' AND user_id='"+db.get("SELECT id FROM points_manager.User WHERE email = '"+automatedTestEmail+"'",1)+"'",1));
        db.close();

    }
    When(~/^User look at complete account page$/) { ->
        waitFor{ browser.title == envVar.createPasswordTitle }
        waitFor{ epoints.completeAccountPageModule.header.text() == envVar.completePageHeader }
        assert browser.title == envVar.createPasswordTitle
        assert epoints.completeAccountPageModule.header.text() == envVar.completePageHeader
    }
    Then(~/^Password field is available$/) { ->
        waitFor{ epoints.completeAccountPageModule.passwordField.isDisplayed() }
        waitFor{ epoints.completeAccountPageModule.basicLabel[0].text() == envVar.completePageLabels[0] }
        assert epoints.completeAccountPageModule.passwordField.isDisplayed()
        assert epoints.completeAccountPageModule.basicLabel[0].text() == envVar.completePageLabels[0]
    }
    Then(~/^Confirm password field is available$/) { ->
        waitFor{ epoints.completeAccountPageModule.confirmPasswordField.isDisplayed() }
        waitFor{ epoints.completeAccountPageModule.basicLabel[1].text() == envVar.completePageLabels[1] }
        assert epoints.completeAccountPageModule.confirmPasswordField.isDisplayed()
        assert epoints.completeAccountPageModule.basicLabel[1].text() == envVar.completePageLabels[1]
    }
    Then(~/^First name field is available$/) { ->
        waitFor{ epoints.completeAccountPageModule.nameField.isDisplayed() }
        waitFor{ epoints.completeAccountPageModule.basicLabel[2].text() == envVar.completePageLabels[2] }
        assert epoints.completeAccountPageModule.nameField.isDisplayed()
        assert epoints.completeAccountPageModule.basicLabel[2].text() == envVar.completePageLabels[2]
    }
    Then(~/^Last name field is available$/) { ->
        waitFor{ epoints.completeAccountPageModule.lastNameField.isDisplayed() }
        waitFor{ epoints.completeAccountPageModule.basicLabel[2].text() == envVar.completePageLabels[2] }
        assert epoints.completeAccountPageModule.lastNameField.isDisplayed()
        assert epoints.completeAccountPageModule.basicLabel[2].text() == envVar.completePageLabels[2]
    }
    Then(~/^Gender select options are available$/) { ->
        waitFor{ epoints.completeAccountPageModule.genderMenWomenIcon.isDisplayed() }
        assert epoints.completeAccountPageModule.genderMenWomenIcon[0].isDisplayed()
        assert epoints.completeAccountPageModule.genderMenWomenIcon[1].isDisplayed()
        assert epoints.completeAccountPageModule.genderMenWomenIcon[0].attr('data-gender') == 'male'
        assert epoints.completeAccountPageModule.genderMenWomenIcon[1].attr('data-gender') == 'female'
        assert epoints.completeAccountPageModule.basicLabel[4].text() == envVar.completePageLabels[4]
    }
    Then(~/^Done option is available$/) { ->
        waitFor{ epoints.completeAccountPageModule.doneButton.isDisplayed() }
        assert epoints.completeAccountPageModule.doneButton.isDisplayed()

    }

    // 1.2 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
    // ------------------------------------------------------------------------------------ complete account page alerts

    When(~/^Done button will be pressed before filling any data$/) { ->
        epoints.completeAccountPageModule.clickDoneButton()
    }
    Then(~/^All create password alerts will be shown$/) { ->
        waitFor{ epoints.completeAccountPageModule.basicAlert.isDisplayed() }
        assert epoints.completeAccountPageModule.basicAlert[0].text() == envVar.completePageAlerts[0]
        assert epoints.completeAccountPageModule.basicAlert[1].text() == envVar.completePageAlerts[1]
        assert epoints.completeAccountPageModule.basicAlert[2].text() == envVar.completePageAlerts[2]
        assert epoints.completeAccountPageModule.basicAlert[3].text() == envVar.completePageAlerts[3]
    }
    When(~/^User Enter two different passwords name and last name$/) { ->
        epoints.completeAccountPageModule.fillAllCompliteAccountData('password','differentpassword','name','lastname')
    }
    When(~/^Press done button$/) { ->
        epoints.completeAccountPageModule.clickDoneButton()
    }
    Then(~/^Only confirmation alert will stay visible$/) { ->
        waitFor{ epoints.completeAccountPageModule.basicAlert.text() == envVar.completePageAlerts[1] }
        assert epoints.completeAccountPageModule.basicAlert.text() == envVar.completePageAlerts[1]
    }

    // 1.3 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
    // ---------------------------------------------------------------- complete account page, proper account completion
    When(~/^User correctly fill all needed data$/) { ->
        epoints.completeAccountPageModule.fillAllCompliteAccountData('password','password','name','lastname')
    }
    When(~/^User select gender$/) { ->
        epoints.completeAccountPageModule.selectGenderOption(0)
    }
    Then(~/^User will be redirected to 'All done' screen$/) { ->
        waitFor{ browser.title == envVar.allDonePageTitle }
        waitFor{ epoints.completeAccountPageModule.allDoneViewHeader.text() == envVar.allDoneViewHeader }
        assert browser.title == envVar.allDonePageTitle
        assert epoints.completeAccountPageModule.allDoneViewHeader.text() == envVar.allDoneViewHeader
    }
    Then(~/^Account data should be updated$/) { ->
        def db = new jdbc("points");
            assert( db.get("SELECT firstName FROM points_manager.User WHERE email = '"+automatedTestEmail+"'",1) == 'name' )
            assert( db.get("SELECT lastName FROM points_manager.User WHERE email = '"+automatedTestEmail+"'",1) == 'lastname' )
            assert( db.get("SELECT password FROM points_manager.User WHERE email = '"+automatedTestEmail+"'",1) != null )
            assert( db.get("SELECT gender FROM points_manager.User WHERE email = '"+automatedTestEmail+"'",1) == 'MALE' )
        db.close()
    }

    // 1.4 //  ----------------------------------------------------------------------------------- Complete account page
    // --------------------------------------------------------------------------------------------------- invalid token
    Given(~/^Complete account page is opened with expired token$/) { ->
        to epointsPage
        epoints = page
        browser.go(envVar.epointsLink+"join/confirm-email/"+"dd76we8d6we7d68we76dwe76d8we6d")
    }
    When(~/^User look at opened page$/) { ->
        //leave empty
    }