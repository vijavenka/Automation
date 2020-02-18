Feature: Epoints API: User management
  As ePoints user
  I want to be able to get essential account data
  To be able to have current information about my points status, transactions, personal details

  @Regression @PositiveCase @Balance
  Scenario Outline: User balance - get user balance (PD-656 epoints site has to accept camel case email credentials)
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When User balance will be requested for '<businessId>'
    Then Correct balance value will be returned for '<businessId>'

  @qa @stag
    Examples:
      | login_password       | businessId |
      | epointsUserDefault_1 | null       |
      | unitedUserDefault_1  | united     |

  @prod
    Examples:
      | login_password       | businessId |
      | epointsUserDefault_1 | null       |
#      | unitedUserDefault_1  | united     |

  @Regression @PositiveCase @Transactions
  Scenario Outline: User transactions - get user transactions
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When User transactions will be requested for parameters '<uuid>', '<page>', '<size>', '<sort>', '<type>', '<businessId>', '200'
    Then Correct transactions will be returned for parameters '<uuid>', '<page>', '<size>', '<sort>', '<type>', '<businessId>'

  @qa @stag
    Examples:
      | login_password       | uuid          | page | size | sort         | type                                         | businessId |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | CONFIRMED                                    | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | REDEEMED                                     | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | SPENT                                        | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | RECEIVED                                     | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | TRANSFERED                                   | null       |

      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | CONFIRMED                                    | united     |
      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | REDEEMED                                     | united     |
      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | SPENT                                        | united     |
      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | RECEIVED                                     | united     |
      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | TRANSFERED                                   | united     |

      | epointsUserDefault_1 | previous_call | 0    | 2    | lastest,desc | CONFIRMED                                    | null       |
      | epointsUserDefault_1 | previous_call | 1    | 2    | lastest,desc | CONFIRMED                                    | null       |
      | unitedUserDefault_1  | previous_call | 0    | 2    | lastest,desc | CONFIRMED                                    | united     |
      | unitedUserDefault_1  | previous_call | 1    | 2    | lastest,desc | CONFIRMED                                    | united     |

      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | CONFIRMED,SPENT,REDEEMED,RECEIVED,TRANSFERED | null       |
      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | CONFIRMED,SPENT,REDEEMED,RECEIVED,TRANSFERED | united     |

  @prod
    Examples:
      | login_password       | uuid          | page | size | sort         | type                                         | businessId |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | CONFIRMED                                    | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | REDEEMED                                     | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | SPENT                                        | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | RECEIVED                                     | null       |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | TRANSFERED                                   | null       |

#      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | CONFIRMED                                    | united     |
#      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | REDEEMED                                     | united     |
#      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | SPENT                                        | united     |
#      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | RECEIVED                                     | united     |
#      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | TRANSFERED                                   | united     |

      | epointsUserDefault_1 | previous_call | 0    | 2    | lastest,desc | CONFIRMED                                    | null       |
      | epointsUserDefault_1 | previous_call | 1    | 2    | lastest,desc | CONFIRMED                                    | null       |
#      | unitedUserDefault_1  | previous_call | 0    | 2    | lastest,desc | CONFIRMED                                    | united     |
#      | unitedUserDefault_1  | previous_call | 1    | 2    | lastest,desc | CONFIRMED                                    | united     |

      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | CONFIRMED,SPENT,REDEEMED,RECEIVED,TRANSFERED | null       |
#      | unitedUserDefault_1  | previous_call | 0    | 9999 | lastest,desc | CONFIRMED,SPENT,REDEEMED,RECEIVED,TRANSFERED | united     |



  @Regression @NegativeCase @Transactions
  Scenario Outline: User transactions - get user transactions - error message validation
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When User transactions will be requested for parameters '<uuid>', '<page>', '<size>', '<sort>', '<type>', '<businessId>', '<status>'
    Then Correct transactions error message will be returned for parameters '<error>', '<message>'

  @qa @stag @prod
    Examples:
      | login_password       | uuid          | page | size | sort         | type      | businessId      | status | error       | message                                                                                                                                                                              |
      | epointsUserDefault_1 | wrongUuid     | 0    | 9999 | lastest,desc | CONFIRMED | null            | 400    | Bad Request | User 'e79fc5cf-f5d3-406f-a2b2-dccd962c20d0' cannot make a request on behalf of user 'wrongUuid'                                                                                      |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | WrongType | null            | 400    | Bad Request | Request parameter 'type' (WrongType) is not equal 'CURRENT' and is not a comma separated list of elements from [PENDING, CONFIRMED, DECLINED, SPENT, REDEEMED, TRANSFERED, RECEIVED] |
      | epointsUserDefault_1 | previous_call | 0    | 9999 | lastest,desc | CONFIRMED | WrongBusinessID | 400    | Bad Request | Value 'WrongBusinessID' is incorrect                                                                                                                                                 |

  @Regression @PositiveCase @RewardsHistory
  Scenario Outline: User rewards history - get user rewards history
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When User rewards history will be requested for parameters '<email>', '<size>', '<businessId>', '200'
    Then Correct rewards history will be returned for parameters '<email>', '<size>', '<businessId>'

  @qa @stag
    Examples:
      | login_password       | email         | size | businessId |
      | epointsUserDefault_1 | previous_call | 10   | null       |
      | unitedUserDefault_1  | previous_call | 4    | united     |

  @prod
    Examples:
      | login_password       | email         | size | businessId |
      | epointsUserDefault_1 | previous_call | 10   | null       |
#      | unitedUserDefault_1  | previous_call | 4    | united     |



  @Regression @PositiveCase @RewardsHistory
  Scenario Outline: User rewards history - get user rewards history - error message validation
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When User rewards history will be requested for parameters '<email>', '<size>', '<businessId>', '<status>'
    Then Correct rewards history error message will be returned for parameters '<error>', '<message>'

  @qa @stag @prod
    Examples:
      | login_password       | email            | size | businessId            | status | error       | message                                                                                                |
      | epointsUserDefault_1 | notExistingEmail | 4    | null                  | 400    | Bad Request | User 'e79fc5cf-f5d3-406f-a2b2-dccd962c20d0' cannot make a request on behalf of user 'notExistingEmail' |
      | epointsUserDefault_1 | previous_call    | 4    | notExistingBusinessId | 400    | Bad Request | Value 'notExistingBusinessId' is incorrect                                                             |

  @Regression @PositiveCase @PersonalDetails
  @deleteUserAfterTest
  Scenario Outline: User profile - check if account dashboard reacts on setting personal and contact data
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<login>'
    And Confirm email url is opened for user '<login>'
    And Confirm email response returns proper data
    And Account is confirmed with details: '<password>', '<firstName>', '<lastName>', '<gender>'
    And User is authorizing with following data '<login>,<password>'
    And User balance is known
    And Account dashboard profile completion is set to 'false'
    When Personal details will be changed to '<firstName>', '<lastName>', '<title>', '<gender>', '<dob>', '200'
    And Contact details will be changed to '<phoneNo>', '<house>', '<street>', '<city>', '<county>', '<country>', '<postCode>', '200'
    And Account dashboard profile completion is set to 'true'

  @qa
    Examples:
      | login                 | password | firstName   | lastName        | title | gender | dob        | phoneNo   | house     | street     | city     | county     | country     | postCode     |
      | epoints.test.account_ | pppppp   | apiTestName | apiTestLastName | Mrs   | MALE   | 08/06/1989 | 123456789 | apiHouse1 | apiStreet1 | apiCity1 | apiCounty1 | apiCpuntry1 | apiPostcode1 |


  @Regression @PositiveCase @PersonalDetails
  Scenario Outline: User profile - user change his personal details
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When Personal details will be changed to '<firstName>', '<lastName>', '<title>', '<gender>', '<dob>', '200'
    Then User personal details will be properly changed to '<firstName>', '<lastName>', '<title>', '<gender>', '<dob>'
    And All associated with '<businessId>' account virtual groups were properly updated with data '<firstName>', '<lastName>', '<gender>', '<dob>'

  @qa
    Examples:
      | login_password       | firstName    | lastName         | title | gender | dob        | businessId |
      | epointsUserDefault_1 | apiTestName  | apiTestLastName  | Mrs   | MALE   | 08/06/1989 | ePoints    |
      | epointsUserDefault_1 | apiTestName2 | apiTestLastName2 | Miss  | FEMALE | 07/04/1973 | ePoints    |
      | unitedUserDefault_1  | apiTestName  | apiTestLastName  | Mrs   | MALE   | 08/06/1989 | united     |
      | unitedUserDefault_1  | apiTestName2 | apiTestLastName2 | Miss  | FEMALE | 07/04/1973 | united     |

  @Regression @PositiveCase @PersonalDetails
  @deleteUserAfterTest
  Scenario Outline: User profile - receiving epoints for filling personal details
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<login>'
    And Confirm email url is opened for user '<login>'
    And Confirm email response returns proper data
    And Account is confirmed with details: '<password>', '<firstName>', '<lastName>', '<gender>'
    And User is authorizing with following data '<login>,<password>'
    And User balance is known
    When Personal details will be changed to '<firstName>', '<lastName>', '<title>', '<gender>', '<dob>', '200'
    Then User will get 5 points for filling personal details

  @qa
    Examples:
      | login                 | password | firstName   | lastName        | title | gender | dob        |
      | epoints.test.account_ | pppppp   | apiTestName | apiTestLastName | Mrs   | MALE   | 08/06/1989 |

  @Regression @PositiveCase @ContactDetails
  Scenario Outline: User profile - user change his contact details
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When Contact details will be changed to '<phoneNo>', '<house>', '<street>', '<city>', '<county>', '<country>', '<postCode>', '200'
    Then User contact details will be properly changed to '<phoneNo>', '<house>', '<street>', '<city>', '<county>', '<country>', '<postCode>'

  @qa
    Examples:
      | login_password       | phoneNo   | house     | street     | city     | county     | country     | postCode     |
      | epointsUserDefault_1 | 123456789 | apiHouse1 | apiStreet1 | apiCity1 | apiCounty1 | apiCpuntry1 | apiPostcode1 |
      | epointsUserDefault_1 | 987654321 | apiHouse2 | apiStreet2 | apiCity2 | apiCounty2 | apiCpuntry2 | apiPostcode2 |
      | unitedUserDefault_1  | 123456789 | apiHouse1 | apiStreet1 | apiCity1 | apiCounty1 | apiCpuntry1 | apiPostcode1 |
      | unitedUserDefault_1  | 987654321 | apiHouse2 | apiStreet2 | apiCity2 | apiCounty2 | apiCpuntry2 | apiPostcode2 |

  @Regression @PositiveCase @PersonalDetails
  @deleteUserAfterTest
  Scenario Outline: User profile - receiving epoints for filling contact details
    Given Epoints API is responding to requests
    And Join epoints request is triggered with '<login>'
    And Confirm email url is opened for user '<login>'
    And Confirm email response returns proper data
    And Account is confirmed with details: '<password>', '<firstName>', '<lastName>', '<gender>'
    And User is authorizing with following data '<login>,<password>'
    And User balance is known
    When Contact details will be changed to '<phoneNo>', '<house>', '<street>', '<city>', '<county>', '<country>', '<postCode>', '200'
    Then User will get 5 points for filling contact details

  @qa
    Examples:
      | login                 | password | firstName   | lastName        | gender | phoneNo   | house     | street     | city     | county     | country     | postCode     |
      | epoints.test.account_ | pppppp   | apiTestName | apiTestLastName | MALE   | 123456789 | apiHouse1 | apiStreet1 | apiCity1 | apiCounty1 | apiCpuntry1 | apiPostcode1 |


  @Regression @PositiveCase @ConsentDetails
  Scenario Outline: User profile - user change his consent details
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When Consent details will be changed to '<subscribedToMarketingEmails>'
    Then User consent details will be properly changed to '<subscribedToMarketingEmails>'

  @qa
    Examples:
      | login_password       | subscribedToMarketingEmails |
      | epointsUserDefault_1 | true                        |
      | epointsUserDefault_1 | false                       |

  @Regression @PositiveCase @ContactDetails
  Scenario Outline: User profile - user change his email
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When Email change will be requested for '<newEmail>', '<oldEmail>', '200'
    Then New email will not be set before verification '<oldEmail>'
    And New email will be set after verification '<newEmail>', '<oldEmail>'
    And All associated with '<businessId>' account virtual groups were properly updated with email '<newEmail>'

  @qa
    Examples:
      | login_password              | newEmail                    | oldEmail                    | businessId |
      | epointsUserDefault_1        | epointsUserDefaultChanged_1 | epointsUserDefault_1        | ePoints    |
      | epointsUserDefaultChanged_1 | epointsUserDefault_1        | epointsUserDefaultChanged_1 | ePoints    |
      | unitedUserDefault_1         | unitedUserDefaultChanged_1  | unitedUserDefault_1         | united     |
      | unitedUserDefaultChanged_1  | unitedUserDefault_1         | unitedUserDefaultChanged_1  | united     |

  @Regression @PositiveCase @ContactDetails
  Scenario Outline: User profile - user change his password
    Given User is authorizing with following data '<login_password>'
    And User Profile is requested
    When Password change will be requested for '<newPassword>', '<oldPassword>', '200'
    Then User is authorizing with following data 'previousCallEmail,<newPassword>'

  @qa
    Examples:
      | login_password         | newPassword | oldPassword |
      | epointsUserDefault_1   | password2   | password    |
      | epointsUserDefault_1_2 | password    | password2   |
      | unitedUserDefault_1    | password2   | password    |
      | unitedUserDefault_1_2  | password    | password2   |