package com.company.ceae.service

import com.company.ceae.entity.Notification
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.UserSessionSource
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(NotificationService.NAME)
public class NotificationServiceBean implements NotificationService {

    @Inject
    DataManager dataManager

    @Inject
    UserSessionSource userSessionSource

    @Override
    int countUnreadNotificationsForCurrentUser() {
        LoadContext loadContext = LoadContext.create(Notification)
                .setQuery(LoadContext.createQuery('select e from ceae$Notification e where e.user.id = :userId and e.read = FALSE')
                .setParameter("userId", userSessionSource.userSession.user.id))
                .setView("_local")


        def count = dataManager.getCount(loadContext)

        count
    }

    @Override
    Entity getEntityInstanceFromNotification(Notification notification) {
        LoadContext loadContext = LoadContext.create(Notification)
                .setQuery(LoadContext.createQuery("select e from $notification.entityClass e where e.id = :entityId")
                .setParameter("entityId", notification.entityId))
                .setView("_minimal")

        dataManager.load(loadContext)
    }
}