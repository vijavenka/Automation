package com.iat.repository;

import com.iat.domain.ecardApprovalReject;

public interface EcardsApprovalRepository {

    String rejectEcard(ecardApprovalReject body, String id, String username, String password);

    String approveEcard(String id, String username, String password);

    String getAllAdminNotifications(String username, String password);

    String markNotificationsAsRead(String body, String username, String password);

}
