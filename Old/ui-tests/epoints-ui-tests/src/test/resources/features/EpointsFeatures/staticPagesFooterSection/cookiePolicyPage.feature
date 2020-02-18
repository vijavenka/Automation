Feature: Epoints cookie policy page
  As an epoints user
  I want to have cookie policy page
  So that I could get information about cookie policy on epoints page

  # // 1.1 //  -------------------------------------------------------------------------------------- Cookie policy page
  # ------------------------------------------------------------------------------------------------------- page content
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @cookiePolicyPage @staticPagesAndFooter
  Scenario: Cookie policy - page content
    Given Cookie policy page is opened
    When User looks on cookie policy page
    Then He can see that cookie policy page contains proper information

  # // 1.2 //  -------------------------------------------------------------------------------------- Cookie policy page
  # --------------------------------------------------------------------------------------------- youronlinechoices link
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @cookiePolicyPage @staticPagesAndFooter
  Scenario: Cookie policy - youronlinechoices link
    Given Cookie policy page is opened
    When User click youronlinechoices link
    Then He will be redirected to youronlinechoices page

  # // 1.3 //  -------------------------------------------------------------------------------------- Cookie policy page
  # ----------------------------------------------------------------------------------------------- allaboutcookies link
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @cookiePolicyPage @staticPagesAndFooter
  Scenario: Cookie policy - allaboutcookies link
    Given Cookie policy page is opened
    When User click allaboutcookies link
    Then He will be redirected to allaboutcookies page