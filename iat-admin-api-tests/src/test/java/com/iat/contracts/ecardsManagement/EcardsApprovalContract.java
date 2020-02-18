package com.iat.contracts.ecardsManagement;

/**
 * Created by miwanczyk on 2016-01-20.
 */
public class EcardsApprovalContract {

    public String getEcardsApprovalListPath() {
        return "/api/ecards/approval";
    }

    public String getEcardsApprovalByIdPath(String id) {
        String path = "/api/ecards/approval/";

        if (!id.equals("null"))
            path += id;

        return path;
    }

    public String setEcardsApprovalAsApprovedPath(String id) {
        String path = "/api/ecards/approval/";

        if (!id.equals("null"))
            path += id + "/";

        return path + "approve";
    }

    public String setEcardsApprovalAsRejectedPath(String id) {
        String path = "/api/ecards/approval/";

        if (!id.equals("null"))
            path += id + "/";

        return path + "reject";
    }

    public String getEcardsApprovalCounterPath() {
        return "/api/ecards/approval/counter";
    }
}
