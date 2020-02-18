Feature: Points - adding for users
  As an active partner of epoints.com
  I want to be able to add points to the users
  So that I could award them for different activities

  #1) Check for PL@iatltd.com user and active status
  #2) Call to add points to user and verify the call
  #3) PointsRequest mandatory fields: NumPoints, ClientID, ApiAccessKey, Tag, PointsType
  #4) PointsRequest additional fields: AutoConfirm, onBehalfOfId, ExternalTransactionId, ReasonText, SourceURI, MerchantName
  #5) Partner ShortName is necessary to authorize transaction - used as clientId

  @PositiveCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - default positive parameters
    Given Active '<Partner>' wants to award '<User>' with points using '<idType>'
    When He makes points allocation call with '<PointsParameters>'
    Then Points should be added to the user balance
    And Transaction ID should be returned
    And Transaction data were correctly saved

  @qa @stag
    Examples:
      | Partner | User                        | PointsParameters                                                                                                                                                                                                                                                                                                           | idType |
      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | UUID   |
      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}     | UUID   |
#       This case does not work on points manager side - needed on ews and there firstly system get uuid using provided email, then make simple /transactions request with idType = UUID
#      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | EMAIL  |
#      | ePoints | pm.api.automation@gmail.com | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"PENDING","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}     | EMAIL  |

  @NegativeCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - default negative parameters
    Given Points Manager API is responding to requests
    When User makes points allocation call with invalid parameter '<User>', '<idType>', '<PointsParameters>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then Points should not be added to the user account

  @qa @stag
    Examples:
      | User                        | idType | PointsParameters                                                                                                                                                                                                                                                                                                                    | code | expErrorCode            | expErrorMsg                                                 |
#       This cases does not work on points manager side - needed on ews and there firstly system get uuid using provided email, then make simple /transactions request with idType = UUID

#      #//incorrect email
#      | notExistingEmail@gmail.com  | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}            | 404  | INVALID_USER            | User with email=[notExistingEmail@gmail.com] is invalid     |
#      #//incorrect idType //TODO probably bug need to search in jira
##      | pm.api.automation@gmail.com | wrongIdType | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 400  | Bad Request             | Failed to convert value of type 'java.lang.String' to required type 'iat.compassmassive.api.points.UserIdType' |
#      #//incorrect num of points
#      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"fifty","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}        | 400  | INVALID_ARGUMENTS       | Invalid value for parameter numPoints = [fifty].            |
#      #//incorrect autoConfirm
#      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"yes","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 400  | INVALID_ARGUMENTS       | Invalid value for parameter autoConfirm = [yes].            |
#      #//incorrect clientId
#      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"wrongClientId","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | 403  | INVALID_PARTNER         | Partner with apiKey 'envDepends' not found                  |
#      #//incorrect apiAccessKey
#      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"wrongAccessKey", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}      | 403  | INVALID_PARTNER         | Partner with accessKey=[wrongAccessKey] is invalid          |
#      #//incorrect tag
#      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"wrongTag","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}            | 400  | INVALID_TAG             | Tag with name wrongTag does not exist                       |
#      #//incorrect pointsType
#      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"wrongPointsType","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}    | 400  | INVALID_ARGUMENTS       | Invalid value for parameter pointsType = [wrongPointsType]. |
#      #//tag frequency once
#      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsRegistration","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | 400  | USER_ALREADY_WAS_REWARD | User was already rewarded for this activity                 |

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

  @PositiveCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - check if user can acquire points from specific tag
    Given Points Manager API is responding to requests
    When User checks if he can acquire points from specific tag '<User>', '<idType>', '<PointsParameters>'
    Then He will receive response about points adding possibility '<possibility>'

  @qa @stag
    Examples:
      | User                        | idType | PointsParameters                                                                                                                                                                                                                                                                                                                    | possibility |
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}          | true        |
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsRegistration","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | false       |

  @NegativeCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - check if user can acquire points from specific tag using invalid parameters
    Given Points Manager API is responding to requests
    When User checks if he can acquire points from specific tag using invalid parameters '<User>', '<idType>', '<PointsParameters>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then He will not receive response about points adding possibility

  @qa @stag
    Examples:
      | User                        | idType | PointsParameters                                                                                                                                                                                                                                                                                                                 | code | expErrorCode      | expErrorMsg                                                 |
      #//incorrect email
      | notExistingEmail@gmail.com  | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}         | 404  | INVALID_USER      | User with email=[notExistingEmail@gmail.com] is invalid     |
      #//incorrect idType //TODO probably bug need to search in jira
#      | pm.api.automation@gmail.com | wrongIdType | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}       | 400  | not_specified     | not_specified                                               |
      #//incorrect num of points
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"fifty","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}     | 400  | INVALID_ARGUMENTS | Invalid value for parameter numPoints = [fifty].            |
      #//incorrect autoConfirm
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"yes","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}       | 400  | INVALID_ARGUMENTS | Invalid value for parameter autoConfirm = [yes].            |
      #//incorrect clientId
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"wrongClientId","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}       | 403  | INVALID_PARTNER   | Partner with apiKey 'envDepends' not found                  |
      #//incorrect apiAccessKey
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"wrongAccessKey", "tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}   | 403  | INVALID_PARTNER   | Partner with accessKey=[wrongAccessKey] is invalid          |
      #//incorrect tag
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"wrongTag","pointsType":"CONFIRMED","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"}         | 400  | INVALID_TAG       | Tag with name wrongTag does not exist                       |
      #//incorrect pointsType
      | pm.api.automation@gmail.com | EMAIL  | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId", "apiAccessKey":"envDepends", "tag":"epointsBuy","pointsType":"wrongPointsType","externalTransactionId":"epoints purchased","reasonText":"automatedTestsTransactionReasonText","sourceUri":"google.com","merchantName":"merchantName"} | 400  | INVALID_ARGUMENTS | Invalid value for parameter pointsType = [wrongPointsType]. |

  @PositiveCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - award few users at once using bulk upload
    Given Points Manager API is responding to requests
    When Partner awards few users at once using bulk upload '<PointsParameters>'
    Then Points should be properly added to awarded user accounts

  @qa @stag
    Examples:
      | PointsParameters                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"} |

#    This test is switched off because it added points to all associated users even for this with expected low amount of points
#    Examples:
#      | PointsParameters                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
#      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"true","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"} |

  @PositiveCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - award few users at once using bulk upload with invalid parameters
    Given Points Manager API is responding to requests
    When Partner awards few users at once using bulk upload with invalid parameters '<PointsParameters>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Points should not be properly added to awarded user accounts

  @qa @stag
    Examples:
      | PointsParameters                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | expResponseCode | expErrorCode      | expErrorMsg                                                                    |
      #//incorrect userId //TODO bug here HS-165
#      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","wrongUserId"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}                                | 400             | not_specified     | not_specified                                                                  |
      #//incorrect allAssociatedUsers
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"notBoolean","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}     | 400             | INVALID_ARGUMENTS | Invalid value for parameter allAssociatedUsers = [notBoolean].                 |
      #//incorrect localCurrencyCode
      #//TODO bug here
#      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"wrongCurrencyCode","localCurrencyValue":"6","conversionRate":"1"} | 400             | not_specified     | not_specified                                                                  |
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"wrongValue","conversionRate":"1"} | 400             | INVALID_ARGUMENTS | Invalid value for parameter localCurrencyValue = [wrongValue].                 |
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"wrongRate"}  | 400             | INVALID_ARGUMENTS | Invalid value for parameter conversionRate = [wrongRate].                      |
      #//incorrect num of points
      | {"numPoints":"fifty","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}      | 400             | INVALID_ARGUMENTS | Invalid value for parameter numPoints = [fifty].                               |
      #//incorrect autoConfirm
      | {"numPoints":"1","autoConfirm":"yes","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}        | 400             | INVALID_ARGUMENTS | Invalid value for parameter autoConfirm = [yes].                               |
      #//incorrect clientId
      | {"numPoints":"1","autoConfirm":"1","clientId":"wrongClient","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}          | 403             | INVALID_PARTNER   | Partner with apiKey 'envDepends' not found                                     |
      #//incorrect apiAccessKey
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"wrongAccessKey","tag":"epointsBuy","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}      | 403             | INVALID_PARTNER   | Partner with apiKey 'wrongAccessKey' not found                                 |
      #//incorrect tag
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"wrongTag","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}            | 400             | INVALID_TAG       | Tag with name wrongTag does not exist                                          |
      #//incorrect pointsType
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsBuy","pointsType":"wrongPointsType","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"}    | 400             | INVALID_ARGUMENTS | Invalid value for parameter pointsType = [wrongPointsType].                    |
      #//tag frequency once
      | {"numPoints":"1","autoConfirm":"1","clientId":"ePoints.com","onBehalfOfId":"OnBehalfOfId","apiAccessKey":"envDepends","tag":"epointsRegistration","pointsType":"CONFIRMED","externalTransactionId":"externalTransactionId1","reasonText":"epoints purchased","sourceUri":"google.com","merchantName":"merchantName","users":["pm.api.automation@gmail.com","pm.api.automation2@gmail.com"],"allAssociatedUsers":"false","localCurrencyCode":"GBP","localCurrencyValue":"6","conversionRate":"1"} | 400             | INVALID_TAG       | Tag with name epointsRegistration used for bulk needs to have Frequency = NONE |

  @PositiveCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - award few users at once using multiple-awards
    Given Points Manager API is responding to requests
    When Partner awards few users at once using multiple-awards '<PointsParameters>'
    Then Points should be properly added to awarded by multiple awards user accounts

  @qa @stag
    Examples:
      | PointsParameters                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
      | current;{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"} |

  @NegativeCase @PointsAllocation
  @Regression
  Scenario Outline: Points allocation to the user - award few users at once using multiple-awards with invalid parameters
    Given Points Manager API is responding to requests
    When Partner awards few users at once using multiple-awards with ivalid parameters values '<PointsParameters>', '<code>', '<expErrorCode>', '<expErrorMsg>'
    Then Points should not be properly added to awarded user accounts

  @qa @stag
    Examples:
      | PointsParameters                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         | code | expErrorCode      | expErrorMsg                                                                    |
      #//incorrect createdAt
      | stringDate;{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"}            | 400  | INVALID_ARGUMENTS | Invalid value for parameter createdAt = [stringDate].                          |
      #//incorrect clientId
      | current;{"clientId":"wrongClientId","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"}             | 403  | INVALID_PARTNER   | Partner with apiKey 'envDepends' not found                                     |
      #//incorrect apiAccessKey
      | current;{"clientId":"ePoints.com","apiAccessKey":"wrongApiAccessKey","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"}        | 403  | INVALID_PARTNER   | Partner with apiKey 'wrongApiAccessKey' not found                              |
      #//incorrect tag
      | current;{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"wrongTag","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"}                 | 400  | INVALID_TAG       | Tag with name wrongTag does not exist                                          |
      #//tag frequency once
      | current;{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsRegistration","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"}      | 400  | INVALID_TAG       | Tag with name epointsRegistration used for bulk needs to have Frequency = NONE |
      #//incorrect userKey
      | current;{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"611fb78b-a9b6-467wrong0-b4d4-0bb728f74be3","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"} | 404  | INVALID_USER      | Account(uuid:611fb78b-a9b6-467wrong0-b4d4-0bb728f74be3) is invalid             |
      #//incorrect numPoints
      | current;{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation@gmail.com","numPoints":"wrongNumPoints","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"};{"clientId":"ePoints.com","apiAccessKey":"envDepends","tag":"epointsBuy","onBehalfOfId":"onBehalfOfId","userKey":"pm.api.automation2@gmail.com","numPoints":"1","externalTransactionId":"externalTransactionId","reasonText":"epoints purchased"}  | 400  | INVALID_ARGUMENTS | Invalid value for parameter details = [wrongNumPoints].                        |