package com.iat.validators.Rewards;

import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class RecentlyRedeemedValidator {

    public void checkIfRecentlyRedeemedProductsAreFromSelectedDepartment(ResponseContainer response, String departmentSeoSlug) {
        List<String> extractedDepartmentSoeSlugs = response.getList("results.departmentSeoSlug");
        assertThat(extractedDepartmentSoeSlugs, hasItem(departmentSeoSlug));
    }

}