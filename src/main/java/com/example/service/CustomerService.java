package com.example.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.dao.UserRepository;
import com.example.dto.category.CategoryResponse;
import com.example.dto.customer.CustomerRequest;
import com.example.dto.customer.CustomerResponse;
import com.example.entity.Category;
import com.example.entity.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private CustomerResponse convertToResponse(Customer customer) {

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName()
                .phoneNumber(customer.getPhoneNumber())
                .dateOfBirth(customer.getDateOfBirth())
                .gender(customer.getGender())
                .profileImage(customer.getProfileImage()))
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .city(customer.getCity())
                .state(customer.getState())
                .contry(customer.getCountry())
                .pincode(customer.getPincode())
                .active(customer.isActive())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .userId(customer.getUser().getId())
                .email(customer.getUser().getEmail())
                .cart(customer.getCart())
                .totalOrders(customer.getTotalOrders())
                .totalReviews(customer.getTotalReviews())
                .totalWishlist(customer.getTotalWishlistItems())
                .orders(customer.getOrders())
                .reviews(customer.getReviews())
                .wishlist(customer.getWishlistItems())
                .build();
    }
	
	public CustomerResponse saveUser(CustomerRequest request) {
		System.out.println("CustomerService.saveUser()");
		
	
	}
	
	public List<CustomerResponse> getAllUser() {
		System.out.println("CustomerService.getAllUser()");
		return customerRepository.findAll();
	}
	
	public CustomerResponse getUserById(long id) {
		System.out.println("CustomerService.getUserById() running.......");
		return customerRepository.findById(id).orElseThrow(() ->
        new RuntimeException("User not found"));
	}
	
	public CustomerResponse  updateUser(long id,User updatedUser) {
		System.out.println("CustomerService.updateuser() running.......");
		 User existingUser = customerRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found with id: " + id));

	        existingUser.setFirstname(updatedUser.getFirstname());
	        existingUser.setLastName(updatedUser.getLastName());
	        existingUser.setEmail(updatedUser.getEmail());
	        existingUser.setPassword(updatedUser.getPassword());
	        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
	        existingUser.setProfileImage(updatedUser.getProfileImage());

	        return customerRepository.save(existingUser);
	}
		
	public void deleteUserById(long id) {
		System.out.println("CustomerService.deleteUserById() running.......");
		 Customer customer = customerRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found with id: " + id));
		 customerRepository.delete(customer);
	}
	
	public void deleteallUser() {
		System.out.println("CustomerService.deleteallUser() running.......");
		customerRepository.deleteAll();
	}
	
	

}
