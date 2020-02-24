@NoLongerRequired-rouletteWillBeThrowOut
Feature: Epoints API: Prizes
  As an epoints user
  I want to have prizes page
  So that I could spin roulette wheel as reward for confirmed transaction on external retailer pages

  @PositiveCase @prizes @deleteClickoutAndPointsAfterTest
  Scenario Outline: Prizes - spins count
    Given User is authorizing with following data '<login_password>'
    And User spins number is set to '<spinsNumber>', '<spinsStatus>'
    When Spins number request will be done for '<spinsStatus>', and user '<login_password>', 200
    Then Correct number of spins '<spinsNumber>', '<spinsStatus>' will be returned in response

  @qa @stag @prod
    Examples:
      | login_password       | spinsNumber | spinsStatus |
      | epointsUserDefault_1 | 0           | AVAILABLE   |
      | epointsUserDefault_1 | 0           | USED        |
      | epointsUserDefault_1 | 0           | DECLINED    |
      | epointsUserDefault_1 | 1           | AVAILABLE   |
      | epointsUserDefault_1 | 1           | USED        |
      | epointsUserDefault_1 | 1           | DECLINED    |

  @PositiveCase @prizes
  Scenario Outline: Prizes - spins count - error message validation
    Given User is authorizing with following data '<login_password>'
    When Spins number request will be done for '<spinsStatus>', and user '<user>', <status>
    Then Spins count error message will be thrown with '<status>', '<error>', '<message>'

  @qa @stag @prod
    Examples:
      | login_password       | spinsStatus | user                 | status | error       | message                          |
      | epointsUserDefault_1 | WRONGSTATUS | epointsUserDefault_1 | 400    | Bad Request | Value 'WRONGSTATUS' is incorrect |

  @PositiveCase @prizes @deleteClickoutAndPointsAfterTest
  Scenario Outline: Prizes - available spins should create Points after using it
    Given Epoints API is responding to requests
    And User is authorizing with following data 'epointsUserDefault_1'
    And User spins number is set to '1', 'AVAILABLE'
    When Spin is used to win 10 points (pointsStatus: <pointsStatus>), 201
    And Spin's status is USED
    Then Spin points record is created with points
    And Number of new spin Points record is 1

    Examples:
      | pointsStatus |
      | PENDING      |
      | CONFIRMED    |

  @NegativeCase @prizes @deleteClickoutAndPointsAfterTest
  Scenario Outline: Prizes - USED spins should not create Points after using it
    Given Epoints API is responding to requests
    And User is authorizing with following data 'epointsUserDefault_1'
    And User spins number is set to '1', 'USED'
    And Spin is used to win 10 points (pointsStatus: <pointsStatus>), 400
    Then Spin awarding results with error INVALID_ARGUMENTS, Spin for given externalTransactionId: [$SPIN-UUID] is in an invalid state [USED]
    And Number of new spin Points record is 1
    #The expected number is 0 + 1 because one record was created during Given phase (when the USED spin was prepared)

    Examples:
      | pointsStatus |
      | PENDING      |
      | CONFIRMED    |

  @NegativeCase @prizes @deleteClickoutAndPointsAfterTest
  Scenario Outline: Prizes - DECLINED spins should not create Points after using it
    Given Epoints API is responding to requests
    And User is authorizing with following data 'epointsUserDefault_1'
    And User spins number is set to '1', 'DECLINED'
    When Spin is used to win 10 points (pointsStatus: <pointsStatus>), 400
    Then Spin awarding results with error INVALID_ARGUMENTS, Spin for given externalTransactionId: [$SPIN-UUID] is in an invalid state [DECLINED]
    And Number of new spin Points record is 0

    Examples:
      | pointsStatus |
      | PENDING      |
      | CONFIRMED    |

  @NegativeCase @prizes
  Scenario Outline: Prizes - no clickout - no spin - no externalTransactionId
    Given Epoints API is responding to requests
    And User is authorizing with following data 'epointsUserDefault_1'
    When Spin is used to win 10 points (pointsStatus: <pointsStatus>), 400
    Then Spin awarding results with error INVALID_ARGUMENTS, ExternalTransactionId is required
    And Number of new spin Points record is 0

    Examples:
      | pointsStatus |
      | PENDING      |
      | CONFIRMED    |

  @NegativeCase @prizes @deleteClickoutAndPointsAfterTest
  Scenario Outline: Spin of user XXX should not be possible to award other user
    Given Epoints API is responding to requests
    And User is authorizing with following data 'epointsUserDefault_1'
    And User spins number is set to '1', 'AVAILABLE'
    And User is authorizing with following data 'epointsUserDefault_2'
    When Spin is used to win 10 points (pointsStatus: <pointsStatus>), 400
    Then Spin awarding results with error INVALID_ARGUMENTS, Spin for given externalTransactionId: [$SPIN-UUID] does not match the requested user uuid [$USER-UUID]
    And Number of new spin Points record is 0
    And Spin's status is AVAILABLE

    Examples:
      | pointsStatus |
      | PENDING      |
      | CONFIRMED    |