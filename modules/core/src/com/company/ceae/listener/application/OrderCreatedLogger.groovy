package com.company.ceae.listener.application

import com.company.ceae.event.OrderCreatedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component


/**
 * Acts as EventListener
 *
 * Responsible for catching CustomerCreatedEvents and
 * logging the creation of the orders, as well as the source the event occured
 */
@Component
class OrderCreatedLogger {


    private final Logger log = LoggerFactory.getLogger(getClass());


    @Async
    @EventListener
    void handleOrderCreated(OrderCreatedEvent event) {
        log.warn("The order - ${event.order.orderDate} was created. Event from source: ${event.source}")
    }
}
