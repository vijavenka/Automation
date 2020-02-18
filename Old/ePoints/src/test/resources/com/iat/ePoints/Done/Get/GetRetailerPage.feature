Feature: Get retailer page

   As a user
       I need a page to see retailer offer without leaving ePoints page
       Where I can get all information about retailer offer, delivery details, vouchers etc.

   #1
   Scenario: Check content of retailer page
        Given Not registered user open ePoints.com
        Then User choose a retailer
        And Top section module has proper content
        And Delivery details section has proper content

   #2
   Scenario: RETAILER PAGE ON EPOINTS - Trust Pilot integration on new retailer page (RD-1158).
        Given Not registered user open ePoints.com
        Then User choose a retailer
        And Can see that trust pilot is available
        And Trust pilot information are available in modal window

   #3
   Scenario: RETAILER PAGE ON EPOINTS - VOUCHER block logic (RD-1160) voucher exists
        Given Registered user open ePoints.com
        Given Retailer page with vouchers is opened
        Then User can see voucher box with proper content
        And User can be redirected to retailer page
        And Proper voucher code is displayed on ePints page

   #4
   Scenario: RETAILER PAGE ON EPOINTS - VOUCHER block logic (RD-1160) voucher not exists
        Given Registered user open ePoints.com
        Given Retailer page without vouchers specials and categories is opened
        Then Voucher block should be not visible

   #5
   Scenario: RETAILER PAGE ON EPOINTS - VOUCHER block logic( RD-1160) see all button
        Given Registered user open ePoints.com
        Given Retailer page with vouchers is opened
        When User click see all vouchers from specific retailer
        Then He will be redirected to voucher page
        And All vouchers will belongs to specific retailer

   #6 In this scenario see all and buy buttons does not work #It is expected that for popular stores special offers are available
   Scenario: RETAILER PAGE ON EPOINTS - SPECIAL OFFER block logic (RD-1161).
        Given Registered user open ePoints.com
        Then User choose a retailer with special offers block
        And Special offers block is visible
        And Special offers block has proper content
        And Offers are displayed in decreasing order
        When User want to see another offer
        Then He use navigation component
        And Different offer are displayed

   #7 #We are not comparing categories to solr results as it is covered in Jmeter scripts #It is expected that for popular stores special offers are available
      #This script was updated according to changes includes in RD-1360
   Scenario: RETAILER PAGE ON EPOINTS - PRODUCT CATEGORIES logic block (RD-1162)(RD-1360).
        Given Registered user open ePoints.com
        Then User choose a retailer
        And Categories filter block is visible
        And Categories filter block has proper content

   #8
   Scenario: RETAILER PAGE ON EPOINTS - add see more category functionality to category block (RD-1360) no popular categories
        Given Registered user open ePoints.com
        Then Retailer page without vouchers specials and categories is opened
        And User can not see category block when retailer has not popular categories

   #9
   Scenario: RETAILER PAGE ON EPOINTS - SIMILIAR RETAILERS block logic (Rd-1163).
        Given Registered user open ePoints.com
       	Then User choose a retailer with similar retailers block
        And Similar retailers block is visible
        And Similar retailers block has proper content
        When User decide to see another retailer card
        Then He is able to be redirected to retailer page using similar retailer block
        When User com back to previous retailer
        And Decide to visit external retailer page
        Then He is able to be redirected to retailer external page using similar retailer block

   #10
   Scenario: RETAILER PAGE ON EPOINTS - SIMILIAR RETAILERS block logic (Rd-1163).
        Given Registered user open ePoints.com
        Then Retailer page without vouchers specials and categories is opened
        Then Similar retailers block should be not visible

   #11
   Scenario: Check if show all retailer button works correctly
        Given Not registered user open ePoints.com
        Then User choose a retailer
        And He can see that show all retailers button is available
        And User can use show all retailers button and be redirected to stores AZ page

   #12
   Scenario: RETAILER PAGE ON EPOINTS - required update to special offer block (RD-1520).
        Given Registered user open ePoints.com
        Then User choose a retailer with special offers block
        And User See that one hundred offers is available
        And All savings are below fifty percent
        When User use see all button
        Then He is redirected to offers page contains all offers

   #13
   Scenario: Retailer page -  all products from retailer block - block should be visible case
        Given Not registered user open ePoints.com
        Then User choose a retailer
        And He can see all product from retailer block with products number
        Then He can use link to see all product from retailer
        And He is redirected to proper page
        And product number is the same as on retailer page block

   #14
   Scenario:RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - update to special offers block all product from retailer invisible
        Given Not registered user open WLS page
        Then Retailer page without vouchers specials and categories is opened
        And User can not see all product from retailer block on retailer page







