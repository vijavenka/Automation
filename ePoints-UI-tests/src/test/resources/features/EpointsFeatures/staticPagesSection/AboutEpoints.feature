Feature: Epoints about epoints page
  As an epoints user
  I want to have about epoints page
  So that I could get some information about epoints page

  # // 1.1 //  -------------------------------------------------------------------------------------- About epoints page
  # ------------------------------------------------------------------------------------------- What is epoints? section
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @Regression @AboutEpointsPage
  Scenario: About epoints page - What is epoints? section
    Given About epoints page is opened
    When User click 'What is epoints?' link
    Then Page will be scrolled to 'What is epoints?' section
    And 'What is epoints?' section has proper content

  # // 1.2 //  -------------------------------------------------------------------------------------- About epoints page
  # ------------------------------------------------------------------------------------- So much choice section content
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @Regression @AboutEpointsPage
  Scenario: About epoints page - So much choice section content
    Given About epoints page is opened
    When User click 'So much choice' link
    Then Page will be scrolled to 'So much choice' section
    And 'So much choice' section has proper content

  # // 1.3 //  -------------------------------------------------------------------------------------- About epoints page
  # ------------------------------------------------------------------------------- So much choice section retailer link
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @Regression @AboutEpointsPage
  Scenario: About epoints page - So much choice section retailer link
    Given About epoints page is opened by logged user
    When User click one of retailer card
    Then He will be redirected to transition page

  # // 1.4 //  -------------------------------------------------------------------------------------- About epoints page
  # ---------------------------------------------------------------------------------------- Hassle-free rewards content
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @Regression @AboutEpointsPage
  Scenario: About epoints page - Hassle-free rewards content
    Given About epoints page is opened
    When User click 'Hassle-free rewards' link
    Then Page will be scrolled to 'Hassle-free rewards' section
    And 'Hassle-free rewards' section has proper content

  # // 1.5 //  -------------------------------------------------------------------------------------- About epoints page
  # --------------------------------------------------------------------------------------- Get involved section content
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @Regression @AboutEpointsPage
  Scenario: About epoints page - Get involved section content
    Given About epoints page is opened
    When User click 'Get involved' link
    Then Page will be scrolled to 'Get involved' section
    And 'Get involved' section has proper content

  # // 1.6 //  -------------------------------------------------------------------------------------- About epoints page
  # ------------------------------------------------------------------------- Get involved section submit a request link
  # Update ------------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
  @Regression @AboutEpointsPage
  Scenario: About epoints page - Get involved section submit a request link
    Given About epoints page is opened
    When User click get in touch button
    Then He will be redirected to submit a request page
