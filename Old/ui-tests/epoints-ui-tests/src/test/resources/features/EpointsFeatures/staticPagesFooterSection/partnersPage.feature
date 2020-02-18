Feature: Epoints partners page
  As an epoints user
  I want to have partners page
  So that I could leave business details to create partnership

  # // 1.1 //  ------------------------------------------------------------------------------------------- Partners page
  # ------------------------------------------------------------------------------------------------------- page content
  @partnersPage @staticPagesAndFooter
  Scenario: Partners page - page content
    Given Partners page is opened
    When User look on partners page
    Then He will se proper information for partners
    And Partner form will be displayed

  # // 1.2 //  ------------------------------------------------------------------------------------------- Partners page
  # ---------------------------------------------------------------------------------- partners form alert email invalid
  @partnersPage @staticPagesAndFooter
  Scenario: Partners page - partners form alert email invalid
    Given Partners page is opened
    When User enter invalid email value
    Then Email is invalid alert will be displayed

  # // 1.3 //  ------------------------------------------------------------------------------------------- Partners page
  # ------------------------------------------------------------------------------- partners form fields required alerts
  @partnersPage @staticPagesAndFooter
  Scenario: Partners page - partners form fields required alerts
    Given Partners page is opened
    When User enter mandatory data and delete it
    Then Under each mandatory field required alert will be displayed

  # // 1.4 //  ------------------------------------------------------------------------------------------- Partners page
  # -------------------------------------------------------------------------------- sending form, only mandatory fields
  @partnersPage @staticPagesAndFooter
  Scenario: Partners page - sending form, only mandatory fields
    Given Partners page is opened
    When User fill mandatory fields with his own proper data
    And Click submit partners form button
    Then Form will be sent and success alert will be displayed

  # // 1.5 //  ------------------------------------------------------------------------------------------- Partners page
  # ------------------------------------------------------------------------------------------- sending form, all fields
  @partnersPage @staticPagesAndFooter
  Scenario: Partners page - sending form, all fields
    Given Partners page is opened
    When User fill all fields with his own proper data
    And Click submit partners form button
    Then Form will be sent and success alert will be displayed

  # // 1.6.1 //  ------------------------------------------------- SPECSAVERS - create partner page for DESKTOP(NBO-746)
  # ---------------------------------------------------------------------------------------------------- specsavers page
  @partnersPage @staticPagesAndFooter
  Scenario: SPECSAVERS - create partner page for DESKTOP(NBO-746) - specsavers page
    Given Partners page is opened
    Given Specsavers partner page is opened
    When User look on specsavers partner page
    Then He will see proper text on specsavers partner page
    And He will see proper images on specsavers partner page

  # // 1.6.2 //  ------------------------------------------------- SPECSAVERS - create partner page for DESKTOP(NBO-746)
  # ------------------------------------------------------------------------------------------------------ about us link
  # Update ------------------------------------------------- SPECAVERS - update copy on partner page on desktop & mobile
  @partnersPage @staticPagesAndFooter
  Scenario: SPECSAVERS - create partner page for DESKTOP(NBO-746) - about us link
    Given Partners page is opened
    Given Specsavers partner page is opened
    When User click about specsavers link
    Then He will be redirected to external specsavers page