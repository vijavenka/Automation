Feature: Bucket List screen interface

  As a superuser
  I require a Redemption Items Landing page
  To be able to manage all buckets

  Background:
    Given Log in to admin portal as user 'i.superuser' with password 'theMightyBlues'
    And Admin portal home page is opened
    When He clicks on 'Redemption Manager' link


  Scenario:  Check if Bucket Manager List screen have all required elements
    Given Bucket List screen is opened properly
    When He looks at the Bucket List Table
    Then Table of buckets have proper columns
    And Edit option should be available for each bucket
    And Publish/Unpublish option should be available for each bucket
    And Delete option should be available for each bucket (except Default Home Bucket)
    And Create New option should be available for create new bucket
    And Update order option should be available for change buckets order

  Scenario Outline: Check if table with buckets list allow sort results by identified columns (Name, Tag, No of Items, Publish)
    Given Bucket List screen is opened properly
    When User clicks on <column> header
    Then Buckets in table should be sorted by <column> column

    Examples:
      | column            |
      | "Name"            |
      | "Tag"             |
      | "Number Of Items" |
      | "Display Order"   |
      | "Publish"         |

  Scenario: Check if bucket Name link go to bucket create/edit screen in view mode
    Given Bucket List screen is opened properly
    When User clicks on 'Default Home Bucket' link in NAME column
    Then Bucket create/edit screen is opened in view mode

  Scenario: Check if bucket Tag link go to bucket create/edit screen in view mode
    Given Bucket List screen is opened properly
    When User clicks on 'Home' link in TAG column
    Then Bucket create/edit screen is opened in view mode

  Scenario: Check if bucket 'Edit' link go to bucket create/edit screen in edit mode
    Given Bucket List screen is opened properly
    When User clicks on Home Bucket EDIT option
    Then Bucket create/edit screen is opened in edit mode

  Scenario: Check if bucket 'Create New' link go to bucket create/edit screen in edit mode
    Given Bucket List screen is opened properly
    When User clicks on 'Create New' bucket option
    Then Bucket create/edit screen is opened in create new mode

  Scenario: Check if 'No of Items' link go through to the redemption list screen
    Given Bucket List screen is opened properly
    When User clicks on Home Bucket NO_OF_ITEMS counter
    Then Redemption Items List Screen is opened