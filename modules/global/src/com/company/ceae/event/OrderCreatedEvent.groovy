package com.company.ceae.event

import com.company.ceae.entity.Order

/**
 * Application event when a order is created
 */
class OrderCreatedEvent {

    String source
    Order order

}
