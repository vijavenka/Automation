package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Partner;
import com.iat.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.get;

@Slf4j
public class PartnerRepositoryImpl implements PartnerRepository {
    @Override
    public Partner findByShortname(String shortname) {
        log.info("GET: {}/partners?shortName={}", Config.getDoormanUrl(), shortname);
        return get(Config.getDoormanUrl() + "/partners?shortName=" + shortname).getBody().as(Partner.class);
    }

}