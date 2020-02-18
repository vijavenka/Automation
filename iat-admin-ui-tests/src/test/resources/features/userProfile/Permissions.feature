Feature: Permissions in IAT Admin
  As a platform owner
  I want to have mechanism to show only part of admin interface structure according to the user's permissions
  So that users with each role can strictly do and see in application only what is defined

  @Regression @Permissions @NBO-10442
  @setManagerThresholdTo50Before @setManagerThresholdToNoneAfter
  Scenario Outline: Permissions for user role - structure visibility
    Given <Username> is logged in to IAT Admin platform
    When He has role '<RoleType>' in the system
    Then User can see only appropriate menu elements according to his role accesses '<Accesses>', '<wizardFinished>'

    Examples:
      | Username     | RoleType   | Accesses                                                                                                                                                                                      | wizardFinished |
      | manager      | Manager    | Reporting;EcardUsageBreakdown;PointsAllocation;GrantDepartments;GrantUsers;ForApproval;AllocationHistory;GrantDepartmentsHistory;GrantUsersHistory;UsersManagement;BrowseUsers;NewUser        | true           |
      | admin        | Admin      | Reporting;EcardUsageBreakdown;PointsAllocation;GrantDepartments;GrantUsers;ForApproval;AllocationHistory;GrantDepartmentsHistory;GrantUsersHistory;UsersManagement;BrowseUsers;NewUser        | true           |
      | superadmin   | Superadmin | Reporting;EcardUsageBreakdown;Config;PointsAllocation;GrantDepartments;GrantUsers;ForApproval;AllocationHistory;GrantDepartmentsHistory;GrantUsersHistory;UsersManagement;BrowseUsers;NewUser | true           |
      | wizard admin | Superadmin | Wizard                                                                                                                                                                                        | false          |
      | uberadmin    | Uberadmin  | PointsAllocation;GrantPartners;AllocationHistory;GrantPartnersHistory                                                                                                                         | true           |

  @Regression @Permissions
  @setManagerThresholdTo50Before @setManagerThresholdToNoneAfter
  Scenario Outline: Permissions for user role - landing page, available options
    Given <Username> is logged in to IAT Admin platform
    When He has role '<RoleType>' in the system
    Then User '<Username>' is on landing page and can see links '<Links>'
    And '<Username>' cannot see links for which he does not have permissions '<notAvailableLinks>'

    Examples:
      | Username     | RoleType   | Links                                                                                                                                        | notAvailableLinks                                                                                                                                                    |
      | manager      | Manager    | REPORTING;ECARD USAGE BREAKDOWN;GRANT DEPARTMENTS;GRANT USERS;DEPARTMENTS;USERS;BROWSE USERS;NEW USER                                        | REASONS;POINTS SHARING;ECARD TEMPLATES;GRANT PARTNERS;PARTNERS                                                                                                       |
      | admin        | Admin      | REPORTING;ECARD USAGE BREAKDOWN;GRANT DEPARTMENTS;GRANT USERS;DEPARTMENTS;USERS;BROWSE USERS;NEW USER                                        | REASONS;POINTS SHARING;ECARD TEMPLATES;GRANT PARTNERS;PARTNERS                                                                                                       |
      | superadmin   | Superadmin | REPORTING;ECARD USAGE BREAKDOWN;REASONS;POINTS SHARING;ECARD TEMPLATES;GRANT DEPARTMENTS;GRANT USERS;DEPARTMENTS;USERS;BROWSE USERS;NEW USER | GRANT PARTNERS;PARTNERS                                                                                                                                              |
      | wizard admin | Superadmin |                                                                                                                                              | REPORTING;ECARD USAGE BREAKDOWN;REASONS;POINTS SHARING;ECARD TEMPLATES;GRANT DEPARTMENTS;GRANT USERS;DEPARTMENTS;USERS;BROWSE USERS;NEW USER;GRANT PARTNERS;PARTNERS |
      | uberadmin    | Uberadmin  | GRANT PARTNERS;PARTNERS                                                                                                                      | REPORTING;ECARD USAGE BREAKDOWN;REASONS;POINTS SHARING;ECARD TEMPLATES;GRANT DEPARTMENTS;GRANT USERS;DEPARTMENTS;USERS;BROWSE USERS;NEW USER                         |

  @Regression @Permissions
  Scenario: Help pdf
    Given Superadmin is logged in to IAT Admin platform
    When User click help option available in menu
    Then New tab will be opened with IAT admin pdf guide