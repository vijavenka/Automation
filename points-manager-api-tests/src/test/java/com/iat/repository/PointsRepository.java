package com.iat.repository;

import com.iat.domain.ExternalTransactionId;
import com.iat.domain.Points;

import java.util.List;

public interface PointsRepository {
    List<ExternalTransactionId> getExternalTransactionIdsFromDateRange(String fromDate, String toDate);

    List<Points> getPointsExternalListWithinMinutes(long pointsUserId, int minutes);

    List<Points> getPointsListWithinMinutes(long pointsUserId, int minutes);

    Points getPointsWithinMinutes(long pointsUserId, int minutes);

    Points getPointsExternalWithinMinutes(long pointsUserId, int minutes);
}