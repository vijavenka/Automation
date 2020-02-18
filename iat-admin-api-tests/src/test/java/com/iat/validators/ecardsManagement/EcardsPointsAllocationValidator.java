package com.iat.validators.ecardsManagement;


import com.iat.utils.ResponseContainer;
import com.iat.validators.GenericValidator;

import java.util.List;

import static com.iat.utils.DateTimeUtil.Format;
import static com.iat.utils.DateTimeUtil.convertToDate;
import static com.iat.utils.matchers.CustomMatchers.containsStringIgnoringCase;
import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcardsPointsAllocationValidator {

    private GenericValidator genericValidator = new GenericValidator();

    public void validateEcardsPointsAllocationHistory(String params, ResponseContainer response) throws Throwable {
        String[] params2 = params.split(",");
        String sortField = params2[0];
        String ascending = params2[1];
        String page = params2[2];
        String maxResults = params2[3];
        String who = params2[4];
        String from = params2[5];
        String to = params2[6];
        String dateFrom = params2[7];
        String dateTo = params2[8];
        String description = params2[9];
        String amount = params2[10];
        String points = params2[11];


        if (maxResults.equals("null"))
            maxResults = "10";


        assertThat("Incorrect results count - more than provided maxResults", response.getList("results.id").size(), lessThanOrEqualTo(Integer.parseInt(maxResults)));
        assertThat("Incorrect searchResultsCount field value - more than provided maxResults", response.getInt("pageSize"), is(lessThanOrEqualTo(Integer.parseInt(maxResults))));


        if (sortField.equals("null"))
            sortField = "date";
        if (ascending.equals("null"))
            ascending = "false";


        //Filtering
        List<String> extractedCreatedAt = response.getList("results.createdAt");

        if (!dateFrom.equals("null")) {
            Long providedStartDate;
            if (dateFrom.contains("T"))
                providedStartDate = convertToDate(dateFrom, Format.yyyyMMddTHHmmssXXX).getTime();
            else
                providedStartDate = Long.parseLong(dateFrom);

            for (String createdAt : extractedCreatedAt)
                assertThat("Allocation record createdAt date: " + createdAt + " is below provided start date", convertToDate(createdAt, Format.yyyyMMddTHHmmssXXX).getTime(), is(greaterThanOrEqualTo(providedStartDate)));
        }
        if (!dateTo.equals("null")) {
            Long providedEndDate;
            if (dateTo.contains("T"))
                providedEndDate = convertToDate(dateTo, Format.yyyyMMddTHHmmssXXX).getTime();
            else
                providedEndDate = Long.parseLong(dateTo);

            for (String createdAt : extractedCreatedAt)
                assertThat("Allocation record createdAt date: " + createdAt + " is above provided end date", convertToDate(createdAt, Format.yyyyMMddTHHmmssXXX).getTime(), is(lessThanOrEqualTo(providedEndDate)));
        }
        if (!description.equals("null")) {
            assertThat("No results for search by description: " + description, response.getInt("searchResultsCount"), is(greaterThan(0)));
            List<String> extractedDescription = response.getList("results.description");
            assertThat("Description does not contain keyword!", extractedDescription, everyItem(containsStringIgnoringCase(description)));
        }

        if (!from.equals("null")) {
            assertThat("No results for search by from: " + from, response.getInt("searchResultsCount"), is(greaterThan(0)));
            List<String> extractedFrom = response.getList("results.from");
            assertThat("From does not contain keyword!", extractedFrom, everyItem(containsStringIgnoringCase(from)));
        }

        if (!to.equals("null")) {
            assertThat("No results for search by to: " + to, response.getInt("searchResultsCount"), is(greaterThan(0)));
            List<String> extractedTo = response.getList("results.to");
            assertThat("From does not contain keyword!", extractedTo, everyItem(containsStringIgnoringCase(to)));
        }

        if (!who.equals("null")) {
            assertThat("No results for search by who: " + who, response.getInt("searchResultsCount"), is(greaterThan(0)));
            List<String> extractedWho = response.getList("results.who");
            assertThat("Who does not contain keyword!", extractedWho, everyItem(containsStringIgnoringCase(who)));
        }
        if (!amount.equals("null")) {
            assertThat("No results for search by amount: " + amount, response.getInt("searchResultsCount"), is(greaterThan(0)));
            List<Float> extractedAmount = response.getList("results.amount");
            assertThat("Amount does not equal keyword!", extractedAmount, everyItem(is(Float.parseFloat(amount))));
        }
        if (!points.equals("null")) {
            assertThat("No results for search by points: " + points, response.getInt("searchResultsCount"), is(greaterThan(0)));
            List<Integer> extractedPoints = response.getList("results.pointsAmount");
            assertThat("Points does not equal keyword!", extractedPoints, everyItem(is(Integer.parseInt(points))));
        }


        //ascending validation
        if (sortField.equals("date")) {
            if (ascending.equals("false")) {
                for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
                    assertThat("Results are not sorted by desc!", convertToDate(extractedCreatedAt.get(i), Format.yyyyMMddTHHmmssXXX), is(sameOrAfter(convertToDate(extractedCreatedAt.get(i + 1), Format.yyyyMMddTHHmmssXXX))));
            } else {
                for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
                    assertThat("Results (date) are not sorted by asc!", convertToDate(extractedCreatedAt.get(i), Format.yyyyMMddTHHmmssXXX), is(sameOrBefore(convertToDate(extractedCreatedAt.get(i + 1), Format.yyyyMMddTHHmmssXXX))));
            }
        }
        if (sortField.equals("points")) {
            List<Integer> extractedPoints = response.getList("results.pointsAmount");
            if (ascending.equals("false")) {
                for (int i = 0; i < extractedPoints.size() - 1; i++)
                    assertThat("Results (points) are not sorted by desc!", extractedPoints.get(i), is(greaterThanOrEqualTo(extractedPoints.get(i + 1))));
            } else {
                for (int i = 0; i < extractedPoints.size() - 1; i++)
                    assertThat("Results (points) are not sorted by asc!", extractedPoints.get(i), is(lessThanOrEqualTo(extractedPoints.get(i + 1))));
            }
        }
        if (sortField.equals("amount")) {
            List<Float> extractedAmount = response.getList("results.amount");
            if (ascending.equals("false")) {
                for (int i = 0; i < extractedAmount.size() - 1; i++)
                    assertThat("Results (amount) are not sorted by desc!", extractedAmount.get(i), is(greaterThanOrEqualTo(extractedAmount.get(i + 1))));
            } else {
                for (int i = 0; i < extractedAmount.size() - 1; i++)
                    assertThat("Results (amount) are not sorted by asc!", extractedAmount.get(i), is(lessThanOrEqualTo(extractedAmount.get(i + 1))));
            }
        }

        if (sortField.equals("id")) {
            List<Long> extractedId = response.getList("results.id");
            if (ascending.equals("false")) {
                for (int i = 0; i < extractedId.size() - 1; i++)
                    assertThat("Results (id) are not sorted by desc!", extractedId.get(i), is(greaterThanOrEqualTo(extractedId.get(i + 1))));
            } else {
                for (int i = 0; i < extractedId.size() - 1; i++)
                    assertThat("Results (id) are not sorted by asc!", extractedId.get(i), is(lessThanOrEqualTo(extractedId.get(i + 1))));
            }
        }

        if (sortField.equals("who")) {
            List<String> extractedWho = response.getList("results.who");
            if (extractedWho.size() > 1)
                genericValidator.validateStringsOrder(extractedWho, ascending, "who");
        }

        if (sortField.equals("from")) {
            List<String> extractedFrom = response.getList("results.from");
            if (extractedFrom.size() > 1)
                genericValidator.validateStringsOrder(extractedFrom, ascending, "from");
        }

        if (sortField.equals("to")) {
            List<String> extractedTo = response.getList("results.to");
            if (extractedTo.size() > 1)
                genericValidator.validateStringsOrder(extractedTo, ascending, "to");
        }

        if (sortField.equals("description")) {
            List<String> extractedDescription = response.getList("results.description");
            if (extractedDescription.size() > 1)
                genericValidator.validateStringsOrder(extractedDescription, ascending, "description");
        }

        if (sortField.equals("partnerName")) {
            List<String> extractedPartnerName = response.getList("results.partnerName");
            if (extractedPartnerName.size() > 1)
                genericValidator.validateStringsOrder(extractedPartnerName, ascending, "partnerName");
        }
    }
}
