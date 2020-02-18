
Feature: Blog page

    As a user
    I require a page when I have information about different staff connected with epoints, products, retailers.

    #1
    Scenario: Checking if hyperlink to blog page is available and has proper content
        Given Not registered user open ePoints.com
        When Blog web page is opened
        Then Blog Page is available with proper content