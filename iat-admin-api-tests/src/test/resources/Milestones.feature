Feature: IAT Admin HR - milestones management
  As an admin
  I want to be able to add or remove milestones
  So that I will be able to control which of important dates connected with my employees will be presented to me

  @Regression @PositiveCase @Milestones
  Scenario Outline: Milestone adding
    Given IAT Admin user is logged in with credentials '<login_password>'
    When He add '<milestoneValue>' milestone for specified milestoneType '<milestoneType>', '201'
    Then Milestone will be available on '<milestoneType>' milestones list

    Examples:
      | login_password                                   | milestoneType   | milestoneValue |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | birthdate       | random         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | workAnniversary | random         |

  @Regression @PositiveCase @Milestones
  @RestoreMilestonesListForDefaultPartner
  Scenario Outline: Milestone deleting
    Given IAT Admin user is logged in with credentials '<login_password>'
    And He add '<milestoneValue>' milestone for specified milestoneType '<milestoneType>', '201'
    When He remove '<milestoneId>' milestone for specified milestoneType '<milestoneType>', '200'
    Then Milestone will not be available on '<milestoneType>' milestones list

    Examples:
      | login_password                                   | milestoneType   | milestoneId   |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | birthdate       | previous_call |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | workAnniversary | previous_call |

  @Regression @PositiveCase @Milestones
  Scenario Outline: Add milestone - negative scenario
    Given IAT Admin user is logged in with credentials '<login_password>'
    When He add '<milestoneValue>' milestone for specified milestoneType '<milestoneType>', '<status>'
    Then Add milestone for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | login_password                                          | milestoneType   | milestoneValue | error     | message          | status |
#      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | birthdate        | stringWrongValue | TODO      | TODO             | 400    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | notExistingType | random         | TODO      | TODO             | 400    |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | birthdate       | random         | Forbidden | Access is denied | 403    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | birthdate       | random         | Forbidden | Access is denied | 403    |

  @Regression @PositiveCase @Milestones
  Scenario Outline: Remove milestone - negative scenario
    Given IAT Admin user is logged in with credentials '<login_password>'
    And He add 'random' milestone for specified milestoneType '<milestoneType>', '201'
    When He remove '<milestoneId>' milestone for specified milestoneType '<milestoneType>', '<status>'
    Then Remove milestone for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | login_password                                          | milestoneType   | milestoneId   | error     | message          | status |
      # super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | notExistingType | previous_call | TODO      | TODO             | 400    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | birthdate       | 9999999999    | TODO      | TODO             | 400    |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | birthdate       | random        | Forbidden | Access is denied | 403    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | birthdate       | random        | Forbidden | Access is denied | 403    |

  @Regression @PositiveCase @Milestones
  Scenario Outline: Get milestones - negative scenario
    Given IAT Admin user is logged in with credentials '<login_password>'
    When He get milestones list for specified milestoneType '<milestoneType>', '<status>'
    Then Get milestone for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | login_password                                          | milestoneType   | error     | message          | status |
      # super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        | notExistingType | TODO      | TODO             | 400    |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd | birthdate       | Forbidden | Access is denied | 403    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              | birthdate       | Forbidden | Access is denied | 403    |

  @Regression @PositiveCase @Milestones @deleteUserAfterTest
  @RestoreMilestonesListForDefaultPartner
  Scenario Outline: Get milestones list
    Given Birthday is enabled and workAnniversary is enabled for company
    Given User with correct data is created according to '<milestoneType>', '<milestoneValue>', '<anniversaryDaysFromToday>', '<createdUserDepartmentName>'
    Given IAT Admin user is logged in with credentials '<user_login_password>'
    When He get users list for specified milestoneType '<milestoneType>', '<allEvents>', '200'
    Then Created user is on user for milestones list for milestoneValue '<milestoneValue>' and '<anniversaryDaysFromToday>', '<shouldBeReturned>'

  @qa
    Examples:
      | user_login_password                                          | milestoneType   | createdUserDepartmentName  | allEvents | milestoneValue | anniversaryDaysFromToday | shouldBeReturned |
      # BIRTHDATE
      # super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | birthdate       | Department level 1 [A]     | false     | 18             | 6                        | true             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | birthdate       | Department level 3 [A.1.3] | true      | 2              | 0                        | true             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | birthdate       | Department level 3 [A.1.3] | false     | 2              | 0                        | false            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | birthdate       | Department level 1 [B]     | true      | 40             | 3                        | true             |
      # manager of A 1 department
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | birthdate       | Department level 2 [A.1]   | false     | 18             | 0                        | true             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | birthdate       | Department level 3 [A.1.1] | true      | 2              | 0                        | true             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | birthdate       | Department level 3 [A.1.2] | false     | 18             | 0                        | true             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | birthdate       | Department level 2 [A.1]   | false     | 18             | 7                        | false            |
      # manager of A 1.1 department
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | birthdate       | Department level 3 [A.1.1] | false     | 18             | 4                        | true             |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | birthdate       | Department level 3 [A.1.1] | true      | 2              | 6                        | true             |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | birthdate       | Department level 3 [A.1.1] | false     | 21             | 7                        | false            |
      #| api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | birthdate       | Department level 3 [A.1.2] | false     | 18             | 0                        | false            |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | birthdate       | Department level 1 [A]     | false     | 18             | 0                        | false            |
      # WORK ANNIVERSARY
      # super admin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | workAnniversary | Department level 1 [A]     | false     | 20             | 0                        | true             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | workAnniversary | Department level 1 [B]     | false     | 25             | 6                        | true             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | workAnniversary | Department level 2 [A.1]   | false     | 10             | 3                        | true             |
      #| api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | workAnniversary | Department level 3 [A.1.3] | false     | 10             | 7                        | false            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | workAnniversary | Department level 3 [A.1.3] | false     | 3              | 0                        | false            |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd             | workAnniversary | Department level 3 [A.1.3] | true      | 3              | 6                        | true             |
      # manager of A 1 department
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | workAnniversary | Department level 2 [A.1]   | false     | 20             | 0                        | true             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | workAnniversary | Department level 3 [A.1.2] | false     | 10             | 2                        | true             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | workAnniversary | Department level 2 [A.1]   | false     | 10             | 7                        | false            |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | workAnniversary | Department level 3 [A.1.1] | true      | 2              | 0                        | true             |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd      | workAnniversary | Department level 3 [A.1.1] | false     | 2              | 0                        | false            |
      # manager of A 1.1 department
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | workAnniversary | Department level 3 [A.1.1] | false     | 10             | 6                        | true             |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | workAnniversary | Department level 3 [A.1.1] | false     | 10             | 7                        | false            |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | workAnniversary | Department level 3 [A.1.1] | true      | 3              | 6                        | true             |
      #| api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | workAnniversary | Department level 3 [A.1.1] | true      | 3              | 7                        | false            |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | workAnniversary | Department level 2 [A.1]   | false     | 10             | 6                        | false            |
      | api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd | workAnniversary | Department level 1 [B]     | true      | 3              | 6                        | false            |

  @Regression @PositiveCase @Milestones
  @deleteUserAfterTest @RestoreMilestonesListForDefaultPartner
  Scenario Outline: Get milestones list for switched off in settings notifications
    Given Birthday is disabled and workAnniversary is disabled for company
    Given User with correct data is created according to '<milestoneType>', '<milestoneValue>', '<anniversaryDaysFromToday>', '<createdUserDepartmentName>'
    Given IAT Admin user is logged in with credentials '<user_login_password>'
    When He get users list for specified milestoneType '<milestoneType>', '<allEvents>', '<status>'
    Then Get milestones for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | user_login_password                              | createdUserDepartmentName | milestoneType   | allEvents | milestoneValue | anniversaryDaysFromToday | error       | message                                             | status |
      # superadmin
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | Department level 1 [A]    | birthdate       | false     | 18             | 0                        | Bad Request | Visibility of milestone birthdate is disabled       | 400    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | Department level 1 [A]    | workAnniversary | false     | 18             | 0                        | Bad Request | Visibility of milestone workAnniversary is disabled | 400    |


  @Regression @PositiveCase @Milestones
  Scenario Outline: Default milestones list creation for new partner - wizard triggered
    Given IAT Admin user is logged in with credentials '<login_password>'
    When IAT admin is logged for first time
    And Wizard step up to '4' is finished
    Then Default milestones lists should be created for new partner

  @qa
    Examples:
      | login_password              |
      | api_admin_wizard_1,P@ssw0rd |