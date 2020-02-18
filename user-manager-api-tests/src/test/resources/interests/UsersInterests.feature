Feature: User Interests - merchants management
  As an API client
  I want to be able to list, create and delete user merchants interests
  So that I will be able to track and manage personal user interests in retailers

  @UserInterests @PositiveCase
  @Regression
  Scenario Outline: Users interests - saving merchant in user interests
    Given Merchant '<merchantId>' is added to user '<userId>', '<userIdType>', '<apiKey>' interests
    When List of user '<userId>', '<userIdType>', '<apiKey>' interests will be pulled
    Then Liked before merchant '<merchantId>' can be found on user '<userId>', '<userIdType>', '<apiKey>' interests list

  @prod
    Examples:
      | userId                               | apiKey                    | userIdType | merchantId                           |
      | user.manager.api.1@iatltd.com        | xHNZaBGQtDmxTkrnI7NOfoXkz | EMAIL      | fbcb90a6-8b1b-4b82-a367-c74ace4c0d8b |
      | 03336909-ff61-45db-976e-192346feddcb | xHNZaBGQtDmxTkrnI7NOfoXkz | UUID       | 377b166c-1a17-445b-b59c-0b90c128397c |

  @qa @stag
    Examples:
      | userId                        | apiKey    | userIdType | merchantId                           |
      | user.manager.api.1@iatltd.com | accessKey | EMAIL      | fbcb90a6-8b1b-4b82-a367-c74ace4c0d8b |
      #| 611fb78b-a9b6-4670-b4d4-0bb728f74aa21 | accessKey | FACEBOOK   | ??? |

  @stag
    Examples:
      | userId                               | apiKey    | userIdType | merchantId                           |
      | b2f60adb-e734-4e41-adb3-ef72cbc812f8 | accessKey | UUID       | 377b166c-1a17-445b-b59c-0b90c128397c |

  @qa
    Examples:
      | userId                               | apiKey    | userIdType | merchantId                           |
      | eedb4b02-e529-4aef-918e-2eff918e119b | accessKey | UUID       | 377b166c-1a17-445b-b59c-0b90c128397c |


  @UserInterests @PositiveCase
  Scenario Outline: Users interests - deleting merchants from user interests
    Given Merchant '<merchantId>' is added to user '<userId>', '<userIdType>', '<apiKey>' interests
    When Merchant '<merchantId>' will be deleted from user '<userId>', '<userIdType>', '<apiKey>' interests
    And List of user '<userId>', '<userIdType>', '<apiKey>' interests will be pulled
    Then Liked before merchant '<merchantId>' cannot be found on user interests list

  @Production
    Examples:
      | userId                               | apiKey                    | userIdType | merchantId                           |
      | user.manager.api.1@iatltd.com        | xHNZaBGQtDmxTkrnI7NOfoXkz | EMAIL      | fbcb90a6-8b1b-4b82-a367-c74ace4c0d8b |
      | 03336909-ff61-45db-976e-192346feddcb | xHNZaBGQtDmxTkrnI7NOfoXkz | UUID       | 377b166c-1a17-445b-b59c-0b90c128397c |

  @ApiBuild @ApiRegression
    Examples:
      | userId                               | apiKey    | userIdType | merchantId                           |
      | user.manager.api.1@iatltd.com        | accessKey | EMAIL      | 377b166c-1a17-445b-b59c-0b90c128397c |
      | b2f60adb-e734-4e41-adb3-ef72cbc812f8 | accessKey | UUID       | fbcb90a6-8b1b-4b82-a367-c74ace4c0d8b |
      #| 611fb78b-a9b6-4670-b4d4-0bb728f74aa21 | apiKey | FACEBOOK   | ??? |

  @ApiBuild @ApiRegression @stag
    Examples:
      | userId                               | apiKey    | userIdType | merchantId                           |
      | b2f60adb-e734-4e41-adb3-ef72cbc812f8 | accessKey | UUID       | fbcb90a6-8b1b-4b82-a367-c74ace4c0d8b |

  @ApiBuild @ApiRegression @qa
    Examples:
      | userId                               | apiKey    | userIdType | merchantId                           |
      | cfc5b177-d648-481e-bc3f-cec071d6bf49 | accessKey | UUID       | fbcb90a6-8b1b-4b82-a367-c74ace4c0d8b |

  @UserInterests @NegativeCase
  Scenario Outline: Users interests - not existing user provided
    Given User Manager API is responding to requests
    When '<user>' is trying to call for interests with '<idType>', '<apiKey>', '<expResponseCode>'
    Then Response with '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>' should be returned

  @Production
    Examples:
      | user                      | apiKey                    | idType | expResponseCode | expErrorCode | expErrorMsg                                            |
      | notExistingUser@gmail.com | xHNZaBGQtDmxTkrnI7NOfoXkz | EMAIL  | 404             | INVALID_USER | User with EMAIL=[notExistingUser@gmail.com] is invalid |

  @ApiBuild @ApiRegression @qa @stag
    Examples:
      | user                      | apiKey    | idType | expResponseCode | expErrorCode | expErrorMsg                                            |
      | notExistingUser@gmail.com | accessKey | EMAIL  | 404             | INVALID_USER | User with EMAIL=[notExistingUser@gmail.com] is invalid |

  @UserInterests @NegativeCase
  Scenario Outline: Users interests - incorrect parameters for add
    Given User Manager API is responding to requests
    When User adds interests with invalid data '<merchantId>', '<userId>', '<userIdType>', '<apiKey>', '<expResponseCode>'
    Then Response with '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>' should be returned

  @qa @stag
    Examples:
      | userId        | apiKey    | userIdType | merchantId                          | expResponseCode | expErrorCode      | expErrorMsg                                            |
      | wrong         | accessKey | EMAIL      | 77b166c-1a17-445b-b59c-0b90c128397a | 404             | INVALID_USER      | User with EMAIL=[wrong] is invalid                     |
      | PL@iatltd.com | accessKey | wrong2     | 77b166c-1a17-445b-b59c-0b90c128397b | 400             | INVALID_ARGUMENTS | Unable to parse 'wrong2' as value of parameter idType. |


  @UserInterests @NegativeCase
  Scenario Outline: Users interests - incorrect parameters for delete
    Given User Manager API is responding to requests
    When User deletes interests with invalid data '<merchantId>', '<userId>', '<userIdType>', '<apiKey>', '<expResponseCode>'
    Then Response with '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>' should be returned

  @qa @stag
    Examples:
      | userId        | apiKey    | userIdType | merchantId                          | expResponseCode | expErrorCode      | expErrorMsg                                            |
      | wrong         | accessKey | EMAIL      | 77b166c-1a17-445b-b59c-0b90c128397a | 404             | INVALID_USER      | User with EMAIL=[wrong] is invalid                     |
      | PL@iatltd.com | accessKey | wrong2     | 77b166c-1a17-445b-b59c-0b90c128397b | 400             | INVALID_ARGUMENTS | Unable to parse 'wrong2' as value of parameter idType. |