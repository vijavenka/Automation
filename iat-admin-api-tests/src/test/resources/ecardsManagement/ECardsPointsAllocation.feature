Feature: IAT Admin HR - points allocation
  As ecards admin user
  I want to have option to get ecards partners list, get departments structure, allocate points to partner and to department, view allocations history
  To be able to properly manage all ecards points allocation activities



  # Partners allocation

  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Get partners - contract validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get partners call is made
    Then Get partners call returns proper contract

  @qa
    Examples:
      | login_password                                      |
      # Platform admin authority for awarding partners
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd |


  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Get partners - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get partners call is made for incorrect data '<status>'
    Then Get partners call returns proper contract for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                             | status | error        | message                                                 |
      # not existing admin
      | not_existing,password2                     | 401    | Unauthorized | Full authentication is required to access this resource |
      # user without priviliges
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd | 403    | Forbidden    | Access is denied                                        |


  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Add virtual points to partner by IAT Platform admin
    Given IAT Admin user is logged in with credentials '<partnerAdminCredentials>'
    And Current ecards points allocation limit for partner is known
    When Iat Platform Admin '<platformAdminCredentials>' distribute virtual points to partner call is made for data '<jsonData>'
    And IAT Admin user is logged in with credentials '<partnerAdminCredentials>'
    Then Ecards points allocation limit for partner was properly updated
    And Allocated points value was increased after allocate points to partner by IAT Platform Admin '<platformAdminCredentials>'

  @qa
    Examples:
      | platformAdminCredentials                            | partnerAdminCredentials                          | jsonData                                                                              |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"receiverId": "7777", "message":"Test message Partner Allocation1", "amount": 12000} |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"receiverId": "7777", "message":"Test message Partner Allocation2", "amount": 1300}  |

  @Regression @NegativeCase @VirtualPointsAllocation
  Scenario Outline: Add virtual points to partner - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Distribute virtual points to '<level>' call for incorrect data '<jsonBody>', '<status>'
    Then Distribute virtual points to '<level>' call for incorrect data returns error message '<status>', '<error_fieldName>', '<message>'

  @qa
    Examples:
      | login_password                                         | level   | jsonBody                                                          | status | error_fieldName | message                                                                                                                                                                 |
      | iat_admin,password2                                    | partner | {}                                                                | 401    | Unauthorized    | Full authentication is required to access this resource                                                                                                                 |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | partner | {"receiverId":7777, "message":"amount equals 0", "amount": 10}    | 403    | Forbidden       | Access is denied                                                                                                                                                        |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | partner | {"receiverId":7777, "message":"amount equals 0", "amount": 10}    | 403    | Forbidden       | Access is denied                                                                                                                                                        |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd    | partner | {}                                                                | 400    | null            | {"errors":[{"message":"receiver Id cannot be empty","fieldName":"allocations[0].receiverId"},{"message":"amount cannot be empty","fieldName":"allocations[0].amount"}]} |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd    | partner | {"receiverId":0, "message":"receiver not existing", "amount": 12} | 400    | null            | {"errors":[{"message":"There is no partner with given id","fieldName":"partner.id"}]}                                                                                   |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd    | partner | {"receiverId":7777, "message":"minus amount", "amount": -12}      | 400    | null            | {"errors":[{"message":"Amount must to be greater then 0","fieldName":"allocations[0].amount"}]}                                                                         |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd    | partner | {"receiverId":7777, "message":"amount equals 0", "amount": 0}     | 400    | null            | {"errors":[{"message":"Amount must to be greater then 0","fieldName":"allocations[0].amount"}]}                                                                         |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd    | partner | {"receiverId":0, "message":"receiver not existing", "amount":"" } | 400    | null            | {"errors":[{"message":"amount cannot be empty","fieldName":"allocations[0].amount"}]}                                                                                   |


  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Get Partners virtual points allocation history
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get virtual points allocation history call is made for '<level>' and filters '<sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points>'
    Then Get virtual points allocation history call for '<level>' returns proper contract and data for filters '<sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points>'

  @qa
    Examples:
      | login_password                                      | level   | sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points             |
      #For partners allocation history: sorting by: date, who, to, description, amount, points; date ranges; search by: description, who, to, amount, points
      #SORTING
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | date,false,null,null,null,null,null,null,null,null,null,null                                          |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | date,true,null,null,null,null,null,null,null,null,null,null                                           |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,6,null,null,null,null,null,null,null,null                                              |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | points,false,null,10,null,null,null,null,null,null,null,null                                          |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | points,true,null,25,null,null,null,null,null,null,null,null                                           |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | amount,false,null,25,null,null,null,null,null,null,null,null                                          |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | amount,true,null,10,null,null,null,null,null,null,null,null                                           |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | who,false,null,10,null,null,null,null,null,null,null,null                                             |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | who,true,null,25,null,null,null,null,null,null,null,null                                              |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | description,false,null,25,null,null,null,null,null,null,null,null                                     |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | description,true,null,10,null,null,null,null,null,null,null,null                                      |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | to,false,null,null,null,null,null,null,null,null,null,null                                            |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | to,true,null,null,null,null,null,null,null,null,null,null                                             |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | partnerName,false,null,null,null,null,null,null,null,null,null,null                                   |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | partnerName,true,null,null,null,null,null,null,null,null,null,null                                    |
      #FILTERING
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,null,null,null,null,null,Test message Partner Allocation,null,null                |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,null,null,null,null,null,Allocation,null,null                                     |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,null,null,null,null,null,O T,null,null                                            |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,api_test_admin_platform_1@api.iat.admin.pl,null,null,null,null,null,null,null     |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,null,null,Ecard,null,null,null,null,null                                          |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,null,null,null,null,null,null,60.0,null                                           |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,null,null,null,null,null,null,null,1300                                           |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd | partner | null,null,null,null,null,null,null,2016-03-01T12:00:00+00:00,2026-03-08T12:00:00+00:00,null,null,null |


  # Department allocation

  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Add virtual points to departments by admin/manager of parent department
    Given Current ecards points allocation limit for parent '<partner_manager_credentials>' is known
    And Current ecards points allocation limit for department '<departmentName>' is known
    When Partner Admin or manager distribute virtual points to department call is made for data '<departmentName>', '<jsonBody>', '<partner_manager_credentials>'
    Then Current ecards points allocation limit for parent '<partner_manager_credentials>' was properly decreased
    And Current ecards points allocation limit for department '<departmentName>' was properly updated

  @qa
    Examples:
      | departmentName             | jsonBody                                                                                                                                       | partner_manager_credentials                                |
      | Department level 1 [A]     | {"message":"Test message Partner Admin allocate points to Department level 1 [A]", "amount": 5500}                                             | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 |
      | Department level 2 [A.1]   | {"message":"Test message Partner Admin allocate points to sub-department Department level 2 [A.1]", "amount": 1000}                            | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 |
      | Department level 3 [A.1.1] | {"message":"Test message Partner Admin allocate points to sub-department 1.1 of sub-dep Department level 3 [A.1.1]", "amount": 50}             | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 |
      | Department level 3 [A.1.2] | {"message":"Test message Partner Admin allocate points to sub-department 1.2 of sub-dep Department level 3 [A.1.2]", "amount": 20}             | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 |
      | Department level 2 [A.1]   | {"message":"Test message Department Manager allocate points to sub-department Department level 2 [A.1] ", "amount": 41}                        | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    |
      | Department level 3 [A.1.1] | {"message":"Test message Department Manager allocate points to sub-department 1.1 of sub-dep Department level 3 [A.1.1]", "amount": 31}        | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    |
      | Department level 3 [A.1.2] | {"message":"Test message Department Manager allocate points to sub-department 1.2 of sub-dep Department level 3 [A.1.2]", "amount": 21}        | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    |
      | Department level 3 [A.1.1] | {"message":"Test message Department Manager allocate points to sub-department 1.1 of sub-dep Department level 3 [A.1.1]", "amount": 55}        | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd |
      | Department level 3 [A.1.2] | {"message":"Test message Department Manager allocate points to sub-department 1.2 of sub-dep Department level 3 [A.1.2]", "amount": 45}        | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd |
      | Department level 1 [A]     | {"message":"Test message Department Manager-Admin allocate points to his parent department (Department level 1 [A])", "amount": 950}           | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         |
      | Department level 2 [A.1]   | {"message":"Test message Department Manager-Admin allocate points to his own department Department level 2 [A.1] ", "amount": 850}             | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         |
      | Department level 3 [A.1.1] | {"message":"Test message Department Manager-Admin allocate points to sub-department 1.1 of sub-dep Department level 3 [A.1.1]", "amount": 750} | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         |
      | Department level 3 [A.1.2] | {"message":"Test message Department Manager-Admin allocate points to sub-department 1.2 of sub-dep Department level 3 [A.1.2]", "amount": 650} | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd         |


  @Regression @NegativeCase @VirtualPointsAllocation
  Scenario Outline: Add virtual points to department - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Distribute virtual points to '<level>' call for incorrect data '<jsonBody>', '<status>'
    Then Distribute virtual points to '<level>' call for incorrect data returns error message '<status>', '<error_fieldName>', '<message>'

  @qa
    Examples:
      | login_password                                             | level      | jsonBody                                                                                                                                 | status | error_fieldName | message                                                                                                                                                                 |
      | api_test_admin_platform_1@api.iat.admin.pl,password2       | departnemt | {}                                                                                                                                       | 401    | Unauthorized    | Full authentication is required to access this resource                                                                                                                 |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd     | department | {"receiverId":1, "message":"amount equals 0", "amount": 0}                                                                               | 403    | Forbidden       | Access is denied                                                                                                                                                        |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | department | {}                                                                                                                                       | 400    | null            | {"errors":[{"message":"amount cannot be empty","fieldName":"allocations[0].amount"},{"message":"receiver Id cannot be empty","fieldName":"allocations[0].receiverId"}]} |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | department | {"receiverId": 0, "message":"receiver not existing", "amount": 12}                                                                       | 400    | null            | {"errors":[{"message":"There is no department with given id:[0]","fieldName":"department.id"}]}                                                                         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | department | {"receiverId":1, "message":"minus amount", "amount": -12} ]                                                                              | 400    | null            | {"errors":[{"message":"Amount must to be greater then 0","fieldName":"allocations[0].amount"}]}                                                                         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | department | {"receiverId":1, "message":"amount equals 0", "amount": 0}                                                                               | 400    | null            | {"errors":[{"message":"Amount must to be greater then 0","fieldName":"allocations[0].amount"}]}                                                                         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | department | {"receiverId":1, "message":"amount equals 0", "amount": ""}                                                                              | 400    | null            | {"errors":[{"message":"amount cannot be empty","fieldName":"allocations[0].amount"}]}                                                                                   |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | department | {"receiverId": "Department level 1 [A]", "message":"Manager allocate points to his parent department", "amount": 1}                      | 400    | null            | {"errors":[{"message":"You can not allocate points to department: [Department level 1 [A]]","fieldName":"department.id"}]}                                              |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | department | {"receiverId": "Department level 2 [A.1]", "message":"Manager allocate points to his own department", "amount": 1}                       | 400    | null            | {"errors":[{"message":"You can not allocate points to department: [Department level 2 [A.1]]","fieldName":"department.id"}]}                                            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | department | {"receiverId": "Department level 1 [B]", "message":"Manager allocate points to other department (not sub) in same company", "amount": 1} | 400    | null            | {"errors":[{"message":"You can not allocate points to department: [Department level 1 [B]]","fieldName":"department.id"}]}                                              |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | department | {"receiverId": "Department from other company", "message":"Partner Admin allocate points to department from other company", "amount": 1} | 400    | null            | {"errors":[{"message":"There is no department with given id:[221521]","fieldName":"department.id"}]}                                                                    |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd    | department | {"receiverId": "Department from other company", "message":"Manager allocate points to department from other company", "amount": 1}       | 400    | null            | {"errors":[{"message":"There is no department with given id:[221521]","fieldName":"department.id"}]}                                                                    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | department | {"receiverId": "Department level 1 [A]", "message":"allocate huge amount", "amount": 1000000000}                                         | 400    | null            | {"errors":[{"message":"There is too much points to allocate","fieldName":"amount"}]}                                                                                    |


  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Get Departments virtual points allocation history
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get virtual points allocation history call is made for '<level>' and filters '<sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points>'
    Then Get virtual points allocation history call for '<level>' returns proper contract and data for filters '<sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points>'

  @qa
    Examples:
      | login_password                                          | level      | sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points                |
      #For department allocation history: sorting by: date, who, from, to, description, amount, points; date ranges; search by: description, who, from, to
      #Partner Admin Authorization
      #SORTING
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | id,false,null,null,null,null,null,null,null,null,null,null                                               |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | id,true,null,null,null,null,null,null,null,null,null,null                                                |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | date,false,null,null,null,null,null,null,null,null,null,null                                             |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | date,true,null,null,null,null,null,null,null,null,null,null                                              |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | points,false,null,15,null,null,null,null,null,null,null,null                                             |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | points,true,null,10,null,null,null,null,null,null,null,null                                              |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | amount,false,null,null,null,null,null,null,null,null,null,null                                           |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | amount,true,null,null,null,null,null,null,null,null,null,null                                            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | who,false,null,6,null,null,null,null,null,null,null,null                                                 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | who,true,null,5,null,null,null,null,null,null,null,null                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | from,false,null,9,null,null,null,null,null,null,null,null                                                |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | from,true,null,10,null,null,null,null,null,null,null,null                                                |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | to,false,null,10,null,null,null,null,null,null,null,null                                                 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | to,true,null,10,null,null,null,null,null,null,null,null                                                  |
      #FILTERING
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,null,null,null,null,P T,null,null                                               |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,null,null,null,null,sub-department,null,null                                    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,api_test_admin_1@api.iat.admin.pl,null,null,null,null,null,null,null                 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,api_test_manager_department_1@api.iat.admin.pl,null,null,null,null,null,null,null    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,null,Department level 3 [A.1.2],null,null,null,null,null                        |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,Test,null,null,null,null,null,null                                              |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,null,null,null,null,null,0.25,null                                              |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,null,null,null,null,null,null,55                                                |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,null,null,2016-03-01T12:00:00+00:00,null,null,null,null                         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | department | null,null,null,null,null,null,null,null,2016-03-08T12:00:00+00:00,null,null,null                         |
      #For department allocation history: sorting by: date, who, from, to, description, amount, points; date ranges; search by: description, who, from, to
      #Manager Authorization       |
      #SORTING
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | id,false,null,null,null,null,null,null,null,null,null,null                                               |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | id,true,null,null,null,null,null,null,null,null,null,null                                                |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | date,false,null,null,null,null,null,null,null,null,null,null                                             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | date,true,null,null,null,null,null,null,null,null,null,null                                              |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | points,false,null,10,null,null,null,null,null,null,null,null                                             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | points,true,null,25,null,null,null,null,null,null,null,null                                              |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | amount,false,null,null,null,null,null,null,null,null,null,null                                           |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | amount,true,null,null,null,null,null,null,null,null,null,null                                            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | who,false,null,25,null,null,null,null,null,null,null,null                                                |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | who,true,null,10,null,null,null,null,null,null,null,null                                                 |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | from,false,null,25,null,null,null,null,null,null,null,null                                               |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | from,true,null,10,null,null,null,null,null,null,null,null                                                |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | to,false,null,10,null,null,null,null,null,null,null,null                                                 |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | to,true,null,25,null,null,null,null,null,null,null,null                                                  |
      #FILTERING
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,null,null,null,null,null,null,F T,null,null                                               |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,null,null,null,null,null,null,sub-department,null,null                                    |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,null,api_test_manager_department_1@api.iat.admin.pl,null,null,null,null,null,null,null    |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,null,api_test_manager_subdepartment_1@api.iat.admin.pl,null,null,null,null,null,null,null |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,25,null,Test,null,null,null,null,null,null                                                |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,null,null,Test,null,null,null,null,null,null                                              |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,null,null,null,null,2016-03-01T12:00:00+00:00,null,null,null,null                         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | department | null,null,null,null,null,null,null,null,2016-03-08T12:00:00+00:00,null,null,null                         |


  @Regression @NegativeCase @VirtualPointsAllocation
  Scenario Outline: Get virtual points allocation history Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get virtual points allocation history call for incorrect data '<level>', '<sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points>', '<status>'
    Then Get virtual points allocation history call for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                          | level      | sortField_ascending_page_maxResults_who_from_to_dateFrom_dateTo_description_amount_points | status | error        | message                                                 |
      | not_existing,password2                                  | department | null,null,null,null,null,null,null,null,null,null,null,null                               | 401    | Unauthorized | Full authentication is required to access this resource |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | partner    | null,null,null,null,null,null,null,null,null,null,null,null                               | 403    | Forbidden    | Access is denied                                        |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | partner    | null,null,null,null,null,null,null,null,null,null,null,null                               | 403    | Forbidden    | Access is denied                                        |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd  | department | null,null,null,null,null,null,null,null,null,null,null,null                               | 403    | Forbidden    | Access is denied                                        |


  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Get virtual points allocation statistics - contract validation for department
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get virtual points allocation statistics call is made for '<level>' with 'null,null,null,null,null,null,null,null,null,null,null,null'
    And For '<level>' level virtual points allocation statistics equals points allocation limit

  @qa
    Examples:
      | login_password                             | level      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd | department |


  @Regression @PositiveCase @VirtualPointsAllocation
  Scenario Outline: Export points allocation history data - trigger file generation
    Given IAT Admin user is logged in with credentials 'api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd'
    When POST call to export ecards <whatExport> is made LAST_MONTH, 202
    Then Name of the exported ecards <whatExport> file is retrieved
    And It is possible to use GET call in order to download exported ecards

  @qa2 @DO-814
    Examples:
      | whatExport  |
      | awards      |
      | partners    |
      | departments |


  @Regression @NegativeCase @VirtualPointsAllocation
  Scenario Outline: Download file call - error message validation
    Given IAT Admin user is logged in with credentials 'api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd'
    When  GET call to download exported file is made with <filename> filename, <status>
    Then GET call to download exported file for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | filename     | status | error             | message                                                |
      | NOT_EXISTING | 404    | Not Found         | File with provided name is not available for download. |
      | null         | 400    | Missing parameter | Missing parameter key                                  |


  @Regression @NegativeCase @VirtualPointsAllocation
  Scenario Outline: Export points allocation history data - error message validation: no dateFrom/dateTo nor dateRange
    Given IAT Admin user is logged in with credentials 'api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd'
    When POST call to export ecards <whatExport> is made <dateRange>, <status>
    Then POST call to export ecards for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa2 @DO-814
    Examples:
      | whatExport  | dateRange     | status | error       | message                                                                                    |
      | awards      | EMPTY_RANGE   | 400    | Bad Request | Providing parameters dateFrom and dateTo in format [yyyy-MM-dd'T'HH:mm:ssXXX] is required. |
      | partners    | EMPTY_RANGE   | 400    | Bad Request | Providing parameters dateFrom and dateTo in format [yyyy-MM-dd'T'HH:mm:ssXXX] is required. |
      | departments | EMPTY_RANGE   | 400    | Bad Request | Providing parameters dateFrom and dateTo in format [yyyy-MM-dd'T'HH:mm:ssXXX] is required. |
      #
      | awards      | INVALID_RANGE | 400    | Bad Request | Providing parameters dateFrom and dateTo in format [yyyy-MM-dd'T'HH:mm:ssXXX] is required. |
      | partners    | INVALID_RANGE | 400    | Bad Request | Providing parameters dateFrom and dateTo in format [yyyy-MM-dd'T'HH:mm:ssXXX] is required. |
      | departments | INVALID_RANGE | 400    | Bad Request | Providing parameters dateFrom and dateTo in format [yyyy-MM-dd'T'HH:mm:ssXXX] is required. |