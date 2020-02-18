
Feature: Invite friend using facebook

     As an user
     I require an interface where I can invite my friends on facebook
     So I can receive points when they will use epoints

     #1 Check content of invite friend page
     Scenario Outline: REFER A FRIEND - update epoints,com for new interface (RD-666).
         Given Not registered user open Home page
         And Click in Sign In option
         And Authentication panel is shown
         And Sign in with facebook button should works
         And User enter facebook credentials '<emailFb>' '<passwordFb>' and confirm login details
         And Open My Account page
         Then Dashboard navigation option is available
         Then Go to invite friends page
         And Check content of invite friends page

            Examples:
                |emailFb|passwordFb|
                |iat.epoints.test@gmail.com|everest01|

     #2v1 Check Invite friend module behaviour when user login using fb credentials.
     Scenario Outline: REFER A FRIEND - access control, epoints and Facebook v1 (RD-668).
         Given Not registered user open Home page
         And Click in Sign In option
         And Authentication panel is shown
         And Sign in with facebook button should works
         And User enter facebook credentials '<emailFb>' '<passwordFb>' and confirm login details
         And Open My Account page
         Then Go to invite friends page
         And All invite friend options should be available for user logged via facebook

            Examples:
                |emailFb|passwordFb|
                |emailwybitnietestowy@gmail.com|Delete777|

     #2v2 Check Invite friend module behaviour when user login using epoints credentials.
     Scenario Outline: REFER A FRIEND - access control, epoints and Facebook v2
          Given Not registered user open ePoints.com
          When User use '<email>' and '<password>' in Log in panel
          And Click in Sign In button
          Then User should be correctly logged in
          Then Open My Account page
          And Go to invite friends page
          Then User should login via facebook to use invite friend options

            Examples:
                |email|password|
                |emailwybitnietestowy@gmail.com|Delete777|

     #3


     #4 Adding link on user wall
     Scenario Outline: REFER A FRIEND - option to add link on own wall (RD-669).
          Given Not registered user open Home page
          And Click in Sign In option
          And Authentication panel is shown
          And Sign in with facebook button should works
          And User enter facebook credentials '<emailFb>' '<passwordFb>' and confirm login details
          And Open My Account page
          Then Go to invite friends page
          And Post Link on own wall
          Then Check if link was posted and it works '<emailFb>' '<passwordFb>'

             Examples:
                 |emailFb|passwordFb|
                 |iat.epoints.test@gmail.com|everest01|

     #5
     Scenario Outline: REFER A FRIEND - record friend access against referer record (RD-672).
          Given User followed fb referer '<refererEmail>' link
          Then He '<friendEmail>' joined epoints
          And RefererID, how accessed '<refererEmail>' are correctly stored in friend '<friendEmail>' data in db
          Then Administration action - delete user from db '<friendEmail>'

             Examples:
             	|refererEmail|friendEmail|
             	|iat.epoints.test@gmail.com|emailwybitnietestowy3@gmail.com|

     #6
     Scenario Outline: REFER A FRIEND - Award points on friend completing registration (RD-673) & REFER A FRIEND - add friend identifier to http session (RD-712).
          Given User followed fb referer '<refererEmail>' link
          And He navigated through different pages to get information about ePoints
          And Referer '<refererEmail>' knows how many points he has
          Then Sign in with facebook button should works
          And User enter facebook credentials '<friendFbEmail>' '<friendFbPassword>' and confirm login details
          Then New account should be created
          And Referer '<refererEmail>' should received another fifty points
          Then Administration action - delete points history from request and points tables for '<refererEmail>'
          Then Administration action - delete user from db '<friendFbEmail>'


             Examples:
                |refererEmail|friendFbEmail|friendFbPassword|
                |iat.epoints.test@gmail.com|emailwybitnietestowy3@gmail.com|Delete777|

     #7
       Scenario Outline: REFER A FRIEND - option to add link on friends wall (RD-670).
          Given Not registered user open Home page
          And Click in Sign In option
          And Authentication panel is shown
          And Sign in with facebook button should works
          And User enter facebook credentials '<emailFb>' '<passwordFb>' and confirm login details
          And Open My Account page
          Then Go to invite friends page
          And Use search to find proper friend '<friendName>'
          Then Post Link on friend wall
          And Check if link was posted on friend wall and it works '<friendFbEmail>' '<friendFbPassword>' '<emailFb>'


             Examples:
                 |emailFb|passwordFb|friendName|friendFbEmail|friendFbPassword|
                 |iat.epoints.test@gmail.com|everest01|Przemek Karierowicz|emailwybitnietestowy12@gmail.com|Delete777|

     #8
      Scenario Outline: REFER A FRIEND - disable friends who are members when adding to friends wall (RD-671).
          Given Not registered user open Home page
          And Click in Sign In option
          And Authentication panel is shown
          And Sign in with facebook button should works
          And User enter facebook credentials '<emailFb>' '<passwordFb>' and confirm login details
          And Open My Account page
          Then Go to invite friends page
          And Use search to find proper friend '<friendName>'
          And Check If user can not add link on friend wall who i already ePoints member

             Examples:
                 |emailFb|passwordFb|friendName|
                 |iat.epoints.test@gmail.com|everest01|Daniel Porebski|























