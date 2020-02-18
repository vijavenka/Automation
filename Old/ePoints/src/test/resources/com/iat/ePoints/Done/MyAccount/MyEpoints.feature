Feature: My epoints management

     As an user
     I require an interface where I can review epoints history
     So I can know how I earned them

     #1
     Scenario: Check if My epoints page have all required elements
         Given Registered user open ePoints.com
         And Open My Account page
         When My Account page opened correctly
         Then Dashboard navigation option is available
         And My epoints navigation option is available
         And Epoints sub page should be opened correctly
         And Epoints sub page should have required elements
     #2
     Scenario: Check if Me epoints page links works correctly
         Given Registered user open ePoints.com
         And Open My Account page
         When My Account page opened correctly
         Then Dashboard navigation option is available
         And My epoints navigation option is available
         And All activity link works properly
         Then I am able to return to my epoints tab
         And Find you favourite stores button works properly
