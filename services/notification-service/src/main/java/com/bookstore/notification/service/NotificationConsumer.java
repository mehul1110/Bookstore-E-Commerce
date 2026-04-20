package com.bookstore.notification.service;

import com.bookstore.common.event.OrderEvent;
import com.bookstore.common.event.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final EmailService emailService;

    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void onOrderEvent(OrderEvent event) {
        log.info("Received order event {}", event);
        emailService.sendOrderNotification(event);
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void onUserEvent(UserEvent event) {
        log.info("Received user event {}", event);
        emailService.sendWelcomeEmail(event);
    }
}
