Feature: Epoints go instore page
  As an epoints user
  I want to have go instore page
  So that I could get information about bigdl partner

  # // 1.1 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
  # ------------------------------------------------------------------------------------------------------- page content
  # ------------------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
  # ----------------------------------------------------------------------------------------- change of texts and images
  @goInstorePage @getSection
  Scenario: PARTNER PAGE - create partner page for BDL for desktop(NBO-344) - page content
    Given Instore page is opened
    When user look on instore page
    Then He will see proper instore header text
    And Three instore images will be displayed
    And Three instore section descriptions will be displayed
    And Two app links will be displayed

  # // 1.2 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
  # ------------------------------------------------------------------------------------------------------- android link
  @goInstorePage @getSection
  Scenario: PARTNER PAGE - create partner page for BDL for desktop(NBO-344) - android link
    Given Instore page is opened
    When User click android app link
    Then Google play store will be opened in new window

  # // 1.3 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
  # ----------------------------------------------------------------------------------------------------- app store link
  @goInstorePage @getSection
  Scenario: PARTNER PAGE - create partner page for BDL for desktop(NBO-344) - app store link
    Given Instore page is opened
    When User click app store link
    Then iTunes will be opened in new window

  # // 2.1 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
  # --------------------------------------------------------------------------------------------------------- bigdl link
  @goInstorePage @getSection
  Scenario: EPOINTS DESKTOP - update the in-store partner page (NBO-752) - bigdl link
    Given Instore page is opened
    When User click bigdl text link
    Then bigDl page will be opened in new page

  # // 2.2 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
  # -------------------------------------------------------------------------------------------------- android text link
  @goInstorePage @getSection
  Scenario: EPOINTS DESKTOP - update the in-store partner page (NBO-752) - android text link
    Given Instore page is opened
    When User click android app text link
    Then Google play store will be opened in new window

  # // 2.3 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
  # -------------------------------------------------------------------------------------------------- android text link
  @goInstorePage@getSection
  Scenario: EPOINTS DESKTOP - update the in-store partner page (NBO-752) - android text link
    Given Instore page is opened
    When User click app store text link
    Then iTunes will be opened in new window