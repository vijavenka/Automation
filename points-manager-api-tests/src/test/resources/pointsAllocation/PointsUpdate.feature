Feature: Points - update
  As an active partner of epoints.com
  I want to be able to update chosen data of user points transaction
  So that I can change wrongly processed transactions and correct them

  @PositiveCase @PointsAllocation
  @Regression
  Scenario Outline: Update points transaction - proper points transaction update
    Given User '<userId>' is awarded with points for his activities '<pointsAllocationParameters>'
    When Partner update user transaction details data '<pointsUpdateParameters>'
    Then User transaction details data will be updated

  @qa @stag
    Examples:
      | userId                      | pointsAllocationParameters                                                                                                                                                                                                                                                                                               | pointsUpdateParameters                                                                                                                        |
      #//TODO bug here HS-149, parameters list should be changed when
      | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"CONFIRMED","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"} |
      | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"DECLINED","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}  |

  @NegativeCase @PointsAllocation
  @Regression
  Scenario Outline: Update points transaction - check behaviour of system when points transaction will be done with invalid parameters
    Given User '<userId>' is awarded with points for his activities '<pointsAllocationParameters>'
    When Partner update user transaction details data using invalid parameters '<pointsUpdateParameters>', '<transactionId>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then User transaction details will not be updated

  @qa @stag
    Examples:
      | transactionId | userId                      | pointsAllocationParameters                                                                                                                                                                                                                                                                                                 | pointsUpdateParameters                                                                                                                                  | code | expErrorCode         | expErrorMsg                                                 |
      #//incorrect transactionId //TODO bug here HS-126
#      | incorrect     | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"CONFIRMED","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}           | 403  | not_specified        | not_specified                                               |
      #//incorrect accessKey
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"wrongAccessKey", "clientId":"ePoints.com", "pointsType":"CONFIRMED","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}       | 403  | INVALID_PARTNER      | Partner with accessKey=[wrongAccessKey] is invalid          |
      #//incorrect clientId
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"envDepends", "clientId":"wrongClientId", "pointsType":"CONFIRMED","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}         | 403  | INVALID_PARTNER      | Partner with apiKey 'envDepends' not found                  |
      #//incorrect userId
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"CONFIRMED","userId":"wrongUserId","userIdType":"EMAIL"}                           | 404  | INVALID_USER         | User with email=[wrongUserId] is invalid                    |
      #//incorrect userIdType
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"CONFIRMED","userId":"pm.api.automation@gmail.com","userIdType":"wrongUserIdType"} | 400  | INVALID_ARGUMENTS    | Invalid value for parameter userIdType = [wrongUserIdType]. |
      #//incorrect pointsStatus
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"wrongStatus","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}         | 400  | INVALID_ARGUMENTS    | Invalid value for parameter pointsType = [wrongStatus].     |
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"REDEEMED","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}            | 400  | INVALID_POINT_STATUS | Cannot update points status to status=REDEEMED              |
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"SPENT","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}               | 400  | INVALID_POINT_STATUS | Cannot update points status to status=SPENT                 |
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"REDEEMED","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}            | 400  | INVALID_POINT_STATUS | Cannot update points status to status=REDEEMED              |
      | correct       | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | {"apiAccessKey":"envDepends", "clientId":"ePoints.com", "pointsType":"SPENT","userId":"pm.api.automation@gmail.com","userIdType":"EMAIL"}               | 400  | INVALID_POINT_STATUS | Cannot update points status to status=SPENT                 |