package com.iat.validators.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalanceDoorman;
import com.iat.domain.rewardsHistory.DoormanRewardsHistory;
import com.iat.domain.rewardsHistory.RewardsHistory;
import com.iat.domain.transactions.DoormanTransactions;
import com.iat.domain.transactions.Transactions;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersValidator {

    private UserRepository userRepository = new UserRepositoryImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private String[] inputTypes = {"CONFIRMED", "RECEIVED"};
    private UsersActions usersActions = new UsersActions();


    public void validateUserBalanceCorrectness(String businessId, ResponseContainer response) {

        UserBalanceDoorman userBalanceFromDoorman;
        if (businessId.equalsIgnoreCase("UNITED"))
            userBalanceFromDoorman = userRepository.findBalanceByUserIdForSpecifiedBusiness(dataExchanger.getUuid(), businessId);
        else
            userBalanceFromDoorman = userRepository.findBalanceByUserId(dataExchanger.getUuid());

        assertThat("pendingPoints for business: " + businessId + " is not correct!", response.getLong("pendingPoints"), is(userBalanceFromDoorman.getPending()));
        assertThat("declinedPoints for business: " + businessId + " is not correct!", response.getLong("declinedPoints"), is(userBalanceFromDoorman.getDeclined()));
        assertThat("confirmedPoints for business: " + businessId + " is not correct!", response.getLong("confirmedPoints"), is(userBalanceFromDoorman.getConfirmed()));
        assertThat("redeemedPoints for business: " + businessId + " is not correct!", response.getLong("redeemedPoints"), is(userBalanceFromDoorman.getRedeemed()));
    }

    public void validateUserTransactionsCorrectness(String uuid, int page, int size, String sort, String type, String businessId, Transactions transactions) throws IOException {

        if (page != 9999 && page != 0) {
            ResponseContainer response = usersActions.getUserTransactions(uuid, 0, size, sort, type, businessId);
            Transactions transactionsForFirstPage = response.getAsObject(Transactions.class);

            assertThat("Wrong date of transactions", transactions.getTransactions().get(0).getDate(), is(not(transactionsForFirstPage.getTransactions().get(0).getDate())));
        }

        if (size != 9999 && size != 0) {
            if (size > 0)
                // it can fail because size define upper limit of returned transactions, can return less transactions
                assertThat("Wrong number of transactions", transactions.getTransactions(), hasSize(size));
        }

        if (!sort.equals("null")) {
            String sortField = sort.split(",")[0];
            String sortOrder = sort.split(",")[1];

            for (int i = 0; i < transactions.getTransactions().size() - 1; i++) {

                if (sortOrder.equals("asc"))
                    assertThat("Wrong elements " + i + " and " + i + 1 + " order", transactions.getTransactions().get(i).getDate(), is(lessThanOrEqualTo(transactions.getTransactions().get(i + 1).getDate())));
                else if (sortOrder.equals("desc"))
                    assertThat("Wrong elements " + i + " and " + i + 1 + " order", transactions.getTransactions().get(i).getDate(), is(greaterThanOrEqualTo(transactions.getTransactions().get(i + 1).getDate())));

            }
        }

        if (page == 0) {
            String types = "";

            String[] temp = type.split(",");
            for (String status : temp)
                types += "'" + status + "',";
            types = types.substring(0, types.lastIndexOf(","));


            DoormanTransactions doormanTransactions = mapper.readValue("{\"transactions\":" + userRepository.getUserTransactions(dataExchanger.getUuid(), types, businessId) + "}", DoormanTransactions.class);

            if (size == 9999)
                assertThat("Number of transactions is different.", transactions.getTransactions().size(), is(equalTo(doormanTransactions.getTransactions().size())));

            for (int i = 0; i < transactions.getTransactions().size(); i++) {
                assertThat("Different balance for element " + i, transactions.getTransactions().get(i).getBalance(), is(equalTo(doormanTransactions.getTransactions().get(i).getBalance())));

                if (asList(inputTypes).contains(doormanTransactions.getTransactions().get(i).getStatus()))
                    assertThat("Wrong transaction " + i + " delta", transactions.getTransactions().get(i).getEarnedPoints().getPoints(), is(equalTo(doormanTransactions.getTransactions().get(i).getDelta())));
                else
                    assertThat("Wrong transaction " + i + " delta", transactions.getTransactions().get(i).getSpentPoints(), is(equalTo(doormanTransactions.getTransactions().get(i).getDelta())));
            }

        }

    }

    public void validateErrorResponse(ResponseContainer response, String error, String message) {
        assertThat("Wrong error code", response.getString("error"), is(error));
        assertThat("Wrong error message", response.getString("message"), is(message));
    }

    public void validateUserRewardHistoryCorrectness(String uuid, int size, String businessId, RewardsHistory rewardsHistory) throws IOException {

        if (size != 9999 && size != 0)
            assertThat("Number of rewards is not correct.", rewardsHistory.getRewards(), hasSize(size));

        DoormanRewardsHistory doormanRewardsHistory = mapper.readValue("{\"rewards\":" + userRepository.getUserRewardsHistory(uuid, businessId) + "}", DoormanRewardsHistory.class);
        for (int i = 0; i < rewardsHistory.getRewards().size(); i++) {
            if (businessId.equalsIgnoreCase("UNITED"))
                assertThat("Wrong reward businessId returned by api.", rewardsHistory.getRewards().get(i).getRedemptionItemInfo().getBusinessId(), is(equalTo("united")));
            else
                assertThat("Wrong reward businessId returned by api.", rewardsHistory.getRewards().get(i).getRedemptionItemInfo().getBusinessId(), is(equalTo(null)));
            //validate title, quantity, price, imageUrl  for each reward
            assertThat("Wrong reward title returned by api.", rewardsHistory.getRewards().get(i).getRedemptionItemInfo().getTitle(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getTitle())));
            assertThat("Wrong reward quantity returned by api.", rewardsHistory.getRewards().get(i).getQuantity(), is(equalTo(Integer.parseInt(doormanRewardsHistory.getRewards().get(i).getQuantity()))));
            assertThat("Wrong reward price returned by api.", rewardsHistory.getRewards().get(i).getEpointsSpentForOneProduct(), is(equalTo(Integer.parseInt(doormanRewardsHistory.getRewards().get(i).getNumPoints()))));
            assertThat("Wrong reward imageUrl returned by api.", rewardsHistory.getRewards().get(i).getRedemptionItemInfo().getImageUrl(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getImageUrl())));

            //validate addressInfo for each reward
            assertThat("Wrong country returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getCountry(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getCountry())));
            assertThat("Wrong county returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getCounty(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getCounty())));
            assertThat("Wrong city returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getCity(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getCity())));
            assertThat("Wrong postcode returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getPostCode(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getPostcode())));
            assertThat("Wrong address1 returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getAddress1(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getAddress1())));
            assertThat("Wrong address2 returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getAddress2(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getAddress2())));
            assertThat("Wrong name returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getName(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getName())));
            assertThat("Wrong phoneNo returned by api.", rewardsHistory.getRewards().get(i).getDeliveryAddress().getPhoneNo(), is(equalTo(doormanRewardsHistory.getRewards().get(i).getPhone())));
        }
    }

    public void checkProfileCompletedState(ResponseContainer response, String profileCompleted) {
        assertThat("Wrong profileCompleted state!", response.getString("profileCompleted"), is(profileCompleted));
    }

}