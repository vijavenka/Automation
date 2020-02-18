Feature: Delete bucket

  As a superuser
  I require a delete functionality
  Which will enable me to hard delete chosen bucket.

  Background:
    Given Log in to admin portal as user 'i.superuser' with password 'theMightyBlues'
    And Admin portal home page is opened
    When He clicks on 'Redemption Manager' link

  Scenario: Delete home bucket
    Given Bucket List screen is opened properly
    And Default Home Bucket is available
    And It is has display order set to 0
    When User tries to delete this bucket
    Then He is not able to do so because 'Delete' button is not available

  Scenario Outline: Delete bucket- confirmation pop-up
    Given Bucket List screen is opened properly
    And There is more than one Bucket available
    When User clicks on the 'Delete' button next to a <bucket>
    Then Confirmation pop-up is displayed
    And User is asked whether he wants to delete this bucket or not
    And 'Yes' and 'No' buttons are available
    And 'No' button is set as a default

    Examples:
      | bucket  |
      | "MTEST" |


  Scenario Outline: Delete bucket- cancel
    Given Bucket List screen is opened properly
    And There is more than one Bucket available
    And User clicks on the 'Delete' button next to a <bucket>
    And Confirmation pop-up is displayed
    When User clicks on <button>
    Then User stays on the Bucket Manager List
    And <bucket> is still displayed
    And 'Delete' button is still present next to a chosen <bucket>.

    Examples:
      | bucket  | button |
      | "MTEST" | "No"   |
      | "MTEST" | "X"    |

  Scenario Outline: Delete bucket- confirm
    Given Bucket List screen is opened properly
    And There is more than one Bucket available
    And User clicks on the 'Delete' button next to a <bucket>
    And Confirmation pop-up is displayed
    When User confirms to delete the bucket
    Then User stays on the Bucket Manager List
    Then <bucket> is no longer displayed on the Bucket Manager

    Examples:
      | bucket  |
      | "MTEST" |