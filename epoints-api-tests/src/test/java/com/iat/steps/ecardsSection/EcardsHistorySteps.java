package com.iat.steps.ecardsSection;

import com.iat.actions.ecardsSection.EcardsHistoryActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;

public class EcardsHistorySteps {

    private EcardsHistoryActions ecardsHistoryActions = new EcardsHistoryActions();
    private ResponseContainer response;


    //Check if user history sent/received ecards messages have proper schema
    @When("^Ecards user calls for ecard history - tab '(.+?)'$")
    public void getEcardsHistory(String historyTab) throws Throwable {
        response = ecardsHistoryActions.getEcardsHistory(historyTab, 200);
    }

    @Then("^User receives message related with Ecard history '(.+?)' page which has proper schema$")
    public void validateEcardsHistoryResponseBasedOnSchema(String historyTab) throws Throwable {
        //controller already has contractValidator
    }

    @Then("^User ecards activity '(.+?)' messages are related with specific user '(.+?)'$")
    public void validateEcardsHistoryMessagesRelatedWithUser(String level, String credentials) throws Throwable {
        String user = credentials.split(",")[0];
        if (level.equals("sent"))
            assertThat("Not every from-email is correct!", response.getList("ecards.from.email"), everyItem(is(user)));
        else
            assertThat("Not every recever-email is correct!", response.getList("ecards.receiver.email"), everyItem(is(user)));
    }


    //Check if user history sent/received ecards messages have proper ecards list order - new on top
    @Then("^Ecards history list in message has descending order$")
    public void validateEcardsHistoryResponseBasedOnEcardsOrder() throws Throwable {
        List<Date> extractedCreatedAt = new ArrayList<>();
        response.getList("ecards.createdAt", Long.class).forEach(createdAt -> extractedCreatedAt.add(new Date(createdAt)));
        //validation for epoch time
        for (int i = 1; i < extractedCreatedAt.size(); ++i)
            assertThat("In Ecards history list dates createAt are incorrectly sorted!", extractedCreatedAt.get(i - 1), sameOrAfter(extractedCreatedAt.get(i)));
    }

    //Check if user without ecards permission cannot pull history activity
    @When("^Ecards user calls for ecard history incorrect data '(.+?)' - tab '(.+?)'$")
    public void getEcardsHistoryError(int status, String level) throws Throwable {
        response = ecardsHistoryActions.getEcardsHistory(level, status);
    }

    @Then("^Ecards are not returned '(\\d+)', '(.+?)' for ecards history$")
    public void checkIfNoEcardsWereReturnedForecardsHist(int status, String message) throws Throwable {
        assertThat("Incorrect status!", response.getInt("status"), is(status));
        assertThat("Incorrect message!", response.getString("message"), is(message));
    }
}
