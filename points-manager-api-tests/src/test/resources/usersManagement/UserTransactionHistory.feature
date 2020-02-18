Feature: Users - transaction history
  As platform admin
  I want to be able segment transaction history of users by client, group and filter them
  So that I can see users transaction and rewards history for selected client or group which match set filter criteria

  #on prod wee need to change user or add transactions for used here

#  NOTE!!!
#  user used for those tests was pointsManagerTestsUser@gmail.pl until 07.06.2017 but it doesn't have any transactions that is why emailfortest@wp.pl is used
#  it is widely used user by "KB"

  @TransactionHistory @PositiveCase
  @Regression
  Scenario Outline: User transaction history - get user transaction history for specified client
    Given Points Manager API is responding to requests
    When Users transaction history will be pulled for specified client with following data '<requestParameters>'
    Then Users transaction history for specified client will be properly returned '<requestParameters>'

  @qa @stag
    Examples:
      | requestParameters                                                                      |
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,null,null |

  @TransactionHistory @NegativeCase
  Scenario Outline: User transaction history - get user transaction history for specified client using invalid parameters
    Given Points Manager API is responding to requests
    When Users transaction history will be pulled for specified client with following invalid data '<requestParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Users transaction history for specified client will not be properly returned

  @Regression @qa @stag
    Examples:
      | requestParameters                                                                              | expResponseCode | expErrorCode         | expErrorMsg                                              |
      #//incorrect clientId
      | wrongClientId,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,null,null       | 403             | UNAUTHORIZED_PARTNER | Unauthorized partner with shortName=[wrongClientId]      |
      #//incorrect apiKey
      | ePoints.com,WrongAccessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,null,null    | 403             | UNAUTHORIZED_PARTNER | Unauthorized partner with shortName=[ePoints.com]        |
      #//incorrect user email
      | ePoints.com,accessKey,notExistingEmail@gmail.com,EMAIL,null,null,null,null,null,null,null,null | 404             | INVALID_USER         | User with userId=[notExistingEmail@gmail.com] is invalid |

  @skip @qa @stag
    Examples:
      | requestParameters                                                                                  | expResponseCode | expErrorCode  | expErrorMsg   |
      #//incorrect idType - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,wrongIdType,null,null,null,null,null,null,null,null       | 400             | not_specified | not_specified |
      #//incorrect status - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,wrongStatus,null,null,null,null,null,null,null      | 400             | not_specified | not_specified |
      #//incorrect tagName  - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,wrongTagName,null,null,null,null,null,null     | 400             | not_specified | not_specified |
      #//incorrect BehalfOffId  - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,wrongBehalfOffId,null,null,null,null,null | 400             | not_specified | not_specified |
      #//incorrect ascending  - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,wrongAscending,null,null,null,null   | 400             | not_specified | not_specified |
      #//incorrect offset  - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,wrongOffset,null,null,null      | 400             | not_specified | not_specified |
      #//incorrect limit  - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,wrongLimit,null,null       | 400             | not_specified | not_specified |
      #//incorrect date - string  - bug
      | ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,wrongDateFormat,null  | 400             | not_specified | not_specified |

  @TransactionHistory @PositiveCase
  @Regression
  Scenario Outline: User transaction history - get user transaction history for specified group
    Given Points Manager API is responding to requests
    When Users transaction history will be pulled for specified group with following data '<requestParameters>'
    Then Users transaction history for specified group will be properly returned '<requestParameters>'

  @qa @stag
    Examples:
      | requestParameters                                                                                                |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,null,null                   |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,REDEEMED,null,null,null,null,null,null,null               |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,epointsBuy,null,null,null,null,null,null             |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,ePoints.com,null,null,null,null,null            |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,true,null,null,null,null                   |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,false,null,null,null,null                  |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,5,null,null,null                      |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,10000,null,null,null                  |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,6,null,null                      |
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,1454544001000,1454630399000 |


  @TransactionHistory @NegativeCase
  Scenario Outline: User transaction history - get user transaction history for specified group using invalid parameters
    Given Points Manager API is responding to requests
    When Users transaction history will be pulled for specified group with following invalid data '<requestParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Users transaction history for specified group will not be properly returned

  @Regression @qa @stag
    Examples:
      | requestParameters                                                                                      | expResponseCode | expErrorCode         | expErrorMsg                                              |
      #//incorrect groupId
      | wrongGroupId,wrongClientId,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,null,null  | 404             | INVALID_GROUP        | Invalid partnersgroup with name=[wrongGroupId]           |
      #//incorrect clientId
      | epoints,wrongClientId,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,null,null       | 403             | UNAUTHORIZED_PARTNER | Unauthorized partner with shortName=[wrongClientId]      |
      #//incorrect apiKey
      | epoints,ePoints.com,WrongAccessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,null,null    | 403             | UNAUTHORIZED_PARTNER | Unauthorized partner with shortName=[ePoints.com]        |
      #//incorrect user email
      | epoints,ePoints.com,accessKey,notExistingEmail@gmail.com,EMAIL,null,null,null,null,null,null,null,null | 404             | INVALID_USER         | User with userId=[notExistingEmail@gmail.com] is invalid |

  @skip @qa @stag
    Examples:
      | requestParameters                                                                                          | expResponseCode | expErrorCode  | expErrorMsg   |
      #//incorrect idType - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,wrongIdType,null,null,null,null,null,null,null,null       | 400             | not_specified | not_specified |
      #//incorrect status - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,wrongStatus,null,null,null,null,null,null,null      | 400             | not_specified | not_specified |
      #//incorrect tagName - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,wrongTagName,null,null,null,null,null,null     | 400             | not_specified | not_specified |
      #//incorrect BehalfOffId - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,wrongBehalfOffId,null,null,null,null,null | 400             | not_specified | not_specified |
      #//incorrect ascending - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,wrongAscending,null,null,null,null   | 400             | not_specified | not_specified |
      #//incorrect offset - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,wrongOffset,null,null,null      | 400             | not_specified | not_specified |
      #//incorrect limit - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,wrongLimit,null,null       | 400             | not_specified | not_specified |
      #//incorrect date - string - bug
      | epoints,ePoints.com,accessKey,emailfortest@wp.pl,EMAIL,null,null,null,null,null,null,wrongDateFormat,null  | 400             | not_specified | not_specified |

  #//TODO fake data need to be produced here Points table, rows with partnerId = 1 and proper tag
  @TransactionHistory @PositiveCase
  @Regression
  Scenario Outline: User transaction history - get user rewards history
    Given Points Manager API is responding to requests
    When User rewards history will be pulled with following data '<requestParameters>'
    Then Users rewards history will be properly returned '<requestParameters>'

  @qa @stag
    Examples:
      | requestParameters                                                             |
      | 07023849-b52c-4d49-b6fd-70a66b012a1c,accessKey,UUID,null,null,null,null,null  |
      | emailfortest@wp.pl,accessKey,EMAIL,true,null,null,null,null                   |
      | emailfortest@wp.pl,accessKey,EMAIL,false,null,null,null,null                  |
      | emailfortest@wp.pl,accessKey,EMAIL,null,5,null,null,null                      |
      | emailfortest@wp.pl,accessKey,EMAIL,null,10000,null,null,null                  |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,2,null,null                      |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,1454544001000,1454630399000 |

  @TransactionHistory @NegativeCase
  Scenario Outline: User transaction history - get user rewards history using invalid parameters
    Given Points Manager API is responding to requests
    When User rewards history will be pulled with following invalid data '<requestParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then User rewards history will not be properly returned

  @Regression @qa @stag
    Examples:
      | requestParameters                                                   | expResponseCode | expErrorCode    | expErrorMsg                                              |
      #//incorrect apiKey
      | emailfortest@wp.pl,WrongAccessKey,EMAIL,null,null,null,null,null    | 403             | INVALID_PARTNER | Partner with accessKey=[WrongAccessKey] is invalid       |
      #//incorrect user email
      | notExistingEmail@gmail.com,accessKey,EMAIL,null,null,null,null,null | 404             | INVALID_USER    | User with userId=[notExistingEmail@gmail.com] is invalid |

  @skip @qa @stag
    Examples:
      | requestParameters                                                      | expResponseCode | expErrorCode  | expErrorMsg   |
      #//incorrect idType
      | emailfortest@wp.pl,accessKey,wrongIdType,,null,null,null,null,null     | 400             | not_specified | not_specified |
      #//incorrect ascending
      | emailfortest@wp.pl,accessKey,EMAIL,,wrongAscending,null,null,null,null | 400             | not_specified | not_specified |
      #//incorrect offset
      | emailfortest@wp.pl,accessKey,EMAIL,null,wrongOffset,null,null,null     | 400             | not_specified | not_specified |
      #//incorrect limit
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,wrongLimit,null,null      | 400             | not_specified | not_specified |
      #//incorrect date - string
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,wrongDateFormat,null | 400             | not_specified | not_specified |


  @TransactionHistory @PositiveCase
  @Regression
  Scenario Outline: User transaction history - get user all transactions history
    Given Points Manager API is responding to requests
    When User transaction history will be pulled with following data '<requestParameters>'
    Then Users transactions history will be properly returned '<requestParameters>'

  @qa @stag
    Examples:
      | requestParameters                                                                                 |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,null,null,null,null,null                   |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,ePoints.com,null,null,null,null,null            |
      | emailfortest@wp.pl,accessKey,EMAIL,null,REDEEMED,null,null,null,null,null,null,null               |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,epointsBuy,null,null,null,null,null,null             |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,true,null,null,null,null                   |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,false,null,null,null,null                  |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,null,5,null,null,null                      |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,null,10000,null,null,null                  |
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,null,null,null,1454544001000,1454630399000 |
      | emailfortest@wp.pl,accessKey,EMAIL,createdAt,null,null,null,true,null,null,null,null              |
      | emailfortest@wp.pl,accessKey,EMAIL,delta,null,null,null,true,null,null,null,null                  |
      | emailfortest@wp.pl,accessKey,EMAIL,status,null,null,null,true,null,null,null,null                 |
      | emailfortest@wp.pl,accessKey,EMAIL,site,null,null,null,true,null,null,null,null                   |
      | emailfortest@wp.pl,accessKey,EMAIL,createdAt,null,null,null,false,null,null,null,null             |
      | emailfortest@wp.pl,accessKey,EMAIL,delta,null,null,null,false,null,null,null,null                 |
      | emailfortest@wp.pl,accessKey,EMAIL,status,null,null,null,false,null,null,null,null                |
      | emailfortest@wp.pl,accessKey,EMAIL,site,null,null,null,false,null,null,null,null                  |


  @TransactionHistory @NegativeCase
  Scenario Outline: User transaction history - get user all transactions history using invalid parameters
    Given Points Manager API is responding to requests
    When User transaction history will be pulled with following invalid data '<requestParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then User transactions history will not be properly returned

  @Regression @qa @stag
    Examples:
      | requestParameters                                                                       | expResponseCode | expErrorCode    | expErrorMsg                                              |
      #incorrect userEmail
      | notExistingEmail@gmail.com,accessKey,EMAIL,null,null,null,null,null,null,null,null,null | 404             | INVALID_USER    | User with userId=[notExistingEmail@gmail.com] is invalid |
      #incorrect accessKey
      | emailfortest@wp.pl,wrongAccessKey,EMAIL,null,null,null,null,null,null,null,null,null    | 403             | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid       |

  @skip @qa @stag
    Examples:
      | requestParameters                                                                          | expResponseCode | expErrorCode  | expErrorMsg   |
      #incorrect idType
      | emailfortest@wp.pl,accessKey,wrongIdType,null,null,null,null,null,null,null,null,null      | 400             | not_specified | not_specified |
      #incorrect sortField
      | emailfortest@wp.pl,accessKey,EMAIL,wrongSortField,null,null,null,null,null,null,null,null  | 400             | not_specified | not_specified |
      #incorrect status
      | emailfortest@wp.pl,accessKey,EMAIL,null,wrongStatus,null,null,null,null,null,null,null     | 400             | not_specified | not_specified |
      #incorrect tag
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,wrongTag,null,null,null,null,null,null        | 400             | not_specified | not_specified |
      #incorrect onBehalfOf
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,wrongOnBehalfOf,null,null,null,null,null | 400             | not_specified | not_specified |
      #incorrect ascending
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,wrongAscending,null,null,null,null  | 400             | not_specified | not_specified |
      #incorrect offset
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,null,wrongOffset,null,null,null     | 400             | not_specified | not_specified |
      #incorrect limit
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,null,null,wrongLimit,null,null      | 400             | not_specified | not_specified |
      #incorrect date
      | emailfortest@wp.pl,accessKey,EMAIL,null,null,null,null,null,null,null,wrongDateFormat,null | 400             | not_specified | not_specified |


  @TransactionHistory @PositiveCase @DO-849
  @Regression
  Scenario Outline: User transaction history - get user last transaction details
    Given All user transactions were pulled in createdAt desc order '<requestParameters>'
    When Get last user transaction request will be done '<requestParameters>'
    Then Last user transaction will be returned
    And Last user transaction data are as expected according to first element on transactions list '<requestParameters>'

  @qa @stag
    Examples:
      | requestParameters                                                                           |
      | emailfortest@wp.pl,accessKey,EMAIL,createdAt,null,epointsBuy,null,false,null,null,null,null |


  @TransactionHistory @NegativeCase @DO-849
  Scenario Outline: User transaction history - get user last transaction details using invalid parameters
    Given Points Manager API is responding to requests
    When Get last user transaction request will be done with invalid parameters '<requestParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Last user transaction will not be returned

  @Regression @qa @stag
    Examples:
      | requestParameters                                     | expResponseCode | expErrorCode    | expErrorMsg                                                      |
      #incorrect accessKey
      | wrongAccessKey,emailfortest@wp.pl,EMAIL,epointsBuy    | 403             | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid               |
      #incorrect userEmail
      | accessKey,notExistingEmail@gmail.com,EMAIL,epointsBuy | 404             | INVALID_USER    | User with userId=[notExistingEmail@gmail.com] is invalid         |
      #incorrect tagName
      | accessKey,emailfortest@wp.pl,EMAIL,wrongTagName       | 400             | INVALID_TAG     | Tag with key=[wrongTagName] for partner [ePoints.com] is invalid |

  @skip @qa @stag
    Examples:
      | requestParameters                                   | expResponseCode | expErrorCode  | expErrorMsg   |
      #incorrect idType
      | accessKey,emailfortest@wp.pl,wrongIdType,epointsBuy | 400             | not_specified | not_specified |