Feature: Audit cms - product management
  As Audit Admin user
  I want to be able to manage my products
  To be able to crate, edit and delete them

  @Regression @PositiveCase @product
  Scenario Outline: Check if it's possible to get lists of all available products
    Given Audit admin user is logged in with credentials '<login_password>'
    When Get product call is made
    Then Get product call returns proper contract

  @qa
    Examples:
      | login_password |
      | admin,admin    |


  @Regression @PositiveCase @product
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Check if it's possible to create product
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create supplier call is made with jsonData 'DEFAULT', code '201'
    And Create brand call is made with jsonData 'DEFAULT', code '201'
    And Create category call is made with jsonData 'DEFAULT', code '201'
    When Create product call is made with jsonData '<jsonData>', code '201'
    Then Create product call returns proper data
    And Get product by id 'previous_call', code '200'
    And Get product by id returns proper data
    And Delete product by id 'previous_call', code '200'

  @qa
    Examples:
      | login_password | jsonData                                                                                                                                                                                                                                                                                           |
      | admin,admin    | { "id": null, "productName": "API_AUDIT_CMS_PRODUCT_", "url": "https://s3-eu-west-1.amazonaws.com/iat-audit-shelf-photos-qa/products/Clean_n_Fresh_Thick_Pine-Bleach-750ml.jpg", "description": "RANDOm DEScription @#$$%%435454 533", "categoryId": "previous_call", "brandId": "previous_call" } |
      # empty url
      | admin,admin    | { "id": null, "productName": "API_AUDIT_CMS_PRODUCT_", "url": null, "description": "RANDOm DEScription @#$$%%435454 533", "categoryId": "previous_call", "brandId": "previous_call" }                                                                                                              |
      # empty description
      | admin,admin    | { "id": null, "productName": "API_AUDIT_CMS_PRODUCT_", "url": null, "description": null, "categoryId": "previous_call", "brandId": "previous_call" }                                                                                                                                               |
      # empty brand
      | admin,admin    | { "id": null, "productName": "API_AUDIT_CMS_PRODUCT_", "url": null, "description": null, "categoryId": "previous_call", "brandId": null }                                                                                                                                                          |


  @Regression @NegativeCase @product
  @deleteAllDataOfCreatedAuditAfterTest
  Scenario Outline: Check create product with improper data error message validation
    Given Audit admin user is logged in with credentials '<login_password>'
    And Create supplier call is made with jsonData 'DEFAULT', code '201'
    And Create brand call is made with jsonData 'DEFAULT', code '201'
    And Create category call is made with jsonData 'DEFAULT', code '201'
    When Create product call is made with jsonData '<jsonData>', code '<code>'
    Then Create product call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'

  @qa
    Examples:
      | login_password | jsonData                                                                                                                                                                                                                                                                     | code | message                   | description           | fieldErrors |
      | admin,admin    | { "id": null, "productName": "", "url": "https://s3-eu-west-1.amazonaws.com/iat-audit-shelf-photos-qa/products/Clean_n_Fresh_Thick_Pine-Bleach-750ml.jpg", "description": "RANDOm DEScription @#$$%%435454 533", "categoryId": "previous_call", "brandId": "previous_call" } | 500  | error.internalServerError | Internal server error | null        |