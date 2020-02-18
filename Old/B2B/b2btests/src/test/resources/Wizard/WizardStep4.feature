Feature: B2B Wizard Step 4 screen
	As a		Webgains client		
	I require	possibility to create and manage stores within my account		
	So that		I'd be able to configure content of the stores according to my needs		

	Background:
		Given Client logs in as superuser
		And Dashboad screen is displayed
		
	
	Scenario: Check if Wizard screen 4 shows all required elements
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		When he clicks 'Continue' button to get to step 4
		Then he is taken to Wizard Step 4 screen	
		And screen 4 includes: header, info text, shop address, shop url, wizard steps
		And screen 4 includes: colours section with main/font colour fields, preview, reset button
		And screen 4 includes: logo image upload facility
		And screen 4 includes: no header, header image, header html, header link sections with corresponding fields
		And screen 4 includes: no footer, footer image, footer html, footer link sections with corresponding fields
		And screen 4 includes: return to dashboard, live preview, save and continue buttons
	
	
	Scenario: User moves to next step without making any changes in 'branding' screen using 'save and continue' button
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he doesn't make any changes in the step
		And he clicks 'Save and continue' button
		Then he is taken to Wizard Step 5 screen
		
	
	Scenario: User moves to next step by choosing it from steps ribbon
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he clicks on step 6 inactive button in wizard steps ribbon
		Then he is taken to Wizard Step 4 screen
	
	
	Scenario: User selects button colors entering color codes manually
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he enters 'main' color code manually
		And he enters 'font' color code manually
		Then he is able to enter any color code for each of the properties
		And selected colors are reflected on live preview image
		And changed colors are saved correctly after step is saved
		
	
	Scenario: User makes changes to button color and then restores them to defaults and saves
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he enters 'main' color code manually
		And he enters 'font' color code manually
		And he resets colors to default settings
		Then color fields values are set to default
		And original colors are reflected on live preview image
		And original colors are saved correctly after step is saved
		
	
	Scenario: (1) User uploads logo image and switches to the next step
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he uploads acceptable image as shop logo
		Then image preview is displayed in 'Brand logo' section
		And logo is saved correctly after step is saved
	
	
	Scenario: (2) User provides header HTML code and saves step
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he opens 'Paste HTML' tab in 'Custom Header' section
		And he provides HTML code
		Then header HTML code is saved correctly after step is saved
	
	
	Scenario: (3) User provides footer HTML code and saves step
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he opens 'Paste HTML' tab in 'Custom Footer' section
		And he provides footer HTML code
		Then footer HTML code is saved correctly after step is saved
		
		
	Scenario: (4) User provides header sever link and saves
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he opens 'Server link' tab in 'Custom Header' section
		And he provides header server link
		Then header server link is saved correctly after step is saved
	
	Scenario: (5) User provides footer sever link and saves
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he opens 'Server link' tab in 'Custom Footer' section
		And he provides footer server link
		Then footer server link is saved correctly after step is saved
		
	
	Scenario: (6) User uploads header image and switches to the next step
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he opens 'Header image' tab in 'Custom Header' section
		And he uploads acceptable header image 
		Then header image preview is displayed
		And header image is saved correctly after step is saved
	
	
	Scenario: (7) User uploads footer image and switches to the next step
		Given client has some campaign(s) set
		And he has campaigns with shops configurations stopped at step 4
		And he clicked 'Continue' button to get to step 4
		And he has been taken to Wizard Step 4 screen
		When he opens 'Footer image' tab in 'Custom Footer' section
		And he uploads acceptable footer image 
		Then footer image preview is displayed
		And footer image is saved correctly after step is saved
		