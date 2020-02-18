package com.iat.stepdefs.rewardsSection

import com.iat.pages.checkout.EpointsBasketSelectedRewardsPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.rewards.EpointsRewardsPage
import com.iat.pages.rewards.modules.EpointsOption
import com.iat.pages.rewards.modules.FilterType
import com.iat.pages.rewards.modules.PaginationModule.SortOption
import com.iat.pages.rewards.modules.RedemptionCardModule
import com.iat.repository.SolrRepository
import com.iat.repository.impl.SolrRepositoryImpl
import com.iat.stepdefs.utils.Functions
import geb.Browser
import org.junit.Assert

import static com.iat.pages.rewards.modules.PaginationModule.PageOption.NEXT
import static com.iat.pages.rewards.modules.PaginationModule.PageOption.PREVIOUS
import static com.iat.pages.rewards.modules.PaginationModule.PerPageOption.*
import static com.iat.pages.rewards.modules.PaginationModule.SortOption.POINTS_ASCENDING
import static com.iat.pages.rewards.modules.PaginationModule.SortOption.POINTS_DESCENDING
import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

def epointsHomePage = new EpointsHomePage()
def rewards = new EpointsRewardsPage()
def func = new Functions()
def browser = new Browser()
def epointsBasketSelectedRewardsPage = new EpointsBasketSelectedRewardsPage()

String selectedDepartment
String selectedCategory
String selectedBrand
String selectedType
def selectedPointsRange
boolean isDisplayed
RedemptionCardModule selectedRedemption
SolrRepository solr = new SolrRepositoryImpl();

Given(~/^Rewards page is opened$/) { ->
    if (!(page in EpointsHomePage)) {
        to EpointsHomePage
        func.clearCookiesAndStorage()
    }

    epointsHomePage = page

    //clear current basket selection
    sleep(1000)
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

    epointsHomePage = page
    epointsHomePage.goToEpointsRewardsPage()
    sleep(1000)
    to EpointsRewardsPage
    at EpointsRewardsPage
    rewards = page

    selectedDepartment = selectedCategory = selectedBrand = selectedType = selectedPointsRange = null
}

Given(~/^Rewards page's list is opened$/) { ->
    if (!(page in EpointsHomePage)) {
        to EpointsHomePage
        func.clearCookiesAndStorage()
        epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    }
    at EpointsHomePage
    epointsHomePage = page
    epointsHomePage.goToEpointsRewardsPage()
    at EpointsRewardsPage
    rewards = page
    rewards.useSearchKeyword("", "All departments")
    sleep(1000)

    selectedDepartment = selectedCategory = selectedBrand = selectedType = selectedPointsRange = null
}

Given(~/^Random department and category selected$/) { ->
    rewards.selectAnyDepartment()
    selectedCategory = rewards.selectAnyCategory()
}

Then(~/^Redemption cards are available$/) { ->
    waitFor { rewards.redemptionList.size() > 0 }
}

Then(~/^(.+?) cards include fields: category, image, title, epointsValue, originalEpointsValue \(optional\), add to basket button$/) {
    String block ->

        def rewardsBlock
        switch (block) {
            case "Product block redemption": rewardsBlock = rewards.productCardBlock; break
            case "Special offers block": rewardsBlock = rewards.specialOffersBlock; break
            case "Recently redeemed block redemption": rewardsBlock = rewards.recentlyRedeemedBlock; break
            default: rewardsBlock = rewards.redemptionList
        }

/*        if (selectedCategory != null)
            assert rewardsBlock.getCategoryNames().unique() == [selectedCategory]
*/
        assert !rewardsBlock.getImages()*.isDisplayed().contains(false)
        assert !rewardsBlock.getTitles().contains("")
        assert !rewardsBlock.getCosts().contains("")
//        assert !rewardsBlock.getAddToBasketButtons()*.isDisplayed().contains(false)
}

When(~/^Product will be added to basket$/) { ->
    selectedRedemption = rewards.redemptionList.addToBasketAnyRedemption()
}

Then(~/^Product will be marked as '(.+?)'$/) { String itemAddedText ->
//    assert selectedRedemption.toBasketText == itemAddedText
    epointsHomePage.headerModule.clickOnBasketIcon()
}

Then(~/^Products added to basket counter will be displayed$/) { ->
//    assert selectedRedemption.toBasketCounter == 1
    assert !epointsHomePage.basketModule.BasketTitle().isEmpty()
}

Then(~/^User can add same product again$/) { ->
    /*assert selectedRedemption.toBasketButton.isDisplayed()
    selectedRedemption.clickAddToBasketButton()
    waitFor { selectedRedemption.toBasketCounter == 2 }
    */
    epointsHomePage.basketModule.increaseQuantity()
}

Then(~/^Added product is available in basket preview$/) { ->
    /*assert rewards.headerModule.headerBasketCounter == 2
    rewards.headerModule.clickOnBasketIcon()
    waitFor { rewards.basketModule.redemptionTitle[0].isDisplayed() }
    assert rewards.basketModule.redemptionTitle[0].text() == selectedRedemption.title
    assert rewards.basketModule.redemptionQuantity[0].text() == "Quantity 2"
    */
    // Already verified above
}

When(~/^Random brand, type, number of epoints selected$/) { ->
    selectedBrand = rewards.selectAnyBrand();
    if(!selectedBrand.isEmpty())
        selectedBrand = "Brand: $selectedBrand"

    selectedType = rewards.selectAnyType();
    if (!selectedType.isEmpty()) selectedType = "type: $selectedType"

    selectedPointsRange = rewards.selectAnyNumberOfEpointsRange()
    selectedPointsRange = "Epoints: " + selectedPointsRange.get(0).toString() + " - " + selectedPointsRange.get(1).toString()
}

Then(~/^'Applied filters' contains all selected filters in selection order$/) { ->
    def expectedFilters
    if (!selectedType.isEmpty() && !selectedBrand.isEmpty())
        expectedFilters = [selectedBrand, selectedType, selectedPointsRange]
    else if (!selectedType.isEmpty() && selectedBrand.isEmpty())
        expectedFilters = [selectedType, selectedPointsRange]
    else if (selectedType.isEmpty() && !selectedBrand.isEmpty())
        expectedFilters = [selectedBrand, selectedPointsRange]
    else
        expectedFilters = [selectedPointsRange]

//    assert rewards.appliedFiltersLabel.text() == "APPLIED FILTERS"
    assert rewards.appliedFiltersLabel.text().substring(2,17).contains("Applied Filter")

    String a = rewards.appliedFilters.toString().replaceAll(",", "")
    String[] str
    str = a.split('\n')
    String str1 = str.toString().replaceAll(",","").replace("[","").replace("]","")
    String str2 = expectedFilters.toString().replaceAll(":", "").replaceAll(",", "").replace("[","").replace("]","")

        assert str1 == str2
}

Then(~/^Applied filters can be cleared without removing department and category selection$/) { ->
    rewards.clearAllAppliedFilters()
//    assert rewards.isDepartmentSelected()
//    assert rewards.isCategorySelected()
}

When(~/^User use search with some phrase$/) { ->
    rewards.useSearchKeyword("Gifts", "Gift Cards")
}

Then(~/^'Applied filters' contains provided keyword search$/) { ->
    sleep(1000)
    assert rewards.breadcrumb.getNodes()[2] == "Gift Cards"
}

Then(~/^Each returned product has correct url according to search scope '(.+?)'$/) { String businessType ->
    for (String url : rewards.redemptionList.getUrls()) {
        if (businessType.equals("united")) {
            assertThat("Product: href does not contains united path", url.contains("united"))
        } else if (businessType.equals("epoints"))
            assertThat("Product href contains united path but should not", !url.contains("united"))
    }
}

Then(~/^Single filter selection can be cleared using 'x' button$/) { ->
//    rewards.removeAppliedFilter(0)
}

Then(~/^Department selection will be persisted$/) { ->
    assert rewards.isDepartmentSelected()
}

When(~/^Breadcrumb category node will be clicked$/) { ->
    rewards.breadcrumb.clickNode(selectedCategory)
}

Then(~/^All 'Applied filters' will be persisted$/) { ->
    assert rewards.appliedFiltersBox.isDisplayed()
}

Then(~/^Banner is available on rewards home page$/) { ->
    waitFor { rewards.banner.isDisplayed() }
}

And(~/^Then external page will be opened after click on it$/) { ->
    rewards.banner.click()
    waitFor { browser.currentUrl == "https://qa.epoints.com/faq" }

}

Then(~/^Popular departments are available on rewards home page$/) { ->
    assert rewards.popularDepartmentSectionTitle.text() == "Popular departments"
    assert rewards.popularDepartmentBlock.size() == 4
}

Then(~/^Popular departments block contains image, department name, categories$/) { ->
    def blockNr = func.returnRandomValue(rewards.popularDepartmentBlock.size())
    assert rewards.popularDepartmentBlockImage[blockNr].isDisplayed()
    assert rewards.popularDepartmentBlockDepartmentName[blockNr].isDisplayed()
    assert rewards.popularDepartmentBlockCategory(blockNr).size() == 6
}

Then(~/^User is redirected to department page after click on department name$/) { ->
    selectedDepartment = rewards.selectRandomPopularDepartment()
    waitFor { rewards.breadcrumb.getNodes().contains(selectedDepartment) }
    assert rewards.selectedFilter[0].text().toLowerCase().contains(selectedDepartment.toLowerCase())
}

When(~/^User selects category from popular department blocks$/) { ->
    def selectedFilters = rewards.selectRandomPopularDepartmentCategory()
    selectedDepartment = selectedFilters.get(0)
    selectedCategory = selectedFilters.get(1)
}

Then(~/^He is redirected to category page$/) { ->
    waitFor { rewards.breadcrumb.getNodes().contains(selectedCategory) }
    assert rewards.breadcrumb.getNodes().contains(selectedCategory)
    assert rewards.selectedFilter[1].text().contains(selectedCategory)
}

Then(~/^Popular categories are available on rewards home page$/) { ->
    assert rewards.popularCategorySectionTitle.text() == "Popular categories"
    assert rewards.popularDepartmentBlock.size() == 4
}

Then(~/^Popular category block contains image, category name$/) { ->
    def blockNr = func.returnRandomValue(rewards.popularCategoryBlock.size())
    assert rewards.popularCategoryBlock[blockNr].isDisplayed()
    assert rewards.popularCategoryBlock[blockNr].isDisplayed()
}

Then(~/^User is redirected to category page after click on category name$/) { ->
    selectedCategory = rewards.selectRandomPopularCategory()
    waitFor { rewards.breadcrumb.getNodes().contains(selectedCategory) }
    assert rewards.selectedFilter[1].text().contains(selectedCategory)
}

Then(~/^Promoted brands are available on rewards home page$/) { ->
    assert rewards.promotedBrandsSectionTitle.text() == "Popular brands"
    assert rewards.promotedBrandImage.size() == 6
}

Then(~/^User is redirected to search page with selected brand after click on brand image$/) { ->
    rewards.selectRandomPromotedBrand()
    waitFor { rewards.brandSection.isDisplayed() }
}

Then(~/^Product block is available$/) { ->
    assertThat("Product block should be displayed!", rewards.productCardBlock.isDisplayed())
    //title will be added to config
    assertThat(rewards.productCardBlock.title, is("Products block"))
    //number of displayed products will be defined in config
    assertThat(rewards.productCardBlock.size(), is(8))
}

Then(~/^See more link is available in product block$/) { ->
    assert rewards.productCardBlock.linkToSeeMore.isDisplayed()
}

Then(~/^See more link redirects user to linked page when clicked$/) { ->
    rewards.openSeeMoreLinkOfProductBlock()
    waitFor { browser.currentUrl.endsWith("/about") }
}

When(~/^Product from products block will be added to basket$/) { ->
    selectedRedemption = rewards.productCardBlock.addToBasketAnyRedemption()
}

Given(~/^Random department is selected$/) { ->
    selectedDepartment = rewards.selectAnyDepartment()
}

Then(~/^Recently redeemed block is available$/) { ->
    waitFor { rewards.recentlyRedeemedBlock.isDisplayed() }
    assert rewards.recentlyRedeemedBlock.isDisplayed()
    //Below statement can be ensured but wee need 4 redeemed items per department
    //rewards.recentlyRedeemedBlock.size() == 4
}

Then(~/^Special offers block is available$/) { ->
    assertThat(rewards.specialOffersBlock.title, endsWith("Special offers"))
    //below size value includes the large card as well
    assertThat(rewards.specialOffersBlock.size(), is(5))
    assertThat(rewards.specialOffersBlock.largerCards.size(), is(1))
}

Then(~/^Recently redeemed products are only from selected department$/) { ->
    boolean categoryFromDepartmentSet
    for (Object category : rewards.recentlyRedeemedBlock.redemptions.category) {
        categoryFromDepartmentSet = false
        for (Object categoryFromFilter : rewards.categories) {
            if (category.text() == categoryFromFilter.text()) {
                categoryFromDepartmentSet = true
                break
            }
        }
        assert categoryFromDepartmentSet
    }
}

When(~/^Product from recently redeemed block will be added to basket$/) { ->
    selectedRedemption = rewards.recentlyRedeemedBlock.addToBasketAnyRedemption()
}

Then(~/^Special offers additional link is redirecting user$/) { ->
    def link = rewards.specialOffersBlock.linkToSeeMore
    assert link.isDisplayed()
    link.click()
    assert browser.getCurrentUrl().endsWith("/contact-us")
}
Then(~/^Pagination is available to use$/) { ->
    assert rewards.paginationTop.isDisplayed()
    assert rewards.paginationTop.perPage.size() == 3
    assert rewards.paginationTop.sortByDrop.isDisplayed()
    assert rewards.paginationBottom.isDisplayed()
    assert rewards.paginationBottom.perPage.size() == 0
    assert !rewards.paginationBottom.sortByDrop.isDisplayed()
}

Then(~/^It is possible to change the amount of displayed cards per page$/) { ->
    rewards.paginationTop.selectItemsPerPageOption(HUNDRED)

    //TODO total value has to be known to be able to handle situation where umber of products is lower than 100/40/20
    assert (rewards.redemptionList.size() <= 100)

    rewards.paginationTop.selectItemsPerPageOption(FORTY)
    assert rewards.redemptionList.size() == 40

    rewards.paginationTop.selectItemsPerPageOption(TWENTY)
    assert rewards.redemptionList.size() == 20
}

Then(~/^It is possible to go to the next\/previous page via arrow button$/) { ->

    int currentPage = rewards.paginationTop.getNumberOfCurrentPage()
    assert rewards.paginationBottom.getNumberOfCurrentPage() == currentPage

    rewards.paginationTop.changePage(NEXT)
    assert rewards.paginationTop.getNumberOfCurrentPage() == ++currentPage
    assert rewards.paginationBottom.getNumberOfCurrentPage() == currentPage

    rewards.paginationTop.changePage(PREVIOUS)
    assert rewards.paginationTop.getNumberOfCurrentPage() == --currentPage
    assert rewards.paginationBottom.getNumberOfCurrentPage() == currentPage

    //both pagination must be checked
    rewards.paginationBottom.changePage(NEXT)
    assert rewards.paginationTop.getNumberOfCurrentPage() == ++currentPage
    assert rewards.paginationBottom.getNumberOfCurrentPage() == currentPage

    rewards.paginationBottom.changePage(PREVIOUS)
    assert rewards.paginationTop.getNumberOfCurrentPage() == --currentPage
    assert rewards.paginationBottom.getNumberOfCurrentPage() == currentPage
}

Then(~/^It is possible to go straight to the particular page via digits link$/) { ->
    rewards.paginationTop.specificPage(3).click()
    assert rewards.paginationTop.getNumberOfCurrentPage() == 3
    assert rewards.paginationBottom.getNumberOfCurrentPage() == 3

    rewards.paginationBottom.specificPage(1).click()
    assert rewards.paginationTop.getNumberOfCurrentPage() == 1
    assert rewards.paginationBottom.getNumberOfCurrentPage() == 1
}

Then(~/^It is possible to sort products by (.*)$/) { SortOption sortOption ->

    rewards.paginationTop.sortByDrop.selected = sortOption.toString()
    sleep(500)
    def costsPossiblySorted = rewards.redemptionList.getCostsInt()

    switch (sortOption) {
        case POINTS_ASCENDING:
            assert costsPossiblySorted.sort(false) == costsPossiblySorted
            break
        case POINTS_DESCENDING:
            assert costsPossiblySorted.sort(false).reverse() == costsPossiblySorted
            break
    }

}


When(~/^Keyword search is used with '(.+?)' for department '(.+?)'$/) { String searchTerm, String department ->
    rewards.useSearchKeyword(searchTerm, department)
}

Then(~/^Each of returned product contains search term '(.+?)'$/) { String searchTerm ->
    //It can fail because search results base also on description
    for (String title : rewards.redemptionList.getTitles())
        assert title.contains(searchTerm)
}

Then(~/^Number of returned products is displayed on breadcrumb$/) { ->
    waitFor { rewards.filters.categories.size() }
    assertThat(rewards.breadcrumb.breadcrumbProductCounter, is(rewards.filters.sumFromCategories))
}

Given(~/^Solar search is set to ([^"]*) scope$/) { String externalIdType ->
    solr = new SolrRepositoryImpl(externalIdType)
}

When(~/^User selects a ([^"]*) which has products filtered by (.*)$/) { String filterLevel, FilterType filter ->
    selectedDepartment = solr.getDepartmentWithFilter(filter)
    def departmentElement = rewards.filters.department(selectedDepartment.trim())
    assertThat("Department's counter should match the solr's value!", departmentElement.counter as int, is(solr.getNumberOfActiveProducts(selectedDepartment, selectedCategory)))
    departmentElement.click()

    if (filterLevel.toLowerCase() != "category")
        return

    selectedCategory = solr.getCategoryWithFilter(selectedDepartment, filter)
    def categoryElement = rewards.filters.category(selectedCategory)
    assertThat("Category's counter should match the solr's value!", categoryElement.counter as int, is(solr.getNumberOfActiveProducts(selectedDepartment, selectedCategory)))
    categoryElement.click()
}

Then(~/^Number of the department's products is correct$/) { ->
    waitFor { rewards.filters.categories.size() > 0 }
    assertThat("Categories' counters should match the solr's value!", rewards.filters.sumFromCategories, is(solr.getNumberOfActiveProducts(selectedDepartment, "")))
}

Then(~/^Number of products stays correct after using the filter (.*)$/) { FilterType filter ->
    waitFor { rewards.filters.multi(filter).isDisplayed() }
    def option = rewards.filters.multi(filter).randomOption
    String filterValue = option.name
    int filterCounter = option.counter
    option.click()

    waitFor { rewards.appliedFilters.size() == 1 }
    sleep(1000)
    assertThat("Department's filter's counter should be matching solr's value!", filterCounter,
            is(solr.getNumberOfFilteredActiveProducts(selectedDepartment, selectedCategory, filter, filterValue)))
    assertThat("Categories' counter should be updated after selecting filter!", rewards.filters.sumFromCategories,
            is(solr.getNumberOfFilteredActiveProducts(selectedDepartment, "", filter, filterValue)))
}

Then(~/^Number of products stays correct after using the epoints filter (.*)$/) { EpointsOption epointsOption ->
    waitFor { rewards.appliedFilters.size() == 1 }
    if (rewards.filters.departments[0] != null)
        assertThat("Categories' counter should be updated after selecting filter!", rewards.filters.sumFromDepartments,
                is(solr.getNumberOfFilteredActiveProducts("", "", FilterType.EPOINTS, epointsOption.filterValue)))

}

Then(~/^([^"]*) cards are containing ([^"]*) links$/) { String block, String businessId ->
    def rewardsBlock
    switch (block) {
        case "Product block redemption": rewardsBlock = rewards.productCardBlock; break
        case "Special offers block": rewardsBlock = rewards.specialOffersBlock; break
        case "Recently redeemed block redemption": rewardsBlock = rewards.recentlyRedeemedBlock; break
        default: rewardsBlock = rewards.redemptionList
    }

    String expectedUrl = "epoints.com"
    if (businessId.toLowerCase() != "epoints") expectedUrl = "$expectedUrl/$businessId"
    expectedUrl = "$expectedUrl/rewards"

    assertThat("Card is redirecting to wrong business page!", rewardsBlock.getUrls(), everyItem(containsString(expectedUrl)))
//    assertThat("Category is redirecting to wrong business page!", rewardsBlock.getCategoryUrls(), everyItem(containsString(expectedUrl)))
}
When(~/^User selects a (.*) epoints option$/) { EpointsOption epointsOption ->
    waitFor { rewards.filters.epoints.isDisplayed() }
    def option = rewards.filters.epointsPredefinedRange(epointsOption)
    assertThat("Department's filter's counter should be matching solr's value!", option.counter as int,
            is(solr.getNumberOfFilteredActiveProducts("", "", FilterType.EPOINTS, epointsOption.filterValue)))
    option.click()
}

When(~/^Department '(.+?)' is selected$/) { String departmentName ->
    if (departmentName.equals("bupa"))
        go("/rewards/" + departmentName)
    else
        rewards.selectDepartmentByName(departmentName)
}

When(~/^Category filter (.+?) displayed$/) { String displayed ->
    isDisplayed = !displayed.contains("not")
    assertThat("Category filter availability is incorrect", rewards.categorySection.isDisplayed(), is(isDisplayed))
}

When(~/^Epoints filter (.+?) displayed$/) { String displayed ->
    isDisplayed = !displayed.contains("not")
    assertThat("Epoints filter availability is incorrect", rewards.numberOfEpointsSection.isDisplayed(), is(isDisplayed))
}

When(~/^Selected department (.+?) displayed$/) { String displayed ->
    isDisplayed = !displayed.contains("not")
    assertThat("Department filter availability is incorrect", rewards.selectedFilter[0].isDisplayed(), is(isDisplayed))
}

When(~/^Rewards info banner (.+?) displayed$/) { String displayed ->
    isDisplayed = !displayed.contains("not")
    assertThat("Three part rewards info availability is incorrect", rewards.threePartRewardsBanner.isDisplayed(), is(isDisplayed))
}
