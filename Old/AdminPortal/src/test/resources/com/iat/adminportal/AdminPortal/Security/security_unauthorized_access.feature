Feature: Checking unauthorized access to managers

  Background:
    Given Admin user is successfully logged in

  Scenario Outline: Admin accesses modules without authorization
    Given Admin user is on <module> page
    And He knows his current location
    When He logs out
    And He goes directly to the location
    Then Admin user is redirected to login page

    Examples:
      | module                   |
      | Feed Manager             |
      | Merchant Manager         |
      | Network Manager          |
      | Brand Manager            |
      | Store Categories Manager |
      #  bug here NBO-10426
      | Filter Manager           |
      | Voucher Manager          |
      #  bug here NBO-10426
      | Redemption Manager       |
      | Email Manager            |
      | Member Manager           |