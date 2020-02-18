Feature: Audit cms - retailer management
  As Audit Admin user
  I want to be able to create retailer with corresponding epoints account
  To be able to award points against retailers which stores were audited and match reward criteria

  @Regression @PositiveCase @retailer
  @deleteCreatedRetailerAfterTest
  Scenario Outline: Check retailer creation and corresponding epoints account
    Given Audit admin user is logged in with credentials '<login_password>'
    When New retailer will be created in IAT audit cms with following jsonData '<jsonBody>', '201'
    Then At the same time corresponding epoints account will be created for new retailer
    And Proper registration site set for retailer epoints account
    And Retailer was awarded with proper points count after registration
    And New retailer account is available on retailers list
    And Chain virtual group is properly created in user manager

  @qa
    Examples:
      | login_password | jsonBody                                                                                                                             |
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "TODAYS"}], "wholesalerId": "1"}   |
      # empty chain
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": null, "wholesalerId": "1"}                  |
      # empty name
      | admin,admin    | { "retailerName": null, "email": "api_audit_cms_retailer","chains": [ {"id": "TODAYS"}], "wholesalerId": "1"}                        |
      #
      # PREMIER
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "PREMIER"}], "wholesalerId": "1"}  |
      # empty wholesaler for Premier
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "PREMIER"}], "wholesalerId": null} |
      # empty name
      | admin,admin    | { "retailerName": null, "email": "api_audit_cms_retailer","chains": [ {"id": "PREMIER"}], "wholesalerId": "1"}                       |
      #
      # NISA
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "NISA"}], "wholesalerId": "1"}     |
      # empty wholesaler for Premier
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "NISA"}], "wholesalerId": null}    |
      # empty name
      | admin,admin    | { "retailerName": null, "email": "api_audit_cms_retailer","chains": [ {"id": "NISA"}], "wholesalerId": "1"}                          |


  @Regression @NegativeCase @retailer
  Scenario Outline: Try create retailer using invalid or missing parameters
    Given Audit admin user is logged in with credentials '<login_password>'
    When New retailer will be created in IAT audit cms with following jsonData '<jsonBody>', '<status>'
    Then Create retailer call returns proper error message '<message>', description '<description>', fieldErrors '<fieldErrors>'
    And Corresponding epoints account will not be created

  @qa
    Examples:
      | login_password | jsonBody                                                                                                                                        | status | message          | description                                                           | fieldErrors                                                                                                 |
      # missing email
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": null,"chains": null, "wholesalerId": "1"}                                                 | 400    | error.validation | null                                                                  | {"fieldErrors": [ { "objectName": "retailerDTO", "field": "email", "message": "NotNull"}]}                  |
      # missing email
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": null,"chains": [ {"id": "TODAYS"}], "wholesalerId": "1"}                                  | 400    | error.validation | null                                                                  | {"fieldErrors": [ { "objectName": "retailerDTO", "field": "email", "message": "NotNull"}]}                  |
      # missing wholesalerId for not premier chain
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "TODAYS"}], "wholesalerId": null}             | 400    | error.validation | Wholesaler field empty for retailer:                                  | {"fieldErrors": [ { "objectName": "Retailer", "field": "wholesalerId", "message": "cannot be null"}]}       |
      # not existing wholesalerID
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "TODAYS"}], "wholesalerId": "1000500100900"}  | 400    | error.validation | Wholesaler with id: 1000500100900 not found                           | {"fieldErrors": [ { "objectName": "Retailer", "field": "wholesalerId", "message": "wholesaler not found"}]} |
      # duplicated email
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "duplicated.audit.retailer@iat.ltd","chains": [ {"id": "TODAYS"}], "wholesalerId": "1"}   | 400    | error.validation | Retailer with email: duplicated.audit.retailer@iat.ltd already exists | {"fieldErrors": [ { "objectName": "Retailer", "field": "email", "message": "cannot be duplicated"}]}        |
      #
      # PREMIER
      # missing email
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": null,"chains": [ {"id": "PREMIER"}], "wholesalerId": "1"}                                 | 400    | error.validation | null                                                                  | {"fieldErrors": [ { "objectName": "retailerDTO", "field": "email", "message": "NotNull"}]}                  |
      # not existing wholesalerID
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "api_audit_cms_retailer","chains": [ {"id": "PREMIER"}], "wholesalerId": "1000500100900"} | 400    | error.validation | Wholesaler with id: 1000500100900 not found                           | {"fieldErrors": [ { "objectName": "Retailer", "field": "wholesalerId", "message": "wholesaler not found"}]} |
      # duplicated email
      | admin,admin    | { "retailerName": "API_AUDIT_CMS_RETAILER_", "email": "duplicated.audit.retailer@iat.ltd","chains": [ {"id": "PREMIER"}], "wholesalerId": "1"}  | 400    | error.validation | Retailer with email: duplicated.audit.retailer@iat.ltd already exists | {"fieldErrors": [ { "objectName": "Retailer", "field": "email", "message": "cannot be duplicated"}]}        |


    #TODO start

#  @Regression @Critical @PositiveCase @retailer @createNewChainRetailerAndStores @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check retailer remove from selected chain
#    Given Audit admin user is logged in with credentials '<login_password>'
#    When Some retailer will be removed from one of the chain he belongs
#    Then All associated with chain stores will be deactivated
#    And Retailer will be removed from stores belongs to chain from which he was removed
#    And Retailer will be deactivated in partner virtual group from where he was removed
#
#    Examples:
#      | login_password |
#      | admin,admin    |
#
#  @Regression @Critical @NegativeCase @retailer @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check retailer remove from selected chain with incorrect parameters
#    Given Audit admin user is logged in with credentials '<login_password>'
#    Given New chain, retailer and stores are created according to '<scenario>'
#    When Admin try to delete retailer from chain according to given '<scenario>', '<status>'
#    Then Proper retailer removing error message will be returned '<message>', '<description>', '<objectNameFE>', '<fieldFE>', '<messageFE>'
#
#    Examples:
#      | login_password | scenario                      | status | objectNameFE | fieldFE | messageFE | message          | description                                               |
#      | admin,admin    | retailerIdNotExists           | 400    | Retailer     | id      | not found | error.validation | Retailer not found: {retailerId}                          |
#      | admin,admin    | chainIdNotExists              | 400    | Chain        | id      | not found | error.validation | Chain not found: {chainId}                                |
#      | admin,admin    | chainAndRetailerAreNotRelated | 400    | general      | general | empty     | error.general    | Retailer has no store associated with chain id: {chainId} |
#
#  @Regression @Critical @PositiveCase @retailer @createNewChainRetailerAndStores @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check retailer take over existing store
#    Given Audit admin user is logged in with credentials '<login_password>'
#    Given New retailer will be created in IAT audit cms with following data 'automatedApiTestRetailer', 'automatedApiTestEmail', 'dynamically', 'Todays'
#    When Retailer take over existing store according to '<scenario>'
#    Then Proper relations between store and new owner will be established
#    And Taken store is in active state even if was deactivated before
#
#    Examples:
#      | login_password | scenario             |
#      | admin,admin    | takenStoreIsActive   |
#      | admin,admin    | takenStoreIsInactive |
#
#  @Regression @Critical @NegativeCase @retailer @createNewChainRetailerAndStores @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check retailer take over existing store using incorrect parameters
#    Given Audit admin user is logged in with credentials '<login_password>'
#    Given New retailer will be created in IAT audit cms with following data 'automatedApiTestRetailer', 'automatedApiTestEmail', 'dynamically', 'Todays'
#    When New Retailer take over existing store according to '<scenario>', '<status>'
#    Then Proper store taking over error message will be returned '<message>', '<description>', '<objectNameFE>', '<fieldFE>', '<messageFE>'
#
#    Examples:
#      | login_password | scenario            | status | objectNameFE | fieldFE | messageFE | message          | description                              |
#      | admin,admin    | retailerIdNotExists | 400    | Retailer     | id      | not found | error.validation | Retailer with id: {retailerId} not found |
#      | admin,admin    | storeIdNotExists    | 400    | Store        | id      | not found | error.validation | Store with id: {storeId} not found       |
#
#
##Tests connected with checking updating of virtual group in user manager according to changes in store or retailer entities
#
#  @Regression @Critical @PositiveCase @retailer @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check creation of virtual groups when store created and assigned to retailer
#    Given Audit admin user is logged in with credentials '<login_password>'
#    And New retailer will be created in IAT audit cms with following data '<retailerName>', '<email>', '<wholesalerId>', '<retailerChain>'
#    When New store will be created for specific chain '<storeChain>'
#    Then Store chain '<storeChain>' virtual group will be added to retailer account
#
#    Examples:
#      | login_password | retailerName             | email                 | wholesalerId | retailerChain | storeChain |
#      | admin,admin    | automatedApiTestRetailer | automatedApiTestEmail | dynamically  | Todays        | Premier    |
#      | admin,admin    | automatedApiTestRetailer | automatedApiTestEmail | dynamically  | Premier       | Todays     |
#
#  @Regression @Critical @PositiveCase @retailer @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check virtual group status change when last store removed
#    Given Audit admin user is logged in with credentials '<login_password>'
#    And New retailer will be created in IAT audit cms with following data '<retailerName>', '<email>', '<wholesalerId>', '<chain>'
#    And New store will be created for specific chain '<chain>'
#    When Last store belonging to retailer form chain '<chain>' will be removed
#    Then Retailer status of virtual group associated witch chain '<chain>' will be changed to deleted
#
#
#    Examples:
#      | login_password | retailerName             | email                 | wholesalerId | chain   |
#      | admin,admin    | automatedApiTestRetailer | automatedApiTestEmail | dynamically  | Todays  |
#      | admin,admin    | automatedApiTestRetailer | automatedApiTestEmail | dynamically  | Premier |
#
#  @Regression @Critical @PositiveCase @retailer @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check virtual groups statuses change when last store updated to other chain
#    Given Audit admin user is logged in with credentials '<login_password>'
#    And New retailer will be created in IAT audit cms with following data '<retailerName>', '<email>', '<wholesalerId>', '<chainOld>'
#    And New store will be created for specific chain '<chainOld>'
#    When Last store belonging to retailer form chain '<chainOld>' will be updated and other chain '<chainNew>' will be set
#    Then Retailer status of virtual group associated witch chain '<chainOld>' will be changed to deleted
#    And Store chain '<chainNew>' virtual group will be added to retailer account
#
#
#    Examples:
#      | login_password | retailerName             | email                 | wholesalerId | chainOld | chainNew |
#      | admin,admin    | automatedApiTestRetailer | automatedApiTestEmail | dynamically  | Todays   | Premier  |
#      | admin,admin    | automatedApiTestRetailer | automatedApiTestEmail | dynamically  | Premier  | Todays   |
#
#  @Regression @Critical @PositiveCase @retailer @deleteNewChainRetailerAndStoresUsingDatabaseQuery
#  Scenario Outline: Check if virtual group will be added when retailer take over store from new chain
#    Given Audit admin user is logged in with credentials '<login_password>'
#    And New retailer will be created in IAT audit cms with following data 'automatedApiTestRetailer', 'automatedApiTestEmail', 'dynamically', '<retailerChain>'
#    And New store will be created for specific chain '<storeChain>'
#    When Retailer take over store with chain for which he does not have virtual group
#    Then Store chain '<storeChain>' virtual group will be added to retailer account
#
#    Examples:
#      | login_password | retailerChain | storeChain |
#      | admin,admin    | Todays        | Premier    |
#      | admin,admin    | Premier       | Todays     |
#
##  Scenario Outline: Test
##    Given Audit admin user is logged in with credentials '<login_password>'
##    Given Test
##
##    Examples:
##      | login_password |
##      | admin,admin    |
#

  #TODO end

  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload stores - by import retailers endpoint - new store and retailer in store
    Given Audit admin user is logged in with credentials '<login_password>'
    And Extract stores ext_rel_ids from importRetailers file '<fileName>'
    When Import retailers call is made for upload retailers data (file: '<fileName>', partner: '<partnerShortName>', code: '200')
    Then Store retailers bulk load properly add retailer
    And Store retailers bulk load - relate partner store with retailer
    And Store retailers bulk load for data '<partnerShortName>', '<wholesalerProvided>' properly relate wholesaler with retailer
    And Epoints account was created for imported retailers
    And Chain virtual group is properly created in user manager for imported retailers for partner '<partnerShortName>'

  @qa
    Examples:
      | login_password | fileName                                         | partnerShortName | wholesalerProvided |
      #store is created with retailer
      | admin,admin    | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  | true               |
      | admin,admin    | importRetailers//premier_import_retailers_1.xlsx | PremierRetailers | true               |
      | admin,admin    | importRetailers//premier_import_retailers_2.xlsx | PremierRetailers | false              |
      | admin,admin    | importRetailers//nisa_import_retailers_1.xlsx    | nisaRetailers    | true               |
      | admin,admin    | importRetailers//nisa_import_retailers_2.xlsx    | nisaRetailers    | false              |


  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload stores - by import retailers endpoint - store in system and new retailer in store
    Given Audit admin user is logged in with credentials '<login_password>'
    And Extract stores ext_rel_ids from importRetailers file '<fileName>'
    And Stores with ext_rel_id are already in system for partner '<partnerShortName>'
    When Import retailers call is made for upload retailers data (file: '<fileName>', partner: '<partnerShortName>', code: '200')
    Then Store retailers bulk load properly add retailer
    And Store retailers bulk load - relate partner store with retailer
    And Store retailers bulk load for data '<partnerShortName>', '<wholesalerProvided>' properly relate wholesaler with retailer
    And Epoints account was created for imported retailers
    And Chain virtual group is properly created in user manager for imported retailers for partner '<partnerShortName>'

  @qa
    Examples:
      | login_password | fileName                                         | partnerShortName | wholesalerProvided |
      | admin,admin    | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  | true               |
      | admin,admin    | importRetailers//premier_import_retailers_2.xlsx | PremierRetailers | false              |
      | admin,admin    | importRetailers//nisa_import_retailers_2.xlsx    | nisaRetailers    | false              |


  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload stores - by import retailers endpoint - store have retailer already but trying update it by provide new on in file (old should stay)
    Given Audit admin user is logged in with credentials '<login_password>'
    And Extract stores ext_rel_ids from importRetailers file '<fileName>'
    And Import retailers call is made for upload retailers data (file: '<fileName>', partner: '<partnerShortName>', code: '200')
    And Store retailers bulk load properly add retailer
    And Store retailers bulk load - relate partner store with retailer
    When Import retailers call is made for upload retailers data (file: '<fileName>', partner: '<partnerShortName>', code: '200')
    Then Store retailers bulk load properly not add retailer

  @qa
    Examples:
      | login_password | fileName                                         | partnerShortName |
      | admin,admin    | importRetailers//todays_import_retailers_1.xlsx  | TodaysRetailers  |
      | admin,admin    | importRetailers//premier_import_retailers_2.xlsx | PremierRetailers |
      | admin,admin    | importRetailers//nisa_import_retailers_2.xlsx    | nisaRetailers    |