Feature: Epoints navigation
  As an epoints user
  I want to have navigation tool
  So that I could navigate to different epoints pages

  # // 1.1 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
  # --------------------------------------------------------------------------------------------------------- navigation
  @Regression @Navigation
  Scenario: NAVIGATION - changes to get area for desktop(NBO-349) -  navigation
    Given Points page is opened by not logged user
    And Additional subnavbar option will appear
    When User use vouchers link
    Then He will be redirected to vouchers page
    When User use play games link
    Then He will be redirected to play games page
    When User use instore link
    Then He will be redirected to in-store page
    When User use invite friend link
    Then He will be redirected to invite friend page

  # // 1.2 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
  # -------------------------------------------------------------- content of navbar on angular page for not logged user
  @Regression @Navigation
  Scenario: NAVIGATION - changes to get area for desktop(NBO-349) -  content of navbar on angular page for not logged user
    Given Points page is opened by not logged user
    Then He can see that navbar has proper content for not logged user

  # // 1.3 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
  # ------------------------------------------------------------------ content of navbar on angular page for logged user
  @Regression @Navigation
  Scenario: NAVIGATION - changes to get area for desktop(NBO-349) -  content of navbar on angular page for logged user
    Given Points page is opened by logged user
    Then He can see that navbar has proper content for logged user

  # // 2.1 //  ----------------- DESKTOP - EPOINTS - IP recognition or manual selection to set global site view(NBO-546)
  # ----------------------------------------------------------------------------------- zone picker content/close button
  @Regression @Navigation
  Scenario: NAVIGATION - zone picker - zone picker content/close button
    Given Epoints home page is opened by not logged user with cookie panel not visible
    When Zone picker panel will be opened
    Then User will see that zone picker panel has proper content
    When User click close zone picker button
    Then Zone picker panel will be closed

#  @Regression @Navigation
#  Scenario Outline: NAVIGATION - roulette option
#    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
#    When User use prizes main navigation button
#    Then He will be redirected to prizes page
#
#    Examples:
#      | userMainPartner | loginState |
#      | epoints         | logged     |
#      | epoints         | not logged |

#  @Regression @Navigation
#  Scenario: NAVIGATION - roulette option
#    When Epoints home page is opened by united logged user with cookie panel not visible
#    Then Prize header menu option will not be displayed