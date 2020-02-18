@Ews
Feature: EWS - User Details - management
  As an User
  I want to be able manage my account details
  So that I can keep my personal data updated and control over passwords


  @UserDetails @PositiveCase
  Scenario Outline: User details - user account details data update
    When User update account details with following data '<userId>', '<idType>', '<apiKey>', '<jsonBody>'
    Then User details data will be updated properly '<idType>', '<apiKey>'

#  @Production
#    Examples:
#      | userId                        | idType | apiKey                    | jsonBody                                                                                                                                                                                                                                                                           |
#      | user.manager.api.1@iatltd.com | EMAIL  | xHNZaBGQtDmxTkrnI7NOfoXkz | {"firstName": "First", "lastName": "Last", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"}                                            |
#      | user.manager.api.1@iatltd.com | EMAIL  | xHNZaBGQtDmxTkrnI7NOfoXkz | {"firstName": "First Updated", "lastName": "Last Updated", "password": "Passw0rd2", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "DK", "language": "PL", "gender": "MALE"} |

  @ApiBuild @ApiRegression @qa @stag
    Examples:
      | userId                        | idType | apiKey    | jsonBody                                                                                                                                                                                                                                                                                           |
      | user.manager.api.1@iatltd.com | UUID   | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "password": "Passw0rd2", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "DK", "language": "PL", "gender": "MALE", "active": true} |
      | user.manager.api.1@iatltd.com | EMAIL  | accessKey | {"firstName": "First", "lastName": "Last", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE", "active": true}                                            |


  @UserRegistration @PositiveCase @ApiBuild @ApiRegression
  @deleteUserAfterTest
  Scenario Outline: User details by userId
    Given User Manager API is responding to requests
    And User sends registration call with data '<jsonBody>' and '<apiKey>'
    And Account is created and his UUID is returned
    When Request for get user details by userId '<apiKey>', '<idType>'
    Then User details are returned with valid contract

  @qa @stag
    Examples:
      | jsonBody                        | apiKey    | idType |
      | {"email": "user.manager.test_"} | accessKey | UUID   |
      | {"email": "user.manager.test_"} | accessKey | EMAIL  |


  @UserDetails @NegativeCase
  Scenario Outline: User details - get with invalid parameters
    Given User Manager API is responding to requests
    When User get his account details with following invalid data '<email>', '<idType>', '<apiKey>', '<expResponseCode>'
    Then Response message with '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>' is returned for get user details

#  @Production
#    Examples:
#      | email                         | idType      | apiKey                    | expResponseCode | expErrorCode      | expErrorMsg                                                 |
#      | notExistingEmail@gmail.com    | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | 404             | INVALID_USER      | User with EMAIL=[notExistingEmail@gmail.com] is invalid     |
#      | user.manager.api.1@iatltd.com | wrongIdType | xHNZaBGQtDmxTkrnI7NOfoXkz | 400             | INVALID_ARGUMENTS | Unable to parse 'wrongIdType' as value of parameter idType. |
#      | user.manager.api.1@iatltd.com | EMAIL       | wrongAccessKey            | 403             | INVALID_PARTNER   | Partner with accessKey=[wrongAccessKey] is invalid          |

  @ApiBuild @ApiRegression @qa @stag
    Examples:
      | email                         | idType      | apiKey         | expResponseCode | expErrorCode      | expErrorMsg                                                 |
      | notExistingEmail@gmail.com    | EMAIL       | accessKey      | 404             | INVALID_USER      | User with EMAIL=[notExistingEmail@gmail.com] is invalid     |
      | user.manager.api.1@iatltd.com | wrongIdType | accessKey      | 400             | INVALID_ARGUMENTS | Unable to parse 'wrongIdType' as value of parameter idType. |
      | user.manager.api.1@iatltd.com | EMAIL       | wrongAccessKey | 403             | INVALID_PARTNER   | Partner with accessKey=[wrongAccessKey] is invalid          |


  @UserDetails @NegativeCase
  Scenario Outline: User details - update with invalid parameters
    Given User Manager API is responding to requests
    When User update his account details with following invalid data '<userId>', '<idType>', '<apiKey>', '<jsonBody>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Response message with '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>' is returned for get user details

#  @Production
#    Examples:
#      | userId                        | idType      | apiKey                    | jsonBody                                                                                                                                                                                                                                | expResponseCode | expErrorCode         | expErrorMsg                                                      |
#      #incorrect email
#      | notExistingEmail@gmail.com    | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | {"firstName": "First", "lastName": "Last", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"} | 404             | INVALID_USER         | User with EMAIL=[notExistingEmail@gmail.com] is invalid          |
#      #already used email
#      | user.manager.api.1@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | { "email": "user.manager.api.1@iatltd.com" }                                                                                                                                                                                            | 400             | EMAIL_ALREADY_EXISTS | Email is already used by different user                          |
#      #incorrect accessKey (apiKey)
#      | user.manager.api.1@iatltd.com | EMAIL       | accessKeyWrong            | {"firstName": "Updated during wrong apiKey" }                                                                                                                                                                                           | 403             | INVALID_PARTNER      | Partner with accessKey=[accessKeyWrong] is invalid               |
#      #incorrect idType
#      | user.manager.api.1@iatltd.com | wrongIdType | xHNZaBGQtDmxTkrnI7NOfoXkz | {"firstName": "Updated during wrong apiKey" }                                                                                                                                                                                           | 400             | INVALID_ARGUMENTS    | Unable to parse 'wrongIdType' as value of parameter idType.      |
#      #incorrect thirdPartyContact
#      | user.manager.api.1@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | { "thirdPartyContact": "wrongValue" }                                                                                                                                                                                                   | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'thirdPartyContact' |
#      #incorrect revoked
#      | user.manager.api.1@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | { "revoked": "wrongValue" }                                                                                                                                                                                                             | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'revoked'           |
#      #incorrect active
#      | user.manager.api.1@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | { "active": "wrongValue" }                                                                                                                                                                                                              | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'active'            |
#      #incorrect ePointsContact
#      | user.manager.api.1@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | { "ePointsContact": "wrongValue" }                                                                                                                                                                                                      | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'ePointsContact'    |
#      #incorrect birthDate
#      | user.manager.api.1@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | { "birthDate": "2016/12/12" }                                                                                                                                                                                                           | 400             | INVALID_ARGUMENTS    | Long type value is expected for field 'birthDate'                |
#      #incorrect gender
#      | user.manager.api.1@iatltd.com | EMAIL       | xHNZaBGQtDmxTkrnI7NOfoXkz | { "gender": "wrongValue" }                                                                                                                                                                                                              | 400             | INVALID_ARGUMENTS    | Illegal value for field 'gender'                                 |
#      #incorrect title
##      | user.manager.api.1@iatltd.com | EMAIL      | xHNZaBGQtDmxTkrnI7NOfoXkz | { "title": "wrongValue" }                                                                                                                                                                                                               | 400             | INVALID_ARGUMENTS    | Illegal value for field 'title'                                  |
##      #incorrect language
##      | user.manager.api.1@iatltd.com | EMAIL      | xHNZaBGQtDmxTkrnI7NOfoXkz | { "language": "wrongValue" }                                                                                                                                                                                                            | 400             | INVALID_ARGUMENTS    | String of length 2 is expected for field 'language'              |


  @ApiBuild @ApiRegression @qa @stag
    Examples:
      | userId                        | idType      | apiKey         | jsonBody                                                                                                                                                                                                                                | expResponseCode | expErrorCode         | expErrorMsg                                                      |
      #incorrect email
      | notExistingEmail@gmail.com    | EMAIL       | accessKey      | {"firstName": "First", "lastName": "Last", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"} | 404             | INVALID_USER         | User with EMAIL=[notExistingEmail@gmail.com] is invalid          |
      #already used email
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "email": "user.manager.api.1@iatltd.com" }                                                                                                                                                                                            | 400             | EMAIL_ALREADY_EXISTS | Email is already used by different user                          |
      #incorrect accessKey (apiKey)
      | user.manager.api.1@iatltd.com | UUID        | accessKeyWrong | {"firstName": "Updated during wrong apiKey" }                                                                                                                                                                                           | 403             | INVALID_PARTNER      | Partner with accessKey=[accessKeyWrong] is invalid               |
      #incorrect idType
      | user.manager.api.1@iatltd.com | wrongIdType | accessKey      | {"firstName": "Updated during wrong apiKey" }                                                                                                                                                                                           | 400             | INVALID_ARGUMENTS    | Unable to parse 'wrongIdType' as value of parameter idType.      |
      #incorrect thirdPartyContact
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "thirdPartyContact": "wrongValue" }                                                                                                                                                                                                   | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'thirdPartyContact' |
      #incorrect revoked
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "revoked": "wrongValue" }                                                                                                                                                                                                             | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'revoked'           |
      #incorrect active
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "active": "wrongValue" }                                                                                                                                                                                                              | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'active'            |
      #incorrect ePointsContact
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "ePointsContact": "wrongValue" }                                                                                                                                                                                                      | 400             | INVALID_ARGUMENTS    | Only 'true' or 'false' are allowed for field 'ePointsContact'    |
      #incorrect birthDate
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "birthDate": "2016/12/12" }                                                                                                                                                                                                           | 400             | INVALID_ARGUMENTS    | Long type value is expected for field 'birthDate'                |
      #incorrect gender
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "gender": "wrongValue" }                                                                                                                                                                                                              | 400             | INVALID_ARGUMENTS    | Illegal value for field 'gender'                                 |
      #incorrect title
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "title": "wrongValue" }                                                                                                                                                                                                               | 400             | INVALID_ARGUMENTS    | Illegal value for field 'title'                                  |
      #incorrect language
      | user.manager.api.1@iatltd.com | UUID        | accessKey      | { "language": "wrongValue" }                                                                                                                                                                                                            | 400             | INVALID_ARGUMENTS    | String of length 2 is expected for field 'language'              |