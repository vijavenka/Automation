Feature: Epoints browse rewards page
  As an epoints user
  I want to have browse rewards page
  So that I could search, view and add to basket different redemptions

  # // 1.1 //  ------------------------------------------------------------------------------------- Browse rewards page
  # ---------------------------------------------------------------------------------------- browse rewards page content
  # Update -------------------------------- EPOINTS SPEND PAGE DESKTOP - add information and disclaimer banner(NBO-1052)
  @searchProductPage @spendSection
  Scenario: Browse rewards page - browse rewards page content
    Given Browse rewards page is  opened
    When User look at browse redemption page
    Then He can see that search tool is available
    And Top points range filter is available
    And Only department facet is available
    And Search setting wrapper is available
    And Pagination module is available
    And Breadcrumb is available
    And Redemption cards are available
    And Other needed buttons are available
    And He can see top generic banner

  # // 1.2 //  ------------------------------------------- Spend page refresh - show initial department filter (RD-3605)
  # ---------------------------------------------------------------------------------- availability of department filter
  @searchProductPage @spendSection
  Scenario: Spend page refresh - show initial department filter (RD-3605) - availability of department filter
    Given Browse rewards page is  opened
    When User look at browse redemption page
    Then Only department facet is available
    When User choose some department option
    Then Category, brand and epoints range facets will appear

  # // 1.3 //  ------------------------------------------- Spend page refresh - show initial department filter (RD-3605)
  # --------------------------------------------------------------------------------------- working of department filter
  @searchProductPage @spendSection
  Scenario: Spend page refresh - show initial department filter (RD-3605) - working of department filter
    Given Browse rewards page is  opened
    When User choose some department option
    Then Department filter will disappears
    And Redemptions will be filtered according to chosen filter
    When User use 'All department' option from breadcrumb
    Then Department filter will appear
    And Redemption results will be reset

  # // 1.4 //  ------------------------------------------------------------------------------------- Browse rewards page
  # ----------------------------------------------------------------------------------- top epoints range filter working
  @searchProductPage @spendSection
  Scenario: Browse rewards page - top epoints range filter working
    Given Browse rewards page is  opened
    When User chose some top epoints range option from top range filter
    Then Redemptions will be properly filtered according to chosen range option from top range filter

  # // 1.5 //  ------------------------------------------------------------------------------------- Browse rewards page
  # ----------------------------------------------------------------------- Search, and epoints range filter working DDL
  @searchProductPage @spendSection
  Scenario Outline: Browse rewards page - search, and epoints range filter working DDL
    Given Browse rewards page is  opened
    When User enter some phrase '<phrase>' into search input field on redemption page
    And User select some redemption range from ddl on redemption page
    And User click search button on redemption page
    Then Results will be appropriate to typed phrase '<phrase>' and chosen points range

  Examples:
    |phrase|
    |watch|
    |Knee|

  # // 1.6 //  ------------------------------------------------------------------------------------- Browse rewards page
  # ------------------------------------------------------------------------------------ add redemption to basket option
  @searchProductPage @spendSection
  Scenario: Browse rewards page - add redemption to basket option
    Given Browse rewards page is  opened
    When User add to basket chosen redemption
    Then Added redemption can be found in basket preview area
    And  Information that product was added to basket will appear on product card

  # // 1.7 //  ---------------------------------------------------------- Spend page refresh - category filter (RD-3615)
  # --------------------------------------------------------------------------------------------- category facet working
  @searchProductPage @spendSection
  Scenario: Spend page refresh - category filter (RD-3615) - category facet working
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    When Some category is chosen on redemption page
    Then Category facet will disappear
    And Type filter will appear
    And Redemptions will be filtered according to chosen category
    When User click selected department name option from breadcrumb
    Then Type filter will disappears
    And Category filter will appear
    And Redemption results will be reset to this from only department selected

  # // 1.8 //  ------------------------------------------------------------- Spend page refresh - brand filter (RD-3625)
  # ------------------------------------------------------------------------------ brand facet working, and clear button
  @searchProductPage @spendSection
  Scenario: Spend page refresh - brand filter (RD-3625) - brand facet working, and clear button
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    When Some brand is chosen on redemption page
    And Redemptions will be filtered according to chosen brand
    When User click clear button on brand facet
    Then Redemption results will be reset to this from only department selected

  # // 1.9 //  ------------------------------------------------------------- Spend page refresh - brand filter (RD-3625)
  # ----------------------------------------------------------------------------------------- brand facet search working
  @searchProductPage @spendSection
  Scenario: Spend page refresh - brand filter (RD-3625) - brand facet search working
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    When User type some brand name into brand facet search
    Then Brands on brand facet list will match typed name

  # // 1.10 //  ------------------------------------------------------------- Spend page refresh - type filter (RD-3635)
  # ------------------------------------------------------------------------------- type facet working, and clear button
  @searchProductPage @spendSection
  Scenario: Spend page refresh - type filter (RD-3635) - type facet working, and clear button
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    Given Some category is chosen on redemption page
    When Some type is chosen on redemption page
    And Redemptions will be filtered according to chosen type
    When User click clear button on type facet
    Then Redemption results will be reset to this from department and category selected

  # // 1.11 //  ------------------------------------------------------------- Spend page refresh - type filter (RD-3635)
  # ------------------------------------------------------------------------------------------ type facet search working
  @searchProductPage @spendSection
  Scenario: Spend page refresh - type filter (RD-3635) - type facet search working
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    Given Some category is chosen on redemption page
    When User type some type name into type facet search
    Then Types on type facet list will match typed name

  # // 1.12 //  --------------------------------------------- Spend page refresh - add counts to filter values (RD-3677)
  # ----------------------------------------------------------------------------------------------- counters correctness
  @searchProductPage @spendSection
  Scenario: Spend page refresh - add counts to filter values (RD-3677) - counters correctness
    Given Browse rewards page is  opened
    When Some department is chosen on redemption page
    Then Proper number of product should be displayed on navigation bar according to chosen department
    When Some category is chosen on redemption page
    Then Proper number of product should be displayed on navigation bar according to chosen category
    When Some brand is chosen on redemption page
    Then Proper number of product should be displayed on navigation bar according to chosen brand
    When Some type is chosen on redemption page
    Then Proper number of product should be displayed on navigation bar according to chosen type

  # // 1.13 //  ----------------------------------------------- Spend page refresh - points to purchase filter (RD-3645)
  # ------------------------------------------------------------------------------------------- number of epoints filter
  @searchProductPage @spendSection
  Scenario: Spend page refresh - points to purchase filter (RD-3645) - number of epoints filter
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    And Number of epoints filter is visible
    When User enter epoints 'from' and 'to' values
    And Click Go button on number of epoints filter
    Then Redemptions will be filtered according to chosen 'from' and 'to' range
    When User click clear button on number of epoints facet
    Then Redemption results will be reset to this from only department selected

  # // 1.14 //  ------------------------------------------------------------------------------------ Browse rewards page
  # --------------------------------------------------------------------------------------------------- clear all button
  @searchProductPage @spendSection
  Scenario: Browse rewards page - clear all button
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    Given Some category is chosen on redemption page
    Given Some brand is chosen on redemption page
    Given User enter epoints 'from' and 'to' values
    And Click Go button on number of epoints filter
    When User click 'clear all' button
    Then Department filter will appear
    And Category facet will disappear
    And Brand filter will disappears
    And Type filter will disappears

  # // 1.15 //  -------------------------------------------------------- Spend page refresh - breadcrumb logic (RD-3655)
  # --------------------------------------------------------------------------------- breadcrumb independent more than 3
  @searchProductPage @spendSection
  Scenario: Spend page refresh - breadcrumb logic (RD-3655) - breadcrumb independent more than 3
    Given Browse rewards page is  opened
    Given Some department is chosen on redemption page
    Given Some category is chosen on redemption page
    When User select three brands
    Then Breadcrumb shows Three brands sentence
    When User select three types
    Then Breadcrumb shows Three types sentence

  # // 1.16 //  -------------------------------------------------------- Spend page refresh - breadcrumb logic (RD-3655)
  # ---------------------------------------------------------------------------------------------- all filters used once
  @searchProductPage @spendSection
  Scenario: Spend page refresh - breadcrumb logic (RD-3655) - all filters used once
    Given Browse rewards page is  opened
    When Some department is chosen on redemption page
    And Some category is chosen on redemption page
    And Some brand is chosen on redemption page
    And Some type is chosen on redemption page
    And User enter epoints 'from' and 'to' values
    And Click Go button on number of epoints filter
    Then Breadcrumb shows all facet values in proper order

  # // 1.17 //  ------------------------------------------------------------------------------------ Browse rewards page
  # ------------------------------------------------------------------------------------------------ points order filter
  @searchProductPage @spendSection
  Scenario Outline: Browse rewards page - points order filter
    Given Browse rewards page is  opened
    When User chose points order option '<orderOption>'
    Then Redemption results will be displayed in proper order '<orderOption>'

    Examples:
      |orderOption|
      |Relevance|
      |Points low to high|
      |Points high to low|


  # // 1.18 //  ------------------------------------------------------------------------------------ Browse rewards page
  # ---------------------------------------------------------------- bottom pagination, working of next/previous buttons
  @searchProductPage @spendSection
  Scenario: Browse rewards page - bottom pagination, working of next/previous buttons
    Given Browse rewards page is  opened
    Given Visible redemption number is set to 20
    When User click next page button on spend
    Then Page will be changed to next on spend
    And Showing module will be increased on spend
    When User click previous page button on spend
    Then Showing module will be decreased on spend
    And Page will be changed to previous on spend

  # // 1.19 //  ------------------------------------------------------------------------------------ Browse rewards page
  # ------------------------------------------------------------------ bottom pagination, working of page numbers button
  @searchProductPage @spendSection
  Scenario: Browse rewards page - bottom pagination, working of page numbers button
    Given Browse rewards page is  opened
    Given Visible redemption number is set to 20
    When User looks on bottom pagination component
    Then He can see tat proper number of page is displayed according to 'out of' information on spend
    When User use some bottom pagination page number on spend
    Then proper page will be displayed on spend

  # // 1.20 //  ------------------------------------------------------------------------------------ Browse rewards page
  # --------------------------------------------------------------------------------------------- top search box, arrows
  @searchProductPage @spendSection
  Scenario: Browse rewards page - top search box, arrows
    Given Browse rewards page is  opened
    Given Visible redemption number is set to 20
    When User click right arrow on top search box on spend
    Then Page will be changed to next on spend
    And Showing module will be increased on spend
    When User click left arrow on top search box on spend
    And Showing module will be decreased on spend
    Then Page will be changed to previous on spend

  # // 1.21 //  ------------------------------------------------------------------------------------ Browse rewards page
  # ------------------------------------------------------------------------------------- top search box, items per page
  @searchProductPage @spendSection
  Scenario: Browse rewards page - top search box, items per page
    Given Browse rewards page is  opened
    When User change 'item per page' parameter on  top search box
    Then Number of elements on page will be changed