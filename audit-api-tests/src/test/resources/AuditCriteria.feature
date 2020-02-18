Feature: Audit cms - audit criteria
  As Audit Admin user
  I want automatically generate answers criteria
  To be able to export them to file

  @Regression @PositiveCase @auditCriteria
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Populate audit criteria table and validate results
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create audit call is made is made with jsonData 'DEFAULT', code '201'
    When Generate AuditCriteria file for '<segmentation>'
    Then AuditCriteria was properly generated for '<segmentation>'

  @qa
    Examples:            #chain;questions;group;licensed;country
      | login_password | segmentation                              |
      | admin,admin    | Todays;20;null;null;null                  |
      | admin,admin    | Premier;20;null;null;null                 |
      | admin,admin    | Todays;20;A;null;null                     |
      | admin,admin    | Todays;20;null;true;null                  |
      | admin,admin    | Todays;20;null;false;null                 |
      | admin,admin    | Todays;20;null;null;EnglandWales          |
      | admin,admin    | Todays;20;null;null;Scotland              |
      | admin,admin    | Todays;20,21;A;null;null                  |
      | admin,admin    | Todays;20,21;A;null;EnglandWales,Scotland |


  @Regression @NegativeCase @auditCriteria
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Export audit criteria -> error messages validation
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create audit call is made is made with jsonData 'DEFAULT', code '201'
    When Generate AuditCriteria file for incorrect data '<segmentation>', '<status>'
    Then Response for generating AuditCriteria file for incorrect data '<segmentation>', '<message>', '<description>', '<objectNameFE>', '<fieldFE>', '<messageFE>' is in proper format

  @qa
    Examples:            #chain;questions;group;licensed;country
      | login_password | segmentation                                              | status | objectNameFE           | fieldFE   | messageFE | message                       | description                                                                                                                                                                                                        |
      | admin,admin    | Todays;null;null;null;null                                | 400    | auditCriteriaExportDTO | questions | NotNull   | error.validation              | null                                                                                                                                                                                                               |
      | admin,admin    | null;20;null;null;null                                    | 400    | auditCriteriaExportDTO | chainId   | NotNull   | error.validation              | null                                                                                                                                                                                                               |
      | admin,admin    | {"auditId": 1247,  "questions": [{"id":20}], "chainId":1} | 400    | Audit                  | id        | not found | error.validation              | Audit with id: 1247 not found                                                                                                                                                                                      |
      | admin,admin    | Todays;20;test;null;null                                  | 400    | general                | general   | general   | error.validation              | No stores found for auditGroup: test , countries:  , licensed: null                                                                                                                                                |
      | admin,admin    | Todays;20;null;null;test                                  | 400    | general                | general   | general   | Request JSON cannot be parsed | Could not read document: Can not construct instance of com.iat.audit.domain.enumeration.Country from String value 'test': value not one of declared Enum instance names: [Scotland, NorthernIreland, EnglandWales] |
      | admin,admin    | Todays;20;AB;null;null                                    | 400    | general                | general   | general   | error.validation              | No stores found for auditGroup: AB , countries:  , licensed: null                                                                                                                                                  |
      | admin,admin    | Premier;20;null;false;null                                | 400    | general                | general   | general   | error.validation              | No stores found for auditGroup: null , countries:  , licensed: false                                                                                                                                               |