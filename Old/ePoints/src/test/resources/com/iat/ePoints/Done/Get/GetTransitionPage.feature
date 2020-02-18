Feature: Transition to retailer - full screen page and modal window

   As an unidentified user
   I require the click out from epoints to retailer transition to be interrupted to remind me to login
   So that I do not lose any potential points I could earn

        #0
        #Scenario: AFFILAITE MANAGER - enable the use of deeplinks outside of solr (RD-1553) - check if transition was opened using deeplink and check clickout
            #Given Tranition page is opened using deeplink
            #When Click in continue anyway option
            #And New clickout for deeplink should be reported

        #1
        Scenario: TRANSITION PAGE DESKTOP- new sign in option using email/password on module page - modal content
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Checking view of panel
            Then Join here link is available
            And Sign in button is available
            And Sign in using facebook is available
            And Learn more button is available
            And Close button is available

       #2
       Scenario: TRANSITION PAGE - create module approach view for full page view (RD-2661) - page content
            Given Transition page is opened
            When Checking view of panel
            Then Join here link is available
            And Sign in button is available
            And Sign in using facebook is available
            And Learn more button is available

        #3
        Scenario Outline: TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2731).
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Click in transition Sign In button
            And Fill out Sign In form with using correct data '<Email>' '<Password>'
            And Click in transition Sign In button
            Then  Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_in' '<Email>'

                    Examples:
                        |Email|Password|
                        |emailwybitnietestowy@gmail.com|Delete777|

        #4
        Scenario Outline: TRANSITION PAGE - create module approach view for full page view (RD-2661) - sign in and clickout
            Given Transition page is opened
            When Click in transition Sign In button
            And Fill out Sign In form with using correct data '<Email>' '<Password>'
            And Click in transition Sign In button
            Then  Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_in' '<Email>'

                    Examples:
                        |Email|Password|
                        |emailwybitnietestowy@gmail.com|Delete777|

        #5
        Scenario Outline: TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2731) - wrong password
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Click in transition Sign In button
            When User try to login in transition page using improper data '<Email>' '<Password>'
            And Click in transition Sign In button
            Then Authorization failed alert should be displayed

                     Examples:
                         |Email|Password|
                         |emailwybitnietestowy@gmail.com|wrongpassword|

        #6
        Scenario Outline: TRANSITION PAGE - create module approach view for full page view (RD-2661) - wrong password
            Given Transition page is opened
            When Click in transition Sign In button
            When User try to login in transition page using improper data '<Email>' '<Password>'
            And Click in transition Sign In button
            Then Authorization failed alert should be displayed

                     Examples:
                         |Email|Password|
                         |emailwybitnietestowy@gmail.com|wrongpassword|

        #7
        Scenario: TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2731) - no login data
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Click in transition Sign In button twice
            Then Alerts of needed email and password should be displayed

        #8
        #Scenario: TRANSITION PAGE - create module approach view for full page view (RD-2661) - no login data
            #Given Transition page is opened
            #When Click in transition Sign In button
            #Then Alerts of needed email and password should be displayed

        #9
        Scenario Outline: TRANSITION PAGE DESKTOP - new sign in option using FACEBOOK on module page (RD-2701).
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Sign in using facebook button will be clicked
            And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
            Then  Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_in' '<fbEmail>'

               Examples:
                   |fbEmail|fbPassword|
                   |emailwybitnietestowy@gmail.com|Delete777|

        #10
        Scenario Outline: TRANSITION PAGE - create module approach view for full page view (RD-2661) - facebook login
            Given Transition page is opened
            When Sign in using facebook button will be clicked
            And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
            Then  Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_in' '<fbEmail>'

                Examples:
                    |fbEmail|fbPassword|
                    |emailwybitnietestowy@gmail.com|Delete777|

        #11
        Scenario: TRANSITION PAGE DESKTOP - new JOIN option on module page (RD-2711) - join correct
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Join button will be used
            And New email will be typed
            And Join button will be used
            Then Success alert will be displayed
            And User will be stored in database as not verified
            And Go to retailer button will be available
            When User use go to retailer button
            Then  Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_in' 'createdInNavClass'



        #12
        Scenario: TRANSITION PAGE - create module approach view for full page view (RD-2661) - join correct
            Given Transition page is opened
            When Join button will be used
            And New email will be typed
            And Join button will be used
            Then Success alert will be displayed
            And User will be stored in database as not verified
            And Go to retailer button will be available
            When User use go to retailer button
            Then  Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_in' 'createdInNavClass'

        #13
        Scenario: TRANSITION PAGE DESKTOP - new JOIN option on module page (RD-2711) - email taken
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Join button will be used
            And Taken email will be typed
            And Join button will be used
            Then Warning alert will be displayed

        #14
        Scenario: TRANSITION PAGE - create module approach view for full page view (RD-2661) - email taken
            Given Transition page is opened
            When Join button will be used
            And Taken email will be typed
            And Join button will be used
            Then Warning alert will be displayed

        #15
        Scenario Outline: TRANSITION PAGE DESKTOP - new FORGOTTEN PASSWORD option on module page (RD-2741) - correct email
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Click in transition Sign In button
            And Forgot password Link will be clicked
            Then User can type email to retrieve password '<validEmail>'
            When User press send button
            Then Success alert will be displayed
            And Link sent on user email should works '<validEmail>'

                    Examples:
                    |validEmail|
                    |emailwybitnietestowy@gmail.com|

        #16
        Scenario Outline: TRANSITION PAGE - create module approach view for full page view (RD-2661) - correct email
            Given Transition page is opened
            When Click in transition Sign In button
            And Forgot password Link will be clicked
            Then User can type email to retrieve password '<validEmail>'
            When User press send button
            Then Success alert will be displayed
            And Link sent on user email should works '<validEmail>'

                    Examples:
                    |validEmail|
                    |emailwybitnietestowy@gmail.com|

        #17
        Scenario Outline: TRANSITION PAGE DESKTOP - new FORGOTTEN PASSWORD option on module page (RD-2741) - incorrect email, no email
            Given Shops A-Z page is opened
            When Select Random retailer
            And Click in link to transfer into retailer modal
            Then Transition modal window should be displayed
            When Click in transition Sign In button
            And Forgot password Link will be clicked
            Then User can type email to retrieve password '<invalidEmail>'
            When User press send button
            Then Email invalid alert will be displayed
            When User type no password
            And User press send button
            Then Email required alert will be displayed

                    Examples:
                    |invalidEmail|
                    |emailwybitnietestowy|

        #18
        Scenario Outline: TRANSITION PAGE - create module approach view for full page view (RD-2661) - incorrect email, no email
            Given Transition page is opened
            When Click in transition Sign In button
            And Forgot password Link will be clicked
            Then User can type email to retrieve password '<invalidEmail>'
            When User press send button
            Then Email invalid alert will be displayed
            When User type no password
            And User press send button
            Then Email required alert will be displayed

                    Examples:
                    |invalidEmail|
                    |emailwybitnietestowy|


        #19
        Scenario Outline: AFFILIATE MANAGER - cross channel sales tracking logic (RD-2837).
            Given Not registered user open ePoints.com
            Given Shop page is opened
            Given Transition page is opened with additional p parameters
            When Click in transition Sign In button
            And Fill out Sign In form with using correct data '<Email>' '<Password>'
            And Click in transition Sign In button
            Then  Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_in' '<Email>'
            And P parameters should correctly be stored in database

                    Examples:
                        |Email|Password|
                        |emailwybitnietestowy@gmail.com|Delete777|



























