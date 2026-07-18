package com.example.dto.review;

import java.time.LocalDateTime;

import com.example.entity.Customer;
import com.example.entity.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {

	    private Long id;

	    private Integer rating;

	    private String comment;

	    private Boolean active;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    private Customer customer;

	    private Product product;
}
