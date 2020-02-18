package com.iat.validators.pointsAllocation;

import com.iat.domain.pointsAllocation.PointsTransaction;
import com.iat.utils.ResponseContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class PointsAllocationValidator {

    public void assertPointsAfterTransaction(ResponseContainer userBalanceBefore, ResponseContainer userBalanceAfter, PointsTransaction pointsTransactionBody) {
        if (pointsTransactionBody.getPointsType() == null) {
            //in case of using multiple points award for multiple partners, pointsType is not send
            assertThat("Wrong confirmed points value after transaction", userBalanceAfter.getInt("confirmedPoints"), is(userBalanceBefore.getInt("confirmedPoints") + pointsTransactionBody.getNumPointsInt()));
        } else {
            switch (pointsTransactionBody.getPointsType()) {
                case "CONFIRMED":
                    assertThat("Wrong confirmed points value after transaction", userBalanceAfter.getInt("confirmedPoints"), is(userBalanceBefore.getInt("confirmedPoints") + pointsTransactionBody.getNumPointsInt()));
                    break;
                case "PENDING":
                    assertThat("Wrong pending points value after transaction", userBalanceAfter.getInt("pendingPoints"), is(userBalanceBefore.getInt("pendingPoints") + pointsTransactionBody.getNumPointsInt()));
                    break;
            }
        }
    }

    public void compareRequestParameterWithTransactionsResponseData(PointsTransaction pointsTransactionJsonBody, ResponseContainer transactionsResponse) {

        assertThat("Status is incorrect!", pointsTransactionJsonBody.getPointsType(), containsString(transactionsResponse.getString("transactions[0].status")));
        assertThat("Delta is incorrect!", pointsTransactionJsonBody.getPointsType(), containsString(transactionsResponse.getString("transactions[0].delta")));
        assertThat("External transaction is incorrect!", pointsTransactionJsonBody.getExternalTransactionId(), containsString(transactionsResponse.getString("transactions[0].externalTransactionId")));
        assertThat("Tag is incorrect!", pointsTransactionJsonBody.getTag(), containsString(transactionsResponse.getString("transactions[0].tagName")));
        assertThat("OnBehalfOfId is incorrect!", pointsTransactionJsonBody.getOnBehalfOfId(), containsString(transactionsResponse.getString("transactions[0].onBehalfOfId")));
        // activity info on transaction list is taken from tags description so data seems to be inconsistent but discussed with Greg and it should stays like that
        assertThat("Activity info is incorrect!", pointsTransactionJsonBody.getExternalTransactionId(), containsString(transactionsResponse.getString("transactions[0].activityInfo")));
    }
}