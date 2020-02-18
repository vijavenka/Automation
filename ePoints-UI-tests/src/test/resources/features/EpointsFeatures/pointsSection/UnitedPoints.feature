Feature: United points page
  As an united epoints user
  I want to have Points page
  So that I could get information about current epoints rewards for buying specific products in stock

  @Regression @UnitedEarnPage
  Scenario: Points page - check content of points page
    When United points page is opened by logged user
    Then User will be presented with "Discover rewards" banner
    And User will be presented with list of latest deals
    And User will be presented with popular brands