Feature: Complite view
		As a user
		I require for the fourth page of checkout to show me that order procedure was correct and give me links to return
		to different page modules.


    Scenario:  User opens complete page and checks working of all links and buttons
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
        Then User click place order button
        And He is re-directed to Complete page
        And Faq link works properly
        And My account button works properly
        And Get epoints button works properly