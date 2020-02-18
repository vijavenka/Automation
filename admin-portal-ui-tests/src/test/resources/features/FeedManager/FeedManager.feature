Feature: Login into admin portal
  As an administrator
  I want to have login functionality
  So that I will be able to authorize in the system and use admin portal Feed Manager


  Scenario Outline: Check feed management options availability
    Given Admin Portal user is logged in
    And He is on Feed Manager page
    When He select row with feed
    Then '<Button>' have to be available

    Examples:
      | Button              | note           |
      | RUN                 |                |
      | ACTIVATE/DEACTIVATE |                |
      | DELETE              | hard delete    |
      | REMOVE & RELOAD     | do we need it? |
      | EDIT                |                |
      | STATS               |                |


  Scenario Outline: Check feed definition first screen form fields for Create new
    Given Admin Portal user is logged in
    And He is on Feed Manager page
    When He clicks CREATE NEW button
    Then Feed screen '1' page is opened
    And Feed screen '1' page is opened in 'Create New' mode
    And '<Fields>' is available and it is <Mandatory>

    Examples:
      | Fields                | Mandatory | Note                                                                  |
      | Name                  | true      |                                                                       |
      | Short Name            | true      | no special signs and numbers, only upercase letters and _ are allowed |
      | Location URL          | true      |                                                                       |
      | Type                  | true      | values: Product, Voucher                                              |
      | Currency              | true      | values: GBP, EUR                                                      |
      | Valid zones and rules | true      | Zones: UK, IE, Rules: values mapped in feed,                          |
      | Map to Merchant       | true      | values: list of active merchants from Merchant Mgr                    |
      | Map to categories     | false     | department is mandatory, category is optional (can be set on screen2  |
      | Affiliate Network     | true      | values: list of active affiliate networks from network Mgr            |
      | Schedule Type         | true      | values: MANUAL, CRON                                                  |
      | Schedule Timing       | false     | available only if Schedule Type: CRON selected                        |
      | Quality Score         | false     |                                                                       |


  Scenario Outline: Check feed definition first screen form fields for Edit
    Given Admin Portal user is logged in
    And He is on Feed Manager page
    When He clicks EDIT button for random Feed
    Then Feed screen '1' page is opened
    And Feed screen '1' page is opened in 'Edit' mode
    And '<Fields>' is available and it is <Mandatory>

    Examples:
      | Fields                | Mandatory | Note                                                                  |
      | Name                  | true      |                                                                       |
      | Short Name            | true      | no special signs and numbers, only upercase letters and _ are allowed |
      | Location URL          | true      |                                                                       |
      | Type                  | true      | values: Product, Voucher                                              |
      | Currency              | true      | values: GBP, EUR                                                      |
      | Valid zones and rules | true      | Zones: UK, IE, Rules: values mapped in feed,                          |
      | Map to Merchant       | true      | values: list of active merchants from Merchant Mgr                    |
      | Map to categories     | false     | department is mandatory, category is optional (can be set on screen2  |
      | Affiliate Network     | true      | values: list of active affiliate networks from network Mgr            |
      | Schedule Type         | true      | values: MANUAL, CRON                                                  |
      | Schedule Timing       | false     | available only if Schedule Type: CRON selected                        |
      | Quality Score         | false     |                                                                       |


  Scenario: Generate Preview From network
    Given Create new feed definition
    And Fill properly screen 1 form fields
    And Click 'NEXT' button
    When Create feed definition screen 2 opened
    And Generate preview button 'FROM NETWORK' clicked
    Then Preview should be properly generated
    And Only one product should be presented
    And Generated preview should be displayed in json format
    And Proper file 'preview' should be stored in S3 in bucket: iat-feeds-qa/feeds/<feedShortName>

  Scenario Outline: Generate Preview From network - validate if for sure pulled from network not only from s3
    Given Create new feed definition
    And Fill properly screen 1 form fields with Location URL as '<url1>'
    And On screen 2 generate preview button 'FROM NETWORK' is clicked
    And Preview should be properly generated with '<content1>'
    When Click 'BACK' button
    And Update Location URL as '<url2>'
    And Click 'NEXT' button
    And On screen 2 generate preview button 'FROM NETWORK' is clicked
    Then Preview should be properly generated with '<content2>'
    And Proper file 'preview' should be stored in S3 in bucket: iat-feeds-qa/feeds/<feedShortName>
    And Modify date of file stored on S3 should be updated

    Examples:
      | url1         | content1         | url2         | content2         |
      | url to file1 | content of file1 | url to file2 | content of file2 |

  Scenario: Generate Preview From network - error message
    Given Create new feed definition
    And Fill properly screen 1 form fields
    And Make sure Location URL value is valid but pointing to broken file
    And Click 'NEXT' button
    When Create feed definition screen 2 opened
    And Generate preview button 'FROM NETWORK' clicked
    Then Preview generation error should be displayed
    And Error message text should be: "Could not load preview JSON from network! Server Response: <log>"
    And Proper error logs should be stored in 'status.json' file on S3 in bucket: iat-feeds-qa/feeds/<feedShortName>


  Scenario Outline: Generate Preview From cache - validate if for sure pulled from s3 only
    Given Create new feed definition
    And Fill properly screen 1 form fields with Location URL as '<url1>'
    And On screen 2 generate preview button 'FROM NETWORK' is clicked
    And Preview should be properly generated with '<content1>'
    When Click 'BACK' button
    And Update Location URL as '<url2>'
    And Click 'NEXT' button
    And On screen 2 generate preview button 'FROM CACHE' is clicked
    Then Preview should be properly generated with '<content1>'
    And 'preview' file stored in S3 in bucket: iat-feeds-qa/feeds/<feedShortName> should not be changed
    And Modify date of file stored on S3 should not be updated

    Examples:
      | url1         | content1         | url2         |
      | url to file1 | content of file1 | url to file2 |

  Scenario: Generate Preview From cache - error message
    Given Create new feed definition
    And Fill properly screen 1 form fields
    And Click 'NEXT' button
    When Create feed definition screen 2 opened
    And Generate preview button 'FROM CACHE' clicked
    Then Preview generation error should be displayed
    And Error message text should be: "Could not load preview JSON from cache! Server Response: <log>"
    And Proper error logs should be stored in 'status.json' file on S3 in bucket: iat-feeds-qa/feeds/<feedShortName> ????

  Scenario: PD-500 Difference of scrap timestamp and current time is less than 24 hrs, product has to display in Solr offerings
    Given In Admin Portal, existing Feeds has scrap time stamp
    And Difference between scrap time stamp and current time of Feeds reload time is less than 24 hrs
    Then Products has to display in Solr offerings

  Scenario: PD-500 Difference of scrap timestamp and current time is greater than 24 hrs, product has to display in Solr debug
    Given In Admin Portal, existing Feeds has scrap time stamp
    And Difference between scrap time stamp and current time of Feeds reload time is greater than 24 hrs
    Then Products has to display in Solr debug

  Scenario: PD-500 If no scrap timestamp is available in Feeds, product has to display in Solr offering
    Given In Admin Portal, existing Feeds dosent have scrap time stamp
    And Feeds are Reload-ed
    Then Products has to display in Solr offerings
