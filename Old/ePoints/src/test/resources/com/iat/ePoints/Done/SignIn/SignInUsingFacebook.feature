

Feature: Sign in and authentication using facebook credentials

    As an not registered user
    I require an interface
    So I can login to epoints.com using facebook credentials and thus be able to access the restricted areas

    #1v1
    Scenario Outline: USER SIGN IN - handle NON-verified accounts for first time Facebook sign ups(RD-660)v1
        Given Not registered user open Home page
        Given Administration action - delete user from db '<fbEmail>'
        And Click in Register option
        And Enter email '<fbEmail>' which will be later connected with facebook account
        When Click in Sign up now and get 50 ePoints
        Then New account should be created
        And Account should have status inactive
        And Email submitted page should be opened
        And User can see that he received '<pointsNumber>' points

                Examples:
                    |fbEmail|pointsNumber|
                    |emailwybitnietestowy3@gmail.com|50|

    #1v2
    Scenario Outline: USER SIGN IN - handle NON-verified accounts for first time Facebook sign ups(RD-660)v2
        Given Not registered user open Home page
        And Click in Sign In option
        And Authentication panel is shown
        And Sign in with facebook button should works
        And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
        Then New account should be created
        And User can see that he received '<pointsNumber>' points
        And His '<name>' '<lastName>' '<gender>' '<dateOfBirth>' '<fbEmail>' were properly downloaded
        #Then Administration action - delete user from db '<fbEmail>'

                Examples:
                    |name|lastName|gender|dateOfBirth|fbEmail|fbPassword|pointsNumber|
                    |Daniel|Porebski|MALE|02/03/1972|emailwybitnietestowy3@gmail.com|Delete777|51|

    #2v1
    Scenario Outline: USER SIGN IN - handle NON-successful first time sign in via Facebook (RD-659)- email does not exist in DB
        Given Not registered user open Home page
        Given Administration action - delete user from db '<fbEmail>'
        And Click in Sign In option
        And Authentication panel is shown
        And Sign in with facebook button should works
        And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
        Then New account should be created
        And User can see that he received '<pointsNumber>' points
        And His '<name>' '<lastName>' '<gender>' '<dateOfBirth>' '<fbEmail>' were properly downloaded
        Then Administration action - delete user from db '<fbEmail>'

                Examples:
                    |name|lastName|gender|dateOfBirth|fbEmail|fbPassword|pointsNumber|
                    |Daniel|Porebski|MALE|02/03/1972|emailwybitnietestowy3@gmail.com|Delete777|50|

    #2v2
    Scenario Outline: USER SIGN IN - handle NON-successful first time sign in via Facebook (RD-659)- email does exist in DB and it is verified
        Given Not registered user open Home page
        And Click in Sign In option
        And Authentication panel is shown
        And Sign in with facebook button should works
        And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
        Then New account should be created
        And His '<name>' '<lastName>' were properly downloaded and '<gender>' '<dateOfBirth>' '<fbEmail>' were not imported
        Then Administration action - delete facebook flag from db '<fbEmail>'

                Examples:
                    |name|lastName|gender|dateOfBirth|fbEmail|fbPassword|
                    |Iat|Epoints|MALE|23/12/1993|iat.epoints.test@gmail.com|everest01|

    #3 Check if user is able to sign in using facebook and epoints credentials as well
    Scenario Outline: USER SIGN IN - add Facebook option to sign in interface for epoints (RD-655).
        Given Not registered user open Home page
        When User use '<emailEp>' and '<passwordFb>' in Log in panel
        And Click in Sign In button
        Then User should be correctly logged in
        Then Click in Sign out link localised in header
        And Click in Sign In option
        And Sign in with facebook button should works
        When User enter facebook credentials '<emailFb>' '<passwordFb>' and confirm login details
        Then User should be correctly logged in

                Examples:
                    |emailEp|emailFb|passwordEp|passwordFb|
                    |emailwybitnietestowy@gmail.com|emailwybitnietestowy@gmail.com|Delete777|Delete777|

    #4
    Scenario Outline: USER SIGN IN - handle successful first time sign in via Facebook (RD-658).
        Given Not registered user open Home page
        And Click in Sign In option
        And Authentication panel is shown
        And Sign in with facebook button should works
        And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
        Then New account should be created
        And Facebook id should be send via cookies and it is the same as in database
        Then Administration action - delete facebook flag from db '<fbEmail>'

                Examples:
                    |fbEmail|fbPassword|
                    |iat.epoints.test@gmail.com|everest01|

