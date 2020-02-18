Feature: My activity management

     As an user
     I require an interface where I can review my activity connected with earning epoints
     So I can keep them up to date

     #1
     Scenario: Check if My activity page have all required elements
         Given Registered user open ePoints.com
         And Open My Account page
         Then Go to my activity page
         Then Check if activity page has proper content

     #2
     Scenario Outline: Check current balance page content
         Given Registered user open ePoints.com
         And Open My Account page
         Then Go to my activity page
         And Check if current balance number is the same as in DB for '<userEmail>'
         And Check if current balance content is properly comparing with DB for '<userEmail>'

                Examples:
                    |userEmail|
                    |emailwybitnietestowy@gmail.com|

     #3
     Scenario Outline: Check pending page content
         Given Registered user open ePoints.com
         And Open My Account page
         Then Go to my activity page
         And Open pending tab
         Then User can check if pending number is the same as in DB for '<userEmail>'
         And He can check if pending content is properly comparing with DB for '<userEmail>'

                Examples:
                    |userEmail|
                    |emailwybitnietestowy@gmail.com|

     #4
     Scenario Outline: Check declined page content
         Given Registered user open ePoints.com
         And Open My Account page
         Then Go to my activity page
         And Open declined tab
         Then User can check if declined number is the same as in DB for '<userEmail>'
         And He can check if declined content is properly comparing with DB for '<userEmail>'

                Examples:
                    |userEmail|
                    |emailwybitnietestowy@gmail.com|



