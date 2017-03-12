package com.company.ceae.service

import com.company.ceae.entity.Notification
import com.haulmont.cuba.core.entity.Entity


public interface NotificationService {
    String NAME = "ceae_NotificationService";

    int countUnreadNotificationsForCurrentUser()

    Entity getEntityInstanceFromNotification(Notification notification)
}