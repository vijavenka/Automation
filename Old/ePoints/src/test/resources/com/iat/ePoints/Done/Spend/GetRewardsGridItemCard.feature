Feature: Card item

    As a user
    I require the page that shows me the redemption items related to my browse interaction and filter options
    So I can see a relevant set of redemption items to select


    Scenario Outline: Check items per page option
        Given Not registered user open ePoints.com
        When Open redemptions browse page
        And Chose number of items per page '<ItemsPerPage>'
        Then Cards for items should be available '<ItemsPerPage>'
        And Card have image '<ItemsPerPage>'
        And Card have title as hyperlink '<ItemsPerPage>'
        And Card have points component '<ItemsPerPage>'
        And Card have claim this reward button '<ItemsPerPage>'

        Examples:
        |ItemsPerPage|
        |20|
        |40|
        |100|

    Scenario Outline: Checking if points range filter works properly
        Given Not registered user open ePoints.com
        When Open redemptions browse page
        Then User can use points range filtering '<RangeDown>'
        And Be sure that products points value will be inside those range '<RangeDown>' '<RangeUp>'

        Examples:
        |RangeDown|RangeUp|
        |100|999|
        |1000|4999|
        |5000|19999|
        |20000|49999|
        |50000|99999|
        |100000|2147483647|


