package com.company.ceae.service


public interface ApplicationEventProducerService {
    String NAME = "ceae_ApplicationEventProducerService";

    void produceApplicationEvent(Object event)
}