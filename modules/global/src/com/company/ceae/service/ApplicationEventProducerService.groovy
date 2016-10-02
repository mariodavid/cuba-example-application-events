package com.company.ceae.service


/**
 * Application event publishing mechanism (implemented through Spring)
 */
public interface ApplicationEventProducerService {
    String NAME = "ceae_ApplicationEventProducerService";

    void produceApplicationEvent(Object event)
}