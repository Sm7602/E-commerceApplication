package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.customer.CustomerRequest;
import com.example.dto.customer.CustomerResponse;
import com.example.dto.customer.CustomerUpdateRequest;
import com.example.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping()
	public CustomerResponse saveCustomer(@Valid @RequestBody CustomerRequest request){
		System.out.println("CustomerController.requestBodyInfo()");
		return customerService.saveCustomer(request);
	}
	
	@GetMapping()
	public List<CustomerResponse> getAllCustomer() {
		System.out.println("CustomerController.getAllUser()");
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable long id) {
		System.out.println("CustomerController.getUserById() running.......");
		return customerService.getCustomerById(id);
	}
	
	@PutMapping("/{id}")
	public CustomerResponse  updateCustomer(@PathVariable long id,@Valid @RequestBody CustomerUpdateRequest request) {
		System.out.println("CustomerController.updateUser() running.......");
		return customerService.updateCustomer(id,request);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomerById(@PathVariable long id) {
		System.out.println("CustomerController.deleteUserById() running.......");
		customerService.deleteCustomerById(id);
	}
	
	@DeleteMapping("/deleteAllUsers")
	public void deleteallCustomer() {
		System.out.println("CustomerController.deleteAllUser() running.......");
		customerService.deleteallCustomer();
	}
	

}
