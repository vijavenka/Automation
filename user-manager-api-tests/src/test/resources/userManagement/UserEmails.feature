Feature: User Details - Email Triggers
  As an User
  I want to be able manage my account details
  So that I can keep my personal data updated and control over passwords


  @Regression @UserDetails @PositiveCase
  Scenario Outline: User details - email resend for change
    Given User Manager API is responding to requests
    When User request to resend '<resentType>' email for '<userId>', '<idType>', '<apiKey>'
    Then Email '<resendType>' will be properly resend

#    Failing sometime by NBO-7792
  @prod
    Examples:
      | userId                        | idType | apiKey                    | resentType |
      | user.manager191276@iatltd.com | EMAIL  | xHNZaBGQtDmxTkrnI7NOfoXkz | mailChange |

  @qa @stag
    Examples:
      | userId                        | idType | apiKey    | resentType |
      | user.manager625844@iatltd.com | EMAIL  | accessKey | mailChange |


  @Regression @UserDetails @PositiveCase
  @deleteUserAfterTest
  Scenario Outline: User details - email resend for registration
    Given User sends registration call with data '<jsonBody>' and '<apiKey>'
    When User request to resend '<resendType>' email 'EMAIL', '<apiKey>'
    Then Email '<resendType>' will be properly resend

  @qa @stag
    Examples:
      | jsonBody                        | apiKey    | resendType   |
      | {"email": "user.manager.test_"} | accessKey | registration |


  @Regression @UserDetails @PositiveCase
  Scenario Outline: User details - email resend for registration
    When User request to resend '<resendType>', '<email>', 'EMAIL', '<apiKey>'
    Then Email '<resendType>' will be properly resend

  @prod
    Examples:
      | email                              | apiKey                    | resendType   |
      | user.manager.unverified@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | registration |

  @qa
    Examples:
      | email                              | apiKey    | resendType   |
      | user.manager.unverified@iatltd.com | accessKey | registration |

  @stag
    Examples:
      | email                              | apiKey    | resendType   |
      | user.manager.unverified@iatltd.com | accessKey | registration |


  @Regression @UserDetails @NegativeCase
  Scenario Outline: User details - resend email with invalid parameters
    Given User Manager API is responding to requests
    When User '<email>' request to resend '<resendType>' email with invalid parameters '<idType>', '<apiKey>', '<expResponseCode>'
    Then Response message with '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>' is returned for get user details

  @prod
    Examples:
      | email                              | idType      | apiKey                    | resendType      | expResponseCode | expErrorCode          | expErrorMsg                                                         |
      | user.manager.api.1@iatltd.com      | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | registration    | 400             | USER_ALREADY_VERIFIED | User has been already verified                                      |
      | notExistingEmail@gmail.com         | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | registration    | 404             | INVALID_USER          | User with EMAIL=[notExistingEmail@gmail.com] is invalid             |
      | user.manager.api.1@iatltd.com      | wrongIdType | xHNZaBGQtDmxTkrnI7NOfoXkz | registration    | 400             | INVALID_ARGUMENTS     | Unable to parse 'wrongIdType' as value of parameter idType.         |
      | user.manager.api.1@iatltd.com      | EMAIL       | wrongAccessKey            | registration    | 403             | INVALID_PARTNER       | Partner with accessKey=[wrongAccessKey] is invalid                  |
      | user.manager.api.1@iatltd.com      | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | wrongResentType | 400             | INVALID_ARGUMENTS     | Unable to parse 'wrongResentType' as value of parameter resendType. |
      | user.manager.unverified@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | mailChange      | 400             | INCORRECT_TOKEN       | Can not find active token to be resend.                             |
      | user.manager.inactive@iatltd.com   | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | mailChange      | 400             | INCORRECT_TOKEN       | Can not find active token to be resend.                             |

  @qa @stag
    Examples:
      | email                              | idType      | apiKey         | resendType      | expResponseCode | expErrorCode          | expErrorMsg                                                         |
      | user.manager.api.1@iatltd.com      | EMAIL       | accessKey      | registration    | 400             | USER_ALREADY_VERIFIED | User has been already verified                                      |
      | notExistingEmail@gmail.com         | EMAIL       | accessKey      | registration    | 404             | INVALID_USER          | User with EMAIL=[notExistingEmail@gmail.com] is invalid             |
      | user.manager.api.1@iatltd.com      | wrongIdType | accessKey      | registration    | 400             | INVALID_ARGUMENTS     | Unable to parse 'wrongIdType' as value of parameter idType.         |
      | user.manager.api.1@iatltd.com      | EMAIL       | wrongAccessKey | registration    | 403             | INVALID_PARTNER       | Partner with accessKey=[wrongAccessKey] is invalid                  |
      | user.manager.api.1@iatltd.com      | EMAIL       | accessKey      | wrongResentType | 400             | INVALID_ARGUMENTS     | Unable to parse 'wrongResentType' as value of parameter resendType. |
      | user.manager.unverified@iatltd.com | EMAIL       | accessKey      | mailChange      | 400             | INCORRECT_TOKEN       | Can not find active token to be resend.                             |
      | user.manager.inactive@iatltd.com   | EMAIL       | accessKey      | mailChange      | 400             | INCORRECT_TOKEN       | Can not find active token to be resend.                             |

