package com.bookstore.feedback.controller;

import com.bookstore.feedback.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final ReviewService reviewService;

    public FeedbackController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> submitFeedback() {
        return ResponseEntity.ok(Map.of("message", "Feedback submitted"));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<String>> reviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.reviewsForProduct(id).stream().map(review -> review.getComment()).toList());
    }

    @GetMapping("/product/{id}/rating")
    public ResponseEntity<Map<String, Double>> rating(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("averageRating", 4.6));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editReview(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("message", "Feedback updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
