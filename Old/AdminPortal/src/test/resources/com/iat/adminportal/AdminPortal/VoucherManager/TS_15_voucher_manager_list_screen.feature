Feature: Voucher Manager List Screen


  Background:
    Given Log in to admin portal as user 'i.superuser' with password 'theMightyBlues'
    And Admin portal home page is opened

  Scenario: Check if Voucher manager List screen have all required fields
    Given Link for 'Voucher Manager' is available
    When He clicks on 'Voucher Manager' link
    Then Raw Voucher statistics section should be available
    And Raw product statistics section should include NEW vouchers count and link to search screen
    And Raw product statistics section should include UPDATED vouchers count and link to search screen
    And Raw product statistics section should include DELETED vouchers count and link to search screen
    And Updated vouchers statistics section should be available
    And Updated Vouchers statistics section should include EDITED vouchers count and link to search screen
    And Updated Vouchers statistics section should include PARTIALLY EDITED vouchers count and link to search screen
    And Table with Voucher Feed Configuration for each network should be available
    And Voucher Feed Configuration table columns should have proper values
      | Network |
      | Status  |
      | Created |
      | Updated |
      | Total   |
      | Deleted |
      | Edited  |
      | Promote |
      | Active  |
      | Edit    |
    And Counters in Status, New, Updated, Total, Deleted, Edited columns should be links into Vouchers search screen
    And Promote Voucher Feed Configuration button should be available
    And Deactivate/Activate Voucher Feed Configuration button should be available
    And Promote all Voucher Feed Configurations button should be available
    And Add new Voucher Feed Configuration button should be available
