
Feature: Sign in and authentication using facebook and epoints credentials

    As an not registered user
    I require an interface
    So I can login to epoints.com using facebook credentials and thus be able to access the restricted areas

    #1v1 Check if facebook option was added in login panel and transition page
    Scenario: USER SIGN IN - add Facebook option to sign in interface for WLS (RD-657) v1
        Given Not registered user open Home page
        When User open login panel and facebook option should be available
        Then User is able to close login panel
        And Go to transition page using product buy button
        Then He can see that facebook login option is also available in transition page

    #1v2 Check if user is able to sign in using facebook and epoints credentials as well
    Scenario Outline: USER SIGN IN - add Facebook option to sign in interface for WLS (RD-657) v2
        Given Not registered user open Home page
        When User use '<emailEp>' and '<passwordFb>' in Log in panel
        And Click in Sign In button
        Then User should be correctly logged in
        Then Click in Sign out link localised in header
        And Click in loozenge sign in option
        And Sign in with facebook button should works
        When User enter facebook credentials '<emailFb>' '<passwordFb>' and confirm login details
        Then User should be correctly logged in

                Examples:
                    |emailEp|emailFb|passwordEp|passwordFb|
                    |emailwybitnietestowy@gmail.com|emailwybitnietestowy@gmail.com|Delete777|Delete777|