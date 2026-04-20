package com.bookstore.feedback.dto;

public record ReviewResponse(Long id, Long productId, Integer rating, String comment) {
}

