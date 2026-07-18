package com.example.dto.customer;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerUpdateRequest {

	
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

	    private Boolean active;
}
