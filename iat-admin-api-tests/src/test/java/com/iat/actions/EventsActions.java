package com.iat.actions;

import com.iat.controller.EventsController;
import com.iat.utils.ResponseContainer;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class EventsActions {

    private EventsController eventsController = new EventsController();

    public ResponseContainer getEventsAllocations(String uriParams, String queryParams, int status) {
        String[] params1 = uriParams.split(",");
        String name = params1[0];
        String year = params1[1];
        String month = params1[2];

        String[] params2 = queryParams.split(",");
        String allEvents = params2[0];
        String limit = params2[1];
        String offSet = params2[2];

        return initResponseContainer(eventsController.getEventsAllocations(name, year, month, allEvents,
                limit, offSet, status));
    }

    public ResponseContainer getEventsAllocationsCount(String name, String allEvents, int status) {
         return initResponseContainer(eventsController.getEventsAllocationsCount(name, allEvents, status));
    }
}
