Feature: Epoints your account page profile tab
  As an epoints user
  I want to have profile section in your account page profile tab
  So that I could set or change personal information, email, password

  # // 1.1 //  -------------------------------------------------------------------------- Your account - profile section
  # -------------------------------------------------------------------------------------------------- email row content
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - email row content
    Given Profile page is opened in '<partner>' context by 'default' user
    And Email address is properly displayed
    And Changing email fields are hidden
    When User click edit email button
    Then Changing email fields will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.2 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------------- fields required alerts
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - fields required alerts
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Email edit form is opened
    When User fill change email form with improper data 'newEmailAutomated'
    Then Email address is invalid alert will be presented
    When User remove previous entered new email data
    Then Email address is required alert will be presented

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.3 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------ emails do not match alert
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - fields required alerts
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Email edit form is opened
    When User fill email change form with two different emails
    And Click save new email button
    Then Emails do not match alert will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.4 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------------------ cancel button
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - cancel button
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Email edit form is opened
    When User click cancel setting new email button
    Then Changing email fields are hidden

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.5 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- validation for current email
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - validation for current email
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Email edit form is opened
    When User fill email change form with email which is already used
    And Click save new email button
    Then Email should be different from existing one message will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.6 //  -------------------------------------------------------------------------- Your account - profile section
  # ----------------------------------------------------------------------------------------- validation for taken email
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - validation for taken email
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Email edit form is opened
    When User fill email change form with email which is already taken
    And Click save new email button
    Then Email is in use message will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.7 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------------- email properly changed
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - email properly changed
    Given User is logged and profile page is opened with data '<oldEmail>' '<password>' in '<partner>' context
    Given Email edit form is opened
    When User fill change email form with proper data '<newEmail>'
    And Click save new email button
    Then Email properly changed alert will be displayed
    When User '<oldEmail>' will follow new email confirmation link
    Then Email will be properly changed
    And Email confirmation page has proper content '<newEmail>'

    Examples:
      | oldEmail                           | newEmail                           | password | partner |
      | united_test_do_not_remove@iat.test | emailwybitnietestowyxxx@gmail.com  | testing  | epoints |
      | emailwybitnietestowyxxx@gmail.com  | united_test_do_not_remove@iat.test | testing  | united  |

  # // 2.1 //  -------------------------------------------------------------------------- Your account - profile section
  # ----------------------------------------------------------------------------------------------- password row content
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - password row content
    Given Profile page is opened in '<partner>' context by 'default' user
    And Current password set data is properly displayed
    And Changing password fields are hidden
    When User click edit password button
    Then Changing password fields will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 2.2 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------------- fields required alerts
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - fields required alerts
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Password edit form is opened
    When User fill change password form with proper data 'password' 'passwordNew'
    And  User remove previous entered new password data
    Then Three fields are required alerts will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 2.3 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- passwords do not match alert
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - passwords do not match alert
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Password edit form is opened
    When User fill password change form with two different password
    And Click save new password button
    Then passwords do not match alert will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 2.4 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- wrong current password alert
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - wrong current password alert
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Password edit form is opened
    When User fill password change form with wrong current password
    And Click save new password button
    Then Wrong current password alert will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 2.5 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------------------ cancel button
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - cancel button
    Given Profile page is opened in '<partner>' context by 'default' user
    Given Password edit form is opened
    When User click cancel setting new password button
    Then Changing password fields are hidden

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 2.6 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------ password properly changed
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - password properly changed
    Given User is logged and profile page is opened with data '<email>' '<oldPassword>' in '<partner>' context
    Given Password edit form is opened
    When User fill change password form with proper data '<oldPassword>' '<newPassword>'
    And Click save new password button
    Then Password properly changed alert will be displayed

    Examples:
      | oldPassword | newPassword | email                              | partner |
      | testing     | Delete888   | united_test_do_not_remove@iat.test | epoints |
      | Delete888   | testing     | united_test_do_not_remove@iat.test | united  |

  # // 3.1 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- personal details row content
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - personal details row content
    Given Profile page is opened in '<partner>' context by 'default' user
    And User personal data are properly displayed
    When User click edit personal details button
    Then User personal data are properly displayed
    And Cancel button will be displayed in personal details module
    And Save button will be displayed in personal details module

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 3.2 //  -------------------------------------------------------------------------- Your account - profile section
  # ---------------------------------------------------------------------------- personal details fields required alerts
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - personal details fields required alerts
    Given Profile page is opened in '<partner>' context by 'default' user
    Given User click edit personal details button
    When User provide date of birth in wrong format
    And Click save personal details button
    Then Invalid date format alert will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 3.3 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------- personal details cancel button
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - personal details cancel button
    Given Profile page is opened in '<partner>' context by 'default' user
    Given User click edit personal details button
    When User click cancel setting new personal details button
    Then Edit view of personal details will be closed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 3.4 //  -------------------------------------------------------------------------- Your account - profile section
  # ---------------------------------------------------------------------------------- personal details properly changed
  @Regression @ProfilePage
  Scenario Outline: Your account - profile section - personal details properly changed
    Given Profile page is opened in '<partner>' context by 'default' user
    Given User click edit personal details button
    When User provide new personal details data
    And Click save personal details button
    Then New personal details data will be properly saved

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 4.1 //  -------------------------------------------------------------------------- Your account - contact section
  # ---------------------------------------------------------------------------------------- contact details row content
  @Regression @ProfilePage
  Scenario Outline: Your account - contact section - contact details  row content
    Given Profile page is opened in '<partner>' context by 'default' user
    And User contact data are properly displayed
    When User click edit contact details button
    Then User contact data are properly displayed
    And Cancel button will be displayed in contact details module
    And Save button will be displayed in contact details module
    And Find address button will be displayed in contact details module

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 4.2 //  -------------------------------------------------------------------------- Your account - contact section
  # ----------------------------------------------------------------------------------------------------- address finder
  @Regression @ProfilePage
  Scenario Outline: Your account - contact section - address finder
    Given Profile page is opened in '<partner>' context by 'default' user
    Given User click edit contact details button
    When User provides proper UK postcode
    And User click find address button
    Then DDL with addresses will be displayed
    And One of ddl addresses can be used to complete contact details

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 4.3 //  -------------------------------------------------------------------------- Your account - contact section
  # --------------------------------------------------------------------------------------- address finder wrong address
  @Regression @ProfilePage
  Scenario Outline: Your account - contact section - address finder wrong address
    Given Profile page is opened in '<partner>' context by 'default' user
    Given User click edit contact details button
    When User provides incorrect UK postcode
    And User click find address button
    Then Incorrect UK postcode provided alert will be displayed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 4.4 //  -------------------------------------------------------------------------- Your account - contact section
  # ------------------------------------------------------------------------------------------------------ cancel button
  @Regression @ProfilePage
  Scenario Outline: Your account - contact section - cancel button
    Given Profile page is opened in '<partner>' context by 'default' user
    Given User click edit contact details button
    When User click cancel setting new contact details button
    Then Edit view of contact details will be closed

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 4.5 //  -------------------------------------------------------------------------- Your account - contact section
  # ----------------------------------------------------------------------------------- contact details properly changed
  @Regression @ProfilePage
  Scenario Outline: Your account - contact section - contact details properly changed
    Given Profile page is opened in '<partner>' context by 'default' user
    Given User click edit contact details button
    When User provide new contact details data
    And Click save contact details button
    Then New contact details data will be properly saved

    Examples:
      | partner |
      | epoints |
      | united  |

  @Regression @ProfilePage
  Scenario Outline: Your account - preferences section - section content
    When Profile page is opened in '<partner>' context by 'default' user
    Then Preferences section is available
    And Preferences section contains opt in/out receiving emails checkbox

    Examples:
      | partner |
      | epoints |
      | united  |

  @Regression @ProfilePage
  Scenario Outline: Your account - preferences section - checkbox state changed
    Given Profile page is opened in '<partner>' context by 'default' user
    When Opt in/out receiving emails checkbox state will be changed
    Then Opt in/out receiving emails checkbox state will be persisted

    Examples:
      | partner |
      | epoints |
      | united  |

  @Regression @ProfilePage
  @deleteUserAfterTest
  Scenario: Your account - preferences section - checkbox state after join
    Given Complete account page is opened
    And User correctly fill all needed data
    And User select gender
    And Press done button
    When Profile page is opened in 'only epoints' context by 'created' user
    Then Opt in/out receiving emails checkbox is checked by default for new user

  @Regression @ProfilePage
  @deleteUserAfterTest
  Scenario Outline: PD-673 After coupon code, user subscribed for one-off fee along with subscription
    Given In epoints, new normal user is created
    And GOLDENTICKET coupon code is applied for ‘Gold 3 months FREE trial’
    Then my-account/profile/billingDetails API will return new invoice id, ‘<planAmount>’ and ‘<addonAmount>’
    And After 3 months of trial period will return to Silver
    Then my-account/profile/billingDetails API will not generate new invoice id
    And By subscribing Gold Monthly with one-off fee
    Then my-account/profile/billingDetails API will return new invoice id, ‘<planAmount>’ and ‘<addonAmount>’

  Examples:
  | planAmount | addonAmount|
  | 0.0        | null       |
  | 2.5        | 50         |

  @Regression @ProfilePage
  @deleteUserAfterTest
  Scenario Outline: PD-673 Directly subscribing for one-off fee along with subscription
    Given In epoints, new normal user is created
    And Gold monthly payment is made with one-off fee
    Then my-account/profile/billingDetails API will return new invoice id, ‘<planAmount>’ and ‘<addonAmount>’
    And After 1 month recurring starts
    Then my-account/profile/billingDetails API will return new invoice id, ‘<planAmount>’ and ‘<addonAmount>’

   Examples:
  | planAmount| addonAmount |
  | 2.5       | 50          |
  | 2.5       | null        |

  Scenario: PD-867 epoints, Silver user upgrades after coupon code expired
    Given In epoints Silver user logged in whose coupon code was expired
    And Upgrades to Gold user with One off joining fee and plan amount
    Then Chargebee invoice will be created
    And DynamoDB membership-transaction subscriptionDetails will have isAddonPaid true, paymentSuccess true
    And initialAmount and recycleAmount will be same


  Scenario: PD-867 epoints, Silver user upgrades after payment expired
    Given In epoints Silver user logged in whose payment was expired
    And Upgrades to Gold user with plan amount alone
    Then Chargebee invoice will be created
    And DynamoDB membership-transaction subscriptionDetails will have isAddonPaid true, paymentSuccess true
    And initialAmount and recycleAmount will be same