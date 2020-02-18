Feature: Epoints API: Ecards -> company activity
  As ePoints user
  I want to be able to see company ecards activity
  To be able to know what ecards were sent in my company between which employees

  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if it's possible to get company ecards activity list
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    And IAT Admin partner eCards settings for pointsSharing are '<pointsSharing>'
    When User call for ecards company activity history
    Then Ecards company activity history will be returned
    And Ecards company ectivity have maximum 20 results

  @qa
    Examples:
      | login_password                              | pointsSharing |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | SAME_COMPANY  |


  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if returned ecards are from proper range
    Given User is authorizing with following data '<login_password>'
    And IAT Admin partner eCards settings for pointsSharing are '<pointsSharing>'
    When User call for ecards company activity history
    Then Ecards are returned from range '<pointsSharing>' according to partners manager settings

  @qa
    Examples:
      | login_password                              | pointsSharing   |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | SAME_COMPANY    |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | SAME_DEPARTMENT |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | ALL_USERS       |


  #//TODO for now ecardsVisible in partner is set by hand that is why we need to use additional user assigned to other partner for this test case
  @Regression @NegativeCase @Ecard
  Scenario Outline: Check if user without ecards permission can pull company activity
    Given User is authorizing with following data '<login_password>'
    When User without ecards call for ecards company activity history '<status>'
    Then Ecards activity are not returned '<status>', '<error>', '<message>'

    Examples:
      | login_password           | status | error     | message          |
      | epoints@test.test,pppppp | 403    | Forbidden | Access is denied |
