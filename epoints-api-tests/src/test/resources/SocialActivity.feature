Feature: Epoints API: Social Activity
  As ePoints user
  I want to be able to like epoints on facebook or follow on twitter
  To be able to receve some points award for mentioned actions

  @Regression @PositiveCase @socialMedia
  @deleteUserAfterTest
  Scenario Outline: Social media - liking epoints on facebook
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<login>'
    And Confirm email url is opened for user '<login>'
    And Confirm email response returns proper data
    And Account is confirmed with details: '<password>', '<firstName>', '<lastName>', '<gender>'
    And User is authorizing with following data '<login>,<password>'
    And User balance is known
    And Facebook like status for user is set to false
    When User like epoints on facebook
    Then User will be awarded for liking epoints on facebook with 10 points
    And User cannot be awarded twice for liking epoints on facebook

  @qa @stag @prod
    Examples:
      | login                 | password | firstName | lastName    | gender |
      | epoints.test.account_ | pppppp   | apiAuto   | testAccount | male   |

  @Regression @PositiveCase @socialMedia
  @deleteUserAfterTest
  Scenario Outline: Social media - following epoints on twitter
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<login>'
    And Confirm email url is opened for user '<login>'
    And Confirm email response returns proper data
    And Account is confirmed with details: '<password>', '<firstName>', '<lastName>', '<gender>'
    And User is authorizing with following data '<login>,<password>'
    And User balance is known
    And Twitter follow status for user is set to false
    When User follow epoints on twitter
    Then User will be awarded for following epoints on twitter with 10 points
    And User cannot be awarded twice for following epoints on twitter

  @qa @stag @prod
    Examples:
      | login                 | password | firstName | lastName    | gender |
      | epoints.test.account_ | pppppp   | apiAuto   | testAccount | male   |