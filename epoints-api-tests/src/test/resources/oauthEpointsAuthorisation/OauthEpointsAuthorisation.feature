Feature: Epoints API: Oauth authorisation
  As ePoints user using mobile app
  I want to be able to be authorised with oath provider
  To be able to use functions restricted for authorized user

  @Regression @PositiveCase @EpointsOAuth
  @setOAuthToNull
  Scenario Outline: Profile call
    Given User is authenticating with following data '<grantType>', '<login_password>', 'default', '200' password based grant type
    And User Profile is requested
    When Personal details will be changed to '<firstName>', '<lastName>', '<title>', '<gender>', '<dob>', '200'
    Then User personal details will be properly changed to '<firstName>', '<lastName>', '<title>', '<gender>', '<dob>'
    And All associated with '<businessId>' account virtual groups were properly updated with data '<firstName>', '<lastName>', '<gender>', '<dob>'

  @qa
    Examples:
      | grantType | login_password       | firstName    | lastName         | title | gender | dob        | businessId |
      | password  | epointsUserDefault_1 | apiTestName2 | apiTestLastName2 | Miss  | FEMALE | 07/04/1973 | ePoints    |
      | password  | epointsUserDefault_1 | apiTestName  | apiTestLastName  | Mrs   | MALE   | 08/06/1989 | ePoints    |
      | password  | unitedUserDefault_1  | apiTestName2 | apiTestLastName2 | Miss  | FEMALE | 07/04/1973 | united     |
      | password  | unitedUserDefault_1  | apiTestName2 | apiTestLastName2 | Miss  | FEMALE | 07/04/1973 | united     |

  @Regression @PositiveCase
  @setOAuthToNull
  @clearUserBasketAfterTest @deleteOrderPointsManager @resetProductsListBeforeTest
  Scenario Outline: checkout flow - validation for order properly created in points manager and epoints properly deducted
    Given User is authenticating with following data '<grantType>', '<login_password>', 'default', '200' password based grant type
    And User Profile is requested
    And Fetching membershipType
    And User delivery address is set 'DEFAULT'
    And Redemption item '0' is selected for '<businessId>' from list ordered 'asc'
    And '0' redemption item is added '2' to basket
    And User balance for '<businessId>' is stored
    Given Checkout is made for basket items and deliveryAddress 'DEFAULT' with following totalEpoints 'previous_call' value for '<businessId>' status '200'
    Then Redemption order is properly created for '<businessId>'
    And User balance for '<businessId>' is properly reduced

    Examples:
      | grantType | login_password       | businessId |
      | password  | epointsUserDefault_1 | null       |
#      | password  | unitedUserDefault_1  | united     |