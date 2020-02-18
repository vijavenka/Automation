Feature: B2B Dashboard screen
	As a		Superuser client		
	I require	possibility to create and manage stores within my account		
	So that		I'd be able to configure content of the stores according to my needs		

	Background:
		Given Client logs in as superuser
		And Dashboad screen is displayed 
	
	Scenario: (OK) Check if DASHBOARD screen shows all required elements
		Given client has some campaign(s) set
		When Dashboad screen loads completely			
		Then he can see Dashboard screen displayed				
		And Dasboard header is displayed				
		And grid/listview options are available				
		And one card per each campaign is displayed
	
	Scenario: (OK) Check if card for COMPLETED shop configuration shows all required elements
		Given client has some campaign(s) set
		And he has campaigns with shops configurations completed already
		When Dashboad screen loads completely
		Then he can see COMPLETED shop configurations (cards)
		And every COMPLETED shop card includes Site/campaign name, campaign ID, Started D&T, current step, address, 'Shop Manager' button





	Scenario: Check if card for NOT STARTED shop configuration shows all required elements
		Given client has some campaign(s) set
		And he has campaigns without shops configured for yet
		When Dashboad screen loads completely
		Then he can see NOT STARTED shop configurations (cards)
		And every NOT STARTED shop card includes Site/campaign name, campaign ID, 'Add a shop' button

	Scenario: Check if card for STARTED/NOT COMPLETED shop configuration shows all required elements
		Given client has some campaign(s) set
		And he has campaigns with shops configurations started already
		When Dashboad screen loads completely
		Then he can see STARTED/NOT COMPLETED shop configurations (cards)
		And every STARTED/NOT COMPLETED shop card includes Site/campaign name, campaign ID, Started D&T, current step, address, 'Continue' button

	Scenario: Webgains client starts a new shop configuration from DASHBOARD
		Given client has some campaign(s) set
		And he has campaigns without shops configured for yet
		When he clicks 'Add a shop' button
		Then he is taken to Wizard Step 1 screen
