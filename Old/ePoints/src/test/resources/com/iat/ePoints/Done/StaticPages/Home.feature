
Feature: Home page

    As a user
    I require a page containing main information about epoints
    So I can easily navigate directly to my desired destination

        Scenario: Checking if home page has proper content
            Given Not registered user open ePoints.com
            And He can see that page contains all needed sections

        Scenario: Checking if home page links works correctly
            Given Not registered user open ePoints.com
            And He can use all links from home page

        Scenario: EPOINTS & WLS - make footer changes (RD-1920).
            Given Not registered user open ePoints.com
            When User look at page footer
            Then He can see partners  button
            When User use partner button
            Then He will be redirected to for partner page
