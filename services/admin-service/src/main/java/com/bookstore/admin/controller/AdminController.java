package com.bookstore.admin.controller;

import com.bookstore.admin.client.ProductServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ProductServiceClient productServiceClient;

    public AdminController(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerAdmin() {
        return ResponseEntity.ok(Map.of("message", "Admin registered"));
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<String>> allUsers() {
        return ResponseEntity.ok(List.of("user1@bookstore.com", "user2@bookstore.com"));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Map<String, String>> updateProduct(@PathVariable Long id) {
        productServiceClient.updateProduct(id);
        return ResponseEntity.ok(Map.of("message", "Product updated"));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productServiceClient.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders")
    public ResponseEntity<List<String>> viewOrders() {
        return ResponseEntity.ok(List.of("PENDING", "SHIPPED"));
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Map<String, String>> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(Map.of("orderId", id.toString(), "status", status));
    }
}

