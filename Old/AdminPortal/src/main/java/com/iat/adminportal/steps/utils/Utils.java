package com.iat.adminportal.steps.utils;

import com.iat.adminportal.domain.UserDetails;

import java.util.Date;

/**
 * Created by kbaranowski on 2017-12-14.
 */
public class Utils {

    public Date findUserCreatedDate(UserDetails userDetails) {
        Date createdDate = new Date();
        for (int i = 0; i < userDetails.getUserGroups().size(); i++) {
            if (createdDate.after(userDetails.getUserGroups().get(i).getCreateDate())) {
                createdDate = userDetails.getUserGroups().get(i).getCreateDate();
            }
        }
        return createdDate;
    }

}