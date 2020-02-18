Feature: From GSuite 
I want Users to be uploaded to ePoints Admin
To be able add, edit, delete them

Scenario Outline: New User is created and validating duplicate user - ingest-service
Given New user is mocked into ingest-service with "<employeeNumber>" "<email>" "<name>" "<gender>" "<birthDate>" "<deptName>" "<role>" "<adminRole>" "<companyStartDate>"
Given Duplicate user is mocked into ingest-service "<employeeNumber>" "<email>" "<name>" "<gender>" "<birthDate>" "<deptName>" "<role>" "<adminRole>" "<companyStartDate>"
Then Verify the mocked data (new and duplicate) in JSON format from API
Then Verify the mocked (new and duplicate) data from AWS Queue
Then Verify the mocked data available in MySQL
Then Verify the mocked duplicate data is rejected

Scenario Outline: User is edited - ingest-service
Given Edit user is mocked into ingest-service with "<edit user>" "<edit department>"
Then Verify the edited mocked data in JSON format from API
Then Verify the edited mocked data from AWS Queue
Then Verify the edited mocked data available in MySQL

Scenario Outline: User is deleted - ingest-service
Given Deleted user is mocked into ingest-service "<delete user>"
Then Verify the deleted mocked data in JSON format from API
Then Verify the deleted mocked data from AWS Queue
Then Verify the deleted mocked data status is inactive in database

Examples:
|employeeNumber|email|name|gender|birthDate|deptName|role|adminRole|companyStartDate|edit user|edit department|delete user|
|e0001|e0001@wp.pl|e0001 Alex|MALE|1986-12-27|/Quality Assurance|USER|NONE|1995-01-01|Alexx|/Quality Assurance/Manager|Alexx|
|e0002|e0002@wp.pl|e0002 Bob|MALE|1986-12-27|/Quality Assurance/Mobile Team|USER|ADMIN|1995-01-01|Bobb|/Quality Assurance/Testing/Mobile Team|Bobb|
|e0003|e0003@wp.pl|e0003 Chan|MALE|1986-12-27|/Quality Assurance/Mobile Team/Android Team|USER|SUPER_ADMIN|1995-01-01|Chann|/Quality Assurance/Testing/Mobile Team/Android Team/Sr. Tester|Chann|
|e0004|e0004@wp.pl|e0004 Dan|MALE|1986-12-27|/Quality Assurance/Mobile Team/Android Team|MANAGER|NONE|1995-01-01|Dann|/Quality Assurance/Testing/Mobile Team/Android Team/Jr. Tester|Dann|
|e0005|e0005@wp.pl|e0005 Ern|MALE|1986-12-27|/Quality Assurance/Mobile Team/iOS Team|MANAGER|ADMIN|1995-01-01|Dann|/Quality Assurance/Testing/Mobile Team/iOS Team|Dann|
|e0006|e0006@wp.pl|e0006 Fazil|MALE|1986-12-27|/Development/Mobile Team/Android Team|MANAGER|ADMIN|1995-01-01|Fazill|/Tech Lead/Development/Mobile Team/Android Team|Fazill|
|e0007|e0007@wp.pl|e0007 Gag|MALE|1986-12-27|/Development/Mobile Team/iOS Team|MANAGER|SUPER_ADMIN|1995-01-01|Gagg|/Tech Lead/Development/Mobile Team/iOS Team|Gagg|





|new user	|new department									|	edit user			|edit department												| delete user	|
|Alex		|/Quality Assurance								|	Alexx				|/Quality Assurance/Manager										|	Alexx		|
|Bob		|/Quality Assurance/Mobile Team					|	Bobb				|/Quality Assurance/Testing/Mobile Team							|	Bobb		|
|Chan		|/Quality Assurance/Mobile Team/Android Team	|	Chann				|/Quality Assurance/Testing/Mobile Team/Android Team/Sr. Tester	|	Chann		|
|Dan		|/Quality Assurance/Mobile Team/Android Team	|	Dann				|/Quality Assurance/Testing/Mobile Team/Android Team/Jr. Tester	|	Dann		|
|Ern		|/Quality Assurance/Mobile Team/iOS Team		|	Ernn				|/Quality Assurance/Testing/Mobile Team/iOS Team				|	Ernn		|
|Fazil		|/Development/Mobile Team/Android Team			|	Fazill				|/Tech Lead/Development/Mobile Team/Android Team				|	Fazill		|
|Gag		|/Development/Mobile Team/iOS Team				|	Gagg				|/Tech Lead/Development/Mobile Team/iOS Team					|	Gagg		|

