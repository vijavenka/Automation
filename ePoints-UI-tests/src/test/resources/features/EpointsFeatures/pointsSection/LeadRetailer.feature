Feature: Epoints lead retailer page
  As an epoints user
  I want to have lead Retailer page
  So that I could read retailer description before being redirected to retailer page and se its offers

#  Assumptions:
#  Merchant with offers(offers differs with commissionValue and maxCommission), long description and long t&c - "Argos Loyalty Points"
#  Merchant without offers, with short description and ahort t&c - "Mybag.com"
#  Feed used for providing offers for "Argos Loyalty Points" merchant - UI_AUTOMATION_LEAD_FEED

#  !!! All tests highly depends on mentioned merchants and its feeds !!!

  @Regression @MerchantLead
  Scenario Outline: Lead page - elements availability according to merchant content and login state
    Given Points page is opened by <loginState> user
    When Merchant Lead <merchantName> is opened
    Then Merchant <merchantName> Lead's elements are properly displayed to <loginState> user
    #Then Following elements are displayed with values matching the merchant's ones according to login state and chosen merchant(2 used each with different content)
    # | name, description, logo, max commission converted to epoints, days to get confirmed points, terms and condition, join sections |

    Examples:
      | loginState | merchantName                   |
      | not logged | UI AUTOMATION LEAD MERCHANT I  |
      | not logged | UI AUTOMATION LEAD MERCHANT II |
      | logged     | UI AUTOMATION LEAD MERCHANT I  |
      | logged     | UI AUTOMATION LEAD MERCHANT II |

  @Regression @MerchantLead
  Scenario Outline: Lead page - working of join buttons
    Given Points page is opened by not logged user
    When Merchant Lead UI AUTOMATION LEAD MERCHANT I is opened
    Then User can be moved to join page by clicking join button in <block> section

    Examples:
      | block          |
      | description    |
      | recommendation |

  @Regression @MerchantLead
  Scenario: Lead page - offers content
    Given Points page is opened by not logged user
    When Merchant Lead UI AUTOMATION LEAD MERCHANT I is opened
    Then On lead's merchant page user can see list of offers with all required elements(commission, title, description, earn button)

  @Regression @MerchantLead
  Scenario Outline: Lead page - earn(merchant button) used
    Given Points page is opened by <loginState> user
    And Merchant Lead UI AUTOMATION LEAD MERCHANT I is opened
    When Earn merchant button will be clicked by <loginState> user
    Then He will be redirected to transition page or retailer page according to login state <loginState>

    Examples:
      | loginState |
      | not logged |
      | logged     |

  @Regression @MerchantLead
  Scenario Outline: Lead page - earn(offer button) used
    Given Points page is opened by <loginState> user
    And Merchant Lead UI AUTOMATION LEAD MERCHANT I is opened
    When Earn offer button will be clicked by <loginState> user
    Then He will be redirected to transition page or retailer page according to login state <loginState>

    Examples:
      | loginState |
      | not logged |
      | logged     |