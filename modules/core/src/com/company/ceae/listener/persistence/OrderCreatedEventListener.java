package com.company.ceae.listener.persistence;

import com.company.ceae.event.OrderCreatedEvent;
import com.company.ceae.service.ApplicationEventProducerService;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import java.sql.Connection;
import java.util.Date;

import com.company.ceae.entity.Order;

import javax.inject.Inject;



/**
 * Acts as PersistentEventListener
 *
 * Transforms the persistence event to an application event
 */
@Component("ceae_OrderCreatedEventListener")
public class OrderCreatedEventListener implements AfterInsertEntityListener<Order> {


    @Inject
    ApplicationEventProducerService applicationEventProducerService;


    @Override
    public void onAfterInsert(Order entity, Connection connection) {
        produceOrderCreatedEvent(entity);
    }

    private void produceOrderCreatedEvent(Order entity) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        entity.setOrderDate(new Date());
        orderCreatedEvent.setOrder(entity);
        orderCreatedEvent.setSource(OrderCreatedEventListener.class.getSimpleName());
        applicationEventProducerService.produceApplicationEvent(orderCreatedEvent);
    }

}