Feature: Epoints invite friends page
  As an epoints user
  I want to have invite friends page
  So that I could earn more epoints by inviting friends to create epoints account

  # // 1.1 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # --------------------------------------------------------------------------------------- not logged user page content
  @Regression @InviteFriendsPage
  Scenario: EPOINTS - create invite a friend link functionality to account area(NBO-1037) - not logged user page content
    Given Invite friend page is opened by not logged user
    When User look on invite friend page
    Then He will see proper page header
    And He will see join option on invite friend page
    And He will see sign in option on invite friend page
    And He will see explanation how it works on invite friend page

  # // 1.2 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # -------------------------------------------------------------------------------------------------------- join button
  @Regression @InviteFriendsPage
  Scenario: EPOINTS - create invite a friend link functionality to account area(NBO-1037) - join button
    Given Invite friend page is opened by not logged user
    When User click on join button on invite friend page
    Then He will be redirected to join page

  # // 1.3 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # ----------------------------------------------------------------------------------------------------- sign in button
  @Regression @InviteFriendsPage
  Scenario: EPOINTS - create invite a friend link functionality to account area(NBO-1037) - sign in button
    Given Invite friend page is opened by not logged user
    When User click on sign in button on invite friend page
    Then Login modal with all needed elements will be displayed over invite friend page

  # // 1.4 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # ------------------------------------------------------------------------------------------- logged user page content
  @Regression @InviteFriendsPage
  Scenario: EPOINTS - create invite a friend link functionality to account area(NBO-1037) - logged user page content
    Given Invite friend page is opened by logged user
    When User look on invite friend page
    Then He will see unique invitation link on invite friend page
    And He will see facebook post button on invite friend page
    And He will see how it works explanation on invite friend page

  # // 1.5 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # --------------------------------------------------------------------------------- share link usage and join by email
  @Regression @InviteFriendsPage
  @deleteUserAfterTest
  Scenario: EPOINTS - create invite a friend link functionality to account area(NBO-1037) - share link usage and join by email
    Given All invited friend are deleted from system
    Given Invite friend page is opened by logged user
    When Invite link will be used be other user
    And Invited user account will be properly registrated
    Then Referrer will receive additional fifty epoints 'epointsUser'

  # // 1.6 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # ------------------------------------------------------------------------------ share link usage and join by facebook
  @Regression @InviteFriendsPage @NBO-10398
  @deleteUserAfterTest @deleteFacebookUserBeforeTest
  Scenario: EPOINTS - create invite a friend link functionality to account area(NBO-1037) - share link usage and join by facebook
    Given All invited friend are deleted from system
    Given Invite friend page is opened by logged user
    When Invite link will be used be other user
    And User use join by facebook option with correct data
    Then Referrer will receive additional fifty epoints 'facebookUser'

  # // 1.7 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # ------------------------------------------------------------------------------------------ share link 10 usage limit
  @Regression @InviteFriendsPage
  Scenario: EPOINTS - create invite a friend link functionality to account area(NBO-1037) - share link 10 usage limit
    Given All invited friend are deleted from system
    Given Invite friend page is opened by logged user
    When Invite link will be used be other user ten times
    Then Referrer will not receive additional fifty epoints in current month

  # // 1.8 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
  # --------------------------------------------------------------------------------------------------- facebook posting
  @userIsLogoutFromFacebook @Regression @InviteFriendsPage
  Scenario: EPOINTS - create invite a friend link functionality to account area NBO-1037 - facebook posting
    Given Invite friend page is opened by logged user
    When User click post on your timeline button
    And Login into facebook account
    Then He will see that facebook post is properly prepared
    And Referer join link is also proper