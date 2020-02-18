Feature: Registration - user account verification
  As an User
  I want to be able manage my account details
  So that I can keep my personal data updated and control over passwords


  @Regression @UserRegistration @PositiveCase
  @deleteUserAfterTest
  Scenario Outline: User details - user ids list correctness after token verification
    Given User sends registration call with data '{"email": "user.manager.test_"}' and '<apiKey>'
    And User '<email>' token is generated and active
    When User token will be verified '<apiKey>'
    Then His system ids list will be returned

  @qa @stag
    Examples:
      | email         | apiKey    |
      | previous_call | accessKey |


  @Regression @UserRegistration @NegativeCase
  Scenario Outline: User details - verify request done with invalid parameters
    Given User Manager API is responding to requests
    When User '<email>' token will be verified with invalid values of parameters '<apiKey>', '<token>', '<expResponseCode>'
    Then Token verification response should contain error information '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'

  @prod
    Examples:
      | email                         | token      | apiKey                    | expResponseCode | expErrorCode    | expErrorMsg                                        |
      | user.manager.api.1@iatltd.com | wrongToken | xHNZaBGQtDmxTkrnI7NOfoXkz | 400             | INCORRECT_TOKEN | Invalid token                                      |
      | user.manager.api.1@iatltd.com | wrongToken | wrongAccessKey            | 403             | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid |

  @qa @stag
    Examples:
      | email                         | token      | apiKey         | expResponseCode | expErrorCode    | expErrorMsg                                        |
      | user.manager.api.1@iatltd.com | wrongToken | accessKey      | 400             | INCORRECT_TOKEN | Invalid token                                      |
      | user.manager.api.1@iatltd.com | wrongToken | wrongAccessKey | 403             | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid |
