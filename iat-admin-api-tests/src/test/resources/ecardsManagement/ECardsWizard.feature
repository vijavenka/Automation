Feature: IAT Admin HR - wizard
  As admin user
  I want to be able to know what is current wizard completion status
  To be able to know if wizard have to be still available or not


  @Regression @PositiveCase @Wizard
  Scenario Outline: Get current status of wizard completion process
    Given IAT Admin user is logged in with credentials '<login_password>'
    And IAT admin is logged for first time
    And Get Wizard finished status call returns 'false' for first time logged user
    And Wizard step up to '<stepNumber>' is finished
    When Get Wizard finished status call is made
    Then Get Wizard finished status call returns '<status>'

  @qa
    Examples:
      | login_password              | stepNumber | status |
      | api_admin_wizard_1,P@ssw0rd | 1          | false  |
      | api_admin_wizard_1,P@ssw0rd | 2          | false  |
      | api_admin_wizard_1,P@ssw0rd | 3          | false  |
      | api_admin_wizard_1,P@ssw0rd | 4          | true   |


  @Regression @PositiveCase @Wizard
  Scenario Outline: Setup global Config without required authority - Error message
    Given IAT Admin user is logged in with credentials '<login_password>'
    And IAT admin is logged for first time
    And Get Wizard finished status call returns 'false' for first time logged user
    When IAT admin try setup config step '<stepNumber>' for config wizard '<status>'
    Then Set up config call for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password              | stepNumber | status | error     | message          |
      #User without hr_global_config authority
      | api_admin_wizard_2,P@ssw0rd | 1          | 403    | Forbidden | Access is denied |
      | api_admin_wizard_2,P@ssw0rd | 2          | 403    | Forbidden | Access is denied |
      | api_admin_wizard_2,P@ssw0rd | 3          | 403    | Forbidden | Access is denied |


  @Regression @NegativeCase @Wizard
  Scenario Outline: Get current status of wizard completion process Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get Wizard finished status call for incorrect data '<status>'
    Then Get Wizard finished status call for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password               | status | error        | message                                                 |
      | api_admin_wizard_1,password2 | 401    | Unauthorized | Full authentication is required to access this resource |


  @Regression @PositiveCase @Wizard
  Scenario Outline: Set wizard last step
    Given IAT Admin user is logged in with credentials '<login_password>'
    And IAT admin is logged for first time
    And Get Wizard finished status call returns 'false' for first time logged user
    When Set Wizard last step call is made with value '<stepNumber>'
    Then Get Wizard finished status call returns proper last step value '<stepNumber>'

  @qa
    Examples:
      | login_password              | stepNumber |
      | api_admin_wizard_1,P@ssw0rd | 1          |
      | api_admin_wizard_1,P@ssw0rd | 2          |
      | api_admin_wizard_1,P@ssw0rd | 3          |
      | api_admin_wizard_1,P@ssw0rd | 4          |


  @Regression @NegativeCase @Wizard
  Scenario Outline: Set wizard last step - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Set Wizard last step call is made with incorrect data '<stepNumber>', '<status>'
    Then Set Wizard last step call is made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password               | stepNumber | status | error        | message                                                 |
      | api_admin_wizard_1,password2 | 1          | 401    | Unauthorized | Full authentication is required to access this resource |



