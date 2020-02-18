Feature: Navigation for Voucher Manager

  Background:
    Given Log in to admin portal as user 'i.superuser' with password 'theMightyBlues'
    And Admin portal home page is opened

  Scenario: Check if navigation link is properly added to admin portal menu
    When He checks admin portal navigation menu
    Then Link for 'Voucher Manager' is available

  Scenario: Check if navigation link properly opened Voucher Manager landing page
    And Link for 'Voucher Manager' is available
    When He clicks on 'Voucher Manager' link
    Then Voucher Manager List screen is opened