package com.iat.actions;


import com.iat.controller.StatisticsController;
import com.iat.utils.ResponseContainer;

import java.util.Date;
import java.util.List;

import static com.iat.utils.DateTimeUtil.*;
import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class StatisticsActions {

    private StatisticsController statisticsController = new StatisticsController();

    public ResponseContainer getStatisticsConfig(int status) {
        return initResponseContainer(statisticsController.getStatisticsConfig(status));
    }

    public void validateStatisticsConfigSet(ResponseContainer response, String params) {
        List<String> statisticIds = asList(params.split(","));
        List<String> extractedStatisticIds = response.getList("id");
        assertThat("Missing statistic Id!", statisticIds, everyItem(isIn(extractedStatisticIds)));
    }

    public ResponseContainer getStatisticById(String statId, String params, int status) {
        String[] params2 = params.split(";");
        String dateRange = params2[0];
        String groupBy = params2[1];
        String filters = params2[2];
        String previousPeriod = params2[3];

        return initResponseContainer(statisticsController.getStatisticById(statId, dateRange, groupBy, filters, previousPeriod, status));
    }

    public void validateStatisticByIdData(ResponseContainer response, String statId, String params) {
        String[] params2 = params.split(";");
        String dateRange = params2[0];
        String groupBy = params2[1];
        String filters = params2[2];
        String previousPeriod = params2[3];

        String extractedStatId = response.getString("statisticId");
        assertThat("Incorrect statisticId", extractedStatId, is(statId));

        String extractedGroupBy = response.getString("groupedBy");
        assertThat("Incorrect groupedBy", extractedGroupBy, is(groupBy));

        if (!dateRange.equals("null")) {
            String extractedFrom = response.getString("from");
            String extractedTo = response.getString("to");
            String paramFrom, paramTo;
            paramFrom = dateRange.split(",")[0];
            paramTo = dateRange.split(",")[1];

            Date formatedParamFromDate = convertToDate(paramFrom, Format.ddMMyyyy_HHmmZ);
            Date formatedExtractedFromDate = convertToDate(extractedFrom, Format.yyyyMMddTHHmmssSSS);
            assertThat("Incorrect DateRange from ", diffBetweenDates(formatedExtractedFromDate, formatedParamFromDate, 10) < 2);

            Date formatedParamToDate = convertToDate(paramTo, Format.ddMMyyyy_HHmmZ);
            Date formatedExtractedToDate = convertToDate(extractedTo, Format.yyyyMMddTHHmmssSSS);
            assertThat("Incorrect DateRange to ", diffBetweenDates(formatedExtractedToDate, formatedParamToDate, 10) < 2);

            List<String> labels = response.getList("items.label");

            //by elastic search issue HS-972
            //Results for Group by hour are returned in UTC
            if (!groupBy.equals("receiver") && !groupBy.equals("department")) {
                assertThat("Wrong first label in set", response.getString("from").contains("00:00:00"));
                for (int i = 0; i < labels.size() - 1; i++)
                    assertThat("Group by [" + groupBy + "] Incorrect key label for index [" + i + "]", convertToDate(labels.get(i + 1), Format.yyyyMMddTHHmmssSSS), is(adjustDateByPeriod(groupBy, convertToDate(labels.get(i), Format.yyyyMMddTHHmmssSSS), 1)));
            }
        }

        String extractedPreviousPeriod = response.getString("previousTotalCount");
        if (previousPeriod.equals("true"))
            assertThat("Incorrect previousPeriod should not be null ", extractedPreviousPeriod, not(isEmptyOrNullString()));
        else
            assertThat("Incorrect previousPeriod should be null ", extractedPreviousPeriod == null);
    }

    private Date adjustDateByPeriod(String groupBy, Date formattedParamDateBase, int index) {
        Date toReturn = null;
        switch (groupBy) {
            case "hour":
                toReturn = adjustDateByHours(formattedParamDateBase, index);
                break;
            case "day":
                toReturn = adjustDateByDays(formattedParamDateBase, index);
                break;
            case "week":
                toReturn = adjustDateByWeeks(formattedParamDateBase, index);
                break;
            case "month":
                toReturn = adjustDateByMonths(formattedParamDateBase, index);
                break;
            case "null":
                toReturn = adjustDateByHours(formattedParamDateBase, index);
                break;
        }
        return toReturn;
    }


    private ResponseContainer postEcardsUsageBreakdownExport(String dateRange, int status) {
        return initResponseContainer(statisticsController.postEcardsUsageBreakdownExport(dateRange, status), "Export response:");
    }

    public ResponseContainer postEcardsUsageBreakdownToBeExport(String params, int status) {
        //todo maybe below function getDatesFromParam should be placed somewhere else to be more generic
        List<String> dates = getDatesFromParams(params, Format.ddMMyyyy_HHmm_XXX);
        String dateRange = dates.get(0) + "," + dates.get(1);
        if (dateRange.equals("null,null")) dateRange = "null";
        return postEcardsUsageBreakdownExport(dateRange, status);
    }

}