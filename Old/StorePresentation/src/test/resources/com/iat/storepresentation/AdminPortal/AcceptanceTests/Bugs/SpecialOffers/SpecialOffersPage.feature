Feature: Special offers page

   As a user
       I need a page to go to
       Where I can see special offers and tool to filter them by percentage saving ord department name

       #1
       Scenario:FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check content of special offers page
            Given Not registered user open WLS page
            Given Special Offers Page is Opened
            When User look at special offers page
            Then He can see filter module
            And Few proposed special offers cards

       #2
       Scenario:FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check working of view special offer button
            Given Not registered user open WLS page
            Given Special Offers Page is Opened
            When User decide to see offer and click view button
            Then Special products offer should be opened

       #3
       Scenario:FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check working of percentage saving filter, department filter and price range
            Given Not registered user open WLS page
            Given Special Offers Page is Opened
            When User chose some percentage saving range
            And User chose department and category
            Then After click show me button proper product should be displayed
            And User can use price filter
            And Be sure that it works correctly