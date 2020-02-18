Feature: Selected rewards view
		As a user
		I require for the first page of checkout to show me details of items I have selected
		to store item before buying them

    Scenario:  User opens selected rewards page
        Given Not registered user open ePoints.com
        Given There is at last one item added to the basket
        And User is on the basket view
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click back to rewards page
        Then He is re-directed to order rewards page

	Scenario: Order rewards- logged in User
        Given Registered user open ePoints.com
	    And There is at last one item added to the basket
	    When User is on the basket view
	    And He clicks on Order rewards button
		And He is on Selected rewards page
		And The items in the basket are affordable
		When User clicks on Order this rewards
		Then User is redirected to Delivery Details screen
		And User's products selection is displayed

    Scenario: Remove single item/remove all items
        Given Not registered user open ePoints.com
        And Opened basket with two product in it
        Then He is able to remove one of the product
        And He is able to remove all items making one action

    Scenario: Increase and decrease number of products/check total epoints needed
        Given Not registered user open ePoints.com
        And Opened basket with two product in it
        Then User can increase quantity of all products
        And Total epoints needed should be calculated properly
        And User can decrease quantity of all products