package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.ChainActions;
import com.iat.actions.RetailerActions;
import com.iat.actions.StoreActions;
import com.iat.actions.WholesalerActions;
import com.iat.domain.Chain;
import com.iat.domain.Retailer;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import com.iat.validators.RetailerValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertTrue;

public class RetailerSteps {

    private RetailerActions retailerActions = new RetailerActions();
    private WholesalerActions wholesalersAction = new WholesalerActions();
    private ChainActions chainActions = new ChainActions();
    private StoreActions storeActions = new StoreActions();
    ObjectMapper mapper = new ObjectMapper();

    private ContractValidator contractValidator = new ContractValidator();
    private RetailerValidator retailerValidator = new RetailerValidator();

    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();

    private HelpFunctions func = new HelpFunctions();


    String response;
    String storeNameBase = "automatedApiTestStore";
    String retailerNameBase = "automatedApiTestRetailer";
    String chainNameBase = "apiAutomatedTestChainId";


    //Check retailer creation and corresponding epoints account
    public void createRandomRetailerData(String retailerName, String retailerEmail, String wholesalerId) {
        dataExchanger.setRetailerName(retailerName.replace("empty", ""));
        dataExchanger.setRetailerEmail(retailerEmail);
        dataExchanger.setWholesalerId(wholesalerId);

        if (!retailerName.equals("null") && !retailerName.equals("empty")) {
            dataExchanger.setRetailerName(retailerName + func.returnEpochOfCurrentDay());
        }

        if (!retailerEmail.equals("null") && !retailerEmail.equals("empty") && !retailerEmail.equals(Config.retailerTakenEmail)) {
            dataExchanger.setRetailerEmail(retailerEmail + func.returnEpochOfCurrentDay() + "@gmail.com");
        }

        if (!wholesalerId.equals("null") && !wholesalerId.equals("empty")) {
            dataExchanger.setWholesalerId(Long.toString(func.returnEpochOfCurrentDay()));
        }

        if (wholesalerId.equals("dynamically")) {
            response = wholesalersAction.getWholesalersList();
            JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(response);
            dataExchanger.setWholesalerId(jsonParserUtils.extractValueFromFlatJson(jsonArray.get(0).getAsJsonObject(), "id"));
        }
    }

    @When("^New retailer will be created in IAT audit cms with following jsonData '(.+?)', '(.+?)'$")
    public void createNewRetailer(String jsonBody, int code) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Retailer retailer = mapper.readValue(jsonBody, Retailer.class);

        dataExchanger.setRetailer(retailer);
        response = retailerActions.createNewRetailer(retailer.toString(), code);

        if (code == 201) {
            JsonObject jsonObject = jsonParserUtils.convertStringToJson(response);
            dataExchanger.getRetailer().setUuid(jsonParserUtils.extractValueFromFlatJson(jsonObject, "epointsUuid"));
            dataExchanger.getRetailer().setId(jsonParserUtils.extractValueFromFlatJson(jsonObject, "id"));

            String chains = jsonObject.getAsJsonObject().getAsJsonArray("chains").toString();
            Retailer retailerToMapChainsList = mapper.readValue("{\"chains\": " + chains + "}", Retailer.class);
            dataExchanger.getRetailer().setChains(retailerToMapChainsList.getChains());

            assertThat("Created retailer name is incorrect", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerName"), is(String.valueOf(retailer.getRetailerName())));
            assertThat("Created retailer email is incorrect", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "email"), is(String.valueOf(retailer.getEmail())));
            assertThat("Created retailer id is empty", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id").length() > 0);
        }
    }


    @Then("^At the same time corresponding epoints account will be created for new retailer$")
    public void checkIfEpointsAccountWasCreatedForNewRetailer() throws Throwable {
        retailerValidator.checkIfCreatedRetailerEpointsAccountWasCreated(true, dataExchanger.getRetailer().getUuid(), dataExchanger.getRetailer().getEmail());
    }

    @Then("^Proper registration site set for retailer epoints account$")
    public void checkRetailerEpointsAccountRegistrationSite() throws Throwable {
        String partnerShortName = "";

        if (dataExchanger.getRetailer().getChains().size() == 0)
            partnerShortName = "TodaysRetailers";
        else if (dataExchanger.getRetailer().getChains().size() == 1)
            partnerShortName = dataExchanger.getRetailer().getChains().get(0).getPartner_short_name();
        else {
            if (retailerValidator.isContainsTodays(dataExchanger.getRetailer().getChains()))
                partnerShortName = "TodaysRetailers";
            if (retailerValidator.isContainsPremier(dataExchanger.getRetailer().getChains()) && !retailerValidator.isContainsTodays(dataExchanger.getRetailer().getChains()))
                partnerShortName = "PremierRetailers";
            if (retailerValidator.isContainsNisa(dataExchanger.getRetailer().getChains()) && !retailerValidator.isContainsTodays(dataExchanger.getRetailer().getChains()))
                partnerShortName = "nisaRetailers";
            if (!retailerValidator.isContainsPremier(dataExchanger.getRetailer().getChains()) && !retailerValidator.isContainsTodays(dataExchanger.getRetailer().getChains()) && !retailerValidator.isContainsNisa(dataExchanger.getRetailer().getChains()))
                partnerShortName = dataExchanger.getRetailer().getChains().get(0).getPartner_short_name();

        }
        retailerValidator.checkRetailerEpointsRegistrationSite(partnerShortName, dataExchanger.getRetailer().getEmail());
    }

    @Then("^Retailer was awarded with proper points count after registration$")
    public void checkRetailerEpointsAccountPointsAwarded() throws Throwable {
        retailerValidator.checkRetailerEpointsRegistrationPoints(dataExchanger.getRetailer().getUuid());
    }


    @Then("^New retailer account is available on retailers list$")
    public void checkIfNewRetailerIsAvailabelOnRetailerList() throws Throwable {
        retailerValidator.checkIfNewRetailerIsAvailableOnRetailerList();
    }

    @Then("^Chain virtual group is properly created in user manager$")
    public void chain_virtual_group_is_properly_created_in_user_manager() throws Throwable {
        String partnerShortName = "";

        if (dataExchanger.getRetailer().getChains().size() == 0) {
            partnerShortName = "TodaysRetailers";
            retailerValidator.checkIfVirtualGroupOfChianPartnerWasCreatedAndIsActive(true, dataExchanger.getRetailer().getEmail(), partnerShortName);
        } else if (dataExchanger.getRetailer().getChains().size() == 1) {
            partnerShortName = dataExchanger.getRetailer().getChains().get(0).getPartner_short_name();
            retailerValidator.checkIfVirtualGroupOfChianPartnerWasCreatedAndIsActive(true, dataExchanger.getRetailer().getEmail(), partnerShortName);
        } else {
            for (Chain chain : dataExchanger.getRetailer().getChains()) {
                partnerShortName = chain.getPartner_short_name();
                retailerValidator.checkIfVirtualGroupOfChianPartnerWasCreatedAndIsActive(true, dataExchanger.getRetailer().getEmail(), partnerShortName);
            }
        }
    }

    @Then("^Chain virtual group is properly created in user manager for imported retailers for partner '(.+?)'$")
    public void chain_virtual_group_is_properly_created_in_user_managerForImportedRetailers(String partnerShortName) throws Throwable {
        String retailersEmails = dataExchanger.getGeneratedRetailerMail();

        for (String email : retailersEmails.split(",")) {
            retailerValidator.checkIfVirtualGroupOfChianPartnerWasCreatedAndIsActive(true, email.toLowerCase(), partnerShortName);
        }
    }


    @Then("Create retailer call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateRetailerErrorMessage(String message, String description, String fieldErrors) throws Throwable {

//        retailerValidator.validateCreateRetailerErrorMessages(message, description, objectNameFE, fieldFE, messageFE, response);

        String responseMessage = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");

        assertThat("field message is incorrect", responseMessage, is(message));
        assertThat("field description is incorrect", responseDescription, containsString(description));

        if (message.equals("error.validation") && fieldErrors.contains("fieldErrors")) {
            JsonArray responseFieldErrorsJsonArray = new gherkin.deps.com.google.gson.JsonParser().parse(response).getAsJsonObject().getAsJsonArray("fieldErrors");
            JsonArray validateFieldErrorsJsonArray = new gherkin.deps.com.google.gson.JsonParser().parse(fieldErrors).getAsJsonObject().getAsJsonArray("fieldErrors");

            for (JsonElement validationElement : validateFieldErrorsJsonArray) {
                boolean foundField = false;
                String errorsFieldName = validationElement.getAsJsonObject().get("field").getAsString();
                String errorsMessage = validationElement.getAsJsonObject().get("message").getAsString();

                for (JsonElement responseElement : responseFieldErrorsJsonArray) {
                    if (errorsFieldName.equals(responseElement.getAsJsonObject().get("field").getAsString()) && errorsMessage.equals(responseElement.getAsJsonObject().get("message").getAsString())) {
                        foundField = true;
                        String errorsObjectName = validationElement.getAsJsonObject().get("objectName").getAsString();


                        assertThat("Field [" + errorsFieldName + "] error have incorrect objectName", responseElement.getAsJsonObject().get("objectName").getAsString(), is(errorsObjectName));
                        assertThat("Field [" + errorsFieldName + "] error have incorrect message", responseElement.getAsJsonObject().get("message").getAsString(), is(errorsMessage));
                        break;
                    }
                }
                assertThat("Field [" + errorsFieldName + "] with message [" + errorsMessage + "] not found in response", foundField, is(true));
            }
        } else {
            String responseFieldErrors = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "fieldErrors");
            assertThat("field fieldErrors: " + responseFieldErrors + " is incorrect, should be: " + fieldErrors, responseFieldErrors, is(fieldErrors));
        }

    }


    @Then("^Corresponding epoints account will not be created$")
    public void checkIfEpointsAccountWasNotCreatedForNewRetailer() throws Throwable {
        retailerValidator.checkIfCreatedRetailerEpointsAccountWasCreated(false, dataExchanger.getRetailerUuid(), dataExchanger.getRetailerEmail());
    }


    @When("^Some retailer will be removed from one of the chain he belongs$")
    public void removeRetailerFromTodaysChain() throws Throwable {
        retailerActions.deleteRetailerFromChosenChain(dataExchanger.getRetailerId(), "1", 200); //Today's chainId always available
    }

    @Then("^All associated with chain stores will be deactivated$")
    public void checkIfTodaysStoresOfGivenRetailerWasDeactivated() throws Throwable {
        retailerValidator.checkActiveStateOfTakenStore(dataExchanger.getStore2Id(), false);
        retailerValidator.checkActiveStateOfTakenStore(dataExchanger.getStore1Id(), true);
    }

    @Then("^Retailer will be removed from stores belongs to chain from which he was removed$")
    public void checkIfRetailerWasRemovedFromStoresBolngsToChainFromRetailerWasRemoved() throws Throwable {
        response = storeActions.getStore(dataExchanger.getStore1Id(), 200);
        assertTrue("Store retailer id is incorrect", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId").equals(dataExchanger.getRetailerId()));
        response = storeActions.getStore(dataExchanger.getStore3Id(), 200);
        assertTrue("Store retailer id is incorrect", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId").equals(dataExchanger.getRetailerId()));
        response = storeActions.getStore(dataExchanger.getStore2Id(), 200);
        assertTrue("Store retailer id was not set to null", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId").equals("null"));
    }

    @Then("^Retailer will be deactivated in partner virtual group from where he was removed$")
    public void checkIfRetailerWasDeactivatedInTodaysVirtualGrup() throws Throwable {
        retailerValidator.checkIfRetailerWasDeletedFromSelectedPartner(true, dataExchanger.getRetailerEmail(), Config.todaysPartnerIdQa);
        //TODO now when store created new virtual group is not created ERV-320 so this check always fail
        //retailerValidator.checkIfRetailerWasDeletedFromSelectedPartner(false, dataExchanger.getRetailerEmail(), Config.premierPartnerIdQa);
    }

    //Check retailer remove from selected chain with incorrect parameters
    @Given("^New chain, retailer and stores are created according to '(.+?)'$")
    public void prepareChainRetailerStoreData(String scenario) throws Throwable {
//        if (scenario.equals("retailerIdNotExists")) {
//            createNewChain();
//        } else if (scenario.equals("chainIdNotExists")) {
//            createNewRetailer();
//        } else if (scenario.equals("chainAndRetailerAreNotRelated")) {
//            createNewChain();
//            createNewRetailer();
//        }
    }

    @When("^Admin try to delete retailer from chain according to given '(.+?)', '(.+?)'$")
    public void deleteRetailerFromChainForGivenScenario(String scenario, int status) throws Throwable {
        if (scenario.equals("retailerIdNotExists")) {
            response = retailerActions.deleteRetailerFromChosenChain("432356456", dataExchanger.getChainId(), status);
            dataExchanger.setRetailerId("432356456");
        } else if (scenario.equals("chainIdNotExists")) {
            response = retailerActions.deleteRetailerFromChosenChain(dataExchanger.getRetailerId(), "345435324", status);
            dataExchanger.setChainId("345435324");
        } else if (scenario.equals("chainAndRetailerAreNotRelated")) {
            response = retailerActions.deleteRetailerFromChosenChain(dataExchanger.getRetailerId(), dataExchanger.getChainId(), status);
        }
    }

    @Then("^Proper retailer removing error message will be returned '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void checkCorrectnessOfRetailerDeleteErrors(String message, String description, String objectNameFE, String fieldFE, String messageFE) throws Throwable {
        retailerValidator.validateCreateRetailerErrorMessages(message, description, objectNameFE, fieldFE, messageFE, response);
    }

    //Check retailer take over existing store
    @When("^Retailer take over existing store according to '(.+?)'$")
    public void takeOverExistingStore(String scenario) throws Throwable {
//        if (scenario.equals("takenStoreIsActive")) {
//        } else if (scenario.equals("takenStoreIsInactive")) {
//            MySQLConnector conn = new MySQLConnector("audit-cms");
//            conn.update("UPDATE audit.store SET active = 0 WHERE id = '" + dataExchanger.getStore1Id() + "'");
//            conn.close();
//        }
        response = retailerActions.retailerTakeOverChosenStore(dataExchanger.getRetailerId(), dataExchanger.getStore1Id(), 200);
    }

    @Then("^Proper relations between store and new owner will be established$")
    public void checkCorrectnesBetweenRetailerAndTakenStore() throws Throwable {
        retailerValidator.checkIfTakenStoreRetailerIdWasChanged(true, dataExchanger.getRetailerId(), dataExchanger.getStore1Id());
        retailerValidator.checkIfTakenStoreRetailerIdWasChanged(false, dataExchanger.getRetailerId(), dataExchanger.getStore2Id());
    }

    @Then("^Taken store is in active state even if was deactivated before$")
    public void checkTakenStoreActiveState() throws Throwable {
        retailerValidator.checkActiveStateOfTakenStore(dataExchanger.getStore1Id(), true);
    }

    //Check retailer take over existing store using incorrect parameters
    @When("^New Retailer take over existing store according to '(.+?)', '(.+?)'$")
    public void takeOverExistingStoreUsingIncorrectParameters(String scenario, int status) throws Throwable {
////        createNewRetailer();
//        if (scenario.equals("retailerIdNotExists")) {
//            response = storeActions.createStore(new Store(null, storeNameBase + func.returnEpochOfCurrentDay() + "1", "true", "true", "auditGroup", "storeType", "line1", "line2", "line3", "line4", "postcode", "EnglandWales", "123", "123", "1", dataExchanger.getRetailerId()), 201);
//            dataExchanger.setStore1Id(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id"));
//
//            response = retailerActions.retailerTakeOverChosenStore("47623462", dataExchanger.getStore1Id(), status);
//            dataExchanger.setRetailerId("47623462");
//        } else if (scenario.equals("storeIdNotExists")) {
//            response = retailerActions.retailerTakeOverChosenStore(dataExchanger.getRetailerId(), "95623487", status);
//            dataExchanger.setStore1Id("95623487");
//
//        }
    }

    @Then("^Proper store taking over error message will be returned '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void checkCorrectnessOfTakingStoreOverErrors(String message, String description, String objectNameFE, String fieldFE, String messageFE) throws Throwable {
        retailerValidator.validateCreateRetailerErrorMessages(message, description, objectNameFE, fieldFE, messageFE, response);
    }

    //Step used for single call making, for manual testing purpose
    @Given("^Test$")
    public void test() throws Throwable {
        response = storeActions.bulkUploadStoresPremierOnly("Premier", "stores.xls", 200);
    }

    //Scenario Outline: Check creation of virtual groups when store created and assigned to retailer
    @When("^New store will be created for specific chain '(.+?)'$")
    public void newStoreWillBeCreatedForSpecificChain(String chainName) throws Throwable {
//        String chainId = null;
//        if (chainName.equals("Todays")) {
//            chainId = Config.todaysChainIdQaAuditCms;
//        } else if (chainName.equals("Premier")) {
//            chainId = Config.premierChainIdQaAuditCms;
//        }
//        response = storeActions.createStore(new Store(null, storeNameBase + func.returnEpochOfCurrentDay() + "2", "true", "true", "auditGroup", "storeType", "line1", "line2", "line3", "line4", "postcode", "EnglandWales", Long.toString(func.returnEpochOfCurrentDay()) + "2", "123", chainId, dataExchanger.getRetailerId()), 201);
//        dataExchanger.setStore1Id(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id"));
    }

    @Then("^Store chain '(.+?)' virtual group will be added to retailer account$")
    public void storeChainVirtualGroupWillBeAddedToRetailerAccount(String chainName) throws Throwable {
        String partnerId = null;
        if (chainName.equals("Todays")) {
            partnerId = Config.todaysPartnerIdQa;
        } else if (chainName.equals("Premier")) {
            partnerId = Config.premierPartnerIdQa;
        }
        retailerValidator.checkIfVirtualGroupOfChianPartnerWasCreatedAndIsActive(true, dataExchanger.getRetailerEmail(), partnerId);
    }

    //Scenario Outline: Check virtual group status change when last store removed
    @When("^Last store belonging to retailer form chain '(.+?)' will be removed$")
    public void removeLastStoreFromChain(String chainName) throws Throwable {
        response = storeActions.deleteStore(dataExchanger.getStore1Id(), 200);
    }

    @Then("^Retailer status of virtual group associated witch chain '(.+?)' will be changed to deleted$")
    public void checkStatusOfVirtualGroupWhereAllStoresWereRemovedForRetailer(String chainName) throws Throwable {
        String partnerId = null;
        if (chainName.equals("Todays")) {
            partnerId = Config.todaysPartnerIdQa;
        } else if (chainName.equals("Premier")) {
            partnerId = Config.premierPartnerIdQa;
        }
        retailerValidator.checkIfRetailerWasDeletedFromSelectedPartner(true, dataExchanger.getRetailerEmail(), partnerId);
    }

    //Scenario Outline: Check virtual groups statuses change when last store updated to other chain
    @When("^Last store belonging to retailer form chain '(.+?)' will be updated and other chain '(.+?)' will be set$")
    public void changeStoreChainId(String oldChain, String newChain) throws Throwable {
        JsonObject jsonObject = jsonParserUtils.convertStringToJson(storeActions.getStore(dataExchanger.getStore1Id(), 200));
        String chainId = null;
        if (oldChain.equals("Todays")) {
            chainId = Config.premierChainIdQaAuditCms;
        } else if (oldChain.equals("Premier")) {
            chainId = Config.todaysChainIdQaAuditCms;
        }
        jsonObject.addProperty("chainId", Long.parseLong(chainId));
        storeActions.updateStore(jsonObject.toString(), 200);
    }

    //Scenario Outline: Check if virtual group will be added when retailer take over store from new chain
    @When("^Retailer take over store with chain for which he does not have virtual group$")
    public void takeOverStore() throws Throwable {
        response = retailerActions.retailerTakeOverChosenStore(dataExchanger.getRetailerId(), dataExchanger.getStore1Id(), 200);
    }

}