package com.iat.ePoints.Locators.Get;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 17.01.14
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class GetRetailerPageLocators {

//Main block with trust pilot
    public Locator retailerImage = new Locator(LocatorType.XPATH, "//div[@class='retailer-image']");
    public Locator retailerName = new Locator(LocatorType.XPATH, "//div[@class='section top-section']//h1");
    public Locator epointsMultiplier = new Locator(LocatorType.XPATH, "//div[@class='section top-section']//div[@class='multiplier']");
    public Locator localTrustPilotViev = new Locator(LocatorType.XPATH, "//div[@class='rating-modal']//div");
//Favourite retailer
    public Locator addToFavouritesPlusButton = new Locator(LocatorType.XPATH, "//div[@class='favourite-retailer-widget']//i[@class='add-to-favourites-icon icon-plus']");
    public Locator addToFavouritesOkButton = new Locator(LocatorType.XPATH, "//div[@class='favourite-retailer-widget']//i[@class='favourite-icon icon-ok']");
//Modal window trust pilot Locators
    public Locator modalTrustPilotContainer = new Locator(LocatorType.XPATH, "//div[@class='module-container']");
    public Locator modalTrustPilotRetailerName = new Locator(LocatorType.XPATH, "//div[@class='module-container']//span[@class='merchant-name']");
    public Locator modalTrustPilotRateSection = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@id='ratingDetails']");
    public Locator modalTrustPilotCommentSection = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@id='reviews']");
    public Locator modalTrustPilotCloseButton = new Locator(LocatorType.XPATH, "//div[@class='module-container']//a[@class='close-modal-link link']");
    public Locator externalTrustPilotLink = new Locator(LocatorType.XPATH, "//div[@class='section top-section']//a[@class='tp-icon']");
    public Locator goToRetailerButton = new Locator(LocatorType.XPATH, "//div[@class='section top-section']//a[@class='retailerLink btn btn-yellow clickOutLink']");
//Special offers block
    public Locator specialOffersContainer = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']");
    public Locator specialOffersShowingOf = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='section-header']//div[@class='results-info']");
    public Locator specialOffersArrowRight = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='section-header']//i[@class='icon-chevron-right']");
    public Locator specialOffersArrowLeft = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='section-header']//i[@class='icon-chevron-left']");
    public Locator specialOffersSelectCategory = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='section-header']//select[@class='categories-filter-dropdown']");
    public Locator specialOffersProductImage = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//div[@class='image']");
    public Locator specialOffersPercentageSaving = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//div[@class='percent-saving']");
    public Locator specialOffersEpointsEarned = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//div[@class='epoints-earned']");
    public Locator specialOffersProductTitle = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//div[@class='title']");
    public Locator specialOffersOldPrice = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//div[@class='price-old']");
    public Locator specialOffersNewPrice = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//div[@class='price']");
    public Locator specialOffersFromRetailer = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//div[@class='price-from-retailer']");
    public Locator specialOffersBuyButton = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//div[@class='product-card-view']//a[@class='buy-button btn btn-yellow clickOutLink']");
    public Locator specialOffersSeeAll = new Locator(LocatorType.XPATH, "//div[@id='retailerSpecialOffersRegion']//a[contains(text(),'See all')]");
//Product Categories block
    public Locator productCategoriesContainer = new Locator(LocatorType.XPATH, "//div[@id='productCategoriesRegion']");
    public Locator productCategoriesMainCategories = new Locator(LocatorType.XPATH, "//div[@id='productCategoriesRegion']//a[@class='categories-title']");
    public Locator productCategoriesSubCategories= new Locator(LocatorType.XPATH, "//div[@id='productCategoriesRegion']//div[@class='categories-breadcrumb']//a");
    public Locator productCategoriesSeeMoreButton= new Locator(LocatorType.XPATH, "//div[@id='productCategoriesRegion']//span[@class='evt-see-more']");
//Vouchers block
    public Locator vouchersContainer = new Locator(LocatorType.XPATH, "//div[@id='retailerVouchersRegion']");
    public Locator vouchersShowingPaginationLeftArrow = new Locator(LocatorType.XPATH, "//div[@class='reg-small-page-control inline-region']//i[@class='icon-chevron-left']");
    public Locator vouchersShowingPaginationRightArrow = new Locator(LocatorType.XPATH, "//div[@class='reg-small-page-control inline-region']//i[@class='icon-chevron-right']");
    public Locator voucherName = new Locator(LocatorType.XPATH, "//article[@class='module-container type-voucher']//h4[@itemprop='name']");
    public Locator voucherDescription = new Locator(LocatorType.XPATH, "//div[@id='retailerVouchersRegion']//div[@class='voucherbox']//p[@class='voucher-description']");
    public Locator voucherButtonGet = new Locator(LocatorType.XPATH, "//article[@class='module-container type-voucher']//a[@class='get-voucher-codes btn btn-yellow clickOutLink']");
    public Locator voucherExpiresDate = new Locator(LocatorType.XPATH, "//article[@class='module-container type-voucher']//span[@class='voucher-expire']");
    public Locator voucherCode = new Locator(LocatorType.XPATH, "//div[@class='voucher-code']");
    public Locator voucherOpenSiteButton = new Locator(LocatorType.XPATH, "//div[@class='open-site']");
    public Locator voucherFacebookShare = new Locator(LocatorType.XPATH, "//article[@class='module-container type-voucher']//span[@class='icon-facebook-squared']");
    public Locator voucherTwitterShare = new Locator(LocatorType.XPATH, "//article[@class='module-container type-voucher']//span[@class='icon-twitter']");
    public Locator voucherSeeAllButton = new Locator(LocatorType.XPATH, "//div[@id='retailerVouchersRegion']//a[contains(text(),'See all')]");
//Delivery details block
    public Locator deliveryDetailsContainer = new Locator(LocatorType.XPATH, "//div[@class='box box-delivery-details']");
    public Locator deliveryDetailsBoxName = new Locator(LocatorType.XPATH, "//div[@class='box box-delivery-details']//div[@class='section-header']");
    public Locator deliveryDetailsBoxContent = new Locator(LocatorType.XPATH, "//div[@class='box box-delivery-details']//p");
//Address block
//Similar products block
    public Locator similarRetailersContainer = new Locator(LocatorType.XPATH, "//div[@class='box box-similar-retailers']");
    public Locator similarRetailersArrowLeft = new Locator(LocatorType.XPATH, "//div[@id='similarRetailersRegion']//a[@id='retailers_prev']");
    public Locator similarRetailersArrowRight = new Locator(LocatorType.XPATH, "//div[@id='similarRetailersRegion']//a[@id='retailers_next']");
    public Locator similarRetailersBasicRetailerCard = new Locator(LocatorType.XPATH, "//div[@id='similarRetailersRegion']//li");
    public Locator similarRetailersBasicInfoOffersButton = new Locator(LocatorType.XPATH, "//div[@id='similarRetailersRegion']//li//div[@class='retailerCard-hover']//a[contains(text(),'Info & Offers')]");
    public Locator similarRetailersBasicVisitRetailerButton = new Locator(LocatorType.XPATH, "//div[@id='similarRetailersRegion']//li//div[@class='retailerCard-hover']//a[contains(text(),'Visit retailer')]");
    public Locator similarRetailersBasicRetailerName = new Locator(LocatorType.XPATH, "//div[@id='similarRetailersRegion']//li//div[@class='retailerCard-hover']//h5");
    public Locator similarRetailersContainerHeader = new Locator(LocatorType.XPATH, "//div[@id='similarRetailersRegion']//div[@class='section-header']");
//all products from retailer block
    public Locator allProductsFromRetailerContainer = new Locator(LocatorType.XPATH, "//div[@id='productsCountRegion']");
    public Locator allProductsFromRetailerLink = new Locator(LocatorType.XPATH, "//div[@id='productsCountRegion']//a[contains(text(),'All products from retailer ')]");
    public Locator allProductsFromRetailerProductsNumber = new Locator(LocatorType.XPATH, "//div[@id='productsCountRegion']//a[@class='products-count']");
//show all retailers button
    public Locator showAllRetailersButton = new Locator(LocatorType.XPATH, "//a[contains(text(),'Show all retailers')]");

}
