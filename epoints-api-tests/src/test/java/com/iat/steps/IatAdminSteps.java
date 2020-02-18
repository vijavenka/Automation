package com.iat.steps;

import com.iat.Config;
import com.iat.domain.EcardsSettingsPointsSharing;
import com.iat.domain.EcardsSettingsTemplates;
import com.iat.repository.IatAdminSettingsRepository;
import com.iat.repository.impl.IatAdminSettingsRepositoryImpl;
import cucumber.api.java.en.Given;


public class IatAdminSteps {

    private IatAdminSettingsRepository IatAdminSettingsRepository = new IatAdminSettingsRepositoryImpl();


    @Given("^IAT Admin partner eCards settings for pointsSharing are '(.+?)'$")
    public void setEcardsGlobalSettingsInIATAdmin(String pointsSharing) throws Throwable {
        if (!pointsSharing.equals("null")) {
            EcardsSettingsPointsSharing settings = new EcardsSettingsPointsSharing(pointsSharing, "SAME_COMPANY", "true", "NONE");
            IatAdminSettingsRepository.setEcardsPointsSharing(settings, Config.getSuperAdminCredentialsForIAT_Admin());
        }
    }

    //Check if it's possible to get list of templates
    @Given("^IAT Admin partner eCards settings for templates are '(.+?)'$")
    public void setEcardsGlobalSettingsInIATAdminTemplates(String templates) throws Throwable {
        if (!templates.equals("null")) {
            EcardsSettingsTemplates settings = new EcardsSettingsTemplates(templates);
            IatAdminSettingsRepository.setEcardsTemplates(settings, Config.getSuperAdminCredentialsForIAT_Admin());
        }
    }
}
