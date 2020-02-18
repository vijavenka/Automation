Feature: IAT Admin HR - users management
  As ecards admin/manager
  I want to have possibility to manage users
  To be able add, edit, delete them


  @Regression @PositiveCase @UsersList
  Scenario Outline: Browse users list - filtering and contract validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get users list call is made with filters '<sortField_ascending_dateFrom_dateTo_departmentName_manager_user_status_page_maxResults>', '200'
    Then Get users list call returns proper contract and data for filters '<sortField_ascending_dateFrom_dateTo_departmentName_manager_user_status_page_maxResults>', '<login_password>'

  @qa
    Examples:
      | login_password                                          | sortField_ascending_dateFrom_dateTo_departmentName_manager_user_status_page_maxResults                                                     |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                                |
      #SORTING
      # createdAt
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | createdAt,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,50                                            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | createdAt,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,50                                             |
       # departmentName
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | departmentName,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                       |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | departmentName,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                        |
      # role
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | role,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | role,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                                |
      # status
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | status,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | status,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                                |
      ## Sorting by id is not supported
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | id,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null             |
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | id,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null              |
      ## Sorting by employeeNumber not supported
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | employeeNumber,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null |
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | employeeNumber,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null  |
      ## will not be working it's impossible to validate it right now -> see ElasticSearch functionality allows sorting only by following rules comment from HS-95
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | user,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                    |
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | user,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                     |
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | manager,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                               |
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | manager,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                |
      #FILTERING
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | createdAt,true,2016-04-08T00:00:00+00:00,2019-04-22T23:00:00+00:00,null,null,null,null,null,null                                           |
      # departmentName                                                    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | createdAt,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,Department level 1 [A],null,null,null,null,null                         |
      # manager - partial name
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,manager,null,null,null,null                                             |
      # manager - email
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,api_test_manager_department_2@api.iat.admin.pl,null,null,null,null      |
      # user - partial name
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,FirstName,null,null,null                                           |
      # user - email
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,api_test_super_admin_1@api.iat.admin.pl,null,null,null             |
      # status
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,DELETED,null,null                                             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,deleted,null,null                                             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,ACTIVE,null,null                                              |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,active,null,null                                              |
      ## not able to sort by one date from/to - agreed to not implement this functionality
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | createdAt,true,2016-04-11T00:00:00+00:00,null,null,null,null,null,null,null                                                   |
      ##| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | createdAt,true,null,2016-03-25T23:00:00+00:00,null,null,null,null,null,null
      #SORTING
      # createdAt
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | createdAt,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                          |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | createdAt,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                           |
      # departmentName
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | departmentName,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | departmentName,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                        |
      # role
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | role,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                                 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | role,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                                  |
      # status
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | status,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                               |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | status,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                                |
      ## Sorting by employeeNumber not supported
      ##| api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | employeeNumber,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                     |
      ##| api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | employeeNumber,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                      |
      ## will not be working it's impossible to validate it right now -> see ElasticSearch functionality allows sorting only by following rules comment from HS-95
      ##| api_test_admin_1@api.iat.admin.pl,password              | user,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                  |
      ##| api_test_admin_1@api.iat.admin.pl,password              | user,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                   |
      ##| api_test_admin_1@api.iat.admin.pl,password              | manager,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                               |
      ##| api_test_admin_1@api.iat.admin.pl,password              | manager,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null |
      #FILTERING
      # date range
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                                |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | createdAt,true,2016-04-18T00:00:00+00:00,2025-04-22T23:00:00+00:00,null,null,null,null,null,null                                           |
      # manager - partial name
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,manager,null,null,null,null                                             |
      # manager - email
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,api_test_manager_department_2@api.iat.admin.pl,null,null,null,null      |
      # user - partial name
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,FirstName,null,null,null                                           |
      # user - email
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,api_test_super_admin_1@api.iat.admin.pl,null,null,null             |
      # status
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,DELETED,null,null                                             |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,deleted,null,null                                             |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,ACTIVE,null,null                                              |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,active,null,null                                              |
      ##not able to sort by one date from/to - agreed to not implement this functionality
      ##| api_test_admin_1@api.iat.admin.pl,password              | createdAt,true,2016-04-11T00:00:00+00:00,null,null,null,null,null,null,null                                                   |
      ##| api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | createdAt,true,null,2016-03-25T23:00:00+00:00,null,null,null,null,null,null                                                   |
      #SORTING
      # createdAt
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | createdAt,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                          |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | createdAt,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                           |
      # departmentName
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | departmentName,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                       |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | departmentName,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                        |
      # role
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | role,false,2015-01-01T00:00:00+00:00,2017-08-23T23:00:00+00:00,null,null,null,null,null,25                                                 |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | role,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                                  |
      # status
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | status,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                               |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | status,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,25                                                |
      ## Sorting by employeeNumber not supported
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | employeeNumber,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                     |
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | employeeNumber,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                      |
      ## will not be working it's impossible to validate it right now -> see ElasticSearch functionality allows sorting only by following rules comment from HS-95
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | user,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                  |
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | user,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                   |
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | manager,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                               |
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | manager,true,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                |
      #FILTERING
      # date range
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,null                                                |
      # departmentName
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,Department level 3 [A.1.1],null,null,null,null,null                          |
      # manager - partial name
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,manager,null,null,null,null                                             |
      # manager - email
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,api_test_manager_department_2@api.iat.admin.pl,null,null,null,null      |
      # user - partial name
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,FirstName ,null,null,null                                          |
      # user - email
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,api_test_manager_subdepartment_1.1@api.iat.admin.pl,null,null,null |
      # status
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,DELETED,null,null                                             |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,deleted,null,null                                             |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,ACTIVE,null,null                                              |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,active,null,null                                              |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,FirstName,null,null,null                                           |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,api_test_manager_subdepartment_1.1@api.iat.admin.pl,null,null,null |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | null,null,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,api_test_manager_subdepartment_1.1@api.iat.admin.pl,null,null,null,null |
      ## not able to sort by one date from/to - agreed to not implement this functionality
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,2016-03-25T00:00:00+00:00,null,null,null,null,null,null,null                                                        |
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | null,null,null,2016-03-25T23:00:00+00:00,null,null,null,null,null,null                                                        |


  @Regression @NegativeCase @UsersList
  Scenario Outline: Browse users list - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get users list call is made with filters '<params>', '<status>'
    Then Get users list call made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                         | params                                                 | status | error        | message                                                 |
      | not_existing,password2                                 | null,null,null,null,null,null,null,null,null,null,null | 401    | Unauthorized | Full authentication is required to access this resource |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | null,null,null,null,null,null,null,null,null,null,null | 403    | Forbidden    | Access is denied                                        |


  @Regression @PositiveCase @Users
  @deleteUserAfterTest
  Scenario Outline: Create, get by id, delete and re-enabling users - contract and data validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Create new user with following json '<jsonBody>' call is made '201'
    Then Get user by id response details are the same as for created user
    And User account properly created for dynamo and mySql
    When Delete user by id 'previous_call' call is made '200'
    Then User account properly deleted for dynamo and mySql
    When Enable user by id 'previous_call' call is made '200'
    Then User account properly created for dynamo and mySql

  @qa
    Examples:
      | login_password                                          | jsonBody                                                                                                                                                                                                                                                                              |
      #super_admin create users
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [D]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27", "companyUsername": "compUser"}      |
      #super_admin create super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create 2", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                          |
#      #super_admin create admin
#      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create 3", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                                |
      #super_admin create manager
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create 4", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                              |
      #super_admin create manager-admin...
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create 5", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                             |
      #admin create users
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create 6", "email": "api.test.user", "name": "FirstName LastName", "gender": "FEMALE", "birthDate": "1999-12-27", "department": {"name": "Department level 1 [D]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}    |
#      #admin create admin
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create 7", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                                |
      #admin create manager
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create 8", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                              |
      ##admin create manager-admin...
      # turned off because test execution take too long, reason of that is probably: too many users in department
      ##| api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create 9", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                             |
      #admin-manager create users
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create 10", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1955-12-27", "department": {"name": "Department level 1 [D]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     |
#      #admin-manager create admin
#      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create 11", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                               |
      #admin-manager create manager
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create 12", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                             |
      ##admin-manager create manager-admin...
      # turned off because test execution take too long, reason of that is probably: too many users in department
      ##| api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create 13", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                            |
      #1st lvl department manager create USER in his department
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create 14", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     |
      #1st lvl department manager create MANAGER in his department
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create 15", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                             |
      #1st lvl department manager create USER in his sub-department
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create 16", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |

  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Create User - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Create new user with following json '<jsonBody>' call is made '<status>'
    Then Create new user call made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                   | jsonBody                                                                                                                                                                                                                                                       | status | error | message                                                                                                                          |
      #invalid credentials
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create Error 13", "email": "api.duplicate2@api.hr.iat.admin", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 400    | null  | {"errors": [ {"message": "User with email api.duplicate2@api.hr.iat.admin already exists in company.", "fieldName": "email" } ]} |
      # duplicated manager/admin account


  @Regression @PositiveCase @Users
  @deleteUserAfterTest
  Scenario Outline: Update user details - contract and data validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    And Create new user with following json '<jsonBody>' call is made '201'
    And Get user by id response details are the same as for created user
    And User account properly created for dynamo and mySql
    When Update user by id 'previous_call' with following json '<jsonBody2>' and '<emailUpdateType>' call is made '200'
    Then Get user by id response details are the same as for updated user
    And Epoints user email is correct after changing email in iat admin '<emailUpdateType>'

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                     | jsonBody2                                                                                                                                                                            | emailUpdateType |
      #update profile fields
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | {"employeeNumber": "Update Before 1", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}     | {"employeeNumber": "Update After 1", "email": "api.test.user", "name": "FirstName LastName Edited", "gender": "MALE", "birthDate": "1986-12-27", "companyStartDate": "1999-12-27"}   | ADMIN_ONLY      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "Update Before 2", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}     | {"employeeNumber": "Update After 1", "email": "api.test.user", "name": "FirstName LastName Edited", "gender": "FEMALE", "birthDate": "1986-12-27", "companyStartDate": "1999-12-27"} | ADMIN_ONLY      |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "Update Before 3", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}     | {"employeeNumber": "Update After 1", "email": "api.test.user", "name": "FirstName LastName Edited", "gender": "MALE", "birthDate": "1986-12-27", "companyStartDate": "1999-12-27"}   | BOTH            |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Update Before 4", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"} | {"employeeNumber": "Update After 1", "email": "api.test.user", "name": "FirstName LastName Edited", "gender": "FEMALE", "birthDate": "1986-12-27", "companyStartDate": "1999-12-27"} | BOTH            |


  @Regression @PositiveCase @Users
  @deleteUserAfterTest @removeGlobalPasswordSetting
  Scenario Outline: Show the prompt to IAT Admin asking whether Admin wants to update epoints email address as well - checking proper flag returned
    Given IAT Admin user is logged in with credentials '<login_password>'
    And Clear Global password settings
    And Set Ecards settings call for 'pointsSharing' is made with data '<pointsSharingSettings>'
    And Create new user with following json '<jsonBody>' call is made '201'
    And Created account is verified on epoints side '<verifiedOnEpointsSide>', '<globalPasswordSet>'
    When Check if the prompt needs to be shown call is done for user 'previous_call'
    Then In response we receive information if prompt needs to be shown according to '<verifiedOnEpointsSide>', '<globalPasswordSet>'

  @qa
    Examples:
      | login_password                                   | jsonBody                                                                                                                                                                                                       | verifiedOnEpointsSide | pointsSharingSettings                                                                                                                                                                           | globalPasswordSet |
      ## account not verified But global password set - user didn't use epoints account yet
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Update email prompt 1", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"} | false                 | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE", "globalPassword": "P@ssw0rd!@@#$%^&*()MLklm"} | true              |
      ## account not verified and global password not set - user didn't use epoints account yet
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Update email prompt 2", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"} | false                 | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}                                               | false             |
      ## account verified and global password not set - user used epoints account already
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Update email prompt 3", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"} | true                  | {"userSharePointsRange": "SAME_DEPARTMENT", "managerSharePointsRange": "SAME_COMPANY", "sharePointsWithManager": true, "approvalProcess": "NONE"}                                               | false             |

  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Update user details - Error message validation
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Create new user with following json 'DEFAULT' call is made '201'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Update user by id '<id>' with following json '<jsonBody2>' and '<emailUpdateType>' call is made '<status>'
    Then Update user by id call made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                         | id            | jsonBody2                                                                                                  | status | error                  | message                                                                                                                                             | emailUpdateType |
      #invalid credentials
      | not_existing,password2                                 | previous_call | {"employeeNumber": "Update After Error 1"}                                                                 | 401    | Unauthorized           | Full authentication is required to access this resource                                                                                             | ADMIN_ONLY      |
      #existing account but without permissions
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | previous_call | {"employeeNumber": "Update After Error 2"}                                                                 | 403    | Forbidden              | Access denied                                                                                                                                       | ADMIN_ONLY      |
      #incorrect field values
      # incorrect id
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | 0             | {"employeeNumber": "Update After Error 3"}                                                                 | 404    | null                   | {"errors": [ {"message": "User with id = 0 not found","fieldName": "id"} ]}                                                                         | ADMIN_ONLY      |
      # incorrect gender
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 4", "gender": "TWO SPIRITS"}                                        | 400    | null                   | {"errors": [ {"message": "Invalid gender.",  "fieldName": "gender"  }] }                                                                            | ADMIN_ONLY      |
      # incorrect birth date
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 5", "birthDate": "1986-27-27"}                                      | 400    | null                   | {"errors": [  {"message": "Incorrect date of birth.", "fieldName": "birthDate" }] }                                                                 | ADMIN_ONLY      |
      # incorrect department
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 6", "department": {"id": "0"}}                                      | 400    | null                   | {"errors": [ {"message": "Invalid department.", "fieldName": "department.id" } ]}                                                                   | ADMIN_ONLY      |
      # incorrect role
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 7", "role": "DOG"}                                                  | 400    | null                   | {"errors": [ {"message": "Invalid user role.", "fieldName": "role" }] }                                                                             | ADMIN_ONLY      |
      # incorrect adminRole
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 8","adminRole": "HIPER_ADMIN"}                                      | 400    | null                   | {"errors": [ {"message": "Invalid admin role.", "fieldName": "adminRole" } ]}                                                                       | ADMIN_ONLY      |
      # incorrect company start date
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 9", "companyStartDate": "1999-27-27"}                               | 400    | null                   | {"errors": [ {"message": "Incorrect company start date.", "fieldName": "companyStartDate" }] }                                                      | ADMIN_ONLY      |
      # company start date in future
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 10", "companyStartDate": "2140-12-27"}                              | 400    | null                   | {"errors": [ {"message": "Company start date should be date in past.", "fieldName": "companyStartDate" } ]}                                         | ADMIN_ONLY      |
      # missing email
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 11", "email": ""}                                                   | 400    | null                   | {"errors": [ {"message": "may not be blank","fieldName": "email"}]}                                                                                 | ADMIN_ONLY      |
      # missing department TODO: error message returned twice (not reported)
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 12", "department": {}}                                              | 400    | null                   | {"errors": [ {"message": "Invalid department.", "fieldName": "department.id" }, {"message": "Invalid department.", "fieldName": "department.id" }]} | ADMIN_ONLY      |
      # duplicated epoints <-> user account
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 13", "email": "api.duplicate2@api.hr.iat.admin"}                    | 400    | null                   | {"errors": [ {"message": "User with email api.duplicate2@api.hr.iat.admin already exists in company.", "fieldName": "email" } ]}                    | ADMIN_ONLY      |
      # duplicated manager/admin account
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 14", "email": "api.duplicate@api.hr.iat.admin" , "role": "MANAGER"} | 400    | null                   | {"errors": [ {"message": "User with username api.duplicate@api.hr.iat.admin already exists in epoints admin.", "fieldName": "email" } ]}            | ADMIN_ONLY      |
      # duplicated epoints email not in company when updating with flag emailUpdateType:BOTH
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 15", "email": "emailfortest@wp.pl"}                                 | 400    | null                   | {"errors": [ {"message": "Epoints user with email emailfortest@wp.pl already exist.", "fieldName": "email" } ]}                                     | BOTH            |
      # wrong value of parameter emailUpdateType
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | previous_call | {"employeeNumber": "Update After Error 15", "email": "api.test.user"}                                      | 400    | Argument type mismatch | Argument type mismatch exception emailUpdateType                                                                                                    | WRONG           |


  @Daily @PositiveCase @Users
  @deleteUserAfterTest
  Scenario Outline: Create & update user with specific department - contract and data validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Create new user with following json '<jsonBody>' call is made '201'
    Then Get user by id response details are the same as for created user
    When Update user by id 'previous_call' with following json '<jsonBody2>' and 'ADMIN_ONLY' call is made '200'
    Then Get user by id response details are the same as for updated user

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                                                                    | jsonBody2                                                                                        |
      ##superadmin
      # Create in dep lvl 1 update to dep lvl 2
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | {"employeeNumber": "create Department 1", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "Update Department 1", "department": {"name": "Department level 2 [A.1]"}}    |
      # Create in dep lvl 2 update to dep lvl 1
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | {"employeeNumber": "create Department 2", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | {"employeeNumber": "Update Department 2", "department": {"name": "Department level 1 [A]"}}      |
      # Create in dep lvl 3 update to dep lvl 2
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | {"employeeNumber": "create Department 3", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "Update Department 3",  "department": {"name": "Department level 2 [A.1]"}}   |
      # Create in dep lvl 1 update to dep lvl 3
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | {"employeeNumber": "create Department 4", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "Update Department 4", "department": {"name": "Department level 3 [A.1.1]"}}  |
#      ## admin  - role will be removed
#      # Create in dep lvl 1 update to dep lvl 2
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "create Department 5", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}            | {"employeeNumber": "Update Department 5", "department": {"name": "Department level 2 [A.1]"}}    |
#      # Create in dep lvl 2 update to dep lvl 1
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "create Department 6", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}          | {"employeeNumber": "Update Department 6", "department": {"name": "Department level 1 [A]"}}      |
#      # Create in dep lvl 3 update to dep lvl 2
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "create Department 7", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "Update Department 7", "department": {"name": "Department level 2 [A.1]"}}    |
#      # Create in dep lvl 1 update to dep lvl 3
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "create Department 8", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}            | {"employeeNumber": "Update Department 8", "department": {"name": "Department level 3 [A.1.1]"}}  |
      ## admin-manager
      # Create in dep lvl 1 update to dep lvl 2
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"employeeNumber": "create Department 9", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}            | {"employeeNumber": "Update Department 9", "department": {"name": "Department level 2 [A.1]"}}    |
      # Create in dep lvl 2 update to dep lvl 1
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"employeeNumber": "create Department 10", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "Update Department 10", "department": {"name": "Department level 1 [A]"}}     |
      # Create in dep lvl 3 update to dep lvl 2
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"employeeNumber": "create Department 11", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "Update Department 11", "department": {"name": "Department level 2 [A.1]"}}   |
      # Create in dep lvl 1 update to dep lvl 3
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"employeeNumber": "create Department 12", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}           | {"employeeNumber": "Update Department 12", "department": {"name": "Department level 3 [A.1.1]"}} |
      ## manager 1st lvl department
      # Create in dep lvl 1 update to dep lvl 2
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Department 13", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}           | {"employeeNumber": "Update Department 13", "department": {"name": "Department level 2 [A.1]"}}   |
      # Create in dep lvl 2 update to dep lvl 1
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Department 14", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "Update Department 14", "department": {"name": "Department level 1 [A]"}}     |
      # Create in dep lvl 3 update to dep lvl 2
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Department 15", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "Update Department 15", "department": {"name": "Department level 2 [A.1]"}}   |
      # Create in dep lvl 1 update to dep lvl 3
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Department 16", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}           | {"employeeNumber": "Update Department 16", "department": {"name": "Department level 3 [A.1.1]"}} |
      # Create in dep lvl 3 update to dep lvl 3
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Department 17", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.2]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "Update Department 17", "department": {"name": "Department level 3 [A.1.3]"}} |
      ## manager sub-department
      # Create in dep lvl 2 update to dep lvl 3
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "create Department 18", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "Update Department 18", "department": {"name": "Department level 3 [A.1.1]"}} |
      # Create in dep lvl 3 update to dep lvl 2
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "create Department 19", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "Update Department 19", "department": {"name": "Department level 2 [A.1]"}}   |
      # Create in dep lvl 3 update to dep lvl 3
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "create Department 20", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.2]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "Update Department 20", "department": {"name": "Department level 3 [A.1.3]"}} |
      ##  manager sub, sub-department
      # Create in dep lvl 3 update to dep lvl 3
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create Department 21", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "Update Department 18", "department": {"name": "Department level 3 [A.1.1]"}} |


  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Create user with specific department - Error message validation
    Given Partner pointsSharing scope for manager to user is set to 'SAME_COMPANY', sharePointsWithManager is set to 'false'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Create new user with following json '<jsonBody>' call is made '<status>'
    Then Create new user call made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                                                                     | status | error | message                                                                         |
      # sub-department manager try create user in parent department
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "create - Department Error 1", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |
      # sub, sub-department manager try create user in parent department
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create - Department Error 2", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |
      # sub, sub-department manager try create user in parent parent department
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create - Department Error 3", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |
      # sub, sub-department manager try create user in sub, sub department but other branch
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create - Department Error 4", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.2]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |


  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Update user with specific department - Error message validation
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Create new user with following json '<jsonBody>' call is made '201'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Update user by id 'previous_call' with following json '<jsonBody2>' and 'ADMIN_ONLY' call is made '<status>'
    Then Update user by id call made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                                                                                | jsonBody2                                                                                               | status | error | message                                                                         |
      # sub-department manager try update user to parent department
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "Create for update - Department Error 1", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | {"employeeNumber": "update - Department Error 1",  "department": {"name": "Department level 1 [A]"}}    | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |
      # sub, sub-department manager try update user to parent department
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Create for update - Department Error 2", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Department Error 2", "department": {"name": "Department level 2 [A.1]"}}   | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |
      # sub, sub-department manager try update user to parent parent department
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Create for update - Department Error 3", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Department Error 3", "department": {"name": "Department level 1 [A]"}}     | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |
      # sub, sub-department manager try update user to sub, sub department but other branch
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Create for update - Department Error 4", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Department Error 4", "department": {"name": "Department level 3 [A.1.2]"}} | 400    | null  | {"errors": [ {"message": "Invalid department.","fieldName": "department.id"} ]} |


  @Daily @PositiveCase @Users
  @deleteUserAfterTest
  Scenario Outline: Create & update user with specific role - contract and data validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Create new user with following json '<jsonBody>' call is made '201'
    Then Get user by id response details are the same as for created user
    And User account properly created for dynamo and mySql
    When Update user by id 'previous_call' with following json '<jsonBody2>' and 'ADMIN_ONLY' call is made '200'
    Then Get user by id response details are the same as for updated user
    And User account properly updated for dynamo and mySql

  @qa
    Examples:
      | login_password                                          | jsonBody                                                                                                                                                                                                                                           | jsonBody2                                                                       |
      #SUPER-ADMIN
      #super_admin -> user to manager
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 1", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "update Role 1", "role": "MANAGER", "adminRole": "NONE"}     |
      #super_admin -> user to admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 2", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "update Role 2", "role": "USER", "adminRole": "ADMIN"}       |
      #super_admin -> user to admin-manager
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 3", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "update Role 3", "role": "MANAGER", "adminRole": "ADMIN"}    |
      #super_admin -> user to super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 4", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "update Role 4", "role": "USER", "adminRole": "SUPER_ADMIN"} |
      #super_admin -> manager to admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 5", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      | {"employeeNumber": "update Role 5", "role": "USER", "adminRole": "ADMIN"}       |
      #super_admin -> manager to super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 6", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      | {"employeeNumber": "update Role 6", "role": "USER", "adminRole": "SUPER_ADMIN"} |
      #super_admin -> manager to user
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 7", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      | {"employeeNumber": "update Role 7", "role": "USER", "adminRole": "NONE"}        |
      #super_admin -> admin to super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 8", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 8", "role": "USER", "adminRole": "SUPER_ADMIN"} |
      #super_admin -> admin to manager
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 9", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 9", "role": "MANAGER", "adminRole": "NONE"}     |
      #super_admin -> admin to user
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 10", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update Role 10", "role": "USER", "adminRole": "NONE"}       |
      #super_admin -> super admin to admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 11", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update Role 11", "role": "USER", "adminRole": "ADMIN"}      |
      #super_admin -> super admin to manager
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 12", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update Role 12", "role": "MANAGER", "adminRole": "NONE"}    |
      #super_admin -> super admin to user
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | {"employeeNumber": "create Role 13", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update Role 13", "role": "USER", "adminRole": "NONE"}       |
#      #ADMIN - Role will be removed
#      #admin -> user to manager
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create Role 14", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 14", "role": "MANAGER", "adminRole": "NONE"}    |
#      #admin -> user to admin
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create Role 15", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 15", "role": "USER", "adminRole": "ADMIN"}      |
#      #admin -> user to admin-manager
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create Role 16", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 16", "role": "MANAGER", "adminRole": "ADMIN"}   |
#      #admin -> manager to admin
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create Role 17", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "update Role 17", "role": "USER", "adminRole": "ADMIN"}      |
#      #admin -> manager to user
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create Role 18", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "update Role 18", "role": "USER", "adminRole": "NONE"}       |
#      #admin -> admin to manager
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create Role 19", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update Role 19", "role": "MANAGER", "adminRole": "NONE"}    |
#      #admin -> admin to user
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create Role 20", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update Role 20", "role": "USER", "adminRole": "NONE"}       |
      #ADMIN-MANAGER
      #admin_manager -> user to manager
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Role 21", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 21", "role": "MANAGER", "adminRole": "NONE"}    |
      #admin_manager -> user to admin
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Role 22", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 22", "role": "USER", "adminRole": "ADMIN"}      |
      #admin_manager -> user to admin-manager
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Role 23", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 23", "role": "MANAGER", "adminRole": "ADMIN"}   |
      #admin_manager -> manager to admin
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Role 24", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "update Role 24", "role": "USER", "adminRole": "ADMIN"}      |
      #admin_manager -> manager to user
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Role 25", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "update Role 25", "role": "USER", "adminRole": "NONE"}       |
      #admin_manager -> admin to manager
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Role 26", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update Role 26", "role": "MANAGER", "adminRole": "NONE"}    |
      #admin_manager -> admin to user
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create Role 27", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update Role 27", "role": "USER", "adminRole": "NONE"}       |
      #MANAGER
      #manager -> user to manager
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create Role 28", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update Role 28", "role": "MANAGER", "adminRole": "NONE"}    |
      #manager -> manager to user
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create Role 29", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "update Role 29", "role": "USER", "adminRole": "NONE"}       |


  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Create user with specific role - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Create new user with following json '<jsonBody>' call is made '403'
    Then Create new user call made for incorrect data returns error message '403', 'Forbidden', 'Access denied'

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                                                                      |
#      #ADMIN - role will be removed
#      #admin -> super admin
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "create - Role Error 1", "email": "api.test.user", "name": "FirstName LastName",  "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      |
      #ADMIN_MANAGER
      #admin-manager -> super admin
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"employeeNumber": "create - Role Error 2", "email": "api.test.user", "name": "FirstName LastName",  "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      |
      #MANAGER
      #manager -> super admin
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create - Role Error 3", "email": "api.test.user", "name": "FirstName LastName",  "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      |
#      #manager -> admin
#      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create - Role Error 4", "email": "api.test.user", "name": "FirstName LastName",  "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}            |
      # sub-department manager try create superadmin
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "create - Role Error 5", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   |
      # sub, sub-department manager try create superadmin
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create - Role Error 6", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} |


  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Update user to specific role - Error message validation
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Create new user with following json '<jsonBody>' call is made '201'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Update user by id 'previous_call' with following json '<jsonBody2>' and 'ADMIN_ONLY' call is made '403'
    Then Update user by id call made for incorrect data returns error message '403', 'Forbidden', 'Access denied'

  @qa
    Examples:
      | login_password                                          | jsonBody                                                                                                                                                                                                                                                            | jsonBody2                                                                                |
      #ADMIN
      #admin -> user to super admin
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create for update - Role Error 1", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "update - Role Error 1", "role": "USER", "adminRole": "SUPER_ADMIN"}  |
      #admin -> manager to super admin
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create for update - Role Error 2", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      | {"employeeNumber": "update - Role Error 2", "role": "USER", "adminRole": "SUPER_ADMIN"}  |
      #admin -> admin to super admin
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create for update - Role Error 3", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update - Role Error 3", "role": "USER", "adminRole": "SUPER_ADMIN"}  |
      #admin -> super admin to user
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create for update - Role Error 4", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}  | {"employeeNumber": "update - Role Error 4", "role": "USER", "adminRole": "NONE"}         |
      #admin -> super admin to manager
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create for update - Role Error 5", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}  | {"employeeNumber": "update - Role Error 5", "role": "MANAGER", "adminRole": "NONE"}      |
      #admin -> super admin to admin
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"employeeNumber": "create for update - Role Error 6", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}  | {"employeeNumber": "update - Role Error 6", "role": "USER", "adminRole": "ADMIN"}        |
      #ADMIN_MANAGER
      #admin_manager -> user to super admin
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create for update - Role Error 7", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}         | {"employeeNumber": "update - Role Error 7", "role": "USER", "adminRole": "SUPER_ADMIN"}  |
      #admin_manager -> manager to super admin
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create for update - Role Error 8", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}      | {"employeeNumber": "update - Role Error 8", "role": "USER", "adminRole": "SUPER_ADMIN"}  |
      #admin_manager -> admin to super admin
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create for update - Role Error 9", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update - Role Error 9", "role": "USER", "adminRole": "SUPER_ADMIN"}  |
      #admin_manager -> super admin to user
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create for update - Role Error 10", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Role Error 10", "role": "USER", "adminRole": "NONE"}        |
      #admin_manager -> super admin to manager
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create for update - Role Error 11", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Role Error 11", "role": "MANAGER", "adminRole": "NONE"}     |
      #admin_manager -> super admin to admin
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      | {"employeeNumber": "create for update - Role Error 12", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Role Error 12", "role": "USER", "adminRole": "ADMIN"}       |
      #MANAGER
      #manager -> user to super admin
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 13", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update - Role Error 13", "role": "USER", "adminRole": "SUPER_ADMIN"} |
      #manager -> manager to super admin
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 14", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "update - Role Error 14", "role": "USER", "adminRole": "SUPER_ADMIN"} |
      #manager -> admin to super admin
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 15", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update - Role Error 15", "role": "USER", "adminRole": "SUPER_ADMIN"} |
      #manager -> user to admin
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 16", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}        | {"employeeNumber": "update - Role Error 16", "role": "USER", "adminRole": "ADMIN"}       |
      #manager -> manager to admin
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 17", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}     | {"employeeNumber": "update - Role Error 17", "role": "USER", "adminRole": "ADMIN"}       |
      #manager -> super admin to admin
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 18", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Role Error 18", "role": "USER", "adminRole": "ADMIN"}       |
      #manager -> super admin to manager
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 19", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Role Error 19", "role": "MANAGER", "adminRole": "NONE"}     |
      #manager -> super admin to user
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 20", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"employeeNumber": "update - Role Error 20", "role": "USER", "adminRole": "NONE"}        |
      #manager -> admin to manager
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 21", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update - Role Error 21", "role": "MANAGER", "adminRole": "NONE"}     |
      #manager -> admin to user
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "create for update - Role Error 22", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | {"employeeNumber": "update - Role Error 22", "role": "USER", "adminRole": "NONE"}        |


  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Get user by id - Error message validation
    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false'
    And Create new user with following json '<jsonBody>' call is made '201'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Get user by id '<id>' call is made '<status>'
    Then Get user by id call made for incorrect data returns error message '<status>', '<error>', '<message>' - enriched message

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                                                                                                    | id            | status | error        | message                                                                    | managerSharePointsRange |
      #invalid credentials
      | not_existing,password2                                       | {"employeeNumber": "Get by id Error 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call | 401    | Unauthorized | Full authentication is required to access this resource                    | SAME_DEPARTMENT         |
      #existing account butwithout permissions
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd       | {"employeeNumber": "Get by id Error 2", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 12345         | 403    | Forbidden    | Access is denied                                                           | SAME_DEPARTMENT         |
      # Get user with not existing id
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Get by id Error 3", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 0             | 404    | null         | {"errors": [ {"message": "User with id 0 not found","fieldName": "id"} ]}  | SAME_DEPARTMENT         |
      # sub-department manager get USER in parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "Get by id Error 4", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_DEPARTMENT         |
      # sub-department manager get user in parent department when scope is set to SAME_COMPANY (HS-92)
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "Get by id Error 5", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                              | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_COMPANY            |
      # sub, sub-department manager get USER in parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Get by id Error 6", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_DEPARTMENT         |
      # sub, sub-department manager get USER in parent, parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Get by id Error 7", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_DEPARTMENT         |


  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Delete user by id - Error message validation
    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false'
    And Create new user with following json '<jsonBody>' call is made '201'
    And IAT Admin user is logged in with credentials '<login_password>'
    When Delete user by id 'previous_call' call is made '<status>'
    Then Delete user by id call made for incorrect data returns error message '<status>', '<error>', '<message>' - enriched message

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                                                                                                       | id            | status | error        | message                                                                    | managerSharePointsRange |
       #invalid credentials
      | not_existing,password2                                       | {"employeeNumber": "Delete by id Error 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call | 401    | Unauthorized | Full authentication is required to access this resource                    | SAME_DEPARTMENT         |
      #existing account butwithout permissions
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd       | {"employeeNumber": "Delete by id Error 2", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 12345         | 403    | Forbidden    | Access is denied                                                           | SAME_DEPARTMENT         |
      # Delete user with not existing id
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Delete by id Error 3", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 0             | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_DEPARTMENT         |
#      # Admin is able to delete SUPER_ADMIN issue?
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "Delete by id Error 4", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call |null| 404    | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]}                                                   |SAME_DEPARTMENT         |
#      # Manager is able to delete ADMIN issue?
#      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "Delete by id Error 5", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | previous_call |null| 404    | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]}                                                 |SAME_DEPARTMENT         |
#      # Manager is able to delete SUPER_ADMIN issue?
#      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "Delete by id Error 6", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call |null| 404    | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]}                                                 |SAME_DEPARTMENT         |
      # sub-department manager enabling USER in parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "Delete by id Error 7", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_DEPARTMENT         |
      # sub-department manager get user in parent department when scope is set to SAME_COMPANY (HS-92)
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "Delete by id Error 8", "email": "api.test.user", "name": "FirstName LastName",   "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}                                              | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_COMPANY            |
      # sub, sub-department manager enabling USER in parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Delete by id Error 9", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_DEPARTMENT         |
      # sub, sub-department manager enabling USER in parent, parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Delete by id Error 10", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}  | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} | SAME_DEPARTMENT         |


  @Regression @NegativeCase @Users
  @deleteUserAfterTest
  Scenario Outline: Re-enable User - Error message validation
    Given Partner pointsSharing scope for manager to user is set to 'SAME_DEPARTMENT', sharePointsWithManager is set to 'false'
    And Create new user with following json '<jsonBody>' call is made '201'
    And Delete user by id 'previous_call' call is made '200'
    And User account properly deleted for dynamo and mySql
    And IAT Admin user is logged in with credentials '<login_password>'
    When Enable user by id '<id>' call is made '<status>'
    Then Enable user by id call made for incorrect data returns error message '<status>', '<error>', '<message>' - enriched message

  @qa
    Examples:
      | login_password                                               | jsonBody                                                                                                                                                                                                                                                                                    | id            | status | error        | message                                                                    |
      #invalid credentials
      | not_existing,password2                                       | {"employeeNumber": "re-enable Error 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call | 401    | Unauthorized | Full authentication is required to access this resource                    |
      #existing account butwithout permissions
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd       | {"employeeNumber": "re-enable Error 2", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 1233          | 403    | Forbidden    | Access is denied                                                           |
      # Enable user with not existing id
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "re-enable Error 3", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | 0             | 404    | null         | {"errors": [ {"message": "User with id 0 not found","fieldName": "id"} ]}  |
#      # Admin is able to enable SUPER_ADMIN issue?
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"employeeNumber": "re-enable Error 4", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call   |null | 404    | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]}                                                   |
#      # Manager is able to enable ADMIN issue?
#      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "re-enable Error 5", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"}       | previous_call   |null | 404    | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]}                                                   |
#      # Manager is able to enable SUPER_ADMIN issue?
#      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "re-enable Error 6", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call  |null  | 404    | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]}                                                 |
      # sub-department manager enabling USER in parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"employeeNumber": "re-enable Error 7", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} |
      # sub, sub-department manager enabling USER in parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "re-enable Error 8", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} |
      # sub, sub-department manager enabling USER in parent, parent department  when scope set to SAME_DEPARTMENT
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "re-enable Error 9", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"}   | previous_call | 404    | null         | {"errors": [ {"message": "User with id %s not found","fieldName": "id"} ]} |


  @Regression @PositiveCase @Users
  Scenario Outline: Update own profile details
    Given IAT Admin user is logged in with credentials '<login_password>'
    And IAT admin request for his profile details and store id
    When Update user by id 'previous_call' with following json '<jsonBody>' and 'ADMIN_ONLY' call is made '200'
    Then Get user by id response details are the same as for updated user
    And User account properly created for dynamo and mySql

  @qa
    Examples:
      | login_password                                       | jsonBody                                                                                                                                                             |
      | api_test_admin_own_profile@api.iat.admin.pl,P@ssw0rd | { "name": "FirstName1 LastName1", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [B]"}, "companyStartDate": "1999-12-27"}   |
      | api_test_admin_own_profile@api.iat.admin.pl,P@ssw0rd | { "name": "FirstName2 LastName2", "gender": "FEMALE", "birthDate": "1986-12-28", "department": {"name": "Department level 1 [B]"}, "companyStartDate": "1999-12-27"} |


  @Regression @NegativeCase @Users
  Scenario Outline: Update own profile details - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    And IAT admin request for his profile details and store id
    When Update user by id 'previous_call' with following json '<jsonBody>' and 'ADMIN_ONLY' call is made '403'
    Then Update user own profile call made for incorrect data returns error message '403', 'Forbidden', 'Access denied'

  @qa
    Examples:
      | login_password                                       | jsonBody                                                                                                                                                                                                                |
      #update employee No
      | api_test_admin_own_profile@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Update own profile Error 1", "name": "Update own profile Error 1", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [B]"}, "companyStartDate": "1999-12-27"} |
      #change adminRole
      | api_test_admin_own_profile@api.iat.admin.pl,P@ssw0rd | {"name": "Update own profile Error 2", "adminRole": "NONE"}                                                                                                                                                             |
      #change role
      | api_test_admin_own_profile@api.iat.admin.pl,P@ssw0rd | {"name": "Update own profile Error 3", "role": "MANAGER"}                                                                                                                                                               |
      #change department
      | api_test_admin_own_profile@api.iat.admin.pl,P@ssw0rd | { "name": "Update own profile Error 4", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [A]"}, "companyStartDate": "1999-12-27"}                                                |


  @Regression @PositiveCase @UsersBulk
  @deleteUserAfterTest
  Scenario Outline: Bulk upload users - contract and data validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Bulk upload users call is made with data '<fileName>', '201'
    Then Bulk upload users call made for data returns proper response
    And Bulk uploaded default user was properly created

    Examples:
      | login_password                                   | fileName   |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | bulk1.xlsx |


  @Regression @NegativeCase @UsersBulk
  Scenario Outline: Bulk upload users - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Bulk upload users call is made with data '<fileName>', '<status1>'
    Then Bulk upload users call made for incorrect data returns error message '<status2>', '<error>', '<message>'

    Examples:
      | login_password                                         | fileName     | status1 | status2 | error                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | message                                                                                                                                                                                                                                                             |
      | not_existing,password2                                 | bulk1.xlsx   | 401     | 401     | Unauthorized                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | Full authentication is required to access this resource                                                                                                                                                                                                             |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | bulk1.xlsx   | 403     | 403     | Forbidden                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | Access is denied                                                                                                                                                                                                                                                    |
      # improper column
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk2.xlsx   | 500     | 500     | Internal Server Error                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | Column names are wrong. Proper order and names of column names are: Department Name, Department Parent Name, Department Level, Is Department Manager, Super Admin, Admin, First Name, Last Name, Email, Gender, Date of Birth, Employee Number, Company Start Date. |
      # firstName empty
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.xlsx   | 201     | 400     | {"errors":[ { "errors": { "firstName": "Empty field", "email": "User case1@bulk.upload already exists in uploaded file. It is duplicated" }, "row": 2, "departmentName": "Department level 1 [D]", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": null, "lastName": "test", "email": "case1@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]}  | ignore                                                                                                                                                                                                                                                              |
      # lastName empty
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.xlsx   | 201     | 400     | {"errors":[ { "errors": { "lastName": "Empty field" }, "row": 3, "departmentName": "Department level 1 [D]", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "test", "lastName": null, "email": "case2@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "2", "uuid": null, "companyStartDate": null }]}                                                                                         | ignore                                                                                                                                                                                                                                                              |
      # gender Incorrect gender
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.xlsx   | 201     | 400     | {"errors":[ { "errors": { "gender": "Incorrect gender (male, female only allowed)" }, "row": 4, "departmentName": "Department level 1 [D]", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "test", "lastName": "test", "email": "case3@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "3", "uuid": null, "companyStartDate": null }]}                                                        | ignore                                                                                                                                                                                                                                                              |
      # dob Incorrect date format
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.xlsx   | 201     | 400     | {"errors":[ { "errors": { "dob": "Incorrect date format, should be: dd/mm/yyy" }, "row": 5, "departmentName": "Department level 1 [D]", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "test", "lastName": "test", "email": "case4@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "1234-hgtujn-&hh*90()", "uuid": null, "companyStartDate": null }]}                                         | ignore                                                                                                                                                                                                                                                              |
      # email empty
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.xlsx   | 201     | 400     | {"errors":[ { "errors": { "email": "Empty field" }, "row": 6, "departmentName": "Department level 1 [D]", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "test", "lastName": "test", "email": null, "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "1", "uuid": null, "companyStartDate": null }]}                                                                                                         | ignore                                                                                                                                                                                                                                                              |
      # departmentName empty
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.xlsx   | 201     | 400     | {"errors":[  { "errors": { "departmentName": "Empty field" }, "row": 7, "departmentName": null, "departmentParentName": null, "departmentManager": true, "superAdmin": false, "admin": true, "firstName": "test", "lastName": "test", "email": "case6@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "2", "uuid": null, "companyStartDate": null }]}                                                                                                      | ignore                                                                                                                                                                                                                                                              |
      # duplicated epoints <-> user account
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.1.xlsx | 201     | 400     | {"errors":[  { "errors": { "email": "User api.duplicate@api.hr.iat.admin already exists in company" }, "row": 2, "departmentName": "Department level 1 [B]", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "DUPLICATED", "lastName": "level 1 [B]", "email": "api.duplicate@api.hr.iat.admin", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]}            | ignore                                                                                                                                                                                                                                                              |
      # duplicated manager/admin account
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk3.1.xlsx | 201     | 400     | {"errors":[  { "errors": { "email": "User api.duplicate@api.hr.iat.admin already exists in company" }, "row": 3, "departmentName": "Department level 1 [B]", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "DUPLICATED", "lastName": "level 1 [B]", "email": "api.duplicate@api.hr.iat.admin", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]}            | ignore                                                                                                                                                                                                                                                              |
      #departments levels
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk4.xlsx   | 201     | 400     | {"errors":[  { "errors": { "departmentParentName": "Empty field" }, "row": 2, "departmentName": "NEW one", "departmentParentName": null, "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "test", "lastName": "test", "email": "case10", "departmentLevel": 4, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]}                                                                                                   | ignore                                                                                                                                                                                                                                                              |
      #departments levels
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk4.xlsx   | 201     | 400     | {"errors":[  { "errors": { "departmentParentName": "Top level(1) departments need to have parent department name empty" }, "row": 3, "departmentName": "NEW First Level", "departmentParentName": "SomeDepartmentNoit", "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "test", "lastName": "test", "email": "case10", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]}                    | ignore                                                                                                                                                                                                                                                              |
      #department not exists
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk4.1.xlsx | 201     | 400     | {"errors":[   { "errors": { "departmentParentName": "There is no department for this partner with name [not Existing one], level [4]" }, "row": 2, "departmentName": "NEW one2", "departmentParentName": "not Existing one", "departmentManager": false, "superAdmin": false, "admin": false, "firstName": "test", "lastName": "test", "email": "case11@auto.api.test", "departmentLevel": 5, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]} | ignore                                                                                                                                                                                                                                                              |
      #roles - user is: superAdmin, admin and manager
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk5.xlsx   | 201     | 400     | {"errors":[   { "errors": { "isDepartmentManager": "User can not be at the same time superAdmin, admin and manager" }, "row": 2, "departmentName": "Test 7", "departmentParentName": null, "departmentManager": true, "superAdmin": true, "admin": true, "firstName": "test", "lastName": "test", "email": "case10206", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]}                                                 | ignore                                                                                                                                                                                                                                                              |
      #roles - user is: superAdmin and admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk5.xlsx   | 201     | 400     | {"errors":[   { "errors": { "isDepartmentManager": "User can not be at the same time superAdmin and admin" }, "row": 3, "departmentName": "Test 7", "departmentParentName": null, "departmentManager": false, "superAdmin": true, "admin": true, "firstName": "test", "lastName": "test", "email": "case10207", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": null, "uuid": null, "companyStartDate": null }]}                                                         | ignore                                                                                                                                                                                                                                                              |
      #roles - empty superAdmin field
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk5.xlsx   | 201     | 400     | {"errors":[   { "errors": { "superAdmin": "Empty field" }, "row": 4, "departmentName": "Test 7", "departmentParentName": null, "departmentManager": true, "superAdmin": null, "admin": true, "firstName": "test", "lastName": "test", "email": "case7@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "2", "uuid": null, "companyStartDate": null }]}                                                                                                      | ignore                                                                                                                                                                                                                                                              |
      #roles - empty admin field
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk5.xlsx   | 201     | 400     | {"errors":[   { "errors": { "admin": "Empty field" }, "row": 5, "departmentName": "Test 7", "departmentParentName": null, "departmentManager": true, "superAdmin": false, "admin": null, "firstName": "test", "lastName": "test", "email": "case8@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "2", "uuid": null, "companyStartDate": null }]}                                                                                                          | ignore                                                                                                                                                                                                                                                              |
      #roles - empty isDepartmentManager field
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd       | bulk5.xlsx   | 201     | 400     | {"errors":[   { "errors": { "isDepartmentManager": "Empty field" }, "row": 6, "departmentName": "Test 7", "departmentParentName": null, "departmentManager": null, "superAdmin": false, "admin": true, "firstName": "test", "lastName": "test", "email": "case9@bulk.upload", "departmentLevel": 1, "gender": null, "dateOfBirth": null, "employeeNumber": "2", "uuid": null, "companyStartDate": null }]}                                                                                            | ignore                                                                                                                                                                                                                                                              |


  @Regression @PositiveCase @ChangePassword
  Scenario Outline: Change user password - correct password change
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Admin change his password using following data '<jsonBody>', '200'
    Then Admin is able to login into IAT admin with new password '<login_password>'

  @qa
    Examples:
      | login_password                                                  | jsonBody                                             |
      | api_super_admin_password_change_test@api.iat.admin.pl,P@ssw0rd  | {"password": "P@ssw0rd", "newPassword": "password1"} |
      | api_super_admin_password_change_test@api.iat.admin.pl,password1 | {"password": "password1", "newPassword": "P@ssw0rd"} |


  @Regression @NegativeCase @ChangePassword
  Scenario Outline: Change user password - incorrect password change
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Admin change his password using following data '<jsonBody>', '<status>'
    Then Change password call made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                                 | jsonBody                                            | status | error | message                                                                                                                                 |
      | api_super_admin_password_change_test@api.iat.admin.pl,P@ssw0rd | { "newPassword": "password"}                        | 400    | null  | {"errors":[ { "message": "may not be empty", "fieldName": "password" }]}                                                                |
      | api_super_admin_password_change_test@api.iat.admin.pl,P@ssw0rd | {"password": "password"}                            | 400    | null  | {"errors":[ { "message": "may not be empty", "fieldName": "newPassword" }]}                                                             |
      | api_super_admin_password_change_test@api.iat.admin.pl,P@ssw0rd | { }                                                 | 400    | null  | {"errors":[ { "message": "may not be empty", "fieldName": "newPassword" }, { "message": "may not be empty", "fieldName": "password" }]} |
      | api_super_admin_password_change_test@api.iat.admin.pl,P@ssw0rd | {"password": "wrong", "newPassword": "newPassword"} | 400    | null  | {"errors":[ { "message": "Incorrect current password.", "fieldName": "password" }]}                                                     |
      | api_super_admin_password_change_test@api.iat.admin.pl,P@ssw0rd | {"password": "password", "newPassword": "shor"}     | 400    | null  | {"errors":[ { "message": "Minimum password length is 6", "fieldName": "newPassword" }]}                                                 |
      | api_super_admin_password_change_test@api.iat.admin.pl,P@ssw0rd | {"password": "P@ssw0rd", "newPassword": "P@ssw0rd"} | 400    | null  | {"errors":[ { "message": "New password should be different than current one.", "fieldName": "newPassword" }]}                           |


  @Regression @PositiveCase @ResetPassword
  Scenario Outline: PD-573 Reset user password - correct user password reset and check if can login to admin
    Given Admin will send remind his password request '<email>', '200'
    Given Proper verification token 'FORGOTTEN_PASSWORD' is generated for email '<email>'
    When Admin set new password '<currentPassword_newPassword>', 'NULL'
    Then Admin is able to login into IAT admin with new password '<email>'

  @qa
    Examples:
      | email                                                 | currentPassword_newPassword                          |
      | api_super_admin_password_change_test@api.iat.admin.pl | {"password": "P@ssw0rd", "newPassword": "password1"} |
      | api_super_admin_password_change_test@api.iat.admin.pl | {"password": "password1", "newPassword": "P@ssw0rd"} |


  @Regression @PositiveCase @RemindPassword
  Scenario Outline: Remind user password - remind password with not existing email, token verification
    When Admin will send remind his password request '<email>', '<status>'
    Then Verification token will not be generated '<email>', '<status>', '<error>', '<message>'

  @qa
    Examples:
      | email                               | status | error | message                                                                           |
      | WERWERREWERW@wefdwefwe.SDFSDVCSDFDS | 404    | null  | {"errors":[ { "message": "User with email %s not found", "fieldName": "email" }]} |


  @Regression @PositiveCase @RemindPassword
  Scenario Outline: Reset user password - incorrect password reset
    Given Admin will send remind his password request '<email>', '200'
    Given Proper verification token 'FORGOTTEN_PASSWORD' is generated for email '<email>'
    When Admin set new password with incorrect data '<newPassword>', '<token>', '<status>'
    Then Reset password call made for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | email                                                 | newPassword | token     | status | error | message                                                                              |
      | api_super_admin_password_change_test@api.iat.admin.pl | null        | correct   | 400    | null  | {"errors":[ { "message": "may not be empty", "fieldName": "password" }]}             |
      | api_super_admin_password_change_test@api.iat.admin.pl | shor        | correct   | 400    | null  | {"errors":[ { "message": "Minimum password length is 6", "fieldName": "password" }]} |
      | api_super_admin_password_change_test@api.iat.admin.pl | password1   | incorrect | 404    | null  | {"errors":[ { "message": "Token incorrectToken not found", "fieldName": "token" }]}  |
      | api_super_admin_password_change_test@api.iat.admin.pl | password1   | null      | 400    | null  | {"errors":[ { "message": "may not be empty", "fieldName": "token" }]}                |


  @Regression @PositiveCase
  @deleteUserAfterTest
  Scenario Outline: PD-573 Set password - account activation
    Given IAT Admin user is logged in with credentials '<login_password>'
    And Create new user with following json '<jsonBody>' call is made '201'
    When New admin activate account and set password for the first time '<currentPassword_newPassword>', 'NotNULL'
    Then New Admin is able to login into IAT admin with new password

  @qa
    Examples:
      | login_password                                   | jsonBody                                                                                                                                                                                                                                                                                | currentPassword_newPassword                                          |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "activation 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"password": null, "newPassword": "Password1", "tnCAccepted": false} |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "activation 1", "email": "api.test.user", "name": "FirstName LastName", "gender": "MALE", "birthDate": "1986-12-27", "department": {"name": "Department level 1 [D]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE", "companyStartDate": "1999-12-27"} | {"password": null, "newPassword": "Password1", "tnCAccepted": true } |


  @Regression @PositiveCase @Users
  @deleteUserAfterTest
  Scenario Outline: Update user details - multiple mail update and email changes history check
    Given IAT Admin user is logged in with credentials '<login_password>'
    And Create new user with following json '<jsonBody>' call is made '201'
    And Get user by id 'previous_call' call is made '200'
    When Created user email will be updated 5 times
    Then History of email changes is properly returned by user details request

  @qa
    Examples:
      | login_password                                   | jsonBody                                                                                                                                                                                                             |
      #update profile fields
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"employeeNumber": "Update multiple mail update", "email": "api.test.user", "name": "FirstName LastName", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"} |


  @qa @Regression
  Scenario: Delete not needed test users - with employeeNumber contains create
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    When Get users list call is made with filters 'createdAt,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,500', '200'
    Then Delete user by id from List call is made to clear environment after tests




  #DEBUG use >>>>>>

#  @AddingTestData
#  Scenario Outline: Create API test admin accounts
#    Given IAT Admin user is logged in with credentials 'super7777,P@ssw0rd'
#    When Create new user with following json '<jsonBody>' call is made '201'
#    When New admin activate account and set password for the first time '<password>'
#    Then New Admin is able to login into IAT admin with new password
#
#    @MI
#    Examples:
#      | jsonBody                                                                                                                                                                                                                                                          | password                                      |
#      | {"employeeNumber": "TEST 1", "email": "api_test_admin_platform_1@api.iat.admin.pl", "name": "PLATFORM ADMIN level 1 [A]", "department": {"name": "Department level 1 [A]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE"}                                | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 2", "email": "api_test_super_admin_1@api.iat.admin.pl", "name": "SUPER_ADMIN level 1 [A]", "department": {"name": "Department level 1 [B]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE"}                                | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 3", "email": "api_test_admin_1@api.iat.admin.pl", "name": "ADMIN level 1 [A]", "department": {"name": "Department level 1 [B]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE"}                                                  | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 4", "email": "api_test_manager_admin_1@api.iat.admin.pl", "name": "ADMIN-MANAGER level 2 [A.1]", "department": {"name": "Department level 2 [A.1]"}, "role": "MANAGER", "adminRole": "ADMIN", "status": "ACTIVE"}                           | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 5", "email": "api_test_admin_approval_1@api.iat.admin.pl", "name": "MANAGER-APPROVAL level 3 [A.1.1]", "department": {"name": "Department level 3 [A.1.1]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                    | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 6", "email": "api_test_user_without_rights@api.iat.admin.pl", "name": "SUPER-ADMIN-NO-RIGHTS level 1 [A]", "department": {"name": "Department level 1 [B]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE"}               | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 7", "email": "api_test_admin_own_profile@api.iat.admin.pl", "name": "SUPER-ADMIN-OWN-PROFILE level 1 [A]", "department": {"name": "Department level 1 [B]"}, "role": "USER", "adminRole": "ADMIN", "status": "ACTIVE"}                     | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 8", "email": "api_super_admin_password_change_test@api.iat.admin.pl", "name": "SUPER-ADMIN-CHANGE-PASSWORD level 1 [A]", "department": {"name": "Department level 1 [B]"}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE"} | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 9", "email": "api_test_manager_department_1@api.iat.admin.pl", "name": "MANAGER level 1 [A]", "department": {"name": "Department level 1 [A]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                                 | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 10", "email": "api_test_manager_department_2@api.iat.admin.pl", "name": "MANAGER level 1 [B]", "department": {"name": "Department level 1 [B]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                                 | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 11", "email": "api_test_manager_subdepartment_1@api.iat.admin.pl", "name": "MANAGER level 2 [A.1]", "department": {"name": "Department level 2 [A.1]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                          | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 12", "email": "api_test_manager_subdepartment_1.1@api.iat.admin.pl", "name": "MANAGER level 3 [A.1.1]", "department": {"name": "Department level 3 [A.1.1]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                    | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 13", "email": "api_test_manager_subdepartment_1.2@api.iat.admin.pl", "name": "MANAGER level 3 [A.1.2]", "department": {"name": "Department level 3 [A.1.2]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                    | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 14", "email": "api_test_manager_department_3@api.iat.admin.pl", "name": "Manager level 1 [C]", "department": {"name": "Department level 1 [C]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                                | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 15", "email": "api_test_manager_subdepartment_2@api.iat.admin.pl", "name": "MANAGER level 2 [C.1]", "department": {"name": "Department level 2 [C.1]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                         | {"password": null, "newPassword": "P@ssw0rd"} |
#      | {"employeeNumber": "TEST 16", "email": "api_test_manager_subdepartment_3@api.iat.admin.pl", "name": "MANAGER level 2 [C.2]", "department": {"name": "Department level 2 [C.2]"}, "role": "MANAGER", "adminRole": "NONE", "status": "ACTIVE"}                         | {"password": null, "newPassword": "P@ssw0rd"} |
#
#  @AddingTestData @deleteUserAfterTest2
#  Scenario Outline: Create API test users (epoints) accounts
#    Given IAT Admin user is logged in with credentials 'super7777,P@ssw0rd'
#    When Create new user with following json '<jsonBody>' call is made '201'
#    Then Confirm new epoints account created via iatAdmin
#
#    @MI
#    Examples:
#      | jsonBody |
#      | {"employeeNumber": "duplicated2", "email": "api.duplicate2@api.hr.iat.admin", "name": "duplicated2 level 2 [A.1]", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"} |
#      | {"employeeNumber": "User 1", "email": "api_test_user1@api.iat.admin.pl", "name": "User_1 level 1 [B]", "department": {"name": "Department level 1 [B]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"} |
#      | {"employeeNumber": "User 2", "email": "api_test_user.a_1@api.iat.admin.pl", "name": "User_A_1 level 2 [A.1]", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}
#      | {"employeeNumber": "User 3", "email": "api_test_user.a_2@api.iat.admin.pl", "name": "User_A_2 level 2 [A.1]", "department": {"name": "Department level 2 [A.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}
#      | {"employeeNumber": "User 1", "email": "api_test_user.a.1.1_1@api.iat.admin.pl", "name": "User_A.1.1_1 level 3 [A.1.1]", "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}
#      | {"employeeNumber": "User 2", "email": "api_test_user.a.1.1_2@api.iat.admin.pl", "name": "User_A.1.1_2 level 3 [A.1.1]", "department": {"name": "Department level 3 [A.1.1]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}
#      | {"employeeNumber": "User 1", "email": "api_test_user.a.1.2_1@api.iat.admin.pl", "name": "User_A.1.2_1 level 3 [A.1.2]", "department": {"name": "Department level 3 [A.1.2]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}
#      | {"employeeNumber": "User 2", "email": "api_test_user.a.1.2_2@api.iat.admin.pl", "name": "User_A.1.2_2 level 3 [A.1.2]", "department": {"name": "Department level 3 [A.1.2]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE"}
#      | {"employeeNumber": "User 5", "email": "api_test_user.a.1.2_5@api.iat.admin.pl", "name": "Not confirmed - used in epoints-api tests", "department": {"name": "Department level 3 [A.1.2]"}, "role": "USER", "adminRole": "NONE", "status": "ACTIVE", "gender": "MALE"}|



#  #Created user for Andy's company
#    Scenario Outline: Create API test admin accounts
#    Given IAT Admin user is logged in with credentials 'super2147335217,P@ssw0rd'
#    When Create new user with following json '<jsonBody>' call is made '201'
#    When New admin activate account and set password for the first time '<password>'
#    Then New Admin is able to login into IAT admin with new password
#
#    Examples:
#      | jsonBody                                                                                                                                                                                                                                                          | password                                      |
#      | {"employeeNumber": "", "email": "andy_test_super_admin_company2@iat.admin.pl", "name": "SUPER ADMIN Test Company 2", "department": {"id": 221020628}, "role": "USER", "adminRole": "SUPER_ADMIN", "status": "ACTIVE"}                                | {"password": null, "newPassword": "P@ssw0rd"} |



#  @MI
#  Scenario: Delete not needed test users - with employeeNumber contains create
#    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
#    When Get users list call is made with filters 'createdAt,false,2015-01-01T00:00:00+00:00,2025-08-23T23:00:00+00:00,null,null,null,null,null,500', '200'
#    Then Delete user by id from List call is made to clear environment after tests
#    When Delete user by id 'bea4478c-9f3b-4361-baba-a6df2bc906bc' call is made '200' for DEBUG
#    When Delete user by id '0f94cb37-bdac-4a22-9ce6-ae931d805e0c' call is made '200' for DEBUG
#    When Delete user by id '2d0a520a-24cb-4594-8d7a-0bd2dfa41aea' call is made '200' for DEBUG
#    When Delete user by id '80a35b99-b20b-4fbe-9f1a-b6c4cd720ec6' call is made '200' for DEBUG
#    When Delete user by id '00f018ec-cabe-4b48-8acb-71846caaed3a' call is made '200' for DEBUG
#    When Delete user by id 'fff1e351-3f95-4a55-8299-ff4fe77b19b7' call is made '200' for DEBUG

