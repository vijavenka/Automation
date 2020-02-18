Feature: Epoints checkout/order summary page
  As an epoints user
  I want to have checkout/order summary page
  So that I could see summary of my order and delivery details

  # // 1.1 //  --------------------------------------------------------------------------- Checkout - order summary page
  # ------------------------------------------------------------------------------------------------------- page content
  @setHighEpointsValue @checkoutOrderSummaryPage @checkout
  Scenario: Checkout - order summary page - page content
    Given Order summary checkout page is opened with one product in it by logged user clear
    When User look on order summary page
    Then He will see that order summary page contains correct product
    And Total epoints cost is properly calculated
    And User will see that order summary page contains correct address
    And Order summary page contains navigation buttons

  # // 1.2 //  --------------------------------------------------------------------------- Checkout - order summary page
  # ------------------------------------------------------------------------------------------------ product edit button
  @checkoutOrderSummaryPage @checkout
  Scenario: Checkout - order summary page - product edit button
    Given Order summary checkout page is opened with one product in it by logged user clear
    When User click edit products button
    Then He will be redirected to selected rewards step

  # // 1.3 //  --------------------------------------------------------------------------- Checkout - order summary page
  # ------------------------------------------------------------------------------------------------ address edit button
  @checkoutOrderSummaryPage @checkout
  Scenario: Checkout - order summary page - address edit button
    Given Order summary checkout page is opened with one product in it by logged user clear
    When User click edit delivery address button
    Then He will be redirected to delivery details step

  # // 1.4 //  --------------------------------------------------------------------------- Checkout - order summary page
  # -------------------------------------------------------------------------------------------------------- back button
  @checkoutOrderSummaryPage @checkout
  Scenario: Checkout - order summary page - back button
    Given Order summary checkout page is opened with one product in it by logged user clear
    When User click back button on order summary page
    Then He will be redirected to delivery details step

  # // 1.5 //  --------------------------------------------------------------------------- Checkout - order summary page
  # ------------------------------------------------------------------------------------------------- place order button
  @checkoutOrderSummaryPage @checkout
  Scenario: Checkout - order summary page - place order button
    Given Order summary checkout page is opened with one product in it by logged user clear
    When User click place order button on order summary page
    Then He will be redirected to confirmation step
    And Order will be correctly placed
