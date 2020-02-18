Feature: View the Feed List

  Background:
    Given Admin user is successfully logged in

  Scenario: View feed list
    Given Admin user is on Feed Manager page
    And List view components is available with column names
      | header  |
      | ID      |
      | Name    |
      | Network |
      | Active  |
      | Delete  |
      | Run     |
      | Stats   |
      | Edit    |
    And View list component is filled with data
    When He is looking at the displayed table
    Then He is seeing the feeds on the list in alphabetical order (ID)
    And List should contain 10 items by default
    And For each item action buttons Activate/Deactivatem Utilities and Edit should be available
    And ID and Name should be hiperlined to Edit/View form