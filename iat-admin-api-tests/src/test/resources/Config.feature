Feature: IAT Admin HR - config management
  As TODO admin
  I want to be able to view and set
  To be able to know what is current settings and be able change this settings


  @Regression @PositiveCase @EcardSettings
  Scenario Outline: Get global settings for different types
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get Ecards settings call for '<settingsType>' is made
    Then Get Ecards settings call for '<settingsType>' return proper contract

  @qa
    Examples:
      | login_password                                          | settingsType  |
      # superadmin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | reasons       |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | pointsSharing |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | templates     |
#      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | milestones    |
      # admin
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | reasons       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | pointsSharing |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | templates     |
      # manager
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | reasons       |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | pointsSharing |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | templates     |


  @Regression @NegativeCase @EcardSettings
  Scenario Outline: Get global settings for different types Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get Ecards settings call for incorrect data '<settingsType>', '<status>'
    Then Get Ecards settings call for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password         | settingsType  | status | error        | message                                                 |
      | not_existing,password2 | reasons       | 401    | Unauthorized | Full authentication is required to access this resource |
      | not_existing,password2 | pointsSharing | 401    | Unauthorized | Full authentication is required to access this resource |
      | not_existing,password2 | templates     | 401    | Unauthorized | Full authentication is required to access this resource |
      #admin
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | milestones    | 403    | Forbidden    | Access is denied                                        |
      #manager
#      | pi_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | milestones    | 403    | Forbidden    | Access is denied                                        |


  @Regression @PositiveCase @EcardSettings
  Scenario Outline: Set global settings for different types
    Given IAT Admin user is logged in with credentials '<login_password>'
    And Clear Global password settings
    When Set Ecards settings call for '<settingsType>' is made with data '<jsonBody>'
    Then Get Ecards settings call for '<settingsType>' is made
    And Ecards settings should be properly updated for '<settingsType>'

  @qa
    Examples:
      | login_password                                   | settingsType  | jsonBody                                                                                                                                                                                        |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | reasons       | {"managerToUserMin": 1, "managerToUserMax":1234567, "userToUserMin":2, "userToUserMax":987654}                                                                                                  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | reasons       | {"managerToUserMin": 2, "managerToUserMax": 1000000, "userToUserMin":1, "userToUserMax":333333}                                                                                                 |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | reasons       | {"managerToUserMin": 1, "managerToUserMax": 223, "userToUserMin": 3, "userToUserMax": 1000000}                                                                                                  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | reasons       | {"managerToUserMin": 3, "managerToUserMax":90000000, "userToUserMin":1, "userToUserMax":987654}                                                                                                 |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | reasons       | {"managerToUserMin": 2, "managerToUserMax": 2000000, "userToUserMin": 2, "userToUserMax": 1000000}                                                                                              |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pointsSharing | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pointsSharing | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "ALL"}                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pointsSharing | {"userSharePointsRange": "ALL_USERS", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "THRESHOLD", "approvalThreshold": 500}                      |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pointsSharing | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE", "globalPassword": "t"}                        |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pointsSharing | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE", "globalPassword": "P@ssw0rd!@@#$%^&*()MLklm"} |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | templates     | {"useDefaultTemplatesSet": false}                                                                                                                                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | templates     | {"useDefaultTemplatesSet": true}                                                                                                                                                                |
#      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | milestones    | [{ "name": "birthdate", "active": true }, { "name": "workAnniversary", "active": false } ]                                                                                                      |
#      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | milestones    | [{ "name": "birthdate", "active": false }, { "name": "workAnniversary", "active": true } ]                                                                                                      |

  @Regression @PositiveCase @EcardSettings
  Scenario Outline: Set global settings for different types Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Set Ecards settings call type '<settingsType>' is made for incorrect data '<jsonBody>', '<status>'
    Then Set Ecards settings call type <settingsType> for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | login_password                                    | settingsType  | jsonBody                                                                                                                                                                         | status | error        | message                                                                                                                                                                                                                                                                                                  |
      | api_test_super_admin_1@api.iat.admin.pl,password2 | reasons       | {}                                                                                                                                                                               | 401    | Unauthorized | Full authentication is required to access this resource                                                                                                                                                                                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd        | reasons       | {}                                                                                                                                                                               | 403    | Forbidden    | Access is denied                                                                                                                                                                                                                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | reasons       | {}                                                                                                                                                                               | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"managerToUserMin"},{"message":"Field cannot be empty","fieldName":"managerToUserMax"},{"message":"Field cannot be empty","fieldName":"userToUserMin"},{"message":"Field cannot be empty","fieldName":"userToUserMax"}]}                       |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | reasons       | {"managerToUserMin": null, "managerToUserMax": null, "userToUserMin": null, "userToUserMax": null}                                                                               | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"managerToUserMin"},{"message":"Field cannot be empty","fieldName":"managerToUserMax"},{"message":"Field cannot be empty","fieldName":"userToUserMin"},{"message":"Field cannot be empty","fieldName":"userToUserMax"}]}                       |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | reasons       | {"managerToUserMin": 0, "managerToUserMax": 1000000, "userToUserMin": 2222, "userToUserMax": 333333}                                                                             | 400    | null         | {"errors":[{"message":"Min value must be greater or equal then 1.","fieldName":"managerToUserMin"}]}                                                                                                                                                                                                     |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | reasons       | {"managerToUserMin": 123, "managerToUserMax": 1000, "userToUserMin": 0, "userToUserMax": 333333}                                                                                 | 400    | null         | {"errors":[{"message":"Min value must be greater or equal then 1.","fieldName":"userToUserMin"}]}                                                                                                                                                                                                        |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | reasons       | {"managerToUserMin": 1000, "managerToUserMax": 99, "userToUserMin": 12, "userToUserMax": 333333}                                                                                 | 400    | null         | {"errors":[{"message":"Min value must be lower then max","fieldName":"managerToUserMin"},{"message":"Max value must be greater then min","fieldName":"managerToUserMax"}]}                                                                                                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | reasons       | {"managerToUserMin": 1230, "managerToUserMax": 10000, "userToUserMin": 1000, "userToUserMax": 99}                                                                                | 400    | null         | { "errors":[{"message":"Min value must be lower then max","fieldName":"userToUserMin"},{"message":"Max value must be greater then min","fieldName":"userToUserMax"}]}                                                                                                                                    |
      | api_test_super_admin_1@api.iat.admin.pl,password2 | pointsSharing | {}                                                                                                                                                                               | 401    | Unauthorized | Full authentication is required to access this resource                                                                                                                                                                                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd        | pointsSharing | {}                                                                                                                                                                               | 403    | Forbidden    | Access is denied                                                                                                                                                                                                                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | pointsSharing | {}                                                                                                                                                                               | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"userSharePointsRange"},{"message":"Field cannot be empty","fieldName":"managerSharePointsRange"},{"message":"Field cannot be empty","fieldName":"sharePointsWithManager"},{"message":"Field cannot be empty","fieldName":"approvalProcess"}]} |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | pointsSharing | {"userSharePointsRange": null, "managerSharePointsRange": null, "sharePointsWithManager": null, "approvalProcess": null}}                                                        | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"userSharePointsRange"},{"message":"Field cannot be empty","fieldName":"managerSharePointsRange"},{"message":"Field cannot be empty","fieldName":"sharePointsWithManager"},{"message":"Field cannot be empty","fieldName":"approvalProcess"}]} |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | pointsSharing | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "ALL_USERS", "sharePointsWithManager": false, "approvalProcess": "NONE"}                                     | 400    | null         | {"errors":[{"message":"Not valid","fieldName":"managerSharePointsRange"}]}                                                                                                                                                                                                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | pointsSharing | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": false, "approvalProcess": "THRESHOLD", "approvalThreshold": null}} | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"approvalThreshold"}]}                                                                                                                                                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | pointsSharing | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "THRESHOLD" }                             | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"approvalThreshold"}]}                                                                                                                                                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | pointsSharing | {"userSharePointsRange": "", "managerSharePointsRange": "", "sharePointsWithManager": "", "approvalProcess": ""}                                                                 | 400    | null         | {"errors":[{"message":"Not readable data","fieldName":"data"}]}                                                                                                                                                                                                                                          |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | pointsSharing | {"userSharePointsRange": "BLE", "managerSharePointsRange": "BLA", "sharePointsWithManager": 0}                                                                                   | 400    | null         | {"errors":[{"message":"Not readable data","fieldName":"data"}]}                                                                                                                                                                                                                                          |
      | api_test_super_admin_1@api.iat.admin.pl,password2 | templates     | {}                                                                                                                                                                               | 401    | Unauthorized | Full authentication is required to access this resource                                                                                                                                                                                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd        | templates     | {}                                                                                                                                                                               | 403    | Forbidden    | Access is denied                                                                                                                                                                                                                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | templates     | {}                                                                                                                                                                               | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"useDefaultTemplatesSet"}]}                                                                                                                                                                                                                    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd  | templates     | {"useDefaultTemplatesSet": null}                                                                                                                                                 | 400    | null         | {"errors":[{"message":"Field cannot be empty","fieldName":"useDefaultTemplatesSet"}]}                                                                                                                                                                                                                    |
#      | api_test_super_admin_1@api.iat.admin.pl,password2       | milestones    | {}                                                                                                                                                                               | 401    | Unauthorized | Full authentication is required to access this resource | {}                                                                                                                                                                                                                                                                                                       |
#      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | milestones    | {}                                                                                                                                                                               | 403    | Forbidden    | Access is denied                                        | {}                                                                                                                                                                                                                                                                                                       |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | milestones    | {}                                                                                                                                                                               | 403    | Forbidden    | Access is denied                                        | {}                                                                                                                                                                                                                                                                                                       |
#      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | milestones    | {}                                                                                                                                                                               | 400    |  null                                                    | {"errors":[{"message":"Field cannot be empty","fieldName":"useDefaultTemplatesSet"}]}                                                                                                                                                                                                                    |
#      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | milestones    | {"birthdayActive": null,"workAnniversaryActive": null}                                                                                                                           | 400    |  null                                                    | {"errors":[{"message":"Field cannot be empty","fieldName":"useDefaultTemplatesSet"}]}                                                                                                                                                                                                                    |

  @Regression @NegativeCase @EcardSettings @GlobalPassword
  @removeGlobalPasswordSetting
  Scenario Outline: Set global settings pointsSharing for global password when already set - error messages
    Given IAT Admin user is logged in with credentials '<login_password>'
    And Clear Global password settings
    When Set Ecards settings call type '<settingsType>' is made for incorrect data '<jsonBody>', '200'
    And Set Ecards settings call type '<settingsType>' is made for incorrect data '<jsonBody2>', '<status>'
    Then Set Ecards settings call type <settingsType> for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                   | settingsType  | jsonBody                                                                                                                                                                 | jsonBody2                                                                                                                                                                 | status | error | message                                                                                             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pointsSharing | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE", "globalPassword": "t"} | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE", "globalPassword": "t2"} | 400    | null  | {"errors":[{"message":"Once set, global password can't be changed.","fieldName":"globalPassword"}]} |