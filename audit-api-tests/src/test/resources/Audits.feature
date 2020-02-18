Feature: Audit cms - audits management
  As Audit Admin user
  I want to be able to manage my audits
  To be able to crate, edit and delete them

  @Regression @Critical @PositiveCase @audits
  Scenario Outline: Check if it's possible to get lists of all available audits
    Given Audit admin user is logged in with credentials '<login_password>'
    When Get audits call is made
    Then Get audits call returns proper contract

  @qa
    Examples:
      | login_password |
      | admin,admin    |


  @Regression @PositiveCase @audit
  Scenario Outline: Check if it's possible to create audit
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create audit call is made is made with jsonData '<jsonData>', code '201'
    Then Create audit call returns proper data
    And Delete audit by id 'previous_call', code '200'

  @qa
    Examples:
      | login_password | jsonData                                                                                    |
      | admin,admin    | {"auditName": "API_AUDIT_CMS_AUDIT_", "auditStart": "2013-01-01", "auditEnd": "2013-01-01"} |

# this will fail as any validation is implemented
  @Regression @NegativeCase @audit
  Scenario Outline: Check create audit validation
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create audit call is made is made with jsonData '<jsonData>', code '<code>'
    Then Create audit call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'

  @qa
    Examples:
      | login_password | jsonData                                                                            | code | message          | description | fieldErrors |
      # missing auditName
      | admin,admin    | {"auditName": null, "auditStart": "2013-01-01", "auditEnd": "2013-01-01"}           | 400  | error.validation | ???         | null        |
      # missing auditName
      | admin,admin    | {"auditName": "API_AUDIT_CMS_AUDIT_", "auditStart": null, "auditEnd": "2013-01-01"} | 400  | error.validation | ???         | null        |
      # missing auditName
      | admin,admin    | {"auditName": "API_AUDIT_CMS_AUDIT_", "auditStart": "2013-01-01", "auditEnd": null} | 400  | error.validation | ???         | null        |

