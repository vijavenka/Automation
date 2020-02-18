Feature: SearchTransaction

  As an affiliate manager
  I want to be able to search through clickouts with specific criteria
  So that users transactions statuses are easy to follow

  #This scenario desn't work anymore because created date is not used to change clickout status, networkTransaction date is used
  Scenario: Search for clickouts which should not be in PENDING state - older than 30 days
    Given Affiliate manager is responding to healthcheck
    When User searches for clickouts between 45 days old and 31 days old
    Then No PENDING clickouts are found


  @Regression @TransactionSearch @qa @prod
  Scenario Outline: Search for clickouts which should not be in PENDING state - older than 30 days -> Database selection
    When User searches for '<leads>' clickouts with daysToConfirm '<daysToConfirm>' between <from> days old and <to> days old in database
    Then No <statusNotExpectedToBeFound> clickouts are found in database

    Examples:
      | leads     | from | to | daysToConfirm           | statusNotExpectedToBeFound |
      | not leads | 45   | 31 | AND daysToConfirm = 30  | PENDING                    |
      | not leads | 55   | 41 | AND daysToConfirm >= 40 | PENDING                    |
#        | leads | 55   | 41 | ; | PENDING                    |