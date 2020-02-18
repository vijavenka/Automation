Feature: Epoints checkout/complete order page
  As an epoints user
  I want to have checkout complete order summary page
  So that I could see details and confirmation of my order

  # Narrative & Acceptance Criteria:
  # Scenarios created to cover last page of the checkout section - final order page

  # // 1.1 //  -------------------------------------------------------------------------------- Checkout - complete page
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @CheckoutComplete
  @setHighEpointsValue
  Scenario Outline: Checkout - complete order page - content and details of the order
    Given Complete checkout page is opened with one product in it by logged user in '<partner>' context
    When User look on complete page
    Then He sees completed order page with confirmation message
    And Complete page redirect buttons will be displayed in '<partner>' context

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.2 //  -------------------------------------------------------------------------------- Checkout - complete page
  # ----------------------------------------------------------------------------------------------------------- faq link
  @Regression @CheckoutComplete
  Scenario Outline: Checkout - complete page - faq link
    Given Complete checkout page is opened with one product in it by logged user in '<partner>' context
    When User use faq link on complete page
    Then He will be redirected to epoints faq page

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.3 //  -------------------------------------------------------------------------------- Checkout - complete page
  # -------------------------------------------------------------------------------------------------- my account button
  @Regression @CheckoutComplete
  Scenario: Checkout - complete page - my account button fo epoints
    Given Complete checkout page is opened with one product in it by logged user in 'epoints' context
    When User click my account button on complete page
    Then He will be redirected to user account page
    And New redemption activity will be visible on activity list

  @Regression @CheckoutComplete
  Scenario: Checkout - complete page - my account button for united
    Given Complete checkout page is opened with one product in it by logged user in 'united' context
    When User click my account button on complete page
    Then He will be redirected to user activity page
    And New redemption activity will be visible on current balance list

  # // 1.4 //  -------------------------------------------------------------------------------- Checkout - complete page
  # ------------------------------------------------------------------------------------------------- get epoints button
  @Regression @CheckoutComplete
  Scenario: Checkout - complete page - get epoints button
    Given Complete checkout page is opened with one product in it by logged user in 'epoints' context
    When User click get epoints button on complete page
    Then He will be redirected to a-z page