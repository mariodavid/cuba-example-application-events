package com.company.ceae.service

import com.company.ceae.entity.Order
import com.company.ceae.event.OrderCreatedEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

import javax.inject.Inject

@Service(ApplicationEventProducerService.NAME)
public class ApplicationEventProducerServiceBean implements ApplicationEventProducerService {

    @Inject
    ApplicationEventPublisher publisher;

    @Override
    void produceApplicationEvent(Object event) {
        publisher.publishEvent(event)
    }

    @Override
    void produceOrderCreatedEvent(Order order, String source) {
        publisher.publishEvent(new OrderCreatedEvent(order: order, source: source))
    }
}