package com.company.ceae.web.order

import com.company.ceae.event.OrderCreatedEvent
import com.company.ceae.service.ApplicationEventProducerService
import com.haulmont.cuba.gui.components.AbstractEditor
import com.company.ceae.entity.Order

import javax.inject.Inject

public class OrderEdit extends AbstractEditor<Order> {


    @Inject
    ApplicationEventProducerService applicationEventProducerService

    /**
     * after commit of the DS an order created event should get fired
     */
    @Override
    protected boolean postCommit(boolean committed, boolean close) {

        publishOrderCreatedEvent()

        return super.postCommit(committed, close)
    }

    /**
     * manually publishing a order created event through a button
     */
    public void notifyNewOrder() {
        publishOrderCreatedEvent()
    }

    private publishOrderCreatedEvent() {
        applicationEventProducerService.produceApplicationEvent(new OrderCreatedEvent(order: item, source: OrderEdit.getSimpleName()))
    }
}