Feature: Ecard usage breakdown
  As an administrator
  I want to have ecard usage breakdown page
  So that I will be able to see number of ecards sent in all departments by users for all reasons

  @Regression @EcardsUsageBreakdown
  Scenario: Ecard usage breakdown - general view
    Given User with reporting management permissions is logged into iat admin
    When User open ecard usage breakdown page
    And User set time preset "This Year"
    Then First element of breadcrumb is displayed
    And Departments navigation table is displayed with proper columns
    And Manager details are properly populated
    And Ecards usage table is displayed with proper columns
    And Ecard usage breakdown filter component is displayed
    And Export to file button is available on ecard usage breakdowng page

  @Regression @EcardsUsageBreakdown
  Scenario: Ecard usage breakdown - department navigation
    Given User with reporting management permissions is logged into iat admin
    And User open ecard usage breakdown page
    And User set time preset "This Year"
    When User click some of department from initial list
    Then Department table will be refreshed and show only departments below selected departments
    And Users table will be updated and show only ecards sent by user from chosen department
    And Selected department will appear on breadcrumb as last element
    And User can use breadcrumb to navigate to higher levels of company structure

  @Regression @EcardsUsageBreakdown
  Scenario: Ecard usage breakdown - ecards usage breakdown export
    Given User with department allocation history permissions is logged into iat admin
    And User has no recent download notifications
    And User open ecard usage breakdown page
    When Export button will be clicked on ecard usage breakdown page
    Then Notification about 'Export is in progress.' is displayed over ecard usage breakdown page
    And Notification about 'Export is in progress.' can be closed on ecard usage breakdown page
    And After some time file ready to be downloaded will appear in notification area
    And File name will be correct - 'ecard-usage-breakdown'