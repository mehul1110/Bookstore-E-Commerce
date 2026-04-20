package com.bookstore.cart.controller;

import com.bookstore.cart.entity.Cart;
import com.bookstore.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        return ResponseEntity.ok(cartService.currentCart());
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addItem() {
        return ResponseEntity.ok(Map.of("message", "Item added"));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateQuantity() {
        return ResponseEntity.ok(Map.of("message", "Cart updated"));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long productId) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, BigDecimal>> total() {
        return ResponseEntity.ok(Map.of("total", cartService.currentCart().getTotalAmount()));
    }
}
