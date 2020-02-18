Feature: Login unsuccessful

  Scenario Outline: Admin user unsuccessful login
    Given User is on login page
    When He enters '<username>' as login
    When He enters '<password>' as password
    And He clicks on login button
    Then Login is unsuccessful

    Examples:
      | username    | password   |
      | i.superuser | dummypass  |
      | dummyname   | muppetshow |