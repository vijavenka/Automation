Feature: Epoints home page
  As an epoints user
  I want to have home page
  So that I could get basic information about epoints, login, etc.

  @Regression @HomePage
  Scenario Outline: Home page content
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    And Merchants section is available with correct heading and description
    And Lead merchants section is available with correct heading and description
    And Redemption offers block banner is displayed
    And Redemption offers block is available with 5 products in it
    And How does it work block is available

    Examples:
      | userMainPartner | loginState |
      | epoints         | logged     |
      | epoints         | not logged |

  @Regression @HomePage
  Scenario: Home page banner
    Given Epoints home page is opened by epoints not logged user with cookie panel not visible
    And Home page's banner is available with correct carousel elements
    And Banner's carousel can be navigated via arrows
    And Banner's carousel can be navigated via dots

  @homePage @homePageAndNavigation
  Scenario Outline: Merchants block, merchant link
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User clicks on chosen merchant card
    Then He will be redirected to individual retailer page or transition page according to login state '<loginState>'

    Examples:
      | userMainPartner | loginState |
      | epoints         | logged     |
      | epoints         | not logged |

  @Regression @HomePage
  Scenario: Merchants block, more retailer link
    Given Epoints home page is opened by epoints not logged user with cookie panel not visible
    When User clicks on "1 500 more retailers" link
    Then He will be redirected to /points/online page

  @Regression @HomePage
  Scenario Outline: Lead merchants block, merchant link
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User click on chosen lead merchant card
    Then He will be redirected to lead merchant page regardless of login state

    Examples:
      | userMainPartner | loginState |
      | epoints         | logged     |
      | epoints         | not logged |

  @Regression @HomePage
  Scenario: Redemption block content
    Given Epoints home page is opened by epoints not logged user with cookie panel not visible
    Then Redemption offers block is available with 5 products in it
    And Redemption offers block cards include fields: category, image, title, epointsValue, originalEpointsValue(optional), add to basket button
    And Redemption offers "More rewards" link is redirecting user

  @Regression @HomePage
  Scenario: Redemption block, redemption card clicked
    Given Epoints home page is opened by epoints not logged user with cookie panel not visible
    And  Redemption offers block is available with 5 products in it
    When User clicks on chosen redemption card
    Then He will be redirected to single redemption page of selected redemption

    #TODO adding to basket will not be added as separate test here because we have many similar on rewards page -
    #TODO - as well as working of category link on redemption card

  @Regression @HomePage
  Scenario: Cookie panel - cookie panel content
    Given Epoints home page is opened by epoints not logged user with cookie panel visible
    When User looks on top of page
    Then He will see cookie panel with proper content

  @Regression @HomePage
  Scenario: Cookie panel - accept button working
    Given Epoints home page is opened by epoints not logged user with cookie panel visible
    When User click accept button on cookie panel
    Then Cookie panel will be closed

  @Regression @HomePage
  Scenario: Cookie panel - find out more button working
    Given Epoints home page is opened by epoints not logged user with cookie panel visible
    When User click find out more button
    Then He will be redirected to cookie policy page

  @Regression @HomePage
  Scenario Outline: RETAILER BLOCK - merchant redirection when user is logged in and merchant has (The Hut) or has not (House of Fraser) description
    When Epoints home page is opened by epoints logged user with cookie panel not visible
    Then <merchantName> merchant will be redirected to transition

    Examples:
      | merchantName    |
      | The Hut         |
      | House of Fraser |

  @Regression @HomePage
  Scenario: BUSINESS ACCOUNT UNITED's home page must be default page if UNITED user is logged
    When Epoints home page is opened by united logged user with cookie panel not visible
    Then United home page is opened
    Then United header options are displayed
    And Epoints logo is displayed

  @Regression @HomePage
  Scenario: BUSINESS ACCOUNT UNITED's - switching to epoints
    When Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to epoints account
    Then epoints home page is opened
    And Epoints header options are displayed
    And Epoints logo is displayed

  @Regression @HomePage
  Scenario: BUSINESS ACCOUNT UNITED's - switching between epoints/united multiple times
    When Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to epoints account
    Then epoints home page is opened
    And Epoints header options are displayed
    And Epoints logo is displayed
    When User switch to united account
    Then United home page is opened
    And United header options are displayed
    And Epoints logo is displayed
    When User switch to epoints account
    Then epoints home page is opened
    And Epoints header options are displayed
    And Epoints logo is displayed
    When User switch to united account
    Then United home page is opened
    And United header options are displayed
    And Epoits logo is displayed