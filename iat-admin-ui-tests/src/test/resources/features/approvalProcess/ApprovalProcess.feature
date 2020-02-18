#Feature: Approval process
#  As an administrator
#  I want to have for approval page
#  So that I will be able approve or reject points awards which exceeds set threshold
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval @setAvailablePointsForAdmin
#  Scenario Outline: Approval page - general view
#    Given Manager responsible for approving requests is signed to iat admin
#    And Some managers already created epoints requests that require approval
#    When Manger opens approval UI
#    Then He is presented with the pending requests list
#    And They are listed in date order with oldest first
#    And They have all of the necessary data displayed '<columns>'
#    And Each row has an approval/decline option available
#    And Live icon with number of awards for approve is displayed
#
#    Examples:
#      | columns                                          |
#      | Date,From,To,Reason,Points,Amount,Reject,Approve |
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - approve button clicked
#    Given Manager responsible for approving requests is signed to iat admin
#    And Some managers already created epoints requests that require approval
#    And Manger opens approval UI
#    When He clicks "approve" button next to one of the requests
#    Then Approve confirmation modal is displayed with "Approve" and "Cancel" options
#    #just for closing popup
#    And He click "cancel" button on approval confirmation popup
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - approve button clicked - confirmation modal cancel button
#    Given Manager responsible for approving requests is signed to iat admin
#    And Some managers already created epoints requests that require approval
#    And Manger opens approval UI
#    When He clicks "approve" button next to one of the requests
#    And He click "cancel" button on approval confirmation popup
#    Then Approval confirmation will be closed
#    And Award for approve row is still visible in approval table
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before
#  Scenario: Approval page - approve button clicked - confirmation modal approve button
#    Given Manager responsible for approving requests is signed to iat admin
#    And Some managers already created epoints requests that require approval
#    And Manger opens approval UI
#    When He clicks "approve" button next to one of the requests
#    And He click "approve" button on approval confirmation popup
#    Then Approval confirmation will be closed
#    And Approval confirmation information will be displayed
#    And Award for approve row is not visible in approval table
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - decline button clicked
#    Given Manager responsible for approving requests is signed to iat admin
#    And Some managers already created epoints requests that require approval
#    And Manger opens approval UI
#    When He clicks "decline" button next to one of the requests
#    Then User is re-directed to reject transaction screen
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - award more than one user, users modal, points per user displayed
#    Given Manager responsible for approving requests is signed to iat admin
#    And Some managers already created multiple epoints requests that require approval
#    And Manger opens approval UI
#    When User click "X users" button in "To" column
#    Then Modal with number of users will be displayed
#    And User can close modal contains users to be awarded
#    And Points and pounds value are correctly calculated per single user
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - approve and manager message check
#    Given Manager has no approval notifications - 'manager view'
#    And Some managers already created epoints requests that require approval
#    And One points award is already approved by admin responsible for approving
#    When Manager login into his account
#    Then He is notified that his ecard was approved
#    And Status of proper points award was changed to approved at user allocation history page
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - messages widget - manager perspective - approve notification link
#    Given Manager has no approval notifications - 'manager view'
#    And Some managers already created epoints requests that require approval
#    And One points award is already approved by admin responsible for approving
#    When Manager login into his account
#    And He click approved elements notification
#    Then He is re-directed to user allocation history
#    And It's filtered down by the "who sent" column
#    And Only the ecards that this manager sent are available there
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - reject and manager message check
#    Given Manager has no approval notifications - 'manager view'
#    And Some managers already created epoints requests that require approval
#    And One points award is already rejected by admin responsible for approving
#    When Manager login into his account
#    Then He is notified that his ecard was rejected
#    And Status of proper points award was changed to rejected at user allocation history page
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - messages widget - manager perspective - reject notification link
#    Given Manager has no approval notifications - 'manager view'
#    And Some managers already created epoints requests that require approval
#    And One points award is already rejected by admin responsible for approving
#    When Manager login into his account
#    And He click rejected elements notification
#    Then He is re-directed to user allocation history
#    And It's filtered down by the "who sent" column
#    And Only the ecards that this manager sent are available there
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdTo50Before @rejectCreatedAwardForApproval
#  Scenario: Approval page - messages widget - admin perspective - pending notification link
#    Given Manager has no approval notifications - 'admin view'
#    Given Some managers already created epoints requests that require approval
#    And Manager responsible for approving requests is signed to iat admin
#    And He is notified that some allocated points waiting for approval
#    When He clicks on one of the pending requests
#    Then He is re-directed to approval UI
#    And Same number of pending approval request is displayed there as on live icon on lefts side menu
#
#  @Regression @ApprovalProcess
#  @setManagerThresholdToNoneBefore
#  Scenario: Approval page - approval page availability for manager without approval permissions
#    Given Manager with approval process config set to "no" is signed to iat admin
#    When User expand "Points allocation" menu option
#    Then "For approval" option is not available
#    And Status column is not displayed in users allocation history
#    And For approval page is not available by direct link