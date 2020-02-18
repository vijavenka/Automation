Feature: Epoints your account ecards history
  As an epoints user which have access to sending ecards
  I want to have ecard history page in your account page profile tab
  So that I could see historical ecards which were sent to me and those which I sent t other people

  Background: User is logged with ecard permission and go to ecards history page
    Given User with a certain ecard access is signed in to epoints
    And Ecards history page is opened

  @Regression @Ecards @EcardsHistory
  Scenario: Personal ecards history - logged out user
    And Epoints user is signed out from epoints
    When He tries to access ecard history area
    Then He is asked to logged in
    And He cannot open ecards history page

  @Regression @Ecards @EcardsHistory
  Scenario: Personal ecards history - received ecards
    When User clicks on "received" navigation option
    Then He is presented with the interface showing received ecards
    And These ecards contain following elements: reason, points , thumbnail image, date, who to

  @Regression @Ecards @EcardsHistory
  Scenario: Personal ecards history - sent ecards
    When User clicks on "sent" navigation option
    Then He is presented with the interface showing sent ecards
    And These ecards contain following elements: reason, points , thumbnail image, date, who to

  @Regression @Ecards @EcardsHistory
  Scenario: Personal ecards history - individual card page link
    And He sent or received some ecards already
    When He clicks on ecard details link of chosen ecard
    Then He is re-directed to full page of chosen ecard

  @Regression @Ecards @EcardsHistory
  Scenario: Personal ecards history - show more
    And There are more than 20 ecards in the history
    And "Show more" button is available at the bottom
    When user clicks on "See more" button
    Then 20 more cards are displayed
    And "See more" button is displayed if more cards available

  @Regression @Ecards @EcardsHistory
  Scenario: Personal ecards history - back to top
    When He scrolls to the bottom of ecards display
    And Clicks on "Back to the top" button
    Then The view is scrolled to the top of the page

  @Regression @Ecards @EcardsHistory @setHighEpointsValue
  Scenario: Personal ecards history - recipients list popup
    Given Last ecard was sent by user defaultEpointsUser, defaultEpointsUserPassword to more than 3 recipients
    When He clicks on "X others" button on chosen ecard on sent history page
    Then The scrollable pop-up with recipients list is displayed over sent history page
    And User can close recipients list popup of chosen history page ecard