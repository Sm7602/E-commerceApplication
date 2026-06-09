package com.example.entity;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Product {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    private String name;

	    private String description;

	    private BigDecimal price;

	    private Integer stockQuantity;

	    private String brand;

	    private String imageUrl;

	    private Double averageRating;
	    
	    @ManyToOne
	    @JsonIgnore
	    @JoinColumn(name = "category_id")
	    private Category category;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "product")
	    private List<CartItem> cartItems;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "product")
	    private List<OrderItem> orderItems;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "product")
	    private List<Review> reviews;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "product")
	    private List<Wishlist> wishlistItems;
	    
	    
}
