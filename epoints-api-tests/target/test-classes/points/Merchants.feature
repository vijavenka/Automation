Feature: Epoints API: Points -> merchants details
  As an epoints user
  I want to have merchant individual page
  To be able to get basic information about merchant and see offers of 'specials merchant'

  @Regression @PositiveCase @merchants
  Scenario Outline: Points - individual merchant details
    Given Epoints API is responding to requests
    When User request details of selected merchant available on merchant list '<params>'
    Then Merchant details response data is same as in contract
    And Returned data in merchant details match those returned in merchants list

  @qa @stag @prod
    Examples:
      #keyword, page, pageSize, department, prefixes
      | params                      |
      | null;0;5;null;null;null     |
      | null;0;5;specials;null;null |


  @Regression @PositiveCase @merchants
  Scenario Outline: Points - individual merchant offers
    Given Epoints API is responding to requests
    When User request offers of selected merchant available on merchant list '<params>'
    Then Merchant offers response data is same as in contract

  @qa @stag @prod
    Examples:
      #keyword, page, pageSize, department, prefixes
      | params                      |
      | null;0;5;specials;null;null |


  @Regression @PositiveCase @promotedMerchants
  Scenario Outline: Get promoted merchant
    Given Epoints API is responding to requests
    When User request promoted merchants '<ids>' details
    Then Promoted merchants details response data is same as in contract for leads merchant
    And Proper merchants were returned according to request data '<ids>'

  @qa
    Examples:
      | ids                                                                       | is_leads |
      | 30a49bb8-bb71-4b51-9482-15c93abbf252                                      | true     |
      | 70324625-314d-4fe9-99e9-0eacde8b3d5a                                      | false    |
      | 70324625-314d-4fe9-99e9-0eacde8b3d5a,30a49bb8-bb71-4b51-9482-15c93abbf252 | false    |
#  @stag
#    Examples:
#      | ids                                                                       | is_leads |
#      | f9a359fa-61f8-4e1f-bb81-07c7e7467e84                                      | true     |
#      | 70324625-314d-4fe9-99e9-0eacde8b3d5a                                      | false    |
#      | 70324625-314d-4fe9-99e9-0eacde8b3d5a,f9a359fa-61f8-4e1f-bb81-07c7e7467e84 | false    |


  @Regression @PositiveCase @promotedMerchants
  Scenario Outline: Get promoted merchant - request inactive merchant data
    Given Epoints API is responding to requests
    When User request promoted merchants '<ids>' details
    Then Inactive merchants are not returned in promoted endpoint

  @qa
    Examples:
      | ids                                  | comment           |
      | 1b6373c8-219e-4bcc-a8c3-e45cc80c4ec2 | inactive merchant |
#  @stag
#    Examples:
#      | ids                                  | comment           |
#      | 1b6373c8-219e-4bcc-a8c3-e45cc80c4ec2 | inactive merchant |