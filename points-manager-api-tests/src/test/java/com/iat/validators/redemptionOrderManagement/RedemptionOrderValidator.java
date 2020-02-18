package com.iat.validators.redemptionOrderManagement;

import com.iat.domain.orderRedemption.Order;
import com.iat.utils.ResponseContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.iat.utils.DateTimeUtil.*;
import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RedemptionOrderValidator {

    public void checkUserBalanceAfterRedemptionOrder(ResponseContainer oldBalance, ResponseContainer currentBalance, Order order) {

        //function prepared for validation changes in balance

        int previousConfirmed = oldBalance.getInt("confirmedPoints");
        int previousRedeemed = oldBalance.getInt("redeemedPoints");

        int confirmed = currentBalance.getInt("confirmedPoints");
        int redeemed = currentBalance.getInt("redeemedPoints");

        int numPoints = order.getProducts().get(0).getNumPoints();
        int quantity = order.getProducts().get(0).getQuantity();

        int orderPointsSum = numPoints * quantity;

        assertThat("Confirmed points are incorrect!", confirmed, is(previousConfirmed - orderPointsSum));
        assertThat("Redeemed points are incorrect!", redeemed, is(previousRedeemed + orderPointsSum));

    }

    public void checkRedemptionHistoryCorrectness(String params, ResponseContainer response) {
        //TODO validation for response data
        String[] params2 = params.split(",");
        String ascending = params2[3];
        String limit = params2[5];
        String startDate = params2[6];
        String endDate = params2[7];

        //Limit validation
        if (!limit.equals("null")) {
            assertThat("Results count is different than provided limit", response.getList("rewards"), hasSize(lessThanOrEqualTo(Integer.parseInt(limit))));
            assertThat("searchResultsCount field value is different than provided limit", response.getInt("searchResultsCount"), is(lessThanOrEqualTo(Integer.parseInt(limit))));
        }

        //startDate endDate validation
        List<Date> extractedCreatedAt = new ArrayList<>();
        response.getList("rewards.createdAt", Long.class).forEach(it -> extractedCreatedAt.add(new Date(it)));

        if (!startDate.equals("null")) {
            Date providedStartDate;
            if (startDate.contains("-"))
                providedStartDate = convertToDate(startDate, Format.dd_MM_yyyy, true);
            else
                providedStartDate = new Date(Long.parseLong(startDate));
            System.out.print("START DATE: " + providedStartDate);

            assertThat("All dates should not be after provided start date!", extractedCreatedAt, everyItem(is(sameOrAfter(providedStartDate))));
        }
        if (!endDate.equals("null")) {
            Date providedEndDate;

            if (endDate.contains("-")) {
                providedEndDate = convertToDate(endDate, Format.dd_MM_yyyy, true);
                providedEndDate = adjustDateBySeconds(adjustDateByDays(providedEndDate, 1), -1); // added time 23:59:59
            } else
                providedEndDate = new Date(Long.parseLong(endDate));
            System.out.print("END DATE: " + providedEndDate);

            assertThat("All dates should not be before provided end date!", extractedCreatedAt, everyItem(is(sameOrBefore(providedEndDate))));
        }

        //ascending validation
        if (ascending.equals("null") || ascending.equals("false")) {
            for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
                assertThat("Results are not sorted by desc!", extractedCreatedAt.get(i), is(sameOrAfter(extractedCreatedAt.get(i + 1))));
        } else {
            for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
                assertThat("Results are not sorted by desc!", extractedCreatedAt.get(i), is(sameOrBefore(extractedCreatedAt.get(i + 1))));
        }
    }
}