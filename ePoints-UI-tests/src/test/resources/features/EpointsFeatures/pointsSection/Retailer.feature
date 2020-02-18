Feature: Epoints retailer page
  As an epoints user
  I want to have Retailer page
  So that I could as not logged user read retailer description before being redirected to retailer page

  Background:
    Given Points page is opened by not logged user

  # // 1.1 //  ------------------------------------------------------------------------------------------- Retailer page
  # -------------------------------------------------------------------------------- check availability of retailer page
  @regression @RetailerPage
  Scenario: Retailer page - check availability of retailer page
    Given Selected retailer page is opened
    Then Retailer Name is displayed on retailer page
    And Retailer description is displayed on retailer page
    And Epoints multiplier is displayed on retailer page
    And Join epoints link is displayed
    And Watch video button is displayed
    And Go to retailer button is displayed
    And Breadcrumb last level show retailer name

  # // 1.2 //  ------------------------------------------------------------------------------------------- Retailer page
  # ---------------------------------------------------------------------------------------------------------- join link
  @regression @RetailerPage
  Scenario: Retailer page - join link
    Given Selected retailer page is opened
    When User click join button on retailer page
    Then He will be redirected to join page

  # // 1.3 //  ------------------------------------------------------------------------------------------- Retailer page
  # ------------------------------------------------------------------------------------------------------- video player
  @regression @RetailerPage
  Scenario: Retailer page - video player
    Given Selected retailer page is opened
    When User click watch video button on retailer page
    Then Video player will be displayed
    And Video player can be closed by using close button

  @regression @RetailerPage
  Scenario: Retailer page - video player
    And Selected retailer page is opened
    When User click 'go to retailer button'
    Then He will be redirected to retailer page or transition page according to login state not logged

  @regression @RetailerPage
  Scenario: Retailer page - go to retailer button
    And Selected retailer page is opened
    And User login to his account as epoints only user
    When User click 'go to retailer button'
    Then He will be redirected to retailer page or transition page according to login state logged