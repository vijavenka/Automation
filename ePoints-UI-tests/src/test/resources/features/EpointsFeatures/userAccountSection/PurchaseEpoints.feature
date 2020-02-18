Feature: Epoints your account page purchase epoints tab
  As an epoints user
  I want to have purchase epoints section in your account page purchase epoints tab
  So that I could buy epoints using paypal system

  # // 1.1 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
  # -------------------------------------------------------------------------------------------------------- tab content
  # Update ------------------------------ EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
  @Regression @PurchaseEpointsPage @NBO-9934
  Scenario: EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142) - tab content
    Given Purchase epoints page is opened
    Then He will see that purchase epoints tab has all needed elements to buy epoints

  # // 1.2 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
  # -------------------------------------------------------------------------------------- epoints to pounds DDL content
  # Update ------------------------------ EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
  @Regression @PurchaseEpointsPage
  Scenario: EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142) - epoints to pounds DDL content
    Given Purchase epoints page is opened
    When User expand buy epoints ddl
    Then He will see it contains proper number of fixed values with proper values

  # // 1.4 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
  # --------------------------------------------------------------------- simulation of available rewards after purchase
  # Update ------------------------------ EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
  @Regression @PurchaseEpointsPage @NBO-9934
  Scenario: EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142) - simulation of available rewards after purchase
    Given Purchase epoints page is opened
    When User select number of epoints he want to buy
    Then Available items counter will be properly updated according to new points value

  # // 1.5 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
  # -------------------------------------------------------------------------------------------- 2nd screen page content
  # Update ------------------------------ EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
  @Regression @PurchaseEpointsPage
  Scenario: EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142) - 2nd screen page content
    Given Purchase epoints page is opened
    And Second confirmation screen is opened with some pound value to change on epoints
    When User look on buy epoints summary page
    Then He can see that page contains all needed element
    And All values are properly calculated on buy epoints summary page

  # // 1.6 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
  # ------------------------------------------------------------------------------------------- 2nd screen cancel button
  @Regression @PurchaseEpointsPage
  Scenario: EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142) - 2nd screen cancel button
    Given Purchase epoints page is opened
    And Second confirmation screen is opened with some pound value to change on epoints
    When User click cancel button on buy epoints summary page
    Then Summary page will be closed and user stay on purchase epoints screen

  # // 1.7 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
  # ------------------------------------------------------------------------------------------ 2nd screen confirm button
  @Regression @PurchaseEpointsPage
  Scenario: EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142) - 2nd screen confirm button
    Given Purchase epoints page is opened
    And Second confirmation screen is opened with some pound value to change on epoints
    When User click confirm button on buy epoints summary page
    Then New external paypal page will be opened

  # // 1.8 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
  # --------------------------------------------------------------------------------- 2nd screen manual redirection link
  @Regression @PurchaseEpointsPage
  Scenario: EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142) - 2nd screen manual redirection link
    Given Purchase epoints page is opened
    And Second confirmation screen is opened with some pound value to change on epoints
    When User click confirm button on buy epoints summary
    Then On basic epoints page proper redirection info will be displayed
    When User click manual redirection link
    Then Paypal page will be opened

  # // 1.9 //  -------------------------- EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217)
  # // 1.9 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
  # ---------------------------------------------------------------------------------------------------- payment failure
  @Regression @PurchaseEpointsPage
  Scenario: EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217/1218) - payment failure
    Given Purchase epoints page is opened
    And Second confirmation screen is opened with some pound value to change on epoints
    When User paymant fails from some reason
    Then On epoints page proper info will be displayed about payment fails
    When User click contact us link on failure message
    Then He will be redirected to help page

  # // 1.10 //  ------------------------- EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217)
  # // 1.10 //  --------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
  # ---------------------------------------------------------------------------------------------------- payment success
  @Regression @PurchaseEpointsPage
  Scenario: EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217/1218) - payment success
    Given Purchase epoints page is opened
    And Second confirmation screen is opened with some pound value to change on epoints
    When User payment was properly done
    Then On epoints page proper info will be displayed about payment success
    And Print button will be available
    And Points will be properly awarded to user epoints account
    When User click spend epoints link on success message
    Then He will be redirected to spend page
    When He back and click go to profile button
    Then He will be redirected to user profile page

#  paypall sandbox flow was changed again, separately tests works but in row not

#  # // 1.11 //  ------------------------- EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217)
#  # --------------------------------------------------------------------------------- correctness of data on paypal page
#  @Regression @PurchaseEpointsPage
#  Scenario: EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217) - correctness of data on paypal page
#    Given Purchase epoints page is opened
#    And Second confirmation screen is opened with some pound value to change on epoints
#    When User decide to buy some epoints and click confirm button
#    Then On paypal page he will see that all values are correct according to his request
#
#  # // 1.12 //  --------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
#  # ---------------------------------------------------------------------------------------------------- payment details
#  @Regression @PurchaseEpointsPage
#  Scenario: EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218) - payment details
#    Given Purchase epoints page is opened
#    And Second confirmation screen is opened with some pound value to change on epoints
#    And User bought some epoints value
#    When User look on page after redirect to epoints
#    Then He can see proper summary of his payment
