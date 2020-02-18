Feature: Wizard screen for new users
  As a new admin user
  I want to have a wizard functionality
  So that I could configure global options for admin tools

  @Regression @Wizard
  @beforeWizard
  Scenario: Wizard configuration - first page
    Given New admin user is logged
    Then He is presented with wizard view first page
    And Other steps in wizard are disabled

#  @Regression @Wizard
#  @beforeWizard
#  Scenario Outline: Step in wizard not finished - no access to step 2 if step 1 is not completed
#    Given New admin user is logged
#    When Admin stopped on wizard's step '1'
#    And Approval settings for wizard are given '<approvalOption>', '<approvalThreshold>'
#    Then He cannot access the next config step in wizard
#
#    Examples:
#      | approvalOption       | approvalThreshold |
#      | approvalForThreshold | null              |

  @Regression @Wizard
  @beforeWizard
  Scenario: Step in wizard not finished - no access to step 3 if step 2 is not completed
    Given New admin user is logged
    When Admin stopped on wizard's step '2'
    Then He cannot access the next config step in wizard

  @Regression @Wizard
  @beforeWizard
  Scenario: Step in wizard not finished - no access to step 4 if step 3 is not completed
    Given New admin user is logged
    When Admin stopped on wizard step '3', he uses config type '6'
    Then He cannot access the next config step in wizard

  @Regression @Wizard
  @beforeWizard
  Scenario: Last step in wizard is not finished - cannot save
    Given New admin user is logged
    And Admin stopped on last step in wizard
    Then He cannot save wizard configuration

  @Regression @Wizard
  @beforeWizard
  Scenario Outline: Wizard configuration - first step default values
    Given New admin user is logged
    Then He is presented with wizard view first page
    And Default values for settings in wizard first page are following: '<userToUser>', '<managerToUser>', '<userToManager>', '<approvalOption>' and '<approvalThreshold>'

    Examples:
      | userToUser           | managerToUser     | userToManager | approvalOption | approvalThreshold |
      | from same department | from same company | No            | approvalForAll | null              |

  @Regression @Wizard
  @beforeWizard
  Scenario Outline: Wizard step 2-4 finished - "Back" button
    Given New admin user is logged
    And Admin stopped on wizard's step '<step>'
    And He fills in data in current wizard step
    When He clicks the 'Back' button in wizard
    Then He is re-directed to the step '<prevStep>' in wizard and can edit it
    And His work on last step '<step>' in wizard is cancelled
    And Wizard still doesn't have the "Complete" state

    Examples:
      | step | prevStep |
      | 2    | 1        |
      | 3    | 2        |
      | 4    | 3        |

  @Regression @Wizard
  @beforeWizard
  Scenario Outline: Wizard step 1-3 finished - "Next" button
    Given New admin user is logged
    And Admin stopped on wizard's step '<step>'
    And He fills in data in current wizard step
    When He clicks the 'Next' button in wizard
    Then He is re-directed to the step '<nextStep>' in wizard and can edit it
    And Current step is saved in the system
    And Wizard still doesn't have the "Complete" state
    And His work on last step '<step>' in wizard isn't cancelled

    Examples:
      | step | nextStep |
      | 1    | 2        |
      | 2    | 3        |
      | 3    | 4        |

  @Regression @Wizard
  @beforeWizard
  Scenario Outline: Wizard navigation bar
    Given New admin user is logged
    And Admin stopped on wizard's step '<step>'
    And He fills in data in current wizard step
    When He goes to step '<availStep>' using navigation bar
    Then He is re-directed to the step '<availStep>' in wizard and can edit it
    And His work on last step '<step>' in wizard is cancelled

    Examples:
      | step | availStep |
      | 2    | 1         |
      | 3    | 1         |
      | 3    | 2         |
      | 4    | 1         |
      | 4    | 2         |
      | 4    | 3         |

  @Regression @Wizard
  @beforeWizard
  Scenario Outline: Save the wizard
    Given New admin user is logged
    And He finished filling in the last step in wizard, he uses config type '<config>'
    When He clicks the 'Save' button in wizard
    Then All settings from wizard are on config pages

    Examples:
      | config |
      | 1      |
      | 2      |
      | 3      |
      | 4      |
#      | 5      |

  @Regression @Wizard
  @beforeWizard
  Scenario Outline: Wizard configuration - "Next" and "Save" button when step isn't filled in
    Given New admin user is logged
    And Admin stopped on wizard's step '<step>'
    When He clicks the '<button>' button in wizard
    Then He is still on current step in wizard

    Examples:
      | step | button |
      | 2    | Next   |
      | 4    | Save   |

  @Regression @Wizard
  @beforeWizard
  Scenario: Wizard configuration - "Next" when no custom templates and unchecked 'Use default templates'
    Given New admin user is logged
    And Admin stopped on "Ecard templates" step in wizard
    When He unchecks "Use default templates" in wizard
    When He clicks the 'Next' button in wizard
    Then He is still on current step in wizard

  @Regression @Wizard
  @beforeWizard
  Scenario: Wizard configuration - Try delete last reason from table in last step
    Given New admin user is logged
    And Admin stopped on wizard's step '4'
    And He fills in data in current wizard step
    When Admin wants to delete last available reason
    Then He is not able to do that because delete button is not displayed

  @Regression @Wizard
  @beforeWizard
  Scenario: Wizard configuration - Delete reason from table in last step
    Given New admin user is logged
    And Admin stopped on wizard's step '4'
    And He fills in data in current wizard step
    And At least 2 reasons are already added
    When Admin deletes last added reason from table in wizard
    Then Table doesn't have deleted reason in wizard
    Then He is still on current step in wizard

  @Regression @Wizard
  @beforeWizard
  Scenario: Wizard configuration - Remove added custom template
    Given New admin user is logged
    And Admin stopped on "Ecard templates" step in wizard
    And He adds custom template in wizard
    When Admin clicks on remove custom template button in wizard with following confirmation
    Then Custom templates list in wizard doesn't contain deleted template
    Then He is still on current step in wizard