package com.bookstore.wishlist.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "PRODUCT-SERVICE", path = "/api/products")
public interface ProductLookupClient {
}

