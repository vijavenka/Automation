Feature: Oauth Authentication
  As an User
  I want to have possibility to be authenticated and secured by oauth
  So that I will be able to use restricted content with oauth based session and proper security level


  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with password grant type
    When User is authenticating with following data '<grantType>', '<username>', '<password>', 'default', '200' password based grant type
    Then Token data is returned properly '<grantType>'

  @qa @stag
    Examples:
      | grantType | username | password |
      | password  | default  | default  |

  @OauthAuthentication @NegativeCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with password grant type - negative cases
    When User is authenticating with following data '<grantType>', '<username>', '<password>', '<authorizationHeader>', '<status>' password based grant type
    Then Oauth standard format error response will be returned '<error>', '<errorDescription>', '<status>' for '<grantType>'

  @qa @stag
    Examples:
      | grantType      | username      | password      | authorizationHeader      | status | error                  | errorDescription                               |
      | password       | wrongUsername | wrongPassword | default                  | 400    | invalid_grant          | Authentication failed for email: wrongUsername |
      | wrongGrantType | default       | default       | default                  | 400    | unsupported_grant_type | Unsupported grant type: wrongGrantType         |
      | password       | default       | default       | wrongAuthorizationHeader | 401    | Unauthorized           | Invalid basic authentication token             |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with password grant type - validate redis usage when token requested for second time
    Given User is authenticating with following data '<grantType>', '<username>', '<password>', 'default', '200' password based grant type
    When User is authenticating with following data '<grantType>', '<username>', '<password>', 'default', '200' password based grant type for second time with <delay>
    Then Difference in oauth response will be only in token expiry time for <delay>

  @qa @stag
    Examples:
      | grantType | username | password | delay |
      | password  | default  | default  | 5     |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with refresh_token based grant type
    When User is authenticating with following data '<grantType>', '<token>', '200' refresh_token based grant type
    Then Token data is returned properly '<grantType>'

  @qa @stag
    Examples:
      | grantType     | token   |
      | refresh_token | dynamic |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - check if always unique access_token is generated when authorizing with refresh_token
    When User is authenticating with following data '<grantType>', '<token>', '200' refresh_token based grant type
    Then Token data is returned properly '<grantType>'
    And New access token was generated
    And Previous access and refresh tokens cannot be used

  @qa @stag
    Examples:
      | grantType     | token   |
      | refresh_token | dynamic |

  @OauthAuthentication @NegativeCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with refresh_token based grant type - negative cases
    When User is authenticating with following data '<grantType>', '<token>', '<status>' refresh_token based grant type
    Then Oauth standard format error response will be returned '<error>', '<errorDescription>', '<status>' for '<grantType>'

  @qa @stag
    Examples:
      | grantType     | token             | status | error         | errorDescription                         |
      | refresh_token | wrongRefreshToken | 400    | invalid_grant | Invalid refresh token: wrongRefreshToken |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with facebook token based grant type
    When User is authenticating with following data '<grantType>', '<token>', '<facebookId>', '200' facebook token based grant type
    Then Token data is returned properly '<grantType>'

  @qa @stag
    Examples:
      | grantType                       | token   | facebookId |
      | http://bigdls.com/auth/facebook | dynamic | dynamic    |


  @OauthAuthentication @NegativeCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with facebook token based grant type - negative cases
    When User is authenticating with following data '<grantType>', '<token>', '<facebookId>', '<status>' facebook token based grant type
    Then Oauth standard format error response will be returned '<error>', '<errorDescription>', '<status>' for '<grantType>'

  @qa @stag
    Examples:
      | grantType                       | token            | facebookId      | status | error        | errorDescription                                      |
      | http://bigdls.com/auth/facebook | wrongAccessToken | dynamic         | 401    | unauthorized | Authentication failed for facebookId 105902003227838. |
      | http://bigdls.com/auth/facebook | dynamic          | wrongFacebookId | 401    | unauthorized | Authentication failed for facebookId wrongFacebookId. |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - get single_use_token based grant type token
    When User is authenticating with following data '<grantType>', '<token>', '200' single_use_token based grant type
    Then Token data is returned properly '<grantType>'

  @qa @stag
    Examples:
      | grantType                         | token   |
      | http://bigdls.com/auth/single_use | dynamic |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - authorize on epoints by using single use token
    When User is authenticating with following data '<grantType>', '<token>', '200' single_use_token based grant type
    Then Validate if it is possible to authorize on epoints '<status>'

  @qa @stag
    Examples:
      | grantType                         | token   | status |
      | http://bigdls.com/auth/single_use | dynamic | 302    |
      | http://bigdls.com/auth/single_use | dynamic | 401    |


  @OauthAuthentication @NegativeCase
  @Regression
  Scenario Outline: Oauth authentication - authenticate user with single_use_token based grant type - negative cases
    When User is authenticating with following data '<grantType>', '<token>', '<status>' single_use_token based grant type
    Then Oauth standard format error response will be returned '<error>', '<errorDescription>', '<status>' for '<grantType>'

  @qa @stag
    Examples:
      | grantType                         | token            | status | error         | errorDescription         |
      | http://bigdls.com/auth/single_use | wrongAccessToken | 401    | invalid_token | Token was not recognised |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - try authenticate user with expired single_use_token grant type
    Given User is authenticating with following data '<grantType>', '<token>', '200' single_use_token based grant type
    When Single use token will not be used in its time of life '<expiryTime>'
    Then Single use token cannot be used after that

  @qa @stag
    Examples:
      | grantType                         | token   | expiryTime |
      | http://bigdls.com/auth/single_use | dynamic | 15         |

  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - try authenticate user with (used in check token request) single_use_token grant type
    Given User is authenticating with following data '<grantType>', '<token>', '200' single_use_token based grant type
    When Single use token will be used in check token request
    Then Single use token cannot be used after that

  @qa @stag
    Examples:
      | grantType                         | token   |
      | http://bigdls.com/auth/single_use | dynamic |

#    Steps can be refactored to be more user friendly but it required additional step implementation which in my opinion is not needed
  @OauthAuthentication @PositiveCase
  @Regression
  Scenario Outline: Oauth authentication - try authenticate with password grant_type when single_use token still not expired
    When User is authenticating with following data 'http://bigdls.com/auth/single_use', '<token>', '200' single_use_token based grant type
    Then User is authenticating with following data 'password', '<username>', '<password>', 'default', '200' password based grant type

  @qa @stag
    Examples:
      | token   | username | password |
      | dynamic | default  | default  |