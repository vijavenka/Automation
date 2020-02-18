Feature: Epoints specsavers page
  As an epoints user
  I want to have specsavers page
  So that I could add epoints to selected accounts because of different reasons

  # // 1.1 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
  # ------------------------------------------------------------------------------------------------- login page content
  @Regression @Specsavers
  Scenario: SPECSAVERS - create admin page and required security(NBO-740) - login page content
    Given Specsavers login page is opened
    Then He can see that specsavers login page contains all needed elements

  # // 1.2 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
  # ---------------------------------------------------------------------------------------------- required input alerts
  @Regression @Specsavers
  Scenario: SPECSAVERS - create admin page and required security(NBO-740) - required input alerts
    Given Specsavers login page is opened
    When User type some login data and remove them
    Then Required alert will be displayed for both inputs

  # // 1.3 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
  # ------------------------------------------------------------------------------------------------------ invalid email
  @Regression @Specsavers
  Scenario: SPECSAVERS - create admin page and required security(NBO-740) - invalid email
    Given Specsavers login page is opened
    When User type invalid email into email field
    Then Email is invalid alert will be displayed under email input

  # // 1.4 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
  # ----------------------------------------------------------------------------------------------- not registered email
  @Regression @Specsavers
  Scenario: SPECSAVERS - create admin page and required security(NBO-740) - not registered email
    Given Specsavers login page is opened
    When User type not registered email into email field
    And Click sign in button on specsaver login page
    Then Authorization alert will be displayed under password input

  # // 1.5 //  ------------------------------------------- SPECSAVERS - create admin page and required security(NBO-740)
  # ------------------------------------------------------------------------------------------------------- proper login
  @Regression @Specsavers
  Scenario: SPECSAVERS - create admin page and required security(NBO-740) - proper login
    Given Specsavers login page is opened
    When User type correct specsaver user login data
    And Click sign in button on specsaver login page
    Then He will be properly logged into admin panel
    When User click sign out button in specsavers control panel
    Then He will be properly logged out form specsavers control panel

  # // 2.1 //  ------------------------------------------------------------------------------ SPECSAVERS - control panel
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @Specsavers
  Scenario: SPECSAVERS - control panel - page content
    Given Specsavers control panel is opened
    Then He can see that specsavers control panel contains all needed elements

  # // 2.2 //  ------------------------------------------------------------------------------ SPECSAVERS - control panel
  # ---------------------------------------------------------------------------------------------------- required alerts
  @Regression @Specsavers
  Scenario: SPECSAVERS - control panel - required alerts
    Given Specsavers control panel is opened
    When User select branch
    And Remove selected branch
    And User select tag
    And Remove selected tag
    And Enter reason for awarding
    And Remove reason for awarding
    And Enter some points to award value
    And Remove points to award value
    Then Under each section should appear field is required alert

  # // 3.1 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
  # -------------------------------------------------------------------------------------------------- select all button
  @Regression @Specsavers
  Scenario: SPECSAVERS - admin page - select a branch(NBO-741) - select all button
    Given Specsavers control panel is opened
    When User use select all checkbox
    Then pick Branches Input Field will be disabled

  # // 3.2 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
  # ---------------------------------------------------------------------------------------- selecting/removing branches
  @Regression @Specsavers
  Scenario: SPECSAVERS - admin page - select a branch(NBO-741) - selecting/removing branches
    Given Specsavers control panel is opened
    When User want to select a branch
    Then He can select few branches manually
    And See that branches were properly added to selected list
    When User want to remove branches
    Then He can remove few selected branches manually
    And See that branches were properly removed from selected list

  # // 3.3 //  ------------------------------------------------------ SPECSAVERS - admin page - select a branch(NBO-741)
  # -------------------------------------------------------------------------------------------- correctness of branches
  @Regression @Specsavers
  Scenario: SPECSAVERS - admin page - select a branch(NBO-741) - correctness of branches
    Given Specsavers control panel is opened
    When Expand branches DDL
    Then User can see that set of branches is properly returned

  # // 4.2 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
  # ---------------------------------------------------------------------------------------------------- points counting
  @Regression @Specsavers
  Scenario: SPECSAVERS - admin page - points to award(NBO-743) - points counting
    Given Specsavers control panel is opened
    When User enter proper £ value into points to award input field
    Then This value will be properly changed int epoints

  # // 4.3 //  ------------------------------------------------------ SPECSAVERS - admin page - points to award(NBO-743)
  # -------------------------------------------------------------------------------------------- modal content/no button
  @Regression @Specsavers
  Scenario: SPECSAVERS - admin page - points to award(NBO-743) - modal content/no button
    Given Specsavers control panel is opened
    Given Branches are chosen
    Given Tag is chosen
    Given Reason for awarding points is chosen
    Given Points to award value is entered
    When User click award points button
    Then Confirm points modal window with proper content will be displayed
    When User click no button in confirm modal window
    Then Confirm points modal window will be closed

  # // 5.1 //  -------------------------------------------------- SPECSAVERS - admin page - reason for awarding(NBO-742)
  # --------------------------------------------------------------------------------------------- correctness of reasons
  @Regression @Specsavers @automatedTestsReasonsAreDeletedAfter
  Scenario: SPECSAVERS - admin page - reason for awarding(NBO-742) - correctness of reasons
    Given Specsavers control panel is opened
    When Expand reason for awarding DDL
    Then User can see that set of reasons is properly returned

  # // 6.1 //  ------------------------------------------------- SPECSAVERS - award points to selected branches(NBO-744)
  # --------------------------------------------------------------------- correct points award, checking database update
  @Regression @Specsavers
  Scenario: SPECSAVERS - award points to selected branches(NBO-744) - correct points award, checking database update
    Given Specsavers control panel is opened
    Given Branches are chosen
    Given Tag is chosen
    Given Reason for awarding points is chosen
    Given Points to award value is entered
    When User click award points button
    And User confirm points award by clicking yes button in confirmation modal
    Then Confirm points modal window will be closed
    And Award success message will be displayed
    And Points will be properly awarded to chosen branches

  # // 7.1 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # -------------------------------------------------------------------------------------------- bulk upload tab content
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - bulk upload tab content
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User look on import tab
    Then He will see field for file selection
    And He will see import button

  # // 7.2 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # ----------------------------------------------------------------------------------------------------- correct upload
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - correct upload
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with correct data
    And Click import button
    Then All file rows will be properly uploaded

  # // 7.3 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # ------------------------------------------------------------------------------------------------ incorrect file type
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - wrong file format
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with incorrect data
    Then Incorrect file format alert will be displayed

  # // 7.4 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # ------------------------------------------------------------------------------------------------------- missing name
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - missing name
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with missing name
    And Click import button
    Then Missing name alert will be displayed

  # // 7.5 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # ------------------------------------------------------------------------------------------------------ missing email
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - missing email
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with missing email
    And Click import button
    Then Missing email alert will be displayed

  # // 7.6 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # ----------------------------------------------------------------------------------------------------- missing reason
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - missing reason
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with missing reason
    And Click import button
    Then Missing reason alert will be displayed

  # // 7.7 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # ----------------------------------------------------------------------------------------------------- missing amount
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - missing amount
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with missing amount
    And Click import button
    Then Missing amount alert will be displayed

  # // 7.8 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # ------------------------------------------------------------------------------------------------------ missing admin
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - missing admin
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with missing admin
    And Click import button
    Then Missing admin alert will be displayed

  # // 7.9 //  --------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # -------------------------------------------------------------------------------------------------------- wrong email
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - wrong email
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with wrong email
    And Click import button
    Then Wrong email alert will be displayed

  # // 7.10 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # --------------------------------------------------------------------------------------------------------- low amount
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - low amount
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with too low amount
    And Click import button
    Then Wrong too low amount alert will be displayed

  # // 7.11 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # --------------------------------------------------------------------------------------------------- very long reason
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - very long reason
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with very long reason
    And Click import button
    Then Points will be properly awarded and reason truncated to 255 signs

  # // 7.12 //  -------------------------------------- SPECSAVERS - add bulk upload option to their admin form(NBO-1048)
  # --------------------------------------------------------------------------------------------------- incorrect header
  @Regression @Specsavers
  Scenario: SPECSAVERS - add bulk upload option to their admin form(NBO-1048) - incorrect header
    Given Specsavers control panel is opened
    Given Import tab is selected
    When User provide file with incorrect header
    And Click import button
    Then Wrong header alert will be displayed

  #reporting

  # // 8.1 //  ------ SPECSAVERS - create the reporting tab within Specsavers admin UI adding "view" structure(NBO-1612)
  # ---------------------------------------------------------------------------------------------- reporting tab content
  @Regression @Specsavers
  Scenario: SPECSAVERS - create the reporting tab within Specsavers admin UI adding "view" structure(NBO-1612) - reporting tab content
    Given Specsavers control panel is opened
    And User click on reporting tab
    When Reporting tab will be opened
    And Report is generated with random start and end dates
    Then Reporting tab contains proper elements

  # // 9.1 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
  # ----------------------------------------------------------------------------------------------- default dates values
  @Regression @Specsavers
  Scenario: SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613) - default dates values
    Given Specsavers control panel is opened
    When User click on reporting tab
    Then Reporting tab will be opened
    And Dates fields will be automatically filled with default values

  # // 9.2 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
  # ------------------------------------------------------------------------------------------------- dates between tabs
  @Regression @Specsavers
  Scenario: SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613) - dates between tabs
    Given Specsavers control panel reports tab is opened
    When User will navigate between three available report tabs
    Then Dates will stay the same

  # // 9.3 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
  # ---------------------------------------------------------------------------------------------- calendar main buttons
  @Regression @Specsavers
  Scenario Outline: SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613) - calendar main buttons
    Given Specsavers control panel reports tab is opened
    When User open calendar picker
    And Click '<button>' on calendar view
    Then Proper action will be executed after clicking '<button>'

    Examples:
      | button |
      | today  |
      | clear  |
      | close  |

  # // 9.4 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
  # --------------------------------------------------------------------------------------------- choosing calendar date
  @Regression @Specsavers
  Scenario: SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613) - choosing calendar date
    Given Specsavers control panel reports tab is opened
    When User open calendar picker
    And User chose some date from calendar
    Then Field will be filled with proper date

  # // 9.5 //  ------- SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613)
  # ---------------------------------------------------------------------------------------- filling wrong dates by hand
  @Regression @Specsavers
  Scenario Outline: SPECSAVERS - add date pickers to the reporting tab that control data set in all 3 views(NBO-1613) - filling wrong dates by hand
    Given Specsavers control panel reports tab is opened
    When User fill start and end dates with not allowed values '<startDate>' '<endDate>'
    Then Please specify correct date range alert will be displayed
    And Generate report button will be disabled

    Examples:
      | startDate  | endDate    |
      | 06-05-2020 | 15-05-2015 |
      | 06-05-2015 | 15-05-2020 |
      | 06-05-2015 | 05-05-2015 |

  # // 10.1 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
  # ------------------------------------------------------------------------------------------------------- page content
  # // Update ------------------------------- SPECSAVERS ADMIN - add tag info to reporting, UI and Spreadsheet(NBO-4013)
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615) - page content
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    When User look on epoints awarded section
    Then He can see that it contains table with proper columns
    And Summary values are available

  # // 10.2 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
  # --------------------------------------------------------------------------------------------- table data correctness
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615) - table data correctness
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    Given Report is generated with random start and end dates
    When User look on epoints awarded section
    Then He can see that all awarded section data is correct according to database

  # // 10.3 //  ------------- SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615)
  # ------------------------------------------------------------------------------------------------------------ summary
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "epoints rewarded breakdown view"(NBO-1615) - summary
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    Given Report is generated with random start and end dates
    When User look on epoints awarded section
    Then He can see that epoints and value summary are correct in awarded section

  # // 11.1 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616) - page content
    Given Specsavers control panel reports tab is opened
    Given Epoints redeemed section is opened
    When User look on epoints redeemed section
    Then He can see that redeemed section contains table with proper columns
    And Summary values are available

  # // 11.2 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
  # --------------------------------------------------------------------------------------------- table data correctness
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616) - table data correctness
    Given Specsavers control panel reports tab is opened
    Given Epoints redeemed section is opened
    Given Report is generated with random start and end dates
    When User look on epoints redeemed section
    Then He can see that all redemeed section data is correct according to database

  # // 11.3 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
  # ---------------------------------------------------------------------------------------------------------- open link
  @Regression @Specsavers @NBO-10020
  Scenario: SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616) - open link
    Given Specsavers control panel reports tab is opened
    Given Epoints redeemed section is opened
    Given Report is generated with random start and end dates
    When User click open link of chosen row
    Then Product page of redeemed product will be opened in new window

  # // 11.4 //  ------------- SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616)
  # ------------------------------------------------------------------------------------------------------------ summary
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "epoints redeemed breakdown view"(NBO-1616) - summary
    Given Specsavers control panel reports tab is opened
    Given Epoints redeemed section is opened
    Given Report is generated with random start and end dates
    When User look on epoints redeemed section
    Then He can see that epoints and value summary are correct

  # // 12.1 //  ------------------------------ SPECSAVERS - populate required data and UI for "overview view".(NBO-1614)
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "overview view".(NBO-1614) - page content
    Given Specsavers control panel reports tab is opened
    Given Epoints overview section is opened
    When User look on epoints overview section
    Then He can see that overview section contains table with proper columns

  # // 12.2 //  ------------------------------ SPECSAVERS - populate required data and UI for "overview view".(NBO-1614)
  # --------------------------------------------------------------------------------------------- table data correctness
  @Regression @Specsavers
  Scenario: SPECSAVERS - populate required data and UI for "overview view".(NBO-1614) - table data correctness
    Given Specsavers control panel reports tab is opened
    Given Epoints overview section is opened
    Given Report is generated with random start and end dates
    When User look on epoints overview section
    Then He can see that all overview section data is correct according to database

  # // 13.1 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
  # --------------------------------------------------------------------------------- availability of pagination options
  @Regression @Specsavers
  Scenario: SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675) - availability of pagination options
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    Given Report is generated with random start and end dates
    When User look on epoints awarded section
    Then He can see pagination component in reporting tab
    When Epoints redeemed section will be opened
    Then He can see pagination component in reporting tab

  # // 13.2 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
  # ------------------------------------------------------------------- top pagination, working of next/previous buttons
  @Regression @Specsavers
  Scenario: SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675) - top pagination, working of next/previous buttons
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    Given Report is generated with random start and end dates
    Given Visible rows number is set to 20
    When User click next page button on specsavers awarded section
    Then Page will be changed to next on specsavers awarded section
    And Showing module will be increased on specsavers awarded section
    When User click previous page button on specsavers awarded section
    Then Showing module will be decreased on specsavers awarded section
    And Page will be changed to previous on specsavers awarded section

  # // 13.3 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
  # ------------------------------------------------------------------ bottom pagination, working of page numbers button
  @Regression @Specsavers
  Scenario: SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675) - bottom pagination, working of page numbers button
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    Given Report is generated with random start and end dates
    Given Visible rows number is set to 20
    When User looks on bottom pagination component
    Then He can see tat proper number of page is displayed according to 'out of' information on specsavers awarded section
    When User use some bottom pagination page number on specsavers awarded section
    Then proper page will be displayed on specsavers awarded section

  # // 13.4 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
  # ---------------------------------------------------------------- bottom pagination, working of next/previous buttons
  @Regression @Specsavers
  Scenario: SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675) - bottom pagination, working of next/previous buttons
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    Given Report is generated with random start and end dates
    Given Visible rows number is set to 20
    When User click next page bottom button on specsavers awarded section
    Then Page will be changed to next on specsavers awarded section
    And Showing module will be increased on specsavers awarded section
    When User click previous page bottom button on specsavers awarded section
    Then Showing module will be decreased on specsavers awarded section
    And Page will be changed to previous on specsavers awarded section

  # // 13.5 //  --------------------------- SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675)
  # ------------------------------------------------------------------------------------- top pagination, items per page
  @Regression @Specsavers
  Scenario: SPECSAVERS - add pagination component to epoints and redeemed tabs(NBO-1675) - top pagination, items per page
    Given Specsavers control panel reports tab is opened
    Given Epoints awarded section is opened
    Given Report is generated with random start and end dates
    When User change 'item per page' parameter on specsavers awarded section
    Then Number of displayed elements on specsavers awarded section will be changed

  # // 14.1 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
  # ---------------------------------------------------------------------------------------------- new store tab content
  @Regression @Specsavers
  Scenario: SPECSAVERS - add functionality to specsavers admin to add new stores as epoints user(NBO-3485) - new store tab content
    Given Specsavers store tab is opened
    When User looks on specsavers store tab
    Then He can see store name input field with proper label
    And He can see email input field with proper label
    And Cancel and send button is properly displayed

  # // 14.2 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
  # ---------------------------------------------------------------------------------------- wrong email structure alert
  @Regression @Specsavers
  Scenario: SPECSAVERS - add functionality to specsavers admin to add new stores as epoints user(NBO-3485) - wrong email structure alert
    Given Specsavers store tab is opened
    When User provide not correct email
    Then Email is incorrect alert will be displayed under new store email field

  # // 14.3 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
  # -------------------------------------------------------------------------------------------------------- taken email
  @Regression @Specsavers
  Scenario: SPECSAVERS - add functionality to specsavers admin to add new stores as epoints user(NBO-3485) - taken email
    Given Specsavers store tab is opened
    When User provide new store email which is already used
    And User provide new store name
    And User clicks send button on new store form
    Then Email is already in use alert will be displayed under new store email field

  # // 14.4 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
  # --------------------------------------------------------------------------------------------------- taken store name
  @Regression @Specsavers
  Scenario: SPECSAVERS - add functionality to specsavers admin to add new stores as epoints user(NBO-3485) - taken store name
    Given Specsavers store tab is opened
    When User provide new store email
    And User provide new store name which is already used
    And User clicks send button on new store form
    Then Store name is already in use alert will be displayed under new store email field

  # // 14.5 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
  # --------------------------------------------------------------------------------------------------- clear all button
  Scenario: SPECSAVERS - add functionality to specsavers admin to add new stores as epoints user(NBO-3485) - clear all button
    Given Specsavers store tab is opened
    When User provide new store email
    And User provide new store name
    And User click clear new store data button
    Then New store input fields will be cleared

  # // 14.6 //  ---- SPECSAVER ADMIN - add functionality to specsavers admin to add new stores as epoints user(NBO-3485)
  # --------------------------------------------------------------------------------------------- correct store creation
  @Regression @Specsavers
  Scenario: SPECSAVERS - add functionality to specsavers admin to add new stores as epoints user(NBO-3485) - correct store creation
    Given Specsavers store tab is opened
    When User provide new store email
    And User provide new store name
    And User clicks send button on new store form
    Then New store (points manager user) will be properly created
    And For new user will get '50' points for registration
