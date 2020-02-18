Feature: Retailer Details - Looking up for retailer's identified by unique business (external id)
  As a points-manager's component
  I want to look up for the retailers of the particular business id
  So that I can process their transactions

  @Regression @UserExternal @PositiveCase
  Scenario Outline: Active retailer should be possible to retrieve
    When User looks up for retailer with externalId <externalId> of type united, apiKey: accessKey, status: 200
    Then Retailer "<expectedRetailer>" is retrieved

  @qa @stag
    Examples:
      | externalId                                  | expectedRetailer                                                                                       |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED     | Firstname, Lastname, test_do_not_remove_active_verified@test.iat, c96b3642-e7f8-42da-9ef6-82c550b57bf6 |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_NOT_VERIFIED | null, null, test_do_not_remove_active_not_verified@test.iat, 980de4eb-cdd5-4b55-a8cd-ede0fd9d5225      |
      #todo I am not sure what that user was representing. So I am leaving it as it is. Need to ask MI about it later.
      #| GRIBEK-UNITED                               | United, User1, united.user1@united.epoints, 2b4bcb10-6382-4b96-b0f1-97c73bdee640                           |

  @Regression @UserExternal @NegativeCase
  Scenario Outline: Validation: invalid data
    When User looks up for retailer with externalId <externalId> of type united, apiKey: accessKey, status: <expectedStatus>
    Then Error message is retrieved "<expectedError>" "<expectedMessage>"

  @qa @stag
    Examples:
      | externalId                                 | expectedStatus | expectedError     | expectedMessage                                                         |
      | TEST_EXTERNAL_UNITED_ID_NOT_EXISTING       | 404            | INVALID_USER      | User with externalIdUnited=[$EXTERNAL_ID] is invalid                    |
      | TEST_EXTERNAL_UNITED_ID_INACTIVE           | 404            | INVALID_USER      | User with externalIdUnited=[$EXTERNAL_ID] is invalid                    |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_DUPLICATE_1 | 400            | INVALID_ARGUMENTS | There is more then one active user with externalIdUnited=[$EXTERNAL_ID] |


  @Regression @UserExternal @NegativeCase
  Scenario Outline: Validation: missing data
    When User looks up for retailer with externalId <externalId> of type <externalIdType>, apiKey: <apiKey>, status: <expectedStatus>
    Then Error message is retrieved "<expectedError>" "<expectedMessage>"

  @qa @stag
    Examples:
      | externalId                              | externalIdType | apiKey           | expectedStatus | expectedError                  | expectedMessage                                                     |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED | invalidtype    | accessKey        | 400            | INVALID_ARGUMENTS              | Unable to parse 'invalidtype' as value of parameter externalIdType. |
      #ExternalIdType empty
      #| TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED |                | accessKey        | 400            | INVALID_ARGUMENTS              | Unable to parse '' as value of parameter externalIdType.            |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED | null           | accessKey        | 400            | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'externalIdType' is not present.          |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED | united         | accessKeyInvalid | 403            | INVALID_PARTNER                | Partner with accessKey=[accessKeyInvalid] is invalid                |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED | united         |                  | 403            | INVALID_PARTNER                | Partner with accessKey=[] is invalid                                |
      | TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED | united         | null             | 400            | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                  |