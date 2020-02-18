@Ews
Feature: Points Adding for users via REST API
  As an active partner of epoints.com
  I want to be able to add points to the users
  So that I could award them for different activities

  #1) Check for PL@iatltd.com user and active status
  #2) Call to add points to user and verify the call
  #3) PointsRequest mandatory fields: NumPoints, ClientID, ApiAccessKey, Tag, PointsType
  #4) PointsRequest additional fields: AutoConfirm, onBehalfOfId, ExternalTransactionId, ReasonText, SourceURI, MerchantName
  #5) Partner ShortName is necessary to authorize transaction - used as clientId

  @PositiveCase @PointsAllocation @PmBuild @PmRegression @Production
  Scenario Outline: Points allocation to the user - default positive parameters
    Given Active '<Partner>' wants to award '<User>' with points using '<idType>'
    When He makes points allocation call with '<PointsParameters>'
    Then Points should be added to the user balance
    And Transaction ID should be returned
    And Transaction data were correctly saved

    Examples:
      | Partner | User                        | PointsParameters                                                                                                                                                                                                                                                                                                           | idType |
      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | UUID   |
      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}     | UUID   |
      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | EMAIL  |
      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}     | EMAIL  |

  @NegativeCase @PointsAllocation @PmBuild @PmRegression @Production
  Scenario Outline: Points allocation to the user - default negative parameters
    Given Points Manager API is responding to requests
    When User makes points allocation call with invalid parameter '<User>', '<idType>', '<PointsParameters>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then Points should not be added to the user account

    Examples:
      | User                        | idType | PointsParameters                                                                                                                                                                                                                                                                                                                    | code | expErrorCode            | expErrorMsg                                                 |
      #//incorrect email bug here NBO-10220
      | notExistingEmail@gmail.com  | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}            | 400  | INVALID_USER            | User with email=[notExistingEmail@gmail.com] is invalid     |
      #//incorrect idType //TODO probably bug need to search in jira
#      | pm.api.automation@gmail.com | wrongIdType | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 400  | Bad Request             | Failed to convert value of type 'java.lang.String' to required type 'iat.compassmassive.api.points.UserIdType' |
      #//incorrect num of points
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"fifty","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}        | 400  | INVALID_ARGUMENTS       | Invalid value for parameter numPoints = [fifty].            |
      #//incorrect autoConfirm
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"yes","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 400  | INVALID_ARGUMENTS       | Invalid value for parameter autoConfirm = [yes].            |
      #//incorrect clientId
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"wrongClientId","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 403  | INVALID_PARTNER         | Partner with apiKey 'envDepends' not found                  |
      #//incorrect apiAccessKey bug here NBO-10220
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"wrongAccessKey", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}      | 403  | INVALID_PARTNER         | Partner with accessKey=[wrongAccessKey] is invalid          |
      #//incorrect tag
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"wrongTag","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}            | 400  | INVALID_TAG             | Tag with name wrongTag does not exist                       |
      #//incorrect pointsType
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"wrongPointsType","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}    | 400  | INVALID_ARGUMENTS       | Invalid value for parameter pointsType = [wrongPointsType]. |
      #//tag frequency once
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsRegistration","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | 400  | USER_ALREADY_WAS_REWARD | User was already rewarded for this activity                 |

      #//incorrect email
      | notExistingEmail@gmail.com  | UUID   | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}            | 404  | INVALID_USER            | Account(uuid:notExistingEmail@gmail.com) is invalid         |
      #//incorrect idType //TODO probably bug need to search in jira
#      | pm.api.automation@gmail.com | wrongIdType | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 400  | Bad Request             | Failed to convert value of type 'java.lang.String' to required type 'iat.compassmassive.api.points.UserIdType' |
      #//incorrect num of points
      | pm.api.automation@gmail.com | UUID   | {"numPoints":"fifty","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}        | 400  | INVALID_ARGUMENTS       | Invalid value for parameter numPoints = [fifty].            |
      #//incorrect autoConfirm
      | pm.api.automation@gmail.com | UUID   | {"numPoints":"1","autoConfirm":"yes","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 400  | INVALID_ARGUMENTS       | Invalid value for parameter autoConfirm = [yes].            |
      #//incorrect clientId
      | pm.api.automation@gmail.com | UUID   | {"numPoints":"1","autoConfirm":"1","clientId":"wrongClientId","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 403  | INVALID_PARTNER         | Partner with apiKey 'envDepends' not found                  |
      #//incorrect apiAccessKey
      | pm.api.automation@gmail.com | UUID   | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"wrongAccessKey", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}      | 403  | INVALID_PARTNER         | Partner with apiKey 'wrongAccessKey' not found              |
      #//incorrect tag
      | pm.api.automation@gmail.com | UUID   | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"wrongTag","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}            | 400  | INVALID_TAG             | Tag with name wrongTag does not exist                       |
      #//incorrect pointsType
      | pm.api.automation@gmail.com | UUID   | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"wrongPointsType","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}    | 400  | INVALID_ARGUMENTS       | Invalid value for parameter pointsType = [wrongPointsType]. |
      #//tag frequency once
      | pm.api.automation@gmail.com | UUID   | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsRegistration","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | 400  | USER_ALREADY_WAS_REWARD | User was already rewarded for this activity                 |