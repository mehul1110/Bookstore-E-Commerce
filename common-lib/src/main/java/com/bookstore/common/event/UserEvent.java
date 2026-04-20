package com.bookstore.common.event;

import java.time.LocalDateTime;

public record UserEvent(Long userId, String email, String type, LocalDateTime occurredAt) {
}

