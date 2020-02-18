package com.iat.ePoints.Locators.Spend;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class BrowseRewardsLocators implements IPageLocators {

    public Locator item_page(String input) {
        String temp = "//article[@class='module-container type-redemption']//a[contains(text(),'" + input + "')]";
        return new Locator(LocatorType.XPATH, temp);
    }
//Browse rewards commons
    public Locator pageHeaderTitle = new Locator(LocatorType.XPATH, "//h1[@class='page-title update-keyword to-left' and text()='Browse rewards']");
    //Search tools spend landig page
    public Locator searcherTextfield = new Locator(LocatorType.XPATH, "//input[@class='input']");
    public Locator searcherButton = new Locator(LocatorType.XPATH, "//button[contains(text(),'Search')]");
    public Locator rangeShowAll_btn = new Locator(LocatorType.XPATH, "//div[contains(text(),'All rewards')]");
    public Locator range100btn = new Locator(LocatorType.XPATH, "//span[@data-name='range-100-999']");
    public Locator range1000btn = new Locator(LocatorType.XPATH, "//span[@data-name='range-1000-4999']");
    public Locator range5000btn = new Locator(LocatorType.XPATH, "//span[@data-name='range-5000-19999']");
    public Locator range20000btn = new Locator(LocatorType.XPATH, "//span[@data-name='range-20000-49999']");
    public Locator range50000btn = new Locator(LocatorType.XPATH, "//span[@data-name='range-50000-99999']");
    public Locator range100000btn = new Locator(LocatorType.XPATH, "//span[@data-name='range-100000-2147483647']");
    //All rewards counter
    public Locator itemsToBuyCounter = new Locator(LocatorType.XPATH, "//div[@id='productCounter']//a");

    //Browse by departments tab
    public Locator departmentTab = new Locator(LocatorType.XPATH, "//h2[@data-tab='#departmentsTab']");
    public Locator basicDepartmentName = new Locator(LocatorType.XPATH, "//div[@id='departmentsTab']//div[@class='dep-box']//span[@class='title']");
    public Locator basicDepartmentCard = new Locator(LocatorType.XPATH, "//div[@id='departmentsTab']//div[@class='dep-box']");
    public String[] departments = {"Books","Fashion","Home & Garden","Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music, Film & Gaming","Toys & Games","Electronics"};
    //Reward by epoints total
    public Locator rewardsByEpointsTab = new Locator(LocatorType.XPATH, "//h2[@data-tab='#rangesTab']");
    public Locator basicSearchRangeBoxLocator = new Locator(LocatorType.XPATH, "//div[@id='rangesTab']//div[@class='search-range-box']//span[@class='title']");
    public Locator basicRewardNumberPerRangeLocator = new Locator(LocatorType.XPATH, "//div[@id='rangesTab']//div[@class='search-range-box']//span[@class='value']");
    //Our top reward picks
    public Locator bucketsTab = new Locator(LocatorType.XPATH, "//h2[@data-tab='#bucketsTab']");
    public Locator basicBucketBoxLocator = new Locator(LocatorType.XPATH, "//div[@id='bucketsTab']//div[@class='bucket-box']");
    public Locator basicBucketBoxTitleLocator = new Locator(LocatorType.XPATH, "//div[@id='bucketsTab']//div[@class='bucket-box']//span[@class='title']");


//*********************************************************************************************************************************************************************
//Redemptions Landing page
    public Locator paginationTopNext_btn = new Locator(LocatorType.XPATH, "//span[contains(@class, 'next-page')]");
    public Locator paginationTopPrev_btn = new Locator(LocatorType.XPATH, "//span[contains(@class, 'previous-page evt-prev-page disabled')]");
    public Locator paginationBottomComponent = new Locator(LocatorType.XPATH, "//ul[@class='pager']");
    public Locator itemsPerPageComponent = new Locator(LocatorType.XPATH, "//div[@class='page-size-settings']");
    public Locator sortByComponent = new Locator(LocatorType.XPATH, "//div[@class='reg-sort-by inline-region']");

    public Locator getRewards = new Locator(LocatorType.XPATH, "//a[contains(text(),'Get rewards')]");
    public Locator returnItemsPerPageLocator(String ItemsPerPage) {
        return new Locator(LocatorType.XPATH, "//span[@data-pagesize='" + ItemsPerPage + "']");
    }
    public Locator sortDrop = new Locator(LocatorType.XPATH, "//select[@id='sort-product']");

    public Locator spendMenu_opt = new Locator(LocatorType.XPATH, "//li[@data-menu='getRewards']//a");
    public Locator spendMenuTab = new Locator(LocatorType.XPATH, "//li[@data-menu='getRewards']");
    public Locator range100box = new Locator(LocatorType.XPATH, "//li[@class='option'][contains(text(),'100+')]");
    public Locator range1000box = new Locator(LocatorType.XPATH, "//li[@class='option'][contains(text(),'1,000+')]");
    public Locator range5000box = new Locator(LocatorType.XPATH, "//li[@class='option'][contains(text(),'5,000+')]");
    public Locator range20000box = new Locator(LocatorType.XPATH, "//li[@class='option'][contains(text(),'20,000+')]");
    public Locator range50000box = new Locator(LocatorType.XPATH, "//li[@class='option'][contains(text(),'50,000+')]");
    public Locator range100000box = new Locator(LocatorType.XPATH, "//li[@class='option'][contains(text(),'100,000+')]");
    //Browse rewards Items Cards
    public Locator itemsCardImgs = new Locator(LocatorType.XPATH, "//article[@class='module-container type-redemption']//div[@class='image']");
    public Locator itemsCardTitles = new Locator(LocatorType.XPATH, "//article[@class='module-container type-redemption']/*/strong[@class='title' and @itemprop='name']");
    public Locator itemsCardPointComponents = new Locator(LocatorType.XPATH, "//article[@class='module-container type-redemption']//div[@class='price']");
    public Locator itemsCardBoxes = new Locator(LocatorType.XPATH, "//article[@class='module-container type-redemption']");
    public Locator itemsCardAddToBasketButton = new Locator(LocatorType.XPATH, "//div[@class='add-to-basket-button']");
    public Locator itemsCardAddToBasketButton2 = new Locator(LocatorType.XPATH, "//div[@class='add-to-basket-button']");
    public Locator backToAllRewardsReference = new Locator(LocatorType.XPATH, "//a[@class='back-to-rewards link']");

    //Bradcrumb locators
    public Locator basicLevelBreadcrumbLocator = new Locator(LocatorType.XPATH, "//span[@class='item action']");
    public Locator lastLevelBradcrumbBeforeTotal = new Locator(LocatorType.XPATH, "//span[@class='item last before-total']");
    public Locator lastLevelBradcrumb = new Locator(LocatorType.XPATH, "//span[@class='item last']");
    public Locator breadcrumbTotalProductsCounter = new Locator(LocatorType.XPATH, "//span[@class='total']");

    //Facets component
        //department
        public Locator departmentFacet = new Locator(LocatorType.XPATH, "//div[@class='filter-department-view s-department']");
        public Locator departmentToSelectNameBasic = new Locator(LocatorType.XPATH, "//div[@class='filter-department-view s-department']//div[@class='body reg-items']//div//span[@class='value-label']");
        public Locator departmentToSelectProductnumber = new Locator(LocatorType.XPATH, "//div[@class='filter-department-view s-department']//div[@class='body reg-items']//div//span[2]");
        //category
        public Locator categoryFacet = new Locator(LocatorType.XPATH, "//div[@class='filter-singleval-view s-categoryFromFeedExtracted_multiVal']//div[contains(text(),'Category')]");
        public Locator categoryClearButton = new Locator(LocatorType.XPATH, "//div[@class='filter-singleval-view s-categoryFromFeedExtracted_multiVal']//span[@class='clear']");
        public Locator categoryToSelectNameBasic = new Locator(LocatorType.XPATH, "//div[@class='filter-singleval-view s-categoryFromFeedExtracted_multiVal']//div[@class='body reg-items']//div//span[@class='value-label']");
        public Locator categoryToSelectProductnumber = new Locator(LocatorType.XPATH, "//div[@class='filter-singleval-view s-categoryFromFeedExtracted_multiVal']//div[@class='body reg-items']//div//span[@class='count']");
        //brand
        public Locator brandFacet = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-brandName']//div[contains(text(),'Brand')]");
        public Locator brandClearButton = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-brandName']//span[@class='clear']");
        public Locator brandToSelectNameBasic = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-brandName']//div[@class='filter-checkbox-item']//span[@class='value-label']");
        public Locator brandToSelectProductnumber = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-brandName']//div[@class='filter-checkbox-item']//span[@class='count']");
        public Locator brandFacetSearchField = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-brandName']//div[@class='search']//input");
        //type
        public Locator typeFacet = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-type']//div[contains(text(),'Type')]");
        public Locator typeClearButton = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-type']//div[contains(text(),'Type')]//span[@class='clear']");
        public Locator typeToSelectNameBasic = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-type']//div[@class='filter-checkbox-item']//span[@class='value-label']");
        public Locator typeToSelectProductnumber = new Locator(LocatorType.XPATH, "//div[@class='filter-multival-view s-type']//div[@class='filter-checkbox-item']//span[@class='count']");
        public Locator typeFacetSearchField = new Locator(LocatorType.XPATH, "//div[2][@class='filter-multival-view s-type']//div[@class='search']//input");
        //epoints ranges
        public Locator epointsFacet = new Locator(LocatorType.XPATH, "//div[@class='filter-range-view i-epointsToPurchase']");
        public Locator epointsClearButton = new Locator(LocatorType.XPATH, "//div[@class='filter-range-view i-epointsToPurchase']//span[@class='clear']");
        public Locator epointsFromField = new Locator(LocatorType.XPATH, "//div[@class='filter-range-view i-epointsToPurchase']//div[@class='filter-range-item']//input[@name='from']");
        public Locator epointsToField = new Locator(LocatorType.XPATH, "//div[@class='filter-range-view i-epointsToPurchase']//div[@class='filter-range-item']//input[@name='to']");
        public Locator epointsGoButton = new Locator(LocatorType.XPATH, "//div[@class='filter-range-view i-epointsToPurchase']//div[@class='filter-range-item']//button");
        public Locator foundBetweenInformation = new Locator(LocatorType.XPATH, "//span[@class='minmax']");

}