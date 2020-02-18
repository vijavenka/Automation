Feature: My account navigation

    As an user
    I require a set of navigation options within the account area
    So I can traverse through the set of account pages easily


    Scenario: Checking if all links to My account tabs are available
        Given Registered user open ePoints.com
        And Open My Account page
        When My Account page opened correctly
        Then Dashboard navigation option is available
        And My epoints navigation option is available
        And My profile navigation option is available
        And Activity navigation option is available
        And Rewards History navigation option is available
        And Invite friends navigation option is available

    Scenario: Checking if all My accounts links redirect to proper tabs
        Given Registered user open ePoints.com
        And Open My Account page
        And My Account page opened correctly
        When Click in each of My account sub pages navigation options
        Then Proper sub page should opened correctly