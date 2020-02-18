Feature: Raw vouchers store - statistics links


  Background:
    Given Log in to admin portal as user 'i.superuser' with password 'theMightyBlues'
    And Admin portal home page is opened
    And Link for 'Voucher Manager' is available
    And He clicks on 'Voucher Manager' link


  Scenario: Check if statistics links for CREATED vouchers working properly

    Given Voucher Manager List screen is opened
    When Click in CREATED links in Raw store statistics section
    Then Voucher manager Search interface should be opened

  Scenario: Check if table link CREATED vouchers working properly for specific network

    Given Voucher Manager List screen is opened
    When Click in CREATED links in table column for network
    Then Voucher manager Search interface should be opened


  Scenario: Check if table link CREATED vouchers working properly for summary

    Given Voucher Manager List screen is opened
    When Click in CREATED links in table column for summary
    Then Voucher manager Search interface should be opened