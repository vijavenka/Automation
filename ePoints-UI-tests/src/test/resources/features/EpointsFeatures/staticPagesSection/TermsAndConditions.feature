Feature: Epoints terms and conditions page
  As an epoints user
  I want to have terms and conditions page
  So that I could get information about terms and conditions on epoints page

  # // 1.1 //  ------------------------------------------------------------------------------------ Terms and conditions
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @TermsAndConditionsPage
  Scenario: Terms and conditions - page content
    Given Terms and conditions page is opened
    When User looks on terms and conditions page
    Then He can see that terms and conditions page contains proper information

  # // 1.2 //  ------------------------------------------------------------------------------------ Terms and conditions
  # ------------------------------------------------------------------------------------------------------- epoints link
  @Regression @TermsAndConditionsPage
  Scenario: Terms and conditions - epoints link
    Given Terms and conditions page is opened
    When User use epoints link
    Then He will be redirected to epoints home page

  # // 1.3 //  ------------------------------------------------------------------------------------ Terms and conditions
  # ------------------------------------------------------------------------------------------------------- support link
  @Regression @TermsAndConditionsPage
  Scenario: Terms and conditions - support link
    Given Terms and conditions page is opened
    When User use support link
    Then Email tool will be opened