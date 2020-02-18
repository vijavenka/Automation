Feature: Network Manager List View

  Background:
    Given Admin user is successfully logged in

  Scenario: General view on network list
    Given Admin user is going to Network Manager page
    When Network Manager is loaded correctly
    Then He should be able to see network list with column names
      | header |
      | ID     |
      | Name   |
      | Active |
      | Stats  |
      | Edit   |
    And Manager should contain View All Network Transactions and Create New buttons
    And For each item action buttons should be available
      | button     |
      | Activate   |
      | Deactivate |
      | Stats      |
      | Edit       |