
#test not implemented  -

Feature: Cookies page

    As a user
    I require a page I can go to page which explains me what is a cookie and why they are used on ePoints website

    #1
    Scenario: Checking if hyperlink to cookies page is available and has proper content
        Given Not registered user open ePoints.com
        When Cookies web page is opened
        Then Cookies Page is available with proper content

