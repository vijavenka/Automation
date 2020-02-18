package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.ProductActions;
import com.iat.domain.Product;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProductSteps {

    private ProductActions productActions = new ProductActions();
    private ContractValidator contractValidator = new ContractValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response, lastCreatedProductId;
    ObjectMapper mapper = new ObjectMapper();


    @When("^Get product call is made$")
    public void getProductList() throws Throwable {
        response = productActions.getProductsList(200);
    }

    @Then("^Get product call returns proper contract$")
    public void validateProductListContract() throws Throwable {
        //TODO contract validation
    }

    @When("^Create product call is made with jsonData '(.+?)', code '(.+?)'$")
    public void createProduct(String jsonBody, int code) throws Throwable {
        if (jsonBody.equals("DEFAULT"))
            jsonBody = "{ \"id\": null, \"productName\": \"API_AUDIT_CMS_PRODUCT_" + dataExchanger.getProducts().size() + "_\", \"url\": \"\", \"description\": \"\", \"categoryId\": \"previous_call\", \"brandId\": \"previous_call\" }";


        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Product product = mapper.readValue(jsonBody, Product.class);

        response = productActions.createProduct(product.toString(), code);
        String id = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        dataExchanger.addProductToProductsList(product);
        dataExchanger.getProducts().get(dataExchanger.getProducts().size() - 1).setId(id);

        lastCreatedProductId = id;

        Thread.sleep(2000);
    }

    @Then("^Create product call returns proper data$")
    public void validateCreateProductRequestResponse() throws Throwable {

        Product productToValidate = dataExchanger.getProducts().get(dataExchanger.getProducts().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseProductName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "productName");
        String responseUrl = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "url");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
        String responseCategoryId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "categoryId");
        String responseBrandId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "brandId");

        assertThat("field id is incorrect", responseId, is(productToValidate.getId()));
        assertThat("field productName is incorrect", responseProductName, is(productToValidate.getProductName()));
        assertThat("field url is incorrect", responseUrl, is(String.valueOf(productToValidate.getUrl())));
        assertThat("field description is incorrect", responseDescription, is(String.valueOf(productToValidate.getDescription())));
        assertThat("field categoryId is incorrect", responseCategoryId, is(String.valueOf(productToValidate.getCategoryId())));
        assertThat("field brandId is incorrect", responseBrandId, is(String.valueOf(productToValidate.getBrandId())));

    }

    @Then("^Create product call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateProductErrorMessage(String message, String description, String fieldErrors) throws Throwable {
        String responseMessage = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
        String responseFieldErrors = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "fieldErrors");

        assertThat("field message: " + responseMessage + " is incorrect, should be: " + message, responseMessage, is(message));
        assertThat("field description: " + responseDescription + " is incorrect, should be: " + description, responseDescription, is(description));
        assertThat("field fieldErrors: " + responseFieldErrors + " is incorrect, should be: " + fieldErrors, responseFieldErrors, is(fieldErrors));
    }


    @Then("^Get product by id '(.+?)', code '(.+?)'$")
    public void getProductById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedProductId;

        response = productActions.getProductById(id, 200);
    }

    @Then("^Get product by id returns proper data$")
    public void validateGetProductById() throws Throwable {
        Product productToValidate = dataExchanger.getProducts().get(dataExchanger.getProducts().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseProductName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "productName");
        String responseUrl = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "url");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
        String responseCategoryId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "categoryId");
        String responseBrandId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "brandId");

        assertThat("field id is incorrect", responseId, is(productToValidate.getId()));
        assertThat("field productName is incorrect", responseProductName, is(productToValidate.getProductName()));
        assertThat("field url is incorrect", responseUrl, is(String.valueOf(productToValidate.getUrl())));
        assertThat("field description is incorrect", responseDescription, is(String.valueOf(productToValidate.getDescription())));
        assertThat("field categoryId is incorrect", responseCategoryId, is(String.valueOf(productToValidate.getCategoryId())));
        assertThat("field brandId is incorrect", responseBrandId, is(String.valueOf(productToValidate.getBrandId())));

    }

    @Then("^Delete product by id '(.+?)', code '(.+?)'$")
    public void deleteProductById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedProductId;

        response = productActions.deleteProductById(id, code);
    }


}