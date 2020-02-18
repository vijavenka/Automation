Feature: Epoints API: Registration and authorization
  As ePoints user
  I want to be able to authorize in epoints portal
  To be able to get access to functionalities and my profile

  @Regression @PositiveCase @Login
  Scenario Outline: Epoints authorization - login with session ID
    Given Epoints API is responding to requests
    When User is authorizing with following data '<login_password>'
    And User Profile is requested
    Then Login process should return Session ID and loginSuccess response

  @qa
    Examples:
      | login_password                              |
       #default epoints
      | epointsUserDefault_1                        |
      #IAT-Admin
      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd |
      #united
      | unitedUserDefault_1                         |

  @prod
    Examples:
      | login_password       |
       #default epoints
      | epointsUserDefault_1 |
#      #IAT-Admin
#      | api_test_user.a_1@api.iat.admin.pl,P@ssw0rd |
#      #united
#      | unitedUserDefault_1                         |


  @Regression @PositiveCase @Login @united
  Scenario Outline: Epoints authorization - Validate businessId value
    Given Epoints API is responding to requests
    When User is authorizing with following data '<login_password>'
    And User Profile is requested
    Then User profile contains proper value in businessId field '<businessId>'

  @qa
    Examples:
      | login_password       | businessId |
       #standard epoints
      | epointsUserDefault_1 | null       |
      #united
      | unitedUserDefault_1  | united     |

  @prod
    Examples:
      | login_password       | businessId |
       #standard epoints
      | epointsUserDefault_1 | null       |
#      #united
#      | unitedUserDefault_1  | united     |


  @Regression @PositiveCase @EmailVerification
  Scenario Outline: Epoints confirm-email - not pre-filled user data for epoints users
    Given Epoints API is responding to requests
    When Confirm email url is opened for user '<login>'
    Then Confirm email response returns proper data

  @qa
    Examples:
      | login                                       |
      | test.automation.api.not.active1@epoints.com |


  @Regression @PositiveCase @EmailVerification
  Scenario Outline: Epoints confirm-email - pre-filled user data for IAT Admin users
    Given Epoints API is responding to requests
    When Confirm email url is opened for user '<login>'
    Then Confirm email response returns proper data for IAT Admin users

  @qa
    Examples:
      | login                                  |
      #IAT-Admin
      | api_test_user.a.1.2_5@api.iat.admin.pl |


  @Regression @PositiveCase @EmailVerification @Registration
  @deleteUserAfterTest
  Scenario Outline: Epoints registration
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<login>'
    And Confirm email url is opened for user '<login>'
    And Confirm email response returns proper data
    When Account is confirmed with details: '<password>', '<firstName>', '<lastName>', '<gender>'
    Then User is authorizing with following data '<login>,<password>'

  @qa @stag
    Examples:
      | login                 | password | firstName | lastName    | gender |
      | epoints.test.account_ | pppppp   | apiAuto   | testAccount | male   |
      | epoints.test.account_ | pppppp   | apiAuto   | testAccount | female |

	  
	@Regression @PositiveCase @EmailVerification @Registration
   @deleteUserAfterTest @NewUserWithGDPR
	Scenario Outline: Epoints registration - new user is created when GDPR is true - Positive case
      Given Epoints API is responding to requests
      And Join epoints request is triggered with '<login>'
      And Confirm email url is opened for user '<login>'
      And Confirm email response returns proper data
      When Account is confirmed with GDPR details: '<jsonBody>', '<status>'
      Then User is authorizing with following data '<login>,<password>'
      And User does subscription registration process '<plan>','<coupon>','<status1>'

	@qa @stag
		Examples:
		| login                 | password  | jsonBody | status | plan | coupon | status1 |
        | epoints.test.account4_| Password1  | {"firstName":"apiAuto", "lastName":"testAccount", "emailVerified" : true, "password" : "Password1", "verified" : true, "active":true, "tncAccepted" : true, "privacyAccepted" : true, "marketingPrefSMS" : false, "marketingPrefEmail" : false } | 302  | gold_yearly | GOLDENTICKET | 200 |
        | epoints.test.account5_| Password1  | {"firstName":"apiAuto", "lastName":"testAccount", "emailVerified" : true, "password" : "Password1", "verified" : true, "active":true, "tncAccepted" : true, "privacyAccepted" : true, "marketingPrefSMS" : true,   "marketingPrefEmail": true  } | 302  | gold_yearlyy | GOLDENTICKET | 400 |


  @Regression @PositiveCase @EmailVerification @Registration
  @deleteUserAfterTest @NewUserWithGDPRFalse
  Scenario Outline: Epoints registration - new user is not created when GDPR is false - Negative case
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<login>'
    And Confirm email url is opened for user '<login>'
    And Confirm email response returns proper data
    When Account is confirmed with GDPR details: '<jsonBody>', '<status>'

  @qa @stag
    Examples:
      | login                 | password  |	jsonBody |	status |
      | epoints.test.account1_| password  | {"firstName":"apiAuto", "lastName":"testAccount", "emailVerified" : true, "password" : "Password1", "verified" : true, "active":true, "tncAccepted" : false, "privacyAccepted" : false, "marketingPrefSMS" : true, "marketingPrefEmail" : true  }  | 400 |
      | epoints.test.account2_| password  | {"firstName":"apiAuto", "lastName":"testAccount", "emailVerified" : true, "password" : "Password1", "verified" : true, "active":true, "tncAccepted" : false, "privacyAccepted" : true, "marketingPrefSMS" : true, "marketingPrefEmail" : true }  | 400 |
      | epoints.test.account3_| password  | {"firstName":"apiAuto", "lastName":"testAccount", "emailVerified" : true, "password" : "Password1", "verified" : true, "active":true, "tncAccepted" : true, "privacyAccepted" : false, "marketingPrefSMS" : true, "marketingPrefEmail" : true }  | 400 |

	  

		
  @Regression @PositiveCase @EmailVerification @Registration
  @deleteUserAfterTest
  Scenario Outline: Epoints registration - for other businesses {united}
    Given Epoints API is responding to requests
    When Join epoints request with '<email>' for business '<businessType>' with externalId '<externalId>' is triggered '200'
    And Confirm email url is opened for user '<email>'
    And Confirm email response returns proper data
    When Account is confirmed with details: '<password>', '<firstName>', '<lastName>', '<gender>'
    Then User is authorizing with following data '<email>,<password>'
    And Unsubscribed field in user profile is set by default to 'false'
    And Subscribed to marketing details field is set by default to 'true' in user profile

  @qa @stag
    Examples:
      | email                 | password | firstName | lastName    | gender | businessType | externalId |
      | epoints.test.account_ | pppppp   | apiAuto   | testAccount | male   | united       | random     |

  @deleteUserAfterTest @Regression @PositiveCase @Registration
  Scenario Outline: Epoints registration for united user - clear join
    Given Epoints API is responding to requests
    When Join epoints request with '<email>' for business '<businessType>' with externalId '<externalId>' is triggered '200'
    And Join epoints for business '<businessType>' response returns proper status 'ok'
    Then Account is created with correct registrationSiteId, virtualGroup, externalId for business '<businessType>', '<initialRegistrationSiteName>'
    And Epoints and business '<businessType>' balances are as expected

  @qa @stag
    Examples:
      | email                        | businessType | externalId | initialRegistrationSiteName |
      | epoints.test.united.account_ | united       | random     | united                      |

  @Regression @PositiveCase @Registration
  @deleteUserAfterTest
  Scenario Outline: Epoints registration for united user - united join for existing epoints account
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<email>'
    When Join epoints request with 'previous_call' for business '<businessType>' with externalId '<externalId>' is triggered '200'
    And Join epoints for business '<businessType>' response returns proper status '<status>'
    Then Account is created with correct registrationSiteId, virtualGroup, externalId for business '<businessType>', '<initialRegistrationSiteName>'
    And Epoints and business '<businessType>' balances are as expected

  @qa @stag
    Examples:
      | email                        | businessType | externalId | initialRegistrationSiteName | status  |
      | epoints.test.united.account_ | united       | random     | ePoints                     | mergeOk |

  @Regression @PositiveCase @Registration
  @deleteUserAfterTest
  Scenario: Epoints standard registration and verify - after that united join is made
    Given Epoints API is responding to requests
    And Email 'epoints.test.account_' Join standard epoints and verify account
    When Join epoints request with 'previous_call' for business 'united' with externalId 'random' is triggered '200'
    Then User is authorizing with following data 'previous_call'
    And User Profile is requested
    And User profile contains proper value in businessId field 'united'


  @Regression @PositiveCase @Registration
  Scenario Outline: Epoints registration for united user - united join - error message validation
    Given Epoints API is responding to requests
    When Join epoints request with '<email>' for business '<businessType>' with externalId '<externalId>' is triggered '<status>'
    Then Correct united account creation error message will be returned '<error>', '<message>'

#  used account for testing epoints.test.account.united_1496222064329@epoints.com / cc0dd7b4-6ee6-4993-8f91-3b57bc283985 / externalUnitedId == 9992
  @qa
    Examples:
      | email                                                 | businessType | externalId   | error       | message                                                      | status |
      # wrong businessType
      | epoints.test.united.account_                          | not existing | random       | Bad Request | Unknown externalIdType: not existing                         | 400    |
      # trying to override already existing externalId
      | epoints.test.account.united_1496222064329@epoints.com | united       | 9993         | Bad Request | User already belongs to United                               | 400    |
      # trying to assign already used externalId to another user
      | epoints.test.united.account_                          | united       | 9992         | Bad Request | United ID already belongs to United but with different email | 400    |
      # businessId out of range
      | epoints.test.united.account_                          | united       | 9999999      | Bad Request | Parameter United ID has to be value in range [1-99999]       | 400    |
      # businessId out of range
      | epoints.test.united.account_                          | united       | 0            | Bad Request | Parameter United ID has to be value in range [1-99999]       | 400    |
      # businessId out of range
      | epoints.test.united.account_                          | united       | not a number | Bad Request | Parameter United ID has to be value in range [1-99999]       | 400    |

  @Regression @PositiveCase @Registration
  @deleteUserAfterTest
  Scenario Outline: Epoints mobile registration
    Given Epoints API is responding to requests
    When Join mobile epoints request is triggered with '<email>', '<password>', '<firstName>', '<lastName>', '200'
    Then Created account is active: true, verified: false
    And Created account has properly saved provided data '<email>', '<password>', '<firstName>', '<lastName>'
    And Created account belongs only to epoints group

  @qa @stag
    Examples:
      | email                 | password | firstName   | lastName            |
      | epoints.test.account_ | password | apiAutoName | testAccountLastName |

  @Regression @PositiveCase @Registration
  @deleteUserAfterTest
  Scenario Outline: Epoints mobile registration - negative cases
    Given Epoints API is responding to requests
    When Join mobile epoints request is triggered with '<email>', '<password>', '<firstName>', '<lastName>', '<status>'
    Then Correct mobile account creation error message will be returned '<status>', '<error>', '<message>'

  @qa @stag
    Examples:
      | email                 | password | firstName   | lastName            | status | error       | message                                              |
      | epointsUserDefault_1  | password | apiAutoName | testAccountLastName | 400    | 400         | User already registered.                             |
      | epoints.test.account_ | null     | apiAutoName | testAccountLastName | 400    | Bad Request | Required String parameter 'password' is not present  |
      | epoints.test.account_ | password | null        | testAccountLastName | 400    | Bad Request | Required String parameter 'firstName' is not present |
      | epoints.test.account_ | password | apiAutoName | null                | 400    | Bad Request | Required String parameter 'lastName' is not present  |



#  @stag
##  used account for testing //TODO
#    Examples:
#      | email | businessType | externalId | error | message | status |

#  Scenario: Epoints registration and verify in one step
#    Given Epoints API is responding to requests
#    When Email 'epointsdonotremove_' Join standard epoints and verify account
#    Then User is authorizing with following data 'previous_call'
#
#  Scenario: Epoints registration and verify for united user in one step
#    Given Epoints API is responding to requests
#    When Join epoints request with 'uniteddonotremove_' for business 'united' with externalId 'random' and verify account
#    Then User is authorizing with following data 'previous_call'
