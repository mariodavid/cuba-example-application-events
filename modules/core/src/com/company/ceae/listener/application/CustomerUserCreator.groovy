/*
 * TODO Copyright
 */

package com.company.ceae.listener.application

import com.company.ceae.entity.Customer
import com.company.ceae.event.CustomerCreatedEvent
import com.company.ceae.event.OrderCreatedEvent
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.security.entity.Group
import com.haulmont.cuba.security.entity.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import com.haulmont.cuba.security.app.Authentication

import javax.inject.Inject


/**
 * Acts as EventListener
 *
 * Responsible for catching CustomerCreatedEvents and
 * creating a User for this newly created Customer
 */
@Component
class CustomerUserCreator {


    private final Logger log = LoggerFactory.getLogger(getClass());


    @Inject
    DataManager dataManager

    @Inject
    Metadata metadata
    
    
    @Inject
    Authentication authentication


    @Async
    @EventListener
    void handleCustomerCreated(CustomerCreatedEvent event) {
        log.warn("The customer was created...")

        authentication.begin()
        try {
            Group company = getGroupFromDb("Company")
            User user = createUserFromCustomer(company, event.customer)

            log.warn("The user ${user.login} was created...".toString())
        }
        finally {
            authentication.end()
        }
    }

    private Group getGroupFromDb(String groupName) {
        LoadContext loadContext = LoadContext.create(Group.class)
                .setQuery(LoadContext.createQuery('select p from sec$Group p where p.name = :groupName')
                .setParameter("groupName", groupName));
        dataManager.load(loadContext)
    }

    private User createUserFromCustomer(Group company, Customer customer) {
        User user = metadata.create(User.class)

        user.group = company
        user.login = customer.name
        user.firstName = customer.firstName
        user.lastName = customer.name
        user.password = "12345"
        dataManager.commit(user)

        return user
    }
}
