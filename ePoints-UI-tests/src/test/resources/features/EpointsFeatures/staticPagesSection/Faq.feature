Feature: Epoints faq page
  As an epoints user
  I want to have faq page
  So that I could get answers for frequently asked questions

  # // 1.1 //  ------------------------------------------------------------------------------------------------ FAQ page
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @FaqPage
  Scenario: FAQ page - page content
    Given FAQ page is opened
    When User look on faq page
    Then He will see that it contains section with frequently asked questions
    And Number of questions and answers is the same