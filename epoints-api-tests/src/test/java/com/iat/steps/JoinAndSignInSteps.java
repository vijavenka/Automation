package com.iat.steps;

import com.iat.Config;
import com.iat.actions.JoinAndSignInActions;
import com.iat.actions.UserManagerUsersActions;
import com.iat.actions.rest.RestActions;
import com.iat.domain.UserBalanceDoorman;
import com.iat.domain.UserDetails;
import com.iat.domain.userProfile.UserDetailsDoorman;
import com.iat.domain.userProfile.UserProfile;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.steps.rest.RestSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ErrorValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;

import java.util.Random;

import static com.iat.utils.matchers.CustomMatchers.hasLength;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JoinAndSignInSteps {

    private RestSteps restSteps = new RestSteps();
    private JoinAndSignInActions joinAndSignInActions = new JoinAndSignInActions();
    private RestActions restActions = new RestActions();
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private UserManagerUsersActions userManagerUsersActions = new UserManagerUsersActions();
    private ResponseContainer response;
    private ErrorValidator errorValidator = new ErrorValidator();


    //Scenario Outline: Epoints authorization - login with session ID
    @When("^User is authorizing with following data '(.+?)'$")
    public void ePointsUserLogIn(String credentials) throws Throwable {

        switch (credentials) {
            case "previous_call":
                credentials = dataExchanger.getEmail() + "," + dataExchanger.getPassword();
                break;
            case "epointsUserDefault_1":
                credentials = Config.getEpointsUserDefault_1;
                break;
            case "epointsUserDefault_1_2":
                credentials = Config.getEpointsUserDefault_1_2;
                break;
            case "epointsUserDefault_2":
                credentials = Config.getEpointsUserDefault_2;
                break;
            case "epointsUserDefaultChanged_1":
                credentials = Config.getEpointsUserDefaultChanged_1;
                break;
            case "unitedUserDefault_1":
                credentials = Config.getUnitedUserDefault_1;
                break;
            case "unitedUserDefault_1_2":
                credentials = Config.getUnitedUserDefault_1_2;
                break;
            case "unitedUserDefaultChanged_1":
                credentials = Config.getUnitedUserDefaultChanged_1;
                break;
            case "newsUserDefault_1":
                credentials = Config.getNewsUserDefault_1;
                break;
        }

        if (credentials.contains("previousCallEmail"))
            credentials = dataExchanger.getEmail() + "," + credentials.split(",")[1];

        sessionId.set(joinAndSignInActions.getSessionIdForAuthUser(credentials));

        restSteps.getUserProfile();
    }

    @Then("^Login process should return Session ID and loginSuccess response$")
    public void ePointsUserLogInResponse() throws Throwable {
        assertThat("SessionId is null", sessionId.get(), not(isEmptyOrNullString()));
    }

    @When("^Join epoints request is triggered with '(.+?)'$")
    public void joinEpoints(String email) throws Throwable {
        if (!email.equals("previous_call"))
            dataExchanger.setEmail(email.contains("@") ? email : email + new DateTime().getMillis() + "@epoints.com");
        joinAndSignInActions.joinEpoints(dataExchanger.getEmail(), 200);
    }

    @When("^Email '(.+?)' Join standard epoints and verify account$")
    public void joinEpointsAndVerify(String login) throws Throwable {
        joinEpoints(login);
        dataExchanger.setUuid(userRepository.findByEmail(dataExchanger.getEmail()).getUuid());
        dataExchanger.setToken(userRepository.findByUUID(dataExchanger.getUuid()).getToken());
        restActions.confirmEmail(dataExchanger.getToken(), "password", "API auto", "Default account", "male", 302);
        dataExchanger.setPassword("password");
    }

    @When("^Join epoints request with '(.+?)' for business '(.+?)' with externalId '(.+?)' is triggered '(\\d+)'$")
    public void joinEpointsForSpecifiedBusinessType(String email, String businessType, String externalId, int status) throws Throwable {
        if (!email.equals("previous_call"))
            dataExchanger.setEmail(email.contains("@") ? email : email + new DateTime().getMillis() + "@epoints.com");

        dataExchanger.setExternalId(externalId.equalsIgnoreCase("random") ? String.valueOf(new Random().nextInt(99999)) : externalId);

        response = joinAndSignInActions.joinEpointsForSpecifiedBusiness(dataExchanger.getEmail(), businessType, dataExchanger.getExternalId(), status);
    }

    @When("^Join epoints request with '(.+?)' for business '(.+?)' with externalId '(.+?)' and verify account$")
    public void joinEpointsForSpecifiedBusinessTypeAndVerify(String email, String businessType, String externalId) throws Throwable {
        joinEpointsForSpecifiedBusinessType(email, businessType, externalId, 200);
        dataExchanger.setUuid(userRepository.findByEmail(dataExchanger.getEmail()).getUuid());
        dataExchanger.setToken(userRepository.findByUUID(dataExchanger.getUuid()).getToken());
        restActions.confirmEmail(dataExchanger.getToken(), "password", "API auto", "Default account", "male", 302);
        dataExchanger.setPassword("password");
    }


    @When("^Join epoints for business '(.+?)' response returns proper status '(.+?)'$")
    public void validationForJoinBusinessStatusInResponse(String businessType, String status) throws Throwable {
        if (businessType.equalsIgnoreCase("united"))
            assertThat("Improper Join response status", response.getString("status"), is(status));
    }


    @Then("^Account is created with correct registrationSiteId, virtualGroup, externalId for business '(.+?)', '(.+?)'$")
    public void validate(String businessType, String initialRegistrationSiteName) throws Throwable {
        UserDetails userDetails = userManagerUsersActions.getUserAccountDetails(dataExchanger.getEmail(), "EMAIL", "accessKey", 200)
                .getAsObject(UserDetails.class);
        dataExchanger.setUuid(userDetails.getUuid());

        if (initialRegistrationSiteName.equals("united")) {
            assertThat("Wrong externalIdUnited!", userDetails.getExternalIdUnited(), is(dataExchanger.getExternalId()));
            assertThat("Wrong registration site name!", userDetails.getRegistrationSiteName(), is("United Retailers"));
        } else
            assertThat("Wrong registration site name!", userDetails.getRegistrationSiteName(), is("ePoints"));

        if (businessType.equals("united")) {
            boolean groupFound = false;
            for (int i = 0; i < userDetails.getUserGroups().size() && !groupFound; i++)
                groupFound = userDetails.getUserGroups().get(i).getExternalIdType() != null && userDetails.getUserGroups().get(i).getExternalIdType().equals("united");
            assertThat("United group was not found in user details!", groupFound);
        }
    }

    @Then("^Epoints and business '(.+?)' balances are as expected$")
    public void checkBalancesForBusinessAccount(String businessType) throws Throwable {
        UserBalanceDoorman epointsBalance = userRepository.findBalanceByUserId(dataExchanger.getUuid());
        assertThat("Incorrect epoints balance", epointsBalance.getRedeemed() == 0 && epointsBalance.getConfirmed() == 50 && epointsBalance.getDeclined() == 0 && epointsBalance.getPending() == 0 && epointsBalance.getSpent() == 0);
        if (businessType.equals("united")) {
            UserBalanceDoorman unitedBalance = userRepository.findBalanceByUserIdForSpecifiedBusiness(dataExchanger.getUuid(), businessType);
            assertThat("Incorrect united balance", unitedBalance.getRedeemed() == 0 && unitedBalance.getConfirmed() == 0 && unitedBalance.getDeclined() == 0 && unitedBalance.getPending() == 0 && unitedBalance.getSpent() == 0);
        }
    }

    @Then("^Unsubscribed field in user profile is set by default to '(true|false)'$")
    public void checkValueOfUnsubscribedFieldInUserDetails(boolean unsubscribed) throws Throwable {
        UserDetails userDetails = userManagerUsersActions.getUserAccountDetails(dataExchanger.getEmail(), "EMAIL", "accessKey", 200)
                .getAsObject(UserDetails.class);
        assertThat("Wrong value of unsubscribed field in user details", userDetails.isUnsubscribed(), is(unsubscribed));
    }

    @Then("^Subscribed to marketing details field is set by default to '(true|false)' in user profile$")
    public void checkValueOfSubscribedToMarketingDetailsFieldInUserProfile(boolean subscribedToMarketingDetails) throws Throwable {
        UserProfile userProfile = restActions.getUserProfile().getAsObject(UserProfile.class);
        assertThat("Wrong value of unsubscribed field in user details", userProfile.getConsentDetails().isSubscribedToMarketingEmails(), is(subscribedToMarketingDetails));
    }

    @Then("^Correct united account creation error message will be returned '(.+?)', '(.+?)'$")
    public void validateErrorResponse(String error, String message) throws Throwable {
        assertThat("Wrong error ", response.getString("error"), is(error));
        assertThat("Wrong message ", response.getString("message"), is(message));
    }

    @When("^Join mobile epoints request is triggered with '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void joinEpointsMobile(String email, String password, String firstName, String lastName, int status) throws Throwable {
        email = email.equals("epointsUserDefault_1") ? Config.getEpointsUserDefault_1 : email;
        dataExchanger.setEmail(email.contains("@") ? email : email + new DateTime().getMillis() + "@epoints.com");
        response = joinAndSignInActions.joinEpointsMobile(dataExchanger.getEmail(), firstName, lastName, password, status);
        if (status == 200) {
            dataExchanger.setUuid(userRepository.findByEmail(dataExchanger.getEmail()).getUuid());
            dataExchanger.setUserDetailsDoorman(new UserRepositoryImpl().findDetailsById(dataExchanger.getUuid()).getAsObject(UserDetailsDoorman.class));
        }
    }

    @Then("^Created account is active: true, verified: false$")
    public void checkAccountVerificationAndActiveStatus() throws Throwable {
        assertThat("Wrong activation status", dataExchanger.getUserDetailsDoorman().getActive());
        assertThat("Wrong verification status", !dataExchanger.getUserDetailsDoorman().getVerified());
    }

    @Then("^Created account has properly saved provided data '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void checkAccountPersonalDataCorrectness(String email, String password, String firstName, String lastName) throws Throwable {
        assertThat("Wrong user name", dataExchanger.getUserDetailsDoorman().getFirstName(), is(firstName));
        assertThat("Wrong user last name", dataExchanger.getUserDetailsDoorman().getLastName(), is(lastName));
        assertThat("Password not set", dataExchanger.getUserDetailsDoorman().getPassword(), allOf(
                not(isEmptyOrNullString()),
                hasLength(greaterThan(8))));
        assertThat("Must change password has incorrect value", !dataExchanger.getUserDetailsDoorman().getMustChangePassword());
    }

    @Then("^Created account belongs only to epoints group$")
    public void checkIfEpointsIsUserOnlyVirtualGroup() throws Throwable {
        assertThat("Wrong number of virtual groups", dataExchanger.getUserDetailsDoorman().getUserGroupDoormen(), hasSize(1));
        assertThat("Wrong number of virtual groups", dataExchanger.getUserDetailsDoorman().getUserGroupDoormen().get(0).getPartnerId(), is(2));
    }

    @Then("^Correct mobile account creation error message will be returned '(\\d+)', '(.+?)', '(.+?)'$")
    public void validateErrorResponseOfMobileAccontCreation(int status, String error, String message) throws Throwable {
        errorValidator.validateErrorResponse(response, status, error, message);
    }

}