Feature: Epoints checkout/selected rewards page
  As an epoints user
  I want to have checkout/selected rewards page
  So that I could see selected rewards, change quantity and see total cost of selected redemptions

  # // 1.1 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @CheckoutSelectedRewards
  @setDefaultPersonalAndContactDataBefore @setHighEpointsValue
  Scenario: Checkout - selected rewards page - page content
    Given Selected rewards checkout page is opened with one product in it by not logged user in 'epoints' context
    When User look on selected rewards page
    Then He can see that it contains all item card elements
    And Summary row is properly displayed
    And Selected rewards page navigation buttons are properly displayed

  # // 1.2 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # ------------------------------------------------------------------------------------ working of order rewards button
  @Regression @CheckoutSelectedRewards
  Scenario Outline: Checkout - selected rewards page - working of order rewards button
    Given Selected rewards checkout page is opened with one product in it by logged user in '<partner>' context
    When User click order rewards Button
    Then He will be redirected to delivery details step

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.3 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # ---------------------------------------------------------------------------------- working of back to rewards button
  @Regression @CheckoutSelectedRewards
  Scenario: Checkout - selected rewards page - working of back to rewards button
    Given Selected rewards checkout page is opened with one product in it by not logged user in '<partner>' context
    When User click back to rewards button
    Then He will be redirected to browse rewards page

  # // 1.4 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # ------------------------------------------------------------------------------------------- increase/decrease button
  @Regression @CheckoutSelectedRewards
  Scenario: Checkout - selected rewards page - increase/decrease button
    Given Selected rewards checkout page is opened with one product in it by not logged user in '<partner>' context
    When User click increase quantity button
    Then Product number will be increased
    And Total cost will be recalculated
    When User click decreased quantity button
    Then Product number will be decreased
    And Total cost will be recalculated

  # // 1.5 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # ---------------------------------------------------------------------------------------------- delete single product
  @Regression @CheckoutSelectedRewards
  Scenario: Checkout - selected rewards page - delete single product
    Given Selected rewards checkout page is opened with two product in it by not logged user in '<partner>' context
    When User click remove button of chosen product
    And Confirm deletion
    Then Chosen product will be deleted from basket

  # // 1.6 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # ------------------------------------------------------------------------------------------------- delete all product
  @Regression @CheckoutSelectedRewards
  Scenario: Checkout - selected rewards page - delete all product
    Given Selected rewards checkout page is opened with two product in it by not logged user in '<partner>' context
    When User click remove all products
    And Confirm deletion of all product from basket
    Then All products will be removed from basket

  # // 1.7 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # -------------------------------------------------------------------------------------------- cancel deleting product
  @Regression @CheckoutSelectedRewards
  Scenario: Checkout - selected rewards page - cancel deleting product
    Given Selected rewards checkout page is opened with two product in it by not logged user in '<partner>' context
    When User click remove all products
    Then Delete popup will be displayed
    When User refuse of deleting product from basket
    Then All product will stay in basket

  # // 1.8 //  ------------------------------------------------------------------------ Checkout - selected rewards page
  # --------------------------------------------------------------------------------------------- low points number info
  @Regression @CheckoutSelectedRewards
  @setLowEpointsValue
  Scenario Outline: Checkout - selected rewards page - low points number info
    Given Selected rewards checkout page is opened with one product in it by logged user in '<partner>' context
    When User look on selected rewards page
    Then He will see that his points number is to low to redeem selected item

    Examples:
      | partner |
      | epoints |
      | united  |