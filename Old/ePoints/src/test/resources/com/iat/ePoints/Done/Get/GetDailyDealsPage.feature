Feature: Get daily deals

   As a user
       I need a page to go to
       Where I can see daily deals of products presented in shop

   #1
   Scenario: EPOINTS - add DAILY DEALS list interfaces to epoints (RD-1141) - check content of all daily deals
       Given Not registered user open ePoints.com
       Given Daily deals page is opened
       When User look at daily deals Page
       Then He Can see standard pagination component and facets
       And All daily deals has all needed elements

   #2
   Scenario: EPOINTS - add DAILY DEALS list interfaces to epoints (RD-1141) - check possibility of buying daily deals and check reported clickout
       Given Registered user open ePoints.com
       Given Daily deals page is opened
       When User want to buy some daily deal
       Then He Click get this deal button
       And Transition page should be replaced with retailer page
       And New clickout should be reported 'user_sign_out' 'user_sign_out' from daily deals page

   #3 // TODO this option is for now unavailable
   #Scenario: EPOINTS - add DAILY DEALS list interfaces to epoints (RD-1141) - epoints range, price, saving slider
       #Given Not registered user open ePoints.com
       #Given Daily deals page is opened
       #Given Facets popup window is opened
       #When User use slider to set epoints earned range value
       #And User use slider to set price range value
       #And User use slider to set saving range value
       #And User admit filter changes
       #Then He can see that presented product match those criteria

   #4
   Scenario: DAILY DEALS - ensure proper use of location filter on epoints (RD-1151) - check showing of modal windows with locations
       Given Not registered user open ePoints.com
       Given Daily deals page is opened without closing location window
       When User look at daily deals page
       Then He can see modal location window
       And Modal location window has proper content

   #5
   Scenario: DAILY DEALS - ensure proper use of location filter on epoints (RD-1151) - check working of change location button
       Given Not registered user open ePoints.com
       Given Daily deals page is opened
       When User close modal location window
       Then He is able to change location using change location button

   #6
   Scenario: EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check page content
       Given Not registered user open ePoints.com
       Given Daily deals page is opened
       When User chose some of daily deals and click on it
       Then Individual daily deal page should be opened
       And User can see that it has proper content and proper values
       When User decide to back to previous page
       Then He can use back to previous page button and he will be redirected to it

   #7
   Scenario: EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check working of clickout
       Given Registered user open ePoints.com
       Given Daily deals page is opened
       When User chose some of daily deals and click on it
       Then Individual daily deal page should be opened
       When Decide to get deal, then he should click get button
       Then Transition page should be replaced with retailer page
       And New clickout should be reported 'user_sign_out' 'user_sign_out' from daily deals page

   #8
   Scenario: EPOINTS - add DAILY DEALS individual product interface to epoints (RD-1795) - check link to retailer
       Given Not registered user open ePoints.com
       Given Daily deals page is opened
       When User chose some of daily deals and click on it
       Then Individual daily deal page should be opened
       When Used decide to go to deal retailer page
       Then He should click retailer link
       And Proper retailer page should be opened