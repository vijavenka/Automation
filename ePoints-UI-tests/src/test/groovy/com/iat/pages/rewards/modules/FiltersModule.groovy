package com.iat.pages.rewards.modules

import com.iat.stepdefs.utils.Functions
import geb.Module
import geb.navigator.Navigator

class FiltersModule extends Module {

    static content = {
        departments(required: false, wait: true) {
            if (browser.currentUrl.contains("list"))
                $("[class*='s_departmentSeoSlug'] .Filter-value").moduleList(FilterDepartmentModule)
            else
                $(".SpendPage-sidebar-departments li").moduleList(FilterDepartmentModule)
        }
        department(required: false, wait: 5) { String departmentName ->
            int index = departments.findIndexOf { it.name.toLowerCase() == departmentName.toLowerCase() }
            index > -1 ? departments[index] : $("empty")
        }

        categories(required: false, wait: true) {
            $("[class*='s_categoryFromFeedExtractedSeoSlugs_multiVal'] label").moduleList(FilterCategoryModule)
        }
        category(required: false, wait: 5) { String categoryName ->
            int index = categories.findIndexOf { it.name.toLowerCase() == categoryName.toLowerCase() }
            index > -1 ? categories[index] : $("empty")
        }
        categoryScroll(required: false, wait: true) {
            $("[class*='s_categoryFromFeedExtractedSeoSlugs_multiVal'] .mCSB_container")
        }

        epoints(required: true) { $(".SpendPage-sidebar-epoints") }
        epointsManualRange(required: true) { epoints.$("form").module(FilterEpointsManualModule) }
        epointsPredefinedRanges(required: true) { epoints.$("li").moduleList(FilterEpointsModule) }
        epointsPredefinedRange(required: false) { EpointsOption option ->
            int index = epointsPredefinedRanges.findIndexOf { it.id == option.id }
            index > -1 ? epointsPredefinedRanges[index] : $("empty")
        }

        filtersMulti(required: false) { $("filter-multi").moduleList(FilterMultiModule) }
        multi(required: false) { FilterType filterType ->
            int index = filtersMulti.findIndexOf { it.title.toUpperCase() == filterType.name() }
            index > -1 ? filtersMulti[index] : $("empty")
        }

    }

    int getSumFromCategories() {
        categoryScroll.jquery.attr("style", "top:0px")
        sleep(500)
        return categories*.counter.sum()
    }

    int getSumFromDepartments() {
        return departments*.counter.sum()
    }

    Navigator getBrands() {
        return multi("Brands")
    }

    Navigator getTypes() {
        return multi("Brands")
    }

    Navigator getAuthors() {
        return multi("Authors")
    }

    Navigator getArtist() {
        return multi("Artists")
    }

    Navigator getActors() {
        return multi("Actors")
    }
}

//note: departments list is having different locators on /rewards/list page than on simply /rewards page
class FilterDepartmentModule extends Module {
    static content = {
        root {
            interact { moveToElement(getNavigator()) }
            getNavigator()
        }

        name(required: true) {
            if (browser.currentUrl.contains("list"))
                root.$(".Filter-name").text().trim()
            else
                root.$("a").text().trim()
        }
        counter(required: false) {
            if (browser.currentUrl.contains("list"))
                root.$(".Filter-count").text().trim().replaceAll(",", "").toInteger()
            else
                root.$("small").text().trim().replaceAll(",", "").toInteger()
        }
    }

    @Override
    Navigator click() {
        System.out.println("Department '$name' was selected.")
        $("a").click()
    }
}


class FilterCategoryModule extends Module {
    static content = {
        root {
            interact { moveToElement(getNavigator()) }
            getNavigator()
        }

        name(required: true) { root.$(".Filter-name").text().trim() }
        counter(required: true) { root.$(".Filter-count").text().trim().replaceAll(",", "").toInteger() }
    }

    @Override
    Navigator click() {
        System.out.println("Category '$name' was selected.")
        super.click()
    }
}


class FilterEpointsModule extends Module {
    static content = {
        root {
            interact { moveToElement(getNavigator()) }
            getNavigator()
        }

        label(required: true) { root.$("label").text().trim() }
        id(required: true) { $("input").@id }
        counter(required: true) { root.$("small").text().trim().replaceAll(",", "").toInteger() }
    }

    @Override
    Navigator click() {
        $("label").click()
    }
}


class FilterEpointsManualModule extends Module {
    static content = {
        root {
            interact { moveToElement(getNavigator()) }
            getNavigator()
        }

        from(required: true) { root.$("input[name='from']") }
        to(required: true) { root.$("input[name='to']") }
        submit(required: true) { root.$("button") }
    }

    void submitRange(int from, int to) {
        this.from << from
        this.to << to
        submit.click()
    }
}


class FilterMultiModule extends Module {

    static content = {
        root {
            interact { moveToElement(getNavigator()) }
            getNavigator()
        }

        title(required: true) {
            String text = root.$("h4").text()
            text != null ? text.trim() : ""
        }
        search(required: false) { root.$(".Filter-search input") }
        options(required: true) { root.$(".Filter-value label").moduleList(FilterMultiOptionModule) }
        option(required: false) { String name ->
            int index = options.findIndexOf { it.name == name }
            options[index]
        }
    }

    FilterMultiOptionModule getRandomOption() {
        int index = new Functions().returnRandomValue(options.size())
        return options[index]
    }

    @Override
    boolean isDisplayed() {
        return options.size() > 0
    }
}


class FilterMultiOptionModule extends Module {

    static content = {
        root {
            interact { moveToElement(getNavigator()) }
            getNavigator()
        }

        name(required: true) { root.$(".Filter-name").text().trim() }
        counter(required: true) { root.$(".Filter-count").text().trim().replaceAll(",", "").toInteger() }
    }
}


enum EpointsOption {
    ICANAFFORD("epoints-0", "Show rewards I can afford", ""),
    THOUSAND("epoints-1", "100 - 999", "[100 TO 999]"),
    FIVE_THOUSANDS("epoints-2", "1,000 - 4,999", "[1000 TO 4999]"),
    TWENTY_THOUSANDS("epoints-3", "5,000 - 19,999", "[5000 TO 19999]"),
    FIFTY_THOUSANDS("epoints-4", "20,000 - 49,999", "[20000 TO 49999]"),
    HUNDRED_THOUSANDS("epoints-5", "50,000 - 99,999", "[50000 TO 99999]"),
    MAX("epoints-6", "100,000+", "[100000 TO *]")

    public String id
    public String label
    public String filterValue

    private EpointsOption(String id, String label, String filterValue) {
        this.id = id
        this.label = label
        this.filterValue = filterValue
    }
}


enum FilterType {
    BRANDS("s_brandName"), TYPES("s_type"), AUTHORS("s_author_multiVal"),
    ARTISTS("s_artist_multiVal"), ACTORS("s_actor_multiVal"), EPOINTS("i_epointsToPurchaseUK")

    public String filter

    private FilterType(String filter) {
        this.filter = filter
    }

    @Override
    String toString() {
        return filter
    }
}