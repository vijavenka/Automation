Feature: Audit cms - reward-criteria management
  As Audit Admin user
  I want to be able to manage my rewards criteria
  To be able to awarding retailers

  @Regression @PositiveCase @rewardCriteria
  Scenario Outline: Check if it's possible to get lists of all available reward-criteria
    Given Audit admin user is logged in with credentials '<login_password>'
    When Get reward-criteria call is made
    Then Get reward-criteria call returns proper contract

  @qa
    Examples:
      | login_password |
      | admin,admin    |


  @Regression @PositiveCase @rewardCriteria
  @createDataBeforeRewardCriteriaTests
  Scenario Outline: Create new reward-criteria for already existing partner (supplier)
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create reward criteria call is made for following jsonData '<jsonData>', code '201'
    Then Create reward criteria call returns proper data
    And Tag was created for reward criteria in points-manager under proper partner

  @qa
    Examples:
      | login_password | jsonData                                                                                                                      |
      # product question type in rule
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             |
      # 2 products question type in rule (from same supplier)
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT and PRODUCT", "points": "RANDOM", "auditId": "previous_call" } |
      # 2 products question type in rule (from same supplier)
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT or PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  |
      # adhoc question type in rule -> IAT SUPPLIER by default
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               |
      # 2 adhoc question type in rule -> IAT SUPPLIER by default
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC and ADHOC", "points": "RANDOM", "auditId": "previous_call" }     |
      # adhoc & product question type in rule -> IAT SUPPLIER by default
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC or PRODUCT", "points": "RANDOM", "auditId": "previous_call" }    |


  @Regression @NegativeCase @rewardCriteria
  @createDataBeforeRewardCriteriaTests
  Scenario Outline: Create new reward-criteria for already existing partner (supplier)
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create reward criteria call is made for following jsonData '<jsonData>', code '<code>'
    Then Create reward criteria call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'

  @qa
    Examples:
      | login_password | jsonData                                                                                                                                                                                                                                                                                                                                                            | code | message                   | description                                               | fieldErrors                                                                                                                                                                                  |
      # 3 products question type in rule (2 from same supplier, 1 from another)
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT and PRODUCT or PRODUCT", "points": "RANDOM", "auditId": "previous_call" }                                                                                                                                                                                                                            | 422  | error.unprocessableEntity | More than one product supplier found for reward criteria: | null                                                                                                                                                                                         |
      # criteriaName - empty
      | admin,admin    | {"criteriaName": "", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }                                                                                                                                                                                                                                                                    | 400  | error.validation          | null                                                      | { "fieldErrors": [ {"objectName": "rewardCriteriaDTO", "field": "criteriaName", "message": "NotEmpty" }, { "objectName": "rewardCriteriaDTO", "field": "criteriaName", "message": "Size" }]} |
      # criteriaName - null
      | admin,admin    | {"criteriaName": null, "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }                                                                                                                                                                                                                                                                  | 400  | error.validation          | null                                                      | { "fieldErrors": [ {"objectName": "rewardCriteriaDTO", "field": "criteriaName", "message": "NotEmpty" }]}                                                                                    |
      # criteriaName - too long
      | admin,admin    | {"criteriaName": "q1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45o255more", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | 400  | error.validation          | null                                                      | {"fieldErrors": [{ "objectName": "rewardCriteriaDTO", "field": "criteriaName", "message": "Size"}]}                                                                                          |
      # criteriaName - duplicated
      | admin,admin    | {"criteriaName": "API_Manual", "criteriaRule": "PRODUCT and PRODUCT or PRODUCT", "points": "RANDOM", "auditId": "previous_call" }                                                                                                                                                                                                                                   | 400  | error.validation          | Reward Criteria with a name: API_Manual already exists    | {"fieldErrors": [{ "objectName": "RewardCriteria", "field": "criteriaName", "message": "is not unique"}]}                                                                                    |
      # criteriaRule - empty
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "", "points": "RANDOM", "auditId": "previous_call" }                                                                                                                                                                                                                                                          | 400  | error.validation          | null                                                      | { "fieldErrors": [ {"objectName": "rewardCriteriaDTO", "field": "criteriaRule", "message": "NotEmpty" }, { "objectName": "rewardCriteriaDTO", "field": "criteriaRule", "message": "Size" }]} |
      # criteriaRule - null
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": null, "points": "RANDOM", "auditId": "previous_call" }                                                                                                                                                                                                                                                        | 400  | error.validation          | null                                                      | { "fieldErrors": [ {"objectName": "rewardCriteriaDTO", "field": "criteriaRule", "message": "NotEmpty" }]}                                                                                    |
      # criteriaRule - not exist question id
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "1500100", "points": "RANDOM", "auditId": "previous_call" }                                                                                                                                                                                                                                                   | 400  | error.validation          | No question found for id: 1500100                         | { "fieldErrors": [{ "objectName": "Question", "field": "id", "message": "cannot be found" }]}                                                                                                |
      # points - empty
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "", "auditId": "previous_call" }                                                                                                                                                                                                                                                         | 400  | error.validation          | null                                                      | { "fieldErrors": [ { "objectName": "rewardCriteriaDTO", "field": "points", "message": "NotNull" }]}                                                                                          |
      # points - null
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": null, "auditId": "previous_call" }                                                                                                                                                                                                                                                       | 400  | error.validation          | null                                                      | { "fieldErrors": [ { "objectName": "rewardCriteriaDTO", "field": "points", "message": "NotNull" }]}                                                                                          |
      # points - below 0
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "-1500", "auditId": "previous_call" }                                                                                                                                                                                                                                                    | 400  | error.validation          | null                                                      | { "fieldErrors": [ { "objectName": "rewardCriteriaDTO", "field": "points", "message": "Min" }]}                                                                                              |
      # points - 0
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "0", "auditId": "previous_call" }                                                                                                                                                                                                                                                        | 400  | error.validation          | null                                                      | { "fieldErrors": [ { "objectName": "rewardCriteriaDTO", "field": "points", "message": "Min" }]}                                                                                              |
      # auditId - empty
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "" }                                                                                                                                                                                                                                                                | 400  | error.validation          | null                                                      | { "fieldErrors": [ { "objectName": "rewardCriteriaDTO", "field": "auditId", "message": "NotNull" }]}                                                                                         |
      # auditId - null
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": null }                                                                                                                                                                                                                                                              | 400  | error.validation          | null                                                      | { "fieldErrors": [ { "objectName": "rewardCriteriaDTO", "field": "auditId", "message": "NotNull" }]}                                                                                         |
      # auditId - not existing id
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "15001009000" }                                                                                                                                                                                                                                                     | 400  | error.validation          | Audit id not found15001009000                             | { "fieldErrors": [ { "objectName": "RewardCriteria", "field": "auditId", "message": "cannot be found" }]}                                                                                    |


  @Regression @NegativeCase
  @createDataBeforeRewardCriteriaTestMissingPartner
  Scenario Outline: Create new reward-criteria for NOT existing partner (supplier)
    Given Audit admin user is logged in with credentials '<login_password>'
    And Partner for related supplier 'previous_call' not exist in points-manager
    When Create reward criteria call is made for following jsonData '<jsonData>', code '201'
    Then Partner was created for fixed supplier 'previous_call'
    And Supplier 'previous_call' contains partner api key
    And Create reward criteria call returns proper data

  @qa
    Examples:
      | login_password | jsonData                                                                                                          |
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" } |