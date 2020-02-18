Feature: Epoints watch videos page
  As an epoints user
  I want to have watch videos page
  So that I could get information about videojug partner

  # // 1.1 //  ------------------------------ MOBILE PARTNER PAGE - create partner page for VIDEOJUG for mobile(NBO-345)
  # ------------------------------------------------------------------------------------------------------- page content
  @watchVideosPage @getSection
  Scenario: MOBILE PARTNER PAGE - create partner page for VIDEOJUG for mobile(NBO-360) - page content
    Given Watch videos page is opened
    When user look on watch videos page
    Then He will see proper header text
    And Three images will be displayed
    And Three section descriptions will be displayed

  # // 1.2 //  ------------------------------ MOBILE PARTNER PAGE - create partner page for VIDEOJUG for mobile(NBO-345)
  # ------------------------------------------------------------------------------------------------------ videojug link
  @watchVideosPage @getSection
  Scenario: MOBILE PARTNER PAGE - create partner page for VIDEOJUG for mobile(NBO-360) - videojug link
    Given Watch videos page is opened
    When User click one of videojug link
    Then He will be redirected to videojug page