package com.iat.steps;

import com.iat.actions.TransitionActions;
import com.iat.domain.TransitionTo;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.TransitionValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TransitionSteps {

    private TransitionActions transitionActions = new TransitionActions();
    private TransitionValidator transitionValidator = new TransitionValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;
    private TransitionTo transitionTo;

    @When("^Clickout is generated and properly stored for merchantId '(.+?)'$")
    public void generateClickoutStoreAndValidate(String merchantId) throws Throwable {
        transitionDetailsForMerchant(merchantId);
        clickoutRequestByUsingTransitionRedirectUrl();
        validateClickoutWasProperlyStored();
    }

    @When("^Transition details are requested for merchantId '(.+?)'$")
    public void transitionDetailsForMerchant(String merchantId) throws Throwable {
        response = transitionActions.getTransitionTo(merchantId, 200);
        transitionTo = response.getAsObject(TransitionTo.class);
        dataExchanger.setTransitionTo(transitionTo);
        dataExchanger.getTransitionTo().setMerchantId(merchantId);

    }

    @When("^Transition details are requested for offerId '(.+?)'$")
    public void transitionDetailsForOffer(String offerId) throws Throwable {
        response = transitionActions.getTransitionToLeads(offerId, 200);
    }

    @Then("^Transition details response is consistent with contract$")
    public void validateTransitionDetailsContract() throws Throwable {
        //todo what is the point of this step? Contract validator is in controller
        //contractValidator.validateResponseWithContract("/transition/GET-transition-to.json", response.toString());
    }

    @Then("^Transition details for lead contains proper static field values$")
    public void transition_details_for_lead_contains_proper_static_field_values() throws Throwable {
        assertThat("Multiplier for lead offer is invalid!", response.getString("multiplier"), is("0"));
        assertThat("leadGenerator flag is incorrect!:", response.getString("leadGenerator"), is("true"));
    }

    @Then("^Transition details for lead '(.+?)' merchant contains proper static field values$")
    public void validateTransitionDetailsLEadMerchantStaticFieldsValues(String isLead) throws Throwable {
        if (isLead.equals("true")) {
            assertThat("Multiplier for lead offer is invalid!", response.getInt("multiplier"), is(0));
            assertThat("Multiplier for lead offer is invalid!", response.getString("maximumCommission"), is(notNullValue()));
            assertThat("leadGenerator flag is incorrect!:", response.getBoolean("leadGenerator"));
        } else {
            assertThat("Multiplier for lead offer is invalid!", response.getInt("multiplier"), is(not(0)));
            assertThat("Multiplier for lead offer is invalid!", response.getString("maximumCommission"), is(nullValue()));
            assertThat("leadGenerator flag is incorrect!:", !response.getBoolean("leadGenerator"));
        }
    }

    @When("^Clickout request is made by using transition details redirectUrl$")
    public void clickoutRequestByUsingTransitionRedirectUrl() throws Throwable {
        transitionActions.getClickout(dataExchanger.getTransitionTo().getRedirectUrl(), 200);
    }

    @Then("^Clickout was properly stored in database$")
    public void validateClickoutWasProperlyStored() throws Throwable {
        transitionActions.extractClickoutFromDbAndStore(null);
        transitionValidator.validateClickoutProperlyCreated();

    }

    @When("^Clickout reference is updated \\(affiliateNetworkId '(.+?)', merchantId '(.+?)', clickoutStatus '(.+?)'\\) and reports are triggered$")
    public void updateClickoutReferenceAndValidate(String affiliateNetworkId, String merchantId, String clickoutStatus) throws Throwable {

        List liveClickout = transitionActions.referenceForLiveClickout(affiliateNetworkId, merchantId, clickoutStatus);
        transitionActions.updateClickoutReference(liveClickout.get(0).toString());
        if (!clickoutStatus.equals("PENDING")) {
            transitionActions.updateClickoutTransactionDetails(liveClickout.get(1).toString(), liveClickout.get(2).toString());

            //awin reports not return CONFIRMATION status at all
            if ((affiliateNetworkId.equals("d8fcae82-e536-4721-8d4d-7eec532eedc3") || affiliateNetworkId.equals("d8fcae82-e536-4721-8d4d-7eec532eedd2"))
                    && clickoutStatus.equals("CONFIRMED"))
                transitionActions.updateClickoutForXDaysToConfirm();
        }

        sleep(2000);

        if (affiliateNetworkId.equals("d8fcae82-e536-4721-8d4d-7eec532eedc3"))
            transitionActions.triggerAffiliateManagerReportsFor("awin", 200);

        if (affiliateNetworkId.equals("d8fcae82-e536-4721-8d4d-7eec532eedd2"))
            transitionActions.triggerAffiliateManagerReportsFor("comjunction", 200);

        transitionActions.waitForClickoutToUpdate(dataExchanger.getClickoutDb().getId(), clickoutStatus);
        transitionActions.extractClickoutFromDbAndStore(dataExchanger.getClickoutDb().getId());

        if (clickoutStatus.equals("DECLINED"))
            sleep(5000);

    }

    @Then("^Clickout & Points statuses are properly changed into '(.+?)'$")
    public void clickoutStatusValidation(String clickoutStatusToValidate) throws Throwable {

        System.out.println("Clickout: " + dataExchanger.getClickoutDb());

        switch (clickoutStatusToValidate) {
            case "PENDING":
                transitionValidator.validateClickoutPendingStatus();
                transitionValidator.validateClickoutOriginalPointsPendingStatus();
                transitionValidator.validateClickoutSpinAndSpunPointsPendingStatus();
                break;
            case "DECLINED":
                transitionValidator.validateClickoutAndSpinAndPoints_declinedConfirmedStatus("DECLINED");
                break;
            case "CONFIRMED":
                transitionValidator.validateClickoutAndSpinAndPoints_declinedConfirmedStatus("CONFIRMED");
                break;
        }
    }

    @Then("^Points in '(.+?)' status were added to user account$")
    public void checkIfPointsWereAddedToUserAccount(String status) throws Throwable {
        transitionValidator.checkIfPointsWereAddedToUserAccountAfterClickoutAndSpin(status);
    }
}
