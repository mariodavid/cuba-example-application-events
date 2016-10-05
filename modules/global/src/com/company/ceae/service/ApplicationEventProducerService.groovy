package com.company.ceae.service

import com.company.ceae.entity.Order


/**
 * Application event publishing mechanism (implemented through Spring)
 */
public interface ApplicationEventProducerService {
    String NAME = "ceae_ApplicationEventProducerService";

    void produceApplicationEvent(Object event)

    void produceOrderCreatedEvent(Order order, String source)
}