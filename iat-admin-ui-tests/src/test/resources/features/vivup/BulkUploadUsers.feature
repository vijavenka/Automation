Feature: Registering in Vivup, later creating new user in eachperson via Bulk upload

    Scenario: PD-393 Registering in Vivup, later creating new user in eachperson via Bulk upload
        Given User is registered in Vivup and did SSO with GDPR
        And Logged out from epoints.com
        And Registering the Vivup user in eachperson via Bulk upload
        And Logging into epoints.com
        Then It should accept default password with change password screen
        And Without asking GDPR it has to login successfully
        
    Scenario: PD-395 Registering in Vivup, later creating new user in eachperson with no default password via Bulk upload
        Given User is registered in Vivup and did SSO with GDPR
        And Logged out from epoints.com
        And Registering the Vivup user in eachperson via Bulk upload
        And Logging into epoints.com
        Then It should display setPassword screen
        And After setting the password
        And Without asking GDPR it has to login successfully        
