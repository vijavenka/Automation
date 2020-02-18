Feature: Partner - details listing
  As an API client
  I want to be able to pull list of available Partners
  So that I could review specific Partners details

  #1.Points Manager exposes controller with set of methods to pull list of available Partners
  #2.Partners are returned regardless of the state (active/not active)
  #3.ShortName is used as unique identifier for Partners and transactions
  #4.Default request has 25 items in response, but can be parametrized

  @Partners @PartnersListing
  @Regression
  @qa @stag @prod
  Scenario: Get default list of Partners
    Given Points Manager API is responding to requests
    When Default call for list of Partners is made
    Then List of partners is returned regardless of the active status

  @Partners @PartnersListing
  @Regression
  Scenario Outline: Pull specific Partner info by shortName
    Given Points Manager API is responding to requests
    When Partner details call is made with partner shortName'<PartnerShortName>'
    Then Partner with provided shortName '<PartnerShortName>' is in results

  @qa @stag
    Examples:
      | PartnerShortName |
      | ews_test_chain_2 |


  @qa @stag  @prod
    Examples:
      | PartnerShortName |
      | ePoints.com      |
      | bdl              |
      | NOT_EXIST        |

  @Partners @PartnersListing
  @Regression
  Scenario Outline: Pull specific Partner info by name
    Given Points Manager API is responding to requests
    When Partner details call is made with partner name'<PartnerName>'
    Then Partner with provided name '<PartnerName>' is in results

  @qa @stag
    Examples:
      | PartnerName      |
      | EWS Test chain 2 |

  @qa @stag @prod
    Examples:
      | PartnerName |
      | ePoints     |
      | bdl         |
      | not_exist   |

  @Partners @PartnersListing
  @Regression
  Scenario Outline: Check if partners details count in results are changed after provide page and size params
    Given Points Manager API is responding to requests
    When Pull list of partners with page equals '<page>' and size equals '<size>'
    Then Partners count should be returned according provided parameters page '<page>' and size '<size>'

  @qa @stag @prod
    Examples:
      | page | size |
      | 0    | 1    |
      | 0    | 6    |
      | 3    | 2    |
      | 1    | 40   |

  @Partners @PartnersListing
  @Regression
  Scenario Outline: Pull specific Partner info by shortName and compare shortName with Name
    Given Points Manager API is responding to requests
    When Partner details call is made with partner shortName'<PartnerShortName>'
    Then Partner shortName '<PartnerShortName>' match partner Name '<PartnerName>'

  @qa @stag
    Examples:
      | PartnerShortName | PartnerName      |
      | ePoints.com      | ePoints          |
      | bdl              | bdl              |
      | ews_test_chain_2 | EWS Test chain 2 |