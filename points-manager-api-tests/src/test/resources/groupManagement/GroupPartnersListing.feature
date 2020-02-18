Feature: Groups - listing group partners
  As platform admin
  I want to be able to see all members of specific group
  To be able to know which partners are from same client

  @PositiveCase @GroupPartners
  @Regression
  Scenario Outline: Get list of partners for specific group shortName - check response
    Given Points Manager API is responding to requests
    When Get group partners request is made for group shortName '<shortName>'
    Then Get group partners response for request match contract

  @qa @stag @prod
    Examples:
      | shortName  |
      | bdl        |
      | specsavers |
      | epoints    |

  @NegativeCase @GroupPartners
  @Regression
  Scenario Outline: Get list of partners for specific group shortName - check errors messages
    When Get group partners request is made with incorrect group shortName '<shortName>' '<code>'
    Then Get group partners response contains proper error messages for incorrect group shortName '<shortName>' '<message>'

  @qa @stag @prod
    Examples:
      | shortName          | message                                     | code |
      | incorrectShortName | Invalid partnersgroup with name=[shortName] | 500  |