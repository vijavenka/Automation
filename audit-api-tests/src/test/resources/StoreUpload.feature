Feature: Audit cms - store management (upload stores)
  As Audit Admin user
  I want to be able to manage stores
  To be able to create, edit, update, delete them

  #TODO CRUD for store endpoints

  #Partners are Todays, Premier, Nisa -> bahaviour of nisa is same as premier

  @Regression @PositiveCase @storesBulkUpload
  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload stores - by import retailers endpoint - (store is created only if retailer email added)
    Given Audit admin user is logged in with credentials '<login_password>'
    When Import retailers call is made for upload retailers data (file: '<fileName>', partner: '<partnerShortName>', code: '200')
    And Get store details by externalId '<store_ext_id>'
    Then Store bulk load for data '<fileName>' properly add/not add store for chain '<partnerShortName>'

  @qa
    Examples:
      | login_password | fileName                                                  | partnerShortName | store_ext_id |
       #
       # TODAYS
       # country England
      | admin,admin    | uploadStores//todays_stores_and_retailers_import_1a.xlsx  | TodaysRetailers  | 9999123457   |
       # country E&W
      | admin,admin    | uploadStores//todays_stores_and_retailers_import_1b.xlsx  | TodaysRetailers  | 9999123457   |
       # country Northern Ireland
      | admin,admin    | uploadStores//todays_stores_and_retailers_import_1c.xlsx  | TodaysRetailers  | 9999123457   |
       # country N. Ireland
      | admin,admin    | uploadStores//todays_stores_and_retailers_import_1d.xlsx  | TodaysRetailers  | 9999123457   |
       # country Scotland
      | admin,admin    | uploadStores//todays_stores_and_retailers_import_1e.xlsx  | TodaysRetailers  | 9999123457   |
       #
       # PREMIER
       # country England
      | admin,admin    | uploadStores//premier_stores_and_retailers_import_1a.xlsx | PremierRetailers | 9999123456   |
       # country E&W
      | admin,admin    | uploadStores//premier_stores_and_retailers_import_1b.xlsx | PremierRetailers | 9999123456   |
       # country Northern Ireland
      | admin,admin    | uploadStores//premier_stores_and_retailers_import_1c.xlsx | PremierRetailers | 9999123456   |
       # country N. Ireland
      | admin,admin    | uploadStores//premier_stores_and_retailers_import_1d.xlsx | PremierRetailers | 9999123456   |
       # country Scotland
      | admin,admin    | uploadStores//premier_stores_and_retailers_import_1e.xlsx | PremierRetailers | 9999123456   |
       #
       # NISA
       # country England
      | admin,admin    | uploadStores//nisa_stores_and_retailers_import_1a.xlsx    | nisaRetailers    | 9999123455   |
       # country E&W
      | admin,admin    | uploadStores//nisa_stores_and_retailers_import_1b.xlsx    | nisaRetailers    | 9999123455   |
       # country Northern Ireland
      | admin,admin    | uploadStores//nisa_stores_and_retailers_import_1c.xlsx    | nisaRetailers    | 9999123455   |
       # country N. Ireland
      | admin,admin    | uploadStores//nisa_stores_and_retailers_import_1d.xlsx    | nisaRetailers    | 9999123455   |
       # country Scotland
      | admin,admin    | uploadStores//nisa_stores_and_retailers_import_1e.xlsx    | nisaRetailers    | 9999123455   |


  @Regression @Critical @NegativeCase @storesBulkUpload
  Scenario Outline: upload stores - by import retailers endpoint - error messages validation
    Given Audit admin user is logged in with credentials '<login_password>'
    When Store retailers bulk load call is made for incorrect data '<fileName>', '<status>', '<partnerShortName>'
    Then Store retailers bulk load for incorrect data '<fileName>' response returns error message '<message>', '<description>'

  @qa
    Examples:
      | login_password | fileName                                                                                | status | message                                   | description                                             | partnerShortName |
      # incorrect credentials
      | admin,admin22  | uploadStores//missingColumns//stores_missing_column_storeName.xlsx                      | 401    | invalid_token                             | Invalid access token: null                              | TodaysRetailers  |
      #
      # TODAYS
      # missing column - store_name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_storeName.xlsx                      | 400    | Column with name store_name not found     | null                                                    | TodaysRetailers  |
      # missing column - audit_group
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_auditGroup.xlsx                     | 400    | Column with name audit_group not found    | null                                                    | TodaysRetailers  |
      # missing column - address_line_1
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine1.xlsx                   | 400    | Column with name address_line_1 not found | null                                                    | TodaysRetailers  |
      # missing column - address_line_2
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine2.xlsx                   | 400    | Column with name address_line_2 not found | null                                                    | TodaysRetailers  |
      # missing column - address_line_3
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine3.xlsx                   | 400    | Column with name address_line_3 not found | null                                                    | TodaysRetailers  |
      # missing column - address_line_4
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine4.xlsx                   | 400    | Column with name address_line_4 not found | null                                                    | TodaysRetailers  |
      # missing column - post_code
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_postCode.xlsx                       | 400    | Column with name post_code not found      | null                                                    | TodaysRetailers  |
      # missing column - country
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_country.xlsx                        | 400    | Column with name country not found        | null                                                    | TodaysRetailers  |
      # missing column - ext_rel_id
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_extRelId.xlsx                       | 400    | Column with name ext_rel_id not found     | null                                                    | TodaysRetailers  |
      # missing column - wholesaler
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_wholesaler.xlsx                     | 400    | error.validation                          | Missing wholesaler for retailer:                        | TodaysRetailers  |
      # missing column - First Name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_firstName.xlsx                      | 400    | Column with name First Name not found     | null                                                    | TodaysRetailers  |
      # missing column - Last Name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_lastName.xlsx                       | 400    | Column with name Last Name not found      | null                                                    | TodaysRetailers  |
      # missing column - Email
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_email.xlsx                          | 400    | Column with name Email not found          | null                                                    | TodaysRetailers  |
      #
      # filed values - wholesaler empty for Todays
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_wholesaler.xlsx | 400    | error.validation                          | Missing wholesaler for retailer: Api missing wholesaler | TodaysRetailers  |
      # filed values - country: EnglandWales (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_1.xlsx  | 400    | error.validation                          | Unsupported country: EnglandWales                       | TodaysRetailers  |
      # filed values - country: NorthernIreland (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_2.xlsx  | 400    | error.validation                          | Unsupported country: NorthernIreland                    | TodaysRetailers  |
      # filed values - country: empty (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_3.xlsx  | 400    | error.validation                          | Unsupported country: null                               | TodaysRetailers  |
      #
      # PREMIER
      # missing column - store_name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_storeName.xlsx                      | 400    | Column with name store_name not found     | null                                                    | PremierRetailers |
      # missing column - audit_group
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_auditGroup.xlsx                     | 400    | Column with name audit_group not found    | null                                                    | PremierRetailers |
      # missing column - address_line_1
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine1.xlsx                   | 400    | Column with name address_line_1 not found | null                                                    | PremierRetailers |
      # missing column - address_line_2
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine2.xlsx                   | 400    | Column with name address_line_2 not found | null                                                    | PremierRetailers |
      # missing column - address_line_3
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine3.xlsx                   | 400    | Column with name address_line_3 not found | null                                                    | PremierRetailers |
      # missing column - address_line_4
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine4.xlsx                   | 400    | Column with name address_line_4 not found | null                                                    | PremierRetailers |
      # missing column - post_code
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_postCode.xlsx                       | 400    | Column with name post_code not found      | null                                                    | PremierRetailers |
      # missing column - country
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_country.xlsx                        | 400    | Column with name country not found        | null                                                    | PremierRetailers |
      # missing column - ext_rel_id
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_extRelId.xlsx                       | 400    | Column with name ext_rel_id not found     | null                                                    | PremierRetailers |
      # missing column - First Name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_firstName.xlsx                      | 400    | Column with name First Name not found     | null                                                    | PremierRetailers |
      # missing column - Last Name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_lastName.xlsx                       | 400    | Column with name Last Name not found      | null                                                    | PremierRetailers |
      # missing column - Email
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_email.xlsx                          | 400    | Column with name Email not found          | null                                                    | PremierRetailers |
      #
      # filed values - country: EnglandWales (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_1.xlsx  | 400    | error.validation                          | Unsupported country: EnglandWales                       | PremierRetailers |
      # filed values - country: NorthernIreland (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_2.xlsx  | 400    | error.validation                          | Unsupported country: NorthernIreland                    | PremierRetailers |
      # filed values - country: empty (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_3.xlsx  | 400    | error.validation                          | Unsupported country: null                               | PremierRetailers |
      #
      # NISA
      # missing column - store_name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_storeName.xlsx                      | 400    | Column with name store_name not found     | null                                                    | nisaRetailers    |
      # missing column - audit_group
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_auditGroup.xlsx                     | 400    | Column with name audit_group not found    | null                                                    | nisaRetailers    |
      # missing column - address_line_1
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine1.xlsx                   | 400    | Column with name address_line_1 not found | null                                                    | nisaRetailers    |
      # missing column - address_line_2
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine2.xlsx                   | 400    | Column with name address_line_2 not found | null                                                    | nisaRetailers    |
      # missing column - address_line_3
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine3.xlsx                   | 400    | Column with name address_line_3 not found | null                                                    | nisaRetailers    |
      # missing column - address_line_4
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_addressLine4.xlsx                   | 400    | Column with name address_line_4 not found | null                                                    | nisaRetailers    |
      # missing column - post_code
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_postCode.xlsx                       | 400    | Column with name post_code not found      | null                                                    | nisaRetailers    |
      # missing column - country
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_country.xlsx                        | 400    | Column with name country not found        | null                                                    | nisaRetailers    |
      # missing column - ext_rel_id
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_extRelId.xlsx                       | 400    | Column with name ext_rel_id not found     | null                                                    | nisaRetailers    |
      # missing column - First Name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_firstName.xlsx                      | 400    | Column with name First Name not found     | null                                                    | nisaRetailers    |
      # missing column - Last Name
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_lastName.xlsx                       | 400    | Column with name Last Name not found      | null                                                    | nisaRetailers    |
      # missing column - Email
      | admin,admin    | uploadStores//missingColumns//stores_missing_column_email.xlsx                          | 400    | Column with name Email not found          | null                                                    | nisaRetailers    |
      #
      # filed values - country: EnglandWales (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_1.xlsx  | 400    | error.validation                          | Unsupported country: EnglandWales                       | nisaRetailers    |
      # filed values - country: NorthernIreland (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_2.xlsx  | 400    | error.validation                          | Unsupported country: NorthernIreland                    | nisaRetailers    |
      # filed values - country: empty (valid are: E&W, Scotland, N. Ireland, England)
      | admin,admin    | uploadStores//validationCases//todays_stores_and_retailers_import_value_country_3.xlsx  | 400    | error.validation                          | Unsupported country: null                               | nisaRetailers    |


  @storesBulkUpload
  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload stores - by import retailers endpoint check if only one store with duplicated ext_rel_id created across all partners
    Given Audit admin user is logged in with credentials '<login_password>'
    When Import retailers call is made for upload retailers data (file: '<fileName>', partner: 'TodaysRetailers', code: '200')
    And Import retailers call is made for upload retailers data (file: '<fileName>', partner: 'PremierRetailers', code: '200')
    And Import retailers call is made for upload retailers data (file: '<fileName>', rows to modify: '2', partner: 'nisaRetailers', code: '200')
    And Get store details by externalId '<store_ext_id>'
    Then Only '1' store with ext_rel_id '<store_ext_id>' was loaded to db for partner 'TodaysRetailers'
    And Only '0' store with ext_rel_id '<store_ext_id>' was loaded to db for partner 'PremierRetailers'
    And Only '0' store with ext_rel_id '<store_ext_id>' was loaded to db for partner 'nisaRetailers'

  @qa
    Examples:
      | login_password | fileName                                                              | store_ext_id |
      | admin,admin    | uploadStores//stores_and_retailers_import_duplicated_ext_rel_ids.xlsx | 100000001    |


  @Regression @PositiveCase @storesBulkUpload
  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload stores - by import retailers endpoint check if store not created if no retailer assigned
    Given Audit admin user is logged in with credentials '<login_password>'
    When Import retailers call is made for upload retailers data (file: '<fileName>', partner: 'TodaysRetailers', code: '200')
    And Import retailers call is made for upload retailers data (file: '<fileName>', partner: 'PremierRetailers', code: '200')
    And Import retailers call is made for upload retailers data (file: '<fileName>', partner: 'nisaRetailers', code: '200')
    And Get store details by externalId '<store_ext_id>' - not found
    Then Only '0' store with ext_rel_id '<store_ext_id>' was loaded to db for partner 'TodaysRetailers'
    And Only '0' store with ext_rel_id '<store_ext_id>' was loaded to db for partner 'PremierRetailers'
    And Only '0' store with ext_rel_id '<store_ext_id>' was loaded to db for partner 'nisaRetailers'

    Examples:
      | login_password | fileName                                                          | store_ext_id |
      | admin,admin    | uploadStores//stores_and_retailers_import_noRetailerAssigned.xlsx | 100000002    |


  @Regression @PositiveCase @storesBulkUpload
  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload premier stores - new store
    Given Audit admin user is logged in with credentials '<login_password>'
    When Store bulk load call is made for data '<fileName>', '<partnerShortName>', '200'
    And Get store details by externalId '<store_ext_id>'
    Then Store bulk load for data '<fileName>' properly add/not add store for chain '<partnerShortName>'
    And Delete store by id 'previous_call', code '200'

  @qa
    Examples:
      | login_password | fileName                     | partnerShortName | store_ext_id |
      # country England
      | admin,admin    | premier_stores_upload_1a.xls | PremierRetailers | 9999123456   |
      # country E&W
      | admin,admin    | premier_stores_upload_1b.xls | PremierRetailers | 9999123456   |
      # country Northern Ireland
      | admin,admin    | premier_stores_upload_1c.xls | PremierRetailers | 9999123456   |
      # country N. Ireland
      | admin,admin    | premier_stores_upload_1d.xls | PremierRetailers | 9999123456   |
      # country Scotland
      | admin,admin    | premier_stores_upload_1e.xls | PremierRetailers | 9999123456   |


  @Regression @NegativeCase @storesBulkUpload
  @deleteImportedRetailersAndStoreAfterTest
  Scenario Outline: upload premier stores - error messages validation
    Given Audit admin user is logged in with credentials '<login_password>'
    When Store bulk load call is made for incorrect data '<fileName>', '<partnerShortName>', '<status>'
    Then Store bulk load error message will be returned '<message>', '<description>'

  @qa
    Examples:
      | login_password | fileName                                      | partnerShortName | status | message          | description                                                                                  |
      | admin,admin    | validationCases//premier_stores_upload_2a.xls | Premier          | 400    | error.validation | Chain with a partner short name: Premier doesn't exist in db.                                |
      | admin,admin    | validationCases//premier_stores_upload_2a.xls | PremierRetailers | 400    | error.validation | Unsupported country: POLSKA                                                                  |
      | admin,admin    | validationCases//premier_stores_upload_2b.xls | PremierRetailers | 400    | error.validation | Unsupported country:                                                                         |
      | admin,admin    | validationCases//premier_stores_upload_2a.xls | TodaysRetailers  | 400    | error.validation | Chain with a partner short name: TodaysRetailers cannot be served in a store upload request. |
      | admin,admin    | validationCases//premier_stores_upload_2a.xls | nisaRetailers    | 400    | error.validation | Chain with a partner short name: nisaRetailers cannot be served in a store upload request.   |


#  @DEBUG
#  Scenario Outline: upload retailers to stores list - MANUAL
#    Given Audit admin user is logged in with credentials '<login_password>'
#    When Store retailers bulk load call is made for data '<fileName>', '<partnerShortName>'
##    Then Store retailers bulk load for data '<fileName>' properly add/not add retailer
##    And Store retailers bulk load for data '<fileName>' properly relate partner '<partnerShortName>' store with retailer
##    And Store retailers bulk load for data '<fileName>', '<partnerShortName>', '<wholesalerProvided>' properly relate wholesaler with retailer
##    And Retailer was properly created in points-manager
##    And Chain '<partnerShortName>' virtual group is properly created in user manager
#
#    Examples:
#      | login_password | fileName                 | partnerShortName | wholesalerProvided |
#      #store have retailer already but trying update it by provide new on in file (old should stay)
#      | admin,admin    | stores_bulk1_Manual.xlsx | TodaysRetailers  | true               |
#      #store have retailer already but trying update it by provide new on in file (old should stay)
##      | admin,admin    | stores_bulk1_premier.xlsx  | PremierRetailers | true               |


