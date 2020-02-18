Feature: Epoints spend page - search box with drop-down
  As an epoints user
  I want to have search box with departments drop-down
  So that I could search for interesting products across specified departments only

  #-----------------------------------------------------------------
  # JIRA Story: NBO-3357
  # Changes to search box functionality - drop-down field. Right now
  # it should be populated with departments from store categories
  # manager from admin-portal. "All departments" as default. If user
  # will go to department, drop-down should present name of department
  # for all categories levels.
  #
  #-----------------------------------------------------------------

  Scenario: Spend page - search box drop-down
    Given User is on epoints.com spend page
    And Search box with departments drop-down is available
    When User clicks on drop-down
    Then He should see departments sorted in alphabetical order
    And Departments should be taken from admin portal for correct country

  Scenario: Spend page - user navigates through departments
    Given user is on epoints.com spend page
    When He goes under any of the departments
    Then Search box drop-down should be switched to department name
    And Should stay for any category under that department
    And It should revert to "All departments" if user will come back to home page