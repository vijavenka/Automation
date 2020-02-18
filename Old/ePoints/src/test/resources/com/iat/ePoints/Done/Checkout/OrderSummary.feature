Feature: Order summary view
		As a user
		I require for the third page of checkout to show me details of delivery which is associated with address in my account
		and shows me selected items and of course place the order.

    Scenario:  User opens Order summary page
        Given Not registered user open ePoints.com
        Given There is at last one item added to the basket
        And User is on the basket view
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click order reward button
        When SignIn window appear enter password
        When User click order reward button
        Then He is re-directed to delivery details page
        Then User click next page in delivery page
        And He is re-directed to Order summary page
        When User click back to delivery details
        Then He return to delivery details page

    Scenario:  Check working of edit buttons in order summary
        Given Not registered user open ePoints.com
        Given There is at last one item added to the basket
        And User is on the basket view
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click order reward button
        When SignIn window appear enter password
        When User click order reward button
        Then He is re-directed to delivery details page
        Then User click next page in delivery page
        And He is re-directed to Order summary page
        When User click edit your selection button
        Then He return to select reward page
        When User click edit your delivery details
        Then He return to delivery details page

    Scenario Outline:  Check details correctness in order summary section
        Given Registered user open ePoints.com
        Given Basket has empty content
        Given There is at last one item added to the basket
        And User is on the basket view
        And Product name will be stored to later compare
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click order reward button
        Then He is re-directed to delivery details page
        Then User click next page in delivery page
        And Can see proper delivery details '<Name>' '<LastName>' '<HouseNumber>' '<Street>' '<City>' '<County>' '<Country>' '<Postcode>'
        And Can see correct product in order summary section

       Examples:
          	|Name|LastName|HouseNumber|Street|City|County|Country|Postcode|
          	|Krzysztof|Baranowski|70|Glowna|Krasowice|Opolskie|PolandOnceAgain|46-100|