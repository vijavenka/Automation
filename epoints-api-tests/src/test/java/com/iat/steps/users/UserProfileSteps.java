package com.iat.steps.users;

import com.iat.Config;
import com.iat.actions.UserManagerUsersActions;
import com.iat.actions.rest.RestActions;
import com.iat.domain.UserDetails;
import com.iat.domain.UserToken;
import com.iat.domain.userProfile.UserProfile;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.repository.impl.UserTokenRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.users.UserProfileValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserProfileSteps {

    private ResponseContainer response;
    private UserProfile userProfile;
    private RestActions restActions = new RestActions();
    private UserProfileValidator userProfileValidator = new UserProfileValidator();
    private UserManagerUsersActions userManagerUsersActions = new UserManagerUsersActions();
    private UserRepository userRepository = new UserRepositoryImpl();
    private String newUserEmail;
    private String oldUserEmail;

    private DataExchanger dataExchanger = DataExchanger.getInstance();

    @When("^Personal details will be changed to '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void changeUserPersonalDetails(String firstName, String lastName, String title, String gender, String dob, int status) throws Throwable {
        if (dataExchanger.getoAuth() != null)
            restActions.savePersonalDetails(firstName, lastName, title, gender, dob, dataExchanger.getoAuth().getAccess_token(), status);
        else
            restActions.savePersonalDetails(firstName, lastName, title, gender, dob, status);
    }

    @Then("^User personal details will be properly changed to '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void validateChangedUserPersonalDetailsDataCorrectness(String firstName, String lastName, String title, String gender, String dob) throws Throwable {
        if (dataExchanger.getoAuth() != null)
            userProfile = restActions.getUserProfile(dataExchanger.getoAuth().getAccess_token(), 200).getAsObject(UserProfile.class);
        else
            userProfile = restActions.getUserProfile().getAsObject(UserProfile.class);
        userProfileValidator.checkIfPersonalDetailsWereProperlyChangedInUserProfile(firstName, lastName, title, gender, dob, userProfile);
    }

    @Then("^All associated with '(.+?)' account virtual groups were properly updated with data '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void validateIfUserVirtualGroupsWereProperlyUpdated(String businessId, String firstName, String lastName, String gender, String dob) throws Throwable {
        response = userManagerUsersActions.getUserAccountDetails(dataExchanger.getEmail(), "EMAIL", "accessKey", 200);
        UserDetails userDetails = response.getAsObject(UserDetails.class);
        userProfileValidator.validateIfUserVirtualGroupsWereProperlyUpdated(businessId, firstName, lastName, gender, dob, userDetails);
    }

    @When("^Contact details will be changed to '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void changeUserContactDetails(String phoneNo, String house, String street, String city, String county, String country, String postCode, int status) throws Throwable {
        restActions.saveContactDetails(phoneNo, house, street, city, county, country, postCode, status);
    }

    @Then("^User contact details will be properly changed to '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void validateChangedUserContactDetailsDataCorrectness(String phoneNo, String house, String street, String city, String county, String country, String postCode) throws Throwable {
        userProfile = restActions.getUserProfile().getAsObject(UserProfile.class);
        userProfileValidator.checkIfContactDetailsWereProperlyChangedInUserProfile(phoneNo, house, street, city, county, country, postCode, userProfile);
        //we can check also dynamo data here but I think there is no point
    }

    @When("^Email change will be requested for '(.+?)', '(.+?)', '(\\d+)'$")
    public void requestChangeOfEmail(String newEmail, String oldEmail, int status) throws Throwable {
        setCorrectEmail(newEmail, oldEmail);
        restActions.saveNewEmail(newUserEmail, status);
    }

    @Then("^New email will not be set before verification '(.+?)'$")
    public void checkIfNewEmailNotSetBeforeVerification(String oldEmail) throws Throwable {
        setCorrectEmail("", oldEmail);
        UserProfile userProfile = restActions.getUserProfile().getAsObject(UserProfile.class);
        assertThat("User email was change before verification.", oldUserEmail, is(equalTo(userProfile.getAccountDetails().getEmail())));
    }

    @Then("^New email will be set after verification '(.+?)', '(.+?)'$")
    public void checkIfNewEmailSetAfterVerification(String newEmail, String oldEmail) throws Throwable {
        setCorrectEmail(newEmail, oldEmail);
        String uuid = userRepository.findByEmail(oldUserEmail).getUuid();
        Thread.sleep(2000);
        List<UserToken> tokens = new UserTokenRepositoryImpl().getTokens(uuid);
        String token = tokens.get(0).getToken();
        restActions.activateNewEmail(token, 200);
    }

    @Then("^All associated with '(.+?)' account virtual groups were properly updated with email '(.+?)'$")
    public void validateIfUserVirtualGroupsWereProperlyUpdated(String businessId, String newEmail) throws Throwable {
        setCorrectEmail(newEmail, "");
        response = userManagerUsersActions.getUserAccountDetails(newUserEmail, "EMAIL", "accessKey", 200);
        UserDetails userDetails = response.getAsObject(UserDetails.class);
        userProfileValidator.validateIfUserVirtualGroupsWereProperlyUpdated(businessId, newUserEmail, userDetails);
    }

    private void setCorrectEmail(String email, String oldEmail) {
        newUserEmail = email;
        oldUserEmail = oldEmail;
        if (email.equals("epointsUserDefaultChanged_1"))
            newUserEmail = Config.getEpointsUserDefaultChanged_1.split(",")[0];
        else if (email.equals("epointsUserDefault_1"))
            newUserEmail = Config.getEpointsUserDefault_1.split(",")[0];
        else if (email.equals("unitedUserDefaultChanged_1"))
            newUserEmail = Config.getUnitedUserDefaultChanged_1.split(",")[0];
        else if (email.equals("unitedUserDefault_1"))
            newUserEmail = Config.getUnitedUserDefault_1.split(",")[0];

        if (oldEmail.equals("epointsUserDefaultChanged_1"))
            oldUserEmail = Config.getEpointsUserDefaultChanged_1.split(",")[0];
        else if (oldEmail.equals("epointsUserDefault_1"))
            oldUserEmail = Config.getEpointsUserDefault_1.split(",")[0];
        else if (oldEmail.equals("unitedUserDefaultChanged_1"))
            oldUserEmail = Config.getUnitedUserDefaultChanged_1.split(",")[0];
        else if (oldEmail.equals("unitedUserDefault_1"))
            oldUserEmail = Config.getUnitedUserDefault_1.split(",")[0];
    }

    @When("^Password change will be requested for '(.+?)', '(.+?)', '(\\d+)'$")
    public void changeUserPassword(String newPassword, String currentPassword, int status) throws Throwable {
        restActions.changeUserPassword(currentPassword, newPassword, status);
    }

    @Then("^User will get (\\d+) points for (.+?)$")
    public void checkBalanceAfterPersonalContactDetailsUpdate(int addedPointsValue, String action) throws Throwable {
        userProfileValidator.checkIfUserBalanceWasUpdated(addedPointsValue);
    }

    @When("^Consent details will be changed to '(true|false)'$")
    public void changeUserConsentDetails(boolean subscribedToMarketingEmails) throws Throwable {
        restActions.saveConsentDetails(subscribedToMarketingEmails, 200);
    }

    @Then("^User consent details will be properly changed to '(true|false)'$")
    public void validateChangedUserConsentDetailsDataCorrectness(boolean subscribedToMarketingEmails) throws Throwable {
        userProfile = restActions.getUserProfile().getAsObject(UserProfile.class);
        userProfileValidator.checkIfConsentDetailsWereProperlyChangedInUserProfile(subscribedToMarketingEmails, userProfile);
    }

}