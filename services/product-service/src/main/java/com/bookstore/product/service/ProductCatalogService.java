package com.bookstore.product.service;

import com.bookstore.product.dto.ProductResponse;
import com.bookstore.product.entity.Product;
import com.bookstore.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductCatalogService {

    private final ProductRepository productRepository;

    public ProductCatalogService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> allProducts() {
        List<ProductResponse> stored = productRepository.findAll().stream().map(this::toResponse).toList();
        return stored.isEmpty() ? List.of(defaultProduct()) : stored;
    }

    public List<ProductResponse> search(String query) {
        List<ProductResponse> stored = productRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query)
                .stream()
                .map(this::toResponse)
                .toList();
        return stored.isEmpty() ? List.of(defaultProduct()) : stored;
    }

    public ProductResponse defaultProduct() {
        return new ProductResponse(1L, "Clean Architecture", "Robert C. Martin", "9780134494166",
                BigDecimal.valueOf(599), 20, "https://example.com/clean-architecture.jpg", "Technology");
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getAuthor(),
                product.getIsbn(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),
                product.getCategory() != null ? product.getCategory().getName() : "General"
        );
    }
}

