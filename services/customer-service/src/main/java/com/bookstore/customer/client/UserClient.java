package com.bookstore.customer.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-SERVICE", path = "/api/users")
public interface UserClient {
}

