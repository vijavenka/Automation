Feature: IAT admin HR - templates
  As IAT Admin user
  I want to be able to mange templates existing in the system
  To be able to create new template, delete existing one and list them

  @Regression @PositiveCase @Templates
  Scenario Outline: Templates - get all existing templates data
    Given IAT Admin user is logged in with credentials '<credentials>'
    When Admin lists all existing in system templates
    Then All templates should be properly returned

  @qa
    Examples:
      | credentials                                      |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd |


  @Regression @PositiveCase @Templates
  Scenario Outline: Templates - get valid templates data for user
    Given IAT Admin user is logged in with credentials '<credentials>'
    And Set Ecards settings call for 'templates' is made with data '<settingsTemplate>'
    When Admin lists valid templates call is made
    Then All valid templates should be properly returned for current default templates

  @qa
    Examples:
      | credentials                                      | settingsTemplate                  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"useDefaultTemplatesSet": true}  |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | {"useDefaultTemplatesSet": false} |


  @Regression @PositiveCase @Templates
  Scenario Outline: Templates - get individual templates data
    Given IAT Admin user is logged in with credentials '<credentials>'
    And Admin lists all existing in system templates
    When Admin retrieve details of chosen template by template id
    Then Retrieved template details are as expected

  @qa
    Examples:
      | credentials                                             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd        |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd              |
      | api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd |


  @Regression @PositiveCase @Templates
  Scenario Outline: Templates - get individual templates data using invalid template id
    Given IAT Admin user is logged in with credentials '<credentials>'
    When Admin retrieve details of not existing template by template id '<templateId>', '<status>'
    Then Retrieved template by id '<templateId>' returns proper error message '<error>', '<message>', '<status>'

  @qa
    Examples:
      | credentials                                | templateId      | error                  | message                                                                             | status |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd | wrongTemplateId | Argument type mismatch | Argument type mismatch exception id                                                 | 400    |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd | 9999999         | null                   | {errors : [{"message":"There is no ECardsTemplate with id:[%s]","fieldName":"id"}]} | 404    |


  @Regression @PositiveCase @Templates @DeleteTemplateAfterTest
  Scenario Outline: Templates - add template to the system
    Given IAT Admin user is logged in with credentials '<credentials>'
    When Admin add new template to the system '<templateData>'
    Then New template will be properly added
    And New template will be available on list of all templates

  @qa
    Examples:
      | credentials                                      | templateData                                                                                                                                                                                                                                                                                                                                                                                                    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "name": "Custom Template Test Creation RANDOM_NAME", "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" } |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "name": "Custom Template Test Creation RANDOM_NAME", "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_6617a4c9-37ad-414c-8cfb-adcbc959243e_af2ec5d2a074ddd178a4963384898a94.png", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_e9a4d42b-53d2-455a-aa29-7670b95ba9a1_af2ec5d2a074ddd178a4963384898a94.png" }   |


  @Regression @PositiveCase @Templates
  Scenario Outline: Templates - try add template to the system with invalid parameters
    Given IAT Admin user is logged in with credentials '<credentials>'
    When Admin add new template to the system with invalid data '<jsonData>', '<status>'
    Then Create template for incorrect data returns error message '<status>', 'null', '<message>'

  @qa
    Examples:
      | credentials                                      | jsonData                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | status | message                                                                              |
      #//missing name
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                                                                                                                                                                                                                                                            | 400    | { "errors":[{"message":"Name cannot be empty","fieldName":"name"}]}                  |
      #//missing image url
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "name": "Custom Template Test Creation RANDOM_NAME", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                                                                                                                                                                                                                                                                                                                                                                          | 400    | { "errors":[{"message":"Image Url cannot be empty","fieldName":"imageUrl"}]}         |
      # too long name
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "name": "newTemplateNameq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45o2555", "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" } | 400    | { "errors":[{"message":"Name is too long","fieldName":"name"}]}                      |
      #//missing thumbnail url
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "name": "Custom Template Test Creation RANDOM_NAME", "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                                                                                                                                                                                                                                                                                                                                                                              | 400    | { "errors":[{"message":"Thumbnail Url cannot be empty","fieldName":"thumbnailUrl"}]} |
      #//wrong image format
#      | admin_platform,password                          | { "name": "Custom Template Test Creation", "imageUrl": "http://media.giphy.com/media/109Ku3hdapZJle/giphy.gif", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                                                                                                                                                                                                                                                                                                                 | 400      |         |
      #//image without http
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "name": "Custom Template Test Creation RANDOM_NAME", "imageUrl": "WWW.iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                                                                                                                                                                                                          | 400    | { "errors":[{"message":"Image Url is not valid","fieldName":"imageUrl"}]}            |


  @Regression @PositiveCase @Templates @DeleteTemplateAfterTest
  Scenario Outline: Templates - update existing template data
    Given IAT Admin user is logged in with credentials '<credentials>'
    And Admin add new template to the system '<jsonBody>'
    When Created template will be updated with new data '<updateJsonBody>'
    Then Template data will be properly updated

  @qa
    Examples:
      | credentials                                      | jsonBody                                                                                                                                                                                                                                                                                                                                                                                                        | updateJsonBody                                                                                                                                                                                                                                                                                                                                                                                    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | { "name": "Custom Template Test Creation RANDOM_NAME", "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" } | { "name": "Custom Template Test Creation", "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_6617a4c9-37ad-414c-8cfb-adcbc959243e_af2ec5d2a074ddd178a4963384898a94.png", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_e9a4d42b-53d2-455a-aa29-7670b95ba9a1_af2ec5d2a074ddd178a4963384898a94.png" } |


  @Regression @PositiveCase @Templates @DeleteTemplateAfterTest
  Scenario Outline: Templates - update existing template data with invalid parameters
    Given IAT Admin user is logged in with credentials '<credentials>'
    And Admin add new template to the system '<jsonBody>'
    When Created template will be updated with new invalid data '<updateJsonBody>', '<status>'
    Then Update template for incorrect data returns error message '<status>', 'null', '<message>'

  @qa
    Examples:
      | credentials                                      | jsonBody | updateJsonBody                                                                                                                                                                                                                                                                                                                                                                                               | status | message                                                                              |
      #//missing name
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | default  | { "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                   | 400    | { "errors":[{"message":"Name cannot be empty","fieldName":"name"}]}                  |
      #//missing image url
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | default  | { "name": "Custom Template Test Creation RANDOM_NAME", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                                                                                                                                 | 400    | { "errors":[{"message":"Image Url cannot be empty","fieldName":"imageUrl"}]}         |
      #//missing thumbnail url
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | default  | { "name": "Custom Template Test Creation RANDOM_NAME", "imageUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" }                                                                                                                                                                     | 400    | { "errors":[{"message":"Thumbnail Url cannot be empty","fieldName":"thumbnailUrl"}]} |
      #//image without http
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | default  | { "name": "Custom Template Test Creation RANDOM_NAME", "imageUrl": "WWW.iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG", "thumbnailUrl": "http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG" } | 400    | { "errors":[{"message":"Image Url is not valid","fieldName":"imageUrl"}]}            |


  @Regression @PositiveCase @Templates
  Scenario Outline: Templates - delete template from the system
    Given IAT Admin user is logged in with credentials '<credentials>'
    And Admin add new template to the system '<jsonBody>'
    Then New template will be deleted
    Then Deleted template will not be available on list of all templates

  @qa
    Examples:
      | credentials                                      | jsonBody |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | default  |


  @Regression @PositiveCase @Templates
  Scenario Outline: Templates - delete template from the system with invalid id parameter
    Given IAT Admin user is logged in with credentials '<credentials>'
    When Delete template with invalid id parameter '<templateId>', '<status>'
    Then Delete template by id '<templateId>' returns proper error message '<error>', '<message>', '<status>'

  @qa
    Examples:
      | credentials                                      | templateId      | error                  | message                                                                             | status |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | wrongTemplateId | Argument type mismatch | Argument type mismatch exception id                                                 | 400    |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | 9999999         | null                   | {errors : [{"message":"There is no ECardsTemplate with id:[%s]","fieldName":"id"}]} | 404    |


