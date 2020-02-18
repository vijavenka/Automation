package com.iat.validators.usersManagement;

import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersListingValidator {

    public void checkIfNumberOfReturnedUsersIsAsExpected(ResponseContainer response, int expectedUsersNumber) {
        assertThat("Users number not as expected in parameter", response.getInt("searchResultsCount"), is(expectedUsersNumber));
        assertThat("Users number not as expected from array size", response.getList("results"), hasSize(expectedUsersNumber));
    }

    public void checkIfUsersDataProperlySorted(ResponseContainer response, String sortField, String order) {
        List<String> dataToSortOrderCheck = response.getList("results." + sortField, String.class);

        for (int i = 0; i < dataToSortOrderCheck.size() - 1; i++) {
            //TODO does not  work for special characters and numbers
            if (dataToSortOrderCheck.get(i) == null) continue;

            if (order.equals("ascending"))
                assertThat(sortField + " values are in incorrect order!", dataToSortOrderCheck.get(i).toLowerCase().compareTo(dataToSortOrderCheck.get(i + 1).toLowerCase()), is(lessThanOrEqualTo(0)));
            else
                assertThat(sortField + " values are in incorrect order!", dataToSortOrderCheck.get(i).toLowerCase().compareTo(dataToSortOrderCheck.get(i + 1).toLowerCase()), is(greaterThanOrEqualTo(0)));
        }
    }

}