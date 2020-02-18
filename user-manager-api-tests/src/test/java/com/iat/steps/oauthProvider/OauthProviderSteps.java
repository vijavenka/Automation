package com.iat.steps.oauthProvider;

import com.iat.Config;
import com.iat.actions.oauthProvider.OauthProviderActions;
import com.iat.domain.OAuth;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.authentication.UserAuthenticationValidator;
import com.iat.validators.oauthProvider.OauthProviderValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OauthProviderSteps {

    private OauthProviderActions oauthProviderActions = new OauthProviderActions();
    private UserAuthenticationValidator userAuthenticationValidator = new UserAuthenticationValidator();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private OauthProviderValidator oauthProviderValidator = new OauthProviderValidator();
    private ResponseContainer response, responseBeforeDelay;
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private OAuth oAuth;

    @When("^User is authenticating with following data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)' password based grant type$")
    public void authorizeUserWithOauthForPasswordGrantType(String grantType, String username, String password, String authorizationHeader, int status) throws Throwable {
        if (authorizationHeader.equals("default")) {
            authorizationHeader = Config.getOauthProviderAuthorizationHeader();
        }

        if (username.equals("default") && password.equals("default")) {
            username = Config.mainTestUserEmail;
            password = Config.mainTestUserPassword;
        }

        dataExchanger.setEmail(username);

        response = oauthProviderActions.authenticateUserByOauthPassword(authorizationHeader, grantType, username, password, status);
        oAuth = response.getAsObject(OAuth.class);
    }

    @Then("Token data is returned properly '(.+?)'$")
    public void validateCorrectnessOfOauthAuthenticationResponse(String grantType) throws Throwable {
        if (grantType.contains("single_use")) {
            assertThat("Single use token flag is not correct", oAuth.isSingleUse());
            assertThat("Token is not set to less than 10 seconds", oAuth.getExpires_in(), is(14));
        } else if (grantType.contains("facebook") || grantType.equals("password")) {
            assertThat("Token is not set to less than one hour", oAuth.getExpires_in(), is(lessThanOrEqualTo(3599)));
        } else if (grantType.equals("refresh_token")) {
            assertThat("Token is not set to hour", oAuth.getExpires_in(), is(lessThanOrEqualTo(3599)));
        }
        response = oauthProviderActions.tokenCheck(oAuth.getAccess_token(), "null", "null", 200);
        userAuthenticationValidator.checkUserUuidCorrectness(dataExchanger.getEmail(), response.getString("uuid"));
    }

    @Then("^Validate if it is possible to authorize on epoints '(.+?)'$")
    public void validateUseSingleUseToken(int status) throws Throwable {
        if (status != 302) {
            response = oauthProviderActions.useSingleUseToken(oAuth.getAccess_token() + "asdfghj", status);
            errorsValidator.validateErrorResponse(response, status, "401", "Authorization failed.");
            assertThat("incorrect messageCode", response.getString("messageCode"), is("errors.security.authorization-failed"));
            System.out.println("\n\nFOR MANUAL PURPOSE URL: " + Config.getEpointsUrl() + "/my-account/profile?single_use_authentication_token=" + oAuth.getAccess_token() + "asdf\n\n");
        } else {
            response = oauthProviderActions.useSingleUseToken(oAuth.getAccess_token(), status);

            userAuthenticationValidator.checkUserUuidCorrectness(dataExchanger.getEmail(), response.getString("id"));
            assertThat("incorrect email", response.getString("email"), is(dataExchanger.getEmail()));
            assertThat("incorrect verified", response.getBoolean("verified"), is(true));

            System.out.println("\n\nFOR MANUAL PURPOSE URL: " + Config.getEpointsUrl() + "/my-account/profile?single_use_authentication_token=" + oAuth.getAccess_token() + "\n\n");
        }


    }

    @When("^User is authenticating with following data '(.+?)', '(.+?)', '(\\d+)' refresh_token based grant type$")
    public void authorizeUserWithOauthForRefreshTokenBasedGrantType(String grantType, String token, int status) throws Throwable {
        if (token.equals("dynamic")) {
            response = oauthProviderActions.authenticateUserByOauthPassword("password", Config.mainTestUserEmail, Config.mainTestUserPassword);
            token = response.get("refresh_token");
            dataExchanger.setEmail(Config.mainTestUserEmail);
            dataExchanger.setoAuth(response.getAsObject(OAuth.class));
        }

        response = oauthProviderActions.authenticateUserByOauthToken(Config.getOauthProviderAuthorizationHeader(), grantType, token, "null", status);
        oAuth = response.getAsObject(OAuth.class);
    }

    @When("^User is authenticating with following data '(.+?)', '(.+?)', '(.+?)', '(\\d+)' facebook token based grant type$")
    public void authorizeUserWithOauthForTokenBasedGrantType(String grantType, String token, String facebookId, int status) throws Throwable {
        if (token.equals("dynamic")) {
            token = Config.User.TESTY_TESTATORI.getFacebookAccessToken();
            dataExchanger.setEmail(Config.User.TESTY_TESTATORI.getEmail());
        }
        if (facebookId.equals("dynamic"))
            facebookId = Config.User.TESTY_TESTATORI.getFacebookId();

        response = oauthProviderActions.authenticateUserByOauthToken(Config.getOauthProviderAuthorizationHeader(), grantType, token, facebookId, status);
        oAuth = response.getAsObject(OAuth.class);
    }

    @Then("^New access token was generated$")
    public void validateIfNewAccessTokenGenerated() throws Throwable {
        assertThat("Old and new access_token are the same", dataExchanger.getoAuth().getAccess_token(), not(oAuth.getAccess_token()));
        assertThat("Old and new refresh_token are the same", dataExchanger.getoAuth().getRefresh_token(), not(oAuth.getRefresh_token()));
        assertThat("Access token expiry was not reset to one hour", oAuth.getExpires_in(), is(3599));
    }

    @Then("^Previous access and refresh tokens cannot be used$")
    public void checkIfOldRefreshTokenIsDisabled() throws Throwable {
        response = oauthProviderActions.authenticateUserByOauthToken(Config.getOauthProviderAuthorizationHeader(), "refresh_token", dataExchanger.getoAuth().getRefresh_token(), "null", 400);
        errorsValidator.validateOauthErrorResponse(response, "invalid_grant", "Invalid refresh token: " + dataExchanger.getoAuth().getRefresh_token());
    }


    @Then("^Oauth standard format error response will be returned '(.+?)', '(.+?)', '(\\d+)' for '(.+?)'$")
    public void validateOauthTokenErrorResponse(String error, String errorDescription, int status, String grantType) throws Throwable {
        if (status == 400 || grantType.contains("facebook") || grantType.contains("single_use"))
            errorsValidator.validateOauthErrorResponse(response, error, errorDescription);
        else if (status == 401 && !grantType.contains("facebook") && grantType.contains("single_use"))
            errorsValidator.validateErrorResponse(response, status, error, errorDescription);

    }

    @When("^User is authenticating with following data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)' password based grant type for second time with (\\d+)$")
    public void authorizeUserWithOauthForPasswordGrantTypeForSecondTimeWithDelay(String grantType, String username, String password, String authorizationHeader, int status, int delay) throws Throwable {
        Thread.sleep(delay * 1000);
        responseBeforeDelay = response;
        authorizeUserWithOauthForPasswordGrantType(grantType, username, password, authorizationHeader, status);
    }

    @Then("^Difference in oauth response will be only in token expiry time for (\\d+)$")
    public void checkIfOauthResponsesDifferOnlyInExpiryTime(int delay) throws Throwable {
        oauthProviderValidator.checkIfOauthResponsesDifferOnlyInExpiryTime(responseBeforeDelay, response, delay);
    }

    @When("^User is authenticating with following data '(.+?)', '(.+?)', '(\\d+)' single_use_token based grant type$")
    public void authorizeUserWithOauthForSingleUseTokenBasedGrantTypee(String grantType, String token, int status) throws Throwable {
        if (token.equals("dynamic")) {
            response = oauthProviderActions.authenticateUserByOauthPassword("password", Config.mainTestUserEmail, Config.mainTestUserPassword);
            token = response.get("access_token");
            dataExchanger.setEmail(Config.mainTestUserEmail);
            dataExchanger.setoAuth(response.getAsObject(OAuth.class));
        } else if (token.equals("previous_call"))
            token = oAuth.getAccess_token();

        response = oauthProviderActions.authenticateUserByOauthToken(Config.getOauthProviderAuthorizationHeader(), grantType, token, "null", status);
        oAuth = response.getAsObject(OAuth.class);
    }

    @When("^Single use token will not be used in its time of life '(\\d+)'$")
    public void waitUntilTokenWillExpire(int expiryTime) throws Throwable {
        Thread.sleep((expiryTime + 1) * 1000);
    }

    @Then("^Single use token cannot be used after that$")
    public void checkIfExpiredOneTimeTokenIsDisabled() throws Throwable {
        response = oauthProviderActions.tokenCheck(oAuth.getAccess_token(), "null", "null", 400);
        errorsValidator.validateOauthErrorResponse(response, "invalid_token", "Token was not recognised");
    }

    @When("^Single use token will be used in check token request$")
    public void useOneTimeTokenInCheckTokenRequest() throws Throwable {
        response = oauthProviderActions.tokenCheck(oAuth.getAccess_token(), "null", "null", 200);
    }

}