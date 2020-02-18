Feature: View the Feed List - Deleting a Feed - Confirmed
  As an Admin user
  I require an option on the Feed List screen
  that will enable me to delete a feed

  Background:
    Given Admin user is successfully logged in

  Scenario: View feed list
    Given Admin user is on Feed Manager page
    And He looks for record to be deleted
    And He clicks on a 'Delete' button next to a chosen feed
    When Admin chooses to confirm the delete action by pressing 'Yes' button
    Then Feed is deleted
    And 'Delete' button disappears and the 'Deleted' text is shown next to a chosen feed on the Feed List
    And Feed becomes 'Deactivated'
    And 'Activate' button is no longer available for the record
    And 'Run' option is no longed available