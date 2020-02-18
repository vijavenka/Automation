
Feature: Registration email

    As a user when I register my email address
    I require an email sent to me that will include a link back to epoints.com
    So I be available to confirm my registration process

    #1
    Scenario: Checking if registration email is sent correctly
        Given Not registered user open Home page
        And Click in Register option
        When Fill the registration form
        And Click in Sign up now and get 50 ePoints
        Then Confirmation email should be sent on user email box

    #2
    Scenario Outline: Resend confirmation email for verified User
        Given Not registered user open Home page
        When User try to resend confirmation email
        And Enter verified email '<userEmail>'
        And Confirm sending verification email
        Then Proper warning alert should be displayed '<verified>'

            Examples:
                |userEmail|verified|
                |emailwybitnietestowy@gmail.com|yes|

    #3
    Scenario Outline: Resend confirmation email for not verified email
        Given Not registered user open Home page
        When User try to resend confirmation email
        And Enter not verified email '<userEmail>'
        And Confirm sending verification email
        Then Proper warning alert should be displayed '<verified>'

            Examples:
                |userEmail|verified|
                |krzysztofbaranowski@gmail.com|no|