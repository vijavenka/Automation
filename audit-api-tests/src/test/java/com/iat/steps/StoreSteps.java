package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.*;
import com.iat.domain.Retailer;
import com.iat.domain.Store;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import com.iat.validators.RetailerValidator;
import com.iat.validators.StoreValidator;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class StoreSteps {

    private StoreActions storeActions = new StoreActions();
    private RetailerActions retailerActions = new RetailerActions();
    private AuditResultActions auditResultActions = new AuditResultActions();
    private LoginActions loginActions = new LoginActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private WholesalerActions wholesalerActions = new WholesalerActions();
    private ContractValidator contractValidator = new ContractValidator();
    private RetailerValidator retailerValidator = new RetailerValidator();
    private StoreValidator storeValidator = new StoreValidator();
    private HelpFunctions helpFunctions = new HelpFunctions();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response, retailerId, retailersEmails, storeId;
    ObjectMapper mapper = new ObjectMapper();
    List<String> storesIds = new ArrayList<>();
    List<Store> storeLocalList = new ArrayList<>();


    @When("^Store retailers bulk load call is made for incorrect data '(.+?)', '(.+?)', '(.+?)'$")
    public void bulkLoadStoreError(String fileName, int status, String partnerShortName) throws Throwable {
        response = storeActions.bulkUploadStoresAndRetailers(partnerShortName, fileName, status);
    }

    @Then("^Store retailers bulk load for incorrect data '(.+?)' response returns error message '(.+?)', '(.+?)'$")
    public void bulkLoadStoreListErrorsMessageValidation(String fileName, String error, String description) throws Throwable {
        if (error.equals("invalid_token") || error.equals("Forbidden")) {
//            String extractedStatus = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "status");
//            assertTrue("Incorrect status should be: " + status + " but was: " + extractedStatus, extractedStatus.equals(status));
            String extractedError = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "error");
            assertTrue("Incorrect error should be: " + error + " but was: " + extractedError, extractedError.equals(error));
            String extractedMessage = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "error_description");
            assertTrue("Incorrect message should be: " + description + " but was: " + extractedMessage, extractedMessage.equals(description));

            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", response);
        } else if (error.equals("error.validation")) {
            String message = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
            assertThat("File: " + fileName + " Incorrect message", message, is(error));
            String desc = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
            assertThat("File: " + fileName + " Incorrect description", desc, containsString(description));
        } else {
            String message = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
            assertThat("File: " + fileName + " Incorrect message", message, is(error));

        }
    }


    @When("^Extract stores ext_rel_ids from importRetailers file '(.+?)'$")
    public void extractExtRelIdForStoreFromImportRetailers(String fileName) throws Throwable {
        //save stores from file
        System.out.print("\nExtract ext_rel_id for stores in from importRetailers file: " + fileName + " and save it in storesIds: ");
        storesIds = storeActions.extractStoreExtRelIdFromImportRetailers(fileName);
        System.out.println(storesIds);

    }

    @When("^Generate unique stores ExtRelIds for '(.+?)' audit results file '(.+?)'$")
    public void auditResultsGeneratedUniqueStoresExtRelIdsAndStoreIds(String partnerShortName, String fileName) throws Throwable {
        //save stores from file
        System.out.print("\nModify ext_rel_id for stores in " + partnerShortName + " auditResults file: " + fileName + " and save it in storesIds: ");

        if (partnerShortName.toLowerCase().contains("todays"))
            storesIds = auditResultActions.updateTodaysAuditResultsFileUniqueStoresExtRelIdsAndReturn(fileName);
        else
            storesIds = auditResultActions.updatePremierAuditResultsFileUniqueStoresExtRelIdsAndReturn(fileName);
        System.out.println(storesIds);

    }

    @When("^Audit results file '(.+?)' have same ext_rel_id as created stores$")
    public void auditResultsUpdateStoresExtRelIdsAndStoreIds(String fileName) throws Throwable {
        //save stores from file
        System.out.print("\nModify ext_rel_id for stores in auditResults file: " + fileName + " to be the same as in exist stores: ");
        auditResultActions.updateAuditResultsFileToBeTheSameAsInCreatedStores(fileName, storesIds);

        System.out.println(storesIds);

    }

    @When("^Update stores ExtRelIds for import retailers file '(.+?)' to be the same as in audit results file$")
    public void importRetailersUpdateStoresExtRelIds(String fileName) throws Throwable {
        //save stores from file
        System.out.print("\nModify ext_rel_id for stores in importRetailers file: " + fileName + ": ");
        storeActions.updateImportRetailersFileExtRelIdsStores(fileName, storesIds);
        System.out.println(storesIds);

    }

    @When("^Update stores ExtRelIds for '(.+?)' import retailers file '(.+?)' to be the same as in audit results file and trigger processing$")
    public void retailersImportUpdateStoresExtRelIdsAndRunImport(String partnerShortName, String fileName) throws Throwable {

        importRetailersUpdateStoresExtRelIds(fileName);
        storesAndRetailersCallImportRetailers(fileName, partnerShortName, 200);
    }


    @When("^Stores with ext_rel_id are already in system for partner '(.+?)'$")
    public void createStoresBeforeTestWithExtRelId(String partnerShortName) throws Throwable {
        String chain = helpFunctions.getChainIdForPartnerShortName(partnerShortName);

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        if (storesIds.size() != 0) {
            for (int i = 0; i < storesIds.size(); i++) {
                Store store = mapper.readValue(storeActions.createStore("{\"storeName\":\"API_AUDIT_CMS_STORE_" + (i + 1) + "\",\"licensed\":true,\"active\":true,\"auditGroup\":\"API TESTS\",\"storeType\":\"type\",\"addressLine1\":\"adress\",\"addressLine2\":\"address\",\"addressLine3\":\"address\",\"addressLine4\":\"address\",\"postCode\":\"postCode\",\"country\":\"EnglandWales\",\"extRelId\":" + storesIds.get(i) + ",\"bigDlBranchId\":null,\"id\":null,\"chainId\": " + chain + "}", 201), Store.class);
                storeLocalList.add(store);
                assertThat("Retailer id is not null", String.valueOf(store.getRetailerId()), is("null"));
            }
        }
        dataExchanger.setStores(storeLocalList);
    }

    @When("^Import retailers call is made for upload retailers data \\(file: '(.+?)', partner: '(.+?)', code: '(.+?)'\\)$")
    public void storesAndRetailersCallImportRetailers(String fileName, String partnerShortName, int code) throws Throwable {
        storeActions.updateEmailsInImportRetailers(fileName);
        storeActions.bulkUploadStoresAndRetailers(partnerShortName, fileName, code);

        Thread.sleep(5000);
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        if (storesIds.size() != 0) {
            Store store = mapper.readValue(storeActions.getStore(storeActions.getStoreIdForStoreExtRelId(storesIds.get(0)), 200), Store.class);
            dataExchanger.clearStoreListAndSetStore(store);
            for (int i = 1; i < storesIds.size(); i++) {
                store = mapper.readValue(storeActions.getStore(storeActions.getStoreIdForStoreExtRelId(storesIds.get(i)), 200), Store.class);
                dataExchanger.addStoreToStoresList(store);
            }
        }
    }

    @When("^Import Retailers call is made for upload store data '(.+?)', '(.+?)', '(.+?)'$")
    public void storesAndRetailersCallImportStores(String fileName, String partnerShortName, int code) throws Throwable {
        storeActions.bulkUploadStoresAndRetailers(partnerShortName, fileName, code);
    }

    @Then("^Store retailers bulk load properly (.+?) retailer$")
    public void storeBulkImportRetailerValidation(String createdFlag) throws Throwable {
        boolean flag = createdFlag.equals("add") ? true : false;
        retailersEmails = dataExchanger.getGeneratedRetailerMail();

        for (String email : retailersEmails.split(",")) {
            String retailerId = retailerActions.getRetailerIdForRetailerEmail(email.toLowerCase());
            if (flag)
                assertThat("Retailer not created for email: " + email, String.valueOf(retailerId), not(""));
            else
                assertThat("Retailer not created for email: " + email, String.valueOf(retailerId), is(""));
        }
    }

    @Then("^Store retailers bulk load - relate partner store with retailer$")
    public void storeBulkImportStoreValidation() throws Throwable {
        retailersEmails = dataExchanger.getGeneratedRetailerMail();

        dataExchanger.clearRetailersList();
        for (String email : retailersEmails.split(",")) {
            Retailer retailer = mapper.readValue(retailerActions.getRetailerById(retailerActions.getRetailerIdForRetailerEmail(email), 200), Retailer.class);
            dataExchanger.addRetailerToRetailersList(retailer);
        }
        assertThat("Stores and retailers list not equal", dataExchanger.getStores().size(), is(dataExchanger.getRetailers().size()));

        for (int j = 0; j < dataExchanger.getRetailers().size(); j++) {
            assertThat("Incorrect store <-> retailer assigned", dataExchanger.getRetailers().get(j).getId(), is(dataExchanger.getStores().get(j).getRetailerId()));
            storeValidator.validateIfStoreRetailerIdUpdated(dataExchanger.getStores().get(j).getId(), dataExchanger.getRetailers().get(j).getId());
        }
    }

    @Then("^Store retailers bulk load for data '(.+?)', '(.+?)' properly relate wholesaler with retailer$")
    public void storeBulkImportWholesalerValidation(String partnerShortName, String wholesalerProvided) throws Throwable {
        String wholesalerName = Config.apiWholesaler1Name;
        String wholesalerId = wholesalerActions.getWholesalerIdForWholesalerName(wholesalerName);

        for (Retailer ret : dataExchanger.getRetailers())
            if (partnerShortName.equals("TodaysRetailers")) {
                assertThat("Retailer wholesaler is incorrect: ", ret.getWholesalerId(), is(wholesalerId));
            } else if (partnerShortName.equals("PremierRetailers") || partnerShortName.equals("nisaRetailers")) {
                if (wholesalerProvided.equals("false")) {
                    assertThat("Retailer wholesaler is incorrect", String.valueOf(ret.getWholesalerId()), is("null"));
                } else if (wholesalerProvided.equals("true")) {
                    assertThat("Retailer wholesaler is incorrect: ", ret.getWholesalerId(), is(wholesalerId));
                }
            }
    }

    @Then("^Epoints account was created for imported retailers$")
    public void validateIfRetailerCreatedInEpoints() throws Throwable {
        retailersEmails = dataExchanger.getGeneratedRetailerMail();

        for (String email : retailersEmails.split(",")) {
            String retailerId = retailerActions.getRetailerIdForRetailerEmail(email.toLowerCase());
            response = retailerActions.getRetailerById(retailerId, 200);
            String uuid = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "epointsUuid");
            assertThat("Retailer: " + email + " uuid is empty", uuid.length() != 0);
            retailerValidator.checkIfCreatedRetailerEpointsAccountWasCreated(true, uuid, email);
        }
    }

    @Before("@clearRetailerIdFOrSpecificStoresBeforeTest")
    public void clearRetailerIdForSpecificStores() {
        assertTrue("TODO", false);
        loginActions.userLogIn("admin,admin");
        String[] storeNames = {Config.apiStore2Name, Config.apiStore2PremierName};

        for (String storeName : storeNames) {
            response = storeActions.getStore(storeActions.getStoreIdForStoreName(storeName), 200);
            JSONObject jsonObject = new JSONObject(response);
            jsonObject.put("retailerId", JSONObject.NULL);
            storeActions.updateStore(jsonObject.toString(), 200);
        }

    }

    @Then("^Only '(.+?)' store with ext_rel_id '(.+?)' was loaded to db for partner '(.+?)'$")
    public void only_one_store_with_ext_rel_id_was_loaded_to_db_for_partner_TodaysRetailers(int count, String extRelId, String partnerShortName) throws Throwable {
        assertTrue("TODO", false);

        String chain = helpFunctions.getChainIdForPartnerShortName(partnerShortName);

    }

    @Given("^Get store details by externalId '(.+?)'$")
    public void getStoreDetailsByExternalId(String extRelId) throws Throwable {
//        assertTrue("TODO", false);
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Store store = mapper.readValue(storeActions.getStore(storeActions.getStoreIdForStoreExtRelId(extRelId), 200), Store.class);
        dataExchanger.clearStoreListAndSetStore(store);
    }

    @Given("^Get store details by externalId '(.+?)' - not found$")
    public void getStoreDetailsByExternalIdNotFound(String extRelId) throws Throwable {
        storeActions.getStoreIdForStoreExtRelIdNotFound(extRelId);
    }


    @Given("^Get store details by externalId and partner '(.+?)', '(.+?)'$")
    public void getStoreDetailsByExternalIdAndPartner(String extRelId, String partnerShortName) throws Throwable {

        //TODO step to remove?
//        String chain = helpFunctions.getChainIdForPartnerShortName(partnerShortName);
//
//        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
//        Store store = mapper.readValue(storeActions.getStore(storeActions.getStoreIdForStoreExtRelIdAndChain(extRelId, chain), 200), Store.class);
//        dataExchanger.clearStoreListAndSetStore(store);

    }

    @When("^Store bulk load call is made for data '(.+?)', '(.+?)', '(.+?)'$")
    public void storesBulkLoad(String fileName, String partnerShortName, int code) throws Throwable {
        storeActions.bulkUploadStoresPremierOnly(partnerShortName, fileName, code);
    }

    @Then("^Store bulk load for data '(.+?)' properly add/not add store for chain '(.+?)'$")
    public void validateStoreProperlyUploaded(String fileName, String partnerShortName) throws Throwable {
        storeValidator.validateIfStoreProperlyCreated(partnerShortName, fileName);
    }

    @After("@deleteStoreAfterTest")
    public void deleteStore() throws Throwable {
        String id = dataExchanger.getStore1Id();
        if (id != null)
            storeActions.deleteStore(id, 200);
    }

    @Then("^Delete store by id '(.+?)', code '(.+?)'$")
    public void deleteSupplierById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            for (int i = 0; i < dataExchanger.getStores().size(); i++) {
                id = dataExchanger.getStores().get(i).getId();
                response = storeActions.deleteStore(id, code);
            }
    }


    @When("^Store bulk load call is made for incorrect data '(.+?)', '(.+?)', '(.+?)'$")
    public void storeUploadErrorMessage(String fileName, String partnerShortName, int code) throws Throwable {
        response = storeActions.bulkUploadStoresPremierOnly(partnerShortName, fileName, code);
    }

    @Then("^Store bulk load error message will be returned '(.*?)', '(.*?)'$")
    public void validateStoreUploadErrorMessage(String message, String description) throws Throwable {
        storeValidator.validateStoreBulkErrorMessages(message, description, response);
    }

}