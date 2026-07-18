package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.auth.AdminRegisterRequest;
import com.example.dto.auth.AuthenticationResponse;
import com.example.dto.auth.CustomerRegisterRequest;
import com.example.dto.auth.LoginRequest;
import com.example.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthgenticationController {
	
	private final AuthenticationService service;

	@PostMapping("/registerAdmin")
	public ResponseEntity<AuthenticationResponse> registerAdmin(AdminRegisterRequest request) {
		System.out.println("AuthgenticationController.registerAdmin()");
		return ResponseEntity.ok(service.registerAdmin(request));
	}
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<AuthenticationResponse> registerCustomer(CustomerRegisterRequest request) {
		System.out.println("AuthgenticationController.registerAdmin()");
		return ResponseEntity.ok(service.registerCustomer(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody LoginRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
}

