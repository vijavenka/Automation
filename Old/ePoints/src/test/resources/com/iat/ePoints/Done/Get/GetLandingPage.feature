Feature: Get landing page

   As a user
   I need a page to go to
   Where I can be informed on where and how I can earn epoints to help me broaden my understanding of where I can earn

        #1
        Scenario: Checking page structure
            Given Not registered user open ePoints.com
            When Open Get ePoints page
            Then Get ePoints page should be opened properly
            And Get ePoints page have proper sections
            And Get ePoints sections have proper content

        #2
        Scenario: Checking if Links and buttons works properly
            Given Not registered user open ePoints.com
            When Open Get ePoints page
            Then Get ePoints page have proper sections
            And All links and buttons should works properly

        #3
        Scenario: RETAILER PAGE ON EPOINTS - change retailer links on home and get pages (RD-1156).
            Given Not registered user open ePoints.com
            Then Following any retailer link from main page user can see that retailer url is correct
            Then User can check that url's are correct also on get page
            Then User can check that url's are correct also on stores a-z page
            Then User can check that url's are correct also on about us page

        #4
        Scenario: Checking content of Stores A-Z page
            Given Shops A-Z page is opened
            Then All needed elements should be visible in AZ page

        #5
        Scenario Outline: Checking working of searcher
             Given Shops A-Z page is opened
             Then Searcher is available
             When '<Retailer>' phrase will be entered and search button used
             Then Proper retailer '<Retailer>' should be found

            Examples:
                |Retailer|
                |John Lewis|
                |ACHICA|

        #6
        Scenario: Checking if Alphabetic search works fine
            Given Shops A-Z page is opened
            Then User can use alphabetic search and it works fine

        #7
        Scenario: RETAILER PAGE ON EPOINTS - add pagination to A-Z interface(RD-1168).
            Given Shops A-Z page is opened
            Then User can see that pagination component is available
            And Showing module is working correctly
            And Items per page module is working correctly
            And Bottom pagination module is working correctly

        #8
        Scenario: RETAILER PAGE ON EPOINTS - update A-Z search results interface
            Given Shops A-Z page is opened
            Then User pointing chosen retailer card can see two buttons
            Then User click Info and offers button and he is redirect to retailer page
            Then Using Visit retailer button he is redirected to transition page

        #9
        Scenario: RETAILER PAGE ON EPOINTS - make retailer favourite logic and A-Z page change (RD-1159).
            Given Registered user open ePoints.com
            Given Administration action - remove favourites from DB
            And User can not see Favourites block on Stores-AZ when he has no any favourites stores selected
            Then He is able to go to retailer page and select one store as favourite
            When User came back to AZ page he can see Your favourite stores block and selected stores
            And Information about selected store should be written in Database
            Then User can decide to remove all stores from favourites
            Then Stores and favourite section will not be displayed

        #10
        Scenario: RETAILER PAGE ON EPOINTS - make retailer favourite logic and A-Z page change (RD-1159) - more retailers added
           Given Registered user open ePoints.com
           Given Shops A-Z page is opened
           Then User can add several retailers to his favourites
           And Favourites stores carousel behave properly
           Then Administration action - remove favourites from DB

        #11
        Scenario: Ensure that Recently visited stores is disabled for not logged user
            Given Shops A-Z page is opened
            Then Not Logged user can not see recently visited stores component

        #12
        Scenario: Ensure that Recently visited stores is enabled for logged user and works fine
            Given Registered user open ePoints.com
            Given Shops A-Z page is opened
            Then Logged user can see recently visited component
            And User can use recently visited stores component

        #13 RETAILER PAGE ON EPOINTS - add department filter to A-Z results (RD-1167) //////////////////////////////////
        Scenario: Check if department filter works correctly on AZ page
             Given Shops A-Z page is opened
             Then User can see properly displayed department filter
             When One of department will be selected
             Then Retailers from others departments should not be displayed
             And Letters without any content should be hidden
             When User want to see results for all categories again
             Then The same categories as on the beginning should be displayed