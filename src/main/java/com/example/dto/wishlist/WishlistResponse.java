package com.example.dto.wishlist;

import java.time.LocalDateTime;

import com.example.entity.Customer;
import com.example.entity.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistResponse {

	    private Long id;

	    private Boolean active;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;


	    private Customer customer;


	    private Product product;
}
