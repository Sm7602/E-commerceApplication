package com.example.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.dao.ProductRepository;
import com.example.dao.UserRepository;
import com.example.dao.WishlistRepository;
import com.example.dto.review.ReviewResponse;
import com.example.dto.wishlist.WishlistRequest;
import com.example.dto.wishlist.WishlistResponse;
import com.example.entity.Customer;
import com.example.entity.Product;
import com.example.entity.Review;
import com.example.entity.User;
import com.example.entity.Wishlist;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    
    private WishlistResponse convertToResponse(Wishlist wishlist) {

        return WishlistResponse.builder()
        		    .id(wishlist.getId())
                .active(wishlist.getActive())
                .createdAt(wishlist.getCreatedAt())
                .updatedAt(wishlist.getUpdatedAt())
                .customer(wishlist.getCustomer())
                .product(wishlist.getProduct())
                .build();
    }

    public WishlistResponse addToWishlist(WishlistRequest request) {
    	System.out.println("WishlistService.addToWishlist()");
    	Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
        Wishlist wishlist =Wishlist.builder()
        		    .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .customer(customer)
                .product(product)
                .build();
        wishlist= wishlistRepository.save(wishlist);
        return convertToResponse(wishlist);
    }

    public List<WishlistResponse> getWishlist(long customerId) {
    	System.out.println("WishlistService.getWishlist()");
    	Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
        
        return wishlistRepository.findByCustomer(customer)
        		                     .stream()
                                 .map(this::convertToResponse)
                                 .toList();
    }

    public void removeFromWishlist(WishlistRequest request) {
        System.out.println("WishlistService.removeFromWishlist()");
      	Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
        Customer wishlist = wishlistRepository.findByCustomerAndProduct(customer, product).orElseThrow(() ->
                        new RuntimeException("Wishlist item not found"));
        wishlistRepository.delete(wishlist);
    }
}