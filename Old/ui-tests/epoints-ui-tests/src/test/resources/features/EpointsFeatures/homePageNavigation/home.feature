Feature: Epoints home page
  As an epoints user
  I want to have home page
  So that I could get basic information about epoints, login, etc.

  # // 1.1 //  ------------------- HOME PAGE - change title and copy of get epoints section for desktop version(NBO-336)
  # ------------------------------------------------------------------------------------------------ get epoints section
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - change title and copy of get epoints section for desktop version(NBO-336) - get epoints section
    Given Epoints home page is opened
    When User look on get epoints for shopping online section
    Then Get epoints for shopping online section has proper title
    And Get epoints for shopping online section text is correct
    And Online stores link on get epoints for shopping online section works fine

  # // 1.2 //  ------------------- HOME PAGE - change title and copy of get epoints section for desktop version(NBO-336)
  # ---------------------------------------------------------------------------------------------- retailer card clicked
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - change title and copy of get epoints section for desktop version(NBO-336) - retailer card clicked
    Given Epoints home page is opened
    When User click some retailer card on get epoints for shopping online section
    Then He will be redirected to transition page of proper retailer

  # // 2.1 //  ------------------------------ HOME PAGE - add new sections in correct order in-store and videos(NBO-338)
  # --------------------------------------------------------------------------------------- get epoints in-store section
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - add new sections in correct order in-store and videos(NBO-338) - get epoints in-store section
    Given Epoints home page is opened
    When User look on get epoints for shopping in-store section
    Then Get epoints for shopping in-store section has proper title
    And Get epoints for shopping in-store section text is correct
    And Learn more link on get epoints for shopping online section works fine

  # // 2.2 //  ------------------------------ HOME PAGE - add new sections in correct order in-store and videos(NBO-338)
  # ----------------------------------------------------------------------------------------- get epoints videos section
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - add new sections in correct order in-store and videos(NBO-338) - get epoints videos section
    Given Epoints home page is opened
    When User look on get epoints for watching videos section
    Then Get epoints for watching videos section has proper title
    And Get epoints for watching videos section text is correct
    And Great videos link on get epoints for shopping online section works fine

  # // 3.1 //  ----------------------- HOME PAGE - update "spending epoints is fun" section for desktop version(NBO-342)
  # --------------------------------------------------------------------------------------- spend epoints easily section
  # Update ---------------------------------- EPOINTS DESKTOP - spend message improvements from home page link(NBO-1039)
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - update "spending epoints is fun" section for desktop version(NBO-342) - spend epoints easily section
    Given Epoints home page is opened
    When User look on spend all your epoints easily section
    Then Spend all your epoints easily section has proper title
    And Spend all your epoints easily section text is correct
    And Image link on spend all your epoints easily section works fine

  # // 4.1 //  ------------------------ HOME PAGE - add game section to the new home page structure for desktop(NBO-337)
  # ------------------------------------------------------------------------------ get epoints for playing games section
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - add game section to the new home page structure for desktop(NBO-337) - get epoints for playing games section
    Given Epoints home page is opened
    When User look on get epoints for playing games section
    Then Get epoints for playing games section has proper title
    And Get epoints for playing games section text is correct
    And Playing games link on get epoints for playing games section works fine

  # // 5.1 //  ---------------------------- HOME PAGE - add video to non-logged in home page to desktop version(NBO-335)
  # ----------------------------------------------------------------------------------------------------- video presence
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - add video to non-logged in home page to desktop version(NBO-335) - video presence
    Given Epoints home page is opened
    And Video splash screen is visible
    And Video play button is visible
    When User click play button
    Then Video will be started

  # // 6.1 //  -------------------------------- IDENTIFIED HOME PAGE - remove the need for identified home page(NBO-343)
  # ------------------------------------------------------------------------------------------ three states of home page
  @homePage @homePageAndNavigation
  Scenario: IDENTIFIED HOME PAGE - remove the need for identified home page(NBO-343) - three states of home page
    Given Epoints home page is opened
    And For not logged user join forms are displayed
    When User login into epoints account
    Then Join forms will be removed from home page
    When Users session expires
    Then Sign in form appears instead of join form in logout state
    And User account will be not available before login

  # // 7.1 //  --------------------------------------------- EPOINTS DESKTOP - update the in-store partner page(NBO-752)
  # --------------------------------------------------------------------------------------------------- video image link
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - add new sections in correct order in-store and videos(NBO-338) - video image link
    Given Epoints home page is opened
    When User click on image in get epoints for watching videos section
    Then He will be redirected to watch page

  # // 7.2 //  --------------------------------------------- EPOINTS DESKTOP - update the in-store partner page(NBO-752)
  # ------------------------------------------------------------------------------------------------- instore image link
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - add new sections in correct order in-store and videos(NBO-338) - instore image link
    Given Epoints home page is opened
    When User click on image in get epoints for shopping in-store section
    Then He will be redirected to instore page

  # // 7.3 //  --------------------------------------------- EPOINTS DESKTOP - update the in-store partner page(NBO-752)
  # --------------------------------------------------------------------------------------------- clumsy bird image link
  @homePage @homePageAndNavigation
  Scenario: HOME PAGE - add new sections in correct order in-store and videos(NBO-338) - clumsy bird image link
    Given Epoints home page is opened
    When User click on image in get epoints for playing games clumsy bird section
    Then He will be redirected to play page

  # // 8.1 //  -------------------------------------------------------------------------- EPOINTS DESKTOP - cookie panel
  # ----------------------------------------------------------------------------------------------- cookie panel content
  @homePage @homePageAndNavigation
  Scenario: EPOINTS DESKTOP - cookie panel - cookie panel content
    Given Epoints home page is opened clear
    When User looks on top of page
    Then He will see cookie panel with proper content

  # // 8.2 //  -------------------------------------------------------------------------- EPOINTS DESKTOP - cookie panel
  # ---------------------------------------------------------------------------------------------- accept button working
  @homePage @homePageAndNavigation
  Scenario: EPOINTS DESKTOP - cookie panel - accept button working
    Given Epoints home page is opened clear
    When User click accept button on cookie panel
    Then Cookie panel will be closed

  # // 8.3 //  -------------------------------------------------------------------------- EPOINTS DESKTOP - cookie panel
  # --------------------------------------------------------------------------------------- find out more button working
  @homePage @homePageAndNavigation
  Scenario: EPOINTS DESKTOP - cookie panel - find out more button working
    Given Epoints home page is opened clear
    When User click find out more button
    Then He will be redirected to cookie policy page