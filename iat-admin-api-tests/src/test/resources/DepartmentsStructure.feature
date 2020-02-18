Feature: IAT Admin HR - department structure management
  As ecards admin user
  I want to TODO


  @Regression @PositiveCase @Departments
  Scenario Outline: Get structure - contract validation
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Set Ecards settings call for 'pointsSharing' is made with data '<settingsPointsSharing>'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get structure call is made '<withRoot>'
    Then Get structure call is not affected by the managerSharePointsRange '<login_password>', '<withRoot>'

  @qa
    Examples:
      | login_password                                             | withRoot | settingsPointsSharing                                                                                                                              |
      # Admins
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | false    | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | false    | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | false    | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | false    | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         | false    | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         | false    | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         | true     | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         | true     | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      # Managers
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | false    | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | false    | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | true     | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | true     | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | false    | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | false    | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | true     | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | true     | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |


  @Regression @PositiveCase @Departments
  Scenario Outline: Get structure - manager name validation
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Set Ecards settings call for 'pointsSharing' is made with data '<settingsPointsSharing>'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get structure call is made '<withRoot>'
    Then Proper managerName is assigned to each department

  @qa
    Examples:
      | login_password                                          | withRoot | settingsPointsSharing                                                                                                                              |
      # Admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | false    | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      # Manager
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | false    | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | true     | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |


  @Regression @PositiveCase @Departments
  Scenario Outline: Get valid structure - contract validation
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Set Ecards settings call for 'pointsSharing' is made with data '<settingsPointsSharing>'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get valid structure call is made
    Then Get valid structure call returns proper results '<login_password>'

  @qa
    Examples:
      | login_password                                             | settingsPointsSharing                                                                                                                              |
      # Admins
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd           | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      # Managers
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |


  @Regression @PositiveCase @Departments
  Scenario Outline: Get valid structure - manager name validation
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Set Ecards settings call for 'pointsSharing' is made with data '<settingsPointsSharing>'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get valid structure call is made
    Then Proper managerName is assigned to each department

  @qa
    Examples:
      | login_password                                             | settingsPointsSharing                                                                                                                              |
      # Admins
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |
      # Managers
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}  |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | {"userSharePointsRange": "SAME_COMPANY", "managerSharePointsRange": "SAME_DEPARTMENT", "sharePointsWithManager": false, "approvalProcess": "NONE"} |


  @Regression @PositiveCase @Departments
  Scenario Outline: Get structure - Error message validation
    Given IAT Admin user is trying log in with credentials '<login_password>', '400'
    When Get structure call is made for incorrect data '<withRoot>', '<status>'
    Then Get structure call for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password         | withRoot | status | error        | message                                                 |
      # not existing admin
      | not_existing,password2 | false    | 401    | Unauthorized | Full authentication is required to access this resource |


#    {"name": "random","parentId": 999} - department name will be generated and Config Department level 2 [D.1] will be used as parent department

#  departments management ---------------------------------------------------------------------------------------------------------

  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment
  Scenario Outline: Departments - create department
    Given IAT Admin user is logged in with credentials '<login_password>'
    When New department is created for '<jsonBody>' and status 201
    Then New department will be available and active on departments structure list
#    And Created department can be properly soft deleted when no users assigned to it

  @qa
    Examples:
      | login_password                                   | jsonBody                                                  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} |

  @Regression @NegativeCase @Departments
  Scenario Outline: Departments - create department error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When New department is created for '<jsonBody>' and status <status>
    Then Error message will be thrown for department create/update/delete '<fieldName>', '<message>'

  @qa
    Examples:
      | login_password                                   | jsonBody                                                      | status | fieldName | message                                     |
#      duplicated department
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "duplicated","parentId": "Department level 2 [D.1]"} | 400    | name      | Non unique name: [Department level 2 [D.1]] |
#      not existing parent id
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "duplicated","parentId": 632168234999}               | 400    | parentId  | Invalid parentId: [632168234999]            |


  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment
  Scenario Outline: Departments - update department name
    Given IAT Admin user is logged in with credentials '<login_password>'
    And New department is created for '<createJsonBody>' and status 201
    And New department will be available and active on departments structure list
    When Created department name will be updated with 'random_name' and status 200
    Then New department will be available and active on departments structure list
#    And Created department can be properly soft deleted when no users assigned to it

  @qa
    Examples:
      | login_password                                   | createJsonBody                                            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} |


  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment @deleteUserAfterTest
  Scenario Outline: Departments - update department name with user in it - checking user department update
    Given IAT Admin user is logged in with credentials '<login_password>'
    And New department is created for '<createJsonBody>' and status 201
    And Create new user with following json '<userJsonBody>' call is made '201'
    When Created department name will be updated with 'random_name' and status 200
    Then New department will be available and active on departments structure list
    And User profile was properly updated with new department name

  @qa
    Examples:
      | login_password                                   | createJsonBody                                            | userJsonBody                                                                                                                                                                                                                                                                          |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} | {"employeeNumber": "create 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |

  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment
  Scenario Outline: Departments - update department name error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    And New department is created for '<createJsonBody>' and status 201
    And New department will be available and active on departments structure list
    When Created department name will be updated with 'existing_random_department_name' and status <status>
    Then Error message will be thrown for department create/update/delete '<fieldName>', '<message>'

  @qa
    Examples:
      | login_password                                   | createJsonBody                                            | status | fieldName | message                                   |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} | 400    | name      | Non unique name: [Department level 1 [B]] |

  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment
  Scenario Outline: Departments - delete department from structure
    Given IAT Admin user is logged in with credentials '<login_password>'
    And New department is created for '<jsonBody>' and status 201
    And New department will be available and active on departments structure list
    When Created department will be deleted with 'existing_previously_created_department' and status 204
    Then New department will be available and not active on departments structure list

  @qa
    Examples:
      | login_password                                   | jsonBody                                                  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} |


  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment
  Scenario Outline: Departments - delete multiple empty departments from structure
    Given IAT Admin user is logged in with credentials '<login_password>'
    And New department is created for '<jsonBody>' and status 201
    And New department will be available and active on departments structure list
    And New department is created under previously created department for '<jsonBody2>' and status 201
    And New department will be available and active on departments structure list
    When Created department will be deleted with 'existing_previously_created_department_parent' and status 204
    Then New department will be available and not active on departments structure list

  @qa
    Examples:
      | login_password                                   | jsonBody                                                  | jsonBody2                                      |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} | {"name": "random","parentId": "previous_call"} |


  @Regression @NegativeCase @Departments
  @RemoveCreatedDepartment @deleteUserAfterTest
  Scenario Outline: Departments - delete department from structure error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    And New department is created for '<jsonBody>' and status 201
    And Create new user with following json '<userJsonBody>' call is made '201'
    When Created department will be deleted with '<departmentId>' and status <status>
    Then Error message will be thrown with '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                   | jsonBody                                                  | status | error                | message                                                     | departmentId                           | userJsonBody                                                                                                                                                                                                                                                                          |
#      user in department
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} | 422    | Unprocessable Entity | Department [departmentIdToBeReplace] contains active users. | existing_previously_created_department | {"employeeNumber": "create 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |
#      not existing departmentId
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} | 400    | Bad Request          | Invalid departmentId: [56421384769734287]                   | 56421384769734287                      | {"employeeNumber": "create 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |

  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment
  Scenario Outline: Departments - delete multiple empty departments from structure error message validation - user in some of child departments
    Given IAT Admin user is logged in with credentials '<login_password>'
    And New department is created for '<jsonBody>' and status 201
    And New department is created under previously created department for '<jsonBody2>' and status 201
    And Create new user with following json '<userJsonBody>' call is made '201'
    When Created department will be deleted with '<departmentId>' and status <status>
    Then Error message will be thrown with '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                   | jsonBody                                                  | jsonBody2                                      | status | error                | message                                                           | departmentId                                  | userJsonBody                                                                                                                                                                                                                                                                          |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"name": "random","parentId": "Department level 2 [D.1]"} | {"name": "random","parentId": "previous_call"} | 422    | Unprocessable Entity | Department [parentDepartmentIdToBeReplace] contains active users. | existing_previously_created_department_parent | {"employeeNumber": "create 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |

  @Regression @NegativeCase @Departments @qa
  Scenario: Departments - admin try to create department
    Given IAT Admin user is logged in with credentials 'api_test_admin_1@api.iat.admin.pl,P@ssw0rd'
    When New department is created for '{"name": "random","parentId": "Department level 2 [D.1]"}' and status 403
    Then Error message will be thrown with '403', 'Forbidden', 'Access is denied'

  @Regression @NegativeCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - admin try to update department name
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [D.1]"}' and status 201
    And New department will be available and active on departments structure list
    And IAT Admin user is logged in with credentials 'api_test_admin_1@api.iat.admin.pl,P@ssw0rd'
    When Created department name will be updated with 'existing_random_department_name' and status 403
    Then Error message will be thrown with '403', 'Forbidden', 'Access is denied'

  @Regression @NegativeCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - admin try to delete department
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [D.1]"}' and status 201
    And New department will be available and active on departments structure list
    And IAT Admin user is logged in with credentials 'api_test_admin_1@api.iat.admin.pl,P@ssw0rd'
    When Created department will be deleted with 'existing_previously_created_department' and status 403
    Then Error message will be thrown with '403', 'Forbidden', 'Access is denied'

  @Regression @NegativeCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - admin try to move department
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    Then New department will be available and active on departments [C] structure list
    And IAT Admin user is logged in with credentials 'api_test_admin_1@api.iat.admin.pl,P@ssw0rd'
    When Created department will be moved to Department level 2 [C.2], 403
    Then Error message will be thrown with '403', 'Forbidden', 'Access is denied'


  @Regression @PositiveCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - moving department without users
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    Then New department will be available and active on departments [C] structure list
    When Created department will be moved to Department level 2 [C.2], 200
    Then New department will be available and active on departments [C] structure list

  @Regression @PositiveCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - moving department without users - inactive department to not active
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    And Created department will be deleted with 'existing_previously_created_department_parent' and status 204
    When Backup department will be moved to the latest department, 200
    Then New department will be available and active on departments [C] structure list


  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment @deleteUserAfterTest
  Scenario Outline: Departments - moving department with users
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    And Create new user with following json '<jsonUser>' call is made '201'
    When Created department will be moved to Department level 2 [C.2], 200
    Then New department will be available and active on departments [C] structure list
    And User profile was properly updated with manager name Manager level 2 [C.2]

  @qa
    Examples:
      | jsonUser                                                                                                                                                                                                                                                                                              |
      | {"employeeNumber": "create 1 Regular user", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}    |
      #NBO-9666
      | {"employeeNumber": "create 2 Manager user", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |

  @Regression @PositiveCase @Departments
  @RemoveCreatedDepartment @deleteUserAfterTest
  Scenario Outline: Departments - moving department with users - more levels
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    And New department is created for '{"name": "random","parentId": "previous_call"}' and status 201
    And Create new user with following json '<jsonUser>' call is made '201'
    When Backup department will be moved to Department level 2 [C.2], 200
    Then New department will be available and active on departments [C] structure list
    And User profile was properly updated with manager name Manager level 2 [C.2]

  @qa
    Examples:
      | jsonUser                                                                                                                                                                                                                                                                                              |
      | {"employeeNumber": "create 1 Regular user", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}    |
     #strange enough NBO-9666 is not occurring when there is more levels of departments
      | {"employeeNumber": "create 2 Manager user", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"id": "previously_created_department"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |

  @Regression @NegativeCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - without users - moving department to under its child should not be allowed
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    And New department is created for '{"name": "random","parentId": "previous_call"}' and status 201
    When Backup department will be moved to the latest department, 400
    Then Error message will be thrown for department create/update/delete 'parentId', 'Department cannot be moved to one of its subdepartments'

  @Regression @NegativeCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - without users - moving department to "itself" should not be allowed
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    When Created department will be moved to the latest department, 400
    Then Error message will be thrown for department create/update/delete 'parentId', 'parentId cannot be the same as departmentId'

  @Regression @NegativeCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - moving department without users - parentId of non existing department
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    When Created department will be moved to 9191, 400
    Then Error message will be thrown for department create/update/delete 'parentId', 'Invalid parentId: [9191]'

  @Regression @NegativeCase @Departments @qa
  @RemoveCreatedDepartment
  Scenario: Departments - moving department without users
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    And New department is created for '{"name": "random","parentId": "Department level 2 [C.1]"}' and status 201
    And Created department will be deleted with 'existing_previously_created_department' and status 204
    When Backup department will be moved to the latest department, 400
    Then Error message will be thrown for department create/update/delete 'parentId', 'Department cannot be moved to not active one'