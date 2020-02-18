@Ews
Feature: Account Balance for user via REST API
  As a active-registered User of Epoints
  I want to be able to check my points balance
  So that I will know exact number of points at any point in time

  #1) Check for pm.api.automation@gmail.com user and active status
  #2) Call for user balance and verify the call
  #3) Verify output to points in database - or user details based on first call
  #4) Hash: 4WqA3UFbuBuqHRcWiRa3rT1FRCHASPVQjpkG70mOo4g= for password: P@ssw0rd

  @PositiveCase @AccountBalance @PmBuild @PmRegression @Production
  Scenario Outline: Balance for active account - check of the call
    Given '<User>' has active account with points
    When Call for account balance is made '<idType>'
    Then Points balance should be returned

    Examples:
      | User                        | idType |
      | pm.api.automation@gmail.com | UUID   |
      | pm.api.automation@gmail.com | EMAIL  |

  @PositiveCase @AccountBalance @PmBuild @PmRegression @Production
  Scenario Outline: Account balance for active account - invalid parameter values
    Given Points Manager API is responding to requests
    When '<User>' makes account balance call with invalid parameters '<idType>', '<apiKey>', '<code>'
    Then His account balance should not be returned due to '<expErrorCode>', '<expErrorMsg>'

    Examples:
      | User                        | idType      | apiKey         | code | expErrorCode    | expErrorMsg                                                                                                    |
# bug here NBO-7769
      | pm.api.automation@gmail.com | UUID        | envDepends     | 404  | INVALID_USER    | User with userId=[pm.api.automation@gmail.com] is invalid                                                      |
      | pm.api.automation@gmail.com | wrongIdType | envDepends     | 404  | Bad Request     | Failed to convert value of type 'java.lang.String' to required type 'iat.compassmassive.api.points.UserIdType' |
# bug here NBO-7768
      | pm.api.automation@gmail.com | UUID        | wrongAccessKey | 403  | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid                                                             |