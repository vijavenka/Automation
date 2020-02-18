package com.iat.steps.registration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.registration.UserRegistrationActions;
import com.iat.domain.UserBalance;
import com.iat.domain.UserDetails;
import com.iat.repository.UserRepository;
import com.iat.repository.UserTokenRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.repository.impl.UserTokenRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.balance.UserBalanceValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserRegistrationSteps {

    private UserRegistrationActions userRegistrationActions = new UserRegistrationActions();
    private UserTokenRepository userTokenRepository = new UserTokenRepositoryImpl();
    private UserBalanceValidator userBalanceValidator = new UserBalanceValidator();
    private UserRepository userRepository = new UserRepositoryImpl();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();
    private ObjectMapper mapper = new ObjectMapper();
    private UserDetails userDetails;
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    @When("^User sends registration call with data '(.+?)' and '(.+?)'$")
    public void registerNewUserByJson(String jsonBody, String apiKey) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        userDetails = mapper.readValue(jsonBody, UserDetails.class);
        responseHolder.setResponse(userRegistrationActions.createUserAccountByJson(userDetails, apiKey, 201));

        userDetails.setUuid(responseHolder.getResponse().getString("uuid"));
        dataExchanger.setEmail(userDetails.getEmail());
        dataExchanger.setUuid(userDetails.getUuid());
    }


    @Given("^User created new account in the system '(.+?)'$")
    public void registerNewRandomUser(String apiKey) throws Throwable {
        registerNewUserByJson("{\"email\": \"user.manager.test_\"}", apiKey);

    }

    @Then("^Account is created and his UUID is returned$")
    public void checkIfAccountWasCreated() throws Throwable {
        assertThat("UUID was not returned!", responseHolder.getResponse().getString("uuid"), is(notNullValue()));
        userRepository.findDetailsById(responseHolder.getResponse().getString("uuid"))
                .validateContract("registration/UserDetails-GET-registration-by-email-response-schema.json", 200);
    }


    @And("^He should receive points for registration$")
    public void checkIfPointsAwarded() throws Throwable {
        Thread.sleep(1000);
        userBalanceValidator.checkIfBalanceValuesAreCorrectById(new UserBalance(50, 0, 0, 0), responseHolder.getResponse().getString("uuid"));
    }

    @Then("^Registration token have to be generated '(.+?)'$")
    public void checkIfTokenGenerated(boolean tokenGenerated) throws Throwable {
        if (tokenGenerated)
            assertThat("Token not created for new user!", userTokenRepository.getToken(dataExchanger.getUuid()).getType(), is("REGISTER"));
    }

    //Scenario Outline: User registration - try to register new user with invalid parameters
    @When("^User makes registration call with invalid '(.+?)' and '(.+?)', '(\\d+)'$")
    public void registerNewUserUsingInvalidParameters(String jsonBody, String apiKey, int expResponseCode) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        userDetails = mapper.readValue(jsonBody, UserDetails.class);
        responseHolder.setResponse(userRegistrationActions.createUserAccountByJson(userDetails, apiKey, expResponseCode));
    }

    @Then("^Response message with '(\\d+)', '(.+?)', '(.+?)', '(.+?)' is returned and account is not created$")
    public void checkIfAccountWasNotCreated(int expResponseCode, String expErrorCode, String responseMessage, String expErrorMsg) throws Throwable {
        if (expResponseCode == 201)
            assertThat("Incorrect Uuid returned for existing user!", responseHolder.getResponse().getString("uuid"), containsString(responseMessage));
        else {
            errorsValidator.validateErrorResponse(responseHolder.getResponse(), expResponseCode, expErrorCode, responseMessage);
            errorsValidator.validateHeaderErrorResponse(responseHolder.getResponse(), expErrorCode, expErrorMsg);
        }

    }
}