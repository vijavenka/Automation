package com.iat.actions.ecardsSection;


import com.iat.controller.ecardsSection.EcardsController;
import com.iat.domain.Ecards.EcardsSent;
import com.iat.utils.ResponseContainer;

import java.util.ArrayList;
import java.util.List;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.lang.Integer.parseInt;

public class EcardsSendEcardToUsersActions {

    private EcardsController ecardsController = new EcardsController();


    public ResponseContainer getEcardsReasonsList(int status) {
        return initResponseContainer(ecardsController.getEcardsReasonsList(status));
    }

    public String getEcardsReasonIdByName(String reasonName) {
        return getEcardsReasonsList(200).getString("find{it.name == '" + reasonName + "'}.id");
    }

    public ResponseContainer getEcardsTemplatesList(int status) {
        return initResponseContainer(ecardsController.getEcardsTemplatesList(status));
    }

    public ResponseContainer sendEcard(EcardsSent ecardsSentObject, int status) {
        return initResponseContainer(ecardsController.sendEcard(ecardsSentObject, status));
    }

    public List<String> getDynamicUsersUuids(List<String> usersKey) {
        List<String> users = new ArrayList<>();

        if (usersKey.get(0).equalsIgnoreCase("DYNAMIC")) {
            int howMany = parseInt(usersKey.get(1));
            users = new EcardsSearchUsersActions().getEcardsUsers("api", 200).getList("results.uuid");
            users = users.subList(0, howMany);
        }
        return users;
    }
}
