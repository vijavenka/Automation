package com.iat.actions.ecardsSection;


import com.iat.controller.ecardsSection.EcardsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class EcardsIndividualEcardActions {

    private EcardsController ecardsController = new EcardsController();

    public ResponseContainer getIndividualEcardDetails(String ecardId, int status) {
        return initResponseContainer(ecardsController.getIndividualEcardDetails(ecardId, status));
    }


}
