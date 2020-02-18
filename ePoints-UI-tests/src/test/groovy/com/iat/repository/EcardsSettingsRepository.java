package com.iat.repository;

import com.iat.domain.EcardsSettings;

public interface EcardsSettingsRepository {

    void setEcardsPointsSharing(EcardsSettings settings, String credentials);

    String getAllReasons(String credentials);
}
