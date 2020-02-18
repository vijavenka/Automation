Feature: Navigation for Voucher Manager

  Background:
    Given Log in to admin portal as user 'i.superuser' with password 'theMightyBlues'
    And Admin portal home page is opened


  Scenario: Check if navigation link for Redemption Manager is properly added to admin portal menu
    When He checks admin portal navigation menu
    Then Link for 'Redemption Manager' is available

  Scenario: Check if navigation link properly opened Redemption Items Manager landing page
    And Link for 'Redemption Manager' is available
    When He clicks on 'Redemption Manager' link
    Then Bucket List screen is opened properly