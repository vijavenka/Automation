Feature: Partner - creation
  As platform admin
  I want to be able to create new Partner
  To be able to register new clients on ePoints.com side

  #1. Points Manager exposes methods for new Partners creation

  @PositiveCase @PartnerCreation @Partners
  @Regression
  Scenario Outline: Creating new Partner - check contract and response data for positive cases
    Given Add new partner call is made with provided data '<name>','<siteURL>','<description>','<email>','<logoURL>','<group>' (positive cases)
    When Partner should be properly created for provided data '<name>','<siteURL>','<description>','<email>','<logoURL>','<group>'
    Then Create Partner response match contract

  @qa @stag
    Examples:
      | name          | siteURL       | description | email           | logoURL               | group |
      | TestPartner01 | null          | null        | null            | null                  | null  |
      | TestPartner02 | www.google.pl | null        | email@email.com | http://www.google.com | null  |
      | TestPartner03 | null          | test        | null            | null                  | null  |


  @NegativeCase @PartnerCreation @Partners
  @Regression
  Scenario Outline: Creating new Partner - check response header data for negative cases
    Given Points Manager API is responding to requests
    When Add new partner call is made with provided data '<name>','<siteURL>','<description>','<email>','<logoURL>','<group>','<expResponseCode>','<expErrorCode>','<expErrorMsg>' (negative cases)
    Then Partner should not be properly created for provided data

  @qa @stag
    Examples:
      | name        | siteURL       | description | email           | logoURL               | group        | expResponseCode | expErrorCode      | expErrorMsg                             |
      | null        | null          | null        | null            | null                  | null         | 400             | INVALID_ARGUMENTS | Cannot register partner without name    |
      | ""#$%       | null          | null        | null            | null                  | null         | 400             | INVALID_ARGUMENTS | JSON parse error: Unexpected character  |
      | TestPartner | www.google.pl | null        | email@email.com | http://www.google.com | not_existing | 404             | INVALID_GROUP     | There is no group with shortName=$GROUP |


  #UNITED
  #Assumption: file will be static with set of known data. File will be used for processing and partner creation validated. After each test partners will be removed.
  @PositiveCase @PartnerCreation @Partners
  @Regression
  @deleteCreatedPartnersAfterTest
  Scenario Outline: Partners bulk upload - correct bulk upload
    Given Points Manager API is responding to requests
    When File '<googleDocId>' with partners list will be processed for partners group '<groupShortName>', '200'
    Then Partners will be properly saved in points manager and assigned to correct partners group '<groupShortName>'

  @qa @stag
    Examples:
      | groupShortName  | googleDocId                                  |
      | UnitedSuppliers | 15N1t4m6ojs6wqE7cfuCjiRMnY0d5hl6r5zK5EPi4reM |
    # PROD
    # |UnitedSuppliers  |1Cnc5Qj97CqX_-tJYc34oauYIQSnJl5wij4w7jp4uMJw  |


  @PositiveCase @PartnerCreation @Partners
  @Regression
  @deleteCreatedPartnersAfterTest
  Scenario Outline: Partners bulk upload - error validation
    Given Points Manager API is responding to requests
    When File '<googleDocId>' with partners list will be processed for partners group '<groupShortName>', '<code>'
    Then Correct error message will be returned for file '<error>', '<message>', '<items>'
    And All added during bulk upload partners will be rolled back

  @qa @stag
    Examples:
      | groupShortName       | googleDocId                                  | code | error                   | message                                                                                                                              | items                                                   |
#  Wrong group short name
      | NotExistingShortName | 15N1t4m6ojs6wqE7cfuCjiRMnY0d5hl6r5zK5EPi4reM | 400  | IMPORT_PROCESSING_ERROR | Invalid partnersGroup with shortName=[NotExistingShortName]                                                                          | null                                                    |
#  Wrong file format
      | UnitedSuppliers      | 1_rfJw9re4IwrheLaE5ZsiTbIF02KxjQv6TXUUgb2GTg | 400  | IMPORT_PROCESSING_ERROR | Cannot download file, reason: https://docs.google.com/spreadsheets/d/1_rfJw9re4IwrheLaE5ZsiTbIF02KxjQv6TXUUgb2GTg/export?format=xlsx | null                                                    |
#  Wrong columns name
      | UnitedSuppliers      | 1mYRWiKhXmrplEp4LCoxtrpmlSSLrg5vaRUO-suEwxTE | 400  | IMPORT_PROCESSING_ERROR | Columns header names should be: Name, ID                                                                                             | null                                                    |
#  Duplicated ExternalId in file(if some id exists in db and in file it is skipped during processing)
      | UnitedSuppliers      | 1xKnZ7K-4OBqEt1ePsZHoOZuI2j3V3ZkkMg_GZ0qTaDQ | 400  | IMPORT_PROCESSING_ERROR | Duplication of ID: ID11111111                                                                                                        | null                                                    |
#  Missing name in file
      | UnitedSuppliers      | 1S8XYWIc_HUvCU_s4mJxCJ6gWBo9fqaMoaSjLkAF49F4 | 400  | IMPORT_PROCESSING_ERROR | Errors when validation partners                                                                                                      | {3=Row: 3: Both values (name, id) needs to be provided} |
#  Missing id in file
      | UnitedSuppliers      | 1G3bVfSAQW6FcH0E_w3W4uYPQPfE5_K0EknRcF17n3Po | 400  | IMPORT_PROCESSING_ERROR | Errors when validation partners                                                                                                      | {3=Row: 3: Both values (name, id) needs to be provided} |
#  Duplicated name is not considered because unique shortName is created if partnerName exists in database



#  @DEBUG
#  Scenario Outline: Partners bulk upload - correct bulk upload
#    When File '<googleDocId>' with partners list will be processed for partners group '<groupShortName>', '200'
#
#@MI
#    Examples:
#      | groupShortName  | googleDocId                                  |
#      | UnitedSuppliers | 1nGE7FNZM4gEF-pTXEzch-e_7zq6PflZOy7h-l1uam80 |
##      | UnitedSuppliers | 1N4ve8v_Evu3e4K0BBuOa1WbbhOqrSIOxXDQtymeR82Q |
