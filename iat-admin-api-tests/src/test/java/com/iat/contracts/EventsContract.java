package com.iat.contracts;

public class EventsContract {

    public String getEventsPath(String name, String year, String month, String allEvents, String limit, String offSet){

        String path = "/api/ecards/milestones/" + name + "/details/" + year + "/" + month + "/";

        if(!allEvents.equals("null"))
            path += "&allEvents=" + allEvents;
        if(!offSet.equals("null"))
            path += "&offset=" + offSet;
        if(!limit.equals("null"))
            path += "&limit=" + limit;

        return path.replace("/&","?");
    }

    public String getEventsPath(String name, String allEvents){

        String path = "/api/ecards/milestones/" + name + "/count/";

        if(!allEvents.equals("null"))
            path += "&allEvents=" + allEvents;

        return path.replace("/&","?");
    }
}
