Feature: Epoints A-Z page
  As an epoints user
  I want to have A-Z page
  So that I could search for retailer I'm interested with

  # // 1.1 //  ------------------------------------------------------------------------------------------------ A-Z page
  # ------------------------------------------------------------------------------------------- check content of az page
  @shopOnlinePage @getSection
  Scenario: A-Z page - check content of az page
    Given A-Z page is opened
    When User looks at az page
    Then Search component is visible
    And Voucher link is available
    And Department facet is available
    And Letter facet is available
    And Bottom pagination is available
    And Retailer cards are available
    And Search box is available
    And Favourite module is available

  # // 1.2 //  ------------------------------------------------------------------------------------------------ A-Z page
  # -------------------------------------------------------------------------------------------- working of letter facet
  @shopOnlinePage @getSection
  Scenario: A-Z page - working of letter facet
    Given A-Z page is opened
    Then User can use letter filter
    And Results will match chosen letter

  # // 1.3 //  ------------------------------------------------------------------------------------------------ A-Z page
  # ------------------------------------------------------------------------------- working of clear button on az filter
  @shopOnlinePage @getSection
  Scenario: A-Z page - working of clear button on az filter
    Given A-Z page is opened
    When User select some letter from az filter
    And Click clear button on az filter
    Then Previous selection will be disabled and filter reset

  # // 1.4 //  ------------------------------------------------------------------------------------------------ A-Z page
  # -------------------------------------------------------------------------------- working of search allowed retailers
  @shopOnlinePage @getSection
  Scenario Outline: A-Z page - working of search allowed retailers
    Given A-Z page is opened
    When User enter '<phrase>' into search
    And Click Search button
    Then Proper results will be displayed according to typed '<phrase>'

    Examples:
        |phrase|
        #|john|
        |achica|
        #|fifty|

  # // 1.5 //  ------------------------------------------------------------------------------------------------ A-Z page
  # ---------------------------------------------------------------------------- working of search not allowed retailers
  @shopOnlinePage @getSection
  Scenario Outline: A-Z page - working of search not allowed retailers
    Given A-Z page is opened
    When User enter '<phrase>' into search
    And Click Search button
    Then Proper message about not found retailer '<phrase>' will be displayed

    Examples:
        |phrase|
        |noexistingretailer|

  # // 1.6 //  ------------------------------------------------------------------------------------------------ A-Z page
  # ---------------------------------------------------------------- bottom pagination, working of next/previous buttons
  @shopOnlinePage @getSection
  Scenario: A-Z page - bottom pagination, working of next/previous buttons
    Given A-Z page is opened
    When User click next page button
    Then Page will be changed to next
    And Showing module will be increased
    When User click previous page button
    And Showing module will be decreased
    Then Page will be changed to previous

  # // 1.7 //  ------------------------------------------------------------------------------------------------ A-Z page
  # ------------------------------------------------------------------ bottom pagination, working of page numbers button
  @shopOnlinePage @getSection
  Scenario: A-Z page - bottom pagination, working of page numbers button
    Given A-Z page is opened
    When User looks on bottom pagination component
    Then He can see tat proper number of page is displayed according to 'out of' information
    When User use some bottom pagination page number
    Then proper page will be displayed

  # // 1.8 //  ------------------------------------------------------------------------------------------------ A-Z page
  # ------------------------------------------------------------------- bottom pagination, working of back to top button
  #@shopOnlinePage
  #Scenario: A-Z page - bottom pagination, working of back to top button
    #Given A-Z page is opened
    #When User scroll page down
    #And User click 'back to top' button
    #Then Page will be scrolled to top

  # // 1.9 //  ------------------------------------------------------------------------------------------------ A-Z page
  # --------------------------------------------------------------------------------------------- top search box, arrows
  @shopOnlinePage @getSection
  Scenario: A-Z page - top search box, arrows
    Given A-Z page is opened
    When User click right arrow on top search box
    Then Page will be changed to next
    And Showing module will be increased
    When User click left arrow on top search box
    And Showing module will be decreased
    Then Page will be changed to previous

  # // 1.10 //  ----------------------------------------------------------------------------------------------- A-Z page
  # ------------------------------------------------------------------------------------- top search box, items per page
  @shopOnlinePage @getSection
  Scenario: A-Z page - top search box, items per page
    Given A-Z page is opened
    When User change 'item per page' parameter on top search box
    Then Number of elements on page will be changed according to chosen value

  # // 1.11 //  ----------------------------------------------------------------------------------------------- A-Z page
  # -------------------------------------------------------------------------------------------- top search box, filters
  @shopOnlinePage @getSection
  Scenario Outline: A-Z page - top search box, filters
    Given A-Z page is opened
    When User select some filter type '<filter>'
    Then Results will be displayed according to chosen filter '<filter>'

    Examples:
       |filter|
       |relevance|
       |nameasc|
       |namedesc|
       |epointsMultiplierasc|
       |epointsMultiplierdesc|

  # // 1.12 //  ----------------------------------------------------------------------------------------------- A-Z page
  # ----------------------------------------------------------- retailer cards user not logged, going to transition page
  @shopOnlinePage @getSection
  Scenario: A-Z page - retailer cards user not logged, going to transition page
    Given A-Z page is opened
    When User chose some retailer and click on its card
    Then He will be redirected to transition page
    And Information modal will be displayed

  # // 1.13 //  ----------------------------------------------------------------------------------------------- A-Z page
  # --------------------------------------------------------------------------- retailer cards user not logged, tooltips
  #@shopOnlinePage @getSection
  #Scenario: A-Z page - retailer cards user not logged, tooltips
    #Given A-Z page is opened
    #When User mouseover epoints retailer card icon
    #Then Tooltip with proper epoints multiplier information will be displayed
    #When User mouseover favourites retailer card icon
    #Then Tooltip with proper information about login needed will be displayed
    #When User mouseover voucher retailer card icon
    #Then Tooltip with proper information about available vouchers will be displayed

  # // 1.14 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # ----------------------------------------------------------------------- retailer cards, user not logged, button name
  #@shopOnlinePage @getSection
  #Scenario: A-Z page - retailer cards, user not logged, button with name
    #Given A-Z page is opened
    #When User hover epoints retailer card with mouse pointer
    #Then Card is greyed out
    #And Button with name of retailer is shown

  # // 1.15 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # ------------------------------------------------------------------------------ retailer cards, user logged, tooltips
  #@shopOnlinePage @getSection
  #Scenario: A-Z page - retailer cards, user logged, tooltips
    #Given Epoints page is opened and user is logged
    #When User mouseover epoints retailer card icon
    #Then Tooltip with proper epoints multiplier information will be displayed for logged user
    #When User mouseover favourites retailer card icon
    #Then Tooltip with information about adding to favourites is shown for logged user
    #When User mouseover voucher retailer card icon
    #Then Tooltip with proper information about available vouchers will be displayed for logged user

  # // 1.16 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # --------------------------------------------------------------------- user not logged and click on favourites button
  @shopOnlinePage @getSection
  Scenario: A-Z page - user not logged and click on favourites button
    Given A-Z page is opened
    When User click add to favourites button on chosen retailer card
    Then Login Modal window will be displayed

  # // 1.17 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # --------------------------------------------------------------------- retailer cards, user logged, adding favourites
  @userInterestsTableRowsAreRemoved @shopOnlinePage @getSection
  Scenario: A-Z page - retailer cards, user logged, adding favourites
    Given Epoints page is opened and user is logged in clear
    Given A-Z page is opened
    When User clicks on favourites retailer card icon of chosen retailer
    Then Heart icon is marked as gold
    And Retailer is added to the favourite retailers

  # // 1.18 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # -------------------------------------------------------------------- retailer cards user logged, removing favourites
  @shopOnlinePage @getSection
  Scenario: A-Z page - retailer cards, user logged, removing favourites
    Given Epoints page is opened and user is logged in clear
    Given A-Z page is opened
    When User go to favourite retailers section
    And Remove from the favourite last retailer
    Then Retailer will be removed and favourite section disabled

  # // 1.19 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # -------------------------------------------------------------- retailer cards, user logged, going to transition page
  @shopOnlinePage @getSection
  Scenario: A-Z page - retailer cards, user logged, going to transition page
    Given Epoints page is opened and user is logged in clear
    Given A-Z page is opened
    When User chose retailer and click on its card
    Then He will be redirected to transition page
    And Information modal will be displayed

  # // 1.20 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # --------------------------------------------------------------------------------------- working of department filter
  @shopOnlinePage @getSection
  Scenario: A-Z page - working of department filter
    Given A-Z page is opened
    When User click chosen department on department filter
    Then Retailers fill be properly filtered
    And Chosen Department will be set as active on department facet

  # // 1.21 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # ----------------------------------------------------------------------- working of clear button on department filter
  @shopOnlinePage @getSection
  Scenario: A-Z page - working of clear button on department filter
    Given A-Z page is opened
    When User click chosen department on department filter
    And User click clear button on department filter
    Then None of department option will be set as active
    And Retailers cards will be displayed in initial order

  # // 1.22 //  ------------------------------------------------------------- WLS Removal - A-Z page new logic (RD-4099)
  # ------------------------------------------------------------------------ recently visited retailer component working
  @shopOnlinePage @getSection
  Scenario: A-Z page - recently visited retailer component working
    Given Epoints page is opened and user is logged in clear
    Given A-Z page is opened
    When User go to chosen retailer page using retailer card
    And Come back to az page
    Then He will se that recently visited retailer component was updated about previous chosen retailer
    And He will be able to use links from recently visited retailers component

  # // 2.1 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build A-Z page (NBO-541)
  # ------------------------------------------------------------------------------ recently visited and retailers / zone
  @shopOnlinePage @getSection
  Scenario Outline: MERCHANT MANAGER - add the zone value to the API calls that build A-Z page (NBO-541) - recently visited and retailers / zone
    Given Epoints page is opened and user is logged in clear
    Given Proper zone is set '<zone>'
    Given A-Z page is opened
    When User check recently visited retailers and retailer cards
    Then He will see that all retailers are properly returned according to chosen zone '<zone>'

    Examples:
      |zone|
      |UK|
      #|IE|

  # // 2.2 //  -------------------- MERCHANT MANAGER - add the zone value to the API calls that build A-Z page (NBO-541)
  # ----------------------------------------------------------------- recently visited and retailers after search / zone
  @shopOnlinePage @getSection
  Scenario Outline: MERCHANT MANAGER - add the zone value to the API calls that build A-Z page (NBO-541) - recently visited and retailers after search / zone
    Given Epoints page is opened and user is logged in clear
    Given Proper zone is set '<zone>'
    Given A-Z page is opened
    When User enter '<phrase>' into search
    And Click Search button
    And User check recently visited retailers and retailer cards
    Then He will see that all retailers are properly returned according to chosen zone '<zone>'

  Examples:
    |zone|phrase|
    |UK|Mark|
    #|IE|Mark|