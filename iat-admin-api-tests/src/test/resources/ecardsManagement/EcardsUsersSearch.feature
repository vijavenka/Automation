Feature: IAT Admin HR - search users
  As admin/manager user
  I want to know which user is in specific department
  To be able to select them and awards points to them


  @Regression @PositiveCase @EcardsUsersSearch
  Scenario Outline: "Browse for users" from specific department
    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get users from department call for '<departmentName>', '<search>'
    Then Get users from department call returns proper data '<search>', '<departmentName>'

  @qa
    Examples:
      | login_password                                             | departmentName             | search                                         | managerSharePointsRange |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 1 [A]     | null                                           | SAME_COMPANY            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 1 [B]     | null                                           | SAME_COMPANY            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 2 [A.1]   | null                                           | SAME_COMPANY            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 3 [A.1.1] | null                                           | SAME_COMPANY            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 3 [A.1.2] | null                                           | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 1 [A]     | null                                           | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 1 [B]     | null                                           | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 2 [A.1]   | null                                           | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 3 [A.1.1] | null                                           | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 3 [A.1.2] | null                                           | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 1 [A]     | null                                           | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 1 [B]     | null                                           | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 2 [A.1]   | null                                           | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 3 [A.1.1] | null                                           | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 3 [A.1.2] | null                                           | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 1 [A]     | null                                           | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 1 [B]     | null                                           | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 2 [A.1]   | null                                           | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 3 [A.1.1] | null                                           | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 3 [A.1.2] | null                                           | SAME_COMPANY            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 1 [A]     | null                                           | SAME_DEPARTMENT         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 1 [B]     | null                                           | SAME_DEPARTMENT         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 2 [A.1]   | null                                           | SAME_DEPARTMENT         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 3 [A.1.1] | null                                           | SAME_DEPARTMENT         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | Department level 3 [A.1.2] | null                                           | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 1 [A]     | null                                           | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 1 [B]     | null                                           | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 2 [A.1]   | null                                           | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 3 [A.1.1] | null                                           | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 3 [A.1.2] | null                                           | SAME_DEPARTMENT         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 1 [A]     | null                                           | SAME_DEPARTMENT         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 2 [A.1]   | null                                           | SAME_DEPARTMENT         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 3 [A.1.1] | null                                           | SAME_DEPARTMENT         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 3 [A.1.2] | null                                           | SAME_DEPARTMENT         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 2 [A.1]   | null                                           | SAME_DEPARTMENT         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 3 [A.1.1] | null                                           | SAME_DEPARTMENT         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 3 [A.1.2] | null                                           | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 1 [B]     | User_1                                         | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 1 [A]     | api_test_manager_department_1@api.iat.admin.pl | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 2 [A.1]   | User_A_1                                       | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department level 2 [A.1]   | api_test_user.a_2@api.iat.admin.pl             | SAME_COMPANY            |


  @Regression @NegativeCase @EcardsUsersSearch
  Scenario Outline: "Browse for users" from specific department - Error message validation
    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get users from department call for incorrect data '<departmentName>', '<search>', '<status>'
    Then Get users from department call for incorrect data returns error message '<status>', '<error>', '<message_or_errorsJson>'

  @qa
    Examples:
      | login_password                                             | departmentName                | search | managerSharePointsRange | status | error        | message_or_errorsJson                                                                       |
      | not_exist,password2                                        | 112                           | test   | SAME_COMPANY            | 401    | Unauthorized | Full authentication is required to access this resource                                     |
      # user have no any rights in system
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd     | Department level 1 [A]        | null   | SAME_DEPARTMENT         | 403    | Forbidden    | Access is denied                                                                            |
      #Not existing department id
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department not exists         | null   | SAME_COMPANY            | 400    | null         | {"errors":[{"message":"You are not allowed to use specified department","fieldName":null}]} |
      ##Department from other company
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | Department from other company | null   | SAME_COMPANY            | 400    | null         | {"errors":[{"message":"You are not allowed to use specified department","fieldName":null}]} |
      ##managers cannot search in parent departments if managerSharePointsRange = SAME_DEPARTMENT
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | Department level 1 [B]        | null   | SAME_DEPARTMENT         | 400    | null         | {"errors":[{"message":"You are not allowed to use specified department","fieldName":null}]} |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | Department level 1 [A]        | null   | SAME_DEPARTMENT         | 400    | null         | {"errors":[{"message":"You are not allowed to use specified department","fieldName":null}]} |


  @Regression @PositiveCase @EcardsUsersSearch
  Scenario Outline: "Browse for users" from specific department - search for direct manager
    Given Partner pointsSharing scope for manager to user is set to 'SAME_COMPANY', sharePointsWithManager is set to '<sharePointsWithManager>'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get users from department call for '<departmentName>', '<search>'
    Then Get manager from department call returns proper data '<managerEmail>', '<sharePointsWithManager>'

  @qa
    Examples:
      | login_password                                               | departmentName           | managerEmail                                      | search                                            | sharePointsWithManager |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | Department level 2 [A.1] | api_test_manager_subdepartment_1@api.iat.admin.pl | null                                              | true                   |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | Department level 2 [A.1] | api_test_manager_subdepartment_1@api.iat.admin.pl | null                                              | false                  |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | Department level 2 [A.1] | api_test_manager_subdepartment_1@api.iat.admin.pl | api_test_manager_subdepartment_1@api.iat.admin.pl | true                   |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | Department level 2 [A.1] | api_test_manager_subdepartment_1@api.iat.admin.pl | api_test_manager_subdepartment_1@api.iat.admin.pl | false                  |


  @Regression @PositiveCase @EcardsUsersSearch
  Scenario Outline: "Search for users" from company structure - call used to search in available user scope but without need of providing departmentId
    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get users from company call for '<search>'
    Then Get users from company call returns proper data '<search>', '<managerSharePointsRange>', '<resultsCondition>'

  @qa
    Examples:
      | login_password                                               | search                                              | managerSharePointsRange | resultsCondition |
      #user searching for himself
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | api_test_super_admin_1@api.iat.admin.pl             | SAME_COMPANY            | searchHimself    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | SUPER_ADMIN                                         | SAME_COMPANY            | searchHimself    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | api_test_admin_1@api.iat.admin.pl                   | SAME_COMPANY            | searchHimself    |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | api_test_manager_department_1@api.iat.admin.pl      | SAME_COMPANY            | searchHimself    |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | api_test_manager_subdepartment_1@api.iat.admin.pl   | SAME_COMPANY            | searchHimself    |
      | api_test_manager_department_2@api.iat.admin.pl,P@ssw0rd      | api_test_manager_department_2@api.iat.admin.pl      | SAME_COMPANY            | searchHimself    |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | api_test_manager_subdepartment_1.1@api.iat.admin.pl | SAME_COMPANY            | searchHimself    |
      # keyword search
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | admin                                               | SAME_COMPANY            | keyword          |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | admin                                               | SAME_COMPANY            | keyword          |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | admin                                               | SAME_COMPANY            | keyword          |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | admin                                               | SAME_COMPANY            | keyword          |
      | api_test_manager_department_2@api.iat.admin.pl,P@ssw0rd      | admin                                               | SAME_COMPANY            | keyword          |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | admin                                               | SAME_COMPANY            | keyword          |
      # scope validation
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | null                                                | SAME_COMPANY            | keyword          |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | null                                                | SAME_DEPARTMENT         | keyword          |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | null                                                | SAME_DEPARTMENT         | keyword          |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | api_test_super_admin_1@api.iat.admin.pl             | SAME_COMPANY            | keyword          |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | api_test_super_admin_1@api.iat.admin.pl             | SAME_COMPANY            | keyword          |
      # out of scope
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | api_test_super_admin_1@api.iat.admin.pl             | SAME_DEPARTMENT         | outOfScope       |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | api_test_super_admin_1@api.iat.admin.pl             | SAME_DEPARTMENT         | outOfScope       |


  @Regression @PositiveCase @EcardsUsersSearch
  Scenario Outline: "Search for users" from company structure - search for direct manager
    Given Partner pointsSharing scope for manager to user is set to 'SAME_COMPANY', sharePointsWithManager is set to '<sharePointsWithManager>'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get users from company call for '<search>'
    Then Get manager from company call returns proper data '<managerEmail>', '<sharePointsWithManager>'

  @qa
    Examples:
      | login_password                                               | managerEmail                                      | search                                            | sharePointsWithManager |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | api_test_manager_subdepartment_1@api.iat.admin.pl | null                                              | true                   |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | api_test_manager_subdepartment_1@api.iat.admin.pl | null                                              | false                  |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | api_test_manager_subdepartment_1@api.iat.admin.pl | api_test_manager_subdepartment_1@api.iat.admin.pl | true                   |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | api_test_manager_subdepartment_1@api.iat.admin.pl | api_test_manager_subdepartment_1@api.iat.admin.pl | false                  |


