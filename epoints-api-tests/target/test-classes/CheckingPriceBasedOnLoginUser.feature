Feature: Epoints API
    As epoints user
    PD-670 I want to verify epointsInPounds for products and gift cards
    Based on login user like Silver, Gold, Platinum and anonymous
    PD-728 epoints, Product pages /rest/spend API, has to list current price based on logged in user and all upgraded price.
    If user is not logged in then Product pages will show platinum price as standard and no upgraded price.


    @Regression @PositiveCase
    Scenario: PD-670, PD-728 epoints url is accessed by anonymous user
        Given In epoints
        And epoints shows anonymous user (ie, no user)
        Then membershipType	will hold null
        And /redemptionitems API will list for products, discountedPrice will have 15% off from solr.f_price value
        And /redemptionitems API will list for gifts, discountedPrice will have solr.f_price value
        And /spend API will list for products, discountedPrice will have 15% off from solr.f_price value
        And /spend API will list for products, discountedGoldPrice will have 0 value
        And /spend API will list for products, discountedPlatinumPrice will have 15% off from solr.f_price value
        And /spend API will list for gifts, discountedPrice will have solr.f_price value

    @Regression @PositiveCase
    Scenario: PD-670, PD-728 epoints url is accessed by silver user
        Given In epoints, silver user logged in
        Then membershipType	will hold silver
        And /redemptionitems API will list for products, discountedPrice will have solr.f_price value
        And /redemptionitems API will list for gifts, discountedPrice will have solr.f_price value
        And /spend API will list for products, discountedPrice will have solr.f_price value
        And /spend API will list for products, discountedGoldPrice will have 10% off from solr.f_price value        
        And /spend API will list for products, discountedPlatinumPrice will have 15% off from solr.f_price value
        And /spend API will list for gifts, discountedPrice will have solr.f_price value
        And /basket API will list for products, discountedPrice will have solr.f_price value
        And /basket API will list for gifts, discountedPrice will have solr.f_price value

    @Regression @PositiveCase
    Scenario: PD-670, PD-728 epoints url is accessed by gold user
        Given In epoints, gold user logged in
        Then membershipType	will hold gold
        And /redemptionitems API will list for products, discountedPrice will have 10% off from solr.f_price value
        And /redemptionitems API will list for gifts, discountedPrice will have solr.f_price value
        And /spend API will list for products, discountedPrice will have 10% off from solr.f_price value
        And /spend API will list for products, discountedGoldPrice will have 0 value
        And /spend API will list for products, discountedPlatinumPrice will have 15% off from solr.f_price value
        And /spend API will list for gifts, discountedPrice will have solr.f_price value
        And /basket API will list for products, discountedPrice (10% off from price)
        And /basket API will list for gifts, discountedPrice (without 10% off from price)

    @Regression @PositiveCase
    Scenario: PD-670, PD-728 epoints url is accessed by platinum user
        Given In epoints, platinum user logged in
        Then membershipType	will hold platinum
        And /redemptionitems API will list for products, discountedPrice will have 15% off from solr.f_price value
        And /redemptionitems API will list for gifts, discountedPrice will have solr.f_price value
        And /spend API will list for products, discountedPrice will have 15% off from solr.f_price value
        And /spend API will list for products, discountedGoldPrice will have 0 value
        And /spend API will list for products, discountedPlatinumPrice will have 0 value
        And /spend API will list for gifts, discountedPrice will have solr.f_price value
        And /basket API will list for products, discountedPrice will have 15% off from solr.f_price value
        And /basket API will list for gifts, discountedPrice will have solr.f_price value
        

# PD-769 epoints.com, when epoints filter is applied. Products has to list based on the filter

@Regression @PositiveCase
    Scenario: PD-769 epoints url is accessed by anonymous user after applying epoints filter
        Given In epoints
        And epoints shows anonymous user (ie, no user)
        Then membershipType	will hold null
        And Apply epoints filter on /redemptionitems API 
        And /redemptionitems API will list all products whose discountedPrice will have 10% off from solr.f_price value and matching the filter criteria
        And /redemptionitems API will list all gifts whose discountedPrice will have solr.f_price value and matching the filter criteria

@Regression @PositiveCase
    Scenario: PD-769 epoints url is accessed by silver user
        Given In epoints, silver user logged in
        Then membershipType	will hold silver
        And Apply epoints filter on /redemptionitems API 
        And /redemptionitems API will list for products whose discountedPrice will have solr.f_price value and matching the filter criteria
        And /redemptionitems API will list for gifts whose discountedPrice will have solr.f_price value and matching the filter criteria

@Regression @PositiveCase
    Scenario: PD-769 epoints url is accessed by gold user
        Given In epoints, gold user logged in
        Then membershipType	will hold gold 
        And Apply epoints filter on /redemptionitems API 
        And /redemptionitems API will list for products whose discountedPrice will have 10% off from solr.f_price value and matching the filter criteria
        And /redemptionitems API will list for gifts whose discountedPrice will have solr.f_price value and matching the filter criteria              


