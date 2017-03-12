/*
 * TODO Copyright
 */

package com.company.ceae.listener.application

import com.company.ceae.entity.Customer
import com.company.ceae.entity.Notification
import com.company.ceae.entity.NotificationSubscription
import com.company.ceae.event.CustomerCreatedEvent
import com.company.ceae.service.NotificationSubscriptionService
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.UserSessionSource
import com.haulmont.cuba.security.app.UserSessionService
import com.haulmont.cuba.security.app.UserSessionsAPI
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

import javax.inject.Inject

/**
 * Acts as EventListener
 *
 * Responsible for catching CustomerCreatedEvents and
 * displaying the new Customer as a notification on the UI
 */
@Component
class CustomerCreatedUserNotificator {

    @Inject
    DataManager dataManager

    @Inject
    Metadata metadata

    @Inject
    UserSessionSource userSessionSource

    @Inject
    NotificationSubscriptionService notificationSubscriptionService

    @Async
    @EventListener
    void handleCustomerCreated(CustomerCreatedEvent event) {
        createNotificationForCurrentUser(event.customer)
    }

    private void createNotificationForCurrentUser(Customer customer) {

        def notificationSubscriptions = notificationSubscriptionService.getNotificationSubscriptionsForEntityClass(customer.metaClass.name)

        notificationSubscriptions.each { NotificationSubscription notificationSubscription ->
            createNotificationIfNecessary(customer, notificationSubscription)
        }

    }

    private void createNotificationIfNecessary(Customer customer, NotificationSubscription notificationSubscription) {
        def isValidForUser = isSubscriptionValidForUser(customer, notificationSubscription)

        if (isValidForUser) {

            Notification notification = metadata.create(Notification.class)
            notification.read = false
            notification.user = notificationSubscription.user
            notification.entityClass = customer.metaClass.name
            notification.entityCaption = customer.instanceName
            notification.entityId = customer.id
            dataManager.commit(notification)

        }
    }

    private boolean isSubscriptionValidForUser(Customer customer, NotificationSubscription notificationSubscription) {

        def condition = notificationSubscription.condition.replace("{E}", "x")
        Eval.x(customer, condition)
    }
}
