Feature: Deeplinks

   As a user
       I need a home page
       Where I can see and use basic navigation options, login tools, and products

        #1
        Scenario:AFFILIATE MANAGER - create deeplinks outside of SOLR for WLS (RD-2774).
            Given Transition page using deeplink is opened
            When Click continue anyway option
            Then Transition page should be replaced with retailer page
            And New clickout should be reported 'user_sign_out' 'user_sign_out' for deeplink
