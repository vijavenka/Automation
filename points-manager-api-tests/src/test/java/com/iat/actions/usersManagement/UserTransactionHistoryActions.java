package com.iat.actions.usersManagement;

import com.iat.controller.usersManagement.UserTransactionHistoryController;
import com.iat.utils.ResponseContainer;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.util.Arrays.asList;

public class UserTransactionHistoryActions {

    private UserTransactionHistoryController userTransactionHistoryController = new UserTransactionHistoryController();

    public ResponseContainer getUserTransactionHistoryByClient(String requestParameters, int status) {
        Deque<String> params = new ArrayDeque<>(asList(requestParameters.split(",")));
        String client = params.pollFirst();
        String apiKey = params.pollFirst();
        String user = params.pollFirst();
        String idType = params.pollFirst();

        return initResponseContainer(userTransactionHistoryController.getUserTransactionHistoryByClient(client, apiKey, user, idType, params, status));
    }

    public ResponseContainer getUserTransactionHistoryByGroup(String requestParameters, int status) {
        Deque<String> params = new ArrayDeque<>(asList(requestParameters.split(",")));
        String group = params.pollFirst();
        String clientId = params.pollFirst();
        String apiKey = params.pollFirst();
        String user = params.pollFirst();
        String idType = params.pollFirst();

        return initResponseContainer(userTransactionHistoryController.getUserTransactionHistoryByGroup(group, clientId, apiKey, user, idType, params, status));
    }

    public ResponseContainer getUserRewardsHistory(String requestParameters, int status) {
        Deque<String> params = new ArrayDeque<>(asList(requestParameters.split(",")));
        String apiKey = params.pollFirst();
        String user = params.pollFirst();
        String idType = params.pollFirst();

        return initResponseContainer(userTransactionHistoryController.getUserRewardsHistory(apiKey, user, idType, params, status));
    }

    public ResponseContainer getUserTransactionHistory(String requestParameters, int status) {
        Deque<String> params = new ArrayDeque<>(asList(requestParameters.split(",")));
        String user = params.pollFirst();
        String apiKey = params.pollFirst();
        String idType = params.pollFirst();

        return initResponseContainer(userTransactionHistoryController.getUserTransactionHistory(user, apiKey, idType, params, status));
    }

    public ResponseContainer getUserLastTransactionHistory(String requestParameters, int status) {
        String[] params = requestParameters.split(",");
        String apiKey = params[0];
        String user = params[1];
        String idType = params[2];
        String tag = params[3];

        return initResponseContainer(userTransactionHistoryController.getUserLastTransactionHistory(apiKey, user, idType, tag, status));
    }

}