Feature: Epoints API: Configuration files
  As ePoints admin
  I want to be able to get epoints pages configuration via rest call
  To be able to set pages content based on config files

#  all files listed as form of documentation
  @Regression @PositiveCase @Configuration
  Scenario Outline: Configuration - get config file
    When Config file '<filePath>' will be requested
    Then File '<filePath>' is properly returned

  @qa @stag @prod
    Examples:
      | filePath                                  |

      | config/business-home.json                 |
      | config/business-join.json                 |
      | config/business-points-config.json        |
      | config/home-config.json                   |
      | config/points-config.json                 |
      | config/prizes-mobile.json                 |

      | locale/translations.en-gb.json            |
      | locale/translations.nl.json               |

      | locale/spend-configs/spend-config.nl.json |
      | locale/spend-configs/spend-config.uk.json |

  @Regression @NegativeCase @Configuration
  Scenario Outline: Configuration - get config file - error message validation
    When Config file '<filePath>' will be requested with wrong file path
    Then Error message will be thrown with '<status>', '<error>', '<message>'

  @qa @stag @prod
    Examples:
      | filePath      | status | error     | message                                                                              |
      | wrongFilePath | 404    | Not Found | Configuration file with provided name [wrongFilePath] is not available for download. |