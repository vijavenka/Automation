Feature: Tracking logic from WLS

    As a user
        I need a transition page
        Where I can log in to my account before leaving store to receive ePoints

        #3
        Scenario:AFFILIATE MANAGER - cross channel sales tracking logic (RD-2837).
            Given Not registered user open WLS page
            When User decide to buy product
            Then He Should click Buy button
            When Transition page will be opened
            Then Additional P parameters can be added
            Then Click in continue anyway option
            And Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_out' 'user_sign_out'
            And P parameters should correctly be stored in database