package com.iat.contracts.ecardsSection;

public class EcardsContract {

    public String getEcardsCompanyActivityPath() {
        return "/rest/ecards/activity/company";
    }

    public String getEcardsHistoryPath(String sentOrReceived) {
        return "/rest/ecards/activity/user/" + sentOrReceived;
    }

    public String getIndividualEcard(String ecardId) {
        String path = "/rest/ecards/";

        if (!ecardId.equals("null"))
            path += ecardId;

        return path;
    }

    public String getEcardsUsersPath(String search) {
        String path = "/rest/users";

        if (!search.equals("null"))
            path += "?keyword=" + search + "&fields=uuid,name,email";

        return path;
    }

    public String getReasonsPath() {
        return "/rest/ecards/reasons";
    }

    public String getTemplatesPath() {
        return "/rest/ecards/templates";
    }

    public String getSendEcardPath() {
        return "/rest/ecards";
    }


}