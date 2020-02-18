Feature: Epoints points page
  As an epoints user
  I want to have Points page
  So that I could search for retailer I'm interested with

  # // 1.1 //  --------------------------------------------------------------------------------------------- Points page
  # --------------------------------------------------------------------------------------- check content of points page
  @Regression @ShopOnlinePage
  Scenario: Points page - check content of points page
    Given Points page is opened by not logged user
    When User looks at az page
    Then Search component is visible
#    And Roulette banner is visible
    And Department facet is available
    And Letter facet is available
    And Bottom pagination is available
    And Retailer cards are available
    And Top pagination component is available
    And Favourite module is available
    And Points page breadcrumb is available
    And Specials promotion block is available with all required elements(title; merchant cards; check more leads link)

  # // 1.2 //  --------------------------------------------------------------------------------------------- Points page
  # -------------------------------------------------------------------------------------------- working of letter facet
  @Regression @ShopOnlinePage
  Scenario: Points page - working of letter facet
    Given Points page is opened by not logged user
    Then User can use letter filter
    And Results will match chosen letter
    And Breadcrumb last level show chosen letter

  # // 1.3 //  --------------------------------------------------------------------------------------------- Points page
  # ------------------------------------------------------------------------------- working of clear button on az filter
  @Regression @ShopOnlinePage
  Scenario: Points page - working of clear button on az filter
    Given Points page is opened by not logged user
    When User select some letter from az filter
    And Click clear button on az filter
    Then Previous selection will be disabled and filter reset
    And Breadcrumb last level is set to points

  # // 1.4 //  --------------------------------------------------------------------------------------------- Points page
  # -------------------------------------------------------------------------------- working of search allowed retailers
  @Regression @ShopOnlinePage
  Scenario Outline: Points page - working of search allowed retailers
    Given Points page is opened by not logged user
    When User enter '<phrase>' into search
    And Click Search button
    Then Proper results will be displayed according to typed '<phrase>'

    Examples:
      | phrase     |
      | john lewis |
      | fifty      |

  # // 1.5 //  --------------------------------------------------------------------------------------------- Points page
  # ---------------------------------------------------------------------------- working of search not allowed retailers
  @Regression @ShopOnlinePage
  Scenario Outline: Points page - working of search not allowed retailers
    Given Points page is opened by not logged user
    When User enter '<phrase>' into search
    And Click Search button
    Then Proper message about not found retailer '<phrase>' will be displayed

    Examples:
      | phrase              |
      | notexistingretailer |

  # // 1.6 //  --------------------------------------------------------------------------------------------- Points page
  # ---------------------------------------------------------------- bottom pagination, working of next/previous buttons
  @Regression @ShopOnlinePage
  Scenario: Points page - bottom pagination, working of next/previous buttons
    Given Points page is opened by not logged user
    When User click next page button
    Then Page will be changed to next
    And Showing module will be increased
    When User click previous page button
    And Showing module will be decreased
    Then Page will be changed to previous

  # // 1.7 //  --------------------------------------------------------------------------------------------- Points page
  # ------------------------------------------------------------------ bottom pagination, working of page numbers button
  @Regression @ShopOnlinePage
  Scenario: Points page - bottom pagination, working of page numbers button
    Given Points page is opened by not logged user
    When User looks on bottom pagination component
    Then He can see tat proper number of page is displayed according to 'out of' information
    When User use some bottom pagination page number
    Then proper page will be displayed

  # // 1.8 //  --------------------------------------------------------------------------------------------- Points page
  # ------------------------------------------------------------------- bottom pagination, working of back to top button
  @Regression @ShopOnlinePage
  Scenario: Points page - bottom pagination, working of back to top button
    Given Points page is opened by not logged user
    When User scroll page down
    And User click 'back to top' button
    Then Page will be scrolled to top

  # // 1.9 //  --------------------------------------------------------------------------------------------- Points page
  # --------------------------------------------------------------------------------------------- top search box, arrows
  @Regression @ShopOnlinePage
  Scenario: Points page - top search box, arrows
    Given Points page is opened by not logged user
    When User click right arrow on top search box
    Then Page will be changed to next
    And Showing module will be increased
    When User click left arrow on top search box
    And Showing module will be decreased
    Then Page will be changed to previous

  # // 1.10 //  -------------------------------------------------------------------------------------------- Points page
  # ------------------------------------------------------------------------------------- top search box, items per page
  @Regression @ShopOnlinePage
  Scenario: Points page - top search box, items per page
    Given Points page is opened by not logged user
    When User change 'item per page' parameter on top search box
    Then Number of elements on page will be changed according to chosen value

  # // 1.11 //  -------------------------------------------------------------------------------------------- Points page
  # -------------------------------------------------------------------------------------------- top search box, filters
  @Regression @ShopOnlinePage
  Scenario Outline: Points page - top search box, filters
    Given Points page is opened by not logged user
    When User select some filter type '<filter>'
    Then Results will be displayed according to chosen filter '<filter>'

    Examples:
      | filter                |
      | relevance             |
      | nameasc               |
      | namedesc              |
      | epointsMultiplierasc  |
      | epointsMultiplierdesc |

  # // 1.12 //  -------------------------------------------------------------------------------------------- Points page
  # --------------------------------------------------------------- retailer cards user logged, going to transition page
  @Regression @ShopOnlinePage
  Scenario: Points page - retailer cards user logged, going to transition page
    Given Points page is opened by logged user
    When User chose some retailer and click on its card
    Then He will be redirected to transition page

  #// 1.13 //  -------------------------------------------------------------------------------------------- Points page
  # --------------------------------------------------------------------------- retailer cards user not logged, tooltips
  @Regression @ShopOnlinePage
  Scenario: Points page - retailer cards user not logged, tooltips
    Given Points page is opened by not logged user
    When User mouseover epoints retailer card icon
    Then Tooltip with proper epoints multiplier information will be displayed
    When User mouseover favourites retailer card icon
    Then Tooltip with proper information about login needed will be displayed
    When User mouseover voucher retailer card icon
    Then Tooltip with proper information about available vouchers will be displayed

  # // 1.14 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  #----------------------------------------------------------------------- retailer cards, user not logged, button name
  @Regression @ShopOnlinePage
  Scenario: Points page - retailer cards, user not logged, button with name
    Given Points page is opened by not logged user
    When User hover epoints retailer card with mouse pointer
    Then Card is greyed out
    And Button with name of retailer is shown

  # // 1.15 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  # ------------------------------------------------------------------------------ retailer cards, user logged, tooltips
  @Regression @ShopOnlinePage
  Scenario: Points page - retailer cards, user logged, tooltips
    Given Points page is opened by logged user
    When User mouseover epoints retailer card icon
    Then Tooltip with proper epoints multiplier information will be displayed for logged user
    When User mouseover favourites retailer card icon
    Then Tooltip with information about adding to favourites is shown for logged user
    When User mouseover voucher retailer card icon
    Then Tooltip with proper information about available vouchers will be displayed for logged user

  # // 1.16 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  # --------------------------------------------------------------------- user not logged and click on favourites button
  @Regression @ShopOnlinePage
  Scenario: Points page - user not logged and click on favourites button
    Given Points page is opened by not logged user
    When User click add to favourites button on chosen retailer card
    Then Login Modal window will be displayed

  # // 1.17 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  # --------------------------------------------------------------------- retailer cards, user logged, adding favourites
  @Regression @ShopOnlinePage
  Scenario: Points page - retailer cards, user logged, adding favourites
    Given Points page is opened by logged user
    When User clicks on favourites retailer card icon of chosen retailer
    Then Heart icon is marked as gold
    And Retailer is added to the favourite retailers

  # // 1.18 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  # -------------------------------------------------------------------- retailer cards user logged, removing favourites
  @Regression @ShopOnlinePage
  Scenario: Points page - retailer cards, user logged, removing favourites
    Given Points page is opened by logged user
    When User go to favourite retailers section
    And Remove from the favourite last retailer
    Then Retailer will be removed and favourite section disabled

  # // 1.19 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  # --------------------------------------------------------------------------------------- working of department filter
  @Regression @ShopOnlinePage
  Scenario: Points page - working of department filter
    Given Points page is opened by not logged user
    When User click chosen department on department filter
    Then Retailers fill be properly filtered
    And Chosen Department will be set as active on department facet
    And Breadcrumb last level show chosen department
#    And Seo department heading is displayed
#    And Seo department copy is displayed

  # // 1.20 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  # ----------------------------------------------------------------------- working of clear button on department filter
  @Regression @ShopOnlinePage
  Scenario: Points page - working of clear button on department filter
    Given Points page is opened by not logged user
    When User click chosen department on department filter
    And User click clear button on department filter
    Then None of department option will be set as active
    And Retailers cards will be displayed in initial order
    And Breadcrumb last level is set to points

  # // 1.22 //  ---------------------------------------------------------- WLS Removal - Points page new logic (RD-4099)
  # ------------------------------------------------------------------------ recently visited retailer component working
  @Regression @ShopOnlinePage
  Scenario: Points page - recently visited retailer component working
    Given Points page is opened by logged user
    When User go to chosen retailer page using retailer card
    And Come back to points page
    Then He will se that recently visited retailer component was updated about previous chosen retailer
    And He will be able to use links from recently visited retailers component

#  # // 2.1 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build Points page (NBO-541)
#  # ------------------------------------------------------------------------------ recently visited and retailers / zone
#  @Regression @ShopOnlinePage
#  Scenario Outline: MERCHANT MANAGER - add the zone value to the API calls that build Points page (NBO-541) - recently visited and retailers / zone
#    Given Points page is opened by logged user
#    Given Proper zone is set '<zone>'
#    When User check recently visited retailers and retailer cards
#    Then He will see that all retailers are properly returned according to chosen zone '<zone>'
#
#    Examples:
#      | zone |
#      | UK   |
#      #|IE|

#  # // 2.2 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build Points page (NBO-541)
#  # ----------------------------------------------------------------- recently visited and retailers after search / zone
#  @Regression @ShopOnlinePage
#  Scenario Outline: MERCHANT MANAGER - add the zone value to the API calls that build Points page (NBO-541) - recently visited and retailers after search / zone
#    Given Registered user open ePoints.com
#    Given Proper zone is set '<zone>'
#    Given Points page is opened
#    When User enter '<phrase>' into search
#    And Click Search button
#    And User check recently visited retailers and retailer cards
#    Then He will see that all retailers are properly returned according to chosen zone '<zone>'
#
#    Examples:
#      | zone | phrase |
#      | UK   | Mark   |
#    #|IE|Mark|

  @Regression @ShopOnlinePage
  Scenario: Points(leads project) - new "leads" department on points/online
    Given Points page is opened by not logged user
    When "Specials" department is selected from departments list
    Then Only "Lead generator" merchants are displayed
    And Stores A-Z filter is narrowed to show only "Lead generator" merchants when used

  @Regression @ShopOnlinePage
  Scenario: Points(leads project) - new "leads" merchant cards design
    Given Points page is opened by not logged user
    When "Specials" department is selected from departments list
    Then "Lead generator" merchants are displayed with different styling than original merchant card
    And "Lead generator" merchants cards contains additional "SPECIALS" badge at top of card
    And "Lead generator" merchants cards contains information about maximum commission

  @Regression @ShopOnlinePage
  Scenario Outline: Points(leads project) - new "leads" merchant cards links
    Given Points page is opened by <userState> user
    And "Specials" department is selected from departments list
    When User click on chosen "Lead generator" merchant card
    Then User is redirected to "Lead generator" merchant page

    Examples:
      | userState  |
      | not logged |
      | logged     |

  @Regression @ShopOnlinePage
  Scenario: Points(leads project) - promote lead gen merchants block, check button clicked
    Given Points page is opened by not logged user
    When "Check" button of chosen merchant from specials promoted block will be clicked
    Then User is redirected to selected "Lead generator" merchant page

  @Regression @ShopOnlinePage
  Scenario: Points(leads project) - promote lead gen merchants block, check more leads button clicked
    Given Points page is opened by not logged user
    When "Check more leads" button from specials promoted block will be clicked
    Then Special department will be selected
    And Only "Lead generator" merchants are displayed
    And Stores A-Z filter is narrowed to show only "Lead generator" merchants when used

#  @Regression @ShopOnlinePage
#  Scenario: Points page - roulette banner link
#    Given Points page is opened by not logged user
#    When User click prizes banner on points page
#    Then He will be redirected to prizes page