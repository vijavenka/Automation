package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.SuppliersActions;
import com.iat.domain.Supplier;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SuppliersSteps {

    private SuppliersActions suppliersActions = new SuppliersActions();
    private ContractValidator contractValidator = new ContractValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response, lastCreatedSupplierId;
    ObjectMapper mapper = new ObjectMapper();


    @When("^Get suppliers call is made$")
    public void getSuppliersList() throws Throwable {
        response = suppliersActions.getSuppliersList(200);
    }

    @Then("^Get suppliers call returns proper contract$")
    public void validateSuppliersListContract() throws Throwable {
        //TODO contract validation
    }

    @When("^Create supplier call is made with jsonData '(.+?)', code '(.+?)'$")
    public void createSupplier(String jsonBody, int code) throws Throwable {
        if (jsonBody.equals("DEFAULT"))
            jsonBody = "{\"supplierName\":\"API_AUDIT_CMS_SUPPLIER_" + dataExchanger.getSuppliers().size() + "_\",\"partnerApiKey\":null,\"id\":null}";
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Supplier supplier = mapper.readValue(jsonBody, Supplier.class);

        response = suppliersActions.createSupplier(supplier.toString(), code);
        String id = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        dataExchanger.addSupplierToSuppliersList(supplier);
        dataExchanger.getSuppliers().get(dataExchanger.getSuppliers().size() - 1).setId(id);

        lastCreatedSupplierId = id;
    }

    @Then("^Create supplier call returns proper data$")
    public void validateCreateSupplierRequestResponse() throws Throwable {
        Supplier supplierToValidate = dataExchanger.getSuppliers().get(dataExchanger.getSuppliers().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseSupplierName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "supplierName");

        assertThat("field id is incorrect", responseId, is(supplierToValidate.getId()));
        assertThat("field supplierName is incorrect", responseSupplierName, is(supplierToValidate.getSupplierName()));
    }

    @Then("^Create supplier call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateSupplierErrorMessage(String message, String description, String fieldErrors) throws Throwable {
        String responseMessage = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
        String responseFieldErrors = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "fieldErrors");

        assertThat("field message: " + responseMessage + " is incorrect, should be: " + message, responseMessage, is(message));
        assertThat("field description: " + responseDescription + " is incorrect, should be: " + description, responseDescription, is(description));
        assertThat("field fieldErrors: " + responseFieldErrors + " is incorrect, should be: " + fieldErrors, responseFieldErrors, is(fieldErrors));
    }


    @Then("^Get supplier by id '(.+?)', code '(.+?)'$")
    public void getSupplierById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedSupplierId;

        response = suppliersActions.getSupplierById(id, 200);
    }

    @Then("^Get supplier by id returns proper data$")
    public void validateGetSupplierById() throws Throwable {
        Supplier supplierToValidate = dataExchanger.getSuppliers().get(dataExchanger.getSuppliers().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseSupplierName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "supplierName");

        assertThat("field id is incorrect", responseId, is(supplierToValidate.getId()));
        assertThat("field supplierName is incorrect", responseSupplierName, is(supplierToValidate.getSupplierName()));
        //TODO supplier partnerApiKey is not returned
    }

    @Then("^Delete supplier by id '(.+?)', code '(.+?)'$")
    public void deleteSupplierById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedSupplierId;

        response = suppliersActions.deleteSupplierById(id, code);
    }


}