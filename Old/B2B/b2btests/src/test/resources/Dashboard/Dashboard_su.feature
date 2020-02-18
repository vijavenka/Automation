Feature: B2B Dashboard screen
	As a		Superuser client		
	I require	possibility to create and manage stores within my account		
	So that		I'd be able to configure content of the stores according to my needs		

	Background:
		Given Client logs in as superuser
		And Dashboard screen is displayed 
	
	Scenario: Check if DASHBOARD screen shows all required elements
		Given client has some campaign(s) set
		When Dashboard screen loads completely
		Then he can see Dashboard screen displayed				
		And Dasboard header is displayed				
		And grid/listview options are available				
		And one card per each campaign is displayed
	
	Scenario: Check if card for COMPLETED shop configuration shows all required elements
		Given client has some campaign(s) set
		And he has campaigns with shops configurations completed already
		When Dashboard screen loads completely
		Then he can see COMPLETED shop configurations (cards)
		And every COMPLETED shop card includes Site/campaign name, campaign ID, Started D&T, current step, address, 'Shop Manager' button

	Scenario: Superuser client starts a new shop configuration from DASHBOARD
		Given client has some campaign(s) set
		And he can see 'Create new shop' card
		When he clicks 'Create new shop' button
		Then he is taken to Wizard Step 0 screen

	Scenario: Superuser client enters SHOP MANAGER from DASHBOARD
		Given client has some campaign(s) set
		And he has campaigns with shops configurations completed already
		And he can see COMPLETED shops (cards)
		When he clicks 'Shop Manager' button
		Then he is taken to Manager 'Summary' screen
		
	Scenario: Check if card for STARTED/NOT COMPLETED shop configuration shows all required elements
		Given client has some campaign(s) set
		And he has campaigns with shops configurations started already (step 0)
		When Dashboard screen loads completely
		Then he can see STARTED/NOT COMPLETED shop configurations (cards)
		And every STARTED/NOT COMPLETED shop card includes Site/campaign name, campaign ID, Started D&T, current step, address, 'Continue' button
		
	Scenario: Superuser client continues previously started shop configuration
		Given client has some campaign(s) set
		And he has campaigns with shops configurations started already (step 0)
		When Dashboard screen loads completely
		And he clicks 'Continue' button on card with configuration started already
		Then he is taken to Shop Wizard, last not saved step (step 1)
		
		