package com.iat.steps;

import com.iat.actions.BasketActions;
import com.iat.domain.basket.Basket;
import com.iat.domain.basket.ProductInBasket;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BasketSteps {

    private BasketActions basketActions = new BasketActions();
    private ResponseContainer response;
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    @When("^'(.+?)' redemption item is added '(.+?)' to basket$")
    public void addRedemptionItemToBasket(int redemptionItemNumber, int quantity) throws Throwable {
        if (dataExchanger.getoAuth() != null)
            response = basketActions.getBasket(dataExchanger.getUuid(), dataExchanger.getoAuth().getAccess_token(), 200);
        else
            response = basketActions.getBasket(dataExchanger.getUuid(), 200);

        String redemptionItemId = dataExchanger.getRedemptionItem(redemptionItemNumber).getId();

        Basket basket = new Basket();
        basket.setItems(singletonList(new ProductInBasket(redemptionItemId, quantity)));

        /*List<String> redemptionItemId = new LinkedList<String>();
        List<ProductInBasket> productInBasketList = new LinkedList<ProductInBasket>();
        Basket basket = new Basket();

        for(int i=0; i<=redemptionItemNumber; i++) {
            redemptionItemId.add(dataExchanger.getRedemptionItem(i).getId());
            productInBasketList.add(new ProductInBasket(redemptionItemId.get(i), quantity));
            basket.setItems(singletonList(productInBasketList));
        }

        String strBasket = null;
        for(int i=0; i<=redemptionItemNumber; i++) {
            strBasket = basket.toJsonRequest().replace("[ [", "[").replace("] ]", "]");
        }*/

        if (dataExchanger.getoAuth() != null)
            response = basketActions.updateBasket(dataExchanger.getUuid(), basket, dataExchanger.getoAuth().getAccess_token());
        else
            response = basketActions.updateBasket(dataExchanger.getUuid(), basket);
    }

    @Then("^Basket is properly updated and contains '(.+?)' redemption items count '(.+?)'$")
    public void validateRedemptionItemsInBasket(int redemptionItemNumber, int quantity) throws Throwable {
        String redemptionItemId = dataExchanger.getRedemptionItem(redemptionItemNumber).getId();

        response = basketActions.getBasket(dataExchanger.getUuid(), 200);
        List<Integer> quantityOfItem = response.getList("items.findAll {it.id == '" + redemptionItemId + "'}.quantity");

        if (quantity == 0) {
            assertThat("Redemption item [" + redemptionItemId + "] should not be in the basket", quantityOfItem, is(empty()));
            return;
        }

        assertThat("Redemption item [" + redemptionItemId + "] should be in the basket", quantityOfItem, hasSize(1));
        assertThat("quantity value incorrect", quantityOfItem, hasItem(quantity));
    }
}
