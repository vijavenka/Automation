
#test not implemented  - (3)

Feature: Register new user

		As a user
		I require an interface to be able to register for epoints
		So that I can become a member
    #1
	Scenario: Checking mandatory fields validation
		Given Not registered user open Home page
		And Click in Register option
		When Leave form fields empty
  		And Click in Sign up now and get 50 ePoints
		Then Warning 'required' system massage should be shown
		And New account cannot be created
    #2
    Scenario: Checking fields length validation
        Given Not registered user open Home page
        And Click in Register option
        When Start entering chars into fields
        Then Only 254 chars length is allowed
        #And Warning 'required' system length massage should be shown
    #3
    #Scenario: Checking special signs in fields validation
        #Given Not registered user open Home page
        #And Click in Register option
        #When Fill out fields - enter words which include chars from table below
        #Then Fields should be marked as incorrectly filled out
        #And System message should be shown with information about incorrect chars entered into each field
    #4
    Scenario: Checking validation for duplicated email
        Given Not registered user open Home page
        And Click in Register option
        And Fill out email address field with using same email address which is currently in database
        Then Click in Sign up now and get 50 ePoints
        Then Email address field should be marked as incorrectly filled
        And System duplicate warning message should be shown
    #5
    Scenario: Checking validation for incorrect email address
        Given Not registered user open Home page
        And Click in Register option
        When Fill out email address field with using improper email address
        Then Click in Sign up now and get 50 ePoints
        Then Warning 'invalid' system massage should be shown
    #6
    Scenario: Checking if account is created
        Given Not registered user open Home page
        And Click in Register option
        And Fill the registration form
        When Click in Sign up now and get 50 ePoints
        Then New account should be created
        And Account should have status inactive
        And Email submitted page should be opened
        Then On Account should be 50 points

    #7
    Scenario: Checking "Tell me more" information window
        Given Not registered user open Home page
        And Click in Register option
        And Tell me more option available
        When Click in Tell me more option
        Then Information window is opened
        And Close option is available
        And Close button is available
        And Close button behaviour is correct
        And Close option behaviour is correct
        And Esc key behaviour is correct