Feature: To return epoints based on logged in User and mapped department
User logged in with Manager Super admin role then birthday count will return company level
User logged in with Manager None role then birthday count is limited to department and sub-department if any

  @Regression @Landing
    Scenario Outline: Based on the User and his mapped department logged in, return recognise balance number.

    Given IAT Admin user is logged in with credentials '<login_password>'
    When Landing page API is called '<To_Validate>'
   

@qa
  Examples:
    | login_password                        |    To_Validate     |
    #Manager with None
    | leafleveldept@gmail.com,password      |    DEPARTMENT      |
    | leafleveldept@gmail.com,password      |    USERS_COUNT     |
    | leafleveldept@gmail.com,password      |    TODAY_FALSE     |
    | leafleveldept@gmail.com,password      |    WEEK_FALSE      |
    | leafleveldept@gmail.com,password      |    TODAY           |
    | leafleveldept@gmail.com,password      |    WEEK            |
    | leafleveldept@gmail.com,password      |    wA_TODAY_FALSE     |
    | leafleveldept@gmail.com,password      |    wA_WEEK_FALSE      |
    | leafleveldept@gmail.com,password      |    wA_TODAY           |
    | leafleveldept@gmail.com,password      |    wA_WEEK            |
    | leafleveldept@gmail.com,password      |    ECARDS_COUNT    |
    | leafleveldept@gmail.com,password      |    RECENT_DATE     |
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password |    DEPARTMENT      |
    | manager-superadmin@gmail.com,password |    USERS_COUNT     |
    | manager-superadmin@gmail.com,password |    TODAY_FALSE     |
    | manager-superadmin@gmail.com,password |    WEEK_FALSE      |
    | manager-superadmin@gmail.com,password |    TODAY           |
    | manager-superadmin@gmail.com,password |    WEEK            |
    | manager-superadmin@gmail.com,password |    wA_TODAY_FALSE     |
    | manager-superadmin@gmail.com,password |    wA_WEEK_FALSE      |
    | manager-superadmin@gmail.com,password |    wA_TODAY           |
    | manager-superadmin@gmail.com,password |    wA_WEEK            |
    | manager-superadmin@gmail.com,password |    ECARDS_COUNT    |
    | manager-superadmin@gmail.com,password |    RECENT_DATE     |
