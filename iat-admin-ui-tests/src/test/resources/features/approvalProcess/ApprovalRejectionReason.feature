#Feature: Approval award - rejection reason
#  As an special administrator
#  I want to have rejection award page under approve awards list
#  So that I will be able to reject award with reason message
#
#  @Regression @ApprovalRejectionReason
#  Scenario Outline: Approval award: rejection reason - reject award success
#    Given Manager 'admin' responsible for approving requests is signed to iat admin
#    And Some manager 'manager' already created epoints requests '<awardData>' that require approval
#    When He goes to 'Reject transaction' page for given award
#    And He inputs reason message with length '<messageLength>', clicks 'Reject' and confirms
#    Then User goes back to approval list
#    And Rejected award transaction is no longer on approaval list
#    And Number in 'Approval live icon' decreased
#
#    #awardData: reasonId;templateId;points;toEmail
#    Examples:
#      | awardData                                     | messageLength |
#      | 22102016112;22102016112;51;emailfortest@wp.pl | 20            |
#      | 22102016112;22102016112;51;emailfortest@wp.pl | 255           |
#
#  @Regression @ApprovalRejectionReason
#  @rejectAwardForApproval
#  Scenario Outline: Approval award: rejection reason - reject award and click 'No'
#    Given Manager 'admin' responsible for approving requests is signed to iat admin
#    And Some manager 'manager' already created epoints requests '<awardData>' that require approval
#    When He goes to 'Reject transaction' page for given award
#    And He inputs reason message with length '<messageLength>', clicks 'Reject' and 'No' afterwards
#    Then User is still on 'Reject transaction' page
#
#    Examples:
#      | awardData                                     | messageLength |
#      | 22102016112;22102016112;51;emailfortest@wp.pl | 20            |
#
#  @Regression @ApprovalRejectionReason
#  @rejectAwardForApproval
#  Scenario Outline: Approval award: rejection reason - cancel reject
#    Given Manager 'admin' responsible for approving requests is signed to iat admin
#    And Some manager 'manager' already created epoints requests '<awardData>' that require approval
#    When He goes to 'Reject transaction' page for given award
#    And He clicks 'Cancel' button
#    Then User goes back to approval list
#    And Award transaction is still on approaval list
#
#    Examples:
#      | awardData                                     |
#      | 22102016112;22102016112;51;emailfortest@wp.pl |
#
#  @Regression @ApprovalRejectionReason
#  @rejectAwardForApproval
#  Scenario Outline: Approval award: rejection reason - proper view
#    Given Manager 'admin' responsible for approving requests is signed to iat admin
#    And Some manager 'manager' already created epoints requests '<awardData>' that require approval
#    When He goes to 'Reject transaction' page for given award
#    Then 'Reject transaction' page has correct information
#
#    Examples:
#      | awardData                                                         |
#      | 22102016112;22102016112;51;emailfortest@wp.pl,emailfortest1@wp.pl |
#
#  @Regression @ApprovalRejectionReason
#  @rejectAwardForApproval
#  Scenario Outline: Approval award: rejection reason - illegal reject award
#    Given Manager 'admin' responsible for approving requests is signed to iat admin
#    And Some manager 'manager' already created epoints requests '<awardData>' that require approval
#    When He goes to 'Reject transaction' page for given award
#    And He provides message with length '<messageLength>' and clicks 'Reject'
#    Then User is still on 'Reject transaction' page
#
#    Examples:
#      | awardData                                     | messageLength |
#      | 22102016112;22102016112;51;emailfortest@wp.pl | 0             |
#      | 22102016112;22102016112;51;emailfortest@wp.pl | 256           |