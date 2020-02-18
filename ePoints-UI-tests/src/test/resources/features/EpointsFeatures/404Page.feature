Feature: Epoints 404 page
  As an epoints user
  I want to have 404 page
  So that I could land on it when something go wrong on epoints.com

  # // 1.1 //  ------------------------------------------------------------------------------------------------ 404 page
  # -------------------------------------------------------------------------------------------------- content and links
  @Regression @404Page
  Scenario: 404 page - content and links
    Given User land on 404 page
    Then He is presented with error page information
    And He can use available rewards/points links

  @Regression @404Page
  Scenario: 404 united page - content and links
    When User land on 404 united page
    Then He is redirected to 404 page in epoints context