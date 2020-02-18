package com.iat.validators;

import com.iat.actions.couponUsage.CouponUsageActions;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CouponUsageValidator {

    private CouponUsageActions couponUsageActions = new CouponUsageActions();
    private UsersActions usersActions = new UsersActions();

    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public void checkNumberCorrectnessOfCouponUsageListElements(ResponseContainer response) {
        List<String> extractedDates = response.getList("results.date");
        int itemsNumber = response.getInt("totalItems");
        assertThat("Coupon usage elements number is incorrect!", extractedDates, hasSize(itemsNumber));
    }

    public void checkOrderCorrectnessOfCouponUsageList(ResponseContainer response) {
        List<Long> extractedDates = response.getList("results.date");

        for (int ii = 0; ii < extractedDates.size() - 1; ii++)
            assertThat("Coupon usage results " + ii + " and " + ii + 1 + " are in wrong order", extractedDates.get(ii), is(greaterThanOrEqualTo(extractedDates.get(ii + 1))));
    }

    public void checkStoreCorrectnessOfCouponUsageList(ResponseContainer response) {
        List<String> extractedStoreNames = response.getList("results.storeName");
        String storeName1 = "Branch A1";
        String storeName2 = "Branch A2";

        int i = -1;
        for (String extractedStoreName : extractedStoreNames) {
            if (++i == 0) continue;
            assertThat("Coupon usage store " + i + " - " + extractedStoreName + " is wrong, should be " + storeName1 + " or " + storeName2, extractedStoreName, anyOf(is(storeName1), is(storeName2)));
        }
    }

    public void checkIfZeroResultsWereReturnedForNotNewsUser(ResponseContainer response) {
        int itemsNumber = response.getInt("totalItems");
        assertThat("Coupon usage elements number is incorrect!", itemsNumber, is(0));
    }

    public void checkIfPointsWereDeductedFromUserBalance() {
        int confirmedPointsBeforeCashIn = dataExchanger.getUserBalance().getConfirmedPoints();
        int confirmedPointsBeforeAfterIn = usersActions.getUserBalance("null", 200).getAsObject(UserBalance.class).getConfirmedPoints();
        int convertedPointsValue = Integer.parseInt(dataExchanger.getCouponUsageCashIn().getEpointsValue());
        assertThat("Confirmed points value is wrong!", confirmedPointsBeforeAfterIn, is(confirmedPointsBeforeCashIn - convertedPointsValue));
    }

    public void checkIfUserCouponUsagePointsSummaryWasUpdated() {
        ResponseContainer responseOld = dataExchanger.getUserCouponUsagePointsSummary();
        int pointsFromCouponsTotalBeforeCashIn = responseOld.getInt("pointsFromCouponsTotal");
        int pointsToConvertBeforeCashIn = responseOld.getInt("pointsToConvert");
        int pointsConvertedBeforeCashIn = responseOld.getInt("pointsConverted");

        ResponseContainer response = couponUsageActions.getCouponUsagePointsSummary();

        int pointsFromCouponsTotalAfterCashIn = response.getInt("pointsFromCouponsTotal");
        int pointsToConvertAfterCashIn = response.getInt("pointsToConvert");
        int pointsConvertedAfterCashIn = response.getInt("pointsConverted");

        assertThat("pointsFromCouponsTotalAfterCashIn is incorrect!", pointsFromCouponsTotalAfterCashIn, is(pointsFromCouponsTotalBeforeCashIn));

        int convertedPointsValue = Integer.parseInt(dataExchanger.getCouponUsageCashIn().getEpointsValue());
        assertThat("pointsToConvertAfterCashIn is incorrect!", pointsToConvertAfterCashIn, is(pointsToConvertBeforeCashIn - convertedPointsValue));
        assertThat("pointsConvertedAfterCashIn is incorrect!", pointsConvertedAfterCashIn, is(pointsConvertedBeforeCashIn + convertedPointsValue));
    }

}