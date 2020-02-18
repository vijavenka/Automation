Feature: Registration - user store verification
  As an User
  I want to be able to verify account creation possibility
  So that I will have full information whether I can create desired epoints account

  @Regression @UserStoreVerification @PositiveCase
  Scenario Outline: User verification - new store/user verification
    Given User Manager API is responding to requests
    When User store verification call is made with following data '<email>', '<lastName>', '<partnerId>', '<apiKey>'
    Then '<expectedResponse>' will contain information about possibility of new user/store creation

  @qa @stag
    Examples:
      | email                 | lastName       | partnerId | apiKey    | expectedResponse           |
      | uniqueEmail@gmail.com | uniqueLastName | 2         | accessKey | []                         |
      | pl@iatltd.com         | uniqueLastName | 2         | accessKey | ["storeEmail"]             |
      | uniqueEmail@gmail.com | PLStore        | 2         | accessKey | ["storeName"]              |
      | pl.store@iatltd.com   | PLStore        | 2         | accessKey | ["storeEmail","storeName"] |

  @Regression @UserStoreVerification @NegativeCase
  Scenario Outline: User verification - new store/user verification with invalid parameters
    Given User Manager API is responding to requests
    When User store verification call is made with incorrect data '<email>', '<lastName>', '<partnerId>', '<apiKey>', '<expResponseCode>'
    Then Response with '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>' should be returned

  @qa @stag
    Examples:
      | email                 | lastName       | partnerId            | apiKey            | expResponseCode | expErrorCode      | expErrorMsg                                                             |
      | uniqueEmail@gmail.com | uniqueLastName | notExistingPartnerId | accessKey         | 400             | INVALID_ARGUMENTS | Unable to parse 'notExistingPartnerId' as value of parameter partnerId. |
      | uniqueEmail@gmail.com | uniqueLastName | 2                    | notExistingApiKey | 403             | INVALID_PARTNER   | Partner with accessKey=[notExistingApiKey] is invalid                   |