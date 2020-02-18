package com.iat.actions.ecardsSection;


import com.iat.controller.ecardsSection.EcardsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

public class EcardsHistoryActions {

    private EcardsController ecardsController = new EcardsController();


    public ResponseContainer getEcardsHistory(String sentOrReceived, int status) {
        assertThat("Wrong sent/received parameter!", sentOrReceived, isOneOf("sent", "received"));
        return initResponseContainer(ecardsController.getEcardsHistory(sentOrReceived, status));
    }

}
