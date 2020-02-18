Feature: Epoints API: Ecards -> sent/received history
  As ePoints user
  I want to be able to see ecards page history
  To be able to see all ecards which were sent to me and by me in scrolable view

  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if user history sent/received ecards messages have proper schema
    Given User is authorizing with following data '<login_password>'
    When Ecards user calls for ecard history - tab '<historyTab>'
    Then User receives message related with Ecard history '<historyTab>' page which has proper schema
    And User ecards activity '<historyTab>' messages are related with specific user '<login_password>'

  @qa
    Examples:
      | login_password                              | historyTab |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | sent       |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | received   |


  @Regression @PositiveCase @Ecard
  Scenario Outline: Check if user history sent/received ecards messages have proper ecards list order - new on top
    Given User is authorizing with following data '<login_password>'
    When Ecards user calls for ecard history - tab '<historyTab>'
    Then Ecards history list in message has descending order

  @qa
    Examples:
      | login_password                              | historyTab |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | sent       |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | received   |