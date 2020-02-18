Feature: Audit cms - question management
  As Audit Admin user
  I want to be able to manage my questions
  To be able to crate, edit and delete them

  @Regression @PositiveCase @question
  Scenario Outline: Check if it's possible to get lists of all available questions
    Given Audit admin user is logged in with credentials '<login_password>'
    When Get question call is made
    Then Get question call returns proper contract

  @qa
    Examples:
      | login_password |
      | admin,admin    |


  @Regression @PositiveCase @question
  @createDataBeforeQuestionsTests
  Scenario Outline: Check if it's possible to create question
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create question call is made with jsonData '<jsonData>', code '201'
    Then Create question call returns proper data
    And Get question by id 'previous_call', code '200'
    And Get question by id returns proper data
    And Delete question by id 'previous_call', code '200'

  @qa
    Examples:
      | login_password | jsonData                                                                                                                                                                                                     |
      # Product type - placement - FREEZER, without productId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement":"FREEZER","imagesNumber": null,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null} |
      # Product type - placement - CHILLER, without productId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement":"CHILLER","imagesNumber": null,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null} |
      # Product type - placement - SHELF, without productId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement":"SHELF","imagesNumber": null,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null}   |
      # Product type - placement - COUNTER, without productId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement":"COUNTER","imagesNumber": null,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null} |
      # Product type - placement - TILL, without productId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement":"TILL","imagesNumber": null,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null}    |
      # Product type - placement - null, without productId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement": null,"imagesNumber": null,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null}     |
      # adhoc type - no productId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"ADHOC","placement": null,"imagesNumber": null,"extRelId":null,"id":null, "productId": null, "categoryId": null}                  |
      # adhoc type - with productId (ERV-317)
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"ADHOC","placement":"CHILLER","imagesNumber": null,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null}   |
      # image type - placement - COUNTER, without categoryId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"IMAGE","placement":"COUNTER","imagesNumber": 99,"extRelId":null,"id":null, "productId": null, "categoryId": null}                |
      # image type - placement - null, with categoryId
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"IMAGE","placement": null,"imagesNumber": 99,"extRelId":null,"id":null, "productId": null, "categoryId": "previous_call"}         |


  @Regression @NegativeCase @question
  @createDataBeforeQuestionsTests
  Scenario Outline: Check create question with improper data error message validation - unique adhocExtId
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create question call is made with jsonData '<jsonData>', code '201'
    When Create question call is made with jsonData '<jsonData>', code '<code>'
    Then Create question call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'

  @qa
    Examples:
      | login_password | jsonData                                                                                                                                                                                             | code | message          | description                         | fieldErrors                                                                                         |
      | admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":2147483647,"questionType":"ADHOC","placement":"FREEZER","imagesNumber":null,"extRelId":null,"id":null, "productId": null, "categoryId": null} | 400  | error.validation | Question Adhoc Ext Id is not unique | {"fieldErrors": [{ "objectName": "QuestionDTO", "field": "adhocExtId", "message": "not unique" }] } |
      # ext_rel_id in question can be ignored because is not used, ext_rel_id is stored in table: audit_criteria
      #| admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement":"FREEZER","imagesNumber":null,"extRelId":2147483647,"id":null, "productId": null, "categoryId": null} | 400  | error.validation | ble                                 | null                                                                                                |


  @Regression @NegativeCase @question
  @createDataBeforeQuestionsTests
  Scenario Outline: Check create question with improper data error message validation
    Given Audit admin user is logged in with credentials '<login_password>'
    When Create question call is made with jsonData '<jsonData>', code '<code>'
    Then Create question call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'

  @qa
    Examples:
      | login_password | jsonData                                                                                                                                                                                                     | code | message                       | description                                                                                                                                         | fieldErrors |
      # productId and categoryId provided in same time
      | admin,admin    | {"questionText":"text","adhocExtId":2147483647,"questionType":"PRODUCT","placement":"FREEZER","imagesNumber":0,"extRelId":"12346789","id":null, "productId": "previous_call", "categoryId": "previous_call"} | 400  | error.validation              | Either product id or category id can be provided at the same time                                                                                   | null        |
      # Improper value in placement
      | admin,admin    | {"questionText":"text","adhocExtId":2147483646,"questionType":"PRODUCT","placement":"BALD_HEAD","imagesNumber":0,"extRelId":"12346789","id":null, "productId": null, "categoryId": null}                     | 400  | Request JSON cannot be parsed | enumeration.ProductPlacement from String value 'BALD_HEAD': value not one of declared Enum instance names: [CHILLER, SHELF, FREEZER, COUNTER, TILL] | null        |
      # Improper value in questionType
      | admin,admin    | {"questionText":"text","adhocExtId":2147483645,"questionType":"BALD_HEAD","placement":"FREEZER","imagesNumber":0,"extRelId":"12346789","id":null, "productId": null, "categoryId": null}                     | 400  | Request JSON cannot be parsed | enumeration.QuestionType from String value 'BALD_HEAD': value not one of declared Enum instance names: [ADHOC, IMAGE, PRODUCT]                      | null        |
      # image type - with productId - working but this combination should not be allowed
      #| admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"IMAGE","placement": null,"imagesNumber": 99,"extRelId":null,"id":null, "productId": "previous_call", "categoryId": null}         |400  | error.validation              | Either product id or category id can be provided at the same time                                                                                   | null        |
      # product type - without productId - working but this combination should not be allowed
      #| admin,admin    | {"questionText":"API_AUDIT_CMS_QUESTION_","adhocExtId":null,"questionType":"PRODUCT","placement": null,"imagesNumber": null,"extRelId":null,"id":null, "productId": null, "categoryId": null}         |400  | error.validation              | Either product id or category id can be provided at the same time                                                                                   | null        |
