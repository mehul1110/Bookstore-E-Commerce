package com.bookstore.notification.dto;

public record NotificationMessage(String channel, String recipient, String payload) {
}

