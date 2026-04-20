package com.bookstore.customer.controller;

import com.bookstore.customer.service.CustomerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerProfileService customerProfileService;

    public CustomerController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @GetMapping("/details")
    public ResponseEntity<Map<String, String>> getDetails() {
        var profile = customerProfileService.currentProfile();
        return ResponseEntity.ok(Map.of("phone", profile.getPhone(), "preference", profile.getPreference()));
    }

    @PostMapping("/details")
    public ResponseEntity<Map<String, String>> createDetails() {
        return ResponseEntity.ok(Map.of("message", "Customer details created"));
    }

    @PutMapping("/details")
    public ResponseEntity<Map<String, String>> updateDetails() {
        return ResponseEntity.ok(Map.of("message", "Customer details updated"));
    }

    @PostMapping("/addresses")
    public ResponseEntity<Map<String, String>> addAddress() {
        return ResponseEntity.ok(Map.of("message", "Address added"));
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<String>> addresses() {
        return ResponseEntity.ok(List.of("Home", "Office"));
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/addresses/{id}/default")
    public ResponseEntity<Map<String, String>> defaultAddress(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("defaultAddressId", id.toString()));
    }
}
