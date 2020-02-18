Feature: Redemption order - creation
  As a user
  I want to be able to manage my redemption orders
  To be able to create new redemption order, view redemption order history and details of specific one

  @PositiveCase @RedemptionOrder
  @Regression
  Scenario Outline: Create new redemption order and get order details
    Given Redemption item for redemption order is selected '<order>' '<userId_idType_apiKey>'
    And Create new redemption order call is made with data '<userId_idType_apiKey>'
    And Create new redemption order response returns order Id
    When Get redemption order details by id call is made with provided apiKey '<userId_idType_apiKey>' and orderId
#    Then Get redemption order details by id response data is same as provided
    And User balance after Create new redemption order call is properly reduced
    And Redemption transaction can be properly refunded

  @qa @stag
    Examples:
      | userId_idType_apiKey                        | order                                                                                                                                                                                                                                                                  |
      | pm.api.automation@gmail.com,UUID,envDepends | {"gift":false,"deductionURI":"epoints.com","name":"Automated Tests","address1":"AutomatedTests","address2":"AutomatedTests","city":"AutomatedTests","county":"AutomatedTests","country":"UK","postcode":"DN55 1PT","phone":"AutomatedTests","zone":"UK","region":"UK"} |

  @NegativeCase @RedemptionOrder
  @Regression
  Scenario Outline: Create new redemption order - system message validation
    Given Points Manager API is responding to requests
    When Create new redemption order call is made with following data '<userId_idType_apiKey>', '<orderJsonBody>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then Create new redemption order response for request without proper data should be empty

  @qa @stag @prod
    Examples:
      | userId_idType_apiKey                                   | code | expErrorCode                   | expErrorMsg                                        | orderJsonBody |
      | pm.api.automation@gmail.com,null,null                  | 400  | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'idType' is not present. | {}            |
      | pm.api.automation@gmail.com,UUID,null                  | 400  | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. | {}            |
      | pm.api.automation@gmail.com,UUID,accessKeyInvalid      | 403  | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             | {}            |
      #//TODO bug NBO-7783 idType has to be changed to UUID after fix and changes in related step
      | pm.api.automation@gmail.com,EMAIL ,envDepends          | 404  | INVALID_USER                   | User with userId=[%s] is invalid                   | {}            |
      #//TODO bug NBO-7783 email is changed to uuid in related step
      | pm.api.automationnotverified@gmail.com,UUID,envDepends | 400  | USER_IS_NOT_VERIFIED           | User with userId=[%s] is not verified              | {}            |
      #//TODO bug NBO-7783 email is changed to uuid in related step
      | pm.api.automation@gmail.com,UUID,envDepends            | 400  | INVALID_ARGUMENTS              | Invalid deductionURI = [null]                      | {}            |

  @qa @stag @prod
    Examples:
      | userId_idType_apiKey                                       | code | expErrorCode              | expErrorMsg                               | orderJsonBody                                                                                                                                                                                                                                                          |
      | pm.api.automation@gmail.com,EMAIL,inactive                 | 403  | PARTNER_IS_NOT_ACTIVE     | Partner with accessKey=[%s] is not active | {}                                                                                                                                                                                                                                                                     |
      | pm.api.automationnotactive@gmail.com,UUID,envDepends       | 400  | USER_IS_NOT_ACTIVE        | User with userId=[%s] is not active       | {}                                                                                                                                                                                                                                                                     |
      | pm.api.automationnotenoughpoints@gmail.com,UUID,envDepends | 400  | INVALID_REDEMPTION_POINTS | User has no enough points to deduct       | {"gift":false,"deductionURI":"epoints.com","name":"Automated Tests","address1":"AutomatedTests","address2":"AutomatedTests","city":"AutomatedTests","county":"AutomatedTests","country":"UK","postcode":"DN55 1PT","phone":"AutomatedTests","zone":"UK","region":"UK"} |

  #This test depends on "Create new redemption order and get order details" and cannot be run alone with orderId = dynamically.
  #If we need to make it independent whole order redemption has to be done from the begin or transaction id taken from db
  @PositiveCase @RedemptionOrder
  @Regression
  Scenario Outline: Get redemption order details and check response contract
    Given Points Manager API is responding to requests
    When Get redemption order details by id call is made with data '<apiKey_orderId>'
    Then Get redemption order details by id response match contract

  @qa @stag
    Examples:
      | apiKey_orderId         |
      | envDepends,dynamically |

  #This test depends on "Create new redemption order and get order details" and cannot be run alone with orderId = dynamically.
  #If we need to make it independent whole order redemption has to be done from the begin or transaction id taken from db
  @NegativeCase @RedemptionOrder
  @Regression
  Scenario Outline: Get redemption order details - system message validation
    Given Points Manager API is responding to requests
    When Get redemption order details by id call is made with following data '<apiKey_orderId>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then Get redemption order details by id response for request without proper data should be empty

  @qa @stag
    Examples:
      | apiKey_orderId   | code | expErrorCode                   | expErrorMsg                                        |
      | null,dynamically | 400  | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | bdl,dynamically  | 403  | UNAUTHORIZED_PARTNER           | Unauthorized partner with apiKey=[%s]              |


  @qa @stag
    Examples:
      | apiKey_orderId       | code | expErrorCode          | expErrorMsg                               |
      | inactive,dynamically | 403  | PARTNER_IS_NOT_ACTIVE | Partner with accessKey=[%s] is not active |


  @PositiveCase @RedemptionOrder
  @Regression
  Scenario Outline: Get recently redeemed list and check response contract
    Given Points Manager API is responding to requests
    When Get recently redeemed call is made with data '<apiKey_region_zone_offset_limit>'
    Then Get recently redeemed response have proper results count for provided data '<apiKey_region_zone_offset_limit>'

  @qa @stag
    Examples:
      | apiKey_region_zone_offset_limit |
      | envDepends,UK,UK,0,2            |
#      | envDepends,UK,IE,0,15            |
#      | envDepends,NL,NL,0,6             |
#      | envDepends,NO,NO,0,11            |
#      | envDepends,SE,SE,0,5             |
#      | envDepends,DK,DK,0,2             |
#      | envDepends,FI,FI,1,1             |

  @NegativeCase @RedemptionOrder
  @Regression
  Scenario Outline: Get recently redeemed list - system message validation
    Given Points Manager API is responding to requests
    When Get recently redeemed call is made with following data '<apiKey_region_zone_offset_limit>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then Get recently redeemed response for request without proper data should be empty

  @qa @stag @prod
    Examples:
      | apiKey_region_zone_offset_limit | code | expErrorCode                   | expErrorMsg                                        |
      | null,UK,UK,0,5                  | 400  | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | invalid,UK,UK,0,5               | 403  | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             |

  @qa @stag @prod
    Examples:
      | apiKey_region_zone_offset_limit | code | expErrorCode          | expErrorMsg                               |
      | inactive,UK,UK,0,5              | 403  | PARTNER_IS_NOT_ACTIVE | Partner with accessKey=[%s] is not active |

  @PositiveCase @RedemptionOrder
  @Regression
  Scenario Outline: Get redemption order history and check response contract
    Given Points Manager API is responding to requests
    When Get redemption order history call is made with data '<userId_idType_apiKey_ascending_offset_limit_startDate_endDate>'
    Then Get redemption order history response have proper results count for provided data '<userId_idType_apiKey_ascending_offset_limit_startDate_endDate>'

  @qa @stag @prod
    Examples:
      | userId_idType_apiKey_ascending_offset_limit_startDate_endDate                     |
      | pm.api.automation@gmail.com,EMAIL,envDepends,null,0,1,null,null                   |
      | pm.api.automation@gmail.com,EMAIL,envDepends,null,0,5,null,null                   |
      | pm.api.automation@gmail.com,EMAIL,envDepends,null,0,5,1481597842000,1481633048000 |
      | pm.api.automation@gmail.com,EMAIL,envDepends,true,0,10,null,null                  |
      | pm.api.automation@gmail.com,EMAIL,envDepends,false,0,10,null,null                 |
      | pm.api.automation@gmail.com,EMAIL,envDepends,false,10,1,null,null                 |

  @NegativeCase @RedemptionOrder
  @Regression
  Scenario Outline:  Get redemption order history - system message validation
    Given Points Manager API is responding to requests
    When Get redemption order history call is made with following data '<userId_idType_apiKey_ascending_offset_limit_startDate_endDate>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then Get redemption order history response for request without proper data should be empty

  @qa @stag @prod
    Examples:
      | userId_idType_apiKey_ascending_offset_limit_startDate_endDate                         | code | expErrorCode                   | expErrorMsg                                        |
      | pm.api.automation@gmail.com,EMAIL,null,true,0,1,1441411201000,1454540399000           | 400  | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | pm.api.automation@gmail.com,null,envDepends,null,true,0,1,1441411201000,1454540399000 | 400  | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'idType' is not present. |
      | pm.api.automation@gmail.com,EMAIL,accessKeyTRUM,true,0,1,1441411201000,1454540399000  | 403  | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             |
#      | pm.api.automation@gmail.com,UUID,envDepends,true,0,1,1441411201000,1454540399000             | 404  | INVALID_USER                   | User with userId=[%s] is invalid                                                                               |
#      | pm.api.automationnotverified@gmail.com,EMAIL,envDepends,true,0,1,1441411201000,1454540399000 | 400  | USER_IS_NOT_VERIFIED           | User with userId=[%s] is not verified                                                                          |
#      | pm.api.automation@gmail.com,EMAIL,envDepends,null,0,5,1454540399000,1441411201000            | 400  | INVALID_ARGUMENTS              | Invalid date parameters date From=Wed Feb 03 22:59:59 UTC 2016 is greater then To=Sat Sep 05 00:00:01 UTC 2015 |
#      | pm.api.automation@gmail.com,EMAIL,envDepends,null,0,5,03-02-2015,1441411201000               | 404  | INVALID_ARGUMENTS              | Invalid date format for ['startDate']                                                                          |
#      | pm.api.automation@gmail.com,EMAIL,envDepends,null,0,5,1454540399000,03-02-2016               | 404  | INVALID_ARGUMENTS              | Invalid date format for ['endDate']                                                                            |
#      | pm.api.automation@gmail.com,EMAIL,envDepends,null,0,5,03-02-2015,03-02-2016                  | 404  | INVALID_ARGUMENTS              | Invalid date format for ['startDate','endDate']                                                                |

  @qa @stag @prod
    Examples:
      | userId_idType_apiKey_ascending_offset_limit_startDate_endDate                   | code | expErrorCode          | expErrorMsg                               |
      | pm.api.automation@gmail.com,EMAIL,inactive,true,0,1,1441411201000,1454540399000 | 403  | PARTNER_IS_NOT_ACTIVE | Partner with accessKey=[%s] is not active |
#      | pm.api.automationnotactive@gmail.com,EMAIL,envDepends,true,0,1,1441411201000,1454540399000 | 400  | USER_IS_NOT_ACTIVE    | User with userId=[%s] is not active       |
