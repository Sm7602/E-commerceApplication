package com.example.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ProductRepository;
import com.example.dao.ReviewRepository;
import com.example.dto.review.ReviewRequest;
import com.example.dto.review.ReviewResponse;
import com.example.dto.review.ReviewUpdateRequest;
import com.example.dao.CustomerRepository;
import com.example.entity.Customer;
import com.example.entity.Product;
import com.example.entity.Review;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    
    private ReviewResponse convertToResponse(Review review) {

        return ReviewResponse.builder()
        		    .id(review.getId())
        		    .rating(review.getRating())
        		    .comment(review.getComment())
                .active(review.getActive())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .customer(review.getCustomer())
                .product(review.getProduct())
                .build();
    }

    public ReviewResponse createReview(ReviewRequest request) {
    	 System.out.println("ReviewService.createReview()");
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Review review = Review.builder()
        		.rating(request.getRating())
    		    .comment(request.getComment())
    		    .active(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .customer(customer)
            .product(product)
            .build();

       
        review= reviewRepository.save(review);
        return convertToResponse(review);
    }

    public List<ReviewResponse> getReviewsByProduct(Long productId) {
   	 System.out.println("ReviewService.getReviewsByProduct()");
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
        return reviewRepository.findByProduct(product)
        		                   .stream()
                               .map(this::convertToResponse)
                               .toList();
    }

    public ReviewResponse updateReview( Long reviewId,ReviewUpdateRequest request) {
      	 System.out.println("ReviewService.updateReview");
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setUpdatedAt(LocalDateTime.now());

        review= reviewRepository.save(review);
        return convertToResponse(review);
    }

    public void deleteReview(Long reviewId) {
     	 System.out.println("ReviewService.deleteReview");
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }
}