Feature: Epoints API: Rewards -> search
  As ePoints user
  I want to be able to search within redemption items products

#  tests failing because of NBO-9315

  @Regression @PositiveCase @EpointsRanges
  Scenario Outline: Epoints ranges all - epoints ranges all for united/epoints
    Given Epoints API is responding to requests
    When Epoints ranges all will be requested for '<businessId>'
    Then Correct set of filters will be returned '<businessId>'

  @qa @stag
    Examples:
      | businessId |
      | null       |
      | united     |


  @Regression @PositiveCase @EpointsRanges
  @prod
  Scenario: Epoints ranges all - ranges count differences between business
    Given Epoints API is responding to requests
    When Epoints ranges call call will be requested for united and epoints
    Then Items count should be different between them


  @Regression @PositiveCase @Facets
  Scenario Outline: Facets - check facets returned for different business
    Given Epoints API is responding to requests
    When Facets will be requested for '<businessId>', '<version>'
    Then Correct set of facets will be returned

  @qa @stag
    Examples:
      | businessId | version |
      | null       | v1      |
      | united     | v1      |
      | null       | v2      |
      | united     | v2      |

  @Regression @PositiveCase @Facets
  Scenario Outline: Facets - check facets returned for different business - multival facets call
    Given Epoints API is responding to requests
    When Multival facets will be requested for '<businessId>', '<version>' for specified filter option '<multivalFilterType>'
    Then Correct facet options '<multivalFilterType>' value are returned according to solr for business '<businessId>'

  @qa @stag
    Examples:
      | businessId | version | multivalFilterType |

      | null       | v2      | s_brandName        |
      | null       | v2      | s_type             |
      | null       | v2      | s_actor_multiVal   |
      | null       | v2      | s_artist_multiVal  |
      | null       | v2      | s_author_multiVal  |

      | united     | v2      | s_brandName        |
      | united     | v2      | s_type             |
      | united     | v2      | s_actor_multiVal   |
      | united     | v2      | s_artist_multiVal  |
      | united     | v2      | s_author_multiVal  |


  @Regression @PositiveCase @Facets
  Scenario Outline: Facets - check facets not returns epoints ranges if there are no results
    Given Epoints API is responding to requests
    When Facets request for empty results is made '<businessId>', '<version>' for specified filter option '<multivalFilterType>'
    Then Facets response for empty results returns proper content

  @qa @stag
    Examples:
      | businessId | version | multivalFilterType       |
      | null       | v2      | s_departmentSeoSlug:bupa |


  @Regression @PositiveCase @RedemptionItems
  Scenario Outline: Epoints redemption items - epoints redemption items returned for different businesses
    Given Epoints API is responding to requests
    When Epoints redemption items will be requested for '<businessId>' and filter '<filterSelected>'
    Then Redemption items only from business '<businessId>' and selected filters '<filterSelected>' will be returned

  @qa @stag
    Examples:
      | businessId | filterSelected                               |
      | null       | null                                         |
      | null       | s_departmentSeoSlug                          |
      | null       | i_epointsToPurchase                          |
      | null       | s_brandName                                  |
      | null       | s_type                                       |
      | null       | s_actor_multiVal                             |
      | null       | s_artist_multiVal                            |
      | null       | s_author_multiVal                            |
      | null       | s_categoryFromFeedExtractedSeoSlugs_multiVal |
#
      | united     | null                                         |
      | united     | s_departmentSeoSlug                          |
      | united     | i_epointsToPurchase                          |
      | united     | s_brandName                                  |
      | united     | s_type                                       |
      | united     | s_actor_multiVal                             |
      | united     | s_artist_multiVal                            |
      | united     | s_author_multiVal                            |
      | united     | s_categoryFromFeedExtractedSeoSlugs_multiVal |

  @Regression @PositiveCase @RedemptionItems
  Scenario Outline: Epoints redemption items - keyword search for business united/epoints
    Given Epoints API is responding to requests
    When Epoints redemption items will be requested for '<businessId>' with keyword '<keyword>'
    Then Redemption items only from business '<businessId>' and used keyword '<keyword>' will be returned

  @qa @stag
    Examples:
      | businessId | keyword |
      | null       | toy     |
      | united     | toy     |


  @Regression @PositiveCase @RedemptionItems
  Scenario Outline: Similar redemption items search query
    Given Epoints API is responding to requests
    When Similar products will be requested for specific item from business '<businessId>'
    Then Similar products will be returned according to specified '<businessId>'

  @qa @stag
    Examples:
      | businessId |
      | null       |
      | united     |