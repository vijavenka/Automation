Feature: IAT Admin HR - Statistics for ecards
  As admin user
  I want to be able to know what kind of statistics are available
  To be able to display them on dashboard

  @Regression @PositiveCase @Statistics
  Scenario Outline: Get statistics config details and validate contract
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get statistics config request is made
    Then Get statistics config request returns proper response
    And Get statistics config include agreed set of statistics '<statistics>'

  @qa
    Examples:
      | login_password                             | statistics                                                                                                                     |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd | epoints-logins,reason-breakdown,send-ecards,unique-send-ecards,ecards-users-breakdown,epoints-ecard-open,epoints-unique-logins |

  @Regression @PositiveCase @Statistics
  Scenario Outline: Get specific statistic by id and validate contract
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get statistic by id '<statId>' request is made with params '<dateRange_filters_groupBy_previousPeriod>'
    Then Get statistic by id '<statId>' request with params '<dateRange_filters_groupBy_previousPeriod>' returns proper response

  @qa
    Examples:
      | login_password                                   | statId                 | dateRange_filters_groupBy_previousPeriod                          |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | epoints-logins         | 18-03-2016 00:00 +0000,20-03-2016 23:59 +0000;hour;null;null      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | epoints-logins         | 10-03-2016 00:00 +0000,15-04-2016 23:59 +0000;day;null;null       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | epoints-logins         | 7-03-2016 00:00 +0000,15-04-2016 23:59 +0000;week;null;null       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | epoints-logins         | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;month;null;null      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | reason-breakdown       | null;reason;null;null                                             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | send-ecards            | 10-03-2016 00:00 +0000,26-03-2016 23:59 +0000;hour;null;null      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | send-ecards            | 10-03-2016 00:00 +0000,15-04-2016 23:59 +0000;day;null;null       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | send-ecards            | 7-03-2016 00:00 +0000,15-04-2016 23:59 +0000;week;null;null       |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | send-ecards            | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;month;null;null      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | unique-send-ecards     | 10-03-2016 00:00 +0000,26-03-2016 23:59 +0000;hour;null;null      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | unique-send-ecards     | 10-03-2016 00:00 +0000,15-04-2016 23:59 +0000;day;null;null       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | unique-send-ecards     | 7-03-2016 00:00 +0000,15-04-2016 23:59 +0000;week;null;null       |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | unique-send-ecards     | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;month;null;null      |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | ecards-users-breakdown | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;receiver;null;null   |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd       | ecards-users-breakdown | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;department;null;null |

  @Regression @NegativeCase @Statistics
  Scenario Outline: Get specific statistic by id - Error message validation
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get statistic by id request is made with improper data '<statId>', '<dateRange_filters_groupBy_previousPeriod>', '<status>'
    Then Get statistic by id request for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | login_password                                         | statId                 | dateRange_filters_groupBy_previousPeriod                       | status | error             | message                                                                                                    |
      | api_test_admin_1@api.iat.admin.pl,password2            | 1                      | null;partnerId;null;null                                       | 401    | Unauthorized      | Full authentication is required to access this resource                                                    |
      #not existing statId
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | 1                      | null;partnerId;null;null                                       | 400    | Bad Request       | Invalid arguments for statistics request                                                                   |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | epoints-logins         | null;null;null;null                                            | 400    | Missing parameter | Missing parameter groupBy                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | epoints-logins         | 15-12-2016 23:59 +0300,10-03-2016 0000 +01:00;month;null;null  | 400    | Bad request       | Providing parameter dateRange in format [dd-MM-yyyy'T'HH:mm:ssXXX],[dd-MM-yyyy'T'HH:mm:ssXXX] is required. |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | reason-breakdown       | null;null;null;null                                            | 400    | Missing parameter | Missing parameter groupBy                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | reason-breakdown       | 15-12-2016 23:59 +0300,10-03-2016 0000 +01:00;reason;null;null | 400    | Bad request       | Providing parameter dateRange in format [dd-MM-yyyy'T'HH:mm:ssXXX],[dd-MM-yyyy'T'HH:mm:ssXXX] is required. |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | send-ecards            | null;null;null;null                                            | 400    | Missing parameter | Missing parameter groupBy                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | send-ecards            | 15-12-2016 23:59 +0300,10-03-2016 0000 +01:00;month;null;null  | 400    | Bad request       | Providing parameter dateRange in format [dd-MM-yyyy'T'HH:mm:ssXXX],[dd-MM-yyyy'T'HH:mm:ssXXX] is required. |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | unique-send-ecards     | null;null;null;null                                            | 400    | Missing parameter | Missing parameter groupBy                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | unique-send-ecards     | 15-12-2016 23:59 +0300,10-03-2016 0000 +01:00;month;null;null  | 400    | Bad request       | Providing parameter dateRange in format [dd-MM-yyyy'T'HH:mm:ssXXX],[dd-MM-yyyy'T'HH:mm:ssXXX] is required. |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | ecards-users-breakdown | null;null;null;null                                            | 400    | Missing parameter | Missing parameter groupBy                                                                                  |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd             | ecards-users-breakdown | 15-12-2016 23:59 +0300,10-03-2016 0000 +01:00;month;null;null  | 400    | Bad request       | Providing parameter dateRange in format [dd-MM-yyyy'T'HH:mm:ssXXX],[dd-MM-yyyy'T'HH:mm:ssXXX] is required. |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | epoints-logins         | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;month;null;null   | 403    | Forbidden         | Access is denied                                                                                           |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | reason-breakdown       | null;reason;null;null                                          | 403    | Forbidden         | Access is denied                                                                                           |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | send-ecards            | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;month;null;null   | 403    | Forbidden         | Access is denied                                                                                           |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | unique-send-ecards     | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;month;null;null   | 403    | Forbidden         | Access is denied                                                                                           |
      | api_test_user_without_rights@api.iat.admin.pl,P@ssw0rd | ecards-users-breakdown | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;month;null;null   | 403    | Forbidden         | Access is denied                                                                                           |

  @Regression @PositiveCase @exportStatistics
  Scenario Outline: Export Ecard usage breakdown (Everyday Hero) data - trigger file generation
    Given IAT Admin user is logged in with credentials 'api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd'
    When POST call to export ecards <whatExport> is made LAST_MONTH, 202
    Then Name of the exported ecards <whatExport> file is retrieved
    And It is possible to use GET call in order to download exported ecards

  @qa
    Examples:
      | whatExport          |
      | ecardUsageBreakdown |

  @Regression @NegativeCase @exportStatistics
  Scenario Outline: Export Ecard usage breakdown (Everyday Hero) data - error message validation: no dateFrom/dateTo nor dateRange
    Given IAT Admin user is logged in with credentials 'api_test_admin_platform_1@api.iat.admin.pl,P@ssw0rd'
    When POST call to export ecards <whatExport> is made <dateRange>, <status>
    Then Export ecards for incorrect data returns error message '<status>', '<error>', '<message>'

  @qa
    Examples:
      | whatExport          | dateRange     | status | error       | message                                                                                                    |
      | ecardUsageBreakdown | EMPTY_RANGE   | 400    | Bad request | Providing parameter dateRange in format [dd-MM-yyyy'T'HH:mm:ssXXX],[dd-MM-yyyy'T'HH:mm:ssXXX] is required. |
      #
      | ecardUsageBreakdown | INVALID_RANGE | 400    | Bad request | Providing parameter dateRange in format [dd-MM-yyyy'T'HH:mm:ssXXX],[dd-MM-yyyy'T'HH:mm:ssXXX] is required. |


  #Test checks only first level of departments, it can be done for all based on goDeeper recurent function.
  @Regression @PositiveCase @StatisticsConfig
  Scenario Outline: Get statistics config details and validate contract
    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get statistic by id '<statId>' request is made with params '<dateRange_filters_groupBy_previousPeriod>'
    Then Each department node should have manager details if manager assigned to it

  @qa
    Examples:
      | login_password                             | statId                 | dateRange_filters_groupBy_previousPeriod                          |
      | api_test_admin_1@api.iat.admin.pl,P@ssw0rd | ecards-users-breakdown | 1-03-2016 00:00 +0000,15-12-2016 23:59 +0000;department;null;null |