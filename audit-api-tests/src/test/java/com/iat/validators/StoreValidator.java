package com.iat.validators;

import com.iat.actions.StoreActions;
import com.iat.domain.Store;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

public class StoreValidator {

    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    private StoreActions storeActions = new StoreActions();
    String response;


    public void validateIfStoreRetailerIdUpdated(String storeId, String retailerId) {
        String response = storeActions.getStore(storeId, 200);
        String storeRetailerId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId");
        assertThat("Store retailer Id incorrect", storeRetailerId, is(retailerId));
    }

    public void validateIfStoreProperlyCreated(String partnerShortName, String fileName) {
        Store store = dataExchanger.getStores().get(dataExchanger.getStores().size() - 1);

        String chain = "1";
        if (partnerShortName.contains("Premier"))
            chain = "2";
        if (partnerShortName.contains("nisa"))
            chain = "3";

        assertThat("Incorrect chainId", store.getChainId(), is(chain));

        String storeName = "API_AUDIT_CMS_STORE";
        ;

        assertThat("Incorrect name", store.getStoreName(), is(storeName));
        assertThat("Incorrect active", store.getActive(), is("true"));

        if (fileName.contains("1a") || fileName.contains("1b"))
            assertThat("Incorrect country", store.getCountry(), is("EnglandWales"));
        else if (fileName.contains("1c") || fileName.contains("1d"))
            assertThat("Incorrect country", store.getCountry(), is("NorthernIreland"));
        else if (fileName.contains("1e"))
            assertThat("Incorrect country", store.getCountry(), is("Scotland"));
        else
            assertThat("Case not covered", store.getCountry(), is("not covered"));
    }

    public void validateStoreBulkErrorMessages(String message, String description, String response) {
        String msg = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
        assertThat("Incorrect error message", msg, is(message));

        String desc = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
        assertThat("Incorrect error description", desc, containsString(description));

    }
}
