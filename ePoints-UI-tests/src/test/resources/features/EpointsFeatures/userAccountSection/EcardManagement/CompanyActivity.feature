Feature: Epoints your account company ecards activity
  As an epoints user which have access to sending ecards
  I want to have ecard activity page in your account page profile left tab
  So that I could see company activity ecards

  Background: User is logged with ecard permission and go to ecards history page
    Given User with a certain ecard access is signed in to epoints
    And Company activity page is opened

  @Regression @Ecards @EcardsActivity
  Scenario: Personal ecards history - logged out user
    And Epoints user is signed out from epoints
    When He tries to access ecard activity area
    Then He is asked to logged in
    And He cannot open ecards activity page

  @Regression @Ecards @EcardsActivity
  Scenario: Ecard activity - feed view
    Then All of the necessary ecard data is shown, including date,from,to,reason,ep

  @Regression @Ecards @EcardsActivity
  Scenario: Ecard activity - limit met and order
    Then Ecard display limit is met - up to '20' ecards
    And The newest ecards are displayed on the top

  @Regression @Ecards @EcardsActivity
  Scenario: Company activity - recipients list popup
    Given Last ecard was sent by user defaultEpointsUser, defaultEpointsUserPassword to more than 3 recipients
    When He clicks on "X others" button on chosen ecard on company activity page
    Then The scrollable pop-up with recipients list is displayed over company activity page
    And User can close recipients list popup of chosen company activity ecard