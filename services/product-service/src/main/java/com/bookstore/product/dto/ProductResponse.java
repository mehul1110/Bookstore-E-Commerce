package com.bookstore.product.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String title,
        String author,
        String isbn,
        BigDecimal price,
        Integer stockQuantity,
        String imageUrl,
        String category
) {
}

