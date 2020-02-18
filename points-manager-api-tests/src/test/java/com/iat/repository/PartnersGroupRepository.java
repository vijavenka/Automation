package com.iat.repository;

import com.iat.domain.PartnersGroup;

public interface PartnersGroupRepository {
    PartnersGroup findByShortName(String shortName);
}