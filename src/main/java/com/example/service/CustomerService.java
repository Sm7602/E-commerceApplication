package com.example.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.CustomerRepository;
import com.example.dto.customer.CustomerRequest;
import com.example.dto.customer.CustomerResponse;
import com.example.dto.customer.CustomerUpdateRequest;
import com.example.entity.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private CustomerResponse convertToResponse(Customer customer) {

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .dateOfBirth(customer.getDateOfBirth())
                .gender(customer.getGender())
                .profileImage(customer.getProfileImage())
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .city(customer.getCity())
                .state(customer.getState())
                .country(customer.getCountry())
                .pincode(customer.getPincode())
                .active(customer.isActive())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .userId(customer.getUser().getId())
                .email(customer.getUser().getEmail())
                .cart(customer.getCart())
                .totalOrders(customer.getTotalOrders())
                .totalReviews(customer.getTotalReviews())
                .totalWishlistItems(customer.getTotalWishlistItems())
                .orders(customer.getOrders())
                .reviews(customer.getReviews())
                .wishlistItems(customer.getWishlistItems())
                .build();
    }
	
	public CustomerResponse saveCustomer(CustomerRequest request) {
		System.out.println("CustomerService.saveCustomer()");
		Customer customer=Customer.builder()
				.firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .profileImage(request.getProfileImage())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .pincode(request.getPincode())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .build();
		
		customer=customerRepository.save(customer);
		return convertToResponse(customer);
	
	}
	
	public List<CustomerResponse> getAllCustomer() {
		System.out.println("CustomerService.getAllCustomer()");
		return customerRepository.findAll()
				.stream()
	            .map(this::convertToResponse)
	            .toList();
	}
	
	public CustomerResponse getCustomerById(long id) {
		System.out.println("CustomerService.getCustomerById() running.......");
		Customer customer= customerRepository.findById(id).orElseThrow(() ->
        new RuntimeException("User not found"));
		
		return convertToResponse(customer);
	}
	
	public CustomerResponse  updateUser(long id,CustomerUpdateRequest request) {
		System.out.println("CustomerService.updateuser() running.......");
		 Customer existingCustomer = customerRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found with id: " + id));

		 existingCustomer.setFirstName(request.getFirstName());
		 existingCustomer.setLastName(request.getLastName());
		 existingCustomer.setPhoneNumber(request.getPhoneNumber());
		 existingCustomer.setProfileImage(request.getProfileImage());
		 existingCustomer.setDateOfBirth(request.getDateOfBirth());
		 existingCustomer.setGender(request.getGender());
		 existingCustomer.setAddressLine1(request.getAddressLine1());
		 existingCustomer.setAddressLine2(request.getAddressLine2());
		 existingCustomer.setCity(request.getCity());
		 existingCustomer.setState(request.getState());
		 existingCustomer.setCountry(request.getCountry());
		 existingCustomer.setPincode(request.getPincode());
		 existingCustomer.setUpdatedAt(LocalDateTime.now());

		 existingCustomer=customerRepository.save(existingCustomer);
			return convertToResponse(existingCustomer);
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
