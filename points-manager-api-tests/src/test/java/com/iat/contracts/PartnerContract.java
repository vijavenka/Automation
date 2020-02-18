package com.iat.contracts;

public class PartnerContract {

    public String getPartnersPath() {

        return "/clients";
    }

    public String getPartnersPostBody(String name, String siteURL, String description, String email, String logoURL, String group) {

        String jsonPost = "";

        if (!siteURL.equals("null"))
            jsonPost += "\"siteUrl\":\"" + siteURL + "\",";
        if (!description.equals("null"))
            jsonPost += "\"description\":\"" + description + "\",";
        if (!email.equals("null"))
            jsonPost += "\"email\":\"" + email + "\",";
        if (!logoURL.equals("null"))
            jsonPost += "\"logoUrl\":\"" + logoURL + "\",";
        if (!group.equals("null"))
            jsonPost += "\"group\":\"" + group + "\",";
        if (!name.equals("null"))
            jsonPost += "\"name\":\"" + name + "\"";

        return "{" + jsonPost + "}";
    }

    public String getPartnerTransactionsPath(String apiKey) {
        String path = "/transactions/";
        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;
        return path.replace("/&", "?");
    }

    public String getPartnersSiteNotVisitedPath(String userId, String idType, String apiKey, String limit, String offset, String random) {

        String path = "/users/" + userId + "/partnersNotVisited";

        if (!idType.equals("null")) {
            path += "?idType=" + idType;

            if (!apiKey.equals("null")) {
                path += "&apiKey=" + apiKey;

                if (!limit.equals("null"))
                    path += "&limit=" + limit;
                if (!offset.equals("null"))
                    path += "&offset=" + offset;
                if (!random.equals("null"))
                    path += "&random=" + random;
            }
        }
        return path;
    }

    public String getPartnerBalancePath(String clientId, String apiKey, String fields, String startDate, String endDate) {

        String path = "/clients/" + clientId + "/";

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!fields.equals("null")) {
                path += "&fields=" + fields;
                if (!startDate.equals("null"))
                    path += "&startDate=" + startDate;
                if (!endDate.equals("null"))
                    path += "&endDate=" + endDate;
            }


        }

        return path;
    }

    public String getPartnersImportPath(String groupShortName, String googleDocId) {
        String path = "/import/partners/";

        if (!groupShortName.equals("null"))
            path += "&groupShortName=" + groupShortName;
        if (!googleDocId.equals("null"))
            path += "&googleDocId=" + googleDocId;
        return path.replace("/&", "/?");
    }

}