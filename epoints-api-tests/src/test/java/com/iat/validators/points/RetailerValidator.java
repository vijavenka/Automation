package com.iat.validators.points;

import com.iat.Config;
import com.iat.actions.points.MerchantsActions;
import com.iat.actions.points.SearchMerchantActions;
import com.iat.domain.retailer.Retailer;
import com.iat.domain.retailer.RetailersList;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RetailerValidator {

    private JdbcDatabaseConnector mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAdminPortal);
    private SearchMerchantActions searchMerchantActions = new SearchMerchantActions();
    private MerchantsActions merchantsActions = new MerchantsActions();
    private ResponseContainer response;

    //TODO when admin portal doorman will be introduced this can be changed to not use db connection
    public void validateRecentlyVisitedMerchantDataCorrectness(List<Retailer> retailers) {
        String merchantId;
        for (Retailer retailer : retailers) {
            merchantId = retailer.getId();
            assertThat("Wrong merchant name", mySQLConnector.getSingleResult("SELECT name FROM admin_ui.Merchant where id = '" + merchantId + "'"), is(retailer.getName()));
            assertThat("Wrong merchant image url", mySQLConnector.getSingleResult("SELECT Image.imageUrl FROM admin_ui.Image Image JOIN admin_ui.Merchant_Image Mimage ON Mimage.image_id = Image.id JOIN admin_ui.Merchant Merchant ON Merchant.id = Mimage.merchant_id where Merchant.id = '" + merchantId + "'"), is(retailer.getImageUrl()));
            if (retailer.getDescription() == null)
                assertThat("Wrong merchant Description", mySQLConnector.getSingleResult("SELECT description FROM admin_ui.Merchant where id = '" + merchantId + "'"), is(""));
            else
                assertThat("Wrong merchant Description", mySQLConnector.getSingleResult("SELECT description FROM admin_ui.Merchant where id = '" + merchantId + "'"), is(retailer.getDescription()));
            if (retailer.getSiteUrl() == null)
                assertThat("Wrong merchant SiteUrl", mySQLConnector.getSingleResult("SELECT siteUrl FROM admin_ui.MerchantZone where merchant_id = '" + merchantId + "'"), is(""));
            else
                assertThat("Wrong merchant SiteUrl", mySQLConnector.getSingleResult("SELECT siteUrl FROM admin_ui.MerchantZone where merchant_id = '" + merchantId + "'"), is(retailer.getSiteUrl()));
            assertThat("Wrong merchant epoints multiplier", Integer.parseInt(mySQLConnector.getSingleResult("SELECT epointsMultiplier FROM admin_ui.Merchant where id = '" + merchantId + "'")), is(retailer.getEpointsMultiplier()));
            if (retailer.getMerchantDomain() == null)
                assertThat("Wrong merchant domain", mySQLConnector.getSingleResult("SELECT merchantDomain FROM admin_ui.Merchant where id = '" + merchantId + "'"), isEmptyOrNullString());
            else
                assertThat("Wrong merchant domain", mySQLConnector.getSingleResult("SELECT merchantDomain FROM admin_ui.Merchant where id = '" + merchantId + "'"), is(retailer.getMerchantDomain()));
            assertThat("Wrong merchant network code", mySQLConnector.getSingleResult("SELECT Network.code FROM admin_ui.AffiliateNetwork Network JOIN admin_ui.Merchant Merchant ON Merchant.network_id = Network.id where Merchant.id = '" + merchantId + "'"), is(retailer.getNetworkCode()));
            assertThat("Wrong merchant zone", mySQLConnector.getSingleResult("SELECT region FROM admin_ui.Merchant where id = '" + merchantId + "'"), is(retailer.getZone()));
            if (retailer.getMaximumCommission() > 0)
                assertThat("Wrong merchant epoints maximum commission", Integer.parseInt(mySQLConnector.getSingleResult("SELECT maximumCommission FROM admin_ui.Merchant where id = '" + merchantId + "'")), is(retailer.getMaximumCommission()));
            if (retailer.getDaysToConfirmCommission() > 0)
                assertThat("Wrong merchant epoints days to confirm commission", Integer.parseInt(mySQLConnector.getSingleResult("SELECT daysToConfirmCommission FROM admin_ui.Merchant where id = '" + merchantId + "'")), is(retailer.getDaysToConfirmCommission()));
            if (retailer.isLeadGenerator())
                assertThat("Wrong merchant lead generator", mySQLConnector.getSingleResult("SELECT leadGenerator FROM admin_ui.Merchant where id = '" + merchantId + "'"), is("1"));
            else
                assertThat("Wrong merchant lead generator", mySQLConnector.getSingleResult("SELECT leadGenerator FROM admin_ui.Merchant where id = '" + merchantId + "'"), is("0"));
            if (retailer.getTermsAndConditions() == null)
                assertThat("Wrong merchant terms, should be null", (mySQLConnector.getSingleResult("SELECT termsAndConditions FROM admin_ui.Merchant where id = '" + merchantId + "'") == null || mySQLConnector.getSingleResult("SELECT termsAndConditions FROM admin_ui.Merchant where id = '" + merchantId + "'").equals("")));
            else
                assertThat("Wrong merchant terms", mySQLConnector.getSingleResult("SELECT termsAndConditions FROM admin_ui.Merchant where id = '" + merchantId + "'"), is(retailer.getTermsAndConditions()));
        }
    }

    public void validateRetailersAddedToFavourites(List<String> retailerIds) {
        response = searchMerchantActions.getSearchMerchantForLoggedUser("null;null;null;null;null;true");
        RetailersList retailersList = response.getAsObject(RetailersList.class);
        boolean merchantFound = false;

        assertThat("favouritesCount and amount of merchants should match!", retailerIds, allOf(
                hasSize(retailersList.getFavouritesCount()),
                hasSize(retailersList.getMerchants().size())));

        for (String retailerId : retailerIds) {
            for (Retailer retailer : retailersList.getMerchants()) {
                merchantFound = retailerId.equals(retailer.getId());
                if (!merchantFound) continue;
                response = merchantsActions.getMerchantDetails(retailerId, 200);
                break;
            }
            assertThat("Merchant " + retailerId + " not found in favourites", merchantFound);
        }
    }

    public void validateRetailerRemovedFromFavourites(String removedRetailerId) {
        response = searchMerchantActions.getSearchMerchantForLoggedUser("null;null;null;null;null;true");
        RetailersList retailersList = response.getAsObject(RetailersList.class);
        boolean merchantFound = false;

        for (Retailer retailer : retailersList.getMerchants()) {
            merchantFound = removedRetailerId.equals(retailer.getId());
            if (merchantFound) break;
        }

        assertThat("Merchant " + removedRetailerId + " found in favourites, bur should be removed", !merchantFound);

    }
}