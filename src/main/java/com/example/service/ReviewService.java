package com.example.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ProductRepository;
import com.example.dao.ReviewRepository;
import com.example.dao.UserRepository;
import com.example.entity.Product;
import com.example.entity.Review;
import com.example.entity.User;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Review createReview(Long userId, Long productId,Integer rating,String comment) {
    	 System.out.println("ReviewService.createReview()");
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Review review = new Review();

        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProduct(Long productId) {
   	 System.out.println("ReviewService.getReviewsByProduct()");
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
        return reviewRepository.findByProduct(product);
    }

    public Review updateReview( Long reviewId, Integer rating,String comment) {
      	 System.out.println("ReviewService.updateReview");
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));
        review.setRating(rating);
        review.setComment(comment);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
     	 System.out.println("ReviewService.deleteReview");
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }
}