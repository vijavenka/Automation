Feature: Manage the item page
		As a user
		I require an option on the redemption items to allow me to add the selected redemption item to my basket
		So that I can then check these item(s) out later


    #1
	Scenario Outline: Go to the item page
	    Given Not registered user open ePoints.com
		When Open redemptions browse page
	    And User use searcher with reward '<item>'
		And User clicks on '<item>' to browse
		Then User is redirected to the individual redemption item page '<item>'
		And Image, description, points to purchase and more information are available
		And 'Add to basket' option is available

		Examples:
		| item  |
		| A Time for Everything |

    #2
	Scenario Outline: Browse the item- go back to list of rewards
	    Given Not registered user open ePoints.com
		When Open redemptions browse page
	    And User use searcher with reward '<item>'
		And User clicks on '<item>' to browse
		Then User is redirected to the individual redemption item page '<item>'
	    When User clicks Back to rewards link
	    Then User is redirected back to list of all redemption items

	    Examples:
	    | user | item |
	    | authenticated | A Time for Everything  |

    #3
    Scenario Outline: Change number of items
    	Given '<user>' user is on the '<item>' item page
    	When User clicks on '<symbolOne>' icon
    	Then If '<symbolOne>' clicked the number of items on the counter is changed by one
    	When User clicks on '<symbolTwo>' icon
    	Then If '<symbolTwo>' clicked the number of items on the counter is changed by one

    	Examples:
    	| user | item | symbolOne | symbolTwo |
    	| authenticated | /spend/a-time-for-everything/45b769e3-b43b-44c6-b649-aa1ccbed2e05/5030619c-a462-407c-9644-03d39a972b86 | + | - |

    #4
    Scenario Outline: Select the reward
        Given '<user>' user is on the '<item>' item page
        And Add to basket option is available
        And There is '<number>' of items chosen
        And Basket is empty
        When User selects item to basket
        Then The item is added to the basket
        And User is informed that one product is added to the basket
        When Second item is added to the basket
        Then The count of item in the basket is increased
        And User is able to see small view of basket
        And Content of small basket view is correct '<itemName>'

        Examples:
        | user | number | item | itemName |
        | unidentified | 1 | /spend/a-time-for-everything/45b769e3-b43b-44c6-b649-aa1ccbed2e05/5030619c-a462-407c-9644-03d39a972b86 | A Time for Everything  |
