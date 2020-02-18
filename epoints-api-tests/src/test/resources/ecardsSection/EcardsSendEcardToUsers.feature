Feature: Epoints API: Ecards -> send
  As ePoints user
  I want to be able to create ecard
  To be able to send it to other epoints users

  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if it's possible to get list of reasons
    Given User is authorizing with following data '<login_password>'
    When User call for get ecards reasons list
    Then Get ecard reasons call returns list of reasons

  @qa
    Examples:
      | login_password                              |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd |


  @Regression @NegativeCase @Ecard
  Scenario Outline: Check if it's possible to get list of reasons - error message validation
    Given User is authorizing with following data '<login_password>'
    When User call for get ecards reasons list with incorrect data '<status>'
    Then Get ecard reasons call for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password           | status | error     | message          |
      | epoints@test.test,pppppp | 403    | Forbidden | Access is denied |


  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if it's possible to get list of templates
    Given User is authorizing with following data '<login_password>'
    And IAT Admin partner eCards settings for templates are '<settingsTemplate>'
    When User call for get ecards templates list
    Then Get ecard templates call returns list of templates according '<settingsTemplate>'

  @qa
    Examples:
      | login_password                              | settingsTemplate |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | true             |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | false            |


  @Regression @NegativeCase @Ecard
  Scenario Outline: Check if it's possible to get list of templates - error message validation
    Given User is authorizing with following data '<login_password>'
    When User call for get ecards templates list with incorrect data '<status>'
    Then Get ecard templates call for incorrect data returns error message '<status>', '<error>', '<message>'

    Examples:
      | login_password           | status | error     | message          |
      | epoints@test.test,pppppp | 403    | Forbidden | Access is denied |


  @Regression @PositiveCase @Ecard
  Scenario Outline: Sending ecards user by user
    Given IAT Admin partner eCards settings for pointsSharing are '<userSharePointsRange>'
    And User is authorizing with following data '<login_password>'
    And Ecards sender balance before send is known
    When Send ecards call is made with data '<jsonBody>', '201'
    Then Ecards sender balance is reduced according ecard details

  @qa
    Examples:
      | login_password                                  | jsonBody                                                                                                                                                         | userSharePointsRange |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "templateId": "default", "message": "SAME_DEPARTMENT with points, user to user", "pointsValue": 2, "usersKey": ["DYNAMIC", "3"], "cc":[]} | SAME_DEPARTMENT      |
      | api_test_user.a.1.1_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "SAME_COMPANY with points, user to user", "pointsValue": 2, "usersKey": ["DYNAMIC", "2"], "cc":[]}    | SAME_COMPANY         |
#      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "ALL_USERS with points, user to user", "pointsValue": 2, "usersKey": ["DYNAMIC", "1], "cc":[]}        | ALL_USERS            |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "templateId": "default", "message": "SAME_DEPARTMENT without points, user to user", "usersKey": ["DYNAMIC", "3"], "cc":[]}                | SAME_DEPARTMENT      |
      | api_test_user.a.1.1_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "SAME_COMPANY without points, user to user", "usersKey": ["DYNAMIC", "2"], "cc":[]}                   | SAME_COMPANY         |
#      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "ALL_USERS with points, user to user", "usersKey": ["DYNAMIC", "1"], "cc":[]}                         | ALL_USERS            |


  @Regression @NegativeCase @Ecard
  Scenario Outline: Sending ecards user by user - Error message validation
    Given IAT Admin partner eCards settings for pointsSharing are '<userSharePointsRange>'
    And User is authorizing with following data '<login_password>'
    When Send ecards call is made with data '<jsonBody>', '<status>'
    Then Send ecards call with incorrect data returns error message '<status>', '<error_fieldName>', '<message>'

  @qa
    Examples:
      | login_password                                  | jsonBody                                                                                                                                                                                                                           | userSharePointsRange | status | error_fieldName | message                                                                                                           |
      # points value is lover than reason allows
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD points less than min", "pointsValue": 1, "usersKey": ["DYNAMIC", "3"], "cc":[]}                                                                          | SAME_COMPANY         | 400    | null            | {"errors": [{ "field": "pointsValue", "message": "Points value should be between %s and %s" }] }                  |
      # points value is greater than reason allows
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "AWARDING_M_to_U", "templateId": "default", "message": "Reason AWARDING_M_to_U points higher than max", "pointsValue": 21, "usersKey": ["DYNAMIC", "3"], "cc":[]}                                                     | SAME_COMPANY         | 400    | null            | {"errors": [{ "field": "pointsValue", "message": "Points value should be between %s and %s" }] }                  |
      # points value higher than user balance
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD points higher than balance", "pointsValue": 100000000, "usersKey": ["DYNAMIC", "3"], "cc":[]}                                                            | SAME_COMPANY         | 400    | null            | {"errors": [{ "field": "pointsValue", "message": "Total amount of points to spent is higher than available" } ] } |
      # empty template
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "message": "Reason WIZARD empty template", "pointsValue": 1, "usersKey": ["DYNAMIC", "3"], "cc":[]}                                                                                                         | SAME_COMPANY         | 400    | null            | {"errors": [{ "field": "templateId", "message": "Template cannot be empty" }] }                                   |
      # empty reason
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"templateId": "default", "message": "Reason empty", "pointsValue": 1, "usersKey": ["DYNAMIC", "3"], "cc":[]}                                                                                                                      | SAME_COMPANY         | 400    | null            | {"errors": [{ "field": "reasonId", "message": "Reason cannot be empty" }] }                                       |
      # empty message
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "templateId": "default", "pointsValue": 1, "usersKey": ["DYNAMIC", "3"], "cc":[]}                                                                                                                           | SAME_COMPANY         | 400    | null            | {"errors": [{ "field": "message", "message": "Message cannot be empty" }] }                                       |
      # empty users list
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "templateId": "default", "message": "empty users list", "pointsValue": 1, "usersKey": [], "cc":[]}                                                                                                          | SAME_COMPANY         | 400    | null            | {"errors": [{ "field": "usersKey", "message": "List of users cannot be empty" }] }                                |
      # user to himself
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd     | {"reasonId": "WIZARD", "templateId": "default", "message": "SAME_DEPARTMENT with points, user to himself", "pointsValue": 3, "usersKey": ["HIMSELF"], "cc":[]}                                                                     | SAME_DEPARTMENT      | 400    | null            | {"errors": [{ "field": "usersKey", "message": "Cannot send eCard to yourself" }] }                                |
      # user to users from other department but in same partner userSharePointsRange = SAME_DEPARTMENT
      | api_test_user.a.1.1_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "SAME_DEPARTMENT with points,user to users from other department but in same partner", "pointsValue": 2, "usersKey": ["12cbce58-b778-459b-8a4f-8db9cd6a0dd7"], "cc":[]} | SAME_DEPARTMENT      | 400    | null            | {"errors": [{ "field": "usersKey", "message": "There are invalid users on your list." }] }                        |


  @Regression @PositiveCase @Ecard
  Scenario Outline: Sending ecards user by user and check users balance
    Given IAT Admin partner eCards settings for pointsSharing are '<userSharePointsRange>'
    And User is authorizing with following data '<sender_login_password>'
    And Ecards sender balance before send is known
    And Ecards receiver '<receiver_login_password>' balance before send is known
    When Send ecards call is made with data '<jsonBody>', '201'
    Then Ecards sender balance is reduced according ecard details
    And Ecards receiver '<receiver_login_password>' balance is increased according ecard details

  @qa
    Examples:
      | sender_login_password                       | jsonBody                                                                                                                                                                          | receiver_login_password                         | userSharePointsRange |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD points less than min", "pointsValue": 2, "usersKey": ["894835b9-29d5-4fd9-b933-3a31fd1bc3f8"], "cc":[]} | api_test_user.a.1.1_1@api.iat.admin.pl,P@ssw0rd | SAME_COMPANY         |
#       ##if want check email received by CC you have to replace CC_EMAILs with "email1","email2"
#       ##if want chack email received by users you have to replace RECEIVERS_KEYs with uuids from dynamo: e.g.: "568061b0-9a16-473e-868a-2dae29ebbdd1"
#       ##Template:
#       #| PROVIDE_USER_CREDENTIALS | {"reasonId": "WIZARD", "templateId": "default", "message": "Reason WIZARD points less than min", "pointsValue": 1, "usersKey": ["DYNAMIC", "3"], "cc":[]}                                             | SAME_COMPANY     | PROVIDE_USER_2_CREDENTIALS |


# PD-474 Identify Partners onboarded by Vivup. This is done by setting a flag isPartnerVivupSponsored to 1 in points_manager.Partner table
# PD-474 Then "ecard-vivup-sponsored" email template will be used for sending ecard from eachperson and epoints.
# PD-474 For other than Vivup Partners, flag isPartnerVivupSponsored to 0 in points_manager.Partner table
# PD-474 Then "ecard" email template will be used for sending ecard from eachperson and epoints.
# Similar to "Sending ecards user by user" line 56

Scenario: PD-474 Sending ecard-vivup-sponsored email template for Vivup Partners in epoints
Given In eachperson after logging, whose points_manager.Partner table has isPartnerVivupSponsored set to 1
And While sending an ecard
Then It sends through "ecard-vivup-sponsored" email template

Scenario: PD-474 Sending ecard-vivup-sponsored email template for Vivup Partners in epoints
Given In eachperson after logging, whose points_manager.Partner table has isPartnerVivupSponsored set to 0
And While sending an ecard
Then It sends through "ecard" email template
