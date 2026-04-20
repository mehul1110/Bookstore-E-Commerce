package com.bookstore.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "PRODUCT-SERVICE", path = "/api/products")
public interface ProductServiceClient {

    @PutMapping("/{id}")
    void updateProduct(@PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable("id") Long id);
}

