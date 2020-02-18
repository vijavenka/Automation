package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.PartnersGroup;
import com.iat.repository.PartnersGroupRepository;

import static io.restassured.RestAssured.get;

public class PartnersGroupRepositoryImpl implements PartnersGroupRepository {

    @Override
    public PartnersGroup findByShortName(String shortname) {
        return get(Config.getDoormanUrl() + "/partnersGroup?shortName=" + shortname).getBody().as(PartnersGroup.class);
    }

}