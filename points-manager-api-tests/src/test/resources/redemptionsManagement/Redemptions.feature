Feature: Redemption order - management
  As a administrator
  I want to be able to manage users redemption orders
  To be able to proceed orders, refund them


  @PositiveCase @Redemptions
  @Regression
  Scenario Outline: Get redemptions list and check response contract
    Given Points Manager API is responding to requests
    When Get redemptions list call is made with data '<apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo>'
    Then Get redemptions response have proper results count for provided data '<apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo>'

  @qa @stag
    Examples:
      | apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo |
      | accessKey,null,null,null,5,null,null,null,null,null                                                |
      | accessKey,true,null,null,15,null,null,null,null,null                                               |
      | accessKey,false,null,null,55,null,null,null,null,null                                              |
      | accessKey,true,redemptionId,null,15,null,null,null,null,null                                       |
      | accessKey,false,redemptionId,null,55,null,null,null,null,null                                      |
      | accessKey,true,orderId,null,15,null,null,null,null,null                                            |
      | accessKey,false,orderId,null,55,null,null,null,null,null                                           |
      | accessKey,null,null,null,null,null,null,status,FULFILL,null                                        |
      | accessKey,null,null,null,null,null,null,status,REFUNDED,null                                       |
      | accessKey,null,null,null,null,null,null,status,REFUNDED,true                                       |
      | accessKey,null,null,null,null,null,null,status,REDEEMED,null                                       |
      | accessKey,null,null,null,5,1441411201000,1454540399000,null,null,null                              |
      | accessKey,null,null,null,5,null,null,merchantName,Hughes,null                                      |

  @prod
    Examples:
      | apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,null,null,null,5,null,null,null,null,null                                |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,null,null,15,null,null,null,null,null                               |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,false,null,null,55,null,null,null,null,null                              |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,redemptionId,null,15,null,null,null,null,null                       |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,false,redemptionId,null,55,null,null,null,null,null                      |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,orderId,null,15,null,null,null,null,null                            |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,false,orderId,null,55,null,null,null,null,null                           |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,null,null,null,null,null,null,status,FULFILL,null                        |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,null,null,null,null,null,null,status,REFUNDED,null                       |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,null,null,null,null,null,null,status,REFUNDED,true                       |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,null,null,null,null,null,null,status,REDEEMED,null                       |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,null,null,null,5,1441411201000,1454540399000,null,null,null              |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,null,null,null,5,null,null,merchantName,Hughes,null                      |

  @searchingByEmailNoLongerSupported
    Examples:
      | apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo |
      | accessKey,null,null,null,5,null,null,userEmail,pm.api.automation@gmail.com,null                    |


  @NegativeCase @Redemptions
  @Regression
  Scenario Outline:  Get redemptions list - system message validation
    Given Points Manager API is responding to requests
    When Get redemptions list call is made with following data '<apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Get redemptions list response for request without proper data should be empty

  @qa @stag
    Examples:
      | apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo | expResponseCode | expErrorCode                   | expErrorMsg                                                                                                    |
      | null,null,null,null,null,null,null,null,null,null                                                  | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                                                             |
      | accessKeyTRUM,null,null,null,null,null,null,null,null,null                                         | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid                                                                         |
      | PartnerAccKey04,null,null,null,null,null,null,null,null,null                                       | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active                                                                      |
      | accessKey,true,createdDate,null,15,null,null,null,null,null                                        | 400             | INVALID_ARGUMENTS              | TODO                                                                                                           |
      | accessKey,true,createdDate,null,15,1454540399000,1441411201000,null,null,null                      | 400             | INVALID_ARGUMENTS              | Invalid date parameters date From=Wed Feb 03 22:59:59 UTC 2016 is greater then To=Sat Sep 05 00:00:01 UTC 2015 |
      | accessKey,true,createdDate,null,15,03-02-2015,1441411201000,null,null,null                         | 404             | INVALID_ARGUMENTS              | Invalid date format for ['startDate']                                                                          |
      | accessKey,true,createdDate,null,15,1454540399000,03-02-2016,null,null,null                         | 404             | INVALID_ARGUMENTS              | Invalid date format for ['endDate']                                                                            |
      | accessKey,true,createdDate,null,15,03-02-2015,03-02-2016,null,null,null                            | 404             | INVALID_ARGUMENTS              | Invalid date format for ['startDate','endDate']                                                                |

  @prod
    Examples:
      | apiKey_ascending_orderField_offset_limit_startDate_endDate_searchField_keyword_withAddictivityInfo | expResponseCode | expErrorCode                   | expErrorMsg                                                                                                    |
      | null,null,null,null,null,null,null,null,null,null                                                  | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                                                             |
      | accessKeyTRUM,null,null,null,null,null,null,null,null,null                                         | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid                                                                         |
      | PartnerAccKey04,null,null,null,null,null,null,null,null,null                                       | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active                                                                      |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,createdDate,null,15,null,null,null,null,null                        | 400             | INVALID_ARGUMENTS              | TODO                                                                                                           |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,createdDate,null,15,1454540399000,1441411201000,null,null,null      | 400             | INVALID_ARGUMENTS              | Invalid date parameters date From=Wed Feb 03 22:59:59 UTC 2016 is greater then To=Sat Sep 05 00:00:01 UTC 2015 |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,createdDate,null,15,03-02-2015,1441411201000,null,null,null         | 404             | INVALID_ARGUMENTS              | Invalid date format for ['startDate']                                                                          |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,createdDate,null,15,1454540399000,03-02-2016,null,null,null         | 404             | INVALID_ARGUMENTS              | Invalid date format for ['endDate']                                                                            |
      | xHNZaBGQtDmxTkrnI7NOfoXkz,true,createdDate,null,15,03-02-2015,03-02-2016,null,null,null            | 404             | INVALID_ARGUMENTS              | Invalid date format for ['startDate','endDate']                                                                |


  @PositiveCase @Redemptions
  @Regression
  Scenario Outline: Get redemptions by id and check response contract
    Given Get redemptions list default call is made
    And Random redemption id is selected from Get redemptions list default call response
    When Get redemptions by id call is made with data '<apiKey>'
    Then Get redemptions by id response match contract

  @qa @stag
    Examples:
      | apiKey    |
      | accessKey |
      | accessKey |
      | accessKey |


  @NegativeCase @Redemptions
  @Regression
  Scenario Outline: Get redemptions by id - system message validation
    Given Points Manager API is responding to requests
    When Get redemptions by id call is made with following data '<apiKey_redemptionId>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Get redemptions by id response for request without proper data should be empty

  @qa @stag
    Examples:
      | apiKey_redemptionId | expResponseCode | expErrorCode                   | expErrorMsg                                        |
      | null,1679           | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | bdldewdew,1679      | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             |
      | PartnerAccKey04,921 | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          |
      | accessKey,00007     | 404             | REDEMPTION_NOT_FOUND           | Redemption with id=[7] is invalid                  |
      | bdl,1679            | 404             | REDEMPTION_NOT_FOUND           | Redemption with id=[1679] is invalid               |


  @PositiveCase @Redemptions
  @Regression
  Scenario Outline: Create new redemptions fulfill update and check response
    Given Get redemptions list call is made with default epoints partner data
    When Create new redemptions fulfill call is made with data '<apiKey>', '<fulfill_fields>'
    Then Create redemptions fulfill response data is true

  @qa @stag
    Examples:
      | apiKey    | fulfill_fields           |
      | accessKey | Merchant,1500,15,5,w123c |


  @NegativeCase @Redemptions
  @Regression
  Scenario Outline: Create new redemptions fulfill update - system message validation
    Given Points Manager API is responding to requests
    When Create new redemptions fulfill call is made with following data '<apiKey_redemptionId>', '<jsonBody>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Create new redemptions fulfill response for request without proper data should be empty

  @qa @stag
    Examples:
      | apiKey_redemptionId | expResponseCode | expErrorCode                   | expErrorMsg                                        | jsonBody |
      | null,1679           | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. | {}       |
      | bdldewdew,1679      | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             | {}       |
      | PartnerAccKey04,921 | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          | {}       |
      | accessKey,w7        | 400             | REDEMPTION_NOT_FOUND           | Redemption with id=[%s] is invalid                 | {}       |
      | accessKey,70000     | 400             | REDEMPTION_NOT_FOUND           | Redemption with id=[%s] is invalid                 | {}       |
      | bdl,1679            | 404             | REDEMPTION_NOT_FOUND           | Redemption with id=[%s] is invalid                 | {}       |
      | accessKey,1679      | 404             | INVALID_JSON                   | Improper json in request                           | {}       |


  @PositiveCase @Redemptions
  @Regression
  Scenario Outline: Create new redemptions refund and check response
    Given Get redemptions list call is made with default epoints partner data
    When Create new redemptions refund call is made with data '<apiKey>', '<activityInfo>'
    Then Create redemptions refund response match contract and data '<activityInfo>'

  @qa @stag
    Examples:
      | apiKey    | activityInfo                |
      | accessKey | tralalallalalallalala ha ha |


  @NegativeCase @Redemptions
  @Regression
  Scenario Outline: Create new redemptions refund - system message validation
    Given Points Manager API is responding to requests
    When Create new redemptions refund call is made with following data '<apiKey>', '<jsonBody>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Create new redemptions refund response should include proper message for following data '<apiKey>', '<jsonBody>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'

  @qa @stag
    Examples:
      | apiKey          | expResponseCode | expErrorCode                   | expErrorMsg                                        | jsonBody        |
      | null            | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. | {"id": "1679"}  |
      | bdldewdew       | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             | {"id": "1679"}  |
      | PartnerAccKey04 | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          | {"id": "921"}   |
      | accessKey       | 405             | REDEMPTION_NOT_FOUND           | Redemption with id=[%s] is invalid                 | {"id": "00007"} |
      | bdl             | 405             | REDEMPTION_NOT_FOUND           | Redemption with id=[%s] is invalid                 | {"id": "1679"}  |
      | accessKey       | 405             | INVALID_JSON                   | Improper json in request                           | {"id": "1679"}  |


