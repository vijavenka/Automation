package com.iat.steps;


import com.iat.utils.DataExchanger;
import com.iat.validators.GenericValidator;
import cucumber.api.java.en.Then;

public class GenericSteps {

    private GenericValidator genericValidator = new GenericValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    @Then("^(.+?) for incorrect data returns error message '(.+?)', '(.+?)', '(.+?)'$")
    public void getGenericErrorMessageValidation(String method, int status, String error, String message) throws Throwable {
        System.out.println("Error message validation for: '" + method + "'");

        if (error.equals("null"))
            genericValidator.multipleFieldsErrorMessageValidation(message, dataExchanger.getResponseContainer());
        else
            genericValidator.validateGenericErrorMessage(dataExchanger.getResponseContainer(), error, message, status);

        dataExchanger.setResponseContainer(null);
    }

}
