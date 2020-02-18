Feature: Epoints spend landing page - recently viewed products
  As an epoints user
  I want to have recently viewed products block on spend page
  So that I could use see lately viewed products from the newest one on landing page

  #-----------------------------------------------------------------
  # JIRA Story: NBO-3452
  # Introduction of new product block called "Recently viewed".
  # It has to present last 3 products viewed by the user. Products
  # are stored in local storage for both logged user and not logged
  # user. After logout block is still presented. Change of the
  # country is causing switch in local storage, not losing info.
  #
  #-----------------------------------------------------------------

  Scenario: Recently viewed products - empty storage
    Given User enters epoints.com page
    And He has empty local storage
    When He goes to Spend Landing Page
    Then He shouldn't see Recently viewed products block

  Scenario Outline: Recently viewed products - user saw less than 3 products
    Given User is on epoints.com spend page
    And He has empty local storage
    When He goes to see <Products> product(s) card
    And He returns to spend page
    Then Recently viewed products block is hidden
    And Local storage keep information about viewed products

    Examples:
      | Products |
      | 1        |
      | 2        |

  Scenario: Recently viewed products - user saw 3 products
    Given User is on epoints.com spend page
    And He has empty local storage
    When He goes to see 3 different product cards
    And He returns to spend page
    Then Recently viewed products block should appear
    And Products should be presented from the newest saw
    And Local storage should keep information about viewed products

  Scenario: Recently viewed products - user viewed more than 3 products
    Given User is browsing products on epoints.com spend page
    And His local storage contains more than 3 products information
    When He goes to see another product
    And He returns to spend page
    Then Recently viewed products block should be present
    And Products should be presented from the latest one

  Scenario: Recently viewed products - user is login out
    Given User is logged on epoints.com
    And User is browsing products on epoints.com spend page
    When He login out from the system
    Then Local storage should keep information about viewed products
    And Recently viewed products block should be present

  Scenario Outline: Recently viewed product - different countries
    Given User is on <country> page of epoints.com
    And He goes to see 3 different products cards
    When He switch to <country> for the first time
    Then He shouldn't see Recently viewed products block
    And Local storage should be empty

    Examples:
      | country |
      | UK      |
      | SE      |
      | NL      |

  Scenario Outline: Recently viewed product - switching between countries
    Given User is on <country> page of epoints.com
    And He goes to see 3 different products cards
    When He switch to <country>
    And Goes back to previous one
    Then He should see Recently viewed products block
    And Local storage should have products for initial <country_in>

    Examples:
      | country_in | country |
      | UK         | SE      |
      | SE         | NL      |
      | NL         | UK      |