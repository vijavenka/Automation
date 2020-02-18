Feature: Epoints API: Checkout
  As a user
  I want to be able to manage my redemption orders
  To be able to create new redemption order, view redemption order history and details of specific one

  @Regression @PositiveCase @Checkout
  @clearUserBasketAfterTest @deleteOrderPointsManager @resetProductsListBeforeTest
  Scenario Outline: checkout flow - validation for order properly created in points manager and epoints properly deducted
#    Given Email 'epoints.test.account_' Join standard epoints and verify account
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    And Fetching membershipType
    And User delivery address is set 'DEFAULT'
    And Redemption item '0' is selected for '<businessId>' from list ordered 'asc'
    And '0' redemption item is added '2' to basket
    And User balance for '<businessId>' is stored
    Given Checkout is made for basket items and deliveryAddress 'DEFAULT' with following totalEpoints 'previous_call' value for '<businessId>' status '200'
    Then Redemption order is properly created for '<businessId>'
    And User balance for '<businessId>' is properly reduced

  @qa
    Examples:
      | login_password       | businessId |
      | epointsUserDefault_1 | null       |
#      | unitedUserDefault_1  | united     |

  @Regression @NegativeCase @Checkout
  @clearUserBasketAfterTest @deleteOrderPointsManager @resetProductsListBeforeTest
  Scenario Outline: checkout flow - negative cases
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    And User delivery address is set 'DEFAULT'
    And Redemption item '0' is selected for '<businessId1>' and '<departmentCategory>' from list ordered '<redemptionListOrder>'
    And '0' redemption item is added '1' to basket
    Given Checkout is made for basket items and deliveryAddress 'DEFAULT' with following totalEpoints '<totalEpoints>' value for '<businessId2>' status '<status>'
    Then Checkout response - error message will be returned '<error>', '<message>'

  @qa
    Examples:
      | login_password       | businessId1 | businessId2 | totalEpoints  | redemptionListOrder | error               | message                             | status | departmentCategory |
      # difference between product's businessId and bossinessId used in checkout request
      | epointsUserDefault_2 | united      | null        | previous_call | asc                 | Bad Request         | Invalid product scope=[united] (    | 400    | null               |
      # difference between product's businessId and bossinessId used in checkout request
      | epointsUserDefault_2 | null        | united      | previous_call | asc                 | Bad Request         | Invalid product scope=[null] (      | 400    | null               |
      # totalEpoints different than sum of products epoints
      | epointsUserDefault_2 | null        | null        | 0             | asc                 | Service Unavailable | Service Unavailable                 | 503    | null               |
      # not enough points
      | epointsUserDefault_2 | null        | null        | previous_call | desc                | Bad Request         | User has no enough points to deduct | 400    | null               |
      # not enough points
      | epointsUserDefault_2 | united      | united      | previous_call | desc                | Bad Request         | User %s has no united account       | 400    | null               |

  @Regression @NegativeCase @Checkout
  @clearUserBasketAfterTest @deleteOrderPointsManager @resetProductsListBeforeTest
  Scenario Outline: checkout flow - negative cases - product with businessIds which are varying
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    And User delivery address is set 'DEFAULT'
    And Redemption item '0' is selected for '<businessId1>' and '<departmentCategory>' from list ordered '<redemptionListOrder>'
    And '0' redemption item is added '1' to basket
    And Redemption item '0' is selected for '<businessId2>' and '<departmentCategory>' from list ordered '<redemptionListOrder>'
    And '0' redemption item is added '1' to basket
    Given Checkout is made for basket items and deliveryAddress 'DEFAULT' with following totalEpoints '<totalEpoints>' value for '<businessId2>' status '<status>'
    Then Checkout response - error message will be returned '<error>', '<message>'

  @qa
    Examples:
      | login_password       | businessId1 | businessId2 | totalEpoints  | redemptionListOrder | error       | message                          | status | departmentCategory |
      | epointsUserDefault_2 | united      | null        | previous_call | asc                 | Bad Request | Invalid product scope=[united] ( | 400    | null               |
      | epointsUserDefault_2 | null        | united      | previous_call | asc                 | Bad Request | Invalid product scope=[null] (   | 400    | null               |