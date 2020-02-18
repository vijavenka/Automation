Feature: Epoints checkout/delivery details page
  As an epoints user
  I want to have checkout/delivery details page
  So that I could select proper delivery address

  # // 1.1 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ------------------------------------------------------------------------------------------------------- page content
  @setHighEpointsValue @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - page content
    Given Delivery details checkout page is opened with one product in it by logged user clear
    When User look on delivery details page
    Then He can sse that delivery detail page contains address cards section
    And First address card is filled with contact data
    And Delivery details page contains redemption selection section
    And Redemption selection section contains proper product and points values
    And Delivery details page contains delivery info section
    And Delivery details page contains navigation buttons

  # // 1.2 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ------------------------------------------------------------------------------ redemptions selection box edit button
  @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - redemptions selection box edit button
    Given Delivery details checkout page is opened with one product in it by logged user clear
    When User click edit button in redemptions selection box
    Then He will be redirected to selected rewards step

  # // 1.3 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # -------------------------------------------------------------------------------------------------------- back button
  @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - back button
    Given Delivery details checkout page is opened with one product in it by logged user clear
    When User click back button on delivery details page
    Then He will be redirected to selected rewards step

  # // 1.4 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # -------------------------------------------------------------------------------------------------------- next button
  @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - next button
    Given Delivery details checkout page is opened with one product in it by logged user clear
    When User click next button on delivery details page
    Then He will be redirected to order summary step

  # // 1.5 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # --------------------------------------------------------------------------- new address form content / cancel button
  @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - new address form content / cancel button
    Given Delivery details checkout page is opened with one product in it by logged user clear
    When User click add new address button
    Then New address form will be displayed
    When User click cancel button on new address form
    Then New addres form will be closed

  # // 1.6 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ----------------------------------------------------------------------------------------------------- address finder
  @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - address finder
    Given Delivery details checkout page is opened with one product in it by logged user clear
    Given New address form is opened
    When User fill post code input field
    And Click find address button
    And User select some address from proposed addresses
    Then House number, street, town inputs will be automatically filled with data

  # // 1.7 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
  # ---------------------------------------------------------------------------------------------- alerts for UK country
  @checkoutDeliveryDetailsPage @checkout
  Scenario: DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565) - alerts for UK country
    Given Delivery details checkout page is opened with one product in it by logged user clear
    Given New address form is opened
    When User fill all new address form fields for country UK
    And Delete all data from new address form
    Then Fields required alerts will be displayed including post code field

  # // 1.8 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
  # ---------------------------------------------------------------------------------------------- alerts for IE country
  @checkoutDeliveryDetailsPage @checkout
  Scenario: DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565) - alerts for IE country
    Given Delivery details checkout page is opened with one product in it by logged user clear
    Given New address form is opened
    When User fill all new address form fields for country IE
    And Delete all data from new address form
    Then Fields required alerts will be displayed excluding post code field

  # // 1.9 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # ---------------------------------------------------------------------------------- next button / remembering address
  @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - next button / remembering address
    Given Delivery details checkout page is opened with one product in it by logged user clear
    Given New address form is opened
    When User fill all new address form fields
    And User click next button on delivery details page
    And User click back button on order summary page
    Then He will see that entered address data were remembered on address card

  # // 1.9 //  ------------------------------------------------------------------------ Checkout - delivery details page
  # --------------------------------------------------------------------------------- use as my contact details checkbox
  @setDefaultPersonalAndContactDataAfter @checkoutDeliveryDetailsPage @checkout
  Scenario: Checkout - delivery details page - use as my contact details checkbox
    Given Delivery details checkout page is opened with one product in it by logged user clear
    Given New address form is opened
    When User fill all new address form fields
    And Select 'use as my contact details' checkbox
    And User click next button on delivery details page
    Then User contact data will be updated