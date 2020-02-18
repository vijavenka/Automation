package com.iat.actions;


import com.iat.controller.SocialActivityController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class SocialActivityActions {

    private SocialActivityController socialActivityController = new SocialActivityController();

    public ResponseContainer getSocialStatus(int status) {
        return initResponseContainer(socialActivityController.getSocialStatus(status), "Get social status RESPONSE:");
    }

    public ResponseContainer likeEpointsOnFacebook(int status) {
        return initResponseContainer(socialActivityController.likeEpointsOnFacebook(status), "Like epoints on facebook RESPONSE:");
    }

    public ResponseContainer followEpointsOnTwitter(int status) {
        return initResponseContainer(socialActivityController.followEpointsOnTwitter(status), "Like epoints on twitter RESPONSE:");
    }
}
