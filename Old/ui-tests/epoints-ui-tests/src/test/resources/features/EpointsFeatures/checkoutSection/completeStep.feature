Feature: Epoints checkout/complete page
  As an epoints user
  I want to have checkout/complete page
  So that I could see that my order went good

  # // 1.1 //  -------------------------------------------------------------------------------- Checkout - complete page
  # ------------------------------------------------------------------------------------------------------- page content
  @setHighEpointsValue @checkoutCompletePage @checkout
  Scenario: Checkout - complete page - page content
    Given Complete checkout page is opened by logged user clear
    When User look on complete page
    Then He can see order confirmation message
    And Complete page redirect buttons will be displayed

  # // 1.2 //  -------------------------------------------------------------------------------- Checkout - complete page
  # ----------------------------------------------------------------------------------------------------------- faq link
  @checkoutCompletePage @checkout
  Scenario: Checkout - complete page - faq link
    Given Complete checkout page is opened by logged user clear
    When User use faq link on complete page
    Then He will be redirected to epoints support page

  # // 1.3 //  -------------------------------------------------------------------------------- Checkout - complete page
  # -------------------------------------------------------------------------------------------------- my account button
  @checkoutCompletePage @checkout
  Scenario: Checkout - complete page - my account button
    Given Complete checkout page is opened by logged user clear
    When User click my account button on complete page
    Then He will be redirected to user account page

  # // 1.4 //  -------------------------------------------------------------------------------- Checkout - complete page
  # ------------------------------------------------------------------------------------------------- get epoints button
  @checkoutCompletePage @checkout
  Scenario: Checkout - complete page - get epoints button
    Given Complete checkout page is opened by logged user clear
    When User click get epoints button on complete page
    Then He will be redirected to a-z page