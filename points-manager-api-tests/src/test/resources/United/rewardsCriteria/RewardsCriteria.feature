Feature: UNITED - reward criteria
  As platform admin
  I want to be able to load rewards criteria into system
  To be able to use them later to process retailer transaction and award them with point according to rewards criteria

  #Assumption: file will be static with set of known data. File will be used for processing and criteria creation validated. After each test criteria will be removed.
  @unitedRewardsCriteria  @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest
  @Regression
  Scenario Outline: Rewards criteria bulk upload - correct bulk upload
    Given Points Manager API is responding to requests
    When File '<googleDocId>' with rewards criteria list will be processed '200'
    Then Rewards criteria will be properly saved in points manager
    And Tag will be created for created reward criteria

  @qa @stag
    Examples:
      | googleDocId                                  |
      | 1gFFoCsfi8QR4zNCfkkE8BtxbFu57VHGP15keDmK3y90 |
#      #manual
#      | 1S-Z2L5GSVfwQXVWZB8CdVBLCI0GpzJGvhBZQHPK2fYM |



  @unitedRewardsCriteria @NegativeCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest
  @Regression
  Scenario Outline: Partners bulk upload - error validation
    Given Points Manager API is responding to requests
    When File '<googleDocId>' with rewards criteria list will be processed '<code>'
    Then Correct error message will be returned for file upload '<error>', '<message>', '<items>'
    And All added during bulk upload criteria will be rolled back

  @qa @stag
    Examples:
      | googleDocId                                  | code | error                   | message                                                                                                                                                | items                                                                                |
      #  Wrong file format
      | 1_rfJw9re4IwrheLaE5ZsiTbIF02KxjQv6TXUUgb2GTg | 400  | IMPORT_PROCESSING_ERROR | Cannot download file, reason: https://docs.google.com/spreadsheets/d/1_rfJw9re4IwrheLaE5ZsiTbIF02KxjQv6TXUUgb2GTg/export?format=xlsx                   | null                                                                                 |
      #  Wrong columns name
      | 11nmyIzCU2ztF1iNh6DcE2aque2arDlnUQCVeojQvrws | 400  | IMPORT_PROCESSING_ERROR | Columns header names should be: [partnerExternalId, productId, productDescription, epointsAmount, purchaseType, awardPromotion, startDate, expiryDate] | null                                                                                 |
      #  Missing partnerExternalId
      | 1B5CAiHukz4nWTAedttlIH74RzLwDdLFflp3ysrzodrM | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 1: value in column partnerExternalId needs to be provided}  |
      #  Missing productId
      | 1fRZ92a8yuhC29cpbkZVnf_C3C17EZwMjZZ7HczjkkQY | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 2: value in column productId needs to be provided}          |
      #  Missing epointsAmount
      | 1vwkdNpfqMOv5OMY7VJAL17HGPVX7CM2VlDV6JUwWlQ0 | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 4: value in column epointsAmount needs to be provided}      |
      #  Missing startDate
      | 1vWTAKskk1WbBxHQ9g8Lknizf7WI3bECBufJ7UqgCuss | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 7: value in column startDate needs to be provided}          |
      #  Missing expiryDate
      | 1s9_bvAttu4yffMdDVaMQjKxWab5YJqq5Wj0VqU_l46k | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 8: value in column expiryDate needs to be provided}         |
      #  Missing description
      | 1GB1WaOsveqDxN0xePQo9bTLq0UClxCQRVm61AIddKiw | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 3: value in column productDescription needs to be provided} |
      #  Not existing partnerId
      | 1iIXPRS_qPkFOMnWjnDgJY4_viiEbT3PhU2ut9ta2n8o | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2: Partner with externalId: 2147483647 does not exist}                       |
      #  Purchase type not in {CASH, DELIVERY, CREDIT}
      | 1oRPPWMLgZHcpENNqD_JGiClmrxG7sB9T0iGxRHw6LDI | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 5: CASHIE is not one of [CREDIT, CASH, DELIVERY]}           |
      #  Epoints amount in wrong format
      | 15KUIr2a7YmfscxvlXZ_o5CuR7vcx-Frtk89AmdCbBe4 | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 4: Cannot get a numeric value from a text cell}             |
      #  Award promotion in wrong format
      | 14o7ulnYdPmgpCy5ZUyF1QP_s04KGSl0pnwTwVXmBOXM | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2, Column index: 6: Cannot get a boolean value from a numeric cell}          |
      #  start date > expiry date
      | 1bqPlntkEDRtgQm1uNqP7caP15U1yml-1mj2eA276XoQ | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2: Expiry date should be greater than start date.}                           |
      #  too long description
      | 1sBLBe0wbHlWg8vxrlQsfHXMhQHrWLe4ssUZ4itT452M | 400  | IMPORT_PROCESSING_ERROR | Errors when validation reward criteria                                                                                                                 | {0=Row: 2: Column index: 3 length must be between 0 and 255}                         |
#  Case for wrong description format skipped, who will enter number into description field, properly handled by the way
#  Case for wrong date format skipped, properly handled by the way




#  @DEBUG
#  @createUnitedPartnersSetBeforeTest
#  @deleteCreatedPartnersAfterTest
#  Scenario Outline: Partners bulk upload - correct bulk upload
#    When File '<googleDocId>' with rewards criteria list will be processed '200'
#
#    Examples:
#      | googleDocId                                  |
#      | 1n3AYYjs5g6DIqzW7rJIv46cMxM3YhnXYLMcccBL2ilI |


