package com.iat.validators.userDetails;

import com.iat.domain.UserDetails;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserDetailsValidator {

    public void checkIfUpdatedUserDataAreAsExpected(ResponseContainer response, String jsonParameters, ResponseContainer responseTemp, String newEmail) {
        //check parameters set during new user creation
        List<String> setParameterLabels = asList("firstName", "lastName", "password", "email", "mobileNumber",
                "houseNumberOrName", "street", "townOrCity", "county", "country", "postcode", "region", "language",
                "title", "gender", "birthDate", "ePointsContact", "thirdPartyContact", "verified");
        String[] setParameterValues = jsonParameters.split(",");
        // for email creation flow is bit different, additional parameter needed
        if (newEmail != null)
            setParameterValues[3] = newEmail;

        String temp, temp2;
        int i = 0;
        String parameterValue;
        for (String parameterLabel : setParameterLabels) {
            parameterValue = setParameterValues[i];

            if (parameterValue.equals("null")) {
                if (!parameterLabel.equals("verified")) {
                    temp = responseTemp.getString(parameterLabel);
                    temp2 = response.getString(parameterLabel);
                    assertThat("Not modified parameter was modified!", temp, is(temp2));
                }
            } else {
                if (newEmail == null) { //Update request returned old email as new one was not verified
                    temp = responseTemp.getString(parameterLabel);
                    assertThat("Response data is not as expected!", parameterValue, is(temp));
                }
                temp = response.getString(parameterLabel);
                assertThat("Get user details data is not as expected!", parameterValue, is(temp));
            }
        }
    }

    public void validateIfUserDetailsUpdated(UserDetails response, UserDetails userDetails) {
        String validUuid = userDetails.getUuid();
        String validEmail = userDetails.getEmail();
        String validFirstName = userDetails.getFirstName();
        String validLastName = userDetails.getLastName();
        String validPassword = userDetails.getPassword();
        String validMobileNumber = userDetails.getMobileNumber();
        String validStreet = userDetails.getStreet();
        String validCounty = userDetails.getCounty();
        String validCountry = userDetails.getCountry();
        String validPostcode = userDetails.getPostcode();
        String validRegion = userDetails.getPostcode();
        String validLanguage = userDetails.getLanguage();
        String validGender = userDetails.getGender();

        String responseUuid = response.getUuid();
        String responseEmail = response.getEmail();
        String responseFirstName = response.getFirstName();
        String responseLastName = response.getLastName();
        String responsePassword = response.getPassword();
        String responseMobileNumber = response.getMobileNumber();
        String responseStreet = response.getStreet();
        String responseCounty = response.getCounty();
        String responseCountry = response.getCountry();
        String responsePostcode = response.getPostcode();
        String responseRegion = response.getPostcode();
        String responseLanguage = response.getLanguage();
        String responseGender = response.getGender();

        if (validFirstName != null)
            assertThat("User: " + responseUuid + " firstName incorrectly updated!", validFirstName, is(responseFirstName));
        if (validLastName != null)
            assertThat("User: " + responseUuid + " lastName incorrectly updated!", validLastName, is(responseLastName));
        if (validEmail != null)
            assertThat("User: " + responseUuid + " email incorrectly updated!", validEmail, is(responseEmail));
        //password returned in profile is hashed
        //if (validPassword != null)
        //    assertThat("User: " + responseUuid + " password incorrectly updated!", validPassword, is(responsePassword));
        if (validMobileNumber != null)
            assertThat("User: " + responseUuid + " mobileNumber incorrectly updated!", validMobileNumber, is(responseMobileNumber));
        if (validStreet != null)
            assertThat("User: " + responseUuid + " street incorrectly updated!", validStreet, is(responseStreet));
        if (validCounty != null)
            assertThat("User: " + responseUuid + " county incorrectly updated!", validCounty, is(responseCounty));
        if (validCountry != null)
            assertThat("User: " + responseUuid + " country incorrectly updated!", validCountry, is(responseCountry));
        if (validPostcode != null)
            assertThat("User: " + responseUuid + " postcode incorrectly updated!", validPostcode, is(responsePostcode));
        if (validRegion != null)
            assertThat("User: " + responseUuid + " region incorrectly updated!", validRegion, is(responseRegion));
        if (validLanguage != null)
            assertThat("User: " + responseUuid + " language incorrectly updated!", validLanguage, is(responseLanguage));
        if (validGender != null)
            assertThat("User: " + responseUuid + " gender incorrectly updated!", validGender, is(responseGender));


    }

}