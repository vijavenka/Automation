Feature: Epoints rewards history page
  As an epoints user
  I want to have rewards history section in your account page
  So that I could see on which products I spend epoints in the past

  # // 1.1 //  ----- ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082)
  # --------------------------------------------------------------------------------------------- page and table content
  @Regression @RewardsHistoryPage
  Scenario Outline: ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082) - page and table content
    Given Rewards history page is opened by logged user in '<partner>' context
    Then He can see that page header has proper name
    And Reward history table has proper columns
    And Reward history page has proper content according to DB for '<partner>' context

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.2 //  ----- ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082)
  # ---------------------------------------------------------------------------------------- working of contact us links
  @Regression @RewardsHistoryPage
  Scenario Outline: ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082) - working of contact us links
    Given Rewards history page is opened by logged user in '<partner>' context
    When User click on chosen redemption contact us link
    Then He will be redirected to contact us help page

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.3 //  ----- ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082)
  # ----------------------------------------------------------------------------------------- empty rewards history page
  @Regression @RewardsHistoryPage
  @userIsLogoutFromFacebook @deleteFacebookUserBeforeTest
  Scenario: ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082) - empty rewards history info
    Given Join page is opened
    Given User use join by facebook option with correct data
    Given Rewards history page is opened
    Then He will see that rewards history page is empty
    And No rewards history info is displayed