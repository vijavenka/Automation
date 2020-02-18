Feature: Payments
  As an API client
  I want to be able to create and manage payments
  So that I will be able to create epoints payment, update its details and see details of selected payment

  @PositiveCase @Payments
  @Regression
  Scenario Outline: Payments - check if new payment can be properly created
    Given New payment is created in the system '<apiKey>'
    When Created payment details will be pulled '<apiKey>'
    Then Pulled payment details data are the same as those used in payment creation

  @qa @stag
    Examples:
      | apiKey    |
      #//TODO bug here HS-138, also json schema has to be updated then
      | accessKey |

  @PositiveCase @Payments
  @Regression
  Scenario Outline: Payments - check if new payment can be properly updated
    Given New payment is created in the system '<apiKey>'
    When Created payment details will be updated '<apiKey>'
    Then Pulled payment details data are the same as those used in payment creation

  @qa @stag
    Examples:
      | apiKey    |
      #//TODO bug here HS-141,142,143,144
      | accessKey |

  @NegativeCase @Payments
  @Regression
  Scenario Outline: Payments - check behaviour of system when invalid parameters used during payment creation
    Given Points Manager API is responding to requests
    When When new payment will be created with invalid parameters values '<apiKey>', '<jsonParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then New payment should not be created

  @qa @stag
    Examples:
      | apiKey         | jsonParameters                                                                                                                                             | expResponseCode | expErrorCode      | expErrorMsg                                         |
      #//incorrect accessKey
      | wrongAccessKey | guid,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,UUID,100,20000,10,10,120,firstName,lastName,email@gmail.pl,currency                             | 403             | INVALID_PARTNER   | Partner with accessKey=[wrongAccessKey] is invalid  |
      #//incorrect taken uuid - copied to test env from prod to ensure existing it on qa //TODO bug here HS-145
      | accessKey      | aa0ecd09794740b99647bd67f8e0d683,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,UUID,100,20000,10,10,120,firstName,lastName,email@gmail.pl,currency | 400             | not_specified     | not_specified                                       |
      #//incorrect not existing user id
      | accessKey      | guid,paymentId,status,notExistingUserId,UUID,100,20000,10,10,120,firstName,lastName,email@gmail.pl,currency                                                | 404             | INVALID_USER      | User with userId=[notExistingUserId] is invalid     |
      #//incorrect idType
      | accessKey      | guid,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,wrongIdType,100,20000,10,10,120,firstName,lastName,email@gmail.pl,currency                      | 400             | INVALID_ARGUMENTS | Invalid value for parameter idType = [wrongIdType]. |
      #//incorrect moneyValue //TODO bug here HS-126
      | accessKey      | guid,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,EMAIL,wrongMoneyValue,20000,10,10,120,firstName,lastName,email@gmail.pl,currency                | 400             | INVALID_ARGUMENTS | not_specified                                       |
      #//incorrect epointsValue //TODO bug here HS-126
      | accessKey      | guid,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,EMAIL,100,wrongEpointsValue,10,10,120,firstName,lastName,email@gmail.pl,currency                | 400             | INVALID_ARGUMENTS | not_specified                                       |
      #//incorrect taxValue //TODO bug here HS-126
      | accessKey      | guid,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,EMAIL,100,20000,wrongTaxValue,10,120,firstName,lastName,email@gmail.pl,currency                 | 400             | INVALID_ARGUMENTS | not_specified                                       |
      #//incorrect feeValue //TODO bug here HS-126
      | accessKey      | guid,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,EMAIL,100,20000,10,wrongFeeValue,120,firstName,lastName,email@gmail.pl,currency                 | 400             | INVALID_ARGUMENTS | not_specified                                       |
      #//incorrect totalValue //TODO bug here HS-126
      | accessKey      | guid,paymentId,status,611fb78b-a9b6-4670-b4d4-0bb728f74be3,EMAIL,100,20000,10,10,wrongTotalValue,firstName,lastName,email@gmail.pl,currency                | 400             | INVALID_ARGUMENTS | not_specified                                       |
      #//empty body //TODO bug here HS-126
      | accessKey      | null,null,null,null,null,null,null,null,null,null,null,null,null,null                                                                                      | 400             | INVALID_ARGUMENTS | Parameter idType=[null] is invalid                  |
      #//only user id and idType //TODO bug here HS-126
      | accessKey      | null,null,null,611fb78b-a9b6-4670-b4d4-0bb728f74be3,UUID,null,null,null,null,null,null,null,null,null                                                      | 400             | not_specified     | not_specified                                       |

  @NegativeCase @Payments
  @Regression
  Scenario Outline: Payments - check behaviour of system when invalid parameters used during payment details pull
    Given Points Manager API is responding to requests
    When When selected payment details will be pulled with invalid parameters values '<apiKey>', '<guid>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Selected payment details should not be returned

  @qa @stag
    Examples:
      | apiKey         | guid                             | expResponseCode | expErrorCode    | expErrorMsg                                        |
      #//incorrect accessKey - guid copied to test env from prod to ensure existing it on qa
      | wrongAccessKey | aa0ecd09794740b99647bd67f8e0d683 | 403             | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid |
      #//incorrect guid //TODO bug here HS-126
      | accessKey      | wrongGuid                        | 500             | not_specified   | not_specified                                      |

  @NegativeCase @Payments
  @Regression
  Scenario Outline: Payments - check behaviour of system when invalid parameters used during payment details update
    Given Points Manager API is responding to requests
    When When payment will be updated with invalid parameters values '<apiKey>', '<guid>' '<jsonParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then New payment should not be updated

  @qa @stag
    Examples:
      | apiKey         | guid                             | jsonParameters                                                                                                    | expResponseCode | expErrorCode    | expErrorMsg                                        |
      #//incorrect accessKey
      | wrongAccessKey | aa0ecd09794740b99647bd67f8e0d683 | null,null,newStatus,null,null,null,null,null,null,null,payerFirstName,payerLastName,payerEmail@gmail.com,currency | 403             | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid |
      #//incorrect guid /TODO bug here HS-126
      | accessKey      | wrongGuid                        | null,null,newStatus,null,null,null,null,null,null,null,payerFirstName,payerLastName,payerEmail@gmail.com,currency | 500             | not_specified   | not_specified                                      |
      #//empty body /TODO bug here HS-126
      | accessKey      | aa0ecd09794740b99647bd67f8e0d683 | null,null,null,null,null,null,null,null,null,null,null,null,null,null                                             | 500             | not_specified   | not_specified                                      |