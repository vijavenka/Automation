Feature: Users profile
  As an administrator
  I want to have user profile page
  So that I will be able to see my account details and be able to change some of them

  @Regression @UserProfile
  @resetAdminPasswordBefore
  Scenario: User profile - general view
    Given User with a certain users management permissions is signed in to iat with defaultProfileTestUser, defaultProfileTestPassword
    When My profile page is clicked on menu
    Then Admin is presented with my profile interface
    And Page contains disabled employee number field
    And Page contains name field
    And Page contains gender checkboxes
    And Page contains date of birth field
    And Page contains email field
    And Page contains disabled user role checkboxes
    And Page contains read only department tree
    And Page contains reset and save button

  @Regression @UserProfile
  Scenario: User profile - reset button clicked
    Given User with a certain users management permissions is signed in to iat with defaultProfileTestUser, defaultProfileTestPassword
    And My profile page is opened
    When User changes his details but doesn't save
    And He clicks on "Reset" button on my profile page
    And He click "Yes" button on reset confirmation popup
    Then Reset confirmation popup will be closed
    And All changes are canceled and user sees his saved datails

  @Regression @UserProfile
  Scenario: User profile - change password button
    Given User with a certain users management permissions is signed in to iat with defaultProfileTestUser, defaultProfileTestPassword
    And My profile page is opened
    When User clicks on Change password button
    Then He is on page which allows him to change password

  @Regression @UserProfile
  Scenario Outline: User profile - edit user details
    Given User with a certain users management permissions is signed in to iat with <userEmail>, <userPassword>
    And My profile page is opened
    And He filled the form with proper data '<userNewData>'
    When He clicks on "Save" button under form
    And User decide if also epoints email has to be updated 'true' on popup
    Then Admin user stays on the my profile form
    And New data are saved and presenting

    Examples:
      | userEmail                | userPassword | userNewData                                                   |
      | defaultProfileTestUser   | password     | profiletestsadmin@wp.pl2;FirstName LastName;FEMALE;12-05-1956 |
      | profiletestsadmin@wp.pl2 | password     | defaultProfileTestUser;FirstName LastName;MALE;01-01-1956     |