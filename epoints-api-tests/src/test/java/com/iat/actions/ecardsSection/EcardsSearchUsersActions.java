package com.iat.actions.ecardsSection;


import com.iat.controller.ecardsSection.EcardsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class EcardsSearchUsersActions {

    private EcardsController ecardsController = new EcardsController();

    public ResponseContainer getEcardsUsers(String search, int status) {
        return initResponseContainer(ecardsController.getEcardsUsers(search, status));
    }
}
