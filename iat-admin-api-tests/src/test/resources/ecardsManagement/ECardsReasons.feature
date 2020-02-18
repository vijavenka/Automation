Feature: IAT Admin HR - reasons management
  As an admin
  I want to be able to view and create my ecards reasons set
  So that I will be able to control points distribution by reasons

  @Regression @PositiveCase @EcardReasons
  Scenario Outline: Reasons creation - get list of reasons
    Given IAT Admin user is logged in with credentials '<login_password>'
    When He make a call to get list of reasons
    Then Response with list of reasons in agreed format
    And Reasons are in descending order

  @qa
    Examples:
      | login_password                                          |
      # super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        |
      # admin
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              |
      # manager
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd |
      # user without rights
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd  |


  @Regression @PositiveCase @EcardReasons
  @removeCreatedReasons
  Scenario Outline: Reasons creation - create and get details of new reason
    Given IAT Admin user is logged in with credentials '<login_password>'
    When New reason is created with following jsonBody '<jsonBody>'
    And Reason Id is returned after create reason request
    Then Get Ecards reason by Id 'previousCall' call is made
    And Get Ecards reason by Id call returns proper data
    And Proper tag was created in Points-Manager
    And Delete Ecards reason by Id 'previousCall' call is made


  @qa
    Examples:
      | login_password                                   | jsonBody                                                                                                                                                                                  |
      # Current global limits ranges
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"DEFINE","managerToUserMin":"2","managerToUserMax":"2000000","userToUserReasonRange":"DEFINE","userToUserMin":"2","userToUserMax":"1000000"} |
      # Min/ Max values in global reasons ranges
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"DEFINE","managerToUserMin":"12","managerToUserMax":"111","userToUserReasonRange":"DEFINE","userToUserMin":"22","userToUserMax":"222"}       |
      # Global limits set
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"GLOBAL","userToUserReasonRange":"GLOBAL"}                                                                                                   |


  @Regression @NegativeCase @EcardReasons
  Scenario Outline: Create new reason Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Create new reason for incorrect data '<jsonBody>', '<status>', '<error>'
    Then Create new reason for incorrect data return error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                   | status | error        | message                                                                                                                                                                                                                                                                                                                                                                                            | jsonBody                                                                                                                                                                           |
      | not_exist,password2                              | 401    | Unauthorized | Full authentication is required to access this resource                                                                                                                                                                                                                                                                                                                                            | {}                                                                                                                                                                                 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | 403    | Forbidden    | Access is denied                                                                                                                                                                                                                                                                                                                                                                                   | {"name":"xyz2","managerToUserReasonRange":"GLOBAL","userToUserReasonRange":"GLOBAL" }                                                                                              |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Range cannot be empty","fieldName":"managerToUserReasonRange"},{"message":"Range cannot be empty","fieldName":"userToUserReasonRange"},{"message":"Name cannot be empty","fieldName":"name"}]}                                                                                                                                                                              | {}                                                                                                                                                                                 |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Range cannot be empty","fieldName":"managerToUserReasonRange"},{"message":"Range cannot be empty","fieldName":"userToUserReasonRange"},{"message":"Name cannot be empty","fieldName":"name"}]}                                                                                                                                                                              | {"name":null,"userToUserReasonRange": null,"managerToUserReasonRange": null,"managerToUserMin":null,"userToUserMin":null,"managerToUserMax":null,"userToUserMax":null}             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Min value should not be empty","fieldName":"managerToUserMin"},{"message":"Max value should not be empty","fieldName":"managerToUserMax"},{"message":"Min value should not be empty","fieldName":"userToUserMin"},{"message":"Max value should not be empty","fieldName":"userToUserMax"}]}                                                                                 | {"name":"xyz2","managerToUserReasonRange":"DEFINE","userToUserReasonRange":"DEFINE" }                                                                                              |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Max value should not be empty","fieldName":"managerToUserMax"},{"message":"Max value should not be empty","fieldName":"userToUserMax"}]}                                                                                                                                                                                                                                    | {"name":"xyz2","userToUserReasonRange":"DEFINE","managerToUserReasonRange":"DEFINE","managerToUserMin":20,"userToUserMin":20   }                                                   |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Reason range is global. Max value should be empty","fieldName":"managerToUserMax"},{"message":"Reason range is global. Min value should be empty","fieldName":"managerToUserMin"},{"message":"Reason range is global. Max value should be empty","fieldName":"userToUserMax"},{"message":"Reason range is global. Min value should be empty","fieldName":"userToUserMin"}]} | {"name":"xyz2","userToUserReasonRange":"GLOBAL","managerToUserReasonRange":"GLOBAL","managerToUserMin":98,"managerToUserMax":99,"userToUserMin":88,"userToUserMax":99  }           |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Min value must be greater or equal than global (%s).","fieldName":"userToUserMin"},{"message":"Min value must be greater or equal than global (%s).","fieldName":"managerToUserMin"}]}                                                                                                                                                                                      | {"name":"xyz2","userToUserReasonRange":"DEFINE","managerToUserReasonRange":"DEFINE","managerToUserMin":1,"userToUserMin":1,"managerToUserMax":10,"userToUserMax":10   }            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Max value must be lower or equal than global (%s).","fieldName":"userToUserMax"},{"message":"Max value must be lower or equal than global (%s).","fieldName":"managerToUserMax"}]}                                                                                                                                                                                          | {"name":"xyz2","userToUserReasonRange":"DEFINE","managerToUserReasonRange":"DEFINE","managerToUserMin":20,"userToUserMin":2,"managerToUserMax":2000001,"userToUserMax":1000001   } |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Reason name should be unique.","fieldName":"name"}]}                                                                                                                                                                                                                                                                                                                        | {"name":"WIZARD","userToUserReasonRange":"DEFINE","managerToUserReasonRange":"DEFINE","managerToUserMin":20,"userToUserMin":2,"managerToUserMax":25,"userToUserMax":25}            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 400    | null         | {"errors":[{"message":"Reason name should be unique.","fieldName":"name"}]}                                                                                                                                                                                                                                                                                                                        | {"name":"QA_Rox","userToUserReasonRange":"DEFINE","managerToUserReasonRange":"DEFINE","managerToUserMin":20,"userToUserMin":2,"managerToUserMax":25,"userToUserMax":25}            |


  @Regression @NegativeCase @EcardReasons
  Scenario Outline: Get reason by id Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get reason by id for incorrect data '<id>', '<status>'
    Then Get reason by id for incorrect data <id> return error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                   | status | error        | message                                                                           | id           |
      | not_exist,password2                              | 401    | Unauthorized | Full authentication is required to access this resource                           | 0            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 404    | null         | {"errors":[{"message":"There is no ECardsReason with id:[%s]","fieldName":"id"}]} | 100500100900 |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 404    | null         | {"errors":[{"message":"There is no ECardsReason with id:[%s]","fieldName":"id"}]} | deleted      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | 404    | null         | {"errors":[{"message":"There is no ECardsReason with id:[%s]","fieldName":"id"}]} | 100500100900 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | 404    | null         | {"errors":[{"message":"There is no ECardsReason with id:[%s]","fieldName":"id"}]} | deleted      |


  @Regression @NegativeCase @EcardReasons
  Scenario Outline: Delete reason by id Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Delete reason by id for incorrect data '<id>', '<status>'
    Then Delete reason by id for incorrect data <id> return error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                         | status | error        | message                                                                           | id           |
      | not_exist,password2                                    | 401    | Unauthorized | Full authentication is required to access this resource                           | 0            |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | 403    | Forbidden    | Access is denied                                                                  | 987819223    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | 404    | null         | {"errors":[{"message":"There is no ECardsReason with id:[%s]","fieldName":"id"}]} | 100500100900 |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | 404    | null         | {"errors":[{"message":"There is no ECardsReason with id:[%s]","fieldName":"id"}]} | deleted      |


  @Regression @NegativeCase @EcardReasons
  Scenario Outline: Delete last reason - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    And IAT admin is logged for first time
    And Wizard step up to '4' is finished
    And He make a call to get list of reasons
    When Delete all reasons
    Then Delete all reasons for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password              | status | error       | message                                                  |
      | api_admin_wizard_1,P@ssw0rd | 400    | Bad Request | You can not delete last reason, at least one must exists |



  # Currently this functionality is turned off because it's cause too many error around
  @Regression @EcardReasons
  @setDefaultReasonGlobalLimits @removeCreatedReasons
  Scenario Outline: Create new reason, and change global ranges - reason should be updated according global changes
    Given IAT Admin user is logged in with credentials '<login_password>'
    And Global reason limits are known
    And Create new reason which is valid with global limitation '<jsonBody>'
    When Update global reason limits
    Then Get Ecards reason by Id 'previousCall' call is made
    And Reason data was updated according global limits change
    And Delete Ecards reason by Id 'previousCall' call is made

  @qa
    Examples:
      | login_password                                   | jsonBody                                                                                                                                                                              |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"DEFINE","managerToUserMin":"3","managerToUserMax":"100","userToUserReasonRange":"DEFINE","userToUserMin":"10","userToUserMax":"100"}    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"DEFINE","managerToUserMin":"10","managerToUserMax":"5001","userToUserReasonRange":"DEFINE","userToUserMin":"10","userToUserMax":"100"}  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"DEFINE","managerToUserMin":"10","managerToUserMax":"100","userToUserReasonRange":"DEFINE","userToUserMin":"3","userToUserMax":"100"}    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"DEFINE","managerToUserMin":"10","managerToUserMax":"100","userToUserReasonRange":"DEFINE","userToUserMin":"10","userToUserMax":"10001"} |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name":"QA Rox ","managerToUserReasonRange":"DEFINE","managerToUserMin":"11","managerToUserMax":"100","userToUserReasonRange":"DEFINE","userToUserMin":"11","userToUserMax":"100"}   |
