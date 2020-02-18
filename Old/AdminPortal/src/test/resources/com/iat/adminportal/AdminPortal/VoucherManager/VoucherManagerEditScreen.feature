Feature: Voucher manager - edit screen

  As a administrator
  I need a page to go to
  Where I can edit existing vouchers and set new values to all fields describing specific voucher

   #1
  Scenario:Voucher Edit screens in the Admin Portal should show Merchant + Network (RD-3872).
    Given Admin portal is opened
    Given Voucher manager Tab is opened
    When User open voucher list view
    And Edit chosen voucher
    Then User will be able to select merchant name
    And He can see that next to merchant name is proper affiliate network name

   #2
  Scenario:EPOINTS - VOUCHER MAN. IMPR. - allow decimal point in £ off (NBO-17).
    Given Admin portal is opened
    Given Voucher manager Tab is opened
    When User open voucher list view
    And Edit chosen voucher
    When User enter unique voucher title
    And User enter decimal value in 'Pounds Off' field
    And User save voucher configuration
    Then No alert will be displayed
    And 'Pounds Off' decimal value will be properly saved