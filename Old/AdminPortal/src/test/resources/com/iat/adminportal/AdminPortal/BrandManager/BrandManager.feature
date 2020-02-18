Feature: Brand manager page

  As a user
  I need a page to go to
  Where I can see all brands and manage them

   #1
  Scenario Outline:BRAND MANAGER - extend search functionality to include synonyms (RD-1549).
    Given Admin portal is opened
    Given Brand manager is opened
    And Synonyms column is available
    When User type phrase '<phrase>' into search
    Then Proper results will be displayed according to phrase '<phrase>'

    Examples:
      | phrase |
      | joh    |
      | mar    |
      | spen   |