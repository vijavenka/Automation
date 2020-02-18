package com.iat.pages.rewards

import com.iat.pages.AbstractPage
import com.iat.pages.rewards.modules.BreadcrumbModule
import com.iat.pages.rewards.modules.FiltersModule
import com.iat.pages.rewards.modules.PaginationModule
import com.iat.pages.rewards.modules.RedemptionCardsBlockModule
import com.iat.stepdefs.utils.Functions
import org.openqa.selenium.Keys

class EpointsRewardsPage extends AbstractPage {

    def func = new Functions()

    static url = '/rewards'
    static at = {
        waitFor {
            getTitle().contains("Start spending your epoints | epoints") ||
                    getTitle().contains("Spend your epoints on") ||
                    getTitle().contains("Find your rewards in")
        }
    }

    static content = {
        //Included Modules
        breadcrumb { module BreadcrumbModule }
        redemptionList { module RedemptionCardsBlockModule }

        //Main rewards page
        mainRewardsText(wait: true, required: true) { $('p[translate="spendPage.homePage.homePageDesc"]') }
        mainRewardsAvailableItemsInfoForm(wait: true, required: true) { $('div.AvailableItems.ng-scope') }
        mainRewardsAvailableItemsCounter(wait: true, required: true) {
            mainRewardsAvailableItemsInfoForm.find('span:contains(" items")')
        }
        mainRewardsAvailableItemsLink(wait: true, required: true) {
            mainRewardsAvailableItemsInfoForm.find('span.AvailableItems-text.ng-scope')
        }

        /*searchInput(wait: true, required: true) { $('.itemsSearch input') }
        searchDepartmentDdl(wait: true, required: true) { $('.itemsSearch .customSelect-selection') }
        searchDepartmentDdlOption(wait: true, required: true) { $('.itemsSearch .customSelect-option') }
        searchButton(wait: true, required: true) { $('.itemsSearch button') }
        */
        searchWrapper(wait: true, required: true) { $('.search-wrapper') }
        searchInput(wait: true, required: true) { searchWrapper.$('input.search-input') }
        searchDepartmentDdl(wait: true, required: true) { searchWrapper.$('.dropdown-search li a') }
        searchDepartmentDdlOption(wait: true, required: true) { $('.Filter-items.mCustomScrollbar .Filter-name') }
        searchDepartmentScroll(wait: true, required: true) { $('.mCSB_draggerContainer .mCSB_dragger_bar') }
        searchButton(wait: true, required: true) { searchWrapper.$('.search-button')}

        departmentSection(required: false) { $(".SpendPage-sidebar-departments") }
        departments(required: false) { departmentSection.$("li a") }
        departmentsCounts(required: false) { departmentSection.$("li small") }
        selectedFilter(required: false) { $('.Filter-value.is-selected') }

        categorySection(required: false) { $(".FilterID--s_categoryFromFeedExtractedSeoSlugs_multiVal") }
        categoriesValues(required: false) { categorySection.$(".Filter-value") }
        categories { categoriesValues.$(".Filter-name") }
        categoriesCounts { categoriesValues.$(".Filter-count") }
        categoriesPanel { $('.mCSB_dragger_bar', 1) }

//        brandSection(required: false, wait: true) { $("div.Filter").has("h4", text: "BRANDS") }
        brandSection(required: false, wait: true) { $('div.Filter.Filter--multi.FilterID--s_brandName') }
        brandSearch(required: false) { brandSection.$("input") }
        brands { brandSection.$(".Filter-name") }
        brandsCounts { brandSection.$(".Filter-count") }

//        typeSection(required: false) { $("div.Filter").has("h4", text: "TYPES") }
        typeSection(required: false) { $('div.Filter.Filter--multi.FilterID--s_type') }
        typeSearch(required: false) { typeSection.$("input") }
        types { typeSection.$(".Filter-name") }
        typesCounts { typeSection.$(".Filter-count") }

        actorSection(required: false) { $("div.Filter").has("h4", text: "ACTORS") }
        actorSearch(required: false) { actorSection.$("input") }
        actors { actorSection.$(".Filter-name") }
        actorsCounts { actorSection.$(".Filter-count") }

        artistSection(required: false) { $("div.Filter").has("h4", text: "ARTISTS") }
        artistSearch(required: false) { artistSection.$("input") }
        artists { artistSection.$(".Filter-name") }
        artistsCounts { artistSection.$(".Filter-count") }

        authorSection(required: false) { $("div.Filter").has("h4", text: "AUTHORS") }
        authorSearch(required: false) { authorSection.$("input") }
        authors { authorSection.$(".Filter-name") }
        authorsCounts { authorSection.$(".Filter-count") }

//        numberOfEpointsSection(required: false) { $("div.Filter").has("h4", text: "EPOINTS") }
        numberOfEpointsSection(required: false) { $('.Filter.Filter--range.FilterID--i_epointsToPurchase') }
        numberOfEpointsFromInput { numberOfEpointsSection.$('.Filter-rangeInput', 0) }
        numberOfEpointsToInput { numberOfEpointsSection.$('.Filter-rangeInput', 1) }
        numberOfEpointsGoButton { numberOfEpointsSection.$('.btn.btn-sm.btn-yellow') }

        threePartRewardsBanner(wait: true, required: true) { $('.spendBanner') }

        filterClearOption(required: false, wait: 2) { String section -> $("div.Filter").has("h4", text: section).find(".Filter-clear") }

        //applied filters section
        appliedFiltersBox(required: false) { $("div.selectedFilters") }
//        appliedFiltersLabel(required: false) { appliedFiltersBox.$('.selectedFilters-header-label') }
        appliedFiltersLabel(required: false) { $("span.collapse-directive-selectedValues.collapse-directive-label") }
        appliedFiltersItems(required: false) { appliedFiltersBox.$('.selectedFilters-content-item') }
//        appliedFiltersClearFilters(required: false) { appliedFiltersBox.$('.selectedFilters-header-clear') }
        appliedFiltersClearFilters(required: false) { appliedFiltersLabel.$('.FiltersColumn-clear') }

        // assumption is that we will have testing configuration applied to qa, stag and we can expect that always all
        // blocks will be available.
        banner(required: true, wait: true) { $(".BlockWithBanner-image") }

        popularDepartmentSectionTitle(required: true, wait: true) {
            $(".ProductCardContainer.BlockWithDepartments").previous('h2')
        }
        popularDepartmentBlock(required: true, wait: true) {
            $(".ProductCardContainer.BlockWithDepartments").$(".BlockDepartment")
        }
        popularDepartmentBlockImage(required: true, wait: true) { popularDepartmentBlock.$("img") }
        popularDepartmentBlockDepartmentName(required: true, wait: true) {
            popularDepartmentBlock.$(".BlockCategory-titleBlock-label")
        }
        popularDepartmentBlockCategory(required: true, wait: true) { int blockNumber -> popularDepartmentBlock[blockNumber].$(".BlockDepartment-item-subItems-link").$('a') }

        popularCategorySectionTitle(required: true, wait: true) {
            $(".ProductCardContainer.BlockWithCategories").previous("h2")
        }
        popularCategoryBlock(required: true, wait: true) {
            $('.ProductCardContainer.BlockWithCategories').$(".BlockCategory")
        }
        popularCategoryBlockImage(required: true, wait: true) { popularCategoryBlock.$(".BlockCategory-image") }
        popularCategoryBlockCategoryName(required: true, wait: true) {
            popularCategoryBlock.$(".BlockCategory-titleBlock-label")
        }

        promotedBrandsSectionTitle(required: true, wait: true) { $('.PopularBrands h3') }
        promotedBrandImage(required: true, wait: true) { $('.PopularBrands-img') }

        productCardBlock(required: true, wait: true) {
            $("block-with-products").has("h2", text: "Products block").module(RedemptionCardsBlockModule)
        }

        recentlyRedeemedBlock(required: true, wait: true) {
            $("block-with-products").has("h2", text: "Recently redeemed").module(RedemptionCardsBlockModule)
        }

        specialOffersBlock(required: true, wait: true) {
            $(".BlockWithSpecialOffers").module(RedemptionCardsBlockModule)
        }

        //pagination area
        paginationTop(required: false, wait: 3) { $(".topPagination").module(PaginationModule) }
        paginationBottom(required: false, wait: 3) { $(".bottomPagination").module(PaginationModule) }

        //element available under filters on /rewards page
        informationalBanner(required: true, wait: true) { $("") }

        filters(required: true) { module(FiltersModule) }

        //new rewards page elements
        carouselBanner { $('') }
    }

    String selectAnyDepartment() {
        sleep(1000)
        waitFor { departments.size() > 0 }

        //def department = func.returnRandomValue(departments.size())
        def department = departments.size() - 1
        department = departments.getAt(department)

        String departmentName = department.text().trim()
        department.click()
        waitFor { breadcrumb.getNodes().contains(departmentName) }
        sleep(1000)
        return departmentName
    }

    String selectDepartmentByName(String deptName) {
        waitFor { departments.size() > 0 }

        //def department = func.returnRandomValue(departments.size())
        def department
        for (int i = 0; i < departments.size(); i++) {
            if (departments.getAt(i).text().contains(deptName)) {
                department = departments.getAt(i)
                break
            }
        }

        String departmentName = department.text().trim()
        department.click()
        waitFor {
            breadcrumb.getNodes().contains(departmentName)
        }
        sleep(1000)
        return departmentName
    }

    String selectAnyCategory() {
        sleep(1000)
        waitFor { categorySection.isDisplayed() }

        //def category = func.returnRandomValue(categories.size())
        def category = 0
        category = categories.getAt(category)

        interact { moveToElement(category) }
        String categoryName = category.text().trim()
        category.click()
        waitFor { breadcrumb.getNodes().contains(categoryName) }
        sleep(1000)
        return categoryName
    }

    void useSearchKeyword(String searchPhrase, String departmentName) {
        waitFor { searchInput.isDisplayed() }

        int numberOfFilters = getAppliedFilters().size()

        searchInput.value(searchPhrase)
        searchDepartmentDdl[0].click()
        waitFor { searchDepartmentDdlOption[0].isDisplayed() }
        sleep(500)

        int index=-1

        while (true) {
            index = searchDepartmentDdlOption.findIndexOf { it.text() == departmentName }

            if(index >= 0)
                break
            else
            {
                interact {
                    clickAndHold(categoriesPanel)
                    moveByOffset(0,100)
                    release()
                }
            }
        }

        searchDepartmentDdlOption[index].click()
//        searchButton.click()

        if (searchPhrase.isEmpty()) return

        if(appliedFiltersBox.isDisplayed())
            numberOfFilters + 1 == getAppliedFilters().size()
    }

    String selectAnyBrand() {
        sleep(1000)
        if (!brandSection.isDisplayed()) return ""

        int numberOfFilters = getAppliedFilters().size()
        def brand = func.returnRandomValue(brands.size())
        brand = brands.getAt(brand)

        interact { moveToElement(brand) }
        String brandName = brand.text().trim()

        func.scrolPageUpDown("down",600)
        brand.click()
        waitFor {
            appliedFiltersBox.isDisplayed()
            numberOfFilters + 1 == getAppliedFilters().size()
        }
        sleep(500)
        return brandName
    }

    String selectAnyType() {
        sleep(1000)
        if (!typeSection.isDisplayed()) return ""

        int numberOfFilters = getAppliedFilters().size()
        def type = func.returnRandomValue(types.size())
        type = types.getAt(type)

        interact { moveToElement(type) }
        String typeName = type.text().trim()
        type.click()
        waitFor {
            appliedFiltersBox.isDisplayed()
            numberOfFilters + 1 == getAppliedFilters().size()
        }
        sleep(500)
        return typeName
    }

    String selectAnyActor() {

        if (!actorSection.isDisplayed()) return ""

        int numberOfFilters = getAppliedFilters().size()
        def actor = func.returnRandomValue(actors.size())
        actor = actors.getAt(actor)

        interact { moveToElement(actor) }
        String actorName = actor.text().trim()
        type.click()
        waitFor {
            appliedFiltersBox.isDisplayed()
            numberOfFilters + 1 == getAppliedFilters().size()
        }
        sleep(500)
        return actorName
    }

    String selectAnyArtist() {

        if (!artistSection.isDisplayed()) return ""

        int numberOfFilters = getAppliedFilters().size()
        def artist = func.returnRandomValue(artists.size())
        artist = artists.getAt(artist)

        interact { moveToElement(artist) }
        String artistName = artist.text().trim()
        type.click()
        waitFor {
            appliedFiltersBox.isDisplayed()
            numberOfFilters + 1 == getAppliedFilters().size()
        }
        sleep(500)
        return artistName
    }

    String selectAnyAuthor() {

        if (!authorSection.isDisplayed()) return ""

        int numberOfFilters = getAppliedFilters().size()
        def author = func.returnRandomValue(authors.size())
        author = authors.getAt(author)

        interact { moveToElement(author) }
        String authorName = author.text().trim()
        type.click()
        waitFor {
            appliedFiltersBox.isDisplayed()
            numberOfFilters + 1 == getAppliedFilters().size()
        }
        sleep(500)
        return authorName
    }

    List<Integer> selectAnyNumberOfEpointsRange() {
        sleep(1000)
        waitFor { numberOfEpointsSection.isDisplayed() }

        int numberOfFilters = getAppliedFilters().size()
        int from = func.returnRandomValue(500)
        int to = (func.returnRandomValue(200) + 6000)

        numberOfEpointsFromInput.value(from)
        numberOfEpointsToInput.value(to)

        sleep(500)
        numberOfEpointsGoButton << Keys.chord(Keys.ENTER)
        waitFor {
            appliedFiltersBox.isDisplayed()
//            numberOfFilters + 1 == getAppliedFilters().size()
        }
        sleep(500)
        return [from, to]
    }

    boolean isDepartmentSelected() {
        return categorySection.isDisplayed()
    }

    boolean isCategorySelected() {
        for (int i = 0; i < categoriesValues.size(); i++) {
            if (categoriesValues[i].hasClass("is-selected"))
                return true
        }
        return false
    }

    def clickOnAvailableItemsLink() {
        waitFor { mainRewardsAvailableItemsInfoForm.isDisplayed() }
        mainRewardsAvailableItemsLink.click()
    }

    def removeAppliedFilter(int filterPosition) {
        int numberOfFilters = getAppliedFilters().size()
        appliedFiltersItems[filterPosition].$("a").click()

        waitFor { numberOfFilters - 1 == getAppliedFilters().size() }
    }

    def clearAllAppliedFilters() {
        waitFor { appliedFiltersClearFilters.isDisplayed() }
        appliedFiltersClearFilters.click()
        waitFor { !appliedFiltersBox.isDisplayed() }
    }

    List<String> getAppliedFilters() {
        sleep(1000)
        if (!appliedFiltersBox.isDisplayed())
            return []
        return appliedFiltersItems*.text()*.trim()
    }

    String selectRandomPopularDepartment() {
        def blockNumber = func.returnRandomValue(popularDepartmentBlock.size())
        def departmentName = popularDepartmentBlockDepartmentName[blockNumber].text()
        popularDepartmentBlockDepartmentName[blockNumber].click()
        return departmentName
    }

    List<String> selectRandomPopularDepartmentCategory() {
        def blockNumber = func.returnRandomValue(popularDepartmentBlock.size())
        def categoryNumber = func.returnRandomValue(popularDepartmentBlockCategory(blockNumber).size())
        def departmentName = popularDepartmentBlockDepartmentName[blockNumber].text()
        def categoryName = popularDepartmentBlockCategory(blockNumber)[categoryNumber].text()
        popularDepartmentBlockCategory(blockNumber)[categoryNumber].click()
        return [departmentName, categoryName]
    }

    String selectRandomPopularCategory() {
        def blockNumber = func.returnRandomValue(popularCategoryBlock.size())
        def categoryName = popularCategoryBlockCategoryName[blockNumber].text()
        popularCategoryBlockCategoryName[blockNumber].click()
        return categoryName
    }

    void selectRandomPromotedBrand() {
        def brandNumber = func.returnRandomValue(promotedBrandImage.size())
        waitFor { promotedBrandImage[brandNumber].isDisplayed() }; promotedBrandImage[brandNumber].click()
    }

    void openSeeMoreLinkOfProductBlock() {
        waitFor { productCardBlock.linkToSeeMore.isDisplayed() }; productCardBlock.linkToSeeMore.click()
    }

    void selectSortByOption(PaginationModule.SortOption sortOption) {
        paginationTop.sortByDrop.selected = sortOption.toString()
        sleep(500)
    }

    void selectItemsPerPageOption(PaginationModule.PerPageOption perPageOption) {
        paginationTop.selectItemsPerPageOption(perPageOption)
    }

}