package com.bookstore.order.service;

import com.bookstore.common.event.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderEventPublisher {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderEventPublisher(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderPlaced(Long orderId, Long userId) {
        kafkaTemplate.send("order-events", new OrderEvent(orderId, userId, "ORDER_PLACED", LocalDateTime.now()));
    }
}

