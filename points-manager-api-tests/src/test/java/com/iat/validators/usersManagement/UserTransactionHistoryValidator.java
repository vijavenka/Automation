package com.iat.validators.usersManagement;

import com.iat.repository.PartnerRepository;
import com.iat.repository.impl.PartnerRepositoryImpl;
import com.iat.utils.HelpFunctions;
import com.iat.utils.ResponseContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTransactionHistoryValidator {

    private HelpFunctions helpFunctions = new HelpFunctions();
    private final PartnerRepository partnerRepository = new PartnerRepositoryImpl();

    //TODO Below function are very similar but did not find clean solution for this, probably best solution will be using hash map and operate on labels not parameter position
    //TODO in that case needed changes here and in contract class

    public void checkIfReturnedTransactionsByClientAreAsExpectedAccordingToParameters(String requestParameters, ResponseContainer response) {
        String[] parameters = requestParameters.split(",");

        List<Date> transactionDates = new ArrayList<>();
        response.getList("transactions.transactionDate", Long.class).forEach(it -> transactionDates.add(new Date(it)));
        int howMany = response.getList("transactions").size();
        int searchResultsCount = response.getInt("searchResultsCount");

        String siteName = partnerRepository.findByShortname(parameters[0]).getName();

        String transactionIndex;
        for (int i = 0; i < howMany; i++) {
            transactionIndex = "transactions[" + i + "].";

            //TODO can be removed iv overkill but site is connected with clientId so in my opinion not
            assertThat("clientId in not as expected", response.getString(transactionIndex + "site"), is(siteName));

            if (!parameters[4].equals("null"))
                assertThat("Status has incorrect value", response.getString(transactionIndex + "status"), is(parameters[4]));
            if (!parameters[5].equals("null"))
                assertThat("tagName has incorrect value", response.getString(transactionIndex + "tagName"), is(parameters[5]));
            if (!parameters[6].equals("null"))
                assertThat("OnBehalfOff has incorrect value", response.getString(transactionIndex + "onBehalfOfId"), is(parameters[6]));
            if (!parameters[10].equals("null"))
                assertThat("createdDate is before set start date", transactionDates.get(i), is(sameOrAfter(new Date(Long.parseLong(parameters[10])))));
            if (!parameters[11].equals("null"))
                assertThat("createdDate is after set end date", transactionDates.get(i), is(sameOrBefore(new Date(Long.parseLong(parameters[11])))));
        }
        if (!parameters[7].equals("null")) {
            for (int i = 0; i < howMany - 1; i++) {
                if (parameters[7].equals("true"))
                    assertThat("Transaction are not sorted date ascending", transactionDates.get(i), is(sameOrBefore(transactionDates.get(i + 1))));
                else if (parameters[7].equals("false"))
                    assertThat("Transaction are not sorted date descending", transactionDates.get(i), is(sameOrAfter(transactionDates.get(i + 1))));
            }
        }
        if (!parameters[8].equals("null") && Integer.parseInt(parameters[8]) > 1000)
            assertThat("Transaction returned but should not", howMany, is(0));
        if (!parameters[9].equals("null")) {
            assertThat("Incorrect number of transactions", howMany, is(Integer.parseInt(parameters[9])));
            assertThat("searchResultsCount has incorrect value!", searchResultsCount, is(howMany));
        }
    }

    public void checkIfReturnedTransactionsByGroupAreAsExpectedAccordingToParameters(String requestParameters, ResponseContainer response) {
        String[] parameters = requestParameters.split(",");

        List<Date> transactionDates = new ArrayList<>();
        response.getList("transactions.transactionDate", Long.class).forEach(it -> transactionDates.add(new Date(it)));
        int howMany = response.getList("transactions").size();
        int searchResultsCount = response.getInt("searchResultsCount");

        String transactionIndex;
        for (int i = 0; i < howMany; i++) {
            transactionIndex = "transactions[" + i + "].";

            if (!parameters[5].equals("null"))
                assertThat("Status has incorrect value", response.getString(transactionIndex + "status"), is(parameters[5]));
            if (!parameters[6].equals("null"))
                assertThat("tagName has incorrect value", response.getString(transactionIndex + "tagName"), is(parameters[6]));
            if (!parameters[7].equals("null"))
                assertThat("OnBehalfOff has incorrect value", response.getString(transactionIndex + "onBehalfOfId"), is(parameters[7]));
            if (!parameters[11].equals("null"))
                assertThat("createdDate is before set start date", transactionDates.get(i), is(sameOrAfter(new Date(Long.parseLong(parameters[11])))));
            if (!parameters[12].equals("null"))
                assertThat("createdDate is after set end date", transactionDates.get(i), is(sameOrBefore(new Date(Long.parseLong(parameters[12])))));
        }
        if (!parameters[8].equals("null")) {
            for (int i = 0; i < howMany - 1; i++) {
                if (parameters[8].equals("true"))
                    assertThat("Transaction are not sorted date ascending", transactionDates.get(i), is(sameOrBefore(transactionDates.get(i + 1))));
                else if (parameters[8].equals("false"))
                    assertThat("Transaction are not sorted date descending", transactionDates.get(i), is(sameOrAfter(transactionDates.get(i + 1))));
            }
        }
        if (!parameters[9].equals("null") && Integer.parseInt(parameters[9]) > 1000)
            assertThat("Transaction returned but should not", howMany, is(0));
        if (!parameters[10].equals("null")) {
            assertThat("Incorrect number of transactions", howMany, is(Integer.parseInt(parameters[10])));
            assertThat("searchResultsCount has incorrect value!", searchResultsCount, is(howMany));
        }
    }

    public void checkIfReturnedRewardsAreAsExpectedAccordingToParameters(String requestParameters, ResponseContainer response) {
        String[] parameters = requestParameters.split(",");

        List<Date> transactionDates = new ArrayList<>();
        response.getList("transactions.transactionDate", Long.class).forEach(it -> transactionDates.add(new Date(it)));
        int howMany = response.getList("transactions").size();
        int searchResultsCount = response.getInt("searchResultsCount");

        String siteName = "Affiliate Manager";

        String transactionIndex;
        for (int i = 0; i < howMany; i++) {
            transactionIndex = "transactions[" + i + "].";
            assertThat("clientId in not as expected", response.getString(transactionIndex + "site"), is(siteName));

            if (!parameters[6].equals("null"))
                assertThat("createdDate is before set start date", transactionDates.get(i), is(sameOrAfter(new Date(Long.parseLong(parameters[6])))));
            if (!parameters[7].equals("null"))
                assertThat("createdDate is after set end date", transactionDates.get(i), is(sameOrBefore(new Date(Long.parseLong(parameters[7])))));
        }
        if (!parameters[3].equals("null")) {
            for (int i = 0; i < howMany - 1; i++) {
                if (parameters[3].equals("true"))
                    assertThat("Transaction are not sorted date ascending", transactionDates.get(i), is(sameOrBefore(transactionDates.get(i + 1))));
                else if (parameters[3].equals("false"))
                    assertThat("Transaction are not sorted date descending", transactionDates.get(i), is(sameOrAfter(transactionDates.get(i + 1))));
            }
        }
        if (!parameters[4].equals("null") && Integer.parseInt(parameters[4]) > 1000)
            assertThat("Transaction returned but should not", howMany, is(0));
        if (!parameters[5].equals("null")) {
            assertThat("Incorrect number of transactions", howMany, is(Integer.parseInt(parameters[5])));
            assertThat("searchResultsCount has incorrect value!", searchResultsCount, is(howMany));
        }
    }

    public void checkIfReturnedTransactionsAreAsExpectedAccordingToParameters(String requestParameters, ResponseContainer response) {
        String[] parameters = requestParameters.split(",");

        List<Date> transactionDates = new ArrayList<>();
        response.getList("transactions.transactionDate", Long.class).forEach(it -> transactionDates.add(new Date(it)));
        int howMany = response.getList("transactions").size();
        int searchResultsCount = response.getInt("searchResultsCount");

        String transactionIndex;
        for (int i = 0; i < howMany; i++) {
            transactionIndex = "transactions[" + i + "].";

            if (!parameters[4].equals("null"))
                assertThat("Status has incorrect value", response.getString(transactionIndex + "status"), is(parameters[4]));
            if (!parameters[5].equals("null"))
                assertThat("tagName has incorrect value", response.getString(transactionIndex + "tagName"), is(parameters[5]));
            if (!parameters[6].equals("null"))
                assertThat("OnBehalfOff has incorrect value", response.getString(transactionIndex + "onBehalfOfId"), is(parameters[6]));
            if (!parameters[10].equals("null"))
                assertThat("createdDate is before set start date", transactionDates.get(i), is(sameOrAfter(new Date(Long.parseLong(parameters[10])))));
            if (!parameters[11].equals("null"))
                assertThat("createdDate is after set end date", transactionDates.get(i), is(sameOrBefore(new Date(Long.parseLong(parameters[11])))));
        }
        if (!parameters[7].equals("null")) {
            List<Integer> deltas = response.getList("transactions.delta", Integer.class);
            List<String> statuses = new ArrayList<>();
            response.getList("transactions.status", String.class).forEach(it -> statuses.add(it.toLowerCase()));
            List<String> sites = new ArrayList<>();
            response.getList("transactions.site", String.class).forEach(it -> sites.add(it.toLowerCase()));

            for (int i = 0; i < howMany - 1; i++) {
                if (parameters[7].equals("true")) {
                    switch (parameters[3]) {
                        case "null":
                        case "createdAt":
                            assertThat("Transaction are not sorted date ascending", transactionDates.get(i), is(sameOrBefore(transactionDates.get(i + 1))));
                            break;
                        case "delta":
                            assertThat("Deltas are not sorted ascending", deltas.get(i), is(lessThanOrEqualTo(deltas.get(i + 1))));
                            break;
                        case "status":
                            assertThat("Statuses values are not in ascending order", statuses.get(i).compareTo(statuses.get(i + 1)), is(lessThanOrEqualTo(0)));
                            break;
                        case "site":
                            assertThat("Sites values are not in ascending order", sites.get(i).compareTo(sites.get(i + 1)), is(lessThanOrEqualTo(0)));
                            break;
                    }
                } else if (parameters[7].equals("false")) {
                    switch (parameters[3]) {
                        case "null":
                        case "createdAt":
                            assertThat("Transaction are not sorted date descending", transactionDates.get(i), is(sameOrAfter(transactionDates.get(i + 1))));
                            break;
                        case "delta":
                            assertThat("Deltas are not sorted descending", deltas.get(i), is(greaterThanOrEqualTo(deltas.get(i + 1))));
                            break;
                        case "status":
                            assertThat("Statuses values are not in descending order", statuses.get(i).compareTo(statuses.get(i + 1)), is(greaterThanOrEqualTo(0)));
                            break;
                        case "site":
                            assertThat("Sites values are not in descending order", sites.get(i).compareTo(sites.get(i + 1)), is(greaterThanOrEqualTo(0)));
                            break;
                    }
                }
            }
            if (!parameters[8].equals("null") && Integer.parseInt(parameters[8]) > 1000)
                assertThat("Transaction returned but should not", howMany, is(0));
            if (!parameters[9].equals("null")) {
                assertThat("Incorrect number of transactions", howMany, is(Integer.parseInt(parameters[9])));
                assertThat("searchResultsCount has incorrect value!", searchResultsCount, is(howMany));
            }
        }
    }

    public void checkIfAllLastTransactionsAreFromLastTransactionDate(ResponseContainer responseTemp, ResponseContainer response) {
        int lastTransactionDayOfYearNumber = helpFunctions.returnDayOfYearFromTimestamp(responseTemp.getLong("transactions[0].transactionDate"));
        List<Integer> lastTransactionsDayOfYearNumber = new ArrayList<>();
        response.getList("transactions.transactionDate", Long.class).forEach(it -> lastTransactionsDayOfYearNumber.add(helpFunctions.returnDayOfYearFromTimestamp(it)));
        String filteredTagName = responseTemp.getString("transactions[0].tagName");
        List<String> tagNames = response.getList("transactions.tagName");
        for (int i = 0; i < lastTransactionsDayOfYearNumber.size(); i++) {
            assertThat("Returned transaction is not from last transaction day", lastTransactionDayOfYearNumber, is(lastTransactionsDayOfYearNumber.get(i)));
            assertThat("Returned transaction tagName is not as expected", filteredTagName, is(tagNames.get(i)));
        }
    }

}