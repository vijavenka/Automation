Feature: Epoints your account create ecard
  As an epoints user which have access to sending ecards
  I want to have ecard management, create ecard page in your account page profile tab
  So that I could create ecard with attached image, epoints and message, and send it to number of selected users

  Background: Logged user goes to Create ecard section
    Given User with a certain ecard access is signed in to epoints
    When Interface to add ecard recipients is opened

  @Regression @Ecards @EcardAward
  Scenario: Ecards creation - image selection
    When User clicks on an image
    Then Its large version is displayed
    And The thumbnail changes to selected state

  @Regression @Ecards @EcardAward
  Scenario: Ecards creation - set up of card
    When User picks a reason, message, image and clicks on next
    Then Ecard should be created and user should be moved to the second step

  @Regression @Ecards @EcardAward
  Scenario Outline: Ecards creation - set up of card validation rules
    When User do not enter data into '<emptyField>' field
    And User click next button on ecard creation step one
    Then User should remain on page '1'
    And Alert '<alert>' should be diplayed

    Examples:
      | emptyField | alert               |
      | reason     | Reason is required  |
      | message    | Message is required |

  @Regression @Ecards @EcardAward
  Scenario: Ecards creation - set up of card, 1024 chars in message
    When User picks a reason, message with '1024' chars, image and clicks on next
    Then Ecard should be created and user should be moved to the second step

  @Regression @Ecards @EcardAward
  Scenario: Ecards creation - set up of card validation rules, 1025 chars in message
    When User picks a reason, message with '1025' chars, image and clicks on next
    Then Validation 'Message is too long' should be displayed
    And User should remain on page '1'

  #//HS-101
  @Regression @Ecards @CreateEcard @Ecards
  Scenario: Create ecard page - find users - initial view
    And First ecard creation step is properly finished
    Then User can see empty search box
    And Clear all users option is available
    And Initially there are no users displayed on search list

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - find users - email not existing in epoints
    And First ecard creation step is properly finished
    When He wants to add an email address that is not in epoints '<searchPhrase>'
    Then The '<searchPhrase>' address is not found in the predictive search
    And Its impossible to add that address to recipients list

    Examples:
      | searchPhrase                      |
      | notExistingEmailAddress@gmail.com |

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - find users - inactive mail
    And First ecard creation step is properly finished
    When He wants to add an email address that is in epoints but is not active '<searchPhrase>'
    Then The '<searchPhrase>' address is not found in the predictive search
    And Its impossible to add that address to recipients list

    Examples:
      | searchPhrase            |
      | inactiveemail@gmail.com |

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - find users - Search
    And First ecard creation step is properly finished
    When He types in the first/last name or email in the search box '<searchPhrase>'
    Then Predictive users list is shown with matching results '<searchPhrase>'

    Examples:
      | searchPhrase |
      | emailfortest |

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - find users - unverified mail
    And First ecard creation step is properly finished
    When He wants to add an email address that is in epoints but is not verified '<searchPhrase>'
    Then Predictive users list is shown with matching results '<searchPhrase>'

    Examples:
      | searchPhrase           |
      | notverifiedemail@pl.pl |

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - find users - select a user
    And First ecard creation step is properly finished
    When He types in the first/last name or email in the search box '<searchPhrase>'
    And Select a user from predictive search list
    Then User's first and last name is added to the search box as a tag view

    Examples:
      | searchPhrase |
      | emailfortest |

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - find users - deselect a user
    And First ecard creation step is properly finished
    And One user is already selected '<searchPhrase>'
    When He clicks on the "X" on selected user's tag
    Then This user is removed from selected users panel

    Examples:
      | searchPhrase |
      | emailfortest |

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - find users - clear button
    And First ecard creation step is properly finished
    And One user is already selected '<searchPhrase>'
    When He clicks on the "clear button" next to users search
    Then This user is removed from selected users panel
    And Predictive search list contains no elements

    Examples:
      | searchPhrase |
      | emailfortest |

  #//HS-98, HS-781
  #//TODO wee ned somehow set user balance and proper reason global limits per reason (Before with mysql in fly or rest calls)
  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - points award - general view
    And First ecard creation step is properly finished
    And One user is already selected '<searchPhrase>'
    When We choose the number of epoints to award
    Then The total number of epoints is counted
    And User is informed how many users will receive these points
    And The total number of epoints is displayed
    And The number of epoints left after transaction is shown as well

    Examples:
      | searchPhrase |
      | emailfortest |

  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - points award - points exceeded max reason limit
    And First ecard creation step is properly finished
    When He provides number of points bigger than available in the reason max limit
    Then He is informed that he number of points exceeded the max reason limit
    And He cannot move on and send an ecard

  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - points award - points exceeded min reason limit
    And First ecard creation step is properly finished
    When He provides number of points lower than available in the reason min limit
    Then He is informed that he number of points exceeded the min reason limit
    And He cannot move on and send an ecard

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - points award - points exceeded users available points number
    And First ecard creation step is properly finished
    And Two users are already selected '<searchPhrase>'
    When He chooses such number of epoints that (no. of users x ep) exceeds available points
    Then He is informed that he cannot allocate these points
    And He cannot move on and send an ecard

    Examples:
      | searchPhrase |
      | emailfortest |

  @Regression @Ecards @CreateEcard
  Scenario Outline: Create ecard page - points award - enough points
    And First ecard creation step is properly finished
    And One user is already selected '<searchPhrase>'
    When He chooses such number of epoints that (no. of users x ep) is lower or the same as available points
    Then Information about the cost of transaction is displayed
    And User can move on to the next step by clicking next button on ecard create second step

    Examples:
      | searchPhrase |
      | emailfortest |


  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - points award - back button
    And First ecard creation step is properly finished
    When User clicks back button on ecard creation second step
    Then He will be moved to ecard creation step one
    And End previous data filled on ecard creation step one will be displayed

  #//HS-102
  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - summary - preview
    And First ecard creation step is properly finished
    When Second ecard creation step is properly finished with 1 selected user
    Then He is presented with the preview of an ecard
    And Preview Contains image, message, reason and epoints

#  @Regression @Ecards @CreateEcard
#  Scenario: Create ecard page - summary - not enough points
#    And First ecard creation step is properly finished
#    And Second ecard creation step is properly finished with 1 selected user
#    But User doesn't have enough points on his account
#    When He clicks "Send ecard" button
#    Then He is informed that he doesn't have enough points
#    And Card is not sent
#    And He stays on the third step of ecard sending wizard page

  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - summary - enough points
    And First ecard creation step is properly finished
    And Second ecard creation step is properly finished with 1 selected user
    When He clicks "Send ecard" button
    Then He is re-directed to the ecard send success screen
    And There's a link to proceed to "History"
    And User can see his sent email in activity stream
    And Balance value was reduced of sent epoints

  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - summary - back button
    And First ecard creation step is properly finished
    And Second ecard creation step is properly finished with 1 selected user
    When He clicks "Back" button on ecard summary screen
    Then He is re-directed to the second wizard page
    And He can see his selected users
    And He can see all of the data he provided on users selection screen

  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - summary - recipients list
    And First ecard creation step is properly finished
    And Second ecard creation step is properly finished with 6 selected user
    And "X others" link is displayed under the email addresses
    When He clicks on "X others" button
    Then The scrollable pop-up with recipients list is displayed here
    And User can close it after viewing it

  #//HS-100
  @Regression @Ecards @CCEmails
  Scenario: Create ecard page - summary - recipients list adding
    And First ecard creation step is properly finished
    And Second ecard creation step is properly finished with 1 selected user
    When User provide an email to cc input field
    Then New email entry will be added to cc list

  @Regression @Ecards @CCEmails
  Scenario: Create ecard page - summary - recipients list removing
    And First ecard creation step is properly finished
    And Second ecard creation step is properly finished with 1 selected user
    And  User provide an email to cc input field
    When User removed provide email from cc list
    Then removed email will be romoved from cc input field

  @Regression @Ecards @CreateEcard
  Scenario: Create ecard page - summary - enough points cc provided
    And First ecard creation step is properly finished
    And Second ecard creation step is properly finished with 1 selected user
    And User provide an email to cc input field
    When He clicks "Send ecard" button
    Then He is re-directed to the ecard send success screen

  #Scenario: Create ecard page - summary - enough points cc not provided covered in some of previous scenarious