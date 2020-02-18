Feature: Epoints API: Points -> search/vouchers
  As an epoints user I want to have possibility to search vouchers
  To be able to find vouchers which I'am interested in

  @Regression @PositiveCase @vouchersFacets
  @qa @stag
  Scenario: Points vouchers - correctness of voucher facets and search response
    Given Epoints API is responding to requests
    When Voucher facets call will be requested
    Then Its response match voucher facets response schema
    And As well as voucher search request

  @Regression @PositiveCase @vouchersSearch
  @qa @stag
  Scenario Outline: Points vouchers - voucher search response for different parameters
    Given Epoints API is responding to requests
    When Voucher search call will be requested with parameters '<page>', '<pageSize>', '<sort>', '<filterMerchant>', '<filterDepartment>', '<filterCategory>'
    Then Response content is correct according to request parameters '<page>', '<pageSize>', '<sort>', '<filterMerchant>', '<filterDepartment>', '<filterCategory>'

    Examples:
      | page | pageSize | sort             | filterMerchant | filterDepartment | filterCategory |
      | 1    | 5        | null             | null           | null             | null           |
      | 0    | 10       | expiryDate\|asc  | null           | null             | null           |
      | 0    | 10       | expiryDate\|desc | null           | null             | null           |
      | 0    | 999      | null             | random         | null             | null           |
      | 0    | 999      | null             | null           | random           | null           |
      | 0    | 999      | null             | null           | null             | random         |