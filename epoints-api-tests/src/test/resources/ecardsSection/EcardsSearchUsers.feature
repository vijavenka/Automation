Feature: Epoints API: Ecards -> search users
  As ePoints user
  I want to be able to search users in selected scope
  To be able to select them and send them ecards


  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if it's possible search for users
    And User is authorizing with following data '<login_password>'
    And IAT Admin partner eCards settings for pointsSharing are '<userSharePointsRange>'
    When Ecards Users search call with keyword '<search>' is made
    Then Valid ecards users are returned according partner ecards pointsSharing '<userSharePointsRange>' settings '<login_password>', '<search>'

  @qa
    Examples:
      | login_password                              | search | userSharePointsRange |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | level  | SAME_DEPARTMENT      |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | level  | SAME_COMPANY         |
      #case not supported right now
      #| api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | test  | ALL_USERS            |










