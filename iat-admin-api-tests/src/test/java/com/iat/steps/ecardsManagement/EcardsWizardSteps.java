package com.iat.steps.ecardsManagement;

import com.iat.actions.ecardsManagement.EcardsWizardActions;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class EcardsWizardSteps {

    private EcardsWizardActions ecardsWizardActions = new EcardsWizardActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ResponseContainer response;


    //Get current status of wizard completion process
    @Given("^IAT admin is logged for first time$")
    public void isIatAdminLoggedFirsTimeValidation() throws Throwable {
        ecardsWizardActions.clearPartnerIatAdminSettings();
    }

    @Given("^Get Wizard finished status call returns '(.+?)' for first time logged user$")
    public void getWizardFinishedStatusAfterFirstLogin(String flag) throws Throwable {
        response = ecardsWizardActions.getEcardsWizardStatus(200);

        String finishedStatus = response.getString("finished");
        assertThat("Incorrect wizard status returned after first login", finishedStatus, is(flag));
        String wizardLastStep = response.getString("wizardLastStep");
        assertThat("Incorrect wizard wizardLastStep value returned after first login", wizardLastStep, is("0"));

    }

    @Given("^Wizard step up to '(\\d+)' is finished$")
    public void wizardUpToStepNoIsFinished(int number) throws Throwable {
        ecardsWizardActions.wizardSetUpConfigToStepNo(number);
    }

    @When("^Get Wizard finished status call is made$")
    public void getWizardFinishedStatus() throws Throwable {
        response = ecardsWizardActions.getEcardsWizardStatus(200);
    }

    @Then("^Get Wizard finished status call returns '(.+?)'$")
    public void getWizardFinishedStatusValidation(String status) throws Throwable {
        String finishedStatus = response.getString("finished");
        assertThat("Incorrect wizard finished status", finishedStatus, is(status));

        //Clear IAT partner settings
        ecardsWizardActions.clearPartnerIatAdminSettings();
    }

    //Setup global Config without required authority - Error message
    @When("^IAT admin try setup config step '(\\d+)' for config wizard '(.+?)'$")
    public void trySetUpConfigWithoutPriviliges(int stepNumber, int status) throws Throwable {
        response = ecardsWizardActions.trySetUpWizardConfig(stepNumber, status);
        dataExchanger.setResponse(response);
    }

    //Get current status of wizard completion process Error message validation
    @When("^Get Wizard finished status call for incorrect data '(.+?)'$")
    public void getWizardFinishedStatusErrorMessage(int status) throws Throwable {
        response = ecardsWizardActions.getEcardsWizardStatus(status);
        dataExchanger.setResponse(response);
    }

    @When("^Set Wizard last step call is made with value '(.+?)'$")
    public void setWizardLastStep(String step) throws Throwable {
        response = ecardsWizardActions.setEcardsWizardStep(step, 200);
    }

    @Then("^Get Wizard finished status call returns proper last step value '(.+?)'$")
    public void getWizardLastStep(String step) throws Throwable {
        response = ecardsWizardActions.getEcardsWizardStatus(200);

        String wizardLastStep = response.getString("wizardLastStep");
        assertThat("Incorrect wizard wizardLastStep value", wizardLastStep, is(step));

        //SQL clean
        ecardsWizardActions.clearPartnerIatAdminSettings();
    }

    @When("^Set Wizard last step call is made with incorrect data '(.*?)', '(.+?)'$")
    public void setWizardLastStepErrorMessage(String step, int status) throws Throwable {
        response = ecardsWizardActions.setEcardsWizardStep(step, status);
        dataExchanger.setResponse(response);
    }


}



