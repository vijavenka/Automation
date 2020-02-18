Feature: Vouchers page

   As a user
       I need a page to go to
       Where I can see available vouchers for product from shop

   #1
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check content of Voucher Page
       Given Not registered user open WLS page
       Given Voucher page is opened
       When User look at voucher Page
       Then He Can see standard pagination component and facets
       And All vouchers has all needed elements

   #2
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check clickout from Voucher Page
       Given Not registered user open WLS page
       Given Voucher page is opened
       When User choose some voucher for him
       Then He Can be sure that voucher is valid
       And He can reach external merchant page with voucher code
       And Clickout should be reported 'user_sign_out' 'user_sign_out' from voucher page 'false'

   #3
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check displaying chosen voucher on individual voucher page
       Given Not registered user open WLS page
       Given Voucher page is opened
       When Individual voucher page will be opened
       Then User can see that highlighted voucher has the same content as chosen one
       And Clicking get voucher code user can receive voucher code

   #4
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check reporting of clickout from individual voucher page
       Given Not registered user open WLS page
       Given Voucher page is opened
       When Individual voucher page will be opened
       Then User can click get voucher code
       When Transition page will be opened
       Then Click in continue anyway option
       And Clickout should be reported 'user_sign_out' 'user_sign_out' from voucher page 'true'