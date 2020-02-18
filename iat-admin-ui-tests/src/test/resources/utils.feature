@Outdated
Feature: Utils

  @utils @setManagerThresholdToNoneBefore @setAvailablePointsForAdmin
  Scenario: Create number admin ecard award
    Given 25 Ecards requests is created

  @utils @restoreData
  Scenario: Restore all require for tests data in points manager
    Given All previous data are removed
    And Groups are created
    And Partners are created
    And Virtual groups are created
    And Departments are created
    And Ecards reasons tags are created
    And Ecards reasons are created
    And Ecards templates are created
    And Ecards Settings are created
    And Super admin and uber admin are created with all authorities
    And Other needed admins and users are created
    
   Scenario: PD-868 When ecard is sent from eachperson, should be able to verify partnerName in cloudwatch log for the partner.isPartnerVivupSponsored set to 1
    Given In eachperson
    When ecard is sent to user
    And when we search for the message 'Ecard email to vivup sponsored users sent for partner' in cloudwatch
    Then log file will have partnerName

  Scenario: PD-868 When ecard is sent from epoints, log will not be created in cloudwatch for the partner.isPartnerVivupSponsored set to 0
    Given In epoints
    And when we search for the message 'Ecard email to non vivup sponsored users sent for partner' in cloudwatch
    Then log file will have partnerName
   
