package com.example.dto.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.Cart;
import com.example.entity.Order;
import com.example.entity.Review;
import com.example.entity.Wishlist;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

	    private Long customerId;

	    private String firstName;

	    private String lastName;

	    private String phoneNumber;

	    private LocalDate dateOfBirth;

	    private String gender;

	    private String profileImage;

	    private String addressLine1;

	    private String addressLine2;

	    private String city;

	    private String state;

	    private String country;

	    private String pincode;

	    private Boolean active;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;


	    private Long userId;

	    private String email;


	    private Cart cart;


	    private Integer totalOrders;

	    private Integer totalReviews;

	    private Integer totalWishlistItems;


	    

	    private List<Order> orders;

	    private List<Review> reviews;

	    private List<Wishlist> wishlistItems;

}
