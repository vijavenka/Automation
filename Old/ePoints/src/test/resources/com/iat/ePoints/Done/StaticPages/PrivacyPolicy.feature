
#test not implemented  -

Feature: Privacy Policy static page availability

    As a user
    I require a page I can go to that will supply me with information about the epoints proposition and the people behind it
    So that i can have confidence in using the site

    #1
    Scenario: Checking if hyperlink to Privacy Policy page is available
        Given Not registered user open ePoints.com
        When Check content of footer for PrivacyPolicy link
        Then Privacy Policy hyperlink should be available in footer
        And Should link to Privacy Policy page

    #2
    Scenario: Checking if page content is correct
       Given Not registered user open ePoints.com
       When Check content of footer for PrivacyPolicy link
       Then Privacy Policy hyperlink should be available in footer
       Then Information about the privacy policy should be displayed