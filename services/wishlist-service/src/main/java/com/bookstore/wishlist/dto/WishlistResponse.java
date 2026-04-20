package com.bookstore.wishlist.dto;

import java.util.List;

public record WishlistResponse(Long userId, List<Long> productIds) {
}

