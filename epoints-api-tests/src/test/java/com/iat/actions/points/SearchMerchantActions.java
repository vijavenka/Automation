package com.iat.actions.points;

import com.iat.controller.points.SearchMerchantController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class SearchMerchantActions {

    private SearchMerchantController searchMerchantController = new SearchMerchantController();

    public ResponseContainer getSearchMerchant(String params, int status) {
        String[] params2 = params.split(";");
        String keyword = params2[0];
        String page = params2[1];
        String pageSize = params2[2];
        String departments = params2[3];
        String prefixes = params2[4];
        String favourite = params2[5];

        return initResponseContainer(searchMerchantController.getSearchMerchant(keyword, page, pageSize, departments, prefixes, favourite, status));
    }

    public ResponseContainer getSearchMerchant(String params) {
        return getSearchMerchant(params, 200);
    }

    public ResponseContainer getSearchMerchantForLoggedUser(String params, int status) {
        String[] params2 = params.split(";");
        String keyword = params2[0];
        String page = params2[1];
        String pageSize = params2[2];
        String departments = params2[3];
        String prefixes = params2[4];
        String favourite = params2[5];

        return initResponseContainer(searchMerchantController.getSearchMerchantForLoggedUser(keyword, page, pageSize, departments, prefixes, favourite, status));
    }

    public ResponseContainer getSearchMerchantForLoggedUser(String params) {
        return getSearchMerchantForLoggedUser(params, 200);
    }

    public ResponseContainer getDepartmentsUndecorated(int status) {
        return initResponseContainer(searchMerchantController.getDepartmentsUndecorated(status));
    }

    public ResponseContainer getMerchantsAboutUsPage(int status) {
        return initResponseContainer(searchMerchantController.getMerchantsAboutUsPage(status));
    }

}