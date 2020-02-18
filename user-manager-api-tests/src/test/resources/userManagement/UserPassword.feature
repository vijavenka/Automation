Feature: User Details - Password reset
  As an User
  I want to be able manage my account details
  So that I can keep my personal data updated and control over passwords


  @Regression @UserPassword @PositiveCase
  Scenario Outline: User details - reset password mail
    Given User Manager API is responding to requests
    When User request to reset his account '<email>', '<apiKey>' password
    Then Reset password email properly resend

  @prod
    Examples:
      | email                         | apiKey                    |
      | user.manager191276@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz |

  @qa @stag
    Examples:
      | email                         | apiKey    |
      | user.manager625844@iatltd.com | accessKey |


  @Regression @UserPassword @NegativeCase
  Scenario Outline: User details - reset password with invalid parameters
    Given User Manager API is responding to requests
    When User tries to reset his password with wrong parameters '<email>', '<apiKey>', '<active>', '<verified>', '<expResponseCode>'
    Then User password should remain due to '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'

  @prod
    Examples:
      | email                              | apiKey                    | active | verified | expResponseCode | expErrorCode         | expErrorMsg                                                          |
      | user.manager191276@iatltd.com      | wrongAccessKey            | true   | true     | 403             | INVALID_PARTNER      | Partner with accessKey=[wrongAccessKey] is invalid                   |
      | notExistingEmail@gmail.com         | xHNZaBGQtDmxTkrnI7NOfoXkz | true   | true     | 404             | INVALID_USER         | User with EMAIL=[notExistingEmail@gmail.com] is invalid              |
      | user.manager.inactive@iatltd.com   | xHNZaBGQtDmxTkrnI7NOfoXkz | false  | true     | 400             | USER_IS_NOT_ACTIVE   | User with EMAIL=[user.manager.inactive@iatltd.com] is not active     |
      | user.manager.unverified@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | true   | false    | 400             | USER_IS_NOT_VERIFIED | User with EMAIL=[user.manager.unverified@iatltd.com] is not verified |

  @qa @stag
    Examples:
      | email                              | apiKey         | active | verified | expResponseCode | expErrorCode         | expErrorMsg                                                          |
      | user.manager625844@iatltd.com      | wrongAccessKey | true   | true     | 403             | INVALID_PARTNER      | Partner with accessKey=[wrongAccessKey] is invalid                   |
      | notExistingEmail@gmail.com         | accessKey      | true   | true     | 404             | INVALID_USER         | User with EMAIL=[notExistingEmail@gmail.com] is invalid              |
      | user.manager.inactive@iatltd.com   | accessKey      | false  | true     | 400             | USER_IS_NOT_ACTIVE   | User with EMAIL=[user.manager.inactive@iatltd.com] is not active     |
      | user.manager.unverified@iatltd.com | accessKey      | true   | false    | 400             | USER_IS_NOT_VERIFIED | User with EMAIL=[user.manager.unverified@iatltd.com] is not verified |