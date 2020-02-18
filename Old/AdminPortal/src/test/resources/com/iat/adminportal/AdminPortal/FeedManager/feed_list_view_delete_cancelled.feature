Feature: View the Feed List - Deleting a Feed - Cancelled
  As an Admin user
  I require an option on the Feed List screen
  that will enable me to delete a feed

  Background:
    Given Admin user is successfully logged in

  Scenario Outline: View feed list
    Given Admin user is on Feed Manager page
    And He looks for record to be deleted
    And He clicks on a 'Delete' button next to a chosen feed
    When Admin chooses to cancel the delete action by pressing '<button>' button
    Then Feed is not deleted
    And Feed still has an active 'Delete' button on the Feed List
    And Feed status (Active/Deactivated) stays unchanged
    And 'Run' option is still enabled

    Examples:
      | button |
      | No     |
      | X      |