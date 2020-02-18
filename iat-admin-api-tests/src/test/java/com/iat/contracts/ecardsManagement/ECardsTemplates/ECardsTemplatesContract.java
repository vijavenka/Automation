package com.iat.contracts.ecardsManagement.ECardsTemplates;


public class ECardsTemplatesContract {

    public String getAllTemplatesPath() {
        return "/api/ecards/templates";
    }

    public String getValidTemplatesPath() {
        return "/api/ecards/templates/valid";
    }


    public String getSingleTemplatesPath(String templateId) {
        return "/api/ecards/templates/" + templateId;
    }

    public String createTemplatesPath() {
        return "/api/ecards/templates";
    }

    public String updateTemplatesPath(String templateId) {
        return "/api/ecards/templates/" + templateId;
    }

    public String deleteTemplatesPath(String templateId) {
        return "/api/ecards/templates/" + templateId;
    }

}