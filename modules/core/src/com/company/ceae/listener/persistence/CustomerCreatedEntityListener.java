package com.company.ceae.listener.persistence;

import com.company.ceae.entity.Customer;
import com.company.ceae.event.CustomerCreatedEvent;
import com.company.ceae.service.ApplicationEventProducerService;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.sql.Connection;


/**
 * Acts as PersistentEventListener
 *
 * Transforms the persistence event to an application event
 */
@Component("ceae_CustomerCreatedEntityListener")
public class CustomerCreatedEntityListener implements AfterInsertEntityListener<Customer> {

    @Inject
    ApplicationEventProducerService applicationEventProducerService;

    @Override
    public void onAfterInsert(Customer entity, Connection connection) {
        produceCustomerCreatedEvent(entity);
    }

    private void produceCustomerCreatedEvent(Customer entity) {
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setCustomer(entity);
        applicationEventProducerService.produceApplicationEvent(customerCreatedEvent);
    }


}