package com.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Customers")
public class Customer{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  
    private boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    private Integer totalOrders;

    private Integer totalReviews;

    private Integer totalWishlistItems;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "cart_id")
	    private Cart cart;
	    
	    @OneToMany(mappedBy = "Customer")
	    private List<Order> orders;
	    
	    @OneToMany(mappedBy = "Customer")
	    private List<Review> reviews;
	    
	    @OneToMany(mappedBy = "Customer")
	    private List<Wishlist> wishlistItems;
	    
	    @OneToOne
	    @JoinColumn(name="user_id")
	    private User user;


}
