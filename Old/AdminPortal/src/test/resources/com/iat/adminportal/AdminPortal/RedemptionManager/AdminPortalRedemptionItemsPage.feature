Feature: Redemption manager / redemption items

  As a administrator
  I need a page to go to
  Where I can search and add redemption items to chosen buckets

  Scenario:REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567) - page content
    Given Admin portal is opened
    Given Redemption items page is opened
    When User look at redemption item page
    Then He can see set of searches
    And Redemption Items table

    #  bug here NBO-10437, after fix usage of points filter should be restored in When/Then steps
  Scenario Outline:REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567) - searches
    Given Admin portal is opened
    Given Redemption items page is opened
    When User use title search with '<Title>'
    Then Proper results will be displayed for given title '<Title>'
    When User use category search with '<Category>'
    Then Proper results will be displayed for given category '<Category>'
       #When User use description search with '<Description>'
       #Then Proper results will be displayed for given description '<Description>'
    When User use type search with '<Type>'
    Then Proper results will be displayed for given type '<Type>'
    When User use merchant search with '<Merchant>'
    Then Proper results will be displayed for given merchant '<Merchant>'
#    When User use points search with '<pointsLow>' '<pointsHigh>'
#    Then Proper results will be displayed for given points '<pointsLow>' '<pointsHigh>'

    Examples:
      | Title | Category       | Description | Type         | Merchant       | pointsLow | pointsHigh |
      | Rocky | Action Figures |             | Accent Chair | 365games.co.uk | 800       | 10000      |

#  bug here NBO-10437, after fix usage of points filter should be restored in When/Then steps
  Scenario Outline:REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567) - searches all
    Given Admin portal is opened
    Given Redemption items page is opened
    When User user all searches with '<Title>' '<Category>' '<Type>' '<Merchant>' '<pointsLow>' '<pointsHigh>'
    Then Proper results should be shown according to '<Title>' '<Category>' '<Type>' '<Merchant>' '<pointsLow>' '<pointsHigh>'

    Examples:
      | Title | Category       | Description | Type         | Merchant       | pointsLow | pointsHigh |
      | Rocky | Action Figures |             | Accent Chair | 365games.co.uk | 800       | 10000      |