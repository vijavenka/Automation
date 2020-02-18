Feature: Get shop page

   As a user
       I need a page to see retailer offer without leaving ePoints page
       Where I can search, see and buy product which are in offer of all retailer

        #1
        Scenario: EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check department navigation visibility
            Given Not registered user open ePoints.com
            When Open Get ePoints page
            When User use price comparison link
            And User choose some department
            Then Department navigation component is available

        #2
        Scenario: EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if department navigation has proper content
            Given Shop page is opened
            Then Department navigation component is available
            And Department left drop down is available and has proper content
            And Search department component is available
            And Department right drop dow list is available

        #3
        Scenario Outline: EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if search works correctly
            Given Shop page is opened
            When User type some phrase '<phrase>' into the searcher
            Then He can see that results are correct according to given word '<phrase>'

                Examples:
                    |phrase|
                    |Batman|
                    |Sony|
        #4
        Scenario Outline: EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if department filter works
            Given Shop page is opened
            When User type some phrase '<phrase>' into the searcher
            And Change department and try to search once again
            Then Different product should be presented according to department

                Examples:
                    |phrase|
                    |Batman|

        #5
        Scenario Outline: EPOINTS - add points to redeem display to product interfaces (RD-1152) - check visibility and working of links #we search to find some product with redeem display, it is assumed that this is first product
            Given Shop page is opened
            When User type some phrase '<phrase>' into the searcher
            Then He can see products with redeem price
            And Link on product tab works correct
            And Link on split view works correct
            When User open individual product shop page
            Then He can see two redeem links
            And Both redeem links works correctly


                Examples:
                    |phrase|
                    |Rascals - Very Best of Rascals|

        #6
        Scenario: EPOINTS - add DEPARTMENT interfaces to epoints (RD-1154) - Department page availability from comparison page
            Given Not registered user open ePoints.com
            When Open Get ePoints page
            When User use price comparison link
            Then He is able to choose some department
            And Open department page using link in popup menu
	        And User can see categories list and categories circles

        #7
        Scenario: EPOINTS - add DEPARTMENT interfaces to epoints (RD-1154) - Department page availability from shop page
            Given Shop page is opened
            Then He is able to choose some department from DDL
            And Open department page using link from DDL
            And User can see categories list and categories circles

        #8
        Scenario: EPOINTS - add DEPARTMENT interfaces to epoints (RD-1154) - second level of department
            Given Shop page is opened
            Then He is able to choose some department from DDL
            And Open department page using link from DDL
            When User chose one of categories
            Then He will be redirected to second level department page or shop page
	    And At the end category page has all needed search, products and  pagination elements

        #9
        Scenario: EPOINTS - add PRODUCT PAGE interface to epoints (RD-1146) - product page availability from comparison page
           Given Shop page is opened
           When User open product page of chosen product
           Then He can see that all product page elements are available
           And Values like name, prise, epoints are the same
           When User decide to back to previous page and use back to previous page button
           Then He will be redirect to price comparison page

        #10
        Scenario: EPOINTS - add PRODUCT PAGE interface to epoints (RD-1146) - product page availability from retailer page
           Given Registered user open ePoints.com
           Then User choose a retailer with special offers block
           When User open product page of chosen product from special offers block
           Then He can see that all product page elements are available
           And Values like name, prise, epoints are the same
           When User decide to back to previous page and use back to previous page button
           Then He will be redirect to retailer page of the same retailer

        #12
        Scenario: EPOINTS - add CATEGORY interfaces to epoints.com (RD-1145 ) - category reached form retailer page category block
             Given Registered user open ePoints.com
             Then User choose a retailer
             When User choose some category in category block
             Then He will be redirected to correct category page
             And At the end category page has all needed search, products and  pagination elements

        #13
        Scenario: EPOINTS - add SEARCH RESULTS interfaces to epoints (RD-1153) - content of product cards
            Given Shop page is opened
            When User look at shop page
            And Viewing products he can see that product cards have all needed elements

        #14
        Scenario: EPOINTS - add SEARCH RESULTS interfaces to epoints (RD-1153) - split view
            Given Shop page is opened
            When User want to see split view of each product
            Then He can see that split view is visible and has proper content

        #15
        Scenario: EPOINTS - add SEARCH RESULTS interfaces to epoints (RD-1153) - facet component
            Given Shop page is opened
            When User look at shop page
            Then He can see facet component
            And Facet component can be used
            When User use clear all filters button
            Then All filters should be cleared

        #16
        Scenario: EPOINTS - add SEARCH RESULTS interfaces to epoints (RD-1153) - facet popup
            Given Shop page is opened
            When User use see all facets option
            Then Popup with proper filters will be showed
            And User can choose some filter
            And Filter should works
            When User use clear all filters button
            Then All filters should be cleared

        #17
        Scenario: EPOINTS - add SEARCH RESULTS interfaces to epoints (RD-1153) - clickout from product card
            Given Registered user open ePoints.com
            Given Shop page is opened
            When User decide to buy product and go to retailer page
            Then He Should click Buy button
            And Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_out' 'user_sign_out' from shop page

        #18
        Scenario: EPOINTS - extend top level navigation to include shop links (RD-1139) - user logged in
            Given Registered user open ePoints.com
            When Open Get ePoints page
            Then He can see vouchers daily deals and special offers additional links
            And User can use them and see that they works properly

        #19
        Scenario: EPOINTS - extend top level navigation to include shop links (RD-1139) - user logged out
            Given Not registered user open ePoints.com
            When Open Get ePoints page
            Then He can see vouchers daily deals and special offers additional links
            And User can use them and see that they works properly



