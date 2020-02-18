Feature: IAT Admin HR - authorization
  As IAT Admin user
  I want to be able to authorize in portal
  To be able to get access to all functions

  @Regression @PositiveCase @Login
  Scenario Outline: Check if it's possible to log in into IAT admin
    Given IAT Admin is responding to requests
    When IAT Admin user is logged in with credentials '<login_password>'
    Then IAT Admin login response should return sessionID

  @qa
    Examples:
      | login_password                             |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd |


  @Regression @NegativeCase @Login
  Scenario Outline: Check log in request validation
    Given IAT Admin is responding to requests
    When IAT Admin user is trying log in with credentials '<login_password>', '<status>'
    Then IAT Admin log in response returns error message '<error>', '<message>'

  @qa
    Examples:
      | login_password                               | error       | message                       | status |
      # invalid login
      | api_test_admin_1@api.iat.admin.plek,P@ssw0rd | Bad Request | Username or password invalid. | 400    |
      # invalid password
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd2  | Bad Request | Username or password invalid. | 400    |


  @Regression @PositiveCase @AdminProfile
  Scenario Outline: Check if Iat user profile contract is proper
    Given IAT Admin user is logged in with credentials '<login_password>'
    When IAT Admin request for his profile details
    Then IAT Admin profile role are properly set '<login_password>'
    And IAT Admin profile response contains authority list

  @qa
    Examples:
      | login_password                                          |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd |
      | api_test_manager_admin_1@api.iat.admin.pl,P@ssw0rd      |
      | api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd     |

