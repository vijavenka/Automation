Feature: Add/edit user details

     As an user
     I require an interface where I can review and edit my personnel details
     So I can keep them up to date

     #1
     Scenario: Check if My profile page have all required elements for filled details
         Given Registered user open ePoints.com
         And Open My Account page
         Then Dashboard navigation option is available
         Then Go to my profile page
         And Account details section should be available
         And Account details section should have required elements
         And Personal details section should be available
         And Personal detail section should be properly displayed for user
         And Contact details section should be available
         And Contact details section should be properly displayed for user

     #2 //TODO uncomment part of test when RD-1295 will be fixed
     Scenario Outline: Check if I am able to change my email address
         Given Not registered user open ePoints.com
         When User use '<oldEmail>' and '<Password>' in Log in panel
         And Click in Sign In button
         And Open My Account page
         Then Go to my profile page
         Then Edit email address
         Then Enter new email address data '<newEmail>'
         Then Confirm email changes
         And Check page behaviour after email changing
         #Then Confirm new email using Link and check changes in DB '<oldEmail>' '<newEmail>'
         #Then Administration action - restore email in DB '<oldEmail>' '<newEmail>'

            Examples:
                |oldEmail|newEmail|Password|
                |iat.epoints.test@gmail.com|emailwybitnietestowyxxx@gmail.com|everest01|
                #|emailwybitnietestowyxxx@gmail.com|iat.epoints.test@gmail.com|everest01|

     #3
     Scenario Outline: Check if I am able to change my password
         Given Not registered user open Home page
         Then User use '<email>' and '<oldPassword>' in Log in panel
         Then Click in Sign In button
         And Open My Account page
         Then Go to my profile page
         Then Edit my password
         Then Enter new password data with '<oldPassword>' '<newPassword>' '<repeatNewPassword>'
         Then Confirm password changes
         And Check page behaviour after password changing

         Examples:
         	|email|oldPassword|newPassword|repeatNewPassword|
         	|emailwybitnietestowy@gmail.com|Delete777|Delete778|Delete778|
         	|emailwybitnietestowy@gmail.com|Delete778|Delete777|Delete777|

     #4
     Scenario Outline: Check if I am able to change my personal data
         Given Registered user open ePoints.com
         And Open My Account page
         Then Go to my profile page
         Then Edit my personal data
         Then Enter new personal data using '<Name>' '<LastName>' '<Title>' '<Gender>' '<DateOfBirth>'
         Then Confirm personal data changes
         And Check page behaviour after personal data changing
         Then Logout form ePoints page
         Then User use '<Email>' and '<Password>' to Log into existing account
         And Click in Sign In button
         And Open My Account page
         Then Go to my profile page
         Then Compare all personal user data with data entered before '<Name>' '<LastName>' '<Title>' '<Gender>' '<DateOfBirth>' '<Email>'

         Examples:
         	|Email|Password|Name|LastName|Title|Gender|DateOfBirth|
         	|emailwybitnietestowy@gmail.com|Delete777|Magdalena|Respondek|Miss|FEMALE|01/01/1988|
         	|emailwybitnietestowy@gmail.com|Delete777|Krzysztof|Baranowski|Mr|MALE|08/06/1989|

     #5
     Scenario Outline: Check if I am able to change my contact data
         Given Registered user open ePoints.com
         And Open My Account page
         Then Go to my profile page
         Then Edit my contact details
         Then Enter new contact details '<Phone>' '<HouseNumber>' '<Street>' '<City>' '<County>' '<Country>' '<Postcode>'
         Then Confirm contact details changes
         And Check page behaviour after contact details changing
         Then Logout form ePoints page
         Then User use '<Email>' and '<Password>' to Log into existing account
         And Click in Sign In button
         And Open My Account page
         Then Go to my profile page
         Then Compare all contact details with data entered before '<Phone>' '<HouseNumber>' '<Street>' '<City>' '<County>' '<Country>' '<Postcode>' '<Email>'

         Examples:
          	|Email|Password|Phone|HouseNumber|Street|City|County|Country|Postcode|
          	|emailwybitnietestowy@gmail.com|Delete777|111222333|80|Poboczna|Wroclaw|Dolnoslaskie|Poland|34-500|
          	|emailwybitnietestowy@gmail.com|Delete777|695805680|70|Glowna|Krasowice|Opolskie|PolandOnceAgain|46-100|

     #6
     Scenario: Check if I am able to cancel editing of data in user profile module
         Given Registered user open ePoints.com
         And Open My Account page
         Then Go to my profile page
         Then Edit email address
         Then Cancel email data edition
         And Check visibility of save email button
         Then Edit my password
         Then Cancel password edition
         And Check visibility of save password button
         Then Edit my personal data
         Then Cancel personal details edition
         And Check visibility of save personal details button
         Then Edit my contact details
         Then Cancel contact details edition
         And Check visibility of save contact details button

     #7 Check if I am able to create my password as facebook user
     Scenario Outline: allow new FACEBOOK users to create epoints password (RD-1110).
         Given Not registered user open Home page
         And Click in Sign In option
         And Authentication panel is shown
         And Sign in with facebook button should works
         And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
         Then New account should be created
         Then Open My Account page
         And Go to my profile page
         Then Edit my password for first time
         Then Create new password data with '<newPassword>' '<repeatNewPassword>'
         Then Confirm password changes
         And Log out and try to login using new ePoints password or email '<fbEmail>' '<newPassword>'

         Examples:
         	|fbEmail|fbPassword|newPassword|repeatNewPassword|
         	|emailwybitnietestowy3@gmail.com|Delete777|Delete778|Delete778|

     #8 Check if Iam able to  change email and use it next to fb account
     Scenario Outline: EPOINTS - facebook registered user changes email address (RD-1111).
         Given Not registered user open Home page
         And Click in Sign In option
         And Authentication panel is shown
         And Sign in with facebook button should works
         And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
         Then Open My Account page
         And Go to my profile page
         Then Edit email address
         Then Enter new email address data '<newEpointsEmail>'
         Then Confirm email changes
         And Check page behaviour after email changing
         Then Follow confirmation link sent on user email '<fbEmail>'
         And Log out and try to login using new ePoints password or email '<newEpointsEmail>' '<newEpointsPassword>'
         Then Administration action - delete user from db '<newEpointsEmail>'

         Examples:
         	|fbEmail|fbPassword|newEpointsEmail|newEpointsPassword|
         	|emailwybitnietestowy3@gmail.com|Delete777|newemail1989@wp.pl|Delete778|
