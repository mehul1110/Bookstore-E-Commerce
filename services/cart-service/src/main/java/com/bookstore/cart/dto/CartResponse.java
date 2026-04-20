package com.bookstore.cart.dto;

import java.math.BigDecimal;

public record CartResponse(int itemCount, BigDecimal totalAmount) {
}

