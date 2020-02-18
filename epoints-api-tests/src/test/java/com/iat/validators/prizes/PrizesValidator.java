package com.iat.validators.prizes;

import com.iat.Config;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;

import java.util.Date;
import java.util.List;

import static com.iat.utils.DateTimeUtil.Format;
import static com.iat.utils.DateTimeUtil.convertToDate;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Arrays.asList;
import static org.exparity.hamcrest.date.DateMatchers.sameInstant;
import static org.exparity.hamcrest.date.DateMatchers.within;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PrizesValidator {

    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private JdbcDatabaseConnector mySQLConnectorPointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);

    public void validateSpinPointsCorrectness(int howManyPoints, String spinUuid, long pointsId) {
        String originalPointsId = dataExchanger.getClickoutDb().getPointsId();

        List originalPoints = mySQLConnectorPointsManager.getResult("SELECT status, autoConfirm, autoConfirmDate, userId, id" +
                " FROM points_manager.Points" +
                " WHERE id = " + originalPointsId, asList("status", "autoConfirm", "autoConfirmDate", "userId", "id")).get(0);

        String originalPointsStatus = originalPoints.get(0).toString();
        String originalPointsAutoConfirm = originalPoints.get(1).toString();
        Date originalPointsAutoConfirmDate = convertToDate(originalPoints.get(2).toString(), Format.yyyyMMddHHmmss);
        String userId = originalPoints.get(3).toString();

        List newPoints = mySQLConnectorPointsManager.getResult("SELECT activityInfo, delta, externalTransactionId, status, tagId, userId, balance, autoConfirm, autoConfirmDate, merchantName, id" +
                " FROM points_manager.Points" +
                " WHERE id = " + pointsId, asList("activityInfo", "delta", "externalTransactionId", "status", "tagId", "userId", "balance", "autoConfirm", "autoConfirmDate", "merchantName", "id")).get(0);

        assertThat("There is no new Points record!", originalPoints.get(4), is(not(newPoints.get(10))));

        String newPointsActivityInfo = newPoints.get(0).toString();
        int newPointsDelta = Integer.parseInt(newPoints.get(1).toString());
        String newPointsExternalTransactionId = newPoints.get(2).toString();
        String newPointsStatus = newPoints.get(3).toString();
        String newPointsUserId = newPoints.get(5).toString();
        String newPointsAutoConfirm = newPoints.get(7).toString();
        Date newPointsAutoConfirmDate = convertToDate(newPoints.get(8).toString(), Format.yyyyMMddHHmmss);

        assertThat("newPoints record - Improper value activityInfo", newPointsActivityInfo, allOf(startsWith("roulette reason"), endsWith(spinUuid)));
        assertThat("newPoints record - Improper value delta", newPointsDelta, is(howManyPoints));
        assertThat("newPoints record - Improper value externalTransactionId", newPointsExternalTransactionId, is(spinUuid));
        assertThat("newPoints record - Improper value status", newPointsStatus, is(originalPointsStatus));
        assertThat("newPoints record - Improper value userId", newPointsUserId, is(userId));
        assertThat("newPoints record - Improper value autoConfirmDate", newPointsAutoConfirmDate, is(sameInstant(originalPointsAutoConfirmDate)));
        assertThat("newPoints record - Improper value autoConfirm", newPointsAutoConfirm, is(originalPointsAutoConfirm));
    }

    public void validateUserBalanceAfterAwarding(int howManyPoints, UserBalance userBalanceBeforeAwarding) {
        UserBalance userBalance = new UsersActions().getUserBalance("null", 200).getAsObject(UserBalance.class);

        assertThat("Confirmed points should not be changed after awarding!", userBalance.getConfirmedPoints(), is(userBalanceBeforeAwarding.getConfirmedPoints()));
        assertThat("Redeemed points should not be changed after awarding!", userBalance.getRedeemedPoints(), is(userBalanceBeforeAwarding.getRedeemedPoints()));
        assertThat("Declined points should not be changed after awarding!", userBalance.getDeclinedPoints(), is(userBalanceBeforeAwarding.getDeclinedPoints()));
        assertThat("Pending points should increase!", userBalance.getPendingPoints(), is(userBalanceBeforeAwarding.getPendingPoints() + howManyPoints));
    }

    public List validateSpinStatus(String spinStatus) {
        String originalPointsId = dataExchanger.getClickoutDb().getPointsId();
        List spin = mySQLConnectorPointsManager.getResult("SELECT spinUuid, spunPointsId, status, spunAt" +
                        " FROM points_manager.Spin WHERE originalPointsId = " + originalPointsId,
                asList("spinUuid", "spunPointsId", "status", "spunAt")).get(0);

        assertThat("Invalid spin status!", spin.get(2).toString(), is(spinStatus));

        switch (spinStatus) {
            case "USED":
                assertThat("spunPointsId should be populated!", spin.get(1), is(notNullValue()));
                assertThat("Invalid spunPointsId!", (long) spin.get(1), is(greaterThan(Long.parseLong(originalPointsId))));
                assertThat("spunAt should be populated!", spin.get(3), is(notNullValue()));
                Date spunAtDate = convertToDate(spin.get(3).toString(), Format.yyyyMMddHHmmss, true);
                assertThat(spunAtDate, is(within(1, MINUTES, new Date())));
                break;
            case "AVAILABLE":
                assertThat("spunPointsId should not be populated!", spin.get(1), is("null"));
                assertThat("spunAt should not be populated!", spin.get(3), is("null"));
                break;
        }

        return spin;
    }
}