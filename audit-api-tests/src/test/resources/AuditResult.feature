Feature: Audit cms - provide audit results file
  As Audit Admin user
  I want to be able to provide audit answers file
  To be able to awarding retailers for audit


  @Regression @PositiveCase @auditResults
  @createDataBeforeRewardCriteriaTests @deleteDataForAuditResultsTests
  Scenario Outline: upload audit results file - stores without retailers in system -> check if proper data stored in answers, reward_points
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create reward criteria call is made for following jsonData '<rewardCriteriaJsonBody>', code '201'
    And Generate unique stores ExtRelIds for '<partnerShortName>' audit results file '<fileName>'
    And Stores with ext_rel_id are already in system for partner '<partnerShortName>'
    And Create AuditCriteria for test
    When Audit result call is made for data '<fileName>', '<partnerShortName>'
    And Extract answers from '<partnerShortName>' auditResults file '<fileName>'
    Then Answers were properly stored for audit for '<partnerShortName>'
    And Reward points was properly stored for '<partnerShortName>' audit when stores 'without' retailers

  @qa
    Examples:
      | login_password | rewardCriteriaJsonBody                                                                                                        | fileName                           | partnerShortName |
      # TODAYS
      # product questions (availability) answer = Yes, No, other
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | todays_audit_results_1.xlsx        | TodaysRetailers  |
      # Q = Yes AND Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_3.xlsx        | TodaysRetailers  |
      # Q = Yes OR Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_3.xlsx        | TodaysRetailers  |
      # Q = Yes AND Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_4.xlsx        | TodaysRetailers  |
      # Q = Yes OR Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_4.xlsx        | TodaysRetailers  |
      # Q = No AND Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_5.xlsx        | TodaysRetailers  |
      # Q = No OR Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_5.xlsx        | TodaysRetailers  |
      # adhoc questions (descriptive) -> Q = Yes, no, email
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | todays_audit_results_2.xlsx        | TodaysRetailers  |
      # PREMIER
      # product questions (availability) answer = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1a.xlsx | PremierRetailers |
      # product questions (availability) answer = ranged and available
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1b.xlsx | PremierRetailers |
      # product questions (availability) answer = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1c.xlsx | PremierRetailers |
      # Q = Yes AND Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_3.xlsx  | PremierRetailers |
      # Q = Yes OR Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_3.xlsx  | PremierRetailers |
      # Q = Yes AND Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_4.xlsx  | PremierRetailers |
      # Q = Yes OR Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_4.xlsx  | PremierRetailers |
      # Q = No AND Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_5.xlsx  | PremierRetailers |
      # Q = No OR Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_5.xlsx  | PremierRetailers |
      # adhoc questions (descriptive) -> Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2a.xlsx | PremierRetailers |
      # adhoc questions (descriptive) -> Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2b.xlsx | PremierRetailers |
      # adhoc questions (descriptive) -> Q = email
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2c.xlsx | PremierRetailers |
      # NISA
      # product questions (availability) answer = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1a.xlsx | nisaRetailers    |
      # product questions (availability) answer = ranged and available
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1b.xlsx | nisaRetailers    |
      # product questions (availability) answer = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1c.xlsx | nisaRetailers    |
      # Q = Yes AND Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_3.xlsx  | nisaRetailers    |
      # Q = Yes OR Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_3.xlsx  | nisaRetailers    |
      # Q = Yes AND Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_4.xlsx  | nisaRetailers    |
      # Q = Yes OR Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_4.xlsx  | nisaRetailers    |
      # Q = No AND Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_5.xlsx  | nisaRetailers    |
      # Q = No OR Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_5.xlsx  | nisaRetailers    |
      # adhoc questions (descriptive) -> Q = Yes
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2a.xlsx | nisaRetailers    |
      # adhoc questions (descriptive) -> Q = No
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2b.xlsx | nisaRetailers    |
      # adhoc questions (descriptive) -> Q = email
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2c.xlsx | nisaRetailers    |


  @Regression @PositiveCase @auditResults
  @createDataBeforeRewardCriteriaTests @deleteDataForAuditResultsTests
  Scenario Outline: upload audit results file - stores with retailers in system -> check if proper data stored in answers, reward_points, points manager awarded
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create reward criteria call is made for following jsonData '<rewardCriteriaJsonBody>', code '201'
    And Generate unique stores ExtRelIds for '<partnerShortName>' audit results file '<fileName>'
    And Update stores ExtRelIds for '<partnerShortName>' import retailers file '<importRetFileName>' to be the same as in audit results file and trigger processing
    And Store retailer's epoints balance is known before process audit results
    And Create AuditCriteria for test
    When Audit result call is made for data '<fileName>', '<partnerShortName>'
    And Extract answers from '<partnerShortName>' auditResults file '<fileName>'
    Then Answers were properly stored for audit for '<partnerShortName>'
    And Reward points was properly stored for '<partnerShortName>' audit when stores 'with' retailers
    And Retailer's epoints balance updated after process audit results
    And Retailer awarded in points-manager with proper details

  @qa
    Examples:
      | login_password | rewardCriteriaJsonBody                                                                                            | fileName                    | importRetFileName                               | partnerShortName |
      # TODAYS
      # product questions answer = Yes, No, other
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_1.xlsx | importRetailers//todays_import_retailers_1.xlsx | TodaysRetailers  |
#       # Q = Yes AND Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_3.xlsx        | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = Yes OR Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_3.xlsx        | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = Yes AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_4.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = Yes OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_4.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = No AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_5.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = No OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_5.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # adhoc questions (descriptive) -> Q = Yes, no, email
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | todays_audit_results_2.xlsx        | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # PREMIER
#      # product questions (availability) answer = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1a.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # product questions (availability) answer = ranged and available
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1b.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # product questions (availability) answer = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1c.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes AND Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_3.xlsx  | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes OR Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_3.xlsx  | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_4.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_4.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = No AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_5.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = No OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_5.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # adhoc questions (descriptive) -> Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2a.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # adhoc questions (descriptive) -> Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2b.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # adhoc questions (descriptive) -> Q = email
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2c.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#       # NISA
#      # product questions (availability) answer = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1a.xlsx | importRetailers//premier_import_retailers_1.xlsx | nisaRetailers    |
#      # product questions (availability) answer = ranged and available
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1b.xlsx | importRetailers//premier_import_retailers_1.xlsx | nisaRetailers    |
#      # product questions (availability) answer = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1c.xlsx | importRetailers//premier_import_retailers_1.xlsx | nisaRetailers    |
#      # Q = Yes AND Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_3.xlsx  | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = Yes OR Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_3.xlsx  | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = Yes AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_4.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = Yes OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_4.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = No AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_5.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = No OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_5.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#     # adhoc questions (descriptive) -> Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2a.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # adhoc questions (descriptive) -> Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2b.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # adhoc questions (descriptive) -> Q = email
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2c.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |


  @Regression @PositiveCase @auditResults
  @createDataBeforeRewardCriteriaTests @deleteDataForAuditResultsTests
  Scenario Outline: upload audit results file - stores without retailers in system -> check if proper data stored in answers, reward_points -> importRetailers -> points manager awarded
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create reward criteria call is made for following jsonData '<rewardCriteriaJsonBody>', code '201'
    And Generate unique stores ExtRelIds for '<partnerShortName>' audit results file '<fileName>'
    And Stores with ext_rel_id are already in system for partner '<partnerShortName>'
    And Update stores ExtRelIds for import retailers file '<importRetFileName>' to be the same as in audit results file
    And Create AuditCriteria for test
    And Audit result call is made for data '<fileName>', '<partnerShortName>'
    And Extract answers from '<partnerShortName>' auditResults file '<fileName>'
    And Answers were properly stored for audit for '<partnerShortName>'
    And Reward points was properly stored for '<partnerShortName>' audit when stores 'without' retailers
    When Import retailers call is made for upload retailers data (file: '<importRetFileName>', partner: '<partnerShortName>', code: '200')
    And Store retailers bulk load properly add retailer
    Then Reward points was properly stored for '<partnerShortName>' audit when stores 'with' retailers
    And Retailer's epoints balance is proper after importRetailers when there was some rewardPoints
    And Retailer awarded in points-manager with proper details

  @qa
    Examples:
      | login_password | rewardCriteriaJsonBody                                                                                            | fileName                    | importRetFileName                               | partnerShortName |
            # TODAYS
      # product questions answer = Yes, No, other
      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_1.xlsx | importRetailers//todays_import_retailers_1.xlsx | TodaysRetailers  |
       # Q = Yes AND Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_3.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = Yes OR Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_3.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = Yes AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_4.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = Yes OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_4.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = No AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | todays_audit_results_5.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # Q = No OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | todays_audit_results_5.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
#      # adhoc questions (descriptive) -> Q = Yes, no, email
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | todays_audit_results_2.xlsx       | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
##      # PREMIER
#      # product questions (availability) answer = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1a.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # product questions (availability) answer = ranged and available
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1b.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # product questions (availability) answer = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" }             | premier_nisa_audit_results_1c.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes AND Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_3.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes OR Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_3.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_4.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = Yes OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_4.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = No AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_5.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # Q = No OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_5.xlsx | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers |
#      # adhoc questions (descriptive) -> Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2a.xlsx  | importRetailers//premier_import_retailers_1.xlsx| PremierRetailers |
#      # adhoc questions (descriptive) -> Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2b.xlsx  | importRetailers//premier_import_retailers_1.xlsx| PremierRetailers |
#      # adhoc questions (descriptive) -> Q = email
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2c.xlsx  | importRetailers//premier_import_retailers_1.xlsx| PremierRetailers |
#       # NISA
#      # product questions (availability) answer = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_1a.xlsx | importRetailers//premier_import_retailers_1.xlsx | nisaRetailers    |
#      # product questions (availability) answer = ranged and available
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_1b.xlsx | importRetailers//premier_import_retailers_1.xlsx | nisaRetailers    |
#      # product questions (availability) answer = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_1c.xlsx | importRetailers//premier_import_retailers_1.xlsx | nisaRetailers    |
#      # Q = Yes AND Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_3.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = Yes OR Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_3.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = Yes AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_4.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = Yes OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_4.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = No AND Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT AND PRODUCT", "points": "RANDOM", "auditId": "previous_call" } | premier_nisa_audit_results_5.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#      # Q = No OR Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "PRODUCT OR PRODUCT", "points": "RANDOM", "auditId": "previous_call" }  | premier_nisa_audit_results_5.xlsx | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    |
#     # adhoc questions (descriptive) -> Q = Yes
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2a.xlsx | importRetailers//nisa_import_retailers_1.xlsx | nisaRetailers    |
#      # adhoc questions (descriptive) -> Q = No
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2b.xlsx | importRetailers//nisa_import_retailers_1.xlsx | nisaRetailers    |
#      # adhoc questions (descriptive) -> Q = email
#      | admin,admin    | {"criteriaName": "API_AUDIT_CMS_CR_", "criteriaRule": "ADHOC", "points": "RANDOM", "auditId": "previous_call" }               | premier_nisa_audit_results_2c.xlsx | importRetailers//nisa_import_retailers_1.xlsx | nisaRetailers    |



















#  @Regression @PositiveCase @auditResults @deleteAllDataOfCreatedAuditAfterTest
#  Scenario Outline: upload audit results file with availability questions - check is proper data stored in answers, reward_points and properly awarded
#    Given Audit admin user is logged in with credentials '<login_password>'
#    And Get store details by externalId '<store_ext_id>'
#    And Store retailer's epoints balance is known before process audit results
#    And Create audit call is made is made with jsonData 'DEFAULT', code '201'
#    And Create new AuditCriteria for store '<store_ext_id>', '<fileName>'
#    And Create new reward-criteria call is made for following data '<params>'
#    When Audit result call is made for data '<fileName>'
#    Then Answers was properly stored for audit 'previous_call' result file '<fileName>' and store
#    And Reward points was properly stored for audit 'previous_call' result file '<fileName>' and rewardCriteria 'previous_call' and store
#    And Retailer's epoints balance updated after process audit results file '<fileName>' for rewardCriteria '<params>'
#    And Retailer awarded in points-manager with proper details for case '<fileName>', '<params>'
#
#    Examples:
#      | login_password | fileName                     | params                                                 | store_ext_id |
#      #TODAYS
#      # Q20 = Yes
#      | admin,admin    | audit_results_1.xlsx         | criteriaRuleForAuditReward_;20;11;previous_call        | 10005001000  |
#      # Q20 = No
#      | admin,admin    | audit_results_2.xlsx         | criteriaRuleForAuditReward_;20;12;previous_call        | 10005001000  |
#      # Q20 = Yes, Q21 = Yes
#      | admin,admin    | audit_results_3.xlsx         | criteriaRuleForAuditReward_;20 AND 21;13;previous_call | 10005001000  |
#      | admin,admin    | audit_results_3.xlsx         | criteriaRuleForAuditReward_;20 OR 21;14;previous_call  | 10005001000  |
#      # Q20 = Yes, Q21 = No
#      | admin,admin    | audit_results_4.xlsx         | criteriaRuleForAuditReward_;20 AND 21;15;previous_call | 10005001000  |
#      | admin,admin    | audit_results_4.xlsx         | criteriaRuleForAuditReward_;20 OR 21;16;previous_call  | 10005001000  |
#      # Q20 = No, Q21 = No
#      | admin,admin    | audit_results_5.xlsx         | criteriaRuleForAuditReward_;20 AND 21;17;previous_call | 10005001000  |
#      | admin,admin    | audit_results_5.xlsx         | criteriaRuleForAuditReward_;20 OR 21;18;previous_call  | 10005001000  |
#      #PREMIER
#      # Q20 = Yes
#      | admin,admin    | audit_results_1_premier.xlsx | criteriaRuleForAuditReward_;20;11;previous_call        | 10005001007  |
#      # Q20 = No
#      | admin,admin    | audit_results_2_premier.xlsx | criteriaRuleForAuditReward_;20;12;previous_call        | 10005001007  |
#      # Q20 = Yes, Q21 = Yes
#      | admin,admin    | audit_results_3_premier.xlsx | criteriaRuleForAuditReward_;20 AND 21;13;previous_call | 10005001007  |
#      | admin,admin    | audit_results_3_premier.xlsx | criteriaRuleForAuditReward_;20 OR 21;14;previous_call  | 10005001007  |
#      # Q20 = Yes, Q21 = No
#      | admin,admin    | audit_results_4_premier.xlsx | criteriaRuleForAuditReward_;20 AND 21;15;previous_call | 10005001007  |
#      | admin,admin    | audit_results_4_premier.xlsx | criteriaRuleForAuditReward_;20 OR 21;16;previous_call  | 10005001007  |
#      # Q20 = No, Q21 = No
#      | admin,admin    | audit_results_5_premier.xlsx | criteriaRuleForAuditReward_;20 AND 21;17;previous_call | 10005001007  |
#      | admin,admin    | audit_results_5_premier.xlsx | criteriaRuleForAuditReward_;20 OR 21;18;previous_call  | 10005001007  |
#
#
#  @Regression @Critical @PositiveCase @auditResults @deleteAllDataOfCreatedAuditAfterTest
#  Scenario Outline: upload audit results file with ad-hoc questions - check is proper data stored in answers, reward_points and properly awarded
#    Given Audit admin user is logged in with credentials '<login_password>'
#    And Get store details by externalId '<store_ext_id>'
#    And Store retailer's epoints balance is known before process audit results
#    And Create new Audit call is made for following data 'auditResult_;2000-01-01;2000-01-10;null'
#    And Create new AuditCriteria for store '<store_ext_id>', '<fileName>'
#    And Create new reward-criteria call is made for following data '<params>'
#    When Audit result call is made for data '<fileName>'
#    Then Answers was properly stored for audit 'previous_call' result file '<fileName>' and store
#    And Reward points was properly stored for audit 'previous_call' result file '<fileName>' and rewardCriteria 'previous_call' and store
#    And Retailer's epoints balance updated after process audit results file '<fileName>' for rewardCriteria '<params>'
#    And Retailer awarded in points-manager with proper details for case '<fileName>', '<params>'
#
#    Examples:
#      | login_password | fileName                           | params                                          | store_ext_id |
#      #TODAYS
#      # Q26 = Yes and Q25 = email
#      | admin,admin    | audit_results_adhoc_1.xlsx         | criteriaRuleForAuditReward_;26;65;previous_call | 10005001000  |
#      # Q26 = No
#      | admin,admin    | audit_results_adhoc_2.xlsx         | criteriaRuleForAuditReward_;26;65;previous_call | 10005001000  |
#      #PREMIER
#      # Q26 = Yes and Q25 = email
#      | admin,admin    | audit_results_adhoc_1_premier.xlsx | criteriaRuleForAuditReward_;26;66;previous_call | 10005001007  |
#      # Q26 = No
#      | admin,admin    | audit_results_adhoc_2_premier.xlsx | criteriaRuleForAuditReward_;26;66;previous_call | 10005001007  |
#
#
#  @Regression2 @Critical @PositiveCase @auditResults @deleteAllDataOfCreatedAuditAfterTest
#  Scenario Outline: upload audit results file with availability questions - retailer for store not exist update store by bulk upload
#    Given Audit admin user is logged in with credentials '<login_password>'
#    And Get store details by externalId '<store_ext_id>'
#    And Create new Audit call is made for following data 'auditResult_;2000-02-01;2000-02-10;null'
#    And Create new AuditCriteria for store '<store_ext_id>', '<fileName>'
#    And Create new reward-criteria call is made for following data '<params>'
#    And Store clear retailerId
#    And Audit result call is made for data '<fileName>'
#    And Answers was properly stored for audit 'previous_call' result file '<fileName>' and store
#    And Reward points was properly stored for audit 'previous_call' result file '<fileName>', rewardCriteria 'previous_call' and store with missing retailer
#    And Retailer's '<retailerEmail>' epoints balance is known before process audit results
#    When Store retailer is updated according to '<scenario>' with params '<storeImport>'
#    Then Reward points was updated with retailerId for audit
#    And Retailer's epoints balance updated after process audit results file '<fileName>' for rewardCriteria '<params>'
#    And Retailer awarded in points-manager with proper details for case '<fileName>', '<params>'
#
#    Examples:
#      | login_password | fileName                                 | params                                           | store_ext_id | retailerEmail           | storeImport                                        | scenario      |
##      #TODAYS
##      # Q20 = Yes
#      | admin,admin    | audit_results_1_no_retailer.xlsx         | criteriaRuleForAuditReward_;20;100;previous_call | 10005001001  | retailer_2@test.test.pl | -                                                  | simpleUpdate  |
#      | admin,admin    | audit_results_1_no_retailer.xlsx         | criteriaRuleForAuditReward_;20;100;previous_call | 10005001001  | retailer_2@test.test.pl | audit_results_no_retailer_stores_bulk.xlsx         | bulkUpload    |
#      | admin,admin    | audit_results_1_no_retailer.xlsx         | criteriaRuleForAuditReward_;20;100;previous_call | 10005001001  | retailer_2@test.test.pl | -                                                  | takeOverStore |
##      #PREMIER
##      # Q20 = Yes
#      | admin,admin    | audit_results_1_no_retailer_premier.xlsx | criteriaRuleForAuditReward_;20;100;previous_call | 10005001008  | retailer_4@test.test.pl | -                                                  | simpleUpdate  |
#      | admin,admin    | audit_results_1_no_retailer_premier.xlsx | criteriaRuleForAuditReward_;20;100;previous_call | 10005001008  | retailer_4@test.test.pl | audit_results_no_retailer_stores_bulk_premier.xlsx | bulkUpload    |
#      | admin,admin    | audit_results_1_no_retailer_premier.xlsx | criteriaRuleForAuditReward_;20;100;previous_call | 10005001008  | retailer_4@test.test.pl | -                                                  | takeOverStore |
#
