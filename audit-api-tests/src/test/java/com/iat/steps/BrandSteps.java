package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.BrandActions;
import com.iat.domain.Brand;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BrandSteps {

    private BrandActions brandActions = new BrandActions();
    private ContractValidator contractValidator = new ContractValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response, lastCreatedBrandId;
    ObjectMapper mapper = new ObjectMapper();


    @When("^Get brand call is made$")
    public void getBrandList() throws Throwable {
        response = brandActions.getBrandsList(200);
    }

    @Then("^Get brand call returns proper contract$")
    public void validateBrandListContract() throws Throwable {
        //TODO contract validation
    }

    @When("^Create brand call is made with jsonData '(.+?)', code '(.+?)'$")
    public void createBrand(String jsonBody, int code) throws Throwable {
        if (jsonBody.equals("DEFAULT"))
            jsonBody = "{\"brandName\":\"API_AUDIT_CMS_BRAND_" + dataExchanger.getBrands().size() + "_\",\"supplierId\":\"previous_call\",\"id\":null}";


        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Brand brand = mapper.readValue(jsonBody, Brand.class);
        dataExchanger.addBrandToBrandsList(brand);

        response = brandActions.createBrand(brand.toString(), code);
        String id = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");

        dataExchanger.getBrands().get(dataExchanger.getBrands().size() - 1).setId(id);
        lastCreatedBrandId = id;
    }

    @Then("^Create brand call returns proper data$")
    public void validateCreateBrandRequestResponse() throws Throwable {

        Brand brandToValidate = dataExchanger.getBrands().get(dataExchanger.getBrands().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseBrandName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "brandName");
        String responseSupplierId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "supplierId");

        assertThat("field id is incorrect", responseId, is(brandToValidate.getId()));
        assertThat("field brandName is incorrect", responseBrandName, is(brandToValidate.getBrandName()));
        assertThat("field supplierId is incorrect", responseSupplierId, is(String.valueOf(brandToValidate.getSupplierId())));

    }

    @Then("^Create brand call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateBrandErrorMessage(String message, String description, String fieldErrors) throws Throwable {
        String responseMessage = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
        String responseFieldErrors = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "fieldErrors");

        assertThat("field message: " + responseMessage + " is incorrect, should be: " + message, responseMessage, is(message));
        assertThat("field description: " + responseDescription + " is incorrect, should be: " + description, responseDescription, is(description));
        assertThat("field fieldErrors: " + responseFieldErrors + " is incorrect, should be: " + fieldErrors, responseFieldErrors, is(fieldErrors));
    }


    @Then("^Get brand by id '(.+?)', code '(.+?)'$")
    public void getBrandById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedBrandId;

        response = brandActions.getBrandById(id, 200);
    }

    @Then("^Get brand by id returns proper data$")
    public void validateGetBrandById() throws Throwable {
        Brand brandToValidate = dataExchanger.getBrands().get(dataExchanger.getBrands().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseBrandName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "brandName");
        String responseSupplierId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "supplierId");

        assertThat("field id is incorrect", responseId, is(brandToValidate.getId()));
        assertThat("field brandName is incorrect", responseBrandName, is(brandToValidate.getBrandName()));
        assertThat("field supplierId is incorrect", responseSupplierId, is(String.valueOf(brandToValidate.getSupplierId())));
    }


    @Then("^Delete brand by id '(.+?)', code '(.+?)'$")
    public void deleteBrandById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedBrandId;

        response = brandActions.deleteBrandById(id, code);
    }


}