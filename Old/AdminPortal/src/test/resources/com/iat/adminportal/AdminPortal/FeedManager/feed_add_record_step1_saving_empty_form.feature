Feature: Create Feed Wizard - screen 1 - saving the form - empty form
  As an admin user
  I require an interface
  that will enable me to create a new feed

  Background:
    Given Admin user is successfully logged in

  Scenario: View feed list
    Given Admin user is on Feed Manager page
    And He clicks on the 'Create new' button
    And First page of 'Create New' Feed Manager is opened