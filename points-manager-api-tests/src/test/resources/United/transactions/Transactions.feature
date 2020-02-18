Feature: UNITED - transactions processing
  As points-manager client
  I want to be able to process transactions into system
  To be able to award users with point according to rewards criteria

  #Assumption: file will be static with set of known data. File will be used for processing and criteria creation validated. After each test criteria will be removed.
  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - wildcard for purchaseType and awardPromotion & validate reporting records creation
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/7jml4xr204ey1mv/Transaction1?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 3 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 4 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 200   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 5 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 6 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 7 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 8 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 800     | 0        | 0        | 0     |
    And Reports table was properly filled for UNITED retailer

  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - wildcard for purchaseType and awardPromotion - empty lines/gaps between transactions
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/ty7yw52mm6hytd9/Transaction1EmptyLines?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId              | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 3  | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 4  | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 5  | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 200   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 7  | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 8  | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 11 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 12 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 800     | 0        | 0        | 0     |

  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - wildcard for purchaseType and awardPromotion - non integer values in qty should be round down to integer - 0 qty transactions should be skipped
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/dqj3tj6ft84b3xx/Transaction1ZeroQtyNonIntegerQty?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 4 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 200   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 5 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 7 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 8 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 600     | 0        | 0        | 0     |


  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - Processing same transactions file more than once not make duplications
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/7jml4xr204ey1mv/Transaction1?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    And File 'https://www.dropbox.com/s/7jml4xr204ey1mv/Transaction1?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 3 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 4 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 200   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 5 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 6 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 7 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 8 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 800     | 0        | 0        | 0     |


  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - wildcard for purchaseType and awardPromotion = true/false
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/pkfbqvlfk54h2md/Transaction2?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-17'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                        | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-17 - $CRITERIA_ID - 1 - 2 | TEST#03 RC: wildcard for purchaseType and awardPromotion = false | 2017-05-31            | UNITED       | 202   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-17 - $CRITERIA_ID - 1 - 3 | TEST#03 RC: wildcard for purchaseType and awardPromotion = false | 2017-05-31            | UNITED       | 202   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-17 - $CRITERIA_ID - 1 - 4 | TEST#03 RC: wildcard for purchaseType and awardPromotion = false | 2017-05-31            | UNITED       | 404   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-17 - $CRITERIA_ID - 1 - 5 | TEST#03 RC: wildcard for purchaseType and awardPromotion = false | 2017-05-31            | UNITED       | 202   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-17 - $CRITERIA_ID - 1 - 6 | TEST#02 RC: wildcard for purchaseType and awardPromotion = true  | 2017-05-31            | UNITED       | 201   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-17 - $CRITERIA_ID - 1 - 7 | TEST#02 RC: wildcard for purchaseType and awardPromotion = true  | 2017-05-31            | UNITED       | 201   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-17 - $CRITERIA_ID - 1 - 8 | TEST#02 RC: wildcard for purchaseType and awardPromotion = true  | 2017-05-31            | UNITED       | 201   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 1613    | 0        | 0        | 0     |


  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - wildcard for awardPromotion and purchaseType = CASH/CREDIT/DELIVERY
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/jmjbvkymo3da86c/Transaction3?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-20'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                           | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-20 - $CRITERIA_ID - 1 - 2 | TEST#04 RC: wildcard for awardPromotion and purchaseType = CASH     | 2017-06-03            | UNITED       | 301   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-20 - $CRITERIA_ID - 1 - 3 | TEST#04 RC: wildcard for awardPromotion and purchaseType = CASH     | 2017-06-03            | UNITED       | 301   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-20 - $CRITERIA_ID - 1 - 4 | TEST#05 RC: wildcard for awardPromotion and purchaseType = CREDIT   | 2017-06-03            | UNITED       | 604   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-20 - $CRITERIA_ID - 1 - 5 | TEST#06 RC: wildcard for awardPromotion and purchaseType = DELIVERY | 2017-06-03            | UNITED       | 303   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-20 - $CRITERIA_ID - 1 - 6 | TEST#04 RC: wildcard for awardPromotion and purchaseType = CASH     | 2017-06-03            | UNITED       | 301   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-20 - $CRITERIA_ID - 1 - 7 | TEST#05 RC: wildcard for awardPromotion and purchaseType = CREDIT   | 2017-06-03            | UNITED       | 302   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-20 - $CRITERIA_ID - 1 - 8 | TEST#06 RC: wildcard for awardPromotion and purchaseType = DELIVERY | 2017-06-03            | UNITED       | 303   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 2415    | 0        | 0        | 0     |


  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - awardPromotion = true/false and purchaseType = CASH/CREDIT/DELIVERY
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/mx78e1osimsksw8/Transaction4?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-23'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                      | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-23 - $CRITERIA_ID - 1 - 2 | TEST#10 RC: awardPromotion = false and purchaseType = CASH     | 2017-06-06            | UNITED       | 404   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-23 - $CRITERIA_ID - 1 - 3 | TEST#10 RC: awardPromotion = false and purchaseType = CASH     | 2017-06-06            | UNITED       | 404   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-23 - $CRITERIA_ID - 1 - 4 | TEST#11 RC: awardPromotion = false and purchaseType = CREDIT   | 2017-06-06            | UNITED       | 810   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-23 - $CRITERIA_ID - 1 - 5 | TEST#12 RC: awardPromotion = false and purchaseType = DELIVERY | 2017-06-06            | UNITED       | 406   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-23 - $CRITERIA_ID - 1 - 6 | TEST#07 RC: awardPromotion = true and purchaseType = CASH      | 2017-06-06            | UNITED       | 401   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-23 - $CRITERIA_ID - 1 - 7 | TEST#08 RC: awardPromotion = true and purchaseType = CREDIT    | 2017-06-06            | UNITED       | 402   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-23 - $CRITERIA_ID - 1 - 8 | TEST#09 RC: awardPromotion = true and purchaseType = DELIVERY  | 2017-06-06            | UNITED       | 403   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 3230    | 0        | 0        | 0     |


  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - check if startDate and expiryDate ranges are properly handled
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/ltnk977i5a3lvex/Transaction5?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-25'
    When File 'https://www.dropbox.com/s/7hvpl2m1h8pt66z/Transaction5_2?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-26'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                                 | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-25 - $CRITERIA_ID - 1 - 2 | TEST#13 RC: check if startDate and expiryDate ranges are properly handled | 2017-06-08            | UNITED       | 500   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#14 RC: check if startDate and expiryDate ranges are properly handled | 2017-06-09            | UNITED       | 1110  | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-25 - $CRITERIA_ID - 1 - 3 | TEST#13 RC: check if startDate and expiryDate ranges are properly handled | 2017-06-08            | UNITED       | 500   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 2110    | 0        | 0        | 0     |


  @unitedTransactions @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Matching transaction with proper criteria - check if transaction products are recognized
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File 'https://www.dropbox.com/s/s7smeixj8gyuvs0/Transaction6?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-30'
    Then Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                                 | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-30 - $CRITERIA_ID - 1 - 2 | TEST#17 RC: check if transaction will be ignored if no criteria match (1) | 2017-06-13            | UNITED       | 5000  | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-30 - $CRITERIA_ID - 1 - 3 | TEST#18 RC: check if transaction will be ignored if no criteria match (2) | 2017-06-13            | UNITED       | 6000  | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 11000   | 0        | 0        | 0     |


  @unitedTransactions @NegativeCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  Scenario Outline: Matching transaction with proper criteria - Errors "validation"
    Given Points Manager API is responding to requests
    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
    When File '<transactionsUrl>' for client 'UNITED' with transactions will be processed '200' for date '<transactionDate>'
    Then Points record is created:
      | status | partnerExternalId | activityInfo | externalTransactionId | rewardCriteriaDescription | onBehalfOfId | delta | balance |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 0       | 0        | 0        | 0     |

  @qa
    Examples:
      | transactionsUrl                                                                  | transactionDate |
      # incorrect EOD number
      | https://www.dropbox.com/s/it3qs1j8id7rqgb/TransactionsInvalidEOD?dl=1            | 2017-05-15      |
      # transaction will be ignored if no criteria match
      | https://www.dropbox.com/s/uny9aigvt8lwtrl/TransactionNoMatchingCriteria?dl=1     | 2017-04-23      |
      # Invalid payMethod
      | https://www.dropbox.com/s/wqmlpiat5s968jz/TransactionImproperPayMethod?dl=1      | 2017-05-15      |
      # transaction will be ignored -> 1 transaction date is not from yesterday
      | https://www.dropbox.com/s/fm7kb3pfdjkt4fn/Transaction1OldTransactions1stRow?dl=1 | null            |
      # transaction will be ignored -> 1 transaction date is not from provided date
      | https://www.dropbox.com/s/fm7kb3pfdjkt4fn/Transaction1OldTransactions1stRow?dl=1 | 2017-05-23      |
      # transaction will be ignored -> 2nd transaction date is not from provided date
      | https://www.dropbox.com/s/m13q50t3r9xegou/Transaction1OldTransactions2ndRow?dl=1 | 2017-05-15      |
      # transaction will be ignored -> empty header
      | https://www.dropbox.com/s/bc48d3sw5h8t5r2/EmptyHeader?dl=1                       | 2017-05-15      |
#      # transaction will be ignored -> 2nd transaction date is not from yesterday - manual, first row date should be proper
#      | https://www.dropbox.com/s/n1p6w5mcrxs49y2/ManualChangeFirstDateToYesterday?dl=1 | null            |


#  @MANUAL
#  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
#  Scenario Outline: Matching transaction with proper criteria - validation for customer id 1-99999
#    Given Points Manager API is responding to requests
#    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
#    When File '<transactionsUrl>' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
#    Then Points record is not created for user with united externalId '<externalId>'
#
#    Examples:
#      | transactionsUrl                                                               | externalId |
#      # invalid customerId (integer) user in dynamo
#      | https://www.dropbox.com/s/lb7bvnhjmss8tpf/TransactionsInvalidCustomerId?dl=1  | 999991     |
#      # invalid customerId (String)
#      | https://www.dropbox.com/s/lshptjkayif77ia/TransactionsInvalidCustomerId2?dl=1 | ADA_DSDS   |
#
#  #mostly for test mail logs
#  @MANUAL
#  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
#  Scenario Outline: Matching transaction with proper criteria - validation for customer id 1-99999
#    Given Points Manager API is responding to requests
#    And File '1OZDjRMaCzrNGTa3f6em4_4VI_9LsJlLkZ2y458khy-E' with rewards criteria list will be processed '200'
#    When File '<transactionsUrl>' for client 'UNITED' with transactions will be processed '200' for date '<transactionDate>'
#    Then Points records are not created
#
#    Examples:
#      | transactionsUrl                                                                                                           | transactionDate |
#      # fail which requires permissions (signed out dropbox user will get 403)
#      | https://dl.dropboxusercontent.com/content_link/JAUEirj9pyigFPX1W1jvY27sLXM9J92iSTi5z2quOEvOBVyyI8Ni7psSZGSFpAbJ/file?dl=1 | 2017-05-15      |
#      # invalid file (picture)
#      | https://www.dropbox.com/s/kjzpssedq1o0bzh/picture?dl=1                                                                    | 2017-05-15      |
#
#
#  Scenario Outline: Store transaction file in s3 before processing
#    When The '<file correctness>' transaction file is pulled to be processed
#    Then Transaction file is saved in s3 transaction-file-united-{env} collection
#    And Saved file name contains creation date in format yyy-mm-dd
#    And Saved file name contains creation timestamp
#
#    Examples:
#      | file correctness  |
#      | correct           |
#      | containing errors |

  #REFUND

    #Assumption: file will be static with set of known data. File will be used for processing and criteria creation validated. After each test criteria will be removed.

  @unitedTransactionsRefunds @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Refund transaction - criteria found for 1 whole transaction refund
    Given Points Manager API is responding to requests
    And File '1yn3iWAqsi4AIjnSMrn8f9TW1pC5e1RALmtLtkHzL2Ww' with rewards criteria list will be processed '200'
    And File 'https://www.dropbox.com/s/7jml4xr204ey1mv/Transaction1?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    And Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 9 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 100     | 0        | 0        | 0     |
    When File 'https://www.dropbox.com/s/dwvy16ljl78ht4l/Transaction1RefundOneTransaction?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-16'
    Then Points record is created:
      | status    | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | CANCELLED | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 9 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
      | DECLINED  | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-16 - $CRITERIA_ID - 1 - 2 | null                                                     | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 0       | 0        | 0        | 0     |

  @unitedTransactionsRefunds @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Refund transaction - refund transaction older than 14 days (currently possible it's should not happened because should be in CONFIRMED state)
    Given Points Manager API is responding to requests
    And File '1yn3iWAqsi4AIjnSMrn8f9TW1pC5e1RALmtLtkHzL2Ww' with rewards criteria list will be processed '200'
    And File 'https://www.dropbox.com/s/7jml4xr204ey1mv/Transaction1?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    And Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 9 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 100     | 0        | 0        | 0     |
    When File 'https://www.dropbox.com/s/ktu596vdf4ctc1v/Transaction1RefundOneTransactionTooLate?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-30'
    Then Points record is created:
      | status    | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | DECLINED  | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-30 - $CRITERIA_ID - 1 - 2 | null                                                     | 2017-05-29            | UNITED       | 100   | 0       |
      | CANCELLED | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 9 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 0       | 0        | 0        | 0     |

  @unitedTransactionsRefunds @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Refund transaction - criteria found, number of refunded products is greater then actual number within last 14 days
    Given Points Manager API is responding to requests
    And File '1yn3iWAqsi4AIjnSMrn8f9TW1pC5e1RALmtLtkHzL2Ww' with rewards criteria list will be processed '200'
    And File 'https://www.dropbox.com/s/qsez8t32pe5gce1/Transaction1ForRefunds?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-15'
    And Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 5 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 500   | 0       |
      | PENDING | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 3 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 600     | 0        | 0        | 0     |
    When File 'https://www.dropbox.com/s/o850bu68946zj30/Transaction1RefundManyTransaction?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-16'
    Then Points record is created:
      | status    | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | DECLINED  | SupplierExternalId_sup_1 | 5 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-16 - null - 1 - 2         | null                                                     | 2017-05-29            | UNITED       | 500   | 0       |
      | DECLINED  | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-16 - null - 1 - 2         | null                                                     | 2017-05-29            | UNITED       | 100   | 0       |
      | CANCELLED | SupplierExternalId_sup_1 | 5 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 500   | 0       |
      | CANCELLED | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-15 - $CRITERIA_ID - 1 - 3 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-05-29            | UNITED       | 100   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 0       | 0        | 0        | 0     |

  @unitedTransactionsRefunds @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Refund transaction - criteria found, partial transaction refund
    Given Points Manager API is responding to requests
    And File '1yn3iWAqsi4AIjnSMrn8f9TW1pC5e1RALmtLtkHzL2Ww' with rewards criteria list will be processed '200'
    And File 'https://www.dropbox.com/s/v1462apv7naj5b3/Transaction5_2ForRefunds?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-26'
    And Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 200   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 200     | 0        | 0        | 0     |
    When File 'https://www.dropbox.com/s/m4wp034erlllgdu/Transaction5_2RefundPartOfOneTransaction?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-27'
    Then Points record is created:
      | status    | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING   | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 100   | 0       |
      | DECLINED  | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-27 - null - 1 - 2         | null                                                     | 2017-06-09            | UNITED       | 100   | 0       |
      | CANCELLED | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 200   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 100     | 0        | 0        | 0     |


#    bug NBO-9445
#  @PmBuild @PmRegression @Refunds @PositiveCase
#  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTestt@resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
#  Scenario: Refund transaction - refund transaction with different criteria than it was bought
#    Given Points Manager API is responding to requests
#    And File '1n3vDlRZcS8Po_noOe5HPCvbJ9tOyl6vVWCxWwfGE0YI' with rewards criteria list will be processed '200'
#    And File '1yn3iWAqsi4AIjnSMrn8f9TW1pC5e1RALmtLtkHzL2Ww' with rewards criteria list will be processed '200'
#    And File 'https://www.dropbox.com/s/v1462apv7naj5b3/Transaction5_2ForRefunds?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-26'
#    And Points record is created:
#      | status  | tagId | partnerId | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
#      | PENDING | 12    | 34        | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 200   | 0       |
#    And Account for UNITED is updated:
#      | confirmed | pending | redeemed | declined | spent |
#      | 0         | 200     | 0        | 0        | 0     |
#    When File 'https://www.dropbox.com/s/aok8npb8mdtpg0x/Transaction5_2RefundPartOfOneTransactionForDifferentRewardCriteria?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-06-01'
#    Then Points record is created:
#      | status    | tagId | partnerId | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
#      | PENDING   | 12    | 34        | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 100   | 0       |
#      | DECLINED  | 12    | 34        | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-27 - null - 1 - 2         | null                                                     | 2017-06-09            | UNITED       | 100   | 0       |
#      | CANCELLED | 12    | 34        | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - null - 1 - 2         | null                                                     | 2017-06-09            | UNITED       | 200   | 0       |
#    And Account for UNITED is updated:
#      | confirmed | pending | redeemed | declined | spent |
#      | 0         | 50      | 0        | 0        | 0     |


  @unitedTransactionsRefunds @PositiveCase
  @createUnitedPartnersSetBeforeTest @deleteRewardsCriteriaDataAfterTest @resetUnitedAccountBalanceBeforeTest @deleteAllExternalSupplierDataAfterTest
  @Regression
  @qa
  Scenario: Refund transaction - criteria found within last 14 days because no current rewards criteria available
    Given Points Manager API is responding to requests
    And File '1yn3iWAqsi4AIjnSMrn8f9TW1pC5e1RALmtLtkHzL2Ww' with rewards criteria list will be processed '200'
    And File 'https://www.dropbox.com/s/v1462apv7naj5b3/Transaction5_2ForRefunds?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-26'
    And Points record is created:
      | status  | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 200   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 200     | 0        | 0        | 0     |
    When File 'https://www.dropbox.com/s/aok8npb8mdtpg0x/Transaction5_2RefundPartOfOneTransactionForDifferentRewardCriteria?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-06-01'
    Then Points record is created:
      | status    | partnerExternalId        | activityInfo                      | externalTransactionId             | rewardCriteriaDescription                                | autoConfirmDateString | onBehalfOfId | delta | balance |
      | PENDING   | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 100   | 0       |
      | DECLINED  | SupplierExternalId_sup_1 | 1 X DIAMOND WHITE CIDER  1.2500ML | 2017-06-01 - null - 1 - 2         | null                                                     | 2017-06-09            | UNITED       | 100   | 0       |
      | CANCELLED | SupplierExternalId_sup_1 | 2 X DIAMOND WHITE CIDER  1.2500ML | 2017-05-26 - $CRITERIA_ID - 1 - 2 | TEST#01 RC: wildcard for purchaseType and awardPromotion | 2017-06-09            | UNITED       | 200   | 0       |
    And Account for UNITED is updated:
      | confirmed | pending | redeemed | declined | spent |
      | 0         | 100     | 0        | 0        | 0     |


  Scenario: UNITED Requests
    Given Points Manager API is responding to requests
#    When File '1Cnc5Qj97CqX_-tJYc34oauYIQSnJl5wij4w7jp4uMJw' with partners list will be processed for partners group 'UnitedSuppliers', '200'
#    When File '1S-Z2L5GSVfwQXVWZB8CdVBLCI0GpzJGvhBZQHPK2fYM' with rewards criteria list will be processed '200'
#    When File 'http://report01.uniteduk.co.uk/mh_get_previous_days_transactions.php?date=2017-11-09' for client 'UNITED' with transactions will be processed '200' for date '2017-11-09'
#    When File 'https://www.dropbox.com/s/c6yjk50vsy47o5t/TEST?dl=1' for client 'UNITED' with transactions will be processed '200' for date '2017-05-25'
    When File 'http://report01.uniteduk.co.uk/mh_get_previous_days_transactions.php?date=2017-11-27' for client 'UNITED' with transactions will be processed '200' for date '2017-11-27'


