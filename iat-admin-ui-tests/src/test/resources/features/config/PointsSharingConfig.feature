Feature: Edit global settings Configuration
  As an admin user
  I want to be able to set rules around company in Edit global settings (points sharing)
  So that I will have full control over points distribution

  @Regression @PointsSharingConfig
  @setGlobalPasswordFromCompanyConfigurationBeforeTest @deleteGlobalPasswordFromCompanyConfigurationAfterTest
  Scenario Outline: General view, options availability
    Given Superadmin bupa user '<bupaUser>' is logged to the system
    When He goes to 'Points sharing' config page
    Then He will see all configuration options according to company '<bupaUser>'

    Examples:
      | bupaUser |
      | false    |
      | true     |

  @Regression @PointsSharingConfig
  Scenario Outline: Manager to user points awarding
    Given Superadmin bupa user 'false' is logged to the system
    And He goes to 'Points sharing' config page
    When He saves the '<settings>' for manager to user points sharing
    Then '<settings>' are saved in the system

    Examples:
      | settings             |
      | from same department |
      | from same company    |

  @Regression @PointsSharingConfig
  Scenario Outline: Points Sharing configuration
    Given Superadmin bupa user 'false' is logged to the system
    And He goes to 'Points sharing' config page
    When He saves the settings for '<userToUser>', '<managerToUser>' and '<userToManager>'
    Then Values are saved in the system

    Examples:
      | userToUser           | managerToUser        | userToManager |
      | from same department | from same department | Yes           |
      | from same company    | from same company    | No            |
      #| with all users       | from same department | Yes           |

  @Regression @PointsSharingConfig
  Scenario Outline: Points Sharing configuration - persistance state of data
    Given Superadmin bupa user 'false' is logged to the system
    And He goes to 'Points sharing' config page
    When He changes radio-buttons state for '<userToUser>', '<managerToUser>' and '<userToManager>'
    Then Values are not saved in the system

    Examples:
      | userToUser           | managerToUser        | userToManager |
      | from same department | from same department | Yes           |
      | from same company    | from same company    | No            |
      #| with all users       | from same department | Yes           |

#  @Regression @PointsSharingConfig
#  Scenario Outline: Approval toggle option - proper configuration
#    Given Superadmin bupa user 'false' is logged to the system ''
#    And He goes to 'Points sharing' config page
#    When He saves the settings for approval '<approvalOption>', '<approvalThreshold>'
#    Then Values are saved in the system
#
#    Examples:
#      | approvalOption       | approvalThreshold |
#      | approvalForAll       | null              |
#      | noApproval           | null              |
#      | approvalForThreshold | 1                 |
#      | approvalForThreshold | 15                |
#
#  @Regression @PointsSharingConfig
#  Scenario Outline: Approval toggle option - bad configuration - no threshold when required
#    Given Superadmin bupa user 'false' is logged to the system
#    And He goes to 'Points sharing' config page
#    When He changes settings for approval '<approvalOption>', '<approvalThreshold>'
#    Then Save button is inactive
#
#    Examples:
#      | approvalOption       | approvalThreshold |
#      | approvalForThreshold | null              |

  @Regression @PointsSharingConfig
  @setGlobalPasswordFromCompanyConfigurationBeforeTest @deleteGlobalPasswordFromCompanyConfigurationAfterTest
  Scenario: General password, default state
    Given Superadmin bupa user 'true' is logged to the system
    When He goes to 'Points sharing' config page
    Then Global password setting will be set to 'No' by default
    And Global password input field will be displayed when user selects option 'Yes, use global password'

  @Regression @PointsSharingConfig
  @setGlobalPasswordFromCompanyConfigurationBeforeTest @deleteGlobalPasswordFromCompanyConfigurationAfterTest
  Scenario: General password, setting global password
    Given Superadmin bupa user 'true' is logged to the system
    And He goes to 'Points sharing' config page
    When User provide global password
    And User save sharing rules
    Then Password will be set
    And global password cannot be changed
    And Global password saved option will be persisted