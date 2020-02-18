Feature: Epoints voucher page
  As an epoints user
  I want to have voucher page
  So that I could search and use vouchers codes which fit my requirements and expectations

  # // 1.1 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # ------------------------------------------------------------------------------------------ not logged, expiry filter
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario Outline: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, expiry filter
    Given Voucher page is opened clear
    When User select chosen expiry filter '<filter>'
    Then Voucher results will be displayed in chosen filter order '<filter>'

    Examples:
        |filter|
        |relevance|
        |expirysoonest|
        |expirylatest|

  # // 1.2 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # --------------------------------------------------------------------- not logged, department filter and clear button
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, department filter and clear button
    Given Voucher page is opened
    When User select some department on department filter
    Then Department facet will disappear
    And Vouchers will be properly filtered according to chosen department
    And Bradcrumb will show proper department name
    When User click 'all departments' button on breadcrumb to show initial results
    Then Department facet will appear
    And Results will be reset

  # // 1.3 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # ----------------------------------------------------------------------- not logged, merchant filter and clear button
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, merchant filter and clear button
    Given Voucher page is opened
    When User select some merchant on merchant filter
    Then Vouchers will be properly filtered according to chosen merchant
    And Bradcrumb will show proper merchant name
    When User click 'clear' button on merchant facet
    Then Results will be reset

  # // 1.4 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # ----------------------------------------------------------------------- not logged, category filter and clear button
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, category filter and clear button
    Given Voucher page is opened
    Given Some department is chosen
    When User select some category on category filter
    Then Vouchers will be properly filtered according to chosen category
    And Bradcrumb will show proper category name
    When User click 'clear' button on category facet
    Then Results will be reset

  # // 1.5 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # --------------------------------------------------------------------------------- not logged, merchant filter search
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, merchant filter search
    Given Voucher page is opened
    When User use merchant search with some phrase
    Then Merchants on merchant facet should match search criteria

  # // 1.6 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # --------------------------------------------------------------------------------- not logged, category filter search
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, category filter search
    Given Voucher page is opened
    Given Some department is chosen
    When User use category search with some phrase
    Then Categories on category facet should match search criteria

  # // 1.7 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # --------------------------------------------------------------------------------------- not logged, clear all button
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, clear all button
    Given Voucher page is opened
    Given Some department is chosen
    Given Some merchant is chosen
    Given Some category is chosen
    Then Vouchers results are filtered, department filter is invisible and breadcrumb is in use
    When User click clear all button
    Then Results will be set to initial position and no filter will be selected

  # // 1.8 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # ------------------------------------------------------------------------------------------------------ share options
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @userIsLogoutFromFacebook @voucherPage @getSection
  Scenario Outline: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - share options
    Given Voucher page is opened
    When User click chosen share option '<shareOption>' of chosen deal
    Then Proper share modal window will be opened with filled link

    Examples:
        |shareOption|
        |facebook|
        |twitter|
        |google|

  # // 1.9 //  ------------------------------------------------ EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # --------------------------------------------------------------------- not logged, breadcrumb independent more than 3
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, breadcrumb independent more than 3
    Given Voucher page is opened
    Given Some department is chosen
    When User select three categories
    Then Breadcrumb shows three categories sentence
    When User select three merchants
    Then Breadcrumb shows three merchants sentence

  # // 1.10 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # ----------------------------------------------------------------------- not logged, breadcrumb all filters used once
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, breadcrumb all filters used once
    Given Voucher page is opened
    Given Some department is chosen
    Given Some category is chosen
    Given Some merchant is chosen
    When User look on breadcrumb
    Then He can see that all chosen filters are included in breadcrumb
    Then User can remove selected options using breadcrumb links

  # // 1.11 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # -------------------------------------------------------------------------------- not logged, get voucher code button
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, get voucher code button
    Given Voucher page is opened
    When User click 'get voucher code' button of chosen voucher
    Then New page with transition page will be opened
    And Voucher code will be displayed on chosen voucher card

  # // 1.12 //  ----------------------------------------------- EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160)
  # ------------------------------------------------------------------------ not logged, individual voucher page content
  # Update ------------------------------------------------------ EPOINTS - complete Angular for vouchers page(NBO-1435)
  @voucherPage @getSection
  Scenario: EPOINTS VOUCHERS - update voucher UI on desktop(RD-4160) - not logged, individual voucher page content
    Given Voucher page is opened
    Given Chosen voucher elements are saved and individual voucher page is opened
    Then All voucher card elements on individual page are the same as on voucher page

  # // 1.13 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
  # ------------------------------------------------------------------- ind voucher page, main - get voucher code button
  @voucherPage @getSection
  Scenario: EPOINTS - complete Angular for vouchers page(NBO-1435) - ind voucher page, main - get voucher code button
    Given Individual voucher page is opened
    When User click 'get voucher code' button of main voucher on individual voucher page
    Then New page with transition page will be opened
    And Voucher code will be displayed on main voucher card on individual voucher page
    And Go to retailer button will be displayed on main voucher card on individual voucher page

  # // 1.14 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
  # ----------------------------------------------------------------------------- ind voucher page, main - share options
  @userIsLogoutFromFacebook @voucherPage @getSection
  Scenario Outline: EPOINTS - complete Angular for vouchers page(NBO-1435) - ind voucher page, main - share options
    Given Individual voucher page is opened
    When User click chosen share option '<shareOption>' of main voucher on individual voucher page
    Then Proper share modal window will be opened with filled link

    Examples:
      |shareOption|
      |facebook|
      |twitter|
      |google|

  # // 1.15 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
  # --------------------------------------------------------------------------- ind voucher page, more - proper retailer
  @voucherPage @getSection
  Scenario: EPOINTS - complete Angular for vouchers page(NBO-1435) - ind voucher page, more - proper retailer
    Given Individual voucher page is opened with more vouchers section
    When User look on individual voucher page
    Then He can see that additional vouchers belongs to proper retailer
    And More voucher section header has proper name

  # // 1.16 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
  # ---------------------------------------------------------------------------- ind voucher page, more - see all button
  @voucherPage @getSection
  Scenario: EPOINTS - complete Angular for vouchers page(NBO-1435) - ind voucher page, more - see all button
    Given Individual voucher page is opened with more vouchers section
    When User click se all button in more vouchers section
    Then He will be redirected to voucher page
    And All displayed vouchers are belongs to same selected merchant

  # // 1.17 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
  # ------------------------------------------------------------------- ind voucher page, more - get voucher code button
  @voucherPage @getSection
  Scenario: EPOINTS - complete Angular for vouchers page(NBO-1435) - ind voucher page, more - get voucher code button
    Given Individual voucher page is opened with more vouchers section
    When User click 'get voucher code' button of other voucher on individual voucher page
    Then New page with transition page will be opened
    And Voucher code will be displayed on other voucher card on individual voucher page
    And Go to retailer button will be displayed on other voucher card on individual voucher page

  # // 1.18 //  ------------------------------------------------- EPOINTS - complete Angular for vouchers page(NBO-1435)
  # ----------------------------------------------------------------------------- ind voucher page, more - share options
  @userIsLogoutFromFacebook @voucherPage @getSection
  Scenario Outline: EPOINTS - complete Angular for vouchers page(NBO-1435) - ind voucher page, main - share options
    Given Individual voucher page is opened with more vouchers section
    When User click chosen share option '<shareOption>' of other voucher on individual voucher page
    Then Proper share modal window will be opened with filled link

    Examples:
      |shareOption|
      |facebook|
      |twitter|
      |google|