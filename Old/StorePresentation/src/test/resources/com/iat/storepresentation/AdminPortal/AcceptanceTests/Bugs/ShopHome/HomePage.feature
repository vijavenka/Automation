Feature: Home Page

   As a user
       I need a home page
       Where I can see and use basic navigation options, login tools, and products

        #1
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - content of product cards
            Given Not registered user open WLS page
            When User look at home page
            And Viewing products he can see that product cards have all needed elements

        #2
        #Scenario:FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - split view
            #Given Not registered user open WLS page
            #When User want to see split view of each product
            #Then He can see that split view is visible and has proper content

        #3
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout
            Given Not registered user open WLS page
            When User decide to buy product
            Then He Should click Buy button
            When Transition page will be opened
            Then Click in continue anyway option
            And Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_out' 'user_sign_out'

        #4
        #Scenario:FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout from split view
            #Given Not registered user open WLS page
            #When User decide to buy product from split view
            #Then He Should click Buy button on split view
            #When Transition page will be opened
            #Then Click in continue anyway option
            #And New clickout should be reported 'user_sign_out' 'user_sign_out'

        #5
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - product individual page compare with product card
           Given Not registered user open WLS page
           When User open product page of chosen product
           Then He can see that all product page elements are available
           And Values like name, prise, epoints are the same
           When User decide to back to previous page and use back to previous page button
           Then He will be redirect to home page

        #6
        Scenario:FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout from individual product page
           Given Not registered user open WLS page
           When User open product page of chosen product
           And Click buy product on individual product page
           When Transition page will be opened
           Then Click in continue anyway option
           And Transition page should be replaced with retailer page
           And New clickout should be reported 'user_sign_out' 'user_sign_out' from individual product page
