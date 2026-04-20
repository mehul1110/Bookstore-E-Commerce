package com.bookstore.feedback.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ORDER-SERVICE", path = "/api/orders")
public interface OrderHistoryClient {
}

