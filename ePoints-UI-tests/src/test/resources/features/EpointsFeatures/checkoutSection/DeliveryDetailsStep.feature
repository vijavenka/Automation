Feature: Epoints checkout/delivery details page
  As an epoints user
  I want to have checkout/delivery details page
  So that I could select proper delivery address

  # // 1.1 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @CheckoutDeliveryDetails
  @setHighEpointsValue
  Scenario Outline: Checkout - delivery details page - page content
    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
    When User look on delivery details page
    Then He can see that delivery detail page contains address cards section
    And Delivery details page contains redemption selection section
    And Redemption selection section contains proper product and points values
    And Delivery details page contains delivery info section
    And Delivery details page contains navigation buttons

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.2 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ------------------------------------------------------------------------------ redemptions selection box edit button
  @Regression @CheckoutDeliveryDetails
  Scenario Outline: Checkout - delivery details page - redemptions selection box edit button
    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
    When User click edit button in redemptions selection box
    Then He will be redirected to selected rewards step

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.3 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # -------------------------------------------------------------------------------------------------------- back button
  @Regression @CheckoutDeliveryDetails
  Scenario Outline: Checkout - delivery details page - back button
    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
    When User click back button on delivery details page
    Then He will be redirected to selected rewards step

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.4 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # -------------------------------------------------------------------------------------------------------- next button
  @Regression @CheckoutDeliveryDetails
  Scenario Outline: Checkout - delivery details page - next button
    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
    When User click next button on delivery details page
    Then He will be redirected to order summary step

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.5 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # --------------------------------------------------------------------------- new address form content / cancel button
  @Regression @CheckoutDeliveryDetails
  Scenario Outline: Checkout - delivery details page - new address form content / cancel button
    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
    When User click add new address button
    Then New address form will be displayed
    When User click cancel button on new address form
    Then New addres form will be closed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.6 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ----------------------------------------------------------------------------------------------------- address finder
  @Regression @CheckoutDeliveryDetails
  Scenario Outline: Checkout - delivery details page - address finder
    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
    Given New address form is opened
    When User fill post code input field
    And Click find address button
    And User select some address from proposed addresses
    Then House number, street, town inputs will be automatically filled with data

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.7 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
  # ---------------------------------------------------------------------------------------------- alerts for UK country
  @Regression @CheckoutDeliveryDetails
  Scenario: DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565) - alerts for UK country
    Given Delivery details checkout page is opened with one product in it by logged user in 'epoints' context
    Given New address form is opened
    When User fill all new address form fields for country UK
    And Delete all data from new address form
    Then Fields required alerts will be displayed including post code field

  # // 1.8 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
  # ---------------------------------------------------------------------------------------------- alerts for IE country
  @Regression @CheckoutDeliveryDetails
  Scenario: DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565) - alerts for IE country
    Given Delivery details checkout page is opened with one product in it by logged user in 'epoints' context
    Given New address form is opened
    When User fill all new address form fields for country IE
    And Delete all data from new address form
    Then Fields required alerts will be displayed excluding post code field

  # // 1.9 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ---------------------------------------------------------------------------------- next button / remembering address
#  Switched off because we have limit of 20 new addresses which can be added to user account
#  @Regression @CheckoutDeliveryDetails
#  Scenario Outline: Checkout - delivery details page - next button / remembering address
#    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
#    Given New address form is opened
#    When User fill all new address form fields in '<partner>' context
#    And User click next button on delivery details page
#    And User click back button on order summary page
#    Then He will see that entered address data were remembered on address card
#
#    Examples:
#      | partner |
#      | epoints |
#      | united  |

  # // 1.9 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # --------------------------------------------------------------------------------- use as my contact details checkbox
  @Regression @CheckoutDeliveryDetails
  @setDefaultPersonalAndContactDataAfter
  Scenario Outline: Checkout - delivery details page - use as my contact details checkbox
    Given Delivery details checkout page is opened with one product in it by logged user in '<partner>' context
    Given New address form is opened
    When User fill all new address form fields in '<partner>' context
    And Select 'use as my contact details' checkbox
    And User click next button on delivery details page
    And User click back button on order summary page
    Then He can see that user contact data will be updated on first address card

    Examples:
      | partner |
      | epoints |
      | united  |