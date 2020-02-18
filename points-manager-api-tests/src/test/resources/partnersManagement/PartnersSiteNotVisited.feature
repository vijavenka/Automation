Feature: Partner - not visited yet
  As a user
  I want to be able to see Partners which not awarded me yet
  To be able to get points award from them


  @Partners @NegativeCase @PartnersSitesNotVisited
  @Regression
  Scenario Outline: Get partners sites not visited yet List - system message validation
    Given Points Manager API is responding to requests
    When Partners site not visited call is made with user data '<UserId_UserIdType_ApiKey>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Partners Site not visited response for request with no partner data should be empty

  @qa @stag
    Examples:
      | UserId_UserIdType_ApiKey                                    | expResponseCode | expErrorCode                   | expErrorMsg                                        |
      | @!!@#$,null,null,null,null,null                             | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'idType' is not present. |
      | 123456,UUID,null,null,null,null                             | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | 123456,UUID,test,null,null,null                             | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             |
      | 123456,UUID,PartnerAccKey04,null,null,null                  | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          |
      | 123456,EMAIL,accessKey,null,null,null                       | 404             | INVALID_USER                   | User with userId=[%s] is invalid                   |
      # bug here
      | pm.user1.notverified@test.pl,EMAIL,accessKey,null,null,null | 400             | USER_IS_NOT_VERIFIED           | User with userId=[%s] is not verified              |
      # bug here
      | pm.user2.notactive@test.pl,EMAIL,accessKey,null,null,null   | 400             | USER_IS_NOT_ACTIVE             | User with userId=[%s] is not active                |

  @qa
    Examples:
      | UserId_UserIdType_ApiKey                                           | expResponseCode | expErrorCode         | expErrorMsg                           |
      | 0d03a6e9-da4b-414c-9c5b-96c749825cfa,UUID,accessKey,null,null,null | 400             | USER_IS_NOT_VERIFIED | User with userId=[%s] is not verified |
      | ea42ef78-dd66-45ae-a220-b1b829dfc0a9,UUID,accessKey,null,null,null | 400             | USER_IS_NOT_ACTIVE   | User with userId=[%s] is not active   |

  @stag
    Examples:
      | UserId_UserIdType_ApiKey                                           | expResponseCode | expErrorCode         | expErrorMsg                           |
      | d3d31e45-00c7-4bbb-a5e1-6612a1f76920,UUID,accessKey,null,null,null | 400             | USER_IS_NOT_VERIFIED | User with userId=[%s] is not verified |
      | df1c54bb-47a5-4f38-8e4f-61e2d5a71801,UUID,accessKey,null,null,null | 400             | USER_IS_NOT_ACTIVE   | User with userId=[%s] is not active   |


  @Partners @PositiveCase @PartnersSitesNotVisited
  @Regression
  Scenario Outline: Get partners sites not visited yet List and check response contract
    Given Points Manager API is responding to requests
    When Partners site not visited call is made '<UserId_UserIdType_ApiKey_limit_offset_random>'
    Then Partners Site not visited response match contract

  @qa @stag
    Examples:
      | UserId_UserIdType_ApiKey_limit_offset_random              |
       # bug here
      | pm.api.automation@gmail.com,EMAIL,accessKey,200,null,null |

  @qa
    Examples:
      | UserId_UserIdType_ApiKey_limit_offset_random                      |
      | fcd73643-8db4-4d69-8417-e0dd763ab978,UUID,accessKey,200,null,null |

  @stag
    Examples:
      | UserId_UserIdType_ApiKey_limit_offset_random                      |
      | 4380107d-a28e-4643-ae31-a88dbc414c62,UUID,accessKey,200,null,null |