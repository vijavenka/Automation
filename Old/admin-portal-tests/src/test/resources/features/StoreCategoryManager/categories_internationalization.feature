Feature: Admin Portal - Store Categories Manager - Internationalization

  As a user
  I want to have option to pick a country  for Store Categories Manager
  So that I will be able to configure Department and Categories for each country separately

  Background: Login user
    Given User is logged to the Admin Portal

  @Basic, @Regression
  @High
  Scenario: CATEGORIES MANAGER - Default view UK
    When User clicks on Store Categories Manager link in menu
    Then Store Categories page is opened
    And Categories tree is displayed for UK

  @Basic, @Regression
  @High
  Scenario Outline: CATEGORIES MANAGER - Switching countries
    Given User is on Store Categories Manager page
    When User picks '<country>'
    Then Categories are loaded and displayed for picked country
    And Cookie is set for region="'<country>'"

    Examples:
      | country                     |
      | 'NL, SE, DK, FI, NO, UK'    |

  @Manual, @Acceptance
  @Medium
  Scenario Outline: CATEGORIES MANAGER - popup menu with generic options for all countries
    Given User is on Store Categories Manager page
    When User picks '<country>'
    And User clicks on any node item
    Then Popup menu with generic '<options>' is displayed

    Examples:
      | country                     | options                                                             |
      | 'NL, SE, DK, FI, NO, UK'    | "View", "Edit", "Remove", "Publish/Unpublish", "Add child category" |

  @Manual, @Integration
  @High
  Scenario Outline: CATEGORIES MANAGER - add new category for country
    Given User is on Store Categories Manager page
    And User picks '<country>'
    And User click on any node to display menu
    When User creates new '<category>' by filling form and clicking Save button
    Then New '<category>' is created in database
    And Category is visible in tree as unpublished
    And All fields are saved to database
    And ParentId is set to correct parrent
    And Region is set to '<country>'

    Examples:
      | country | category |
      | UK      | Desks    |
      | NL      | Books    |
      | NL      | Sport    |

  @Manual, @Integration
  @High
  Scenario: CATEGORIES MANAGER - adding the same category to more than one country
    Given User is on Store Categories Manager page
    When User creates category for one country
    And Later creates same category for second country
    Then Categories are created in correct regions
    And Categories are visible only for own categories trees