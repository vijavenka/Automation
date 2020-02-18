Feature: Epoints browse rewards page
  As an epoints user
  I want to have browse rewards page
  So that I could search, view and add to basket different redemptions

  # // 1.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # --------------------------------------------------- rewards page displaying products card with all required elements
  @Regression @SearchProductPage
  Scenario: Rewards page product card elements - validate all required card elements are displayed
    Given Rewards page is opened
    And Random department and category selected
    Then Redemption cards are available
    And Redemption cards include fields: category, image, title, epointsValue, originalEpointsValue (optional), add to basket button
    And Redemption cards are containing epoints links

  # // 1.2 //  -------------------------------------------------------------------------------------------- Rewards page
  # -------------------------------- products added to basked are properly marked and available in basket after addition
  @Regression @SearchProductPage
  Scenario: Rewards page product card elements - products added to basked are properly marked
    Given Rewards page is opened
    And Random department and category selected
    And Redemption cards are available
    When Product will be added to basket
    Then Product will be marked as 'Item added'
    And Products added to basket counter will be displayed
    And User can add same product again
    And Added product is available in basket preview

  # // 1.3 //  -------------------------------------------------------------------------------------------- Rewards page
  # ---------------------------------- selected filters: brand, type, epointsToPurchase are displayed in dedicated frame
  @Regression @SearchProductPage
  Scenario: Rewards page filters - selected filters: brand, type, epointsToPurchase are displayed in dedicated frame
    Given Rewards page is opened
    And Random department and category selected
    When Random brand, type, number of epoints selected
    Then 'Applied filters' contains all selected filters in selection order
    And Applied filters can be cleared without removing department and category selection

  # // 1.4 //  -------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------- keyword search is displayed in dedicated frame, user can remove single selected filter
  @Regression @SearchProductPage
  Scenario: Rewards page filters - keyword search is displayed in dedicated frame, user can remove single selected filter
    Given Rewards page is opened
    And Random department and category selected
    When User use search with some phrase
    Then 'Applied filters' contains provided keyword search
    And Single filter selection can be cleared using 'x' button
    And Department selection will be persisted

  # // 1.5 //  -------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------ selected filters behaviour when breadcrumb node clicked
  @Regression @SearchProductPage
  Scenario: Rewards page filters - selected filters behaviour when breadcrumb node clicked
    Given Rewards page is opened
    And Random department and category selected
    And Random brand, type, number of epoints selected
    When Breadcrumb category node will be clicked
    Then All 'Applied filters' will be persisted
    And 'Applied filters' contains all selected filters in selection order

  # // 1.6 //  -------------------------------------------------------------------------------------------- Rewards page
  # --------------------------------------------- rewards page displaying commercial banner which links to external page
  @Regression @SearchProductPage
  Scenario: Rewards page banner - rewards page displaying commercial banner which links to external page
    When Rewards page is opened
    Then Banner is available on rewards home page
    And Then external page will be opened after click on it

  # // 1.7 //  -------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------------------- rewards page displaying popular departments blocks
  @Regression @SearchProductPage
  Scenario: Rewards page popular departments - rewards page displaying popular departments blocks
    When Rewards page is opened
    Then Popular departments are available on rewards home page
    And Popular departments block contains image, department name, categories
    And User is redirected to department page after click on department name

  # // 1.8 //  -------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------- rewards page popular departments block have clickable category
  @Regression @SearchProductPage
  Scenario: Rewards page popular departments - rewards page popular departments block have clickable category
    Given Rewards page is opened
    When User selects category from popular department blocks
    Then He is redirected to category page

  # // 1.9 //  -------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------------ rewards page displaying popular categories blocks
  @Regression @SearchProductPage
  Scenario: Rewards page popular categories - rewards page displaying popular categories blocks
    When Rewards page is opened
    Then Popular categories are available on rewards home page
    And Popular category block contains image, category name
    And User is redirected to category page after click on category name

  # // 1.10 //  ------------------------------------------------------------------------------------------- Rewards page
  # ---------------------------------------------------------------------------- rewards page displaying promoted brands
  @Regression @SearchProductPage
  Scenario: Rewards page promoted brands - rewards page displaying promoted brands
    When Rewards page is opened
    Then Promoted brands are available on rewards home page
    And User is redirected to search page with selected brand after click on brand image

  # // 1.11 //  ------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------------------------------------------- rewards page product block
  @Regression @SearchProductPage
  Scenario: Rewards page product card elements - validate all required card elements are displayed
    Given Rewards page is opened
    Then Product block is available
    And Product block redemption cards include fields: category, image, title, epointsValue, originalEpointsValue (optional), add to basket button
    And See more link is available in product block
    And See more link redirects user to linked page when clicked

  # // 1.12 //  ------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------------------------------------------- rewards page product block
  # not sure if this is overkill as cards in many places have same test of adding to basket possibility
  @Regression @SearchProductPage
  Scenario: Rewards page product card  - products from product card added to basked are properly marked
    Given Rewards page is opened
    And Product block is available
    When Product from products block will be added to basket
    Then Product will be marked as 'Item added'
    And Products added to basket counter will be displayed
    And User can add same product again
    And Added product is available in basket preview

  # // 1.13 //  ------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------------------------- rewards page recently redeemed block
  @Regression @SearchProductPage
  Scenario: Rewards page recently redeemed block cards elements - validate all required card elements are displayed
    Given Rewards page is opened
    And Random department is selected
    Then Recently redeemed block is available
    And Recently redeemed block redemption cards include fields: category, image, title, epointsValue, originalEpointsValue (optional), add to basket button
    And Recently redeemed products are only from selected department

  # // 1.14 //  ------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------------------------- rewards page recently redeemed block
  # not sure if this is overkill as cards in many places have same test of adding to basket possibility
  @Regression @SearchProductPage
  Scenario: Rewards page recently redeemed block cards elements - products from recently redeemed added to basked are properly marked
    Given Rewards page is opened
    Then Recently redeemed block is available
    When Product from recently redeemed block will be added to basket
    Then Product will be marked as 'Item added'
    And Products added to basket counter will be displayed
    And User can add same product again
    And Added product is available in basket preview

  # // 1.15 //  ------------------------------------------------------------------------------------------- Rewards page
  # --------------------------------------------------------------------------------------- special offers product block
  @Regression @SearchProductPage
  Scenario: Rewards page special offers block - validate all required card elements are displayed
    Given Rewards page is opened
    Then Special offers block is available
    And Special offers block cards include fields: category, image, title, epointsValue, originalEpointsValue (optional), add to basket button
    And Special offers block cards are containing epoints links
    And Special offers additional link is redirecting user

  # // 1.16 //  ------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------------------------------------------- updated pagination section
  @Regression @SearchProductPage
  Scenario: Rewards page pagination section
    Given Rewards page's list is opened
    Then Pagination is available to use
    And It is possible to change the amount of displayed cards per page
    And It is possible to go to the next/previous page via arrow button
    And It is possible to go straight to the particular page via digits link
    And It is possible to sort products by POINTS_ASCENDING
    And It is possible to sort products by POINTS_DESCENDING


  # // 1.17 //  ------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------------------------------------------ updated search page
  @Regression @SearchProductPage
  Scenario Outline: Rewards page - updated search page
    Given Rewards page is opened
    When Keyword search is used with '<searchTerm>' for department '<department>'
    Then Each of returned product contains search term '<searchTerm>'
    And Number of returned products is displayed on breadcrumb
    And Each returned product has correct url according to search scope 'epoints'

    Examples:
      | searchTerm | department            |
      | Mysterion  | Toys, Games & Gadgets |
      | Vinyl      | Toys, Games & Gadgets |

  # // 1.18 //  ------------------------------------------------------------------------------------------- Rewards page
  # ---------------------------------------------------------------------------------------------------- updated filters
  @Regression @SearchProductPage
  Scenario Outline: Filtering - epoints scope
    Given Rewards page is opened
    When User selects a <departmentCategory> which has products filtered by <filter>
    Then Number of the department's products is correct
    And Number of products stays correct after using the filter <filter>

    Examples:
      | filter  | departmentCategory |
      | BRANDS  | department         |
      | AUTHORS | department         |
      | ACTORS  | department         |
      | ARTISTS | department         |
    #
      | BRANDS  | category           |
      | TYPES   | category           |
      | AUTHORS | category           |
      | ACTORS  | category           |
      | ARTISTS | category           |

  # // 1.19 //  ------------------------------------------------------------------------------------------- Rewards page
  # ---------------------------------------------------------------------------------------------------- updated filters
  @Regression @SearchProductPage
  @setHighEpointsValue
  Scenario Outline: Filtering by epoints - epoints scope
    Given Epoints home page is opened by epoints logged user with cookie panel not visible
    And Rewards page is opened
    When User selects a <epointsFilter> epoints option
    Then Number of products stays correct after using the epoints filter <epointsFilter>

    Examples:
      | epointsFilter     |
      | THOUSAND          |
      | FIVE_THOUSANDS    |
      | TWENTY_THOUSANDS  |
      | FIFTY_THOUSANDS   |
      | HUNDRED_THOUSANDS |
      | MAX               |

  # similar test can be added to united scope but it require config maintenance
  @Regression @SearchProductPage
  Scenario Outline: Rewards individual department page - page content for department without own products in solr
    Given Rewards page is opened
    When Department '<departmentWithoutOwnProducts>' is selected
    And Banner is available on rewards home page
    And Promoted brands are available on rewards home page
    And Special offers block is available
    And Category filter is not displayed
    And Epoints filter is not displayed
    And Selected department is displayed
    And Rewards info banner is displayed

    Examples:
      | departmentWithoutOwnProducts |
      | bupa                         |


#    #    UNITED BUSINESS TYPE#


  # // 1.1.1 //  ------------------------------------------------------------------------------------------ Rewards page
  # -------------------------------------------- UNITED rewards page displaying products card with all required elements
  @Regression @SearchProductPage @United
  Scenario: Rewards page product card elements - validate all required card elements are displayed - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Random department and category selected
    Then Redemption cards are available
    And Redemption cards include fields: category, image, title, epointsValue, originalEpointsValue (optional), add to basket button
    And Redemption cards are containing united links

  # // 1.2.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # -------------------------------- products added to basked are properly marked and available in basket after addition
  @Regression @SearchProductPage @United
  Scenario: Rewards page product card elements - products added to basked are properly marked - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Random department and category selected
    And Redemption cards are available
    When Product will be added to basket
    Then Product will be marked as 'Item added'
    And Products added to basket counter will be displayed
    And User can add same product again
    And Added product is available in basket preview

  # // 1.3.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # ---------------------------------- selected filters: brand, type, epointsToPurchase are displayed in dedicated frame
  @Regression @SearchProductPage @United
  Scenario: Rewards page filters - selected filters: brand, type, epointsToPurchase are displayed in dedicated frame - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Random department and category selected
    When Random brand, type, number of epoints selected
    Then 'Applied filters' contains all selected filters in selection order
    And Applied filters can be cleared without removing department and category selection

  # // 1.4.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------- keyword search is displayed in dedicated frame, user can remove single selected filter
  @Regression @SearchProductPage @United
  Scenario: Rewards page filters - keyword search is displayed in dedicated frame, user can remove single selected filter - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Random department and category selected
    When User use search with some phrase
    Then 'Applied filters' contains provided keyword search
    And Single filter selection can be cleared using 'x' button
    And Department selection will be persisted

  # // 1.5.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------ selected filters behaviour when breadcrumb node clicked
  @Regression @SearchProductPage @United
  Scenario: Rewards page filters - selected filters behaviour when breadcrumb node clicked - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Random department and category selected
    And Random brand, type, number of epoints selected
    When Breadcrumb category node will be clicked
    Then All 'Applied filters' will be persisted
    And 'Applied filters' contains all selected filters in selection order

  # // 1.6.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # --------------------------------------------- rewards page displaying commercial banner which links to external page
  @Regression @SearchProductPage @United
  Scenario: Rewards page banner - rewards page displaying commercial banner which links to external page - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    Then Banner is available on rewards home page
    And Then external page will be opened after click on it

  # // 1.7.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------------------- rewards page displaying popular departments blocks
  @Regression @SearchProductPage @United
  Scenario: Rewards page popular departments - rewards page displaying popular departments blocks - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    Then Popular departments are available on rewards home page
    And Popular departments block contains image, department name, categories
    And User is redirected to department page after click on department name

  # // 1.8.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------- rewards page popular departments block have clickable category
  @Regression @SearchProductPage @United
  Scenario: Rewards page popular departments - rewards page popular departments block have clickable category - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    When User selects category from popular department blocks
    Then He is redirected to category page

  # // 1.9.1 //  -------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------------ rewards page displaying popular categories blocks
  @Regression @SearchProductPage @United
  Scenario: Rewards page popular categories - rewards page displaying popular categories blocks - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    Then Popular categories are available on rewards home page
    And Popular category block contains image, category name
    And User is redirected to category page after click on category name

  # // 1.10.1 //  ------------------------------------------------------------------------------------------- Rewards page
  # ---------------------------------------------------------------------------- rewards page displaying promoted brands
  @Regression @SearchProductPage @United
  Scenario: Rewards page promoted brands - rewards page displaying promoted brands - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    Then Promoted brands are available on rewards home page
    And User is redirected to search page with selected brand after click on brand image

  # // 1.11.1 //  ---------------------------------------------------------------------------------- united Rewards page
  # ---------------------------------------------------------------------------------- united rewards page product block
  @Regression @SearchProductPage @United
  Scenario: United Rewards page product card elements - validate all required card elements are displayed - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    Then Product block is available
    And Product block redemption cards include fields: category, image, title, epointsValue, originalEpointsValue (optional), add to basket button
    And Product block redemption cards are containing united links
    And See more link is available in product block
    And See more link redirects user to linked page when clicked

  # // 1.12.1 //  ------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------------------------------------------- rewards page product block
  # not sure if this is overkill as cards in many places have same test of adding to basket possibility
  @Regression @SearchProductPage @United
  Scenario: Rewards page product card  - products from product card added to basked are properly marked - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Product block is available
    When Product from products block will be added to basket
    Then Product will be marked as 'Item added'
    And Products added to basket counter will be displayed
    And User can add same product again
    And Added product is available in basket preview

  # // 1.15.1 //  ----------------------------------------------------------------------------------------- Rewards page
  # -------------------------------------------------------------------------------- united special offers product block
  @Regression @SearchProductPage @United
  Scenario: United Rewards page special offers block - validate all required card elements are displayed - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    Then Special offers block is available
    And Special offers block cards include fields: category, image, title, epointsValue, originalEpointsValue (optional), add to basket button
    And Special offers block cards are containing united links
    And Special offers additional link is redirecting user

  # // 1.16.1 //  ------------------------------------------------------------------------------------------- Rewards page
  # ----------------------------------------------------------------------------------------- updated pagination section
  @Regression @SearchProductPage @United
  Scenario: Rewards page pagination section - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page's list is opened
    Then Pagination is available to use
    And It is possible to change the amount of displayed cards per page
    And It is possible to go to the next/previous page via arrow button
    And It is possible to go straight to the particular page via digits link
    And It is possible to sort products by POINTS_ASCENDING
    And It is possible to sort products by POINTS_DESCENDING

  # // 1.17.1 //  ------------------------------------------------------------------------------------------- Rewards page
  # ------------------------------------------------------------------------------------------------ updated search page
  @Regression @SearchProductPage @United
  Scenario Outline: Rewards page - updated search page - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    When Keyword search is used with '<searchTerm>' for department '<department>'
    Then Each of returned product contains search term '<searchTerm>'
    And Number of returned products is displayed on breadcrumb
    And Each returned product has correct url according to search scope 'united'

    Examples:
      | searchTerm | department            |
      | Mysterion  | Toys, Games & Gadgets |
      | Vinyl      | Toys, Games & Gadgets |


  # // 1.18.1 //  ------------------------------------------------------------------------------------ United Rewards page
  # --------------------------------------------------------------------------------------- updated filters UNITED scope
  @Regression @SearchProductPage @United
  Scenario Outline: Filtering - united scope - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Solar search is set to UNITED scope
    When User selects a <departmentCategory> which has products filtered by <filter>
    Then Number of the department's products is correct
    And Number of products stays correct after using the filter <filter>

    Examples:
      | filter  | departmentCategory |
      | BRANDS  | department         |
      | AUTHORS | department         |
      | ACTORS  | department         |
      | ARTISTS | department         |
    #
      | BRANDS  | category           |
      | TYPES   | category           |
      | AUTHORS | category           |
      | ACTORS  | category           |
      | ARTISTS | category           |

  # // 1.19.1 //  ------------------------------------------------------------------------------------ United Rewards page
  # --------------------------------------------------------------------------------------- updated filters UNITED scope
  @Regression @SearchProductPage @United
  @setHighEpointsValue
  Scenario Outline: Filtering by epoints - united scope - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And Rewards page is opened
    And Solar search is set to UNITED scope
    When User selects a <epointsFilter> epoints option
    Then Number of products stays correct after using the epoints filter <epointsFilter>

    Examples:
      | epointsFilter     |
      | THOUSAND          |
      | FIVE_THOUSANDS    |
      | TWENTY_THOUSANDS  |
      | FIFTY_THOUSANDS   |
      | HUNDRED_THOUSANDS |
      | MAX               |