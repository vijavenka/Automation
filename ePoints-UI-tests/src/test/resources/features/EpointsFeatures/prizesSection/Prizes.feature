#Feature: Epoints prizes page
#  As an epoints user
#  I want to have prizes page
#  So that I could spin roulette wheel as reward for confirmed transaction on external retailer pages
#
## For this tests spins will be added to database manually and endpoints will not be copied from api tests as this will be
## to much for simple ui tests like those
#
#  @Regression @PrizesPage
#  Scenario Outline: Prizes page - page content for different type of users
#    Given User has '<spinsAvailable>' spins awarded for confirmed transactions
#    When Prizes page is opened by '<userLoginState>' user
#    Then Page content is correct according to '<userLoginState>' and '<spinsAvailable>'
#
#    Examples:
#      | userLoginState | spinsAvailable |
#      | not logged     | false          |
#      | logged         | false          |
#      | identified     | false          |
#      | not logged     | true           |
#      | logged         | true           |
#      | identified     | true           |
#
##    this test will be run fro all 3 buttons which redirects to /points/online but only for one state
#  @Regression @PrizesPage
#  Scenario Outline: Prizes page - redirection to points page
#    Given User has 'false' spins awarded for confirmed transactions
#    And Prizes page is opened by 'logged' user
#    When Button from '<buttonClicked>' which redirects user to 'points/online' page will be clicked
#    Then He will be redirected to /points/online page
#
#    Examples:
#      | buttonClicked |
#      | firstSection  |
#      | spinsNumber   |
#      | secondSection |
#
#  @Regression @PrizesPage
#  Scenario: Prizes page - sign in button used
#    Given User has 'false' spins awarded for confirmed transactions
#    And Prizes page is opened by 'not logged' user
#    When User click sign in button available on first information section
#    Then Login modal with all needed elements will be displayed over prizes page