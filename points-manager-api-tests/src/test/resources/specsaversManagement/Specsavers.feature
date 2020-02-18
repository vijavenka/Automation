Feature: Specsavers
  As a admin
  I want to have set of functionalities
  To be able to mange my (client) content

  @Specsavers @PositiveCases
  @Regression
  Scenario Outline: Get reasons list for group - check contract
    Given Points Manager API is responding to requests
    When Reasons list call is made for group data '<groupShortName_ApiKey_ClientId>'
    Then Reasons list for group response match contract

  @qa @stag
    Examples:
      | groupShortName_ApiKey_ClientId                            |
      | bdl,bdl,bdl                                               |
      | specsavers,hCend6OofpwaPRATN1uhz6411,specsaversBranchesUK |
      | epoints,accessKey,ePoints.com                             |
      | ratingsPlus,ji16mwRMWBUxJDiSfigIXg,ratings                |
      | PPB,M4l7OulPbKd5WFHiselgmJ0SR,birdseye                    |


  @Specsavers @NegativeCases
  @Regression
  Scenario Outline: Get reasons list for group - system message validation
    Given Points Manager API is responding to requests
    When Reasons list call for group is made with incorrect data '<groupShortName_ApiKey_ClientId>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Reasons list for group response is empty

  @qa @stag
    Examples:
      | groupShortName_ApiKey_ClientId    | expResponseCode | expErrorCode                   | expErrorMsg                                          |
      | @!!@#$,null,null                  | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.   |
      | 123456,#$%%,null                  | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'clientId' is not present. |
  #    //TODO small bug here already expErrorMsg changed to make test case green
      | 123456,UUID,test                  | 404             | INVALID_GROUP                  | Invalid partnersgroup with name=[%s]                 |
      | epoints,PartnerAccKey04,partner04 | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active            |
      | epoints,FAKEaccessKEY,ePoints.com | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]             |
      | epoints,bdl,bdl                   | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]             |


  @Specsavers @PositiveCases
  @Regression
  Scenario Outline: Get reasons list for partner - check contract
    Given Points Manager API is responding to requests
    When Reasons list call is made for partner data '<partnerApiKey>'
    Then Reasons list for partner response match contract

  @qa @stag
    Examples:
      | partnerApiKey             |
      | bdl                       |
      | M4l7OulPbKd5WFHiselgmJ0SR |
      | ji16mwRMWBUxJDiSfigIXg    |
      | hCend6OofpwaPRATN1uhz6411 |
      | accessKey                 |


  @Specsavers @NegativeCases
  @Regression
  Scenario Outline: Get reasons list for partner - system message validation
    Given Points Manager API is responding to requests
    When Reasons list call for partner is made with incorrect data '<partnerApiKey>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Reasons list for partner response is empty

  @qa @stag
    Examples:
      | partnerApiKey   | expResponseCode | expErrorCode                   | expErrorMsg                                        |
      | null            | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | 123456          | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             |
      | PartnerAccKey04 | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          |


  @Specsavers @PositiveCases
  @Regression
  Scenario Outline: Get users info for partner - check contract
    Given Points Manager API is responding to requests
    When User info for partner call is made for partner data '<partnerShortName_ApiKey>'
    Then User info for partner response match contract

  @qa @stag
    Examples:
      | partnerShortName_ApiKey                        |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411 |
      | specsaversBranchesNL,TZpOPIfirWiggQOhLLGbFAPrR |
      | specsaversBranchesDK,VSLAkSW0IyeeRyJnBkItYRCj3 |
      | specsaversBranchesNO,2UZsnq1BOvGPgEp601jB8toG7 |
      | specsaversBranchesFI,FdGlVxeoBb9mBQIRSLvloUTDI |
      | specsaversBranchesSE,rmAIpjua4nPZvwx19OyN0cAln |


  @Specsavers @NegativeCases
  @Regression
  Scenario Outline: Get users info for partner - system message validation
    Given Points Manager API is responding to requests
    When User info for partner call is made with incorrect data '<partnerShortName_ApiKey>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then User info for partner response is empty

  @qa @stag
    Examples:
      | partnerShortName_ApiKey   | expResponseCode | expErrorCode                   | expErrorMsg                                        |
      | partner04,null            | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | partner04,PartnerAccKey04 | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          |
      | ePoints.com,FAKEaccessKEY | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]           |
      | NOT_Exist,FAKEaccessKEY   | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]           |


  @Specsavers @PositiveCases
  @Regression
  Scenario Outline: Get partner report overview - check contract
    Given Points Manager API is responding to requests
    When Partner report overview call is made for partner data '<partnerShortName_ApiKey_start_end>'
    Then Partner report overview response match contract

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_start_end                                    |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,05-09-2015,01-02-2016 |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,01-01-2014,01-02-2016 |
      | specsaversBranchesNL,TZpOPIfirWiggQOhLLGbFAPrR,05-11-2015,01-02-2016 |
      | specsaversBranchesDK,VSLAkSW0IyeeRyJnBkItYRCj3,05-09-2015,01-02-2016 |
      | specsaversBranchesNO,2UZsnq1BOvGPgEp601jB8toG7,05-10-2015,01-02-2016 |
      | specsaversBranchesFI,FdGlVxeoBb9mBQIRSLvloUTDI,05-09-2015,01-02-2016 |
      | specsaversBranchesSE,rmAIpjua4nPZvwx19OyN0cAln,05-01-2015,01-02-2016 |


  @Specsavers @NegativeCases
  @Regression
  Scenario Outline: Get partner report overview - system message validation
    Given Points Manager API is responding to requests
    When Partner report overview call is made with incorrect data '<partnerShortName_ApiKey_start_end>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Partner report overview response is empty

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_start_end                                    | expResponseCode | expErrorCode                   | expErrorMsg                                                                                                |
      | partner04,null,05-09-2015,01-02-2016                                 | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                                                         |
      | partner04,PartnerAccKey04,05-09-2015,01-02-2016                      | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active                                                                  |
      | NOT_Exist,FAKEaccessKEY,05-09-2015,01-02-2016                        | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]                                                                   |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,01-02-2016,05-09-2015 | 403             | INVALID_ARGUMENTS              | Invalid date parameters date From=Sat Jun 04 00:00:00 UTC 18 is greater then To=Fri Jun 04 00:00:00 UTC 17 |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null             | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'startDate' is not present.                                                      |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,01-02-2016,null       | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'endDate' is not present.                                                        |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,99-02-2016,99-02-2016 | 404             | INVALID_ARGUMENTS              | TODO                                                                                                       |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,2016-01-31,2016-02-5  | 404             | INVALID_ARGUMENTS              | TODO                                                                                                       |


  @Specsavers @PositiveCases
  @Regression
  Scenario Outline: Get partner points awarded - check contract
    Given Partner points awarded call is made for partner data '<partnerShortName_ApiKey_page_size_start_end>'
    When Partner points awarded call is returned with proper pages size '<partnerShortName_ApiKey_page_size_start_end>'
    Then Partner points awarded response match contract

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_page_size_start_end                                    |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,05-09-2015,01-02-2016 |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,3,3,01-01-2014,01-02-2016       |
      | specsaversBranchesNL,TZpOPIfirWiggQOhLLGbFAPrR,0,1,05-11-2015,01-02-2016       |
      | specsaversBranchesDK,VSLAkSW0IyeeRyJnBkItYRCj3,null,3,05-09-2015,01-02-2016    |
      | specsaversBranchesNO,2UZsnq1BOvGPgEp601jB8toG7,3,null,05-10-2015,01-02-2016    |
      | specsaversBranchesFI,FdGlVxeoBb9mBQIRSLvloUTDI,1,2,05-09-2015,01-02-2016       |
      | specsaversBranchesSE,rmAIpjua4nPZvwx19OyN0cAln,1,1,05-01-2015,01-02-2016       |


  @Specsavers @NegativeCases
  @Regression
  Scenario Outline: Get partner points awarded - system message validation
    Given Points Manager API is responding to requests
    When Partner points awarded call is made with incorrect data '<partnerShortName_ApiKey_page_size_start_end>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Partner points awarded response is empty

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_page_size_start_end                                    | expResponseCode | expErrorCode                   | expErrorMsg                                                                                                |
      | partner04,null,null,null,05-09-2015,01-02-2016                                 | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                                                         |
      | partner04,PartnerAccKey04,null,null,05-09-2015,01-02-2016                      | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active                                                                  |
      | NOT_Exist,FAKEaccessKEY,null,null,05-09-2015,01-02-2016                        | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]                                                                   |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,01-02-2016,05-09-2015 | 403             | INVALID_ARGUMENTS              | Invalid date parameters date From=Sat Jun 04 00:00:00 UTC 18 is greater then To=Fri Jun 04 00:00:00 UTC 17 |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,null,null             | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'startDate' is not present.                                                      |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,01-02-2016,null       | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'endDate' is not present.                                                        |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,99-02-2016,99-02-2016 | 404             | INVALID_ARGUMENTS              | TODO                                                                                                       |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,2016-01-31,2016-02-5  | 404             | INVALID_ARGUMENTS              | TODO                                                                                                       |


  @Specsavers @PositiveCases
  @Regression
  Scenario Outline: Get partner points redeemed - check contract
    Given Partner points redeemed call is made for partner data '<partnerShortName_ApiKey_page_size_start_end>'
    When Partner points redeemed call is returned with proper pages size '<partnerShortName_ApiKey_page_size_start_end>'
    Then Partner points redeemed response match contract

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_page_size_start_end                                    |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,05-09-2015,01-02-2016 |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,3,3,01-01-2014,01-02-2016       |
      | specsaversBranchesNL,TZpOPIfirWiggQOhLLGbFAPrR,0,1,05-11-2015,01-02-2016       |
      | specsaversBranchesDK,VSLAkSW0IyeeRyJnBkItYRCj3,null,3,05-09-2015,01-02-2016    |
      | specsaversBranchesNO,2UZsnq1BOvGPgEp601jB8toG7,3,null,05-10-2015,01-02-2016    |
      | specsaversBranchesFI,FdGlVxeoBb9mBQIRSLvloUTDI,1,2,05-09-2015,01-02-2016       |
      | specsaversBranchesSE,rmAIpjua4nPZvwx19OyN0cAln,1,1,05-01-2015,01-02-2016       |


  @Specsavers @NegativeCases
  @Regression
  Scenario Outline: Get partner points redeemed - system message validation
    Given Points Manager API is responding to requests
    When Partner points redeemed call is made with incorrect data '<partnerShortName_ApiKey_page_size_start_end>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Partner points redeemed response is empty

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_page_size_start_end                                    | expResponseCode | expErrorCode                   | expErrorMsg                                                                                                |
      | partner04,null,null,null,05-09-2015,01-02-2016                                 | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.                                                         |
      | partner04,PartnerAccKey04,null,null,05-09-2015,01-02-2016                      | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active                                                                  |
      | NOT_Exist,FAKEaccessKEY,null,null,05-09-2015,01-02-2016                        | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]                                                                   |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,01-02-2016,05-09-2015 | 403             | INVALID_ARGUMENTS              | Invalid date parameters date From=Sat Jun 04 00:00:00 UTC 18 is greater then To=Fri Jun 04 00:00:00 UTC 17 |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,null,null             | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'startDate' is not present.                                                      |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,01-02-2016,null       | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'endDate' is not present.                                                        |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,99-02-2016,99-02-2016 | 404             | INVALID_ARGUMENTS              | TODO                                                                                                       |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null,null,2016-01-31,2016-02-5  | 404             | INVALID_ARGUMENTS              | TODO                                                                                                       |


  @Specsavers @PositiveCases
  @Regression
  Scenario Outline: Get partner points nets - check contract
    Given Points Manager API is responding to requests
    When Partner points nets call is made for partner data '<partnerShortName_ApiKey_currency>'
    Then Partner points nets response match contract

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_currency                   |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,GBP |
      | specsaversBranchesNL,TZpOPIfirWiggQOhLLGbFAPrR,EUR |
      | specsaversBranchesDK,VSLAkSW0IyeeRyJnBkItYRCj3,DKK |
      | specsaversBranchesNO,2UZsnq1BOvGPgEp601jB8toG7,NOK |
      | specsaversBranchesFI,FdGlVxeoBb9mBQIRSLvloUTDI,EUR |
      | specsaversBranchesSE,rmAIpjua4nPZvwx19OyN0cAln,SEK |


  @Specsavers @NegativeCases
  @Regression
  Scenario Outline: Get partner points nets - system message validation
    Given Points Manager API is responding to requests
    When Partner points nets call is made with incorrect data '<partnerShortName_ApiKey_currency>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Partner points nets response is empty

  @qa @stag
    Examples:
      | partnerShortName_ApiKey_currency                    | expResponseCode | expErrorCode                   | expErrorMsg                                          |
      | partner04,null,null                                 | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present.   |
      | partner04,PartnerAccKey04,null                      | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'currency' is not present. |
      | partner04,PartnerAccKey04,GBP                       | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active            |
      | NOT_Exist,FAKEaccessKEY,GBP                         | 403             | UNAUTHORIZED_PARTNER           | Unauthorized partner with shortName=[%s]             |
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,null | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'currency' is not present. |
      #      //bug here
      | specsaversBranchesUK,hCend6OofpwaPRATN1uhz6411,GPB  | 403             | UNAUTHORIZED_PARTNER           | GPB isn't a currency code according to ISO 4217      |