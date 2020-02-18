package com.iat.contracts;

public class SocialActivityContract {

    public String getScoialPath() {
        return "/rest/social";
    }

    public String getFacebookLikePath() {
        return "/rest/social/facebook/like";
    }

    public String getTwitterFollowPath() {
        return "/rest/social/twitter/follow";
    }

}