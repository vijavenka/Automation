package com.iat.actions.ecardsManagement.ECardsTemplates;


import com.iat.controller.ecardsManagement.ECardsTemplates.ECardsTemplatesController;
import com.iat.domain.EcardsConfig.Template;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class ECardsTemplatesActions {

    private ECardsTemplatesController eCardsTemplatesController = new ECardsTemplatesController();

    public ResponseContainer listAllTemplates(int status) {
        return initResponseContainer(eCardsTemplatesController.listAllTemplates(status));
    }

    public ResponseContainer listValidTemplates(int status) {
        return initResponseContainer(eCardsTemplatesController.listValidTemplates(status));
    }

    public ResponseContainer getTemplate(String templateId, int status) {
        return initResponseContainer(eCardsTemplatesController.getTemplate(templateId, status));
    }

    public ResponseContainer addNewTemplate(Template template, int status) {
        return initResponseContainer(eCardsTemplatesController.addNewTemplate(template, status));
    }

    public ResponseContainer updateTemplate(String templateId, Template jstemplatenBody, int status) {
        return initResponseContainer(eCardsTemplatesController.updateTemplate(templateId, jstemplatenBody, status));
    }

    public ResponseContainer deleteTemplate(String templateId, int status) {
        return initResponseContainer(eCardsTemplatesController.deleteTemplate(templateId, status));
    }
}