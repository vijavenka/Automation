Feature: B2B 
	As a		Webgains client		
	I require	possibility to create and manage stores within my account		
	So that		I'd be able to configure content of the stores according to my needs		

	Background:
		Given Client logs in as superuser
		And Dashboard screen is displayed
	
	Scenario: Superuser client continues previously started shop configuration
		Given client has some campaign(s) set
		And he has campaigns with shops configurations started already (step 0)
		When Dashboard screen loads completely
		And he clicks 'Continue' button on card with configuration started already
		Then he is taken to Shop Wizard, last not saved step (step 1)