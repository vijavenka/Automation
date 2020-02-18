Feature: User Authentication
  As an User
  I want to have possibility to authenticated
  So that I will be able to use restricted content

  @UserAuthentication @PositiveCase
  @Regression
  Scenario Outline: User authentication - authenticate user
    Given User Manager API is responding to requests
    When User is authorizing with following data '<email>', '<password>', '<apiKey>', '200'
    Then Correct user '<email>' epoints uuid is returned

  @qa @stag
    Examples:
      | email                            | password | apiKey    |
      | epoints.api.automation.1@test.pl | P@ssw0rd | accessKey |

  @UserAuthentication @PositiveCase
  @Regression
  Scenario Outline: User authentication - authenticate user with incorrect data
    Given User Manager API is responding to requests
    When User is authorizing with following data '<email>', '<password>', '<apiKey>', '<status>'
    Then Following error response with following data is returned '<status>', '<error>', '<message>'

  @qa @stag
    Examples:
      | email                            | password | apiKey         | status | error           | message                                            |
#  notExistingEmail
      | notexistingemail@test.pl         | P@ssw0rd | accessKey      | 404    | Not Found       | Authentication failed                              |
##  wrongPassword
      | epoints.api.automation.1@test.pl | Password | accessKey      | 404    | Not Found       | Authentication failed                              |
##  wrongApiKey
      | epoints.api.automation.1@test.pl | P@ssw0rd | wrongAccessKey | 403    | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid |

#                    FACEBOOK MANUAL TEST

  #FB account:  staticfacebookemail@gmail.com / P@ssw0rd!@#
  #Open: https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=210426522440945&redirect_uri=http%3A%2F%2Fdev.epoints.com:8080/members/facebook&scope=user_photos,email,user_birthday,user_online_presence
  #Settings -> applications -> remove epoints app
  #and allow access to your account
  #wait some time... when loading will fail extract access_token from url

#  Two cases to be tested here, when user already exists and not exists in user manager

  @Facebook
  Scenario Outline:  User authentication - authenticate user by facebook static user
    Given User Manager API is responding to requests
    When Epoints user log in with facebook '<accessToken>', '<facebookId>', '<apiKey>', '200'
    Then Correct user '<email>' epoints uuid is returned

  @qa @stag
    Examples:
      | email                         | facebookId      | accessToken                         | apiKey    |
#      token has to be generated automatically using above instruction
      | staticfacebookemail@gmail.com | 101309290631459 | extractedManuallyAndPasteInStepDefs | accessKey |

  @Facebook
  @Regression
  Scenario Outline:  User authentication - authenticate user by facebook static user with incorrect data
    Given User Manager API is responding to requests
    When Epoints user log in with facebook '<accessToken>', '<facebookId>', '<apiKey>', '<status>'
    Then Following error response with following data is returned '<status>', '<error>', '<message>'

  @qa @stag
    Examples:
      | facebookId      | accessToken                         | apiKey         | status | error           | message                                            |
      #      token has to be generated automatically using above instruction
      #  wrong accessKey
      | 101309290631459 | extractedManuallyAndPasteInStepDefs | wrongAccessKey | 403    | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid |
      #  wrong facebookId
      | wrongFacebookId | extractedManuallyAndPasteInStepDefs | accessKey      | 404    | Not Found       | Authentication failed                              |
      #  wrong accessToken
      | 101309290631459 | wrongAccessToken                    | accessKey      | 404    | Not Found       | Authentication failed                              |

  @prod
    Examples:
      | facebookId      | accessToken                         | apiKey                    | status | error           | message                                            |
      #  wrong accessKey
      | 101309290631459 | extractedManuallyAndPasteInStepDefs | wrongAccessKey            | 403    | INVALID_PARTNER | Partner with accessKey=[wrongAccessKey] is invalid |
      #  wrong facebookId
      | wrongFacebookId | extractedManuallyAndPasteInStepDefs | xHNZaBGQtDmxTkrnI7NOfoXkz | 404    | Not Found       | Authentication failed                              |
      #  wrong accessToken
      | 101309290631459 | wrongAccessToken                    | xHNZaBGQtDmxTkrnI7NOfoXkz | 404    | Not Found       | Authentication failed                              |