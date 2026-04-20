package com.bookstore.notification.service;

import com.bookstore.common.event.OrderEvent;
import com.bookstore.common.event.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    public void sendOrderNotification(OrderEvent event) {
        log.info("Sending order notification for order {}", event.orderId());
    }

    public void sendWelcomeEmail(UserEvent event) {
        log.info("Sending welcome email to {}", event.email());
    }
}

