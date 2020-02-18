

Feature: Join in using facebook

		As a user
		I require an interface to be able to register for epoints using facebook creditionals
		So that I can become a member

        #1
        Scenario: Check if Join interface has proper facebook option
            Given Not registered user open ePoints.com
            And Join via facebook is available on main page
            Then Click in Register option
            Then Join interface should has sign in with facebook option
            And Sign in with facebook button should works
            And Sign in with facebook window should has proper content

        #2 Check if user can join using facebook and all needed data are properly download to epoints
        Scenario Outline: USER REGISTRATION - add facebook integration to Join interface (RD-652)(RD-653).
            Given Not registered user open ePoints.com
            Given Administration action - delete user from db 'emailwybitnietestowy3@gmail.com'
            Then Click in Register option
            And Sign in with facebook button should works
            And User enter facebook credentials '<fbEmail>' '<fbPassword>' and confirm login details
            Then New account should be created
            And No confirmation email should be sent
            And Congratulation message should be displayed
            And User can see that his avatar is visible
            And User can see that he received '<pointsNumber>' points
            And His '<name>' '<lastName>' '<gender>' '<dateOfBirth>' '<fbEmail>' were properly downloaded
            And All data '<name>' '<lastName>' '<gender>' '<dateOfBirthFbFormat>' '<fbEmail>' are correctly stored in db
            And User is recognized as facebook user
            Then Administration action - delete user from db '<fbEmail>'

                Examples:
                    |name|lastName|gender|dateOfBirth|fbEmail|fbPassword|pointsNumber|dateOfBirthFbFormat|
                    |Daniel|Porebski|MALE|02/03/1972|emailwybitnietestowy3@gmail.com|Delete777|50|1972-03-02 00:00:00.0|

