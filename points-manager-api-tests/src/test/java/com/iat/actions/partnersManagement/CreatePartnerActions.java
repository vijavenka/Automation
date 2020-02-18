package com.iat.actions.partnersManagement;


import com.iat.controller.partnersManagement.CreatePartnerController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class CreatePartnerActions {

    private CreatePartnerController createPartnerController = new CreatePartnerController();

    public ResponseContainer createNewPartner(String name, String siteURL, String description, String email, String logoURL, String group, int status) {
        return initResponseContainer(createPartnerController.createNewPartner(name, siteURL, description, email, logoURL, group, status));
    }

    public ResponseContainer bulkUploadPartners(String groupShortName, String googleDocId, int status) {
        return initResponseContainer(createPartnerController.bulkUploadPartners(groupShortName, googleDocId, status));
    }
}
