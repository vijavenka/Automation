package com.iat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.FixCallsActions;
import com.iat.actions.SuppliersActions;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class FixCallsSteps {

    private SuppliersActions suppliersActions = new SuppliersActions();
    private FixCallsActions fixCallsActions = new FixCallsActions();
    private ContractValidator contractValidator = new ContractValidator();
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPool_pointsManager);

    DataExchanger dataExchanger = DataExchanger.getInstance();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    String response;
    ObjectMapper mapper = new ObjectMapper();


    @When("^Execute fix missing supplier partner api key$")
    public void executeSuppliersFix() throws Throwable {
        response = fixCallsActions.fixMissingSuppliersPartnerIds(200);
    }


    @Then("^(?:Fixed supplier|Supplier) '(.+?)' contains partner api key$")
    public void validateSupplierPartnerApiKey(String id) throws Throwable {
        if (id.equals("previous_call"))
            id = dataExchanger.getSuppliers().get(dataExchanger.getSuppliers().size() - 1).getId();

        response = suppliersActions.getSupplierById(id, 200);
        String apiKey = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "partnerApiKey");
        assertTrue("api key not filled: " + apiKey, !apiKey.equals("null"));
    }


    @Then("^Partner was created for fixed supplier '(.+?)'$")
    public void validatePartnerCreatedInPointsManager(String id) throws Throwable {
        if (id.equals("previous_call"))
            id = dataExchanger.getSuppliers().get(dataExchanger.getSuppliers().size() - 1).getId();

        response = suppliersActions.getSupplierById(id, 200);
        String apiKey = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "partnerApiKey");

        String partnerShortName = mySQLConnector_pointsManager.getSingleResult("SELECT shortName from Partner where accessKey = '" + apiKey + "'");
        String partnerGroupId = mySQLConnector_pointsManager.getSingleResult("SELECT groupId from Partner where accessKey = '" + apiKey + "'");

        String suppliersGroupId = mySQLConnector_pointsManager.getSingleResult("SELECT id from PartnersGroup where name = 'Suppliers'");

        assertThat("Group Id: " + partnerGroupId + " assigned to Partner created for fixed supplier is incorrect, should be: " + suppliersGroupId, partnerGroupId, is(suppliersGroupId));
    }


    @Then("^Delete partners for audit tests suppliers$")
    public void deletePartnerForSupplierById() throws Throwable {
        mySQLConnector_pointsManager.execute("DELETE from VirtualGroup where partnerId in (SELECT id from Partner where name like 'API_AUDIT_CMS_TEST_%')");
        mySQLConnector_pointsManager.execute("DELETE from Partner where name like 'API_AUDIT_CMS_TEST_%'");
    }

}