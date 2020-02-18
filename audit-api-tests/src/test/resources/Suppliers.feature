Feature: Audit cms - suppliers management
  As Audit Admin user
  I want to be able to manage my suppliers
  To be able to crate, edit and delete them

  @Regression @PositiveCase @suppliers
  Scenario Outline: Check if it's possible to get lists of all available suppliers
    Given Audit admin user is logged in with credentials '<login_password>'
    When Get suppliers call is made
    Then Get suppliers call returns proper contract

  @qa @MI
    Examples:
      | login_password |
      | admin,admin    |


  @Regression @PositiveCase @suppliers
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Check if it's possible to create supplier
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create supplier call is made with jsonData '<jsonData>', code '201'
    Then Create supplier call returns proper data
    And Get supplier by id 'previous_call', code '200'
    And Get supplier by id returns proper data
    And Delete supplier by id 'previous_call', code '200'

  @qa
    Examples:
      | login_password | jsonData                                                                  |
      | admin,admin    | {"supplierName":"API_AUDIT_CMS_SUPPLIER_","partnerApiKey":null,"id":null} |


  @Regression @NegativeCase @suppliers
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Check create supplier with improper data error message validation
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create supplier call is made with jsonData '<jsonData>', code '<code>'
    Then Create supplier call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'

  @qa
    Examples:
      | login_password | jsonData                                           | code | message                   | description           | fieldErrors |
      | admin,admin    | {"supplierName":"","partnerApiKey":null,"id":null} | 500  | error.internalServerError | Internal server error | null        |


  @Regression @PositiveCase @suppliers
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Check if fix made to correct prod suppliers data DO-622 working as expected
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create supplier call is made with jsonData '<jsonData>', code '201'
    When Execute fix missing supplier partner api key
    Then Fixed supplier 'previous_call' contains partner api key
    And Partner was created for fixed supplier 'previous_call'
    And Delete supplier by id 'previous_call', code '200'
    And Delete partners for audit tests suppliers

  @qa
    Examples:
      | login_password | jsonData                                                                  |
      | admin,admin    | {"supplierName":"API_AUDIT_CMS_SUPPLIER_","partnerApiKey":null,"id":null} |