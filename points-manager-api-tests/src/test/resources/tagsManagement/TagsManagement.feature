Feature: Tags - create, get
  As a API client
  I want to be able to post new tag data, pull list of tags and pull details of chosen tag
  So that I will be able to create new tag in the system, list tags or get detailed info of chosen tag

  @Tags @PositiveCase
  @Regression
  Scenario Outline: Return tag by id - check correctness of contract and if returned data is as expected
    Given Points Manager API is responding to requests
    When Tag By tagKey request will be done with existing in the system '<tagKey>'
    Then Tag details data should be returned
    And '<otherTagFields>' will be as expected according to test data

  @qa @stag
    Examples:
      | tagKey              | otherTagFields                              |
      | epointsRegistration | 10000,ONCE,Completed epoints registration,1 |
      | epointsLogin        | 1,DAILY,logged into epoints,1               |

    # OtherFields are in order cap,frequency,description,autoConfirm

  @Tags @NegativeCase
  @Regression
  Scenario Outline: Return tag by id - check correctness response code and message for tag which not exists in the system
    Given Points Manager API is responding to requests
    When Tag By tagKey request will be done with not existing in the system '<tagKey>'
    Then Tag details data should not be returned
    And Response message and code should be as expected

  @qa @stag
    Examples:
      | tagKey                    |
      | notExistingInSystemTagKey |
      | epointsPersonalDetailss   |

  @Tags @PositiveCase
  @Regression
  @qa @stag
  Scenario: Return tag list from current user - compare tag details of one tag from list with data returned by tag by tagKey request
    Given Points Manager API is responding to requests
    And List of current client tags is pulled
    And Tags request response data has proper structure
    When Tag By tagKey request will be done with one of previous returned tagKeys
    Then Tag details returned by first request will be the same as in second request

  @Tags @PositiveCase
  @Regression
  @qa @stag
  Scenario: Create new tag - check if created tag is available on list of returned tags and all data is as expected
    Given Points Manager API is responding to requests
    And New tag is already created
    When List of current client tags is pulled
    Then New tag will be available on the list
    And New tag details on the list will be same as those posted during creation

  @Tags @NegativeCase
  @Regression
  @qa @stag
  Scenario: Create new tag - check if system properly responds when tried to create tag which exists in the system
    Given Points Manager API is responding to requests
    When New tag creation request will be done with tagKey which exists in system
    Then New tag will not be created
    And Response message and code should be as expected

  @Tags @PositiveCase
  @Regression
  @qa @stag
  Scenario: Create new tag - check if created tag can be found and returned by tag by tagKey request
    Given Points Manager API is responding to requests
    Given New tag is already created
    When Tag By tagKey request will be done for new created tagKey
    Then Tag details data should be returned
    And All returned tag details will be as expected according to created tag