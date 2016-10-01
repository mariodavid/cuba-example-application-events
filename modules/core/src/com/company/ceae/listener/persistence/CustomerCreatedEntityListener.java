/*
 * TODO Copyright
 */

package com.company.ceae.listener.persistence;

import com.company.ceae.entity.Order;
import com.company.ceae.event.CustomerCreatedEvent;
import com.company.ceae.event.OrderCreatedEvent;
import com.company.ceae.service.ApplicationEventProducerService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import java.sql.Connection;
import java.util.Date;

import com.company.ceae.entity.Customer;

import javax.inject.Inject;

@Component("ceae_CustomerCreatedEntityListener")
public class CustomerCreatedEntityListener implements AfterInsertEntityListener<Customer> {

    @Inject
    ApplicationEventProducerService applicationEventProducerService;


    @Override
    public void onAfterInsert(Customer entity, Connection connection) {

        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setCustomer(entity);
        applicationEventProducerService.produceApplicationEvent(customerCreatedEvent);
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        Order order = new Order();
        order.setOrderDate(new Date());
        orderCreatedEvent.setOrder(order);
        applicationEventProducerService.produceApplicationEvent(orderCreatedEvent);
    }


}