Feature: Get daily deals

   As a user
       I need a page to go to
       Where I can see daily deals of products presented in shop

   #1
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check content of all daily deals
       Given Not registered user open WLS page
       Given Daily deals page is opened
       When User look at daily deals Page
       Then He Can see standard pagination component and facets
       And All daily deals has all needed elements

   #2
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check possibility of buying daily deals and check reported clickout
       Given Not registered user open WLS page
       Given Daily deals page is opened
       When User want to buy some daily deal
       Then He Click get this deal button
       When Transition page will be opened
       Then Click continue anyway option
       Then Transition page should be replaced with retailer page
       And New clickout should be reported 'user_sign_out' 'user_sign_out' from daily deals page

   #3 //TODO fo now sliders are disabled
   #Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - - epoints range, price, saving slider
       #Given Not registered user open WLS page
       #Given Daily deals page is opened
       #Given Facets popup window is opened
       #When User use slider to set epoints earned range value
       #And User use slider to set price range value
       #And User use slider to set saving range value
       #And User admit filter changes
       #Then He can see that presented product match those criteria

   #4
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - location from home page
       Given Not registered user open WLS page
       When User chose daily deals location from DDL
       Then He Will be redirected to daily deal page
       And Proper location will be applied for search results

   #5
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - location from popup window
       Given Not registered user open WLS page
       Given Daily deals page is opened
       Given Facets popup window is opened
       When User chose daily deals location from popup window
       And Confirmed settings using done button
       And Proper location will be applied for search results

   #6
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check individual daily deals page content
       Given Not registered user open WLS page
       Given Daily deals page is opened
       When User chose some of daily deals and click on it
       Then Individual daily deal page should be opened
       And User can see that it has proper content and proper values
       When User decide to back to previous page
       Then He can use back to previous page button and he will be redirected to it

   #7
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check working of clickout from individual daily deal page
       Given Not registered user open WLS page
       Given Daily deals page is opened
       When User chose some of daily deals and click on it
       Then Individual daily deal page should be opened
       When Decide to get deal, then he should click get button
       Then Transition page will be opened
       When Click continue anyway option
       Then Transition page should be replaced with retailer page
       And New clickout should be reported 'user_sign_out' 'user_sign_out' from daily deals page

   #8
   Scenario:FRONT END REFACTOR - add epoints search solution to WLS Daily Deals pages (RD-2098) - check link to retailer from individual daily deal page
       Given Not registered user open WLS page
       Given Daily deals page is opened
       When User chose some of daily deals and click on it
       Then Individual daily deal page should be opened
       When Used decide to go to deal retailer page
       Then He should click retailer link
       And Proper retailer page should be opened






