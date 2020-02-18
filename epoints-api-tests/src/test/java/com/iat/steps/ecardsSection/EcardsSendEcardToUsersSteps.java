package com.iat.steps.ecardsSection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.JoinAndSignInActions;
import com.iat.actions.ecardsSection.EcardsSendEcardToUsersActions;
import com.iat.actions.users.UsersActions;
import com.iat.domain.Ecards.EcardsSent;
import com.iat.domain.Ecards.Reason;
import com.iat.steps.GenericSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.utils.SessionIdKeeper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EcardsSendEcardToUsersSteps {

    private EcardsSendEcardToUsersActions ecardsSendEcardToUsersActions = new EcardsSendEcardToUsersActions();
    private UsersActions usersActions = new UsersActions();
    private JoinAndSignInActions joinAndSignInActions = new JoinAndSignInActions();
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;
    private EcardsSent ecardsSent;
    private int senderBalance;
    private int receiverBalance;


    //Check if it's possible to get list of reasons
    @When("^User call for get ecards reasons list$")
    public void getEcardsReasonsList() throws Throwable {
        response = ecardsSendEcardToUsersActions.getEcardsReasonsList(200);
    }

    @Then("^Get ecard reasons call returns list of reasons$")
    public void getEcardsReasonsListMatchContract() throws Throwable {
        //controller is doing validation already
    }


    //Check if it's possible to get list of reasons - error message validation
    @When("^User call for get ecards reasons list with incorrect data '(.+?)'$")
    public void getEcardsReasonsListErrorMessage(int status) throws Throwable {
        response = ecardsSendEcardToUsersActions.getEcardsReasonsList(status);
        dataExchanger.setResponseContainer(response);
    }

    @When("^User call for get ecards templates list$")
    public void getEcardsTemplatesList() throws Throwable {
        response = ecardsSendEcardToUsersActions.getEcardsTemplatesList(200);
    }

    @Then("^Get ecard templates call returns list of templates according '(.+?)'$")
    public void getEcardsTemplatesListMatchContract(String settingsTemplate) throws Throwable {

        List<String> extractedName = response.getList("ecardTemplates.name");
        if (settingsTemplate.equals("false"))
            assertThat("Template is not custom one", extractedName, everyItem(containsString("Custom Template ")));
        else
            assertThat("There is no any Default template returned, but settings are set to return them", extractedName, hasItem(not(containsString("Custom Template "))));
    }


    //Check if it's possible to get list of reasons - error message validation
    @When("^User call for get ecards templates list with incorrect data '(.+?)'$")
    public void getEcardsTemplatesListErrorMessage(int status) throws Throwable {
        response = ecardsSendEcardToUsersActions.getEcardsTemplatesList(status);
        dataExchanger.setResponseContainer(response);
    }


    //Sending ecards user by user

    @Given("^Ecards sender balance before send is known$")
    public void ecardsSenderBalanceBefore() throws Throwable {
        response = usersActions.getUserBalance("null", 200);
        senderBalance = response.getInt("confirmedPoints");
    }

    @When("^Send ecards call is made with data '(.+?)', '(.+?)'$")
    public void sendEcard(String jsonBody, int status) throws Throwable {
        ecardsSent = new ObjectMapper().readValue(jsonBody, EcardsSent.class);

        if (ecardsSent.getUsersKey() != null)
            if (ecardsSent.getUsersKey().size() > 1)
                ecardsSent.setUsersKey(ecardsSendEcardToUsersActions.getDynamicUsersUuids(ecardsSent.getUsersKey()));
        if (ecardsSent.getUsersKey().size() == 1 && ecardsSent.getUsersKey().get(0).equals("HIMSELF"))
            ecardsSent.setUsersKey(singletonList(dataExchanger.getUserProfile().getAccountDetails().getId()));

        response = ecardsSendEcardToUsersActions.sendEcard(ecardsSent, status);
        dataExchanger.setResponseContainer(response);
    }

    @Then("^Send ecards call with incorrect data returns error message '(.+?)', '(.+?)', '(.+?)'$")
    public void sendEcardsCallWithIncorrectDataReturnsErrorMessage(int status, String error, String message) throws Throwable {
        if (message.contains("%s")) {
            Reason reason = ecardsSendEcardToUsersActions.getEcardsReasonsList(200).getAsObject(Reason.class, "find{it.id == " + ecardsSent.getReasonId() + "}");

            new GenericSteps().getGenericErrorMessageValidation("Send ecards call", status, error, String.format(message, reason.getUserToUserMin(), reason.getUserToUserMax()));
        } else
            new GenericSteps().getGenericErrorMessageValidation("Send ecards call", status, error, message);

    }


    @Then("^Ecards sender balance is reduced according ecard details$")
    public void ecardsSenderBalanceAfter() throws Throwable {
        Thread.sleep(4000);
        int senderBalanceBefore = senderBalance;
        response = usersActions.getUserBalance("null", 200);
        senderBalance = response.getInt("confirmedPoints");

        if (ecardsSent.getPointsValue() != null) {
            assertThat("Sender balance not reduced: ", senderBalanceBefore, greaterThan(senderBalance));
            assertThat("Sender balance not reduced properly: ", senderBalance, is(senderBalanceBefore - ecardsSent.getPointsValue() * ecardsSent.getUsersKey().size()));
        } else
            assertThat("Sender balance was changed but should stay unchanged: ", senderBalanceBefore, is(senderBalance));

    }

    //Sending ecards user by user and check users balance
    @Given("^Ecards receiver '(.+?)' balance before send is known$")
    public void ecardsReceiverBalanceBefore(String credentials) throws Throwable {
        //copy sender session
        String sessionCopy = sessionId.get();

        //authorize into receiver account
        sessionId.set(joinAndSignInActions.getSessionIdForAuthUser(credentials));
        response = usersActions.getUserBalance("null", 200);
        receiverBalance = response.getInt("confirmedPoints");

        //back to sender session
        sessionId.set(sessionCopy);
    }


    @Then("^Ecards receiver '(.+?)' balance is increased according ecard details$")
    public void ecardsReceiverBalanceAfter(String credentials) throws Throwable {
        int receiverBalanceBefore = receiverBalance;
        //authorize into receiver account
        sessionId.set(joinAndSignInActions.getSessionIdForAuthUser(credentials));
        response = usersActions.getUserBalance("null", 200);
        receiverBalance = response.getInt("confirmedPoints");

        if (ecardsSent.getPointsValue() != null) {
            assertThat("Sender balance not reduced: ", receiverBalanceBefore, lessThan(senderBalance));
            assertThat("Sender balance not reduced properly: ", receiverBalance, is(receiverBalanceBefore + ecardsSent.getPointsValue() * ecardsSent.getUsersKey().size()));
        } else
            assertThat("Sender balance was changed but should stay unchanged: ", receiverBalanceBefore, is(receiverBalance));
    }
}
