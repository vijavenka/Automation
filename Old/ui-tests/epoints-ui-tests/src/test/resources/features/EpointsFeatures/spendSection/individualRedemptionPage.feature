Feature: Epoints individual redemption page
  As an epoints user
  I want to have individual redemption page
  So that I could read more information about redemption I am interested and add it to basket

  # // 1.1 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------------------------------------------------------------------------------------- page content
  # Update -------------------------------- EPOINTS SPEND PAGE DESKTOP - add information and disclaimer banner(NBO-1052)
  @indRedemptionPage @spendSection
  Scenario: Individual redemption page - page content
    Given Browse rewards page is opened clear
    When User choose some reward and click on it
    Then Individual page of chosen redemption will be opened
    And It will contains picture, description and delivery information areas
    And It will contains basket module with proper elements
    And He can see top generic banner

  # // 1.2 //  ------------------------------------------------------------------------------ Individual redemption page
  # --------------------------------------------------------------------------------------------- back to rewards button
  @indRedemptionPage @spendSection
  Scenario: Individual redemption page - back to rewards button
    Given Browse rewards page is opened
    When User choose some reward and click on it
    Then Individual page of chosen redemption will be opened
    When User click 'back to rewards link'
    Then He will be redirected to browse rewards page

  # // 1.3 //  ------------------------------------------------------------------------------ Individual redemption page
  # --------------------------------------------------------------------------- basket module, more less product buttons
  @indRedemptionPage @spendSection
  Scenario: Individual redemption page - basket module, more less product buttons
    Given Browse rewards page is opened
    When User choose some reward and click on it
    Then Individual page of chosen redemption will be opened
    When User increase number of products in basket module
    Then Product counter will be increased
    When User decrease number of products in basket module
    Then Product counter will be decreased

  # // 1.4 //  ------------------------------------------------------------------------------ Individual redemption page
  # ---------------------------------------------------------------------------------------------- add product to basket
  @indRedemptionPage @spendSection
  Scenario: Individual redemption page - add product to basket
    Given Browse rewards page is opened
    When User choose some reward and click on it
    Then Individual page of chosen redemption will be opened
    When User add product to the basket
    Then Information about selected reward will be displayed
    When User add product to the basket
    Then Number of rewards on displayed information will increase
    When User Open basked preview
    Then Name and quantity will be correct according to added before products

  # // 1.5 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------------------------------------------------ availability of direct basket link after addition
  @indRedemptionPage @spendSection
  Scenario: Individual redemption page - availability of direct basket link after addition
    Given Browse rewards page is opened
    And User choose some reward and click on it
    And Individual page of chosen redemption will be opened
    When User add product to the basket
    Then Direct basket link will appears
    And It takes user to basket after click

