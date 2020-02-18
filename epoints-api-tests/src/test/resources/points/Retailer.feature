Feature: Epoints API: Points -> retailers - recently visited
  As an epoints user
  I want to have history of visited retailers
  To be able to remember which of retailers was recently visited by me

#  //TODO for now did not figured out how to trigger complete transition process to put new retailer into recently visited retailer list, such test wil also be needed

  @Regression @PositiveCase @recentlyVisited
  Scenario Outline: Points - recently visited retailers
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When Recently visited retailer request is done
    And Merchant data returned by recently visited retailers call is correct

  @qa @stag @prod
    Examples:
      | login_password       |
      | epointsUserDefault_1 |

  @Regression @PositiveCase @favoriteRetailers
  @deleteAllMerchantFromUserFavouritesBeforeTest
  Scenario Outline: Points - favourite retailers
    Given User is authorizing with following data '<login_password>'
    When User add '2' retailers to favourites
    Then Retailers will be properly added to favourites
    And Retailers can be also removed from favourites

  @qa @stag @prod
    Examples:
      | login_password       |
      | epointsUserDefault_1 |