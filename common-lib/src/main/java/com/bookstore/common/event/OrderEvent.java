package com.bookstore.common.event;

import java.time.LocalDateTime;

public record OrderEvent(Long orderId, Long userId, String type, LocalDateTime occurredAt) {
}

