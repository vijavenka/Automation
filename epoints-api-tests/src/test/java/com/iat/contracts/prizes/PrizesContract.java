package com.iat.contracts.prizes;

public class PrizesContract {

    public String spinsPath(String uuid, String status) {
        String path = "/rest/prizes/roulette/spins/count/";

        if (!uuid.equals("null"))
            path += "&uuid=" + uuid;

        if (!status.equals("null"))
            path += "&status=" + status;

        return path.replace("/&", "?");
    }

}