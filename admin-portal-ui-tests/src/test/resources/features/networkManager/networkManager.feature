Feature: Login into admin portal
  As an administrator
  I want to have network manager functionality
  So that I will be able to create/edit affiliate networks definitions

# This test internally will check content of all ddls
  @AdminPortal @NetworkManager
  Scenario Outline: Check network fields availability on create/edit page
    Given Admin Portal user is logged in
    And He is on Feed Manager page
    When He select row with feed by clicking network id
    Then All '<fields>' should be available

    Examples:
      | fields                                                                                                                                                                 |
      | Name,Description,Code,Feed URL Field,Tracking Variable Field,Schedule Timing,Product Property Name,Product Title Field,Content Type,Pull Method, Pre-processing Stages |

# All fields are mandatory apart of Description, Schedule Timing, Pre-procressing Stages.
  @AdminPortal @NetworkManager
  Scenario Outline: Affiliate network creation
    Given Admin Portal user is logged in
    And He click create new network button
    When He provide all network data '<networkData>'
    And Click 'Save' button
    Then New affiliate network will be saved and available on networks list
    And All fields will be stored in tables: 'admin_ui.AffiliateNetwork' and 'admin_ui.AffiliateNetworkPreprocessingStage'

    Examples:
      | networkData |
      |             |
