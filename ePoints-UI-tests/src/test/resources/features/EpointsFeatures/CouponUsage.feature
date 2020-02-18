Feature: Epoints Cash In page
  As an epoints user
  I want to have 404 page
  So that I could land on it when something go wrong on epoints.com

  # // 1.1 //  ---------------------------------------------------- RETAILER CASH IN PAGE - required login functionality
  # ------------------------------------------------------------------------------------ page opened by not allowed user
  @Regression @CouponUsage
  Scenario: RETAILER CASH IN PAGE - required login functionality - page opened by not allowed user
    Given Epoints home page is opened by epoints logged user with cookie panel not visible
    When Not 'News project group' member tries open 'coupon usage' page
    Then User will be moved to '404' page

  # // 1.2 //  ---------------------------------------------------- RETAILER CASH IN PAGE - required login functionality
  # ---------------------------------------------------------------------------------------- page opened by allowed user
  @Regression @CouponUsage
  Scenario Outline: RETAILER CASH IN PAGE - required login functionality - page opened by allowed user
    Given User with a certain coupon usage access is signed in to epoints
    When 'News project group' member tries open 'coupon usage' page
    Then User will be moved to 'coupon usage' page
    And It contains cashed points statistics
    And It contains 'cash out' module
    And It contains coupons redemption breakdown table wit columns '<columnNames>'
    And It contains pagination module

    Examples:
      | columnNames                                              |
      | Date;Store Name;Coupon Name;Coupon Saving;epoints Earned |

#  # // 1.3 //  ---------------------------------------------------- RETAILER CASH IN PAGE - required login functionality
#  # --------------------------------------------------------------------------------------------------- cash out epoints
#  @Regression @CouponUsage
#  Scenario: RETAILER CASH IN PAGE - required login functionality - cash out epoints
#    Given Coupon usage page is opened
#    When User click 'cash out' button
#    And Fill all form needed data
#    And Click 'submit' button
#    Then Cash out form will be closed
#    And Available epoints to be convert statistic will be updated
#    And Epoints converted statistic will be updated
#    And 'Cash out' button cash value will be updated
#    And User balance will be decreased about used points value
#
#  # // 1.3 //  ---------------------------------------------------- RETAILER CASH IN PAGE - required login functionality
#  # ----------------------------------------------------------------------------------------- maximum available checkbox
#  @Regression @CouponUsage
#  Scenario: RETAILER CASH IN PAGE - required login functionality - cash out epoints
#    Given Coupon usage page is opened
#    And User click 'cash out' button
#    When User click 'maximum available' checkbox
#    Then Points value to be used field will be automatically filled

  #//TODO fields validation to be done, not sure if alerts will be added or only submit button disabled