Feature: Epoints API: Transition and clickouts
  As ePoints user
  I want to be able to have transition page wit correct link
  To be able to go to external merchant/offer page and been track to get later commission for my shopping

  @Regression @PositiveCase @Transition
  Scenario Outline: Epoints transition-to - contract validation for leads offers
    Given Epoints API is responding to requests
    When Transition details are requested for offerId '<offerId>'
    Then Transition details response is consistent with contract
    And Transition details for lead contains proper static field values

  @qa
    Examples:
      | offerId                              |
      | 058fbfc9-356d-3c46-b053-a373040bf3e2 |
#  @stag
#    Examples:
#      | offerId |
#      |         |


  @Regression @PositiveCase @Transition
  Scenario Outline: Epoints transition-to - contract validation for merchants
    Given Epoints API is responding to requests
    And User is authorizing with following data '<login_password>'
    When Transition details are requested for merchantId '<merchantId>'
    Then Transition details response is consistent with contract
    And Transition details for lead '<is_leads>' merchant contains proper static field values

  @qa
    Examples:
      | login_password       | merchantId                           | is_leads |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    |
      | epointsUserDefault_1 | 30a49bb8-bb71-4b51-9482-15c93abbf252 | true     |
#  @stag
#    Examples:
#      | merchantId                           | is_leads |
#      | a9a6d063-870c-409c-8df8-ec712d40611a | false    |
#      | f8e87117-8f7a-414a-a8d4-2bbdd46975b7 | true     |


  @Regression @PositiveCase @Clickout
  @deleteClickoutAndPointsAfterTest
  Scenario Outline: Clickouts creation validation
    Given Epoints API is responding to requests
    Given User is authorizing with following data '<login_password>'
    When Transition details are requested for merchantId '<merchantId>'
    When Clickout request is made by using transition details redirectUrl
    Then Clickout was properly stored in database

  @qa
    Examples:
      | login_password       | merchantId                           | is_leads |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     |


  @Regression @PositiveCase @Clickout
  @deleteClickoutAndPointsAfterTest
  Scenario Outline: Clickouts status change (PENDING -> CONFIRMED) validation
    Given Epoints API is responding to requests
    And User is authorizing with following data '<login_password>'
    And User balance is known
    And Clickout is generated and properly stored for merchantId '<merchantId>'
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'PENDING') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'PENDING'
    And Points in 'PENDING' status were added to user account
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'CONFIRMED') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'CONFIRMED'
    And Points in 'CONFIRMED' status were added to user account

  @qa
    Examples:
      | login_password       | merchantId                           | is_leads | affiliateNetworkId                   |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 |
      | epointsUserDefault_1 | 20ba67b0-fe79-42c0-ae78-9a537b735044 | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 |


  @PositiveCase @NoLongerRequired-rouletteWillBeThrowOut
  @deleteClickoutAndPointsAfterTest
  Scenario Outline: Roulette - points won when originalPoints status = PENDING - validate clickout CONFIRMED
    Given Epoints API is responding to requests
    And User is authorizing with following data '<login_password>'
    And User balance is known
    And Clickout is generated and properly stored for merchantId '<merchantId>'
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'PENDING') and reports are triggered
    And User won prize on roulette (points value: 'RANDOM', points status: '<rouletteAwardCallStatus>') with status: '201'
    Then Clickout & Points statuses are properly changed into 'PENDING'
    And Points in 'PENDING' status were added to user account
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'CONFIRMED') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'CONFIRMED'
    And Points in 'CONFIRMED' status were added to user account

  @qa
    Examples:
      | login_password       | merchantId                           | is_leads | affiliateNetworkId                   | rouletteAwardCallStatus |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |
      | epointsUserDefault_1 | 20ba67b0-fe79-42c0-ae78-9a537b735044 | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | 20ba67b0-fe79-42c0-ae78-9a537b735044 | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |


  @PositiveCase @NoLongerRequired-rouletteWillBeThrowOut
  @deleteClickoutAndPointsAfterTest
  Scenario Outline: Roulette - points won when originalPoints status = CONFIRMED - validate clickout CONFIRMED
    Given Epoints API is responding to requests
    And User is authorizing with following data '<login_password>'
    And User balance is known
    And Clickout is generated and properly stored for merchantId '<merchantId>'
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'PENDING') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'PENDING'
    And Points in 'PENDING' status were added to user account
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'CONFIRMED') and reports are triggered
    And User won prize on roulette (points value: 'RANDOM', points status: '<rouletteAwardCallStatus>') with status: '201'
    Then Clickout & Points statuses are properly changed into 'CONFIRMED'
    And Points in 'CONFIRMED' status were added to user account

  @qa
    Examples:
      | login_password       | merchantId                           | is_leads | affiliateNetworkId                   | rouletteAwardCallStatus |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |


  @Regression @PositiveCase @Clickout
  @deleteClickoutAndPointsAfterTest
  Scenario Outline: Clickouts status change (PENDING -> DECLINED) validation
    Given Epoints API is responding to requests
    And User is authorizing with following data '<login_password>'
    And User balance is known
    And Clickout is generated and properly stored for merchantId '<merchantId>'
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'PENDING') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'PENDING'
    And Points in 'PENDING' status were added to user account
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'DECLINED') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'DECLINED'
    And Points in 'DECLINED' status were added to user account

  @qa
    Examples:
      | login_password       | merchantId                           | is_leads | affiliateNetworkId                   |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 |


  @PositiveCase @NoLongerRequired-rouletteWillBeThrowOut
  @deleteClickoutAndPointsAfterTest
  Scenario Outline: Roulette - points won when originalPoints status = PENDING - validate clickout DECLINED
    Given Epoints API is responding to requests
    And User is authorizing with following data '<login_password>'
    And User balance is known
    And Clickout is generated and properly stored for merchantId '<merchantId>'
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'PENDING') and reports are triggered
    And User won prize on roulette (points value: 'RANDOM', points status: '<rouletteAwardCallStatus>') with status: '201'
    Then Clickout & Points statuses are properly changed into 'PENDING'
    And Points in 'PENDING' status were added to user account
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'DECLINED') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'DECLINED'
    And Points in 'DECLINED' status were added to user account

  @qa
    Examples:
      | login_password       | merchantId                           | is_leads | affiliateNetworkId                   | rouletteAwardCallStatus |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |


  @PositiveCase @NoLongerRequired-rouletteWillBeThrowOut
  @deleteClickoutAndPointsAfterTest
  Scenario Outline: Roulette - points won when originalPoints status = DECLINED - awarding is not possible in this case
    Given Epoints API is responding to requests
    And User is authorizing with following data '<login_password>'
    And User balance is known
    And Clickout is generated and properly stored for merchantId '<merchantId>'
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'PENDING') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'PENDING'
    And Points in 'PENDING' status were added to user account
    When Clickout reference is updated (affiliateNetworkId '<affiliateNetworkId>', merchantId '%', clickoutStatus 'DECLINED') and reports are triggered
    Then Clickout & Points statuses are properly changed into 'DECLINED'
    And Points in 'DECLINED' status were added to user account
    When User won prize on roulette (points value: 'RANDOM', points status: '<rouletteAwardCallStatus>') with status: '400'


  @qa
    Examples:
      | login_password       | merchantId                           | is_leads | affiliateNetworkId                   | rouletteAwardCallStatus |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | 70324625-314d-4fe9-99e9-0eacde8b3d5a | false    | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | CONFIRMED               |
      | epointsUserDefault_1 | a0c29975-6be6-4183-bc90-f1bf3ec493ba | true     | d8fcae82-e536-4721-8d4d-7eec532eedc3 | PENDING                 |
