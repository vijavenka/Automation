Feature: User Details - user group
  As a user-manager's component
  I want to add group with externalId
  So that I can process external business transactions

  @Regression @PositiveCase @UserGroup
  @deleteUserAfterTest
  @qa @stag
  Scenario: User group creation - with externalId
    Given User Manager API is responding to requests
    And User sends registration call with data '{"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}' and 'accessKey'
    When User sends adding group call with data "{"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}" and "accessKey"
    Then User's group is matching the one which was sent, apiKey: accessKey
    And User's externalIdUnited parameter is set properly, apiKey: accessKey

  @Regression @PositiveCase @UserGroup
  @deleteUserAfterTest
  Scenario Outline: User group creation - with externalId - with status other than "active"
    Given User Manager API is responding to requests
    And User sends registration call with data '{"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}' and 'accessKey'
    When User sends adding group call with data "<json>" and "accessKey"
    Then User's group is matching the one which was sent, apiKey: accessKey
    And User's externalIdUnited parameter is not set, apiKey: accessKey

  @qa @stag
    Examples:
      | json                                                                                                                                                                                                  |
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "deleted", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"} |
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}                      |

  @Regression @PositiveCase @UserGroup
  @deleteUserAfterTest
  Scenario Outline: User group update - with externalId - from status "active" to  status other than "active"
    Given User Manager API is responding to requests
    And User sends registration call with data '{"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}' and 'accessKey'
    When User sends adding group call with data "{"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}" and "accessKey"
    When User sends adding group call with data "<json>" and "accessKey"
    Then User's group is matching the one which was sent, apiKey: accessKey
    And User's externalIdUnited parameter is not set, apiKey: accessKey

  @qa @stag
    Examples:
      | json                                                                                                                                                                                                  |
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "deleted", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"} |
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}                      |

  @Regression @PositiveCase @UserGroup
  @deleteUserAfterTest
  @qa @stag
  Scenario: User group deletion - with externalId
    Given User Manager API is responding to requests
    And User sends registration call with data '{"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}' and 'accessKey'
    When User sends adding group call with data "{"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}" and "accessKey"
    When User sends deleting that group call, apiKey: accessKey
    Then User's group status is properly changed, apiKey: accessKey
    And User's externalIdUnited parameter is not set, apiKey: accessKey

  @Regression @NegativeCase @UserGroup
  @deleteUserAfterTest
  Scenario Outline: User group creation - error validation
    Given User Manager API is responding to requests
    And User sends registration call with data '{"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}' and 'accessKey'
    When User sends adding group call with incorrect data '<json>' and '<accessKey>', '<status>'
    Then Correct group creation error message will be returned '<error>', '<message>'

  @qa @stag
    Examples:
      | json                                                                                                                                                                                                         | error             | message                                                                                                          | status | accessKey   |
#      wrong externalIdType
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "notexisting", "partnerId": 2147297958, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}    | INVALID_ARGUMENTS | Arguments externalIdType: notexisting is incorrect. Supported types: [united]                                    | 400    | accessKey   |
#      not existing partnerId
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 21472979589999, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}     | INVALID_ARGUMENTS | Partner for given id: 21472979589999 does not exist.                                                             | 400    | accessKey   |
#      not existing groupId
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":408999999999, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"} | INVALID_ARGUMENTS | VirtualGroup for given id: 408999999999 does not exist.                                                          | 400    | accessKey   |
#      partnerId and groupId do not match
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":1002, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}         | INVALID_ARGUMENTS | Provided partnerId: 2147297958 is not correct for provided Virtual Group [id: 1002, name: ePoints, partnerId: 2] | 400    | accessKey   |
#      wrong active value
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "activeeeee", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}     | INVALID_ARGUMENTS | Status has incorrect value: activeeeee. Supported values for status: [active, deleted]                           | 400    | accessKey   |
#      wrong accessKey
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}         | INVALID_PARTNER   | Partner with accessKey=[notexisting] is invalid                                                                  | 403    | notexisting |

  @Regression @NegativeCase @UserGroup
  @deleteUserAfterTest
  Scenario Outline: User group deletion - error validation
    Given User Manager API is responding to requests
    And User sends registration call with data '{"firstName": "First", "lastName": "Last", "email": "user.manager.test_", "mobileNumber": "54321", "street": "Street", "county": "county", "country": "country", "postcode": "postcode", "region": "region", "language": "UK", "gender": "FEMALE"}' and 'accessKey'
    When User sends adding group call with data "{"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"}" and "accessKey"
    When User sends deleting group call with incorrect data '<json>' and '<accessKey>', '<status>'
    Then Correct group creation error message will be returned '<error>', '<message>'

  @qa @stag
    Examples:
      | json                                                                                                                                                                                                 | error           | message                                         | status | accessKey   |
      | {"externalId": "$AUTO_TEST_GROUP", "externalIdType": "united", "partnerId": 2147297958, "groupId":4089, "status": "active", "firstName": "First", "lastName": "Last", "email": "email_test@iat.iat"} | INVALID_PARTNER | Partner with accessKey=[notexisting] is invalid | 403    | notexisting |