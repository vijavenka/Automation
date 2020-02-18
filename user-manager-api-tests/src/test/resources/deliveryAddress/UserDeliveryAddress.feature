Feature: User Delivery Address - management
  As an user
  I want to be able to manage my delivery addresses
  To be able to provide my details to epoints team

  @UserDeliveryAddress @PositiveCase
  @Regression
  Scenario Outline: User delivery address details - check response contract
    Given User Manager API is responding to requests
    When User calls for delivery address with '<userId>', '<apiKey>', '<limit>', '<offset>'
    Then User delivery address is returned and matches the contract

  @prod
    Examples:
      | userId                        | apiKey                    | limit | offset |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | null  | null   |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | 1     | 1      |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | 20    | null   |

  @qa @stag
    Examples:
      | userId                        | apiKey    | limit | offset |
      | user.manager.api.1@iatltd.com | accessKey | null  | null   |
      | user.manager.api.1@iatltd.com | accessKey | 1     | 1      |
      | user.manager.api.1@iatltd.com | accessKey | 20    | null   |


  @UserDeliveryAddress @NegativeCase
  @Regression
  Scenario Outline: User delivery address details - system message validation
    Given User Manager API is responding to requests
    When Call for user delivery address is made with incorrect data '<userId>', '<apiKey>', '<limit>', '<offset>', '<status>'
    Then Response should contain error information '<userId>', '<apiKey>', '<status>', '<expErrorCode>', '<expErrorMsg>'

  @prod
    Examples:
      | userId                             | apiKey                    | limit | offset | status | expErrorCode                   | expErrorMsg                                        |
      | 123456                             | null                      | null  | null   | 400    | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | 123456                             | test                      | null  | null   | 403    | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             |
      | 123456                             | inactive                  | null  | null   | 403    | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          |
      | 123456                             | xHNZaBGQtDmxTkrnI7NOfoXkz | null  | null   | 404    | INVALID_USER                   | User with UUID=[%s] is invalid                     |
      | user.manager.unverified@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | null  | null   | 400    | USER_IS_NOT_VERIFIED           | User with UUID=[%s] is not verified                |
      | user.manager.inactive@iatltd.com   | xHNZaBGQtDmxTkrnI7NOfoXkz | null  | null   | 400    | USER_IS_NOT_ACTIVE             | User with UUID=[%s] is not active                  |

  @qa @stag
    Examples:
      | userId                             | apiKey    | limit | offset | status | expErrorCode                   | expErrorMsg                                        |
      | 123456                             | null      | null  | null   | 400    | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. |
      | 123456                             | test      | null  | null   | 403    | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             |
      | 123456                             | inactive  | null  | null   | 403    | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          |
      | 123456                             | accessKey | null  | null   | 404    | INVALID_USER                   | User with UUID=[%s] is invalid                     |
      | user.manager.unverified@iatltd.com | accessKey | null  | null   | 400    | USER_IS_NOT_VERIFIED           | User with UUID=[%s] is not verified                |
      | user.manager.inactive@iatltd.com   | accessKey | null  | null   | 400    | USER_IS_NOT_ACTIVE             | User with UUID=[%s] is not active                  |


  @UserDeliveryAddress @PositiveCase
  @Regression
  Scenario Outline: User delivery address details - add new address
    Given User Manager API is responding to requests
    When '<user>' with '<apiKey>' adds new '<deliveryAddress>'
    Then Response of delivery address creation match contract

  @prod
    Examples:
      | user                          | apiKey                    | deliveryAddress                                                                                                                                                                             |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | firstName:PL,lastName:Test,houseNumberOrName:103,street:Stoke Rd,townOrCity:Guildford,county:London,country:UK,postcode:GU1 4JN,additionalInfo:Primary address,phoneNo:004476589481         |
      | user.manager.api.1@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | firstName:PLHugo,lastName:Test,houseNumberOrName:1,street:High Stret,townOrCity:Worthing,county:Worthing,country:UK,postcode:BN11 1LL,additionalInfo:Secondary address,phoneNo:044778899656 |

  @qa @stag
    Examples:
      | user                          | apiKey    | deliveryAddress                                                                                                                                                                             |
      | user.manager.api.1@iatltd.com | accessKey | firstName:PL,lastName:Test,houseNumberOrName:103,street:Stoke Rd,townOrCity:Guildford,county:London,country:UK,postcode:GU1 4JN,additionalInfo:Primary address,phoneNo:004476589481         |
      | user.manager.api.1@iatltd.com | accessKey | firstName:PLHugo,lastName:Test,houseNumberOrName:1,street:High Stret,townOrCity:Worthing,county:Worthing,country:UK,postcode:BN11 1LL,additionalInfo:Secondary address,phoneNo:044778899656 |


  @UserDeliveryAddress @NegativeCase
  @Regression
  Scenario Outline: Add user delivery address details - system message validation
    Given User Manager API is responding to requests
    When Call to add delivery address is made with incorrect data data '<userId>', '<apiKey>', '<deliveryAddressParams>', '<expResponseCode>'
    Then Response should contain error information '<userId>', '<apiKey>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'

  @prod
    Examples:
      | userId                             | apiKey                    | expResponseCode | expErrorCode                   | expErrorMsg                                        | deliveryAddressParams                                                                                                                                                                             |
      | user.manager.api.1@iatltd.com      | null                      | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.api.1@iatltd.com      | test                      | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.api.1@iatltd.com      | inactive                  | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | 123456                             | xHNZaBGQtDmxTkrnI7NOfoXkz | 404             | INVALID_USER                   | User with UUID=[%s] is invalid                     | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.unverified@iatltd.com | xHNZaBGQtDmxTkrnI7NOfoXkz | 400             | USER_IS_NOT_VERIFIED           | User with UUID=[%s] is not verified                | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.inactive@iatltd.com   | xHNZaBGQtDmxTkrnI7NOfoXkz | 400             | USER_IS_NOT_ACTIVE             | User with UUID=[%s] is not active                  | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.api.1@iatltd.com      | xHNZaBGQtDmxTkrnI7NOfoXkz | 400             | DELIVERY_ADDRESS_HAS_NO_DATA   | Delivery address to be saved is empty              | firstName:,lastName:,houseNumberOrName:,street:,townOrCity:,county:,country:,postcode:,additionalInfo:,phoneNo:                                                                                   |

  @qa @stag
    Examples:
      | userId                             | apiKey    | expResponseCode | expErrorCode                   | expErrorMsg                                        | deliveryAddressParams                                                                                                                                                                             |
      | user.manager.api.1@iatltd.com      | null      | 400             | REQUIRED_PARAMETER_NOT_PRESENT | Required String parameter 'apiKey' is not present. | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.api.1@iatltd.com      | test      | 403             | INVALID_PARTNER                | Partner with accessKey=[%s] is invalid             | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.api.1@iatltd.com      | inactive  | 403             | PARTNER_IS_NOT_ACTIVE          | Partner with accessKey=[%s] is not active          | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | 123456                             | accessKey | 404             | INVALID_USER                   | User with UUID=[%s] is invalid                     | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.unverified@iatltd.com | accessKey | 400             | USER_IS_NOT_VERIFIED           | User with UUID=[%s] is not verified                | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.inactive@iatltd.com   | accessKey | 400             | USER_IS_NOT_ACTIVE             | User with UUID=[%s] is not active                  | firstName:PL,lastName:Test,houseNumberOrName:103,street:Marine Parade,townOrCity:Brighton,county:Brighton,country:UK,postcode:BN2 1TB,additionalInfo:It shouldn't be saved,phoneNo:00440022446688 |
      | user.manager.api.1@iatltd.com      | accessKey | 400             | DELIVERY_ADDRESS_HAS_NO_DATA   | Delivery address to be saved is empty              | firstName:,lastName:,houseNumberOrName:,street:,townOrCity:,county:,country:,postcode:,additionalInfo:,phoneNo:                                                                                   |
