Feature: Add item to the basket

    As a user
    I require an option on the redemption items
    to allow me to add the selected redemption item to my basket so that I can then check these item(s) out later


    Scenario: Add item to the Cart
        Given Not registered user open ePoints.com
        And Opened Spend Tab
        And Basket is empty
        Then User can choose one of top reward picks
        And Add to basket selected product
        Then User is able to return to spend landing page
        And Choose Rewards by epoints total category
        And Add to basket selected product
        When Searching for product is finished
        Then User is able to see all products in basket


