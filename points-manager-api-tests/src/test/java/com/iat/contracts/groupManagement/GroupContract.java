package com.iat.contracts.groupManagement;

public class GroupContract {

    public String getGroupPath(String shortName) {

        if (shortName.equals("null"))
            shortName = "";

        return "/group/" + shortName + "/partners";
    }

}