package com.iat.steps.registration;

import com.iat.actions.registration.UserTokenActions;
import com.iat.domain.UserToken;
import com.iat.repository.UserRepository;
import com.iat.repository.UserTokenRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.repository.impl.UserTokenRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserRegistrationValidationSteps {

    private UserRepository userRepository = new UserRepositoryImpl();
    private UserTokenRepository userTokenRepository = new UserTokenRepositoryImpl();
    private UserTokenActions userTokenActions = new UserTokenActions();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();
    private UserToken token;
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    //Scenario Outline: User details - user ids list correctness after token verification
    @Given("^User '(.+?)' token is generated and active$")
    public void getActiveTokenForUser(String email) throws Throwable {
        if (email.equals("previous_call")) {
            email = dataExchanger.getEmail();
            token = userTokenRepository.getToken(userRepository.findByEmail(email).getUuid());
            assertThat("Token is not active: ", token.isActive());
            assertThat("Token type is not REGISTER: ", token.getType(), is("REGISTER"));
        } else
            assertThat("TODO: ", false);

    }

    @When("^User token will be verified '(.+?)'$")
    public void verifyUserTokenAndRetunIdsList(String apiKey) throws Throwable {
        responseHolder.setResponse(userTokenActions.verifyUserToken(apiKey, token.getValue(), 200));
    }

    @Then("^His system ids list will be returned$")
    public void verifyIfUserIdsListIsCorrect() throws Throwable {
        //contractValidator.validateResponseWithContract("userVerify-response-schema.json", responseHolder.getResponse());
        //TODO should I check ids values or json schema is enough?
    }

    @Then("^Token verification response should contain error information '(\\d+)', '(.+?)', '(.+?)'$")
    public void tokenValidationResponseErrorMessage(int expResponseCode, String expErrorCode, String responseMessage) throws Throwable {
        errorsValidator.validateErrorResponse(responseHolder.getResponse(), expResponseCode, expErrorCode, responseMessage, true);
    }

}
