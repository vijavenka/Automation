package com.iat.contracts;

public class MilestonesContract {

    public String getMilestonesPath(String milestoneType) {
        String path = "/api/ecards/milestones";

        if (!milestoneType.equals("null"))
            path += "/" + milestoneType;

        return path;
    }

    public String getMilestonesByIdPath(String milestoneType, String milestoneId) {
        String path = "/api/ecards/milestones";

        if (!milestoneType.equals("null"))
            path += "/" + milestoneType;

        if (!milestoneId.equals("null"))
            path += "/" + milestoneId;

        return path;
    }

    public String getUsersListForMilestonesWithTypePath(String milestoneType, String allEvents) {
        String path = "/api/ecards/milestones";

        if (!milestoneType.equals("null"))
            path += "/" + milestoneType;

        path += "/list";

        if (!allEvents.equals("null"))
            path += "?allEvents=" + allEvents;

        return path;
    }
}