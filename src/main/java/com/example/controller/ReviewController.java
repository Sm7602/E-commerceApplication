package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Review;
import com.example.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review createReview( @RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer rating,@RequestParam String comment) {
        System.out.println("ReviewController.createReview()");
        return reviewService.createReview( userId, productId,rating,comment);
    }

    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProduct( @PathVariable Long productId) {
        System.out.println("ReviewController.getReviewsByProduct()");
        return reviewService.getReviewsByProduct(productId);
    }

    @PutMapping("/{reviewId}")
    public Review updateReview(  @PathVariable Long reviewId, @RequestParam Integer rating,@RequestParam String comment) {
        System.out.println("ReviewController.updateReview()");
        return reviewService.updateReview(reviewId,rating,comment);
    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview( @PathVariable Long reviewId) {
        System.out.println("ReviewController.deleteReview()");
        reviewService.deleteReview(reviewId);
        return "Review deleted successfully";
    }
}