Feature: Registering in Vivup, later creating new user in eachperson via incremental
#    Registering in Vivup, later editing existing user in eachperson
#    Deleting a Vivup user, in eachperson
#    Re-enabling the deleted Vivup user, in eachperson

    Scenario: PD-393 Registering in Vivup, later creating new user in eachperson via incremental
        Given User is registered in Vivup and did SSO with GDPR
        And Logged out from epoints.com
        And Registering the Vivup user in eachperson
        And Logging into epoints.com
        Then It should accept default password with change password screen
        And Without asking GDPR it has to login successfully
        And Profile page, Membership Type: Gold - Free Employee Account

    Scenario: PD-393 Registering in Vivup, later editing existing user in eachperson
        Given User is registered in Vivup and did SSO with GDPR
        And Logged out from epoints.com
        And Identifying different user in eachperson
        And Updating the existing user to Vivup user in eachperson
        And Logging into epoints.com
        And Without asking GDPR it has to login successfully

    Scenario: PD-393 Deleting a Vivup user, in eachperson
        Given User is registered in Vivup and did SSO with GDPR
        And Logged out from epoints.com
        And Registering the Vivup user in eachperson
        And Logging into epoints.com
        Then It should accept default password with change password screen
        And Login and logout successfully
        And Deleting the user in eachperson
        Then DynamoDB userGroup status of Vivup, epoints should be active and eachperson status should be deleted

    Scenario: PD-393 Re-enabling the deleted Vivup user, in eachperson
        Given User is registered in Vivup and did SSO with GDPR
        And Logged out from epoints.com
        And Registering the Vivup user in eachperson
        And Logging into epoints.com
        Then It should accept default password with change password screen
        And Login and logout successfully
        And Deleting the user in eachperson
        Then DynamoDB userGroup status of Vivup, epoints should be active and eachperson status should be deleted
        And Re-enabling the deleted Vivup user, in eachperson
        Then DynamoDB userGroup status of Vivup should be deleted and epoints, eachperson status should be active

    Scenario: PD-395 Registering in Vivup, later creating new user in eachperson with no default password via incremental
        Given User is registered in Vivup and did SSO with GDPR
        And Logged out from epoints.com
        And Registering the Vivup user in eachperson
        And Logging into epoints.com
        Then It should display setPassword screen
        And After setting the password
        And Without asking GDPR it has to login successfully
        And Profile page, Membership Type: Gold - Free Employee Account
        
