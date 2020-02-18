Feature: Browse results page - search results tools

    As a user
    I require a standard set of search interface tools
    to help me configure my result set into a more meaningful ordered set for me

    #1
    Scenario: Check if all required search tools are available for not registered user
        Given Not registered user open ePoints.com
        And Open redemptions browse page
        Then Page size component is available
        And Sort order component is available
        And Pagination component is available
        And Ranges buttons are available
        And Rest of filters will be available

    #2
    Scenario: Check working of pagination component on browse rewards page
        Given Not registered user open ePoints.com
        When Open redemptions browse page
        Then Showing module is working correctly
        And Items per page module is working correctly
        And Bottom pagination module is working correctly

    #3
    Scenario: Spend page refresh - show initial department filter (RD-3605) - reach by product counter,without breadcrumb
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        When redemption page will by opened using product counter
        Then Initial department filter should be available
        When User choose some department option
        Then Rest of filters will be available

    #4
    Scenario: Spend page refresh - show initial department filter (RD-3605) - reach by search,with breadcrumb
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        When redemption page will by opened using search
        Then Initial department filter should be available
        When User choose some department option
        Then Rest of filters will be available
        And Breadcrumb module will be properly displayed for chosen department
        When User click all department breadcrumb option
        Then Initial department filter should be available

    #5
    Scenario: Spend page refresh - points to purchase filter (RD-3645).
        Given Not registered user open ePoints.com
        When Open redemptions browse page
        And Use point range filter
        Then Only product in point range will be displayed
        And Found between information will be correctly displayed

    #6
    Scenario: Spend page refresh - breadcrumb logic (RD-3655).
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        And redemption page will by opened using product counter
        When User choose some department option
        Then On redemption first part of breadcrumb will be displayed
        When User select some category
        Then Breadcrumb will be extended by category name
        When User select some brand
        Then Breadcrumb will be extended by brand name
        When User select some type
        Then Breadcrumb will be extended by type name
        When Use point range filter
        Then Breadcrumb will be extended by points range

    #7
    Scenario: Spend page refresh - category filter (RD-3615).
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        And redemption page will by opened using product counter
        When User choose some department option
        Then Category filter should be available
        When User select some category
        Then Breadcrumb will be extended by category name
        And Category filter should disappears
        When Used decide to comeback to category selection
        Then He can click bread crumb department section
        And Category filter should be available

    #8
    Scenario: Spend page refresh - brand filter (RD-3625) - functional + breadcrumb no search
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        And redemption page will by opened using product counter
        When User choose some department option
        Then On redemption first part of breadcrumb will be displayed
        And Brand filter should be available
        When User select some brand
        And User add second brand
        Then Breadcrumb will also contains name of second brand
        When Third Brand will be added
        Then Only information about number of items will be displayed on breadcrumb
        When User use clear brand button
        Then Selected brands will be cleared

    #9
    Scenario: Spend page refresh - brand filter (RD-3625) - functional + search
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        And redemption page will by opened using product counter
        When User choose some department option
        Then On redemption first part of breadcrumb will be displayed
        And Brand filter should be available
        When User use brand search with some phrase
        Then Search should works correctly and gave correct results

    #10
    Scenario: Spend page refresh - type filter (RD-3635) - functional + breadcrumb no search
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        And redemption page will by opened using product counter
        And Department and category is already chosen
        When User select some type
        And User add second type
        Then Breadcrumb will also contains name of second type
        When Third type will be added
        Then Only information about number of types will be displayed on breadcrumb
        When User use clear type button
        Then Selected types will be cleared

    #11
    Scenario: Spend page refresh - type filter (RD-3635) - functional +  search
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        And redemption page will by opened using product counter
        And Department and category is already chosen
        Then Type filter should be available
        When User use type search with some phrase
        Then Type search should works correctly and gave correct results

    #12
    Scenario: Spend page refresh - add counts to filter values (RD-3677).
        Given Not registered user open ePoints.com
        And He is on the Get rewards main tab
        And redemption page will by opened using product counter
        When User choose some department option
        Then Proper number of product should be displayed on navigation bar
        When User select some category
        Then Proper number of product should be displayed on navigation bar
        When User select some brand
        Then Proper number of product should be displayed on navigation bar
        When User select some type
        Then Proper number of product should be displayed on navigation bar







