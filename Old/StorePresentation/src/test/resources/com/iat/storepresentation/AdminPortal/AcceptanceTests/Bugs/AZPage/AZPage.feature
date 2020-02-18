Feature: AZ page

   As a user
   I need a page to go to
   Where I can search retailers and have stored my favourites retailers


        #1
        Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - content of AZ page
            Given Registered user open WLS page
            Given AZ page is opened
            Then All needed elements should be visible in AZ page

        #2
        Scenario Outline:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - working of search
            Given Not registered user open WLS page
            Given AZ page is opened
            Then Searcher is available
            When '<Retailer>' phrase will be entered and search button used
            Then Proper retailer '<Retailer>' should be found

                Examples:
                    |Retailer|
                    |John Lewis|
                    |PC World|

        #3
        Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - alphabetic search
            Given Not registered user open WLS page
            Given AZ page is opened
            Then User can use alphabetic search and it works fine

      #4
      Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - merchant card
            Given Not registered user open WLS page
            Given AZ page is opened
            Then User pointing chosen retailer card can see two buttons
            Then User click Info and offers button and he is redirect to retailer page
            Then Using Visit retailer button he is redirected to transition page

      #5
      Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - favourite logic
            Given Registered user open WLS page
            Given Administration action - remove favourites from DB
            Given AZ page is opened
            And User can not see Favourites block on Stores-AZ when he has no any favourites stores selected
            Then He is able to go to retailer page and select one store as favourite
            When User came back to AZ page he can see Your favourite stores block and selected stores
            And Information about selected store should be written in database
            Then User can decide to remove all stores from favourites
            Then Stores and favourite section will not be displayed

      #6
      Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - favourite logic several products
            Given Registered user open WLS page
            Given AZ page is opened
            Then User can add several retailers to his favourites
            And Favourites stores carousel behave properly
            Then Administration action - remove favourites from DB

      #7
      Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - recently visited stores for not logged user
            Given Not registered user open WLS page
            Given AZ page is opened
            Then Not Logged user can not see recently visited stores component

      #8
      Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - recently visited stores for logged user
            Given Registered user open WLS page
            Given AZ page is opened
            Then Logged user can see recently visited component
            And User can use recently visited stores component

      #9
      Scenario:RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - department filter
            Given Not registered user open WLS page
            Given AZ page is opened
            Then User can see properly displayed department filter
            When One of department will be selected
            Then Retailers from others departments should not be displayed
            And Letters without any content should be hidden
            When User want to see results for all categories again
            Then The same categories as on the beginning should be displayed

