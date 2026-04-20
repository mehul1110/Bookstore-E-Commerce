package com.bookstore.wishlist.controller;

import com.bookstore.wishlist.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<List<Long>> getWishlist() {
        return ResponseEntity.ok(wishlistService.getOrCreate(1L).getItems().stream().map(item -> item.getProductId()).toList());
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<Map<String, String>> add(@PathVariable Long productId) {
        return ResponseEntity.ok(Map.of("message", "Added to wishlist", "productId", productId.toString()));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> remove(@PathVariable Long productId) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clear() {
        return ResponseEntity.noContent().build();
    }
}
