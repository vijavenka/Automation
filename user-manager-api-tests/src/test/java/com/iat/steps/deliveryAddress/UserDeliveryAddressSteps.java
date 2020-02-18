package com.iat.steps.deliveryAddress;

import com.iat.actions.deliveryAddress.UserDeliveryAddressActions;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserDeliveryAddressSteps {

    private UserDeliveryAddressActions deliveryAddressActions = new UserDeliveryAddressActions();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private UserRepository userRepository = new UserRepositoryImpl();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();

    //Scenario Outline: User delivery address details - check response contract
    @When("^User calls for delivery address with '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void getDeliveryAddressForUser(String userId, String apiKey, String limit, String offset) throws Throwable {
        responseHolder.setResponse(deliveryAddressActions.getDeliveryAddress(userRepository.findByEmail(userId).getUuid(), apiKey, limit, offset, 200));
    }

    @Then("^User delivery address is returned and matches the contract$")
    public void verifyUserDeliveryAddressResponseWithContract() throws Throwable {
        //validator is in controller
        //contractValidator.validateResponseWithContract("deliveryAddress/deliveryAddressList-response-schema.json", responseHolder.getResponse());
        //TODO: Add assertion for offset and limit - page and pageSize validation
    }

    //Scenario Outline: User delivery address details - system message validation
    @When("^Call for user delivery address is made with incorrect data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void getDeliveryAddressErrorResponse(String userId, String apiKey, String limit, String offset, int expResponseCode) throws Throwable {
        responseHolder.setResponse(
                deliveryAddressActions.getDeliveryAddress(userId.equals("123456") ? "123456" : userRepository.findByEmail(userId).getUuid(), apiKey, limit, offset, expResponseCode));
    }

    @Then("^Response should contain error information '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getDeliveryAddressResponseIsEmpty(String userId, String apiKey, int expResponseCode, String expErrorCode, String expErrorMsg) throws Throwable {
        errorsValidator.validateErrorResponse(
                responseHolder.getResponse(), expResponseCode, expErrorCode, apiKey.equals("accessKey") || apiKey.equals("xHNZaBGQtDmxTkrnI7NOfoXkz") ? String.format(expErrorMsg, userId.equals("123456") ? "123456" : userRepository.findByEmail(userId).getUuid()) : String.format(expErrorMsg, apiKey), true);
    }

    //Scenario Outline: User delivery address details - add new address
    @When("^'(.+?)' with '(.+?)' adds new '(.+?)'$")
    public void addDeliveryAddress(String userId, String apiKey, String deliveryAddressParams) throws Throwable {
        responseHolder.setResponse(deliveryAddressActions.addDeliveryAddress(userRepository.findByEmail(userId).getUuid(), apiKey, deliveryAddressParams, 201));
    }

    @Then("^Response of delivery address creation match contract$")
    public void verifyResponseContractOfNewAddressCreation() throws Throwable {
        //validator is in controller
        //contractValidator.validateResponseWithContract("deliveryAddress/deliveryAddressCreation-response-schema.json", responseHolder.getResponse());
    }

    //Scenario Outline: Add user delivery address details - system message validation
    @When("^Call to add delivery address is made with incorrect data data '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void callCreateDeliveryAddressWithWrongParams(String userId, String apiKey, String deliveryAddressParams, int expResponseCode) throws Throwable {
        responseHolder.setResponse(deliveryAddressActions.addDeliveryAddress(userId.equals("123456") ? "123456" : userRepository.findByEmail(userId).getUuid(), apiKey, deliveryAddressParams, expResponseCode));
    }
}
