Feature: Audit cms - authorization
  As Audit Admin user
  I want to be able to log in to cms
  To be able to make authorized requests

  @Regression @PositiveCase @Login
  Scenario Outline: Check if it's possible to authorize in audit-cms
    Given Audit admin user is logged in with credentials '<login_password>'
    When Audit admin login response should return bearer

  @qa
    Examples:
      | login_password |
      | admin,admin    |