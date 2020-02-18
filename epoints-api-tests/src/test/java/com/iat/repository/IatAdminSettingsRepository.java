package com.iat.repository;

import com.iat.domain.EcardsSettingsPointsSharing;
import com.iat.domain.EcardsSettingsTemplates;
import com.iat.utils.ResponseContainer;

public interface IatAdminSettingsRepository {

    void setEcardsPointsSharing(EcardsSettingsPointsSharing settings, String credentials);

    void setEcardsTemplates(EcardsSettingsTemplates settings, String credentials);

    ResponseContainer getDepartmentsStructure(String withRoot, String credentials);

}
