package com.iat.steps.users;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.BasketActions;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.domain.orderRedemption.DeliveryCost;
import com.iat.domain.orderRedemption.Order;
import com.iat.domain.orderRedemption.Product;
import com.iat.domain.userProfile.Address;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.HashMap;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CheckoutSteps {

    private ResponseContainer response;
    private UsersActions usersActions = new UsersActions();
    private BasketActions basketActions = new BasketActions();
    private int totalEpointsValue;
    private UserBalance userBalance;
    private UserBalance userBalanceForNotUsedScope;

    private JdbcDatabaseConnector mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ObjectMapper mapper = new ObjectMapper();

    @Given("^User balance for '(.+?)' is stored$")
    public void storeUserBalance(String businessId) throws Throwable {

        //Increase user balance before checkout
        if (dataExchanger.getoAuth() != null)
            response = basketActions.getBasket(dataExchanger.getUuid(), dataExchanger.getoAuth().getAccess_token(), 200);
        else
            response = basketActions.getBasket(dataExchanger.getUuid(), 200);
        List<Product> items = response.getList("items", Product.class);
        List<DeliveryCost> deliveryCosts = response.getList("deliveryCost", DeliveryCost.class);

        int confirmedToIncrease = 0;
        String activeMembershipType = dataExchanger.getLoginSuccess().getActiveMembershipType().contains("gold") ? "gold" : "silver";

        for (Product item : items) {
//            confirmedToIncrease += item.getQuantity() * item.getEpoints().getUk();
            float price = item.getPrice();
            String categories = item.getCategories().get(0).toLowerCase();
            if((activeMembershipType.equalsIgnoreCase("gold")) && !(categories.contains("gift"))){
                assertThat("DiscountedPrice for Gold calculation is wrong",(float)(price-(price*0.1)),is(item.getDiscountedPrice()));
            }
            else{
                assertThat("DiscountedPrice for Gold gift or Silver calculation is wrong",(float)price,is(item.getDiscountedPrice()));
            }
            confirmedToIncrease += item.getQuantity() * item.getDiscountedPriceInEpoints();
        }

        for(DeliveryCost deliveryCost : deliveryCosts) {
            confirmedToIncrease += deliveryCost.getDeliveryCost() * 200;
        }

        String sqlTable = "points_manager.User";
        if (businessId.equalsIgnoreCase("united"))
            sqlTable = "points_manager.Account";

        mySQLConnector.execute("UPDATE " + sqlTable + " SET confirmed = " + (confirmedToIncrease + " WHERE userKey = '" + dataExchanger.getUuid() + "'"));

        if (dataExchanger.getoAuth() != null)
            userBalance = usersActions.getUserBalance(businessId, dataExchanger.getoAuth().getAccess_token(), 200).getAsObject(UserBalance.class);
        else
            userBalance = usersActions.getUserBalance(businessId, 200).getAsObject(UserBalance.class);

        if (dataExchanger.getoAuth() != null) {
            if (businessId.equals("united"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("null", dataExchanger.getoAuth().getAccess_token(), 200).getAsObject(UserBalance.class);
            else if (businessId.equals("null"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("united", dataExchanger.getoAuth().getAccess_token(), 200).getAsObject(UserBalance.class);
        } else {
            if (businessId.equals("united"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("null", 200).getAsObject(UserBalance.class);
            else if (businessId.equals("null"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("united", 200).getAsObject(UserBalance.class);
        }
    }

    @Given("^Checkout is made for basket items and deliveryAddress '(.+?)' with following totalEpoints '(.+?)' value for '(.+?)' status '(.+?)'$")
    public void checkout(String deliveryAddressJsonBody, String totalEpoints, String businessId, int status) throws Throwable {
        Address address;

        //address json
        if (deliveryAddressJsonBody.equalsIgnoreCase("DEFAULT"))
            address = dataExchanger.getDeliveryAddress();
        else
            address = mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES).readValue("{ " + deliveryAddressJsonBody + "}", Address.class);

        //redemption json
        if (dataExchanger.getoAuth() != null)
            response = basketActions.getBasket(dataExchanger.getUuid(), dataExchanger.getoAuth().getAccess_token(), 200);
        else
            response = basketActions.getBasket(dataExchanger.getUuid(), 200);

        List<Product> items = response.getList("items", Product.class);
        List<DeliveryCost> deliveryCosts = response.getList("deliveryCost", DeliveryCost.class);

        totalEpointsValue = 0;
        if (totalEpoints.equalsIgnoreCase("previous_call")) {
            for (Product item : items)
//                totalEpointsValue += item.getQuantity() * item.getEpoints().getUk();
                totalEpointsValue += item.getQuantity() * item.getDiscountedPriceInEpoints();

            for(DeliveryCost deliveryCost : deliveryCosts)
                totalEpointsValue += deliveryCost.getDeliveryCost() * 200;
        }
        else
            totalEpointsValue = Integer.parseInt(totalEpoints);
        dataExchanger.setOrder(new Order(address, items, totalEpointsValue));

        if (!totalEpoints.equalsIgnoreCase("previous_call"))
            dataExchanger.getOrder().getItems().get(0).getEpoints().setUk(totalEpointsValue);

        if (dataExchanger.getoAuth() != null)
            response = usersActions.redemptionOrder(businessId, dataExchanger.getEmail(), dataExchanger.getOrder().toJsonRequest(), dataExchanger.getoAuth().getAccess_token(), status);
        else
            response = usersActions.redemptionOrder(businessId, dataExchanger.getEmail(), dataExchanger.getOrder().toJsonRequest(), status);

        if (status == 200)
            dataExchanger.getOrder().setId(response.toString());

    }

    @Then("^Checkout response - error message will be returned '(.+?)', '(.+?)'$")
    public void validateCheckoutErrorMessage(String error, String message) throws Throwable {
        assertThat("Wrong error ", response.getString("error"), is(error));
        assertThat("Wrong message ", response.getString("message"), containsString(format(message, dataExchanger.getUserProfile().getAccountDetails().getId())));
    }

    @Then("^Redemption order is properly created for '(.+?)'$")
    public void validateRedemptionOrderCreated(String businessId) throws Throwable {

        String pointsManagerUserId = mySQLConnector.getSingleResult("SELECT id FROM points_manager.User where userKey =\"" + dataExchanger.getUuid() + "\"");
        Order order = dataExchanger.getOrder();


        HashMap<Integer, List> redemptionOrders = mySQLConnector.getResult("SELECT * FROM points_manager.RedemptionOrder where id = '" + dataExchanger.getOrder().getId() + "'",
                asList("id", "userId", "name", "address1", "address2", "city", "country", "phone", "postcode", "zone", "region", "businessId"));

        for (List redemptionOrder : redemptionOrders.values()) {
            String orderId = redemptionOrder.get(0).toString();
            String userId = redemptionOrder.get(1).toString();
            String name = redemptionOrder.get(2).toString();
            String address1 = redemptionOrder.get(3).toString();
            String address2 = redemptionOrder.get(4).toString();
            String city = redemptionOrder.get(5).toString();
            String country = redemptionOrder.get(6).toString();
            String phone = redemptionOrder.get(7).toString();
            String postcode = redemptionOrder.get(8).toString();
            String zone = redemptionOrder.get(9).toString();
            String region = redemptionOrder.get(10).toString();
            String businessIdPointsManager = redemptionOrder.get(11).toString();

            assertThat("Order record: improper userId! ", userId, is(pointsManagerUserId));
            assertThat("Order record: improper address1! ", address1, is(order.getAddress().getHouse()));
            assertThat("Order record: improper address2! ", address2, is(order.getAddress().getStreet()));
            assertThat("Order record: improper city! ", city, is(order.getAddress().getCity()));
            assertThat("Order record: improper country! ", country, is(order.getAddress().getCountry()));
            assertThat("Order record: improper postcode! ", postcode, is(order.getAddress().getPostCode()));
            assertThat("Order record: improper zone! ", zone, is("UK"));
            assertThat("Order record: improper region! ", region, is("UK"));
            assertThat("Order record: improper businessId! ", businessIdPointsManager, is(businessId));

            HashMap<Integer, List> redemptionOrderProducts = mySQLConnector.getResult("SELECT * FROM points_manager.Product where orderId = '" + orderId + "' order by id desc",
                    asList("id", "productId", "title", "numPoints", "quantity", "localCurrencyCode", "fulfillStatus", "productScope"));

            for (Product product : order.getItems()) {
                System.out.println(product);
                boolean productFound = false;

                for (List redemptionOrderProduct : redemptionOrderProducts.values()) {
                    System.out.println("PM: " + redemptionOrderProduct.get(1));
                    productFound = redemptionOrderProduct.get(1).equals(product.getId());

                    if (!productFound) continue;

                    assertThat("Product:  improper title!", redemptionOrderProduct.get(2).toString(), is(product.getTitle()));
                    assertThat("Product:  improper numPoints!", redemptionOrderProduct.get(3).toString(), is(String.valueOf(product.getEpoints().getUk())));
                    assertThat("Product:  improper quantity!", redemptionOrderProduct.get(4).toString(), is(String.valueOf(product.getQuantity())));
                    assertThat("Product:  improper localCurrencyCode!", redemptionOrderProduct.get(5).toString(), is("GBP"));
                    assertThat("Product:  improper fulfillStatus!", redemptionOrderProduct.get(6).toString(), is("FULFILL"));
                    assertThat("Product:  improper productScope!", redemptionOrderProduct.get(7).toString(), is(businessId));

                    break;
                }
                assertThat("Not found product with id: " + product.getId(), productFound);
            }
        }
    }

    @Then("^User balance for '(.+?)' is properly reduced$")
    public void validateUserBalanceAfterCheckout(String businessId) throws Throwable {
        int previousConfirmed = userBalance.getConfirmedPoints();
        int previousRedeemed = userBalance.getRedeemedPoints();
        if (dataExchanger.getoAuth() != null)
            userBalance = usersActions.getUserBalance(businessId, dataExchanger.getoAuth().getAccess_token(), 200).getAsObject(UserBalance.class);
        else
            userBalance = usersActions.getUserBalance(businessId, 200).getAsObject(UserBalance.class);
        int currentConfirmed = userBalance.getConfirmedPoints();
        int currentRedeemed = userBalance.getRedeemedPoints();

        assertThat("Balance: Confirmed improperly reduced!", currentConfirmed, is(previousConfirmed - totalEpointsValue));
        assertThat("Balance: Redeemed improperly increased!", currentRedeemed, is(previousRedeemed + totalEpointsValue));

        //to be sure that points of not used scope was not touched
        previousConfirmed = userBalanceForNotUsedScope.getConfirmedPoints();
        previousRedeemed = userBalanceForNotUsedScope.getRedeemedPoints();

        if (dataExchanger.getoAuth() != null) {
            if (businessId.equals("united"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("null", dataExchanger.getoAuth().getAccess_token(), 200).getAsObject(UserBalance.class);
            else if (businessId.equals("null"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("united", dataExchanger.getoAuth().getAccess_token(), 200).getAsObject(UserBalance.class);
        } else {
            if (businessId.equals("united"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("null", 200).getAsObject(UserBalance.class);
            else if (businessId.equals("null"))
                userBalanceForNotUsedScope = usersActions.getUserBalance("united", 200).getAsObject(UserBalance.class);
        }
        currentConfirmed = userBalanceForNotUsedScope.getConfirmedPoints();
        currentRedeemed = userBalanceForNotUsedScope.getRedeemedPoints();

        assertThat("Balance: Confirmed for not used scope was changed but should not!", currentConfirmed, is(previousConfirmed));
        assertThat("Balance: Redeemed for not used scope was changed but should not!", currentRedeemed, is(previousRedeemed));
    }

}