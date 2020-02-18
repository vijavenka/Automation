package com.iat.steps;

import com.iat.actions.TransitionActions;
import com.iat.actions.transactions.TransactionsActions;
import com.iat.domain.transactions.PointsDetails;
import com.iat.utils.DataExchanger;
import com.iat.utils.HelpFunctions;
import cucumber.api.java.en.Given;

import java.util.HashMap;
import java.util.List;

public class TransactionsSteps {

    private TransactionsActions transactionsActions = new TransactionsActions();
    private TransitionActions transitionActions = new TransitionActions();
    private HelpFunctions helpFunctions = new HelpFunctions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    @Given("^User won prize on roulette \\(points value: '(.+?)', points status: '(.+?)'\\) with status: '(.+?)'$")
    public void userWonRoulettePrize(String pointsValue, String prizeStatus, int status) throws Throwable {
        int prizePoints = pointsValue.equalsIgnoreCase("RANDOM") ? helpFunctions.returnRandomValue(1000) : Integer.parseInt(pointsValue);

        HashMap<Integer, List> spin = transitionActions.extractSpinForClickout();
        String spinSpinUuid = spin.get(0).get(0).toString();

        dataExchanger.setPointsDetails(new PointsDetails(prizePoints, prizeStatus, spinSpinUuid));
        transactionsActions.postTransactions(dataExchanger.getPointsDetails(), dataExchanger.getUserProfile().getAccountDetails().getId(), status);

        dataExchanger.setSpinUsed(true);
    }
}
