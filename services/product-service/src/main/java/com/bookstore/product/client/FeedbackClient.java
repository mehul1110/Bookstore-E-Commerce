package com.bookstore.product.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "FEEDBACK-SERVICE", path = "/api/feedback")
public interface FeedbackClient {
}

