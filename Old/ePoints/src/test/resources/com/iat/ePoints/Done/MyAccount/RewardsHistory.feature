Feature: Rewards history page view

    As an user
    I require an interface that will show me my redemption history and the status of each order
    So I can validate them

    #1
    Scenario: Checking empty rewards history list
        Given Registered user with no rewards history open ePoints.com
        And Open My Account page
        And Open rewards history page
        When Rewards history page is correctly opened
        Then Rewards history page is empty
    #2
    Scenario: Checking if rewards history list records have required elements
        Given Registered user with sme rewards history open ePoints.com
        And Open My Account page
        And Open rewards history page
        When Rewards history page is correctly opened
        And Rewards history page have one record minimum
        Then Reward date is displayed
        And Reward image is displayed
        And Reward title is displayed
        And ePoints used is displayed
        And Delivery details are displayed
        And Contact Us link is displayed

    #3
    Scenario: Checking if in reward history module data are properly written (product name, delivery details).
        Given Registered user open ePoints.com
        Given Basket has empty content
        Given There is at last one item added to the basket
        And User is on the basket view
        And User try to remember chosen product name
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click order reward button
        And User try to remember Delivery details
        Then User click next page in delivery page
        Then User click place order button
        Then User goes to user account page
        And Open rewards history page
        And He can see that all data are properly saved in reward history module

    #4
    Scenario: Checking if detailed page of bought product can be reached correctly
        Given Registered user with sme rewards history open ePoints.com
        And Open My Account page
        And Open rewards history page
        When Rewards history page is correctly opened
        Then User can see details of chosen product from list
        And Get correct information about ordered product

    #5
    Scenario Outline: Checking rewards history list content
        Given Registered user open ePoints.com
        And Open My Account page
        And Open rewards history page
        When Rewards history page is correctly opened
        Then Rewards history page can bee seen with proper content comparing to DB for email '<userEmail>'

                Examples:
                    |userEmail|
                    |emailwybitnietestowy@gmail.com|