Feature: User Activation - status management
  As a platform admin
  I want to be able to change activation status of selected user
  So that I will be able to control access to the account on epoints.com

  @Regression @UserActivation @PositiveCase
  Scenario Outline: User activation - set status
    Given User Manager API is responding to requests
    When User '<userId>', '<apiKey>' activation status will be changed to '<userActivationStatus>'
    Then '<userId>' account activation status should be changed to '<userActivationStatus>'

  @prod
    Examples:
      | userId                        | apiKey                    | userActivationStatus |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | 0                    |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | 1                    |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | false                |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | true                 |

  @qa @stag
    Examples:
      | userId                        | apiKey    | userActivationStatus |
      | user.manager.api.1@iatltd.com | accessKey | 0                    |
      | user.manager.api.1@iatltd.com | accessKey | 1                    |
      | user.manager.api.1@iatltd.com | accessKey | false                |
      | user.manager.api.1@iatltd.com | accessKey | true                 |


  @Regression @UserActivation @NegativeCase
  Scenario Outline: User activation status - not existing user or not allowed status parameters
    Given User Manager API is responding to requests
    When '<user>' tries to change user status with wrong parameters '<idType>', '<apiKey>', '<userActivateStatus>', '<status>', '<expErrorCode>', '<expErrorMsg>'
    Then User activation status should not be changed due to '<status>', '<expErrorCode>', '<expErrorMsg>'

  @prod
    Examples:
      | user                          | userActivateStatus | idType  | apiKey                    | status | expErrorCode      | expErrorMsg                                             |
      | notExistingEmail@gmail.com    | false              | EMAIL   | xHNZaBGQtDmxTkrnI7NOfoXkz | 404    | INVALID_USER      | User with EMAIL=[notExistingEmail@gmail.com] is invalid |
      | user.manager.api.1@iatltd.com | wrong              | EMAIL   | xHNZaBGQtDmxTkrnI7NOfoXkz | 400    | INVALID_ARGUMENTS | Unable to parse 'wrong' as value of parameter state.    |
      | user.manager.api.1@iatltd.com | true               | wrongId | xHNZaBGQtDmxTkrnI7NOfoXkz | 400    | INVALID_ARGUMENTS | Unable to parse 'wrongId' as value of parameter idType. |
      | user.manager.api.1@iatltd.com | true               | EMAIL   | wrongAccessKey            | 403    | INVALID_PARTNER   | Partner with accessKey=[wrongAccessKey] is invalid      |


  @qa @stag
    Examples:
      | user                          | userActivateStatus | idType  | apiKey         | status | expErrorCode      | expErrorMsg                                             |
      | notExistingEmail@gmail.com    | false              | EMAIL   | accessKey      | 404    | INVALID_USER      | User with EMAIL=[notExistingEmail@gmail.com] is invalid |
      | user.manager.api.1@iatltd.com | wrong              | EMAIL   | accessKey      | 400    | INVALID_ARGUMENTS | Unable to parse 'wrong' as value of parameter state.    |
      | user.manager.api.1@iatltd.com | true               | wrongId | accessKey      | 400    | INVALID_ARGUMENTS | Unable to parse 'wrongId' as value of parameter idType. |
      | user.manager.api.1@iatltd.com | true               | EMAIL   | wrongAccessKey | 403    | INVALID_PARTNER   | Partner with accessKey=[wrongAccessKey] is invalid      |
