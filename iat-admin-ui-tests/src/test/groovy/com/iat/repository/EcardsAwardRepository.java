package com.iat.repository;

import com.iat.domain.ecardAward;

public interface EcardsAwardRepository {

    String setEcardsAward(ecardAward jsonBody, String username, String password);

}