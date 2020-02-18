Feature: Epoints privacy policy page
  As an epoints user
  I want to have privacy policy page
  So that I could get information about privacy policy on epoints page

  # // 1.1 //  ------------------------------------------------------------------------------------- Privacy policy page
  # ------------------------------------------------------------------------------------------------------- page content
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @privacyPolicyPage @staticPagesAndFooter
  Scenario: Privacy policy page - page content
    Given Privacy policy page is opened
    When User looks on privacy policy page
    Then He can see that privacy policy page contains proper information

  # // 1.2 //  ------------------------------------------------------------------------------------- Privacy policy page
  # ------------------------------------------------------------------------------------------------- google policy link
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @privacyPolicyPage @staticPagesAndFooter
  Scenario: Privacy policy page - google policy link
    Given Privacy policy page is opened
    When User click google policy link
    Then He will be redirected to google privacy policy page