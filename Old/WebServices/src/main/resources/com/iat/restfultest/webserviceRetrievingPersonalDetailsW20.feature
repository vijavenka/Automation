Feature: Get user personal details via webservice

As an ePoints client,
I want to be able to retrieve non-sensitive details about my user such as first name, etc. from the ePoints platform where these exist
So I can know my user better and personalize their experience

Background:
    Given I know who my ePoints user is

Scenario: Retrieving user information
    When I make simple call to get his information
    Then All non-sensitive details will be returned

Scenario: Retrieving parametrized user information
    When I make call to get information from fields
    Then I will receive all requested details

Scenario: Retrieving user information with invalid userId
    When I make call to get information with invalid userId
    Then I will not receive any details

Scenario: Retrieving user information with invalid accessKey
    When I make call to get information with invalid accessKey
    Then I will not receive any details

Scenario: Retrieving user information with inactive state
    When I make call to get information about inactive user
    Then I will not receive any details

Scenario: Retrieving user information for inactive client
    When I make call to get information of user from inactive client
    Then I will not receive any details


