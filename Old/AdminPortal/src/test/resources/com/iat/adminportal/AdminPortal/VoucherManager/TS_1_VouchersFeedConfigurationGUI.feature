Feature: Voucher Manager GUI


  Background:
    Given Log in to admin portal as user 'i.superuser' with password 'theMightyBlues'
    And Admin portal home page is opened
    And Link for 'Voucher Manager' is available
    And He clicks on 'Voucher Manager' link

  Scenario: Open Feed Configuration screen
    Given Voucher Manager List screen is opened
    When He clicks on 'Add New Feed Configuration' button
    Then Voucher manager 'Create new' form is opened

  Scenario: Check all fields on create form
    Given He clicks on 'Add New Feed Configuration' button
    When Voucher manager 'Create new' form is opened
    Then Labels of create new form are visible
    And Name field is available
    And Short Name field is available
    And Location URL field is available
    And Affiliate Network drop-down is available with values from Network Manager
    And Product Property field is available
    And Content Type drop-down is available with values
      | - select -      |
      | CSV_WITH_HEADER |
      | XML             |
      | JSON            |
      | TAB_DELIMITED   |
    And Pull Method drop-down is available with values
      | - select -                     |
      | HTTP_REST_LIKE                 |
      | HTTP_BASIC_AUTH_REST_LIKE      |
      | FTP                            |
      | HTTP_EXTRA_HEADERS_REST_LIKE   |
      | HTTP_REST_LIKE_WITH_LOGIN_FORM |
      | AMAZON_SIGNED_HTTP_REST_LIKE   |
    And Cron timing field is available
    And Pre-processing Stages are available with values
      | - select -                |
      | GZIP_REMOVAL              |
      | XML_PROCESS_INSTR_REMOVAL |