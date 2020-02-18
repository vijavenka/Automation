package com.iat.actions.paymentsManagement;

import com.iat.controller.paymentsManagement.PaymentsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class PaymentsActions {

    private PaymentsController paymentsController = new PaymentsController();

    public ResponseContainer createNewPaymentInSystem(String apiKey, String jsonParameters, int status) {
        return initResponseContainer(paymentsController.createNewPaymentInSystem(apiKey, jsonParameters, status));
    }

    public ResponseContainer getDetailsOfSelectedPayment(String apiKey, String guid, int status) {
        return initResponseContainer(paymentsController.getDetailsOfSelectedPayment(apiKey, guid, status));
    }

    public ResponseContainer updatePaymentInSystem(String apiKey, String guid, String jsonParameters, int status) {
        return initResponseContainer(paymentsController.updatePaymentInSystem(apiKey, guid, jsonParameters, status));
    }

    public ResponseContainer updateSelectedPaymentDetailsUsingInvalidParameters(String apiKey, String guid, int status, String expErrorCode, String expErrorMsg) {
        return initResponseContainer(paymentsController.updateSelectedPaymentDetailsUsingInvalidParameters(apiKey, guid, status, expErrorCode, expErrorMsg));
    }

}