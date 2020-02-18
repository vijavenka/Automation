package com.iat.steps.ecardsManagement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.EcardsConfigActions;
import com.iat.actions.ecardsManagement.EcardsReasonsActions;
import com.iat.domain.EcardsConfig.Reason;
import com.iat.steps.GenericSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ecardsManagement.EcardsReasonsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcardsReasonsSteps {

    private EcardsReasonsActions ecardsReasonsActions = new EcardsReasonsActions();
    private EcardsConfigActions ecardsConfigActions = new EcardsConfigActions();
    private EcardsReasonsValidator ecardsReasonsValidator = new EcardsReasonsValidator();
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseContainer response;
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    //Scenario Outline: Reasons creation - get list of reasons
    @When("^He make a call to get list of reasons$")
    public void getEcardsReasonsList() throws Throwable {
        response = ecardsReasonsActions.getEcardsReasonsList(200);
    }

    @Then("^Response with list of reasons in agreed format$")
    public void getEcardsReasonsListContractValidation() throws Throwable {
        //done at controller level
    }

    @Then("Reasons are in descending order$")
    public void getEcardsReasonsListSortOrderValidation() throws Throwable {
        ecardsReasonsValidator.validateEcardsReasonsListOrder(response);
    }


    //Scenario Outline: Reasons creation - create and get details of new reason
    @When("^New reason is created with following jsonBody '(.+?)'$")
    public void createNewReasonJson(String jsonBody) throws Throwable {
        //Set global Reason
        ecardsConfigActions.setDefaultGlobalReasonLimits();

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Reason reason = mapper.readValue(jsonBody, Reason.class);
        dataExchanger.setReasonObject(reason);
        response = ecardsReasonsActions.createNewEcardsReason(reason.toJsonRequest(), 201);
        dataExchanger.getReasonObject().setId(response.toString());
    }

    @When("^Reason Id is returned after create reason request$")
    public void createNewReasonReturnsId() throws Throwable {
        assertThat("Create new ecards reason response is without Id!!", response.toString(), not(isEmptyOrNullString()));
    }


    @Then("^Get Ecards reason by Id '(.+?)' call is made$")
    public void getEcardsReasonById(String id) throws Throwable {
        if (id.equals("previousCall")) {
            id = dataExchanger.getReasonObject().getId();
        }
        response = ecardsReasonsActions.getEcardsReasonById(id, 200);
    }

    @Then("^Get Ecards reason by Id call returns proper data$")
    public void getEcardsReasonsByIdIsHaveProperData() throws Throwable {
        ecardsReasonsValidator.validateGetReasonByIdData(response);
    }

    @Then("^Proper tag was created in Points-Manager$")
    public void proper_tag_was_created_in_Points_Manager() throws Throwable {
        String results = ecardsReasonsValidator.validateGetReasonByIdProperTagCreated();
        assertThat("Tag was not created in points manager: " + results, not(isEmptyOrNullString()));
    }

    @Then("^Delete Ecards reason by Id '(.+?)' call is made$")
    public void deleteEcardsReasonById(String id) throws Throwable {
        if (id.equals("previousCall")) {
            id = dataExchanger.getReasonObject().getId();
        }
        response = ecardsReasonsActions.deleteEcardsReasonById(id, 200);
        assertThat("Reason: " + dataExchanger.getReasonObject().getId() + " not deleted ", ecardsReasonsActions.isEcardsReasonDeleted(dataExchanger.getReasonObject().getId()));
    }

    //Create new reason Error message validation
    @When("^Create new reason for incorrect data '(.+?)', '(.+?)', '(.+?)'$")
    public void createNewReasonErrorMessage(String jsonBody, int status, String error) throws Throwable {
        if (!error.equals("Unauthorized") && !error.equals("Forbidden")) {
            //Set global Reason
            ecardsConfigActions.setDefaultGlobalReasonLimits();
        }

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Reason reason = mapper.readValue(jsonBody, Reason.class);
        dataExchanger.setReasonObject(reason);
        response = ecardsReasonsActions.createNewEcardsReason(reason.toJsonRequest(), status);
        dataExchanger.setResponse(response);

    }

    @Then("^Create new reason for incorrect data return error message '(.+?)', '(.+?)', '(.+?)'$")
    public void createNewReasonErrorMessageValidation(int status, String error, String message) throws Throwable {
        if (message.contains("Min") && message.contains("greater"))
            message = String.format(message, dataExchanger.getGlobalReasonLimits().getUserToUserMin(), dataExchanger.getGlobalReasonLimits().getManagerToUserMin());
        if (message.contains("Max") && message.contains("lower"))
            message = String.format(message, dataExchanger.getGlobalReasonLimits().getUserToUserMax(), dataExchanger.getGlobalReasonLimits().getManagerToUserMax());

        new GenericSteps().getGenericErrorMessageValidation("Create new reason", status, error, message);
    }

    //Get reason by id Error message validation
    @When("^Get reason by id for incorrect data '(.+?)', '(.+?)'$")
    public void getEcardsReasonByIdErrorMessage(String id, int status) throws Throwable {
        if (id.equals("deleted"))
            id = ecardsReasonsActions.getRandomDeletedResonId(Config.getTestPartnerId());

        if (id.equals("random"))
            id = ecardsReasonsActions.getRandomActiveResonId(Config.getTestPartnerId());

        response = ecardsReasonsActions.getEcardsReasonById(id, status);
        dataExchanger.setResponse(response);
    }

    //Delete reason by id Error message validation
    @When("^Delete reason by id for incorrect data '(.+?)', '(.+?)'$")
    public void deleteEcardsReasonByIdErrorMessage(String id, int status) throws Throwable {
        if (id.equals("deleted")) {
            id = ecardsReasonsActions.getRandomDeletedResonId(Config.getTestPartnerId());
        }
        response = ecardsReasonsActions.deleteEcardsReasonById(id, status);
        dataExchanger.setResponse(response);
    }

    @Then("^(.+?) for incorrect data (.+?) return error message '(.+?)', '(.+?)', '(.+?)'$")
    public void deleteEcardsReasonByIdErrorMessageValidation(String method, String id, int status, String error, String message) throws Throwable {
        if (id.equals("deleted"))
            id = ecardsReasonsActions.getRandomDeletedResonId(Config.getTestPartnerId());

        new GenericSteps().getGenericErrorMessageValidation(method, status, error, String.format(message, id));
    }

    @When("^Delete all reasons$")
    public void deleteAllReasons() throws Throwable {
        List<Long> ids = response.getList("id");
        if (ids.size() > 1) {
            for (int i = 0; i < ids.size() - 1; i++)
                ecardsReasonsActions.deleteEcardsReasonById(ids.get(i).toString(), 200);
        }
        response = ecardsReasonsActions.deleteEcardsReasonById(ids.get(ids.size() - 1).toString(), 400);
        dataExchanger.setResponse(response);
    }

    //Create new reason, and change global ranges - reason should be updated according global changes
    @Given("^Global reason limits are known$")
    public void global_reason_limits_are_known() throws Throwable {
        //Set global Reason
        ecardsConfigActions.updateGlobalReasonLimits("{\"managerToUserMin\": 1, \"managerToUserMax\": 10000, \"userToUserMin\": 1, \"userToUserMax\": 20000}");
    }

    @Given("^Create new reason which is valid with global limitation '(.+?)'$")
    public void create_new_reason_which_is_valid_with_global_limitation(String jsonBody) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Reason reason = mapper.readValue(jsonBody, Reason.class);
        dataExchanger.setReasonObject(reason);
        response = ecardsReasonsActions.createNewEcardsReason(reason.toJsonRequest(), 201);
        dataExchanger.getReasonObject().setId(response.toString());
        assertThat("Create new ecards reason response is without Id!!", response.toString(), not(isEmptyOrNullString()));
    }

    @When("^Update global reason limits$")
    public void update_global_reason_limits() throws Throwable {
        ecardsConfigActions.updateGlobalReasonLimits("{\"managerToUserMin\": 4, \"managerToUserMax\": 5000, \"userToUserMin\": 5, \"userToUserMax\": 10000}");
    }

    @Then("^Reason data was updated according global limits change$")
    public void reason_data_was_updated_according_global_limits_change() throws Throwable {
        String extractedManagerToUserReasonRange = response.getString("managerToUserReasonRange");
        String extractedUserToUserReasonRange = response.getString("userToUserReasonRange");

        Reason createdReason = dataExchanger.getReasonObject();
        Reason globalLimits = dataExchanger.getGlobalReasonLimits();

        //manager to user
        if (createdReason.getManagerToUserMin() >= globalLimits.getManagerToUserMin() && createdReason.getManagerToUserMax() <= globalLimits.getManagerToUserMax()) {
            assertThat("Respone managerToUserReasonRange value is invalid!", extractedManagerToUserReasonRange, is("DEFINE"));
            System.out.println("MtU - DEFINE");
        } else {
            assertThat("Respone managerToUserReasonRange value is invalid!", extractedManagerToUserReasonRange, is("GLOBAL"));
            System.out.println("MtU - GLOBAL");
        }

        //user to user
        if (createdReason.getUserToUserMin() >= globalLimits.getUserToUserMin() && createdReason.getUserToUserMax() <= globalLimits.getUserToUserMax()) {
            assertThat("Respone managerToUserReasonRange value is invalid!", extractedUserToUserReasonRange, is("DEFINE"));
            System.out.println("UtU - DEFINE");
        } else {
            assertThat("Respone managerToUserReasonRange value is invalid!", extractedUserToUserReasonRange, is("GLOBAL"));
            System.out.println("UtU - GLOBAL");
        }
    }
}



