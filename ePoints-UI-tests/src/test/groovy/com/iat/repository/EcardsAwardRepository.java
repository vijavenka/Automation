package com.iat.repository;

import com.iat.domain.EcardAward;

public interface EcardsAwardRepository {

    String setEcardsAward(EcardAward jsonBody, String username, String password);

}