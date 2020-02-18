Feature: B2B Wizard Step 0 screen
	As a		Webgains client		
	I require	possibility to create and manage stores within my account		
	So that		I'd be able to configure content of the stores according to my needs		

	Background:
		Given Client logs in as superuser
		And Dashboad screen is displayed 
	
	Scenario: Check if Wizard screen 0 shows all required elements
		Given client has some campaign(s) set
		And he has campaigns without shops configured for yet
		When he clicks 'Add a shop' button
		Then he is taken to Wizard Step 0 screen
		And screen includes: header, info text, shop address, shop url field, 'Build your shop' button
	
	
	Scenario: Client leaves empty epoints.com subdomain and tries to continue	
		Given client has some campaign(s) set
		And he has campaigns without shops configured for yet
		And he clicked 'Add a shop' button
		And he has been taken to Wizard Step 0 screen
		When he clears sub-domain field
		And he clicks 'Build your shop' button
		Then appropriate warning message is displayed
		And shop configuration is NOT created in mongo db
		And he is taken to Wizard Step 0 screen
	
	
	Scenario Outline: Webgains provides incorrect epoints.com subdomain and tries to continue	
		Given client has some campaign(s) set
		And he has campaigns without shops configured for yet
		And he clicked 'Add a shop' button
		And he has been taken to Wizard Step 0 screen
		When he provides incorrect epoints.com subdomain (<string_domain>)
		And he clicks 'Build your shop' button
		Then appropriate warning message is displayed
		And shop configuration is NOT created in mongo db
		And he is taken to Wizard Step 0 screen
		
	Examples:
		| string_domain			|
		| incorrect subdomain	|
		| !@#$%^&*()			|
		
	
	Scenario: Webgains provides duplicated epoints.com subdomain and tries to continue	
		Given client has some campaign(s) set
		And he has campaigns without shops configured for yet
		And he knows existing shop URL address
		And he clicked 'Add a shop' button
		And he has been taken to Wizard Step 0 screen
		When he provides duplicated epoints.com sub-domain
		And he clicks 'Build your shop' button
		Then appropriate warning message is displayed
		And shop configuration is NOT created in mongo db
		And he is taken to Wizard Step 0 screen
		
	
	Scenario: Webgains client continues shop configuration to step 2 with default sub-domain	
		Given client has some campaign(s) set
		And he has campaigns without shops configured for yet
		And he clicked 'Add a shop' button
		And he has been taken to Wizard Step 0 screen
		When he clicks 'Build your shop' button
		Then he is taken to Wizard Step 1 screen
		And default sub-domain is correctly saved and displayed in dashboard