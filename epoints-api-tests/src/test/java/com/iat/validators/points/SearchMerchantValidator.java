package com.iat.validators.points;

import com.iat.Config;
import com.iat.actions.points.SearchMerchantActions;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.util.HashMap;
import java.util.List;

import static com.iat.utils.matchers.CustomMatchers.containsStringIgnoringCase;
import static com.iat.utils.matchers.CustomMatchers.startsWithIgnoringCase;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchMerchantValidator {

    private SearchMerchantActions searchMerchantActions = new SearchMerchantActions();
    private JdbcDatabaseConnector mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAdminPortal);

    public void validateDataCorrectnessOfSearchMerchantResponse(String params, ResponseContainer response) {
        String[] params2 = params.split(";");
        String keyword = params2[0];
        String page = params2[1];
        String pageSize = params2[2];
        String department = params2[3];
        String prefixes = params2[4];
        //TODO handle favourites if needed, there is separate test for that
        //If yes wee need to user search merchant call for logged user
        String favourites = params2[5];

        if (!keyword.equals("null")) {
            List<String> extractedMerchantName = response.getList("merchants.name");
            assertThat("All merchants should contain: " + keyword + " in name!", extractedMerchantName, everyItem(containsStringIgnoringCase(keyword)));
        }

        if (!page.equals("null")) {
            List<String> extractedMerchantNameWithOffset = response.getList("merchants.name");

            ResponseContainer localResponse = searchMerchantActions.getSearchMerchant(keyword + ";0;" + Integer.parseInt(pageSize) * Integer.parseInt(page) * 2 + ";" + department + ";" + prefixes + ";null");
            List<String> extractedMerchantNameWithoutOffset = localResponse.getList("merchants.name");

            for (int i = 0; i < 5; i++)
                assertThat("Comparision products with offset and without it failed", extractedMerchantNameWithoutOffset.get((Integer.parseInt(pageSize) * Integer.parseInt(page)) + i), is(extractedMerchantNameWithOffset.get(i)));
        }

        if (!pageSize.equals("null")) {
            List<String> extractedMerchantName = response.getList("merchants.name");

            assertThat("Wrong number of returned merchants!", extractedMerchantName, hasSize(Integer.parseInt(pageSize)));
        }

        if (!department.equals("null")) {
            List<String> extractedMerchantIds = response.getList("merchants.id");

            for (String id : extractedMerchantIds) {
                HashMap<Integer, List> rs = mySQLConnector.getResult("SELECT friendlyUrl FROM admin_ui.StoreCategory storeCategory INNER JOIN admin_ui.Merchant_StoreCategory merchantStoreCategory ON storeCategory.id = merchantStoreCategory.store_category_id WHERE merchantStoreCategory.merchant_id = '" + id + "' AND storeCategory.type = 'Department'", singletonList("friendlyUrl"));
                boolean departmentFound = false;
                for (int i = 0; i < rs.size() && !departmentFound; i++)
                    departmentFound = rs.get(i).get(0).equals(department);
                assertThat("Department: " + department + " not found for merchant: " + id, departmentFound);
            }


        }

        if (!prefixes.equals("null")) {
            List<String> extractedMerchantName = response.getList("merchants.name");
            assertThat("All merchants names should start with proper prefix!", extractedMerchantName, everyItem(startsWithIgnoringCase(prefixes)));
        }
    }

}