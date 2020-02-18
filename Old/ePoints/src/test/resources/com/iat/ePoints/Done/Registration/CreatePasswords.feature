
Feature: Create password

    As a user
    When I register I receive an email with a link to follow
    So I can go to land page and create a password

    #1
    Scenario: Checking if create password page view is correct
         Given Page for create password is opened
         When Checking view of page
         Then Password field is available
         And Confirm password field is available
         And First name field is available
         And Last name field is available
         And Done option is available
         Then Administration action - delete user from db 'emailwybitnietestowy3@gmail.com'

    #2
    Scenario: Checking if setting password password field validations are correctly
         Given Page for create password is opened
         When Done button will be pressed before filling any data
         Then All create password alerts will be shown
         When Enter only new password and name
         And Confirm password which do not match first one
         Then Proper system message should be shown
         Then Administration action - delete user from db 'emailwybitnietestowy3@gmail.com'

    #3
    Scenario: Checking if account activation working correctly
         Given Page for create password is opened
         And All needed credentials are correctly filled by user
         When Click in Done button
         Then Account should be activated
         And User is redirected to all done screen
         And Tell me more option is available
         And Get epoints now is available
         Then Administration action - delete user from db 'emailwybitnietestowy3@gmail.com'

    #4
    Scenario: Check If forgot your password page is available and has proper content
         Given Not registered user open Home page
         Then User is able to reach forgot password page using sign in popup
         When He looks on forgot your password page
         Then He can see all needed elements to recover the password to ePoints account

    #5
    Scenario: Check If forgot your password page returns proper alerts
         Given Not registered user open Home page
         Then User is able to reach forgot password page using sign in popup
         When User use try to restore password without entering email
         Then Email is required alert should appear
         When User enter incorrect email
         Then Email does not exist alert should appear

    #6
    Scenario Outline: Check If password restoration link is created and is working
         Given Not registered user open Home page
         Then User is able to reach forgot password page using sign in popup
         When User enter correct email '<userEmail>'
         And Submit sending email
         Then Success alert should be shown
         And Link sent on user email should works '<userEmail>'
         And Allow to create new password '<newPassword>'
         Then User can check if new password works '<userEmail>' '<newPassword>'

             Examples:
                 |userEmail|newPassword|
                 |emailwybitnietestowy@gmail.com|newPassword|
                 |emailwybitnietestowy@gmail.com|Delete777|


