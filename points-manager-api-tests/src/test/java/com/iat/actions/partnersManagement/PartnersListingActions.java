package com.iat.actions.partnersManagement;

import com.iat.controller.partnersManagement.PartnersListingController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PartnersListingActions {

    private PartnersListingController partnersListingController = new PartnersListingController();

    public ResponseContainer getDefaultPartnerList(int status) {
        return initResponseContainer(partnersListingController.getDefaultPartnersList(status));
    }

    public ResponseContainer getPartnersListForPageSize(int page, int size, int status) {
        return initResponseContainer(partnersListingController.getPartnersListForPageSize(page, size, status));
    }

    public ResponseContainer getPartnersListByShortName(String shortName, int status) {
        return initResponseContainer(partnersListingController.getPartnersListByShortName(shortName, status));
    }

    public ResponseContainer getPartnersListByName(String name, int status) {
        return initResponseContainer(partnersListingController.getPartnersListByName(name, status));
    }

    public boolean checkPartnerAvailabilityStatus(String name) {
        assertThat(getPartnersListByName(name, 200).getString("results[0].name"), is(name));
        return true;
    }
}
