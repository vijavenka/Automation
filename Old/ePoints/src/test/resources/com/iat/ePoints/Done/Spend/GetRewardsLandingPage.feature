Feature: Redemptions item landing page

    As an ePoints User
    I require a redemption landing page
    So I can start of a browse journey by selecting points ranges I can redeem from or aspire to


    Scenario: View for the logged out User
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        When He looks at the page
        Then The first page is adapted for the person who is new to the ePoints
        And There is information on how many items User can buy
        And Searcher is displayed
        And User can choose a range and search the items
        And Chose product from proposed by system

    Scenario: Spend page refresh - restructure page (RD-3575).
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        When He looks at the page
        Then Te will be able to see three tabs
        When Departments Tab will be chosen
        Then Proper set of department tab will be displayed
        When Rewards by epoints total tab will be chosen
        Then Proper points value card will be displayed
        When Our top reward picks tab will be chosen
        Then Product buckets cards will be displayed

    Scenario: Spend page refresh - add logic for department tab (RD-3595).
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        When Departments Tab will be chosen
        And User will chose some of department
        Then He will be redirected to redemption page
        And Only category filter will be visible from facets set