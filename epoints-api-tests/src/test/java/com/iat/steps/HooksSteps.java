package com.iat.steps;

import com.iat.Config;
import com.iat.actions.BasketActions;
import com.iat.actions.JoinAndSignInActions;
import com.iat.actions.points.RetailersActions;
import com.iat.actions.points.SearchMerchantActions;
import com.iat.actions.rest.RestActions;
import com.iat.domain.basket.Basket;
import com.iat.domain.retailer.Retailer;
import com.iat.domain.retailer.RetailersList;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;
import com.iat.utils.SessionIdKeeper;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;

public class HooksSteps {

    private BasketActions basketActions = new BasketActions();
    private UserRepository userRepository = new UserRepositoryImpl();
    private JdbcDatabaseConnector mySQLConnectorPointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);
    private JdbcDatabaseConnector mySQLConnectorAffiliateManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolAffiliateManager);
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private SearchMerchantActions searchMerchantActions = new SearchMerchantActions();
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private JoinAndSignInActions joinAndSignInActions = new JoinAndSignInActions();
    private RetailersActions retailersActions = new RetailersActions();
    private RestActions restActions = new RestActions();


    @After("@deleteUserAfterTest")
    public void deleteUserById() throws Throwable {
        userRepository.removeUserById(dataExchanger.getUuid());
    }

    @After("@clearUserBasketAfterTest")
    public void clearUserBasketAfterTest() throws Throwable {
        Basket basket = new Basket();
        basket.setItems(new ArrayList<>());
//        String strBasket = basket.toJsonRequest().replace("[ [", "[").replace("] ]", "]");

        if (dataExchanger.getoAuth() != null)
            basketActions.updateBasket(dataExchanger.getUuid(), basket, dataExchanger.getoAuth().getAccess_token());
        else
            basketActions.updateBasket(dataExchanger.getUuid(), basket);

    }

    @After("@deleteOrderPointsManager")
    public void deleteOrderFromPointsManagerAfterTest() throws Throwable {
        if (dataExchanger.getOrder() != null) {
            System.out.println("Delete Points Manager: Points, Products, RedemptionOrder" + (dataExchanger.getOrder().getId() == null ? "" : ": " + dataExchanger.getOrder().getId()));
            mySQLConnectorPointsManager.execute("DELETE FROM points_manager.Points WHERE productId in (SELECT id FROM points_manager.Product WHERE orderId = '" + dataExchanger.getOrder().getId() + "')");
            mySQLConnectorPointsManager.execute("DELETE FROM points_manager.Product WHERE orderId = '" + dataExchanger.getOrder().getId() + "'");
            mySQLConnectorPointsManager.execute("DELETE FROM points_manager.RedemptionOrder WHERE id = '" + dataExchanger.getOrder().getId() + "'");
        }
    }

    //Below step will be executed only for user Config.getEpointsUserDefault_1
    @Before("@deleteAllMerchantFromUserFavouritesBeforeTest")
    public void deleteAllMerchantFromUserFavourites() throws Throwable {
        sessionId.set(joinAndSignInActions.getSessionIdForAuthUser(Config.getEpointsUserDefault_1));
        ResponseContainer response = restActions.getUserProfile(200);
        String userUuid = response.getString("accountDetails.id");
        ResponseContainer responseContainer = searchMerchantActions.getSearchMerchantForLoggedUser("null;null;null;null;null;true");
        RetailersList retailersList = responseContainer.getAsObject(RetailersList.class);
        for (Retailer retailer : retailersList.getMerchants())
            retailersActions.putDeleteFavouritesRetailers(retailer.getId(), userUuid, false, 200);
    }

    @After("@deleteClickoutAndPointsAfterTest")
    public void deleteClickoutAfterTest() throws Throwable {
        if (dataExchanger.getClickoutDb() != null) {
            String pointsIds = getPointsIds();
            System.out.println("DELETE from affiliate_manager.Clickout WHERE reference = '" + dataExchanger.getClickoutDb().getReference() + "'");
            mySQLConnectorAffiliateManager.execute("DELETE from affiliate_manager.Clickout WHERE reference = '" + dataExchanger.getClickoutDb().getReference() + "'");


            if (!dataExchanger.getClickoutDb().getPointsId().equals("null")) {
                System.out.println("SELECT spunPointsId from points_manager.Spin WHERE originalPointsId in (" + pointsIds + ")");
                String spunPointsId = mySQLConnectorPointsManager.getSingleResult("SELECT spunPointsId from points_manager.Spin WHERE originalPointsId in (" + pointsIds + ")");
                System.out.println("DELETE from points_manager.Spin WHERE originalPointsId in (" + pointsIds + ")");
                mySQLConnectorPointsManager.execute("DELETE from points_manager.Spin WHERE originalPointsId in (" + pointsIds + ")");
                if (spunPointsId != null) {
                    System.out.println("DELETE from points_manager.Points WHERE id in(SELECT spunPointsId from points_manager.Spin WHERE originalPointsId in (" + pointsIds + ")");
                    mySQLConnectorPointsManager.execute("DELETE from points_manager.Points WHERE id in(SELECT spunPointsId from points_manager.Spin WHERE originalPointsId in (" + pointsIds + ")");
                }
                System.out.println("DELETE from points_manager.Points WHERE id in (" + pointsIds + ")");
                mySQLConnectorPointsManager.execute("DELETE from points_manager.Points WHERE id in (" + pointsIds + ")");
            }
        }
        dataExchanger.setSpinUsed(false);
    }

    private String getPointsIds() {
        HashMap<Integer, List> clickoutsPoints =
                mySQLConnectorAffiliateManager
                        .getResult("SELECT pointsId from affiliate_manager.Clickout WHERE reference = '1bdbabf7-e89c-4980-9869-1a639c35dc20' AND pointsId is not null", singletonList("pointsId"));
        String pointsIds = "";
        for (List clickoutPoints : clickoutsPoints.values())
            pointsIds += ", " + clickoutPoints.get(0).toString();
        return pointsIds.replaceFirst(", ", "");
    }

    @After(value = "@setOAuthToNull", order = 9000)
    public void setOAuthUsageFlagToFalse() throws Throwable {
        dataExchanger.setoAuth(null);
    }

    @Before("@resetProductsListBeforeTest")
    public void resetProductsListBeforeTest() throws Throwable {
        dataExchanger.resetProductsList();
    }

    @After("@deleteBtreeUserAfterTest")
    public void deleteBtreeUserById() throws Throwable { // Temporary fix
        if(dataExchanger.getUuid() != null) {
            System.out.println("Delete Points Manager: Points, Payment, User " + (dataExchanger.getUuid() == null ? "" : ": " + dataExchanger.getUuid()));

            mySQLConnectorPointsManager.execute("DELETE FROM points_manager.Points WHERE userId = (SELECT id FROM points_manager.User WHERE userKey = '" + dataExchanger.getUuid() + "')");
            mySQLConnectorPointsManager.execute("DELETE FROM points_manager.Payment WHERE userId = (SELECT id FROM points_manager.User WHERE userKey = '" + dataExchanger.getUuid() + "')");
            mySQLConnectorPointsManager.execute("DELETE FROM points_manager.User WHERE userKey = " + dataExchanger.getUuid() + "')");
            userRepository.removeUserById(dataExchanger.getUuid());
        }
    }
}