Feature: Retailer page

   As a user
       I need a page to see retailer offer without leaving ePoints page
       Where I can get all information about retailer offer, delivery details, vouchers etc.

   #1
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - checking page content
        Given Not registered user open WLS page
        Then User choose a retailer
        And Top section module has proper content
        And Delivery details section has proper content

   #2
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - trust pilot
        Given Not registered user open WLS page
        Then User choose a retailer
        And Can see that trust pilot is available
        And Trust pilot information are available in modal window

   #3
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - vouchers on retailer page
        Given Not registered user open WLS page
        Given Retailer page with vouchers is opened
        Then User can see voucher box with proper content
        And User can be redirected to retailer page
        And Proper voucher code is displayed on ePints page

   #4
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - vouchers no exists
        Given Not registered user open WLS page
        Given Retailer page without vouchers specials and categories is opened
        Then Voucher block should be not visible

   #5
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - vouchers see all button
        Given Not registered user open WLS page
        Given Retailer page with vouchers is opened
        When User click see all vouchers from specific retailer
        Then He will be redirected to voucher page
        And All vouchers will belongs to specific retailer

   #6
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - special offers block
        Given Not registered user open WLS page
        Then User choose a retailer
        And Special offers block is visible
        And Special offers block has proper content
        And Offers are displayed in decreasing order
        When User want to see another offer
        Then He use navigation component
        And Different offer are displayed

   #7
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - category block
        Given Not registered user open WLS page
        Then User choose a retailer
        And Categories filter block is visible
        And Categories filter block has proper content

   #8
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - no category block
        Given Not registered user open WLS page
        Then Retailer page without vouchers specials and categories is opened
        And User can not see category block when retailer has not popular categories

   #9 //TODO
   #Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - similar retailers block
        #Given Not registered user open WLS page
       	#Then User choose a retailer
        #And Similar retailers block is visible
        #And Similar retailers block has proper content
        #When User decide to see another retailer card
        #Then He is able to be redirected to retailer page using similar retailer block
        #When User com back to previous retailer
        #And Decide to visit external retailer page
        #Then He is able to be redirected to retailer external page using similar retailer block

   #10
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - no similar retailers block
        Given Not registered user open WLS page
        Then Retailer page without vouchers specials and categories is opened
        Then Similar retailers block should be not visible

   #11
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - update to special offers block
        Given Not registered user open WLS page
        Then User choose a retailer
        And User See that one hundred offers is available
        And All savings are below fifty percent
        When User use see all button
        Then He is redirected to offers page contains all offers

   #12
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - update to special offers block all product from retailer visible
        Given Not registered user open WLS page
        Then User choose a retailer
        And He can see all product from retailer block with products number
        Then He can use link to see all product from retailer
        And He is redirected to proper page
        And product number is the same as on retailer page block

   #13
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - update to special offers block all product from retailer invisible
        Given Not registered user open WLS page
        Then Retailer page without vouchers specials and categories is opened
        And User can not see all product from retailer block on retailer page