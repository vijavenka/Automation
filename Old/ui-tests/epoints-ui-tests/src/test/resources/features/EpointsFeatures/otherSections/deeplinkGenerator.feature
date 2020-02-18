Feature: Epoints babyworld deeplink generator
  As an epoints admin user
  I want to have deeplink generator page
  So that I could create deepliplinks from external retailer pages and use them on babyworld pages

  # // 1.1 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # ------------------------------------------------------------------------------------------------------- page content
  # Test checks content of /deeplink/babyworld page. There should be: predictive store input field, native url input field,
  # p1 input field and generated link area. Apart of that two buttons: clear and generate link. Each input field should have own label.
  @deeplinkCreator @externalPages
  Scenario: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - page content
    Given User is opening deeplink creator landing page clear
    When He look at deeplink generator page
    Then He can see babyworld logo
    And All needed input elements and buttons

  # // 1.2 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # ------------------------------------------------------------------------------------------------------- clear button
  # When user enter text to store, native url, p1 fields he should be able to clear all fields by clicking on 'clear button'.
  @deeplinkCreator @externalPages
  Scenario: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - clear button
    Given User is opening deeplink creator landing page
    When He look at deeplink generator page
    Then He can see that input fields are empty
    When User fill generator input fields with some values
    And Click generator clear button
    Then Input generator fields will be cleared

  # // 1.3 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # -------------------------------------------------------------------------------------------------- predictive search
  # When user starts to filling store text then predictive mechanism should starts working. It means that retailers which
  # match typed text should be displayed on DDL under store field and user can choose one from it.
  @deeplinkCreator @externalPages
  Scenario Outline: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - predictive search
    Given User is opening deeplink creator landing page
    When User start typing some text '<storeName>' into store input field
    Then DDL with retailer will be displayed
    And On list user can find these with phrase '<storeName>' typed into input field

    Examples:
        |storeName|
        |House of|
        |John|

  # // 1.4 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # --------------------------------------------------------------------------------- generation of link and its working
  # When user correctly fill store, native url and p1 fields and click 'generate link' button, in generated link field
  # proper deeplink should be generated. Used should redirect user to transition page and then to original native page.
  @deeplinkCreator @externalPages
  Scenario Outline: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - generation of link and its working
    Given User is opening deeplink creator landing page
    When User properly enter store field and native url field with '<storeName>' '<nativeURL>'
    And User click generate link button
    Then Deeplink will be properly generated
    And Try direct link will be displayed
    When User use created link
    Then He will be redirected to epoints transition page
    When User disagree to collecting epoints in at transition modal
    Then He will be redirected to native retailer page '<shortURL>'

    Examples:
        |storeName|nativeURL|shortURL|
        |John Lewis|http://www.johnlewis.com/?s_afcid=af_95617&awc=1203_1410950012_b8fd0e4c7d7c704ce38f79911c1a5c7b|http://www.johnlewis.com/|
        |House of Fraser|http://www.houseoffraser.co.uk/on/demandware.store/Sites-hof-Site/default/Default-Start?cm_mmc=AWIN-_-Textlink-_-Generic-_-Homepage&istCompanyId=17910aed-1bae-4362-9580-b523eb87a91e&istItemId=imrqpqmx&istBid=t&_$ja=tsid:45090|http://www.houseoffraser.co.uk/on/demandware.store/Sites-hof-Site/default/Default-Start|

  # // 1.5 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
  # ------------------------------------------------------------------------------------------------- merchant not found
  # When user enter merchant name which is not in database then deeplink will not be generated and proper message displayed
  @deeplinkCreator @externalPages
  Scenario Outline: WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993) - merchant not found
    Given User is opening deeplink creator landing page
    When User properly enter store field and native url field with '<wrongStoreName>' '<nativeURL>'
    And User click generate link button
    Then Merchant not found message including wrong merchant name '<wrongStoreName>' will be displayed
    And Deeplink will not be generated

    Examples:
        |wrongStoreName|nativeURL|shortURL|
        |John Lewissss|http://www.johnlewis.com/?s_afcid=af_95617&awc=1203_1410950012_b8fd0e4c7d7c704ce38f79911c1a5c7b|http://www.johnlewis.com/|