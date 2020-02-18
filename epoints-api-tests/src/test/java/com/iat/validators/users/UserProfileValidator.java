package com.iat.validators.users;

import com.iat.Config;
import com.iat.actions.users.UsersActions;
import com.iat.domain.UserBalance;
import com.iat.domain.UserDetails;
import com.iat.domain.UserGroup;
import com.iat.domain.userProfile.UserProfile;
import com.iat.repository.PartnerRepository;
import com.iat.repository.impl.PartnerRepositoryImpl;
import com.iat.utils.DataExchanger;

import java.util.Date;

import static com.iat.utils.DateTimeUtil.Format;
import static com.iat.utils.DateTimeUtil.convertToDate;
import static org.exparity.hamcrest.date.DateMatchers.sameDay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserProfileValidator {

    private PartnerRepository partnerRepository = new PartnerRepositoryImpl();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private UsersActions usersActions = new UsersActions();

    public void checkIfPersonalDetailsWereProperlyChangedInUserProfile(String firstName, String lastName, String title, String gender, String dob, UserProfile userProfile) {
        assertThat("Wrong first name returned by profile.", userProfile.getPersonalDetails().getFirstName(), is(firstName));
        assertThat("Wrong last name returned by profile.", userProfile.getPersonalDetails().getLastName(), is(lastName));
        assertThat("Wrong title returned by profile.", userProfile.getPersonalDetails().getTitle(), is(title));
        assertThat("Wrong gender returned by profile.", userProfile.getPersonalDetails().getGender(), is(gender));
        assertThat("Wrong dob returned by profile.", new Date(userProfile.getPersonalDetails().getDob()), is(sameDay(convertToDate(dob, Format.ddMMyyyy, true))));
    }

    public void validateIfUserVirtualGroupsWereProperlyUpdated(String businessId, String firstName, String lastName, String gender, String dob, UserDetails userDetails) {
        String epointsPartnerId = partnerRepository.findByShortname(Config.epointsPartnerShortName).getId();
        String unitedPartnerId = partnerRepository.findByShortname(Config.unitedPartnerShortName).getId();

        int numberOfGroupsFound = 0;
        for (UserGroup userGroup : userDetails.getUserGroups()) {
            if (userGroup.getPartnerId().toString().equals(epointsPartnerId) || userGroup.getPartnerId().toString().equals(unitedPartnerId)) {
                assertThat("Wrong first name returned by dynamo details.", userGroup.getFirstName(), is(firstName));
                assertThat("Wrong last name returned by dynamo details.", userGroup.getLastName(), is(lastName));
                assertThat("Wrong gender returned by dynamo details.", userGroup.getGender(), is(gender));
                assertThat("Wrong dob returned by dynamo details.", new Date(userDetails.getBirthDate()), is(sameDay(convertToDate(dob, Format.ddMMyyyy, true))));
                numberOfGroupsFound++;
            }
        }
        if (businessId.equalsIgnoreCase("UNITED"))
            assertThat("Wrong number of groups found.", numberOfGroupsFound, is(2));
        else
            assertThat("Wrong number of groups found.", numberOfGroupsFound, is(1));
    }

    public void validateIfUserVirtualGroupsWereProperlyUpdated(String businessId, String email, UserDetails userDetails) {
        String epointsPartnerId = partnerRepository.findByShortname(Config.epointsPartnerShortName).getId();
        String unitedPartnerId = partnerRepository.findByShortname(Config.unitedPartnerShortName).getId();

        assertThat("Wrong email address in main user details json.", userDetails.getEmail(), is(email));


        int numberOfGroupsFound = 0;
        for (UserGroup userGroup : userDetails.getUserGroups()) {
            if (userGroup.getPartnerId().toString().equals(epointsPartnerId) || userGroup.getPartnerId().toString().equals(unitedPartnerId)) {
                assertThat("Wrong email returned by profile.", userGroup.getEmail(), is(email));
                numberOfGroupsFound++;
            }
        }
        if (businessId.equalsIgnoreCase("UNITED"))
            assertThat("Wrong number of groups found.", numberOfGroupsFound, is(2));
        else
            assertThat("Wrong number of groups found.", numberOfGroupsFound, is(1));
    }

    public void checkIfContactDetailsWereProperlyChangedInUserProfile(String phoneNo, String house, String street, String city, String county, String country, String postCode, UserProfile userProfile) {
        assertThat("Wrong phone number returned by profile.", userProfile.getContactDetails().getPhoneNo(), is(phoneNo));
        assertThat("Wrong house returned by profile.", userProfile.getContactDetails().getAddress().getHouse(), is(house));
        assertThat("Wrong street returned by profile.", userProfile.getContactDetails().getAddress().getStreet(), is(street));
        assertThat("Wrong city returned by profile.", userProfile.getContactDetails().getAddress().getCity(), is(city));
        assertThat("Wrong county returned by profile.", userProfile.getContactDetails().getAddress().getCounty(), is(county));
        assertThat("Wrong country returned by profile.", userProfile.getContactDetails().getAddress().getCountry(), is(country));
        assertThat("Wrong postCode returned by profile.", userProfile.getContactDetails().getAddress().getPostCode(), is(postCode));
    }

    public void checkIfUserBalanceWasUpdated(int addedPointsValue) {
        UserBalance userBalance = usersActions.getUserBalance("null", 200).getAsObject(UserBalance.class);
        assertThat(addedPointsValue + " points were not added for filling personal or contact details", userBalance.getConfirmedPoints(), is(dataExchanger.getUserBalance().getConfirmedPoints() + addedPointsValue));
    }

    public void checkIfConsentDetailsWereProperlyChangedInUserProfile(boolean subscribedToMarketingEmails, UserProfile userProfile) {
        assertThat("subscribed to marketing emails flag is not correct", userProfile.getConsentDetails().isSubscribedToMarketingEmails(), is(subscribedToMarketingEmails));
    }

}