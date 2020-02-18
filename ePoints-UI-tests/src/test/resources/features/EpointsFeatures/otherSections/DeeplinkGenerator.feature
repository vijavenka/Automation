Feature: Epoints babyworld deeplink generator
  As an epoints admin user
  I want to have deeplink generator page
  So that I could create deepliplinks from external retailer pages and use them on babyworld pages

  # // 1.1 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # ------------------------------------------------------------------------------------------------------- page content
  # Test checks content of /deeplink/babyworld page. There should be: predictive store input field, native url input field,
  # p1 input field and generated link area. Apart of that two buttons: clear and generate link. Each input field should have own label.
  @Regression @DeeplinkCreator
  Scenario: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - page content
    Given User is opening deeplink creator landing page
    Then All needed input elements and buttons ar visible

  # // 1.2 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # ------------------------------------------------------------------------------------------------------- clear button
  # When user enter text to store, native url, p1 fields he should be able to clear all fields by clicking on 'clear button'.
  @Regression @DeeplinkCreator
  Scenario: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - clear button
    Given User is opening deeplink creator landing page
    And He can see that input fields are empty
    When User fill generator input fields with some values
    And Click generator clear button
    Then Input generator fields will be cleared

  # // 1.3 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # -------------------------------------------------------------------------------------------------- predictive search
  # When user starts to filling store text then predictive mechanism should starts working. It means that retailers which
  # match typed text should be displayed on DDL under store field and user can choose one from it.
  @Regression @DeeplinkCreator
  Scenario Outline: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - predictive search
    Given User is opening deeplink creator landing page
    When User start typing some text '<storeName>' into store input field
    Then DDL with retailer will be displayed
    And On list user can find these with phrase '<storeName>' typed into input field

    Examples:
      | storeName |
      | Bab       |
      | John      |

  # // 1.4 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # --------------------------------------------------------------------------------- generation of link and its working
  # When user correctly fill store, native url and p1 fields and click 'generate link' button, in generated link field
  # proper deeplink should be generated. Used should redirect user to transition page and then to original native page.
  @Regression @DeeplinkCreator
  Scenario Outline: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - generation of link and its working
    Given User is opening deeplink creator landing page
    When User properly enter store field and native url field with '<storeName>' '<nativeURL>'
    And User click generate link button
    Then Deeplink will be properly generated
    And Try direct link will be displayed
    When User use created link
    Then He will be redirected to epoints transition page
    When User use continue anyway button
    Then He will be redirected to native retailer page '<shortURL>'

    Examples:
      | storeName          | nativeURL                                                                                       | shortURL                   |
      | John Lewis Rewards | http://www.johnlewis.com/?s_afcid=af_95617&awc=1203_1410950012_b8fd0e4c7d7c704ce38f79911c1a5c7b | https://www.johnlewis.com/ |

  # // 1.5 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # ------------------------------------------------------------------------------------------------- merchant not found
  # When user enter merchant name which is not in database then deeplink will not be generated and proper message displayed
  @Regression @DeeplinkCreator
  Scenario Outline: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - merchant not found
    Given User is opening deeplink creator landing page
    When User properly enter store field and native url field with '<wrongStoreName>' '<nativeURL>'
    And User click generate link button
    Then Merchant not found message including wrong merchant name '<wrongStoreName>' will be displayed
    And Deeplink will not be generated

    Examples:
      | wrongStoreName | nativeURL                                                                                        |
      | John Lewissss  | https://www.johnlewis.com/?s_afcid=af_95617&awc=1203_1410950012_b8fd0e4c7d7c704ce38f79911c1a5c7b |