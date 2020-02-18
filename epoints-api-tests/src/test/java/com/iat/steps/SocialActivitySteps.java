package com.iat.steps;

import com.iat.actions.SocialActivityActions;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SocialActivitySteps {

    private SocialActivityActions socialActivityActions = new SocialActivityActions();
    private UsersActions usersActions = new UsersActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;

    @Given("^Facebook like status for user is set to (.+?)$")
    public void checkFacebookLikeStatusForUser(String status) throws Throwable {
        response = socialActivityActions.getSocialStatus(200);
        assertThat("Wrong status for facebook social activity", response.getString("facebook"), is(status));
    }

    @When("^User like epoints on facebook$")
    public void likeEpointsOnFacebook() throws Throwable {
        response = socialActivityActions.likeEpointsOnFacebook(200);
    }

    @Then("^User will be awarded for (.+?) epoints on (.+?) with (\\d+) points$")
    public void checkIfUserWasAwardedForSocialActivity(String activity, String platform, int awardedPointsValue) throws Throwable {
        UserBalance userBalance = usersActions.getUserBalance("null", 200).getAsObject(UserBalance.class);
        assertThat("User did not received 10 points for liking epoints omn facebook", userBalance.getConfirmedPoints(), is(dataExchanger.getUserBalance().getConfirmedPoints() + awardedPointsValue));
    }

    @Then("^User cannot be awarded twice for (.+?) epoints on (.+?)$")
    public void checkIfUserWasNotAwardedTwiceForSocialActivity(String activity, String platform) throws Throwable {
        if (platform.equals("facebook"))
            response = socialActivityActions.likeEpointsOnFacebook(400);
        else if (platform.equals("twitter"))
            response = socialActivityActions.followEpointsOnTwitter(400);
        assertThat("Wrong  message for second try of liking facebook", response.getString("message"), is("User was already rewarded for this activity"));
        assertThat("Wrong error message for second try of liking facebook", response.getString("error"), is("Bad Request"));
    }

    @Given("^Twitter follow status for user is set to (.+?)$")
    public void checkTwitterStatusForUser(String status) throws Throwable {
        response = socialActivityActions.getSocialStatus(200);
        assertThat("Wrong status for facebook social activity", response.getString("facebook"), is(status));
    }

    @When("^User follow epoints on twitter$")
    public void followEpointsOnTwitter() throws Throwable {
        response = socialActivityActions.followEpointsOnTwitter(200);
    }

}