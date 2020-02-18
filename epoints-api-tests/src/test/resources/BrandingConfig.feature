Feature: Epoints API: Branding config
  As ePoints user
  I want to be able to get branding information
  To be able to views epoints.com page with branding design

  @Regression @PositiveCase @Branding
  Scenario Outline: Epoints Branding - contract validation
    Given User is authorizing with following data '<login_password>'
    When User requesting branding call '<brandingAvailability>'
    Then Branding response will be returned '<brandingAvailability>'

  @qa
    Examples:
      | login_password                              | brandingAvailability |
      | epointsUserDefault_1                        | false                |
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd | true                 |

  @prod
    Examples:
      | login_password       | brandingAvailability |
      | epointsUserDefault_1 | false                |
      #| TODO | true                 |







