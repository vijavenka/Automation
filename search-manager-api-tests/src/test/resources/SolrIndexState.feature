Feature: SOLR Index - state of products
  As a platform administrator
  I want to constantly monitor state of my SOLR Index
  So that I will be notified when number of products is too low

  @Production @Regression @SolrIndex
  Scenario Outline: SOLR Index - Products Count
    Given Solr '<index>' is responding to queries
    When Query for products count is performed
    Then Number of current available products should be higher than agreed '<threshold>'

    Examples:
      | index | threshold |
      #cloud 2
      | 8991  | 1000      |
      | 8992  | 1000      |
#      #cloud 1
#      | 8983  | 1000      |
#      | 8984  | 1000      |
#      | 8985  | 1000      |
#      | 8986  | 1000      |
