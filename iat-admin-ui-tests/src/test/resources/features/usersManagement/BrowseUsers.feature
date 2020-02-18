Feature: Find employee
  As an administrator
  I want to have Find employee page
  So that I will be able to see whole list of users below my department

  Background: User is logged with users management permission and go to Find employee page
    Given User with a certain users management permissions is signed in to iat
    And Find employee page is opened

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario Outline: Find employee - general view
    Then He can see a users table with proper columns '<columnsNames>'
    But The table is empty
    And Each of the users table columns is sortable
    And Pagination component is available on find employee page
    And Filter component is available on find employee page

    Examples:
      | columnsNames                                                              |
      | Date added,User details,Department,Manager,Employee number,Status,Actions |

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  @NBO-10044
  Scenario Outline: Find employee - filtering
    When He provides some filter data on find employee page '<inFilterOption>'
    Then Users table shows proper elements according to the chosen filter '<inFilterOption>'
    And They are ordered by the added date

    Examples:
      | inFilterOption |
      | User           |
      | Status         |
      | Manager        |
      | Department     |

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Find employee - pagination
    When User changes the pages on find employee page
    And Moves around with pagination on find employee page
    Then Chosen page results are displayed on find employee page
    And Sorting settings are applied to consecutive pages

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario Outline: Find employee - sorting
    When User use '<sortOption>' sorting option for find employee table '<column>'
    Then Users table results will be correctly sorted according to selected column sort option '<column>', '<sortOption>'

    Examples:
      | column     | sortOption |
      | Date added | ascending  |
#      | Date added      | descending |
##      | User details    | ascending  |
##      | User details    | descending |
#      | Department      | ascending  |
#      | Department      | descending |
##      | Manager         | ascending  |
##      | Manager         | descending |
#      | Employee number | ascending  |
#      | Employee number | descending |
##      | Role            | ascending  |
##      | Role            | descending |
#      | Status          | ascending  |
#      | Status          | descending |

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Find employee - deleted users rows
    When Deleted users will be filtered
    Then There are active "edit" buttons displayed for this records

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Find employee - edit button click
    When User click edit button of chosen user
    Then He will be redirected to edit page of chosen user

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Filter  - date range filter
    And There is some find employee data available in the table
    When User selects the date range on find employee page
    And User clicks search button on find employee page
    Then Data is properly filtered by the date on find employee page

  @Regression @FindEmployee
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Filter  - clear button
    And There is some find employee data available in the table
    And User selects the date range on find employee page
    And User clicks search button on find employee page
    When User click clear filters button on find employee page
    Then The initial find employee data set is displayed