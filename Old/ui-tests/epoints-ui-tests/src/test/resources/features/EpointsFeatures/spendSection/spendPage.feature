Feature: Epoints spend page
  As an epoints user
  I want to have spend page
  So that I could use search, or one of three main filters presented in different tabs to start looking for redemptions

  # // 1.1 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
  # ------------------------------------------------------------------------------------------------- spend page content
  # Update -------------------------------- EPOINTS SPEND PAGE DESKTOP - add information and disclaimer banner(NBO-1052)
  @spendHomePage @spendSection
  Scenario: Spend page refresh - restructure page(RD-3575) - spend page content
    Given Spend page is opened
    When User look at spend landing page
    Then He can see main items counter
    And He can see search component
    And He can see two tabs with basic filter selection
    And He can see top generic banner

  # // 1.2 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
  # -------------------------------------------------------------------------------------------- redemption counter link
  #@spendHomePage @spendSection
  #Scenario: Spend page refresh - restructure page(RD-3575) - redemption counter link
    #Given Spend page is opened
    #When User click redemption counter link
    #Then He will be redirected to browse rewards page
    #And Item number on search settings panel will be the same as on redemption counter

  # // 1.3 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
  # -------------------------------------------------------------------------------- search with points range DDL filter
  @spendHomePage @spendSection
  Scenario Outline: Spend page refresh - restructure page(RD-3575) - search with points range DDL filter
    Given Spend page is opened
    When User enter some phrase '<phrase>' into search input field
    And User select some redemption range '<pointsRange>' from ddl
    And User click search button on spend page
    Then He will be redirected to browse rewards page
    And Filters will be properly set on browse rewards page according to typed phrase and chosen points range '<phrase>' '<pointsRange>'
    And Results will be appropriate to typed phrase and chosen points range '<phrase>' '<pointsRange>'

    Examples:
      |phrase|pointsRange|
      |watch|1,000+|
      |Dolls|5,000+|

  # // 1.4 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
  # ---------------------------------------------------------------------------------------------- browse by departments
  @spendHomePage @spendSection
  Scenario: Spend page refresh - restructure page(RD-3575) - browse by departments
    Given Spend page is opened
    When User go to 'browse by departments' tab
    And User select some department card
    Then He will be redirected to browse rewards page
    And Department facet should be already chosen
    And Products number should be correct according to department card counter

  # // 1.5 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
  # ------------------------------------------------------------------------------------------- rewards by epoints total
  @spendHomePage @spendSection
  Scenario: Spend page refresh - restructure page(RD-3575) - rewards by epoints total
    Given Spend page is opened
    When User go to 'rewards by epoints total' tab
    And User select some epoints value card
    Then He will be redirected to browse rewards page
    And Presented redemptions will be in proper ranges according to chosen epoints value card

  # // 1.6 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
  # -------------------------------------------------------- redemption counter link for logged user / high points value
  @setHighEpointsValue @spendHomePage @spendSection
  Scenario: Spend page refresh - restructure page(RD-3575) - redemption counter link for logged user / high points value
    Given Spend page is opened by logged user
    When User look at spend landing page
    Then He can see main available items counter
    When User click items counter
    Then He will be redirected to spend list page


  # // 1.7 //  ---------------------------------------------------------- Spend page refresh - restructure page(RD-3575)
  # --------------------------------------------------------- redemption counter link for logged user / low points value
  @setLowEpointsValue @spendHomePage @spendSection
  Scenario: Spend page refresh - restructure page(RD-3575) - redemption counter link for logged user / low points value
    Given Spend page is opened by logged user
    When User look at spend landing page
    Then He will not see items counter because any of them is available for him