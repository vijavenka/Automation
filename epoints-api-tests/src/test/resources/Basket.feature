Feature: Epoints API: Basket
  As ePoints user
  I want to be able to add chosen redemption items to basket
  To be able to collect rewards for redeem

  @Regression @PositiveCase @Basket
  @clearUserBasketAfterTest @resetProductsListBeforeTest
  Scenario Outline: Basket - update, get
    Given Epoints API is responding to requests
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    And Fetching membershipType
    And Redemption item '0' is selected for '<businessId>' from list ordered 'asc'
    When '0' redemption item is added '4' to basket
    Then Basket is properly updated and contains '0' redemption items count '4'
    When '0' redemption item is added '2' to basket
    Then Basket is properly updated and contains '0' redemption items count '2'
    When Redemption item '1' is selected for '<businessId>' from list ordered 'asc'
    And '1' redemption item is added '3' to basket
    Then Basket is properly updated and contains '1' redemption items count '3'
    And Basket is properly updated and contains '0' redemption items count '0'

  @qa @stag @prod
    Examples:
      | login_password       | businessId |
      | epointsUserDefault_1 | null       |
#      | epointsUserDefault_1 | united     |

  #  PD-717 Based on MembershipType(Silver/Gold) product price will have (0/10) percent discount.
   # Then deliveryCost has to calculate based on product price reached points_manager.DeliveryCost.freeDeliveryFrom
  #  If reached then no deliveryCost otherwise deliveryCost is calculated

  Scenario Outline: PD-717 Based on different MembershipType, user buys multiple products with the same or different merchants and deliveryCost reached
    Given In epoints '<user>' logged in
    Then The product price will be '<user_MembershipType>' percentage discounted
    And Based on cummulative product price of each merchant
    And If product price reached points_manager.DeliveryCost.freeDeliveryFrom after applying logged in ‘<user_MembershipType>’ percentage discount on freeDeliveryFrom
    Then points_manager.DeliveryCost.deliveryCost will not be applied

    Examples:
      | user      | user_MembershipType |
      | silver    | 0                   |
      | gold      | 10                  |


  Scenario Outline: PD-717 Based on different MembershipType, user buys multiple products with the same or different merchants and deliveryCost not reached
    Given In epoints '<user>' logged in
    Then The product price will be '<user_MembershipType>' percentage discounted
    And Based on cummulative product price of each merchant
    And If product price does not reach points_manager.DeliveryCost.freeDeliveryFrom after applying logged in ‘<user_MembershipType>’ percentage discount on freeDeliveryFrom
    Then points_manager.DeliveryCost.deliveryCost will be applied

    Examples:
      | user      | user_MembershipType |
      | silver    | 0                   | 
      | gold      | 10                  |


  Scenario: PD-774,PD-864,PD-892,PD-909 epoints Gold or Silver user, ordering of a product from a merchant and verifying points in pending and confirmed column
  when points_manager.User.restrictBonusPoints=0
    Given In epoints
    When Gold or Silver user completed ordering products from merchant
    Then Bonus points will be calculated from discountedPriceInEpoints * points_manager.BonusPointsPercentage.bonusPointsPercentage column value / 100
    And This calculated value will be available in points_manager.User.pending column
    And It will be appended into points_manager.User.confirmed column after 3 days 
    And Product points value will be refunded to points_manager.User.confirmed column
    And Activity Page, Current balance tab, bonus points will be displayed in Out column 
    
  Scenario: PD-774,PD-864,PD-892 epoints Gold or Silver user, cancelled the product before 3 days when points_manager.User.restrictBonusPoints=0
    Given In epoints
    When Gold or Silver user cancelled the product before 3 days
    Then Calculated value discountedPriceInEpoints * points_manager.BonusPointsPercentage.bonusPointsPercentage column value / 100 will be removed from points_manager.User.pending column
    And Product points value will be refunded to points_manager.User.confirmed column
    And Activity Page, Declined tab, bonus points will be displayed in Out column
    
  Scenario: PD-774,PD-864,PD-892 epoints Gold or Silver user, cancelled the product after 3 days when points_manager.User.restrictBonusPoints=0
    Given In epoints
    When Gold or Silver user cancelled the product after 3 days
    Then Calculated value discountedPriceInEpoints * points_manager.BonusPointsPercentage.bonusPointsPercentage column value / 100 will be removed from points_manager.User.confirmed column whatever available
    And Product points value will be refunded to points_manager.User.confirmed column
    And Activity Page, Current balance tab, bonus points will be displayed in Out column

  Scenario: PD-774,PD-864,PD-892,PD-909 epoints Gold or Silver user, ordering of multiple product from different merchants and cancelling one merchant product
  when points_manager.User.restrictBonusPoints=0
    Given In epoints
    When Gold or Silver user completed ordering products from different merchants
    Then Bonus points will be calculated for each merchant as discountedPriceInEpoints * points_manager.DeliveryCost.bonusPointsPercentage column value / 100
    And This calculated value will be available in points_manager.User.pending column
    And After 3 days if user cancelled one merchant products ie after /auto-confirm-points api is executed
    Then Partial points has to be refunded as discountedPriceInEpoints * points_manager.BonusPointsPercentage.bonusPointsPercentage column value / 100 from confirmed column of User table
    And Product points value will be refunded to points_manager.User.confirmed column
    And Activity Page, Current balance tab, bonus points will be displayed in Out column

Scenario: PD-774,PD-864,PD-892,PD-909 epoints Gold or Silver user, ordering of multiple product from different merchants and cancelling one merchant product
  when points_manager.User.restrictBonusPoints=1
    Given In epoints
    When Gold or Silver user completed ordering products from different merchants
    Then Bonus points will not be calculated
    And If user cancelled one merchant products
    And Product points value will be refunded to points_manager.User.confirmed column
