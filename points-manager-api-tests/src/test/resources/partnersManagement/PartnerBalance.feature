Feature: Partner - points balance
  As platform admin
  I want to be able to see Partner balance in agreed details level
  To be able to expose only those chosen ones

  @Partners @NegativeCases @PartnerBalance
  @Regression
  Scenario Outline: Get partner balance - check response for selected fields
    Given Partner get balance call is made with data '<clientId_apiKey_fields_startDate_endDate>'
    When Partner get balance response for request match contract
    Then Partner get balance response include fields selected '<clientId_apiKey_fields_startDate_endDate>'

  @qa @stag
    Examples:
      | clientId_apiKey_fields_startDate_endDate                                                                         |
      | ePoints.com;accessKey;clientId,confirmedPoints,pendingPoints,declinedPoints,redeemedPoints,spentPoints;null;null |
      | ePoints.com;accessKey;clientId;null;null                                                                         |
      | ePoints.com;accessKey;confirmedPoints;null;null                                                                  |
      | ePoints.com;accessKey;pendingPoints;null;null                                                                    |
      | ePoints.com;accessKey;declinedPoints;null;null                                                                   |
      | ePoints.com;accessKey;redeemedPoints;null;null                                                                   |
      | ePoints.com;accessKey;spentPoints;null;null                                                                      |
      | ePoints.com;accessKey;clientId,spentPoints;null;null                                                             |
      | ePoints.com;accessKey;pendingPoints,spentPoints;null;null                                                        |
      | ePoints.com;accessKey;clientId,confirmedPoints,redeemedPoints,spentPoints;null;null                              |

  @prod
    Examples:
      | clientId_apiKey_fields_startDate_endDate                                                                                         |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;clientId,confirmedPoints,pendingPoints,declinedPoints,redeemedPoints,spentPoints;null;null |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;clientId;null;null                                                                         |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;confirmedPoints;null;null                                                                  |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;pendingPoints;null;null                                                                    |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;declinedPoints;null;null                                                                   |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;redeemedPoints;null;null                                                                   |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;spentPoints;null;null                                                                      |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;clientId,spentPoints;null;null                                                             |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;pendingPoints,spentPoints;null;null                                                        |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;clientId,confirmedPoints,redeemedPoints,spentPoints;null;null                              |

  @Partners @NegativeCases @PartnerBalance
  @Regression
  Scenario Outline: Get partner balance - system message validation
    Given Points Manager API is responding to requests
    When Partner get balance call is made with some miss data '<clientId_apiKey_fields_startDate_endDate>','<expResponseCode>','<expErrorCode>','<expErrorMsg>'
    Then Partner get balance response for request with miss data should be empty

  @qa @stag
    Examples:
      | clientId_apiKey_fields_startDate_endDate             | expResponseCode | expErrorCode                   | expErrorMsg                                                                                                |
      | ePoints.com2;null;null;null;null                     | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                                                         |
      | ePoints.com2;accessKey;null;null;null                | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'fields' is not present.                                                         |
      | ePoints.com2;accessKey;EMAIL;null;null               | 400             | INVALID_ARGUMENTS              | Field [EMAIL] is not defined                                                                               |
      | ePoints.com2;accessKey;clientId;null;null            | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[ePoints.com2]                                                         |
      | ePoints.com;accessKey;clientId;12-12-2012;11-12-2012 | 400             | INVALID_ARGUMENTS              | Invalid date parameters date From=Sat Jun 04 00:00:00 GMT 18 is greater then To=Fri Jun 04 00:00:00 GMT 17 |
      # bug here
      | ePoints.com;accessKey;clientId;11-13-2012;2012-56-20 | 400             | INVALID_ARGUMENTS              | Invalid Date format                                                                                        |

  @prod
    Examples:
      | clientId_apiKey_fields_startDate_endDate                             | expResponseCode | expErrorCode                   | expErrorMsg                                                                                                |
      | ePoints.com2;null;null;null;null                                     | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                                                         |
      | ePoints.com2;xHNZaBGQtDmxTkrnI7NOfoXkz;null;null;null                | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'fields' is not present.                                                         |
      | ePoints.com2;xHNZaBGQtDmxTkrnI7NOfoXkz;EMAIL;null;null               | 400             | INVALID_ARGUMENTS              | Field [EMAIL] is not defined                                                                               |
      | ePoints.com2;xHNZaBGQtDmxTkrnI7NOfoXkz;clientId;null;null            | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[ePoints.com2]                                                         |
      | ePoints.com;xHNZaBGQtDmxTkrnI7NOfoXkz;clientId;12-12-2012;11-12-2012 | 400             | INVALID_ARGUMENTS              | Invalid date parameters date From=Sat Jun 04 00:00:00 GMT 18 is greater then To=Fri Jun 04 00:00:00 GMT 17 |
      # bug here
      | ePoints.com;accessKey;clientId;11-13-2012;2012-56-20                 | 400             | INVALID_ARGUMENTS              | Invalid Date format                                                                                        |