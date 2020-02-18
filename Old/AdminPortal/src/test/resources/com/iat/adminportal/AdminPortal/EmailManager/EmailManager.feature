Feature: Email manager page

  As a administrator
  I need a page to go to
  Where I can create rules for sending email to specified users

  Scenario:EMAIL MANAGEMENT - create email management list screen for "lists" (RD-1972) -
    Given Admin portal is opened
    When User look at admin portal main page
    Then He can see Email Management tab
    When He click this tab
    Then He will see email management list screen
    And This page has all needed elements and information

  Scenario:EMAIL MANAGEMENT - create email management create/edit screen for "lists" (RD-1984) -
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He can see all needed elements on popup window
    And He is able to add new account rule
    When Duplicated name Will be used
    And Save button pressed
    Then Name alert will be shown

  Scenario:EMAIL MANAGEMENT - create/edit page list rule - " account status" (RD-2307) -
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Account status rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add account status
    And After saving information are saved correctly - account status

  #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - account status option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add account status
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule account status

  Scenario:EMAIL MANAGEMENT - EMAIL MANAGEMENT - create/edit page list rule - " complete profile " (RD-2296) -
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Complete account rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add complete profile rule
    And After saving information are saved correctly - complete profile

    #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - complete profile option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add complete profile rule
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule complete profile rule

  Scenario:EMAIL MANAGEMENT - create/edit page list rule - "gender " (RD-2284) -
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Gender rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add gender rule
    And After saving information are saved correctly - gender

   #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - gender option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add gender rule
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule gender rule

  Scenario:EMAIL MANAGEMENT - create/edit page list rule - "logged in " (RD-2274) three options
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Logged in rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add logged in rule
    And After saving information are saved correctly - logged in

   #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged within option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add logged in rule
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule logged within

  Scenario:EMAIL MANAGEMENT - create/edit page list rule - "logged in " (RD-2274) from to option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Logged from rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add logged from rule
    And After saving information are saved correctly - logged from

   #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged from/to option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add logged from rule
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule logged from rule

  Scenario:EMAIL MANAGEMENT - create/edit page list rule - "created at" (RD-2264) three options
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Created at rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add created at rule
    And After saving information are saved correctly - created at

   #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - created within option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add created at rule
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule created within

  Scenario:EMAIL MANAGEMENT - create/edit page list rule - "created at"(RD-2264) from/to options
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Created from rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add created from rule
    And After saving information are saved correctly - created from

   #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - created from/to option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add created from rule
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule created from rule

  Scenario:EMAIL MANAGEMENT - create/edit page list rule - "registration site" (RD-2317) -
    Given Admin portal is opened
    Given Email manager is opened
    When User want to create new rule
    Then He is able to add new account rule
    And Registration site rule is available
    When User want to edit existing rule
    Then He is able to edit existing rule and add registration site rule
    And After saving information are saved correctly - registration site

    #//bug here NBO-10436
  Scenario:EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - registration site option
    Given Admin portal is opened
    Given Email manager is opened
    When User want to edit existing rule
    Then He is able to edit existing rule and add registration site rule
    And Save created rule
    When User want to see email list according to created rule
    Then He can press view button
    And Proper list will be displayed according to rule registration site rule

  Scenario:EMAIL MANAGEMENT - list screen functionality "Pause" (Rd-2254) -
    Given Admin portal is opened
    Given Email manager is opened
    When User decide to stop some mailing rule
    Then User click pause button next to chosen mailing rule
    And Value next to button is set to paused
    And Database field is update to false
    When User want to start mailing rule again
    Then User click start button next to chosen mailing rule
    And Value next to button is set to active
    And Database field is update to true

  Scenario:EMAIL MANAGEMENT - list screen functionality "Copy" (RD-2244) -
    Given Admin portal is opened
    Given Email manager is opened
    When User want to copy existing rule
    Then He should click copy button of chosen one
    And Enter new name
    And Click save button
    When User search for created rule
    Then He can see that rule was created correctly
    And Be sure that all rules were copied properly

#  this scenario has to be rewritten because users data cannot be updated in points manager, only dynamo
#  Scenario:Admin Panel - email manager - mailing list - new rule (RD-4208) -
#    Given Admin portal is opened
#    Given Email manager is opened
#    Given Gender male rule is set
#    Given Two specified emails are set as 'spam' and 'foreigner'
#    When User want to see email list according to created rule
#    Then On the list 'spam' and 'foreigner' email will not be included
#    When Used before emails 'spam' and 'foreigner' fields will be reset
#    And  List view will be opened once again
#    Then Used before emails will be visible