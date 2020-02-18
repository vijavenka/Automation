package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.*;
import com.iat.domain.Retailer;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.AuditResultsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonObject;
import org.json.JSONObject;

import static org.junit.Assert.assertTrue;

public class AuditResultSteps {

    private StoreActions storeActions = new StoreActions();
    private RetailerActions retailerActions = new RetailerActions();
    private AuditsActions auditsActions = new AuditsActions();
    private AuditResultActions auditResultActions = new AuditResultActions();
    private AuditCriteriaActions auditCriteriaActions = new AuditCriteriaActions();
    private AuditResultsValidator auditResultsValidator = new AuditResultsValidator();
    private final UserRepository userRepository = new UserRepositoryImpl();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    ObjectMapper mapper = new ObjectMapper();
    private HelpFunctions helpFunctions = new HelpFunctions();


    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response;

    @When("^Audit result call is made for data '(.+?)', '(.+?)'$")
    public void auditResultRequest(String fileName, String partnerShortName) throws Throwable {
        if (partnerShortName.toLowerCase().contains("todays"))
            auditResultActions.updateTodaysAuditResultsFileUniqueQuestionIds(fileName);
        else
            auditResultActions.updatePremierAuditResultsFileUniqueQuestionIds(fileName);

        auditResultActions.auditResult(helpFunctions.getChainIdForPartnerShortName(partnerShortName), dataExchanger.getAuditObject().getId(), fileName, 202);
        auditResultActions.waitAfterAuditProcessing();
    }


    @Given("^Store retailer's epoints balance is known before process audit results$")
    public void retailersEpointsBalanceBefore() throws Throwable {

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        dataExchanger.clearRetailersList();

        for (String email : dataExchanger.getGeneratedRetailerMail().split(",")) {
            //save retailer to list
            String retailerId = retailerActions.getRetailerIdForRetailerEmail(email.toLowerCase());
            response = retailerActions.getRetailerById(retailerId, 200);
            Retailer retailer = mapper.readValue(response, Retailer.class);

            //save retailer balance
            String epointsConfirmed = new UserRepositoryImpl().findUserByUserId(retailer.getUuid()).getConfirmed();
            retailer.setBalance(epointsConfirmed);
            dataExchanger.addRetailerToRetailersList(retailer);
        }
    }


    @Then("^Retailer's epoints balance updated after process audit results$")
    public void retailerEpointsBalanceAfter() throws Throwable {
        auditResultsValidator.validateAuditResultsProperlyAwardedRetailer(false);
    }

    @Then("^Retailer's epoints balance is proper after importRetailers when there was some rewardPoints$")
    public void retailerEpointsBalanceAfterImaportRetailers() throws Throwable {
        auditResultsValidator.validateAuditResultsProperlyAwardedRetailer(true);
    }

    @Given("^Retailer awarded in points-manager with proper details$")
    public void validateIfRetailerAwardsProperlyInPointsManager() throws Throwable {
        auditResultsValidator.validatePointsRecordInPointsManager();
    }

    @Given("^Store clear retailerId$")
    public void store_clear_retailerId() throws Throwable {
        Thread.sleep(2000);
        response = storeActions.getStore(dataExchanger.getStore1Id(), 200);
        JSONObject jsonObject = new JSONObject(response);
        jsonObject.put("retailerId", JSONObject.NULL);
        storeActions.updateStore(jsonObject.toString(), 200);
    }

    @Given("^Retailer's '(.+?)' epoints balance is known before process audit results$")
    public void getRetailerEmailBalance(String retailerEmail) throws Throwable {
//        dataExchanger.setRetailerId(retailerActions.getRetailerIdForRetailerEmail(retailerEmail));
//        dataExchanger.setUserUuid(jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(retailerActions.getRetailerById(dataExchanger.getRetailerId(), 200)), "epointsUuid"));
//        dataExchanger.setUserBalance(userRepository.findUserByUserId(dataExchanger.getUserUuid()).getConfirmed());
    }

    @When("^Store retailer is updated according to '(.+?)' with params '(.+?)'$")
    public void storeRetailerIsUpdatedAccordingToScenario(String scenario, String fileName) throws Throwable {
        if (scenario.equals("simpleUpdate")) {
            response = storeActions.getStore(dataExchanger.getStore1Id(), 200);
            JsonObject jsonObject = jsonParserUtils.convertStringToJson(response);
            jsonObject.addProperty("retailerId", dataExchanger.getRetailerId());
            storeActions.updateStore(jsonObject.toString(), 200);

            response = storeActions.getStore(dataExchanger.getStore1Id(), 200);
            String retailerId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId");
            assertTrue("Store: " + dataExchanger.getStore1Id() + " retailerId was not set properly: " + retailerId, retailerId.equals(dataExchanger.getRetailerId()));
        } else if (scenario.equals("bulkUpload")) {
            if (fileName.equals("audit_results_no_retailer_stores_bulk.xlsx"))
                storeActions.bulkUploadStoresAndRetailers("TodaysRetailers", fileName, 200);
            else
                storeActions.bulkUploadStoresAndRetailers("PremierRetailers", fileName, 200);

            response = storeActions.getStore(dataExchanger.getStore1Id(), 200);
            String retailerId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "retailerId");
            assertTrue("Store: " + dataExchanger.getStore1Id() + " retailerId was not set properly: " + retailerId, retailerId.equals(dataExchanger.getRetailerId()));
        } else if (scenario.equals("takeOverStore")) {
            response = retailerActions.retailerTakeOverChosenStore(dataExchanger.getRetailerId(), dataExchanger.getStore1Id(), 200);
        }
    }
}