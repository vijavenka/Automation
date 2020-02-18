package com.iat.steps.redemptionOrderManagement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.accountManagement.AccountBalanceActions;
import com.iat.actions.epointsActions.EpointsActions;
import com.iat.actions.redemptionOrderManagement.RedemptionOrderActions;
import com.iat.actions.redemptionsManagement.RedemptionsActions;
import com.iat.domain.ProductId;
import com.iat.domain.orderRedemption.Order;
import com.iat.domain.orderRedemption.OrderRefund;
import com.iat.domain.orderRedemption.Product;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.ProductRepositoryImpl;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.steps.pointsAllocation.AddPointsSteps;
import com.iat.utils.HelpFunctions;
import com.iat.utils.ResponseContainer;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.redemptionOrderManagement.RedemptionOrderValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RedemptionOrderSteps {

    private RedemptionOrderActions redemptionOrderActions = new RedemptionOrderActions();
    private AccountBalanceActions accountBalanceActions = new AccountBalanceActions();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private EpointsActions epointsActions = new EpointsActions();
    private ObjectMapper mapper = new ObjectMapper();
    private AddPointsSteps addPointsSteps = new AddPointsSteps();
    private RedemptionOrderValidator redemptionOrderValidator = new RedemptionOrderValidator();
    private RedemptionsActions redemptionsActions = new RedemptionsActions();
    private HelpFunctions helpFunctions = new HelpFunctions();
    private ResponseContainer response;
    private ResponseContainer userBalanceResponse;
    private ErrorsValidator errorsValidator = new ErrorsValidator();

    @Given("^Redemption item for redemption order is selected '(.+?)' '(.+?)'$")
    public void selectDynamicallyRedemptionProductAndCreateOrderJsonBody(String orderJsonBody, String userParams) throws Throwable {
        responseHolder.setUserIdType(userParams.split(",")[1]);
        if (responseHolder.getUserIdType().equals("UUID"))
            responseHolder.setUserId(userRepository.findByEmail(userParams.split(",")[0]).getUuid());
        else if (responseHolder.getUserIdType().equals("email"))
            responseHolder.setUserId(userParams.split(",")[0]);
        responseHolder.setUserId(userRepository.findByEmail(userParams.split(",")[0]).getUuid());


        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Order order = mapper.readValue(orderJsonBody, Order.class);

        response = epointsActions.getRedemptionProductList(200);
        String resultsPath = "results[" + (response.getList("results").size() - 1) + "].";
        response = epointsActions.getRedemptionIndividualProduct(response.getString(resultsPath + "seoSlug"), response.getString(resultsPath + "id"), 200);
        responseHolder.setOrder(completeOrderBodyByAddingProductData(order, response));

        //we also need to add points to make redemption order, it will be done here
        int confirmedPoints = accountBalanceActions.getAccountBalance(responseHolder.getUserId(), "UUID", 200).get("confirmedPoints");
        if (confirmedPoints < responseHolder.getOrder().getProducts().get(0).getNumPoints()) {
            String addPointsJsonBody = "{\"numPoints\":\"" + responseHolder.getOrder().getProducts().get(0).getNumPoints() + "\",\"autoConfirm\":\"1\",\"clientId\":\"ePoints.com\",\"onBehalfOfId\":\"OnBehalfOfId\", \"apiAccessKey\":\"envDepends\", \"tag\":\"epointsBuy\",\"pointsType\":\"CONFIRMED\",\"externalTransactionId\":\"epoints purchased\",\"reasonText\":\"automatedTestsTransactionReasonText\",\"sourceUri\":\"google.com\",\"merchantName\":\"merchantName\"} ";
            addPointsSteps.addPointsToUser(addPointsJsonBody);
        }

        userBalanceResponse = accountBalanceActions.getAccountBalance(responseHolder.getUserId(), "UUID", 200);
    }

    private Order completeOrderBodyByAddingProductData(Order order, ResponseContainer redemptionItemData) throws IOException {

        Product product = mapper.readValue("{}", Product.class);

        product.setProductId(redemptionItemData.getString("id"));
        product.setImageUrl(redemptionItemData.getString("imageUrl"));
        product.setTitle(redemptionItemData.getString("title"));

        product.setNumPoints(redemptionItemData.getInt("epoints.UK"));

        product.setQuantity(1);
        product.setMerchantName(redemptionItemData.getString("merchantName"));
        product.setMerchantId(redemptionItemData.getString("merchantId"));
        product.setDepartment(redemptionItemData.getString("department"));
        product.setBrand(redemptionItemData.getString("brand"));

        //TODO commented text has to be used  - single category available now on epoints side
        List<String> temporaryList = redemptionItemData.getList("categories", String.class);
        product.setCategories(temporaryList);

        if (redemptionItemData.get("localCurrencyValue") != null)
            product.setLocalCurrencyValue(redemptionItemData.getFloat("localCurrencyValue"));
        product.setCurrency(redemptionItemData.getString("currency"));
        if (redemptionItemData.get("conversionRate") != null)
            product.setConversionRate(redemptionItemData.getInt("conversionRate"));

        List<Product> prod = new ArrayList<>();
        prod.add(product);
        order.setProducts(prod);

        return order;
    }

    @Given("^Create new redemption order call is made with data '(.+?)'$")
    public void createNewRedemptionOrder(String params) throws Throwable {
        String email = params.split(",")[0];
        response = redemptionOrderActions.createRedemptionOrder(params.replace(email, userRepository.findByEmail(email).getUuid()), mapper.writeValueAsString(responseHolder.getOrder()), 201);
        responseHolder.setOrderTransactionId(response.toString());
    }

    @Given("^Create new redemption order response returns order Id$")
    public void createNewRedemptionOrderReturnsOrderId() throws Throwable {
        assertThat("Missing order Id.", Integer.parseInt(response.toString()), is(greaterThan(0)));
    }

    @When("^Get redemption order details by id call is made with provided apiKey '(.+?)' and orderId$")
    public void getRedemptionOrderByApiKeyAndyId(String params) throws Throwable {
        String apiKey = params.split(",")[2];
        String extractedOrderId = response.toString();

        response = redemptionOrderActions.getRedemptionOrder(apiKey + "," + extractedOrderId, 200);
    }

    @Then("^Get redemption order details by id response data is same as provided$")
    public void getRedemptionOrderByApiKeyAndyIdIsSameAsProvidedData() throws Throwable {
        Order order = response.getAsObject(Order.class);
        //TODO bug here
        assertThat("Orders should be equal.", order, is(responseHolder.getOrder()));
    }

    @Then("^User balance after Create new redemption order call is properly reduced$")
    public void checkIfUserBalanceIsReducedAfterRedemptionOrder() throws Throwable {
        redemptionOrderValidator.checkUserBalanceAfterRedemptionOrder(userBalanceResponse, accountBalanceActions.getAccountBalance(responseHolder.getUserId(), "UUID", 200), responseHolder.getOrder());
    }

    @When("^Redemption transaction can be properly refunded$")
    public void refundRecentOrderTransaction() throws Throwable {

        List<ProductId> productsIds = new ProductRepositoryImpl().getProductsByOrderId(responseHolder.getOrderTransactionId());

        for (ProductId id : productsIds) {
            mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
            OrderRefund orderRefund = mapper.readValue("{\"id\":\"" + id.getId() + "\",\"activityInfo\":\"Automated tests transaction refund\",\"refundDate\":\"" + helpFunctions.returnEpochOfCurrentDay() + "\"}", OrderRefund.class);

            redemptionsActions.createRedemptionsRefund("envDepends", mapper.writeValueAsString(orderRefund), 200);
            System.out.println(responseHolder.getOrderTransactionId());
            System.out.println(id.getId());
        }
    }

    @When("^Create new redemption order call is made with following data '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void createNewRedemptionOrderErrorResponse(String params, String orderJsonBody, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        String email = params.split(",")[0];
        params = params.replace(email, userRepository.findByEmail(email).getUuid());

        if (!orderJsonBody.equals("{}")) {
            mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
            Order order = mapper.readValue(orderJsonBody, Order.class);
            response = epointsActions.getRedemptionProductList(200);
            response = epointsActions.getRedemptionIndividualProduct(response.getString("results[0].seoSlug"), response.getString("results[0].id"), 200);

            response = redemptionOrderActions.createRedemptionOrder(params, mapper.writeValueAsString(completeOrderBodyByAddingProductData(order, response)), status);
        } else
            response = redemptionOrderActions.createRedemptionOrder(params, orderJsonBody, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, redemptionOrderActions.createErrorMessage(params, expErrorCode, expErrorMsg));
    }

    @Then("^Create new redemption order response for request without proper data should be empty$")
    public void createNewRedemptionOrderResponseIsEmpty() throws Throwable {
        assertThat("Response should be null!", response.toString(), isEmptyOrNullString());
    }

    //Get redemption order details and check response contract
    @When("^Get redemption order details by id call is made with data '(.+?)'$")
    public void getRedemptionOrderById(String params) throws Throwable {
        params = params.replace("dynamically", responseHolder.getOrderTransactionId());
        response = redemptionOrderActions.getRedemptionOrder(params, 200);
    }

    @Then("^Get redemption order details by id response match contract$")
    public void getRedemptionOrderByIdResponseMatchContract() throws Throwable {
        //contract check is done in controller class after refactoring
    }


    //Get redemption order details - system message validation
    @When("^Get redemption order details by id call is made with following data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getRedemptionOrderByIdErrorResponse(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        params = params.replace("dynamically", responseHolder.getOrderTransactionId()).replace("bdl", Config.getBdlPartnerAccessKey());
        response = redemptionOrderActions.getRedemptionOrder(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, params.split(",")[0]));
    }

    @Then("^Get redemption order details by id response for request without proper data should be empty$")
    public void getRedemptionOrderByIdIsEmpty() throws Throwable {
        assertThat("Response should be null!", response.toString(), isEmptyOrNullString());
    }

    //Get recently redeemed list and check response contract
    @When("^Get recently redeemed call is made with data '(.+?)'$")
    public void getRecentlyRedeemed(String params) throws Throwable {
        response = redemptionOrderActions.getRecentlyRedeemed(params, 200);
    }

    @Then("^Get recently redeemed response have proper results count for provided data '(.+?)'$")
    public void getRecentlyRedeemedResponseMatchContract(String params) throws Throwable {
        int limit = Integer.parseInt(params.split(",")[4]);
        assertThat("Results count is different than provided limit", response.getList("redeems"), hasSize(allOf(
                lessThanOrEqualTo(limit),
                equalTo(response.getInt("searchResultsCount")))));
    }

    //Get recently redeemed list - system message validation
    @When("^Get recently redeemed call is made with following data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getRecentlyRedeemedErrorResponse(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = redemptionOrderActions.getRecentlyRedeemed(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, format(expErrorMsg, params.split(",")[0]));
    }

    @Then("^Get recently redeemed response for request without proper data should be empty$")
    public void getRecentlyRedeemedResponseIsEmpty() throws Throwable {
        assertThat("Response should be null!", response.toString(), isEmptyOrNullString());
    }

    //Get redemption order history and check response contract
    @When("^Get redemption order history call is made with data '(.+?)'$")
    public void getRedemptionOrderHistory(String params) throws Throwable {
        response = redemptionOrderActions.getRedemptionOrderHistory(params, 200);
    }

    @Then("^Get redemption order history response have proper results count for provided data '(.+?)'$")
    public void checkRedemptionHistoryCorrectness(String params) throws Throwable {
        redemptionOrderValidator.checkRedemptionHistoryCorrectness(params, response);
    }

    //Get redemption order history - system message validation
    @When("^Get redemption order history call is made with following data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getRedemptionOrderHistoryErrorResponse(String params, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = redemptionOrderActions.getRedemptionOrderHistory(params, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, redemptionOrderActions.createErrorMessage(params, expErrorCode, expErrorMsg));
    }

    @Then("^Get redemption order history response for request without proper data should be empty$")
    public void getRedemptionOrderHistoryResponseIsEmpty() throws Throwable {
        assertThat("Response should be null!", response.toString(), isEmptyOrNullString());
    }
}