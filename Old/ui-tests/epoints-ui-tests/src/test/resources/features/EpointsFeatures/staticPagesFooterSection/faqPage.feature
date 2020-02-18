Feature: Epoints faq page
  As an epoints user
  I want to have faq page
  So that I could get answers for frequently asked questions

  # // 1.1 //  ------------------------------------------------------------------------------------------------ FAQ page
  # ------------------------------------------------------------------------------------------------------- page content
  @faqPage @staticPagesAndFooter
  Scenario: FAQ page - page content
    Given FAQ page is opened
    When User look on faq page
    Then He will see that it contains proper search
    And Section with frequently asked questions