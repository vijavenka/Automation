Feature: Epoints contact us page
  As an epoints user
  I want to have contact us page
  So that I could submit a request to epoints team

  # // 1.1 //  ----------------------------------------------------------------------------------------- Contact us page
  # ------------------------------------------------------------------------------------------------------- page content
  @contactUsPage @staticPagesAndFooter
  Scenario: Contact us page - page content
    Given Contact us page is opened
    When User look at contact us page
    Then He will see that contact us page contains all needed elements to submit a request