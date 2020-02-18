Feature: Epoints API: Rewards -> redemption items
  As ePoints user
  I want to be able to view all details of redemption Item


  @Regression @PositiveCase @RedemptionItemsDetails
  Scenario Outline: Rewards - product details contract validation
    Given Epoints API is responding to requests
    When Redemption product details are requested '<productSeoSlug>'
    Then Product details response data is same as in contract

  @qa
    Examples:
      | productSeoSlug                                                     |
      | wireless-optical-scroll-mouse/14c22b3a-1cb5-337e-92ee-66a7b9acf681 |

  @stag
    Examples:
      | productSeoSlug                                               |
      | britain-yesterday-today/633a54d9-9017-31a8-bd67-8492b3d3ecfe |


  @Regression @PositiveCase @RedemptionItemsRelated
  Scenario Outline: Rewards - related products contract validation
    Given Epoints API is responding to requests
    When Related redemption '<productSeoSlug>' products list is requested with params '0;5'
    Then Related redemption products response data is same as in contract

  @qa
    Examples:
      | productSeoSlug                                                                |
      | babystyle-oyster-a-lot-of-images-product/50c7ad08-58db-3339-9b24-446090278b00 |

  @stag
    Examples:
      | productSeoSlug                                               |
      | britain-yesterday-today/633a54d9-9017-31a8-bd67-8492b3d3ecfe |


  @Regression @PositiveCase @RedemptionItemsRecently
  @qa @stag
  Scenario: Rewards - recently redeemed on rewards home contract validation
    Given Epoints API is responding to requests
    When Recently redeemed products list is requested
    Then Recently redeemed products response data is same as in contract

  @Regression @PositiveCase @RedemptionItemsRecently
  @qa @stag
  Scenario Outline: Rewards - recently redeemed on department page contract validation
    Given Epoints API is responding to requests
    When Recently redeemed products list is requested for specified department '<departmentSeoSlug>'
    Then Recently redeemed products response data is same as in contract
    And Returned products are only from specified department '<departmentSeoSlug>'

    Examples:
      | departmentSeoSlug |
      | baby-and-child    |