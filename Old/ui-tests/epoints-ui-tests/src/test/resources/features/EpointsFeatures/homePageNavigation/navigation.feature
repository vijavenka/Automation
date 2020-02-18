Feature: Epoints navigation
  As an epoints user
  I want to have navigation tool
  So that I could navigate to different epoints pages

  # // 1.1 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
  # --------------------------------------------------------------------------------------------------------- navigation
  @navigation  @homePageAndNavigation
  Scenario: NAVIGATION - changes to get area for desktop(NBO-349) -  navigation
    Given Epoints site is opened
    When User use get button from main navbar
    Then He will land on get online page
    And additional four subnavbar option will appear
    When User use play games link
    Then He will be redirected to get play games page
    When User use watch link
    Then He will be redirected to get watch page
    When User use instore link
    Then He will be redirected to get in-store page
    When User use invite friend link
    Then He will be redirected to get invite friend page

  # // 1.2 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
  # -------------------------------------------------------------- content of navbar on angular page for not logged user
  @navigation @homePageAndNavigation
  Scenario: NAVIGATION - changes to get area for desktop(NBO-349) -  content of navbar on angular page for not logged user
    Given Epoints site is opened
    When User use get button from main navbar
    Then He can see that navbar has proper content on angular page for not logged user

  # // 1.3 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
  # ------------------------------------------------------------------ content of navbar on angular page for logged user
  @navigation @homePageAndNavigation
  Scenario: NAVIGATION - changes to get area for desktop(NBO-349) -  content of navbar on angular page for logged user
    Given Epoints site is opened
    Given User is logged into epoints page
    When User use get button from main navbar
    Then He can see that navbar has proper content on angular page for logged user

  # // 2.1 //  ----------------------------------------------- SPECSAVERS - add partner page link to navigation(NBO-748)
  # -------------------------------------------------------------------------------------------- partner page sub navbar
  @navigation @homePageAndNavigation
  Scenario: SPECSAVERS - add partner page link to navigation(NBO-748) -  partner page sub navbar
    Given Epoints site is opened
    Given Partner page is opened
    When User click case studies sub navbar option
    Then He will be redirected to specsavers partner page

  # // 3.1 //  ----------------- DESKTOP - EPOINTS - IP recognition or manual selection to set global site view(NBO-546)
  # ----------------------------------------------------------------------------------- zone picker content/close button
  @navigation @homePageAndNavigation
  Scenario: NAVIGATION - zone picker - zone picker content/close button
    Given Epoints site is opened
    When Zone picker panel will be opened
    Then User will see that zone picker panel has proper content
    When User click close zone picker button
    Then Zone picker panel will be closed