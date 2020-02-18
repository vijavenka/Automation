package com.iat.validators.pointsAllocation;

import com.iat.utils.ResponseContainer;
import com.iat.utils.ResponseHolder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PointsUpdateValidator {

    private ResponseHolder responseHolder;

    public PointsUpdateValidator() {
        responseHolder = ResponseHolder.getInstance();
    }

    public void checkCorrectnesOfUserPointsValuesAfterUpdate(ResponseContainer oldBalance, ResponseContainer newBalance) {
        int pointsValue = responseHolder.getPointsTransaction().getNumPointsInt();

        if (responseHolder.getPointsTransactionUpdate().getPointsType().equals("CONFIRMED"))
            assertThat("Confirmed points value was not properly changed", newBalance.getInt("confirmedPoints"), is(oldBalance.getInt("confirmedPoints") + pointsValue));
        else if (responseHolder.getPointsTransactionUpdate().getPointsType().equals("DECLINED"))
            assertThat("Declined points value was not properly changed", newBalance.getInt("declinedPoints"), is(oldBalance.getInt("declinedPoints") + pointsValue));
        assertThat("Pending points value was not properly changed", newBalance.getInt("pendingPoints"), is(oldBalance.getInt("pendingPoints") - pointsValue));
        assertThat("REDEEMED points values are not the same", newBalance.getInt("redeemedPoints"), is(oldBalance.getInt("redeemedPoints")));
    }

}