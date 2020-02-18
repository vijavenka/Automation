
#test not implemented  -

Feature: About us static page availability

    As a user
    I require a page I can go to that will supply me with information about the epoints proposition and the people behind it
    So that i can have confidence in using the site

    #1
    Scenario: Checking if hyperlink to About Us page is available
        Given Not registered user open ePoints.com
        When Check header content of component
        Then About hyperlink should be available

    #2
    Scenario: Checking if page content is correct
        Given Not registered user open ePoints.com
        When About Epoints page is opened
        Then Check content of page about epoints

    #3
    Scenario: Check working of get in touch button in about Emarket page
        Given Not registered user open ePoints.com
        When About Epoints page is opened
        Then Go to submit a request page using get in touch button
        Then Check content of 'submit a request page'