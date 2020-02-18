package com.iat.domain.couponUsage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CouponUsageCashIn extends AbstractDomain {

    private String epointsValue;
    private String sortCode;
    private String accountNumber;
    private String accountHoldersName;
    private String usersReference;
    @JsonIgnore
    private String body = "";

    public CouponUsageCashIn(String epointsValue, String sortCode, String accountNumber, String accountHoldersName, String usersReference) {

        this.setEpointsValue(epointsValue);
        this.setSortCode(sortCode);
        this.setAccountNumber(accountNumber);
        this.setAccountHoldersName(accountHoldersName);
        this.setUsersReference(usersReference);
    }

    @Override
    public String toJsonRequest() {
        return body;
    }

    public void toJson() {
        String jsonBody = "{epointsValue_TO_REPLACE sortCode_TO_REPLACE  accountNumber_TO_REPLACE accountHoldersName_TO_REPLACE usersReference_TO_REPLACE }";

        if (epointsValue.equals("empty"))
            jsonBody = jsonBody.replace("epointsValue_TO_REPLACE", "\"epointsValue\": ,");
        else if (epointsValue.equals("null"))
            jsonBody = jsonBody.replace("epointsValue_TO_REPLACE", "");
        else
            jsonBody = jsonBody.replace("epointsValue_TO_REPLACE", "\"epointsValue\": " + epointsValue + ",");

        if (sortCode.equals("empty"))
            jsonBody = jsonBody.replace("sortCode_TO_REPLACE", "\"sortCode\": \"\",");
        else if (sortCode.equals("null"))
            jsonBody = jsonBody.replace("sortCode_TO_REPLACE", "");
        else
            jsonBody = jsonBody.replace("sortCode_TO_REPLACE", "\"sortCode\": \"" + sortCode + "\",");

        if (accountNumber.equals("empty"))
            jsonBody = jsonBody.replace("accountNumber_TO_REPLACE", "\"accountNumber\": \"\",");
        else if (accountNumber.equals("null"))
            jsonBody = jsonBody.replace("accountNumber_TO_REPLACE", "");
        else
            jsonBody = jsonBody.replace("accountNumber_TO_REPLACE", "\"accountNumber\": \"" + accountNumber + "\",");

        if (accountHoldersName.equals("empty"))
            jsonBody = jsonBody.replace("accountHoldersName_TO_REPLACE", "\"accountHoldersName\": \"\",");
        else if (accountHoldersName.equals("null"))
            jsonBody = jsonBody.replace("accountHoldersName_TO_REPLACE", "");
        else
            jsonBody = jsonBody.replace("accountHoldersName_TO_REPLACE", "\"accountHoldersName\": \"" + accountHoldersName + "\",");

        if (usersReference.equals("empty"))
            jsonBody = jsonBody.replace("usersReference_TO_REPLACE", "\"usersReference\": \"\",");
        else if (usersReference.equals("null"))
            jsonBody = jsonBody.replace("usersReference_TO_REPLACE", "");
        else
            jsonBody = jsonBody.replace("usersReference_TO_REPLACE", "\"usersReference\": \"" + usersReference + "\",");

        body = jsonBody.replace(",}", "}").replace(", }", "}").replace(",  }", "}");
    }

}