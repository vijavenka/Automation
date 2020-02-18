package com.iat.pages.rewards.modules

import geb.Module
import geb.module.Select
import geb.navigator.Navigator

import static com.iat.pages.rewards.modules.PaginationModule.PageOption.NEXT
import static com.iat.pages.rewards.modules.PaginationModule.PageOption.PREVIOUS

class PaginationModule extends Module {

    static content = {
        previousButton(required: true, wait: true) { $("[class*='prevButton']") }
        nextButton(required: true, wait: true) { $("[class*='nextButton']") }
        pages(required: true, wait: true) { $(".pagination-numericButton").hasNot("span", text: "...") }
        specificPage(required: true, wait: true) { int pageNumber ->
            int index = pages.findIndexOf { it.text().trim().toInteger() == pageNumber }
            pages[index]
        }

        sortByDrop(required: false) { $(".paginationOrder select").module(Select) }
        perPage(required: false) { $(".paginationPerPage-button") }
    }

    @Override
    boolean isDisplayed() {
        return super.isDisplayed() && pages.size() > 0
    }

    int changePage(PageOption direction) {

        int currentPage = getNumberOfCurrentPage()

        if (direction == NEXT && isNextActive()) {
            nextButton.click()
            waitFor { getNumberOfCurrentPage() > currentPage }
            return getNumberOfCurrentPage()
        } else if (direction == PREVIOUS && isPreviousActive()) {
            previousButton.click()
            waitFor { getNumberOfCurrentPage() < currentPage }
            return getNumberOfCurrentPage()
        }

        return 0
    }

    void selectItemsPerPageOption(PerPageOption perPageOption) {
        int index = perPage.findIndexOf { it.text().trim() == perPageOption.toString() }
        def perPageElement = perPage[index]
        perPageElement.click()
        waitFor { isElementDisabled(perPageElement) }
        sleep(1000)
    }

    boolean isNextActive() {
        return !isElementDisabled(nextButton)
    }

    boolean isPreviousActive() {
        return !isElementDisabled(previousButton)
    }

    int getNumberOfCurrentPage() {
        int index = pages.findIndexOf { isElementDisabled(it) }
        return pages[index].text().trim().toInteger()
    }

    private boolean isElementDisabled(Navigator element) {
        return element.@disabled == "true"
    }

    enum PageOption {
        NEXT, PREVIOUS
    }

    enum PerPageOption {
        TWENTY("20"), FORTY("40"), HUNDRED("100")

        String value

        private PerPageOption(String value) {
            this.value = value
        }

        @Override
        String toString() {
            return this.value
        }
    }

    enum SortOption {
        RELEVANCE("Relevance"), POINTS_ASCENDING("Points low to high"), POINTS_DESCENDING("Points high to low")

        String value

        private SortOption(String value) {
            this.value = value
        }

        @Override
        String toString() {
            return this.value
        }
    }
}
