package com.iat.steps;

import com.iat.actions.EventsActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.When;

public class EventsSteps {

    private EventsActions eventsActions = new EventsActions();

    private ResponseContainer response;

    @When("^Get list of birthday or aniversary '(.+?)' '(.+?)'$")
    public void Get_list_of_birthday_or_aniversary(String uriParams, String queryParams) throws Throwable {
        response = eventsActions.getEventsAllocations(uriParams, queryParams, 200);

    }

    @When("^Get count of birthday or aniversary '(.+?)' '(.+?)'$")
    public void get_count_of_birthday_or_aniversary(String name, String allEvents) throws Throwable {
        response = eventsActions.getEventsAllocationsCount(name, allEvents, 200);
    }
}
