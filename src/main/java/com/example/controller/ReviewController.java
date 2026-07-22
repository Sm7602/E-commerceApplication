package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.review.ReviewRequest;
import com.example.dto.review.ReviewResponse;
import com.example.dto.review.ReviewUpdateRequest;
import com.example.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ReviewResponse createReview(@Valid @RequestBody ReviewRequest request) {
        System.out.println("ReviewController.createReview()");
        return reviewService.createReview(request);
    }

    @GetMapping("/product/{productId}")
    public List<ReviewResponse> getReviewsByProduct( @PathVariable Long productId) {
        System.out.println("ReviewController.getReviewsByProduct()");
        return reviewService.getReviewsByProduct(productId);
    }

    @PutMapping("/{reviewId}")
    public ReviewResponse updateReview(@PathVariable Long reviewId,@Valid @RequestBody ReviewUpdateRequest request) {
        System.out.println("ReviewController.updateReview()");
        return reviewService.updateReview(reviewId,request);
    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview( @PathVariable Long reviewId) {
        System.out.println("ReviewController.deleteReview()");
        reviewService.deleteReview(reviewId);
        return "Review deleted successfully";
    }
}