Feature: Registration - user account
  As an User
  I want to be able to register on user-manager side
  So that I will have active account to gather and manage my epoints

  @Regression @UserRegistration @PositiveCase
  @deleteUserAfterTest
  Scenario Outline: User registration - create new user by email only
    Given User Manager API is responding to requests
    When User sends registration call with data '<jsonBody>' and '<apiKey>'
    Then Account is created and his UUID is returned
    And He should receive points for registration
    And User 'previous_call' token is generated and active

  @qa @stag
    Examples:
      | jsonBody                        | apiKey    |
      | {"email": "user.manager.test_"} | accessKey |
#      | {"email": "user.manager.api.2@iatltd.com"} | xHNZaBGQtDmxTkrnI7NOfoXkz |


  @Regression @UserRegistration @PositiveCase
  @deleteUserAfterTest
  Scenario Outline: User registration - create new user
    Given User Manager API is responding to requests
    When User sends registration call with data '<jsonBody>' and '<apiKey>'
    Then Account is created and his UUID is returned
    And He should receive points for registration
    And Registration token have to be generated '<tokenGenerated>'

  @qa @stag
    Examples:
      | apiKey    | tokenGenerated | jsonBody                                                                                                                                                                                                                                                                                                                     |
      | accessKey | true           | {"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}                                                                           |
      | accessKey | false          | {"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}                                                   |
      | accessKey | false          | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_", "password": "Passw0rd2", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE"} |


  @Regression @UserRegistration @NegativeCase
  Scenario Outline: User registration - new user with invalid parameters
    Given User Manager API is responding to requests
    When User makes registration call with invalid '<jsonBody>' and '<apiKey>', '<expResponseCode>'
    Then Response message with '<expResponseCode>', '<expErrorCode>', '<responseMessage>', '<expErrorMsg>' is returned and account is not created

  @qa @stag
    Examples:
      | apiKey    | jsonBody                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | expResponseCode | expErrorCode      | expErrorMsg                                                               | responseMessage                                                                           |
      #without email
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE", "userId": null, "idType": "UUID", "title": "Mr" }                                                                                                                            | 400             | INVALID_ARGUMENTS | Cannot register user without e-mail address                               | Cannot register user without e-mail address                                               |
      #incorrect accessKey (apiKey)
      | WrongKey  | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_", "password": "Passw0rd2", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE"}                                                                                                                                                | 403             | INVALID_PARTNER   | Partner with accessKey=[WrongKey] is invalid                              | Partner with accessKey=[WrongKey] is invalid                                              |
      #invalid idType
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE", "userId": "12345", "idType": "Wrong" }                                                                                  | 400             | INVALID_ARGUMENTS | Cannot register user (with userId = [12345]) and invalid idType = [Wrong] | Cannot register user (with userId = [12345]) and invalid idType = [Wrong]                 |
      #incorrect thirdPartyContact
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "title": "Mr", "gender": "MALE", "birthDate": "20161212", "ePointsContact": false, "thirdPartyContact": "wrongValue", "testUser": false } | 400             | INVALID_ARGUMENTS | not_specified                                                             | com.iat.compassmassive.user.controller.to.UserCreationTO["thirdPartyContact"])            |
      #incorrect ePointsContact
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "title": "Mr", "gender": "MALE", "birthDate": "20161212", "ePointsContact": "wrongValue", "thirdPartyContact": false, "testUser": false } | 400             | INVALID_ARGUMENTS | not_specified                                                             | com.iat.compassmassive.user.controller.to.UserCreationTO["ePointsContact"])               |
      #incorrect Birth date
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "title": "Mr", "gender": "MALE", "birthDate": "2016/12/12", "ePointsContact": false, "thirdPartyContact": false, "testUser": false }      | 400             | INVALID_ARGUMENTS | not_specified                                                             | Failed to parse Date value '2016/12/12'                                                   |
      #incorrect Gender
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "title": "Mr", "gender": "WRONG" }                                                                                                        | 400             | INVALID_ARGUMENTS | not_specified                                                             | from String value 'WRONG': value not one of declared Enum instance names: [FEMALE, MALE]  |
      #incorrect Title
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "title": "WRONG", "gender": "MALE" }                                                                                                      | 400             | INVALID_ARGUMENTS | not_specified                                                             | from String value 'WRONG': value not one of declared Enum instance names: [Miss, Mr, Mrs] |
      #incorrect Language
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "WRONG", "title": "Mr", "gender": "MALE" }                                                                                                      | 400             | INVALID_ARGUMENTS | String of length 2 is expected for field 'language'                       | String of length 2 is expected for field 'language'                                       |

  @stag
    Examples:
      | apiKey    | jsonBody                                                                                                                                                                                                                                                                                                                                                                                                                 | expResponseCode | expErrorCode | expErrorMsg | responseMessage                      |
      #already used email
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE"}                                                                      | 201             | null         | null        | a7fc2640-a0b2-40b6-a7a1-b971170dcea6 |
      #already used email uuid
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE", "userId": "a7fc2640-a0b2-40b6-a7a1-b971170dcea6", "idType": "UUID" } | 201             | null         | null        | a7fc2640-a0b2-40b6-a7a1-b971170dcea6 |

  @qa
    Examples:
      | apiKey    | jsonBody                                                                                                                                                                                                                                                                                                                                                                                                                 | expResponseCode | expErrorCode | expErrorMsg | responseMessage                      |
      #already used email
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE"}                                                                      | 201             | null         | null        | c85e327f-2557-4508-8cb3-548864c4a5df |
      #already used email uuid
      | accessKey | {"firstName": "First Updated", "lastName": "Last Updated", "email": "user.manager.test_1481614933458@test.bdl", "password": "Passw0rd27", "mobileNumber": "12345", "street": "Street update", "county": "county update", "country": "country update", "postcode": "postcode update", "region": "region update", "language": "PL", "gender": "MALE", "userId": "a7fc2640-a0b2-40b6-a7a1-b971170dcea6", "idType": "UUID" } | 201             | null         | null        | c85e327f-2557-4508-8cb3-548864c4a5df |

#  #setting users
#  @MANUAL
#  Scenario Outline: User registration - create new user
#    Given User Manager API is responding to requests
#    When User sends registration call with data '<jsonBody>' and '<apiKey>'
#    Then Account is created and his UUID is returned
#    And He should receive points for registration
#    And Registration token have to be generated '<tokenGenerated>'
#
#    Examples:
#      | apiKey    | tokenGenerated | jsonBody                                                                                                                                                                                                                                                                            |
#      | accessKey | false          | {"firstName": "First", "lastName": "Last", "email": "user.manager.api.1@iatltd.com", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"}   |
#      | accessKey | false          | {"firstName": "First", "lastName": "Last", "email": "user.manager625844@iatltd.com", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"}   |
#      #below one unfortunately requires manual change (in dynamo) of active to false
#      | accessKey | false          | "firstName": "First", "lastName": "Last", "email": "user.manager.inactive@iatltd.com", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"} |
#      | accessKey | false          | {"firstName": "First", "lastName": "PLStore", "email": "pl@iatltd.com", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"}                |
#      | accessKey | false          | {"firstName": "First", "lastName": "Last", "email": "pl.store@iatltd.com", "password": "Passw0rd", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "UK", "language": "UK", "gender": "FEMALE"}             |
