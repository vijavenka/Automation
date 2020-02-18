Feature: Social icons availability

    As a user
    I expect to see standard Twitter and Facebook interfaces and expect the required logic to work in a standard format
    So I can like and share with my friends


    Scenario: Checking if social icons are available
        Given Not registered user open ePoints.com
        When Check content of footer for Social icons
        Then Facebook icon should be available
        And Twitter icon should be available