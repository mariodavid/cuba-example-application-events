package com.company.ceae.service

import com.company.ceae.entity.NotificationSubscription


public interface NotificationSubscriptionService {
    String NAME = "ceae_NotificationSubscriptionService";

    Collection<NotificationSubscription> getNotificationSubscriptionsForEntityClass(String entityClass)
}