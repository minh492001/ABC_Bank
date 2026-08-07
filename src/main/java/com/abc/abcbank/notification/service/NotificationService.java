package com.abc.abcbank.notification.service;

import com.abc.abcbank.auth_users.entity.User;
import com.abc.abcbank.notification.dto.NotificationDTO;

public interface NotificationService {

    void sendEmail(NotificationDTO notificationDTO, User user);

}
