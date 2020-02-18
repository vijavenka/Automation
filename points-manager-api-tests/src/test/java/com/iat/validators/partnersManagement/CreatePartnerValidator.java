package com.iat.validators.partnersManagement;

import com.iat.repository.PartnerRepository;
import com.iat.repository.PartnersGroupRepository;
import com.iat.repository.impl.PartnerRepositoryImpl;
import com.iat.repository.impl.PartnersGroupRepositoryImpl;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class CreatePartnerValidator {

    private PartnerRepository partnerRepository;
    private PartnersGroupRepository partnersGroupRepository;

    public CreatePartnerValidator() {

        partnerRepository = new PartnerRepositoryImpl();
        partnersGroupRepository = new PartnersGroupRepositoryImpl();
    }

    public void validatePartnersBulkUploadCorrectness(List<String> partnersData, String groupShortName) {
        String partnerName, partnerShortName, partnerExternalId, providedPointsManagerGroupId, pointsManagerGroupId, pointsManagerPartnerName, pointsManagerExternalId;
        for (String partner : partnersData) {
            partnerName = partner.split(";")[0];
            partnerShortName = partner.split(";")[1];
            partnerExternalId = partner.split(";")[2];

            providedPointsManagerGroupId = partnersGroupRepository.findByShortName(groupShortName).getId();
            pointsManagerPartnerName = partnerRepository.findByShortname(partnerShortName).getName();
            pointsManagerExternalId = partnerRepository.findByShortname(partnerShortName).getExternalId();
            pointsManagerGroupId = partnerRepository.findByShortname(partnerShortName).getGroupId();

            assertThat("Incorrect partner name!", partnerName, is(pointsManagerPartnerName));
            assertThat("Incorrect partner externalId!", partnerExternalId, is(pointsManagerExternalId));
            assertThat("Incorrect partner groupId!", providedPointsManagerGroupId, is(pointsManagerGroupId));
        }
    }

    public void validatePartnersBulkUploadErrorMessage(ResponseContainer response, String error, String message, String items) {
        assertThat("Wrong error!", response.getString("error"), is(error));
        assertThat("Wrong message!", response.getString("message"), is(message));
        if (!items.equals("null"))
            assertThat("Wrong items!", response.get("items").toString(), is(items));
    }

    public void validatePartnersWereRolledBack(List<String> partnersData) {
        for (String partner : partnersData) {
            String partnerShortName = partner.split(";")[1];
            assertThat("Partner(shortName): " + partnerShortName + " should be rolled back and not available in points manager", partnerRepository.findByShortname(partnerShortName).getName(), is(nullValue()));
        }
    }
}