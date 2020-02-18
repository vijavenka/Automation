Feature: From GSuite
I want Department hierarchy to be uploaded to ePoints Admin
To be able to add, edit, delete them

Scenario: New department hierarchy - ingest-service
Given New department is mocked into ingest-service
Then Verify the mocked data in JSON format from API
Then Verify the mocked data from AWS Queue
Then Verify the mocked data available in MySQL

Scenario: Edit department hierarchy - ingest-service
Given Edit department is mocked into ingest-service
Then Verify the edited mocked data in JSON format from API
Then Verify the edited mocked data from AWS Queue
Then Verify the edited mocked data available in MySQL

Scenario: Remove department hierarchy - ingest-service
Given Removed department is mocked into ingest-service
Then Verify the removed mocked data in JSON format from API
Then Verify the removed mocked data from AWS Queue
Then Verify the removed mocked data is not available in MySQL