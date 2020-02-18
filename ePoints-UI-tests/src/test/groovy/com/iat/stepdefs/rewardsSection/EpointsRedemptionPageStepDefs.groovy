package com.iat.stepdefs.rewardsSection

import com.iat.pages.checkout.EpointsBasketSelectedRewardsPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.rewards.EpointsRedemptionPage
import com.iat.pages.rewards.EpointsRewardsPage
import com.iat.pages.rewards.modules.RedemptionCardModule
import com.iat.repository.impl.SolrRepositoryImpl
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString

def epointsHomePage = new EpointsHomePage()
def epointsBasketSelectedRewardsPage = new EpointsBasketSelectedRewardsPage()
def rewards = new EpointsRewardsPage()
def redemption = new EpointsRedemptionPage()
def basket = new EpointsBasketSelectedRewardsPage()
def func = new Functions()
def browser = new Browser()

RedemptionCardModule selectedRedemption
String selectedRedemptionName
String selectedRedemptionCost
String selectedDepartment
String selectedCategory
When(~/^User opens any redemption page$/) { ->

    if (!(page in EpointsHomePage)) {
        to EpointsHomePage
        epointsHomePage = page
        func.clearCookiesAndStorage()
        epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    }
    epointsHomePage = page

    //clear current basket selection
    if (epointsHomePage.headerModule.headerBasketIcon.isDisplayed()) {
        epointsHomePage.headerModule.clickOnBasketIcon()
        epointsHomePage.basketModule.clickOnBasketPanelViewAllRedemptionsLink()
        at EpointsBasketSelectedRewardsPage
        epointsBasketSelectedRewardsPage = page
        epointsBasketSelectedRewardsPage.clickOnBasketRemoveAllItemsLink()
        epointsBasketSelectedRewardsPage.clickOnBasketRemoveAllPopUpDeleteLink()
        to EpointsHomePage
        epointsHomePage = page
    }
    sleep(1000)
    epointsHomePage.goToEpointsRewardsPage()
    at EpointsRewardsPage
    rewards = page
    selectedDepartment = rewards.selectAnyDepartment()
    selectedCategory = rewards.selectAnyCategory()
    selectedRedemption = rewards.redemptionList.getRandomCard()
    selectedRedemptionName = selectedRedemption.title
    selectedRedemptionCost = selectedRedemption.cost
    selectedRedemption.click()
    at EpointsRedemptionPage
    redemption = page

    waitFor { redemption.header.text() == selectedRedemptionName }
}

Then(~/^It will contains picture, description and delivery information areas$/) { ->
    assert redemption.descriptionSection.isDisplayed()
    assert redemption.deliverySection.isDisplayed()
    assert redemption.pictureSection.isDisplayed()
}
Then(~/^It will contains basket module with proper elements$/) { ->
    waitFor { redemption.basketWidgetTitle.text().contains("Get this reward for") }
    assert redemption.basketWidgetPrice.isDisplayed()
    assert redemption.quantityBox.isDisplayed()
    assert redemption.selectRewardButton.isDisplayed()
    assert redemption.increaseButton.isDisplayed()
    assert redemption.decreaseButton.isDisplayed()
    assert redemption.basketWidgetPrice.text() == selectedRedemptionCost
}

Then(~/^It will contains 'buy epoints widget'$/) { ->
    assert redemption.buyEpointsWidget.isDisplayed()
}

Then(~/^It will contains breadcrumb$/) { ->
    assert redemption.breadcrumb.isDisplayed()
}

Then(~/^It will contains 'Related rewards' module$/) { ->
    assert redemption.relatedRewards.isDisplayed()
    assert redemption.relatedRewardsTitle.text() == "Related rewards"
}

When(~/^User increase number of products in basket module$/) { ->
    redemption.changeProductsQuantity(2)
}
Then(~/^Product counter will be increased$/) { ->
    waitFor { redemption.quantityBoxValue == 2 }
}
When(~/^User decrease number of products in basket module$/) { ->
    redemption.changeProductsQuantity(1)
}
Then(~/^Product counter will be decreased$/) { ->
    waitFor { redemption.quantityBoxValue == 1 }
}

Then(~/^Proper redemption breadcrumb is displayed$/) { ->
    def expectedBreadcrumb = ["Home", "Rewards", selectedDepartment, selectedCategory]
    assert expectedBreadcrumb == redemption.breadcrumb.getNodes()
}

Then(~/^Proper ([^"]*) breadcrumb is displayed after clicking the breadcrumb's '([^"]*)' node$/) { String whatRewards, String node ->
    def expectedBreadcrumb = node == "Home" ? [] : ["Home", "Rewards"]

    String nodeToClick = node
    if (node == "category") {
        expectedBreadcrumb.addAll([selectedDepartment, selectedCategory])
        nodeToClick = selectedCategory
    } else if (node == "department") {
        expectedBreadcrumb.addAll([selectedDepartment])
        nodeToClick = selectedDepartment
    }

    redemption.breadcrumb.clickNode(nodeToClick)

    if (node != "Home") {
        at EpointsRewardsPage
        rewards = page
        //checking if user was redirected to a corresponding page
        if (node == "category") assert rewards.isCategorySelected() && rewards.isDepartmentSelected()
        else if (node == "department") assert rewards.isDepartmentSelected() && !rewards.isCategorySelected()
        else if (node == "Rewards") assert !rewards.isCategorySelected() && !rewards.isDepartmentSelected()

        assert expectedBreadcrumb == rewards.breadcrumb.getNodes()

        String expectedReward = "epoints.com/rewards"
        if (whatRewards.toLowerCase().contains("united")) expectedReward = "epoints.com/united/rewards"
        assertThat("Breacrumbs are redirecting user to improper page!", browser.getCurrentUrl(), containsString(expectedReward))
    } else at EpointsHomePage
}

When(~/^User adds that product in the number of (\d+) to the basket$/) { int howMany ->
    redemption.addToBasket(howMany)
}

Then(~/^On the redemption page the selection of the item is displayed$/) { ->
    assert redemption.imageSelectedProductText == "Added to basket"
    assert redemption.viewSelectedRewardsText == "view selected rewards"
    assert redemption.selectRewardButton.text() == "Selected"
}

Then(~/^On the redemption page the selection of the item is not displayed$/) { ->
    assert redemption.imageSelectedProductText.isEmpty()
    assert redemption.viewSelectedRewardsText.isEmpty()
    assert redemption.selectRewardButton.text() == "Select reward"
}

Then(~/^More instances of the product can be added after that$/) { ->
    redemption.addToBasket(1)

    assert redemption.imageSelectedProductText == "Added to basket"
    assert redemption.viewSelectedRewardsText == "view selected rewards"
    assert redemption.selectRewardButton.text() == "Selected"
}

Then(~/^Product with correct quantity is available on basket 'rewards' page$/) { ->
    redemption.viewSelectedRewardsLink.click()
    basket = page
    assert basket.redemptionNameBasic[0].text().equals(selectedRedemptionName)
    assert basket.redemptionNumberBasic[0].value().equals("4")
}

When(~/^Users empty the basket$/) { ->
    redemption.viewSelectedRewardsLink.click()
    basket = page
    basket.removeAllRedemptionItemsFromBasket()
    browser.getDriver().navigate().back()
    at EpointsRedemptionPage
    redemption = page
}

Given(~/^Redemption (.+?) page with max number of multiple images is opened$/) { String businessType ->
    String url = new SolrRepositoryImpl().getUrlOfMaxImagesProducts(businessType)
    assertThat("There is no product in SOLR with multiple images - it is not bug, but should be added", !url.isEmpty())

    go url
    at EpointsRedemptionPage
    redemption = page
}

Then(~/^Thumbnails are displayed$/) { ->
    assert !redemption.thumbnails*.isDisplayed().contains(false)
    assert redemption.thumbnails.size() == 5
}

Then(~/^Controls to change the main image are functional$/) { ->
    assert redemption.imageControlLeft.isDisplayed()
    assert redemption.imageControlRight.isDisplayed()

    //first assertion to check clicks on thumbnail
    redemption.thumbnails.each {
        it.click()
        assert it.classes().contains("active")
        assert redemption.primaryImageSource == it.$("img").@src
    }

    //checking the thumbnails with exclusion of edge cases
    for (int ii = 0 + 1; ii < 5 - 1; ii++) {
        redemption.thumbnails[ii].click()
        assert redemption.thumbnails[ii].classes().contains("active")
        assert redemption.primaryImageSource == redemption.thumbnails[ii].$("img").@src
        assert redemption.imageControlLeft.classes().contains("active-arrow")
        assert redemption.imageControlRight.classes().contains("active-arrow")
    }

    //edge cases
    redemption.thumbnails[0].click()
    //if the first thumbnail is clicked there is no more thumbnails on the left to move to and left controller is not active
    assert !redemption.imageControlLeft.classes().contains("active-arrow")
    assert redemption.imageControlRight.classes().contains("active-arrow")
    assert redemption.primaryImageSource == redemption.thumbnails[0].$("img").@src
    //if the last thumbnail is clicked there is no more thumbnails on the right to move to and right controller is not active
    redemption.thumbnails[4].click()
    assert redemption.imageControlLeft.classes().contains("active-arrow")
    assert !redemption.imageControlRight.classes().contains("active-arrow")
    assert redemption.primaryImageSource == redemption.thumbnails[4].$("img").@src
}

Then(~/^Larger version of the main image is showed after zooming/) { ->
    redemption.primaryImageZoom.click()
    waitFor { redemption.zoomedImageCloseButton.isDisplayed() }
    //not actual zoom is tested. Only the presence of the exit zoom button
    //assert redemption.zoomedImageCloseButton.isDisplayed()
}

Given(~/^Redemption page with the (.+?) product that is no longer in the index is opened$/) { String businessType ->

    String url = new SolrRepositoryImpl().getUrlOfNotActiveProduct(businessType)
    println url
    assertThat("There is no product marked as Deleted in SOLR - it is not a bug, but such product should be added", !url.isEmpty())

    go url
    at EpointsRedemptionPage
    redemption = page
}

Then(~/^Basket box is replaced by appropriate message$/) { ->
    assert redemption.basketWidget.text().trim() == "Sorry, out of stock"
    //assert redemption.basketWidget.children() == $("h2")
}

Then(~/^Related redemption cards are available$/) { ->
    waitFor { redemption.redemptionList.size() > 0 }
}

Then(~/^Related redemption cards include fields : category, image, title, epointsValue, originalEpointsValue \(optional\), add to basket button$/) {
    ->
    assert rewards.redemptionList.getCategoryNames().unique() == [selectedCategory]
    assert !rewards.redemptionList.getImages()*.isDisplayed().contains(false)
    assert !rewards.redemptionList.getTitles().contains("")
    assert !rewards.redemptionList.getCosts().contains("")
    assert !rewards.redemptionList.getAddToBasketButtons()*.isDisplayed().contains(false)
}

Given(~/^Related rewards section contains max (\d+) products$/) { int maxNumberOfProducts ->
    int actualRedemptionNumber = redemption.redemptionList.size()
    assert 0 < actualRedemptionNumber && actualRedemptionNumber <= maxNumberOfProducts
}

When(~/^Related product will be added to basket$/) { ->
    selectedRedemption = redemption.redemptionList.addToBasketAnyRedemption()
}

Then(~/^Related product will be marked as '(.+?)'$/) { String itemAddedText ->
    assert selectedRedemption.toBasketText == itemAddedText
}

Then(~/^Added related product is available in basket preview$/) { ->
    assert redemption.headerModule.headerBasketCounter == 1
    redemption.headerModule.clickOnBasketIcon()
    waitFor { redemption.basketModule.redemptionTitle[0].isDisplayed() }
    assert redemption.basketModule.redemptionTitle[0].text() == selectedRedemption.title
    assert redemption.basketModule.redemptionQuantity[0].text() == "Quantity 1"
}

When(~/^User click on category link on related reward card$/) { ->
    random = func.returnRandomValue(redemption.redemptionList.size())
    selectedRedemption = redemption.redemptionList.getRandomCard()
    selectedRedemption.category.click()
}

Then(~/^He will be redirected rewards page with correct filters selection$/) { ->
    at EpointsRewardsPage
}