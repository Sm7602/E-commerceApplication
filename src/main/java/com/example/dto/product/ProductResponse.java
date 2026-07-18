package com.example.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.Category;
import com.example.entity.Review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

	    private Long id;

	    private String name;

	    private String description;

	    private BigDecimal price;

	    private Double discountPercentage;

	    private BigDecimal discountedPrice;

	    private Integer stockQuantity;

	    private String brand;

	    private String sku;

	    private Boolean featured;

	    private Integer totalSold;

	    private String imageUrl;

	    private Double averageRating;

	    private Boolean active;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;


	    private Category category;



	    private Integer totalReviews;

	    private Integer totalWishlistUsers;

	    private Integer totalCartUsers;



	    private List<Review> reviews;
}
