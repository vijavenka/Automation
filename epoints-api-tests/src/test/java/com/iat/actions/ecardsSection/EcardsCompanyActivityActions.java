package com.iat.actions.ecardsSection;


import com.iat.controller.ecardsSection.EcardsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class EcardsCompanyActivityActions {

    private EcardsController ecardsController = new EcardsController();

    public ResponseContainer getEcardsCompanyActivity(int status) {
        return initResponseContainer(ecardsController.getCompanyActivity(status));
    }
}
