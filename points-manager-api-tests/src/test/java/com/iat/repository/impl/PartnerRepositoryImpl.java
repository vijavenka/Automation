package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Partner;
import com.iat.repository.PartnerRepository;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

public class PartnerRepositoryImpl implements PartnerRepository {
    @Override
    public Partner findByShortname(String shortname) {
        System.out.println("\nGET: " + Config.getDoormanUrl() + "/partners?shortName=" + shortname);
        return get(Config.getDoormanUrl() + "/partners?shortName=" + shortname).getBody().as(Partner.class);
    }

    @Override
    public void deletePartnerByShortname(String shortname) {
        System.out.println("\nDELETE: " + Config.getDoormanUrl() + "/partners?shortName=" + shortname);
        delete(Config.getDoormanUrl() + "/partners?shortName=" + shortname);
    }

    @Override
    public void deleteExternalPartnerAndRelations(String externalId) {
        System.out.println("\nDELETE: " + Config.getDoormanUrl() + "/partners/externalId/" + externalId);
        delete(Config.getDoormanUrl() + "/partners/externalId/" + externalId);
    }

    public static void main(String[] args) {
        System.out.println(new PartnerRepositoryImpl().findByShortname("bdl"));
    }
}
