package com.iat.contracts.usersManagement;

import java.util.Deque;
import java.util.List;

import static java.util.Arrays.asList;

public class UserTransactionHistoryContract {

    public String getUsersTransactionHistoryForCient(String client, String apiKey, String user, String idType, Deque<String> otherParams) {
        return "/transactions/client/" + client + "/?apiKey=" + apiKey + "&user=" + user + "&idType=" + idType + getRestOfRequestForGetUsersHistory(otherParams, 1);
    }

    private String getRestOfRequestForGetUsersHistory(Deque<String> params, int labelsShifted) {
        String request = "";
        List<String> requestLabels = asList("sortField", "status", "tag", "onBehalfOf", "ascending", "offset", "limit", "startDate", "endDate");
        int i = 0;
        for (String param : params) {
            if (!param.equals("null"))
                request += "&" + requestLabels.get(labelsShifted + i) + "=" + param;
            i++;
        }
        return request;
    }

    public String getUsersTransactionHistoryForGroup(String group, String clientId, String apiKey, String user, String idType, Deque<String> otherParams) {
        return "/transactions/group/" + group + "/?clientId=" + clientId + "&apiKey=" + apiKey + "&user=" + user + "&idType=" + idType + getRestOfRequestForGetUsersHistory(otherParams, 1);
    }

    public String getUsersRewardsHistory(String user, String apiKey, String idType, Deque<String> otherParams) {
        return "/users/" + user + "/rewards/?apiKey=" + apiKey + "&idType=" + idType + getRestOfRequestForGetUsersHistory(otherParams, 4);
    }

    public String getUserTransactionsHistory(String user, String apiKey, String idType, Deque<String> otherParams) {
        return "/transactions/user/" + user + "/?apiKey=" + apiKey + "&idType=" + idType + getRestOfRequestForGetUsersHistory(otherParams, 0);
    }

    public String getLastPointsSet(String apiKey, String user, String idType, String tag) {
        return "/transactions/lastSet/?apiKey=" + apiKey + "&user=" + user + "&idType=" + idType + "&tag=" + tag;
    }

}