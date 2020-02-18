Feature: Get price comparison

   As a user
       I need a page to go to
       Where I can compare prices of different products

   Scenario: EPOINTS - extend top level navigation to include price comparison link (RD-1366)-user logged out.
       Given Not registered user open ePoints.com
       When Open Get ePoints page
       Then Price comparison link is visible
       When User use price comparison link
       Then He is redirected to correct comparison page

   Scenario: EPOINTS - extend top level navigation to include price comparison link (RD-1366)-user logged in.
       Given Registered user open ePoints.com
       When Open Get ePoints page
       Then Price comparison link is visible
       When User use price comparison link
       Then He is redirected to correct comparison page

   Scenario: EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) check if /price-comparison does not contains navigation module
       Given Not registered user open ePoints.com
       When Open Get ePoints page
       When User use price comparison link
       And Look at comparison page
       Then User can not see department level navigation filter

   Scenario: EPOINTS - create new landing page for price comparison area (products) (RD-1143) - check content of comparison page
       Given Not registered user open ePoints.com
       Given Products comparison  page is opened
       When User look at comparison page
       Then He can see that browser is available
       And Proper Departments links are available

   Scenario Outline: EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check working of search
       Given Not registered user open ePoints.com
       Given Products comparison  page is opened
       When User use search by typing some word '<word>' and pressing search button
       Then He can see results on shop page contains given word '<word>'

            Examples:
            |word|
            |Batman|
            |Sony|

   Scenario: EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check working of category cards
       Given Not registered user open ePoints.com
       Given Products comparison  page is opened
       Then User choosing department is able to see category card with set of links