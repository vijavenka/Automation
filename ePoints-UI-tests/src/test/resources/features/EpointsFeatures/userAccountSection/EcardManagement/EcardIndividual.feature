Feature: Epoints your account ecard individual page
  As an epoints user which have access to sending ecards
  I want to have ecard individual page in your account page profile tab
  So that I could see full screen view of historical sent or received ecard with all data including personal message

  Background: User is logged with ecard permission and go to ecards history page
    Given User with a certain ecard access is signed in to epoints
    And Ecards history page is opened

  @Regression @Ecards @EcardIndividualPage
  Scenario: Personal ecards history - logged out user
    And Epoints user is signed out from epoints
    When He clicks on the individual ecard link
    Then He is re-directed to log in page

  @Regression @Ecards @EcardIndividualPage
  Scenario: Personal ecards history - logged in user
    When He clicks on the individual ecard link
    Then He is re-directed to ecard page
    And It shows epoints if added, message, image & reason

  @Regression @Ecards @EcardIndividualPage
  Scenario: Personal ecards history - ecard details comparison
    And He sent or received some ecards already
    When He clicks on ecard details link of chosen ecard
    Then He is re-directed to full page of chosen ecard
    And Individual ecard page details match those available on history list

  @Regression @Ecards @EcardIndividualPage
  Scenario: Personal ecards history - unauthorised user
    And User has an ecard link
    But He is not a sender or a recipient of an ecard
    When He clicks on the link
    Then The information about the ecard not being available for him is displayed