package com.example.dto.auth;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterRequest extends BaseRegisterRequest {

	 @NotBlank(message = "First name is required.")
	    private String firstName;

	    @NotBlank(message = "Last name is required.")
	    private String lastName;

	    @Pattern(
	            regexp = "^[0-9]{10}$",
	            message = "Phone number must contain 10 digits."
	    )
	    private String phoneNumber;

	    @NotNull(message = "Date of birth is required.")
	    private LocalDate dateOfBirth;

	    @NotBlank(message = "Gender is required.")
	    private String gender;

	    private String profileImage;

	    @NotBlank(message = "Address is required.")
	    private String addressLine1;

	    private String addressLine2;

	    @NotBlank(message = "City is required.")
	    private String city;

	    @NotBlank(message = "State is required.")
	    private String state;

	    @NotBlank(message = "Country is required.")
	    private String country;

	    @Pattern(
	            regexp = "^[0-9]{6}$",
	            message = "Pincode must contain 6 digits."
	    )
	    private String pincode;

	}