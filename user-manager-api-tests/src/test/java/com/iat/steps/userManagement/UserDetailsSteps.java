package com.iat.steps.userManagement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.userDetails.UserDetailsActions;
import com.iat.domain.User;
import com.iat.domain.UserDetails;
import com.iat.repository.UserRepository;
import com.iat.repository.UserTokenRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.repository.impl.UserTokenRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.userDetails.UserDetailsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class UserDetailsSteps {

    private UserDetailsActions userDetailsActions = new UserDetailsActions();
    private UserDetailsValidator userDetailsValidator = new UserDetailsValidator();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer responseTemp;
    private String userId;
    private String newEmail = null;
    private UserTokenRepository userTokenRepository = new UserTokenRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private UserDetails userDetails;
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    @When("^User update account details with following data '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void updateUserAccountDetails(String id, String idType, String apiKey, String jsonBody) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        userDetails = mapper.readValue(jsonBody, UserDetails.class);
        if (idType.equals("EMAIL"))
            userId = id;
        else if (idType.equals("UUID"))
            userId = userRepository.findByEmail(id).getUuid();
        responseHolder.setResponse(userDetailsActions.updateUserAccountDetailsByJson(userId, idType, apiKey, userDetails, 200));
        responseTemp = responseHolder.getResponse();
        // contractValidator.validateResponseWithContract("userDetailsGET-response-schema.json", responseHolder.getResponse());
    }

    @Then("^User details data will be updated properly '(.+?)', '(.+?)'$")
    public void validateIfUserDetailsUpdated(String idType, String apiKey) throws Throwable {
        responseHolder.setResponse(userDetailsActions.getUserAccountDetails(userId, idType, apiKey));
        //contractValidator.validateResponseWithContract("userDetailsGET-response-schema.json", responseHolder.getResponse());

        userDetailsValidator.validateIfUserDetailsUpdated(responseHolder.getResponse().getAsObject(UserDetails.class), userDetails);
    }

    @When("^Request for get user details by epointsToken '(.+?)', '(.+?)'$")
    public void request_for_get_user_details_by_epointsToken(String epointsToken, String apiKey) throws Throwable {
        responseHolder.setResponse(userDetailsActions.getUserAccountDetailsByEpointsToken(epointsToken, apiKey, 200));
    }

    @When("^Request for get user details by userId '(.+?)', '(.+?)'$")
    public void request_for_get_user_details_by_userId(String apiKey, String idType) throws Throwable {
        if (idType.equals("UUID"))
            responseHolder.setResponse(userDetailsActions.getUserAccountDetails(dataExchanger.getUuid(), idType, apiKey));
        if (idType.equals("EMAIL"))
            responseHolder.setResponse(userDetailsActions.getUserAccountDetails(dataExchanger.getEmail(), idType, apiKey));
    }

    @Then("^User details are returned with valid contract$")
    public void user_details_are_returned() throws Throwable {
        // contractValidator.validateResponseWithContract("userDetailsByEpointsTokenGET-response-schema.json", responseHolder.getResponse());
    }


    //Scenario Outline: User details - email resend for change
    @When("^User request to resend '(.+?)' email for '(.+?)', '(.+?)', '(.+?)'$")
    public void resendSpecifiedUserEmailChangeEmail(String resendType, String userId, String idType, String apiKey) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        userDetails = mapper.readValue("{ \"email\": \"updated.for.resend@iatltd.com\" }", UserDetails.class);
        userDetailsActions.updateUserAccountDetailsByJson(userId, idType, apiKey, userDetails, 200);
        System.out.println("Issue reported NBO-7792");
        responseHolder.setResponse(userDetailsActions.resendUserEmailChangeOrRegistrationEmail(userId, idType, apiKey, resendType));
    }


    @When("^User request to resend '(.+?)' email '(.+?)', '(.+?)'$")
    public void resendSpecifiedUserRegristraionEmail(String resendType, String idType, String apiKey) throws Throwable {
        responseHolder.setResponse(userDetailsActions.resendUserEmailChangeOrRegistrationEmail(dataExchanger.getEmail(), idType, apiKey, resendType));
    }

    @When("^User request to resend '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void resendSpecifiedUserRegristraionEmailToEmail(String resendType, String email, String idType, String apiKey) throws Throwable {
        responseHolder.setResponse(userDetailsActions.resendUserEmailChangeOrRegistrationEmail(email, idType, apiKey, resendType));
    }

    @Then("^Email '(.+?)' will be properly resend$")
    public void checkIfResetEmailWasResend(String emailType) throws Throwable {
        assertThat(emailType + " EMAIL was not sent", responseHolder.getResponse().toString(), is("true"));
    }

    //Scenario Outline: User details - behaviour of system when resend email request done with invalid parameters
    @When("^User '(.+?)' request to resend '(.+?)' email with invalid parameters '(.+?)', '(.+?)', '(\\d+)'$")
    public void resendSpecifiedUserRegristraionEmailWithInvalidParameters(String userId, String resendType, String idType, String apiKey, int expResponseCode) throws Throwable {
        responseHolder.setResponse(userDetailsActions.resendUserEmailChangeOrRegistrationEmail(userId, idType, apiKey, resendType, expResponseCode));
    }

    @Then("^Email will not be properly resend$")
    public void checkIfResetEmailWasNotResend() throws Throwable {
        assertThat("Email was resend", responseHolder.getResponse().toString(), isEmptyOrNullString());
    }

    //Scenario Outline: User details - behaviour of system user verify request done with invalid parameters
    @Given("^User '(.+?)' token is generated$")
    public String getUserActiveToken(String email) throws Throwable {
        User user = userRepository.findByEmail(email);
        return userTokenRepository.getToken(user.getUuid()).getValue();
    }

    @When("^User '(.+?)' token will be verified with invalid values of parameters '(.+?)', '(.+?)', '(\\d+)'$")
    public void tryVeryfyTokenWithInvalidParameterValues(String email, String apiKey, String token, int expResponseCode) throws Throwable {
        responseHolder.setResponse(userDetailsActions.verifyUserToken(apiKey, token, expResponseCode));
    }

    @Then("^His system ids list will not be returned$")
    public void checkIfUserIdsListWasNotReturned() throws Throwable {
        assertThat("Response is not empty", responseHolder.getResponse().toString(), isEmptyOrNullString());
    }


    @Then("^All data will be saved in user account '(.+?)', '(.+?)', '(.+?)'$")
    public void checkIfUserAccountDetailsDataWereProperlySaved(String idType, String apiKey, String jsonParameters) throws Throwable {
        responseHolder.setResponse(userDetailsActions.getUserAccountDetails(userId, idType, apiKey));
        //contractValidator.validateResponseWithContract("userDetailsGET-response-schema.json", responseHolder.getResponse());
        userDetailsValidator.checkIfUpdatedUserDataAreAsExpected(responseHolder.getResponse(), jsonParameters, responseTemp, newEmail);
    }

    //Scenario Outline: User details - behaviour of system when user details get with invalid parameters
    @When("^User get his account details with following invalid data '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void getUserAccountDetailsDataUsingInvalidParameters(String id, String idType, String apiKey, int expResponseCode) throws Throwable {
        if (idType.equals("EMAIL")) {
            userId = id;
        } else if (idType.equals("UUID") || idType.equals("wrongIdType")) {
            userId = userRepository.findByEmail(id).getUuid();
        }
        responseHolder.setResponse(userDetailsActions.getUserAccountDetails(userId, idType, apiKey, expResponseCode));
    }


    //Scenario Outline: User details - behaviour of system when user details will be updated with invalid parameters
    @When("^User update his account details with following invalid data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void updateUserAccountDetailsDataUsingInvalidParameters(String id, String idType, String apiKey, String jsonBody, int expResponseCode, String expErrorCode, String expErrorMsg) throws Throwable {
        if (idType.equals("EMAIL"))
            userId = id;
        else if (idType.equals("UUID") || idType.equals("wrongIdType"))
            userId = userRepository.findByEmail(id).getUuid();

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        userDetails = mapper.readValue(jsonBody, UserDetails.class);
        responseHolder.setResponse(userDetailsActions.updateUserAccountDetailsByJson(userId, idType, apiKey, userDetails, expResponseCode));
    }

    @Then("^Response message with '(\\d+)', '(.+?)', '(.+?)' is returned for get user details$")
    public void checkIfAccountWasNotCreated(int expResponseCode, String expErrorCode, String responseMessage) throws Throwable {
        errorsValidator.validateErrorResponse(responseHolder.getResponse(), expResponseCode, expErrorCode, responseMessage, true);
    }


    @Then("^Response message with '(\\d+)', '(.+?)', '(.+?)' is returned for resend tokens$")
    public void resendEmailsTokensErrorMessage(int expResponseCode, String expErrorCode, String responseMessage) throws Throwable {
        errorsValidator.validateErrorResponse(responseHolder.getResponse(), expResponseCode, expErrorCode, responseMessage);
    }
}