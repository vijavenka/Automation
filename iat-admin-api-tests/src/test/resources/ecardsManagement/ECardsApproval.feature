Feature: IAT Admin HR - ecards approval process
  As ecards administrator
  I want to have possibility to manage approval process
  To be able turn it OFF/ON and manage entire process

#  Remember to set approval manager id in user who grant users

  @Regression2 @Critical @PositiveCase @EcardsApprovalList @ApprovalReject
  Scenario Outline: Check if approval process is properly turned ON and it's possible to reject
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON '<approvalProcess_approvalThreshold>'
    And Approval manager '<approval_login_password>' checks current approvals list count
    And IAT Admin user is logged in with credentials 'api_test_manager_department_1@api.iat.admin.pl,password'
    And IAT Admin user current department allocation limit is known before grant users in approval flow
    When New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '<points_value>'
    Then IAT Admin user current department allocation limit was not decreased after send ecard in approval flow
    And Approval manager '<approval_login_password>' checks that he have new pending approves on approvals list
    And Approval manager '<approval_login_password>' reject pending approvals from list with message '<message>'
    And Approval manager '<approval_login_password>' checks current approvals list count was decreased

    Examples:
      | approval_login_password                             | approvalProcess_approvalThreshold | message                    | points_value |
      | api_test_admin_approval_1@api.iat.admin.pl,password | ALL,null                          | job good it was not [JODA] | 3            |
      | api_test_admin_approval_1@api.iat.admin.pl,password | THRESHOLD,100                     | job good it was not [JODA] | 100          |


  @Regression2 @Critical @NegativeCase @ApprovalReject
  Scenario Outline: Reject approval - Error message validation
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON 'ALL,null'
    And New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '2'
    And IAT Admin user is logged in with credentials '<approval_login_password>'
    When Approval manager try reject with incorrect data '<approvalId>', '<jsonBody>', '<status>'
    Then Approval rejection made for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | approval_login_password                             | approvalId | jsonBody                               | status | error     | message                                     |
      #reject already approved
      | api_test_admin_approval_1@api.iat.admin.pl,password | approved   | {"message": "already approved update"} | 404    | Not Found | Pending eCard Batch with id = %s not found. |
      #reject already rejected (try change message)
      | api_test_admin_approval_1@api.iat.admin.pl,password | rejected   | {"message": "already rejected update"} | 404    | Not Found | Pending eCard Batch with id = %s not found. |
      #not existing id
      | api_test_admin_approval_1@api.iat.admin.pl,password | 1000500100 | {"message": "not existing id"}         | 404    | Not Found | Pending eCard Batch with id = %s not found. |
      ##reject with message longer than 255 chars - there is no validation for max length on b/e -> Luk's decission
       ##| api_test_admin_approval_1@api.iat.admin.pl,password | dynamic    | {"message": "MORE_THAN_255"}           | 400    | Bad Request | Full authentication is required to access this resource |
      #reject with {"message": ""}
      | api_test_admin_approval_1@api.iat.admin.pl,password | dynamic    | {"message": ""}                        | 400    | message   | may not be empty                            |
      #reject with {"message": null}
      | api_test_admin_approval_1@api.iat.admin.pl,password | dynamic    | {"message": null}                      | 400    | message   | may not be empty                            |
      #reject with {}
      | api_test_admin_approval_1@api.iat.admin.pl,password | dynamic    | {}                                     | 400    | message   | may not be empty                            |


  @Regression2 @Critical @NegativeCase @ApprovalReject
  Scenario Outline: Reject approval - Error message validation for unauthorized
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON 'ALL,null'
    And IAT Admin user is logged in with credentials '<approval_login_password>'
    When Unauthorized try reject with incorrect data '<jsonBody>', '<status>'
    Then Approval rejection made for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | approval_login_password                              | jsonBody                    | status | error        | message                                                 |
#      #unauthorized
      | api_test_admin_approval_1@api.iat.admin.pl,password2 | {"message": "unauthorized"} | 401    | Unauthorized | Full authentication is required to access this resource |


  @Regression2 @Critical @PositiveCase @EcardsApprovalList @ApprovalApprove
  Scenario Outline: Check if approval process is properly turned ON and it's possible to approve
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON '<approvalProcess_approvalThreshold>'
    And Approval manager '<approval_login_password>' checks current approvals list count
    And IAT Admin user is logged in with credentials 'api_test_manager_department_1@api.iat.admin.pl,password'
    And IAT Admin user current department allocation limit is known before grant users in approval flow
    And New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '<points_value>'
    And IAT Admin user current department allocation limit was not decreased after send ecard in approval flow
    And Approval manager '<approval_login_password>' checks that he have new pending approves on approvals list
    When Approval manager '<approval_login_password>' approve pending approval from list
    Then Approval manager '<approval_login_password>' checks current approvals list count was decreased
    And IAT Admin user is logged in with credentials 'api_test_manager_department_1@api.iat.admin.pl,password'
    And IAT Admin user current department allocation limit was decreased after approve ecard send for points value '<points_value>'

    Examples:
      | approval_login_password                             | approvalProcess_approvalThreshold | points_value |
      | api_test_admin_approval_1@api.iat.admin.pl,password | ALL,null                          | 2            |
      | api_test_admin_approval_1@api.iat.admin.pl,password | THRESHOLD,25                      | 30           |


  @Regression2 @Critical @NegativeCase @ApprovalApprove
  Scenario Outline: Approve approval - Error message validation
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON 'ALL,null'
    And New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '2'
    And IAT Admin user is logged in with credentials '<approval_login_password>'
    When Approval manager try approve with incorrect data '<approvalId>', '<status>'
    Then Approve call made for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | approval_login_password                             | approvalId | status | error     | message                                     |
      #approve already approved
      | api_test_admin_approval_1@api.iat.admin.pl,password | approved   | 404    | Not Found | Pending eCard Batch with id = %s not found. |
      #approve already rejected (try change message)
      | api_test_admin_approval_1@api.iat.admin.pl,password | rejected   | 404    | Not Found | Pending eCard Batch with id = %s not found. |
      #not existing id
      | api_test_admin_approval_1@api.iat.admin.pl,password | 1000500100 | 404    | Not Found | Pending eCard Batch with id = %s not found. |


  @Regression2 @Critical @NegativeCase @ApprovalApprove
  Scenario Outline: Approve approval - Error message validation for unauthorized
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON 'ALL,null'
    And IAT Admin user is logged in with credentials '<approval_login_password>'
    When Unathorized try approve with incorrect data '<status>'
    Then Approve call made for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | approval_login_password                              | status | error        | message                                                 |
      #unauthorized
      | api_test_admin_approval_1@api.iat.admin.pl,password2 | 401    | Unauthorized | Full authentication is required to access this resource |


  @Regression2 @Critical @PositiveCase @EcardsApprovalById
  Scenario: Get approval by Id and validate contract
    Given Approval manager 'api_test_admin_approval_1@api.iat.admin.pl,password' checks current approvals list count
    When Approval manager made request for get approval details by id
    Then Requested approval details are returned with proper contract


  @Regression2 @Critical @NegativeCase @EcardsApprovalById
  Scenario Outline: Get approval by Id - Error message validation
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON 'ALL,null'
    And New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '2'
    And IAT Admin user is logged in with credentials '<approval_login_password>'
    When Approval manager made request for get approval details by id with incorrect data '<approvalId>', '<status>'
    Then Get approval details by id call made for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | approval_login_password                             | approvalId | status | error     | message                                     |
      #reject already approved
      | api_test_admin_approval_1@api.iat.admin.pl,password | approved   | 404    | Not Found | Pending eCard Batch with id = %s not found. |
      #reject already rejected (try change message)
      | api_test_admin_approval_1@api.iat.admin.pl,password | rejected   | 404    | Not Found | Pending eCard Batch with id = %s not found. |
      #not existing id
      | api_test_admin_approval_1@api.iat.admin.pl,password | 1000500100 | 404    | Not Found | Pending eCard Batch with id = %s not found. |


  @Regression2 @Critical @NegativeCase @EcardsApprovalById
  Scenario Outline: Get approval by Id - Error message validation for unauthorized
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON 'ALL,null'
    And IAT Admin user is logged in with credentials '<approval_login_password>'
    When Unathorized made request for get approval details by id with incorrect data '<status>'
    Then Get approval details by id call made for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | approval_login_password | status | error        | message                                                 |
      #unauthorized
      | not_existing,password2  | 401    | Unauthorized | Full authentication is required to access this resource |


  @Regression2 @Critical @PositiveCase @EcardsApproval
  Scenario Outline: Check if approval process is not required for ecards without points or points below threshold limit
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON '<approvalProcess_approvalThreshold>'
    And IAT Admin user is logged in with credentials 'api_test_manager_department_1@api.iat.admin.pl,password'
    And IAT Admin user current department allocation limit is known before grant users in approval flow
    And Approval manager '<approval_login_password>' checks current approvals list count
    When New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '<points>'
    And IAT Admin user current department allocation limit was decreased after approve ecard send for points value '<points>'
    Then Approval manager '<approval_login_password>' checks current approvals list count was not changed

    Examples:
      | approval_login_password                             | approvalProcess_approvalThreshold | points |
      | api_test_admin_approval_1@api.iat.admin.pl,password | ALL,null                          | null   |
      | api_test_admin_approval_1@api.iat.admin.pl,password | THRESHOLD,25                      | null   |
      | api_test_admin_approval_1@api.iat.admin.pl,password | THRESHOLD,25                      | 24     |


  @Regression2 @Critical @PositiveCase @EcardsApproval @Notifications
  Scenario Outline: Check if notifications are send to approval admin in approval flow
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON '<approvalProcess_approvalThreshold>'
    And Approval manager '<approval_login_password>' checks current notifications list count
    When New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '<points_value>'
    Then Approval manager '<approval_login_password>' checks current notifications list count was increased
    And Approval manager '<approval_login_password>' mark all notifications as read
    And Approval manager '<approval_login_password>' checks current notifications list is empty

    Examples:
      | approval_login_password                             | approvalProcess_approvalThreshold | points_value |
      | api_test_admin_approval_1@api.iat.admin.pl,password | ALL,null                          | 2            |
      | api_test_admin_approval_1@api.iat.admin.pl,password | THRESHOLD,25                      | 30           |


  @Regression2 @Critical @PositiveCase @EcardsApproval @Notifications
  Scenario Outline: Check if notifications are send to Manager admin after aprove/reject pending approval
    Given Partner 'api_test_super_admin_1@api.iat.admin.pl,password' approvalProcess is turned ON '<approvalProcess_approvalThreshold>'
    And IAT admin user 'api_test_manager_department_1@api.iat.admin.pl,password' checks current notifications list count
    And New random Ecard is created by manager 'api_test_manager_department_1@api.iat.admin.pl,password' with points value '<points_value>'
    And IAT admin user 'api_test_manager_department_1@api.iat.admin.pl,password' checks current notifications list count was not changed
    When Approval manager '<approval_login_password>' process '<action>' pending approvals from list
    Then IAT admin user 'api_test_manager_department_1@api.iat.admin.pl,password' checks current notifications list count was increased
    And IAT admin user 'api_test_manager_department_1@api.iat.admin.pl,password' mark all notifications as read
    And IAT admin user 'api_test_manager_department_1@api.iat.admin.pl,password' checks current notifications list is empty

    Examples:
      | approval_login_password                             | approvalProcess_approvalThreshold | points_value | action  |
      | api_test_admin_approval_1@api.iat.admin.pl,password | ALL,null                          | 2            | approve |
      | api_test_admin_approval_1@api.iat.admin.pl,password | THRESHOLD,25                      | 30           | reject  |