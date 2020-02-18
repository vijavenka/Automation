Feature: Partner - transactions
  As platform admin
  I want to be able to see how much points Partner spent in current month
  To be able to use this value in invoices


  @Partners @NegativeCase @PartnerTransactions
  @Regression
  @qa @stag @prod
  Scenario: Try get partner transactions without providing partner accessKey
    Given Points Manager API is responding to requests
    When Partner transactions call is made with no partner data
    Then Partner transactions response for request with no partner data should be empty


  @Partners @PositiveCase @PartnerTransactions
  @Regression
  Scenario Outline: Get partner transactions for current month and check response contract
    Given Points Manager API is responding to requests
    When Partner transactions call is made with partner data '<PartnerApiKey>'
    Then Partner transactions response data is proper for partner '<PartnerName>'

  @qa @stag
    Examples:
      | PartnerName | PartnerApiKey |
      | ePoints     | accessKey     |
      | bdl         | bdl           |

  @prod
    Examples:
      | PartnerName | PartnerApiKey             |
      | ePoints     | xHNZaBGQtDmxTkrnI7NOfoXkz |
      | bdl         | cTuVVEoIAGk5Lir0quvyMPuJu |


  @Partners @NegativeCase @PartnerTransactions
  @Regression
  Scenario Outline: Get partner transactions for current month - check response header data for negative cases
    Given Points Manager API is responding to requests
    When Partner transactions call is made with partner data '<PartnerApiKey>','<expResponseCode>','<expErrorCode>','<expErrorMsg>' (negative cases)
    Then Partner transactions response is empty

  @qa @stag
    Examples:
      | PartnerShortName | PartnerApiKey   | Description | expResponseCode | expErrorCode          | expErrorMsg                               |
      | partner04        | PartnerAccKey04 | inactive    | 403             | PARTNER_IS_NOT_ACTIVE | Partner with accessKey=[%s] is not active |
      | NOT_EXIST        | bbb             | not exist   | 403             | INVALID_PARTNER       | There is no partner with accessKey=%s     |

  @prod
    Examples:
      | PartnerShortName | PartnerApiKey | Description | expResponseCode | expErrorCode          | expErrorMsg                               |
      | inactivetest     | inactive      | inactive    | 403             | PARTNER_IS_NOT_ACTIVE | Partner with accessKey=[%s] is not active |
      | NOT_EXIST        | bbb           | not exist   | 403             | INVALID_PARTNER       | There is no partner with accessKey=%s     |
