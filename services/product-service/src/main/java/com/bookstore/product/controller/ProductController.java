package com.bookstore.product.controller;

import com.bookstore.product.dto.ProductResponse;
import com.bookstore.product.service.ProductCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private final ProductCatalogService productCatalogService;

    public ProductController(ProductCatalogService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponse>> products() {
        return ResponseEntity.ok(productCatalogService.allProducts());
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> product(@PathVariable Long id) {
        return ResponseEntity.ok(productCatalogService.defaultProduct());
    }

    @GetMapping("/api/products/search")
    public ResponseEntity<List<ProductResponse>> search(@RequestParam String q) {
        return ResponseEntity.ok(productCatalogService.search(q));
    }

    @GetMapping("/api/products/category/{id}")
    public ResponseEntity<List<Map<String, Object>>> byCategory(@PathVariable Long id) {
        return ResponseEntity.ok(List.of(Map.of("categoryId", id, "product", productCatalogService.defaultProduct())));
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponse> createProduct() {
        return ResponseEntity.ok(productCatalogService.defaultProduct());
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productCatalogService.defaultProduct());
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/categories")
    public ResponseEntity<List<String>> categories() {
        return ResponseEntity.ok(List.of("Fiction", "Technology", "History"));
    }

    @PostMapping("/api/categories")
    public ResponseEntity<Map<String, String>> createCategory(@RequestParam String name) {
        return ResponseEntity.ok(Map.of("name", name));
    }
}
