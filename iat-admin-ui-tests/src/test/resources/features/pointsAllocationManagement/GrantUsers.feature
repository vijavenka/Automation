Feature: Recognise users
  As an administrator
  I want to have Recognise users page
  So that I will be able to award points to chosen users

  #step 1
  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 1 page content
    Given User with points users award permissions is logged into iat admin
    When Recognise users page is opened
    Then Available points for award info is displayed
    And Select reason ddl is available
    And Message input field is available
    And Cc input field is available
    And Image preview is available
    And Images to select are available
    And Next button is displayed

  @Regression @RecogniseUsers
  Scenario: Recognise users - cc list adding new email
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    When User types email address in cc field
    Then It's added to the CC list

  @Regression @RecogniseUsers
  Scenario: Recognise users - cc email removing
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And User types email address in cc field
    When Manager decides to remove an email
    Then It disappears from cc field

  @Regression @RecogniseUsers
  Scenario: Recognise users - new image selection
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And One of the images is selected
    When Manager decides to select another image
    Then The new image is highlighted
    And The previous image is unselected

  @Regression @RecogniseUsers
  Scenario: Recognise users - first step finished next button
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    When User chooses the reason from the drop-down
    And User adds message
    And User types email address in cc field
    And One of the images is selected
    And User clicks next button on first step
    Then He is re-directed to the select users screen

  #step 2
  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 page content
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    When Second users award step is opened
    Then Available points for award info is displayed
    And Points per user input field is available
    And Global search for user is available
    And Select users tree is available
    And User award summary is displayed
    And Next button is displayed on second step
    And Back button is displayed on second step

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 users tree all users from chosen node selected
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    When Users clicks All option next to chosen group
    Then All of the users from the group appear selected

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 points value < than reason global minimum
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    When Manager types points value smaller than reason's minimum to points per user field
    Then He is informed that he exceed minimum reason limit

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 points value > than reason global maximum
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    When Manager types points value bigger than reason's maximum to points per user field
    Then He is informed that he exceed maximum reason limit

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 predictive input search
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    When User decides to choose users from one group
    And Types part of a first last name or email
    And He confirms
    Then Chosen user appears selected

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 predictive input search removing user
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    And Some users are already chosen
    When He decides to remove selected user
    Then Chosen users disappear from the selection

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 not enough points for allocation
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    And Some users are already chosen
    When He decides to allocate greater number of points that is available
    Then He is not able to continue
    And He is informed that there is not enough points to allocate

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 enough points for allocation
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    And Some users are already chosen
    When He decides to allocate points from his account to the users
    And User clicks next button on second step
    Then He is re-directed to the summary screen
    And He is able to continue the process

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 points field empty
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And Users tree is opened
    And Some users are already chosen
    When When he decides to leave the points field empty
    And User clicks next button on second step
    Then He is re-directed to the summary screen
    And He is able to continue the process

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 back button
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    When User click back button on second step
    Then He is re-directed to the select card details screen
    And All previous set data on select card detail screen are persisted

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario Outline: Recognise users - Recognise users step 2 global search usage
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    When User use global search search with phrase '<searchPhrase>'
    Then Results will contain '<searchPhrase>' in field '<field>'

    Examples:
      | field     | searchPhrase  |
      | userEmail | emailfortest@ |
      | userName  | Kris Barano   |

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 2 merging same users selected by global and department search
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Second users award step is opened
    And He decides to allocate points from his account to the users
    And Some users are already chosen
    And Same users are added by global search
    Then Points summary take into consideration twice added users
    And Twice added user are merged on third confirmation step

  #step 3
  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario Outline: Recognise users - Recognise users step 3, table data correctness
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Manager is on the third wizard screen enabling him to confirm the ecard
    And The tabular view of company members along with allocated points is displayed at the top '<tableColumns>'
    And Ecard preview is available, including reason, (ep amount), message & image
    When User click back button on third step
    Then He is re-directed to the select users screen

    Examples:
      | tableColumns                 |
      | Email,Name,Department,Points |

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  Scenario: Recognise users - Recognise users step 3 save button
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Manager is on the third wizard screen enabling him to confirm the ecard
    When Manager clicks on the save button on third step
    Then Confirmation is shown about card being sent successfully
    And Epoints are added to chosen accounts
    And Virtual pot is deducted

  @Regression @RecogniseUsers
  @setAvailablePointsForAdmin
  @NBO-10043
  Scenario: Recognise users - Recognise users step 3 remove user
    Given User with points users award permissions is logged into iat admin
    And Recognise users page is opened
    And Manager is on the third wizard screen enabling him to confirm the ecard
    When Manager decides to remove users from award table
    And Confirm user removing in confirmation popup
    Then User will be removed from users award table