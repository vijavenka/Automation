Feature: Login into admin portal
  As an administrator
  I want to have login functionality
  So that I will be able to authorize in the system and use admin portal managers

  Scenario Outline: Admin user successful login
    Given User is on login page
    When He enters '<login>' as login
    When He enters '<password>' as password
    And He clicks on login button
    Then Admin user is correctly logged in

    Examples:
      | login       | password       |
      | i.superuser | theMightyBlues |
