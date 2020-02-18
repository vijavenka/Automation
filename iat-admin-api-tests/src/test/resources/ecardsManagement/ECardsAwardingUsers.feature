Feature: IAT Admin HR - awarding users
  As ecards admin/manager user
  I want to have possibility to award users
  To be able to properly award them epoints via ecards


  @Regression @PositiveCase @GrantUsers
  Scenario Outline: Get user awarding points history
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get user awarding points history call is made with filters '<sortField_ascending_page_maxResults_dateFrom_dateTo_from_to_points_amount_senderDepartment_receiverDepartment_approvalStatus>'
    Then Get user awarding points history call returns proper contract and data for filters '<sortField_ascending_page_maxResults_dateFrom_dateTo_from_to_points_amount_senderDepartment_receiverDepartment_approvalStatus>'

  @qa
    Examples:
      | login_password                                             | sortField_ascending_page_maxResults_dateFrom_dateTo_from_to_points_amount_senderDepartment_receiverDepartment_approvalStatus  |
      #For partners allocation history: sorting by: reason, amount, points, receiver, sender, date; date ranges; search by: reason, from, to, amount, points,
      #SORTING
      # default: sort by date asc
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,null                                                              |
      # asc = false, by date
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | date,false,null,null,null,null,null,null,null,null,null,null,null                                                             |
      # asc = true, by date
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | date,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                         |
      # asc = false, by id, maxResults = 10
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | id,false,null,10,null,null,null,null,null,null,null,null,null                                                                 |
      # asc = true, by id, maxResults = 10
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | id,true,null,10,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                             |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | points,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | points,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | amount,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | amount,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | reason,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | reason,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                       |
      # issue sender desc improperly sorted
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | sender,false,null,20,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                        |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | sender,true,null,20,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | receiver,false,null,20,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | receiver,true,null,20,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | date,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | date,false,null,null,null,2027-03-06T10:00:00+00:00,null,null,null,null,null,null,null                                        |
#      #Functionality turned off
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | approvalStatus,false,null,20,null,null,null,null,null,null,null,null,null                                                    |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | approvalStatus,true,null,20,null,null,null,null,null,null,null,null,null                                                     |
      #FILTERING
      # by sender exact name
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,10,2017-07-01T08:00:00+00:00,null,MANAGER level 1 [A],null,null,null,null,null,null                            |
      # by sender email (iat Admin manager/admin)
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,10,2017-07-01T08:00:00+00:00,null,api_test_manager_department_1@api.iat.admin.pl,null,null,null,null,null,null |
      # by sender email (epoints user)
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,10,2017-07-01T08:00:00+00:00,null,api_test_user.a_1@api.iat.admin.pl,null,null,null,null,null,null             |
      # by sender partial name
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,10,2017-07-01T08:00:00+00:00,null,department_1,null,null,null,null,null,null                                   |
      # by receiver name
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,10,2017-07-01T08:00:00+00:00,null,null,MANAGER level 2 [A.1],null,null,null,null,null                          |
      # by receiver email
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,10,2017-07-01T08:00:00+00:00,null,null,api_test_user.a_1@api.iat.admin.pl,null,null,null,null,null             |
      # by points
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,100,null,null,null,null                                          |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,3,null,null,null,null                                            |
      # by amount
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,0.015,null,null,null                                        |
      # by sender department
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,[A],null,null                                          |
      # by receiver department
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,[A.1],null                                        |
      #Functionality turned off
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,APPROVED                                                         |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,PENDING                                                          |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,REJECTED                                                         |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,approved                                                         |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,pending                                                          |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,rejected                                                         |
#      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                 | null,null,null,null,null,null,null,null,null,null,null,null,sssssRUU                                                         |
      ### check manager scope
      #SORTING
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,13,null,null,null,null,null,null,null,null,null                                                                |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | id,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                          |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | id,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                           |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | date,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                        |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | date,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | points,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | points,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                       |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | amount,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | amount,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                       |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | reason,false,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | reason,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                       |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | receiver,false,null,20,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                      |
#      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | receiver,true,null,20,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                                           |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | sender,false,null,10,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                        |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | sender,true,null,10,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | date,true,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,null,null                                         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | date,false,null,null,null,2028-03-06T10:00:00+00:00,null,null,null,null,null,null,null                                        |
      #FILTERING
      # by From
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,10,2017-07-01T08:00:00+00:00,null,User_A_1 User_A_1  level 2 [A.1],null,null,null,null,null,null               |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,10,2017-07-01T08:00:00+00:00,null,api_test_user.a_1@api.iat.admin.pl,null,null,null,null,null,null             |
      # by to
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,10,2017-07-01T08:00:00+00:00,null,null,MANAGER level 2 [A.1],null,null,null,null,null                          |
      # by points
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,100,null,null,null,null                                          |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,6,null,null,null,null                                            |
      # by amount
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,0.5,null,null,null                                          |
      # by sender department
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,Department level 2 [A.1],null,null                     |
      # by receiver department
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | null,null,null,null,2017-07-01T08:00:00+00:00,null,null,null,null,null,null,Department level 2 [A.1],null                     |


  @Regression @NegativeCase @GrantUsers
  Scenario Outline: Get user awarding points history - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get user awarding points history call for incorrect data '<jsonBody>', '<status>'
    Then Get user awarding points history call for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                         | jsonBody                                                         | status | error        | message                                                 |
      | not_existing,password2                                 | null,null,null,null,null,null,null,null,null,null,null,null,null | 401    | Unauthorized | Full authentication is required to access this resource |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | null,null,null,null,null,null,null,null,null,null,null,null,null | 403    | Forbidden    | Access is denied                                        |


  @Regression @PositiveCase @GrantUsers
  Scenario Outline: Awarding ecards users by admin/manager
#    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'true' (Once PD-570 is resolved, this can be uncommented)
    And IAT Admin user is logged in with credentials '<login_password>'
    And IAT user current limit and stats values are known before grant users
    When User award points call is made with data '<jsonData>', '200'
    Then User award points call properly reduced IAT user limits
    #turned off because it's required sleep
#    And User award points call properly update award stats

  @qa
    Examples:
      | login_password                                               | jsonData                                                                                                                                                                                                                                                                                                                                                   | managerSharePointsRange |
      # ADMIN level 1 [B] sent ecards to users
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department B when SAME_COMPANY", "pointsValue": 13, "usersKey": ["DYNAMIC", "Department level 1 [B]", "3"], "cc":[]}                                                                                                           | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department B when SAME_COMPANY", "usersKey": ["DYNAMIC", "Department level 1 [B]", "3"], "cc":[], "products": [{ "productTitle": "ProdTitle11", "productId": "9e1fa84d-ea9f-3e1c-80de-117f5c0fb11f", "productPoints": 208, "quantity" : 1 }, { "productTitle": "ProdTitle22", "productId": "879259ac-dc85-33e0-a099-c6c649f050ad", "productPoints": 208, "quantity" : 1 }]}    | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A when SAME_COMPANY", "pointsValue": 11, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                                        | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A when SAME_COMPANY", "pointsValue": 11, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                                        | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A.1 when SAME_COMPANY", "pointsValue": 9, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "5"], "cc":[]}                                                                                                     | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A.1.1 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                                                | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A.1.2 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                                                | SAME_COMPANY            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department B when SAME_DEPARTMENT", "pointsValue": 13, "usersKey": ["DYNAMIC", "Department level 1 [B]", "3"], "cc":[]}                                                                                                        | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A when SAME_DEPARTMENT", "pointsValue": 11, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                                     | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A.1 when SAME_DEPARTMENT", "pointsValue": 9, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "5"], "cc":[]}                                                                                                  | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A.1.1 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                                             | SAME_DEPARTMENT         |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd                   | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A.1.2 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                                             | SAME_DEPARTMENT         |
      # MANAGER level 1 [A] sent ecards to users
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department B when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 1 [B]", "1"], "cc":[]}                                                                                       | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                    | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A.1 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "1"], "cc":[]}                                                                                | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A.1.1 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                            | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A.1.2 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                            | SAME_COMPANY            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                 | SAME_DEPARTMENT         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A.1 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "1"], "cc":[]}                                                                             | SAME_DEPARTMENT         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A.1.1 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                         | SAME_DEPARTMENT         |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 1 [A] to users from department id A.1.2 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                         | SAME_DEPARTMENT         |
      # MANAGER level 2 [A.1] sent ecards to users
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department B when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 1 [B]", "1"], "cc":[]}                                                                                     | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department id A when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                  | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department id A.1 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "1"], "cc":[]}                                                                              | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department id A.1.1 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                          | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department id A.1.2 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                          | SAME_COMPANY            |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department id A.1 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "1"], "cc":[]}                                                                           | SAME_DEPARTMENT         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department id A.1.1 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                       | SAME_DEPARTMENT         |
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd   | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U custom template 1,MANAGER level 2 [A.1] to users from department id A.1.2 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                       | SAME_DEPARTMENT         |
      # ADMIN-MANAGER level 2 [A.1] sent ecards to users
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department B when SAME_COMPANY", "pointsValue": 13, "usersKey": ["DYNAMIC", "Department level 1 [B]", "3"], "cc":[]}                                                                                                 | SAME_COMPANY            |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A when SAME_COMPANY", "pointsValue": 11, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                              | SAME_COMPANY            |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A.1 when SAME_COMPANY", "pointsValue": 9, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "5"], "cc":[]}                                                                                           | SAME_COMPANY            |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A.1.1 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                                      | SAME_COMPANY            |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A.1.2 when SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                                      | SAME_COMPANY            |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department B when SAME_DEPARTMENT", "pointsValue": 13, "usersKey": ["DYNAMIC", "Department level 1 [B]", "3"], "cc":[]}                                                                                              | SAME_DEPARTMENT         |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A when SAME_DEPARTMENT", "pointsValue": 11, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]}                                                                                           | SAME_DEPARTMENT         |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A.1 when SAME_DEPARTMENT", "pointsValue": 9, "usersKey": ["DYNAMIC", "Department level 2 [A.1]", "5"], "cc":[]}                                                                                        | SAME_DEPARTMENT         |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A.1.1 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.1]", "1"], "cc":[]}                                                                                   | SAME_DEPARTMENT         |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd           | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN-MANAGER level 2 [A.1] to users from department id A.1.2 when SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["DYNAMIC", "Department level 3 [A.1.2]", "1"], "cc":[]}                                                                                   | SAME_DEPARTMENT         |
      # Grant manager direct manager  MANAGER level 2 [A.1.1] to Parent MANAGER level 2 [A.1] when sharePointsWithManager = true
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1, MANAGER level 2 [A.1.1] to Parent MANAGER level 2 [A.1] when sharePointsWith managers = true and SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC-MANAGER", "Department level 2 [A.1]", "api_test_manager_subdepartment_1@api.iat.admin.pl"], "cc":[]} | SAME_COMPANY            |


  @Regression @NegativeCase @GrantUsers
  Scenario Outline: Awarding ecards users - Error message validation
#    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false' (Once PD-570 is resolved, this can be uncommented)
    And IAT Admin user is logged in with credentials '<login_password>'
    And IAT user current limit and stats values are known before grant users
    When User award points call is made with data '<jsonData>', '<status>'
    Then User award points call with incorrect data returns error message '<status>', '<error_fieldName>', '<message>'
    And User award points call for incorrect data not reduced IAT admin limit

  @qa
    Examples:
      | login_password                              | jsonData                                                                                                                                                                                                             | managerSharePointsRange | status | error_fieldName | message                                                                                                       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING_M_to_U points less than min", "pointsValue": 1, "usersKey": ["DYNAMIC", "Department level 1 [B]", "1"], "cc":[]}                | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"Points value should be between %s and %s","fieldName":"pointsValue"}]}                 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING M to U points higher than max", "pointsValue": 16, "usersKey": ["DYNAMIC", "Department level 1 [B]", "1"], "cc":[]}             | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"Points value should be between %s and %s","fieldName":"pointsValue"}]}                 |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD points higher than department allocation limit", "pointsValue": 2000000, "usersKey": ["DYNAMIC", "Department level 1 [D]", "50"], "cc":[]} | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"Total amount of points to spent is higher than available","fieldName":"pointsValue"}]} |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"reasonId": "WIZARD", "message": "Empty template", "pointsValue": 13, "usersKey": ["DYNAMIC", "Department level 1 [B]", "1"], "cc":[]}                                                                              | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"Template cannot be empty","fieldName":"templateId"}]}                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"templateId": "default", "message": "Empty Reason", "pointsValue": 13, "usersKey": ["DYNAMIC", "Department level 1 [B]", "1"], "cc":[]}                                                                             | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"Reason cannot be empty","fieldName":"reasonId"}]}                                      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"reasonId": "WIZARD", "templateId": "default", "pointsValue": 13, "usersKey": ["DYNAMIC", "Department level 1 [B]", "3"], "cc":[]}                                                                                  | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"Message cannot be empty","fieldName":"message"}]}                                      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"reasonId": "WIZARD", "templateId": "default", "message": "Empty users list", "pointsValue": 13, "cc":[]}                                                                                                           | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"List of users cannot be empty","fieldName":"usersKey"}]}                               |
      | api_test_admin_1@api.iat.admin.pl,password2 | {}                                                                                                                                                                                                                   | SAME_COMPANY            | 401    | Unauthorized    | Full authentication is required to access this resource                                                       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd  | {"reasonId": "WIZARD", "templateId": "default", "message": "Incorrect uuid", "pointsValue": 13, "usersKey": ["1000500100900"], "cc":[]}                                                                              | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"There are invalid users on your list.","fieldName":"usersKey"}]}                       |


  @Regression @NegativeCase @GrantUsers
  Scenario Outline: Awarding ecards users - Error message validation -> case awarding users from parent department
    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false'
    And Get users from department call for 'Department level 1 [A]', 'User'
    And IAT Admin user is logged in with credentials '<login_password>'
    And IAT user current limit and stats values are known before grant users
    When User award points call is made with data '<jsonData>', '<status>'
    Then User award points call for incorrect data returns error message '<status>', '<error_fieldName>', '<message>'
    And User award points call for incorrect data not reduced IAT admin limit

  @qa
    Examples:
      | login_password                                             | jsonData                                                                                                                                                                                                                        | managerSharePointsRange | status | error_fieldName | message                                                                                 |
      # case for awarding users which are out of user scope
      | api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "awarding users from parent department when managerSharePointsRange = SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["PARENT_DEPARTMENT", "1"], "cc":[]} | SAME_DEPARTMENT         | 400    | null            | {"errors":[{"message":"There are invalid users on your list.","fieldName":"usersKey"}]} |


  @Regression @NegativeCase @GrantUsers
  Scenario Outline: Awarding ecards users - Error message validation -> case awarding own account
#    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false' (Once PD-570 is resolved, this can be uncommented)
    And IAT Admin user is logged in with credentials '<login_password>'
    And IAT user current limit and stats values are known before grant users
    When User award points call is made with data '<jsonData>', '<status>'
    Then User award points call for incorrect data returns error message '<status>', '<error_fieldName>', '<message>'
    And User award points call for incorrect data not reduced IAT admin limit

  @qa
    Examples:
      | login_password                                          | jsonData                                                                                                                                                                                                             | managerSharePointsRange | status | error_fieldName | message                                                                                  |
      ## case for awarding admin own account
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Admin award his own account when managerSharePointsRange = SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["OWN_ACCOUNT", "1"], "cc":[]}      | SAME_DEPARTMENT         | 400    | null            | {"errors":[{"message":"You are not allowed to award yourself.","fieldName":"usersKey"}]} |
      ## case for awarding manager own account
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Manager awarding his own account when managerSharePointsRange = SAME_DEPARTMENT", "pointsValue": 10, "usersKey": ["OWN_ACCOUNT", "1"], "cc":[]} | SAME_DEPARTMENT         | 400    | null            | {"errors":[{"message":"You are not allowed to award yourself.","fieldName":"usersKey"}]} |


  @Regression @NegativeCase @GrantUsers
  Scenario Outline: Awarding ecards users - Error message validation -> case awarding user direct manager
    Given Partner pointsSharing scope for manager to user is set to '<managerSharePointsRange>', sharePointsWithManager is set to 'false'
    And IAT Admin user is logged in with credentials '<login_password>'
    And IAT user current limit and stats values are known before grant users
    When User award points call is made with data '<jsonData>', '<status>'
    Then User award points call for incorrect data returns error message '<status>', '<error_fieldName>', '<message>'
    And User award points call for incorrect data not reduced IAT admin limit

  @qa
    Examples:
      | login_password                                               | jsonData                                                                                                                                                                                                                                                                                                                                                    | managerSharePointsRange | status | error_fieldName | message                                                                                                            |
      #Grant manager direct manager when sharePointsWithManager = false
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1, MANAGER level 2 [A.1.1] to Parent MANAGER level 2 [A.1] when sharePointsWith managers = false and SAME_COMPANY", "pointsValue": 10, "usersKey": ["DYNAMIC-MANAGER", "Department level 2 [A.1]", "api_test_manager_subdepartment_1@api.iat.admin.pl"], "cc":[]} | SAME_COMPANY            | 400    | null            | {"errors":[{"message":"There are invalid users on your list. Cannot award your manager.","fieldName":"usersKey"}]} |


  @Regression @PositiveCase @GrantUsers
  Scenario Outline: Awarding ecards users by admin/manager and check user balance -> real user email
    Given Partner approvalProcess is set to 'NONE'
    And IAT Admin user is logged in with credentials '<login_password>'
    And IAT user current limit and stats values are known before grant users
    And ePoints user (from departmentName 'Department level 1 [A]') balance before awarding by iat Admin is known
    When User award points call is made with data '<jsonData>', '200'
    Then ePoints user balance after awarding by iat Admin was properly increased
    And User award points call properly reduced IAT user limits

  @qa
    Examples:
      | login_password                             | jsonData                                                                                                                                                                                                                                            |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD custom template 1,ADMIN level 1 [B] to users from department id A when SAME_COMPANY", "pointsValue": 11, "usersKey": ["DYNAMIC", "Department level 1 [A]", "1"], "cc":[]} |


# PD-474 Identify Partners onboarded by Vivup. This is done by setting a flag isPartnerVivupSponsored to 1 in points_manager.Partner table
# Then "ecard-vivup-sponsored" email template will be used for sending ecard from eachperson and epoints.
# For other than Vivup Partners, flag isPartnerVivupSponsored to 0 in points_manager.Partner table
# Then "ecard" email template will be used for sending ecard from eachperson and epoints. 
# Similar to 'Awarding ecards users by admin/manager' line 123

Scenario: PD-474 Sending ecard-vivup-sponsored email template for Vivup Partners in eachperson
Given In eachperson after logging, whose points_manager.Partner table has isPartnerVivupSponsored set to 1
And While sending an ecard
Then It sends through "ecard-vivup-sponsored" email template

Scenario: PD-474 Sending ecard-vivup-sponsored email template for Vivup Partners in eachperson
Given In eachperson after logging, whose points_manager.Partner table has isPartnerVivupSponsored set to 0
And While sending an ecard
Then It sends through "ecard" email template

  # PD-665 Nomination notification email will be triggered to all the managers in that department, if ecard sent to users
  # PD-665 If there are no managers in that department, then Nomination notification email will be triggered to first manager found in structure
  # PD-756 If nominations sent to managers then, Nomination notification email will be triggered to all the managers of upper level department in their company structure
  # PD-665 And this email will be triggered weekly collective whose ecard is sent before 18:00 hrs of Sunday
  # PD-665 ecard sent on or after 18:00 hrs will be sent next week similarly
  # PD-665 Only when points_manager.Partner.isEligibleForNominationNotifiction is set to 1

  Scenario: PD-665 eachperson department has users, managers and ecard sent to users BEFORE 18:00 hrs for the Partner whose isEligibleForNominationNotifiction column is set to 1
    Given In eachperson, logged in whose points_manager.Partner.isEligibleForNominationNotifiction is set to 1
    When ecard is sent to user with nomination
    And Whose nominated time is BEFORE 18:00 hrs of the week (Sunday)
    Then Nomination notification email will be sent collectively for THIS week on Sunday to all the managers of that department
    And The email contains all manager names, number of nominations, start date and end date

  Scenario: PD-665 eachperson department has users, managers and ecard sent to users ON or AFTER 18:00 hrs for the Partner whose isEligibleForNominationNotifiction column is set to 1
    Given In eachperson, logged in whose points_manager.Partner.isEligibleForNominationNotifiction is set to 1
    When ecard is sent to user with nomination
    And Whose nominated time is ON or AFTER 18:00 hrs of the week (Sunday)
    Then Nomination notification email will be sent collectively for NEXT week on Sunday to all the managers of that department
    And The email contains all manager names, number of nominations, start date and end date

  Scenario: PD-665 eachperson department has users and NO manager and ecard sent to users BEFORE 18:00 hrs for the Partner whose isEligibleForNominationNotifiction column is set to 1
    Given In eachperson, logged in whose points_manager.Partner.isEligibleForNominationNotifiction is set to 1
    When ecard is sent to user with nomination
    And Whose nominated time is BEFORE 18:00 hrs of the week
    Then Nomination notification email will be sent collectively for THIS week on Sunday to first manager found in structure
    And The email contains first manager name found in structure, number of nominations, start date and end date

  Scenario: PD-665 eachperson department has users, managers and ecard sent with NO nomination to users BEFORE 18:00 hrs for the Partner whose isEligibleForNominationNotifiction column is set to 1
    Given In eachperson, logged in whose points_manager.Partner.isEligibleForNominationNotifiction is set to 1
    When ecard is sent to user with NO nomination
    And Whose nominated time is BEFORE 18:00 hrs of the week
    Then Nomination notification email will not be sent to any of the managers

  Scenario: PD-756 eachperson department has users and managers and ecard sent to user and manager BEFORE 18:00 hrs for the Partner whose isEligibleForNominationNotifiction column is set to 1
    Given In eachperson, logged in whose points_manager.Partner.isEligibleForNominationNotifiction is set to 1
    When ecard is sent to users and managers with internal nomination
    And Whose nominated time is BEFORE 18:00 hrs of the week
    Then Nomination notification email with users count will be sent to all the managers in that department
    And Nomination notification email with managers count not including SuperAdmin will be sent to all the managers in the upper level department

  Scenario: PD-756 eachperson root department has users and managers and ecard sent to user and manager BEFORE 18:00 hrs for the Partner whose isEligibleForNominationNotifiction column is set to 1
    Given In eachperson, logged in whose points_manager.Partner.isEligibleForNominationNotifiction is set to 1
    When ecard is sent to users and managers with internal nomination
    And Whose nominated time is BEFORE 18:00 hrs of the week
    Then Nomination notification email with users count will be sent to all the managers in that root department
    And Root department Managers count will not be sent

  Scenario: PD-665 eachperson department has users, managers and ecard sent to user BEFORE 18:00 hrs, Partner.isEligibleForNominationNotifiction has 0
    Given In eachperson, logged in whose points_manager.Partner.isEligibleForNominationNotifiction is set to 0
    When ecard is sent to user with nomination
    And Whose nominated time is BEFORE 18:00 hrs of the week
    Then Nomination notification email will not be sent to any of the managers
    
  # ------------- PD-709 In eachperson and epoints, ecards/nomination API has been extended to retrieve more than one data.
  # From iat_admin_qa.NominationsSettings table
  Scenario: PD-709 eachperson, verifying ecards/nomination API retrieves more than one data
    Given In eachperson logged in
    When ecards/nomination API is called
    Then It will retrieve all the nominations which is active from iat_admin_qa.NominationsSettings table with respect to the partner

  Scenario: PD-709 epoints, verifying ecards/nomination API retrieves more than one data
    Given In epoints logged in
    When ecards/nomination API is called
    Then It will retrieve all the nominations which is active from iat_admin_qa.NominationsSettings table with respect to the partner

