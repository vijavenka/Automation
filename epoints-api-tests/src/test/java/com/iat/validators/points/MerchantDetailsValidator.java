package com.iat.validators.points;

import com.iat.utils.ResponseContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MerchantDetailsValidator {

    public void compareMerchantDetailsDataWithMerchantsListData(ResponseContainer responseMerchantsList, ResponseContainer responseMerchantDetails, int merchantIndex) {
        //TODO for sure this can be done much more pretty
        String merchantNameFromList = responseMerchantsList.getString("merchants.name[" + merchantIndex + "]");
        String merchantImageUrlFromList = responseMerchantsList.getString("merchants.imageUrl[" + merchantIndex + "]");
        String merchantDescriptionFromList = responseMerchantsList.getString("merchants.description[" + merchantIndex + "]");
        String merchantSiteUrlFromList = responseMerchantsList.getString("merchants.siteUrl[" + merchantIndex + "]");
        String merchantEpointsMultiplierFromList = responseMerchantsList.getString("merchants.epointsMultiplier[" + merchantIndex + "]");
        String merchantDomainFromList = responseMerchantsList.getString("merchants.merchantDomain[" + merchantIndex + "]");
        String merchantDeliveryDescriptionFromList = responseMerchantsList.getString("merchants.deliveryDescription[" + merchantIndex + "]");
        String merchantNetworkCodeFromList = responseMerchantsList.getString("merchants.networkCode[" + merchantIndex + "]");
        String merchantZoneFromList = responseMerchantsList.getString("merchants.zone[" + merchantIndex + "]");
        String merchantLeadGeneratorFromList = responseMerchantsList.getString("merchants.leadGenerator[" + merchantIndex + "]");
        String merchantSeoTitleFromList = responseMerchantsList.getString("merchants.seoTitle[" + merchantIndex + "]");
        String merchantVoucherCountFromList = responseMerchantsList.getString("merchants.voucherCount[" + merchantIndex + "]");
        String merchantFavouriteFromList = responseMerchantsList.getString("merchants.favourite[" + merchantIndex + "]");

        String merchantName = responseMerchantDetails.getString("name");
        String merchantImageUrl = responseMerchantDetails.getString("imageUrl");
        String merchantDescription = responseMerchantDetails.getString("description");
        String merchantSiteUrl = responseMerchantDetails.getString("siteUrl");
        String merchantEpointsMultiplier = responseMerchantDetails.getString("epointsMultiplier");
        String merchantDomain = responseMerchantDetails.getString("merchantDomain");
        String merchantDeliveryDescription = responseMerchantDetails.getString("deliveryDescription");
        String merchantNetworkCode = responseMerchantDetails.getString("networkCode");
        String merchantZone = responseMerchantDetails.getString("zone");
        String merchantLeadGenerator = responseMerchantDetails.getString("leadGenerator");
        String merchantSeoTitle = responseMerchantDetails.getString("seoTitle");
        String merchantVoucherCount = responseMerchantDetails.getString("voucherCount");
        String merchantFavourite = responseMerchantDetails.getString("favourite");

        assertThat("Merchant names are not the same list!", merchantNameFromList, is(merchantName));
//        assertThat("Merchant names are not the same list!", merchantImageUrlFromList, is(merchantImageUrl));
        assertThat("Merchant names are not the same list!", merchantDescriptionFromList, is(merchantDescription));
//        assertThat("Merchant names are not the same list!", merchantSiteUrlFromList, is(merchantSiteUrl));
        assertThat("Merchant names are not the same list!", merchantEpointsMultiplierFromList, is(merchantEpointsMultiplier));
        assertThat("Merchant names are not the same list!", merchantDomainFromList, is(merchantDomain));
        assertThat("Merchant names are not the same list!", merchantDeliveryDescriptionFromList, is(merchantDeliveryDescription));
        assertThat("Merchant names are not the same list!", merchantNetworkCodeFromList, is(merchantNetworkCode));
        assertThat("Merchant names are not the same list!", merchantZoneFromList, is(merchantZone));
        assertThat("Merchant names are not the same list!", merchantLeadGeneratorFromList, is(merchantLeadGenerator));
        assertThat("Merchant names are not the same list!", merchantSeoTitleFromList, is(merchantSeoTitle));
        assertThat("Merchant names are not the same list!", merchantVoucherCountFromList, is(merchantVoucherCount));
        assertThat("Merchant names are not the same list!", merchantFavouriteFromList, is(merchantFavourite));


    }

}