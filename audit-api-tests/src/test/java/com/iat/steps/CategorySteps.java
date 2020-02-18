package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.CategoryActions;
import com.iat.domain.Category;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CategorySteps {

    private CategoryActions categoryActions = new CategoryActions();
    private ContractValidator contractValidator = new ContractValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response, lastCreatedCategoryId;
    ObjectMapper mapper = new ObjectMapper();


    @When("^Get categories call is made$")
    public void getCategoryList() throws Throwable {
        response = categoryActions.getCategoriesList(200);
    }

    @Then("^Get categories call returns proper contract$")
    public void validateCategoryListContract() throws Throwable {
        //TODO contract validation
    }

    @When("^Create category call is made with jsonData '(.+?)', code '(.+?)'$")
    public void createCategory(String jsonBody, int code) throws Throwable {
        if (jsonBody.equals("DEFAULT"))
            jsonBody = "{\"categoryName\":\"API_AUDIT_CMS_CATEGORY_\",\"label\":\"API_AUDIT_CMS_CATEGORY_LABEL_\",\"id\":null}";


        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Category category = mapper.readValue(jsonBody, Category.class);

        response = categoryActions.createCategory(category.toString(), code);
        String id = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        dataExchanger.addCategoryToCategoriesList(category);
        dataExchanger.getCategories().get(dataExchanger.getCategories().size() - 1).setId(id);

        lastCreatedCategoryId = id;
    }

    @Then("^Create category call returns proper data$")
    public void validateCreateCategoryRequestResponse() throws Throwable {

        Category categoryToValidate = dataExchanger.getCategories().get(dataExchanger.getCategories().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseCategoryName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "categoryName");
        String responseLabel = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "label");
        String responseParent = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "parent");

        assertThat("field id is incorrect", responseId, is(categoryToValidate.getId()));
        assertThat("field categoryName is incorrect", responseCategoryName, is(categoryToValidate.getCategoryName()));
        assertThat("field label is incorrect", responseLabel, is(categoryToValidate.getLabel()));
        assertThat("field parent is incorrect", responseParent, is(String.valueOf(categoryToValidate.getParent())));

    }

    @Then("^Create category call returns proper error message '(.+?)', description '(.+?)', fieldErrors '(.+?)'$")
    public void validateCreateCategoryErrorMessage(String message, String description, String fieldErrors) throws Throwable {
        String responseMessage = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
        String responseDescription = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
        String responseFieldErrors = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "fieldErrors");

        assertThat("field message: " + responseMessage + " is incorrect, should be: " + message, responseMessage, is(message));
        assertThat("field description: " + responseDescription + " is incorrect, should be: " + description, responseDescription, is(description));
        assertThat("field fieldErrors: " + responseFieldErrors + " is incorrect, should be: " + fieldErrors, responseFieldErrors, is(fieldErrors));
    }


    @Then("^Get category by id '(.+?)', code '(.+?)'$")
    public void getCategoryById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedCategoryId;

        response = categoryActions.getCategoryById(id, 200);
    }

    @Then("^Get category by id returns proper data$")
    public void validateGetCategoryById() throws Throwable {
        Category categoryToValidate = dataExchanger.getCategories().get(dataExchanger.getCategories().size() - 1);

        String responseId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "id");
        String responseCategoryName = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "categoryName");
        String responseLabel = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "label");
        String responseParent = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "parent");

        assertThat("field id is incorrect", responseId, is(categoryToValidate.getId()));
        assertThat("field categoryName is incorrect", responseCategoryName, is(categoryToValidate.getCategoryName()));
        assertThat("field label is incorrect", responseLabel, is(categoryToValidate.getLabel()));
        assertThat("field parent is incorrect", responseParent, is(String.valueOf(categoryToValidate.getParent())));
    }

    @Then("^Delete category by id '(.+?)', code '(.+?)'$")
    public void deleteCategoryById(String id, int code) throws Throwable {
        if (id.equals("previous_call"))
            id = lastCreatedCategoryId;

        response = categoryActions.deleteCategoryById(id, code);
    }


}