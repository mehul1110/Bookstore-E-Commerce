package com.bookstore.order.controller;

import com.bookstore.order.service.OrderEventPublisher;
import com.bookstore.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderEventPublisher publisher;
    private final OrderService orderService;

    public OrderController(OrderEventPublisher publisher, OrderService orderService) {
        this.publisher = publisher;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> placeOrder() {
        var order = orderService.createDefaultOrder();
        publisher.publishOrderPlaced(order.getId(), order.getUserId());
        return ResponseEntity.ok(Map.of("message", "Order placed", "status", "PENDING"));
    }

    @GetMapping
    public ResponseEntity<List<String>> myOrders() {
        return ResponseEntity.ok(List.of("PENDING", "DELIVERED"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> orderById(@PathVariable Long id) {
        var order = orderService.createDefaultOrder();
        return ResponseEntity.ok(Map.of("id", id, "status", order.getStatus().name()));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Map<String, String>> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("id", id.toString(), "status", "CANCELLED"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> allOrders() {
        return ResponseEntity.ok(List.of("PENDING", "PROCESSING", "SHIPPED"));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, String>> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(Map.of("id", id.toString(), "status", status));
    }
}
