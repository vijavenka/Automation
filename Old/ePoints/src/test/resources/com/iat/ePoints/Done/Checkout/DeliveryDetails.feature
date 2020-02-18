Feature: Delivery details view
		As a user
		I require for the second page of checkout to show me details of delivery which is associated with address in my account
		and gives me possibility to send items to other address.


    Scenario:  User opens delivery details page
        Given Not registered user open ePoints.com
        Given There is at last one item added to the basket
        And User is on the basket view
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click order reward button
        When SignIn window appear enter password
        When User click order reward button
        Then He is re-directed to delivery details page
        When User click back or edit button
        Then He return to select reward page

    Scenario Outline:  User is sure that address from user account is properly copied in delivery details

        #Changing contact data

        Given Registered user open ePoints.com
        And Open My Account page
        Then Go to my profile page
        Then Edit my contact details
        Then Enter new contact details '<Phone>' '<HouseNumber>' '<Street>' '<City>' '<County>' '<Country>' '<Postcode>'
        Then Confirm contact details changes
        Then There is at last one item added to the basket
        And User is on the basket view
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click order reward button
        Then He can see that all contact data was copied properly '<Name>' '<LastName>' '<HouseNumber>' '<Street>' '<City>' '<County>' '<Country>' '<Postcode>'

        Examples:
          	|Name|LastName|Email|Password|Phone|HouseNumber|Street|City|County|Country|Postcode|
          	|Krzysztof|Baranowski|emailwybitnietestowy@gmail.com|Delete777|111444333|80|Pob|Wroc|Dolnos|Pol|34-444|
          	|Krzysztof|Baranowski|emailwybitnietestowy@gmail.com|Delete777|695444680|70|Glo|Kras|Opol|PolandOnceAg|46-444|

    Scenario Outline:  User can add new address and replace his account contact data in delivery details section
        Given Not registered user open ePoints.com
        Given There is at last one item added to the basket
        And User is on the basket view
        When User clicks on Order rewards button
        Then He is re-directed to the Selected rewards page
        When User click order reward button
        When SignIn window appear enter password
        When User click order reward button
        Then He is re-directed to delivery details page
        Then User is able to add new address
        And All data should be filled correctly '<Name>' '<LastName>' '<HouseNumber>' '<Street>' '<City>' '<County>' '<Country>' '<Postcode>'
        And Confirm button should be used
        Then Open My Account page
        And Go to my profile page
        And Compare all details with data entered before '<Name>' '<LastName>' '<HouseNumber>' '<Street>' '<City>' '<County>' '<Country>' '<Postcode>'

       Examples:
          	|Name|LastName|HouseNumber|Street|City|County|Country|Postcode|
          	|Krzysztof|Baranowski|80|Poboczna|Wroclaw|Dolnoslaskie|Poland|34-500|
          	|Krzysztof|Baranowski|70|Glowna|Krasowice|Opolskie|PolandOnceAgain|46-100|