Feature: Epoints API: Coupon-usage, cash-in points
  As ePoints user which is also bdl news branch manager
  I want to be able to list of my branch coupons usage and be able to cash in received for coupon redemption points on epoints.com page
  To be able to see distribution of coupons usage of my branch and get real money for epoints received for coupons usages

  #assumption - bdl manager has only one branch, and coupon are redeemed in that branch(validation will check if all rows belongs to same store)
  @Regression @PositiveCase @CashIn
  Scenario Outline: Coupons-usage - get list of coupons
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When News branch manager pull 'coupon usage' list
    Then All coupons redemption of manager branch will be returned
    And Coupon usage number is correct
    And Data will be returned in redeemed date desc order
    And All coupon usages belongs to managers store
    #//TODO we can do coupon redemption before test and check correctness of first element on list, but it requires more time

  @qa
    Examples:
      | login_password    |
      | newsUserDefault_1 |

  @Regression @NegativeCase @CashIn
  Scenario Outline: Coupons-usage - try to get list of coupons-usage by not news member
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When News branch manager pull 'coupon usage' list
    Then Zero results will be returned

  @qa
    Examples:
      | login_password       |
      | epointsUserDefault_1 |

  @Regression @PositiveCase @CashIn
  @PointForCouponRedeemAreAdded
  Scenario Outline: Coupons-usage, cash-in - correct cash-in
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    And User coupon usage points summary is known
    And User balance is known
    When User cash-in some epoints
    And Balance confirmed points will be constantly decreased of cashed points
    And User coupon usage points summary is updated of cashed points

  @qa
    Examples:
      | login_password    |
      | newsUserDefault_1 |

  @Regression @NegativeCase @CashIn
  Scenario Outline: Coupons-usage, cash-in - error validation
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When User try to cash epoints with following data '<body>'
    Then Cash-in returns proper error message '<message>'

  @qa
    Examples:
      | login_password    | body                                                                             | message                                       |
      # epointsValue lover than 2000
      | newsUserDefault_1 | 5,123222,12333333,test,my-reference                                              | Insufficient number of points to cash in      |
      # epointsValue higher than available value
      | newsUserDefault_1 | 2000000,123222,12333333,test,my-reference                                        | User has not enough points to deduct.         |
      # sort code above 8 signs
      | newsUserDefault_1 | 2000,12-34-56-78,12333333,test,my-reference                                      | Sort code must be between 6 and 8 signs       |
      # sort code below 6 SIGNS
      | newsUserDefault_1 | 2000,12-34,12333333,test,my-reference                                            | Sort code must be between 6 and 8 signs       |
      # account number BELOW 8 SIGNS
      | newsUserDefault_1 | 2000,12-34-56,1234567,test,my-reference                                          | Account number must be between 8 and 10 signs |
      # account number above 10 signs
      | newsUserDefault_1 | 2000,12-34-56,123456789012,test,my-reference                                     | Account number must be between 8 and 10 signs |
      # accountHoldersName above 40 signs
      | newsUserDefault_1 | 2000,12-34-56,12345678,12345678901234567890 qa 123456 - =234/5%_+11,my-reference | Account name must be between 1 and 40 signs   |
      # accountHoldersName empty
      | newsUserDefault_1 | 2000,12-34-56,12345678,empty,my-reference                                        | Account name must be between 1 and 40 signs   |
      # usersReference above 40 signs
      | newsUserDefault_1 | 2000,12-34-56,12345678,test,12345678901234567890 qa 123456 - =234/5%_+11         | User reference must be between 1 and 40 signs |
      # usersReference empty
      | newsUserDefault_1 | 2000,12-34-56,12345678,test,empty                                                | User reference must be between 1 and 40 signs |