package com.iat.validators.integration;

import com.iat.Config;
import com.iat.utils.JdbcDatabaseConnector;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class BraintreeValidator {

    private JdbcDatabaseConnector mySQLConnectorPointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);

    public void validateBraintreeStatus(String transactionId, String actualStatus) {

            List payment = mySQLConnectorPointsManager.getResult("SELECT status, userId, moneyValue, epoints FROM points_manager.Payment where paymentId = " + "'" + transactionId + "'", asList("status", "userId", "moneyValue", "epoints")).get(0);

            String paymentStatus = payment.get(0).toString();
            String paymentUserId = payment.get(1).toString();
            BigDecimal paymentMoneyValue = ((BigDecimal) payment.get(2));
            Integer paymentEpoints = ((Integer) payment.get(3));

            List user = mySQLConnectorPointsManager.getResult("SELECT confirmed, pending FROM points_manager.User where id =" + paymentUserId, asList("confirmed", "pending")).get(0);

            Integer userConfirmed = (Integer)user.get(0);
            Integer userPending = (Integer)user.get(1);

            String sTagkey = "epointsBuy";
            String tagTagId =  mySQLConnectorPointsManager.getSingleResult("SELECT id FROM points_manager.Tag where tagKey = " + "'" + sTagkey + "'" );
            int iTagId = Integer.parseInt(tagTagId);
            String pointsDelta = mySQLConnectorPointsManager.getSingleResult("SELECT delta FROM points_manager.Points where userId=" + paymentUserId + " and tagId = " + iTagId);


            BigDecimal moneyValue = new BigDecimal(200);
            int points = Integer.parseInt(pointsDelta);
            assertThat("delta", paymentEpoints, is((points)));
            log.info("Delta : {}", paymentEpoints);

            BigDecimal payEpoints = moneyValue.multiply(paymentMoneyValue);
            assertThat("confirmed", 51 + paymentEpoints, is(userConfirmed));
            assertThat("pending", 0, is(userPending));

            assertThat("Status", actualStatus, is(paymentStatus));
            log.info("Status : {} {}", paymentStatus, " - validation passed");
    }
}
