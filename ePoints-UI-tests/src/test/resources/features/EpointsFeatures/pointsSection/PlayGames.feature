Feature: Epoints play games page
  As an epoints user
  I want to have play games page
  So that I could earn more epoints by playing different games

  # // 1.1 //  ------------------------------------------- GAME PAGE - create partner page for GAME for desktop(NBO-346)
  # // 1.5 //  -------------------------------------------------GAME PAGE - add adverts to game page on DESKTOP(NBO-817)
  # ----------------------------------------------------------------------------------------- page content - user logged
  @Regression @PlayGamesPage
  Scenario: GAME PAGE - create partner page for GAME for desktop(NBO-346) -page content - user logged
    Given Play games page is opened by logged user
    When User look on play games page
    Then He will see clumsy bird game window
    And Clumsy bird image description will be displayed
    And Commercials will be displayed
    And Other elements like points counter will be displayed

  # // 1.2 // GAME PAGE - show message and UI for users who access game page and are not identified for DESKTOP(NBO-655)
  # // 1.5 //  -------------------------------------------------GAME PAGE - add adverts to game page on DESKTOP(NBO-817)
  # ------------------------------------------------------------------------------------- page content - user not logged
  @Regression @PlayGamesPage
  Scenario: GAME PAGE - show message and UI for users who access game page and are not identified for DESKTOP(NBO-655) - page content - user not logged
    Given Play games page is opened by not logged user
    When User look on play games page
    Then He will see clumsy bird game window
    And Clumsy bird image description will be displayed
    And Commercials will be displayed
    And Other elements like page header will be displayed
    And Sign into epoints button will be displayed

  # // 1.3 // GAME PAGE - show message and UI for users who access game page and are not identified for DESKTOP(NBO-655)
  # ----------------------------------------------------------------------------------------------------- sign in button
  @Regression @PlayGamesPage
  Scenario: GAME PAGE - show message and UI for users who access game page and are not identified for DESKTOP(NBO-655) - sign in button
    Given Play games page is opened by not logged user
    When User click sign in button on game page
    Then Login modal with all needed elements will be displayed over play games page

  # // 1.4 //  ---------------- GAME PAGE - integrate the Clumsy Bird game into the DESKTOP game page interface(NBO-665)
  # ------------------------------------------------------------------------------------------ game window, close button
  @Regression @PlayGamesPage
  Scenario: GAME PAGE - integrate the Clumsy Bird game into the DESKTOP game page interface(NBO-665) - game window, close button
    Given Play games page is opened by not logged user
    When User click play clumsy bird button
    Then Modal window with clumsy bird game will be opened
    When User click close clumsy bird game button
    Then Modal window with clumsy bird game will be closed