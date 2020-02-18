package com.iat.steps.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.rest.RestActions;
import com.iat.domain.UserDetails;
import com.iat.domain.userProfile.UserProfile;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestSteps {

    private RestActions restActions = new RestActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private ResponseContainer response;
    private ObjectMapper mapper = new ObjectMapper();
    private UserDetails userDetails;

    @Then("^User Profile is requested$")
    public void getUserProfile() throws Throwable {
        if (dataExchanger.getoAuth() != null)
            response = restActions.getUserProfile(dataExchanger.getoAuth().getAccess_token(), 200);
        else
            response = restActions.getUserProfile(200);

        UserProfile userProfile = response.getAsObject(UserProfile.class);
        dataExchanger.setUserProfile(userProfile);

        dataExchanger.setUuid(userProfile.getAccountDetails().getId());
        dataExchanger.setEmail(userProfile.getAccountDetails().getEmail());
    }


    @Then("^User profile contains proper value in businessId field '(.+?)'$")
    public void checkBusinessIdInUserProfile(String businessIdValue) throws Throwable {
        List<String> businessIds = response.getList("accountDetails.businessIds");

        if (businessIdValue.equals("null"))
            assertThat("BusinessIds field should be empty!", businessIds, is(empty()));
        else
            assertThat("incorrect value for first businessId!", businessIds.get(0), is(businessIdValue));
    }


    @When("^User requesting branding call '(.+?)'$")
    public void getBrandingConfig(boolean brandingAvailability) throws Throwable {
        response = restActions.getBrandingConfig(brandingAvailability, 200);
    }

    @Then("^Branding response will be returned '(.+?)'$")
    public void getBrandingConfigMatchContract(boolean brandingAvailability) throws Throwable {

        if (brandingAvailability) {
            assertThat("Incorrect templateUrl field value", response.getString("templateUrl"), is("https://epoints-public-images.s3.amazonaws.com/bupa_epoints.png"));
            assertThat("Incorrect colors.primary field value", response.getString("colors.primary"), is("#0079C8"));
            assertThat("Incorrect colors.secondary field value", response.getString("colors.secondary"), is("#FFFFFF"));
        } else
            assertThat("Branding response should be empty", response.toString(), isEmptyOrNullString());


    }

    @When("^Confirm email url is opened for user '(.+?)'$")
    public void confirmEmailUrlRequest(String email) throws Throwable {

        email = email.contains("@") ? email : dataExchanger.getEmail();

        dataExchanger.setUuid(userRepository.findByEmail(email).getUuid());
        System.out.println("UUID: " + dataExchanger.getUuid());
        dataExchanger.setToken(userRepository.findByUUID(dataExchanger.getUuid()).getToken());
        System.out.println("Token: " + dataExchanger.getToken());

        response = restActions.getEmailVerification(dataExchanger.getToken(), 200);
    }

    @Then("^Confirm email response returns proper data$")
    public void confirmEmailMatchContract() throws Throwable {
        /*assertThat("firstName field have incorrect value!", response.getString("user.firstName"), isEmptyOrNullString());
        assertThat("lastName field have incorrect value!", response.getString("user.lastName"), isEmptyOrNullString());
        assertThat("gender field have incorrect value!", response.getString("user.gender"), isEmptyOrNullString());*/
        String jsonBody = "{firstName=, lastName=, gender=}";
        assertThat("firstName, lastName, gender field have incorrect value!", response.getString("user"), is(jsonBody));

    }

    @Then("^Confirm email response returns proper data for IAT Admin users$")
    public void confirmEmailHaveDataSetInIATAdminMatchContract() throws Throwable {
        assertThat("firstName field have incorrect value!", response.getString("user.firstName"), is("Not"));
        assertThat("lastName field have incorrect value!", response.getString("user.lastName"), is("confirmed - used in epoints-api tests"));
        assertThat("gender field have incorrect value:!", response.getString("user.gender"), is("MALE"));

    }

    @When("^Account is confirmed with details: '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void accountActivation(String password, String firstName, String lastName, String gender) throws Throwable {
        response = restActions.confirmEmail(dataExchanger.getToken(), password, firstName, lastName, gender, 302);
    }

    @When("^Account is confirmed with GDPR details: '(.+?)', '(.+?)'$")
    public void Account_is_confirmed_with_GDPR_details(String jsonBody, int status) throws Throwable{
        userDetails = mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES).readValue( jsonBody , UserDetails.class);
        response = restActions.confirmEmailGDPR(dataExchanger.getToken(), userDetails, status);
    }

    @And("^User does subscription registration process '(.+?)','(.+?)','(.+?)'$")
    public void userDoesSubscriptionRegistrationProcessPlanCoupon(String plan, String coupon, int status) throws  Throwable{
        restActions.subscriptionsRegistration(dataExchanger.getEmail(), plan, coupon, status);
    }
}
