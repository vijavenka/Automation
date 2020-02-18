package com.iat.repository;

import com.iat.domain.Partner;

public interface PartnerRepository {
    Partner findByShortname(String shortname);

    void deletePartnerByShortname(String shortname);

    void deleteExternalPartnerAndRelations(String externalId);
}