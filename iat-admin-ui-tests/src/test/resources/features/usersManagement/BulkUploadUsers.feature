Feature: Bulk upload users
  As a user with create users permission
  I want to have interface for users upload using special file
  So that I will be able create many company epoints users or admins, managers at once

  @Regression @BulkUploadUsers
  Scenario: Bulk upload users - general view
    Given User with a certain bulk upload permissions is signed in to iat
    And Bulk upload page is opened
    Then There is a drop down box
    And There is an inactive 'Add users' button

  @Regression @BulkUploadUsers
  Scenario: Bulk upload users - activate 'Add users' button
    Given User with a certain bulk upload permissions is signed in to iat
    And Bulk upload page is opened
    When User uploads file 'bulk1.xlsx'
    Then Button 'Add users' started to be active

  @Regression @BulkUploadUsers
  @removeCreatedUsersByBulkUpload
  Scenario: Bulk upload users - proper add users
    Given User with a certain bulk upload permissions is signed in to iat
    And Bulk upload page is opened
    When User uploads file 'bulk1.xlsx' with new users
    And Afterwards button 'Add users' is clicked
    Then Operation is successfully finished
    And Users are added to the system

#  @Regression @BulkUploadUsers
#  @removeCreatedUsersByBulkUpload
#  Scenario: Bulk upload users - bulk upload users twice
#    Given User with a certain bulk upload permissions is signed in to iat
#    And Bulk upload page is opened
#    When User uploads file 'bulk1.xlsx' with new users
#    And Afterwards button 'Add users' is clicked
#    When User uploads file 'bulk1.xlsx' with new users
#    And Afterwards button 'Add users' is clicked
#    Then Operation is successfully finished
#    And Users are added to the system

  @Regression @BulkUploadUsers
  Scenario: Bulk upload users - bad file extension
    Given User with a certain bulk upload permissions is signed in to iat
    And Bulk upload page is opened
    When User uploads image file
    Then Information about bad file extension occurs
    And There is an inactive 'Add users' button

  @Regression @BulkUploadUsers
  Scenario: Bulk upload users - errors in file
    Given User with a certain bulk upload permissions is signed in to iat
    And Bulk upload page is opened
    When User uploads file 'bulk2Errors.xlsx' with new users
    And Afterwards button 'Add users' is clicked
    Then Operation is not successfully finished
    And Error message describing all wrong rows is displayed

  @Regression
  Scenario: Adding an active user in partner2 through bulk upload
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Identified an active user
    And IAT Admin user is logged in with credentials 'api_test_super_admin_2@api.iat.admin.pl,P@ssw0rd'
    And Adding the same user through bulk upload
    Then It should throw proper error message

  @Regression
  Scenario: Adding an in-active user in partner2 through bulk upload
    Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
    And Identified an in-active user
    And IAT Admin user is logged in with credentials 'api_test_super_admin_2@api.iat.admin.pl,P@ssw0rd'
    And Adding the same user through bulk upload
    Then It should allow us to create the user
    And In partner1, it shouldn't allow us to re-active the user
    
    # Bulk upload file will work only for insertion of users along with new department.
    # Bulk upload file will work for deletion of records
    @Regression @BulkUploadUsers
  Scenario: PD-208, PD-392 Bulk upload file, inserting and deleting of records
      Given User with a certain bulk upload permissions is signed in to iat
      And Bulk upload page is opened
      When User uploads file 'bulk2.xlsx' with new users
      And Afterwards button 'Add users' is clicked
      Then Operation is successfully finished
      When Adding delete flag to existing file 'bulk2.xlsx'
      And Afterwards button 'Add users' is clicked
      Then Operation is successfully finished
      And Users are added to the system


# Bulk upload will not work for any updation like department update, role or admin privileage change, company start date and company username.
  @Regression @BulkUploadUsers
 Scenario: PD-208 Bulk upload file, updating of records (duplicate record)
    Given User with a certain bulk upload permissions is signed in to iat
    And Bulk upload page is opened
    When User uploads file 'bulk_update.xlsx' with updating of records
    And Afterwards button 'Add users' is clicked
    Then Throws all emails which has duplicate records
