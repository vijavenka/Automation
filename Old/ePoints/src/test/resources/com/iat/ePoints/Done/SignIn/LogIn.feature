#test not implemented  - (8)(9)(11)

Feature: Sign in and authentication

    As an not registered user
    I require an interface
    So I can login to epoints.com and thus be able to access the restricted areas

    #1
    Scenario: Check if Log in Panel have all required elements
        Given Not registered user open ePoints.com
        When Click in Sign In option
        Then Authentication panel is shown
        And Email label is available
        And Email field is available
        And Password label is available
        And Password field is available
        And Forgot password link is available
        And Sign In button is available
        And Join here Link is available
        And Resend confirmation link is available
        And Sign in using facebook button is available
        And Close Link is available

    #2
    Scenario: Sign In with not filled fields
        Given Not registered user open ePoints.com
        And Click in Sign In option
        And Authentication panel is shown
        And Leave fields empty
        When Click in Sign In button
        Then Fields should be marked
        And Warning system information should be visible

    #3
    Scenario: Sign in into not existing account
        Given Not registered user open ePoints.com
        And Click in Sign In option
        And Authentication panel is shown
        When Fill email address using email which is not in the database
        And Fill Password field with random data
        And Click in Sign In button
        Then Warning authentication system error should shown

    #4
    Scenario: Sign in into existing account which is not activated
        Given Not registered user open ePoints.com
        And Click in Sign In option
        And Authentication panel is shown
        When Fill email address using inactive account email which is in the database
        And Fill Password field with random data
        And Click in Sign In button
        Then Warning authentication system error should shown

    #5
    Scenario: Sign in into existing and active account using incorrect password
        Given Not registered user open ePoints.com
        And Click in Sign In option
        And Authentication panel is shown
        When Fill email address using active account email which is in the database
        And Fill Password field with random data
        And Click in Sign In button
        Then Warning authentication system error should shown

    #6
    Scenario: Sign in correctly
        Given Not registered user open ePoints.com
        When User use 'emailwybitnietestowy@gmail.com' and 'Delete777' in Log in panel
        And Click in Sign In button
        Then User should be correctly logged in

    #7
    Scenario: Checking Sign out option for logged in user
        Given User is logged in
        When Click in Sign out link localised in header
        Then Homepage with Sign in / register box is opened

    #8
    #Scenario: Sign in when user is provide by cookie and is not authenticated
        #Given Home page opened
        #When Registered user is on page
        #And User provide by cookie
        #And User is not authenticated
        #And Click in "Not <username>? / Not Your account?
        #Then Authentication modal panel should shown
        #And Email field should be filled out automatically
        #And Password field should be available
        #And Not You? link should be available

    #9
    #Scenario: Checking Sign out option for user identified by cookie
        #Given User is identified by cookies
        #When Click in Sign out link localised in header
        #Then Homepage with Sign in / register box is opened

    #10
    Scenario: Checking if submit redemption order offer panel to log in
        Given User is not logged in
        And There is at last one item added to the basket
        And User is on the basket view
        When User clicks on Order rewards button
        Then Basket is opened
        And Order these rewards option is available
        When Click in Order these rewards option
        Then Authentication modal panel should shown


