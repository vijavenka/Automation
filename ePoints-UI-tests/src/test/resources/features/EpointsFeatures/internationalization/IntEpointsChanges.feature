Feature: Epoints in new regions
  As an epoints user
  I want to have epoints.com page with proper content
  So that I could use epoints.com page in new regions like NL,SE,DK,FI,NO with changed/reduced content which is not
  required

  # // 1.1 //  ----------- EPOINTS INT - remove sign in by Facebook from the sign in UI for non-uk sub-domains(NBO-3247)
  # ----------------------------------------------------------------- EPOINTS INT - remove join option from UI(NBO-3248)
  # ----------------------------------------------------------- check if buttons was properly removed from sign in popup
  @Regression @Internationalization
  Scenario: EPOINTS INT - remove sign in by Facebook from the sign in UI for non-uk sub-domains(NBO-349) - check if button was properly removed from sign in popup
    Given ePoints page is opened in one of new regions
    When User click in sign in option
    Then Authentication panel is shown
    And Facebook sign in option is not available in new regions on sign in popup
    And Join option is not available in new regions on sign in popup

  # // 1.2 //  ----------- EPOINTS INT - remove sign in by Facebook from the sign in UI for non-uk sub-domains(NBO-3247)
  # ------------------------------------------------------------- check if button was properly removed from sign in page
  @Regression @Internationalization
  Scenario: EPOINTS INT - remove sign in by Facebook from the sign in UI for non-uk sub-domains(NBO-349) - check if button was properly removed from sign in page
    Given ePoints page is opened in one of new regions by identified user
    Given Sign in page is opened
    When User look on opened page
    Then He will see that facebook sign in option is not available in new regions on sign in page

  # // 2.1 //  ------------------------------------------------------ EPOINTS INT - remove join option from UI(NBO-3248)
  # ----------------------------------------------------------------- check if jon was removed from header and home page
  @Regression @Internationalization
  Scenario: EPOINTS INT - remove join option from UI(NBO-3248) - check if jon was removed from header and home page
    Given ePoints page is opened in one of new regions
    When User look on opened page
    Then He cannot see join option in header because it is not available in regions different than UK
    And He cannot see join option on home page because it is not available in regions different than UK, only sign in form

  # // 2.2 //  ------------------------------------------------------ EPOINTS INT - remove join option from UI(NBO-3248)
  # ------------------------------------------------------------------- check if jon page is not available by direct url
  @Regression @Internationalization
  Scenario: EPOINTS INT - remove join option from UI(NBO-3248) - check if jon page is not available by direct url
    Given ePoints page is opened in one of new regions
    When User try to reach jon page by using /join url
    Then He will be redirected to 404 page

  # // 3.1 //  ------ EPOINTS INT - remove Facebook and Twitter options from the footer for non-uk sub-domains(NBO-3250)
  # -------------------------------------------------------------------------- check if widgets were removed from footer
  @Regression @Internationalization
  Scenario: EPOINTS INT - remove Facebook and Twitter options from the footer for non-uk sub-domains(NBO-3250) - check if widgets were removed from footer
    Given ePoints page is opened in one of new regions
    When User look on opened page
    Then He cannot see facebook and twitter widgets because they are not available in regions different than UK

  # // 4.1 //  ------ EPOINTS INT - remove get section from non-UK sub-domain sites on both desktop and mobile(NBO-3251)
  # --------------------------------------------------------------------- check if Points option was removed from header
  @internationalization  @intEpointsChanges
  Scenario: EPOINTS INT - remove get section from non-UK sub-domain sites on both desktop and mobile(NBO-3251) - check if Points option was removed from header
    Given ePoints page is opened in one of new regions
    When User look on opened page
    Then He cannot see Points link because it is not available in regions different than UK

  # // 4.2 //  ------ EPOINTS INT - remove get section from non-UK sub-domain sites on both desktop and mobile(NBO-3251)
  # -------------------------------------------------- check if /get page and any subpage cannot be opened by direct url
  @Regression @Internationalization
  Scenario: EPOINTS INT - remove get section from non-UK sub-domain sites on both desktop and mobile(NBO-3251) - check if /get page and any subpage cannot be opened by direct url
    Given ePoints page is opened in one of new regions
    When User try to open points page or any other of not available subpages
    Then Each time he will be redirected to 404 page

  # // 5.1 //  ----------------- EPOINTS INT - remove partner page link and Ui from non-uk sub domain requests(NBO-3253)
  # ------------------------------------------------------------------- check if partner link is not available in footer
  @Regression @Internationalization
  Scenario: EPOINTS INT - remove partner page link and Ui from non-uk sub domain requests(NBO-3253) - check if partner link is not available on footer
    Given ePoints page is opened in one of new regions
    When User look on opened page
    Then He cannot see partner link in footer because it is not available in regions different than UK


  # // 6.1 //  -- EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253)
  # ------------------------------------------------------------------- check if user account area elements were removed
  @Regression @Internationalization
  Scenario: EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253) - check if user account area elements were removed
    Given My epoints page is opened in one of new regions
    When User look on opened page
    Then He cannot see get section link because it is not available in regions different than UK
    And He cannot see you top sites section because it is not available in regions different than UK
    And He cannot see buy epoints link because it is not available in regions different than UK

  # // 6.2 //  -- EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253)
  # ---------------------------------------------------------- check if /buy-epoints page cannot be opened by direct url
  @Regression @Internationalization
  Scenario: EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253) - check if /buy-epoints page cannot be opened by direct url
    Given ePoints page is opened in one of new regions
    When User try to reach purchase epoints page by using /buy-epoints url
    Then He will be redirected to 404 page

  # // 7.1 //  - EPOINTS INT - use a different About epoints page format for non-uk sub domain users on desktop and mobile(NBO-3254)
  # ---------------------------------------------------------------------- check content of about us page of each region
  @Regression @Internationalization
  Scenario Outline: EPOINTS INT - use a different About epoints page format for non-uk sub domain users on desktop and mobile(NBO-3254) - check content of about us page of each region
    Given About us page of chosen region '<region>' is opened
    When User look on opened page
    Then He will see that about us page of chosen region '<region>' has proper content

    Examples:
      | region |
      | NL     |
#    |SE|
#    |DK|
#    |FI|
#    |NO|

  # // 9.1 //  -- EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253)
  # ------------------------------------------------------------------ check if proper user account sections were hidden
  @Regression @Internationalization
  Scenario: EPOINTS INT - restrict profile functionality for non-uk users(NBO-3441) - check if proper user account sections were hidden
    Given Profile page is opened in one of new regions
    When User look on opened page
    Then He cannot see edit email button because it is not available in regions different than UK
    And He cannot see personal details section because it is not available in regions different than UK
    And He cannot see contact details section because it is not available in regions different than UK
    And He can see change your password section

  # // 10.1 //  ------------- EPOINTS INT - create specific home page format for non-uk sub-domains on DESKTOP(NBO-3397)
  # -------------------------------------------------------------------------------------- new regions home page content
  @Regression @Internationalization
  Scenario: EPOINTS INT - create specific home page format for non-uk sub-domains on DESKTOP(NBO-3397) - new regions home page content
    Given ePoints page is opened in one of new regions
    When User look on opened page
    Then He will see sign in module with email and password input fields
    And He will see currency image section
    And He will see catalogue image section
    And He will see spend image section
