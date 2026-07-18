package com.example.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.AdminRepository;
import com.example.dao.CartRepository;
import com.example.dao.CustomerRepository;
import com.example.dao.UserRepository;
import com.example.dto.auth.AdminRegisterRequest;
import com.example.dto.auth.AuthenticationResponse;
import com.example.dto.auth.CustomerRegisterRequest;
import com.example.dto.auth.LoginRequest;
import com.example.entity.Admin;
import com.example.entity.Cart;
import com.example.entity.Customer;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.security.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	
	private final CartRepository cartRepository;
	
	private final CustomerRepository customerRepository;
	
	private final JwtService jwtService;
	
	private final AdminRepository adminRepository;

	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	
	
	public AuthenticationResponse registerAdmin(AdminRegisterRequest request) {

        System.out.println("AuthenticationService.registerAdmin()");

		 if (userRepository.findByEmail(request.getEmail()).isPresent()) {
		        throw new RuntimeException("Email is already registered. Please login.");
		    }
		
	    User user = User.builder()
	    		    .firstname(request.getFirstName())
	            .lastname(request.getLastName())
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.ADMIN)
	            .build();

	    user = userRepository.save(user);

	    Admin admin = Admin.builder()
	    		   .firstName(request.getFirstName())
	           .lastName(request.getLastName())
               .phoneNumber(request.getPhoneNumber())
               .createdAt(LocalDateTime.now())
               .updatedAt(LocalDateTime.now())
               .active(true)
               .user(user)
	            .build();

	    adminRepository.save(admin);

	    String jwtToken = jwtService.generateToken(new HashMap<>(), user);

	    return AuthenticationResponse.builder()
   		    .token(jwtToken)
   	        .tokenType("Bearer")
   	        .userId(user.getId())
   	        .email(user.getEmail())
   	        .role(user.getRole().name())
   	        .message("Registration successful")
   	        .build();
	}
	
	public AuthenticationResponse registerCustomer(CustomerRegisterRequest request) {
        System.out.println("AuthenticationService.registerCustomer()");
       
		 if (userRepository.findByEmail(request.getEmail()).isPresent()) {
		        throw new RuntimeException("Email is already registered. Please login.");
		    }
		
	    User user = User.builder()
	            .firstname(request.getFirstName())
	            .lastname(request.getLastName())
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.CUSTOMER)
	            .build();

	    user = userRepository.save(user);

	    Cart cart = Cart.builder()
				.active(true)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.totalAmount(BigDecimal.ZERO)
				.build();

		cartRepository.save(cart);



		 Customer customer = Customer.builder()
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
		            .active(true)
		            .createdAt(LocalDateTime.now())
		            .updatedAt(LocalDateTime.now())
		            .cart(cart)
		            .user(user)
		            .build();

		customerRepository.save(customer);


		
		cart.setCustomer(customer);
		cartRepository.save(cart);

	    String jwtToken = jwtService.generateToken(new HashMap<>(), user);

	    return AuthenticationResponse.builder()
	    		    .token(jwtToken)
	    	        .tokenType("Bearer")
	    	        .userId(user.getId())
	    	        .email(user.getEmail())
	    	        .role(user.getRole().name())
	    	        .message("Registration successful")
	    	        .build();
	}
	
	public AuthenticationResponse authenticate(LoginRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail()
						,request.getPassword()));
		
		var user =userRepository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken =jwtService.generaTetoken(user);
		
		return AuthenticationResponse.builder()
    		    .token(jwtToken)
    	        .tokenType("Bearer")
    	        .userId(user.getId())
    	        .email(user.getEmail())
    	        .role(user.getRole().name())
    	        .message("Login successful")
    	        .build();
	}

}


