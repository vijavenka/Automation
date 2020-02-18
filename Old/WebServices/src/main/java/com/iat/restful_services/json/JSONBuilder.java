package com.iat.restful_services.json;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 23.05.13
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */


public class JSONBuilder {

    public String buildAddPointsJSON(int pnts, String apiaccesskey, String userid, String tagname, String pointsstatus, String externaltransactionid, String assignerreference, String activityinfo) {

        String json = "{\"points\":" + pnts +
                        ", \"apiAccessKey\":\"" + apiaccesskey +
                        "\", \"userId\":\"" + userid +
                        "\", \"tagName\":\"" + tagname +
                        "\", \"pointsStatus\":\"" + pointsstatus +
                        "\", \"externalTransactionId\":\"" + externaltransactionid +
                        "\", \"assignerReference\":\"" + assignerreference +
                        "\", \"activityInfo\":\"" + activityinfo +
                        "\"}";

        return json;
    }
}
