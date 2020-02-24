Feature: Epoints API: Ecards -> ecard details
  As ePoints user
  I want to be able to see individual ecard page
  To be able to see all details including personal message of ecards which were sent to me and by me

  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if it's possible to get single ecard details
    Given User is authorizing with following data '<login_password>'
    And User have list of his historical ecards '<direction>'
    When User call for individual ecard details
    Then Get ecard details call returns details of selected ecard

  @qa
    Examples:
      | login_password                              | direction |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | received  |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | sent      |


  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if it's possible to get single other user personal ecard details
    Given User is authorizing with following data 'api_test_user.a_1@api.iat.admin.pl,P@ssw0rd'
    And User have list of his historical ecards 'received'
    And User is authorizing with following data '<login_password>'
    When User call for individual ecard details which belongs to other user '<status>'
    Then Get ecard details should not be returned '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                  | status | error     | message          |
      | api_test_user.a.1.1_2@api.iat.admin.pl,P@ssw0rd | 403    | Forbidden | Access is denied |


  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if it's possible to get single ecard details which not exists
    Given User is authorizing with following data '<login_password>'
    When User call for individual ecard details which not exists '<status>'
    Then Get ecard details should not be returned '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                              | status | error     | message                          |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | 404    | Not Found | Cannot find eCard with id = [%s] |