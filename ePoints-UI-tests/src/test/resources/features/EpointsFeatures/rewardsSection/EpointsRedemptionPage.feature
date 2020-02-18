Feature: Epoints individual redemption page
  As an epoints user
  I want to have individual redemption page
  So that I could read more information about redemption I am interested and add it to basket

  # // 1.1 //  ------------------------------------------------------------------------------ Individual redemption page
  # ---------------------------------------------------------------------- redemption page contains all require elements
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - page content
    When User opens any redemption page
    Then It will contains picture, description and delivery information areas
    And It will contains basket module with proper elements
    #And It will contains 'buy epoints widget'
    And It will contains breadcrumb
    And It will contains 'Related rewards' module

  # // 1.2 //  ------------------------------------------------------------------------------ Individual redemption page
  # ----------------------------------- redemption page basket widget allows to increase or decrease quantity of product
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - basket module, more less product buttons
    Given User opens any redemption page
    When User increase number of products in basket module
    Then Product counter will be increased
    When User decrease number of products in basket module
    Then Product counter will be decreased

  # // 1.3 //  ------------------------------------------------------------------------------ Individual redemption page
  # ----------------------- displayed breadcrumb is home-rewards-department-category and it allows easy page redirecting
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - breadcrumb node redirection
    When User opens any redemption page
    Then Proper redemption breadcrumb is displayed
    And Proper rewards breadcrumb is displayed after clicking the breadcrumb's 'category' node
    And Proper rewards breadcrumb is displayed after clicking the breadcrumb's 'department' node
    And Proper rewards breadcrumb is displayed after clicking the breadcrumb's 'Rewards' node
    And Proper rewards breadcrumb is displayed after clicking the breadcrumb's 'Home' node

  # // 1.4 //  ------------------------------------------------------------------------------ Individual redemption page
  # ---------------------- redemption page displays text alerts so a user knows that product was already added to basket
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - product already selected is clearly indicated
    Given User opens any redemption page
    When User adds that product in the number of 3 to the basket
    Then On the redemption page the selection of the item is displayed
    And More instances of the product can be added after that
    And Product with correct quantity is available on basket 'rewards' page

  # // 1.5 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------ redemption page is not displaying alerts about selected product after removing it from the basket
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - product already selected is clearly indicated
    Given User opens any redemption page
    When User adds that product in the number of 3 to the basket
    And Users empty the basket
    Then On the redemption page the selection of the item is not displayed

  # // 1.6 //  ------------------------------------------------------------------------------ Individual redemption page
  # ----------------------------- redemption page contains advance image display (mutliple images, zooming-in the image)
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - product can have multiple images
    Given Redemption epoints page with max number of multiple images is opened
    Then Thumbnails are displayed
    And Controls to change the main image are functional
    And Larger version of the main image is showed after zooming

  # // 1.7 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------- redemption page should not have the basket widget available if product is not to be bought
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - it should not be possible to buy deleted product
    When Redemption page with the epoints product that is no longer in the index is opened
    Then Basket box is replaced by appropriate message

  # // 1.8 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------------------------------ redemption page related rewards card contains all required elements
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - redemption page related rewards card contains all required elements
    When User opens any redemption page
    Then Related redemption cards are available
    And Related redemption cards include fields : category, image, title, epointsValue, originalEpointsValue (optional), add to basket button

  # // 1.9 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------- redemption page related rewards section has mx 5 items, each of reward can by added to basket as simple card
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - redemption page related rewards section has mx 5 items, each can be added to basket
    Given User opens any redemption page
    And Related rewards section contains max 5 products
    When Related product will be added to basket
    Then Related product will be marked as 'Item added'
    And Added related product is available in basket preview

  # // 1.10 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------------------- redemption page related rewards category link redirect to correct rewards page
  @Regression @IndRedemptionPage
  Scenario: Individual redemption page - redemption page related rewards category link redirection
    Given User opens any redemption page
    When User click on category link on related reward card
    Then He will be redirected rewards page with correct filters selection
    And Proper redemption breadcrumb is displayed
    
    
  # // 1.11 // -------------------- PD-204 When brand is not configured in brand manger, products should move to debug index
  # PD-204 When 'brand status' is deactivated in brand manager, corresponding product with brand should move to debug index
  # PD-204 When feed is not configured with brand then product should load without brand name in offering index

  Scenario: PD-204 When brand is not available in brand manager of Admin Portal,
  
  products should move to offerings
    Given In Admin Portal
    When Brand name is not available in Brand Manager
    Then In solr this Brand name should be listed in offerings
    And In epoints this Brand name should be listed

  Scenario: PD-204 When brand is deactivated in brand manager of Admin Portal, products should move to offerings
    Given In Admin Portal
    When Brand is deactivated in Brand Manager
    Then In solr this Brands should be listed in offerings
    And In epoints this Brand should be listed

  Scenario: PD-204 When brand is active in brand manager of Admin Portal, products should move to offerings 
    Given In Admin Portal
    When Brand is active in Brand Manager
    Then In solr this Brand should be listed in offerings
    And In epoints this Brand should be listed

  Scenario: PD-204 When feed is not configured with brand name in 2nd and 3rd page of Admin Portal, then products should load without brand name in offering index
    Given In Admin Portal
    When Feed is not configured with Brand in 2nd and 3rd page
    Then In solr these products should be listed in offering index without Brand name
    And In epoints these products has to be listed
    
  Scenario: PD-204 epoints, Brand as Unknown should not be displayed when we select Department
    Given In epoints
    And In Shop page
    And When we select any Department
    And Brand will get listed
    Then Within Brand, Unknown should not be displayed 
    And But Products of Unknown brand has to be listed
    
 Scenario: PD-204 epoints, Brand as Unknown should not be displayed when we select Retailers
    Given In epoints
    And In Shop page
    And When we select any Retailers
    And Brand will get listed
    Then Within Brand, Unknown should not be displayed 
    And But Products of Unknown brand has to be listed
    
    # 1.12 ------------------PD-209 EPOINTS SHOP - Extension to minimum amount in sort by 'relevance'
  Scenario: PD-290 epoints, 'relevance' option selected from category page
    Given In epoints
    And In shop page when category is selected
    Then Default relevance will be selected
    And We can verify products got listed whose price greater than or equal to 10 pounds
    Then Remaining products will be listed whose price is lesser than 10 pounds

  Scenario: PD-290 epoints, 'relevance' option selected from retailers page
    Given In epoints
    And In shop page when retailer is selected
    Then Default relevance will be selected
    And We can verify products got listed whose price greater than or equal to 10 pounds
    Then Remaining products will be listed whose price is lesser than 10 pounds

  Scenario: PD-290 epoints, 'relevance' option selected from brand page
    Given In epoints
    And In shop page when retailer and brand is selected
    Then Default relevance will be selected
    And We can verify products got listed whose price greater than or equal to 10 pounds
    Then Remaining products will be listed whose price is lesser than 10 pounds

  Scenario: PD-290 epoints, 'relevance' option selected from brand, category page
    Given In epoints
    And In shop page when retailer, brand, department and category is selected
    Then Default relevance will be selected
    And We can verify products got listed whose price greater than or equal to 10 pounds
    Then Remaining products will be listed whose price is lesser than 10 pounds

  Scenario: PD-290 epoints, 'relevance' option selected from brand, category, epoints page
    Given In epoints
    And In shop page when retailer, brand, department, category, epoints is selected
    Then Default relevance will be selected
    And We can verify products got listed whose price greater than or equal to 10 pounds
    Then Remaining products will be listed whose price is lesser than 10 pounds

  Scenario: PD-290 epoints, 'relevance' option selected from department, type page
    Given In epoints
    And In shop page when department, type is selected
    Then Default relevance will be selected
    And We can verify products got listed whose price greater than or equal to 10 pounds
    Then Remaining products will be listed whose price is lesser than 10 pounds

# 1.13 ----------------- PD-572 Products should not display in both offerings and debug
  Scenario: PD-572 When mandatory fields title is missing in the feed, then products will display in debug
    Given In Admin Portal
    And Upload the feed where Title is missing for a product
    And Activate and Run the feed
    Then In Solr missing Title product will appear in debug
    And In Solr remaining products appear in offering

  Scenario: PD-572 When mandatory fields Price is missing in the feed, then products will display in debug
    Given In Admin Portal
    And Upload the feed where Price is missing for a product
    And Activate and Run the feed
    Then In Solr missing Price product will appear in debug
    And In Solr remaining products appear in offering

  Scenario: PD-572 When mandatory fields Image is missing in the feed, then products will display in debug
    Given In Admin Portal
    And Upload the feed where Image is missing for a product
    And Activate and Run the feed
    Then In Solr missing Image product will appear in debug
    And In Solr remaining products appear in offering

  Scenario: PD-572 When BRAND is not configured in Brand Manager, then products has to appear without brand name
    Given In Admin Portal
    And BRAND is not configured in Brand Manager
    And In Feed, dont configure Brand Name in 2nd page
    And In Feed, configure Brand Name in 3rd page
    And Activate and Run the feed based on CRON timing
    Then It will appear in offering with brand name
    And Again Run the feed based on CRON timing
    Then It will appear in offering without brand name


# 1.14 ------------- PD-329 CRON job is to execute daily so that any recurring transaction done in Cardstream to update Chargebee
# Transaction details has to update DynamoDB of membership-transaction and points_manager.Payment table
# Transaction details can be of success or failure
# During failure it has to update reason like card expiry, user cancelling, cards blocked.

  Scenario: PD-329 For every success recurring transaction done in Cardstream, Chargebee has to be updated
    Given epoints user, who has initiated recurring transaction
    And For each success recurring transaction done in Cardstream
    Then It has to update Chargebee with new invoice
    And It has to update DynamoDB of membership-transaction
    And It has to create a record in points_manager.Payment table

  Scenario: PD-329 For every failure recurring transaction done in Cardstream, DynamoDB and Payment table has to be updated
    Given epoints user, who has initiated recurring transaction
    And For each failure recurring transaction done in Cardstream
    Then It wont update Chargebee with new invoice
    And It has to update DynamoDB of membership-transaction
    And It will update points_manager.Payment table

#    UNITED BUSINESS TYPE#

# // 2.1 //  ------------------------------------------------------------------------------ Individual redemption page
  # ---------------------------------------------------------------------- redemption page contains all require elements
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - page content - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    And User opens any redemption page
    When User opens any redemption page
    Then It will contains picture, description and delivery information areas
    And It will contains basket module with proper elements
    #And It will contains 'buy epoints widget'
    And It will contains breadcrumb
    And It will contains 'Related rewards' module

  # // 2.2 //  ------------------------------------------------------------------------------ Individual redemption page
  # ----------------------------------- redemption page basket widget allows to increase or decrease quantity of product
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - basket module, more less product buttons - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    And User opens any redemption page
    When User increase number of products in basket module
    Then Product counter will be increased
    When User decrease number of products in basket module
    Then Product counter will be decreased

  # // 2.3 //  ------------------------------------------------------------------------------ Individual redemption page
  # ----------------------- displayed breadcrumb is home-rewards-department-category and it allows easy page redirecting
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - breadcrumb node redirection - united page - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    When User opens any redemption page
    Then Proper redemption breadcrumb is displayed
    And Proper united rewards breadcrumb is displayed after clicking the breadcrumb's 'category' node
    And Proper united rewards breadcrumb is displayed after clicking the breadcrumb's 'department' node
    And Proper united rewards breadcrumb is displayed after clicking the breadcrumb's 'Rewards' node
    And Proper united rewards breadcrumb is displayed after clicking the breadcrumb's 'Home' node

  # // 2.4 //  ------------------------------------------------------------------------------ Individual redemption page
  # ---------------------- redemption page displays text alerts so a user knows that product was already added to basket
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - product already selected is clearly indicated - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    And User opens any redemption page
    When User adds that product in the number of 3 to the basket
    Then On the redemption page the selection of the item is displayed
    And More instances of the product can be added after that
    And Product with correct quantity is available on basket 'rewards' page

  # // 2.5 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------ redemption page is not displaying alerts about selected product after removing it from the basket
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - product already selected is clearly indicated - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    And User opens any redemption page
    When User adds that product in the number of 3 to the basket
    And Users empty the basket
    Then On the redemption page the selection of the item is not displayed

  # // 2.6 //  ------------------------------------------------------------------------------ Individual redemption page
  # ----------------------------- redemption page contains advance image display (mutliple images, zooming-in the image)
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - product can have multiple images - united
    Given Redemption united page with max number of multiple images is opened
    Then Thumbnails are displayed
    And Controls to change the main image are functional
    And Larger version of the main image is showed after zooming

  # // 2.7 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------- redemption page should not have the basket widget available if product is not to be bought
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - it should not be possible to buy deleted product - united
    When Redemption page with the united product that is no longer in the index is opened
    Then Basket box is replaced by appropriate message

  # // 2.8 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------------------------------ redemption page related rewards card contains all required elements
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - redemption page related rewards card contains all required elements
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    When User opens any redemption page
    Then Related redemption cards are available
    And Related redemption cards include fields : category, image, title, epointsValue, originalEpointsValue (optional), add to basket button

  # // 2.9 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------- redemption page related rewards section has mx 5 items, each of reward can by added to basket as simple card
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - redemption page related rewards section has mx 5 items, each can be added to basket - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    And User opens any redemption page
    And Related rewards section contains max 5 products
    When Related product will be added to basket
    Then Related product will be marked as 'Item added'
    And Added related product is available in basket preview

  # // 2.10 //  ------------------------------------------------------------------------------ Individual redemption page
  # ------------------------------------- redemption page related rewards category link redirect to correct rewards page
  @Regression @IndRedemptionPage @United
  Scenario: Individual redemption page - redemption page related rewards category link redirection - united
    Given Epoints home page is opened by united logged user with cookie panel not visible
    And User switch to united account
    And User opens any redemption page
    When User click on category link on related reward card
    Then He will be redirected rewards page with correct filters selection
    And Proper redemption breadcrumb is displayed
