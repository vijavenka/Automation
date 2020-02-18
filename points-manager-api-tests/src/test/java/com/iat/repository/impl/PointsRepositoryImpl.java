package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.ExternalTransactionId;
import com.iat.domain.Points;
import com.iat.repository.PointsRepository;

import java.util.List;

import static io.restassured.RestAssured.get;
import static java.util.Arrays.asList;

public class PointsRepositoryImpl implements PointsRepository {

    @Override
    public List<ExternalTransactionId> getExternalTransactionIdsFromDateRange(String fromDate, String toDate) {
        return asList(get(Config.getDoormanUrl() + "/externalTransactionIds?fromDate=" + fromDate + "&toDate=" + toDate).as(ExternalTransactionId[].class));
    }

    @Override
    public List<Points> getPointsExternalListWithinMinutes(long pointsUserId, int minutes) {
        System.out.println("Points records: \n");
        get(Config.getDoormanUrl() + "/points/external/within?pointsUserId=" + pointsUserId + "&minutes=" + minutes).prettyPrint();
        return asList(get(Config.getDoormanUrl() + "/points/external/within?pointsUserId=" + pointsUserId + "&minutes=" + minutes).as(Points[].class));
    }

    @Override
    public Points getPointsExternalWithinMinutes(long pointsUserId, int minutes) {
        return getPointsExternalListWithinMinutes(pointsUserId, minutes).get(0);
    }

    @Override
    public List<Points> getPointsListWithinMinutes(long pointsUserId, int minutes) {
        return asList(get(Config.getDoormanUrl() + "/points/within?pointsUserId=" + pointsUserId + "&minutes=" + minutes).as(Points[].class));
    }

    @Override
    public Points getPointsWithinMinutes(long pointsUserId, int minutes) {
        return getPointsListWithinMinutes(pointsUserId, minutes).get(0);
    }

}