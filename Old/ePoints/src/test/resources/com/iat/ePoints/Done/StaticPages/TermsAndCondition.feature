Feature: T&C static page availability

    As a user
    I require a page I can go to that will supply me with information about the epoints proposition and the people behind it
    So that i can have confidence in using the site


    Scenario: Checking if hyperlink to Terms & Conditions page is available
        Given Not registered user open ePoints.com
        When Check content of footer for T&C link
        Then T&C hyperlink should be available in footer
        And Should link to T&C page

    Scenario: Checking if page content is correct
        Given Not registered user open ePoints.com
        And Terms and Conditions page is opened
        When Check content of page terms and conditions
        Then Information about the Terms and Conditions should be displayed