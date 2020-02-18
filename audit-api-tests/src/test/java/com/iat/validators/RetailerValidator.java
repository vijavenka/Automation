package com.iat.validators;

import com.iat.Config;
import com.iat.actions.RetailerActions;
import com.iat.actions.StoreActions;
import com.iat.domain.Chain;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RetailerValidator {

    private StoreActions storeActions = new StoreActions();
    private RetailerActions retailerActions = new RetailerActions();

    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPool_pointsManager);

    String response;

    @Deprecated
    public void checkReturnedDataCorrectnessOfCreatedRetailer(String retName, String retEmail, String response) {

    }

    public void checkIfCreatedRetailerEpointsAccountWasCreated(boolean wasAdded, String retUuid, String retEmail) {
        if (wasAdded) {
            assertTrue("Epoints account was not created", new UserRepositoryImpl().findByEmail(retEmail.toLowerCase()).getUuid().equals(retUuid));
        } else {
            if (retEmail != null) {
                if (retEmail.length() > 0 && !retEmail.equals(Config.retailerTakenEmail)) {
                    assertTrue("Epoints account was created but should not", new UserRepositoryImpl().findByEmail(retEmail).getUuid() == null);
                }
            }

        }
    }


    public boolean isContainsTodays(List<Chain> chains) {
        boolean flag = false;
        for (Chain chain : chains) {
            if (chain.getChainName().equalsIgnoreCase("Today's")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isContainsPremier(List<Chain> chains) {
        boolean flag = false;
        for (Chain chain : chains) {
            if (chain.getChainName().equalsIgnoreCase("Premier")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isContainsNisa(List<Chain> chains) {
        boolean flag = false;
        for (Chain chain : chains) {
            if (chain.getChainName().equalsIgnoreCase("Nisa")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void checkRetailerEpointsRegistrationSite(String partnerShortName, String retEmail) {
        String epointsRegistrationSiteName = new UserRepositoryImpl().findByEmail(retEmail.toLowerCase()).getRegistrationSiteShortName();
        assertThat("Epoints account created with incorrect registrationSite", epointsRegistrationSiteName, is(partnerShortName));

    }

    public void checkRetailerEpointsRegistrationPoints(String retUuid) {
        String epointsConfirmedPoints = new UserRepositoryImpl().findUserByUserId(retUuid).getConfirmed();
        assertThat("Epoints account created with incorrect Points value", epointsConfirmedPoints, is("50"));
    }

    public void checkIfNewRetailerIsAvailableOnRetailerList() {
        response = retailerActions.getRetailersList("0;10;id,desc", 200);
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(response);
        boolean found = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "email").equalsIgnoreCase(dataExchanger.getRetailer().getEmail())) {
                found = true;
                assertThat("Retailer id is not as expected ", jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "id"), is(dataExchanger.getRetailer().getId()));
                assertThat("Retailer name is not as expected ", jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "retailerName"), is(String.valueOf(dataExchanger.getRetailer().getRetailerName())));
                assertThat("Retailer epointsId is not as expected ", jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "epointsUuid"), is(dataExchanger.getRetailer().getUuid()));
                assertThat("Retailer wholesaler is not as expected ", jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "wholesalerId"), is(String.valueOf(dataExchanger.getRetailer().getWholesalerId())));
                break;
            }
        }
        assertTrue("Retailer was not found on retailers list", found);
    }

    public void validateCreateRetailerErrorMessages(String message, String description, String objectNameFE, String fieldFE, String messageFE, String response) {
        boolean fieldFound = false;
        assertTrue("Incorrect error validation message", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message").equals(message));

        if (description.contains("{wholesalerId}")) {
            assertTrue("Wrong error description in case of not existing wholesaler id", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description").equals(description.replace("{wholesalerId}", dataExchanger.getWholesalerId())));
        } else if (description.contains("{retailerEmail}")) {
            assertTrue("Wrong error description in case of not existing retailer name", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description").equals(description.replace("{retailerEmail}", dataExchanger.getRetailerEmail().toLowerCase())));
        } else if (description.contains("{retailerId}")) {
            assertTrue("Wrong error description in case of not existing retailer id", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description").equals(description.replace("{retailerId}", dataExchanger.getRetailerId())));
        } else if (description.contains("{storeId}")) {
            assertTrue("Wrong error description in case of not existing store id", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description").equals(description.replace("{storeId}", dataExchanger.getStore1Id())));
        } else if (description.contains("{chainId}")) {
            assertTrue("Wrong error description in case of not existing or wrong chain id", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description").equals(description.replace("{chainId}", dataExchanger.getChainId())));
        } else if (description.contains("{retailerEmail}")) {
            assertTrue("Wrong error description in case of duplicated email", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description").equals(description.replace("{retailerEmail}", dataExchanger.getRetailerEmail().toLowerCase())));
        }
        if (!message.equals("error.general")) {
            String[] objectNames = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "fieldErrors", "objectName");
            String[] fieldNames = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "fieldErrors", "field");
            String[] messages = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "fieldErrors", "message");

            for (int i = 0; i < fieldNames.length; i++) {
                if (fieldNames[i].equals(fieldFE)) {
                    fieldFound = true;
                    assertTrue("Incorrect message for field: " + fieldFE + "  was: " + messages[i] + " but should be: " + messageFE, messages[i].equals(messageFE.replace("empty", "")));
                    assertTrue("Incorrect objectName for field: " + fieldFE + "  was: " + objectNames[i] + " but should be: " + objectNameFE, objectNames[i].equals(objectNameFE));
                }
            }
            assertTrue("Missing field: \"" + fieldFE + "\" in error response", fieldFound);
        }
    }

    public void checkIfRetailerWasDeletedFromSelectedPartner(boolean wasDeleted, String retailerEmail, String partnerId) throws InterruptedException {
        response = new UserRepositoryImpl().returnAllUserData(retailerEmail.toLowerCase());
        if (wasDeleted) {
            assertTrue("Retailers was not deleted from virtual group or group not found", getVirtualGroupStatusByPartnerId(response, partnerId).equals("deleted"));
        } else {
            assertTrue("Retailers was deleted from  virtual group or group not found", getVirtualGroupStatusByPartnerId(response, partnerId).equals("active"));
        }
    }

    public void checkIfVirtualGroupOfChianPartnerWasCreatedAndIsActive(boolean wasCreated, String retailerEmail, String partnerShortName) throws InterruptedException {
        response = new UserRepositoryImpl().returnAllUserData(retailerEmail.toLowerCase());
        String partnerId = mySQLConnector_pointsManager.getSingleResult("SELECT id FROM  Partner where shortName = \"" + partnerShortName + "\"");
        if (wasCreated) {
            assertTrue("Partner virtual Group was not created for retailer", getVirtualGroupStatusByPartnerId(response, partnerId).equals("active"));
        } else {
            assertTrue("Partner virtual Group was created for retailer", getVirtualGroupStatusByPartnerId(response, partnerId).equals("Not found"));
        }
    }

    private String getVirtualGroupStatusByPartnerId(String response, String partnerId) {
        String[] groupsPartners = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "userGroups", "partnerId");
        String[] groupsStatuses = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "userGroups", "status");
        for (int i = 0; i < groupsPartners.length; i++) {
            if (groupsPartners[i].equals(partnerId)) {
                return groupsStatuses[i];
            }
        }
        return "Not found";
    }

    public void checkIfTakenStoreRetailerIdWasChanged(boolean shouldBeTaken, String retId, String storeId) {
        response = storeActions.getStore(storeId, 200);
        if (shouldBeTaken) {
            assertTrue("Taken store retailerId was not changed but should be", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId").toString().equals(retId));
        } else {
            assertTrue("Taken store retailerId was changed but should not be", !jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId").toString().equals(retId));
        }
    }

    public void checkActiveStateOfTakenStore(String storeIdChain1, boolean activeState) {
        response = storeActions.getStore(storeIdChain1, 200);
        if (activeState) {
            assertTrue("Store is deactivated but should not be ", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "active") == "true");
        } else {
            assertTrue("Store is active but should not be ", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "active") == "false");
        }
    }

}