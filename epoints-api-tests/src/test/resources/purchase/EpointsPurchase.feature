Feature: Epoints API: Epoints purchase
  As ePoints user
  I want to be able to buy epoints for real money
  To be able to increase epoints amount on my account and buy more products using epoints

  @Regression @PositiveCase @EpointsPurchase
  Scenario Outline: Epoints purchase - buy/epoints contract and response validation
    Given User is authorizing with following data '<login_password>'
    When User request '<epointsNumber>' epoints buy call
    Then In response he will receive transaction details including tax, fee

  @qa
    Examples:
      | login_password       | epointsNumber                          |
      | epointsUserDefault_1 | {"moneyValue": "30", "epoints": 6000}  |
      | epointsUserDefault_1 | {"moneyValue": "60", "epoints": 12000} |







