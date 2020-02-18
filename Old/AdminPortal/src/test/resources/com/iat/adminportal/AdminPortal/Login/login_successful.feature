Feature: Login successful

  Scenario: Admin user successful login
    Given User is on login page
    When He enters 'i.superuser' as login
    When He enters 'theMightyBlues' as password
    And He clicks on login button
    Then Admin user is correctly logged in