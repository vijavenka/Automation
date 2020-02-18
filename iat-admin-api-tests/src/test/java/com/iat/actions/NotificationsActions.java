package com.iat.actions;


import com.iat.controller.NotificationController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class NotificationsActions {

    private NotificationController notificationController = new NotificationController();

    public ResponseContainer getNotifications(int status) {
        return initResponseContainer(notificationController.getNotifications(status));
    }

    public ResponseContainer getNotifications() {
        return getNotifications(200);
    }

    public ResponseContainer setNotificationsRead(String jsonBody, int status) {
        return initResponseContainer(notificationController.setNotificationsRead(jsonBody, status));
    }

}
