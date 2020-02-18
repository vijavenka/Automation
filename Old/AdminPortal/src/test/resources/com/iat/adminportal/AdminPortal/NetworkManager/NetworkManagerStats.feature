Feature: Network manager / stats

  As a administrator
  I need a page to go to
  Where I can search for stats and information about traffic which go out from ePoints web page

  Scenario:ADMIN MANAGER - cross channel sales reporting view in network manager (RD-3019) - columns availability
    Given Admin portal is opened
    Given Affiliate window stats page is opened
    When User add P1 column
    Then P1 colum should be visible
    When User add P2 column
    Then P2 colum should be visible
    When User add P3 column
    Then P3 colum should be visible
    When User add P4 column
    Then P4 colum should be visible
    When User remove P1 column
    Then P1 column should be removed
    When User remove P2 column
    Then P2 column should be removed
    When User remove P3 column
    Then P3 column should be removed
    When User remove P4 column
    Then P4 column should be removed

  Scenario:ADMIN MANAGER - cross channel sales reporting view in network manager (RD-3019) - columns content correctness
    Given Admin portal is opened
    Given Affiliate window stats page is opened
    Given Data in clickout database is modified
    When User add P1 column
    When User add P2 column
    When User add P3 column
    When User add P4 column
    Then User can see that P columns data is correct according to database