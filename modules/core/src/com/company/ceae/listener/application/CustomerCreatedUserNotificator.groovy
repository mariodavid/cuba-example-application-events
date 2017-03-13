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
import com.haulmont.cuba.core.sys.AppContext
import com.haulmont.cuba.core.sys.SecurityContext
import com.haulmont.cuba.security.app.Authentication
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
        createNotificationsForCustomerCreatedEvent(event.customer)
    }

    private void createNotificationsForCustomerCreatedEvent(Customer customer) {

        def notificationSubscriptions = notificationSubscriptionService.getNotificationSubscriptionsForEntityClass(customer.metaClass.name)

        notificationSubscriptions.each { NotificationSubscription notificationSubscription ->
            createNotificationIfNecessary(customer, notificationSubscription)
        }

    }

    private void createNotificationIfNecessary(Customer customer, NotificationSubscription notificationSubscription) {
        def isValidForUser = isSubscriptionValidForUser(customer, notificationSubscription)

        if (isValidForUser) {
            createNotification(notificationSubscription, customer)
        }
    }

    private boolean isSubscriptionValidForUser(Customer customer, NotificationSubscription notificationSubscription) {
        def condition = notificationSubscription.condition.replace("{E}", "x")
        def result = false
        try {
            result = Eval.x(customer, condition)
        }
        catch (Exception e) {
            e.printStackTrace()
        }

        result

    }

    private void createNotification(NotificationSubscription notificationSubscription, Customer customer) {
        Notification notification = metadata.create(Notification.class)
        notification.read = false
        notification.user = notificationSubscription.user
        notification.entityClass = customer.metaClass.name
        notification.entityCaption = customer.instanceName
        notification.entityId = customer.id
        dataManager.commit(notification)
    }

}
