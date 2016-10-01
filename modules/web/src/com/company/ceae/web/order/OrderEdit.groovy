package com.company.ceae.web.order

import com.company.ceae.event.OrderCreatedEvent
import com.company.ceae.service.ApplicationEventProducerService
import com.haulmont.cuba.gui.components.AbstractEditor
import com.company.ceae.entity.Order
import org.springframework.context.ApplicationEventPublisher

import javax.inject.Inject

public class OrderEdit extends AbstractEditor<Order> {


    @Inject
    ApplicationEventProducerService applicationEventProducerService

    @Override
    protected boolean postCommit(boolean committed, boolean close) {

        applicationEventProducerService.produceApplicationEvent(new OrderCreatedEvent(order: item))

        return super.postCommit(committed, close)
    }
}