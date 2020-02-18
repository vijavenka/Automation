package com.iat.validators.ecardsManagement;


import com.iat.actions.DepartmentsStructureActions;
import com.iat.domain.ecardsAwarding.EcardAward;
import com.iat.domain.ecardsAwarding.EcardAwardsList;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.GenericValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.iat.utils.DateTimeUtil.Format;
import static com.iat.utils.DateTimeUtil.convertToDate;
import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EcardsAwardingUsersValidator {


    private GenericValidator genericValidator = new GenericValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public void validateGetEcardsAwardingPoints(String params, ResponseContainer response) throws Throwable {
        EcardAwardsList ecardAwardsList = response.getAsObject(EcardAwardsList.class);
        List<EcardAward> results = ecardAwardsList.getResults();

        String[] params2 = params.split(",");
        String sortField = params2[0];
        String ascending = params2[1];
        String page = params2[2];
        String maxResults = params2[3];
        String dateFrom = params2[4];
        String dateTo = params2[5];
        String from = params2[6];
        String to = params2[7];
        String points = params2[8];
        String amount = params2[9];
        String senderDepartment = params2[10];
        String receiverDepartment = params2[11];
        String approvalStatus = params2[12];

        int maxResultsInt;
        if (maxResults.equals("null"))
            maxResultsInt = 10;
        else
            maxResultsInt = Integer.parseInt(maxResults);

        assertThat("Incorrect results count", ecardAwardsList.getResults(), hasSize(lessThanOrEqualTo(maxResultsInt)));
        assertThat("Incorrect searchResultsCount field value", ecardAwardsList.getResults(), hasSize(ecardAwardsList.getPageSize()));


        if (sortField.equals("null"))
            sortField = "date";
        if (ascending.equals("null"))
            ascending = "false";


        //SORTING
        if (sortField.equals("date")) {
            for (int i = 0; i < results.size() - 1; i++) {
                if (ascending.equals("false"))
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (date) are not sorted by desc", convertToDate(results.get(i).getCreatedAt(), Format.yyyyMMddTHHmmssXXX),
                            sameOrAfter(convertToDate(results.get(i + 1).getCreatedAt(), Format.yyyyMMddTHHmmssXXX)));
                else
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (date) are not sorted by asc", convertToDate(results.get(i).getCreatedAt(), Format.yyyyMMddTHHmmssXXX),
                            sameOrBefore(convertToDate(results.get(i + 1).getCreatedAt(), Format.yyyyMMddTHHmmssXXX)));
            }
        }

        if (sortField.equals("id")) {
            for (int i = 0; i < results.size() - 1; i++) {
                if (ascending.equals("false"))
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (id) are not sorted by desc", results.get(i).getId(), greaterThanOrEqualTo(results.get(i + 1).getId()));
                else
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (id) are not sorted by asc", results.get(i).getId(), lessThanOrEqualTo(results.get(i + 1).getId()));
            }
        }

        if (sortField.equals("points")) {
            for (int i = 0; i < results.size() - 1; i++) {
                if (ascending.equals("false"))
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (points) are not sorted by desc", results.get(i).getPoints(), greaterThanOrEqualTo(results.get(i + 1).getPoints()));
                else
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (points) are not sorted by desc", results.get(i).getPoints(), lessThanOrEqualTo(results.get(i + 1).getPoints()));
            }
        }

        if (sortField.equals("amount")) {
            for (int i = 0; i < results.size() - 1; i++) {
                if (ascending.equals("false"))
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (amount) are not sorted by desc", results.get(i).getAmount(), greaterThanOrEqualTo(results.get(i + 1).getAmount()));
                else
                    assertThat("Results [" + i + "] and [" + (i + 1) + "] (amount) are not sorted by desc", results.get(i).getAmount(), lessThanOrEqualTo(results.get(i + 1).getAmount()));
            }
        }

        //strings sorting
        if (sortField.equals("reason")) {
            List<String> extractedReason = response.getList("results.reason");
            if (extractedReason.size() > 1)
                genericValidator.validateStringsOrder(extractedReason, ascending, sortField);
        }


        if (sortField.equals("sender") || sortField.equals("receiver")) {
            List<String> extracted = new ArrayList<>();

            for (EcardAward result : results) {
                String sent = "";

                switch (sortField) {
                    case "sender":
                        if (result.getSentFrom().getEmail() != null)
                            sent += result.getSentFrom().getEmail() + " ";

                        if (result.getSentFrom().getName() != null)
                            sent += result.getSentFrom().getName();
                        break;
                    case "receiver":
                        if (result.getSentTo().getEmail() != null)
                            sent += result.getSentTo().getEmail() + " ";

                        if (result.getSentTo().getName() != null)
                            sent += result.getSentTo().getName();
                        break;
                }

                extracted.add(sent);
            }

            if (extracted.size() > 1)
                genericValidator.validateStringsOrder(extracted, ascending, sortField);
        }


        if (sortField.equals("approvalStatus")) {
            List<String> extractedReason = response.getList("results.approvalStatus");
            genericValidator.validateStringsOrder(extractedReason, ascending, sortField);
        }

        // FILTERING
        if (!dateFrom.equals("null")) {
            Date providedStartDate = dateFrom.contains("T") ? convertToDate(dateFrom, Format.yyyyMMddTHHmmssXXX) : new Date(Long.parseLong(dateFrom));
            for (EcardAward ecardAward : results)
                assertThat("Order createdAt date not in range", convertToDate(ecardAward.getCreatedAt(), Format.yyyyMMddTHHmmssXXX), sameOrAfter(providedStartDate));
        }

        if (!dateTo.equals("null")) {
            Date providedEndDate = dateTo.contains("T") ? convertToDate(dateTo, Format.yyyyMMddTHHmmssXXX) : new Date(Long.parseLong(dateTo));
            for (EcardAward ecardAward : results)
                assertThat("Order createdAt date not in range", convertToDate(ecardAward.getCreatedAt(), Format.yyyyMMddTHHmmssXXX), sameOrBefore(providedEndDate));
        }


        //validation for filtering
        if (!from.equals("null") || !senderDepartment.equals("null")) {

            if (!from.equals("null")) {
                for (EcardAward result : results)
                    assertThat("Incorrect results for search by sender", result.getSentFrom().getName() + " " + result.getSentFrom().getEmail(), containsString(from));
                assertThat("No results for search by From", results.size(), greaterThan(0));

            } else if (!senderDepartment.equals("null")) {
                for (EcardAward result : results)
                    assertThat("Incorrect results for search by Sender Department", result.getSentFrom().getDepartmentName(), containsString(senderDepartment));
                assertThat("No results for search by Sender Department", results.size(), greaterThan(0));
            }
        }

        //validation for filtering
        if (!to.equals("null") || !receiverDepartment.equals("null")) {

            if (!to.equals("null")) {
                for (EcardAward result : results)
                    assertThat("Incorrect results for search by receiver", result.getSentTo().getName() + " " + result.getSentTo().getEmail(), containsString(to));
                assertThat("No results for search by To", results.size(), greaterThan(0));

            } else if (!receiverDepartment.equals("null")) {
                for (EcardAward result : results)
                    assertThat("Incorrect results for search by Receiver Department", result.getSentTo().getDepartmentName(), containsString(receiverDepartment));
                assertThat("No results for search by Receiver Department", results.size(), greaterThan(0));
            }
        }


        if (!amount.equals("null")) {
            for (EcardAward result : results)
                assertThat("Incorrect results for search by amount", result.getAmount(), is(Float.parseFloat(amount)));
            assertThat("No results for search by amount", results.size(), greaterThan(0));
        }

        if (!points.equals("null")) {
            for (EcardAward result : results)
                assertThat("\"Incorrect results for search by points", result.getPoints(), is(Integer.parseInt(points)));
            assertThat("No results for search by points", results.size(), greaterThan(0));
        }

        if (!approvalStatus.equals("null")) {
            if (approvalStatus.equalsIgnoreCase("APPROVED") || approvalStatus.equalsIgnoreCase("PENDING") || approvalStatus.equalsIgnoreCase("REJECTED")) {
                for (EcardAward result : results)
                    assertThat("\"Incorrect results for search by points", result.getAmount(), is(points));
                assertThat("No results for search by points", results.size(), greaterThan(0));
            } else
                assertThat("Should be no results when filter by approvalStatus field with values which are not in (APPROVED, PENDING, REJECTED)", results.size(), is(0));
        }


        System.out.println("Validating results scope");
        new DepartmentsStructureActions().getUserDepartmentsScope();

        for (EcardAward result : results) {
            boolean departmentInScope = dataExchanger.getValidDepartmentsNamesScope().contains(result.getSentTo().getDepartmentName())
                    || dataExchanger.getValidDepartmentsNamesScope().contains(result.getSentFrom().getDepartmentName());
            assertThat("Current award department is out of user scope: \n" + result.toString(), departmentInScope);
        }
    }
}
