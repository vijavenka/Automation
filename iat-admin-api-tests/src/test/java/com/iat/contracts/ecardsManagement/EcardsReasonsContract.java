package com.iat.contracts.ecardsManagement;

public class EcardsReasonsContract {

    public String getEcardsReasonsPath() {
        return "/api/ecards/reasons/";
    }

    public String getEcardsReasonsByIdPath(String id) {
        String path = "/api/ecards/reasons/";

        if (!id.equals("null"))
            path += id + "/";

        return path;
    }
}
