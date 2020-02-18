Feature: Voucher manager - edit screen

  As a administrator
  I need a page to go to
  Where I can see, search and edit all vouchers with specified status and affiliate network

   #1
  Scenario: Voucher Manager - search functionality for merchants (RD-3926)
    Given Admin portal is opened
    Given Voucher manager Tab is opened
    When User open voucher list view
    And Use search with merchant name which was assigned to some voucher
    Then Results will be displayed
    And Returned vouchers will have proper merchant name
