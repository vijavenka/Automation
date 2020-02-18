Feature: Search Page

   As a user
       I need a search page
       Where I can see product search results according to chosen filters

        #1
        Scenario Outline:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of home page search
            Given Not registered user open WLS page
            When User use search with chose phrase '<phrase>'
            Then Search page should be opened
            And Proper product connected with chosen phrase should be displayed '<phrase>'

                            Examples:
                                |phrase|
                                |Batman|
                                |Spiderman|

        #2
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of pagination component
            Given Not registered user open WLS page
            Given Search page is opened
            Then User can see that pagination component is available
            And Showing module is working correctly
            And Items per page module is working correctly
            And Bottom pagination module is working correctly

        #3
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of price filter
            Given Not registered user open WLS page
            Given Search page is opened
            And User can use price filter
            And Be sure that it works correctly

        #4
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - content of product cards
            Given Not registered user open WLS page
            Given Search page is opened
            When User look at search page
            And Viewing products he can see that product cards have all needed elements

        #5
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - content of product split view
            Given Not registered user open WLS page
            Given Search page is opened
            When User want to see split view of each product
            Then He can see that split view is visible and has proper content

        #6
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - clickout from product card
            Given Not registered user open WLS page
            Given Search page is opened
            When User decide to buy product
            Then He Should click Buy button
            When Transition page will be opened
            Then Click in continue anyway option
            And Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_out' 'user_sign_out'

        #7
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - facet component
            Given Not registered user open WLS page
            Given Search page is opened
            When User look at search page
            Then He can see facet component
            And Facet component can be used
            When User use clear all filters button
            Then All filters should be cleared

        #8
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - facet popup window
            Given Not registered user open WLS page
            Given Search page is opened
            When User use see all facets option
            Then Popup with proper filters will be showed
            And User can choose some filter
            And Filter should works
            When User use clear all filters button
            Then All filters should be cleared

