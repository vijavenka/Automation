Feature: Get vouchers

   As a user
       I need a page to go to
       Where I can see available vouchers for product from shop

   #1
   Scenario: EPOINTS - add VOUCHERS interfaces to epoints (RD-1140) - check content of Voucher Page
       Given Not registered user open ePoints.com
       Given Voucher page is opened
       When User look at voucher Page
       Then He Can see standard pagination component and facets
       And All vouchers has all needed elements

   #2
   Scenario Outline: EPOINTS - add VOUCHERS interfaces to epoints (RD-1140) - check possibility of using voucher, db validate, voucher code and clickout
       Given Registered user open ePoints.com
       Given Voucher page is opened
       When User choose some voucher for him
       Then He Can be sure that voucher is valid
       And He can reach external merchant page with voucher code
       And Clickout should be reported 'user_sign_in' '<email>' from voucher page

                Examples:
                    |email|
                    |emailwybitnietestowy@gmail.com|

   #3
   Scenario Outline: EPOINTS - add VOUCHER individual product interface to epoints (RD-1805) - check displaying chosen voucher on individual voucher page
        Given Registered user open ePoints.com
        Given Voucher page is opened
        When Individual voucher page will be opened
        Then User can see that highlighted voucher has the same content as chosen one
        And Clicking get voucher code user can receive voucher code

                Examples:
                    |fbEmail|fbPassword|
                    |iat.epoints.test@gmail.com|everest01|

   #4
   Scenario Outline: EPOINTS - add VOUCHER individual product interface to epoints (RD-1805) - check reporting of clickout from individual voucher page
        Given Registered user open ePoints.com
        Given Voucher page is opened
        When Individual voucher page will be opened
        Then User can click get voucher code
        And Clickout should be reported 'user_sign_in' '<email>' from voucher page

                Examples:
                    |email|
                    |emailwybitnietestowy@gmail.com|
