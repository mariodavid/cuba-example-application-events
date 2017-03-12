package com.company.ceae.service

import com.company.ceae.entity.Notification
import com.company.ceae.entity.NotificationSubscription
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.UserSessionSource
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(NotificationSubscriptionService.NAME)
public class NotificationSubscriptionServiceBean implements NotificationSubscriptionService {


    @Inject
    DataManager dataManager

    @Override
    Collection<NotificationSubscription> getNotificationSubscriptionsForEntityClass(String entityClass) {
        LoadContext loadContext = LoadContext.create(NotificationSubscription)
                .setQuery(LoadContext.createQuery('select e from ceae$NotificationSubscription e where e.entityClass = :entityClass')
                .setParameter("entityClass", entityClass))
                .setView('notificationSubscription-view')

        dataManager.loadList(loadContext)
    }
}