Feature: Member manager page

  As a administrator
  I need a page to go to
  Where I can search for epoints registered user and see all detail of they account


  Scenario:MEMBER MANAGER - add user details interface (RD-1966) -
    Given Admin portal is opened
    When User look at admin portal main page
    Then He can see member management tab
    When He click members tab
    Then He will see member manager main page
    And Member manager page has all needed elements

  Scenario Outline:MEMBER MANAGER - create personnel details tab (RD-2035) - by Email
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with email '<email>'
    And He want search using email
    And He press search button
    Then He can choose personal detail tab and results will be displayed
    And Results are correct comparing with DB email on personal details tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:MEMBER MANAGER - create personnel details tab (RD-2035) - by UUID
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with uuid of email '<email>'
    And He want search using uuid
    And He press search button
    Then He can choose personal detail tab and results will be displayed
    And Results are correct comparing with DB email on personal details tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:MEMBER MANAGER - create click out history tab (RD-2065) - by Email
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with email '<email>'
    And He want search using email
    And He press search button
    Then He can choose clickout history tab and results will be displayed
    And Results are correct comparing with DB email on clickout history tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:MEMBER MANAGER - create click out history tab (RD-2065) - by UUID
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with uuid of email '<email>'
    And He want search using uuid
    And He press search button
    Then He can choose clickout history tab and results will be displayed
    And Results are correct comparing with DB email on clickout history tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:MEMBER MANAGER - create redemption history tab (RD-2055) - by Email
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with email '<email>'
    And He want search using email
    And He press search button
    Then He can choose redemptions history tab and results will be displayed
    And Results are correct comparing with DB email on redemptions history tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:MEMBER MANAGER - create redemption history tab (RD-2055) - by UUID
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with uuid of email '<email>'
    And He want search using uuid
    And He press search button
    Then He can choose redemptions history tab and results will be displayed
    And Results are correct comparing with DB email on redemptions history tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:MEMBER MANAGER - create points history tab (RD-2045) - by Email
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with email '<email>'
    And He want search using email
    And He press search button
    Then He can choose points history tab and results will be displayed
    And Results are correct comparing with DB email on points history tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:MEMBER MANAGER - create points history tab (RD-2045) - by UUID
    Given Admin portal is opened
    Given Member manager page is opened
    When User will use search with uuid of email '<email>'
    And He want search using uuid
    And He press search button
    Then He can choose points history tab and results will be displayed
    And Results are correct comparing with DB email on points history tab '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |

  Scenario Outline:EPOINTS - add deactivate user option to member manager (NBO-1) -
    Given Admin portal is opened
    Given Member manager page is opened
    Given User will use search with email '<email>'
    And He want search using email
    And He press search button
    When User check user deactivated checkbox for email '<email>'
    Then Active flag will be set to 'false' in points manager for email '<email>'
    When User uncheck user deactivated checkbox
    Then Active flag will be set to 'true' in points manager for email '<email>'

    Examples:
      | email              |
      | emailfortest@wp.pl |