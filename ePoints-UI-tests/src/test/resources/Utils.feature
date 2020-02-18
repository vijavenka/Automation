Feature: Utils

  @utils @setHighEpointsValue
  Scenario: Create number user ecard award
    Given 25 Ecards requests is created to populate test user received history

  Scenario: Create number user ecard award
    Given 25 Ecards requests is created to populate test user sent history
    
  Scenario: PD-868 When ecard is sent from epoints, should be able to verify partnerName in cloudwatch log for the partner.isPartnerVivupSponsored set to 1
    Given In epoints
    When ecard is sent to user
    And when we search for the message 'Ecard email to vivup sponsored users sent for partner' in cloudwatch
    Then log file will have partnerName
  
  Scenario: PD-868 When ecard is sent from epoints, log will not be created in cloudwatch for the partner.isPartnerVivupSponsored set to 0
    Given In epoints
    And when we search for the message 'Ecard email to non vivup sponsored users sent for partner' in cloudwatch
    Then log file will have partnerName
