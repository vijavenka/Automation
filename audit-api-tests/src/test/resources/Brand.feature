Feature: Audit cms - brand management
  As Audit Admin user
  I want to be able to manage my brands
  To be able to crate, edit and delete them

  @Regression @PositiveCase @brand
  Scenario Outline: Check if it's possible to get lists of all available brands
    Given Audit admin user is logged in with credentials '<login_password>'
    When Get brand call is made
    Then Get brand call returns proper contract

  @qa
    Examples:
      | login_password |
      | admin,admin    |


  @Regression @PositiveCase @brand
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Check if it's possible to create brand
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create supplier call is made with jsonData 'DEFAULT', code '201'
    When Create brand call is made with jsonData '<jsonData>', code '201'
    Then Create brand call returns proper data
    And Get brand by id 'previous_call', code '200'
    And Get brand by id returns proper data
    And Delete brand by id 'previous_call', code '200'

  @qa
    Examples:
      | login_password | jsonData                                                                    |
      | admin,admin    | {"brandName":"API_AUDIT_CMS_BRAND_","supplierId":"previous_call","id":null} |
      | admin,admin    | {"brandName":"API_AUDIT_CMS_BRAND_","supplierId":null,"id":null}            |


  @Regression @NegativeCase @brand
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Check create brand with improper data error message validation
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create supplier call is made with jsonData 'DEFAULT', code '201'
    When Create brand call is made with jsonData '<jsonData>', code '<code>'
    Then Create brand call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'

  @qa
    Examples:
      | login_password | jsonData                                                | code | message                   | description           | fieldErrors |
      #empty name
      | admin,admin    | {"brandName":"","supplierId":"previous_call","id":null} | 500  | error.internalServerError | Internal server error | null        |
      #not existing supplier
      | admin,admin    | {"brandName":"","supplierId":"2147483647","id":null}    | 500  | error.internalServerError | Internal server error | null        |