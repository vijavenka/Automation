package com.iat.steps.ecardsManagement.ECardsTemplates;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.ecardsManagement.ECardsTemplates.ECardsTemplatesActions;
import com.iat.domain.EcardsConfig.Template;
import com.iat.steps.GenericSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ECardsTemplatesSteps {

    private ECardsTemplatesActions cardsTemplatesActions = new ECardsTemplatesActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseContainer response;
    private Template template;
    private int random;

    //Scenario Outline: Templates - get all existing templates data
    @When("^Admin lists all existing in system templates$")
    public void listExistingTemplates() throws Throwable {
        response = cardsTemplatesActions.listAllTemplates(200);
        dataExchanger.setTemplatesListObject(response.getList("customEcardTemplates", Template.class));
    }

    @Then("^All templates should be properly returned$")
    public void checkIfAllTemplatesWereReturned() throws Throwable {
        //contract validated in controller
    }

    //Templates - get valid templates data for user
    @When("^Admin lists valid templates call is made$")
    public void listAllValidTemplates() throws Throwable {
        response = cardsTemplatesActions.listValidTemplates(200);
    }

    @Then("^All valid templates should be properly returned for current default templates$")
    public void validationForAllValidTemplates() throws Throwable {
        List<String> extractedName = response.getList("ecardTemplates.name");
        if (dataExchanger.getTemplatesConfigObject().getUseDefaultTemplatesSet())
            assertThat("There is no any Default template returned, but settings are set to return them", extractedName, hasItem(not(containsString("Custom Template "))));
        else
            assertThat("Only Custom templates should be available", extractedName, everyItem(containsString("Custom Template ")));
    }


    //Scenario Outline: Templates - get individual templates data
    @When("^Admin retrieve details of chosen template by template id$")
    public void getDetailsOfSingleTemplate() throws Throwable {
        List<Template> customEcardTemplates = response.getList("customEcardTemplates", Template.class);
        random = new Random().nextInt(customEcardTemplates.size() - 1);
        String templateId = customEcardTemplates.get(random).getId();

        response = cardsTemplatesActions.getTemplate(templateId, 200);
    }

    @Then("^Retrieved template details are as expected$")
    public void checkIfSingleDetailsTemplatesDataAreAsExpected() throws Throwable {
        Template template = dataExchanger.getTemplatesListObject().get(random);

        assertThat("id is incorrect", response.getString("id"), is(template.getId()));
        assertThat("createdAt is incorrect", response.getString("createdAt"), is(template.getCreatedAt()));
        assertThat("name is incorrect", response.getString("name"), is(template.getName()));
        assertThat("imageUrl is incorrect", response.getString("imageUrl"), is(template.getImageUrl()));
        assertThat("thumbnailUrl is incorrect", response.getString("thumbnailUrl"), is(template.getThumbnailUrl()));
    }

    //Scenario Outline: Templates - get individual templates data using invalid template id
    @When("^Admin retrieve details of not existing template by template id '(.+?)', '(\\d+)'$")
    public void getDetailsOfSingleTemplateUsingInvalidTemplateId(String templateId, int status) throws Throwable {
        response = cardsTemplatesActions.getTemplate(templateId, status);
        dataExchanger.setResponse(response);
    }

    @Then("^(.+?) by id '(.+?)' returns proper error message '(.+?)', '(.+?)', '(.+?)'$")
    public void checkIfSingleDetailsTemplatesDataWereNotReturned(String method, String templateId, String error, String message, int status) throws Throwable {
        new GenericSteps().getGenericErrorMessageValidation(method, status, error, String.format(message, templateId));

    }

    //Scenario Outline: Templates - add template to the system
    @When("^Admin add new template to the system '(.+?)'$")
    public void addNewTemplateToTehSystem(String jsonBody) throws Throwable {
        if (jsonBody.equalsIgnoreCase("default"))
            jsonBody = "{ \"name\": \"Custom Template Test Creation RANDOM_NAME\", \"imageUrl\": \"http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_cfa210c1-1293-4c37-a0e8-715c67b1b5bb_22a47f059281601ebdb8a8b23a1d4933.JPEG\", \"thumbnailUrl\": \"http://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_3035cd21-bbb6-4124-a279-7f98bcd0720d_22a47f059281601ebdb8a8b23a1d4933.JPEG\" }";

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        template = mapper.readValue(jsonBody, Template.class);

        response = cardsTemplatesActions.addNewTemplate(template, 201);
        template.setId(response.toString());

        dataExchanger.setTemplate(template);
    }

    @Then("^New template will be properly added$")
    public void checkIfTemplateWaCreated() throws Throwable {
        assertThat("Newly created template id was not returned", template.getId(), not(isEmptyOrNullString()));
        assertThat("Newly created template is not returned in get individual template call", cardsTemplatesActions.getTemplate(template.getId(), 200).getStatusCode(), is(200));
    }

    @Then("^New template will be available on list of all templates$")
    public void checkIfNewTemplateIsReturnedInTemplatesList() throws Throwable {
        List<Template> templatesList = cardsTemplatesActions.listAllTemplates(200).getList("customEcardTemplates", Template.class);
        assertThat("Newly created template is not available on templates list", templatesList, hasItem(hasProperty("id", equalTo(template.getId()))));
    }

    //Scenario Outline: Templates - try add template to the system with invalid parameters
    @When("^Admin add new template to the system with invalid data '(.+?)', '(.+?)'$")
    public void addNewTemplateToTheSystemWithIncorrectParametersData(String jsonData, int status) throws Throwable {
        response = cardsTemplatesActions.addNewTemplate(mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES).readValue(jsonData, Template.class), status);
        dataExchanger.setResponse(response);
    }

    //Scenario Outline: Templates - update existing template data
    @When("^Created template will be updated with new data '(.+?)'$")
    public void updateExistingTemplateData(String jsonBody) throws Throwable {
        Template templateCreated = dataExchanger.getTemplate();
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        template = mapper.readValue(jsonBody, Template.class);

        cardsTemplatesActions.updateTemplate(templateCreated.getId(), template, 200);
        response = cardsTemplatesActions.getTemplate(templateCreated.getId(), 200);
    }

    @Then("^Template data will be properly updated$")
    public void checkIfSelectedTemplateDataWasUpdated() throws Throwable {
        assertThat("name field incorrectly updated", response.getString("name"), is(template.getName()));
        assertThat("imageUrl field incorrectly updated", response.getString("imageUrl"), is(template.getImageUrl()));
        assertThat("thumbnailUrl field incorrectly updated", response.getString("thumbnailUrl"), is(template.getThumbnailUrl()));
    }

    //Scenario Outline: Templates - update existing template data with invalid parameters
    @When("^Created template will be updated with new invalid data '(.+?)', '(\\d+)'$")
    public void updateExistingTemplateDataWithInvalidParametersData(String jsonBody, int status) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Template templateWithInvalidData = mapper.readValue(jsonBody, Template.class);

        response = cardsTemplatesActions.updateTemplate(template.getId(), templateWithInvalidData, status);
        dataExchanger.setResponse(response);
    }


    //Scenario Outline: Templates - delete template from the system
    @Then("^New template will be deleted$")
    public void deleteChosenTemplate() throws Throwable {
        response = cardsTemplatesActions.deleteTemplate(template.getId(), 200);
    }

    @Then("^Deleted template will not be available on list of all templates$")
    public void checkIfTemplateWasDeleted() throws Throwable {
        List<Template> templatesList = cardsTemplatesActions.listAllTemplates(200).getList("customEcardTemplates", Template.class);
        assertThat("Deleted template still available on list", templatesList, not(hasItem(hasProperty("id", equalTo(template.getId())))));
    }

    //Scenario Outline: Templates - delete template from the system with invalid id parameter
    @When("^Delete template with invalid id parameter '(.+?)', '(\\d+)'$")
    public void deleteChosenTemplateWithInvalidIdParameter(String templateId, int status) throws Throwable {
        response = cardsTemplatesActions.deleteTemplate(templateId, status);
        dataExchanger.setResponse(response);
    }


}
