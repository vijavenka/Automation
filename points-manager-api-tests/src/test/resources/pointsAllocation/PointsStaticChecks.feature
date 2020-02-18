Feature: Points - static data check
  As an admin of epoints.com
  I want to be able to check points table dta consistency
  So that I could be sure there is no issues connected with awarded points

  @PositiveCase @PointsStaticChecks
  @qa @stag @prod
  Scenario: Points table - check if externalTransactionIds are duplicated
    When Transaction ids will be returned for selected date range
    Then There should be not duplicated entries of external transaction ids in returned data