package stepdefs.EpointsStepDefs.getSection

import cucumber.api.java.Before
import geb.Browser
import geb.js.JQueryAdapter
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables
import static cucumber.api.groovy.EN.*


/**
 * Created by kbaranowski on 2015-04-01.
 */

def epoints = new epointsPage()
def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()

    // 1.1 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
    // ------------------------------------------------------------------------------------ not logged user page content
    Given(~/^Invite friend page is opened by not logged user$/) { ->
        to epointsPage
        epoints = page
        epoints.clickGetButton()
        epoints.clickInviteFriendButton()
    }
    When(~/^User look on invite friend page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink+envVar.inviteFriendURL }
        assert  browser.currentUrl == envVar.epointsLink+envVar.inviteFriendURL
    }
    Then(~/^He will see proper page header$/) { ->
        waitFor{ epoints.inviteFriendPage.mainBoldedPageHeader.isDisplayed() }
        assert epoints.inviteFriendPage.mainBoldedPageHeader.text() == envVar.inviteFriendBoldText
    }
    Then(~/^He will see join option on invite friend page$/) { ->
        waitFor{ epoints.inviteFriendPage.joinNowButton.isDisplayed() }
        assert epoints.inviteFriendPage.joinNowButton.isDisplayed()
        assert epoints.inviteFriendPage.joinNowText.text().replace('\n','').replace(' ','') == envVar.inviteFriendJoinText.replace('\n','').replace(' ','')
    }
    Then(~/^He will see sign in option on invite friend page$/) { ->
        waitFor{ epoints.inviteFriendPage.signInButton.isDisplayed() }
        assert epoints.inviteFriendPage.signInButton.isDisplayed()
    }
    Then(~/^He will see explanation how it works on invite friend page$/) { ->
        waitFor{ epoints.inviteFriendPage.howItWorksText.isDisplayed() }
        assert epoints.inviteFriendPage.howItWorksText.text().replace('\n','').replace(' ','') == envVar.inviteFriendHowItWorks.replace('\n','').replace(' ','')
    }

    // 1.2 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
    // ----------------------------------------------------------------------------------------------------- join button
    When(~/^User click on join button on invite friend page$/) { ->
        epoints.inviteFriendPage.clikJoinButton()
    }

    // 1.3 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
    // -------------------------------------------------------------------------------------------------- sign in button
    When(~/^User click on sign in button on invite friend page$/) { ->
        epoints.inviteFriendPage.clikSignInButton()
    }

    // 1.4 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
    // ---------------------------------------------------------------------------------------- logged user page content
    Given(~/^Invite friend page is opened by logged user$/) { ->
        to epointsPage
        epoints = page
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
        epoints.clickGetButton()
        epoints.clickInviteFriendButton()
    }
    Then(~/^He will see unique invitation link on invite friend page$/) { ->
        waitFor{ epoints.inviteFriendPage.uniqueInvitationLinkText.isDisplayed() }
        assert epoints.inviteFriendPage.uniqueInvitationLinkText.text().replace('\n','').replace(' ','') == envVar.inviteFriendCopyInvitationLinkText.replace('\n','').replace(' ','')
        waitFor{ epoints.inviteFriendPage.uniqueInvitationLinkField.isDisplayed() }
        def mysql = new jdbc('points')
            String referrerId = mysql.get("SELECT userKey FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
        mysql.close()
        assert epoints.inviteFriendPage.uniqueInvitationLinkField.value() == envVar.returnUniqueInvitationLink(referrerId)
    }
    Then(~/^He will see facebook post button on invite friend page$/) { ->
        waitFor{ epoints.inviteFriendPage.orSimplyText.isDisplayed() }
        assert epoints.inviteFriendPage.orSimplyText.text().replace('\n','').replace(' ','') == envVar.inviteFriendOrSimplyText.replace('\n','').replace(' ','')
        waitFor{ epoints.inviteFriendPage.postLinkOnFacebookButton.isDisplayed() }
        assert epoints.inviteFriendPage.postLinkOnFacebookButton.isDisplayed()
    }
    Then(~/^He will see how it works explanation on invite friend page$/) { ->
        waitFor{ epoints.inviteFriendPage.howItWorksTextLogged.isDisplayed() }
        epoints.inviteFriendPage.howItWorksTextLogged.text().replace('\n','').replace(' ','') == envVar.inviteFriendHowItWorks.replace('\n','').replace(' ','')
    }

    // 1.5 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
    // ------------------------------------------------------------------------------------------------ share link usage
    int currentPointsValue
    Given(~/^All invited friend are deleted from system$/) { ->
        def mysql = new jdbc("points")
        mysql.update("DELETE FROM points_manager.Request WHERE userId='" + mysql.get("SELECT userKey FROM points_manager.User WHERE email='" + envVar.testUserEmail + "'", 1) + "'")
        Thread.sleep(1000)
        mysql.update("DELETE FROM points_manager.Points WHERE userId='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + envVar.testUserEmail + "'", 1) + "'")

        int numOfElem =  Integer.parseInt(mysql.get("SELECT count(*) FROM points_manager.User WHERE refererId = '"+mysql.get("SELECT userKey FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)+"'",1))
        if(numOfElem>0) {
            currentPointsValue = Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1))

            mysql.get_all_query_content("SELECT email FROM points_manager.User WHERE refererId = '" + mysql.get("SELECT userKey FROM points_manager.User WHERE email = '" + envVar.testUserEmail + "'", 1) + "'")
            email = mysql.get_value_of_proper_row(true, 1)
            for (int i = 0; i < numOfElem-1; i++) {
                mysql.update("DELETE FROM points_manager.Request WHERE userId='" + mysql.get("SELECT userKey FROM points_manager.User WHERE email='" + email + "'", 1) + "'")
                mysql.update("DELETE FROM points_manager.Points WHERE userId='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + email + "'", 1) + "'")
                mysql.update("DELETE FROM points_manager.UserToken WHERE user_id='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + email + "'", 1) + "'")
                mysql.update("DELETE FROM points_manager.UserId WHERE user_id='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + email + "'", 1) + "'")
                mysql.update("DELETE FROM points_manager.User WHERE id='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + email + "'", 1) + "'")
                email = mysql.get_value_of_proper_row(true, 1)
            }
        }
        mysql.close()
    }
    When(~/^Invite link will be used be other user$/) { ->
        waitFor{ epoints.inviteFriendPage.uniqueInvitationLinkField.isDisplayed() }
        inviteLink = epoints.inviteFriendPage.uniqueInvitationLinkField.value()
        if(!inviteLink.contains(envVar.epointsLink)){
            inviteLink = inviteLink.replace("epoints.com/", envVar.epointsLink)
        }
        browser.go(inviteLink)
    }
    String newEmail
    When(~/^Invited user account will be properly registrated$/) { ->
        newEmail = "automatedtestaccount"+func.returnRandomValue(10000)+"@gmail.pl"
        epoints.joinModule.enterEmailAddressOnJoin(newEmail)
        epoints.joinModule.clickJoinNowButton()
        Thread.sleep(1000)
        def mysql = new jdbc("points");
            browser.go(envVar.epointsLink+"join/confirm-email/"+mysql.get("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType = 'REGISTER' AND user_id='"+mysql.get("SELECT id FROM points_manager.User WHERE email = '"+newEmail+"'",1)+"'",1));
            epoints.completeAccountPageModule.fillAllCompliteAccountData('password','password','name','lastname')
            epoints.completeAccountPageModule.selectGenderOption(0)
            epoints.completeAccountPageModule.clickDoneButton()
            waitFor{ mysql.get("SELECT count(*) FROM points_manager.User WHERE email = '"+newEmail+"'",1) =='1' }
            assert mysql.get("SELECT refererId FROM points_manager.User WHERE email = '"+newEmail+"'",1) ==  mysql.get("SELECT userKey FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
        mysql.close();
    }

    Then(~/^Referrer will receive additional fifty epoints$/) { ->
        def mysql = new jdbc('points')
            waitFor(15){ (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue+51)) } //+1 for logout .Points had to be cleared
            assert (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue+51))
            assert mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY createdAt DESC",1) == 'User referred a friend who joined epoints.com'
            assert mysql.get("SELECT delta FROM points_manager.Points ORDER BY createdAt DESC",1) == '50'
            assert mysql.get("SELECT externalTransactionId FROM points_manager.Points ORDER BY createdAt DESC",1) == mysql.get("SELECT userKey FROM points_manager.User WHERE email = '"+newEmail+"'",1)+"|epointsReferAFriend"
            assert mysql.get("SELECT status FROM points_manager.Points ORDER BY createdAt DESC",1) == 'CONFIRMED'
            assert mysql.get("SELECT userId FROM points_manager.Points ORDER BY createdAt DESC",1) == mysql.get("SELECT id FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert mysql.get("SELECT balance FROM points_manager.Points ORDER BY createdAt DESC",1) == mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert mysql.get("SELECT onBehalfOfId FROM points_manager.Points ORDER BY createdAt DESC",1) == 'ePoints.com'
        mysql.close()
    }

    // 1.6 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
    // --------------------------------------------------------------------------------------- share link 10 usage limit
    When(~/^Invite link will be used be other user ten times$/) { ->
        waitFor{ epoints.inviteFriendPage.uniqueInvitationLinkField.isDisplayed() }
        inviteLink = epoints.inviteFriendPage.uniqueInvitationLinkField.value()
        if(!inviteLink.contains(envVar.epointsLink)){
            inviteLink = inviteLink.replace("epoints.com/", envVar.epointsLink)
        }
        for(int i=0; i<11; i++){
            browser.go(inviteLink)
            newEmail = "automatedtestaccount"+func.returnRandomValue(100000)+"@gmail.pl"
            epoints.joinModule.enterEmailAddressOnJoin(newEmail)
            epoints.joinModule.clickJoinNowButton()
            def mysql = new jdbc("points");
                currentPointsValue = Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1))
                browser.go(envVar.epointsLink+"join/confirm-email/"+mysql.get("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType = 'REGISTER' AND user_id='"+mysql.get("SELECT id FROM points_manager.User WHERE email = '"+newEmail+"'",1)+"'",1));
            mysql.close();
            epoints.completeAccountPageModule.fillAllCompliteAccountData('password','password','name','lastname')
            epoints.completeAccountPageModule.selectGenderOption(0)
            epoints.completeAccountPageModule.clickDoneButton()
            mysql = new jdbc('points')
            if(i<0){
                //System.out.println(currentPointsValue)
                //System.out.println(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1))
                waitFor(10){ (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue+51)) } //+1 for logout .Points had to be cleared
                assert (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue+51))
            }else if(i!=0 && i!=10){
                //System.out.println(currentPointsValue)
                //System.out.println(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1))
                waitFor(10){ (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue+50)) }
                assert (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue+50))
            }else if(i==10){
                //System.out.println(currentPointsValue)
                //System.out.println(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1))
                waitFor(10){ (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue)) }
                assert (Integer.parseInt(mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'", 1)) == (currentPointsValue))
            }
            mysql.close()
        }
    }
    Then(~/^Referrer will not receive additional fifty epoints in current month$/) { ->
        //assertion done in previous step
    }

    // 1.7 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
    // ------------------------------------------------------------------------------------------------ facebook posting
    @Before('@userIsLogoutFromFacebook')
    def userIsLogoutFromFacebook(){
        def browser = new Browser()
        browser.go('https://www.facebook.com/home.php')
        Thread.sleep(2000)
        if(browser.$('#userNavigationLabel').isDisplayed()){
            browser.$('#userNavigationLabel').click()
            browser.waitFor{ browser.$('li', text: 'Wyloguj się').isDisplayed() }
            browser.browser.$('li', text: 'Wyloguj się').click()
            browser.waitFor{ browser.$('.loggedout_menubar_container').isDisplayed() }
        }
    }
    When(~/^User click post on your timeline button$/) { ->
        browser.withNewWindow({ epoints.inviteFriendPage.clickPostOnFacebookWallButton() }, close:true) {
            waitFor { $('input', id: 'email').isDisplayed() }
            $('input', id: 'email').value(envVar.testUserEmail)
            $('input', id: 'pass').value(envVar.testUserPassword)
            $('input', type: 'submit').click()
            waitFor{ $('.uiAttachmentDesc').text() == envVar.inviteFriendFacebookPost }
            assert $('.uiAttachmentDesc').text() == envVar.inviteFriendFacebookPost
            inviteLink = $('.attachmentText').find('a').attr('href')
        }
    }
    When(~/^Login into facebook account$/) { ->
        //done in previous step
    }
    Then(~/^He will see that facebook post is properly prepared$/) { ->
        // done in previous step
    }
    Then(~/^Referer join link is also proper$/) { ->
        browser.go(inviteLink)
        waitFor{ browser.title == envVar.joinPageTag }
        assert browser.title == envVar.joinPageTag
    }