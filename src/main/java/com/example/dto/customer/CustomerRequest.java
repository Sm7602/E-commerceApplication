package com.example.dto.customer;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerRequest {
	 
	    @NotBlank(message = "First Name is required.")
	    private String firstName;

	    @NotBlank(message = "Last Name is required.")
	    private String lastName;

	    @NotBlank(message = "Phone Number is required.")
	    @Pattern(regexp = "^[0-9]{10}$",
	            message = "Phone Number must contain 10 digits.")
	    private String phoneNumber;

	    @NotNull(message = "Date of Birth is required.")
	    private LocalDate dateOfBirth;

	    @NotBlank(message = "Gender is required.")
	    private String gender;

	    private String profileImage;

	    @NotBlank(message = "Address Line 1 is required.")
	    private String addressLine1;

	    private String addressLine2;

	    @NotBlank(message = "City is required.")
	    private String city;

	    @NotBlank(message = "State is required.")
	    private String state;

	    @NotBlank(message = "Country is required.")
	    private String country;

	    @NotBlank(message = "Pincode is required.")
	    private String pincode;

	    @NotNull(message = "User Id is required.")
	    private Long userId;
}
